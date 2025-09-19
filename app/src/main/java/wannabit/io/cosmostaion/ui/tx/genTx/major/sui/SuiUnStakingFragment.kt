package wannabit.io.cosmostaion.ui.tx.genTx.major.sui

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import com.google.gson.JsonObject
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.fetcher.moveValidatorImg
import wannabit.io.cosmostaion.chain.fetcher.moveValidatorName
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.dpToPx
import wannabit.io.cosmostaion.common.formatAmount
import wannabit.io.cosmostaion.common.formatAssetValue
import wannabit.io.cosmostaion.common.setImageFromSvg
import wannabit.io.cosmostaion.common.setTokenImg
import wannabit.io.cosmostaion.common.showToast
import wannabit.io.cosmostaion.common.updateButtonView
import wannabit.io.cosmostaion.databinding.FragmentSuiUnstakingBinding
import wannabit.io.cosmostaion.databinding.ItemSegmentedFeeBinding
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.tx.TransferTxResultActivity
import wannabit.io.cosmostaion.ui.tx.genTx.BaseTxFragment
import wannabit.io.cosmostaion.ui.tx.genTx.SuiTxType
import wannabit.io.cosmostaion.ui.tx.genTx.TransferStyle
import java.math.BigDecimal
import java.math.RoundingMode

class SuiUnStakingFragment(
    private val selectedChain: BaseChain, private val staked: Pair<String, JsonObject>?
) : BaseTxFragment() {

    private var _binding: FragmentSuiUnstakingBinding? = null
    private val binding get() = _binding!!

    private var selectedFeePosition = 0
    private var suiFeeBudget = BigDecimal.ZERO

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSuiUnstakingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
        setUpSimulate()
        setUpBroadcast()
    }

    private fun initView() {
        binding.apply {
            BaseData.getAsset(selectedChain.apiName, selectedChain.getStakeAssetDenom())?.let { asset ->
                titleUnstakeImg.setTokenImg(asset)
                titleUnstake.text = getString(R.string.title_unstaking, asset.symbol)
            }

            listOf(stakeCoinView, feeView).forEach {
                it.setBackgroundResource(
                    R.drawable.cell_bg
                )
            }
            segmentView.setBackgroundResource(R.drawable.segment_fee_bg)
            btnFee.visibility = View.GONE
            initFee()
            initValidatorView()
        }
    }

    private fun initValidatorView() {
        binding.apply {
            (selectedChain as ChainSui).suiFetcher()?.let { fetcher ->
                fetcher.suiValidators.firstOrNull { it["suiAddress"].asString == staked?.first }
                    ?.let { validator ->
                        monikerImg.setImageFromSvg(
                            validator.moveValidatorImg(), R.drawable.icon_default_vaildator
                        )
                        monikerName.text = validator.moveValidatorName()
                        objectId.text = staked?.second?.get("stakedSuiId")?.asString

                        val principal = try {
                            staked?.second?.get("principal")?.asLong?.toBigDecimal()
                                ?.movePointLeft(9)?.setScale(9, RoundingMode.DOWN)
                        } catch (e: Exception) {
                            BigDecimal.ZERO
                        }

                        val estimatedReward = try {
                            staked?.second?.get("estimatedReward")?.asLong?.toBigDecimal()
                                ?.movePointLeft(9)?.setScale(9, RoundingMode.DOWN)
                        } catch (e: Exception) {
                            BigDecimal.ZERO
                        }

                        principalTxt.text = formatAmount(principal.toString(), 9)
                        earned.text = formatAmount(estimatedReward.toString(), 9)
                        totalStaked.text =
                            formatAmount(principal?.add(estimatedReward).toString(), 9)
                        startEarning.text =
                            "Epoch #" + staked?.second?.get("stakeActiveEpoch")?.asString
                    }

                txSimulate()
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

            BaseData.getAsset(selectedChain.apiName, selectedChain.getGasAssetDenom())?.let { asset ->
                feeTokenImg.setTokenImg(asset)
                feeToken.text = asset.symbol
                suiFeeBudget =
                    (selectedChain as ChainSui).suiFetcher()?.suiBaseFee(SuiTxType.SUI_UNSTAKE)
                updateFeeView()
            }
        }
    }

    private fun updateFeeView() {
        binding.apply {
            (selectedChain as ChainSui).apply {
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
            btnUnstake.setOnClickListener {
                Intent(requireContext(), PasswordCheckActivity::class.java).apply {
                    suiUnStakeresultLauncher.launch(this)
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

    private fun txSimulate() {
        binding.apply {
            btnUnstake.updateButtonView(false)
            if (staked == null) return
            backdropLayout.visibility = View.VISIBLE
            (selectedChain as ChainSui).apply {
                suiFetcher()?.let { fetcher ->
                    staked.second.get("stakedSuiId").asString?.let { objectId ->
                        txViewModel.suiUnStakeSimulate(
                            fetcher, mainAddress, objectId, suiFeeBudget.toString()
                        )
                    }
                }
            }
        }
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

    private val suiUnStakeresultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK && isAdded) {
                binding.backdropLayout.visibility = View.VISIBLE
                (selectedChain as ChainSui).apply {
                    suiFetcher()?.let { fetcher ->
                        staked?.second?.get("stakedSuiId")?.asString?.let { objectId ->
                            txViewModel.suiUnStakeBroadcast(
                                fetcher, mainAddress, objectId, suiFeeBudget.toString(), this
                            )
                        }
                    }
                }
            }
        }

    private fun setUpBroadcast() {
        txViewModel.suiBroadcast.observe(viewLifecycleOwner) { response ->
            if (response["result"] != null) {
                val status =
                    response["result"].asJsonObject["effects"].asJsonObject["status"].asJsonObject["status"].asString
                Intent(requireContext(), TransferTxResultActivity::class.java).apply {
                    if (status != "success") {
                        putExtra("isSuccess", false)
                    } else {
                        putExtra("isSuccess", true)
                    }
                    putExtra("txHash", response["result"].asJsonObject["digest"].asString)
                    putExtra("fromChainTag", selectedChain.tag)
                    putExtra("transferStyle", TransferStyle.SUI_STYLE.ordinal)
                    putExtra("suiResult", response.toString())
                    startActivity(this)
                }
                dismiss()
            }
        }
    }

    private fun isBroadCastTx(isSuccess: Boolean) {
        binding.apply {
            backdropLayout.visibility = View.GONE
            btnUnstake.updateButtonView(isSuccess)
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
        txViewModel.suiBroadcast.removeObservers(viewLifecycleOwner)
    }
}