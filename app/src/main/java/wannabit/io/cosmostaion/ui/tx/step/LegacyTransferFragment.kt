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
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.binance.dex.api.client.BinanceDexEnvironment
import com.binance.dex.api.client.Wallet
import com.binance.dex.api.client.domain.broadcast.TransactionOption
import com.binance.dex.api.client.domain.broadcast.Transfer
import org.bitcoinj.core.ECKey
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.BNB_BEACON_BASE_FEE
import wannabit.io.cosmostaion.chain.cosmosClass.BNB_GECKO_ID
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_BASE_FEE
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_GECKO_ID
import wannabit.io.cosmostaion.common.BaseConstant.BASE_GAS_AMOUNT
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.cosmos.Signer
import wannabit.io.cosmostaion.data.model.req.LCoin
import wannabit.io.cosmostaion.data.model.req.LFee
import wannabit.io.cosmostaion.data.model.res.BnbToken
import wannabit.io.cosmostaion.data.model.res.OktToken
import wannabit.io.cosmostaion.databinding.FragmentLegacyTransferBinding
import wannabit.io.cosmostaion.ui.dialog.tx.AmountSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.LegacyInsertAmountFragment
import wannabit.io.cosmostaion.ui.dialog.tx.MemoFragment
import wannabit.io.cosmostaion.ui.dialog.tx.MemoListener
import wannabit.io.cosmostaion.ui.dialog.tx.address.AddressFragment
import wannabit.io.cosmostaion.ui.dialog.tx.address.AddressListener
import wannabit.io.cosmostaion.ui.dialog.tx.address.AddressType
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import java.math.BigDecimal
import java.math.RoundingMode

