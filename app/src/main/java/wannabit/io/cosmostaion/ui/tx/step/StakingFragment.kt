package wannabit.io.cosmostaion.ui.tx.step

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.cosmos.base.abci.v1beta1.AbciProto
import com.cosmos.base.v1beta1.CoinProto
import com.cosmos.staking.v1beta1.StakingProto.Validator
import com.cosmos.staking.v1beta1.TxProto.MsgDelegate
import com.cosmos.tx.v1beta1.TxProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.formatString
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.setMonikerImg
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.databinding.FragmentStakingBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.dialog.tx.AmountSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.AssetFragment
import wannabit.io.cosmostaion.ui.dialog.tx.AssetSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.ChainFragment
import wannabit.io.cosmostaion.ui.dialog.tx.InsertAmountFragment
import wannabit.io.cosmostaion.ui.dialog.tx.MemoFragment
import wannabit.io.cosmostaion.ui.dialog.tx.MemoListener
import wannabit.io.cosmostaion.ui.dialog.tx.validator.ValidatorDefaultFragment
import wannabit.io.cosmostaion.ui.dialog.tx.validator.ValidatorDefaultListener
import wannabit.io.cosmostaion.ui.dialog.tx.validator.ValidatorFragment
import wannabit.io.cosmostaion.ui.main.chain.TxType
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import java.math.BigDecimal
import java.math.RoundingMode

