package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dpTimeToYear
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatCurrentTimeToYear
import wannabit.io.cosmostaion.common.formatTxTime
import wannabit.io.cosmostaion.common.formatTxTimeStampToHour
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.common.voteDpTime
import wannabit.io.cosmostaion.data.model.res.CosmosHistory
import wannabit.io.cosmostaion.data.model.res.TransactionList
import wannabit.io.cosmostaion.databinding.ItemHistoryBinding
import java.math.RoundingMode

class HistoryViewHolder(
    val context: Context, private val binding: ItemHistoryBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindHistory(
        chain: BaseChain,
        historyGroup: Pair<String, CosmosHistory>,
        headerIndex: Int,
        cnt: Int,
        position: Int
    ) {
        binding.apply {
            historyView.setBackgroundResource(R.drawable.item_bg)
            headerLayout.visibleOrGone(headerIndex == position)
            historyGroup.second.header?.let { header ->
                val headerDate = formatTxTime(context, header.timestamp)
                val currentDate = formatCurrentTimeToYear()

                if (headerDate == currentDate) {
                    headerTitle.text = context.getString(R.string.str_today)
                } else {
                    headerTitle.text = headerDate
                }
                headerCnt.text = "(" + cnt.toString() + ")"
            }

            if (historyGroup.second.isSuccess()) {
                txSuccessImg.setImageResource(R.drawable.icon_history_success)
            } else {
                txSuccessImg.setImageResource(R.drawable.icon_history_fail)
            }

            txMessage.text = historyGroup.second.getMsgType(context, chain.address)
            txHash.text = historyGroup.second.data?.txhash
            historyGroup.second.header?.let { header ->
                txTime.text = formatTxTimeStampToHour(context, header.timestamp)
            }
            historyGroup.second.data?.height?.let { height ->
                txHeight.text = "(" + height + ")"
                txHeight.visibility = View.VISIBLE
            } ?: run {
                txHeight.visibility = View.GONE
            }

            sendTxImage.visibleOrGone(
                historyGroup.second.getMsgCnt() == 1 && historyGroup.second.getMsgType(
                    context,
                    chain.address
                ).equals(context.getString(R.string.tx_send), true)
            )
            sendTxImage.setColorFilter(
                ContextCompat.getColor(context, R.color.color_base02),
                PorterDuff.Mode.SRC_IN
            )

            historyGroup.second.getDpCoin(chain).let { dpCoins ->
                if (dpCoins.isNotEmpty()) {
                    if (dpCoins.size > 1) {
                        txCnt.visibility = View.VISIBLE
                        txCnt.text = "+" + (dpCoins.size - 1)
                    } else {
                        txCnt.visibility = View.GONE
                    }
                    BaseData.getAsset(chain.apiName, dpCoins[0].denom)?.let { asset ->
                        asset.decimals?.let { decimal ->
                            val amount = dpCoins[0].amount.toBigDecimal().movePointLeft(decimal)
                                .setScale(decimal, RoundingMode.DOWN)
                            txAmount.text = formatAmount(amount.toString(), decimal)
                            txDenom.text = asset.symbol
                            txDenom.setTextColor(asset.assetColor())
                        }
                    }

                } else {
                    historyGroup.second.getDpToken(chain)?.let { dpTokens ->
                        dpTokens.second.movePointLeft(dpTokens.first.decimals)
                            ?.setScale(dpTokens.first.decimals, RoundingMode.DOWN)
                            ?.let { dpAmount ->
                                txAmount.text =
                                    formatAmount(dpAmount.toPlainString(), dpTokens.first.decimals)
                                txDenom.text = dpTokens.first.symbol
                                txDenom.setTextColor(Color.parseColor("#ffffff"))
                            }

                    } ?: run {
                        if (historyGroup.second.getMsgType(
                                context, chain.address
                            ) == context.getString(R.string.tx_vote)
                        ) {
                            txDenom.text = historyGroup.second.getVoteOption()
                            txDenom.setTextColor(Color.parseColor("#ffffff"))
                            txAmount.text = ""
                        } else {
                            txAmount.text = ""
                            txDenom.text = "-"
                            txDenom.setTextColor(Color.parseColor("#ffffff"))
                            txCnt.visibility = View.GONE
                        }
                    }
                }

            } ?: run {
                txAmount.text = ""
                txDenom.text = "-"
                txDenom.setTextColor(Color.parseColor("#ffffff"))
                txCnt.visibility = View.GONE
            }
        }
    }

    fun bindOktHistory(
        historyOktGroup: Pair<String, TransactionList>,
        headerIndex: Int,
        cnt: Int,
        position: Int
    ) {
        binding.apply {
            historyView.setBackgroundResource(R.drawable.item_bg)
            headerLayout.visibleOrGone(headerIndex == position)
            historyOktGroup.second.transactionTime.let { timeStamp ->
                val headerDate = dpTimeToYear(timeStamp.toLong())
                val currentDate = formatCurrentTimeToYear()

                if (headerDate == currentDate) {
                    headerTitle.text = context.getString(R.string.str_today)
                } else {
                    headerTitle.text = headerDate
                }
                headerCnt.text = "(" + cnt.toString() + ")"
            }

            if (historyOktGroup.second.state == "success") {
                txSuccessImg.setImageResource(R.drawable.icon_history_success)
            } else {
                txSuccessImg.setImageResource(R.drawable.icon_history_fail)
            }

            txMessage.text = historyOktGroup.second.height
            txHash.text = historyOktGroup.second.txId
            historyOktGroup.second.transactionTime.let { timeStamp ->
                txTime.text = voteDpTime(timeStamp.toLong())
            }
            txDenom.text = "-"
        }
    }
}