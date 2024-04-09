package wannabit.io.cosmostaion.ui.main.dapp

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Base64
import android.util.Log
import android.view.View
import android.webkit.JavascriptInterface
import android.webkit.JsResult
import android.webkit.WebChromeClient
import android.webkit.WebResourceRequest
import android.webkit.WebSettings
import android.webkit.WebStorage
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.appcompat.app.AlertDialog
import com.cosmos.tx.v1beta1.ServiceGrpc
import com.cosmos.tx.v1beta1.ServiceProto.BroadcastTxRequest
import com.cosmos.tx.v1beta1.TxProto
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.databind.SerializationFeature
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonObject
import com.google.protobuf.ByteString
import com.google.protobuf.util.JsonFormat
import com.trustwallet.walletconnect.WCClient
import com.trustwallet.walletconnect.models.WCAccount
import com.trustwallet.walletconnect.models.WCPeerMeta
import com.trustwallet.walletconnect.models.WCSignTransaction
import com.trustwallet.walletconnect.models.session.WCSession
import com.walletconnect.android.Core
import com.walletconnect.android.CoreClient
import com.walletconnect.sign.client.Sign
import com.walletconnect.sign.client.SignClient
import com.walletconnect.sign.client.SignInterface
import com.walletconnect.util.bytesToHex
import net.i2p.crypto.eddsa.Utils
import okhttp3.OkHttpClient
import org.apache.commons.lang3.StringUtils
import org.bouncycastle.util.Strings
import org.json.JSONArray
import org.json.JSONObject
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.chain.allEvmLines
import wannabit.io.cosmostaion.chain.cosmosClass.ChainInjective
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.BaseConstant.COSMOS_KEY_TYPE_PUBLIC
import wannabit.io.cosmostaion.common.BaseConstant.ETHERMINT_KEY_TYPE_PUBLIC
import wannabit.io.cosmostaion.common.BaseConstant.INJECTIVE_KEY_TYPE_PUBLIC
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.model.req.BroadcastReq
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.data.model.req.PubKey
import wannabit.io.cosmostaion.data.model.req.Signature
import wannabit.io.cosmostaion.data.model.req.StdTxValue
import wannabit.io.cosmostaion.database.model.BaseAccountType
import wannabit.io.cosmostaion.databinding.ActivityDappBinding
import wannabit.io.cosmostaion.ui.main.dapp.option.DappUrlDialog
import wannabit.io.cosmostaion.ui.main.dapp.option.WcSignFragment
import java.io.BufferedReader
import java.net.URLDecoder
import java.nio.charset.StandardCharsets
import java.util.TreeMap
import java.util.concurrent.TimeUnit

class DappActivity : BaseActivity() {

    private lateinit var binding: ActivityDappBinding

    private var selectedChain: CosmosLine? = null
    private var wcUrl: String? = ""

    private var wcVersion = 1
    private var walletConnectURI: String? = null
    private var currentV2PairingUri: String? = null

