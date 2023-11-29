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
import com.cosmos.tx.v1beta1.ServiceGrpc.newStub
import com.cosmos.tx.v1beta1.ServiceProto
import io.grpc.ManagedChannel
import io.grpc.ManagedChannelBuilder
import io.grpc.stub.StreamObserver
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.common.BaseActivity
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.historyToMintscan
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.databinding.ActivityTxResultBinding
import wannabit.io.cosmostaion.databinding.DialogWaitBinding
import wannabit.io.cosmostaion.ui.main.MainActivity
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import java.util.concurrent.TimeUnit

class TxResultActivity : BaseActivity() {

    private lateinit var binding: ActivityTxResultBinding

    private var selectedChain: CosmosLine? = null
    private var isSuccess: Boolean = false
    private var txHash: String = ""
    private var errorMsg: String = ""
    private var fetchCnt = 10
    private var txResponse: ServiceProto.GetTxResponse? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTxResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        initView()
        clickAction()
    }

    private fun initView() {
        selectedChain = BaseData.baseAccount?.displayCosmosLineChains?.firstOrNull {
            it.tag == intent.getStringExtra(
                "selectedChain"
            ).toString()
        }
        isSuccess = intent.getBooleanExtra("isSuccess", false)
        errorMsg = intent.getStringExtra("errorMsg") ?: ""
        txHash = intent.getStringExtra("txHash") ?: ""

        if (selectedChain is ChainBinanceBeacon) {
            if (txHash.isNotEmpty()) {
                updateView()
            }

        } else {
            if (isSuccess) {
                fetchTx()
            } else {
                showError()
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        startMainActivity()
    }

    private fun updateView() {
        binding.apply {
            loading.visibility = View.GONE
            btnConfirm.updateButtonView(true)
            if (isSuccess) {
                successLayout.visibility = View.VISIBLE
            } else {
                failLayout.visibility = View.VISIBLE
                failMsg.text = errorMsg
            }
        }
    }

    private fun clickAction() {
        binding.apply {
            viewSuccessMintscan.setOnClickListener {
                if (selectedChain is ChainBinanceBeacon) {
                    historyToMintscan(selectedChain, txHash)
                } else {
                    historyToMintscan(selectedChain, txResponse?.txResponse?.txhash)
                }
            }

            viewFailMintscan.setOnClickListener {
                if (selectedChain is ChainBinanceBeacon) {
                    historyToMintscan(selectedChain, txHash)
                } else {
                    historyToMintscan(selectedChain, txResponse?.txResponse?.txhash)
                }
            }

            btnConfirm.setOnClickListener {
                startMainActivity()
            }
        }
    }

    private fun fetchTx() {
        CoroutineScope(Dispatchers.IO).launch {
            val stub = newStub(getChannel())
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
                        getChannel()?.shutdown()
                        getChannel()?.awaitTermination(6L, TimeUnit.SECONDS)
                        Handler(Looper.getMainLooper()).postDelayed({
                            fetchTx()
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

    private fun getChannel(): ManagedChannel? {
        selectedChain?.let {
            return ManagedChannelBuilder.forAddress(it.grpcHost, it.grpcPort).useTransportSecurity().build()
        }
        return null
    }

    private fun showMoreWait() {
        val inflater = getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val binding = DialogWaitBinding.inflate(inflater)
        val alertDialog = AlertDialog.Builder(this, R.style.AppTheme_AlertDialogTheme)
            .setView(binding.root)

        val dialog = alertDialog.create()
        dialog.show()

        binding.btnClose.setOnClickListener {
            startMainActivity()
            dialog.dismiss()
        }

        binding.btnWait.setOnClickListener {
            fetchCnt = 10
            fetchTx()
            dialog.dismiss()
        }
    }

    private fun showError() {
        binding.apply {
            loading.visibility = View.GONE
            failLayout.visibility = View.VISIBLE
            failMsg.text = errorMsg
            btnConfirm.updateButtonView(true)
        }
    }

    private fun startMainActivity() {
        val intent = Intent(this@TxResultActivity, MainActivity::class.java)
        intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP or FLAG_ACTIVITY_NEW_TASK)
        startActivity(intent)
        ApplicationViewModel.shared.txRecreate()
    }
}