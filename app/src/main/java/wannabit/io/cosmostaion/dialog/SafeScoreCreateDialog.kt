package wannabit.io.cosmostaion.dialog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.DialogSafeScoreCreateBinding
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.WUtil
import java.math.BigDecimal
import java.util.*

class SafeScoreCreateDialog : DialogFragment() {
    private var dialogSafeScoreCreateBinding: DialogSafeScoreCreateBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.color.colorTrans)
        dialogSafeScoreCreateBinding =
            DialogSafeScoreCreateBinding.inflate(inflater, container, false)
        WUtil.DpRiskRate2(
            context,
            BigDecimal(arguments!!.getString("riskRate")),
            dialogSafeScoreCreateBinding!!.riskRate,
            dialogSafeScoreCreateBinding!!.riskScore,
            dialogSafeScoreCreateBinding!!.riskLayer
        )
        dialogSafeScoreCreateBinding!!.currentPriceTitle.text = String.format(
            getString(R.string.str_current_title3), arguments!!.getString("denom")!!
                .uppercase(Locale.getDefault())
        )
        dialogSafeScoreCreateBinding!!.currentPrice.text = WDp.getDpRawDollor(
            context, BigDecimal(
                arguments!!.getString("currentPrice")
            ).movePointLeft(18), 4
        )
        dialogSafeScoreCreateBinding!!.liquidationPriceTitle.text = String.format(
            getString(R.string.str_liquidation_title3), arguments!!.getString("denom")!!
                .uppercase(Locale.getDefault())
        )
        dialogSafeScoreCreateBinding!!.liquidationPrice.text = WDp.getDpRawDollor(
            context, BigDecimal(
                arguments!!.getString("liquidationPrice")
            ), 4
        )
        dialogSafeScoreCreateBinding!!.btnNega.setOnClickListener { dialog!!.dismiss() }
        dialogSafeScoreCreateBinding!!.btnPosi.setOnClickListener {
            val resultIntent = Intent()
            targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, resultIntent)
            dialog!!.dismiss()
        }
        return dialogSafeScoreCreateBinding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogSafeScoreCreateBinding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?): SafeScoreCreateDialog {
            val frag = SafeScoreCreateDialog()
            frag.arguments = bundle
            return frag
        }
    }
}