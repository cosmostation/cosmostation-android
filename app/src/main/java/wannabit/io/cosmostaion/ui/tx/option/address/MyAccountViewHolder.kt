package wannabit.io.cosmostaion.ui.tx.option.address

import android.content.Context
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.databinding.ItemMyAccountBinding
import wannabit.io.cosmostaion.ui.tx.genTx.SendAssetType

class MyAccountViewHolder(
    val context: Context, private val binding: ItemMyAccountBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun myAccountBind(refAddress: RefAddress, sendAssetType: SendAssetType, cnt: Int = 0) {
        binding.apply {
            bottomView.visibleOrGone(adapterPosition == cnt)

            CoroutineScope(Dispatchers.IO).launch {
                val account =
                    AppDatabase.getInstance().baseAccountDao().selectAccount(refAddress.accountId)
                val address =
                    if (sendAssetType == SendAssetType.ONLY_EVM_COIN || sendAssetType == SendAssetType.ONLY_EVM_ERC20) {
                        refAddress.evmAddress
                    } else {
                        refAddress.dpAddress
                    }

                withContext(Dispatchers.Main) {
                    accountName.text = account?.name
                    accountAddress.text = address

                    allChains().firstOrNull { it.tag == refAddress.chainTag }?.let { chain ->
                        if (chain is ChainBitCoin86) {
                            chainTypeBadge.visibility = View.GONE
                            when (chain.accountKeyType.pubkeyType) {
                                PubKeyType.BTC_TAPROOT -> {
                                    chainBitSideBadge.setBackgroundResource(R.drawable.round_box_bit_taproot)
                                    chainBitSideBadge.setTextColor(
                                        ContextCompat.getColorStateList(
                                            context, R.color.color_base01
                                        )
                                    )
                                    chainBitSideBadge.text = context.getString(R.string.str_taproot)
                                    chainBitSideBadge.visibility = View.VISIBLE
                                    chainBadge.visibility = View.GONE
                                }

                                PubKeyType.BTC_NATIVE_SEGWIT -> {
                                    chainBitSideBadge.setBackgroundResource(R.drawable.round_box_bit)
                                    chainBitSideBadge.setTextColor(
                                        ContextCompat.getColorStateList(
                                            context, R.color.color_base01
                                        )
                                    )
                                    chainBitSideBadge.text =
                                        context.getString(R.string.str_native_segwit)
                                    chainBitSideBadge.visibility = View.VISIBLE
                                    chainBadge.visibility = View.GONE
                                }

                                else -> {
                                    chainBitSideBadge.visibility = View.GONE
                                    chainBadge.visibility = View.VISIBLE
                                    chainBadge.text =
                                        if (chain.accountKeyType.pubkeyType == PubKeyType.BTC_NESTED_SEGWIT) {
                                            context.getString(R.string.str_nested_segwit)
                                        } else {
                                            context.getString(R.string.str_legacy)
                                        }
                                }
                            }

                        } else {
                            chainBitSideBadge.visibility = View.GONE
                            if (!chain.isDefault) {
                                chainBadge.visibility = View.VISIBLE
                                chainBadge.text = context.getString(R.string.str_old)
                                chainBadge.setBackgroundResource(R.drawable.round_box_deprecated)
                                chainBadge.setTextColor(
                                    ContextCompat.getColor(
                                        context, R.color.color_base02
                                    )
                                )
                                when (chain.tag) {
                                    "okt996_Keccak" -> {
                                        chainTypeBadge.text =
                                            context.getString(R.string.str_ethsecp256k1)
                                        chainTypeBadge.visibility = View.VISIBLE
                                    }

                                    "okt996_Secp" -> {
                                        chainTypeBadge.text =
                                            context.getString(R.string.str_secp256k1)
                                        chainTypeBadge.visibility = View.VISIBLE
                                    }

                                    else -> {
                                        chainTypeBadge.visibility = View.GONE
                                    }
                                }

                            } else {
                                chainBadge.visibility = View.GONE
                                chainTypeBadge.visibility = View.GONE
                            }
                        }
                    }
                }
            }
        }
    }
}