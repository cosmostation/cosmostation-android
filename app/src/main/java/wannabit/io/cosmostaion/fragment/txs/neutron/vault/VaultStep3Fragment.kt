package wannabit.io.cosmostaion.fragment.txs.neutron.vault

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.lifecycle.ViewModelProvider
import cosmos.base.abci.v1beta1.Abci
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.PasswordCheckActivity
import wannabit.io.cosmostaion.activities.TxDetailgRPCActivity
import wannabit.io.cosmostaion.activities.txs.neutron.vault.VaultActivity
import wannabit.io.cosmostaion.base.BaseBroadCastActivity
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.databinding.FragmentVaultStep3Binding
import wannabit.io.cosmostaion.model.factory.neutron.VaultViewModelProviderFactory
import wannabit.io.cosmostaion.model.repository.neutron.VaultRepository
import wannabit.io.cosmostaion.model.viewModel.neutron.VaultViewModel
import wannabit.io.cosmostaion.network.req.neutron.Bond
import wannabit.io.cosmostaion.network.req.neutron.BondReq
import wannabit.io.cosmostaion.network.req.neutron.Unbond
import wannabit.io.cosmostaion.network.req.neutron.UnbondReq
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.WKey

class VaultStep3Fragment : BaseFragment() {

    private var _binding: FragmentVaultStep3Binding? = null
    private val binding get() = _binding!!

    private lateinit var vaultViewModel: VaultViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentVaultStep3Binding.inflate(layoutInflater, container, false)
        val vaultViewModelFactory = VaultViewModelProviderFactory(VaultRepository())
        vaultViewModel = ViewModelProvider(this, vaultViewModelFactory)[VaultViewModel::class.java]
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    override fun onRefreshTab() {
        getSActivity()?.let {
            binding.apply {
                WDp.setDpCoin(requireContext(), baseDao, baseActivity.mChainConfig, it.mTxFee.amount[0], feeAmountSymbol, feeAmount)
                WDp.setDpCoin(context, baseDao, baseActivity.mChainConfig, it.mAmount, vaultAmountSymbol, vaultAmount)
                memo.text = it.mTxMemo
            }
        }
    }

    private fun onClick() {
        binding.apply {
            btnBefore.setOnClickListener { getSActivity()?.onBeforeStep() }
            btnConfirm.setOnClickListener { onStartLiquid() }
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
            var req: Any? = null
            if (it.mTxType == BaseConstant.CONST_PW_TX_VAULT_DEPOSIT) {
                req = BondReq(Bond())
            } else if (it.mTxType == BaseConstant.CONST_PW_TX_VAULT_WITHDRAW) {
                req = UnbondReq(Unbond(it.mAmount.amount))
            }

            val broadcastTxRequest = Signer.getGrpcContractReq(
                WKey.onAuthResponse(it.mBaseChain, it.mAccount), req, it.mAccount.address, it.mContractAddress, it.mAmount,
                it.mTxFee, it.mTxMemo, WKey.getECKey(baseApplication, it.mAccount), baseDao.chainIdGrpc, it.mAccount.customPath, it.mBaseChain, it.mTxType)

            vaultViewModel.broadCastTx(it.mBaseChain, broadcastTxRequest)
        }

        vaultViewModel.txResponse.observe(requireActivity()) {
            intentInfo(it)
        }
    }

    private fun intentInfo(txResponse: Abci.TxResponse) {
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

    private fun getSActivity(): VaultActivity? {
        return baseActivity as? VaultActivity
    }
}