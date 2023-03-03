package wannabit.io.cosmostaion.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.binance.dex.api.client.encoding.message.CancelOrderMessage
import com.google.gson.Gson
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.txs.wc.BnbWalletConnectActivity
import wannabit.io.cosmostaion.databinding.DialogWcCancelBinding

class WcCancelDialog : DialogFragment() {
    private var wcCancelBinding: DialogWcCancelBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.color.colorTrans)
        wcCancelBinding = DialogWcCancelBinding.inflate(inflater, container, false)
        val json = Gson().fromJson(
            arguments!!.getString("param"), JsonObject::class.java
        )
        val msg = Gson().fromJson(json.getAsJsonArray("msgs")[0], CancelOrderMessage::class.java)
        wcCancelBinding!!.wcCancelSymbol.text = msg.symbol
        wcCancelBinding!!.btnNega.setOnClickListener { dismiss() }
        wcCancelBinding!!.btnPosi.setOnClickListener {
            (activity as BnbWalletConnectActivity?)!!.onBnbSign(
                arguments!!.getLong("id")
            )
            dismiss()
        }
        return wcCancelBinding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        wcCancelBinding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?): WcCancelDialog {
            val frag = WcCancelDialog()
            frag.arguments = bundle
            return frag
        }
    }
}