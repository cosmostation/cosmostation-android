package wannabit.io.cosmostaion.ui.wallet

import android.content.Context
import android.graphics.Color
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.database.model.BaseAccount
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.ItemWalletSelectBinding
import java.math.BigDecimal
import java.math.RoundingMode

class WalletSelectViewHolder(
    val context: Context, private val binding: ItemWalletSelectBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun evmBind(
        account: BaseAccount,
        line: EthereumLine,
        selectedEvmTags: MutableList<String>,
        evmSelectListener: WalletSelectAdapter.SelectListener
    ) {
        binding.apply {
            chainImg.setImageResource(line.logo)
            chainName.text = line.name.uppercase()
            if (account.type == BaseAccountType.MNEMONIC) {
                chainPath.text = line.getHDPath(account.lastHDPath)
            } else if (line.accountPrefix?.isNotEmpty() == true) {
                chainPath.text = ByteUtils.convertBech32ToEvm(line.address)
            } else {
                chainPath.text = line.address
            }
            updateView(line, selectedEvmTags)

            if (line.fetched) {
                skeletonChainValue.visibility = View.GONE
                val availableAmount =
                    line.evmBalance.movePointLeft(18).setScale(18, RoundingMode.DOWN)
                chainBalance.text = formatAmount(availableAmount.toString(), 18)
                chainDenom.text = line.coinSymbol
                line.stakeDenom?.let { denom ->
                    BaseData.getAsset(line.apiName, denom)?.let { asset ->
                        chainDenom.setTextColor(asset.assetColor())
                    }
                } ?: run {
                    chainDenom.setTextColor(Color.parseColor("#ffffff"))
                }

                if (line.evmBalance > BigDecimal.ZERO) {
                    chainAssetCnt.text = "1 Coins"
                } else {
                    chainAssetCnt.text = "0 Coins"
                }
            }

            selectView.setOnClickListener {
                if (selectedEvmTags.contains(line.tag)) {
                    selectedEvmTags.removeIf { it == line.tag }
                } else {
                    selectedEvmTags.add(line.tag)
                }
                updateView(line, selectedEvmTags)
                evmSelectListener.evmSelect(selectedEvmTags)
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
                chainPath.text = line.getHDPath(account.lastHDPath)
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
                chainPath.text = line.address
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
                    if (line is ChainBinanceBeacon) {
                        val availableAmount = line.lcdBalanceAmount(line.stakeDenom)
                        chainBalance.text = formatAmount(availableAmount.toString(), 8)
                        chainDenom.text = line.stakeDenom?.uppercase()
                        chainDenom.setTextColor(Color.parseColor("#ffffff"))
                        cnt = line.lcdAccountInfo?.balances?.size ?: 0

                        chainBalance.visibility = View.VISIBLE
                        chainDenom.visibility = View.VISIBLE
                        chainAssetCnt.visibility = View.VISIBLE

                    } else if (line is ChainOkt996Keccak) {
                        val availableAmount = line.lcdBalanceAmount(line.stakeDenom)
                        chainBalance.text = formatAmount(availableAmount.toString(), 18)
                        chainDenom.text = line.stakeDenom?.uppercase()
                        chainDenom.setTextColor(Color.parseColor("#ffffff"))
                        cnt = line.oktLcdAccountInfo?.value?.coins?.size ?: 0

                        chainBalance.visibility = View.VISIBLE
                        chainDenom.visibility = View.VISIBLE
                        chainAssetCnt.visibility = View.VISIBLE

                    } else {
                        if (line.cosmosBalances == null) {
                            chainBalance.visibility = View.GONE
                            chainDenom.visibility = View.GONE
                            respondLayout.visibility = View.VISIBLE
                            chainAssetCnt.visibility = View.GONE

                        } else {
                            chainBalance.visibility = View.VISIBLE
                            chainDenom.visibility = View.VISIBLE
                            respondLayout.visibility = View.GONE
                            chainAssetCnt.visibility = View.VISIBLE

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