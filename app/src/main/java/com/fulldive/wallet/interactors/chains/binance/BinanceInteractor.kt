package com.fulldive.wallet.interactors.chains.binance

import com.fulldive.wallet.di.modules.DefaultInteractorsModule
import com.fulldive.wallet.extensions.combine
import com.fulldive.wallet.extensions.concat
import com.fulldive.wallet.extensions.or
import com.fulldive.wallet.interactors.chains.StationInteractor
import com.fulldive.wallet.rx.AppSchedulers
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Completable
import io.reactivex.Single
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.dao.Balance
import wannabit.io.cosmostaion.model.NodeInfo
import java.math.BigDecimal
import javax.inject.Inject

@ProvidedBy(DefaultInteractorsModule::class)
class BinanceInteractor @Inject constructor(
    private val binanceRepository: BinanceRepository,
    private val stationInteractor: StationInteractor
) {
    fun update(account: Account, chain: BaseChain): Completable {
        return Completable
            .concatArray(
                updateTokensList().subscribeOn(AppSchedulers.io()),
                updateTickersList().subscribeOn(AppSchedulers.io()),
                updateFees().subscribeOn(AppSchedulers.io())
            )
            .andThen(updateAccount(account))
            .andThen(updateNodeInfo())
            .flatMapCompletable { nodeInfo ->
                stationInteractor
                    .updateStationParams(
                        chain,
                        nodeInfo.network
                    )
                    .onErrorComplete() // XXX: onErrorComplete()
            }
    }

    fun updateTokensList(): Completable {
        return Single
            .zip(
                binanceRepository.requestTokens(DEFAULT_LIMIT).subscribeOn(AppSchedulers.io()),
                binanceRepository.requestMiniTokens(DEFAULT_LIMIT).subscribeOn(AppSchedulers.io()),
                ::concat
            )
            .flatMapCompletable(binanceRepository::setTokens)
    }

    fun updateTickersList(): Completable {
        return Single
            .zip(
                binanceRepository.requestTickers().subscribeOn(AppSchedulers.io()),
                binanceRepository.requestMiniTickers().subscribeOn(AppSchedulers.io()),
                ::concat
            )
            .flatMapCompletable(binanceRepository::setTickers)
    }

    fun updateFees(): Completable {
        return binanceRepository
            .requestFees()
            .flatMapCompletable(binanceRepository::setFees)
    }

    fun updateAccount(account: Account): Completable {
        return binanceRepository
            .requestAccount(account.address)
            .map { accountInfo ->
                combine(
                    Account().apply {
                        id = account.id
                        address = accountInfo.address
                        sequenceNumber = accountInfo.sequence.toInt()
                        accountNumber = accountInfo.account_number.toInt()
                    },
                    accountInfo
                        .balances
                        ?.map { coin ->
                            Balance().apply {
                                accountId = account.id
                                symbol = coin.symbol
                                balance = BigDecimal(coin.free)
                                locked = BigDecimal(coin.locked)
                                frozen = BigDecimal(coin.frozen)
                                fetchTime = System.currentTimeMillis()
                            }
                        }
                        .or(emptyList())
                )
            }
            .flatMapCompletable { (account, balances) ->
                binanceRepository
                    .setAccount(account)
                    .andThen(binanceRepository.setAccountBalances(account.id, balances))
            }
    }

    fun updateNodeInfo(): Single<NodeInfo> {
        return binanceRepository
            .requestNodeInfo()
            .flatMap { nodeInfo ->
                binanceRepository
                    .setNodeInfo(nodeInfo)
                    .toSingleDefault(nodeInfo.node_info)
            }
    }


    companion object {
        private const val DEFAULT_LIMIT = 3000
    }
}