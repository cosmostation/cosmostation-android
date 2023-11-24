package wannabit.io.cosmostaion.ui.tx.step.kava

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
import androidx.lifecycle.ViewModelProvider
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.chain.cosmosClass.ChainBinanceBeacon
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.data.repository.chain.KavaRepositoryImpl
import wannabit.io.cosmostaion.databinding.FragmentBep3Binding
import wannabit.io.cosmostaion.ui.dialog.tx.AmountSelectListener
import wannabit.io.cosmostaion.ui.dialog.tx.address.AddressFragment
import wannabit.io.cosmostaion.ui.dialog.tx.address.AddressListener
import wannabit.io.cosmostaion.ui.dialog.tx.address.AddressType
import wannabit.io.cosmostaion.ui.dialog.tx.kava.Bep3InsertAmountFragment
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TxResultActivity
import wannabit.io.cosmostaion.ui.tx.step.BaseTxFragment
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModel
import wannabit.io.cosmostaion.ui.viewmodel.chain.KavaViewModelProviderFactory
import java.math.BigDecimal
import java.math.RoundingMode

class Bep3Fragment(
    private val fromChain: CosmosLine,
    private val denom: String
) : BaseTxFragment() {

    private var _binding: FragmentBep3Binding? = null
    private val binding get() = _binding!!

    private lateinit var kavaViewModel: KavaViewModel

    private var toChains: CosmosLine? = null

    private var availableAmount: BigDecimal = BigDecimal.ZERO
    private var toSendAmount = ""
    private var existedAddress = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBep3Binding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViewModel()
        initView()
        clickAction()
        setUpBroadcast()
    }

    private fun initViewModel() {
        val kavaRepository = KavaRepositoryImpl()
        val kavaViewModelProviderFactory = KavaViewModelProviderFactory(kavaRepository)
        kavaViewModel = ViewModelProvider(this, kavaViewModelProviderFactory)[KavaViewModel::class.java]
    }

    private fun initView() {
        binding.apply {
            recipientView.setBackgroundResource(R.drawable.cell_bg)
            sendAssetView.setBackgroundResource(R.drawable.cell_bg)
            addressView.setBackgroundResource(R.drawable.cell_bg)

            BaseData.baseAccount?.let { baseAccount ->
                if (fromChain is ChainBinanceBeacon) {

                } else {
                    toChains = baseAccount.allCosmosLineChains.firstOrNull { it.name == "BNB Beacon" }
//                    toChains = baseAccount.allCosmosLineChains.filter { it.name == "BNB Beacon" }.toMutableList()
                    fromChainImg.setImageResource(R.drawable.chain_kava)
                    fromChainName.text = "KAVA"

                    toChainImg.setImageResource(R.drawable.chain_binance)
                    toChainName.text = "BNB Beacon"

                    BaseData.getAsset(fromChain.apiName, denom)?.let { asset ->
                        tokenImg.setTokenImg(asset)
                        tokenName.text = asset.symbol
                    }
                    availableAmount = fromChain.balanceAmount(denom)
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

            } else {
                BaseData.getAsset(fromChain.apiName, denom)?.let { asset ->
                    asset.decimals?.let { decimal ->
                        val dpAmount = BigDecimal(toAmount).movePointLeft(decimal)
                            .setScale(decimal, RoundingMode.DOWN)
                        val price = BaseData.getPrice(asset.coinGeckoId)
                        val value = price.multiply(dpAmount)

                        sendAmount.text = formatAmount(dpAmount.toPlainString(), decimal)
                        sendValue.text = formatAssetValue(value)
                    }
                }
            }
        }
    }

    private fun updateAddressView(address: String) {
        existedAddress = address
        binding.apply {
            if (address.isEmpty()) {
                recipientAddressMsg.text = getString(R.string.str_tap_for_add_address_msg)
            } else {
                recipientAddress.text = address
            }
            recipientAddressMsg.visibleOrGone(address.isEmpty())
            recipientAddress.visibleOrGone(address.isNotEmpty())
        }
    }

    private fun clickAction() {
        var isClickable = true
        binding.apply {
            sendAssetView.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    Bep3InsertAmountFragment(
                        fromChain,
                        denom,
                        availableAmount,
                        toSendAmount,
                        object : AmountSelectListener {
                            override fun select(toAmount: String) {
                                updateAmountView(toAmount)
                            }

                        }).show(requireActivity().supportFragmentManager, Bep3InsertAmountFragment::class.java.name)

                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            addressView.setOnClickListener {
                if (isClickable) {
                    isClickable = false
                    AddressFragment(
                        fromChain,
                        toChains,
                        existedAddress,
                        AddressType.BEP3_TRANSFER,
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

            btnSend.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    bep3SendResultLauncher.launch(this)
                    requireActivity().overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_fade_out)
                }
            }
        }
    }

    private val bep3SendResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
//                txViewModel.broadClaimIncentive(getChannel(selectedChain), selectedChain.address, incentive, txFee, txMemo, selectedChain)
            }
        }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnSend.updateButtonView(isSuccess)
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
                putExtra("selectedChain", fromChain.tag)
                val hash = txResponse.txhash
                if (!TextUtils.isEmpty(hash)) putExtra("txHash", hash)
                startActivity(this)
            }
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}