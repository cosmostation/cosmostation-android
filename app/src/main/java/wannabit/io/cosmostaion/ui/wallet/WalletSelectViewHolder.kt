package wannabit.io.cosmostaion.ui.wallet

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.fadeInAnimation
import wannabit.io.cosmostaion.common.fadeOutAnimation
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
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

            if (chain.isEvmCosmos()) {
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
                if (chain.supportEvm) {
                    if (chain.web3j == null) {
                        respondLayout.visibility = View.VISIBLE
                        chainBalance.visibility = View.GONE
                        chainDenom.visibility = View.GONE
                        chainAssetCnt.visibility = View.GONE

                    } else {
                        val availableAmount = chain.evmRpcFetcher?.evmBalance?.movePointLeft(18)
                            ?.setScale(18, RoundingMode.DOWN)
                        chainBalance.text = formatAmount(availableAmount.toString(), 18)
                        chainDenom.text = chain.coinSymbol
                        BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                            chainDenom.setTextColor(asset.assetColor())
                        }
                        if (BigDecimal.ZERO < chain.evmRpcFetcher?.evmBalance) {
                            chainAssetCnt.text = "1 Coins"
                        } else {
                            chainAssetCnt.text = "0 Coins"
                        }
                        skeletonChainValue.visibility = View.GONE
                    }

                } else {
                    if (chain.grpcFetcher?.cosmosBalances == null) {
                        respondLayout.visibility = View.VISIBLE
                        chainBalance.visibility = View.GONE
                        chainDenom.visibility = View.GONE
                        chainAssetCnt.visibility = View.GONE

                    } else {
                        BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                            val availableAmount = chain.grpcFetcher?.balanceAmount(chain.stakeDenom)
                                ?.movePointLeft(asset.decimals ?: 6)
                            chainBalance.text =
                                formatAmount(availableAmount.toString(), asset.decimals ?: 6)
                            chainDenom.text = asset.symbol
                            chainDenom.setTextColor(asset.assetColor())
                        }

                        val cnt = chain.grpcFetcher?.cosmosBalances?.count() ?: 0
                        chainAssetCnt.text = "$cnt Coins"
                        skeletonChainValue.visibility = View.GONE
                    }
                }
                respondLayout.visibility = View.GONE
                chainBalance.visibility = View.VISIBLE
                chainDenom.visibility = View.VISIBLE
                chainAssetCnt.visibility = View.VISIBLE

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

    fun bind(
        account: BaseAccount,
        line: CosmosLine,
        selectedCosmosTags: MutableList<String>,
        listener: WalletSelectAdapter.SelectListener
    ) {
        binding.apply {
            chainImg.setImageResource(line.logo)
            chainName.text = line.name.uppercase()

            if (account.type == BaseAccountType.MNEMONIC) {
//                chainPath.text = line.getHDPath(account.lastHDPath)
                if (!line.isDefault) {
                    chainLegacy.visibility = View.VISIBLE
                    chainTypeBadge.visibility = View.VISIBLE
                    when (line.tag) {
                        "okt996_Keccak" -> {
                            chainTypeBadge.text = context.getString(R.string.str_ethsecp256k1)
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

            } else {
//                chainPath.text = line.address
                if (!line.isDefault) {
                    chainLegacy.visibility = View.VISIBLE
                    chainTypeBadge.visibility = View.VISIBLE
                    when (line.tag) {
                        "okt996_Keccak" -> {
                            chainTypeBadge.text = context.getString(R.string.str_ethsecp256k1)
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
            }
            updateView(line, selectedCosmosTags)

            if (line.fetched) {
                var cnt = 0
                line.stakeDenom?.let { denom ->
//                    if (line is ChainOkt996Keccak) {
//                        val availableAmount = line.lcdBalanceAmount(line.stakeDenom)
//                        chainBalance.text = formatAmount(availableAmount.toString(), 18)
//                        chainDenom.text = line.stakeDenom?.uppercase()
//                        chainDenom.setTextColor(Color.parseColor("#ffffff"))
//                        cnt = line.oktLcdAccountInfo?.value?.coins?.size ?: 0
//
//                        chainBalance.visibility = View.VISIBLE
//                        chainDenom.visibility = View.VISIBLE
//                        chainAssetCnt.visibility = View.VISIBLE
//
//                    } else {
//                        if (line.cosmosBalances == null) {
//                            chainBalance.visibility = View.GONE
//                            chainDenom.visibility = View.GONE
//                            respondLayout.visibility = View.VISIBLE
//                            chainAssetCnt.visibility = View.GONE
//
//                        } else {
//                            chainBalance.visibility = View.VISIBLE
//                            chainDenom.visibility = View.VISIBLE
//                            respondLayout.visibility = View.GONE
//                            chainAssetCnt.visibility = View.VISIBLE
//
//                            BaseData.getAsset(line.apiName, denom)?.let { asset ->
//                                val availableAmount =
//                                    line.balanceAmount(denom).movePointLeft(asset.decimals ?: 6)
//                                chainBalance.text =
//                                    formatAmount(availableAmount.toString(), asset.decimals ?: 6)
//                                chainDenom.text = asset.symbol
//                                chainDenom.setTextColor(asset.assetColor())
//                            }
//                            cnt = line.cosmosBalances?.count() ?: 0
//                        }
//                    }
                    chainAssetCnt.text = "$cnt Coins"
                    skeletonChainValue.visibility = View.GONE
                }
            }

            selectView.setOnClickListener {
                if (line.tag == "cosmos118") {
                    return@setOnClickListener
                }
                if (selectedCosmosTags.contains(line.tag)) {
                    selectedCosmosTags.removeIf { it == line.tag }
                } else {
                    selectedCosmosTags.add(line.tag)
                }
                updateView(line, selectedCosmosTags)
                listener.select(selectedCosmosTags)
            }
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