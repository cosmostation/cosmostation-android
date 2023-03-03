package wannabit.io.cosmostaion.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.DialogSafeScoreStatusBinding
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.WUtil
import java.math.BigDecimal
import java.util.*

class SafeScoreStatusDialog : DialogFragment() {
    private var dialogSafeScoreStatusBinding: DialogSafeScoreStatusBinding? = null
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.color.colorTrans)
        dialogSafeScoreStatusBinding =
            DialogSafeScoreStatusBinding.inflate(inflater, container, false)
        WUtil.DpRiskRate2(
            context,
            BigDecimal(arguments!!.getString("riskRate")),
            dialogSafeScoreStatusBinding!!.riskRate,
            dialogSafeScoreStatusBinding!!.riskScore,
            dialogSafeScoreStatusBinding!!.riskLayer
        )
        dialogSafeScoreStatusBinding!!.currentPriceTitle.text = String.format(
            getString(R.string.str_current_title3), arguments!!.getString("denom")!!
                .uppercase(Locale.getDefault())
        )
        dialogSafeScoreStatusBinding!!.currentPrice.text = WDp.getDpRawDollor(
            context, BigDecimal(
                arguments!!.getString("currentPrice")
            ), 4
        )
        dialogSafeScoreStatusBinding!!.liquidationPriceTitle.text = String.format(
            getString(R.string.str_liquidation_title3), arguments!!.getString("denom")!!
                .uppercase(Locale.getDefault())
        )
        dialogSafeScoreStatusBinding!!.liquidationPrice.text = WDp.getDpRawDollor(
            context, BigDecimal(
                arguments!!.getString("liquidationPrice")
            ), 4
        )
        dialogSafeScoreStatusBinding!!.btnNega.setOnClickListener { dialog!!.dismiss() }
        return dialogSafeScoreStatusBinding!!.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        dialogSafeScoreStatusBinding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?): SafeScoreStatusDialog {
            val frag = SafeScoreStatusDialog()
            frag.arguments = bundle
            return frag
        }
    }
}