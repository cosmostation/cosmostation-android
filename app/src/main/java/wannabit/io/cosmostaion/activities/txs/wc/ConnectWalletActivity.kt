package wannabit.io.cosmostaion.activities.txs.wc

import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.text.Html
import android.util.Base64
import android.view.MenuItem
import android.view.MotionEvent
import android.view.View
import android.webkit.*
import android.widget.*
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import com.google.android.gms.common.util.CollectionUtils
import com.google.common.collect.Lists
import com.google.common.collect.Maps
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.google.gson.JsonArray
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
import cosmos.tx.v1beta1.TxOuterClass
import cosmos.tx.v1beta1.TxOuterClass.SignDoc
import cosmos.tx.v1beta1.TxOuterClass.TxBody
import okhttp3.OkHttpClient
import org.apache.commons.lang3.StringUtils
import org.bitcoinj.core.ECKey
import org.json.JSONException
import org.json.JSONObject
import org.web3j.crypto.Credentials
import org.web3j.crypto.RawTransaction
import org.web3j.crypto.Sign
import org.web3j.crypto.Sign.SignatureData
import org.web3j.crypto.TransactionEncoder
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.DefaultBlockParameterName
import org.web3j.protocol.core.methods.request.Transaction
import org.web3j.protocol.core.methods.response.EthSendTransaction
import org.web3j.protocol.http.HttpService
import org.web3j.utils.Numeric
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.PasswordCheckActivity
import wannabit.io.cosmostaion.base.BaseActivity
import wannabit.io.cosmostaion.base.BaseChain
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.cosmos.MsgGenerator
import wannabit.io.cosmostaion.crypto.CryptoHelper
import wannabit.io.cosmostaion.dao.Account
import wannabit.io.cosmostaion.databinding.ActivityConnectWalletBinding
import wannabit.io.cosmostaion.dialog.CommonAlertDialog
import wannabit.io.cosmostaion.dialog.Dialog_Wc_Account
import wannabit.io.cosmostaion.dialog.Dialog_Wc_Account.OnDialogSelectListener
import wannabit.io.cosmostaion.dialog.Dialog_Wc_Raw_Data
import wannabit.io.cosmostaion.dialog.Dialog_Wc_Raw_Data.WcSignRawDataListener
import wannabit.io.cosmostaion.dialog.Dialog_Wc_Raw_Data_Evmos
import wannabit.io.cosmostaion.dialog.Dialog_Wc_Raw_Data_Evmos.WcEvmosSignRawDataListener
import wannabit.io.cosmostaion.model.StdSignMsg
import wannabit.io.cosmostaion.model.WcSignDirectModel
import wannabit.io.cosmostaion.model.WcSignModel
import wannabit.io.cosmostaion.model.type.Coin
import wannabit.io.cosmostaion.model.type.Msg
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.WKey
import wannabit.io.cosmostaion.utils.WUtil
import wannabit.io.cosmostaion.utils.WalletConnectManager.addWhiteList
import wannabit.io.cosmostaion.utils.WalletConnectManager.getWhiteList
import java.math.BigInteger
import java.util.*
import java.util.concurrent.ExecutionException
import java.util.concurrent.TimeUnit

class ConnectWalletActivity : BaseActivity() {
    private lateinit var binding: ActivityConnectWalletBinding

