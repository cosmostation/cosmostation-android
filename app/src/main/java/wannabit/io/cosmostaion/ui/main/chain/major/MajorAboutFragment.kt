package wannabit.io.cosmostaion.ui.main.chain.major

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.gson.JsonObject
import com.google.gson.JsonPrimitive
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.BaseChain
import wannabit.io.cosmostaion.chain.majorClass.ChainBitCoin86
import wannabit.io.cosmostaion.chain.majorClass.ChainIota
import wannabit.io.cosmostaion.chain.majorClass.ChainSolana
import wannabit.io.cosmostaion.chain.majorClass.ChainSui
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.formatPercent
import wannabit.io.cosmostaion.common.formatTxTime
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentAboutBinding
import java.math.RoundingMode
import java.util.Locale

class MajorAboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: BaseChain
    private var chainParam: JsonObject? = JsonObject()

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: BaseChain): MajorAboutFragment {
            val args = Bundle().apply {
                putParcelable("selectedChain", selectedChain)
            }
            val fragment = MajorAboutFragment()
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
            rewardTitle.visibility = View.GONE
            rewardCopyMsg.visibility = View.GONE
            rewardView.visibility = View.GONE

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedChain", BaseChain::class.java)
                    ?.let { selectedChain = it }
            } else {
                (arguments?.getParcelable("selectedChain") as? BaseChain)?.let {
                    selectedChain = it
                }
            }
            chainParam = selectedChain.getChainParam()

            chainName.text = selectedChain.getChainName()
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

                val chainIdCosmos = it.getAsJsonPrimitive("chain_id") ?: JsonPrimitive("")
                if (chainIdCosmos.asString.isEmpty()) {
                    chainIdCosmosLayout.visibility = View.GONE
                    chainIdEvmLayout.visibility = View.GONE
                } else {
                    chainIdCosmosLayout.visibility = View.VISIBLE
                    chainIdEvmLayout.visibility = View.GONE
                    chainIdCosmosInfo.text = chainIdCosmos.asString
                }

                chainNetwork.text = if (selectedChain.isTestnet) {
                    getString(R.string.str_testnet)
                } else {
                    getString(R.string.str_mainnet)
                }

                stakingInfoView.goneOrVisible(selectedChain is ChainBitCoin86 || selectedChain is ChainSolana)
                stakingInfoTitle.goneOrVisible(selectedChain is ChainBitCoin86 || selectedChain is ChainSolana)

                stakingDenom.text = if (selectedChain is ChainSui || selectedChain is ChainIota) {
                    BaseData.getAsset(selectedChain.apiName, selectedChain.getMainAssetDenom())?.symbol
                } else {
                    "-"
                }

                unbondingTime.text = if (selectedChain is ChainSui || selectedChain is ChainIota) {
                    getString(R.string.str_instant)
                } else {
                    "-"
                }
                chainInflation.text = "-"
                when (selectedChain) {
                    is ChainSui -> {
                        val apy = (selectedChain as ChainSui).suiFetcher()?.let { fetcher ->
                            if (fetcher.suiApys.isNotEmpty()) {
                                fetcher.suiApys[0]["apy"].asString?.let { apy ->
                                    formatPercent(
                                        apy.toBigDecimal().movePointRight(2)
                                            .setScale(2, RoundingMode.DOWN)
                                            .toString()
                                    )
                                } ?: run {
                                    "-"
                                }

                            } else {
                                "-"
                            }
                        }
                        chainApr.text = apy
                    }

                    is ChainIota -> {
                        val apy = (selectedChain as ChainIota).iotaFetcher()?.let { fetcher ->
                            if (fetcher.iotaApys.isNotEmpty()) {
                                fetcher.iotaApys[0]["apy"].asString?.let { apy ->
                                    formatPercent(
                                        apy.toBigDecimal().movePointRight(2)
                                            .setScale(2, RoundingMode.DOWN)
                                            .toString()
                                    )
                                } ?: run {
                                    "-"
                                }

                            } else {
                                "-"
                            }
                        }
                        chainApr.text = apy
                    }

                    else -> {
                        chainApr.text = "-"
                    }
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
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
                                        Intent.ACTION_VIEW,
                                        Uri.parse(about.get("coingecko").asString)
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
                        return
                    }
                }
        }
    }

    private fun setUpUpdateData() {
        ApplicationViewModel.shared.updateParamResult.observe(viewLifecycleOwner) {
            initView()
        }
    }

    override fun onDestroyView() {
        _binding = null
        ApplicationViewModel.shared.updateParamResult.removeObservers(viewLifecycleOwner)
        super.onDestroyView()
    }
}