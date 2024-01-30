package wannabit.io.cosmostaion.ui.tx.step.neutron

import android.app.Activity
import android.content.Intent
import android.os.Build
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
import com.cosmos.tx.v1beta1.TxProto
import com.cosmwasm.wasm.v1.TxProto.MsgExecuteContract
import com.google.gson.Gson
import com.google.protobuf.ByteString
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainNeutron
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.amountHandlerLeft
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.getChannel
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.req.Bond
import wannabit.io.cosmostaion.data.model.req.BondReq
import wannabit.io.cosmostaion.data.model.req.Unbond
import wannabit.io.cosmostaion.data.model.req.UnbondReq
import wannabit.io.cosmostaion.data.model.res.FeeInfo
import wannabit.io.cosmostaion.databinding.FragmentVaultBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.main.chain.TxType
import wannabit.io.cosmostaion.ui.option.tx.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.AssetFragment
import wannabit.io.cosmostaion.ui.option.tx.general.AssetSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.InsertAmountFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoListener
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import java.math.BigDecimal
import java.math.RoundingMode

class VaultFragment : BaseTxFragment() {

    private var _binding: FragmentVaultBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: ChainNeutron
    private lateinit var vaultType: VaultType

    private var toCoin: CoinProto.Coin? = null
    private var feeInfos: MutableList<FeeInfo> = mutableListOf()
    private var selectedFeeInfo = 0
    private var txFee: TxProto.Fee? = null
    private var txMemo = ""