    private var mWcURL: String? = null
    private var wcClient: WCClient? = null
    private var wcSession: WCSession? = null
    private var mWcPeerMeta: WCPeerMeta? = null
    private var isDeepLink = false
    private var isDapp = false
    private var mWcEthereumTransaction: WCEthereumTransaction? = null
    private var mSignMessage: WCEthereumSignMessage? = null
    private val chainAccountMap: MutableMap<String, Account> = Maps.newHashMap()
    private var isHideToolbar = false
    private var lastClickPositionY = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityConnectWalletBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initView()
        initWebView()
        settingViewFromIntent()
        settingToolbar()
    }

    private fun settingViewFromIntent() {
        if (fromScheme(intent)) {
            intent.data?.let { data ->
                if (WC_URL_SCHEME_HOST_WC == data.host) {
                    isDeepLink = true
                    settingForWc(data.query)
                } else if (WC_URL_SCHEME_HOST_DAPP == data.host) {
                    data.query?.let { settingForDappExternalScheme(it) }
                } else if (WC_URL_SCHEME_HOST_DAPP_INTERNAL == data.host) {
                    data.query?.let { settingForDappInternalScheme(it) }
                } else {
                }
            }
        } else if (intent.hasExtra(INTENT_KEY_WC_URL)) {
            settingForWc(intent.getStringExtra(INTENT_KEY_WC_URL))
        } else if (intent.hasExtra(INTENT_KEY_DAPP_URL)) {
            intent.getStringExtra(INTENT_KEY_DAPP_URL)?.let { settingForDappExternalScheme(it) }

        }
    }

    private fun settingForDappInternalScheme(url: String) {
        loadBaseAccount()
        isDapp = true
        binding.wcWebview.visibility = View.VISIBLE
        binding.wcWebview.loadUrl(url)
        changeDappConnectStatus(false)
        binding.dappLayout.visibility = View.VISIBLE
        binding.wcPeer.text = url
        binding.wcLayer.visibility = View.GONE
        binding.loadingLayer.visibility = View.GONE
        binding.btnDisconnect.visibility = View.GONE
    }

    private fun settingForWc(url: String?) {
        mWcURL = url
        if (isDeepLink) {
            checkPassword()
        } else {
            loadBaseAccount()
            initWalletConnect()
        }
        binding.wcWebview.visibility = View.GONE
        binding.dappLayout.visibility = View.GONE
        binding.btnDisconnect.visibility = View.VISIBLE
    }

    private fun settingForWcDefaultScheme(url: String) {
        if (wcSession != null && wcClient != null && wcClient?.session != null && wcClient?.isConnected == true) {
            return
        }
        binding.loadingLayer.visibility = View.VISIBLE
        mWcURL = url
        isDapp = true
        loadBaseAccount()
        initWalletConnect()
    }

    private fun settingForDappExternalScheme(url: String) {
        isDapp = true
        binding.wcWebview.visibility = View.VISIBLE
        binding.wcWebview.loadUrl(url)
        changeDappConnectStatus(false)
        binding.dappLayout.visibility = View.VISIBLE
        binding.wcPeer.text = url
        binding.wcLayer.visibility = View.GONE
        binding.loadingLayer.visibility = View.GONE
        binding.btnDisconnect.visibility = View.GONE
    }

    private fun changeDappConnectStatus(connected: Boolean) {
        if (connected) {
            binding.wcLight.setImageResource(R.drawable.ic_passed_img)
            binding.wcState.setText(R.string.str_wc_dapp_connected)
            binding.wcState.setTextColor(getColor(R.color.colorBlackDayNight))
        } else {
            binding.wcLight.setImageResource(R.drawable.ic_pass_gr)
            binding.wcState.setText(R.string.str_wc_dapp_not_connected)
            binding.wcState.setTextColor(getColor(R.color.colorGray5))
        }
    }

    private fun checkPassword() {
        if (!baseDao.onHasPassword()) {
            binding.emptyAccount.visibility = View.VISIBLE
            binding.loadingLayer.visibility = View.GONE
            binding.btnDisconnect.setText(R.string.str_dismiss)
        } else {
            val intent = Intent(this, PasswordCheckActivity::class.java)
            connectWalletResultLauncher.launch(intent)
            overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out)
        }
    }

    private fun settingToolbar() {
        setSupportActionBar(findViewById(R.id.tool_bar))
        supportActionBar?.setDisplayShowTitleEnabled(false)
        supportActionBar?.setDisplayHomeAsUpEnabled(!isDapp)
        binding.toolbarTitle.visibility = if (isDapp) View.GONE else View.VISIBLE
    }

    private fun fromScheme(intent: Intent): Boolean {
        return intent.data != null && WC_URL_SCHEME_COSMOSTATION == intent.data?.scheme
    }

    private fun loadBaseAccount() {
        val currentAccount = baseDao.onSelectAccount(baseDao.lastUser)
        chainAccountMap[currentAccount.baseChain] = currentAccount
        mBaseChain = BaseChain.getChain(currentAccount.baseChain)
        mChainConfig = ChainFactory.getChain(mBaseChain)
        binding.wcCard.setCardBackgroundColor(
            ContextCompat.getColor(
                this, mChainConfig.chainBgColor()
            )
        )
    }

    private fun initView() {
        binding.dappClose.setOnClickListener { finish() }
        binding.btnDisconnect.setOnClickListener {
            moveToBackIfNeed()
            finish()
        }
    }

    private fun initWebView() {
        WebStorage.getInstance().deleteAllData()
        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
        binding.wcWebview.setOnTouchListener { _: View?, event: MotionEvent ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                lastClickPositionY = -1
            } else if (event.action == MotionEvent.ACTION_UP) {
                lastClickPositionY = -1
            }
            false
        }
        binding.wcWebview.setOnScrollChangeListener(View.OnScrollChangeListener { v: View?, scrollX: Int, scrollY: Int, oldScrollX: Int, oldScrollY: Int ->
            if (lastClickPositionY == -1) {
                lastClickPositionY = oldScrollY
            }
            if (lastClickPositionY > scrollY && Math.abs(scrollY - oldScrollY) > 50 && isHideToolbar) {
                isHideToolbar = false
                supportActionBar?.show()
            } else if (lastClickPositionY < scrollY && Math.abs(scrollY - oldScrollY) > 50 && !isHideToolbar) {
                isHideToolbar = true
                supportActionBar?.hide()
            }
        })
        binding.wcWebview.settings.javaScriptEnabled = true
        binding.wcWebview.settings.setUserAgentString(binding.wcWebview.getSettings().userAgentString + " Cosmostation/APP/Android/" + BuildConfig.VERSION_NAME)
        binding.wcWebview.getSettings().domStorageEnabled = true
        binding.wcWebview.getSettings().mixedContentMode = WebSettings.MIXED_CONTENT_ALWAYS_ALLOW
        binding.wcWebview.setWebChromeClient(object : WebChromeClient() {
            override fun onJsAlert(
                view: WebView, url: String, message: String, result: JsResult
            ): Boolean {
                AlertDialog.Builder(view.context, R.style.DialogTheme).setMessage(message)
                    .setPositiveButton("OK") { dialog: DialogInterface?, which: Int -> result.confirm() }
                    .setOnDismissListener { dialog: DialogInterface? -> result.confirm() }.create()
                    .show()
                return true
            }

            override fun onJsConfirm(
                view: WebView, url: String, message: String, result: JsResult
            ): Boolean {
                AlertDialog.Builder(view.context, R.style.DialogTheme).setMessage(message)
                    .setPositiveButton("OK") { _: DialogInterface?, _: Int -> result.confirm() }
                    .setNegativeButton("Cancel") { _: DialogInterface?, _: Int -> result.cancel() }
                    .setOnDismissListener { dialog: DialogInterface? -> result.cancel() }.create()
                    .show()
                return true
            }
        })
        binding.wcWebview.setWebViewClient(object : WebViewClient() {
            override fun shouldOverrideUrlLoading(view: WebView, url: String): Boolean {
                var modifiedUrl = url
                if (isFinishing) {
                    return true
                }
                if (modifiedUrl.startsWith("wc:")) {
                    settingForWcDefaultScheme(modifiedUrl)
                    return true
                } else if (modifiedUrl.startsWith("intent:")) {
                    if (modifiedUrl.contains("intent://wcV1")) {
                        modifiedUrl = modifiedUrl.replace(
                            "#Intent;package=com.chainapsis.keplr;scheme=keplrwallet;end;",
                            "#Intent;package=wannabit.io.cosmostaion;scheme=cosmostation;end;"
                        )
                        modifiedUrl = modifiedUrl.replace("intent://wcV1", "intent://wc")
                        modifiedUrl =
                            modifiedUrl.replace("scheme=keplrwallet", "scheme=cosmostation")
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
                        existPackage?.let { startActivity(it) } ?: run {
                            val marketIntent = Intent(Intent.ACTION_VIEW)
                            marketIntent.data =
                                Uri.parse("market://details?id=" + intent.getPackage())
                            startActivity(marketIntent)
                        }
                        return true
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                }
                return false
            }
        })
    }

    private fun initWalletConnect() {
        val client: OkHttpClient =
            OkHttpClient.Builder().pingInterval(100000, TimeUnit.MILLISECONDS).build()
        wcClient = WCClient(GsonBuilder(), client)
        val meta = WCPeerMeta(
            getString(R.string.str_wc_peer_name),
            getString(R.string.str_wc_peer_url),
            getString(R.string.str_wc_peer_desc),
            Lists.newArrayList()
        )
        mWcURL?.let {
            wcSession = WCSession.from(it)
            wcSession?.let { session ->
                wcClient?.connect(
                    session, meta, UUID.randomUUID().toString(), null
                )
            }
        }
        wcClient?.onGetAccounts = { id: Long ->
            wcClient?.approveRequest(id, makeWCAccount())
        }
        wcClient?.onDisconnect = { code: Int, reason: String? ->
            runOnUiThread {
                Toast.makeText(
                    baseContext, getString(R.string.str_wc_not_connected), Toast.LENGTH_SHORT
                ).show()
                if (!isFinishing && !isDapp) finish()
                if (isDapp) changeDappConnectStatus(false)
            }
        }
        wcClient?.onSessionRequest = { id: Long, wcPeerMeta: WCPeerMeta ->
            runOnUiThread {
                var url: String? = wcPeerMeta.url
                if (isDapp) {
                    url = Uri.parse(binding.wcWebview.url).host
                }
                if (getWhiteList(this).contains(url)) {
                    processSessionRequest(wcPeerMeta)
                } else {
                    val finalUrl = url
                    CommonAlertDialog.showDoubleButton(
                        this@ConnectWalletActivity,
                        getString(R.string.str_wc_connect_alert_title),
                        Html.fromHtml(
                            String.format(
                                "%s<br/><b>%s</b><br/><br/><font color=\"#ff2745\">%s</font>",
                                getString(R.string.str_wc_connect_alert_message),
                                url,
                                getString(R.string.str_wc_connect_alert_guide)
                            ), Html.FROM_HTML_MODE_COMPACT
                        ),
                        getString(R.string.str_cancel),
                        {
                            binding.loadingLayer.postDelayed(
                                { binding.loadingLayer.visibility = View.GONE }, 1000
                            )
                            wcClient?.rejectSession(getString(R.string.str_cancel))
                            if (!isFinishing && !isDapp) finish()
                            if (isDapp) changeDappConnectStatus(false)
                        },
                        getString(R.string.str_ok)
                    ) {
                        finalUrl?.let { addWhiteList(this, it) }
                        processSessionRequest(wcPeerMeta)
                    }
                }
            }
            null
        }
        wcClient?.onSignTransaction = { id: Long, (_, transaction): WCSignTransaction ->
            runOnUiThread {
                onShowSignDialog(
                    makeSignBundle(
                        TYPE_TRUST_WALLET, id, transaction
                    )
                )
            }
        }
        wcClient?.onKeplrEnable = { id: Long, strings: List<String> ->
            runOnUiThread { onKeplrEnable(id, strings) }
        }
        wcClient?.onEthSign = { id: Long, signMessage: WCEthereumSignMessage? ->
            mSignMessage = signMessage
            runOnUiThread {
                onShowEvmosSignDialog(
                    makeEvmosSignBundle(
                        TYPE_ETH_SIGN_MESSAGE, id, null, signMessage
                    )
                )
            }
        }
        wcClient?.onEthSendTransaction =
            { id: Long, wcEthereumTransaction: WCEthereumTransaction? ->
                mWcEthereumTransaction = wcEthereumTransaction
                runOnUiThread {
                    onShowEvmosSignDialog(
                        makeEvmosSignBundle(
                            TYPE_ETH_SIGN_TRANSACTION, id, wcEthereumTransaction, null
                        )
                    )
                }
            }
        wcClient?.onCosmostationAccounts = { id: Long, strings: List<String> ->
            runOnUiThread { onShowAccountDialog(id, strings, Lists.newArrayList(), 0) }
        }
        wcClient?.onCosmostationSignTx = { id: Long, jsonArray: JsonArray ->
            runOnUiThread {
                onShowSignDialog(
                    makeSignBundle(
                        TYPE_COSMOS_WALLET, id, jsonArray.toString()
                    )
                )
            }
        }
        wcClient?.onKeplrGetKeys = { id: Long, strings: List<String> ->
            runOnUiThread {
                val chainId = strings[0]
                if (!chainAccountMap.containsKey(WDp.getChainTypeByChainId(chainId).chain)) {
                    onShowKeplrAccountDialog(id, chainId)
                } else {
                    chainAccountMap[WDp.getChainTypeByChainId(chainId).chain]?.let {
                        wcClient?.approveRequest(
                            id, Lists.newArrayList(
                                toKeplrWallet(
                                    it
                                )
                            )
                        )
                        moveToBackIfNeed()
                    } ?: run {
                        wcClient?.rejectRequest(id)
                        moveToBackIfNeed()
                    }

                }
            }
        }
        wcClient?.onKeplrSignAmino = { id: Long, jsonArray: JsonArray ->
            runOnUiThread {
                onShowSignDialog(
                    makeSignBundle(
                        TYPE_COSMOS_WALLET, id, jsonArray.toString()
                    )
                )
            }
        }
        wcClient?.onCosmosGetAccounts = { id: Long, strings: List<String> ->
            runOnUiThread { onShowAccountDialog(id, strings, Lists.newArrayList(), 0) }
        }
        wcClient?.onCosmosSignAmino = { id: Long, jsonArray: JsonArray ->
            runOnUiThread {
                onShowSignDialog(
                    makeSignBundle(
                        TYPE_COSMOS_WALLET, id, jsonArray.toString()
                    )
                )
            }
        }
        wcClient?.onCosmosSignDirect = { id: Long, jsonArray: JsonArray ->
            runOnUiThread {
                onShowSignDialog(
                    makeSignBundle(
                        TYPE_COSMOS_SIGN_DIRECT, id, jsonArray.toString()
                    )
                )
            }
        }
        wcClient?.onCosmostationSignDirectTx = { id: Long, jsonArray: JsonArray ->
            runOnUiThread {
                onShowSignDialog(
                    makeSignBundle(
                        TYPE_COSMOS_SIGN_DIRECT, id, jsonArray.toString()
                    )
                )
            }
        }
    }

    private fun processSessionRequest(wcPeerMeta: WCPeerMeta) {
        mWcPeerMeta = wcPeerMeta
        if (!isDeepLink && !isDapp) {
            onInitView(wcPeerMeta)
            if (BaseChain.EVMOS_MAIN == mBaseChain) {
                wcClient?.approveSession(
                    Lists.newArrayList(
                        WKey.generateEthAddressFromPrivateKey(chainAccountMap[mBaseChain.chain]?.let {
                            getPrivateKey(
                                it
                            )
                        })
                    ), 9001
                )
            } else {
                chainAccountMap[mBaseChain.chain]?.address?.let {
                    wcClient?.approveSession(listOf(it), 1)
                } ?: run {
                    wcClient?.approveSession(listOf(), 1)
                }
            }
        } else {
            if (BaseChain.EVMOS_MAIN == mBaseChain) {
                wcClient?.approveSession(
                    Lists.newArrayList(
                        WKey.generateEthAddressFromPrivateKey(chainAccountMap[mBaseChain.chain]?.let {
                            getPrivateKey(
                                it
                            )
                        })
                    ), 9001
                )
            } else {
                wcClient?.approveSession(Lists.newArrayList(), 1)
            }
            binding.loadingLayer.postDelayed(
                { binding.loadingLayer.visibility = View.GONE }, 2500
            )
        }
        Toast.makeText(baseContext, getString(R.string.str_wc_connected), Toast.LENGTH_SHORT).show()
        changeDappConnectStatus(true)
    }

    @Throws(Exception::class)
    private fun processEthSign(signMessage: WCEthereumSignMessage): SignatureData {
        val credentials = Credentials.create(chainAccountMap[mBaseChain.chain]?.let {
            getPrivateKey(
                it
            )
        })
        return Sign.signPrefixedMessage(signMessage.data.toByteArray(), credentials.ecKeyPair)
    }

    @Throws(InterruptedException::class, ExecutionException::class)
    private fun processEthSend(wcEthereumTransaction: WCEthereumTransaction): EthSendTransaction? {
        val rpcUrl: String = if (BaseChain.EVMOS_MAIN == mBaseChain) {
            "https://eth.bd.evmos.org:8545"
        } else {
            return null
        }
        val web3 = Web3j.build(HttpService(rpcUrl))
        val credentials = Credentials.create(chainAccountMap[mBaseChain.chain]?.let {
            getPrivateKey(
                it
            )
        })
        val ethGetTransactionCount =
            web3.ethGetTransactionCount(credentials.address, DefaultBlockParameterName.LATEST)
                .sendAsync().get()
        val nonce = ethGetTransactionCount.transactionCount
        var value = BigInteger.ZERO
        wcEthereumTransaction.value?.let {
            if (StringUtils.isNotBlank(it)) {
                value = BigInteger(it.replace("0x", ""), 16)
            }
        }
        val transaction = Transaction(
            wcEthereumTransaction.from,
            nonce,
            BigInteger.ZERO,
            BigInteger.ZERO,
            wcEthereumTransaction.to,
            value,
            wcEthereumTransaction.data
        )
        val limit = web3.ethEstimateGas(transaction).sendAsync().get()
        val rawTransaction = RawTransaction.createTransaction(
            9001,
            nonce,
            limit.amountUsed,
            wcEthereumTransaction.to,
            value,
            wcEthereumTransaction.data,
            BigInteger.valueOf(500000000L),
            BigInteger.valueOf(27500000000L)
        )
        val signedMessage = TransactionEncoder.signMessage(rawTransaction, credentials)
        val hexValue = Numeric.toHexString(signedMessage)
        return web3.ethSendRawTransaction(hexValue).sendAsync().get()
    }

    private fun moveToBackIfNeed() {
        if (isDeepLink) {
            moveTaskToBack(true)
        }
    }

    private fun makeWCAccount(): List<WCAccount> {
        return if (mBaseChain == BaseChain.KAVA_MAIN && chainAccountMap.containsKey(mBaseChain.chain)) {
            chainAccountMap[mBaseChain.chain]?.address?.let { listOf(WCAccount(459, it)) }
                ?: run { listOf() }
        } else {
            listOf()
        }
    }

    fun approveTrustRequest(id: Long, wcSignTransaction: String) {
        val wcStdSignMsg = Gson().fromJson(wcSignTransaction, StdSignMsg::class.java)
        try {
            val transactionJson = JSONObject(wcSignTransaction)
            val messagesArray = transactionJson.getJSONArray("messages")
            val msgList = Lists.newArrayList<Msg>()
            for (i in 0 until messagesArray.length()) {
                val rawMessage = messagesArray.getJSONObject(i).getJSONObject("rawJsonMessage")
                val msgModel = Msg()
                msgModel.type = rawMessage.getString("type")
                msgModel.value =
                    Gson().fromJson(rawMessage.getString("value"), Msg.Value::class.java)
                msgModel.value.amount = parseAmount(msgModel.value.amount)
                msgList.add(msgModel)
            }
            wcStdSignMsg.msgs = msgList
            val account = Account()
            account.accountNumber = wcStdSignMsg.account_number.toInt()
            account.sequenceNumber = wcStdSignMsg.sequence.toInt()
            val tx = MsgGenerator.getWcTrustBroadcaseReq(
                account,
                msgList,
                wcStdSignMsg.fee,
                wcStdSignMsg.memo,
                getKey(WDp.getChainTypeByChainId(wcStdSignMsg.chain_id).chain),
                wcStdSignMsg.chain_id
            )
            val gson = GsonBuilder().disableHtmlEscaping().create()
            val result = gson.toJson(tx)
            wcClient?.approveRequest(id, result)
            Toast.makeText(
                baseContext, getString(R.string.str_wc_request_responsed), Toast.LENGTH_SHORT
            ).show()
        } catch (e: JSONException) {
            e.printStackTrace()
        }
    }

    private fun parseAmount(amount: Any): Any {
        try {
            return Gson().fromJson(Gson().toJson(amount), Coin::class.java)
        } catch (ignored: Exception) {
        }
        try {
            return Gson().fromJson(
                Gson().toJson(amount), object : TypeToken<List<Coin?>?>() {}.type
            )
        } catch (ignored: Exception) {
        }
        return amount
    }

    fun approveCosmosRequest(id: Long, transaction: String?) {
        val transactionJson = Gson().fromJson(transaction, JsonArray::class.java)
        val signModel = WcSignModel(
            transactionJson[2].asJsonObject, getKey(
                WDp.getChainTypeByChainId(
                    transactionJson[0].asString
                ).chain
            )
        )
        wcClient?.approveRequest(id, Lists.newArrayList(signModel))
        Toast.makeText(
            baseContext, getString(R.string.str_wc_request_responsed), Toast.LENGTH_SHORT
        ).show()
        moveToBackIfNeed()
    }

    fun approveCosmosSignDirectRequest(id: Long, transaction: String?) {
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
                Base64.decode(
                    jsonObject["authInfoBytes"].asString, Base64.DEFAULT
                )
            )
            val accountNumber = jsonObject["accountNumber"].asLong
            val signDoc = SignDoc.newBuilder().setBodyBytes(txBody.toByteString())
                .setAuthInfoBytes(authInfo.toByteString()).setChainId(chainId)
                .setAccountNumber(accountNumber).build()
            val key = getKey(WDp.getChainTypeByChainId(chainId).chain)
            val signModel = WcSignDirectModel(signDoc.toByteArray(), jsonObject, key)
            wcClient?.approveRequest(id, signModel)
            Toast.makeText(
                baseContext, getString(R.string.str_wc_request_responsed), Toast.LENGTH_SHORT
            ).show()
            moveToBackIfNeed()
        } catch (e: Exception) {
            wcClient?.rejectRequest(id, "Signing error.")
            Toast.makeText(
                baseContext, getString(R.string.str_unknown_error), Toast.LENGTH_SHORT
            ).show()
            moveToBackIfNeed()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == android.R.id.home) {
            finish()
            return true
        }
        return super.onOptionsItemSelected(item)
    }

    private fun onInitView(meta: WCPeerMeta?) {
        if (isDapp) {
            return
        }
        if (meta != null) {
            if (!CollectionUtils.isEmpty(meta.icons)) {
                Picasso.get().load(meta.icons[0]).fit().placeholder(R.drawable.validator_none_img)
                    .into(binding.wcImg)
            }
            binding.wcName.text = meta.name
            binding.wcUrl.text = meta.url
            binding.wcLayer.visibility = View.VISIBLE
            binding.loadingLayer.visibility = View.GONE
        }
        binding.wcCard.setCardBackgroundColor(
            ContextCompat.getColor(
                this, mChainConfig.chainBgColor()
            )
        )
        binding.wcAddress.text = chainAccountMap[mBaseChain.chain]?.address
    }

    private fun hasAccount(chainId: String?): Boolean {
        val requestChain = WDp.getChainTypeByChainId(chainId)
        if (requestChain == null) {
            onShowNotSupportChain(chainId)
            return false
        }
        val existAccount = baseDao.onSelectAllAccountsByChainWithKey(requestChain)
        if (existAccount.isEmpty()) {
            onShowNoAccountsForChain()
            return false
        }
        return true
    }

    private fun onShowKeplrAccountDialog(id: Long, chainId: String?) {
        if (!hasAccount(chainId)) {
            wcClient?.approveRequest(id, Lists.newArrayList<Any>())
            moveToBackIfNeed()
            return
        }
        val bundle = Bundle()
        bundle.putLong("id", id)
        bundle.putString("chainName", chainId)
        if (!this.isFinishing) {
            val dialog = Dialog_Wc_Account.newInstance(bundle)
            dialog.setOnSelectListener(object : OnDialogSelectListener {
                override fun onSelect(id: Long, account: Account) {
                    chainAccountMap[WDp.getChainTypeByChainId(chainId).chain] = account
                    if (mBaseChain == null) {
                        mBaseChain = WDp.getChainTypeByChainId(chainId)
                        mChainConfig = ChainFactory.getChain(mBaseChain)
                        onInitView(mWcPeerMeta)
                    }
                    wcClient?.approveRequest(id, Lists.newArrayList(toKeplrWallet(account)))
                    moveToBackIfNeed()
                }

                override fun onCancel() {
                    wcClient?.approveRequest(id, Lists.newArrayList<Any>())
                    moveToBackIfNeed()
                }
            })
            dialog.isCancelable = false
            dialog.show(supportFragmentManager, "dialog")
        }
    }

    private fun onShowAccountDialog(
        id: Long, chains: List<String>, selectedAccounts: MutableList<Account>, index: Int
    ) {
        if (index >= chains.size) {
            val wcAccounts: MutableList<WCCosmostationAccount> = mutableListOf()
            for (account in selectedAccounts) {
                toCosmosatationAccount(account)?.let { wcAccounts.add(it) }
            }
            wcClient?.approveRequest(id, Lists.newArrayList(wcAccounts))
            moveToBackIfNeed()
            return
        }
        if (!hasAccount(chains[index])) {
            onShowAccountDialog(id, chains, selectedAccounts, index + 1)
            return
        } else if (chainAccountMap.containsKey(WDp.getChainTypeByChainId(chains[index]).chain)) {
            val account = chainAccountMap[WDp.getChainTypeByChainId(chains[index]).chain]
            account?.let { selectedAccounts.add(it) }
            onShowAccountDialog(id, chains, selectedAccounts, index + 1)
            return
        }
        val bundle = Bundle()
        bundle.putLong("id", id)
        bundle.putString("chainName", chains[index])
        if (!this.isFinishing) {
            val dialog = Dialog_Wc_Account.newInstance(bundle)
            dialog.setOnSelectListener(object : OnDialogSelectListener {
                override fun onSelect(id: Long, account: Account) {
                    chainAccountMap[WDp.getChainTypeByChainId(chains[index]).chain] = account
                    if (mBaseChain == null) {
                        mBaseChain = WDp.getChainTypeByChainId(chains[index])
                        mChainConfig = ChainFactory.getChain(mBaseChain)
                        onInitView(mWcPeerMeta)
                    }
                    selectedAccounts.add(account)
                    onShowAccountDialog(id, chains, selectedAccounts, index + 1)
                }

                override fun onCancel() {
                    onShowAccountDialog(id, chains, selectedAccounts, index + 1)
                }
            })
            dialog.isCancelable = false
            dialog.show(supportFragmentManager, "dialog$index")
        }
    }

    override fun onBackPressed() {
        if (binding.wcWebview.visibility == View.VISIBLE && binding.wcWebview.canGoBack()) {
            binding.wcWebview.goBack()
        } else {
            super.onBackPressed()
        }
    }

    private fun getKey(chainId: String): ECKey? {
        val currentAccount = chainAccountMap[chainId]
        currentAccount?.let {
            return ECKey.fromPrivate(BigInteger(getPrivateKey(it), 16))
        } ?: run {
            return null
        }
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
                WUtil.getWalletName(this, account),
                "secp256k1",
                it.pubKey,
                WKey.generateTenderAddressBytesFromPrivateKey(key.privateKeyAsHex),
                account.address,
                false
            )
        }
    }

    private fun toCosmosatationAccount(account: Account): WCCosmostationAccount? {
        val key = getKey(account.baseChain)
        return key?.let {
            WCCosmostationAccount(
                WUtil.getWalletName(this, account),
                "secp256k1",
                it.pubKey,
                WKey.generateTenderAddressBytesFromPrivateKey(key.privateKeyAsHex),
                account.address
            )
        }
    }

    private fun onShowSignDialog(bundle: Bundle) {
        if (!this.isFinishing) {
            val wcRawDataDialog =
                Dialog_Wc_Raw_Data.newInstance(bundle, object : WcSignRawDataListener {
                    override fun sign(type: Int, id: Long, transaction: String) {
                        if (type == TYPE_TRUST_WALLET) {
                            approveTrustRequest(id, transaction)
                        } else if (type == TYPE_COSMOS_WALLET) {
                            approveCosmosRequest(id, transaction)
                        } else if (type == TYPE_COSMOS_SIGN_DIRECT) {
                            approveCosmosSignDirectRequest(id, transaction)
                        }
                    }

                    override fun reject(id: Long) {
                        rejectSignRequest(id)
                    }
                })
            wcRawDataDialog.isCancelable = false
            wcRawDataDialog.show(supportFragmentManager, "dialog")
        }
    }

    private fun onShowEvmosSignDialog(bundle: Bundle) {
        val dialog =
            Dialog_Wc_Raw_Data_Evmos.newInstance(bundle, object : WcEvmosSignRawDataListener {
                override fun sign(
                    type: Int, id: Long, wcEthereumTransaction: String, signMessage: String
                ) {
                    if (type == TYPE_ETH_SIGN_MESSAGE) {
                        Thread {
                            try {
                                val signResult = mSignMessage?.let { processEthSign(it) }
                                wcClient?.approveRequest(id, signResult)
                            } catch (e: Exception) {
                                wcClient?.rejectRequest(
                                    id, getString(R.string.str_unknown_error)
                                )
                            }
                        }.start()
                    } else if (type == TYPE_ETH_SIGN_TRANSACTION) {
                        Thread {
                            try {
                                mWcEthereumTransaction?.let {
                                    val result = processEthSend(it)
                                    result?.let { ethSendResult ->
                                        wcClient?.approveRequest(
                                            id, ethSendResult.transactionHash
                                        )
                                    } ?: run {
                                        wcClient?.rejectRequest(
                                            id, getString(R.string.str_unknown_error)
                                        )
                                    }
                                } ?: run {
                                    wcClient?.rejectRequest(
                                        id, getString(R.string.str_unknown_error)
                                    )
                                }
                            } catch (e: InterruptedException) {
                                wcClient?.rejectRequest(
                                    id, getString(R.string.str_unknown_error)
                                )
                            } catch (e: ExecutionException) {
                                wcClient?.rejectRequest(
                                    id, getString(R.string.str_unknown_error)
                                )
                            }
                        }.start()
                    }
                }

                override fun reject(id: Long) {
                    rejectSignRequest(id)
                }
            })
        dialog.isCancelable = false
        dialog.show(supportFragmentManager, "dialog")
    }

    private fun rejectSignRequest(id: Long) {
        wcClient?.rejectRequest(id, getString(R.string.str_cancel))
        moveToBackIfNeed()
    }

    private fun onShowNoAccountsForChain() {
        CommonAlertDialog.showSingleButton(
            this,
            getString(R.string.str_error_not_support_chain_title),
            getString(R.string.str_error_not_support_chain_msg),
            getString(R.string.str_ok),
            null,
            false
        )
    }

    private fun onShowNotSupportChain(chainId: String?) {
        CommonAlertDialog.showSingleButton(
            this,
            getString(R.string.str_error_not_support_chain_title),
            String.format(getString(R.string.str_error_not_support_msg), chainId),
            getString(R.string.str_ok),
            null,
            false
        )
    }

    private fun makeSignBundle(type: Int, id: Long, transaction: String): Bundle {
        val bundle = Bundle()
        bundle.putString("transaction", transaction)
        bundle.putString("url", mWcPeerMeta?.url)
        bundle.putLong("id", id)
        bundle.putInt("type", type)
        return bundle
    }

    private fun makeEvmosSignBundle(
        type: Int, id: Long, transaction: WCEthereumTransaction?, message: WCEthereumSignMessage?
    ): Bundle {
        val bundle = Bundle()
        if (transaction == null) {
            bundle.putString("message", message?.data)
        } else {
            bundle.putString("transaction", transaction.data)
            bundle.putString("address", transaction.from)
        }
        bundle.putString("url", mWcPeerMeta?.url)
        bundle.putLong("id", id)
        bundle.putInt("type", type)
        return bundle
    }

    private fun onKeplrEnable(id: Long, strings: List<String>) {
        if (hasAccount(strings[0])) {
            wcClient?.approveRequest(id, strings)
            moveToBackIfNeed()
        } else {
            binding.loadingLayer.visibility = View.GONE
        }
    }

    override fun onNewIntent(intent: Intent) {
        super.onNewIntent(intent)
        intent.data?.let { data ->
            if (fromScheme(intent) && WC_URL_SCHEME_HOST_WC == data.host) {
                if (wcSession != null && wcClient != null && wcClient?.session != null && wcClient?.isConnected == true) {
                    return
                }
                mWcURL = data.query
                binding.loadingLayer.visibility = View.VISIBLE
                initWalletConnect()
            } else if (fromScheme(intent) && (WC_URL_SCHEME_HOST_DAPP == data.host || WC_URL_SCHEME_HOST_DAPP_INTERNAL == data.host)) {
                if (binding.wcWebview.visibility != View.VISIBLE) {
                    Toast.makeText(
                        this@ConnectWalletActivity, R.string.str_unknown_error, Toast.LENGTH_SHORT
                    ).show()
                    return
                }
                data.query?.let { url -> binding.wcWebview.loadUrl(url) }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        wcClient?.let {
            if (it.isConnected) {
                it.killSession()
            } else {
                it.disconnect()
            }
        }
    }

    private val connectWalletResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == RESULT_OK) {
                initWalletConnect()
            } else {
                finish()
            }
        }

    companion object {
        const val TYPE_TRUST_WALLET = 1
        const val TYPE_COSMOS_WALLET = 2
        const val TYPE_ETH_SIGN_MESSAGE = 3
        const val TYPE_ETH_SIGN_TRANSACTION = 4
        const val TYPE_COSMOS_SIGN_DIRECT = 5
        const val WC_URL_SCHEME_HOST_WC = "wc"
        const val WC_URL_SCHEME_HOST_DAPP = "dapp"
        const val WC_URL_SCHEME_HOST_DAPP_INTERNAL = "internaldapp"
        const val WC_URL_SCHEME_COSMOSTATION = "cosmostation"
        const val INTENT_KEY_WC_URL = "wcUrl"
        const val INTENT_KEY_DAPP_URL = "dappUrl"
    }
}