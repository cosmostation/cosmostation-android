package wannabit.io.cosmostaion.ui.main.dapp

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.PorterDuff
import android.graphics.PorterDuffColorFilter
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Base64
import android.util.Log
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cosmos.tx.v1beta1.ServiceGrpc
import com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxRequest
import com.cosmos.tx.v1beta1.TxProto
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import com.google.protobuf.ByteString
import com.google.protobuf.util.JsonFormat
import com.walletconnect.android.Core
import com.walletconnect.android.CoreClient
import com.walletconnect.sign.client.Sign
import com.walletconnect.sign.client.SignClient
import com.walletconnect.sign.client.SignInterface
import com.walletconnect.util.bytesToHex
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import net.i2p.crypto.eddsa.Utils
import org.bouncycastle.util.Strings
import org.json.JSONArray
import org.json.JSONObject
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.http.HttpService
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.PubKeyType
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.chain.cosmosClass.ChainInjective
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.testnetClass.ChainBitcoin84Testnet
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.BaseConstant.COSMOS_KEY_TYPE_PUBLIC
import wannabit.io.cosmostaion.common.BaseConstant.ETHERMINT_KEY_TYPE_PUBLIC
import wannabit.io.cosmostaion.common.BaseConstant.INJECTIVE_KEY_TYPE_PUBLIC
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.CosmostationConstants.DAPP_ADDITIONAL_SCRIPT
import wannabit.io.cosmostaion.common.formatJsonOptions
import wannabit.io.cosmostaion.common.jsonRpcResponse
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.safeApiCall
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.req.EstimateGasParams
import wannabit.io.cosmostaion.data.model.req.EstimateGasParamsWithValue
import wannabit.io.cosmostaion.data.model.req.EthCall
import wannabit.io.cosmostaion.data.model.req.JsonRpcRequest
import wannabit.io.cosmostaion.data.model.req.PubKey
import wannabit.io.cosmostaion.data.model.req.Signature
import wannabit.io.cosmostaion.data.model.res.NetworkResult
import wannabit.io.cosmostaion.data.repository.tx.TxRepositoryImpl
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModelProviderFactory
import wannabit.io.cosmostaion.data.viewmodel.tx.TxViewModel
import wannabit.io.cosmostaion.data.viewmodel.tx.TxViewModelProviderFactory
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.ActivityDappBinding
import wannabit.io.cosmostaion.sign.Signer
import java.io.BufferedReader
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.util.TreeMap
import java.util.concurrent.TimeUnit

class DappActivity : BaseActivity() {

    private lateinit var binding: ActivityDappBinding

    private var allChains: MutableList<BaseChain>? = mutableListOf()

    private var selectChain: BaseChain? = null
    private var selectEvmChain: BaseChain? = null
    private var selectMajorChain: BaseChain? = null
    private var selectBitcoin: BaseChain? = null
    private var rpcUrl: String? = null
    private var web3j: Web3j? = null
    private var wcUrl: String? = ""
    private var bitNetwork: String = "mainnet"

    private var walletConnectURI: String? = null
    private var currentV2PairingUri: String? = null

    private var dAppType: DAppType? = null

    private var processingRequestID: Long? = null

    private var currentEvmChainId: String? = null

    private var currentMessageJson: JSONObject = JSONObject()
    private var currentMessageId: String = ""
    private var bitToAddress: String = ""
    private var bitSatAmount: String = ""

