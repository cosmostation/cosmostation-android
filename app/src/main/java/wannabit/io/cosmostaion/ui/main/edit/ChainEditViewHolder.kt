package wannabit.io.cosmostaion.ui.main.edit

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.ItemEditBinding
import java.math.BigDecimal

class ChainEditViewHolder(
    val context: Context, private val binding: ItemEditBinding
) : RecyclerView.ViewHolder(binding.root) {

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
            chainLegacy.visibleOrGone(!chain.isDefault)
            chainPath.visibleOrGone(baseAccount.type == BaseAccountType.MNEMONIC)
            chainPath.text = chain.getHDPath(baseAccount.lastHDPath)

            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getInstance().refAddressDao()
                    .selectRefAddress(baseAccount.id, chain.tag)?.let { refAddress ->
                        withContext(Dispatchers.Main) {
                            if (chain.fetched) {
                                skeletonChainValue.visibility = View.GONE
                                skeletonAssetCnt.visibility = View.GONE

                                if (chain.isEvmCosmos()) {
                                    if (chain.grpcFetcher?.cosmosBalances == null || chain.web3j == null) {
                                        respondLayout.visibility = View.VISIBLE
                                        chainValue.visibility = View.GONE
                                        assetCnt.visibility = View.GONE
                                        return@withContext
                                    }

                                } else if (chain.isCosmos()) {
                                    if (chain is ChainOktEvm) {
                                        if (chain.oktFetcher?.lcdAccountInfo?.isJsonNull == true || chain.web3j == null) {
                                            respondLayout.visibility = View.VISIBLE
                                            chainValue.visibility = View.GONE
                                            assetCnt.visibility = View.GONE
                                            return@withContext
                                        }

                                    } else if (chain is ChainOkt996Keccak) {
                                        if (chain.oktFetcher?.lcdAccountInfo?.isJsonNull == true) {
                                            respondLayout.visibility = View.VISIBLE
                                            chainValue.visibility = View.GONE
                                            assetCnt.visibility = View.GONE
                                            return@withContext
                                        }

                                    } else if (chain.grpcFetcher?.cosmosBalances == null) {
                                        respondLayout.visibility = View.VISIBLE
                                        chainValue.visibility = View.GONE
                                        assetCnt.visibility = View.GONE
                                        return@withContext
                                    }

                                } else {
                                    if (chain.web3j == null) {
                                        respondLayout.visibility = View.VISIBLE
                                        chainValue.visibility = View.GONE
                                        assetCnt.visibility = View.GONE
                                        return@withContext
                                    }
                                }

                                if (chain.isCosmos()) {
                                    val coinCntString = refAddress.lastCoinCnt.toString() + " Coins"
                                    if (chain.supportCw20) {
                                        val tokenCnt =
                                            chain.grpcFetcher?.tokens?.count { BigDecimal.ZERO < it.amount?.toBigDecimal() }
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
                                        if (BigDecimal.ZERO >= chain.evmRpcFetcher?.evmBalance) "0" else "1" + " Coins"
                                    val tokenCnt =
                                        chain.evmRpcFetcher?.evmTokens?.count { BigDecimal.ZERO < it.amount?.toBigDecimal() }
                                    if (tokenCnt == 0) {
                                        assetCnt.text = coinCnt
                                    } else {
                                        assetCnt.text = "$tokenCnt Tokens, $coinCnt"
                                    }
                                }
                                chainValue.text = formatAssetValue(refAddress.lastUsdValue(), true)
                            }
                        }
                    }
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