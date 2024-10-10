package wannabit.io.cosmostaion.ui.tx.genTx.okt

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
import com.google.gson.Gson
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.OktFetcher
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_BASE_FEE
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_GECKO_ID
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.BaseConstant.BASE_GAS_AMOUNT
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.sign.Signer
import wannabit.io.cosmostaion.data.model.req.LCoin
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.data.model.res.OktToken
import wannabit.io.cosmostaion.databinding.FragmentOktDepositBinding
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.option.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.tx.option.general.LegacyInsertAmountFragment
import wannabit.io.cosmostaion.ui.tx.option.general.MemoFragment
import wannabit.io.cosmostaion.ui.tx.option.general.MemoListener
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import java.math.BigDecimal
import java.math.RoundingMode

class OktDepositFragment : BaseTxFragment() {

    private var _binding: FragmentOktDepositBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private var oktTokenInfo: OktToken? = null

    private var toDepositAmount = ""
    private var txMemo = ""

    private var availableAmount = BigDecimal.ZERO
    private var gasAmount = BigDecimal(BASE_GAS_AMOUNT)
    private var gasFee = BigDecimal(OKT_BASE_FEE)

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): OktDepositFragment {
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
                arguments?.getParcelable("selectedChain", BaseChain::class.java)
                    ?.let { selectedChain = it }

            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
            }

            listOf(
                oktDepositView, memoView, feeView
            ).forEach { it.setBackgroundResource(R.drawable.cell_bg) }

            when (selectedChain) {
                is ChainOkt996Keccak -> initData(
                    selectedChain, (selectedChain as ChainOkt996Keccak).oktFetcher
                )

                is ChainOktEvm -> initData(selectedChain, (selectedChain as ChainOktEvm).oktFetcher)
            }
        }
    }

    private fun initData(chain: BaseChain, oktFetcher: OktFetcher?) {
        binding.apply {
            oktFetcher?.oktTokens?.get("data")?.asJsonArray?.firstOrNull { it.asJsonObject["symbol"].asString == selectedChain.stakeDenom }
                ?.let { tokenInfo ->
                    oktTokenInfo = Gson().fromJson(tokenInfo, OktToken::class.java)
                    oktTokenInfo?.let {
                        tokenImg.setTokenImg(chain.assetImg(it.original_symbol))
                        tokenName.text = it.original_symbol.uppercase()

                        val available = oktFetcher.oktBalanceAmount(selectedChain.stakeDenom)
                        availableAmount = if (gasFee < available) {
                            available.subtract(gasFee)
                        } else {
                            BigDecimal.ZERO
                        }
                    }
                }
        }
    }

    private fun initFee() {
        when (selectedChain) {
            is ChainOkt996Keccak -> initFeeData(
                selectedChain, (selectedChain as ChainOkt996Keccak).oktFetcher
            )

            is ChainOktEvm -> initFeeData(selectedChain, (selectedChain as ChainOktEvm).oktFetcher)
        }
    }

    private fun initFeeData(chain: BaseChain, oktFetcher: OktFetcher?) {
        binding.apply {
            feeTokenImg.setTokenImg(chain.assetImg(chain.stakeDenom))
            feeToken.text = chain.stakeDenom.uppercase()

            if (oktFetcher?.oktDeposits?.get("validator_address")?.isJsonNull != true) {
                oktFetcher?.oktDeposits?.get("validator_address")?.asJsonArray?.size()
                    ?.let { existCnt ->
                        gasAmount = BigDecimal(BASE_GAS_AMOUNT)
                        gasFee = BigDecimal(OKT_BASE_FEE)
                        if (existCnt > 10) {
                            val multiplier = if (existCnt > 20) 4 else 3
                            gasAmount = gasAmount.multiply(BigDecimal(multiplier.toString()))
                            gasFee = gasFee.multiply(BigDecimal(multiplier.toString()))
                        }
                    }
            }

            val price = BaseData.getPrice(OKT_GECKO_ID)
            val value = price.multiply(gasFee).setScale(6, RoundingMode.DOWN)
            feeAmount.text = formatAmount(gasFee.toPlainString(), 18)
            feeDenom.text = chain.stakeDenom.uppercase()
            feeValue.text = formatAssetValue(value)
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
                    LegacyInsertAmountFragment.newInstance(selectedChain,
                        oktTokenInfo,
                        availableAmount.toString(),
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
                    MemoFragment.newInstance(selectedChain, txMemo, object : MemoListener {
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

                val depositCoin = LCoin(selectedChain.stakeDenom, toDepositAmount)
                val gasCoin = LCoin(selectedChain.stakeDenom, gasFee.toString())
                val fee = LFee(gasAmount.toString(), mutableListOf(gasCoin))

                val oktDepositMsg = Signer.oktDepositMsg(selectedChain.address, depositCoin)
                txViewModel.broadcastOktTx(oktDepositMsg, fee, txMemo, selectedChain)
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