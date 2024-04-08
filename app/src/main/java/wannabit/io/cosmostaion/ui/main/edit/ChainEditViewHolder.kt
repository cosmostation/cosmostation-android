package wannabit.io.cosmostaion.ui.main.edit

import android.content.Context
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.ItemEditBinding
import java.math.BigDecimal

class ChainEditViewHolder(
    val context: Context, private val binding: ItemEditBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun evmBind(
        baseAccount: BaseAccount,
        line: EthereumLine,
        displayEvmChains: MutableList<String>,
        listener: ChainEditAdapter.SelectListener
    ) {
        binding.apply {
            updateView(line, displayEvmChains)
            chainImg.setImageResource(line.logo)
            chainName.text = line.name.uppercase()

            if (baseAccount.type == BaseAccountType.MNEMONIC) {
                chainPath.text = line.getHDPath(baseAccount.lastHDPath)
            } else {
                chainPath.visibility = View.GONE
            }

            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getInstance().refAddressDao().selectRefAddress(baseAccount.id, line.tag)
                    ?.let { refAddress ->
                        withContext(Dispatchers.Main) {
                            skeletonChainValue.visibility = View.GONE
                            skeletonAssetCnt.visibility = View.GONE

                            if (line !is ChainOktEvm) {
                                if (line.supportCosmos && line.cosmosBalances == null) {
                                    respondLayout.visibility = View.VISIBLE
                                    chainValue.visibility = View.GONE
                                    assetCnt.visibility = View.GONE
                                    return@withContext
                                }
                            }

                            if (line.web3j == null) {
                                respondLayout.visibility = View.VISIBLE
                                chainValue.visibility = View.GONE
                                assetCnt.visibility = View.GONE
                                return@withContext
                            }

                            chainValue.text = formatAssetValue(refAddress.lastUsdValue(), true)
                            val coinCntString = refAddress.lastCoinCnt.toString() + " Coins"
                            val tokenCnt =
                                line.evmTokens.count { it.amount?.toBigDecimal()!! > BigDecimal.ZERO }
                            if (tokenCnt == 0) {
                                assetCnt.text = coinCntString
                            } else {
                                assetCnt.text = "$tokenCnt Tokens, $coinCntString"
                            }
                        }
                    }
            }

            editView.setOnClickListener {
                if (displayEvmChains.contains(line.tag)) {
                    displayEvmChains.removeIf { it == line.tag }
                } else {
                    displayEvmChains.add(line.tag)
                }
                updateView(line, displayEvmChains)
                listener.evmSelect(displayEvmChains)
            }
        }
    }

    fun bind(
        baseAccount: BaseAccount,
        line: CosmosLine,
        displayChains: MutableList<String>,
        listener: ChainEditAdapter.SelectListener
    ) {
        binding.apply {
            updateView(line, displayChains)
            chainImg.setImageResource(line.logo)
            chainName.text = line.name.uppercase()

            if (baseAccount.type == BaseAccountType.MNEMONIC) {
                chainPath.text = line.getHDPath(baseAccount.lastHDPath)
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
                chainPath.visibility = View.GONE
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

                    val layoutParams = chainLegacy.layoutParams as ViewGroup.MarginLayoutParams
                    layoutParams.setMargins(0, 2, 0, 0)
                    chainLegacy.layoutParams = layoutParams

                } else {
                    chainLegacy.visibility = View.GONE
                    chainTypeBadge.visibility = View.GONE
                }
            }

            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getInstance().refAddressDao().selectRefAddress(baseAccount.id, line.tag)
                    ?.let { refAddress ->
                        withContext(Dispatchers.Main) {
                            skeletonChainValue.visibility = View.GONE
                            skeletonAssetCnt.visibility = View.GONE

                            if (line !is ChainOkt996Keccak && line !is ChainBinanceBeacon) {
                                if (line.cosmosBalances == null) {
                                    respondLayout.visibility = View.VISIBLE
                                    chainValue.visibility = View.GONE
                                    assetCnt.visibility = View.GONE

                                } else {
                                    respondLayout.visibility = View.GONE
                                    chainValue.visibility = View.VISIBLE
                                    assetCnt.visibility = View.VISIBLE
                                }
                            }

                            chainValue.text = formatAssetValue(refAddress.lastUsdValue(), true)
                            val coinCntString = refAddress.lastCoinCnt.toString() + " Coins"
                            if (line.supportCw20 || line.supportErc20) {
                                val tokenCnt =
                                    line.tokens.count { it.amount?.toBigDecimal()!! > BigDecimal.ZERO }
                                if (tokenCnt == 0) {
                                    assetCnt.text = coinCntString
                                } else {
                                    assetCnt.text = "$tokenCnt Tokens, $coinCntString"
                                }

                            } else {
                                assetCnt.text = coinCntString
                            }
                        }
                    }
            }

            editView.setOnClickListener {
                if (line.tag == "cosmos118") {
                    return@setOnClickListener
                }
                if (displayChains.contains(line.tag)) {
                    displayChains.removeIf { it == line.tag }
                } else {
                    displayChains.add(line.tag)
                }
                updateView(line, displayChains)
                listener.select(displayChains)
            }
        }
    }

    private fun updateView(line: CosmosLine, displayChainLines: MutableList<String>) {
        binding.apply {
            if (displayChainLines.contains(line.tag)) {
                editView.setBackgroundResource(R.drawable.item_select_bg)
            } else {
                editView.setBackgroundResource(R.drawable.cell_bg)
            }
        }
    }
}