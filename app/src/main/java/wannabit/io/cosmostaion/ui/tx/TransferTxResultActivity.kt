package wannabit.io.cosmostaion.ui.tx

import android.app.AlertDialog
import android.content.Context
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.cosmos.base.abci.v1beta1.AbciProto.TxResponse
import com.cosmos.tx.v1beta1.ServiceGrpc
import com.cosmos.tx.v1beta1.ServiceProto
import com.cosmos.tx.v1beta1.ServiceProto.GetTxResponse
import com.google.gson.Gson
import com.google.gson.JsonObject
import com.google.gson.JsonParser
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.protocol.http.HttpService
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosEndPointType
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin84
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.historyToMintscan
import wannabit.io.cosmostaion.common.jsonRpcResponse
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.api.RetrofitInstance
import wannabit.io.cosmostaion.data.model.req.JsonRpcRequest
import wannabit.io.cosmostaion.data.repository.address.AddressRepositoryImpl
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.data.viewmodel.address.AddressBookViewModel
import wannabit.io.cosmostaion.data.viewmodel.address.AddressBookViewModelProviderFactory
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.databinding.ActivityTxResultBinding
import wannabit.io.cosmostaion.databinding.DialogWaitBinding
import wannabit.io.cosmostaion.ui.main.setting.wallet.book.AddressBookType
import wannabit.io.cosmostaion.ui.main.setting.wallet.book.SetAddressFragment
import wannabit.io.cosmostaion.ui.tx.genTx.TransferStyle
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class TransferTxResultActivity : BaseActivity() {

    private lateinit var binding: ActivityTxResultBinding

    private var transferStyle: TransferStyle? = TransferStyle.COSMOS_STYLE
    private lateinit var fromChain: BaseChain
    private lateinit var toChain: BaseChain
    private var toAddress: String = ""
    private var toMemo: String = ""

    private var isSuccess: Boolean = false
    private var txHash: String = ""
    private var errorMsg: String = ""
    private var fetchCnt = 15
    private var txResponse: GetTxResponse? = null

    private var evmRecipient: TransactionReceipt? = null

    private var suiResult: JsonObject? = null

    private lateinit var addressBookViewModel: AddressBookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTxResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initView()
        setUpClickAction()
    }

    private fun initViewModel() {
        val addressRepository = AddressRepositoryImpl()
        val addressBookViewModelProviderFactory =
            AddressBookViewModelProviderFactory(addressRepository)
        addressBookViewModel = ViewModelProvider(
            this, addressBookViewModelProviderFactory
        )[AddressBookViewModel::class.java]
    }

    private fun initView() {
        binding.apply {
            btnConfirm.updateButtonView(true)
            BaseData.baseAccount?.sortedDisplayChains()?.firstOrNull {
                it.tag == intent.getStringExtra(
                    "fromChainTag"
                ).toString()
            }?.let { fromChainWithTag ->
                fromChain = fromChainWithTag
            }

            BaseData.baseAccount?.allChains?.firstOrNull {
                it.tag == intent.getStringExtra(
                    "toChainTag"
                ).toString()
            }?.let { toChainWithTag ->
                toChain = toChainWithTag
            }

            isSuccess = intent.getBooleanExtra("isSuccess", false)
            errorMsg = intent.getStringExtra("errorMsg") ?: ""
            txHash = intent.getStringExtra("txHash") ?: ""

            toAddress = intent.getStringExtra("recipientAddress") ?: ""
            toMemo = intent.getStringExtra("memo") ?: ""

            intent.getStringExtra("suiResult")?.let { intentData ->
                suiResult = JsonParser.parseString(intentData).asJsonObject ?: JsonObject()
            }

            transferStyle = enumValues<TransferStyle>()[intent.getIntExtra("transferStyle", -1)]

            if (transferStyle == TransferStyle.WEB3_STYLE) {
                if (txHash.isNotEmpty()) {
                    loadEvmTx()
                } else {
                    showError()
                }

            } else if (transferStyle == TransferStyle.SUI_STYLE) {
                if (txHash.isNotEmpty()) {
                    updateView()
                } else {
                    showError()
                }

            } else if (transferStyle == TransferStyle.BIT_COIN_STYLE) {
                if (isSuccess) {
                    if (txHash.isNotEmpty()) {
                        loadBitTx()
                    } else {
                        showError()
                    }
                } else {
                    showError()
                }

            } else {
                if (isSuccess) {
                    if (txHash.isNotEmpty()) {
                        loadHistoryTx()
                    } else {
                        showError()
                    }
                } else {
                    showError()
                }
            }
            initQuotes()
        }
    }

    private fun updateView() {
        binding.apply {
            if (transferStyle == TransferStyle.WEB3_STYLE) {
                loading.visibility = View.GONE
                if (evmRecipient?.isStatusOK == true) {
                    successLayout.visibility = View.VISIBLE
                    successHash.text = txHash
                    showAddressBook()

                } else {
                    failLayout.visibility = View.VISIBLE
                    failHash.text = evmRecipient?.logsBloom.toString()
                    viewFailMintscan.visibility = View.GONE
                }

            } else if (transferStyle == TransferStyle.SUI_STYLE) {
                if (isSuccess) {
                    loading.visibility = View.GONE
                    successLayout.visibility = View.VISIBLE
                    successHash.text = txHash
                    showAddressBook()

                } else {
                    showError()
                }

            } else {
                if (isSuccess && txResponse?.txResponse?.code == 0) {
                    loading.visibility = View.GONE
                    successLayout.visibility = View.VISIBLE
                    successHash.text = txHash
                    showAddressBook()

                } else {
                    showError()
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            viewSuccessMintscan.setOnClickListener {
                when (transferStyle) {
                    TransferStyle.WEB3_STYLE -> {
                        historyToMintscan(fromChain, txHash)
                    }

                    TransferStyle.SUI_STYLE, TransferStyle.BIT_COIN_STYLE -> {
                        historyToMintscan(fromChain, txHash)
                    }

                    else -> {
                        historyToMintscan(fromChain, txResponse?.txResponse?.txhash)
                    }
                }
            }

            viewFailMintscan.setOnClickListener {
                when (transferStyle) {
                    TransferStyle.WEB3_STYLE -> {
                        historyToMintscan(fromChain, txHash)
                    }

                    TransferStyle.SUI_STYLE -> {
                        historyToMintscan(fromChain, txHash)
                    }

                    else -> {
                        historyToMintscan(fromChain, txResponse?.txResponse?.txhash)
                    }
                }
            }

            btnConfirm.setOnClickListener {
                BaseData.baseAccount?.let { account ->
                    ApplicationViewModel.shared.loadChainData(
                        fromChain, account.id, false
                    )
                }
                finish()
            }
        }
    }

    private fun loadHistoryTx() {
        lifecycleScope.launch(Dispatchers.IO) {
            fromChain.apply {
                if (cosmosFetcher?.endPointType(this) == CosmosEndPointType.USE_RPC) {
                    val txStatusRequest = JsonRpcRequest(
                        method = "tx", params = listOf(txHash)
                    )
                    val txStatusResponse = jsonRpcResponse(mainUrl, txStatusRequest)
                    if (txStatusResponse.isSuccessful) {
                        val txStatusJsonObject = Gson().fromJson(
                            txStatusResponse.body?.string(), JsonObject::class.java
                        )

                        if (!txStatusJsonObject.has("error")) {
                            val txResult =
                                txStatusJsonObject["result"].asJsonObject["tx_result"].asJsonObject["ResponseBase"].asJsonObject
                            val txResultResponse = if (txResult["Error"].isJsonNull) {
                                TxResponse.newBuilder().setTxhash(txHash).setCode(0).setRawLog("")
                                    .build()

                            } else {
                                errorMsg = txResult["Error"].asJsonObject["value"].asString ?: ""
                                TxResponse.newBuilder().setTxhash(txHash).setCode(-1)
                                    .setRawLog(errorMsg).build()
                            }
                            txResponse =
                                GetTxResponse.newBuilder().setTxResponse(txResultResponse).build()
                            Handler(Looper.getMainLooper()).postDelayed({
                                updateView()
                            }, 0)

                        } else {
                            fetchCnt -= 1
                            if (isSuccess && fetchCnt > 0) {
                                Handler(Looper.getMainLooper()).postDelayed({
                                    loadHistoryTx()
                                }, 6000)

                            } else {
                                runOnUiThread {
                                    showMoreWait()
                                }
                            }
                        }

                    } else {
                        fetchCnt -= 1
                        if (isSuccess && fetchCnt > 0) {
                            Handler(Looper.getMainLooper()).postDelayed({
                                loadHistoryTx()
                            }, 6000)

                        } else {
                            runOnUiThread {
                                showMoreWait()
                            }
                        }
                    }

                } else if (cosmosFetcher?.endPointType(this) == CosmosEndPointType.USE_GRPC) {
                    val channel = fromChain.cosmosFetcher?.getChannel()
                    val stub = ServiceGrpc.newStub(channel)
                    val request = ServiceProto.GetTxRequest.newBuilder().setHash(txHash).build()

                    stub.getTx(request, object : StreamObserver<GetTxResponse> {
                        override fun onNext(value: GetTxResponse?) {
                            txResponse = value
                            Handler(Looper.getMainLooper()).postDelayed({
                                updateView()
                            }, 0)
                        }

                        override fun onError(t: Throwable?) {
                            fetchCnt -= 1
                            if (isSuccess && fetchCnt > 0) {
                                channel?.shutdown()
                                channel?.awaitTermination(6L, TimeUnit.SECONDS)
                                Handler(Looper.getMainLooper()).postDelayed({
                                    loadHistoryTx()
                                }, 6000)

                            } else {
                                runOnUiThread {
                                    showMoreWait()
                                }
                            }
                        }

                        override fun onCompleted() {}
                    })

                } else {
                    val response = RetrofitInstance.lcdApi(fromChain).lcdTxInfo(txHash)
                    if (response.isSuccessful && response.body()
                            ?.get("tx_response")?.isJsonNull == false && response.body()
                            ?.get("tx_response")?.asJsonObject?.isJsonNull == false
                    ) {
                        val result = response.body()?.get("tx_response")?.asJsonObject
                        val txResultResponse =
                            TxResponse.newBuilder().setTxhash(result?.get("txhash")?.asString)
                                .setCode(result?.get("code")?.asInt ?: 0)
                                .setRawLog(result?.get("raw_log")?.asString).build()
                        txResponse =
                            GetTxResponse.newBuilder().setTxResponse(txResultResponse).build()
                        Handler(Looper.getMainLooper()).postDelayed({
                            updateView()
                        }, 0)

                    } else {
                        fetchCnt -= 1
                        if (isSuccess && fetchCnt > 0) {
                            Handler(Looper.getMainLooper()).postDelayed({
                                loadHistoryTx()
                            }, 6000)

                        } else {
                            runOnUiThread {
                                showMoreWait()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun loadEvmTx() {
        lifecycleScope.launch(Dispatchers.IO) {
            val web3j = Web3j.build(
                HttpService(
                    fromChain.evmRpcFetcher?.getEvmRpc() ?: fromChain.evmRpcURL
                )
            )
            try {
                val receiptTx = web3j.ethGetTransactionReceipt(txHash).send()
                if (receiptTx.transactionReceipt.isPresent) {
                    evmRecipient = receiptTx.transactionReceipt.get()
                }
                if (evmRecipient == null) {
                    fetchCnt -= 1
                    Handler(Looper.getMainLooper()).postDelayed({
                        loadEvmTx()
                    }, 6000)
                } else {
                    runOnUiThread {
                        updateView()
                    }
                }

            } catch (e: IOException) {
                fetchCnt -= 1
                if (isSuccess && fetchCnt > 0) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        loadEvmTx()
                    }, 6000)

                } else {
                    runOnUiThread {
                        showMoreWait()
                    }
                }
            }
        }
    }

    private fun loadBitTx() {
        lifecycleScope.launch(Dispatchers.IO) {
            try {
                val response = RetrofitInstance.bitApi(fromChain as ChainBitCoin84).bitTx(txHash)
                if (response.isSuccessful) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        binding.apply {
                            loading.visibility = View.GONE
                            successLayout.visibility = View.VISIBLE
                            successHash.text = txHash
                            showAddressBook()
                        }
                    }, 0)

                } else {
                    fetchCnt -= 1
                    if (isSuccess && fetchCnt > 0) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            loadBitTx()
                        }, 6000)

                    } else {
                        runOnUiThread {
                            showMoreWait()
                        }
                    }
                }

            } catch (e: IOException) {
                fetchCnt -= 1
                if (isSuccess && fetchCnt > 0) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        loadBitTx()
                    }, 6000)

                } else {
                    runOnUiThread {
                        showMoreWait()
                    }
                }
            }
        }
    }

    private fun initQuotes() {
        val num = Random.nextInt(resources.getStringArray(R.array.quotes).size)
        val quote = resources.getStringArray(R.array.quotes)[num].split("—")
        binding.quoteMsg.text = quote[0]
        binding.quoteAuthor.text = "- " + quote[1] + " -".uppercase()
    }

    private fun showAddressBook() {
        lifecycleScope.launch(Dispatchers.IO) {
            AppDatabase.getInstance().addressBookDao().selectAll()
                .firstOrNull { it.address == toAddress }?.let { existed ->
                    if (existed.memo != toMemo) {
                        withContext(Dispatchers.Main) {
                            SetAddressFragment.newInstance(
                                existed, null, "", toMemo, AddressBookType.AfterTxEdit
                            ).show(
                                supportFragmentManager, SetAddressFragment::class.java.name
                            )
                        }
                    }
                    return@launch

                } ?: run {
                if (AppDatabase.getInstance().refAddressDao().selectAll()
                        .none { it.dpAddress == toAddress || it.evmAddress == toAddress }
                ) {
                    withContext(Dispatchers.Main) {
                        SetAddressFragment.newInstance(
                            null, toChain, toAddress, toMemo, AddressBookType.AfterTxNew
                        ).show(
                            supportFragmentManager, SetAddressFragment::class.java.name
                        )
                    }
                }
            }
        }
    }

    private fun showMoreWait() {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DialogWaitBinding.inflate(inflater)
        val alertDialog =
            AlertDialog.Builder(this, R.style.AppTheme_AlertDialogTheme).setView(binding.root)

        val dialog = alertDialog.create()
        dialog.show()

        binding.btnClose.setOnClickListener {
            finish()
            dialog.dismiss()
        }

        binding.btnWait.setOnClickListener {
            fetchCnt = 10
            loadHistoryTx()
            dialog.dismiss()
        }
    }

    private fun showError() {
        binding.apply {
            loading.visibility = View.GONE
            failLayout.visibility = View.VISIBLE

            if (transferStyle == TransferStyle.SUI_STYLE) {
                val errorMsg =
                    suiResult?.get("result")?.asJsonObject?.get("effects")?.asJsonObject?.get("status")?.asJsonObject?.get(
                        "error"
                    )?.asString
                failHash.visibleOrGone(errorMsg?.isNotEmpty() == true)
                failHash.text = errorMsg

            } else {
                failHash.visibleOrGone(errorMsg.isNotEmpty())
                failHash.text = errorMsg
            }

            if (txHash.isNotEmpty()) {
                viewFailMintscan.visibility = View.VISIBLE
            } else {
                viewFailMintscan.visibility = View.GONE
            }
            btnConfirm.updateButtonView(true)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}