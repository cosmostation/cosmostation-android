package wannabit.io.cosmostaion.ui.wallet

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.OktFetcher
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.fadeInAnimation
import wannabit.io.cosmostaion.common.fadeOutAnimation
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.databinding.ItemWalletSelectBinding
import java.math.BigDecimal
import java.math.RoundingMode

class WalletSelectViewHolder(
    val context: Context, private val binding: ItemWalletSelectBinding
) : RecyclerView.ViewHolder(binding.root) {

    private val handler = Handler(Looper.getMainLooper())
    private val starEvmAddressAnimation = object : Runnable {
        override fun run() {
            binding.apply {
                if (chainAddress.visibility == View.VISIBLE) {
                    fadeOutAnimation(chainAddress)
                    fadeInAnimation(chainEvmAddress)
                } else {
                    fadeOutAnimation(chainEvmAddress)
                    fadeInAnimation(chainAddress)
                }
            }
            handler.postDelayed(this, 5000)
        }
    }

    fun mainnetBind(
        chain: BaseChain,
        selectedTags: MutableList<String>,
        selectListener: WalletSelectAdapter.SelectListener
    ) {
        binding.apply {
            chainImg.setImageResource(chain.logo)
            chainName.text = chain.name.uppercase()

            if (chain.isEvmCosmos() || chain is ChainOktEvm) {
                chainAddress.text = chain.address
                chainEvmAddress.text = chain.evmAddress
                chainAddress.visibility = View.INVISIBLE
                chainEvmAddress.visibility = View.VISIBLE

                handler.removeCallbacks(starEvmAddressAnimation)
                handler.postDelayed(starEvmAddressAnimation, 5000)

            } else if (chain.isCosmos()) {
                chainAddress.text = chain.address
                chainAddress.visibility = View.VISIBLE
                chainEvmAddress.visibility = View.GONE

                handler.removeCallbacks(starEvmAddressAnimation)

            } else {
                chainAddress.text = chain.evmAddress
                chainAddress.visibility = View.VISIBLE
                chainEvmAddress.visibility = View.GONE

                handler.removeCallbacks(starEvmAddressAnimation)
            }
            updateView(chain, selectedTags)

            if (chain.fetched) {
                val cnt: Int
                skeletonChainValue.visibility = View.GONE
                if (chain.isEvmCosmos()) {
                    if (chain.grpcFetcher?.cosmosBalances == null) {
                        respondLayout.visibility = View.VISIBLE
                        chainBalance.visibility = View.GONE
                        chainDenom.visibility = View.GONE
                        chainAssetCnt.visibility = View.GONE
                        return
                    }

                } else if (chain.isCosmos()) {
                    if (chain is ChainOktEvm) {
                        if (chain.oktFetcher?.lcdAccountInfo?.isJsonNull == true) {
                            respondLayout.visibility = View.VISIBLE
                            chainBalance.visibility = View.GONE
                            chainDenom.visibility = View.GONE
                            chainAssetCnt.visibility = View.GONE
                            return
                        }

                    } else if (chain is ChainOkt996Keccak) {
                        if (chain.oktFetcher?.lcdAccountInfo?.isJsonNull == true) {
                            respondLayout.visibility = View.VISIBLE
                            chainBalance.visibility = View.GONE
                            chainDenom.visibility = View.GONE
                            chainAssetCnt.visibility = View.GONE
                            return
                        }

                    } else {
                        if (chain.grpcFetcher?.cosmosBalances == null) {
                            respondLayout.visibility = View.VISIBLE
                            chainBalance.visibility = View.GONE
                            chainDenom.visibility = View.GONE
                            chainAssetCnt.visibility = View.GONE
                            return
                        }
                    }

                } else {
                    if (chain.web3j == null) {
                        respondLayout.visibility = View.VISIBLE
                        chainBalance.visibility = View.GONE
                        chainDenom.visibility = View.GONE
                        chainAssetCnt.visibility = View.GONE
                        return
                    }
                }
                respondLayout.visibility = View.GONE
                chainBalance.visibility = View.VISIBLE
                chainDenom.visibility = View.VISIBLE
                chainAssetCnt.visibility = View.VISIBLE

                if (!chain.isDefault) {
                    chainLegacy.visibility = View.VISIBLE
                    chainTypeBadge.visibility = View.VISIBLE
                    when (chain.tag) {
                        "okt996_Keccak" -> {
                            chainTypeBadge.text = context.getString(R.string.str_keccak256)
                        }

                        "okt996_Secp" -> {
                            chainTypeBadge.text = context.getString(R.string.str_secp256k1)
                        }

                        else -> {
                            chainTypeBadge.visibility = View.GONE
                        }
                    }

                } else {
                    chainLegacy.visibility = View.GONE
                    chainTypeBadge.visibility = View.GONE
                }

                if (chain.isEvmCosmos()) {
                    BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                        val availableAmount = chain.grpcFetcher?.balanceAmount(chain.stakeDenom)
                            ?.movePointLeft(asset.decimals ?: 6)
                        chainBalance.text =
                            formatAmount(availableAmount.toString(), asset.decimals ?: 6)
                        chainDenom.text = asset.symbol
                        chainDenom.setTextColor(asset.assetColor())
                    }
                    cnt = chain.grpcFetcher?.cosmosBalances?.count() ?: 0

                } else if (chain.isCosmos()) {
                    when (chain) {
                        is ChainOktEvm -> {
                            updateOktInfo(chain, chain.oktFetcher)
                            cnt =
                                chain.oktFetcher?.lcdAccountInfo?.get("value")?.asJsonObject?.get("coins")?.asJsonArray?.size()
                                    ?: 0
                        }

                        is ChainOkt996Keccak -> {
                            updateOktInfo(chain, chain.oktFetcher)
                            cnt =
                                chain.oktFetcher?.lcdAccountInfo?.get("value")?.asJsonObject?.get("coins")?.asJsonArray?.size()
                                    ?: 0
                        }

                        else -> {
                            BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                                val availableAmount =
                                    chain.grpcFetcher?.balanceAmount(chain.stakeDenom)
                                        ?.movePointLeft(asset.decimals ?: 6)
                                chainBalance.text =
                                    formatAmount(availableAmount.toString(), asset.decimals ?: 6)
                                chainDenom.text = asset.symbol
                                chainDenom.setTextColor(asset.assetColor())
                            }
                            cnt = chain.grpcFetcher?.cosmosBalances?.count() ?: 0
                        }
                    }

                } else {
                    val availableAmount = chain.evmRpcFetcher?.evmBalance?.movePointLeft(18)
                        ?.setScale(18, RoundingMode.DOWN)
                    chainBalance.text = formatAmount(availableAmount.toString(), 18)
                    chainDenom.text = chain.coinSymbol
                    BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                        chainDenom.setTextColor(asset.assetColor())
                    }

                    cnt = if (BigDecimal.ZERO < chain.evmRpcFetcher?.evmBalance) {
                        1
                    } else {
                        0
                    }
                }
                chainAssetCnt.text = "$cnt Coins"

                selectView.setOnClickListener {
                    if (selectedTags.contains(chain.tag)) {
                        selectedTags.removeIf { it == chain.tag }
                    } else {
                        selectedTags.add(chain.tag)
                    }
                    updateView(chain, selectedTags)
                    selectListener.select(selectedTags)
                }
            }
        }
    }

    private fun updateOktInfo(chain: BaseChain, oktFetcher: OktFetcher?) {
        binding.apply {
            val availableAmount = oktFetcher?.lcdBalanceAmount(chain.stakeDenom)
            chainBalance.text = formatAmount(availableAmount.toString(), 18)
            chainDenom.text = chain.stakeDenom.uppercase()
            chainDenom.setTextColor(Color.parseColor("#ffffff"))
        }
    }

    private fun updateView(chain: BaseChain, selectedTags: MutableList<String>) {
        binding.apply {
            if (selectedTags.contains(chain.tag)) {
                selectView.setBackgroundResource(R.drawable.item_wallet_select_bg)
            } else {
                selectView.setBackgroundResource(R.drawable.item_bg)
            }
        }
    }
}