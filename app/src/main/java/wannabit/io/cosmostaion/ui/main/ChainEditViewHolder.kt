package wannabit.io.cosmostaion.ui.main

import android.content.Context
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
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.common.fadeInAnimation
import wannabit.io.cosmostaion.common.fadeOutAnimation
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.ItemEditBinding
import java.math.BigDecimal

class ChainEditViewHolder(
    val context: Context, private val binding: ItemEditBinding
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

    fun bind(
        chain: BaseChain,
        displayChains: MutableList<String>,
        listener: ChainEditAdapter.SelectListener
    ) {
        binding.apply {
            updateView(chain, displayChains)
            chainImg.setChainLogo(chain)
            chainName.text = chain.name
            skeletonChainValue.visibility = View.VISIBLE
            skeletonAssetCnt.visibility = View.VISIBLE
            respondLayout.visibility = View.GONE
            chainValue.visibility = View.GONE
            assetCnt.visibility = View.GONE

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
                chainLegacy.defaultSet()
                chainLegacy.visibleOrGone(!chain.isDefault)
                chainLegacy.text = context.getString(R.string.str_old)
            }

            editView.setOnClickListener {
                if (chain.tag == "cosmos118") return@setOnClickListener
                if (displayChains.contains(chain.tag)) {
                    displayChains.removeIf { it == chain.tag }
                } else {
                    displayChains.add(chain.tag)
                }
                updateView(chain, displayChains)
                listener.select(displayChains)
            }

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
                } else if (chain.isSupportErc20()) {
                    chain.evmAddress
                } else {
                    chain.mainAddress
                }

                chainAddress.visibility = View.VISIBLE
                chainEvmAddress.visibility = View.GONE
                handler.removeCallbacks(starEvmAddressAnimation)
            }

            when (chain.fetchState) {
                FetchState.SUCCESS -> {
                    skeletonChainValue.visibility = View.GONE
                    skeletonAssetCnt.visibility = View.GONE
                    respondLayout.visibility = View.GONE
                    chainValue.visibility = View.VISIBLE
                    assetCnt.visibility = View.VISIBLE

                    chainValue.text = formatAssetValue(chain.allValue(false) ?: BigDecimal.ZERO)
                    val coinCntString = chain.coinCnt.toString() + " Coins"
                    val tokenCnt = chain.tokenCnt
                    assetCnt.text = if (tokenCnt > 0) {
                        "$tokenCnt Tokens, $coinCntString"
                    } else {
                        coinCntString
                    }
                }

                FetchState.FAIL -> {
                    skeletonChainValue.visibility = View.GONE
                    skeletonAssetCnt.visibility = View.GONE
                    respondLayout.visibility = View.VISIBLE
                    chainValue.visibility = View.GONE
                    assetCnt.visibility = View.GONE
                }

                else -> {
                    skeletonChainValue.visibility = View.VISIBLE
                    skeletonAssetCnt.visibility = View.VISIBLE
                    respondLayout.visibility = View.GONE
                    chainValue.visibility = View.GONE
                    assetCnt.visibility = View.GONE
                }
            }
        }
    }

    fun testnetBind(
        chain: BaseChain,
        displayChains: MutableList<String>,
        listener: ChainEditAdapter.SelectListener
    ) {
        binding.apply {
            updateView(chain, displayChains)
            chainImg.setChainLogo(chain)
            chainName.text = chain.name

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
                chainLegacy.defaultSet()
                chainLegacy.visibleOrGone(!chain.isDefault)
                chainLegacy.text = context.getString(R.string.str_old)
            }

            editView.setOnClickListener {
                if (displayChains.contains(chain.tag)) {
                    displayChains.removeIf { it == chain.tag }
                } else {
                    displayChains.add(chain.tag)
                }
                updateView(chain, displayChains)
                listener.select(displayChains)
            }

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
                } else if (chain.isSupportErc20()) {
                    chain.evmAddress
                } else {
                    chain.address
                }
                chainAddress.visibility = View.VISIBLE
                chainEvmAddress.visibility = View.GONE

                handler.removeCallbacks(starEvmAddressAnimation)
            }

            when (chain.fetchState) {
                FetchState.SUCCESS -> {
                    skeletonChainValue.visibility = View.GONE
                    skeletonAssetCnt.visibility = View.GONE
                    respondLayout.visibility = View.GONE
                    chainValue.visibility = View.VISIBLE
                    assetCnt.visibility = View.VISIBLE

                    chainValue.text = formatAssetValue(chain.allValue(false) ?: BigDecimal.ZERO)
                    val coinCntString = chain.coinCnt.toString() + " Coins"
                    val tokenCnt = chain.tokenCnt
                    assetCnt.text = if (tokenCnt > 0) {
                        "$tokenCnt Tokens, $coinCntString"
                    } else {
                        coinCntString
                    }
                }

                FetchState.FAIL -> {
                    skeletonChainValue.visibility = View.GONE
                    skeletonAssetCnt.visibility = View.GONE
                    respondLayout.visibility = View.VISIBLE
                    chainValue.visibility = View.GONE
                    assetCnt.visibility = View.GONE
                }

                else -> {
                    skeletonChainValue.visibility = View.VISIBLE
                    skeletonAssetCnt.visibility = View.VISIBLE
                    respondLayout.visibility = View.GONE
                    chainValue.visibility = View.GONE
                    assetCnt.visibility = View.GONE
                }
            }
        }
    }

    private fun updateView(chain: BaseChain, displayChainLines: MutableList<String>) {
        binding.apply {
            if (displayChainLines.contains(chain.tag)) {
                editView.setBackgroundResource(R.drawable.item_select_bg)
            } else {
                editView.setBackgroundResource(R.drawable.cell_bg)
            }
        }
    }

    private fun TextView.defaultSet() {
        setBackgroundResource(R.drawable.round_box_deprecated)
        setTextColor(ContextCompat.getColorStateList(context, R.color.color_base02))
    }
}