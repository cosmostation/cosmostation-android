package wannabit.io.cosmostaion.ui.main.edit

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.ChainType
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.ItemEditBinding

class ChainEditViewHolder(
    val context: Context,
    private val binding: ItemEditBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(
        baseAccount: BaseAccount,
        line: CosmosLine,
        cnt: Int,
        displayChains: MutableList<String>,
        listener: ChainEditAdapter.SelectListener
    ) {
        binding.apply {
            when (line.chainType) {
                ChainType.COSMOS_TYPE -> {
                    headerLayout.visibleOrGone(adapterPosition == 0)
                    headerTitle.text = context.getString(R.string.str_cosmos_class)
                    headerCnt.text = cnt.toString()
                    updateView(this, line, displayChains)

                    chainImg.setImageResource(line.logo)
                    chainName.text = line.name.uppercase()

                    if (baseAccount.type == BaseAccountType.MNEMONIC) {
                        chainPath.text = line.getHDPath(baseAccount.lastHDPath)
                        if (line.evmCompatible) {
                            chainLegacy.visibility = View.VISIBLE
                            chainLegacy.text = context.getString(R.string.str_evm)
                            chainLegacy.setBackgroundResource(R.drawable.round_box_evm)
                            chainLegacy.setTextColor(
                                ContextCompat.getColor(
                                    context,
                                    R.color.color_base01
                                )
                            )
                        } else if (!line.isDefault) {
                            chainLegacy.visibility = View.VISIBLE
                            chainLegacy.text = context.getString(R.string.str_legacy)
                            chainLegacy.setBackgroundResource(R.drawable.round_box_deprecated)
                            chainLegacy.setTextColor(
                                ContextCompat.getColor(
                                    context,
                                    R.color.color_base02
                                )
                            )
                        } else {
                            chainLegacy.visibility = View.GONE
                        }

                    } else {
                        chainPath.visibility = View.GONE
                        if (line.evmCompatible) {
                            chainLegacy.visibility = View.VISIBLE
                            chainLegacy.text = context.getString(R.string.str_evm)
                            chainLegacy.setBackgroundResource(R.drawable.round_box_evm)
                            chainLegacy.setTextColor(
                                ContextCompat.getColor(
                                    context,
                                    R.color.color_base01
                                )
                            )
                        } else {
                            chainLegacy.visibility = View.GONE
                        }
                    }

                    CoroutineScope(Dispatchers.IO).launch {
                        AppDatabase.getInstance().refAddressDao().selectRefAddress(baseAccount.id, line.tag)?.let { refAddress ->
                            withContext(Dispatchers.Main) {
                                skeletonChainValue.visibility = View.GONE
                                skeletonAssetCnt.visibility = View.GONE

                                chainValue.text = formatAssetValue(refAddress.lastUsdValue())
                                assetCnt.text = refAddress.lastCoinCnt.toString() + " Coins"
                            }
                        }
                    }

                    editView.setOnClickListener {
                        if (line.tag == "cosmos118") { return@setOnClickListener }
                        if (displayChains.contains(line.tag)) {
                            displayChains.removeIf { it == line.tag }
                        } else {
                            displayChains.add(line.tag)
                        }
                        updateView(this, line, displayChains)
                        listener.select(displayChains)
                    }

                } else -> { }
            }
        }
    }

    private fun updateView(binding: ItemEditBinding, line: CosmosLine, displayChainLines: MutableList<String>) {
        binding.apply {
            if (displayChainLines.contains(line.tag)) {
                editView.setBackgroundResource(R.drawable.item_select_bg)
            } else {
                editView.setBackgroundResource(R.drawable.cell_bg)
            }
        }
    }
}