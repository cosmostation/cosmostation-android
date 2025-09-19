package wannabit.io.cosmostaion.ui.tx.genTx

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.zxing.client.android.Intents
import com.google.zxing.integration.android.IntentIntegrator
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_BASE_FEE
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.chain.fetcher.OktFetcher
import wannabit.io.cosmostaion.common.BaseConstant.BASE_GAS_AMOUNT
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.req.LCoin
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.data.model.res.OktToken
import wannabit.io.cosmostaion.databinding.FragmentLegacyTransferBinding
import wannabit.io.cosmostaion.sign.Signer
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.qr.QrCodeActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.option.address.AddressListener
import wannabit.io.cosmostaion.ui.tx.option.address.AddressType
import wannabit.io.cosmostaion.ui.tx.option.address.CommonAddressFragment
import wannabit.io.cosmostaion.ui.tx.option.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.tx.option.general.LegacyInsertAmountFragment
import wannabit.io.cosmostaion.ui.tx.option.general.MemoFragment
import wannabit.io.cosmostaion.ui.tx.option.general.MemoListener
import java.math.BigDecimal
import java.math.RoundingMode

class LegacyTransferFragment : BaseTxFragment() {

    private var _binding: FragmentLegacyTransferBinding? = null
    private val binding get() = _binding!!

    private lateinit var fromChain: BaseChain
    private lateinit var toSendDenom: String

    private var oktToken: OktToken? = null

    private var toSendAmount = ""
    private var existedAddress = ""
    private var txMemo = ""

