package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Context
import android.graphics.Color
import android.graphics.PorterDuff
import android.view.View
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.iotaCoinSymbol
import wannabit.io.cosmostaion.chain.fetcher.suiCoinSymbol
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.IOTA_MAIN_DENOM
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dpTimeToMonth
import wannabit.io.cosmostaion.common.dpTimeToYear
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatCurrentTimeToYear
import wannabit.io.cosmostaion.common.formatTxTime
import wannabit.io.cosmostaion.common.formatTxTimeStampToHour
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.CosmosHistory
import wannabit.io.cosmostaion.databinding.ItemHistoryBinding
import java.math.BigDecimal
import java.math.RoundingMode
import kotlin.math.abs

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
                headerCnt.text = "($cnt)"
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
                txHeight.text = "($height)"
                txHeight.visibility = View.VISIBLE
            } ?: run {
                txHeight.visibility = View.GONE
            }

            sendTxImage.visibleOrGone(
                historyGroup.second.getMsgCnt() == 1 && historyGroup.second.getMsgType(
                    context, chain.address
                ).equals(context.getString(R.string.tx_send), true)
            )
            sendTxImage.setColorFilter(
                ContextCompat.getColor(context, R.color.color_base02), PorterDuff.Mode.SRC_IN
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

    fun bindSuiHistory(
        chain: ChainSui,
        historySuiGroup: Pair<String, JsonObject>,
        headerIndex: Int,
        cnt: Int,
        position: Int
    ) {
        binding.apply {
            historyView.setBackgroundResource(R.drawable.item_bg)
            headerLayout.visibleOrGone(headerIndex == position)
            val headerDate = dpTimeToYear(historySuiGroup.second["timestampMs"].asString.toLong())
            val currentDate = formatCurrentTimeToYear()
            if (headerDate == currentDate) {
                headerTitle.text = context.getString(R.string.str_today)
            } else {
                headerTitle.text = headerDate
            }
            headerCnt.text = "($cnt)"

            if (historySuiGroup.second["effects"].asJsonObject["status"].asJsonObject["status"].asString == "success") {
                txSuccessImg.setImageResource(R.drawable.icon_history_success)
            } else {
                txSuccessImg.setImageResource(R.drawable.icon_history_fail)
            }

            var title = ""
            var description = ""
            val txs =
                historySuiGroup.second["transaction"].asJsonObject["data"].asJsonObject["transaction"].asJsonObject["transactions"].asJsonArray
            val sender =
                historySuiGroup.second["transaction"].asJsonObject["data"].asJsonObject["sender"].asString
            title = if (sender == chain.mainAddress) {
                context.getString(R.string.tx_send)
            } else {
                context.getString(R.string.tx_receive)
            }

            if (txs.size() > 0) {
                description = txs.last().asJsonObject.entrySet().first().key ?: ""
                description = if (txs.size() > 1) {
                    description + " + " + txs.size()
                } else {
                    description
                }

                txs.forEach { tx ->
                    if (tx.asJsonObject["MoveCall"] != null) {
                        val txType = tx.asJsonObject["MoveCall"].asJsonObject["function"].asString
                        if (txType.contains("request_withdraw_stake")) {
                            title = context.getString(R.string.str_unstake)
                        } else if (txType.contains("request_add_stake")) {
                            title = context.getString(R.string.str_stake)
                        } else if (txType.contains("swap")) {
                            title = context.getString(R.string.title_swap)
                        } else if (txType.contains("mint")) {
                            title = "Supply"
                        } else if (txType.contains("redeem")) {
                            title = "Redeem"
                        } else if (txType.contains("unwrap")) {
                            title = "unwrap"
                        }
                    }
                }

            } else {
                txDenom.text = "-"
                txAmount.text = ""
            }

            txMessage.text = title.ifEmpty {
                description
            }
            txHash.text = historySuiGroup.second["digest"].asString
            txTime.text = dpTimeToMonth(historySuiGroup.second["timestampMs"].asString.toLong())
            txHeight.text = "(" + historySuiGroup.second["checkpoint"].asString + ")"

            when (title) {
                context.getString(R.string.tx_send), context.getString(R.string.tx_receive) -> {
                    historySuiGroup.second["balanceChanges"]?.let { balanceChanges ->
                        if (title == context.getString(R.string.tx_send)) {
                            balanceChanges.asJsonArray.firstOrNull { it.asJsonObject["owner"].asJsonObject["AddressOwner"].asString != chain.mainAddress }
                        } else {
                            balanceChanges.asJsonArray.firstOrNull { it.asJsonObject["owner"].asJsonObject["AddressOwner"].asString == chain.mainAddress }
                        }?.let { change ->
                            val symbol = change.asJsonObject["coinType"].asString
                            val amount = change.asJsonObject["amount"].asString
                            val intAmount = abs(amount.toLong())

                            chain.suiFetcher?.let { fetcher ->
                                val asset = BaseData.getAsset(chain.apiName, symbol)
                                val metaData = fetcher.suiCoinMeta[symbol]

                                if (asset != null) {
                                    val dpAmount =
                                        intAmount.toBigDecimal().movePointLeft(asset.decimals ?: 9)
                                            .setScale(asset.decimals ?: 9, RoundingMode.DOWN)
                                    txAmount.text =
                                        formatAmount(dpAmount.toString(), asset.decimals ?: 9)
                                    txDenom.text = asset.symbol

                                } else if (metaData != null) {
                                    txDenom.text = metaData["symbol"].asString
                                    val dpAmount = intAmount.toBigDecimal()
                                        .movePointLeft(metaData["decimals"].asInt)
                                        ?.setScale(18, RoundingMode.DOWN)
                                    txAmount.text = formatAmount(dpAmount.toString(), 9)

                                } else {
                                    txDenom.text = symbol.suiCoinSymbol()
                                    val dpAmount = intAmount.toBigDecimal().movePointLeft(9)
                                        ?.setScale(18, RoundingMode.DOWN)
                                    txAmount.text = formatAmount(dpAmount.toString(), 9)
                                }
                            }
                        }
                    }
                }

                context.getString(
                    R.string.str_stake
                ) -> {
                    historySuiGroup.second["transaction"].asJsonObject["data"].asJsonObject["transaction"].asJsonObject["inputs"].asJsonArray?.let { inputs ->
                        inputs.forEach { input ->
                            if (input.asJsonObject["type"].asString == "pure" && input.asJsonObject["valueType"].asString == "u64") {
                                BaseData.getAsset(chain.apiName, SUI_MAIN_DENOM)?.let { asset ->
                                    val dpAmount =
                                        input.asJsonObject["value"].asString.toBigDecimal()
                                            .movePointLeft(asset.decimals ?: 9)
                                            .setScale(asset.decimals ?: 9, RoundingMode.DOWN)
                                    txAmount.text =
                                        formatAmount(dpAmount.toString(), asset.decimals ?: 9)
                                    txDenom.text = asset.symbol
                                }
                            }
                        }
                    }
                }

                context.getString(R.string.str_unstake) -> {
                    historySuiGroup.second["effects"].asJsonObject["gasUsed"].asJsonObject?.let { gasUsed ->
                        val computationCost = gasUsed["computationCost"].asString.toBigDecimal()
                        val storageCost = gasUsed["storageCost"].asString.toBigDecimal()
                        val storageRebate = gasUsed["storageRebate"].asString.toBigDecimal()
                        val gasFee = computationCost.add(storageCost).subtract(storageRebate)

                        BaseData.getAsset(chain.apiName, SUI_MAIN_DENOM)?.let { asset ->
                            historySuiGroup.second["balanceChanges"].asJsonArray.firstOrNull { it.asJsonObject["coinType"].asString == SUI_MAIN_DENOM }
                                ?.let { balance ->
                                    val amount =
                                        balance.asJsonObject["amount"].asString.toBigDecimal()
                                    val dpAmount =
                                        amount.add(gasFee).movePointLeft(asset.decimals ?: 9)
                                            .setScale(asset.decimals ?: 9, RoundingMode.DOWN)
                                    txAmount.text =
                                        formatAmount(dpAmount.toString(), asset.decimals ?: 9)
                                    txDenom.text = asset.symbol
                                }
                        }
                    }
                }

                else -> {
                    txDenom.text = "-"
                    txAmount.text = ""
                }
            }
        }
    }

    fun bindIotaHistory(
        chain: ChainIota,
        historyIotaGroup: Pair<String, JsonObject>,
        headerIndex: Int,
        cnt: Int,
        position: Int
    ) {
        binding.apply {
            historyView.setBackgroundResource(R.drawable.item_bg)
            headerLayout.visibleOrGone(headerIndex == position)
            val headerDate = dpTimeToYear(historyIotaGroup.second["timestampMs"].asString.toLong())
            val currentDate = formatCurrentTimeToYear()
            if (headerDate == currentDate) {
                headerTitle.text = context.getString(R.string.str_today)
            } else {
                headerTitle.text = headerDate
            }
            headerCnt.text = "($cnt)"

            if (historyIotaGroup.second["effects"].asJsonObject["status"].asJsonObject["status"].asString == "success") {
                txSuccessImg.setImageResource(R.drawable.icon_history_success)
            } else {
                txSuccessImg.setImageResource(R.drawable.icon_history_fail)
            }

            var title = ""
            var description = ""
            val txs =
                historyIotaGroup.second["transaction"].asJsonObject["data"].asJsonObject["transaction"].asJsonObject["transactions"].asJsonArray
            val sender =
                historyIotaGroup.second["transaction"].asJsonObject["data"].asJsonObject["sender"].asString
            title = if (sender == chain.mainAddress) {
                context.getString(R.string.tx_send)
            } else {
                context.getString(R.string.tx_receive)
            }

            if (txs.size() > 0) {
                description = txs.last().asJsonObject.entrySet().first().key ?: ""
                description = if (txs.size() > 1) {
                    description + " + " + txs.size()
                } else {
                    description
                }

                txs.forEach { tx ->
                    if (tx.asJsonObject["MoveCall"] != null) {
                        val txType = tx.asJsonObject["MoveCall"].asJsonObject["function"].asString
                        if (txType.contains("request_withdraw_stake")) {
                            title = context.getString(R.string.str_unstake)
                        } else if (txType.contains("request_add_stake")) {
                            title = context.getString(R.string.str_stake)
                        } else if (txType.contains("swap")) {
                            title = context.getString(R.string.title_swap)
                        } else if (txType.contains("mint")) {
                            title = "Supply"
                        } else if (txType.contains("redeem")) {
                            title = "Redeem"
                        } else if (txType.contains("unwrap")) {
                            title = "unwrap"
                        }
                    }
                }

            } else {
                txDenom.text = "-"
                txAmount.text = ""
            }

            txMessage.text = title.ifEmpty {
                description
            }
            txHash.text = historyIotaGroup.second["digest"].asString
            txTime.text = dpTimeToMonth(historyIotaGroup.second["timestampMs"].asString.toLong())
            txHeight.text = "(" + historyIotaGroup.second["checkpoint"].asString + ")"

            when (title) {
                context.getString(R.string.tx_send), context.getString(R.string.tx_receive) -> {
                    historyIotaGroup.second["balanceChanges"]?.let { balanceChanges ->
                        if (title == context.getString(R.string.tx_send)) {
                            balanceChanges.asJsonArray.firstOrNull { it.asJsonObject["owner"].asJsonObject["AddressOwner"].asString != chain.mainAddress }
                        } else {
                            balanceChanges.asJsonArray.firstOrNull { it.asJsonObject["owner"].asJsonObject["AddressOwner"].asString == chain.mainAddress }
                        }?.let { change ->
                            val symbol = change.asJsonObject["coinType"].asString
                            val amount = change.asJsonObject["amount"].asString
                            val intAmount = abs(amount.toLong())

                            chain.iotaFetcher?.let { fetcher ->
                                val asset = BaseData.getAsset(chain.apiName, symbol)
                                val metaData = fetcher.iotaCoinMeta[symbol]

                                if (asset != null) {
                                    val dpAmount =
                                        intAmount.toBigDecimal().movePointLeft(asset.decimals ?: 9)
                                            .setScale(asset.decimals ?: 9, RoundingMode.DOWN)
                                    txAmount.text =
                                        formatAmount(dpAmount.toString(), asset.decimals ?: 9)
                                    txDenom.text = asset.symbol

                                } else if (metaData != null) {
                                    txDenom.text = metaData["symbol"].asString
                                    val dpAmount = intAmount.toBigDecimal()
                                        .movePointLeft(metaData["decimals"].asInt)
                                        ?.setScale(18, RoundingMode.DOWN)
                                    txAmount.text = formatAmount(dpAmount.toString(), 9)

                                } else {
                                    txDenom.text = symbol.iotaCoinSymbol()
                                    val dpAmount = intAmount.toBigDecimal().movePointLeft(9)
                                        ?.setScale(18, RoundingMode.DOWN)
                                    txAmount.text = formatAmount(dpAmount.toString(), 9)
                                }
                            }
                        }
                    }
                }

                context.getString(
                    R.string.str_stake
                ) -> {
                    historyIotaGroup.second["transaction"].asJsonObject["data"].asJsonObject["transaction"].asJsonObject["inputs"].asJsonArray?.let { inputs ->
                        inputs.forEach { input ->
                            if (input.asJsonObject["type"].asString == "pure" && input.asJsonObject["valueType"].asString == "u64") {
                                BaseData.getAsset(chain.apiName, IOTA_MAIN_DENOM)?.let { asset ->
                                    val dpAmount =
                                        input.asJsonObject["value"].asString.toBigDecimal()
                                            .movePointLeft(asset.decimals ?: 9)
                                            .setScale(asset.decimals ?: 9, RoundingMode.DOWN)
                                    txAmount.text =
                                        formatAmount(dpAmount.toString(), asset.decimals ?: 9)
                                    txDenom.text = asset.symbol
                                }
                            }
                        }
                    }
                }

                context.getString(R.string.str_unstake) -> {
                    historyIotaGroup.second["effects"].asJsonObject["gasUsed"].asJsonObject?.let { gasUsed ->
                        val computationCost = gasUsed["computationCost"].asString.toBigDecimal()
                        val storageCost = gasUsed["storageCost"].asString.toBigDecimal()
                        val storageRebate = gasUsed["storageRebate"].asString.toBigDecimal()
                        val gasFee = computationCost.add(storageCost).subtract(storageRebate)

                        BaseData.getAsset(chain.apiName, IOTA_MAIN_DENOM)?.let { asset ->
                            historyIotaGroup.second["balanceChanges"].asJsonArray.firstOrNull { it.asJsonObject["coinType"].asString == IOTA_MAIN_DENOM }
                                ?.let { balance ->
                                    val amount =
                                        balance.asJsonObject["amount"].asString.toBigDecimal()
                                    val dpAmount =
                                        amount.add(gasFee).movePointLeft(asset.decimals ?: 9)
                                            .setScale(asset.decimals ?: 9, RoundingMode.DOWN)
                                    txAmount.text =
                                        formatAmount(dpAmount.toString(), asset.decimals ?: 9)
                                    txDenom.text = asset.symbol
                                }
                        }
                    }
                }

                else -> {
                    txDenom.text = "-"
                    txAmount.text = ""
                }
            }
        }
    }

    fun bindEthHistory(
        chain: BaseChain,
        historyGroup: Pair<String, JsonObject>,
        headerIndex: Int,
        cnt: Int,
        position: Int
    ) {
        binding.apply {
            historyView.setBackgroundResource(R.drawable.item_bg)
            headerLayout.visibleOrGone(headerIndex == position)
            val headerDate = dpTimeToYear(historyGroup.second["txTime"].asString.toLong())
            val currentDate = formatCurrentTimeToYear()

            if (headerDate == currentDate) {
                headerTitle.text = context.getString(R.string.str_today)
            } else {
                headerTitle.text = headerDate
            }
            headerCnt.text = "($cnt)"

            if (historyGroup.second["txStatus"].asString == "success") {
                txSuccessImg.setImageResource(R.drawable.icon_history_success)
            } else {
                txSuccessImg.setImageResource(R.drawable.icon_history_fail)
            }

            val from = historyGroup.second["from"].asJsonArray
            val to = historyGroup.second["to"].asJsonArray

            if (from.size() < 0 && to.size() < 0) {
                txMessage.text = "Contract call"
            } else {
                txMessage.text =
                    if (from.size() > 0 && from[0].asJsonObject["address"].asString == chain.evmAddress) {
                        context.getString(R.string.tx_send)
                    } else if (to.size() > 0 && to[0].asJsonObject["address"].asString == chain.evmAddress) {
                        context.getString(R.string.tx_receive)
                    } else {
                        "Contract call"
                    }
            }
            txHash.text = historyGroup.second["txHash"].asString
            txTime.text = dpTimeToMonth(historyGroup.second["txTime"].asString.toLong())

            if (historyGroup.second["tokenAddress"].asString.isEmpty()) {
                if (historyGroup.second["amount"].asString.isNotEmpty()) {
                    val amount = historyGroup.second["amount"].asString.toBigDecimal()
                    if (amount > BigDecimal.ZERO) {
                        txAmount.text = formatAmount(
                            historyGroup.second["amount"].asString, 18
                        )
                        txDenom.text = chain.getMainAssetSymbol().uppercase()
                        txDenom.setTextColor(Color.parseColor("#ffffff"))

                    } else {
                        txAmount.text = ""
                        txDenom.text = "-"
                        txDenom.setTextColor(Color.parseColor("#ffffff"))
                        txCnt.visibility = View.GONE
                    }
                } else {
                    txAmount.text = ""
                    txDenom.text = "-"
                    txDenom.setTextColor(Color.parseColor("#ffffff"))
                    txCnt.visibility = View.GONE
                }

            } else {
                chain.evmRpcFetcher?.evmTokens?.firstOrNull { it.address.uppercase() == historyGroup.second["tokenAddress"].asString.uppercase() }
                    ?.let { asset ->
                        txAmount.text =
                            formatAmount(historyGroup.second["amount"].asString, asset.decimals)
                        txDenom.text = asset.symbol.uppercase()
                        txDenom.setTextColor(Color.parseColor("#ffffff"))
                    } ?: run {
                    txAmount.text = ""
                    txDenom.text = "-"
                    txDenom.setTextColor(Color.parseColor("#ffffff"))
                    txCnt.visibility = View.GONE
                }
            }
        }
    }

    fun bindBitHistory(
        chain: ChainBitCoin86,
        historyBitGroup: Pair<String, JsonObject>,
        headerIndex: Int,
        cnt: Int,
        position: Int
    ) {
        binding.apply {
            historyView.setBackgroundResource(R.drawable.item_bg)
            headerLayout.visibleOrGone(headerIndex == position)
            val headerDate =
                if (historyBitGroup.second["status"].asJsonObject["block_time"] != null) {
                    dpTimeToYear(historyBitGroup.second["status"].asJsonObject["block_time"].asLong * 1000)
                } else {
                    "Mempool"
                }
            val currentDate = formatCurrentTimeToYear()

            if (headerDate == currentDate) {
                headerTitle.text = context.getString(R.string.str_today)
            } else {
                headerTitle.text = headerDate
            }
            headerCnt.text = "($cnt)"

            if (historyBitGroup.second["status"].asJsonObject["confirmed"].asBoolean) {
                txSuccessImg.setImageResource(R.drawable.icon_history_success)
                txSuccessImg.setColorFilter(
                    ContextCompat.getColor(context, R.color.color_green), PorterDuff.Mode.SRC_IN
                )
                txTime.text =
                    dpTimeToMonth(historyBitGroup.second["status"].asJsonObject["block_time"].asLong * 1000)
                chain.btcFetcher()?.let { fetcher ->
                    txHeight.text =
                        "(" + fetcher.btcBlockHeight.minus(historyBitGroup.second["status"].asJsonObject["block_height"].asLong) + " Confirmed)"
                }

            } else {
                txSuccessImg.setImageResource(R.drawable.icon_history_pending)
                txSuccessImg.setColorFilter(
                    ContextCompat.getColor(context, R.color.color_blue), PorterDuff.Mode.SRC_IN
                )
                txTime.text = ""
                txHeight.text = ""
            }
            txHash.text = historyBitGroup.second["txid"].asString

            val title: String
            var inputAmounts = BigDecimal.ZERO
            var outputAmounts = BigDecimal.ZERO
            val displayAmount: BigDecimal?
            val inputs = historyBitGroup.second["vin"].asJsonArray.filter {
                it.asJsonObject["prevout"].asJsonObject.has(
                    "scriptpubkey_address"
                ) && it.asJsonObject["prevout"].asJsonObject["scriptpubkey_address"].asString.uppercase() == chain.mainAddress.uppercase()
            }
            inputs.forEach { input ->
                val value =
                    input.asJsonObject["prevout"].asJsonObject["value"].asLong.toBigDecimal()
                inputAmounts = inputAmounts.add(value)
            }

            val outputs =
                historyBitGroup.second["vout"].asJsonArray.filter { it.asJsonObject.has("scriptpubkey_address") && it.asJsonObject["scriptpubkey_address"].asString.uppercase() == chain.mainAddress.uppercase() }
            outputs.forEach { output ->
                val value = output.asJsonObject["value"].asLong.toBigDecimal()
                outputAmounts = outputAmounts.add(value)
            }
            val fee = historyBitGroup.second["fee"].asLong.toBigDecimal()

            if (inputs.isNotEmpty()) {
                title = context.getString(R.string.tx_send)
                displayAmount = inputAmounts.subtract(outputAmounts).subtract(fee).movePointLeft(8)
                    .setScale(8, RoundingMode.DOWN)
            } else {
                title = context.getString(R.string.tx_receive)
                displayAmount = outputAmounts.movePointLeft(8).setScale(8, RoundingMode.DOWN)
            }

            txMessage.text = title
            txAmount.text = formatAmount(
                displayAmount.toString(), 8
            )
            txDenom.text = chain.getMainAssetSymbol()
            txDenom.setTextColor(Color.parseColor("#ffffff"))
        }
    }
}