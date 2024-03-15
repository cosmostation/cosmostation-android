package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.net.Uri
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.EXPLORER_BINANCE_URL
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_EXPLORER
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.common.dpTimeToYear
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatCurrentTimeToYear
import wannabit.io.cosmostaion.common.formatTxTime
import wannabit.io.cosmostaion.common.formatTxTimeStampToHour
import wannabit.io.cosmostaion.common.formatTxTimeToHour
import wannabit.io.cosmostaion.common.formatTxTimeToYear
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.common.voteDpTime
import wannabit.io.cosmostaion.data.model.res.BnbHistory
import wannabit.io.cosmostaion.data.model.res.CosmosHistory
import wannabit.io.cosmostaion.data.model.res.TransactionList
import wannabit.io.cosmostaion.databinding.ItemHistoryBinding
import java.math.RoundingMode

class HistoryViewHolder(
    val context: Context, private val binding: ItemHistoryBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun bindHistory(
        line: CosmosLine,
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

            txMessage.text = historyGroup.second.getMsgType(context, line.address)
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

            historyView.setOnClickListener {
                val historyUrl =
                    CosmostationConstants.EXPLORER_BASE_URL + "/" + line.apiName + "/transactions/" + historyGroup.second.data?.txhash
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(historyUrl)))
            }

            historyGroup.second.getDpCoin(line).let { dpCoins ->
                if (dpCoins.isNotEmpty()) {
                    if (dpCoins.size > 1) {
                        txCnt.visibility = View.VISIBLE
                        txCnt.text = "+" + (dpCoins.size - 1)
                    } else {
                        txCnt.visibility = View.GONE
                    }
                    BaseData.getAsset(line.apiName, dpCoins[0].denom)?.let { asset ->
                        asset.decimals?.let { decimal ->
                            val amount = dpCoins[0].amount.toBigDecimal().movePointLeft(decimal)
                                .setScale(decimal, RoundingMode.DOWN)
                            txAmount.text = formatAmount(amount.toString(), decimal)
                            txDenom.text = asset.symbol
                            txDenom.setTextColor(asset.assetColor())
                        }
                    }

                } else {
                    if (historyGroup.second.getMsgType(
                            context, line.address
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

            } ?: run {
                txAmount.text = ""
                txDenom.text = "-"
                txDenom.setTextColor(Color.parseColor("#ffffff"))
                txCnt.visibility = View.GONE
            }
        }
    }

    fun bindBnbHistory(
        line: CosmosLine,
        historyBnbGroup: Pair<String, BnbHistory>,
        headerIndex: Int,
        cnt: Int,
        position: Int
    ) {
        binding.apply {
            historyView.setBackgroundResource(R.drawable.item_bg)
            headerLayout.visibleOrGone(headerIndex == position)
            historyBnbGroup.second.timeStamp?.let { timeStamp ->
                val headerDate = formatTxTimeToYear(context, timeStamp)
                val currentDate = formatCurrentTimeToYear()

                if (headerDate == currentDate) {
                    headerTitle.text = context.getString(R.string.str_today)
                } else {
                    headerTitle.text = headerDate
                }
                headerCnt.text = "(" + cnt.toString() + ")"
            }

            if (historyBnbGroup.second.isSuccess()) {
                txSuccessImg.setImageResource(R.drawable.icon_history_success)
            } else {
                txSuccessImg.setImageResource(R.drawable.icon_history_fail)
            }

            txMessage.text = historyBnbGroup.second.bnbTxType(context, line.address)
            txHash.text = historyBnbGroup.second.txHash
            historyBnbGroup.second.timeStamp?.let { timeStamp ->
                txTime.text = formatTxTimeToHour(context, timeStamp)
            }
            historyBnbGroup.second.blockHeight?.let { height ->
                txHeight.text = "(" + height + ")"
                txHeight.visibility = View.VISIBLE
            } ?: run {
                txHeight.visibility = View.GONE
            }
            txDenom.text = "-"

            historyView.setOnClickListener {
                val historyUrl = EXPLORER_BINANCE_URL + "tx/" + historyBnbGroup.second.txHash
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(historyUrl)))
            }
        }
    }

    fun bindOktHistory(
        historyOktGroup: Pair<String, TransactionList>, headerIndex: Int, cnt: Int, position: Int
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

            historyView.setOnClickListener {
                val historyUrl = OKT_EXPLORER + "tx/" + historyOktGroup.second.txId
                context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(historyUrl)))
            }
        }
    }
}