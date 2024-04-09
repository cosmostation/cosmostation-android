package wannabit.io.cosmostaion.ui.main.chain.evm

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.EthereumLine
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentAboutBinding
import java.util.Locale

class EvmAboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedEvmChain: EthereumLine

    companion object {
        @JvmStatic
        fun newInstance(selectedEvmChain: EthereumLine): EvmAboutFragment {
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
        _binding = FragmentAboutBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
    }

    private fun initView() {
        binding.apply {
            descriptionView.setBackgroundResource(R.drawable.item_bg)
            informationTitle.visibility = View.GONE
            informationView.visibility = View.GONE

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedEvmChain", EthereumLine::class.java)
                    ?.let { selectedEvmChain = it }
            } else {
                (arguments?.getParcelable("selectedEvmChain") as? EthereumLine)?.let {
                    selectedEvmChain = it
                }
            }

            chainName.text = selectedEvmChain.name.uppercase()
            selectedEvmChain.getChainListParam().let {
                if (Prefs.language == BaseUtils.LANGUAGE_KOREAN || Locale.getDefault().language == "ko") {
                    chainDescription.text = it?.getAsJsonObject("description")?.get("ko")?.asString
                } else if (Prefs.language == BaseUtils.LANGUAGE_ENGLISH || Locale.getDefault().language == "en") {
                    chainDescription.text = it?.getAsJsonObject("description")?.get("en")?.asString
                } else {
                    chainDescription.text = it?.getAsJsonObject("description")?.get("ja")?.asString
                }
            }
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            selectedEvmChain.getChainListParam()?.getAsJsonObject("about")?.let { about ->
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
                    return
                }
            }
        }
    }
}