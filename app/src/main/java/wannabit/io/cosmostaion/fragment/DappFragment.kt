package wannabit.io.cosmostaion.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.*
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.MainActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.utils.ThemeUtil

class DappFragment : BaseFragment() {
    private lateinit var webView: WebView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
        ThemeUtil.themeColor = ThemeUtil.modLoad(requireActivity())
        ThemeUtil.applyTheme(ThemeUtil.themeColor)
    }

    override fun onRefreshTab() {
        super.onRefreshTab()
        webView.loadUrl("https://dapps.cosmostation.io?chain=" + baseActivity.mChainConfig.chainName())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_dapps, container, false)
        webView = view.findViewById(R.id.webview)
        initWebView()
        return view
    }

    private fun initWebView() {
        webView.settings.javaScriptEnabled = true
        webView.settings.userAgentString =
            webView.settings.userAgentString + " Cosmostation/APP/Android/DappTab" + BuildConfig.VERSION_NAME
        webView.settings.domStorageEnabled = true
        webView.settings.mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        webView.webChromeClient = WebChromeClient()
        webView.webViewClient = object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                if (url.startsWith("intent:")) {
                    val replacedUrl = if (BuildConfig.DEBUG) {
                        url.replace("wannabit.io.cosmostaion", "wannabit.io.cosmostaion.debug")
                    } else {
                        url
                    }

                    try {
                        val intent = Intent.parseUri(replacedUrl, Intent.URI_INTENT_SCHEME)
                        val existPackage: Intent? =
                            intent.getPackage()?.let {
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

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle?): DappFragment {
            val fragment = DappFragment()
            fragment.arguments = bundle
            return DappFragment()
        }
    }
}