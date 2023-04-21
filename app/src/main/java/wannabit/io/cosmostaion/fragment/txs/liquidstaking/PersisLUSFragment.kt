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
import wannabit.io.cosmostaion.activities.txs.liquidstaking.PersisLiquidActivity
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.databinding.FragmentPersisLusBinding
import wannabit.io.cosmostaion.databinding.ItemToastMsgBinding
import wannabit.io.cosmostaion.model.viewModel.PersisViewModel
import wannabit.io.cosmostaion.utils.WDp
import java.math.BigDecimal
import java.math.RoundingMode

class PersisLUSFragment : BaseFragment() {

    private var _binding: FragmentPersisLusBinding? = null
    private val binding get() = _binding!!

    private val persisViewModel: PersisViewModel by activityViewModels()

    private var mInputCoinDenom: String? = null
    private var mAvailableMaxAmount: BigDecimal = BigDecimal.ZERO

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View {
        _binding = FragmentPersisLusBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        persisViewModel.cValue.observe(viewLifecycleOwner, Observer { cValue ->
            onUpdateView(cValue)
        })
        onClickLiquidRedeem()
    }

    override fun onRefreshTab() {
        persisViewModel.cValue.value?.let {
            onUpdateView(it)
        }
    }

    fun onUpdateView(cValue: String) {
        mInputCoinDenom = "stk/uatom"
        val mOutputCoinDenom = "ibc/C8A74ABBE2AF892E15680D916A7C22130585CE5704F9B17A10F184A90D53BECA"
        val inputDecimal = WDp.getDenomDecimal(baseDao, baseActivity.mChainConfig, mInputCoinDenom)

        WDp.setDpSymbolImg(baseDao, baseActivity.mChainConfig, mInputCoinDenom, binding.imgInputCoin)
        WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, mInputCoinDenom, binding.txtInputCoin)

        mAvailableMaxAmount = baseDao.getAvailable(mInputCoinDenom)
        binding.inputAmount.text = WDp.getDpAmount2(requireActivity(), mAvailableMaxAmount, inputDecimal, inputDecimal)

        binding.inputsRate.text = WDp.getDpAmount2(BigDecimal.ONE, 0, 6)
        WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, mInputCoinDenom, binding.inputsRateSymbol)
        val rate = BigDecimal(cValue).movePointLeft(18)
        val redeemFee = BigDecimal("0.005")
        val outAmount = BigDecimal.ONE.divide(rate, 12, RoundingMode.DOWN).subtract(redeemFee)

        binding.outputsRate.text = WDp.getDpAmount2(outAmount, 0, 6)
        WDp.setDpSymbol(requireContext(), baseDao, baseActivity.mChainConfig, mOutputCoinDenom, binding.outputsRateSymbol)
    }

    fun onClickLiquidRedeem() {
        binding.btnStartRedeem.setOnClickListener {
            if (!baseActivity.mAccount.hasPrivateKey) {
                baseActivity.onInsertKeyDialog()
            }
            if (mAvailableMaxAmount <= BigDecimal.ZERO) {
                val bindingLayout = ItemToastMsgBinding.inflate(layoutInflater)
                bindingLayout.toastMsg.text = getString(R.string.error_not_enough_to_balance)
                val toast = Toast(requireContext())
                toast.view = bindingLayout.root
                toast.show()
            }

            Intent(requireContext(), PersisLiquidActivity::class.java).apply {
                putExtra("inputDenom", mInputCoinDenom)
                putExtra("txType", BaseConstant.CONST_PW_TX_PERSIS_LIQUID_REDEEM)
                startActivity(this)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}