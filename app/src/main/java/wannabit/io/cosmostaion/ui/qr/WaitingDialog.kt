package wannabit.io.cosmostaion.ui.qr

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import wannabit.io.cosmostaion.R


class WaitingDialog : DialogFragment() {

    companion object {
        fun newInstance(): WaitingDialog {
            val dialog = WaitingDialog()
            dialog.isCancelable = false
            return dialog
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View? {
        dialog!!.window!!.setBackgroundDrawableResource(R.color.color_transparent)
        dialog!!.window!!.setDimAmount(0.5f)
        return layoutInflater.inflate(R.layout.loading_dialog, container, false)
    }
}