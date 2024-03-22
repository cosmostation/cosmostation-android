package wannabit.io.cosmostaion.ui.main.setting.general

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AlertDialog
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.ActivityPushDialogBinding

class PushDialogActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        val title = bundle?.getString("title")
        val body = bundle?.getString("body")
        val url = bundle?.getString("link")

        val binding = ActivityPushDialogBinding.inflate(layoutInflater)
        val alertDialog = AlertDialog.Builder(this, R.style.AppTheme_AlertDialogTheme).setView(binding.root)

        val dialog = alertDialog.create()
        dialog.show()
        dialog.setCancelable(false)

        binding.apply {
            dialogTitle.text = title
            dialogBody.text = body
            btnClose.setOnClickListener {
                finish()
            }
            btnExplorer.visibility = if (url == null) View.GONE else View.VISIBLE
            btnExplorer.setOnClickListener {
                url?.let {
                    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(it))
                    startActivity(intent);
                    finish()
                }
            }
        }
    }
}