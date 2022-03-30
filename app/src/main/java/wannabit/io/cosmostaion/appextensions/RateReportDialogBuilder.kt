package wannabit.io.cosmostaion.appextensions

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.EditText
import androidx.core.content.ContextCompat
import wannabit.io.cosmostaion.R

object RateReportDialogBuilder {

    fun show(context: Context, onPositiveClicked: (String) -> Unit) {
        val view = LayoutInflater.from(context).inflate(R.layout.rate_report_dialog_layout, null)
        val messageEditText = view.findViewById<EditText>(R.id.messageEditText)
        val dialog = AlertDialog.Builder(context)
            .setView(view)
            .setTitle(R.string.rate_us_title)
            .setPositiveButton(R.string.rate_submit) { _, _ ->
                onPositiveClicked.invoke(messageEditText.text.toString())
            }
            .create()

        dialog.setOnShowListener {
            dialog.getButton(AlertDialog.BUTTON_POSITIVE)
                ?.setTextColor(ContextCompat.getColor(context, R.color.textColorAccent))
            dialog.getButton(AlertDialog.BUTTON_NEGATIVE)
                ?.setTextColor(ContextCompat.getColor(context, R.color.textColorSecondary))
        }

        dialog.show()
    }
}