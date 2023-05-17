package wannabit.io.cosmostaion.fragment.txs.neutron.defi.swap

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.fragment.app.viewModels
import dagger.hilt.android.AndroidEntryPoint
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.PasswordCheckActivity
import wannabit.io.cosmostaion.activities.txs.neutron.defi.NeutronSwapActivity
import wannabit.io.cosmostaion.base.BaseBroadCastActivity
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.databinding.FragmentNeutronSwapStep3Binding
import wannabit.io.cosmostaion.model.viewModel.neutron.AstroportViewModel
import wannabit.io.cosmostaion.utils.WDp
import wannabit.io.cosmostaion.utils.WKey
import wannabit.io.cosmostaion.utils.getTxResultIntent

@AndroidEntryPoint
class NeutronSwapStep3Fragment : BaseFragment() {

    private var _binding: FragmentNeutronSwapStep3Binding? = null
    private val binding get() = _binding!!

    private val astroViewModel: AstroportViewModel by viewModels()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = FragmentNeutronSwapStep3Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        onClick()
    }

    override fun onRefreshTab() {
        getSActivity()?.let {
            binding.apply {
                WDp.setDpCoin(requireContext(), baseDao, baseActivity.mChainConfig, it.mTxFee.amount[0], swapFeeAmountSymbol, swapFeeAmount)

                WDp.setDpCoin(context, baseDao, baseActivity.mChainConfig, it.inputCoin?.denom, it.mSwapInAmount, swapInSymbol, swapInAmount)
                WDp.setDpCoin(context, baseDao, baseActivity.mChainConfig, it.outputCoin?.denom, it.mSwapOutAmount, swapOutSymbol, swapOutAmount)
                binding.memo.text = getSActivity()?.mTxMemo
            }
        }
    }

    private fun onClick() {
        binding.apply {
            btnBefore.setOnClickListener { getSActivity()?.onBeforeStep() }
            btnConfirm.setOnClickListener { onStartSwap() }
        }
    }

    private fun onStartSwap() {
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
            val broadcastTxRequest = Signer.getGrpcContractSwapReq(
                WKey.onAuthResponse(it.mBaseChain, it.mAccount), it.mAccount.address, it.selectedPool, it.mInputPair, it.mSwapInAmount, it.mBeliefPrice,
                it.mTxFee, it.mTxMemo, WKey.getECKey(baseApplication, it.mAccount), baseDao.chainIdGrpc, it.mAccount.customPath, it.mBaseChain)

            astroViewModel.broadCastTx(it.mBaseChain, broadcastTxRequest)
        }

        astroViewModel.txResponse.observe(viewLifecycleOwner) {
            getTxResultIntent(requireContext(), it)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun getSActivity(): NeutronSwapActivity? {
        return baseActivity as? NeutronSwapActivity?
    }
}