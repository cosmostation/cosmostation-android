package wannabit.io.cosmostaion.ui.main.chain.evm

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
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.formatTxTime
import wannabit.io.cosmostaion.common.hexToBigDecimal
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentEvmAboutBinding
import wannabit.io.cosmostaion.data.viewmodel.ApplicationViewModel
import java.util.Locale

class EvmAboutFragment : Fragment() {

    private var _binding: FragmentEvmAboutBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedEvmChain: BaseChain
    private var chainParam: JsonObject? = JsonObject()

    companion object {
        @JvmStatic
        fun newInstance(selectedEvmChain: BaseChain): EvmAboutFragment {
            val args = Bundle().apply {
                putParcelable("selectedEvmChain", selectedEvmChain)
            }
            val fragment = EvmAboutFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentEvmAboutBinding.inflate(layoutInflater, container, false)
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
            listOf(descriptionView, informationView).forEach {
                it.setBackgroundResource(
                    R.drawable.item_bg
                )
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedEvmChain", BaseChain::class.java)
                    ?.let { selectedEvmChain = it }
            } else {
                (arguments?.getParcelable("selectedEvmChain") as? BaseChain)?.let {
                    selectedEvmChain = it
                }
            }
            chainParam = selectedEvmChain.getChainParam()

            chainName.text = selectedEvmChain.getChainName()
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
                    ""
                } else {
                    formatTxTime(
                        requireContext(), originTime
                    )
                }

                val chainIdEvm = it.getAsJsonPrimitive("chain_id_evm") ?: JsonPrimitive("")
                chainIdEvmInfo.text = chainIdEvm.asString.hexToBigDecimal().toString()

                val coinForGas = it.getAsJsonPrimitive("gas_asset_symbol") ?: JsonPrimitive("")
                coinForGasInfo.text = coinForGas.asString

                chainNetwork.text = if (selectedEvmChain.isTestnet) {
                    getString(R.string.str_testnet)
                } else {
                    getString(R.string.str_mainnet)
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