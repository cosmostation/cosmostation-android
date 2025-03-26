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
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_BASE_FEE
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.chain.fetcher.OktFetcher
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.req.LCoin
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.databinding.FragmentOktSelectValidatorBinding
import wannabit.io.cosmostaion.sign.Signer
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import wannabit.io.cosmostaion.ui.tx.option.general.MemoFragment
import wannabit.io.cosmostaion.ui.tx.option.general.MemoListener
import wannabit.io.cosmostaion.ui.tx.option.validator.OkValidatorListener
import wannabit.io.cosmostaion.ui.tx.option.validator.OktValidatorFragment
import java.math.BigDecimal
import java.math.RoundingMode

class OktSelectValidatorFragment : BaseTxFragment() {

    private var _binding: FragmentOktSelectValidatorBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain

    private lateinit var oktSelectValidatorAdapter: OktSelectValidatorAdapter

    private var myValidators: MutableList<JsonObject> = mutableListOf()
    private var txMemo = ""

    private var gasAmount = BigDecimal(BaseConstant.BASE_GAS_AMOUNT)
    private var gasFee = BigDecimal(OKT_BASE_FEE)

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): OktSelectValidatorFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = OktSelectValidatorFragment()
            fragment.arguments = args
            return fragment
        }
    }

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
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedChain", BaseChain::class.java)
                    ?.let { selectedChain = it }

            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
            }

            listOf(
                memoView, feeView
            ).forEach { it.setBackgroundResource(R.drawable.cell_bg) }

            when (selectedChain) {
                is ChainOkt996Keccak -> initData((selectedChain as ChainOkt996Keccak).oktFetcher)
                is ChainOktEvm -> initData((selectedChain as ChainOktEvm).oktFetcher)
            }
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

    private fun initData(oktFetcher: OktFetcher?) {
        binding.apply {
            val allValidators = oktFetcher?.oktValidatorInfo
            val myValidatorAddress =
                oktFetcher?.oktDeposits?.get("validator_address")?.asJsonArray?.map { it }
            allValidators?.forEach { validatorInfo ->
                myValidatorAddress?.forEach {
                    if (it.asString == validatorInfo["operator_address"].asString) {
                        myValidators.add(validatorInfo)
                    }
                }
            }
            selectCnt.text = "(" + myValidators.size + ")"
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
            }

            val coinGeckoId = BaseData.getAsset(chain.apiName, chain.stakeDenom)?.coinGeckoId
            val price = BaseData.getPrice(coinGeckoId)
            val value = price.multiply(gasFee).setScale(6, RoundingMode.DOWN)
            feeAmount.text = formatAmount(gasFee.toPlainString(), 18)
            feeDenom.text = chain.stakeDenom.uppercase()
            feeValue.text = formatAssetValue(value)
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
                handleOneClickWithDelay(
                    OktValidatorFragment(selectedChain, myValidators, object : OkValidatorListener {
                        override fun select(selectValidators: MutableList<JsonObject>) {
                            myValidators.clear()
                            myValidators.addAll(selectValidators)
                            selectCnt.text = "(" + myValidators.size + ")"
                            oktSelectValidatorAdapter.notifyDataSetChanged()
                            txValidate()
                            initFee()
                        }
                    })
                )
            }

            emptyLayout.setOnClickListener {
                handleOneClickWithDelay(
                    OktValidatorFragment(selectedChain, myValidators, object : OkValidatorListener {
                        override fun select(selectValidators: MutableList<JsonObject>) {
                            validatorLayout.visibility = View.VISIBLE
                            emptyLayout.visibility = View.GONE
                            myValidators.clear()
                            myValidators.addAll(selectValidators)
                            selectCnt.text = "(" + myValidators.size + ")"
                            oktSelectValidatorAdapter.notifyDataSetChanged()
                            txValidate()
                            initFee()
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

                val gasCoin = LCoin(selectedChain.stakeDenom, gasFee.toString())
                val fee = LFee(gasAmount.toString(), mutableListOf(gasCoin))

                val myValidators =
                    myValidators.map { it["operator_address"].asString }.toMutableList()
                val oktAddSharesMsg = Signer.oktAddShareMsg(selectedChain.address, myValidators)
                txViewModel.broadcastOktTx(oktAddSharesMsg, fee, txMemo, selectedChain)
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