class StakingFragment(
    val selectedChain: CosmosLine,
    private var toValidator: Validator?
) : BaseTxFragment() {

    private var _binding: FragmentStakingBinding? = null
    private val binding get() = _binding!!

    private var feeInfos: MutableList<FeeInfo> = mutableListOf()
    private var selectedFeeInfo = 0
    private var txFee: TxProto.Fee? = null

//    private var toValidator: Validator? = null
    private var toCoin: CoinProto.Coin? = null
    private var txMemo = ""

    private var availableAmount = BigDecimal.ZERO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStakingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initFee()
        updateFeeView()
        clickAction()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            validatorView.setBackgroundResource(R.drawable.cell_bg)
            amountView.setBackgroundResource(R.drawable.cell_bg)
            memoView.setBackgroundResource(R.drawable.cell_bg)
            feeView.setBackgroundResource(R.drawable.cell_bg)

            if (toValidator == null) {
                selectedChain.cosmosValidators.firstOrNull { it.description.moniker == "Cosmostation" }?.let { vaildator ->
                    toValidator = vaildator
                } ?: run {
                    toValidator = selectedChain.cosmosValidators[0]
                }
            }
            updateValidator()
        }
    }

    private fun initFee() {
        binding.apply {
            feeInfos = selectedChain.getFeeInfos(requireContext())
            feeSegment.setSelectedBackground(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_accent_purple
                )
            )
            feeSegment.setRipple(
                ContextCompat.getColor(
                    requireContext(),
                    R.color.color_accent_purple
                )
            )

            for (i in feeInfos.indices) {
                val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                feeSegment.addView(
                    segmentView.root,
                    i,
                    LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                )
                segmentView.btnTitle.text = feeInfos[i].title
            }
            selectedFeeInfo = selectedChain.getFeeBasePosition()
            txFee = selectedChain.getInitFee(requireContext())
        }
    }

    private fun updateValidator() {
        binding.apply {
            toValidator?.let { validator ->
                monikerImg.setMonikerImg(selectedChain, validator.operatorAddress)
                monikerName.text = validator.description?.moniker
                jailedImg.visibleOrGone(validator.jailed)
            }
            selectedChain.stakeDenom?.let { denom ->
                BaseData.getAsset(selectedChain.apiName, denom)?.let { asset ->
                    val commissionRate =
                        toValidator?.commission?.commissionRates?.rate?.toBigDecimal()
                            ?.movePointLeft(16)?.setScale(2, RoundingMode.DOWN)
                    commissionPercent.text = formatString("$commissionRate%", 3)
                }
            }
        }
        txSimul()
    }

    private fun updateAmountView(toAmount: String) {
        binding.apply {
            val stakeDenom = selectedChain.stakeDenom
            toCoin = CoinProto.Coin.newBuilder().setAmount(toAmount).setDenom(stakeDenom).build()

            stakeDenom?.let {
                BaseData.getAsset(selectedChain.apiName, it)?.let { asset ->
                    asset.decimals?.let { decimal ->
                        val dpAmount = BigDecimal(toAmount).movePointLeft(decimal).setScale(decimal, RoundingMode.DOWN)
                        delegateAmountMsg.visibility = View.GONE
                        delegateAmount.text = formatAmount(dpAmount.toPlainString(), decimal)
                        delegateAmount.setTextColor(ContextCompat.getColor(requireContext(), R.color.color_base01))
                        delegateDenom.visibility = View.VISIBLE
                        delegateDenom.text = asset.symbol
                    }
                }
            }
            txSimul()
        }
    }

    private fun updateMemoView(memo: String) {
        binding.apply {
            txMemo = memo
            if (txMemo.isEmpty()) {
                tabMemoMsg.text = getString(R.string.str_tap_for_add_memo_msg)
                tabMemoMsg.setTextColor(ContextCompat.getColorStateList(requireContext(), R.color.color_base03))
            } else {
                tabMemoMsg.text = txMemo
                tabMemoMsg.setTextColor(ContextCompat.getColorStateList(requireContext(), R.color.color_base01))
            }
        }
        txSimul()
    }

    private fun updateFeeView() {
        binding.apply {
            txFee?.getAmount(0)?.let { fee ->
                BaseData.getAsset(selectedChain.apiName, fee.denom)?.let { asset ->
                    feeTokenImg.setTokenImg(asset)
                    feeToken.text = asset.symbol

                    val amount = fee.amount.toBigDecimal()
                    val price = BaseData.getPrice(asset.coinGeckoId)

                    asset.decimals?.let { decimal ->
                        val dpAmount = amount.movePointLeft(decimal).setScale(decimal, RoundingMode.DOWN)
                        feeAmount.text = formatAmount(dpAmount.toPlainString(), decimal)
                        feeDenom.text = asset.symbol
                        val value = price.multiply(amount).movePointLeft(decimal).setScale(decimal, RoundingMode.DOWN)
                        feeValue.text = formatAssetValue(value)
                    }
                }

                selectedChain.stakeDenom?.let { denom ->
                    val balanceAmount = selectedChain.balanceAmount(denom)
                    val vestingAmount = selectedChain.vestingAmount(denom)

                    txFee?.let {
                        availableAmount = if (it.getAmount(0).denom == denom) {
                            val feeAmount = it.getAmount(0).amount.toBigDecimal()
                            if (feeAmount > balanceAmount) {

                            }
                            balanceAmount.add(vestingAmount).subtract(feeAmount)

                        } else {
                            balanceAmount.add(vestingAmount)
                        }
                    }
                }
            }
        }
    }

    private fun clickAction() {
        var isClickable = true
        binding.apply {
            validatorView.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    val bottomSheet = ValidatorDefaultFragment(selectedChain, object : ValidatorDefaultListener {
                        override fun select(validatorAddress: String) {
                            toValidator = selectedChain.cosmosValidators.firstOrNull { it.operatorAddress == validatorAddress }
                            updateValidator()
                        }
                    })

                    bottomSheet.show(
                        requireActivity().supportFragmentManager,
                        ValidatorFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            amountView.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    InsertAmountFragment(
                        TxType.DELEGATE,
                        null,
                        availableAmount,
                        toCoin?.amount,
                        selectedChain.stakeDenom?.let { BaseData.getAsset(selectedChain.apiName, it) },
                        null,
                        object : AmountSelectListener {
                            override fun select(toAmount: String) {
                                updateAmountView(toAmount)
                            }

                        }).show(requireActivity().supportFragmentManager, InsertAmountFragment::class.java.name)

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            memoView.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    MemoFragment(txMemo, object : MemoListener {
                        override fun memo(memo: String) {
                            updateMemoView(memo)
                        }

                    }).show(
                        requireActivity().supportFragmentManager, MemoFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            feeTokenLayout.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    AssetFragment(selectedChain, feeInfos[selectedFeeInfo].feeDatas, object : AssetSelectListener {
                        override fun select(denom: String) {
                            var tempCoin: CoinProto.Coin? = null
                            selectedChain.getDefaultFeeCoins(requireContext()).forEach { feeCoin ->
                                if (feeCoin.denom == denom) {
                                    tempCoin = CoinProto.Coin.newBuilder().setDenom(denom).setAmount(feeCoin.amount).build()
                                }
                            }
                            val tempTxFee = TxProto.Fee.newBuilder()
                                .setGasLimit(BaseConstant.BASE_GAS_AMOUNT.toLong())
                                .addAmount(tempCoin).build()
                            txFee = tempTxFee
                            updateFeeView()
                        }

                    }).show(
                        requireActivity().supportFragmentManager, ChainFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            feeSegment.setOnPositionChangedListener { position ->
                selectedFeeInfo = position
                txFee = selectedChain.getBaseFee(requireContext(), selectedFeeInfo, txFee?.getAmount(0)?.denom)
                updateFeeView()
                txSimul()
            }

            btnUnstake.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    unDelegateResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_fade_out)
                }
            }
        }
    }

    private val unDelegateResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                txViewModel.broadDelegate(getChannel(selectedChain), selectedChain.address, onBindDelegate(), txFee, txMemo, selectedChain)
            }
        }

    private fun txSimul() {
        binding.apply {
            if (toCoin == null) { return }
            backdropLayout.visibility = View.VISIBLE
            txViewModel.simulateDelegate(getChannel(selectedChain), selectedChain.address, onBindDelegate(), txFee, txMemo)
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulate.observe(viewLifecycleOwner) { gasInfo ->
            isBroadCastTx(true)
            updateFeeViewWithSimul(gasInfo)
        }

        txViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(false)
            requireContext().showToast(view, response, true)
            return@observe
        }
    }

    private fun updateFeeViewWithSimul(gasInfo: AbciProto.GasInfo) {
        txFee?.let { fee ->
            feeInfos[selectedFeeInfo].feeDatas.firstOrNull { it.denom == fee.getAmount(0).denom }?.let { gasRate ->
                val gasLimit = (gasInfo.gasUsed.toDouble() * selectedChain.gasMultiply()).toLong().toBigDecimal()
                val feeCoinAmount = gasRate.gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)

                val feeCoin =  CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0).denom).setAmount(feeCoinAmount.toString()).build()
                txFee = TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong()).addAmount(feeCoin).build()
            }
        }
        updateFeeView()
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnUnstake.updateButtonView(isSuccess)
    }

    private fun setUpBroadcast() {
        txViewModel.broadcastTx.observe(viewLifecycleOwner) { txResponse ->
            Intent(requireContext(), TxResultActivity::class.java).apply {
                if (txResponse.code > 0) {
                    putExtra("isSuccess", false)
                } else {
                    putExtra("isSuccess", true)
                }
                putExtra("errorMsg", txResponse.rawLog)
                putExtra("selectedChain", selectedChain.tag)
                val hash = txResponse.txhash
                if (!TextUtils.isEmpty(hash)) putExtra("txHash", hash)
                startActivity(this)
            }
        }
    }

    private fun onBindDelegate(): MsgDelegate {
        return MsgDelegate.newBuilder().setDelegatorAddress(selectedChain.address).setValidatorAddress(toValidator?.operatorAddress).setAmount(toCoin).build()
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}