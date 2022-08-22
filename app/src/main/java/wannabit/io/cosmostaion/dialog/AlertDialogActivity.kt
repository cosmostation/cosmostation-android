package wannabit.io.cosmostaion.dialog

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import wannabit.io.cosmostaion.R

class AlertDialogActivity : Activity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val bundle = intent.extras
        val title = bundle?.getString("title")
        val body = bundle?.getString("body")
        val url = bundle?.getString("link")

        AlertDialog.Builder(this, R.style.DialogTheme).run {
            setTitle(title)
            setMessage(body)
            setPositiveButton(R.string.str_explore) { _, _ ->
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(intent);
                finish()
            }
            setNegativeButton(R.string.str_close) { _, _ ->
                finish()
            }
            show()
        }
    }
}