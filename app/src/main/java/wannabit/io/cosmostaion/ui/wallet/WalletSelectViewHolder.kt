package wannabit.io.cosmostaion.ui.wallet

import android.content.Context
import android.graphics.Color
import android.os.Handler
import android.os.Looper
import android.view.View
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.OktFetcher
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
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

            if (chain is ChainSui || chain is ChainBitCoin84) {
                chainAddress.text = chain.mainAddress
                chainAddress.visibility = View.VISIBLE
                chainEvmAddress.visibility = View.GONE

                handler.removeCallbacks(starEvmAddressAnimation)

            } else if (chain.isEvmCosmos()) {
                chainAddress.text = chain.address
                chainEvmAddress.text = chain.evmAddress
                chainAddress.visibility = View.INVISIBLE
                chainEvmAddress.visibility = View.VISIBLE

                handler.removeCallbacks(starEvmAddressAnimation)
                handler.postDelayed(starEvmAddressAnimation, 5000)

            } else if (chain.supportCosmos()) {
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

            if (chain is ChainBitCoin84) {
                chainLegacy.visibility = View.VISIBLE
                chainTypeBadge.visibility = View.GONE
                when (chain.accountKeyType.pubkeyType) {
                    PubKeyType.BTC_NESTED_SEGWIT -> {
                        chainLegacy.defaultSet()
                        chainLegacy.text = "NESTED SEGWIT"
                    }

                    PubKeyType.BTC_LEGACY -> {
                        chainLegacy.defaultSet()
                        chainLegacy.text = "LEGACY"
                    }

                    else -> {
                        chainLegacy.setBackgroundResource(R.drawable.round_box_bit)
                        chainLegacy.setTextColor(
                            ContextCompat.getColorStateList(
                                context,
                                R.color.color_base01
                            )
                        )
                        chainLegacy.text = "NATIVE SEGWIT"
                    }
                }

            } else {
                chainLegacy.defaultSet()

                if (!chain.isDefault) {
                    chainLegacy.visibility = View.VISIBLE
                    chainLegacy.text = context.getString(R.string.str_old)
                    when (chain.tag) {
                        "okt996_Keccak" -> {
                            chainTypeBadge.visibility = View.VISIBLE
                            chainTypeBadge.text = context.getString(R.string.str_keccak256)
                        }

                        "okt996_Secp" -> {
                            chainTypeBadge.visibility = View.VISIBLE
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

            if (chain.fetched) {
                var cnt = 0
                skeletonChainValue.visibility = View.GONE

                selectView.setOnClickListener {
                    if (selectedTags.contains(chain.tag)) {
                        selectedTags.removeIf { it == chain.tag }
                    } else {
                        selectedTags.add(chain.tag)
                    }
                    updateView(chain, selectedTags)
                    selectListener.select(selectedTags)
                }

                if (chain is ChainBitCoin84) {
                    if (chain.btcFetcher?.bitState == false) {
                        respondLayout.visibility = View.VISIBLE
                        chainBalance.visibility = View.GONE
                        chainDenom.visibility = View.GONE
                        chainAssetCnt.visibility = View.GONE
                        return
                    }

                } else if (chain is ChainSui) {
                    if (chain.suiFetcher?.suiState == false) {
                        respondLayout.visibility = View.VISIBLE
                        chainBalance.visibility = View.GONE
                        chainDenom.visibility = View.GONE
                        chainAssetCnt.visibility = View.GONE
                        return
                    }

                } else if (chain.supportCosmos()) {
                    if (chain is ChainOktEvm) {
                        if (chain.oktFetcher?.oktAccountInfo?.isJsonNull == true) {
                            respondLayout.visibility = View.VISIBLE
                            chainBalance.visibility = View.GONE
                            chainDenom.visibility = View.GONE
                            chainAssetCnt.visibility = View.GONE
                            return
                        }

                    } else {
                        if (chain.cosmosFetcher?.cosmosBalances == null) {
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

                if (chain is ChainBitCoin84) {
                    chain.btcFetcher()?.let { fetcher ->
                        val availableAmount =
                            fetcher.btcBalances.movePointLeft(8).setScale(8, RoundingMode.DOWN)
                        val pendingInputAmount =
                            fetcher.btcPendingInput.movePointLeft(8).setScale(8, RoundingMode.DOWN)
                        val totalAmount = availableAmount.add(pendingInputAmount)
                        chainBalance.text = formatAmount(totalAmount.toString(), 9)
                        chainDenom.text = chain.coinSymbol
                        chainDenom.setTextColor(
                            ContextCompat.getColorStateList(
                                context, R.color.color_base01
                            )
                        )
                        cnt =
                            if (chain.btcFetcher()?.btcBalances == BigDecimal.ZERO && chain.btcFetcher()?.btcPendingInput == BigDecimal.ZERO) 0 else 1
                    }

                } else if (chain is ChainSui) {
                    val availableAmount = chain.suiFetcher()?.let { fetcher ->
                        fetcher.suiBalanceAmount(SUI_MAIN_DENOM)?.movePointLeft(9)
                            ?.setScale(9, RoundingMode.DOWN)
                    }
                    chainBalance.text = formatAmount(availableAmount.toString(), 9)
                    chainDenom.text = chain.coinSymbol
                    chainDenom.setTextColor(
                        ContextCompat.getColorStateList(
                            context, R.color.color_base01
                        )
                    )
                    cnt = chain.suiFetcher?.suiBalances?.size ?: 0

                } else if (chain.supportCosmos()) {
                    if (chain is ChainOktEvm) {
                        updateOktInfo(chain, chain.oktFetcher)
                        cnt =
                            chain.oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get("coins")?.asJsonArray?.size()
                                ?: 0

                    } else {
                        BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                            val availableAmount =
                                chain.cosmosFetcher?.balanceAmount(chain.stakeDenom)
                                    ?.movePointLeft(asset.decimals ?: 6)
                            chainBalance.text =
                                formatAmount(availableAmount.toString(), asset.decimals ?: 6)
                            chainDenom.text = asset.symbol
                            chainDenom.setTextColor(asset.assetColor())
                        }
                        cnt = (chain.cosmosFetcher?.cosmosBalances?.count {
                            BaseData.getAsset(
                                chain.apiName, it.denom
                            ) != null
                        } ?: 0)
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
            }
        }
    }

    fun testnetBind(
        chain: BaseChain,
        selectedTags: MutableList<String>,
        selectListener: WalletSelectAdapter.SelectListener
    ) {
        binding.apply {
            chainImg.setImageResource(chain.logo)
            chainName.text = chain.name.uppercase()
            chainTypeBadge.visibility = View.GONE

            if (chain is ChainBitCoin84) {
                chainAddress.text = chain.mainAddress
                chainAddress.visibility = View.VISIBLE
                chainEvmAddress.visibility = View.GONE

            } else if (chain.isEvmCosmos()) {
                chainAddress.text = chain.address
                chainEvmAddress.text = chain.evmAddress
                chainAddress.visibility = View.INVISIBLE
                chainEvmAddress.visibility = View.VISIBLE

                handler.removeCallbacks(starEvmAddressAnimation)
                handler.postDelayed(starEvmAddressAnimation, 5000)

            } else {
                chainAddress.text = chain.address
                chainAddress.visibility = View.VISIBLE
                chainEvmAddress.visibility = View.GONE

                handler.removeCallbacks(starEvmAddressAnimation)
            }
            updateView(chain, selectedTags)

            if (chain is ChainBitCoin84) {
                chainLegacy.visibility = View.VISIBLE
                when (chain.accountKeyType.pubkeyType) {
                    PubKeyType.BTC_NESTED_SEGWIT -> {
                        chainLegacy.defaultSet()
                        chainLegacy.text = "NESTED SEGWIT"
                    }

                    PubKeyType.BTC_LEGACY -> {
                        chainLegacy.defaultSet()
                        chainLegacy.text = "LEGACY"
                    }

                    else -> {
                        chainLegacy.setBackgroundResource(R.drawable.round_box_bit)
                        chainLegacy.setTextColor(
                            ContextCompat.getColorStateList(
                                context,
                                R.color.color_base01
                            )
                        )
                        chainLegacy.text = "NATIVE SEGWIT"
                    }
                }

            } else {
                chainLegacy.visibility = View.GONE
            }

            if (chain.fetched) {
                skeletonChainValue.visibility = View.GONE
                selectView.setOnClickListener {
                    if (selectedTags.contains(chain.tag)) {
                        selectedTags.removeIf { it == chain.tag }
                    } else {
                        selectedTags.add(chain.tag)
                    }
                    updateView(chain, selectedTags)
                    selectListener.select(selectedTags)
                }

                if (chain is ChainBitCoin84) {
                    if (chain.btcFetcher?.bitState == false) {
                        respondLayout.visibility = View.VISIBLE
                        chainBalance.visibility = View.GONE
                        chainDenom.visibility = View.GONE
                        chainAssetCnt.visibility = View.GONE
                        return
                    }

                } else if (chain.supportCosmos()) {
                    if (chain.cosmosFetcher?.cosmosBalances == null) {
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
                chainTypeBadge.visibility = View.GONE

                var cnt = 0
                if (chain is ChainBitCoin84) {
                    chain.btcFetcher()?.let { fetcher ->
                        val availableAmount =
                            fetcher.btcBalances.movePointLeft(8).setScale(8, RoundingMode.DOWN)
                        val pendingInputAmount =
                            fetcher.btcPendingInput.movePointLeft(8).setScale(8, RoundingMode.DOWN)
                        val totalAmount = availableAmount.add(pendingInputAmount)
                        chainBalance.text = formatAmount(totalAmount.toString(), 9)
                        chainDenom.text = chain.coinSymbol
                        cnt =
                            if (chain.btcFetcher()?.btcBalances == BigDecimal.ZERO && chain.btcFetcher()?.btcPendingInput == BigDecimal.ZERO) 0 else 1
                    }

                } else {
                    BaseData.getAsset(chain.apiName, chain.stakeDenom)?.let { asset ->
                        val availableAmount = chain.cosmosFetcher?.balanceAmount(chain.stakeDenom)
                            ?.movePointLeft(asset.decimals ?: 6)
                        chainBalance.text =
                            formatAmount(availableAmount.toString(), asset.decimals ?: 6)
                        chainDenom.text = asset.symbol
                        chainDenom.setTextColor(asset.assetColor())
                    }
                    cnt = (chain.cosmosFetcher?.cosmosBalances?.count {
                        BaseData.getAsset(
                            chain.apiName, it.denom
                        ) != null
                    } ?: 0)
                }
                chainAssetCnt.text = "$cnt Coins"
            }
        }
    }

    private fun updateOktInfo(chain: BaseChain, oktFetcher: OktFetcher?) {
        binding.apply {
            val availableAmount = oktFetcher?.oktBalanceAmount(chain.stakeDenom)
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

private fun TextView.defaultSet() {
    setBackgroundResource(R.drawable.round_box_deprecated)
    setTextColor(ContextCompat.getColorStateList(context, R.color.color_base02))
}