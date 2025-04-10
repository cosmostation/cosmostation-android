package wannabit.io.cosmostaion.ui.main.dapp

import android.annotation.SuppressLint
import android.app.Activity
import android.app.Dialog
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import com.google.android.material.bottomsheet.BottomSheetBehavior
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.databinding.FragmentWepBinding

class DappWepFragment : BottomSheetDialogFragment() {

    private var _binding: FragmentWepBinding? = null
    private val binding get() = _binding!!
    private var behavior: BottomSheetBehavior<View>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWepBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initWebView()
        val bottomSheet = dialog?.findViewById<View>(R.id.design_bottom_sheet) as View
        behavior = BottomSheetBehavior.from(bottomSheet)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        val dialog = super.onCreateDialog(savedInstanceState)
        dialog.setOnShowListener { dialogInterface ->
            val bottomSheetDialog = dialogInterface as BottomSheetDialog
            setupRatio(bottomSheetDialog)
        }
        return dialog
    }

    private fun setupRatio(bottomSheetDialog: BottomSheetDialog) {
        val bottomSheet = bottomSheetDialog.findViewById<View>(R.id.design_bottom_sheet) as View
        val behavior = BottomSheetBehavior.from(bottomSheet)
        val layoutParams = bottomSheet.layoutParams
        layoutParams.height = bottomSheetDialogDefaultHeight(windowHeight())
        bottomSheet.layoutParams = layoutParams
        behavior.state = BottomSheetBehavior.STATE_EXPANDED
    }

    private fun bottomSheetDialogDefaultHeight(windowHeight: Int): Int {
        return windowHeight * 19 / 20
    }

    private fun windowHeight(): Int {
        val displayMetrics = DisplayMetrics()
        (context as Activity?)!!.windowManager.defaultDisplay.getMetrics(displayMetrics)
        return displayMetrics.heightPixels
    }

    override fun onStart() {
        super.onStart()

        val bottomSheetDialog = dialog as BottomSheetDialog
        val bottomSheet =
            bottomSheetDialog.findViewById<View>(com.google.android.material.R.id.design_bottom_sheet)

        bottomSheet?.let { sheet ->
            val behavior = BottomSheetBehavior.from(sheet)
            behavior.isHideable = true
            behavior.skipCollapsed = true
        }
    }

    override fun onResume() {
        super.onResume()
        binding.webView.loadUrl("https://dapps.cosmostation.io")
    }

    @SuppressLint("SetJavaScriptEnabled", "ClickableViewAccessibility")
    private fun initWebView() {
        binding.apply {
            webView.settings.javaScriptEnabled = true
            webView.settings.userAgentString =
                webView.settings.userAgentString + " Cosmostation/APP/Android/DappTab" + BuildConfig.VERSION_NAME
            webView.settings.domStorageEnabled = true
            webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
            webView.webChromeClient = WebChromeClient()
            webView.setOnTouchListener { _, event ->
                when (event.action) {
                    MotionEvent.ACTION_DOWN -> {
                        if (event.y > windowHeight() / 2) {
                            behavior?.setHideable(false)
                            behavior?.isDraggable = false
                        } else {
                            behavior?.isDraggable = true
                            behavior?.setHideable(
                                true
                            )
                        }
                    }
                }
                false
            }
            webView.webViewClient = object : WebViewClient() {
                override fun onPageFinished(view: WebView?, url: String?) {
                    super.onPageFinished(view, url)
                }

                override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                    if (url.startsWith("intent:")) {
                        val replacedUrl = if (BuildConfig.DEBUG) {
                            url.replace("wannabit.io.cosmostaion", "wannabit.io.cosmostaion.debug")
                        } else {
                            url
                        }

                        try {
                            val intent = Intent.parseUri(replacedUrl, Intent.URI_INTENT_SCHEME)
                            val existPackage: Intent? = intent.getPackage()?.let {
                                activity?.packageManager?.getLaunchIntentForPackage(it)
                            }
                            if (existPackage != null) {
                                startActivity(intent)
                            } else {
                                val marketIntent = Intent(Intent.ACTION_VIEW)
                                marketIntent.data =
                                    Uri.parse("market://details?id=" + intent.getPackage())
                                startActivity(marketIntent)
                            }
                            return true
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    } else {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
                        return true
                    }
                    return false
                }
            }
        }
    }
}