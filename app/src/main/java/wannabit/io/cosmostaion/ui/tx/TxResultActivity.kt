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
import androidx.lifecycle.lifecycleScope
import com.cosmos.tx.v1beta1.ServiceGrpc.newStub
import com.cosmos.tx.v1beta1.ServiceProto
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.historyToMintscan
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.databinding.ActivityTxResultBinding
import wannabit.io.cosmostaion.databinding.DialogWaitBinding
import wannabit.io.cosmostaion.ui.main.MainActivity
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModelProviderFactory
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

    private lateinit var walletViewModel: WalletViewModel

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

            btnConfirm.updateButtonView(true)
            if (selectedChain is ChainBinanceBeacon || selectedChain is ChainOkt996Keccak) {
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
                    loadHistoryTx()
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
            startMainActivity()
        } else {
            finish()
        }
    }

    private fun updateView() {
        binding.apply {
            if (isSuccess) {
                if (selectedChain is ChainOkt60) {
                    Handler(Looper.getMainLooper()).postDelayed({
                        loading.visibility = View.GONE
                        successLayout.visibility = View.VISIBLE
                    }, 3000)

                } else {
                    loading.visibility = View.GONE
                    successLayout.visibility = View.VISIBLE
                }

            } else {
                loading.visibility = View.GONE
                failLayout.visibility = View.VISIBLE
                failMsg.visibleOrGone(errorMsg.isNotEmpty())
                failMsg.text = errorMsg
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            viewSuccessMintscan.setOnClickListener {
                if (selectedChain is ChainBinanceBeacon || selectedChain is ChainOkt996Keccak) {
                    historyToMintscan(selectedChain, txHash)
                } else {
                    historyToMintscan(selectedChain, txResponse?.txResponse?.txhash)
                }
            }

            viewFailMintscan.setOnClickListener {
                if (selectedChain is ChainBinanceBeacon || selectedChain is ChainOkt996Keccak) {
                    historyToMintscan(selectedChain, txHash)
                } else {
                    historyToMintscan(selectedChain, txResponse?.txResponse?.txhash)
                }
            }

            btnConfirm.setOnClickListener {
                when (txResultType) {
                    TxResultType.SKIP -> {
                        startMainActivity()
                    }

                    else -> {
                        finish()
                        BaseData.baseAccount?.let { account ->
                            selectedChain?.let { chain ->
                                ApplicationViewModel.shared.loadChainData(
                                    chain, account.id, false
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun loadHistoryTx() {
        lifecycleScope.launch(Dispatchers.IO) {
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
                startMainActivity()
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

    private fun showError() {
        binding.apply {
            loading.visibility = View.GONE
            failLayout.visibility = View.VISIBLE
            failMsg.visibleOrGone(errorMsg.isNotEmpty())
            failMsg.text = errorMsg
            btnConfirm.updateButtonView(true)
        }
    }

    private fun startMainActivity() {
        Intent(this@TxResultActivity, MainActivity::class.java).apply {
            addFlags(FLAG_ACTIVITY_CLEAR_TOP or FLAG_ACTIVITY_NEW_TASK)
            startActivity(this)
            ApplicationViewModel.shared.txRecreate(1)
        }
    }
}

enum class TxResultType { COSMOS, SKIP }