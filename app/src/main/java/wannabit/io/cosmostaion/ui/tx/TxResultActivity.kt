package wannabit.io.cosmostaion.ui.tx

import android.app.AlertDialog
import android.content.Context
import android.content.Intent
import android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP
import android.content.Intent.FLAG_ACTIVITY_NEW_TASK
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import androidx.lifecycle.ViewModelProvider
import com.cosmos.tx.v1beta1.ServiceGrpc.newStub
import com.cosmos.tx.v1beta1.ServiceProto
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.web3j.protocol.Web3j
import org.web3j.protocol.core.methods.response.TransactionReceipt
import org.web3j.protocol.http.HttpService
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.historyToMintscan
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.databinding.ActivityTxResultBinding
import wannabit.io.cosmostaion.databinding.DialogWaitBinding
import wannabit.io.cosmostaion.ui.main.MainActivity
import wannabit.io.cosmostaion.ui.main.setting.wallet.book.SetAddressFragment
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModelProviderFactory
import java.io.IOException
import java.util.concurrent.TimeUnit
import kotlin.random.Random

class TxResultActivity : BaseActivity() {

    private lateinit var binding: ActivityTxResultBinding

    private var selectedChain: CosmosLine? = null
    private var isSuccess: Boolean = false
    private var txHash: String = ""
    private var errorMsg: String = ""
    private var fetchCnt = 15
    private var txResponse: ServiceProto.GetTxResponse? = null

    private var txResultType: TxResultType? = TxResultType.COSMOS

    private var evmRecipient: TransactionReceipt? = null

    private lateinit var walletViewModel: WalletViewModel