    private lateinit var walletViewModel: WalletViewModel
    private lateinit var txViewModel: TxViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDappBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        setUpObserve()
        allChains = initAllKeyData()
    }

    private suspend fun initData() {
        if (BaseData.baseAccount == null && BaseData.getLastAccount() != null) {
            BaseData.baseAccount = BaseData.getLastAccount()
        }
        if (BaseData.chainParam == null) {
            loadParam()
        }
        if (BaseData.assets?.isEmpty() == true) {
            loadAsset()
        }

        val ecoChain = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("selectedEvmChain", BaseChain::class.java)
        } else {
            (intent.getParcelableExtra("selectedEvmChain")) as? BaseChain
        }
        selectEvmChain = allChains?.firstOrNull { it.name == ecoChain?.name }

        val ecoMajorChain = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            intent.getParcelableExtra("selectedChain", BaseChain::class.java)
        } else {
            (intent.getParcelableExtra("selectedChain")) as? BaseChain
        }
        selectBitcoin = allChains?.firstOrNull { it.tag == ecoMajorChain?.tag }
    }

    private fun initViewModel() {
        val walletRepository = WalletRepositoryImpl()
        val walletViewModelProviderFactory = WalletViewModelProviderFactory(walletRepository)
        walletViewModel = ViewModelProvider(
            this, walletViewModelProviderFactory
        )[WalletViewModel::class.java]

        val txRepository = TxRepositoryImpl()
        val txViewModelProviderFactory = TxViewModelProviderFactory(txRepository)
        txViewModel = ViewModelProvider(this, txViewModelProviderFactory)[TxViewModel::class.java]
    }

    private fun initAllKeyData(): MutableList<BaseChain> {
        binding.loading.visibility = View.VISIBLE
        val result = allChains()
        lifecycleScope.launch(Dispatchers.IO) {
            initData()
            BaseData.baseAccount?.let { account ->
                account.apply {
                    if (type == BaseAccountType.MNEMONIC) {
                        result.forEach { chain ->
                            if (chain.publicKey == null) {
                                chain.setInfoWithSeed(seed, chain.setParentPath, lastHDPath)
                            }
                        }

                    } else if (type == BaseAccountType.PRIVATE_KEY) {
                        result.forEach { chain ->
                            if (chain.publicKey == null) {
                                chain.setInfoWithPrivateKey(privateKey)
                            }
                        }
                    }
                }
            }
            withContext(Dispatchers.Main) {
                binding.loading.visibility = View.GONE
                setUpDappView()
            }
        }
        return result
    }

    private fun setUpDappView() {
        binding.apply {
            accountName.text = BaseData.baseAccount?.name
            setUpBarFunction()
            dappWebView.apply {
                settings.apply {
                    javaScriptEnabled = true
                    userAgentString =
                        "$userAgentString Cosmostation/APP/Android/${BuildConfig.VERSION_NAME}"
                    domStorageEnabled = true
                    mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                    webViewClient = dappWebViewClient
                    webChromeClient = dappWebChromeClient
                }
            }

            dappWebView.visibility = View.VISIBLE
            dappWebView.addJavascriptInterface(DappJavascriptInterface(), "station")
            setDAppUrl()
        }
    }

    private fun setDAppUrl() {
        binding.apply {
            val filterIntentUri = Uri.parse(intent.getStringExtra("dappUrl").toString()) ?: null
            if (filterIntentUri.toString() != "null") {
                if (filterIntentUri?.host == WC_URL_SCHEME_HOST_DAPP_INTERNAL || filterIntentUri?.host == WC_URL_SCHEME_HOST_DAPP_EXTERNAL) {
                    val query = filterIntentUri.query
                    if (query?.contains("url") == true || query?.contains("uri") == true) {
                        dappWebView.loadUrl(query.substringAfter("="))
                    } else {
                        wcUrl = filterIntentUri.query
                        dappWebView.loadUrl(query.toString())
                    }
                } else {
                    dAppType = DAppType.DEEPLINK_WC2
                    connectWalletConnect(filterIntentUri?.query)
                }

            } else {
                wcUrl = intent.getStringExtra("dapp") ?: ""
                dappWebView.loadUrl(intent.getStringExtra("dapp") ?: "")
            }
        }

        Handler(Looper.getMainLooper()).postDelayed({
            if (!isFinishing && !isDestroyed) {
                if (BaseData.getInjectWarn()) {
                    InjectWarnFragment().show(
                        supportFragmentManager, InjectWarnFragment::class.java.name
                    )
                }
            }
        }, 1000)
    }

    private fun setUpBarFunction() {
        binding.apply {
            btnBack.setOnClickListener {
                if (dappWebView.canGoBack()) {
                    dappWebView.goBack()
                } else {
                    if (!BaseData.isBackGround) {
                        BaseData.appSchemeUrl = ""
                    }
                    finish()
                    emitCloseToWeb()
                    dappWebView.removeJavascriptInterface("station")
                }
            }
            btnNext.colorFilter = PorterDuffColorFilter(
                ContextCompat.getColor(
                    this@DappActivity, R.color.color_base03
                ), PorterDuff.Mode.SRC_IN
            )
            btnNext.isClickable = false
            btnNext.setOnClickListener {
                dappWebView.goForward()
            }

            btnClose.setOnClickListener {
                if (!BaseData.isBackGround) {
                    BaseData.appSchemeUrl = ""
                }
                finish()
                emitCloseToWeb()
                dappWebView.removeJavascriptInterface("station")
            }
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent.getStringExtra("dappUrl")?.let {
            Uri.parse(it)?.let { data ->
                if (data.scheme != WC_URL_SCHEME_COSMOSTATION) {
                    return
                }

                if (WC_URL_SCHEME_HOST_WC == data.host) {
                    walletConnectURI = if (data.query?.startsWith("uri=") == true) {
                        data.query?.replace("uri=", "")
                    } else {
                        data.query
                    }
                    if (walletConnectURI == null || walletConnectURI?.startsWith("wc") == false || isSessionConnected()) return
                    connectWalletConnect(walletConnectURI)

                } else if (WC_URL_SCHEME_HOST_DAPP_EXTERNAL == data.host || WC_URL_SCHEME_HOST_DAPP_INTERNAL == data.host) {
                    data.query?.let { url -> binding.dappWebView.loadUrl(url) }
                }
            }
        }
    }

    private fun isSessionConnected(): Boolean {
        if (walletConnectURI != null && currentV2PairingUri == walletConnectURI) {
            return true
        }
        return false
    }

    private fun initInjectScript(view: WebView?) {
        try {
            val inputStream = assets.open("injectScript.js")
            val existingScript = inputStream.bufferedReader().use(BufferedReader::readText)
            val script = if (wcUrl?.contains("astroport") == true) {
                existingScript + "\n" + DAPP_ADDITIONAL_SCRIPT
            } else {
                existingScript
            }
            view?.evaluateJavascript(script, null)
        } catch (_: Exception) {
        }
    }

    private fun connectWalletConnect(url: String?) {
        if (isSessionConnected()) return
        url?.let {
            walletConnectURI = url
            if (url.contains("@2")) {
                connectWalletConnectV2(url)
            }
        }
    }

    private fun connectWalletConnectV2(url: String) {
        currentV2PairingUri = walletConnectURI
        val pairingParams = Core.Params.Pair(url)
        CoreClient.Pairing.pair(pairingParams) {
            currentV2PairingUri = null
        }

        SignClient.setWalletDelegate(object : SignInterface.WalletDelegate {
            override fun onConnectionStateChange(state: Sign.Model.ConnectionState) {}

            override fun onError(error: Sign.Model.Error) {}

            override fun onSessionDelete(deletedSession: Sign.Model.DeletedSession) {
                currentV2PairingUri = null
            }

            override fun onSessionProposal(sessionProposal: Sign.Model.SessionProposal) {
                if (isFinishing) return

                val sessionNamespaces = mutableMapOf<String, Sign.Model.Namespace.Session>()

                runOnUiThread {
                    if (dAppType == DAppType.DEEPLINK_WC2) {
                        binding.dappWebView.loadUrl(sessionProposal.url)
                        wcUrl = sessionProposal.url
                        val rejectParams = Sign.Params.Reject(sessionProposal.proposerPublicKey, "")
                        SignClient.rejectSession(rejectParams) {}
                        CoreClient.Pairing.getPairings().forEach { pair ->
                            CoreClient.Pairing.disconnect(pair.topic)
                        }
                        dAppType = DAppType.INTERNAL_URL

                    } else {
                        val methods =
                            sessionProposal.requiredNamespaces.values.flatMap { it.methods }
                        val events = sessionProposal.requiredNamespaces.values.flatMap { it.events }

                        sessionProposal.requiredNamespaces.values.flatMap { it.chains }
                            .map { chain ->
                                val chainId = chain.split(":")[1]
                                val chainName = chain.split(":")[0]

                                allChains?.find { it.chainIdCosmos.lowercase() == chainId.lowercase() }
                                    ?.let { line ->
                                        selectChain = line
                                        sessionNamespaces[chainName] = Sign.Model.Namespace.Session(
                                            accounts = listOf("$chain:${line.address}"),
                                            methods = methods,
                                            events = events,
                                            extensions = null
                                        )
                                        val approveProposal = Sign.Params.Approve(
                                            proposerPublicKey = sessionProposal.proposerPublicKey,
                                            namespaces = sessionNamespaces
                                        )

                                        binding.loadingLayer.apply {
                                            postDelayed({
                                                visibility = View.GONE
                                            }, 2500)
                                        }

                                        SignClient.approveSession(approveProposal) { error ->
                                            Log.e("WCV2", error.throwable.stackTraceToString())
                                        }

                                    } ?: run {
                                    binding.loadingLayer.visibility = View.GONE
                                    makeToast(getString(R.string.error_not_support, chainId))
                                    return@runOnUiThread
                                }
                            }
                    }
                }
            }

            override fun onSessionRequest(sessionRequest: Sign.Model.SessionRequest) {
                if (isFinishing) return

                if (sessionRequest.request.id == processingRequestID) return
                processingRequestID = sessionRequest.request.id
                processV2SessionRequest(sessionRequest)
            }

            override fun onSessionSettleResponse(settleSessionResponse: Sign.Model.SettledSessionResponse) {}

            override fun onSessionUpdateResponse(sessionUpdateResponse: Sign.Model.SessionUpdateResponse) {}
        })
        return
    }

    private fun processV2SessionRequest(sessionRequest: Sign.Model.SessionRequest) {
        runOnUiThread {
            sessionRequest.request.apply {
                when (method) {
                    "cosmos_getAccounts" -> {
                        val v2Accounts = selectChain?.address?.let { address ->
                            val toV2Account = V2Account(
                                "secp256k1",
                                Base64.encodeToString(selectChain?.publicKey, Base64.NO_WRAP),
                                address
                            )
                            listOf(toV2Account)
                        } ?: emptyList()

                        val response = if (v2Accounts.isNotEmpty()) {
                            Sign.Params.Response(
                                sessionTopic = sessionRequest.topic,
                                jsonRpcResponse = Sign.Model.JsonRpcResponse.JsonRpcResult(
                                    id, Gson().toJson(v2Accounts)
                                )
                            )
                        } else {
                            Sign.Params.Response(
                                sessionTopic = sessionRequest.topic,
                                jsonRpcResponse = Sign.Model.JsonRpcResponse.JsonRpcError(
                                    id, 500, "No account"
                                )
                            )
                        }

                        SignClient.respond(response) { error ->
                            Log.e("WCV2", error.throwable.stackTraceToString())
                        }
                    }

                    "cosmos_signDirect" -> {
                        val signBundle = signBundle(id, params, "sign_direct")
                        showSignDialog(signBundle,
                            object : PopUpCosmosSignFragment.WcSignRawDataListener {
                                override fun sign(id: Long, data: String) {
                                    approveSignDirectV2Request(id, data, sessionRequest)
                                }

                                override fun cancel(id: Long) {
                                    cancelV2SignRequest(id, sessionRequest)
                                }
                            })
                    }

                    "cosmos_signAmino" -> {
                        val signBundle = signBundle(id, params, "sign_amino")
                        showSignDialog(signBundle,
                            object : PopUpCosmosSignFragment.WcSignRawDataListener {
                                override fun sign(id: Long, data: String) {
                                    approveSignAminoV2Request(id, data, sessionRequest)
                                }

                                override fun cancel(id: Long) {
                                    cancelV2SignRequest(id, sessionRequest)
                                }
                            })
                    }
                }
            }
        }
    }

    fun approveSignDirectV2Request(
        id: Long, transaction: String, sessionRequest: Sign.Model.SessionRequest
    ) {
        try {
            val jsonObject = Gson().fromJson(transaction, JsonObject::class.java)
            val signDocJson = jsonObject["signDoc"].asJsonObject
            val chainId = signDocJson["chainId"].asString
            val accountNumber = signDocJson["accountNumber"].asLong
            val signDoc = TxProto.SignDoc.newBuilder().setBodyBytes(
                ByteString.copyFrom(
                    Utils.hexToBytes(signDocJson["bodyBytes"].asString)
                )
            ).setAuthInfoBytes(
                ByteString.copyFrom(
                    Utils.hexToBytes(
                        signDocJson["authInfoBytes"].asString
                    )
                )
            ).setChainId(chainId).setAccountNumber(accountNumber).build()

            val signatureTx = Signer.signature(selectChain, signDoc.toByteArray())
            val pubKey = PubKey(
                pubKeyType(),
                Strings.fromByteArray(Base64.encode(selectChain?.publicKey, Base64.DEFAULT))
                    .replace("\n", "")
            )
            val signature = Signature(
                pubKey, signatureTx, null, null
            )
            val signModel = WcSignDirectModel(signDocJson, signature)

            val response = Sign.Params.Response(
                sessionTopic = sessionRequest.topic,
                jsonRpcResponse = Sign.Model.JsonRpcResponse.JsonRpcResult(
                    id, Gson().toJson(signModel)
                )
            )
            SignClient.respond(response) { error ->
                Log.e("WCV2", error.throwable.stackTraceToString())
            }
            makeToast(R.string.str_wc_request_responsed)

        } catch (e: Exception) {
            val response = Sign.Params.Response(
                sessionTopic = sessionRequest.topic,
                jsonRpcResponse = Sign.Model.JsonRpcResponse.JsonRpcError(
                    id, 500, "Signing error."
                )
            )
            SignClient.respond(response) { error ->
                Log.e("WCV2", error.throwable.stackTraceToString())
            }
            makeToast(R.string.str_unknown_error)
        }
    }

    fun approveSignAminoV2Request(
        id: Long, transaction: String, sessionRequest: Sign.Model.SessionRequest
    ) {
        try {
            val signDocJson = Gson().fromJson(transaction, JsonObject::class.java)

            val mapper = ObjectMapper()
            mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
            val signDoc = mapper.writeValueAsString(
                mapper.readValue(signDocJson.toString(), TreeMap::class.java)
            )
            val signatureTx =
                Signer.signature(selectChain, signDoc.toByteArray(StandardCharsets.UTF_8))
            val pubKey = PubKey(
                pubKeyType(),
                Strings.fromByteArray(Base64.encode(selectChain?.publicKey, Base64.DEFAULT))
                    .replace("\n", "")
            )
            val signature = Signature(
                pubKey, signatureTx, null, null
            )
            val signModel = WcSignModel(signDocJson, signature)

            val response = Sign.Params.Response(
                sessionTopic = sessionRequest.topic,
                jsonRpcResponse = Sign.Model.JsonRpcResponse.JsonRpcResult(
                    id, Gson().toJson(signModel)
                )
            )
            SignClient.respond(response) { error ->
                Log.e("WCV2", error.throwable.stackTraceToString())
            }
            makeToast(R.string.str_wc_request_responsed)

        } catch (e: Exception) {
            val response = Sign.Params.Response(
                sessionTopic = sessionRequest.topic,
                jsonRpcResponse = Sign.Model.JsonRpcResponse.JsonRpcError(
                    id, 500, "Signing error."
                )
            )
            SignClient.respond(response) { error ->
                Log.e("WCV2", error.throwable.stackTraceToString())
            }
            makeToast(R.string.str_unknown_error)
        }
    }

    private fun cancelV2SignRequest(
        id: Long, sessionRequest: Sign.Model.SessionRequest
    ) {
        val response = Sign.Params.Response(
            sessionTopic = sessionRequest.topic,
            jsonRpcResponse = Sign.Model.JsonRpcResponse.JsonRpcError(
                id, 300, getString(R.string.str_cancel)
            )
        )
        SignClient.respond(response) { error ->
            Log.e("WCV2", error.throwable.stackTraceToString())
        }
        makeToast(R.string.str_cancel)
    }

    override fun onDestroy() {
        val pairingList = CoreClient.Pairing.getPairings()
        pairingList.forEach { CoreClient.Pairing.disconnect(it.topic) }
        super.onDestroy()
        binding.apply {
            dappWebView.removeJavascriptInterface("station")
        }
    }

    private fun signBundle(id: Long, data: String, method: String? = ""): Bundle {
        val bundle = Bundle()
        bundle.putString("data", data)
        bundle.putLong("id", id)
        bundle.putString("method", method)
        return bundle
    }

    private fun showSignDialog(
        bundle: Bundle, signListener: PopUpCosmosSignFragment.WcSignRawDataListener
    ) {
        bundle.getString("data")?.let { data ->
            PopUpCosmosSignFragment(
                allChains,
                selectChain,
                bundle.getLong("id"),
                data,
                bundle.getString("method"),
                signListener
            ).show(
                supportFragmentManager, PopUpCosmosSignFragment::class.java.name
            )
        }
    }

    private fun showEvmSignDialog(
        bundle: Bundle, signListener: PopUpEvmSignFragment.WcSignRawDataListener
    ) {
        bundle.getString("data")?.let { data ->
            PopUpEvmSignFragment(
                selectEvmChain, bundle.getLong("id"), data, bundle.getString("method"), signListener
            ).show(
                supportFragmentManager, PopUpEvmSignFragment::class.java.name
            )
        }
    }

    private fun showSuiSignDialog(
        bundle: Bundle, signListener: PopUpSuiSignFragment.WcSignRawDataListener
    ) {
        bundle.getString("data")?.let { data ->
            PopUpSuiSignFragment(
                selectMajorChain,
                bundle.getLong("id"),
                data,
                bundle.getString("method"),
                signListener
            ).show(
                supportFragmentManager, PopUpSuiSignFragment::class.java.name
            )
        }
    }

    private fun showBitSignDialog(
        bundle: Bundle,
        signListener: PopUpBitSignFragment.WcSignRawDataListener
    ) {
        bundle.getString("data")?.let { data ->
            PopUpBitSignFragment(
                selectBitcoin,
                bundle.getLong("id"),
                data,
                bundle.getString("method"),
                signListener
            ).show(
                supportFragmentManager, PopUpSuiSignFragment::class.java.name
            )
        }
    }

    private val dappWebViewClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            binding.dappUrl.text = Uri.parse(url).host
            initInjectScript(view)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)

            if (view?.canGoForward() == true) {
                binding.btnNext.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        this@DappActivity, R.color.color_base01
                    ), PorterDuff.Mode.SRC_IN
                )
                binding.btnNext.isClickable = true

            } else {
                binding.btnNext.colorFilter = PorterDuffColorFilter(
                    ContextCompat.getColor(
                        this@DappActivity, R.color.color_base03
                    ), PorterDuff.Mode.SRC_IN
                )
                binding.btnNext.isClickable = false
            }
        }

        override fun shouldOverrideUrlLoading(
            view: WebView, request: WebResourceRequest
        ): Boolean {
            if (isFinishing) {
                return true
            }
            var modifiedUrl = URLDecoder.decode(request.url.toString(), "UTF-8")

            when {
                modifiedUrl.startsWith("cosmostation://wc") -> {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(modifiedUrl)))
                    return true
                }

                modifiedUrl.startsWith("wc:") -> {
                    connectWalletConnect(modifiedUrl)
                    return true
                }

                modifiedUrl.startsWith("keplrwallet://wcV1") -> {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW, Uri.parse(
                                modifiedUrl.replace(
                                    "keplrwallet://wcV1", "cosmostation://wc"
                                )
                            )
                        )
                    )
                    return true
                }

                modifiedUrl.startsWith("keplrwallet://wcV2") -> {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW, Uri.parse(
                                modifiedUrl.replace(
                                    "keplrwallet://wcV2", "cosmostation://wc"
                                )
                            )
                        )
                    )
                    return true
                }

                modifiedUrl.startsWith("keplrwalletwcv2://wcV2") -> {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW, Uri.parse(
                                modifiedUrl.replace(
                                    "keplrwalletwcv2://wcV2", "cosmostation://wc"
                                )
                            )
                        )
                    )
                    return true
                }

                modifiedUrl.startsWith("intent:") -> {
                    if (modifiedUrl.contains("intent://wcV2")) {
                        modifiedUrl = modifiedUrl.replace(
                            "#Intent;package=com.chainapsis.keplr;scheme=keplrwallet;end;",
                            "#Intent;package=wannabit.io.cosmostaion;scheme=cosmostation;end;"
                        )
                        modifiedUrl = modifiedUrl.replace(
                            "intent://wcV2", "intent://wc"
                        )
                        modifiedUrl = modifiedUrl.replace(
                            "scheme=keplrwallet", "scheme=cosmostation"
                        )
                    }
                    if (BuildConfig.DEBUG) {
                        modifiedUrl = modifiedUrl.replace(
                            "wannabit.io.cosmostaion", "wannabit.io.cosmostaion.debug"
                        )
                    }
                    try {
                        val intent = Intent.parseUri(modifiedUrl, Intent.URI_INTENT_SCHEME)
                        val existPackage = intent.getPackage()?.let {
                            packageManager.getLaunchIntentForPackage(it)
                        }
                        existPackage?.let {
                            startActivity(intent)
                        } ?: run {
                            val marketIntent = Intent(
                                Intent.ACTION_VIEW
                            )
                            marketIntent.data =
                                Uri.parse("market://details?id=" + intent.getPackage())
                            startActivity(marketIntent)
                        }
                        return true
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
            }
            return false
        }
    }

    private val dappWebChromeClient = object : WebChromeClient() {
        override fun onJsAlert(
            view: WebView, url: String, message: String, result: JsResult
        ): Boolean {
            AlertDialog.Builder(
                view.context, R.style.AppTheme_AlertDialogTheme
            ).setMessage(message)
                .setPositiveButton("OK") { dialog: DialogInterface?, which: Int -> result.confirm() }
                .setOnDismissListener { dialog: DialogInterface? -> result.confirm() }.create()
                .show()
            return true
        }

        override fun onJsConfirm(
            view: WebView, url: String, message: String, result: JsResult
        ): Boolean {
            AlertDialog.Builder(
                view.context, R.style.AppTheme_AlertDialogTheme
            ).setMessage(message)
                .setPositiveButton("OK") { _: DialogInterface?, _: Int -> result.confirm() }
                .setNegativeButton("Cancel") { _: DialogInterface?, _: Int -> result.cancel() }
                .setOnDismissListener { dialog: DialogInterface? -> result.cancel() }.create()
                .show()
            return true
        }
    }

    fun processRequest(message: String) {
        var isCosmostation = false
        try {
            val requestJson = JSONObject(message)
            if (!requestJson.has("isCosmostation") || !requestJson.getBoolean("isCosmostation")) {
                return
            }
            isCosmostation = true
            val messageId = requestJson.getString("messageId")
            val messageJson = requestJson.getJSONObject("message")

            when (messageJson.getString("method")) {
                "cos_requestAccount", "cos_account", "ten_requestAccount", "ten_account" -> {
                    val params = messageJson.getJSONObject("params")
                    val chainId = params.getString("chainName")
                    BaseData.baseAccount?.let { account ->
                        selectedChain(allChains, chainId)?.let { chain ->
                            selectChain = chain
                            val accountJson = JSONObject()
                            accountJson.put("isKeystone", false)
                            accountJson.put("isEthermint", selectChain?.supportEvm)
                            accountJson.put("isLedger", false)
                            accountJson.put("address", selectChain?.address)
                            accountJson.put("name", account.name)
                            accountJson.put("publicKey", selectChain?.publicKey?.bytesToHex())
                            appToWebResult(messageJson, accountJson, messageId)

                        } ?: run {
                            appToWebError(
                                messageJson, messageId, "Invalid method parameter(s)."
                            )
                        }
                    }
                }

                "cos_supportedChainIds" -> {
                    val supportChainIds =
                        allChains?.filter { it.supportCosmos() && it.chainIdCosmos.isNotEmpty() }
                            ?.map { it.chainIdCosmos }?.distinct()
                    if (supportChainIds?.isNotEmpty() == true) {
                        val dataJson = JSONObject()
                        dataJson.put("official", JSONArray(supportChainIds))
                        dataJson.put("unofficial", JSONArray(arrayListOf<String>()))
                        appToWebResult(messageJson, dataJson, messageId)

                    } else {
                        appToWebError("Error")
                    }
                }

                "cos_supportedChainNames" -> {
                    val supportChainNames = allChains?.filter {
                        it.supportCosmos() && it.name.lowercase().isNotEmpty()
                    }?.map { it.name.lowercase() }?.distinct()
                    if (supportChainNames?.isNotEmpty() == true) {
                        val dataJson = JSONObject()
                        dataJson.put("official", JSONArray(supportChainNames))
                        dataJson.put("unofficial", JSONArray(arrayListOf<String>()))
                        appToWebResult(messageJson, dataJson, messageId)

                    } else {
                        appToWebError("Error")
                    }
                }

                "cos_disconnect" -> {
                    appToWebResult(
                        messageJson, null, messageId
                    )
                }

                "cos_addChain" -> {
                    val params = messageJson.getJSONObject("params")
                    val supportChainIds =
                        allChains?.filter { !it.isTestnet && it.supportCosmos() && it.chainIdCosmos.isNotEmpty() }
                            ?.map { it.chainIdCosmos }?.distinct()
                    if (supportChainIds?.contains(params.getString("chainId")) == true) {
                        appToWebResult(
                            messageJson, true, messageId
                        )
                    } else {
                        appToWebError(
                            messageJson, messageId, getString(R.string.error_not_support_chain)
                        )
                    }
                }

                "cos_signMessage" -> {
                    val params = messageJson.getJSONObject("params")
                    val signBundle = signBundle(0, params.toString(), "sign_message")
                    showSignDialog(signBundle,
                        object : PopUpCosmosSignFragment.WcSignRawDataListener {
                            override fun sign(id: Long, data: String) {
                                approveSignMessageRequest(messageJson, messageId, data)
                            }

                            override fun cancel(id: Long) {
                                appToWebError(
                                    messageJson, messageId, "Reject"
                                )
                            }
                        })
                }

                "cos_signAmino" -> {
                    val params = messageJson.getJSONObject("params")
                    val signBundle = signBundle(0, params.toString(), "sign_amino")
                    showSignDialog(signBundle,
                        object : PopUpCosmosSignFragment.WcSignRawDataListener {
                            override fun sign(id: Long, data: String) {
                                approveSignAminoInjectRequest(messageJson, messageId, data)
                            }

                            override fun cancel(id: Long) {
                                appToWebError(
                                    messageJson, messageId, "Reject"
                                )
                            }
                        })
                }

                "cos_signDirect" -> {
                    val params = messageJson.getJSONObject("params")
                    val signBundle = signBundle(0, params.toString(), "sign_direct")
                    showSignDialog(signBundle,
                        object : PopUpCosmosSignFragment.WcSignRawDataListener {
                            override fun sign(id: Long, data: String) {
                                approveSignDirectInjectRequest(messageJson, messageId, data)
                            }

                            override fun cancel(id: Long) {
                                appToWebError(
                                    messageJson, messageId, "Reject"
                                )
                            }
                        })
                }

                "cos_sendTransaction" -> {
                    try {
                        selectChain?.let { chain ->
                            val params = messageJson.getJSONObject("params")
                            val txBytes = params.getString("txBytes")
                            val mode = params.getInt("mode")
                            val request =
                                BroadcastTxRequest.newBuilder().setModeValue(mode).setTxBytes(
                                    ByteString.copyFrom(Base64.decode(txBytes, Base64.DEFAULT))
                                ).build()
                            val txStub =
                                ServiceGrpc.newBlockingStub(chain.cosmosFetcher()?.getChannel())
                                    .withDeadlineAfter(8L, TimeUnit.SECONDS)
                            val response = txStub.broadcastTx(request)
                            appToWebResult(
                                messageJson, JSONObject(
                                    JsonFormat.printer().includingDefaultValueFields()
                                        .preservingProtoFieldNames().print(response)
                                ), messageId
                            )
                        }

                    } catch (e: Exception) {
                        appToWebError(
                            messageJson, messageId, "Not implemented"
                        )
                    }
                }

                // evm method
                "eth_requestAccounts", "wallet_requestPermissions" -> {
                    val address = allChains?.firstOrNull { chain -> chain.supportEvm }?.evmAddress
                    appToWebResult(
                        messageJson, JSONArray(listOf(address)), messageId
                    )
                }

                "wallet_switchEthereumChain" -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val evmChainIds =
                            allChains?.map { chain -> chain.chainIdEvm.uppercase() }?.distinct()
                        val chainId = (messageJson.getJSONArray("params")
                            .get(0) as JSONObject).getString("chainId")

                        if (evmChainIds?.contains(chainId.uppercase()) == true) {
                            currentEvmChainId = chainId
                            selectEvmChain =
                                allChains?.firstOrNull { it.chainIdEvm.uppercase() == currentEvmChainId?.uppercase() }
                            rpcUrl = selectEvmChain?.evmRpcFetcher?.getEvmRpc()
                                ?: selectEvmChain?.evmRpcURL
                            web3j = Web3j.build(HttpService(rpcUrl))
                            appToWebResult(messageJson, JSONObject.NULL, messageId)
                            emitToWeb(chainId)

                            val chainNetwork =
                                allChains?.firstOrNull { it.chainIdEvm.uppercase() == chainId.uppercase() }?.name
                            withContext(Dispatchers.Main) {
                                makeToast("Connected to $chainNetwork network")
                            }

                        } else {
                            appToWebError(
                                messageJson, messageId, getString(R.string.error_not_support_chain)
                            )

                            withContext(Dispatchers.Main) {
                                makeToast(getString(R.string.error_not_support_chain))
                            }
                        }
                    }
                }

                "eth_chainId" -> {
                    if (selectEvmChain == null) {
                        selectEvmChain =
                            allChains?.firstOrNull { chain -> chain.isSupportErc20() && chain.chainIdEvm == "0x1" }
                    }
                    currentEvmChainId =
                        allChains?.firstOrNull { it.name == selectEvmChain?.name }?.chainIdEvm
                    rpcUrl = selectEvmChain?.evmRpcFetcher?.getEvmRpc() ?: selectEvmChain?.evmRpcURL
                    web3j = Web3j.build(HttpService(rpcUrl))
                    appToWebResult(messageJson, currentEvmChainId, messageId)
                }

                "eth_accounts" -> {
                    if (selectEvmChain == null) {
                        selectEvmChain =
                            allChains?.firstOrNull { chain -> chain.isSupportErc20() && chain.chainIdEvm == "0x1" }
                    }

                    if (selectEvmChain?.evmAddress?.isNotEmpty() == true) {
                        appToWebResult(
                            messageJson, JSONArray(listOf(selectEvmChain?.evmAddress)), messageId
                        )

                    } else {
                        appToWebResult(
                            messageJson, JSONArray(arrayListOf<String>()), messageId
                        )
                    }
                }

                "eth_estimateGas" -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val params = messageJson.getJSONArray("params")
                        val param = params.getJSONObject(0)

                        rpcUrl?.let {
                            val ethGasRequest = try {
                                JsonRpcRequest(
                                    method = "eth_estimateGas", params = listOf(
                                        EstimateGasParamsWithValue(
                                            param.getString("from") ?: null,
                                            if (param.has("to")) param.getString("to") else null,
                                            if (param.has("data")) param.getString("data") else null,
                                            if (param.has("value")) param.getString("value") else null
                                        )
                                    )
                                )

                            } catch (e: Exception) {
                                JsonRpcRequest(
                                    method = "eth_estimateGas", params = listOf(
                                        EstimateGasParams(
                                            param.getString("from") ?: null,
                                            if (param.has("to")) param.getString("to") else null,
                                            if (param.has("data")) param.getString("data") else null
                                        )
                                    )
                                )
                            }

                            val ethGasResponse = jsonRpcResponse(it, ethGasRequest)
                            val gasJsonObject = Gson().fromJson(
                                ethGasResponse.body?.string(), JsonObject::class.java
                            )

                            try {
                                appToWebResult(
                                    messageJson,
                                    gasJsonObject.asJsonObject["result"].asString,
                                    messageId
                                )
                            } catch (e: Exception) {
                                appToWebError(messageJson, messageId, "JSON-RPC error")
                            }
                        }
                    }
                }

                "eth_blockNumber" -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        rpcUrl?.let {
                            val ethBlockNumberRequest = JsonRpcRequest(
                                method = "eth_blockNumber", params = listOf()
                            )

                            val ethBlockNumberResponse = jsonRpcResponse(it, ethBlockNumberRequest)
                            val ethBlockNumberJsonObject = Gson().fromJson(
                                ethBlockNumberResponse.body?.string(), JsonObject::class.java
                            )

                            try {
                                appToWebResult(
                                    messageJson,
                                    ethBlockNumberJsonObject.asJsonObject["result"].asString,
                                    messageId
                                )
                            } catch (e: Exception) {
                                appToWebError(messageJson, messageId, "JSON-RPC error")
                            }
                        }
                    }
                }

                "eth_getBlockByNumber" -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val params = messageJson.getJSONArray("params")

                        val ethGetBlockNumberRequest = JsonRpcRequest(
                            method = "eth_getBlockByNumber", params = listOf(params[0], params[1])
                        )
                        rpcUrl?.let {
                            val ethGetBlockNumberResponse =
                                jsonRpcResponse(it, ethGetBlockNumberRequest)
                            val getBlockNumberJsonObject = Gson().fromJson(
                                ethGetBlockNumberResponse.body?.string(), JsonObject::class.java
                            )
                            try {
                                appToWebObjectResult(
                                    messageJson, getBlockNumberJsonObject, messageId
                                )
                            } catch (e: Exception) {
                                appToWebError(messageJson, messageId, "JSON-RPC error")
                            }
                        }
                    }
                }

                "eth_gasPrice" -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        rpcUrl?.let {
                            val ethGasPriceRequest = JsonRpcRequest(
                                method = "eth_gasPrice", params = listOf()
                            )

                            val ethGasPriceResponse = jsonRpcResponse(it, ethGasPriceRequest)
                            val ethGasPriceJsonObject = Gson().fromJson(
                                ethGasPriceResponse.body?.string(), JsonObject::class.java
                            )

                            try {
                                appToWebResult(
                                    messageJson,
                                    ethGasPriceJsonObject.asJsonObject["result"].asString,
                                    messageId
                                )
                            } catch (e: Exception) {
                                appToWebError(messageJson, messageId, "JSON-RPC error")
                            }
                        }
                    }
                }

                "eth_maxPriorityFeePerGas" -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        rpcUrl?.let {
                            val ethMaxFeePerGasRequest = JsonRpcRequest(
                                method = "eth_maxPriorityFeePerGas", params = listOf()
                            )

                            val ethMaxFeePerGasResponse =
                                jsonRpcResponse(it, ethMaxFeePerGasRequest)
                            val ethMaxFeePerGasJsonObject = Gson().fromJson(
                                ethMaxFeePerGasResponse.body?.string(), JsonObject::class.java
                            )

                            try {
                                appToWebResult(
                                    messageJson,
                                    ethMaxFeePerGasJsonObject.asJsonObject["result"].asString,
                                    messageId
                                )
                            } catch (e: Exception) {
                                appToWebError(messageJson, messageId, "JSON-RPC error")
                            }
                        }
                    }
                }

                "eth_call" -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val params = messageJson.getJSONArray("params")
                        val param = params.getJSONObject(0)

                        rpcUrl?.let {
                            val ethCallRequest = JsonRpcRequest(
                                method = "eth_call", params = listOf(
                                    EthCall(
                                        null, param.getString("to"), param.getString("data")
                                    ), DefaultBlockParameterName.LATEST
                                )
                            )

                            val ethCallResponse = jsonRpcResponse(it, ethCallRequest)
                            val callJsonObject = Gson().fromJson(
                                ethCallResponse.body?.string(), JsonObject::class.java
                            )

                            try {
                                appToWebResult(
                                    messageJson,
                                    callJsonObject.asJsonObject["result"].asString,
                                    messageId
                                )
                            } catch (e: Exception) {
                                appToWebError(messageJson, messageId, "JSON-RPC error")
                            }
                        }
                    }
                }

                "personal_sign" -> {
                    val params = messageJson.getJSONArray("params")
                    val signBundle = signBundle(0, params.toString(), "personal_sign")
                    showEvmSignDialog(
                        signBundle,
                        object : PopUpEvmSignFragment.WcSignRawDataListener {
                            override fun sign(id: Long, data: String) {
                                appToWebResult(
                                    messageJson, data, messageId
                                )
                            }

                            override fun cancel(id: Long) {
                                appToWebError(messageJson, messageId, "Permit rejected.")
                            }
                        })
                }

                "eth_signTypedData_v4", "eth_signTypedData_v3" -> {
                    val params = messageJson.getJSONArray("params")
                    if (params.get(0).toString()
                            .lowercase() != selectEvmChain?.evmAddress?.lowercase()
                    ) {
                        appToWebError(messageJson, messageId, "Wrong address")
                        return
                    }
                    val signBundle = signBundle(0, params.toString(), "eth_signTypedData")
                    showEvmSignDialog(signBundle,
                        object : PopUpEvmSignFragment.WcSignRawDataListener {
                            override fun sign(id: Long, data: String) {
                                appToWebResult(
                                    messageJson, data, messageId
                                )
                            }

                            override fun cancel(id: Long) {
                                appToWebError(messageJson, messageId, "Transaction rejected.")
                            }
                        })
                }

                // sign method
                "eth_sendTransaction" -> {
                    val params = messageJson.getJSONArray("params")
                    val signBundle = signBundle(0, params[0].toString(), "eth_sendTransaction")
                    showEvmSignDialog(signBundle,
                        object : PopUpEvmSignFragment.WcSignRawDataListener {
                            override fun sign(id: Long, data: String) {
                                lifecycleScope.launch(Dispatchers.IO) {
                                    val ethSendTransaction =
                                        web3j?.ethSendRawTransaction(data)?.send()
                                    appToWebResult(
                                        messageJson, ethSendTransaction?.transactionHash, messageId
                                    )
                                }
                            }

                            override fun cancel(id: Long) {
                                appToWebError(messageJson, messageId, "Transaction rejected.")
                            }
                        })
                }

                // result method
                "eth_getTransactionReceipt" -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val params = messageJson.getJSONArray("params")

                        val ethReceiptRequest = JsonRpcRequest(
                            method = "eth_getTransactionReceipt",
                            params = listOf(params.getString(0))
                        )
                        rpcUrl?.let {
                            val ethReceiptResponse = jsonRpcResponse(it, ethReceiptRequest)
                            val receiptJsonObject = Gson().fromJson(
                                ethReceiptResponse.body?.string(), JsonObject::class.java
                            )
                            try {
                                appToWebObjectResult(
                                    messageJson, receiptJsonObject, messageId
                                )
                            } catch (e: Exception) {
                                appToWebError(messageJson, messageId, "JSON-RPC error")
                            }
                        }
                    }
                }

                "eth_getTransactionByHash" -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val params = messageJson.getJSONArray("params")

                        val ethByHashRequest = JsonRpcRequest(
                            method = "eth_getTransactionByHash",
                            params = listOf(params.getString(0))
                        )
                        rpcUrl?.let {
                            val ethByHashResponse = jsonRpcResponse(it, ethByHashRequest)
                            val byHashJsonObject = Gson().fromJson(
                                ethByHashResponse.body?.string(), JsonObject::class.java
                            )
                            try {
                                appToWebObjectResult(
                                    messageJson, byHashJsonObject, messageId
                                )
                            } catch (e: Exception) {
                                appToWebError(messageJson, messageId, "JSON-RPC error")
                            }
                        }
                    }
                }

                // sui
                "sui_getAccount" -> {
                    if (selectMajorChain == null) {
                        selectMajorChain = allChains?.find { it.name == "Sui" }
                    }
                    val accountJson = JSONObject()
                    accountJson.put("address", selectMajorChain?.mainAddress)
                    accountJson.put("publicKey", "0x" + selectMajorChain?.publicKey?.bytesToHex())
                    appToWebResult(
                        messageJson, accountJson, messageId
                    )
                }

                "sui_getChain" -> {
                    selectMajorChain = allChains?.find { it.name == "Sui" }
                    appToWebResult(
                        messageJson, "mainnet", messageId
                    )
                }

                "sui_signTransaction", "sui_signTransactionBlock" -> {
                    val params = messageJson.getJSONObject("params")
                    val signBundle = signBundle(0, params.toString(), "sui_signTransaction")
                    showSuiSignDialog(signBundle,
                        object : PopUpSuiSignFragment.WcSignRawDataListener {
                            override fun sign(id: Long, data: String, signature: String) {
                                val signed = JSONObject()
                                signed.put("transactionBlockBytes", data)
                                signed.put("signature", signature)
                                appToWebResult(
                                    messageJson, signed, messageId
                                )
                            }

                            override fun cancel(id: Long) {
                                appToWebError(messageJson, messageId, "User rejected the request.")
                            }
                        })
                }

                "sui_signAndExecuteTransactionBlock", "sui_signAndExecuteTransaction" -> {
                    val params = messageJson.getJSONObject("params")
                    val signBundle =
                        signBundle(0, params.toString(), "sui_signAndExecuteTransactionBlock")
                    showSuiSignDialog(signBundle,
                        object : PopUpSuiSignFragment.WcSignRawDataListener {
                            override fun sign(id: Long, data: String, signature: String) {
                                approveSuiSignExecuteRequest(
                                    messageJson, messageId, data, signature
                                )
                            }

                            override fun cancel(id: Long) {
                                appToWebError(messageJson, messageId, "User rejected the request.")
                            }
                        })
                }

                "sui_signMessage", "sui_signPersonalMessage" -> {
                    val params = messageJson.getJSONObject("params")
                    if (params.getString("accountAddress")
                            .lowercase() != selectMajorChain?.mainAddress?.lowercase()
                    ) {
                        appToWebError(messageJson, messageId, "Wrong address")
                        return
                    }
                    val signBundle = signBundle(0, params.toString(), "sui_signMessage")
                    showSuiSignDialog(signBundle,
                        object : PopUpSuiSignFragment.WcSignRawDataListener {
                            override fun sign(id: Long, data: String, signature: String) {
                                val signed = JSONObject()
                                signed.put("messageBytes", data)
                                signed.put("signature", signature)
                                appToWebResult(
                                    messageJson, signed, messageId
                                )
                            }

                            override fun cancel(id: Long) {
                                appToWebError(messageJson, messageId, "User rejected the request.")
                            }
                        })
                }

                //babylon
                "bit_requestAccount" -> {
                    if (selectBitcoin == null) {
                        selectBitcoin =
                            allChains?.find { it is ChainBitCoin84 && it.accountKeyType.pubkeyType == PubKeyType.BTC_NATIVE_SEGWIT }
                    }

                    if (selectBitcoin?.mainAddress?.isNotEmpty() == true) {
                        appToWebResult(
                            messageJson, listOf(selectBitcoin?.mainAddress), messageId
                        )
                    } else {
                        appToWebError(messageJson, messageId, "None Address")
                    }
                }

                "bit_getPublicKeyHex" -> {
                    if (selectBitcoin == null) {
                        selectBitcoin =
                            allChains?.find { it is ChainBitCoin84 && it.accountKeyType.pubkeyType == PubKeyType.BTC_NATIVE_SEGWIT }
                    }
                    appToWebResult(
                        messageJson,
                        Base64.encodeToString(selectBitcoin?.publicKey, Base64.NO_WRAP),
                        messageId
                    )
                }

                "bit_getNetwork" -> {
                    if (selectBitcoin == null) {
                        selectBitcoin =
                            allChains?.find { it is ChainBitCoin84 && it.accountKeyType.pubkeyType == PubKeyType.BTC_NATIVE_SEGWIT }
                    }
                    bitNetwork = if (selectBitcoin is ChainBitcoin84Testnet) {
                        "signet"
                    } else {
                        "mainnet"
                    }
                    appToWebResult(
                        messageJson, bitNetwork, messageId
                    )
                }

                "bit_switchNetwork" -> {
                    val params = messageJson.getJSONArray("params")
                    if (params.length() > 0) {
                        bitNetwork = params.get(0).toString()
                        selectBitcoin = if (bitNetwork == "mainnet") {
                            allChains?.find {
                                it is ChainBitCoin84 && it.accountKeyType.pubkeyType == PubKeyType.BTC_NATIVE_SEGWIT && it.isDefault
                            }
                        } else {
                            allChains?.find {
                                it is ChainBitCoin84 && it.accountKeyType.pubkeyType == PubKeyType.BTC_NATIVE_SEGWIT && it.name.uppercase()
                                    .contains(bitNetwork.uppercase())
                            }
                        }
                        appToWebResult(
                            messageJson, bitNetwork, messageId
                        )
                    } else {
                        appToWebError(messageJson, messageId, "Error : No Network")
                    }
                }

                "bit_getAddress" -> {
                    if (selectBitcoin == null) {
                        selectBitcoin =
                            allChains?.find { it is ChainBitCoin84 && it.accountKeyType.pubkeyType == PubKeyType.BTC_NATIVE_SEGWIT }
                    }

                    if (selectBitcoin?.mainAddress?.isNotEmpty() == true) {
                        appToWebResult(
                            messageJson, selectBitcoin?.mainAddress, messageId
                        )
                    } else {
                        appToWebError(messageJson, messageId, "None Address")
                    }
                }

                "bit_getBalance" -> {
                    currentMessageJson = messageJson
                    currentMessageId = messageId

                    selectBitcoin?.let { chain ->
                        walletViewModel.balance(chain)
                    } ?: run {
                        appToWebError(
                            messageJson, messageId, getString(R.string.error_not_support)
                        )
                    }
                }

                "bit_pushTx" -> {
                    val params = messageJson.getJSONArray("params")
                    if (params.length() > 0) {
                        val txHex = params.get(0).toString()
                        val pushTxRequest = JsonRpcRequest(
                            method = "sendrawtransaction", params = listOf(txHex)
                        )
                        val pushTxResponse =
                            jsonRpcResponse(selectBitcoin?.mainUrl ?: "", pushTxRequest)
                        val pushTxJsonObject = Gson().fromJson(
                            pushTxResponse.body?.string(), JsonObject::class.java
                        )
                        try {
                            val result = pushTxJsonObject["result"].asString
                            appToWebResult(
                                messageJson, result, messageId
                            )

                        } catch (e: Exception) {
                            val errorMessage =
                                pushTxJsonObject["error"].asJsonObject["message"].asString
                            appToWebError(
                                messageJson, messageId, errorMessage
                            )
                        }

                    } else {
                        appToWebError(
                            messageJson, messageId, "No Param Data"
                        )
                    }
                }

                "bit_sendBitcoin" -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val params = messageJson.getJSONObject("params")
                        currentMessageJson = messageJson
                        currentMessageId = messageId
                        bitToAddress = params.getString("to")
                        bitSatAmount = params.getLong("satAmount").toString()

                        if (selectBitcoin?.mainAddress?.lowercase() == bitToAddress.lowercase()) {
                            appToWebError(messageJson, messageId, "Wrong address")
                            return@launch
                        }

                        val signBundle = signBundle(0, params.toString(), "bit_sendBitcoin")
                        showBitSignDialog(
                            signBundle,
                            object : PopUpBitSignFragment.WcSignRawDataListener {
                                override fun sign(id: Long, txHex: String) {
                                    lifecycleScope.launch(Dispatchers.IO) {
                                        val bitSendTxRequest = JsonRpcRequest(
                                            method = "sendrawtransaction", params = listOf(txHex)
                                        )
                                        val bitSendTxResponse =
                                            jsonRpcResponse(
                                                selectBitcoin?.mainUrl ?: "",
                                                bitSendTxRequest
                                            )
                                        val bitSendTxJsonObject = Gson().fromJson(
                                            bitSendTxResponse.body?.string(), JsonObject::class.java
                                        )
                                        try {
                                            val result = bitSendTxJsonObject["result"].asString
                                            appToWebResult(
                                                currentMessageJson, result, currentMessageId
                                            )

                                        } catch (e: Exception) {
                                            val errorMessage =
                                                bitSendTxJsonObject["error"].asJsonObject["message"].asString
                                            appToWebError(
                                                currentMessageJson, currentMessageId, errorMessage
                                            )
                                        }
                                    }
                                }

                                override fun cancel(id: Long) {
                                    appToWebError(
                                        messageJson,
                                        messageId,
                                        "User rejected the request."
                                    )
                                }
                            })
                    }
                }

                "bit_signMessage" -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val params = messageJson.getJSONObject("params")
                        val signBundle = signBundle(0, params.toString(), "bit_signMessage")
                        showBitSignDialog(
                            signBundle,
                            object : PopUpBitSignFragment.WcSignRawDataListener {
                                override fun sign(id: Long, txHex: String) {
                                    appToWebResult(
                                        messageJson, txHex, messageId
                                    )
                                }

                                override fun cancel(id: Long) {
                                    appToWebError(
                                        messageJson,
                                        messageId,
                                        "User rejected the request."
                                    )
                                }
                            })
                    }
                }

                "bit_signPsbt" -> {
                    lifecycleScope.launch(Dispatchers.IO) {
                        val params = messageJson.getString("params")
                        val signBundle = signBundle(0, params, "bit_signPsbt")
                        showBitSignDialog(
                            signBundle,
                            object : PopUpBitSignFragment.WcSignRawDataListener {
                                override fun sign(id: Long, txHex: String) {
                                    appToWebResult(
                                        messageJson, txHex, messageId
                                    )
                                }

                                override fun cancel(id: Long) {
                                    appToWebError(
                                        messageJson,
                                        messageId,
                                        "User rejected the request."
                                    )
                                }
                            })
                    }
                }

                else -> {
                    appToWebError(messageJson, messageId, "Not implemented")
                }
            }

        } catch (e: Exception) {
            if (isCosmostation) {
                appToWebError(e.message)
            }
        }
    }

    private fun approveSignMessageRequest(
        messageJson: JSONObject, messageId: String, signatureData: String
    ) {
        val request = Request(msgs = emptyList())
        request.updateMsgData(signatureData, selectChain?.address)

        val mapper = ObjectMapper()
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
        val signDoc = mapper.writeValueAsString(
            mapper.readValue(request.toJson(), TreeMap::class.java)
        )

        val signatureTx = Signer.signature(
            selectChain, signDoc.toByteArray(StandardCharsets.UTF_8)
        )
        val pubKey = PubKey(
            pubKeyType(), Strings.fromByteArray(
                Base64.encode(
                    selectChain?.publicKey, Base64.DEFAULT
                )
            ).replace("\n", "")
        )

        val signed = JSONObject()
        signed.put("signature", signatureTx)
        signed.put(
            "pub_key", JSONObject(Gson().toJson(pubKey))
        )
        appToWebResult(messageJson, signed, messageId)
    }

    private fun approveSignAminoInjectRequest(
        messageJson: JSONObject, messageId: String, transactionData: String
    ) {
        val signDocJson = Gson().fromJson(transactionData, JsonObject::class.java)

        val mapper = ObjectMapper()
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
        val signDoc = mapper.writeValueAsString(
            mapper.readValue(signDocJson.toString(), TreeMap::class.java)
        )

        val signatureTx = Signer.signature(
            selectChain, signDoc.toByteArray(StandardCharsets.UTF_8)
        )
        val pubKey = PubKey(
            pubKeyType(), Strings.fromByteArray(
                Base64.encode(
                    selectChain?.publicKey, Base64.DEFAULT
                )
            ).replace("\n", "")
        )

        val signed = JSONObject()
        signed.put("signature", signatureTx)
        signed.put(
            "pub_key", JSONObject(Gson().toJson(pubKey))
        )
        signed.put("signed_doc", JSONObject(signDoc))
        appToWebResult(messageJson, signed, messageId)
    }

    private fun approveSignDirectInjectRequest(
        messageJson: JSONObject, messageId: String, transactionData: String
    ) {
        val transactionJson = Gson().fromJson(transactionData, JsonObject::class.java)
        val chainId = transactionJson["chain_id"].asString
        val accountNumber = transactionJson["account_number"].asLong
        val signDoc = TxProto.SignDoc.newBuilder().setBodyBytes(
            ByteString.copyFrom(
                Utils.hexToBytes(transactionJson["body_bytes"].asString)
            )
        ).setAuthInfoBytes(
            ByteString.copyFrom(
                Utils.hexToBytes(
                    transactionJson["auth_info_bytes"].asString
                )
            )
        ).setChainId(chainId).setAccountNumber(accountNumber).build()

        val signatureTx = Signer.signature(selectChain, signDoc.toByteArray())
        val pubKey = PubKey(
            pubKeyType(), Strings.fromByteArray(
                Base64.encode(
                    selectChain?.publicKey, Base64.DEFAULT
                )
            ).replace("\n", "")
        )
        val signed = JSONObject()
        signed.put("signature", signatureTx)
        signed.put("pub_key", JSONObject(Gson().toJson(pubKey)))
        signed.put("signed_doc", JSONObject(transactionData))
        appToWebResult(messageJson, signed, messageId)
    }

    private fun approveSuiSignExecuteRequest(
        messageJson: JSONObject, messageId: String, txByte: String, signature: String
    ) {
        lifecycleScope.launch(Dispatchers.IO) {
            (selectMajorChain as ChainSui).suiFetcher()?.let { fetcher ->
                val params = messageJson.getJSONObject("params")
                val txJsonObject = JsonParser.parseString(params.toString()).asJsonObject
                val options = if (txJsonObject["options"] != null) {
                    formatJsonOptions(
                        Gson().fromJson(
                            txJsonObject["options"], JsonObject::class.java
                        )
                    )
                } else {
                    mapOf("showInput" to true, "showEffects" to true, "showEvents" to true)
                }

                val param = listOf(
                    txByte, mutableListOf(signature), options, "WaitForLocalExecution"
                )
                val suiExecuteRequest = JsonRpcRequest(
                    method = "sui_executeTransactionBlock", params = param
                )
                val suiExecuteResponse = jsonRpcResponse(fetcher.suiRpc(), suiExecuteRequest)
                val suiExecuteJsonObject = JSONObject(suiExecuteResponse.body?.string())
                appToWebResult(messageJson, suiExecuteJsonObject.getJSONObject("result"), messageId)
            }
        }
    }

    // chain changed
    private fun emitToWeb(chainId: String) {
        val responseJson = JSONObject().apply {
            put("result", chainId)
        }
        val postMessageJson = JSONObject().apply {
            put("message", responseJson)
            put("type", "chainChanged")
            put("isCosmostation", true)
        }
        runOnUiThread {
            binding.dappWebView.evaluateJavascript(
                String.format("window.postMessage(%s);", postMessageJson.toString()), null
            )
        }
    }

    private fun emitCloseToWeb() {
        val responseJson = JSONObject().apply {
            put("result", JSONArray())
        }
        val postMessageJson = JSONObject().apply {
            put("message", responseJson)
            put("type", "accountsChanged")
            put("isCosmostation", true)
        }
        runOnUiThread {
            binding.dappWebView.evaluateJavascript(
                String.format("window.postMessage(%s);", postMessageJson.toString()), null
            )
        }
    }

    private fun appToWebResult(messageJson: JSONObject, resultJson: Any?, messageId: String) {
        val responseJson = JSONObject().apply {
            put("result", resultJson)
        }
        val postMessageJson = JSONObject().apply {
            put("message", messageJson)
            put("response", responseJson)
            put("messageId", messageId)
            put("isCosmostation", true)
        }
        runOnUiThread {
            binding.dappWebView.evaluateJavascript(
                String.format("window.postMessage(%s);", postMessageJson.toString()), null
            )
        }
    }

    private fun appToWebObjectResult(
        messageJson: JSONObject, resultJson: JsonObject, messageId: String
    ) {
        val responseJson = JsonObject().apply {
            addProperty("jsonrpc", "2.0")
            add("result", resultJson.asJsonObject["result"])
        }
        val postMessageJson = JsonObject().apply {
            add("message", Gson().fromJson(messageJson.toString(), JsonObject::class.java))
            add("response", responseJson)
            addProperty("messageId", messageId)
            addProperty("isCosmostation", true)
        }
        runOnUiThread {
            binding.dappWebView.evaluateJavascript(
                String.format("window.postMessage(%s);", postMessageJson.toString()), null
            )
        }
    }

    private fun appToWebError(error: String?) {
        val responseJson = JSONObject()
        responseJson.put("error", error)
        val postMessageJson = JSONObject()
        postMessageJson.put("response", responseJson)
        postMessageJson.put("isCosmostation", true)
        postMessageJson.put("messageId", "0")
        runOnUiThread {
            binding.dappWebView.evaluateJavascript(
                String.format(
                    "window.postMessage(%s);", postMessageJson.toString()
                ), null
            )
        }
    }

    private fun appToWebError(messageJson: JSONObject, messageId: String, message: String) {
        val responseJson = JSONObject().apply {
            val errorJson = JSONObject().apply {
                put("code", 4001)
                put("message", message)
            }
            put("error", errorJson)
        }
        val postMessageJson = JSONObject().apply {
            put("message", messageJson)
            put("response", responseJson)
            put("isCosmostation", true)
            put("messageId", messageId)
        }
        runOnUiThread {
            binding.dappWebView.evaluateJavascript(
                String.format(
                    "window.postMessage(%s);", postMessageJson.toString()
                ), null
            )
        }
    }

    private fun pubKeyType(): String {
        return if (selectChain is ChainInjective) {
            INJECTIVE_KEY_TYPE_PUBLIC
        } else if (selectChain?.supportEvm == true) {
            ETHERMINT_KEY_TYPE_PUBLIC
        } else {
            COSMOS_KEY_TYPE_PUBLIC
        }
    }

    private fun selectedChain(
        classChains: MutableList<BaseChain>?, chainId: String?
    ): BaseChain? {
        return classChains?.firstOrNull { chain ->
            (chain.chainIdCosmos.equals(chainId, ignoreCase = true) || chain.name.equals(
                chainId, ignoreCase = true
            )) && chain.isDefault
        }
    }

    private fun setUpObserve() {
        walletViewModel.balanceResult.observe(this) {
            appToWebResult(
                currentMessageJson,
                (selectBitcoin as ChainBitCoin84).btcFetcher()?.btcBalances,
                currentMessageId
            )
        }
    }

    inner class DappJavascriptInterface {
        @JavascriptInterface
        fun request(message: String) {
            processRequest(message)
        }
    }

    companion object {
        const val WC_URL_SCHEME_HOST_WC = "wc"
        const val WC_URL_SCHEME_HOST_DAPP_EXTERNAL = "dapp"
        const val WC_URL_SCHEME_HOST_DAPP_INTERNAL = "internaldapp"
        const val WC_URL_SCHEME_COSMOSTATION = "cosmostation"
    }

    data class V2Account(
        val algo: String,
        val pubkey: String,
        val address: String,
    )

    class WcSignModel(txMsg: JsonObject, sig: Signature) {
        private var signed: TreeMap<*, *>?
        private var signature: Signature
        private var signDoc: TreeMap<*, *>?

        init {
            val mapper = ObjectMapper()
            mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
            signed = mapper.readValue(txMsg.toString(), TreeMap::class.java)
            signDoc = signed
            signature = sig
        }
    }

    class WcSignDirectModel(
        txMsg: JsonObject, sig: Signature
    ) {
        private var signDoc: JsonObject
        private var signature: Signature

        init {
            signDoc = txMsg
            signature = sig
        }
    }

    private suspend fun loadParam() {
        when (val response = safeApiCall { RetrofitInstance.mintscanJsonApi.param() }) {
            is NetworkResult.Success -> {
                BaseData.chainParam = response.data
            }

            is NetworkResult.Error -> {}
        }
    }

    private suspend fun loadAsset() {
        when (val response = safeApiCall { RetrofitInstance.mintscanApi.asset() }) {
            is NetworkResult.Success -> {
                BaseData.assets = response.data.assets
            }

            is NetworkResult.Error -> {}
        }
    }
}

enum class DAppType {
    INTERNAL_URL, DEEPLINK_WC2
}

class Fee(
    val amount: List<Any> = emptyList(), val gas: String = "0"
)

class MsgSignDataValue(
    var data: String, var signer: String?
)

class Msg(
    val type: String = "sign/MsgSignData", var value: MsgSignDataValue
)

class Request(
    val account_number: String = "0",
    val chain_id: String = "",
    val fee: Fee = Fee(),
    val memo: String = "",
    var msgs: List<Msg>,
    val sequence: String = "0"
) {
    init {
        msgs = listOf(
            Msg(
                value = MsgSignDataValue(
                    data = "defaultData", signer = "defaultSigner"
                )
            )
        )
    }

    fun updateMsgData(data: String, signer: String?) {
        msgs = listOf(
            Msg(
                value = MsgSignDataValue(
                    data = data, signer = signer
                )
            )
        )
    }

    fun toJson(): String {
        val gson = GsonBuilder().setPrettyPrinting().create()
        return gson.toJson(this)
    }
}