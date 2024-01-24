package wannabit.io.cosmostaion.ui.qr

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsetsController
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.journeyapps.barcodescanner.CaptureActivity
import com.journeyapps.barcodescanner.DecoratedBarcodeView
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.databinding.ActivityQrCodeBinding

class QrCodeActivity : CaptureActivity() {

    private lateinit var binding: ActivityQrCodeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        initFullScreen()
        binding.btnDisconnect.setOnClickListener {
            onBackPressed()
        }
    }

    override fun initializeContent(): DecoratedBarcodeView {
        binding = ActivityQrCodeBinding.inflate(layoutInflater)
        setContentView(binding.root)
        return binding.zxingBarcodeScanner
    }

    private fun initFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            val controller = window.insetsController
            if (controller != null) {
                controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
        }
    }

    override fun attachBaseContext(newBase: Context?) {
        super.attachBaseContext(BaseUtils.updateResources(newBase))
    }
}