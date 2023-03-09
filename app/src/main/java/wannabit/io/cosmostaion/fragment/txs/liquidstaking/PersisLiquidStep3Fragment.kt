package wannabit.io.cosmostaion.fragment.txs.liquidstaking

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.Observer
import cosmos.base.abci.v1beta1.Abci.TxResponse
import cosmos.tx.v1beta1.ServiceOuterClass
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.PasswordCheckActivity
import wannabit.io.cosmostaion.activities.TxDetailgRPCActivity
import wannabit.io.cosmostaion.activities.txs.liquidstaking.PersisLiquidActivity
import wannabit.io.cosmostaion.base.BaseBroadCastActivity
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.databinding.FragmentLiquidStep3Binding
import wannabit.io.cosmostaion.task.TaskResult
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.WKey
import wannabit.io.cosmostaion.utils.WLog

class PersisLiquidStep3Fragment : BaseFragment() {

    private var _binding: FragmentLiquidStep3Binding? = null
    private val binding get() = _binding!!

    private lateinit var persisLUSViewModel: PersisViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentLiquidStep3Binding.inflate(layoutInflater, container, false)
        persisLUSViewModel = PersisViewModel()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    override fun onRefreshTab() {
        getSActivity()?.let {
            WDp.setDpCoin(requireContext(), baseDao, baseActivity.mChainConfig, it.mTxFee.amount[0], binding.lsFeeAmountSymbol, binding.lsFeeAmount)

            WDp.setDpCoin(context, baseDao, baseActivity.mChainConfig, it.mSwapInCoin, binding.lsInAmountSymbol, binding.lsInAmount)
            WDp.setDpCoin(context, baseDao, baseActivity.mChainConfig, it.mSwapOutCoin, binding.lsOutAmountSymbol, binding.lsOutAmount)
            binding.memo.text = getSActivity()?.mTxMemo
        }
    }

    private fun onClick() {
        binding.btnBefore.setOnClickListener { getSActivity()?.onBeforeStep() }

        binding.btnConfirm.setOnClickListener {
            onStartLiquid()
        }
    }

    private fun onStartLiquid() {
        if (baseDao.isAutoPass) {
            onBroadCastTx()
        } else {
            Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                activityResultLauncher.launch(this)
                baseActivity.overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out)
            }
        }
    }

    var activityResultLauncher = registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
        if (result.resultCode == BaseBroadCastActivity.RESULT_OK) {
            getSActivity()?.onShowWaitDialog()
            onBroadCastTx()
        }
    }

    private fun onBroadCastTx() {
        getSActivity()?.let {
            val broadcastTxRequest = Signer.getGrpcPersisLiquidStakeReq(WKey.onAuthResponse(it.mBaseChain, it.mAccount), it.mAccount.address, it.mSwapInCoin,
                it.mTxFee, it.mTxMemo, WKey.getECKey(baseApplication, it.mAccount), baseDao.chainIdGrpc, it.mAccount.customPath, it.mBaseChain)
            persisLUSViewModel.broadCastTx(it.mBaseChain, broadcastTxRequest)
        }
        persisLUSViewModel.txResponse.observe(this, Observer { response ->
            intentInfo(response)
        })
    }

    private fun intentInfo(txResponse: TxResponse) {
        Intent(requireContext(), TxDetailgRPCActivity::class.java).apply {
            if (txResponse.code > 0) {
                putExtra("isSuccess", false)
            } else {
                putExtra("isSuccess", true)
            }
            putExtra("errorCode", txResponse.code)
            putExtra("errorMsg", txResponse.rawLog)

            val hash = txResponse.txhash
            if (!TextUtils.isEmpty(hash)) putExtra("txHash", hash)
            startActivity(this)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun getSActivity(): PersisLiquidActivity? {
        return baseActivity as? PersisLiquidActivity
    }
}