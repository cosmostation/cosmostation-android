package wannabit.io.cosmostaion.ui.tx.step

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
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.binance.dex.api.client.BinanceDexEnvironment
import com.binance.dex.api.client.Wallet
import com.binance.dex.api.client.domain.broadcast.TransactionOption
import com.binance.dex.api.client.domain.broadcast.Transfer
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.zxing.client.android.Intents
import com.google.zxing.integration.android.IntentIntegrator
import org.bitcoinj.core.ECKey
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.BNB_BEACON_BASE_FEE
import wannabit.io.cosmostaion.chain.cosmosClass.BNB_GECKO_ID
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt996Keccak
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_BASE_FEE
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_GECKO_ID
import wannabit.io.cosmostaion.chain.evmClass.ChainOktEvm
import wannabit.io.cosmostaion.common.BaseConstant.BASE_GAS_AMOUNT
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.model.req.LCoin
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.data.model.res.BnbToken
import wannabit.io.cosmostaion.data.model.res.OktToken
import wannabit.io.cosmostaion.databinding.FragmentLegacyTransferBinding
import wannabit.io.cosmostaion.ui.option.tx.address.AddressListener
import wannabit.io.cosmostaion.ui.option.tx.address.AddressType
import wannabit.io.cosmostaion.ui.option.tx.address.CommonAddressFragment
import wannabit.io.cosmostaion.ui.option.tx.general.AmountSelectListener
import wannabit.io.cosmostaion.ui.option.tx.general.LegacyInsertAmountFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoFragment
import wannabit.io.cosmostaion.ui.option.tx.general.MemoListener
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.qr.QrCodeActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import java.math.BigDecimal
import java.math.RoundingMode

class LegacyTransferFragment : BaseTxFragment() {

    private var _binding: FragmentLegacyTransferBinding? = null
    private val binding get() = _binding!!

    private lateinit var fromChain: CosmosLine
    private lateinit var toSendDenom: String

    private var bnbToken: BnbToken? = null
    private var oktToken: OktToken? = null

    private var toSendAmount = ""
    private var existedAddress = ""
    private var txMemo = ""

