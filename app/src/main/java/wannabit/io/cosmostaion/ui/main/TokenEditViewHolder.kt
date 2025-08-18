package wannabit.io.cosmostaion.ui.main

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.testnetClass.ChainGnoTestnet
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.databinding.ItemTokenEditBinding
import java.math.BigDecimal
import java.math.RoundingMode

class TokenEditViewHolder(
    val binding: ItemTokenEditBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        walletViewModel: WalletViewModel, chain: BaseChain, token: Token
    ) {
        binding.apply {
            tokenImg.setTokenImg(token.image)
            tokenImg.clipToOutline = true
            tokenName.text = token.symbol
            tokenAddress.text = token.address

            skeletonTokenAmount.visibility = View.VISIBLE
            skeletonTokenValue.visibility = View.VISIBLE
            tokenAmount.visibility = View.GONE
            tokenValue.visibility = View.GONE

            chain.cosmosFetcher()?.tokens?.firstOrNull { it.chainName == chain.apiName && it.address == token.address }
                ?.let { cw20Token ->
                    if (cw20Token.fetched) {
                        val dpAmount =
                            cw20Token.amount?.toBigDecimal()?.movePointLeft(cw20Token.decimals)
                                ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO

                        skeletonTokenAmount.visibility = View.GONE
                        skeletonTokenValue.visibility = View.GONE
                        tokenAmount.visibility = View.VISIBLE
                        tokenValue.visibility = View.VISIBLE

                        tokenAmount.text = formatAmount(dpAmount.toPlainString(), 6)
                        chain.cosmosFetcher?.let {
                            tokenValue.text = formatAssetValue(it.tokenValue(cw20Token.address))
                        }

                    } else {
                        skeletonTokenAmount.visibility = View.VISIBLE
                        skeletonTokenValue.visibility = View.VISIBLE
                        tokenAmount.visibility = View.GONE
                        tokenValue.visibility = View.GONE
                    }

                } ?: run {
                chain.evmRpcFetcher()?.evmTokens?.firstOrNull { it.chainName == chain.apiName && it.address == token.address }
                    ?.let { evmToken ->
                        if (chain.isSupportMultiCall() && chain.multicallAddress().isNotEmpty()) {
                            skeletonTokenAmount.visibility = View.GONE
                            skeletonTokenValue.visibility = View.GONE
                            tokenAmount.visibility = View.VISIBLE
                            tokenValue.visibility = View.VISIBLE

                            val dpAmount =
                                evmToken.amount?.toBigDecimal()?.movePointLeft(evmToken.decimals)
                                    ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO

                            tokenAmount.text = formatAmount(dpAmount.toPlainString(), 6)
                            tokenValue.text = formatAssetValue(
                                chain.evmRpcFetcher?.tokenValue(evmToken.address) ?: BigDecimal.ZERO
                            )

                        } else {
                            if (evmToken.fetched) {
                                val dpAmount =
                                    evmToken.amount?.toBigDecimal()
                                        ?.movePointLeft(evmToken.decimals)
                                        ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO

                                skeletonTokenAmount.visibility = View.GONE
                                skeletonTokenValue.visibility = View.GONE
                                tokenAmount.visibility = View.VISIBLE
                                tokenValue.visibility = View.VISIBLE

                                tokenAmount.text = formatAmount(dpAmount.toPlainString(), 6)
                                chain.evmRpcFetcher?.let {
                                    tokenValue.text =
                                        formatAssetValue(it.tokenValue(evmToken.address))
                                }

                            } else {
                                skeletonTokenAmount.visibility = View.VISIBLE
                                skeletonTokenValue.visibility = View.VISIBLE
                                tokenAmount.visibility = View.GONE
                                tokenValue.visibility = View.GONE

                                CoroutineScope(Dispatchers.IO).launch {
                                    walletViewModel.erc20Balance(chain, token)
                                }
                            }
                        }

                    } ?: run {
                    (chain as ChainGnoTestnet).gnoRpcFetcher()?.grc20Tokens?.firstOrNull { it.chainName == chain.apiName && it.address == token.address }
                        ?.let { grcToken ->
                            if (token.fetched) {
                                val dpAmount = grcToken.amount?.toBigDecimal()
                                    ?.movePointLeft(grcToken.decimals)
                                    ?.setScale(6, RoundingMode.DOWN) ?: BigDecimal.ZERO

                                skeletonTokenAmount.visibility = View.GONE
                                skeletonTokenValue.visibility = View.GONE
                                tokenAmount.visibility = View.VISIBLE
                                tokenValue.visibility = View.VISIBLE

                                tokenAmount.text = formatAmount(dpAmount.toPlainString(), 6)
                                chain.gnoRpcFetcher?.let {
                                    tokenValue.text =
                                        formatAssetValue(it.grc20TokenValue(token.address))
                                }

                            } else {
                                skeletonTokenAmount.visibility = View.VISIBLE
                                skeletonTokenValue.visibility = View.VISIBLE
                                tokenAmount.visibility = View.GONE
                                tokenValue.visibility = View.GONE

                                CoroutineScope(Dispatchers.IO).launch {
                                    walletViewModel.grc20Balance(chain, token)
                                }
                            }
                        }
                }
            }
        }
    }
}