    private var availableAmount = BigDecimal.ZERO

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            fromChain: BaseChain, toSendDenom: String
        ): LegacyTransferFragment {
            val args = Bundle().apply {
                putParcelable("fromChain", fromChain)
                putString("toSendDenom", toSendDenom)
            }
            val fragment = LegacyTransferFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentLegacyTransferBinding.inflate(layoutInflater, container, false)
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
            arguments?.apply {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    getParcelable(
                        "fromChain", BaseChain::class.java
                    )?.let { fromChain = it }

                } else {
                    (getParcelable("fromChain") as? BaseChain)?.let {
                        fromChain = it
                    }
                }
                getString("toSendDenom")?.let { toSendDenom = it }
            }
            listOf(sendAssetView, addressView, memoView, feeView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }
            initData(fromChain, (fromChain as ChainOktEvm).oktFetcher)
        }
    }

    private fun initData(chain: BaseChain, oktFetcher: OktFetcher?) {
        binding.apply {
            BaseData.getAsset(chain.apiName, toSendDenom)?.let { asset ->
                tokenImg.setTokenImg(asset)
                tokenName.text = asset.symbol

                val available = oktFetcher?.oktBalanceAmount(toSendDenom)
                availableAmount = if (toSendDenom == chain.getStakeAssetDenom()) {
                    if (BigDecimal(OKT_BASE_FEE) < available) {
                        available?.subtract(BigDecimal(OKT_BASE_FEE))
                    } else {
                        BigDecimal.ZERO
                    }
                } else {
                    available
                }
            }
        }
    }

    private fun initFee() {
        binding.apply {
            feeTokenImg.setTokenImg(fromChain.assetImg(fromChain.getStakeAssetDenom()))
            feeToken.text = fromChain.getStakeAssetDenom().uppercase()

            val coinGeckoId =
                BaseData.getAsset(fromChain.apiName, fromChain.getStakeAssetDenom())?.coinGeckoId
            val price = BaseData.getPrice(coinGeckoId)
            val amount = BigDecimal(OKT_BASE_FEE)
            val value = price.multiply(amount).setScale(6, RoundingMode.DOWN)

            feeAmount.text = formatAmount(amount.toPlainString(), 18)
            feeValue.text = formatAssetValue(value)
        }
    }

    private fun updateAmountView(toAmount: String) {
        binding.apply {
            tabMsgTxt.visibility = View.GONE
            amountLayout.visibility = View.VISIBLE
            toSendAmount = toAmount

            val dpAmount = BigDecimal(toAmount).setScale(18, RoundingMode.DOWN)
            sendAmount.text = formatAmount(dpAmount.toPlainString(), 18)

            if (toSendDenom == fromChain.getStakeAssetDenom()) {
                sendValue.visibility = View.VISIBLE

                val coinGeckoId =
                    BaseData.getAsset(fromChain.apiName, fromChain.getStakeAssetDenom())?.coinGeckoId
                val price = BaseData.getPrice(coinGeckoId)
                val toSendValue = price.multiply(dpAmount).setScale(6, RoundingMode.DOWN)
                sendValue.text = formatAssetValue(toSendValue)

            } else {
                sendValue.visibility = View.GONE
            }
        }
        txValidate()
    }

    private fun updateAddressView(address: String) {
        existedAddress = address
        binding.apply {
            if (address.isEmpty()) {
                recipientAddressMsg.visibility = View.VISIBLE
                recipientAddressMsg.text = getString(R.string.str_tap_for_add_address_msg)
            } else {
                recipientAddressMsg.visibility = View.GONE
                recipientAddress.text = address
            }
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

    @SuppressLint("WrongConstant")
    private fun setUpClickAction() {
        binding.apply {
            btnQr.setOnClickListener {
                val integrator = IntentIntegrator.forSupportFragment(this@LegacyTransferFragment)
                integrator.setOrientationLocked(true)
                integrator.captureActivity = QrCodeActivity::class.java
                qrCodeResultLauncher.launch(integrator.createScanIntent())
            }

            sendAssetView.setOnClickListener {
                handleOneClickWithDelay(
                    LegacyInsertAmountFragment.newInstance(fromChain,
                        oktToken,
                        availableAmount.toString(),
                        toSendAmount,
                        object : AmountSelectListener {
                            override fun select(toAmount: String) {
                                updateAmountView(toAmount)
                            }
                        })
                )
            }

            addressView.setOnClickListener {
                handleOneClickWithDelay(
                    CommonAddressFragment.newInstance(fromChain,
                        existedAddress,
                        AddressType.DEFAULT_TRANSFER,
                        object : AddressListener {
                            override fun selectAddress(address: String, memo: String) {
                                updateAddressView(address)
                                if (memo.isNotEmpty()) {
                                    txMemo = memo
                                    tabMemoMsg.text = txMemo
                                    tabMemoMsg.setTextColor(
                                        ContextCompat.getColorStateList(
                                            requireContext(), R.color.color_base01
                                        )
                                    )
                                }
                            }
                        })
                )
            }

            memoView.setOnClickListener {
                handleOneClickWithDelay(
                    MemoFragment.newInstance(fromChain, txMemo, object : MemoListener {
                        override fun memo(memo: String) {
                            updateMemoView(memo)
                        }
                    })
                )
            }

            btnSend.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    sendResultLauncher.launch(this)
                    if (Build.VERSION.SDK_INT >= 34) {
                        requireActivity().overrideActivityTransition(
                            Activity.OVERRIDE_TRANSITION_OPEN,
                            R.anim.anim_slide_in_bottom,
                            R.anim.anim_fade_out
                        )
                    } else {
                        requireActivity().overridePendingTransition(
                            R.anim.anim_slide_in_bottom,
                            R.anim.anim_fade_out
                        )
                    }
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
            if (toSendAmount.isEmpty()) {
                return
            }
            if (toSendAmount.toBigDecimal() <= BigDecimal.ZERO) {
                return
            }
            binding.btnSend.updateButtonView(true)
        }
    }

    private val qrCodeResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.getStringExtra(Intents.Scan.RESULT)?.trim()?.let { qrData ->
                    val scanString = qrData.split(" (MEMO) ")
                    val addressScan: String
                    var memoScan = ""

                    if (scanString.size == 2) {
                        addressScan = scanString[0]
                        memoScan = scanString[1]
                    } else {
                        addressScan = scanString[0]
                    }
                    if (addressScan.isEmpty() || addressScan.length < 5) {
                        requireContext().makeToast(R.string.error_invalid_address)
                        return@let
                    }
                    if (addressScan == fromChain.address) {
                        requireContext().makeToast(R.string.error_self_sending)
                        return@let
                    }

                    if (BaseUtils.isValidChainAddress(fromChain, addressScan)) {
                        updateAddressView(addressScan.trim())
                        if (scanString.size > 1) {
                            updateMemoView(memoScan.trim())
                        }

                    } else {
                        requireContext().makeToast(R.string.error_invalid_address)
                        return@let
                    }
                }
            }
        }

    private val sendResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE

                val fee =
                    LFee(BASE_GAS_AMOUNT, mutableListOf(LCoin(fromChain.getStakeAssetDenom(), OKT_BASE_FEE)))
                val sendCoin = LCoin(toSendDenom, toSendAmount)
                val recipientAddress =
                    binding.recipientAddress.text.toString().trim().replace("(", "")
                        .replace(")", "")

                val oktSendMsg = Signer.oktSendMsg(
                    fromChain.address, recipientAddress, mutableListOf(sendCoin)
                )
                txViewModel.broadcastOktTx(
                    oktSendMsg, fee, txMemo, fromChain
                )
            }
        }

    private fun setUpBroadcast() {
        txViewModel.broadcastOktTx.observe(viewLifecycleOwner) { txResponse ->
            binding.backdropLayout.visibility = View.GONE
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
                putExtra("selectedChain", fromChain.tag)
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