package wannabit.io.cosmostaion.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.DialogFragment
import com.binance.dex.api.client.encoding.message.NewOrderMessage
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.txs.wc.BnbWalletConnectActivity
import wannabit.io.cosmostaion.base.chains.Binance
import wannabit.io.cosmostaion.databinding.DialogWcTradeBinding
import wannabit.io.cosmostaion.utils.WDp
import java.math.BigDecimal
import java.util.*

class WcTradeDialog : DialogFragment() {
    private var dialogWcTradeBinding: DialogWcTradeBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.color.colorTrans)
        dialogWcTradeBinding = DialogWcTradeBinding.inflate(inflater, container, false)
        val json = Gson().fromJson(
            arguments!!.getString("param"), JsonObject::class.java
        )
        val rawMsg = Gson().fromJson(json.getAsJsonArray("msgs")[0], JsonObject::class.java)
        val msg = Gson().fromJson(json.getAsJsonArray("msgs")[0], NewOrderMessage::class.java)
        val pairDenom = msg.symbol.split("_").toTypedArray()
        dialogWcTradeBinding!!.wcTradeSymbol.text = pairDenom[0].split("-").toTypedArray()[0]
        dialogWcTradeBinding!!.wcTradePriceDenom.text = pairDenom[1].split("-").toTypedArray()[0]
        val dpPrice = BigDecimal(msg.price).movePointLeft(8)
        val dpAmount = BigDecimal(msg.quantity).movePointLeft(8)
        dialogWcTradeBinding!!.wcTradePrice.text =
            WDp.getDpAmount2(context, BigDecimal(msg.price), 8, 8)
        if (rawMsg["side"].asLong == 1L) {
            dialogWcTradeBinding!!.wcTradeSide.text = "BUY"
            dialogWcTradeBinding!!.wcTradeSide.setTextColor(
                ContextCompat.getColor(
                    activity!!, R.color.color_bnbBuy
                )
            )
            Picasso.get().load(
                Binance.BINANCE_COIN_IMG_URL + pairDenom[1].split("-").toTypedArray()[0].lowercase(
                    Locale.getDefault()
                ) + ".png"
            )
                .fit().placeholder(R.drawable.token_default).error(R.drawable.token_default)
                .into(dialogWcTradeBinding!!.fromCoinIcon)
            Picasso.get().load(
                Binance.BINANCE_COIN_IMG_URL + pairDenom[0].split("-").toTypedArray()[0].lowercase(
                    Locale.getDefault()
                ) + ".png"
            )
                .fit().placeholder(R.drawable.token_default).error(R.drawable.token_default)
                .into(dialogWcTradeBinding!!.toCoinIcon)
            dialogWcTradeBinding!!.fromCoinSymbol.text = pairDenom[1].split("-").toTypedArray()[0]
            dialogWcTradeBinding!!.toCoinSymbol.text = pairDenom[0].split("-").toTypedArray()[0]
            dialogWcTradeBinding!!.fromCoinAmount.text =
                WDp.getDpAmount2(context, dpAmount.multiply(dpPrice), 0, 8)
            dialogWcTradeBinding!!.toCoinAmount.text = WDp.getDpAmount2(context, dpAmount, 0, 8)
        } else if (rawMsg["side"].asLong == 2L) {
            dialogWcTradeBinding!!.wcTradeSide.text = "SELL"
            dialogWcTradeBinding!!.wcTradeSide.setTextColor(
                ContextCompat.getColor(
                    activity!!, R.color.color_bnbSell
                )
            )
            Picasso.get().load(
                Binance.BINANCE_COIN_IMG_URL + pairDenom[0].split("-").toTypedArray()[0].lowercase(
                    Locale.getDefault()
                ) + ".png"
            )
                .fit().placeholder(R.drawable.token_default).error(R.drawable.token_default)
                .into(dialogWcTradeBinding!!.fromCoinIcon)
            Picasso.get().load(
                Binance.BINANCE_COIN_IMG_URL + pairDenom[1].split("-").toTypedArray()[0].lowercase(
                    Locale.getDefault()
                ) + ".png"
            )
                .fit().placeholder(R.drawable.token_default).error(R.drawable.token_default)
                .into(dialogWcTradeBinding!!.toCoinIcon)
            dialogWcTradeBinding!!.fromCoinSymbol.text = pairDenom[0].split("-").toTypedArray()[0]
            dialogWcTradeBinding!!.toCoinSymbol.text = pairDenom[1].split("-").toTypedArray()[0]
            dialogWcTradeBinding!!.fromCoinAmount.text = WDp.getDpAmount2(context, dpAmount, 0, 8)
            dialogWcTradeBinding!!.toCoinAmount.text =
                WDp.getDpAmount2(context, dpAmount.multiply(dpPrice), 0, 8)
        }
        dialogWcTradeBinding!!.btnNega.setOnClickListener { dialog!!.dismiss() }
        dialogWcTradeBinding!!.btnPosi.setOnClickListener {
            (activity as BnbWalletConnectActivity?)!!.onBnbSign(
                arguments!!.getLong("id")
            )
            dismiss()
        }
        return dialogWcTradeBinding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogWcTradeBinding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?): WcTradeDialog {
            val frag = WcTradeDialog()
            frag.arguments = bundle
            return frag
        }
    }
}