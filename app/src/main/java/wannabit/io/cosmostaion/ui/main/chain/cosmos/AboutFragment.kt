package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.content.Intent
import android.graphics.PorterDuff
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.formatPercent
import wannabit.io.cosmostaion.common.formatTxTime
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentAboutBinding
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.tx.option.general.ChangeRewardAddressWarnFragment
import java.math.RoundingMode
import java.util.Locale

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private var chainParam: JsonObject? = JsonObject()

    private var isClickable = true

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): AboutFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = AboutFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
        setUpUpdateData()
    }

    private fun initView() {
        binding.apply {
            listOf(descriptionView, informationView, stakingInfoView, rewardView).forEach {
                it.setBackgroundResource(
                    R.drawable.item_bg
                )
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedChain", BaseChain::class.java)
                    ?.let { selectedChain = it }
            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
            }
            chainParam = selectedChain.getChainParam()

            chainName.text = selectedChain.name.uppercase()
            chainParam?.getAsJsonObject("params")?.getAsJsonObject("chainlist_params")?.let {
                if (Prefs.language == BaseUtils.LANGUAGE_KOREAN || Locale.getDefault().language == "ko") {
                    chainDescription.text = it.getAsJsonObject("description")?.get("ko")?.asString
                } else if (Prefs.language == BaseUtils.LANGUAGE_ENGLISH || Locale.getDefault().language == "en") {
                    chainDescription.text = it.getAsJsonObject("description")?.get("en")?.asString
                } else {
                    chainDescription.text = it.getAsJsonObject("description")?.get("ja")?.asString
                }

                val originTime = it.getAsJsonPrimitive("origin_genesis_time")?.asString ?: ""
                initialTime.text = if (originTime.isEmpty()) {
                    "-"
                } else {
                    formatTxTime(
                        requireContext(), originTime
                    )
                }

                val chainIdCosmos = it.getAsJsonPrimitive("chain_id_cosmos") ?: JsonPrimitive("")
                val chainIdEvm = it.getAsJsonPrimitive("chain_id_evm") ?: JsonPrimitive("")
                if (selectedChain.supportCosmos() && selectedChain.supportEvm) {
                    chainIdCosmosInfo.visibility = View.VISIBLE
                    chainIdEvmInfo.visibility = View.VISIBLE
                    chainIdCosmosTitle.text = getString(R.string.str_chain_id_cosmos)
                    chainIdCosmosInfo.text = chainIdCosmos.asString
                    chainIdEvmInfo.text = chainIdEvm.asString

                } else {
                    chainIdCosmosLayout.visibility = View.VISIBLE
                    chainIdEvmLayout.visibility = View.GONE
                    if (selectedChain.supportCosmos()) {
                        chainIdCosmosInfo.text = chainIdCosmos.asString
                    } else {
                        chainIdCosmosInfo.text = chainIdEvm.asString
                    }
                }

                chainNetwork.text = if (selectedChain.isTestnet) {
                    getString(R.string.str_testnet)
                } else {
                    getString(R.string.str_mainnet)
                }

                stakingDenom.text = if (!selectedChain.supportStaking) {
                    "-"
                } else {
                    BaseData.getAsset(selectedChain.apiName, selectedChain.stakeDenom)?.symbol
                }
                val unBondingTime = unBondingTime()
                unbondingTime.text = if (unBondingTime.isNotEmpty()) {
                    "$unBondingTime Days"
                } else {
                    "-"
                }

                val inflation = try {
                    chainParam?.getAsJsonObject("params")
                        ?.getAsJsonObject("minting_inflation")?.get("inflation")?.asString ?: ""
                } catch (e: Exception) {
                    ""
                }
                chainInflation.text = if (inflation.isNotEmpty()) {
                    formatPercent(
                        inflation.toBigDecimal().movePointRight(2).setScale(2, RoundingMode.DOWN)
                            .toString()
                    )
                } else {
                    "-"
                }

                val apr = try {
                    chainParam?.getAsJsonObject("params")?.get("apr")?.asString
                        ?: ""
                } catch (e: NumberFormatException) {
                    ""
                }
                chainApr.text = if (apr.isNotEmpty()) {
                    formatPercent(
                        apr.toBigDecimal().movePointRight(2).setScale(2, RoundingMode.DOWN)
                            .toString()
                    )
                } else {
                    "-"
                }

                if (selectedChain.supportStaking) {
                    if (selectedChain.address == selectedChain.cosmosFetcher?.rewardAddress) {
                        cautionImg.visibility = View.GONE
                        rewardAddressWarnMsg.visibility = View.GONE
                    } else {
                        cautionImg.visibility = View.VISIBLE
                        rewardAddressWarnMsg.visibility = View.VISIBLE
                        cautionImg.setColorFilter(
                            ContextCompat.getColor(requireContext(), R.color.color_accent_red),
                            PorterDuff.Mode.SRC_IN
                        )
                        rewardAddress.setTextColor(
                            ContextCompat.getColorStateList(
                                requireContext(), R.color.color_accent_red
                            )
                        )
                    }
                    rewardAddress.text = selectedChain.cosmosFetcher?.rewardAddress

                } else {
                    rewardView.visibility = View.GONE
                    rewardTitle.visibility = View.GONE
                    rewardCopyMsg.visibility = View.GONE
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            rewardView.setOnClickListener {
                val clipboard =
                    requireActivity().getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                val clip = ClipData.newPlainText(
                    "address", selectedChain.cosmosFetcher?.rewardAddress
                )
                clipboard.setPrimaryClip(clip)
                requireActivity().makeToast(R.string.str_msg_address_copied)
            }

            rewardChangeAddress.setOnClickListener {
                handleOneClickWithDelay(ChangeRewardAddressWarnFragment.newInstance(selectedChain))
            }

            chainParam?.getAsJsonObject("params")?.getAsJsonObject("chainlist_params")
                ?.getAsJsonObject("about")?.let { about ->
                about.get("website")?.let {
                    if (about.get("website").asString?.isNotEmpty() == true) {
                        website.setOnClickListener {
                            startActivity(
                                Intent(
                                    Intent.ACTION_VIEW, Uri.parse(about.get("website").asString)
                                )
                            )
                        }
                    }
                } ?: run {
                    return
                }

                about.get("github")?.let {
                    if (about.get("github").asString?.isNotEmpty() == true) {
                        github.setOnClickListener {
                            startActivity(
                                Intent(
                                    Intent.ACTION_VIEW, Uri.parse(about.get("github").asString)
                                )
                            )
                        }
                    }

                } ?: run {
                    return
                }

                about.get("twitter")?.let {
                    if (about.get("twitter").asString?.isNotEmpty() == true) {
                        twitter.setOnClickListener {
                            startActivity(
                                Intent(
                                    Intent.ACTION_VIEW, Uri.parse(about.get("twitter").asString)
                                )
                            )
                        }
                    }

                } ?: run {
                    return
                }

                about.get("coingecko")?.let {
                    if (about.get("coingecko").asString?.isNotEmpty() == true) {
                        coingecko.setOnClickListener {
                            startActivity(
                                Intent(
                                    Intent.ACTION_VIEW, Uri.parse(about.get("coingecko").asString)
                                )
                            )
                        }
                    }

                } ?: run {
                    return
                }

                about.get("blog")?.let {
                    if (about.get("blog").asString?.isNotEmpty() == true) {
                        blog.setOnClickListener {
                            startActivity(
                                Intent(
                                    Intent.ACTION_VIEW, Uri.parse(about.get("blog").asString)
                                )
                            )
                        }
                    }

                } ?: run {
                    about.get("medium")?.let {
                        if (about.get("medium").asString?.isNotEmpty() == true) {
                            blog.setOnClickListener {
                                startActivity(
                                    Intent(
                                        Intent.ACTION_VIEW, Uri.parse(about.get("medium").asString)
                                    )
                                )
                            }
                        }
                    }
                }
            }
        }
    }

    private fun unBondingTime(): String {
        val unBondingTime = chainParam?.getAsJsonObject("params")
            ?.getAsJsonObject("staking_params")?.getAsJsonObject("params")
            ?.get("unbonding_time")?.asString ?: ""
        return if (unBondingTime.isNotEmpty()) {
            unBondingTime.replace("s", "").toInt().div(60).div(60).div(24).toString()
        } else {
            ""
        }
    }

    private fun setUpUpdateData() {
        ApplicationViewModel.shared.updateParamResult.observe(viewLifecycleOwner) {
            initView()
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

    override fun onDestroyView() {
        _binding = null
        ApplicationViewModel.shared.updateParamResult.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }
}