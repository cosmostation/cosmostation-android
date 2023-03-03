package wannabit.io.cosmostaion.dialog

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import wannabit.io.cosmostaion.R

class WaitDialog : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        dialog!!.window!!.setBackgroundDrawableResource(R.color.colorTrans)
        dialog!!.window!!.setDimAmount(0.2f)
        return layoutInflater.inflate(R.layout.dialog_wait, null)
    }

    companion object {
        fun newInstance(): WaitDialog {
            return WaitDialog()
        }
    }
}