    private var wcV1Client: WCClient? = null
    private var wcV1Session: WCSession? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDappBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setUpDappView()
    }

    private fun setUpDappView() {
        binding.apply {
            supportActionBar?.setDisplayHomeAsUpEnabled(false)
            dappClose.setOnClickListener { finish() }
            dappRefresh.setOnClickListener { dappWebView.reload() }
            wcPeer.setOnClickListener {
                DappUrlDialog.newInstance(binding.dappWebView.url ?: "",
                    object : DappUrlDialog.UrlListener {
                        override fun input(url: String) {
                            if (StringUtils.isNotEmpty(binding.dappWebView.url) && binding.dappWebView.url != url) {
                                wcUrl = url
                                binding.dappWebView.loadUrl(url)
                            }
                        }

                    }).show(supportFragmentManager, "dialog")
            }

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

            intent.data?.let { data ->
                data.query?.let { query ->
                    wcUrl = query
                    dappWebView.visibility = View.VISIBLE
                    dappWebView.loadUrl(query)
                    dappWebView.addJavascriptInterface(DappJavascriptInterface(), "station")
                    WebStorage.getInstance().deleteAllData()
                }
            }

            setSupportActionBar(toolBar)
            supportActionBar?.setDisplayShowTitleEnabled(false)
        }
    }

    private fun isSessionConnected(): Boolean {
        if (wcV1Session != null && wcV1Client != null && wcV1Client?.session != null && wcV1Client?.isConnected == true) {
            return true
        }
        if (walletConnectURI != null && currentV2PairingUri == walletConnectURI) {
            return true
        }
        return false
    }

    private fun connectWalletConnect(url: String?) {
        if (isSessionConnected()) return
        url?.let {
            binding.loadingLayer.visibility = View.VISIBLE
            walletConnectURI = url
            if (url.contains("@2")) {
                connectWalletConnectV2(url)
            } else {
                connectWalletConnectV1(url)
            }
        }
    }

    private fun connectWalletConnectV1(url: String) {
        val meta = WCPeerMeta(
            getString(R.string.str_wc_peer_name),
            getString(R.string.str_wc_peer_url),
            getString(R.string.str_wc_peer_desc),
            listOf()
        )
        wcV1Session = WCSession.from(url)
        val client = OkHttpClient.Builder().pingInterval(30, TimeUnit.SECONDS).build()
        wcV1Client = WCClient(GsonBuilder(), client).apply {
            onSessionRequest = processSessionRequest
            onGetAccounts = processGetAccounts
            onDisconnect = processDisconnect
            onSignTransaction = processSignTrust
            wcV1Session?.let { session -> connect(session, meta) }
        }
    }

    private val processSessionRequest = { _: Long, wcPeerMeta: WCPeerMeta ->
        runOnUiThread {
            binding.loadingLayer.apply {
                postDelayed({ visibility = View.GONE }, 2500)
            }
            makeToast(R.string.str_wc_connected)

            BaseData.baseAccount?.let { account ->
                account.allCosmosLineChains.firstOrNull { it.name.lowercase() == wcPeerMeta.name.lowercase() && it.tag == "kava459" }
                    ?.let { chain ->
                        selectedChain = chain
                        selectedChain?.fetchFilteredChain()

                        chain.address?.let { address ->
                            wcV1Client?.approveSession(listOf(address), 1)
                        } ?: run {
                            wcV1Client?.approveSession(listOf(), 1)
                        }
                    }
            }
        }
    }

    private val processGetAccounts: (Long) -> Unit = { id: Long ->
        selectedChain?.address?.let { address ->
            wcV1Client?.approveRequest(id, listOf(WCAccount(459, address)))
        }
    }

    private val processDisconnect = { _: Int, _: String? ->
        runOnUiThread {
            makeToast(R.string.str_wc_not_connected)
            wcV1Client = null
        }
    }

    private val processSignTrust = { id: Long, (_, transaction): WCSignTransaction ->
        runOnUiThread {
            val signBundle = signBundle(
                id, wcUrl, transaction
            )
            showSignDialog(signBundle, object : WcSignFragment.WcSignRawDataListener {
                override fun sign(id: Long, data: String) {
                    approveTrustRequest(
                        id, transaction
                    )
                }

                override fun cancel(id: Long) {
                    wcV1Client?.rejectRequest(id, getString(R.string.str_cancel))
                }
            })
        }
    }

    private fun convertKavaTx(txString: String): JSONObject {
        val jsonTx = JSONObject(txString)

        return JSONObject().apply {
            put("chain_id", jsonTx.get("chainId"))
            put("account_number", jsonTx.get("accountNumber"))

            val msgs = JSONArray().apply {
                val messageArray = jsonTx.getJSONArray("messages")
                for (i in 0 until messageArray.length()) {
                    val rawMessage = messageArray.getJSONObject(i).getJSONObject("rawJsonMessage")
                    put(JSONObject().apply {
                        put("type", rawMessage.getString("type"))
                        put("value", JSONObject(rawMessage.getString("value")))
                    })
                }
            }
            put("msgs", msgs)

            val fee = jsonTx.getJSONObject("fee")
            val amounts = fee.getJSONArray("amounts")
            val kavaFee = JSONObject().apply {
                put("gas", fee.getString("gas"))
                put("amount", amounts)
            }
            put("fee", kavaFee)

            put("sequence", jsonTx.get("sequence"))
            put("memo", jsonTx.get("memo"))
        }
    }

    private fun approveTrustRequest(id: Long, wcSignTransaction: String) {
        try {
            val kavaTx = convertKavaTx(wcSignTransaction)
            val broadcastReq = getTrustSignDoc(kavaTx)
            val result = GsonBuilder().disableHtmlEscaping().create().toJson(broadcastReq)
            wcV1Client?.approveRequest(id, result)
            makeToast(R.string.str_wc_request_responsed)

        } catch (e: Exception) {
            wcV1Client?.rejectRequest(id, "Signing error.")
            makeToast(R.string.str_unknown_error)
        }
    }

    private fun getTrustSignDoc(tx: JSONObject): BroadcastReq {
        val mapper = ObjectMapper().apply {
            configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
        }

        val sortedTx = mapper.writeValueAsString(
            mapper.readValue(tx.toString(), TreeMap::class.java)
        )
        val signatureTx =
            Signer.signature(selectedChain, sortedTx.toByteArray(StandardCharsets.UTF_8))
        val pubKey = PubKey(
            pubKeyType(),
            Strings.fromByteArray(Base64.encode(selectedChain?.publicKey, Base64.DEFAULT))
                .replace("\n", "")
        )
        val signatures = mutableListOf(
            Signature(
                pubKey, signatureTx, null, null
            )
        )
        val fee = Gson().fromJson(tx.getJSONObject("fee").toString(), LFee::class.java)

        val value = StdTxValue(
            null, fee, signatures, tx.getString("memo")
        )
        return BroadcastReq(
            "block", value
        )
    }

    private fun connectWalletConnectV2(url: String) {
        wcVersion = 2
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
                    val methods = sessionProposal.requiredNamespaces.values.flatMap { it.methods }
                    val events = sessionProposal.requiredNamespaces.values.flatMap { it.events }

                    sessionProposal.requiredNamespaces.values.flatMap { it.chains }.map { chain ->
                        val chainId = chain.split(":")[1]
                        val chainName = chain.split(":")[0]

                        BaseData.baseAccount?.let { account ->
                            account.allEvmLineChains.find { it.chainIdCosmos.lowercase() == chainId.lowercase() }
                                ?.let { line ->
                                    selectedChain = line

                                }
                                ?: account.allCosmosLineChains.find { it.chainIdCosmos.lowercase() == chainId.lowercase() }
                                    ?.let { line ->
                                        selectedChain = line

                                    } ?: run {
                                    binding.loadingLayer.visibility = View.GONE
                                    makeToast(getString(R.string.error_not_support, chainId))
                                    return@let
                                }

                            selectedChain?.fetchFilteredChain()
                            sessionNamespaces[chainName] = Sign.Model.Namespace.Session(
                                accounts = listOf("$chain:${selectedChain?.address}"),
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
                        }
                    }
                }
            }

            override fun onSessionRequest(sessionRequest: Sign.Model.SessionRequest) {
                if (isFinishing) return

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
                        val v2Accounts = selectedChain?.address?.let { address ->
                            val toV2Account = V2Account(
                                "secp256k1",
                                Base64.encodeToString(selectedChain?.publicKey, Base64.NO_WRAP),
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
                        val signBundle = signBundle(id, wcUrl, params)
                        showSignDialog(signBundle, object : WcSignFragment.WcSignRawDataListener {
                            override fun sign(id: Long, data: String) {
                                approveSignDirectV2Request(id, data, sessionRequest)
                            }

                            override fun cancel(id: Long) {
                                cancelV2SignRequest(id, sessionRequest)
                            }
                        })
                    }

                    "cosmos_signAmino" -> {
                        val signBundle = signBundle(id, wcUrl, params)
                        showSignDialog(signBundle, object : WcSignFragment.WcSignRawDataListener {
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

            val signatureTx = Signer.signature(selectedChain, signDoc.toByteArray())
            val pubKey = PubKey(
                pubKeyType(),
                Strings.fromByteArray(Base64.encode(selectedChain?.publicKey, Base64.DEFAULT))
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
                Signer.signature(selectedChain, signDoc.toByteArray(StandardCharsets.UTF_8))
            val pubKey = PubKey(
                pubKeyType(),
                Strings.fromByteArray(Base64.encode(selectedChain?.publicKey, Base64.DEFAULT))
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

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent.data?.let { data ->
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
                binding.loadingLayer.visibility = View.VISIBLE
                connectWalletConnect(walletConnectURI)

            } else if (WC_URL_SCHEME_HOST_DAPP_EXTERNAL == data.host || WC_URL_SCHEME_HOST_DAPP_INTERNAL == data.host) {
                data.query?.let { url -> binding.dappWebView.loadUrl(url) }
            }
        }
    }

    override fun onDestroy() {
        if (wcVersion == 1) {
            wcV1Client?.let {
                if (it.isConnected) {
                    it.killSession()
                } else {
                    it.disconnect()
                }
            }
        } else {
            val pairingList = CoreClient.Pairing.getPairings()
            pairingList.forEach { CoreClient.Pairing.disconnect(it.topic) }
        }
        super.onDestroy()
    }

    private fun signBundle(id: Long, url: String?, data: String): Bundle {
        val bundle = Bundle()
        bundle.putString("data", data)
        bundle.putString("url", url)
        bundle.putLong("id", id)
        return bundle
    }

    private fun showSignDialog(bundle: Bundle, signListener: WcSignFragment.WcSignRawDataListener) {
        bundle.getString("data")?.let { data ->
            WcSignFragment(
                selectedChain, bundle.getLong("id"), data, bundle.getString("url"), signListener
            ).show(
                supportFragmentManager, WcSignFragment::class.java.name
            )
        }
    }

    private val dappWebViewClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            binding.wcPeer.text = Uri.parse(url).host
            loadInjectScript(view)
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
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

    private fun loadInjectScript(view: WebView?) {
        try {
            val inputStream = assets.open("injectScript.js")
            inputStream.bufferedReader().use(BufferedReader::readText)
        } catch (e: Exception) {
            null
        }?.let { view?.loadUrl("javascript:(function(){$it})()") }
    }

    fun processRequest(message: String) {
        var isCosmostation = false
        try {
            val requestJson = JSONObject(message)
            if (!requestJson.has("isCosmostation") || !requestJson.getBoolean("isCosmostation")) {
                return
            }
            isCosmostation = true
            val messageId = requestJson.getLong("messageId")
            val messageJson = requestJson.getJSONObject("message")

            val evmSupportIds =
                allEvmLines().filter { it.supportCosmos }.map { it.chainIdCosmos }.distinct()
            val cosmosSupportIds = allCosmosLines().map { it.chainIdCosmos }.distinct()
            val supportChainIds = evmSupportIds.union(cosmosSupportIds)

            val evmSupportNames =
                allEvmLines().filter { it.supportCosmos }.map { it.name.lowercase() }.distinct()
            val cosmosSupportNames = allCosmosLines().map { it.name.lowercase() }.distinct()
            val supportChainNames = evmSupportNames.union(cosmosSupportNames)

            when (messageJson.getString("method")) {
                "cos_requestAccount", "cos_account", "ten_requestAccount", "ten_account" -> {
                    val params = messageJson.getJSONObject("params")
                    val chainId = params.getString("chainName")
                    appToWebResult(messageJson, makeAppToWebAccount(chainId), messageId)
                }

                "cos_supportedChainIds" -> {
                    val dataJson = JSONObject()
                    dataJson.put("official", JSONArray(supportChainIds))
                    dataJson.put("unofficial", JSONArray(arrayListOf<String>()))
                    appToWebResult(messageJson, dataJson, messageId)
                }

                "cos_supportedChainNames", "ten_supportedChainNames" -> {
                    val dataJson = JSONObject()
                    dataJson.put("official", JSONArray(supportChainNames))
                    dataJson.put("unofficial", JSONArray(arrayListOf<String>()))
                    appToWebResult(messageJson, dataJson, messageId)
                }

                "cos_activatedChainIds" -> {
                    appToWebResult(
                        messageJson, JSONArray(supportChainIds), messageId
                    )
                }

                "cos_activatedChainNames" -> {
                    appToWebResult(
                        messageJson, JSONArray(supportChainNames), messageId
                    )
                }

                "cos_addChain", "cos_disconnect" -> {
                    appToWebResult(
                        messageJson, true, messageId
                    )
                }

                "cos_signAmino" -> {
                    val params = messageJson.getJSONObject("params")
                    val signBundle = signBundle(0, wcUrl, params.toString())
                    showSignDialog(signBundle, object : WcSignFragment.WcSignRawDataListener {
                        override fun sign(id: Long, data: String) {
                            approveSignAminoInjectRequest(messageJson, messageId, data)
                        }

                        override fun cancel(id: Long) {
                            appToWebError("Canceled", messageId)
                        }
                    })
                }

                "cos_signDirect" -> {
                    val params = messageJson.getJSONObject("params")
                    val doc = params.getJSONObject("doc")
                    val signBundle = signBundle(0, wcUrl, doc.toString())
                    showSignDialog(signBundle, object : WcSignFragment.WcSignRawDataListener {
                        override fun sign(id: Long, data: String) {
                            approveSignDirectInjectRequest(messageJson, messageId, data)
                        }

                        override fun cancel(id: Long) {
                            appToWebError("Canceled", messageId)
                        }
                    })
                }

                "cos_sendTransaction" -> {
                    try {
                        selectedChain?.let { line ->
                            val params = messageJson.getJSONObject("params")
                            val txBytes = params.getString("txBytes")
                            val mode = params.getInt("mode")
                            val request =
                                BroadcastTxRequest.newBuilder().setModeValue(mode).setTxBytes(
                                    ByteString.copyFrom(Base64.decode(txBytes, Base64.DEFAULT))
                                ).build()
                            val txStub = ServiceGrpc.newBlockingStub(getChannel(line))
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
                        appToWebError("Unknown", messageId)
                    }
                }

                else -> {
                    appToWebError("Not implemented", messageId)
                }
            }
        } catch (e: Exception) {
            if (isCosmostation) {
                appToWebError(e.message, 0L)
            }
        }
    }

    private fun approveSignAminoInjectRequest(
        messageJson: JSONObject, messageId: Long, transactionData: String
    ) {
        val signDocJson = Gson().fromJson(transactionData, JsonObject::class.java)

        val mapper = ObjectMapper()
        mapper.configure(SerializationFeature.ORDER_MAP_ENTRIES_BY_KEYS, true)
        val signDoc = mapper.writeValueAsString(
            mapper.readValue(signDocJson.toString(), TreeMap::class.java)
        )
        val signatureTx = Signer.signature(
            selectedChain, signDoc.toByteArray(StandardCharsets.UTF_8)
        )
        val pubKey = PubKey(
            pubKeyType(), Strings.fromByteArray(
                Base64.encode(
                    selectedChain?.publicKey, Base64.DEFAULT
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
        messageJson: JSONObject, messageId: Long, transactionData: String
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

        val signatureTx = Signer.signature(selectedChain, signDoc.toByteArray())
        val pubKey = PubKey(
            pubKeyType(), Strings.fromByteArray(
                Base64.encode(
                    selectedChain?.publicKey, Base64.DEFAULT
                )
            ).replace("\n", "")
        )
        val signed = JSONObject()
        signed.put("signature", signatureTx)
        signed.put("pub_key", JSONObject(Gson().toJson(pubKey)))
        signed.put("signed_doc", JSONObject(transactionData))
        appToWebResult(messageJson, signed, messageId)
    }

    private fun makeAppToWebAccount(chainId: String): JSONObject {
        val accountJson = JSONObject()
        accountJson.put("isKeystone", false)
        accountJson.put("isEthermint", false)
        accountJson.put("isLedger", false)
        BaseData.baseAccount?.let { account ->
            account.allEvmLineChains.firstOrNull { it.name.lowercase() == chainId.lowercase() && it.isDefault }
                ?.let { filteredChainsWithChainName ->
                    selectedChain = filteredChainsWithChainName

                } ?: run {
                account.allEvmLineChains.firstOrNull { it.chainIdCosmos.lowercase() == chainId.lowercase() && it.isDefault }
                    ?.let { filteredChainsWithChainId ->
                        selectedChain = filteredChainsWithChainId
                    }
                selectedChain?.fetchFilteredChain()
            }

            if (selectedChain == null) {
                account.allCosmosLineChains.firstOrNull { it.name.lowercase() == chainId.lowercase() && it.isDefault }
                    ?.let { filteredChainsWithChainName ->
                        selectedChain = filteredChainsWithChainName

                    } ?: run {
                    account.allCosmosLineChains.firstOrNull { it.chainIdCosmos.lowercase() == chainId.lowercase() && it.isDefault }
                        ?.let { filteredChainsWithChainId ->
                            selectedChain = filteredChainsWithChainId
                        }
                    selectedChain?.fetchFilteredChain()
                }
            }
            accountJson.put("address", selectedChain?.address)
            accountJson.put("name", account.name)
            accountJson.put("publicKey", selectedChain?.publicKey?.bytesToHex())
        }
        return accountJson
    }

    private fun appToWebResult(messageJson: JSONObject, resultJson: Any, messageId: Long) {
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

    private fun appToWebError(error: String?, messageId: Long) {
        val responseJson = JSONObject()
        responseJson.put("error", error)
        val postMessageJson = JSONObject()
        postMessageJson.put("response", responseJson)
        postMessageJson.put("isCosmostation", true)
        postMessageJson.put("messageId", messageId)
        runOnUiThread {
            binding.dappWebView.evaluateJavascript(
                String.format(
                    "window.postMessage(%s);", postMessageJson.toString()
                ), null
            )
        }
    }

    private fun pubKeyType(): String {
        return when (selectedChain) {
            is ChainInjective -> INJECTIVE_KEY_TYPE_PUBLIC
            is EthereumLine -> ETHERMINT_KEY_TYPE_PUBLIC
            else -> COSMOS_KEY_TYPE_PUBLIC
        }
    }

    private fun CosmosLine.fetchFilteredChain() {
        BaseData.baseAccount?.apply {
            if (type == BaseAccountType.MNEMONIC) {
                if (address?.isEmpty() == true) {
                    setInfoWithSeed(seed, setParentPath, lastHDPath)
                }

            } else if (type == BaseAccountType.PRIVATE_KEY) {
                if (address?.isEmpty() == true) {
                    setInfoWithPrivateKey(privateKey)
                }
            }
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
}