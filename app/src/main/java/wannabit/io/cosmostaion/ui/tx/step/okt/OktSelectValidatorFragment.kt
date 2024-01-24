package wannabit.io.cosmostaion.ui.tx.step.okt

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_BASE_FEE
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_GECKO_ID
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.model.req.LCoin
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.data.model.res.OktValidatorResponse
import wannabit.io.cosmostaion.databinding.FragmentOktSelectValidatorBinding
import wannabit.io.cosmostaion.ui.option.tx.general.MemoFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoListener
import wannabit.io.cosmostaion.ui.option.tx.validator.OkValidatorListener
import wannabit.io.cosmostaion.ui.option.tx.validator.OktValidatorFragment
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import java.math.BigDecimal
import java.math.RoundingMode

class OktSelectValidatorFragment(val selectedChain: ChainOkt60) : BaseTxFragment() {

    private var _binding: FragmentOktSelectValidatorBinding? = null
    private val binding get() = _binding!!

    private lateinit var oktSelectValidatorAdapter: OktSelectValidatorAdapter

    private var myValidators: MutableList<OktValidatorResponse> = mutableListOf()
    private var txMemo = ""

    private var gasAmount = BigDecimal(BaseConstant.BASE_GAS_AMOUNT)
    private var gasFee = BigDecimal(OKT_BASE_FEE)

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentOktSelectValidatorBinding.inflate(layoutInflater, container, false)
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
            memoView.setBackgroundResource(R.drawable.cell_bg)
            feeView.setBackgroundResource(R.drawable.cell_bg)

            val allValidators = selectedChain.oktValidatorInfo
            val myValidatorAddress = selectedChain.oktDepositedInfo?.validatorAddress?.map { it }
            allValidators.forEach { validatorInfo ->
                if (myValidatorAddress?.contains(validatorInfo.operatorAddress) == true) {
                    myValidators.add(validatorInfo)
                }
            }
            selectCnt.text = "(" + myValidators.size + ")"

            oktSelectValidatorAdapter = OktSelectValidatorAdapter(selectedChain)
            recycler.setHasFixedSize(true)
            recycler.layoutManager = LinearLayoutManager(requireContext())
            recycler.adapter = oktSelectValidatorAdapter
            oktSelectValidatorAdapter.submitList(myValidators)

            if (myValidators.size > 0) {
                validatorLayout.visibility = View.VISIBLE
            } else {
                emptyLayout.visibility = View.VISIBLE
            }
        }
    }

    private fun initFee() {
        binding.apply {
            selectedChain.stakeDenom?.let { stakeDenom ->
                feeTokenImg.setTokenImg(selectedChain.assetImg(stakeDenom))
                feeToken.text = stakeDenom.uppercase()

                selectedChain.oktDepositedInfo?.validatorAddress?.size?.let { existCnt ->
                    val noCnt = myValidators.size
                    val max = if (existCnt >= noCnt) existCnt else noCnt

                    gasAmount = BigDecimal(BaseConstant.BASE_GAS_AMOUNT)
                    gasFee = BigDecimal(OKT_BASE_FEE)
                    if (max > 10) {
                        val multiplier = if (max > 20) 4 else 3
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
            oktSelectValidatorAdapter.setOnItemClickListener {
                OktValidatorFragment(selectedChain, myValidators, object : OkValidatorListener {
                    override fun select(selectValidators: MutableList<OktValidatorResponse>) {
                        myValidators.clear()
                        myValidators.addAll(selectValidators)
                        selectCnt.text = "(" + myValidators.size + ")"
                        oktSelectValidatorAdapter.notifyDataSetChanged()
                        txValidate()
                        initFee()
                    }
                }).show(
                    requireActivity().supportFragmentManager, OktValidatorFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }

            emptyLayout.setOnClickListener {
                OktValidatorFragment(selectedChain, myValidators, object : OkValidatorListener {
                    override fun select(selectValidators: MutableList<OktValidatorResponse>) {
                        validatorLayout.visibility = View.VISIBLE
                        emptyLayout.visibility = View.GONE
                        myValidators.clear()
                        myValidators.addAll(selectValidators)
                        selectCnt.text = "(" + myValidators.size + ")"
                        oktSelectValidatorAdapter.notifyDataSetChanged()
                        txValidate()
                        initFee()
                    }
                }).show(
                    requireActivity().supportFragmentManager, OktValidatorFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }

            memoView.setOnClickListener {
                MemoFragment(txMemo, object : MemoListener {
                    override fun memo(memo: String) {
                        updateMemoView(memo)
                    }

                }).show(
                    requireActivity().supportFragmentManager, MemoFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }

            btnSelectValidator.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    selectValidatorResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                    )
                }
            }
        }
    }

    private fun setClickableOnce(clickable: Boolean) {
        if (clickable) {
            isClickable = false

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 1000)
        }
    }

    private fun txValidate() {
        binding.apply {
            if (myValidators.size <= 0) {
                return
            }
            binding.btnSelectValidator.updateButtonView(true)
        }
    }

    private val selectValidatorResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE

                selectedChain.stakeDenom?.let { stakeDenom ->
                    val gasCoin = LCoin(stakeDenom, gasFee.toString())
                    val fee = LFee(gasAmount.toString(), mutableListOf(gasCoin))

                    selectedChain.address?.let { address ->
                        val myValidators = myValidators.map { it.operatorAddress }.toMutableList()
                        val oktAddSharesMsg = Signer.oktAddShareMsg(address, myValidators)
                        txViewModel.broadcastOktTx(oktAddSharesMsg, fee, txMemo, selectedChain)
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
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}