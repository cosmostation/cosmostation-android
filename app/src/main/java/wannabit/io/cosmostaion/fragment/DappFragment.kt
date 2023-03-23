package wannabit.io.cosmostaion.fragment

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.webkit.WebChromeClient
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.MainActivity
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.dialog.CommonAlertDialog
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
        webView.loadUrl(
            "https://dapps.cosmostation.io?chain=" + baseActivity.mChainConfig.chainName() + "&theme=" + ThemeUtil.currentMode(
                context
            )
        )
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
            override fun onPageFinished(view: WebView?, url: String?) {
                webView.visibility = View.VISIBLE
                super.onPageFinished(view, url)
            }

            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                (activity as? MainActivity)?.let {
                    if (it.mAccount.isLedger) {
                        CommonAlertDialog.showSingleButton(
                            it,
                            it.getString(R.string.str_with_ledger),
                            it.getString(R.string.str_ledger_not_support_dapp_msg),
                            it.getString(R.string.str_confirm),
                            null,
                            true
                        )
                        return true

                    } else if (!it.mAccount.hasPrivateKey) {
                        CommonAlertDialog.showDoubleButton(
                            it,
                            it.getString(R.string.str_only_observe_title),
                            it.getString(R.string.str_only_observe_msg),
                            Html.fromHtml("<font color=\"#9C6CFF\">" + it.getString(R.string.str_add_mnemonics) + "</font>", Html.FROM_HTML_MODE_COMPACT),
                            { view: View? -> it.onAddMnemonicForAccount() },
                            it.getString(R.string.str_close),
                            null
                        )
                        return true
                    }
                }

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
            return fragment
        }
    }
}