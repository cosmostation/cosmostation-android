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
import com.cosmos.tx.v1beta1.ServiceGrpc
import com.cosmos.tx.v1beta1.ServiceProto
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.historyToMintscan
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.repository.address.AddressRepositoryImpl
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.databinding.ActivityTxResultBinding
import wannabit.io.cosmostaion.databinding.DialogWaitBinding
import wannabit.io.cosmostaion.ui.main.setting.wallet.book.SetAddressFragment
import wannabit.io.cosmostaion.ui.tx.step.TransferStyle
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.address.AddressBookViewModel
import wannabit.io.cosmostaion.ui.viewmodel.address.AddressBookViewModelProviderFactory
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModelProviderFactory
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
    private var txResponse: ServiceProto.GetTxResponse? = null

    private lateinit var walletViewModel: WalletViewModel
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
        val walletRepository = WalletRepositoryImpl()
        val walletViewModelProviderFactory = WalletViewModelProviderFactory(walletRepository)
        walletViewModel =
            ViewModelProvider(this, walletViewModelProviderFactory)[WalletViewModel::class.java]

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
            BaseData.baseAccount?.sortedDisplayCosmosLines()?.firstOrNull {
                it.tag == intent.getStringExtra(
                    "fromChainTag"
                ).toString()
            }?.let { fromChainWithTag ->
                fromChain = fromChainWithTag

            } ?: run {
                BaseData.baseAccount?.sortedDisplayEvmLines()?.firstOrNull {
                    it.tag == intent.getStringExtra("fromChainTag").toString()
                }
            }?.let { fromChainWithTag ->
                fromChain = fromChainWithTag
            }

            BaseData.baseAccount?.allCosmosLineChains?.firstOrNull {
                it.tag == intent.getStringExtra(
                    "toChainTag"
                ).toString()
            }?.let { toChainWithTag ->
                toChain = toChainWithTag

            } ?: run {
                BaseData.baseAccount?.allEvmLineChains?.firstOrNull {
                    it.tag == intent.getStringExtra(
                        "toChainTag"
                    ).toString()
                }?.let { toChainWithTag ->
                    toChain = toChainWithTag
                }
            }

            isSuccess = intent.getBooleanExtra("isSuccess", false)
            errorMsg = intent.getStringExtra("errorMsg") ?: ""
            txHash = intent.getStringExtra("txHash") ?: ""

            toAddress = intent.getStringExtra("recipientAddress") ?: ""
            toMemo = intent.getStringExtra("memo") ?: ""

            if (transferStyle == TransferStyle.WEB3_STYLE) {

            } else {
                if (txHash.isNotEmpty()) {
                    loadHistoryTx()
                } else {
                    loading.visibility = View.GONE
                    failLayout.visibility = View.VISIBLE
                    failMsg.visibleOrGone(errorMsg.isNotEmpty())
                    failMsg.text = errorMsg
                }
            }
            initQuotes()
        }
    }

    private fun updateView() {
        binding.apply {
            if (transferStyle == TransferStyle.WEB3_STYLE) {

            } else {
                if (isSuccess) {
                    loading.visibility = View.GONE
                    successLayout.visibility = View.VISIBLE
                    showAddressBook()

                } else {
                    loading.visibility = View.GONE
                    failLayout.visibility = View.VISIBLE
                    failMsg.visibleOrGone(errorMsg.isNotEmpty())
                    failMsg.text = errorMsg
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            viewSuccessMintscan.setOnClickListener {
                if (transferStyle == TransferStyle.WEB3_STYLE) {

                } else {
                    historyToMintscan(fromChain as CosmosLine, txResponse?.txResponse?.txhash)
                }
            }

            viewFailMintscan.setOnClickListener {
                if (transferStyle == TransferStyle.WEB3_STYLE) {

                } else {
                    historyToMintscan(fromChain as CosmosLine, txResponse?.txResponse?.txhash)
                }
            }

            btnConfirm.setOnClickListener {
                BaseData.baseAccount?.let { account ->
                    if (transferStyle == TransferStyle.WEB3_STYLE) {

                    } else {
                        ApplicationViewModel.shared.loadChainData(
                            fromChain as CosmosLine, account.id, false
                        )
                    }
                }
                finish()
            }
        }
    }

    private fun loadHistoryTx() {
        lifecycleScope.launch(Dispatchers.IO) {
            (fromChain as CosmosLine).apply {
                val stub = ServiceGrpc.newStub(getChannel(this))
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
                            getChannel(this@apply).shutdown()
                            getChannel(this@apply).awaitTermination(6L, TimeUnit.SECONDS)
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

    private fun showAddressBook() {
        lifecycleScope.launch(Dispatchers.IO) {
            AppDatabase.getInstance().addressBookDao().selectAll()
                .firstOrNull { it.address == toAddress && it.chainName == toChain.name }
                ?.let { existed ->
                    if (existed.memo != toMemo) {
                        withContext(Dispatchers.Main) {
                            SetAddressFragment(existed, null, "", toMemo).show(
                                supportFragmentManager, SetAddressFragment::class.java.name
                            )
                        }
                    }
                    return@launch
                }

//            if (AppDatabase.getInstance().refAddressDao().selectAll()
//                    .none { it.dpAddress == toAddress }
//            ) {
//                withContext(Dispatchers.Main) {
//                    SetAddressFragment(null, toChain, toAddress, toMemo).show(
//                        supportFragmentManager, SetAddressFragment::class.java.name
//                    )
//                }
//                return@launch
//            }
        }
//            AppDatabase.getInstance().addressBookDao().selectAll()
//                .firstOrNull { it.address == toAddress && it.chainName == toChain.name }
//                ?.let { existed ->
//                    if (existed.memo != toMemo) {
//                        withContext(Dispatchers.Main) {
//                            SetAddressFragment(existed, null, "", toMemo).show(
//                                supportFragmentManager, SetAddressFragment::class.java.name
//                            )
//                        }
//                    }
//                    return@launch
//                }

//            if (AppDatabase.getInstance().refAddressDao().selectAll().none { it.dpAddress == toAddress }) {
//                withContext(Dispatchers.Main) {
//                    SetAddressFragment(null, toChain, toAddress, toMemo).show(
//                        supportFragmentManager, SetAddressFragment::class.java.name
//                    )
//                }
//            }
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
            failMsg.visibleOrGone(errorMsg.isNotEmpty())
            failMsg.text = errorMsg
            btnConfirm.updateButtonView(true)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
    }
}