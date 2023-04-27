package wannabit.io.cosmostaion.fragment.txs.neutron

import android.content.Intent
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import cosmos.base.abci.v1beta1.Abci
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.PasswordCheckActivity
import wannabit.io.cosmostaion.activities.TxDetailgRPCActivity
import wannabit.io.cosmostaion.activities.txs.neutron.DaoProposalActivity
import wannabit.io.cosmostaion.base.BaseBroadCastActivity
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.databinding.FragmentDaoVoteStep3Binding
import wannabit.io.cosmostaion.model.viewModel.NeutronViewModel
import wannabit.io.cosmostaion.network.req.neutron.Vote
import wannabit.io.cosmostaion.network.req.neutron.VoteReq
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.WKey

class DaoVoteStep3Fragment : BaseFragment() {

    private var _binding: FragmentDaoVoteStep3Binding? = null
    private val binding get() = _binding!!

    private val neutronViewModel: NeutronViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentDaoVoteStep3Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    override fun onRefreshTab() {
        with(binding) {
            getSActivity()?.let {
                WDp.setDpCoin(requireContext(), baseDao, baseActivity.mChainConfig, it.mTxFee.amount[0], feeSymbol, feeAmount)
                myOpinion.text = "# " + it.mProposal_id + " - " + it.mOpinion
                memo.text = it.mTxMemo
            }
        }
    }

    private fun onClick() {
        with(binding) {
            btnBefore.setOnClickListener { getSActivity()?.onBeforeStep() }

            btnConfirm.setOnClickListener { onStartVote() }
        }
    }

    private fun onStartVote() {
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
            val broadcastTxRequest = Signer.getGrpcContractReq(
                WKey.onAuthResponse(it.mBaseChain, it.mAccount), VoteReq(Vote(it.mProposal_id, it.mOpinion)), it.mAccount.address, BaseConstant.NEUTRON_NTRN_DAO_SINGLE_TESTNET_ADDRESS, it.mAmount,
                it.mTxFee, it.mTxMemo, WKey.getECKey(baseApplication, it.mAccount), baseDao.chainIdGrpc, it.mAccount.customPath, it.mBaseChain, it.mTxType)
            neutronViewModel.broadCastTx(it.mBaseChain, broadcastTxRequest)
        }

        neutronViewModel.txResponse.observe(viewLifecycleOwner) {
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

    private fun getSActivity(): DaoProposalActivity? {
        return baseActivity as? DaoProposalActivity
    }
}