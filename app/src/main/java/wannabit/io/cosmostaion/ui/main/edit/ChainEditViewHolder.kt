package wannabit.io.cosmostaion.ui.main.edit

import android.content.Context
import android.os.Handler
import android.os.Looper
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.fadeInAnimation
import wannabit.io.cosmostaion.common.fadeOutAnimation
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.model.BaseAccount
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
        baseAccount: BaseAccount,
        chain: BaseChain,
        displayChains: MutableList<String>,
        listener: ChainEditAdapter.SelectListener
    ) {
        binding.apply {
            updateView(chain, displayChains)
            chainImg.setImageResource(chain.logo)
            chainName.text = chain.name.uppercase()
            skeletonChainValue.visibility = View.VISIBLE
            skeletonAssetCnt.visibility = View.VISIBLE
            respondLayout.visibility = View.GONE
            chainValue.visibility = View.GONE
            assetCnt.visibility = View.GONE

            if (chain is ChainBitCoin84) {
                when (chain.accountKeyType.pubkeyType) {
                    PubKeyType.BTC_NESTED_SEGWIT -> {
                        chainLegacy.visibility = View.VISIBLE
                        chainBitBadge.visibility = View.GONE
                        chainLegacy.text = "NESTED SEGWIT"
                    }

                    PubKeyType.BTC_LEGACY -> {
                        chainLegacy.visibility = View.VISIBLE
                        chainBitBadge.visibility = View.GONE
                        chainLegacy.text = "LEGACY"
                    }

                    else -> {
                        chainLegacy.visibility = View.GONE
                        chainBitBadge.visibility = View.VISIBLE
                    }
                }
            } else {
                chainLegacy.visibleOrGone(!chain.isDefault)
                chainBitBadge.visibility = View.GONE
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

            } else if (chain.supportCosmos()) {
                chainAddress.text = chain.address
                chainAddress.visibility = View.VISIBLE
                chainEvmAddress.visibility = View.GONE

                handler.removeCallbacks(starEvmAddressAnimation)

            } else if (chain.supportEvm) {
                chainAddress.text = chain.evmAddress
                chainAddress.visibility = View.VISIBLE
                chainEvmAddress.visibility = View.GONE

                handler.removeCallbacks(starEvmAddressAnimation)

            } else {
                chainAddress.text = chain.mainAddress
                chainAddress.visibility = View.VISIBLE
                chainEvmAddress.visibility = View.GONE

                handler.removeCallbacks(starEvmAddressAnimation)
            }

            if (chain.fetched) {
                skeletonChainValue.visibility = View.GONE
                skeletonAssetCnt.visibility = View.GONE

                if (chain is ChainBitCoin84) {
                    if (chain.btcFetcher?.bitState == false) {
                        respondLayout.visibility = View.VISIBLE
                        chainValue.visibility = View.GONE
                        assetCnt.visibility = View.GONE
                        return
                    }

                } else if (chain is ChainSui) {
                    if (chain.suiFetcher?.suiState == false) {
                        respondLayout.visibility = View.VISIBLE
                        chainValue.visibility = View.GONE
                        assetCnt.visibility = View.GONE
                        return
                    }

                } else if (!chain.supportCosmos()) {
                    if (chain.web3j == null) {
                        respondLayout.visibility = View.VISIBLE
                        chainValue.visibility = View.GONE
                        assetCnt.visibility = View.GONE
                        return
                    }

                } else {
                    if (chain.supportEvm) {
                        if (chain is ChainOktEvm) {
                            if (chain.oktFetcher?.oktAccountInfo?.isJsonNull == true || chain.web3j == null) {
                                respondLayout.visibility = View.VISIBLE
                                chainValue.visibility = View.GONE
                                assetCnt.visibility = View.GONE
                                return
                            }

                        } else {
                            if (chain.cosmosFetcher?.cosmosBalances == null || chain.web3j == null) {
                                respondLayout.visibility = View.VISIBLE
                                chainValue.visibility = View.GONE
                                assetCnt.visibility = View.GONE
                                return
                            }
                        }

                    } else {
                        if (chain is ChainOkt996Keccak) {
                            if (chain.oktFetcher?.oktAccountInfo?.isJsonNull == true) {
                                respondLayout.visibility = View.VISIBLE
                                chainValue.visibility = View.GONE
                                assetCnt.visibility = View.GONE
                                return
                            }

                        } else {
                            if (chain.cosmosFetcher?.cosmosBalances == null) {
                                respondLayout.visibility = View.VISIBLE
                                chainValue.visibility = View.GONE
                                assetCnt.visibility = View.GONE
                                return
                            }
                        }
                    }
                }
                respondLayout.visibility = View.GONE
                chainValue.visibility = View.VISIBLE
                assetCnt.visibility = View.VISIBLE

                chainValue.text = formatAssetValue(chain.allValue(true), true)
                if (chain is ChainBitCoin84) {
                    val cnt =
                        if (chain.btcFetcher()?.btcBalances == BigDecimal.ZERO && chain.btcFetcher()?.btcPendingInput == BigDecimal.ZERO) "0" else "1"
                    assetCnt.text = "$cnt Coins"

                } else if (chain is ChainSui) {
                    assetCnt.text = chain.suiFetcher?.suiBalances?.size.toString() + " Coins"

                } else if (chain is ChainOkt996Keccak) {
                    assetCnt.text =
                        chain.oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get(
                            "coins"
                        )?.asJsonArray?.size().toString() + " Coins"

                } else if (chain.supportCosmos()) {
                    val coinCntString = if (chain is ChainOktEvm) {
                        chain.oktFetcher?.oktAccountInfo?.get("value")?.asJsonObject?.get(
                            "coins"
                        )?.asJsonArray?.size().toString() + " Coins"
                    } else {
                        (chain.cosmosFetcher?.cosmosBalances?.count {
                            BaseData.getAsset(
                                chain.apiName, it.denom
                            ) != null
                        } ?: 0).toString() + " Coins"
                    }

                    if (chain.supportCw20) {
                        val tokenCnt =
                            chain.cosmosFetcher?.tokens?.count { BigDecimal.ZERO < it.amount?.toBigDecimal() }
                        if (tokenCnt == 0) {
                            assetCnt.text = coinCntString
                        } else {
                            assetCnt.text = "$tokenCnt Tokens, $coinCntString"
                        }

                    } else if (chain.supportEvm) {
                        val tokenCnt =
                            chain.evmRpcFetcher?.evmTokens?.count { BigDecimal.ZERO < it.amount?.toBigDecimal() }
                        if (tokenCnt == 0) {
                            assetCnt.text = coinCntString
                        } else {
                            assetCnt.text = "$tokenCnt Tokens, $coinCntString"
                        }

                    } else {
                        assetCnt.text = coinCntString
                    }

                } else {
                    val coinCnt =
                        if (BigDecimal.ZERO >= chain.evmRpcFetcher?.evmBalance) "0" + " Coins" else "1" + " Coins"
                    val tokenCnt = if (Prefs.getDisplayErc20s(baseAccount.id, chain.tag) != null) {
                        Prefs.getDisplayErc20s(baseAccount.id, chain.tag)?.size
                    } else {
                        chain.evmRpcFetcher?.evmTokens?.count { BigDecimal.ZERO < it.amount?.toBigDecimal() }
                    }
                    if (tokenCnt == 0) {
                        assetCnt.text = coinCnt
                    } else {
                        assetCnt.text = "$tokenCnt Tokens, $coinCnt"
                    }
                }
            }
        }
    }

    fun testnetBind(
        baseAccount: BaseAccount,
        chain: BaseChain,
        displayChains: MutableList<String>,
        listener: ChainEditAdapter.SelectListener
    ) {
        binding.apply {
            updateView(chain, displayChains)
            chainImg.setImageResource(chain.logo)
            chainName.text = chain.name.uppercase()

            if (chain is ChainBitCoin84) {
                when (chain.accountKeyType.pubkeyType) {
                    PubKeyType.BTC_NESTED_SEGWIT -> {
                        chainLegacy.visibility = View.VISIBLE
                        chainBitBadge.visibility = View.GONE
                        chainLegacy.text = "NESTED SEGWIT"
                    }

                    PubKeyType.BTC_LEGACY -> {
                        chainLegacy.visibility = View.VISIBLE
                        chainBitBadge.visibility = View.GONE
                        chainLegacy.text = "LEGACY"
                    }

                    else -> {
                        chainLegacy.visibility = View.GONE
                        chainBitBadge.visibility = View.VISIBLE
                    }
                }
            } else {
                chainLegacy.visibleOrGone(!chain.isDefault)
                chainBitBadge.visibility = View.GONE
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
                chainAddress.text = if (chain is ChainBitCoin84) {
                    chain.mainAddress
                } else {
                    chain.address
                }
                chainAddress.visibility = View.VISIBLE
                chainEvmAddress.visibility = View.GONE

                handler.removeCallbacks(starEvmAddressAnimation)
            }

            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getInstance().refAddressDao()
                    .selectRefAddress(baseAccount.id, chain.tag)?.let { refAddress ->
                        withContext(Dispatchers.Main) {
                            if (chain.fetched) {
                                skeletonChainValue.visibility = View.GONE
                                skeletonAssetCnt.visibility = View.GONE

                                if (chain is ChainBitCoin84) {
                                    if (chain.btcFetcher?.bitState == false) {
                                        respondLayout.visibility = View.VISIBLE
                                        chainValue.visibility = View.GONE
                                        assetCnt.visibility = View.GONE
                                        return@withContext
                                    }

                                } else if (chain.isEvmCosmos()) {
                                    if (chain.cosmosFetcher?.cosmosBalances == null || chain.web3j == null) {
                                        respondLayout.visibility = View.VISIBLE
                                        chainValue.visibility = View.GONE
                                        assetCnt.visibility = View.GONE
                                        return@withContext
                                    }

                                } else {
                                    if (chain.cosmosFetcher?.cosmosBalances == null) {
                                        respondLayout.visibility = View.VISIBLE
                                        chainValue.visibility = View.GONE
                                        assetCnt.visibility = View.GONE
                                        return@withContext
                                    }
                                }

                                if (chain is ChainBitCoin84) {
                                    val cnt =
                                        if (chain.btcFetcher()?.btcBalances == BigDecimal.ZERO && chain.btcFetcher()?.btcPendingInput == BigDecimal.ZERO) "0" else "1"
                                    assetCnt.text = "$cnt Coins"

                                } else if (chain.supportCosmos()) {
                                    val coinCntString = refAddress.lastCoinCnt.toString() + " Coins"
                                    if (chain.supportCw20) {
                                        val tokenCnt =
                                            chain.cosmosFetcher?.tokens?.count { BigDecimal.ZERO < it.amount?.toBigDecimal() }
                                        if (tokenCnt == 0) {
                                            assetCnt.text = coinCntString
                                        } else {
                                            assetCnt.text = "$tokenCnt Tokens, $coinCntString"
                                        }

                                    } else if (chain.supportEvm) {
                                        val tokenCnt =
                                            chain.evmRpcFetcher?.evmTokens?.count { BigDecimal.ZERO < it.amount?.toBigDecimal() }
                                        if (tokenCnt == 0) {
                                            assetCnt.text = coinCntString
                                        } else {
                                            assetCnt.text = "$tokenCnt Tokens, $coinCntString"
                                        }

                                    } else {
                                        assetCnt.text = coinCntString
                                    }

                                } else {
                                    val coinCnt =
                                        if (BigDecimal.ZERO >= chain.evmRpcFetcher?.evmBalance) "0" + " Coins" else "1" + " Coins"
                                    val tokenCnt =
                                        chain.evmRpcFetcher?.evmTokens?.count { BigDecimal.ZERO < it.amount?.toBigDecimal() }
                                    if (tokenCnt == 0) {
                                        assetCnt.text = coinCnt
                                    } else {
                                        assetCnt.text = "$tokenCnt Tokens, $coinCnt"
                                    }
                                }
                                chainValue.text = formatAssetValue(chain.allValue(true), true)
                            }
                        }
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
}