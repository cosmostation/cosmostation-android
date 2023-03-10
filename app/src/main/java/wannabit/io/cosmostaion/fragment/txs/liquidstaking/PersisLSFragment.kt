package wannabit.io.cosmostaion.fragment.txs.liquidstaking

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.txs.liquidstaking.PersisLSActivity
import wannabit.io.cosmostaion.activities.txs.liquidstaking.PersisLiquidActivity
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentPersisLsBinding
import wannabit.io.cosmostaion.databinding.ItemToastMsgBinding
import wannabit.io.cosmostaion.utils.WDp
import java.math.BigDecimal

class PersisLSFragment : BaseFragment() {

    private var _binding: FragmentPersisLsBinding? = null
    private val binding get() = _binding!!

    private val persisViewModel: PersisViewModel by activityViewModels()

    private var mInputCoinDenom: String? = null
    private var mOutputCoinDenom: String? = null
    private var mAvailableMaxAmount: BigDecimal = BigDecimal.ZERO

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPersisLsBinding.inflate(layoutInflater, container, false)
        getSActivity()?.onShowWaitDialog()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        persisViewModel.cValue.observe(viewLifecycleOwner, Observer { cValue ->
            getSActivity()?.onHideWaitDialog()
            onUpdateView(cValue)
        })
        onClickLiquidStake()
    }

    override fun onRefreshTab() {
        super.onRefreshTab()
        persisViewModel.cValue.value?.let {
            onUpdateView(it)
        }
    }

    fun onUpdateView(cValue: String) {
        mInputCoinDenom = "ibc/C8A74ABBE2AF892E15680D916A7C22130585CE5704F9B17A10F184A90D53BECA"
        mOutputCoinDenom = "stk/uatom"
        val inputDecimal = WDp.getDenomDecimal(baseDao, baseActivity.mChainConfig, mInputCoinDenom)

        WDp.setDpSymbolImg(baseDao, baseActivity.mChainConfig, mInputCoinDenom, binding.imgInputCoin)
        WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, mInputCoinDenom, binding.txtInputCoin)
        WDp.setDpSymbolImg(baseDao, baseActivity.mChainConfig, mOutputCoinDenom, binding.imgOutputCoin)
        WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, mOutputCoinDenom, binding.txtOutputCoin)

        mAvailableMaxAmount = baseDao.getAvailable(mInputCoinDenom)
        binding.inputAmount.text = WDp.getDpAmount2(requireActivity(), mAvailableMaxAmount, inputDecimal, inputDecimal)

        binding.inputsRate.text = WDp.getDpAmount2(BigDecimal.ONE, 0, 6)
        WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, mInputCoinDenom, binding.inputsRateSymbol)
        binding.outputsRate.text = WDp.getDpAmount2(BigDecimal(cValue), 18, 6)
        WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, mOutputCoinDenom, binding.outputsRateSymbol)
    }

    fun onClickLiquidStake() {
        binding.btnStartStake.setOnClickListener {
            if (!baseActivity.mAccount.hasPrivateKey) {
                baseActivity.onInsertKeyDialog()
            }
            if (mAvailableMaxAmount <= BigDecimal.ZERO) {
                val bindingLayout = ItemToastMsgBinding.inflate(layoutInflater)
                bindingLayout.toastMsg.text = getString(R.string.error_not_enough_liquid_stake)
                val toast = Toast(requireContext())
                toast.view = bindingLayout.root
                toast.show()
                return@setOnClickListener
            }

            Intent(requireContext(), PersisLiquidActivity::class.java).apply {
                putExtra("inputDenom", mInputCoinDenom)
                putExtra("txType", BaseConstant.CONST_PW_TX_PERSIS_LIQUID_STAKING)
                startActivity(this)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }

    private fun getSActivity(): PersisLSActivity? {
        return baseActivity as PersisLSActivity
    }
}