package com.fulldive.wallet.interactors.chains.okex

import com.fulldive.wallet.di.modules.DefaultInteractorsModule
import com.fulldive.wallet.interactors.chains.StationInteractor
import com.fulldive.wallet.rx.AppSchedulers
import com.joom.lightsaber.ProvidedBy
import io.reactivex.Completable
import io.reactivex.Single
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.model.NodeInfo
import javax.inject.Inject

@ProvidedBy(DefaultInteractorsModule::class)
class OkexInteractor @Inject constructor(
    private val okexRepository: OkexRepository,
    private val stationInteractor: StationInteractor
) {
    fun update(account: Account, chain: BaseChain): Completable {
        return Completable
            .concatArray(
                updateValidatorsList().subscribeOn(AppSchedulers.io()),
                updateTickersList().subscribeOn(AppSchedulers.io())
                    .onErrorComplete(),  //XXX: remove
                updateTokensList().subscribeOn(AppSchedulers.io())
            )
            .andThen(
                Completable.concatArray(
                    updateAccountBalance(account).subscribeOn(AppSchedulers.io()),
                    updateUnbonding(account).subscribeOn(AppSchedulers.io()),
                    updateStakingInfo(account).subscribeOn(AppSchedulers.io())
                )
            )
            .andThen(updateNodeInfo())
            .flatMapCompletable { nodeInfo ->
                stationInteractor
                    .updateStationParams(
                        chain,
                        nodeInfo.network
                    )
            }

    }

    fun updateValidatorsList(): Completable {
        return okexRepository.updateValidatorsList()
    }

    fun updateTickersList(): Completable {
        return okexRepository.updateTickersList()
    }

    fun updateTokensList(): Completable {
        return okexRepository.updateTokensList()
    }

    fun updateAccountBalance(account: Account): Completable {
        return okexRepository.updateAccountBalance(account)
    }

    fun updateUnbonding(account: Account): Completable {
        return okexRepository.updateUnbonding(account)
    }

    fun updateStakingInfo(account: Account): Completable {
        return okexRepository.updateStakingInfo(account)
    }


    fun updateAccount(account: Account): Completable {
        return okexRepository
            .requestAccount(account.address)
            .map { accountInfo ->
                Account().apply {
                    id = account.id
                    if (accountInfo.type == BaseConstant.COSMOS_AUTH_TYPE_OKEX_ACCOUNT) {
                        address = accountInfo.value.eth_address
                        sequenceNumber = accountInfo.value.sequence.toInt()
                        accountNumber = accountInfo.value.account_number.toInt()
                    }
                }
            }
            .flatMapCompletable(okexRepository::setAccount)
    }

    fun updateNodeInfo(): Single<NodeInfo> {
        return okexRepository
            .requestNodeInfo()
            .flatMap { nodeInfo ->
                okexRepository
                    .setNodeInfo(nodeInfo)
                    .toSingleDefault(nodeInfo.node_info)
            }
    }
}