package wannabit.io.cosmostaion.ui.tx.genTx.major.sui

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
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.JsonObject
import com.sui.rpc.v2.ObjectProto
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.getStringField
import wannabit.io.cosmostaion.chain.fetcher.suiCoinType
import wannabit.io.cosmostaion.chain.fetcher.suiNftUrl
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.chain.majorClass.SUI_MAIN_DENOM
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setChainLogo
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.common.visibleOrGone
import wannabit.io.cosmostaion.databinding.FragmentNftTransferBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TransferTxResultActivity
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import wannabit.io.cosmostaion.ui.tx.genTx.SendAssetType
import wannabit.io.cosmostaion.ui.tx.genTx.SuiTxType
import wannabit.io.cosmostaion.ui.tx.genTx.TransferStyle
import wannabit.io.cosmostaion.ui.tx.option.address.AddressListener
import wannabit.io.cosmostaion.ui.tx.option.address.TransferAddressFragment
import java.math.BigDecimal
import java.math.RoundingMode

class SuiNftTransferFragment(
    private val fromChain: BaseChain, private val toSendSuiNFT: ObjectProto.Object?
) : BaseTxFragment() {

    private var _binding: FragmentNftTransferBinding? = null
    private val binding get() = _binding!!

    private lateinit var toChain: BaseChain
    private val sendAssetType = SendAssetType.SUI_NFT

    private var selectedFeePosition = 0
    private var suiFeeBudget = BigDecimal.ZERO
    private var toAddress = ""

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentNftTransferBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initData()
        setUpClickAction()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initData() {
        binding.apply {
            initFee()
            listOf(
                recipientChainView, addressView, sendAssetView, memoView, feeView
            ).forEach { it.setBackgroundResource(R.drawable.cell_bg) }
            chainImg.alpha = 0.2f
            memoView.visibility = View.GONE
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)
            btnFee.visibility = View.GONE

            initToChain()
            initNft()
        }
    }

    private fun initToChain() {
        binding.apply {
            toChain = fromChain
            chainImg.setChainLogo(toChain)
            chainName.text = toChain.getChainName()?.uppercase()
        }
    }

    private fun initNft() {
        binding.apply {
            nftImg.clipToOutline = true
            toSendSuiNFT?.let { data ->
                val imageUrl = if (data.hasDisplay()) data.display.output.suiNftUrl() else null
                imageUrl?.let { url ->
                    Glide.with(requireActivity()).load(url).diskCacheStrategy(
                        DiskCacheStrategy.ALL
                    ).placeholder(R.drawable.icon_nft_default).error(R.drawable.icon_nft_default)
                        .into(nftImg)
                } ?: run {
                    nftImg.setImageResource(R.drawable.icon_nft_default)
                }
                nftTitle.text = data.objectId

                val name = if (data.hasDisplay()) data.display.output.getStringField("name") else null
                if (name.isNullOrEmpty()) {
                    nftId.visibility = View.GONE
                } else {
                    nftId.text = name
                }
            }
        }
    }

    private fun initFee() {
        binding.apply {
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

            feeSegment.visibility = View.VISIBLE
            val suiGasTitle = listOf(
                "Default"
            )
            for (i in suiGasTitle.indices) {
                val segmentView = ItemSegmentedFeeBinding.inflate(layoutInflater)
                feeSegment.addView(
                    segmentView.root,
                    i,
                    LinearLayout.LayoutParams(0, dpToPx(requireContext(), 32), 1f)
                )
                segmentView.btnTitle.text = suiGasTitle[i]
            }
            feeSegment.setPosition(0, false)
            selectedFeePosition = 0

            BaseData.getAsset(fromChain.apiName, fromChain.getGasAssetDenom())?.let { asset ->
                feeTokenImg.setTokenImg(asset)
                feeToken.text = asset.symbol
                suiFeeBudget =
                    (fromChain as ChainSui).suiFetcher()?.suiBaseFee(SuiTxType.SUI_SEND_NFT)
                updateFeeView()
            }
        }
    }

    private fun updateRecipientAddressView(address: String) {
        binding.apply {
            toAddress = address
            if (address.isEmpty()) {
                recipientAddressMsg.text = getString(R.string.str_tap_for_add_address_msg)
            } else {
                recipientAddress.text = address
            }
            recipientAddressMsg.visibleOrGone(address.isEmpty())
            recipientAddress.visibleOrGone(address.isNotEmpty())
        }
        txSimulate()
    }

    private fun updateFeeView() {
        binding.apply {
            (fromChain as ChainSui).apply {
                val coinGeckoId = BaseData.getAsset(apiName, getGasAssetDenom())?.coinGeckoId
                val price = BaseData.getPrice(coinGeckoId)
                val dpBudget = suiFeeBudget.movePointLeft(9).setScale(9, RoundingMode.DOWN)
                val value = price.multiply(dpBudget)

                feeAmount.text = formatAmount(dpBudget.toPlainString(), 9)
                feeValue.text = formatAssetValue(value)
            }
        }
    }

    @SuppressLint("WrongConstant")
    private fun setUpClickAction() {
        binding.apply {
            addressView.setOnClickListener {
                handleOneClickWithDelay(
                    TransferAddressFragment.newInstance(fromChain,
                        toChain,
                        toAddress,
                        sendAssetType,
                        object : AddressListener {
                            override fun selectAddress(address: String, memo: String) {
                                updateRecipientAddressView(address)
                            }
                        })
                )
            }

            btnNftSend.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    nftSendResultLauncher.launch(this)
                    if (Build.VERSION.SDK_INT >= 34) {
                        requireActivity().overrideActivityTransition(
                            Activity.OVERRIDE_TRANSITION_OPEN,
                            R.anim.anim_slide_in_bottom,
                            R.anim.anim_fade_out
                        )
                    } else {
                        requireActivity().overridePendingTransition(
                            R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                        )
                    }
                }
            }
        }
    }

    private val nftSendResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                (fromChain as ChainSui).apply {
                    suiFetcher()?.let { fetcher ->
                        val gasCoin = suiGasInput()
                        if (toSendSuiNFT != null && gasCoin != null) {
                            txViewModel.suiNftSendBroadcast(
                                requireContext(),
                                fetcher,
                                mainAddress,
                                toAddress,
                                toSendSuiNFT,
                                suiFeeBudget.toString(),
                                gasCoin,
                                this
                            )
                        }
                    }
                }
            }
        }

    private fun txSimulate() {
        binding.apply {
            if (toAddress.isEmpty()) return
            btnNftSend.updateButtonView(false)
            backdropLayout.visibility = View.VISIBLE
            (fromChain as ChainSui).apply {
                suiFetcher()?.let { fetcher ->
                    val gasCoin = suiGasInput()
                    if (toSendSuiNFT != null && gasCoin != null) {
                        txViewModel.suiNftSendSimulate(
                            requireContext(),
                            fetcher,
                            mainAddress,
                            toAddress,
                            toSendSuiNFT,
                            suiFeeBudget.toString(),
                            gasCoin
                        )
                    }
                }
            }
        }
    }

    private fun suiGasInput(): ObjectProto.Object? {
        (fromChain as ChainSui).suiFetcher()?.let { fetcher ->
            return fetcher.suiObjects.firstOrNull { it.objectType.suiCoinType() == SUI_MAIN_DENOM }
        }
        return null
    }

    private fun setUpSimulate() {
        txViewModel.simulate.observe(viewLifecycleOwner) { gasUsed ->
            suiFeeBudget = gasUsed?.toBigDecimal()
            updateFeeView()
            isBroadCastTx(true)
        }

        txViewModel.errorMessage.observe(viewLifecycleOwner) { response ->
            isBroadCastTx(false)
            requireContext().showToast(view, response, true)
            return@observe
        }
    }

    private fun setUpBroadcast() {
        txViewModel.suiBroadcast.observe(viewLifecycleOwner) { response ->
            if (response != null) {
                val isSuccess = response.transaction.effects.status.success
                val suiResultJson = JsonObject().apply {
                    add("result", JsonObject().apply {
                        add("effects", JsonObject().apply {
                            add("status", JsonObject().apply {
                                addProperty("status", if (isSuccess) "success" else "failure")
                                if (!isSuccess) {
                                    addProperty("error", response.transaction.effects.status.error.description)
                                }
                            })
                        })
                    })
                }

                Intent(requireContext(), TransferTxResultActivity::class.java).apply {
                    putExtra("isSuccess", isSuccess)
                    putExtra("txHash", response.transaction.digest)
                    putExtra("fromChainTag", fromChain.tag)
                    putExtra("transferStyle", TransferStyle.SUI_STYLE.ordinal)
                    putExtra("suiResult", suiResultJson.toString())
                    startActivity(this)
                }
                dismiss()
            }
        }
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.backdropLayout.visibility = View.GONE
        binding.btnNftSend.updateButtonView(isSuccess)
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

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        txViewModel.suiBroadcast.removeObservers(viewLifecycleOwner)
    }
}