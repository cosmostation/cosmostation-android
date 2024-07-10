package wannabit.io.cosmostaion.ui.main.setting.general

import android.app.Activity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils
import android.text.TextWatcher
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.ActivityDevDialogBinding

class DevDialogActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityDevDialogBinding.inflate(layoutInflater)
        val alertDialog =
            AlertDialog.Builder(this, R.style.AppTheme_AlertDialogTheme).setView(binding.root)

        val dialog = alertDialog.create()
        dialog.show()
        dialog.setCancelable(false)

        binding.apply {
            url.background = ContextCompat.getDrawable(
                this@DevDialogActivity, R.drawable.item_common_bg
            )

            url.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence, start: Int, count: Int, after: Int
                ) {
                }

                override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable) {
                    if (!TextUtils.isEmpty(binding.url.text.toString())) {
                        url.background = ContextCompat.getDrawable(
                            this@DevDialogActivity, R.drawable.item_common_white_border_bg
                        )
                    } else {
                        url.background = ContextCompat.getDrawable(
                            this@DevDialogActivity, R.drawable.item_common_bg
                        )
                    }
                }
            })

            btnCancel.setOnClickListener {
                finish()
            }

            btnConfirm.setOnClickListener {
                intent.apply {
                    putExtra("url", url.text.toString())
                    setResult(RESULT_OK, this)
                    finish()
                }
            }
        }
    }
}