    private var availableAmount = BigDecimal.ZERO

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(
            fromChain: CosmosLine, toSendDenom: String
        ): LegacyTransferFragment {
            val args = Bundle().apply {
                putSerializable("fromChain", fromChain)
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
                    getSerializable(
                        "fromChain", CosmosLine::class.java
                    )?.let { fromChain = it }

                } else {
                    (getSerializable("fromChain") as? CosmosLine)?.let {
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

            if (fromChain is ChainBinanceBeacon) {
                (fromChain as ChainBinanceBeacon).apply {
                    lcdBeaconTokens.firstOrNull { it.symbol == toSendDenom }?.let { token ->
                        bnbToken = token
                        val originalSymbol = token.originalSymbol
                        tokenImg.setTokenImg(assetImg(originalSymbol))
                        tokenName.text = originalSymbol.uppercase()

                        val available = lcdBalanceAmount(toSendDenom)
                        availableAmount = if (toSendDenom == stakeDenom) {
                            if (available > BigDecimal(BNB_BEACON_BASE_FEE)) {
                                available.subtract(BigDecimal(BNB_BEACON_BASE_FEE))
                            } else {
                                BigDecimal.ZERO
                            }
                        } else {
                            available
                        }
                    }
                }

            } else if (fromChain is ChainOkt996Keccak) {
                (fromChain as ChainOkt996Keccak).apply {
                    oktTokenInfo?.data?.firstOrNull { it.symbol == toSendDenom }?.let { tokenInfo ->
                        oktToken = tokenInfo
                        val originalSymbol = tokenInfo.originalSymbol
                        tokenImg.setTokenImg(assetImg(originalSymbol))
                        tokenName.text = originalSymbol.uppercase()

                        val available = lcdBalanceAmount(toSendDenom)
                        availableAmount = if (toSendDenom == stakeDenom) {
                            if (available > BigDecimal(OKT_BASE_FEE)) {
                                available.subtract(BigDecimal(OKT_BASE_FEE))
                            } else {
                                BigDecimal.ZERO
                            }
                        } else {
                            available
                        }
                    }
                }

            } else if (fromChain is ChainOktEvm) {
                (fromChain as ChainOktEvm).apply {
                    oktTokenInfo?.data?.firstOrNull { it.symbol == toSendDenom }?.let { tokenInfo ->
                        oktToken = tokenInfo
                        val originalSymbol = tokenInfo.originalSymbol
                        tokenImg.setTokenImg(assetImg(originalSymbol))
                        tokenName.text = originalSymbol.uppercase()

                        val available = lcdBalanceAmount(toSendDenom)
                        availableAmount = if (toSendDenom == stakeDenom) {
                            if (available > BigDecimal(OKT_BASE_FEE)) {
                                available.subtract(BigDecimal(OKT_BASE_FEE))
                            } else {
                                BigDecimal.ZERO
                            }
                        } else {
                            available
                        }
                    }
                }
            }
        }
    }

    private fun initFee() {
        binding.apply {
            if (fromChain is ChainBinanceBeacon) {
                fromChain.stakeDenom?.let { stakeDenom ->
                    feeTokenImg.setTokenImg((fromChain as ChainBinanceBeacon).assetImg(stakeDenom))
                    feeToken.text = stakeDenom.uppercase()

                    val price = BaseData.getPrice(BNB_GECKO_ID)
                    val amount = BigDecimal(BNB_BEACON_BASE_FEE)
                    val value = price.multiply(amount).setScale(6, RoundingMode.DOWN)

                    feeAmount.text = formatAmount(amount.toPlainString(), 8)
                    feeValue.text = formatAssetValue(value)
                }

            } else if (fromChain is ChainOkt996Keccak) {
                fromChain.stakeDenom?.let { stakeDenom ->
                    feeTokenImg.setTokenImg((fromChain as ChainOkt996Keccak).assetImg(stakeDenom))
                    feeToken.text = stakeDenom.uppercase()

                    val price = BaseData.getPrice(OKT_GECKO_ID)
                    val amount = BigDecimal(OKT_BASE_FEE)
                    val value = price.multiply(amount).setScale(6, RoundingMode.DOWN)

                    feeAmount.text = formatAmount(amount.toPlainString(), 18)
                    feeValue.text = formatAssetValue(value)
                }

            } else if (fromChain is ChainOktEvm) {
                fromChain.stakeDenom?.let { stakeDenom ->
                    feeTokenImg.setTokenImg((fromChain as ChainOktEvm).assetImg(stakeDenom))
                    feeToken.text = stakeDenom.uppercase()

                    val price = BaseData.getPrice(OKT_GECKO_ID)
                    val amount = BigDecimal(OKT_BASE_FEE)
                    val value = price.multiply(amount).setScale(6, RoundingMode.DOWN)

                    feeAmount.text = formatAmount(amount.toPlainString(), 18)
                    feeValue.text = formatAssetValue(value)
                }
            }
        }
    }

    private fun updateAmountView(toAmount: String) {
        binding.apply {
            tabMsgTxt.visibility = View.GONE
            amountLayout.visibility = View.VISIBLE
            toSendAmount = toAmount

            if (fromChain is ChainBinanceBeacon) {
                val dpAmount = BigDecimal(toAmount).setScale(8, RoundingMode.DOWN)
                sendAmount.text = formatAmount(dpAmount.toPlainString(), 8)

                if (toSendDenom == fromChain.stakeDenom) {
                    sendValue.visibility = View.VISIBLE
                    val price = BaseData.getPrice(BNB_GECKO_ID)
                    val toSendValue = price.multiply(dpAmount).setScale(6, RoundingMode.DOWN)
                    sendValue.text = formatAssetValue(toSendValue)
                } else {
                    sendValue.visibility = View.GONE
                }

            } else if (fromChain is ChainOkt996Keccak || fromChain is ChainOktEvm) {
                val dpAmount = BigDecimal(toAmount).setScale(18, RoundingMode.DOWN)
                sendAmount.text = formatAmount(dpAmount.toPlainString(), 18)
                if (toSendDenom == fromChain.stakeDenom) {
                    sendValue.visibility = View.VISIBLE
                    val price = BaseData.getPrice(OKT_GECKO_ID)
                    val toSendValue = price.multiply(dpAmount).setScale(6, RoundingMode.DOWN)
                    sendValue.text = formatAssetValue(toSendValue)
                } else {
                    sendValue.visibility = View.GONE
                }
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
                        bnbToken,
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
                                updateMemoView(memo)
                            }
                        })
                )
            }

            memoView.setOnClickListener {
                handleOneClickWithDelay(
                    MemoFragment.newInstance(txMemo, object : MemoListener {
                        override fun memo(memo: String) {
                            updateMemoView(memo)
                        }
                    })
                )
            }

            btnSend.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    sendResultLauncher.launch(this)
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
                    var addressScan = ""
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

                if (fromChain is ChainBinanceBeacon) {
                    ECKey.fromPrivate(fromChain.privateKey)?.let {
                        val wallet = Wallet(it.privateKeyAsHex, BinanceDexEnvironment.PROD)
                        fromChain.lcdAccountInfo?.let { account ->
                            wallet.accountNumber = account.accountNumber
                            wallet.sequence = account.sequence.toLong()
                        }

                        val transfer = Transfer()
                        transfer.coin = toSendDenom
                        transfer.fromAddress = fromChain.address
                        transfer.toAddress = existedAddress
                        transfer.amount = toSendAmount

                        val options = TransactionOption(txMemo, 82, null)
                        txViewModel.broadcastBnbSend(transfer, wallet, options)
                    }

                } else if (fromChain is ChainOkt996Keccak || fromChain is ChainOktEvm) {
                    fromChain.stakeDenom?.let { LCoin(it, OKT_BASE_FEE) }?.let { gasCoin ->
                        val fee = LFee(BASE_GAS_AMOUNT, mutableListOf(gasCoin))
                        val sendCoin = LCoin(toSendDenom, toSendAmount)
                        val recipientAddress =
                            binding.recipientAddress.text.toString().trim().replace("(", "")
                                .replace(")", "")

                        fromChain.address?.let { address ->
                            val oktSendMsg = Signer.oktSendMsg(
                                address, recipientAddress, mutableListOf(sendCoin)
                            )
                            txViewModel.broadcastOktTx(
                                oktSendMsg, fee, txMemo, fromChain
                            )
                        }
                    }
                }
            }
        }

    private fun setUpBroadcast() {
        txViewModel.broadcastBnbTx.observe(viewLifecycleOwner) { txResponse ->
            Intent(requireContext(), TxResultActivity::class.java).apply {
                txResponse?.let { resp ->
                    if (resp[0].isOk) {
                        putExtra("isSuccess", true)
                    } else {
                        putExtra("isSuccess", false)
                        putExtra("errorMsg", resp[0].log)
                    }
                    putExtra("selectedChain", fromChain.tag)
                    val hash = resp[0].hash
                    if (!TextUtils.isEmpty(hash)) putExtra("txHash", hash)
                    startActivity(this)
                }
            }
            dismiss()
        }

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