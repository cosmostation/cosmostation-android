package wannabit.io.cosmostaion.activities.txs.wc

import android.content.DialogInterface
import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.util.Base64
import android.util.Log
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.webkit.*
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import com.google.android.gms.common.util.CollectionUtils
import com.google.common.collect.Maps
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.google.gson.reflect.TypeToken
import com.squareup.picasso.Picasso
import com.trustwallet.walletconnect.WCClient
import com.trustwallet.walletconnect.models.WCAccount
import com.trustwallet.walletconnect.models.WCPeerMeta
import com.trustwallet.walletconnect.models.WCSignTransaction
import com.trustwallet.walletconnect.models.cosmostation.WCCosmostationAccount
import com.trustwallet.walletconnect.models.ethereum.WCEthereumSignMessage
import com.trustwallet.walletconnect.models.ethereum.WCEthereumTransaction
import com.trustwallet.walletconnect.models.keplr.WCKeplrWallet
import com.trustwallet.walletconnect.models.session.WCSession
import com.walletconnect.android.Core
import com.walletconnect.android.CoreClient
import com.walletconnect.sign.client.Sign
import com.walletconnect.sign.client.SignClient
import com.walletconnect.sign.client.SignInterface
import cosmos.tx.v1beta1.TxOuterClass
import cosmos.tx.v1beta1.TxOuterClass.SignDoc
import cosmos.tx.v1beta1.TxOuterClass.TxBody
import net.i2p.crypto.eddsa.Utils
import okhttp3.OkHttpClient
import org.apache.commons.lang3.StringUtils
import org.bitcoinj.core.ECKey
import org.json.JSONArray
import org.json.JSONObject
import org.web3j.crypto.Credentials
import org.web3j.crypto.RawTransaction
import org.web3j.crypto.TransactionEncoder
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.http.HttpService
import org.web3j.utils.Numeric
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.PasswordCheckActivity
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseApplication
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.cosmos.MsgGenerator
import wannabit.io.cosmostaion.crypto.CryptoHelper
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.databinding.ActivityConnectWalletBinding
import wannabit.io.cosmostaion.dialog.*
import wannabit.io.cosmostaion.dialog.Dialog_Wc_Account.OnDialogSelectListener
import wannabit.io.cosmostaion.dialog.DappSignDialog.WcSignRawDataListener
import wannabit.io.cosmostaion.dialog.Dialog_Wc_Raw_Data_Evmos.WcEvmosSignRawDataListener
import wannabit.io.cosmostaion.model.WcSignDirectModel
import wannabit.io.cosmostaion.model.WcSignModel
import wannabit.io.cosmostaion.model.type.Coin
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.WKey
import wannabit.io.cosmostaion.utils.WUtil
import wannabit.io.cosmostaion.utils.WalletConnectManager.addWhiteList
import wannabit.io.cosmostaion.utils.WalletConnectManager.getWhiteList
import java.io.BufferedReader
import java.math.BigDecimal
import java.math.BigInteger
import java.util.concurrent.TimeUnit


class WalletConnectActivity : BaseActivity() {

    private lateinit var binding: ActivityConnectWalletBinding

    private var mWalletConnectURI: String? = null

    private var wcVersion = 1
    private var wcV1Client: WCClient? = null
    private var wcV1Session: WCSession? = null
    private var wcV1PeerMeta: WCPeerMeta? = null

    private var connectType = ConnectType.QRWalletConnect

    private val loadedAccountMap: MutableMap<String, Account> = Maps.newHashMap()

