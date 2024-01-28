package wannabit.io.cosmostaion.ui.wallet

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.ItemWalletSelectBinding

class WalletSelectViewHolder(
    val context: Context, private val binding: ItemWalletSelectBinding
) : RecyclerView.ViewHolder(binding.root) {

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
                chainPath.text = line.getHDPath(account.lastHDPath)
            } else {
                chainPath.text = line.address
            }
            updateView(line, selectedCosmosTags)

            if (account.type == BaseAccountType.MNEMONIC) {
                if (line.evmCompatible) {
                    chainLegacy.visibility = View.VISIBLE
                    chainLegacy.text = context.getString(R.string.str_evm)
                    chainLegacy.setBackgroundResource(R.drawable.round_box_evm)
                    chainLegacy.setTextColor(
                        ContextCompat.getColor(
                            context, R.color.color_base01
                        )
                    )
                } else if (!line.isDefault) {
                    chainLegacy.visibility = View.VISIBLE
                    chainLegacy.text = context.getString(R.string.str_legacy)
                    chainLegacy.setBackgroundResource(R.drawable.round_box_deprecated)
                    chainLegacy.setTextColor(
                        ContextCompat.getColor(
                            context, R.color.color_base02
                        )
                    )
                } else {
                    chainLegacy.visibility = View.GONE
                }

            } else {
                if (line.evmCompatible) {
                    chainLegacy.visibility = View.VISIBLE
                    chainLegacy.text = context.getString(R.string.str_evm)
                    chainLegacy.setBackgroundResource(R.drawable.round_box_evm)
                    chainLegacy.setTextColor(
                        ContextCompat.getColor(
                            context, R.color.color_base01
                        )
                    )
                } else {
                    chainLegacy.visibility = View.GONE
                }
            }

            line.stakeDenom?.let { denom ->
                var cnt = 0

                if (line.fetched) {
                    if (line is ChainBinanceBeacon) {
                        val availableAmount = line.lcdBalanceAmount(line.stakeDenom)
                        chainBalance.text = formatAmount(availableAmount.toString(), 8)
                        chainDenom.text = line.stakeDenom?.uppercase()
                        chainDenom.setTextColor(Color.parseColor("#ffffff"))
                        cnt = line.lcdAccountInfo?.balances?.size ?: 0

                    } else if (line is ChainOkt60) {
                        val availableAmount = line.lcdBalanceAmount(line.stakeDenom)
                        chainBalance.text = formatAmount(availableAmount.toString(), 18)
                        chainDenom.text = line.stakeDenom?.uppercase()
                        chainDenom.setTextColor(Color.parseColor("#ffffff"))
                        cnt = line.oktLcdAccountInfo?.value?.coins?.size ?: 0

                    } else {
                        if (line.cosmosBalances == null) {
                            chainNotRespond.visibility = View.VISIBLE
                            chainAssetCnt.visibility = View.GONE
                        } else {
                            BaseData.getAsset(line.apiName, denom)?.let { asset ->
                                val availableAmount =
                                    line.balanceAmount(denom).movePointLeft(asset.decimals ?: 6)
                                chainBalance.text =
                                    formatAmount(availableAmount.toString(), asset.decimals ?: 6)
                                chainDenom.text = asset.symbol
                                chainDenom.setTextColor(asset.assetColor())
                            }
                            cnt = line.cosmosBalances?.count() ?: 0
                        }
                    }
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

    private fun updateView(line: CosmosLine, selectedCosmosTags: MutableList<String>) {
        binding.apply {
            if (selectedCosmosTags.contains(line.tag)) {
                selectView.setBackgroundResource(R.drawable.item_wallet_select_bg)
            } else {
                selectView.setBackgroundResource(R.drawable.item_bg)
            }
        }
    }
}