class LegacyTransferFragment(
    private val selectedChain: CosmosLine,
    private val toSendDenom: String
) : BaseTxFragment() {

    private var _binding: FragmentLegacyTransferBinding? = null
    private val binding get() = _binding!!

    private var bnbTokenInfo: BnbToken? = null
    private var oktTokenInfo: OktToken? = null

    private var toSendAmount = ""
    private var existedAddress = ""
    private var txMemo = ""

    private var availableAmount = BigDecimal.ZERO

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
        clickAction()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            sendAssetView.setBackgroundResource(R.drawable.cell_bg)
            addressView.setBackgroundResource(R.drawable.cell_bg)
            memoView.setBackgroundResource(R.drawable.cell_bg)
            feeView.setBackgroundResource(R.drawable.cell_bg)
            recipientAddress.visibleOrGone(selectedChain is ChainOkt60)

            if (selectedChain is ChainBinanceBeacon) {
                selectedChain.lcdBeaconTokens.firstOrNull { it.symbol == toSendDenom }?.let { tokenInfo ->
                    bnbTokenInfo = tokenInfo
                    val originalSymbol = tokenInfo.originalSymbol
                    tokenImg.setTokenImg(selectedChain.assetImg(originalSymbol))
                    tokenName.text = originalSymbol.uppercase()
                }

                val available = selectedChain.lcdBalanceAmount(toSendDenom)
                availableAmount = if (toSendDenom == selectedChain.stakeDenom) {
                    available.subtract(BigDecimal(BNB_BEACON_BASE_FEE))
                } else {
                    available
                }

            } else if (selectedChain is ChainOkt60) {
                selectedChain.oktTokenInfo?.data?.firstOrNull { it.symbol == toSendDenom }?.let { tokenInfo ->
                    oktTokenInfo = tokenInfo
                    val originalSymbol = tokenInfo.originalSymbol
                    tokenImg.setTokenImg(selectedChain.assetImg(originalSymbol))
                    tokenName.text = originalSymbol.uppercase()

                    val available = selectedChain.lcdBalanceAmount(toSendDenom)
                    availableAmount = if (toSendDenom == selectedChain.stakeDenom) {
                        available.subtract(BigDecimal(OKT_BASE_FEE))
                    } else {
                        available
                    }
                }
            }
        }
    }

    private fun initFee() {
        binding.apply {
            if (selectedChain is ChainBinanceBeacon) {
                selectedChain.stakeDenom?.let { stakeDenom ->
                    feeTokenImg.setTokenImg(selectedChain.assetImg(stakeDenom))
                    feeToken.text = stakeDenom.uppercase()

                    val price = BaseData.getPrice(BNB_GECKO_ID)
                    val amount = BigDecimal(BNB_BEACON_BASE_FEE)
                    val value = price.multiply(amount).setScale(6, RoundingMode.DOWN)
                    feeAmount.text = formatAmount(amount.toPlainString(), 8)
                    feeDenom.text = stakeDenom.uppercase()
                    feeValue.text = formatAssetValue(value)
                }

            } else if (selectedChain is ChainOkt60) {
                selectedChain.stakeDenom?.let { stakeDenom ->
                    feeTokenImg.setTokenImg(selectedChain.assetImg(stakeDenom))
                    feeToken.text = stakeDenom.uppercase()

                    val price = BaseData.getPrice(OKT_GECKO_ID)
                    val amount = BigDecimal(OKT_BASE_FEE)
                    val value = price.multiply(amount).setScale(6, RoundingMode.DOWN)

                    feeAmount.text = formatAmount(amount.toPlainString(), 18)
                    feeDenom.text = stakeDenom.uppercase()
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

            if (selectedChain is ChainBinanceBeacon) {
                val dpAmount = BigDecimal(toAmount).setScale(8, RoundingMode.DOWN)
                sendAmount.text = formatAmount(dpAmount.toPlainString(), 8)

                if (toSendDenom == selectedChain.stakeDenom) {
                    sendValue.visibility = View.VISIBLE
                    val price = BaseData.getPrice(BNB_GECKO_ID)
                    val toSendValue = price.multiply(dpAmount).setScale(6, RoundingMode.DOWN)
                    sendValue.text = formatAssetValue(toSendValue)
                } else {
                    sendValue.visibility = View.GONE
                }

            } else if (selectedChain is ChainOkt60) {
                val dpAmount = BigDecimal(toAmount).setScale(18, RoundingMode.DOWN)
                sendAmount.text = formatAmount(dpAmount.toPlainString(), 18)
                if (toSendDenom == selectedChain.stakeDenom) {
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
            if (selectedChain is ChainOkt60) {
                if (address.isEmpty()) {
                    recipientAddressMsg.visibility = View.VISIBLE
                    recipientAddressMsg.text = getString(R.string.str_tap_for_add_address_msg)
                } else {
                    recipientAddressMsg.visibility = View.GONE
                    if (BaseKey.isValidBech32(address)) {
                        recipientEvmAddress.text = ByteUtils.convertBech32ToEvm(address)
                        recipientAddress.text = "(" + address + ")"
                    } else if (BaseKey.isValidEthAddress(address)) {
                        recipientEvmAddress.text = address
                        recipientAddress.text = "(" + ByteUtils.convertEvmToBech32(address, selectedChain.accountPrefix) + ")"
                    }
                }

            } else {
                recipientAddress.visibility = View.GONE
                if (address.isEmpty()) {
                    recipientAddressMsg.visibility = View.VISIBLE
                    recipientAddressMsg.text = getString(R.string.str_tap_for_add_address_msg)

                } else {
                    recipientAddressMsg.visibility = View.GONE
                    recipientEvmAddress.text = address
                }
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
                        requireContext(),
                        R.color.color_base03
                    )
                )
            } else {
                tabMemoMsg.text = txMemo
                tabMemoMsg.setTextColor(
                    ContextCompat.getColorStateList(
                        requireContext(),
                        R.color.color_base01
                    )
                )
            }
        }
        txValidate()
    }

    private fun clickAction() {
        var isClickable = true
        binding.apply {
            sendAssetView.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    LegacyInsertAmountFragment(
                        selectedChain,
                        bnbTokenInfo,
                        oktTokenInfo,
                        availableAmount,
                        toSendAmount,
                        object : AmountSelectListener {
                            override fun select(toAmount: String) {
                                updateAmountView(toAmount)
                            }

                        }).show(
                        requireActivity().supportFragmentManager,
                        LegacyInsertAmountFragment::class.java.name
                    )

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            addressView.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    AddressFragment(
                        selectedChain,
                        selectedChain,
                        existedAddress,
                        AddressType.DEFAULT_TRANSFER,
                        object : AddressListener {
                            override fun address(address: String) {
                                updateAddressView(address)
                            }

                        }).show(
                        requireActivity().supportFragmentManager, AddressFragment::class.java.name
                    )

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

            btnSend.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    sendResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom,
                        R.anim.anim_fade_out
                    )
                }
            }
        }
    }

    private fun txValidate() {
        binding.apply {
            if (toSendAmount.isEmpty() || recipientAddress.text.isEmpty()) { return }
            if (BigDecimal(toSendAmount) == BigDecimal.ZERO) { return }
            binding.btnSend.updateButtonView(true)
        }
    }

    private val sendResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE

                if (selectedChain is ChainBinanceBeacon) {
                    ECKey.fromPrivate(selectedChain.privateKey)?.let {
                        val wallet = Wallet(it.privateKeyAsHex, BinanceDexEnvironment.PROD)
                        selectedChain.lcdAccountInfo?.let { account ->
                            wallet.accountNumber = account.accountNumber
                            wallet.sequence = account.sequence.toLong()
                        }

                        val transfer = Transfer()
                        transfer.coin = toSendDenom
                        transfer.fromAddress = selectedChain.address
                        transfer.toAddress = existedAddress
                        transfer.amount = toSendAmount

                        val options = TransactionOption(txMemo, 82, null)
                        txViewModel.broadcastBnbSend(transfer, wallet, options)
                    }

                } else if (selectedChain is ChainOkt60) {
                    selectedChain.stakeDenom?.let { LCoin(it, OKT_BASE_FEE) }?.let { gasCoin ->
                        val fee = LFee(BASE_GAS_AMOUNT, mutableListOf(gasCoin))
                        val sendCoin = LCoin(toSendDenom, toSendAmount)
                        val recipientAddress = binding.recipientAddress.text.toString().trim().replace("(", "").replace(")", "")

                        selectedChain.address?.let { address ->
                            val oktSendMsg = Signer.oktSendMsg(address, recipientAddress, mutableListOf(sendCoin))
                            txViewModel.broadcastOktTx(oktSendMsg, fee, txMemo, selectedChain)
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
                    putExtra("selectedChain", selectedChain.tag)
                    val hash = resp[0].hash
                    if (!TextUtils.isEmpty(hash)) putExtra("txHash", hash)
                    startActivity(this)
                }
            }
        }

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