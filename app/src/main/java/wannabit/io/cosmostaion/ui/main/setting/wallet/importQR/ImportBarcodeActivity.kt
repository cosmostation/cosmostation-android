package wannabit.io.cosmostaion.ui.main.setting.wallet.importQR

import android.annotation.SuppressLint
import android.app.Activity
import android.os.Build
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.DecodeCallback
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.ActivityImportBarcodeBinding

class ImportBarcodeActivity : AppCompatActivity() {

    private lateinit var binding: ActivityImportBarcodeBinding

    private lateinit var codeScanner: CodeScanner

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityImportBarcodeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        setUpClickAction()
    }

    @SuppressLint("WrongConstant")
    private fun initView() {
        codeScanner = CodeScanner(this, binding.barcodeScanner)
        codeScanner.decodeCallback = DecodeCallback {
            runOnUiThread {
                intent.apply {
                    putExtra("import", it.text)
                    setResult(RESULT_OK, this)
                    finish()
                    if (Build.VERSION.SDK_INT >= 34) {
                        overrideActivityTransition(Activity.OVERRIDE_TRANSITION_OPEN, R.anim.anim_fade_in, R.anim.anim_slide_out_bottom)
                    } else {
                        overridePendingTransition(R.anim.anim_fade_in, R.anim.anim_slide_out_bottom)
                    }
                }
            }
        }
        codeScanner.startPreview()
    }

    private fun setUpClickAction() {
        binding.btnDisconnect.setOnClickListener {
            finish()
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}