package wannabit.io.cosmostaion.dialog

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.DialogSafeScoreConfirmBinding
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.WUtil
import java.math.BigDecimal
import java.util.*

class SafeScoreConfirmDialog : DialogFragment() {
    private var dialogSafeScoreConfirmBinding: DialogSafeScoreConfirmBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.color.colorTrans)
        dialogSafeScoreConfirmBinding =
            DialogSafeScoreConfirmBinding.inflate(inflater, container, false)
        WUtil.DpRiskRate2(
            context,
            BigDecimal(arguments!!.getString("beforeRiskRate")),
            dialogSafeScoreConfirmBinding!!.beforeRiskRate,
            dialogSafeScoreConfirmBinding!!.beforeRiskScore,
            dialogSafeScoreConfirmBinding!!.beforeRiskLayer
        )
        WUtil.DpRiskRate2(
            context,
            BigDecimal(arguments!!.getString("afterRiskRate")),
            dialogSafeScoreConfirmBinding!!.afterRiskRate,
            dialogSafeScoreConfirmBinding!!.afterRiskScore,
            dialogSafeScoreConfirmBinding!!.afterRiskLayer
        )
        dialogSafeScoreConfirmBinding!!.currentPriceTitle.text = String.format(
            getString(R.string.str_current_title3), arguments!!.getString("denom")!!
                .uppercase(Locale.getDefault())
        )
        dialogSafeScoreConfirmBinding!!.currentPrice.text = WDp.getDpRawDollor(
            context, BigDecimal(
                arguments!!.getString("currentPrice")
            ), 4
        )
        dialogSafeScoreConfirmBinding!!.beforeLiquidationPriceTitle.text = String.format(
            getString(R.string.str_before_liquidation_title2), arguments!!.getString("denom")!!
                .uppercase(Locale.getDefault())
        )
        dialogSafeScoreConfirmBinding!!.beforeLiquidationPrice.text = WDp.getDpRawDollor(
            context, BigDecimal(
                arguments!!.getString("beforeLiquidationPrice")
            ), 4
        )
        dialogSafeScoreConfirmBinding!!.afterLiquidationPriceTitle.text = String.format(
            getString(R.string.str_after_liquidation_title2), arguments!!.getString("denom")!!
                .uppercase(Locale.getDefault())
        )
        dialogSafeScoreConfirmBinding!!.afterLiquidationPrice.text = WDp.getDpRawDollor(
            context, BigDecimal(
                arguments!!.getString("afterLiquidationPrice")
            ), 4
        )
        dialogSafeScoreConfirmBinding!!.btnNega.setOnClickListener { dialog!!.dismiss() }
        dialogSafeScoreConfirmBinding!!.btnPosi.setOnClickListener {
            val resultIntent = Intent()
            targetFragment!!.onActivityResult(targetRequestCode, Activity.RESULT_OK, resultIntent)
            dialog!!.dismiss()
        }
        return dialogSafeScoreConfirmBinding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogSafeScoreConfirmBinding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?): SafeScoreConfirmDialog {
            val frag = SafeScoreConfirmDialog()
            frag.arguments = bundle
            return frag
        }
    }
}