    private var isHideToolbar = false
    private var lastClickPositionY = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConnectWalletBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupViews()
        processByConnectType()
    }

    private fun processByConnectType() {
        loadConnectType()
        if (connectType.hasBaseAccount()) {
            loadBaseAccount()
        }

        if (!connectType.hasBaseAccount() && baseDao.onHasPassword()) {
            val intent = Intent(this, PasswordCheckActivity::class.java)
            connectWalletResultLauncher.launch(intent)
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out)
        } else {
            fillByConnectType()
        }
    }

    private fun fillByConnectType() {
        intent.data?.let { data ->
            if (ConnectType.DeepLinkWalletConnectCommon == connectType) {
                setupWalletConnectTypeView(data.toString().replace("wc:/", "wc:"))
            } else {
                data.query?.let { query ->
                    if (connectType.isDapp()) {
                        setupDappTypeView(query)
                    } else {
                        setupWalletConnectTypeView(query)
                    }
                }
            }
        }
        intent.extras?.getString("wcUrl")?.let {
            setupWalletConnectTypeView(it)
        }
    }

    private fun loadConnectType() {
        intent.data?.let { data ->
            if (fromCosmostationScheme(data)) {
                when (data.host) {
                    WC_URL_SCHEME_HOST_WC -> {
                        connectType = ConnectType.DeepLinkWalletConnect
                    }
                    WC_URL_SCHEME_HOST_DAPP_EXTERNAL -> {
                        connectType = ConnectType.ExternalDapp
                    }
                    WC_URL_SCHEME_HOST_DAPP_INTERNAL -> {
                        connectType = ConnectType.InternalDapp
                    }
                    else -> {}
                }
            } else if (fromCommonWalletConnectScheme(data)) {
                connectType = ConnectType.DeepLinkWalletConnectCommon
            }
        }
    }

    private fun setupDappTypeView(url: String) {
        supportActionBar?.setDisplayHomeAsUpEnabled(false)
        binding.toolbarTitle.visibility = View.GONE
        binding.dappLayout.visibility = View.VISIBLE
        binding.wcPeer.text = url
        binding.dappWebView.visibility = View.VISIBLE
        binding.wcLayer.visibility = View.GONE
        binding.loadingLayer.visibility = View.GONE
        binding.btnDisconnect.visibility = View.GONE
        binding.dappLeft.setOnClickListener { if (binding.dappWebView.canGoBack()) binding.dappWebView.goBack() }
        binding.dappRight.setOnClickListener { if (binding.dappWebView.canGoForward()) binding.dappWebView.goForward() }
        binding.dappRefresh.setOnClickListener { binding.dappWebView.reload() }
        changeDappConnectStatus(false)
        binding.dappWebView.loadUrl(url)
        binding.dappWebView.addJavascriptInterface(CustomJavaScript(), "station")
        WebStorage.getInstance().deleteAllData()

    }

    private fun setupWalletConnectTypeView(url: String) {
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        binding.toolbarTitle.visibility = View.VISIBLE
        binding.dappLayout.visibility = View.GONE
        binding.dappWebView.visibility = View.GONE
        binding.btnDisconnect.visibility = View.VISIBLE
        mWalletConnectURI = url
        connectWalletConnect()
    }

    private fun processConnectScheme(url: String) {
        if (isSessionConnected()) return
        binding.loadingLayer.visibility = View.VISIBLE
        mWalletConnectURI = url
        connectWalletConnect()
    }

    private fun changeDappConnectStatus(connected: Boolean) {
    }

    private fun fromCosmostationScheme(data: Uri): Boolean {
        return WC_URL_SCHEME_COSMOSTATION == data.scheme
    }

    private fun fromCommonWalletConnectScheme(data: Uri): Boolean {
        return WC_URL_SCHEME_COMMON == data.scheme
    }

    private fun loadBaseAccount() {
        val currentAccount = baseDao.onSelectAccount(baseDao.lastUser)
        loadedAccountMap[currentAccount.baseChain] = currentAccount
        mBaseChain = BaseChain.getChain(currentAccount.baseChain)
    }

    private fun setupViews() {
        binding.dappClose.setOnClickListener { finish() }
        binding.btnDisconnect.setOnClickListener {
            moveToBackIfNeed()
            finish()
        }
        binding.dappWebView.apply {
            settings.apply {
                javaScriptEnabled = true
                userAgentString = "$userAgentString Cosmostation/APP/Android/${BuildConfig.VERSION_NAME}"
                domStorageEnabled = true
                mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
                webChromeClient = dappWebChromeClient
                webViewClient = dappWebViewClient
                setOnTouchListener(webViewTouchListener)
                setOnScrollChangeListener(webViewScrollChangeListener)
            }
        }
        setSupportActionBar(binding.toolBar)
        supportActionBar?.setDisplayShowTitleEnabled(false)
    }

    private fun connectWalletConnectV2(uri: String) {
        wcVersion = 2

        val pairingParams = Core.Params.Pair(uri)
        CoreClient.Pairing.pair(pairingParams) { error ->
            Log.e("WCV2", error.throwable.stackTraceToString())
        }

        SignClient.setWalletDelegate(object : SignInterface.WalletDelegate {
            override fun onConnectionStateChange(state: Sign.Model.ConnectionState) {
            }

            override fun onError(error: Sign.Model.Error) {
            }

            override fun onSessionDelete(deletedSession: Sign.Model.DeletedSession) {
            }

            override fun onSessionProposal(sessionProposal: Sign.Model.SessionProposal) {
                if (isFinishing) {
                    return
                }

                val sessionNamespaces: MutableMap<String, Sign.Model.Namespace.Session> = mutableMapOf()
                val methods = sessionProposal.requiredNamespaces.values.flatMap { it.methods }
                val events = sessionProposal.requiredNamespaces.values.flatMap { it.events }
                runOnUiThread {
                    val chains = sessionProposal.requiredNamespaces.values.flatMap { it.chains }
                    showAccountDialog(chains.map { it.split(":")[1] }, mutableListOf()) {
                        chains.map { chain ->
                            val chainId = chain.split(":")[1]
                            val chainName = chain.split(":")[0]
                            loadedAccountMap[WDp.getChainTypeByChainId(chainId).chain]?.address?.let {
                                sessionNamespaces[chainName] = Sign.Model.Namespace.Session(
                                    accounts = listOf("$chain:$it"), methods = methods, events = events, extensions = null
                                )
                            }
                        }
                        val approveProposal = Sign.Params.Approve(
                            proposerPublicKey = sessionProposal.proposerPublicKey, namespaces = sessionNamespaces
                        )

                        if (!connectType.isDapp()) {
                            setupConnectInfoView(sessionProposal)
                        } else {
                            changeDappConnectStatus(
                                true
                            )
                            binding.loadingLayer.apply {
                                postDelayed({ visibility = View.GONE }, 2500)
                            }
                        }
                        SignClient.approveSession(approveProposal) { error ->
                            Log.e("WCV2", error.throwable.stackTraceToString())
                        }
                    }
                }
            }

            override fun onSessionRequest(sessionRequest: Sign.Model.SessionRequest) {
                if (isFinishing) {
                    return
                }

                processV2SessionRequest(sessionRequest)
            }

            override fun onSessionSettleResponse(settleSessionResponse: Sign.Model.SettledSessionResponse) {
            }

            override fun onSessionUpdateResponse(sessionUpdateResponse: Sign.Model.SessionUpdateResponse) {
            }
        })

        return
    }

    private fun processV2SessionRequest(sessionRequest: Sign.Model.SessionRequest) {
        runOnUiThread {
            sessionRequest.request.apply {
                when (method) {
                    "cosmos_getAccounts" -> {
                        sessionRequest.chainId?.let {
                            val chainId = it.split(":")[1]
                            showAccountDialog(listOf(chainId), mutableListOf()) { accounts ->
                                val v2Accounts = accounts.map { toV2Account(it) }
                                val response = Sign.Params.Response(
                                    sessionTopic = sessionRequest.topic, jsonRpcResponse = Sign.Model.JsonRpcResponse.JsonRpcResult(
                                        id, Gson().toJson(v2Accounts)
                                    )
                                )
                                SignClient.respond(response) { error ->
                                    Log.e("WCV2", error.throwable.stackTraceToString())
                                }
                            }
                        } ?: run {
                            val response = Sign.Params.Response(
                                sessionTopic = sessionRequest.topic, jsonRpcResponse = Sign.Model.JsonRpcResponse.JsonRpcError(
                                    id, 500, "No account"
                                )
                            )
                            SignClient.respond(response) { error ->
                                Log.e("WCV2", error.throwable.stackTraceToString())
                            }
                        }
                    }
                    "cosmos_signDirect" -> {
                        val signBundle = generateSignBundle(id, params)
                        showSignDialog(signBundle, object : WcSignRawDataListener {
                            override fun sign(id: Long, transaction: String) {
                                approveCosmosSignDirectV2Request(id, transaction, sessionRequest)
                            }

                            override fun cancel(id: Long) {
                                cancelV2SignRequest(id, sessionRequest)
                            }
                        })
                    }
                    "cosmos_signAmino" -> {
                        val signBundle = generateSignBundle(id, params)
                        showSignDialog(signBundle, object : WcSignRawDataListener {
                            override fun sign(id: Long, transaction: String) {
                                approveCosmosSignAminoV2Request(id, transaction, sessionRequest)
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

    private fun connectWalletConnectV1(uri: String) {
        val meta = WCPeerMeta(
            getString(R.string.str_wc_peer_name), getString(R.string.str_wc_peer_url), getString(R.string.str_wc_peer_desc), listOf()
        )
        wcV1Session = WCSession.from(uri)
        val client: OkHttpClient = OkHttpClient.Builder().pingInterval(30, TimeUnit.SECONDS).build()
        wcV1Client = WCClient(GsonBuilder(), client).apply {
            onSessionRequest = processSessionRequest
            onDisconnect = processDisconnect
            onGetAccounts = processGetAccounts
            onKeplrEnable = processKeplrEnable
            onKeplrGetKeys = processGetKeplrAccounts
            onCosmostationAccounts = processGetCosmosAccounts
            onCosmosGetAccounts = processGetCosmosAccounts
            onEthSign = processSignEvm
            onEthSendTransaction = processSendEvm
            onSignTransaction = processSignTrust
            onKeplrSignAmino = processSignAmino
            onCosmostationSignTx = processSignAmino
            onCosmosSignAmino = processSignAmino
            onCosmosSignDirect = processSignDirect
            onCosmostationSignDirectTx = processSignDirect
            wcV1Session?.let { session -> connect(session, meta) }
        }
    }

    private fun connectWalletConnect() {
        mWalletConnectURI?.let {
            if (it.contains("@2")) {
                connectWalletConnectV2(it)
            } else {
                connectWalletConnectV1(it)
            }
        }
    }

    private val processGetAccounts: (Long) -> Unit = { id: Long ->
        wcV1Client?.approveRequest(id, generateWCDefaultAccount())
    }

    private val processKeplrEnable = { id: Long, chains: List<String> ->
        runOnUiThread {
            if (hasAccount(chains.first())) {
                wcV1Client?.approveRequest(id, chains)
                moveToBackIfNeed()
            } else {
                wcV1Client?.rejectRequest(id)
            }
            binding.loadingLayer.visibility = View.GONE
        }
    }

    private val processDisconnect = { _: Int, _: String? ->
        runOnUiThread {
            if (connectType.isDapp()) {
                changeDappConnectStatus(false)
                Toast.makeText(
                    baseContext, getString(R.string.str_wc_not_connected), Toast.LENGTH_SHORT
                ).show()
            } else {
                finish()
            }
        }
    }

    private val processSignEvm = { id: Long, signMessage: WCEthereumSignMessage ->
        runOnUiThread {
            val signBundle = generateSignBundle(id, signMessage.data)
            showEvmSignDialog(signBundle, object : WcEvmosSignRawDataListener {
                override fun sign(id: Long) {
                    processEthSign(id, signMessage)
                }

                override fun reject(id: Long) {
                    wcV1Client?.rejectRequest(
                        id, getString(R.string.str_unknown_error)
                    )
                }
            })
        }
    }

    private val processSendEvm = { id: Long, wcEthereumTransaction: WCEthereumTransaction ->
        runOnUiThread {
            val signBundle = generateSignBundle(
                id, wcEthereumTransaction.data
            )
            showEvmSignDialog(signBundle, object : WcEvmosSignRawDataListener {
                override fun sign(id: Long) {
                    processEthSend(
                        id, wcEthereumTransaction
                    )
                }

                override fun reject(id: Long) {
                    wcV1Client?.rejectRequest(
                        id, getString(R.string.str_unknown_error)
                    )
                }
            })
        }
    }

    private val processGetKeplrAccounts = { id: Long, strings: List<String> ->
        runOnUiThread {
            val chainId = strings.first()
            loadedAccountMap[WDp.getChainTypeByChainId(
                chainId
            ).chain]?.let {
                wcV1Client?.approveRequest(
                    id, listOf(
                        toKeplrWallet(
                            it
                        )
                    )
                )
                moveToBackIfNeed()
            } ?: run {
                showKeplrAccountDialog(
                    id, chainId
                )
            }
        }
    }

    private val processGetCosmosAccounts = { id: Long, strings: List<String>? ->
        runOnUiThread {
            if (strings != null) {
                showAccountDialog(strings, mutableListOf()) { accounts ->
                    fillConnectInfoAddressIfNeed()
                    wcV1Client?.approveRequest(id, accounts.mapNotNull {
                        toCosmosatationAccount(it)
                    })
                }
            } else {
                wcV1Client?.rejectRequest(id, "null point exception.")
                Toast.makeText(
                    baseContext, getString(R.string.str_unknown_error), Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    private val processSignAmino = { id: Long, jsonArray: JsonArray ->
        try {
            val chainId = jsonArray.get(0).asString
            val chainType = WDp.getChainTypeByChainId(chainId)
            val chainConfig = ChainFactory.getChain(chainType)
            val denom = chainConfig.mainDenom()
            val msgJson = jsonArray.get(2).asJsonObject
            val fee = msgJson.get("fee").asJsonObject
            val gas = fee.get("gas").asString
            val amounts = fee.get("amount").asJsonArray
            if (amounts.size() == 0) {
                val jsonObject = JsonObject()
                jsonObject.addProperty("amount", BigDecimal(gas).divide(BigDecimal(40)).toPlainString())
                jsonObject.addProperty("denom", denom)
                amounts.add(jsonObject)
            }
            val mainDenomFee = amounts.firstOrNull { it.asJsonObject["denom"].asString == denom && it.asJsonObject["amount"].asString == "0" }
            mainDenomFee?.asJsonObject?.addProperty("amount", BigDecimal(gas).divide(BigDecimal(40)).toPlainString())
        } catch (_: Exception) {
        }
        runOnUiThread {
            val signBundle = generateSignBundle(
                id, jsonArray.toString()
            )
            showSignDialog(signBundle, object : WcSignRawDataListener {
                override fun sign(
                    id: Long, transaction: String
                ) {
                    approveCosmosRequest(
                        id, transaction
                    )
                }

                override fun cancel(
                    id: Long
                ) {
                    cancelSignRequest(
                        id
                    )
                }
            })
        }
    }

    private val processSessionRequest = { _: Long, wcPeerMeta: WCPeerMeta ->
        runOnUiThread {
            var url: String? = wcPeerMeta.url
            if (connectType.isDapp()) {
                url = Uri.parse(
                    binding.dappWebView.url
                ).host
            }
            if (getWhiteList(
                    this
                ).contains(
                    url
                )
            ) {
                processSessionRequest(
                    wcPeerMeta
                )
            } else {
                showWhitelistAlert(
                    url, wcPeerMeta
                )
            }
        }
    }

    private fun showWhitelistAlert(
        url: String?, wcPeerMeta: WCPeerMeta
    ) {
        CommonAlertDialog.showDoubleButton(
            this@WalletConnectActivity, getString(
                R.string.str_wc_connect_alert_title
            ), Html.fromHtml(
                String.format(
                    "%s<br/><b>%s</b><br/><br/><font color=\"#ff2745\">%s</font>", getString(
                        R.string.str_wc_connect_alert_message
                    ), url, getString(
                        R.string.str_wc_connect_alert_guide
                    )
                ), Html.FROM_HTML_MODE_COMPACT
            ), getString(
                R.string.str_cancel
            ), {
                if (isFinishing) {
                    return@showDoubleButton
                }

                wcV1Client?.rejectSession(
                    getString(
                        R.string.str_cancel
                    )
                )
                if (connectType.isDapp()) {
                    changeDappConnectStatus(
                        false
                    )
                    binding.loadingLayer.postDelayed(
                        {
                            binding.loadingLayer.visibility = View.GONE
                        }, 1000
                    )
                } else {
                    finish()
                }
            }, getString(
                R.string.str_ok
            )
        ) {
            url?.let { whiteListUrl ->
                addWhiteList(
                    this, whiteListUrl
                )
            }
            processSessionRequest(
                wcPeerMeta
            )
        }
    }

    private val processSignTrust = { id: Long, (_, transaction): WCSignTransaction ->
        runOnUiThread {
            val signBundle = generateSignBundle(
                id, transaction
            )
            showSignDialog(signBundle, object : WcSignRawDataListener {
                override fun sign(
                    id: Long, transaction: String
                ) {
                    approveTrustRequest(
                        id, transaction
                    )
                }

                override fun cancel(
                    id: Long
                ) {
                    cancelSignRequest(
                        id
                    )
                }
            })
        }
    }

    private val processSignDirect = { id: Long, jsonArray: JsonArray ->
        runOnUiThread {
            val signBundle = generateSignBundle(
                id, jsonArray.toString()
            )
            showSignDialog(signBundle, object : WcSignRawDataListener {
                override fun sign(
                    id: Long, transaction: String
                ) {
                    approveCosmosSignDirectRequest(
                        id, transaction
                    )
                }

                override fun cancel(
                    id: Long
                ) {
                    cancelSignRequest(
                        id
                    )
                }
            })
        }
    }

    private fun processSessionRequest(
        wcPeerMeta: WCPeerMeta
    ) {
        wcV1PeerMeta = wcPeerMeta
        if (!connectType.isDapp()) {
            setupConnectInfoView(wcPeerMeta)
        } else {
            changeDappConnectStatus(
                true
            )
            binding.loadingLayer.apply {
                postDelayed(
                    {
                        visibility = View.GONE
                    }, 2500
                )
            }
        }
        Toast.makeText(
            baseContext, getString(
                R.string.str_wc_connected
            ), Toast.LENGTH_SHORT
        ).show()

        mBaseChain?.let { baseChain ->
            loadedAccountMap[mBaseChain.chain]?.let { account ->
                when (baseChain) {
                    BaseChain.EVMOS_MAIN -> {
                        wcV1Client?.approveSession(
                            listOf(
                                WKey.generateEthAddressFromPrivateKey(
                                    getPrivateKey(
                                        account
                                    )
                                )
                            ), 9001
                        )
                        return
                    }
                    else -> {
                        wcV1Client?.approveSession(
                            listOf(
                                account.address
                            ), 1
                        )
                        return
                    }
                }
            }
        }

        wcV1Client?.approveSession(
            listOf(), 1
        )
    }

    private fun processEthSign(
        id: Long, signMessage: WCEthereumSignMessage
    ) {
        try {
            loadedAccountMap[mBaseChain.chain]?.let { account ->
                val credentials = Credentials.create(
                    getPrivateKey(
                        account
                    )
                )
                val signed = org.web3j.crypto.Sign.signPrefixedMessage(
                    signMessage.data.toByteArray(), credentials.ecKeyPair
                )
                wcV1Client?.approveRequest(
                    id, signed
                )
            } ?: run {
                wcV1Client?.rejectRequest(
                    id, getString(
                        R.string.str_unknown_error
                    )
                )
            }
        } catch (e: Exception) {
            wcV1Client?.rejectRequest(
                id, getString(
                    R.string.str_unknown_error
                )
            )
        }
    }

    private fun processEthSend(
        id: Long, wcEthereumTransaction: WCEthereumTransaction
    ) {
        try {
            val rpcUrl: String = if (BaseChain.EVMOS_MAIN == mBaseChain) {
                "https://eth.bd.evmos.org:8545"
            } else {
                throw Exception(
                    "No endpoint."
                )
            }

            val web3 = Web3j.build(
                HttpService(
                    rpcUrl
                )
            )
            val account = loadedAccountMap[mBaseChain.chain] ?: run {
                throw Exception(
                    "No account."
                )
            }

            val credentials = Credentials.create(
                getPrivateKey(
                    account
                )
            )
            val ethGetTransactionCount = web3.ethGetTransactionCount(
                credentials.address, DefaultBlockParameterName.LATEST
            ).sendAsync().get()
            val nonce = ethGetTransactionCount.transactionCount
            var value = BigInteger.ZERO
            wcEthereumTransaction.value?.let {
                if (StringUtils.isNotBlank(
                        it
                    )
                ) {
                    value = BigInteger(
                        it.replace(
                            "0x", ""
                        ), 16
                    )
                }
            }
            val transaction = Transaction(
                wcEthereumTransaction.from, nonce, BigInteger.ZERO, BigInteger.ZERO, wcEthereumTransaction.to, value, wcEthereumTransaction.data
            )
            val limit = web3.ethEstimateGas(
                transaction
            ).sendAsync().get()
            val rawTransaction = RawTransaction.createTransaction(
                9001, nonce, limit.amountUsed, wcEthereumTransaction.to, value, wcEthereumTransaction.data, BigInteger.valueOf(
                    500000000L
                ), BigInteger.valueOf(
                    27500000000L
                )
            )
            val signedMessage = TransactionEncoder.signMessage(
                rawTransaction, credentials
            )
            val hexValue = Numeric.toHexString(
                signedMessage
            )
            val sendResult = web3.ethSendRawTransaction(
                hexValue
            ).sendAsync().get()
            wcV1Client?.approveRequest(
                id, sendResult.transactionHash
            )
        } catch (e: Exception) {
            wcV1Client?.rejectRequest(
                id, getString(
                    R.string.str_unknown_error
                )
            )
        }
    }

    private fun moveToBackIfNeed() {
        if (connectType == ConnectType.DeepLinkWalletConnect || connectType == ConnectType.DeepLinkWalletConnectCommon) {
            moveTaskToBack(
                true
            )
        }
    }

    private fun generateWCDefaultAccount(): List<WCAccount> {
        if (mBaseChain == BaseChain.KAVA_MAIN) {
            loadedAccountMap[mBaseChain.chain]?.let {
                return listOf(
                    WCAccount(
                        459, it.address
                    )
                )
            }
        }

        return listOf()
    }

    private fun convertKavaTx(txString: String): JSONObject {
        val kavaTx = JSONObject()
        val jsonTx = JSONObject(txString)

        kavaTx.put("chain_id", jsonTx.get("chainId"))
        kavaTx.put("account_number", jsonTx.get("accountNumber"))

        val messageArray = jsonTx.getJSONArray("messages")
        val msgs = JSONArray()
        for (i in 0 until messageArray.length()) {
            val rawMessage = messageArray.getJSONObject(i).getJSONObject("rawJsonMessage")
            val msg = JSONObject()
            msg.put("type", rawMessage.getString("type"))
            msg.put("value", JSONObject(rawMessage.getString("value")))
            msgs.put(msg)
        }
        kavaTx.put("msgs", msgs)

        val fee = jsonTx.getJSONObject("fee")
        val amount = fee.getJSONArray("amounts")
        val kavaFee = JSONObject()
        kavaFee.put("gas", fee.getString("gas"))
        kavaFee.put("amount", amount)
        kavaTx.put("fee", kavaFee)

        kavaTx.put("sequence", jsonTx.get("sequence"))
        kavaTx.put("memo", jsonTx.get("memo"))

        return kavaTx
    }

    fun approveTrustRequest(id: Long, wcSignTransaction: String) {
        try {
            val kavaTx = convertKavaTx(wcSignTransaction)
            val broadcaseReq = MsgGenerator.getKavaWcBroadcastReq(
                kavaTx, getKey(WDp.getChainTypeByChainId(kavaTx.getString("chain_id")).chain)
            )
            val result = GsonBuilder().disableHtmlEscaping().create().toJson(broadcaseReq)
//
//            val wcStdSignMsg = Gson().fromJson(wcSignTransaction, StdSignMsg::class.java)
//            val transactionJson = JSONObject(wcSignTransaction)
//            val messagesArray = transactionJson.getJSONArray("messages")
//            val msgList = arrayListOf<Msg>()
//            for (i in 0 until messagesArray.length()) {
//                val rawMessage = messagesArray.getJSONObject(i).getJSONObject("rawJsonMessage")
//                val msgModel = Msg()
//                msgModel.type = rawMessage.getString("type")
//                msgModel.value =
//                    Gson().fromJson(rawMessage.getString("value"), Msg.Value::class.java)
//                if (msgModel.value.amount != null) {
//                    msgModel.value.amount = parseAmount(msgModel.value.amount)
//                }
//                msgList.add(msgModel)
//            }
//            wcStdSignMsg.msgs = msgList
//            val account = Account()
//            account.accountNumber = wcStdSignMsg.account_number.toInt()
//            account.sequenceNumber = wcStdSignMsg.sequence.toInt()
//            val tx = MsgGenerator.getWcTrustBroadcaseReq(
//                account,
//                msgList,
//                wcStdSignMsg.fee,
//                wcStdSignMsg.memo,
//                getKey(WDp.getChainTypeByChainId(wcStdSignMsg.chain_id).chain),
//                wcStdSignMsg.chain_id
//            )
//            val result = GsonBuilder().disableHtmlEscaping().create().toJson(tx)
            wcV1Client?.approveRequest(id, result)
            Toast.makeText(
                baseContext, getString(R.string.str_wc_request_responsed), Toast.LENGTH_SHORT
            ).show()
        } catch (e: Exception) {
            wcV1Client?.rejectRequest(id, "Signing error.")
            Toast.makeText(
                baseContext, getString(R.string.str_unknown_error), Toast.LENGTH_SHORT
            ).show()
        }
        moveToBackIfNeed()
    }

    private fun parseAmount(amount: Any): Any {
        try {
            return Gson().fromJson(Gson().toJson(amount), Coin::class.java)
        } catch (ignored: Exception) {
        }
        try {
            return Gson().fromJson(
                Gson().toJson(amount), object : TypeToken<List<Coin>>() {}.type
            )
        } catch (ignored: Exception) {
        }
        return amount
    }

    fun approveCosmosRequest(id: Long, transaction: String) {
        try {
            val transactionJson = Gson().fromJson(transaction, JsonArray::class.java)
            val chainId = transactionJson[0].asString
            val txMsg = transactionJson[2].asJsonObject
            val signModel = WcSignModel(txMsg, getKey(WDp.getChainTypeByChainId(chainId).chain))
            wcV1Client?.approveRequest(id, listOf(signModel))
            Toast.makeText(
                baseContext, getString(R.string.str_wc_request_responsed), Toast.LENGTH_SHORT
            ).show()
        } catch (e: Exception) {
            wcV1Client?.rejectRequest(id, "Signing error.")
            Toast.makeText(
                baseContext, getString(R.string.str_unknown_error), Toast.LENGTH_SHORT
            ).show()
        }
        moveToBackIfNeed()
    }

    fun approveCosmosSignDirectRequest(id: Long, transaction: String) {
        try {
            val transactionJson = Gson().fromJson(transaction, JsonArray::class.java)
            val jsonObject = transactionJson[1].asJsonObject
            val chainId = jsonObject["chainId"].asString
            val txBody = TxBody.parseFrom(
                Base64.decode(
                    jsonObject["bodyBytes"].asString, Base64.DEFAULT
                )
            )
            val authInfo = TxOuterClass.AuthInfo.parseFrom(
                Base64.decode(jsonObject["authInfoBytes"].asString, Base64.DEFAULT)
            )
            val accountNumber = jsonObject["accountNumber"].asLong
            val signDoc = SignDoc.newBuilder().setBodyBytes(txBody.toByteString()).setAuthInfoBytes(authInfo.toByteString()).setChainId(chainId).setAccountNumber(accountNumber).build()
            val key = getKey(WDp.getChainTypeByChainId(chainId).chain)
            val signModel = WcSignDirectModel(signDoc.toByteArray(), jsonObject, key)
            wcV1Client?.approveRequest(id, signModel)
            Toast.makeText(
                baseContext, getString(R.string.str_wc_request_responsed), Toast.LENGTH_SHORT
            ).show()
        } catch (e: Exception) {
            wcV1Client?.rejectRequest(id, "Signing error.")
            Toast.makeText(
                baseContext, getString(R.string.str_unknown_error), Toast.LENGTH_SHORT
            ).show()
        }
        moveToBackIfNeed()
    }

    fun approveCosmosSignDirectV2Request(
        id: Long, transaction: String, sessionRequest: Sign.Model.SessionRequest
    ) {
        try {
            val jsonObject = Gson().fromJson(transaction, JsonObject::class.java)
            val signDocJson = jsonObject["signDoc"].asJsonObject
            val chainId = signDocJson["chainId"].asString
            val txBody = TxBody.parseFrom(Utils.hexToBytes(signDocJson["bodyBytes"].asString))
            val authInfo = TxOuterClass.AuthInfo.parseFrom(Utils.hexToBytes(signDocJson["authInfoBytes"].asString))
            val accountNumber = signDocJson["accountNumber"].asLong
            val signDoc = SignDoc.newBuilder().setBodyBytes(txBody.toByteString()).setAuthInfoBytes(authInfo.toByteString()).setChainId(chainId).setAccountNumber(accountNumber).build()
            val key = getKey(WDp.getChainTypeByChainId(chainId).chain)
            val signModel = WcSignDirectModel(signDoc.toByteArray(), signDocJson, key)
            val response = Sign.Params.Response(
                sessionTopic = sessionRequest.topic, jsonRpcResponse = Sign.Model.JsonRpcResponse.JsonRpcResult(
                    id, Gson().toJson(signModel.signature)
                )
            )
            SignClient.respond(response) { error ->
                Log.e("WCV2", error.throwable.stackTraceToString())
            }
            Toast.makeText(
                baseContext, getString(R.string.str_wc_request_responsed), Toast.LENGTH_SHORT
            ).show()
        } catch (e: Exception) {
            val response = Sign.Params.Response(
                sessionTopic = sessionRequest.topic, jsonRpcResponse = Sign.Model.JsonRpcResponse.JsonRpcError(
                    id, 500, "Signing error."
                )
            )
            SignClient.respond(response) { error ->
                Log.e("WCV2", error.throwable.stackTraceToString())
            }
            Toast.makeText(
                baseContext, getString(R.string.str_unknown_error), Toast.LENGTH_SHORT
            ).show()
        }
        moveToBackIfNeed()
    }

    fun approveCosmosSignAminoV2Request(
        id: Long, transaction: String, sessionRequest: Sign.Model.SessionRequest
    ) {
        try {
            val jsonObject = Gson().fromJson(transaction, JsonObject::class.java)
            val signDocJson = jsonObject["signDoc"].asJsonObject
            val chainId = signDocJson["chain_id"].asString
            val signModel = WcSignModel(signDocJson, getKey(WDp.getChainTypeByChainId(chainId).chain))
            val response = Sign.Params.Response(
                sessionTopic = sessionRequest.topic, jsonRpcResponse = Sign.Model.JsonRpcResponse.JsonRpcResult(
                    id, Gson().toJson(signModel.signature)
                )
            )
            SignClient.respond(response) { error ->
                Log.e("WCV2", error.throwable.stackTraceToString())
            }
            Toast.makeText(
                baseContext, getString(R.string.str_wc_request_responsed), Toast.LENGTH_SHORT
            ).show()
        } catch (e: Exception) {
            val response = Sign.Params.Response(
                sessionTopic = sessionRequest.topic, jsonRpcResponse = Sign.Model.JsonRpcResponse.JsonRpcError(
                    id, 500, "Signing error."
                )
            )
            SignClient.respond(response) { error ->
                Log.e("WCV2", error.throwable.stackTraceToString())
            }
            Toast.makeText(
                baseContext, getString(R.string.str_unknown_error), Toast.LENGTH_SHORT
            ).show()
        }
        moveToBackIfNeed()
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fillConnectInfoAddressIfNeed() {
        mBaseChain?.let { baseChain ->
            loadedAccountMap[baseChain.chain]?.let {
                binding.wcAddress.text = it.address
                return
            }
        }

        val accountList = loadedAccountMap.toList()
        accountList.firstOrNull()?.let {
            binding.wcAddress.text = it.second.address
        }
    }

    private fun setupConnectInfoView(meta: WCPeerMeta?) {
        if (connectType.isDapp()) {
            return
        }

        fillConnectInfoAddressIfNeed()

        if (meta != null) {
            if (!CollectionUtils.isEmpty(meta.icons)) {
                Picasso.get().load(meta.icons.first()).fit().placeholder(R.drawable.validator_none_img).into(binding.wcImg)
            } else {
                binding.wcImg.setImageResource(R.drawable.validator_none_img)
            }
            if (StringUtils.isBlank(meta.name)) {
                binding.wcName.text = getString(R.string.str_wallet_connect)
            } else {
                binding.wcName.text = meta.name
            }
            binding.wcUrl.text = meta.url
            binding.wcLayer.visibility = View.VISIBLE
            binding.loadingLayer.visibility = View.GONE
        }
    }

    private fun setupConnectInfoView(proposal: Sign.Model.SessionProposal) {
        if (connectType.isDapp()) {
            return
        }

        fillConnectInfoAddressIfNeed()

        if (!CollectionUtils.isEmpty(proposal.icons)) {
            Picasso.get().load(proposal.icons.first().path).fit().placeholder(R.drawable.validator_none_img).into(binding.wcImg)
        } else {
            binding.wcImg.setImageResource(R.drawable.validator_none_img)
        }
        if (StringUtils.isBlank(proposal.name)) {
            binding.wcName.text = getString(R.string.str_wallet_connect)
        } else {
            binding.wcName.text = proposal.name
        }
        binding.wcUrl.text = proposal.url
        binding.wcLayer.visibility = View.VISIBLE
        binding.loadingLayer.visibility = View.GONE
    }

    private fun hasAccount(chainId: String): Boolean {
        WDp.getChainTypeByChainId(chainId)?.let {
            val existAccount = baseDao.onSelectAllAccountsByChainWithKey(it)
            if (existAccount.isNotEmpty()) {
                return true
            }
        } ?: run {
            showErrorDialog(
                String.format(getString(R.string.str_error_not_support_msg), chainId)
            )
            return false
        }

        showErrorDialog(
            String.format(
                getString(R.string.str_error_not_support_chain_msg), chainId
            )
        )
        return false
    }

    private fun showKeplrAccountDialog(id: Long, chainId: String) {
        if (!hasAccount(chainId)) {
            wcV1Client?.approveRequest(id, listOf<Any>())
            moveToBackIfNeed()
            return
        }

        val bundle = Bundle()
        bundle.putString("chainName", chainId)
        val dialog = Dialog_Wc_Account.newInstance(bundle)
        dialog.setOnSelectListener(object : OnDialogSelectListener {
            override fun onSelect(account: Account) {
                loadedAccountMap[WDp.getChainTypeByChainId(chainId).chain] = account
                fillConnectInfoAddressIfNeed()
                wcV1Client?.approveRequest(id, listOf(toKeplrWallet(account)))
                moveToBackIfNeed()
            }

            override fun onCancel() {
                wcV1Client?.approveRequest(id, listOf<WCKeplrWallet>())
                moveToBackIfNeed()
            }
        })
        dialog.show(supportFragmentManager, "dialog")
    }

    private fun showAccountDialog(
        chains: List<String>?, selectedAccounts: MutableList<Account>, index: Int = 0, action: (selectedAccounts: List<Account>) -> Unit
    ) {
        if (chains != null) {
            if (index >= chains.size) {
                action(selectedAccounts)
                moveToBackIfNeed()
                return
            }

            loadedAccountMap[WDp.getChainTypeByChainId(chains[index]).chain]?.let {
                selectedAccounts.add(it)
                showAccountDialog(chains, selectedAccounts, index + 1, action)
                return
            }

            if (!hasAccount(chains[index])) {
                showAccountDialog(chains, selectedAccounts, index + 1, action)
                return
            }

            val bundle = Bundle()
            bundle.putString("chainName", chains[index])
            val dialog = Dialog_Wc_Account.newInstance(bundle)
            dialog.setOnSelectListener(object : OnDialogSelectListener {
                override fun onSelect(account: Account) {
                    loadedAccountMap[WDp.getChainTypeByChainId(chains[index]).chain] = account
                    selectedAccounts.add(account)
                    showAccountDialog(chains, selectedAccounts, index + 1, action)
                }

                override fun onCancel() {
                    showAccountDialog(chains, selectedAccounts, index + 1, action)
                }
            })
            dialog.show(supportFragmentManager, "dialog$index")
        } else {
            return
        }
    }

    override fun onBackPressed() {
        if (connectType.isDapp() && binding.dappWebView.canGoBack()) {
            binding.dappWebView.goBack()
        } else {
            super.onBackPressed()
        }
    }

    private fun getKey(chainId: String): ECKey? {
        loadedAccountMap[chainId]?.let {
            return ECKey.fromPrivate(BigInteger(getPrivateKey(it), 16))
        }

        return null
    }

    private fun getPrivateKey(account: Account): String {
        return if (account.fromMnemonic) {
            val entropy = CryptoHelper.doDecryptData(
                getString(R.string.key_mnemonic) + account.uuid, account.resource, account.spec
            )
            WKey.getKeyWithPathfromEntropy(account, entropy).privateKeyAsHex
        } else {
            CryptoHelper.doDecryptData(
                getString(R.string.key_private) + account.uuid, account.resource, account.spec
            )
        }
    }

    private fun toKeplrWallet(account: Account): WCKeplrWallet? {
        val key = getKey(account.baseChain)
        return key?.let {
            WCKeplrWallet(
                WUtil.getWalletName(this, account), "secp256k1", Utils.bytesToHex(it.pubKey), WKey.generateTenderAddressBytesFromPrivateKey(key.privateKeyAsHex), account.address, false
            )
        }
    }

    private fun toCosmosatationAccount(account: Account): WCCosmostationAccount? {
        val key = getKey(account.baseChain)
        return key?.let {
            WCCosmostationAccount(
                WUtil.getWalletName(this, account), "secp256k1", Utils.bytesToHex(it.pubKey), WKey.generateTenderAddressBytesFromPrivateKey(key.privateKeyAsHex), account.address
            )
        }
    }

    private fun toV2Account(account: Account): V2Account? {
        val key = getKey(account.baseChain)
        return key?.let {
            V2Account(
                account.address,
                Utils.bytesToHex(it.pubKey),
                "secp256k1",
            )
        }
    }

    private fun showSignDialog(bundle: Bundle, signListener: WcSignRawDataListener) {
        val wcRawDataDialog = DappSignDialog.newInstance(bundle.getLong("id"), bundle.getString("data")!!, bundle.getString("url"), signListener)
        wcRawDataDialog.show(supportFragmentManager, "dialog")
    }

    private fun showEvmSignDialog(
        bundle: Bundle, listener: WcEvmosSignRawDataListener
    ) {
        val dialog = Dialog_Wc_Raw_Data_Evmos.newInstance(bundle, listener)
        dialog.show(supportFragmentManager, "dialog")
    }

    private fun cancelSignRequest(id: Long) {
        wcV1Client?.rejectRequest(id, getString(R.string.str_cancel))
        moveToBackIfNeed()
    }

    private fun cancelV2SignRequest(
        id: Long, sessionRequest: Sign.Model.SessionRequest
    ) {
        val response = Sign.Params.Response(
            sessionTopic = sessionRequest.topic, jsonRpcResponse = Sign.Model.JsonRpcResponse.JsonRpcError(
                id, 300, getString(R.string.str_cancel)
            )
        )
        SignClient.respond(response) { error ->
            Log.e("WCV2", error.throwable.stackTraceToString())
        }
        Toast.makeText(
            baseContext, getString(R.string.str_cancel), Toast.LENGTH_SHORT
        ).show()
        moveToBackIfNeed()
    }

    private fun showErrorDialog(message: String) {
        CommonAlertDialog.showSingleButton(
            this, getString(R.string.str_error_not_support_chain_title), message, getString(R.string.str_ok), null, false
        )
    }

    private fun generateSignBundle(id: Long, data: String): Bundle {
        val bundle = Bundle()
        bundle.putString("data", data)
        bundle.putString("url", wcV1PeerMeta?.url)
        bundle.putLong("id", id)
        return bundle
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent.data?.let { data ->
            if (!fromCosmostationScheme(data)) {
                return
            }

            if (WC_URL_SCHEME_HOST_WC == data.host) {
                if (isSessionConnected()) return
                mWalletConnectURI = data.query
                binding.loadingLayer.visibility = View.VISIBLE
                connectWalletConnect()
            } else if (WC_URL_SCHEME_HOST_DAPP_EXTERNAL == data.host || WC_URL_SCHEME_HOST_DAPP_INTERNAL == data.host) {
                if (!connectType.isDapp()) {
                    Toast.makeText(
                        this@WalletConnectActivity, R.string.str_unknown_error, Toast.LENGTH_SHORT
                    ).show()
                } else {
                    data.query?.let { url -> binding.dappWebView.loadUrl(url) }
                }
            }
        }
    }

    private fun isSessionConnected(): Boolean {
        if (wcV1Session != null && wcV1Client != null && wcV1Client?.session != null && wcV1Client?.isConnected == true) {
            return true
        }
        return false
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

    private val connectWalletResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == RESULT_OK) {
            fillByConnectType()
        } else {
            finish()
        }
    }

    private val webViewTouchListener = { _: View?, event: MotionEvent ->
        if (event.action == MotionEvent.ACTION_DOWN) {
            lastClickPositionY = -1
        } else if (event.action == MotionEvent.ACTION_UP) {
            lastClickPositionY = -1
        }
        false
    }

    private val webViewScrollChangeListener = View.OnScrollChangeListener { _: View?, _: Int, scrollY: Int, _: Int, oldScrollY: Int ->
        if (lastClickPositionY == -1) {
            lastClickPositionY = oldScrollY
        }
        if (lastClickPositionY > scrollY && Math.abs(scrollY - oldScrollY) > 50 && isHideToolbar) {
            isHideToolbar = false
            supportActionBar?.show()
        } else if (lastClickPositionY < scrollY && Math.abs(
                scrollY - oldScrollY
            ) > 50 && !isHideToolbar
        ) {
            isHideToolbar = true
            supportActionBar?.hide()
        }
    }

    private val dappWebChromeClient = object : WebChromeClient() {
        override fun onJsAlert(
            view: WebView, url: String, message: String, result: JsResult
        ): Boolean {
            AlertDialog.Builder(
                view.context, R.style.DialogTheme
            ).setMessage(message).setPositiveButton("OK") { dialog: DialogInterface?, which: Int -> result.confirm() }.setOnDismissListener { dialog: DialogInterface? -> result.confirm() }.create().show()
            return true
        }

        override fun onJsConfirm(
            view: WebView, url: String, message: String, result: JsResult
        ): Boolean {
            AlertDialog.Builder(
                view.context, R.style.DialogTheme
            ).setMessage(message).setPositiveButton("OK") { _: DialogInterface?, _: Int -> result.confirm() }.setNegativeButton("Cancel") { _: DialogInterface?, _: Int -> result.cancel() }.setOnDismissListener { dialog: DialogInterface? -> result.cancel() }.create().show()
            return true
        }
    }

    private val dappWebViewClient = object : WebViewClient() {
        override fun onPageStarted(view: WebView?, url: String?, favicon: Bitmap?) {
            super.onPageStarted(view, url, favicon)

            binding.wcPeer.text = Uri.parse(url).host
            try {
                val inputStream = assets.open("injectScript.js")
                inputStream.bufferedReader().use(BufferedReader::readText)
            } catch (e: Exception) {
                null
            }?.let { view?.loadUrl("javascript:(function(){$it})()") }
        }

        override fun onPageFinished(view: WebView?, url: String?) {
            super.onPageFinished(view, url)
        }

        override fun shouldOverrideUrlLoading(
            view: WebView, request: WebResourceRequest
        ): Boolean {
            var modifiedUrl = request.url.toString()
            if (isFinishing) {
                return true
            }
            if (modifiedUrl.startsWith("wc:")) {
                processConnectScheme(modifiedUrl)
                return true
            } else if (modifiedUrl.startsWith("keplrwallet://wcV1")) {
                processConnectScheme(modifiedUrl)
                return true
            } else if (modifiedUrl.startsWith("intent:")) {
                if (modifiedUrl.contains("intent://wcV1")) {
                    modifiedUrl = modifiedUrl.replace(
                        "#Intent;package=com.chainapsis.keplr;scheme=keplrwallet;end;", "#Intent;package=wannabit.io.cosmostaion;scheme=cosmostation;end;"
                    )
                    modifiedUrl = modifiedUrl.replace(
                        "intent://wcV1", "intent://wc"
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
                        marketIntent.data = Uri.parse("market://details?id=" + intent.getPackage())
                        startActivity(marketIntent)
                    }
                    return true
                } catch (e: Exception) {
                    e.printStackTrace()
                }
            }
            return false
        }
    }


    fun processRequest(message: String) {
        try {
            val json = JSONObject(message)
            val method = json.getString("method")
            if (method == "cos_requestAccount" || method == "cos_account" || method == "ten_requestAccount" || method == "ten_account") {
                val params = json.getJSONObject("params")
                val chainId = params.getString("chainName")
                val dataJson = JSONObject()
                dataJson.put("isKeystone", false)
                dataJson.put("isEthermint", false)
                dataJson.put("isLedger", false)
                val baseDao = BaseApplication.getInstance().baseDao
                val account = baseDao.onSelectAccount(baseDao.lastUser)
                val key = ECKey.fromPrivate(BigInteger(getPrivateKey(account), 16))
                val baseChain = WDp.getChainTypeByChainId(chainId)
                dataJson.put(
                    "address", WKey.genTendermintBech32Address(
                        baseChain, Utils.bytesToHex(key.pubKey)
                    )
                )
                dataJson.put("name", WUtil.getWalletName(this, account))
                dataJson.put("publicKey", Utils.bytesToHex(key.pubKey))
                val responseJson = JSONObject()
                responseJson.put("result", dataJson)
                val resJson = JSONObject()
                resJson.put("response", responseJson)
                resJson.put("message", json)
                runOnUiThread {
                    binding.dappWebView.evaluateJavascript(
                        String.format("window.postMessage(%s);", resJson.toString()), null
                    )
                }
            } else if (method == "cos_supportedChainIds") {
                val dataJson = JSONObject()
                dataJson.put(
                    "official", listOf("cosmoshub-4", "osmosis-1", "stride-1", "injective-1")
                )
                dataJson.put("unofficial", listOf("a"))
                val responseJson = JSONObject()
                responseJson.put("result", dataJson)
                val resJson = JSONObject()
                resJson.put("response", responseJson)
                resJson.put("message", json)
                runOnUiThread {
                    binding.dappWebView.evaluateJavascript(
                        String.format("window.postMessage(%s);", resJson.toString()), null
                    )
                }
            } else if (method == "cos_signAmino") {
                val params = json.getJSONObject("params")
                val doc = params.getJSONObject("doc")
                val signBundle = generateSignBundle(0, doc.toString())
                showSignDialog(signBundle, object : WcSignRawDataListener {
                    override fun sign(id: Long, transaction: String) {
                        val transactionJson = Gson().fromJson(transaction, JsonObject::class.java)
                        val chainId = transactionJson.get("chain_id").asString
                        val baseDao = BaseApplication.getInstance().baseDao
                        val account = baseDao.onSelectAccount(baseDao.lastUser)
                        val key = ECKey.fromPrivate(BigInteger(getPrivateKey(account), 16))
                        val signModel = WcSignModel(transactionJson, key)
                        val signed = JSONObject()
                        signed.put("signature", signModel.signature.signature)
                        signed.put(
                            "pub_key", JSONObject(Gson().toJson(signModel.signature.pub_key))
                        )
                        signed.put("signed_doc", JSONObject(transaction))
                        val responseJson = JSONObject()
                        responseJson.put("result", signed)
                        val resJson = JSONObject()
                        resJson.put("response", responseJson)
                        resJson.put("message", json)
                        runOnUiThread {
                            binding.dappWebView.evaluateJavascript(
                                String.format("window.postMessage(%s);", resJson.toString()), null
                            )
                        }
                    }

                    override fun cancel(id: Long) {
                        val responseJson = JSONObject()
                        responseJson.put("error", "cancel")
                        val resJson = JSONObject()
                        resJson.put("response", responseJson)
                        resJson.put("message", json)
                        runOnUiThread {
                            binding.dappWebView.evaluateJavascript(
                                String.format("window.postMessage(%s);", resJson.toString()), null
                            )
                        }
                    }
                })
            } else if (method == "cos_signDirect") {
                val params = json.getJSONObject("params")
                val doc = params.getJSONObject("doc")
                val signBundle = generateSignBundle(0, doc.toString())
                showSignDialog(signBundle, object : WcSignRawDataListener {
                    override fun sign(id: Long, transaction: String) {
                        val transactionJson = Gson().fromJson(transaction, JsonObject::class.java)
                        val chainId = transactionJson["chain_id"].asString
                        val txBody = TxBody.parseFrom(Utils.hexToBytes(transactionJson["body_bytes"].asString))
                        val authInfo = TxOuterClass.AuthInfo.parseFrom(Utils.hexToBytes(transactionJson["auth_info_bytes"].asString))
                        val accountNumber = transactionJson["account_number"].asLong
                        val signDoc = SignDoc.newBuilder().setBodyBytes(txBody.toByteString()).setAuthInfoBytes(authInfo.toByteString()).setChainId(chainId).setAccountNumber(accountNumber).build()
                        val baseDao = BaseApplication.getInstance().baseDao
                        val account = baseDao.onSelectAccount(baseDao.lastUser)
                        val key = ECKey.fromPrivate(BigInteger(getPrivateKey(account), 16))
                        val signModel = WcSignDirectModel(signDoc.toByteArray(), transactionJson, key)
                        val signed = JSONObject()
                        signed.put("signature", signModel.signature.signature)
                        signed.put(
                            "pub_key", JSONObject(Gson().toJson(signModel.signature.pub_key))
                        )
                        signed.put("signed_doc", JSONObject(transaction))
                        val responseJson = JSONObject()
                        responseJson.put("result", signed)
                        val resJson = JSONObject()
                        resJson.put("response", responseJson)
                        resJson.put("message", json)
                        runOnUiThread {
                            binding.dappWebView.evaluateJavascript(
                                String.format("window.postMessage(%s);", resJson.toString()), null
                            )
                        }
                    }

                    override fun cancel(id: Long) {
                        val responseJson = JSONObject()
                        responseJson.put("error", "cancel")
                        val resJson = JSONObject()
                        resJson.put("response", responseJson)
                        resJson.put("message", json)
                        runOnUiThread {
                            binding.dappWebView.evaluateJavascript(
                                String.format("window.postMessage(%s);", resJson.toString()), null
                            )
                        }
                    }
                })
            } else if (method == "cos_sendTransaction") {
            }
        } catch (e: Exception) {
            if (message == "undefined") {
                return
            }
            val responseJson = JSONObject()
            responseJson.put("error", e.message)
            val resJson = JSONObject()
            resJson.put("response", responseJson)
            runOnUiThread {
                binding.dappWebView.evaluateJavascript(
                    String.format("window.postMessage(%s);", resJson.toString()), null
                )
            }
        }
    }

    inner class CustomJavaScript {
        @JavascriptInterface
        fun request(message: String) {
            runOnUiThread {
                processRequest(message)
            }
        }
    }

    companion object {
        const val WC_URL_SCHEME_HOST_WC = "wc"
        const val WC_URL_SCHEME_HOST_DAPP_EXTERNAL = "dapp"
        const val WC_URL_SCHEME_HOST_DAPP_INTERNAL = "internaldapp"
        const val WC_URL_SCHEME_COSMOSTATION = "cosmostation"
        const val WC_URL_SCHEME_COMMON = "wc"
    }

    data class V2Account(
        val algo: String,
        val pubkey: String,
        val address: String,
    )
}