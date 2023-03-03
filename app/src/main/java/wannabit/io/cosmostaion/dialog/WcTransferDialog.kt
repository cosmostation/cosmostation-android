package wannabit.io.cosmostaion.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.binance.dex.api.client.encoding.message.TransferMessage
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.squareup.picasso.Picasso
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.txs.wc.BnbWalletConnectActivity
import wannabit.io.cosmostaion.base.chains.Binance
import wannabit.io.cosmostaion.databinding.DialogWcTransferBinding
import wannabit.io.cosmostaion.utils.WDp
import java.math.BigDecimal
import java.util.*

class WcTransferDialog : DialogFragment() {
    private var dialogWcTransferBinding: DialogWcTransferBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.color.colorTrans)
        dialogWcTransferBinding = DialogWcTransferBinding.inflate(inflater, container, false)
        val json = Gson().fromJson(
            arguments!!.getString("param"), JsonObject::class.java
        )
        val msg = Gson().fromJson(json.getAsJsonArray("msgs")[0], TransferMessage::class.java)
        if (msg.inputs != null && msg.inputs.size > 0 && msg.outputs != null && msg.outputs.size > 0) {
            dialogWcTransferBinding!!.wcRecipientAddress.text = msg.outputs[0].address
            val dpDenom = msg.outputs[0].coins[0].denom.split("-").toTypedArray()[0]
            val dpAmount = BigDecimal(msg.outputs[0].coins[0].amount).movePointLeft(8)
            Picasso.get()
                .load(Binance.BINANCE_COIN_IMG_URL + dpDenom.lowercase(Locale.getDefault()) + ".png")
                .fit().placeholder(R.drawable.token_default).error(R.drawable.token_default)
                .into(dialogWcTransferBinding!!.wcSendCoinIcon)
            dialogWcTransferBinding!!.wcSendCoinSymbol.text = dpDenom
            dialogWcTransferBinding!!.wcSendAmount.text = WDp.getDpAmount2(context, dpAmount, 0, 8)
            dialogWcTransferBinding!!.wcMemo.text = arguments!!.getString("memo")
        }
        dialogWcTransferBinding!!.btnNega.setOnClickListener { dialog!!.dismiss() }
        dialogWcTransferBinding!!.btnPosi.setOnClickListener {
            (activity as BnbWalletConnectActivity?)!!.onBnbSign(
                arguments!!.getLong("id")
            )
            dismiss()
        }
        return dialogWcTransferBinding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogWcTransferBinding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?): WcTransferDialog {
            val frag = WcTransferDialog()
            frag.arguments = bundle
            return frag
        }
    }
}