package wannabit.io.cosmostaion.fragment.txs.authz.grantee

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.activityViewModels
import com.google.common.collect.Lists
import cosmos.base.abci.v1beta1.Abci
import org.apache.commons.lang3.StringUtils
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.PasswordCheckActivity
import wannabit.io.cosmostaion.activities.TxDetailgRPCActivity
import wannabit.io.cosmostaion.activities.txs.authz.AuthzRevokeActivity
import wannabit.io.cosmostaion.base.BaseBroadCastActivity
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.databinding.FragmentRevokeStep3Binding
import wannabit.io.cosmostaion.model.viewModel.authz.AuthzViewModel
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.WKey
import wannabit.io.cosmostaion.utils.setAuthzType

class RevokeStep3Fragment : BaseFragment() {

    private var _binding: FragmentRevokeStep3Binding? = null
    private val binding get() = _binding!!

    private val authzViewModel: AuthzViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentRevokeStep3Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    override fun onRefreshTab() {
        getSActivity()?.let { activity ->
            binding.apply {
                WDp.setDpCoin(requireContext(), baseDao, baseActivity.mChainConfig, activity.mTxFee.amount[0], txFeeDenom, txFeeAmount)
                val texts: MutableList<String?> = Lists.newArrayList()
                activity.mGrantees.forEach { texts.add(String.format("%s \n( %s )", it.grantee, setAuthzType(it))) }
                revokeGrantee.text = StringUtils.join(texts, "\n\n")
                memo.text = activity.mTxMemo
            }
        }
    }

    private fun onClick() {
        binding.apply {
            btnBefore.setOnClickListener { getSActivity()?.onBeforeStep() }
            btnConfirm.setOnClickListener { onStartRevoke() }
        }
    }

    private fun onStartRevoke() {
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
            val broadcastTxRequest = Signer.getGrpcAuthzRevokeReq(WKey.onAuthResponse(it.mBaseChain, it.mAccount), it.mGrantees,
                it.mTxFee, it.mTxMemo, WKey.getECKey(baseApplication, it.mAccount), baseDao.chainIdGrpc, it.mAccount.customPath, it.mBaseChain)
            authzViewModel.broadCastTx(it.mBaseChain, broadcastTxRequest)
        }

        authzViewModel.txResponse.observe(viewLifecycleOwner) { response ->
            intentInfo(response)
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

    private fun getSActivity(): AuthzRevokeActivity? {
        return baseActivity as? AuthzRevokeActivity
    }
}