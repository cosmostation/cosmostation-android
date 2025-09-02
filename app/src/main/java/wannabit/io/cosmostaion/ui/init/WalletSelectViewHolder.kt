package wannabit.io.cosmostaion.ui.init

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
import wannabit.io.cosmostaion.chain.FetchState
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.chain.fetcher.OktFetcher
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSolana
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.IOTA_MAIN_DENOM
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.chain.testnetClass.ChainGnoTestnet
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.fadeInAnimation
import wannabit.io.cosmostaion.common.fadeOutAnimation
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.databinding.ItemWalletSelectBinding
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
            chainImg.setChainLogo(chain)
            chainName.text = chain.getChainName()

            if (chain.isEvmCosmos()) {
                chainAddress.text = chain.address
                chainEvmAddress.text = chain.evmAddress
                chainAddress.visibility = View.INVISIBLE
                chainEvmAddress.visibility = View.VISIBLE

                handler.removeCallbacks(starEvmAddressAnimation)
                handler.postDelayed(starEvmAddressAnimation, 5000)

            } else {
                chainAddress.text = if (chain.supportCosmos()) {
                    chain.address
                } else if (chain.supportEvm) {
                    chain.evmAddress
                } else {
                    chain.mainAddress
                }

                chainAddress.visibility = View.VISIBLE
                chainEvmAddress.visibility = View.GONE
                handler.removeCallbacks(starEvmAddressAnimation)
            }
            updateView(chain, selectedTags)

            if (chain is ChainBitCoin86) {
                chainLegacy.visibility = View.VISIBLE
                chainTypeBadge.visibility = View.GONE
                when (chain.accountKeyType.pubkeyType) {
                    PubKeyType.BTC_NESTED_SEGWIT -> {
                        chainLegacy.defaultSet()
                        chainLegacy.text = context.getString(R.string.str_nested_segwit)
                    }

                    PubKeyType.BTC_LEGACY -> {
                        chainLegacy.defaultSet()
                        chainLegacy.text = context.getString(R.string.str_legacy)
                    }

                    PubKeyType.BTC_NATIVE_SEGWIT -> {
                        chainLegacy.setBackgroundResource(R.drawable.round_box_bit)
                        chainLegacy.setTextColor(
                            ContextCompat.getColorStateList(
                                context, R.color.color_base01
                            )
                        )
                        chainLegacy.text = context.getString(R.string.str_native_segwit)
                    }

                    else -> {
                        chainLegacy.setBackgroundResource(R.drawable.round_box_bit_taproot)
                        chainLegacy.setTextColor(
                            ContextCompat.getColorStateList(
                                context, R.color.color_base01
                            )
                        )
                        chainLegacy.text = context.getString(R.string.str_taproot)
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

            when (chain.fetchState) {
                FetchState.SUCCESS -> {
                    skeletonChainValue.visibility = View.GONE
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
                    chainAssetCnt.text = chain.coinCnt.toString() + " Coins"

                    if (chain is ChainBitCoin86) {
                        chain.btcFetcher()?.let { fetcher ->
                            val availableAmount =
                                fetcher.btcBalances.movePointLeft(8).setScale(8, RoundingMode.DOWN)
                            val pendingInputAmount = fetcher.btcPendingInput.movePointLeft(8)
                                .setScale(8, RoundingMode.DOWN)
                            val totalAmount = availableAmount.add(pendingInputAmount)
                            chainBalance.text = formatAmount(totalAmount.toString(), 8)
                            chainDenom.text = chain.getMainAssetSymbol()
                            chainDenom.setTextColor(
                                ContextCompat.getColorStateList(
                                    context, R.color.color_base01
                                )
                            )
                        }

                    } else if (chain is ChainSui) {
                        val availableAmount = chain.suiFetcher()?.let { fetcher ->
                            fetcher.suiBalanceAmount(SUI_MAIN_DENOM)?.movePointLeft(9)
                                ?.setScale(9, RoundingMode.DOWN)
                        }
                        chainBalance.text = formatAmount(availableAmount.toString(), 9)
                        chainDenom.text = chain.getMainAssetSymbol()
                        chainDenom.setTextColor(
                            ContextCompat.getColorStateList(
                                context, R.color.color_base01
                            )
                        )

                    } else if (chain is ChainIota) {
                        val availableAmount = chain.iotaFetcher()?.let { fetcher ->
                            fetcher.iotaBalanceAmount(IOTA_MAIN_DENOM)?.movePointLeft(9)
                                ?.setScale(9, RoundingMode.DOWN)
                        }
                        chainBalance.text = formatAmount(availableAmount.toString(), 9)
                        chainDenom.text = chain.getMainAssetSymbol()
                        chainDenom.setTextColor(
                            ContextCompat.getColorStateList(
                                context, R.color.color_base01
                            )
                        )

                    } else if (chain is ChainSolana) {
                        val availableAmount = chain.solanaFetcher().let { fetcher ->
                            fetcher?.solanaBalanceAmount()?.movePointLeft(9)
                                ?.setScale(9, RoundingMode.DOWN)
                        }
                        chainBalance.text = formatAmount(availableAmount.toString(), 9)
                        chainDenom.text = chain.coinSymbol
                        chainDenom.setTextColor(
                            ContextCompat.getColorStateList(
                                context, R.color.color_base01
                            )
                        )

                    } else if (chain is ChainGnoTestnet) {
                        BaseData.getAsset(chain.apiName, chain.getMainAssetDenom())?.let { asset ->
                            val availableAmount =
                                chain.gnoRpcFetcher?.balanceAmount(chain.getMainAssetDenom())
                                    ?.movePointLeft(asset.decimals ?: 6)
                            chainBalance.text =
                                formatAmount(availableAmount.toString(), asset.decimals ?: 6)
                            chainDenom.text = asset.symbol
                            chainDenom.setTextColor(asset.assetColor())
                        }

                    } else if (chain.supportCosmos()) {
                        if (chain is ChainOktEvm) {
                            updateOktInfo(chain, chain.oktFetcher)

                        } else {
                            BaseData.getAsset(chain.apiName, chain.getMainAssetDenom())?.let { asset ->
                                val availableAmount =
                                    chain.cosmosFetcher?.balanceAmount(chain.getMainAssetDenom())
                                        ?.movePointLeft(asset.decimals ?: 6)
                                chainBalance.text =
                                    formatAmount(availableAmount.toString(), asset.decimals ?: 6)
                                chainDenom.text = asset.symbol
                                chainDenom.setTextColor(asset.assetColor())
                            }
                        }

                    } else {
                        val availableAmount = chain.evmRpcFetcher?.evmBalance?.movePointLeft(18)
                            ?.setScale(18, RoundingMode.DOWN)
                        chainBalance.text = formatAmount(availableAmount.toString(), 18)
                        chainDenom.text = chain.getMainAssetSymbol()
                        BaseData.getAsset(chain.apiName, chain.getMainAssetDenom())?.let { asset ->
                            chainDenom.setTextColor(asset.assetColor())
                        }
                    }
                }

                FetchState.FAIL -> {
                    skeletonChainValue.visibility = View.GONE
                    respondLayout.visibility = View.VISIBLE
                    chainBalance.visibility = View.GONE
                    chainDenom.visibility = View.GONE
                    chainAssetCnt.visibility = View.GONE
                }

                else -> {
                    skeletonChainValue.visibility = View.VISIBLE
                    respondLayout.visibility = View.GONE
                    chainBalance.visibility = View.GONE
                    chainDenom.visibility = View.GONE
                    chainAssetCnt.visibility = View.GONE
                }
            }
        }
    }

    fun testnetBind(
        chain: BaseChain,
        selectedTags: MutableList<String>,
        selectListener: WalletSelectAdapter.SelectListener
    ) {
        binding.apply {
            chainImg.setChainLogo(chain)
            chainName.text = chain.getChainName()
            chainTypeBadge.visibility = View.GONE

            if (chain.isEvmCosmos()) {
                chainAddress.text = chain.address
                chainEvmAddress.text = chain.evmAddress
                chainAddress.visibility = View.INVISIBLE
                chainEvmAddress.visibility = View.VISIBLE

                handler.removeCallbacks(starEvmAddressAnimation)
                handler.postDelayed(starEvmAddressAnimation, 5000)

            } else {
                chainAddress.text = if (chain is ChainBitCoin86) {
                    chain.mainAddress
                } else if (chain.supportEvm) {
                    chain.evmAddress
                } else {
                    chain.address
                }
                chainAddress.visibility = View.VISIBLE
                chainEvmAddress.visibility = View.GONE

                handler.removeCallbacks(starEvmAddressAnimation)
            }
            updateView(chain, selectedTags)

            if (chain is ChainBitCoin86) {
                chainLegacy.visibility = View.VISIBLE
                when (chain.accountKeyType.pubkeyType) {
                    PubKeyType.BTC_NESTED_SEGWIT -> {
                        chainLegacy.defaultSet()
                        chainLegacy.text = context.getString(R.string.str_nested_segwit)
                    }

                    PubKeyType.BTC_LEGACY -> {
                        chainLegacy.defaultSet()
                        chainLegacy.text = context.getString(R.string.str_legacy)
                    }

                    PubKeyType.BTC_NATIVE_SEGWIT -> {
                        chainLegacy.setBackgroundResource(R.drawable.round_box_bit)
                        chainLegacy.setTextColor(
                            ContextCompat.getColorStateList(
                                context, R.color.color_base01
                            )
                        )
                        chainLegacy.text = context.getString(R.string.str_native_segwit)
                    }

                    else -> {
                        chainLegacy.setBackgroundResource(R.drawable.round_box_bit_taproot)
                        chainLegacy.setTextColor(
                            ContextCompat.getColorStateList(
                                context, R.color.color_base01
                            )
                        )
                        chainLegacy.text = context.getString(R.string.str_taproot)
                    }
                }

            } else {
                chainLegacy.visibility = View.GONE
            }

            when (chain.fetchState) {
                FetchState.SUCCESS -> {
                    skeletonChainValue.visibility = View.GONE
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
                    chainAssetCnt.text = chain.coinCnt.toString() + " Coins"

                    if (chain is ChainBitCoin86) {
                        chain.btcFetcher()?.let { fetcher ->
                            val availableAmount =
                                fetcher.btcBalances.movePointLeft(8).setScale(8, RoundingMode.DOWN)
                            val pendingInputAmount = fetcher.btcPendingInput.movePointLeft(8)
                                .setScale(8, RoundingMode.DOWN)
                            val totalAmount = availableAmount.add(pendingInputAmount)
                            chainBalance.text = formatAmount(totalAmount.toString(), 8)
                            chainDenom.text = chain.getMainAssetSymbol()
                        }

                    } else if (chain is ChainGnoTestnet) {
                        BaseData.getAsset(chain.apiName, chain.getMainAssetDenom())?.let { asset ->
                            val availableAmount =
                                chain.gnoRpcFetcher?.balanceAmount(chain.getMainAssetDenom())
                                    ?.movePointLeft(asset.decimals ?: 6)
                            chainBalance.text =
                                formatAmount(availableAmount.toString(), asset.decimals ?: 6)
                            chainDenom.text = asset.symbol
                            chainDenom.setTextColor(asset.assetColor())
                        }

                    } else if (chain.supportCosmos()) {
                        BaseData.getAsset(chain.apiName, chain.getMainAssetDenom())?.let { asset ->
                            val availableAmount =
                                chain.cosmosFetcher?.balanceAmount(chain.getMainAssetDenom())
                                    ?.movePointLeft(asset.decimals ?: 6)
                            chainBalance.text =
                                formatAmount(availableAmount.toString(), asset.decimals ?: 6)
                            chainDenom.text = asset.symbol
                            chainDenom.setTextColor(asset.assetColor())
                        }

                    } else {
                        val availableAmount = chain.evmRpcFetcher?.evmBalance?.movePointLeft(18)
                            ?.setScale(18, RoundingMode.DOWN)
                        chainBalance.text = formatAmount(availableAmount.toString(), 18)
                        chainDenom.text = chain.getMainAssetSymbol()
                        BaseData.getAsset(chain.apiName, chain.getMainAssetDenom())?.let { asset ->
                            chainDenom.setTextColor(asset.assetColor())
                        }
                    }
                }

                FetchState.FAIL -> {
                    skeletonChainValue.visibility = View.GONE
                    respondLayout.visibility = View.VISIBLE
                    chainBalance.visibility = View.GONE
                    chainDenom.visibility = View.GONE
                    chainAssetCnt.visibility = View.GONE
                }

                else -> {
                    skeletonChainValue.visibility = View.VISIBLE
                    respondLayout.visibility = View.GONE
                    chainBalance.visibility = View.GONE
                    chainDenom.visibility = View.GONE
                    chainAssetCnt.visibility = View.GONE
                }
            }
        }
    }

    private fun updateOktInfo(chain: BaseChain, oktFetcher: OktFetcher?) {
        binding.apply {
            val availableAmount = oktFetcher?.oktBalanceAmount(chain.getMainAssetDenom())
            chainBalance.text = formatAmount(availableAmount.toString(), 18)
            chainDenom.text = chain.getMainAssetDenom().uppercase()
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