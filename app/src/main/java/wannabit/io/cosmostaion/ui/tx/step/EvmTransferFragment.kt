package wannabit.io.cosmostaion.ui.tx.step

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
import org.web3j.protocol.Web3j
import org.web3j.protocol.http.HttpService
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainOkt60
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_BASE_FEE
import wannabit.io.cosmostaion.chain.cosmosClass.OKT_GECKO_ID
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseKey
import wannabit.io.cosmostaion.common.ByteUtils
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.data.model.res.Token
import wannabit.io.cosmostaion.database.model.AddressBook
import wannabit.io.cosmostaion.database.model.RefAddress
import wannabit.io.cosmostaion.databinding.FragmentEvmTransferBinding
import wannabit.io.cosmostaion.ui.dialog.tx.AmountSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.InsertAmountFragment
import wannabit.io.cosmostaion.ui.dialog.tx.address.AddressFragment
import wannabit.io.cosmostaion.ui.dialog.tx.address.AddressListener
import wannabit.io.cosmostaion.ui.dialog.tx.address.AddressType
import wannabit.io.cosmostaion.ui.main.chain.TxType
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.TxResultType
import java.math.BigDecimal
import java.math.RoundingMode

class EvmTransferFragment(
    private val selectedChain: CosmosLine, private val toSendDenom: String
) : BaseTxFragment() {

    private var _binding: FragmentEvmTransferBinding? = null
    private val binding get() = _binding!!

    private var selectedToken: Token? = null

    private var toSendAmount = ""
    private var existedAddress = ""

    private var availableAmount = BigDecimal.ZERO

    private var evmFeeAmount = BigDecimal("600000000000000")

    private var hexValue: String = ""

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEvmTransferBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        updateFeeView()
        setUpClickAction()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            listOf(sendAssetView, addressView, feeView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }

            selectedToken = selectedChain.tokens.firstOrNull { it.address == toSendDenom }
            selectedToken?.let { token ->
                tokenImg.setTokenImg(token.assetImg())
                tokenName.text = token.symbol
                availableAmount = token.amount?.toBigDecimal()
            }

            if (selectedChain is ChainOkt60) {
                selectedChain.stakeDenom?.let { denom ->
                    feeTokenImg.setTokenImg(selectedChain.assetImg(denom))
                    feeToken.text = denom.uppercase()
                }

            } else {
                BaseData.assets?.firstOrNull { it.denom == selectedChain.stakeDenom }
                    ?.let { asset ->
                        feeTokenImg.setTokenImg(asset)
                        feeToken.text = asset.symbol
                    }
            }
        }
    }

    private fun updateFeeView() {
        binding.apply {
            if (selectedChain is ChainOkt60) {
                val price = BaseData.getPrice(OKT_GECKO_ID)
                val calFeeAmount = BigDecimal(OKT_BASE_FEE)
                val value = price.multiply(calFeeAmount).setScale(6, RoundingMode.DOWN)
                feeAmount.text = formatAmount(calFeeAmount.toPlainString(), 18)
                feeDenom.text = selectedChain.stakeDenom?.uppercase()
                feeValue.text = formatAssetValue(value)

            } else {
                BaseData.assets?.firstOrNull { it.denom == selectedChain.stakeDenom }
                    ?.let { asset ->
                        val calFeeAmount =
                            evmFeeAmount.movePointLeft(18).setScale(18, RoundingMode.DOWN)
                        val price = BaseData.getPrice(asset.coinGeckoId)
                        val value = price.multiply(calFeeAmount).setScale(6, RoundingMode.DOWN)
                        feeAmount.text = formatAmount(calFeeAmount.toPlainString(), 18)
                        feeDenom.text = asset.symbol
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

            selectedToken?.let { token ->
                val dpAmount = BigDecimal(toAmount).movePointLeft(token.decimals)
                    .setScale(token.decimals, RoundingMode.DOWN)
                val price = BaseData.getPrice(selectedToken?.coinGeckoId)
                val value = price.multiply(dpAmount)

                sendAmount.text = formatAmount(dpAmount.toPlainString(), token.decimals)
                sendValue.text = formatAssetValue(value)
            }
        }
        txSimulate()
    }

    private fun updateAddressView(address: String) {
        existedAddress = address
        binding.apply {
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
                    recipientAddress.text = "(" + ByteUtils.convertEvmToBech32(
                        address, selectedChain.accountPrefix
                    ) + ")"
                }
            }
        }
        txSimulate()
    }

    private fun setUpClickAction() {
        binding.apply {
            sendAssetView.setOnClickListener {
                InsertAmountFragment(TxType.TRANSFER,
                    TransferAssetType.ERC20_TRANSFER,
                    availableAmount,
                    toSendAmount,
                    null,
                    selectedToken,
                    object : AmountSelectListener {
                        override fun select(toAmount: String) {
                            updateAmountView(toAmount)
                        }

                    }).show(
                    requireActivity().supportFragmentManager, InsertAmountFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }

            addressView.setOnClickListener {
                AddressFragment(selectedChain,
                    selectedChain,
                    existedAddress,
                    AddressType.EVM_TRANSFER,
                    object : AddressListener {
                        override fun selectAddress(
                            refAddress: RefAddress?, addressBook: AddressBook?, addressTxt: String
                        ) {
                            refAddress?.dpAddress?.let {
                                updateAddressView(it)

                            } ?: run {
                                addressBook?.let {
                                    updateAddressView(it.address)

                                } ?: run {
                                    updateAddressView(addressTxt)
                                }
                            }
                        }

                    }).show(
                    requireActivity().supportFragmentManager, AddressFragment::class.java.name
                )
                setClickableOnce(isClickable)
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

    private fun setClickableOnce(clickable: Boolean) {
        if (clickable) {
            isClickable = false

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 1000)
        }
    }

    private val sendResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE

                val web3j = Web3j.build(HttpService(selectedChain.rpcUrl))
                txViewModel.broadcastErc20Send(web3j, hexValue)
            }
        }

    private fun txSimulate() {
        binding.apply {
            if (toSendAmount.isEmpty() || recipientEvmAddress.text.isEmpty()) {
                return
            }
            if (BigDecimal(toSendAmount) == BigDecimal.ZERO) {
                return
            }

            btnSend.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE
            txViewModel.simulateErc20Send(
                binding.recipientEvmAddress.text.toString().trim(),
                toSendAmount,
                selectedToken,
                selectedChain
            )
        }
    }

    private fun setUpSimulate() {
        txViewModel.simulateErc20Send.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(true)
            response.first?.let { evmFeeAmount = it.toBigDecimal() }
            response.second?.let { hexValue = it }
            updateFeeView()
        }

        txViewModel.erc20ErrorMessage.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(false)
            requireContext().showToast(view, response.first, true)
            return@observe
        }
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnSend.updateButtonView(isSuccess)
    }

    private fun setUpBroadcast() {
        txViewModel.broadcastErc20SendTx.observe(viewLifecycleOwner) { txResponse ->
            Intent(requireContext(), TxResultActivity::class.java).apply {
                if (txResponse?.isNotEmpty() == true) {
                    putExtra("isSuccess", true)
                    putExtra("txHash", txResponse)
                } else {
                    putExtra("isSuccess", false)
                    putExtra("errorMsg", txResponse)
                }
                putExtra("selectedChain", selectedChain.tag)
                putExtra("txResultType", TxResultType.EVM.toString())
                startActivity(this)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}