    private var availableAmount = BigDecimal.ZERO

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: CosmosLine, vaultType: VaultType): VaultFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
                putSerializable("vaultType", vaultType)
            }
            val fragment = VaultFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVaultBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initFee()
        updateFeeView()
        setUpClickAction()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.apply {
                    getParcelable("selectedChain", ChainNeutron::class.java)?.let {
                        selectedChain = it
                    }
                    getSerializable("vaultType", VaultType::class.java)
                }

            } else {
                arguments?.apply {
                    (getParcelable("selectedChain") as? ChainNeutron)?.let {
                        selectedChain = it
                    }
                    (getSerializable("vaultType") as? VaultType)?.let {
                        vaultType = it
                    }
                }
            }

            listOf(
                amountView, memoView, feeView
            ).forEach { it.setBackgroundResource(R.drawable.cell_bg) }
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)

            if (vaultType == VaultType.DEPOSIT) {
                vaultTitle.text = getString(R.string.title_vault_deposit)
                vaultAmountTitle.text = getString(R.string.title_vault_deposit_amount)
            } else {
                vaultTitle.text = getString(R.string.title_vault_withdraw)
                vaultAmountTitle.text = getString(R.string.title_vault_withdraw_amount)
            }
        }
    }

    private fun initFee() {
        binding.apply {
            feeInfos = selectedChain.getFeeInfos(requireContext())
            feeSegment.setSelectedBackground(
                ContextCompat.getColor(
                    requireContext(), R.color.color_accent_purple
                )
            )
            feeSegment.setRipple(
                ContextCompat.getColor(
                    requireContext(), R.color.color_accent_purple
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
            feeSegment.setPosition(selectedChain.getFeeBasePosition(), false)
            selectedFeeInfo = selectedChain.getFeeBasePosition()
            txFee = selectedChain.getInitFee(requireContext())
        }
    }

    private fun updateAmountView(toAmount: String) {
        binding.apply {
            val stakeDenom = selectedChain.stakeDenom
            toCoin = CoinProto.Coin.newBuilder().setAmount(toAmount).setDenom(stakeDenom).build()

            stakeDenom?.let {
                BaseData.getAsset(selectedChain.apiName, it)?.let { asset ->
                    asset.decimals?.let { decimal ->
                        val dpAmount = BigDecimal(toAmount).movePointLeft(decimal)
                            .setScale(decimal, RoundingMode.DOWN)
                        vaultAmountMsg.visibility = View.GONE
                        vaultAmount.text = formatAmount(dpAmount.toPlainString(), decimal)
                        vaultAmount.setTextColor(
                            ContextCompat.getColor(
                                requireContext(), R.color.color_base01
                            )
                        )
                        vaultDenom.visibility = View.VISIBLE
                        vaultDenom.text = asset.symbol
                        vaultDenom.setTextColor(asset.assetColor())
                    }
                }
            }
            txSimulate()
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
        txSimulate()
    }

    private fun updateFeeView() {
        binding.apply {
            txFee?.getAmount(0)?.let { fee ->
                BaseData.getAsset(selectedChain.apiName, fee.denom)?.let { asset ->
                    feeTokenImg.setTokenImg(asset)
                    feeToken.text = asset.symbol

                    val amount = fee.amount.toBigDecimal().amountHandlerLeft(asset.decimals ?: 6)
                    val price = BaseData.getPrice(asset.coinGeckoId)
                    val value = price.multiply(amount)

                    feeAmount.text = formatAmount(amount.toPlainString(), asset.decimals ?: 6)
                    feeValue.text = formatAssetValue(value)
                }

                if (vaultType == VaultType.DEPOSIT) {
                    selectedChain.stakeDenom?.let { denom ->
                        val balanceAmount = selectedChain.balanceAmount(denom)
                        availableAmount = if (fee.denom == denom) {
                            val feeAmount = fee.amount.toBigDecimal()
                            if (feeAmount > balanceAmount) {
                                BigDecimal.ZERO
                            } else {
                                balanceAmount.subtract(feeAmount)
                            }
                        } else {
                            balanceAmount
                        }
                    }
                } else {
                    availableAmount = selectedChain.neutronDeposited
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            amountView.setOnClickListener {
                InsertAmountFragment(insertTxType(),
                    null,
                    availableAmount,
                    toCoin?.amount,
                    selectedChain.stakeDenom?.let {
                        BaseData.getAsset(
                            selectedChain.apiName, it
                        )
                    },
                    null,
                    object : AmountSelectListener {
                        override fun select(toAmount: String) {
                            updateAmountView(toAmount)
                        }

                    }).show(
                    requireActivity().supportFragmentManager, InsertAmountFragment::class.java.name
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

            feeTokenLayout.setOnClickListener {
                AssetFragment(selectedChain,
                    feeInfos[selectedFeeInfo].feeDatas,
                    object : AssetSelectListener {
                        override fun select(denom: String) {
                            selectedChain.getDefaultFeeCoins(requireContext())
                                .firstOrNull { it.denom == denom }?.let { feeCoin ->
                                    val updateFeeCoin = CoinProto.Coin.newBuilder().setDenom(denom)
                                        .setAmount(feeCoin.amount).build()

                                    val updateTxFee = TxProto.Fee.newBuilder()
                                        .setGasLimit(BaseConstant.BASE_GAS_AMOUNT.toLong())
                                        .addAmount(updateFeeCoin).build()

                                    txFee = updateTxFee
                                    updateFeeView()
                                    txSimulate()
                                }
                        }

                    }).show(
                    requireActivity().supportFragmentManager, AssetFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }

            feeSegment.setOnPositionChangedListener { position ->
                selectedFeeInfo = position
                txFee = selectedChain.getBaseFee(
                    requireContext(), selectedFeeInfo, txFee?.getAmount(0)?.denom
                )
                updateFeeView()
                txSimulate()
            }

            btnConfirm.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    vaultResultLauncher.launch(this)
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

    private fun insertTxType(): TxType {
        return if (vaultType == VaultType.DEPOSIT) TxType.VAULT_DEPOSIT
        else TxType.VAULT_WITHDRAW
    }

    private val vaultResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                txViewModel.broadcastWasm(
                    getChannel(selectedChain), onBindWasmVaultMsg(), txFee, txMemo, selectedChain
                )
            }
        }

    private fun txSimulate() {
        if (toCoin == null) {
            return
        }
        binding.apply {
            btnConfirm.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE
            txViewModel.simulateWasm(
                getChannel(selectedChain),
                selectedChain.address,
                onBindWasmVaultMsg(),
                txFee,
                txMemo
            )
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulate.observe(viewLifecycleOwner) { gasInfo ->
            isBroadCastTx(true)
            updateFeeViewWithSimulate(gasInfo)
        }

        txViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(false)
            requireContext().showToast(view, response, true)
            return@observe
        }
    }

    private fun updateFeeViewWithSimulate(gasInfo: AbciProto.GasInfo) {
        txFee?.let { fee ->
            feeInfos[selectedFeeInfo].feeDatas.firstOrNull { it.denom == fee.getAmount(0).denom }
                ?.let { gasRate ->
                    val gasLimit =
                        (gasInfo.gasUsed.toDouble() * selectedChain.gasMultiply()).toLong()
                            .toBigDecimal()
                    val feeCoinAmount =
                        gasRate.gasRate?.multiply(gasLimit)?.setScale(0, RoundingMode.UP)
                    val feeCoin = CoinProto.Coin.newBuilder().setDenom(fee.getAmount(0).denom)
                        .setAmount(feeCoinAmount.toString()).build()
                    txFee =
                        TxProto.Fee.newBuilder().setGasLimit(gasLimit.toLong()).addAmount(feeCoin)
                            .build()
                }
        }
        updateFeeView()
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnConfirm.updateButtonView(isSuccess)
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
            dismiss()
        }
    }

    private fun onBindWasmVaultMsg(): MutableList<MsgExecuteContract?> {
        val result: MutableList<MsgExecuteContract?> = mutableListOf()
        if (vaultType == VaultType.DEPOSIT) {
            val req = BondReq(Bond())
            val jsonData = Gson().toJson(req)
            val msg = ByteString.copyFromUtf8(jsonData)
            result.add(
                MsgExecuteContract.newBuilder().setSender(selectedChain.address)
                    .setContract(selectedChain.param?.params?.chainlistParams?.vaults?.get(0)?.address)
                    .setMsg(msg).addFunds(toCoin).build()
            )
            return result

        } else {
            val req = UnbondReq(Unbond(toCoin?.amount))
            val jsonData = Gson().toJson(req)
            val msg = ByteString.copyFromUtf8(jsonData)
            result.add(
                MsgExecuteContract.newBuilder().setSender(selectedChain.address)
                    .setContract(selectedChain.param?.params?.chainlistParams?.vaults?.get(0)?.address)
                    .setMsg(msg).build()
            )
            return result
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

enum class VaultType { DEPOSIT, WITHDRAW }