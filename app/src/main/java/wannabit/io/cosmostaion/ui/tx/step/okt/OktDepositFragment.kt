package wannabit.io.cosmostaion.ui.tx.step.okt

import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_BASE_FEE
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_GECKO_ID
import wannabit.io.cosmostaion.common.BaseConstant.BASE_GAS_AMOUNT
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.model.req.LCoin
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.data.model.res.OktToken
import wannabit.io.cosmostaion.databinding.FragmentOktDepositBinding
import wannabit.io.cosmostaion.ui.option.tx.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.LegacyInsertAmountFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoListener
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import java.math.BigDecimal
import java.math.RoundingMode

class OktDepositFragment : BaseTxFragment() {

    private var _binding: FragmentOktDepositBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: ChainOkt60

    private var oktTokenInfo: OktToken? = null

    private var toDepositAmount = ""
    private var txMemo = ""

    private var availableAmount = BigDecimal.ZERO
    private var gasAmount = BigDecimal(BASE_GAS_AMOUNT)
    private var gasFee = BigDecimal(OKT_BASE_FEE)

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: CosmosLine): OktDepositFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = OktDepositFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOktDepositBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initFee()
        setUpClickAction()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedChain", ChainOkt60::class.java)
                    ?.let { selectedChain = it }

            } else {
                (arguments?.getParcelable("selectedChain") as? ChainOkt60)?.let {
                    selectedChain = it
                }
            }

            listOf(
                oktDepositView, memoView, feeView
            ).forEach { it.setBackgroundResource(R.drawable.cell_bg) }

            selectedChain.oktTokenInfo?.data?.firstOrNull { it.symbol == selectedChain.stakeDenom }
                ?.let { tokenInfo ->
                    oktTokenInfo = tokenInfo
                    val originalSymbol = tokenInfo.originalSymbol
                    tokenImg.setTokenImg(selectedChain.assetImg(originalSymbol))
                    tokenName.text = originalSymbol.uppercase()

                    val available = selectedChain.lcdBalanceAmount(selectedChain.stakeDenom)
                    availableAmount = if (available > gasFee) {
                        available.subtract(gasFee)
                    } else {
                        BigDecimal.ZERO
                    }
                }
        }
    }

    private fun initFee() {
        binding.apply {
            selectedChain.stakeDenom?.let { stakeDenom ->
                feeTokenImg.setTokenImg(selectedChain.assetImg(stakeDenom))
                feeToken.text = stakeDenom.uppercase()

                selectedChain.oktDepositedInfo?.validatorAddress?.size?.let { existCnt ->
                    gasAmount = BigDecimal(BASE_GAS_AMOUNT)
                    gasFee = BigDecimal(OKT_BASE_FEE)
                    if (existCnt > 10) {
                        val multiplier = if (existCnt > 20) 4 else 3
                        gasAmount = gasAmount.multiply(BigDecimal(multiplier.toString()))
                        gasFee = gasFee.multiply(BigDecimal(multiplier.toString()))
                    }
                }

                val price = BaseData.getPrice(OKT_GECKO_ID)
                val value = price.multiply(gasFee).setScale(6, RoundingMode.DOWN)
                feeAmount.text = formatAmount(gasFee.toPlainString(), 18)
                feeDenom.text = stakeDenom.uppercase()
                feeValue.text = formatAssetValue(value)
            }
        }
    }

    private fun updateAmountView(toAmount: String) {
        binding.apply {
            tabMsgTxt.visibility = View.GONE
            amountLayout.visibility = View.VISIBLE
            toDepositAmount = toAmount

            val dpAmount = BigDecimal(toAmount).setScale(18, RoundingMode.DOWN)
            depositAmount.text = formatAmount(dpAmount.toPlainString(), 18)

            val price = BaseData.getPrice(OKT_GECKO_ID)
            val toSendValue = price.multiply(dpAmount).setScale(6, RoundingMode.DOWN)
            depositValue.text = formatAssetValue(toSendValue)
        }
        txValidate()
    }

    private fun updateMemoView(memo: String) {
        binding.apply {
            txMemo = memo
            if (txMemo.isEmpty()) {
                tabMemoMsg.text = getString(R.string.str_tap_for_add_memo_msg)
                tabMemoMsg.setTextColor(
                    ContextCompat.getColorStateList(
                        requireContext(), R.color.color_base03
                    )
                )
            } else {
                tabMemoMsg.text = txMemo
                tabMemoMsg.setTextColor(
                    ContextCompat.getColorStateList(
                        requireContext(), R.color.color_base01
                    )
                )
            }
        }
        txValidate()
    }

    private fun setUpClickAction() {
        binding.apply {
            oktDepositView.setOnClickListener {
                handleOneClickWithDelay(
                    LegacyInsertAmountFragment(selectedChain,
                        null,
                        oktTokenInfo,
                        availableAmount,
                        toDepositAmount,
                        object : AmountSelectListener {
                            override fun select(toAmount: String) {
                                updateAmountView(toAmount)
                            }
                        })
                )
            }

            memoView.setOnClickListener {
                handleOneClickWithDelay(
                    MemoFragment(txMemo, object : MemoListener {
                        override fun memo(memo: String) {
                            updateMemoView(memo)
                        }
                    })
                )
            }

            btnDeposit.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    depositResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                    )
                }
            }
        }
    }

    private fun handleOneClickWithDelay(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        if (isClickable) {
            isClickable = false

            bottomSheetDialogFragment.show(
                requireActivity().supportFragmentManager, bottomSheetDialogFragment::class.java.name
            )

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 300)
        }
    }

    private fun txValidate() {
        binding.apply {
            if (toDepositAmount.isEmpty()) {
                return
            }
            if (BigDecimal(toDepositAmount) == BigDecimal.ZERO) {
                return
            }
            binding.btnDeposit.updateButtonView(true)
        }
    }

    private val depositResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE

                selectedChain.stakeDenom?.let { stakeDenom ->
                    val depositCoin = LCoin(stakeDenom, toDepositAmount)
                    val gasCoin = LCoin(stakeDenom, gasFee.toString())
                    val fee = LFee(gasAmount.toString(), mutableListOf(gasCoin))

                    selectedChain.address?.let { address ->
                        val oktDepositMsg = Signer.oktDepositMsg(address, depositCoin)
                        txViewModel.broadcastOktTx(oktDepositMsg, fee, txMemo, selectedChain)
                    }
                }
            }
        }

    private fun setUpBroadcast() {
        txViewModel.broadcastOktTx.observe(viewLifecycleOwner) { txResponse ->
            Intent(requireContext(), TxResultActivity::class.java).apply {
                if (txResponse != null) {
                    if (txResponse.txhash != null) {
                        putExtra("txHash", txResponse.txhash)
                        putExtra("isSuccess", true)
                    }
                    if (txResponse.code != null) {
                        putExtra("errorMsg", txResponse.rawLog)
                        putExtra("isSuccess", false)
                    }
                } else {
                    putExtra("isSuccess", false)
                }
                putExtra("selectedChain", selectedChain.tag)
                startActivity(this)
            }
            dismiss()
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}