    // addressBook
    private var recipientChain: CosmosLine? = null
    private var recipientAddress: String = ""
    private var memo: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTxResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initViewModel()
        initView()
        setUpClickAction()
    }

    private fun initViewModel() {
        val walletRepository = WalletRepositoryImpl()
        val walletViewModelProviderFactory = WalletViewModelProviderFactory(walletRepository)
        walletViewModel =
            ViewModelProvider(this, walletViewModelProviderFactory)[WalletViewModel::class.java]
    }

    private fun initView() {
        binding.apply {
            selectedChain = BaseData.baseAccount?.sortedDisplayCosmosLines()?.firstOrNull {
                it.tag == intent.getStringExtra(
                    "selectedChain"
                ).toString()
            }
            isSuccess = intent.getBooleanExtra("isSuccess", false)
            errorMsg = intent.getStringExtra("errorMsg") ?: ""
            txHash = intent.getStringExtra("txHash") ?: ""
            txResultType = TxResultType.valueOf(
                intent.getStringExtra("txResultType") ?: TxResultType.COSMOS.toString()
            )

            recipientChain = allCosmosLines().firstOrNull {
                it.tag == intent.getStringExtra("recipientChain").toString()
            }
            recipientAddress = intent.getStringExtra("recipientAddress") ?: ""
            memo = intent.getStringExtra("memo") ?: ""

            btnConfirm.updateButtonView(true)
            if (selectedChain is ChainBinanceBeacon || (selectedChain is ChainOkt60 && txResultType == TxResultType.COSMOS)) {
                if (txHash.isNotEmpty()) {
                    updateView()
                } else {
                    loading.visibility = View.GONE
                    btnConfirm.updateButtonView(true)
                    failLayout.visibility = View.VISIBLE
                    failMsg.visibleOrGone(errorMsg.isNotEmpty())
                    failMsg.text = errorMsg
                }

            } else {
                if (isSuccess) {
                    if (txResultType == TxResultType.EVM) {
                        loadEvmTx()
                    } else {
                        loadHistoryTx()
                    }
                } else {
                    showError()
                }
            }
            initQuotes()
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        if (txResultType == TxResultType.SKIP) {
            startMainActivity(1)
        } else {
            finish()
        }
    }

    private fun updateView() {
        binding.apply {
            if (txResultType == TxResultType.COSMOS || txResultType == TxResultType.SKIP) {
                if (isSuccess) {
                    if (selectedChain is ChainOkt60) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            loading.visibility = View.GONE
                            successLayout.visibility = View.VISIBLE
                            showAddressBook()
                        }, 3000)

                    } else {
                        loading.visibility = View.GONE
                        successLayout.visibility = View.VISIBLE
                        showAddressBook()
                    }

                } else {
                    loading.visibility = View.GONE
                    failLayout.visibility = View.VISIBLE
                    failMsg.visibleOrGone(errorMsg.isNotEmpty())
                    failMsg.text = errorMsg
                }

            } else {
                loading.visibility = View.GONE
                walletViewModel.evmTxHash(selectedChain?.apiName, txHash)
                if (evmRecipient?.isStatusOK == true) {
                    successLayout.visibility = View.VISIBLE
                } else {
                    failLayout.visibility = View.VISIBLE
                    failMsg.text = evmRecipient?.logsBloom.toString()
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            viewSuccessMintscan.setOnClickListener {
                if (txResultType == TxResultType.EVM) {
                    walletViewModel.evmTxHashResult.observe(this@TxResultActivity) { txHash ->
                        historyToMintscan(selectedChain, txHash)
                    }

                    walletViewModel.evmTxHashErrorMessage.observe(this@TxResultActivity) {
                        viewSuccessMintscan.isEnabled = false
                    }

                } else if (selectedChain is ChainBinanceBeacon || selectedChain is ChainOkt60) {
                    historyToMintscan(selectedChain, txHash)
                } else {
                    historyToMintscan(selectedChain, txResponse?.txResponse?.txhash)
                }
            }

            viewFailMintscan.setOnClickListener {
                if (txResultType == TxResultType.EVM) {
                    walletViewModel.evmTxHashResult.observe(this@TxResultActivity) { txHash ->
                        historyToMintscan(selectedChain, txHash)
                    }

                    walletViewModel.evmTxHashErrorMessage.observe(this@TxResultActivity) {
                        viewSuccessMintscan.isEnabled = false
                    }

                } else if (selectedChain is ChainBinanceBeacon || selectedChain is ChainOkt60) {
                    historyToMintscan(selectedChain, txHash)
                } else {
                    historyToMintscan(selectedChain, txResponse?.txResponse?.txhash)
                }
            }

            btnConfirm.setOnClickListener {
                when (txResultType) {
                    TxResultType.SKIP -> {
                        startMainActivity(1)
                    }
                    TxResultType.EVM -> {
                        finish()
                        BaseData.baseAccount?.let { account ->
                            ApplicationViewModel.shared.loadAllTokenBalance(selectedChain!!, account.id)
                        }
                    }
                    else -> {
                        finish()
                        BaseData.baseAccount?.let { account ->
                            ApplicationViewModel.shared.loadChainData(selectedChain!!, account.id)
                        }
                    }
                }
            }
        }
    }

    private fun loadHistoryTx() {
        CoroutineScope(Dispatchers.IO).launch {
            selectedChain?.let { line ->
                val stub = newStub(getChannel(line))
                val request = ServiceProto.GetTxRequest.newBuilder().setHash(txHash).build()

                stub.getTx(request, object : StreamObserver<ServiceProto.GetTxResponse> {
                    override fun onNext(value: ServiceProto.GetTxResponse?) {
                        txResponse = value
                        Handler(Looper.getMainLooper()).postDelayed({
                            updateView()
                        }, 0)
                    }

                    override fun onError(t: Throwable?) {
                        fetchCnt -= 1
                        if (isSuccess && fetchCnt > 0) {
                            getChannel(line).shutdown()
                            getChannel(line).awaitTermination(6L, TimeUnit.SECONDS)
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
            }
        }
    }

    private fun loadEvmTx() {
        CoroutineScope(Dispatchers.IO).launch {
            val rpcUrl = selectedChain?.rpcUrl
            val web3j = Web3j.build(HttpService(rpcUrl))
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

    private fun initQuotes() {
        val num = Random.nextInt(resources.getStringArray(R.array.quotes).size)
        val quote = resources.getStringArray(R.array.quotes)[num].split("—")
        binding.quoteMsg.text = quote[0]
        binding.quoteAuthor.text = "- " + quote[1] + " -".uppercase()
    }

    private fun showMoreWait() {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DialogWaitBinding.inflate(inflater)
        val alertDialog =
            AlertDialog.Builder(this, R.style.AppTheme_AlertDialogTheme).setView(binding.root)

        val dialog = alertDialog.create()
        dialog.show()

        binding.btnClose.setOnClickListener {
            if (txResultType == TxResultType.SKIP) {
                startMainActivity(1)
            } else {
                finish()
            }
            dialog.dismiss()
        }

        binding.btnWait.setOnClickListener {
            fetchCnt = 10
            loadHistoryTx()
            dialog.dismiss()
        }
    }

    private fun showAddressBook() {
        if (recipientChain != null && recipientAddress.isNotEmpty()) {
            CoroutineScope(Dispatchers.IO).launch {
                AppDatabase.getInstance().addressBookDao().selectAll()
                    .firstOrNull { it.address == recipientAddress && it.chainName == recipientChain?.name }
                    ?.let { existed ->
                        if (existed.memo != memo) {
                            SetAddressFragment(existed, null, "", memo).show(
                                supportFragmentManager, SetAddressFragment::class.java.name
                            )
                            return@launch
                        }
                    }

                if (AppDatabase.getInstance().refAddressDao().selectAll()
                        .none { it.dpAddress == recipientAddress }
                ) {
                    withContext(Dispatchers.Main) {
                        SetAddressFragment(null, recipientChain, recipientAddress, memo).show(
                            supportFragmentManager, SetAddressFragment::class.java.name
                        )
                    }
                    return@launch
                }
            }
        }
    }

    private fun showError() {
        binding.apply {
            loading.visibility = View.GONE
            failLayout.visibility = View.VISIBLE
            failMsg.visibleOrGone(errorMsg.isNotEmpty())
            failMsg.text = errorMsg
            btnConfirm.updateButtonView(true)
        }
    }

    private fun startMainActivity(page: Int) {
        Intent(this@TxResultActivity, MainActivity::class.java).apply {
            addFlags(FLAG_ACTIVITY_CLEAR_TOP or FLAG_ACTIVITY_NEW_TASK)
            putExtra("page", page)
            startActivity(this)
            ApplicationViewModel.shared.txRecreate()
        }
    }
}

enum class TxResultType { COSMOS, EVM, SKIP }