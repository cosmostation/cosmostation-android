package wannabit.io.cosmostaion.ui.main.chain.cosmos

import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.CosmosLine
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.formatPercent
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentAboutBinding
import java.math.BigDecimal
import java.math.RoundingMode
import java.util.Locale

class AboutFragment : Fragment() {

    private var _binding: FragmentAboutBinding? = null
    private val binding get() = _binding!!

    private lateinit var selectedChain: CosmosLine

    companion object {
        @JvmStatic
        fun newInstance(selectedChain: CosmosLine): AboutFragment {
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
        clickAction()
    }

    private fun initView() {
        binding.apply {
            descriptionView.setBackgroundResource(R.drawable.item_bg)
            informationView.setBackgroundResource(R.drawable.item_bg)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                arguments?.getParcelable("selectedChain", CosmosLine::class.java)
                    ?.let { selectedChain = it }
            } else {
                (arguments?.getParcelable("selectedChain") as? CosmosLine)?.let {
                    selectedChain = it
                }
            }

            chainName.text = selectedChain.name.uppercase()
            selectedChain.getChainListParam().let {
                if (Prefs.language == BaseUtils.LANGUAGE_KOREAN || Locale.getDefault().language == "ko") {
                    chainDescription.text = it?.getAsJsonObject("description")?.get("ko")?.asString
                } else if (Prefs.language == BaseUtils.LANGUAGE_ENGLISH || Locale.getDefault().language == "en") {
                    chainDescription.text = it?.getAsJsonObject("description")?.get("en")?.asString
                } else {
                    chainDescription.text = it?.getAsJsonObject("description")?.get("ja")?.asString
                }
            }

            val unBondingTime = unBondingTime(selectedChain)
            val inflation = try {
                val inflation = selectedChain.getChainParam()?.getAsJsonObject("params")
                    ?.getAsJsonObject("minting_inflation")?.get("inflation")?.asString ?: "0"
                inflation.toBigDecimal().movePointRight(2).setScale(2, RoundingMode.DOWN).toString()
            } catch (e: Exception) {
                ""
            }
            val apr = try {
                val apr =
                    selectedChain.getChainParam()?.getAsJsonObject("params")?.get("apr")?.asString
                        ?: "0"
                apr.toBigDecimal().movePointRight(2).setScale(2, RoundingMode.DOWN)
            } catch (e: NumberFormatException) {
                BigDecimal("0.00")
            }

            unbondingTime.text = if (unBondingTime.isNotEmpty()) {
                "$unBondingTime Days"
            } else {
                "-"
            }
            chainInflation.text = if (inflation.isNotEmpty()) {
                formatPercent(inflation)
            } else {
                "-"
            }
            chainApr.text = formatPercent(apr.toPlainString())
        }
    }

    private fun clickAction() {
        binding.apply {
            selectedChain.getChainListParam()?.getAsJsonObject("about")?.let { about ->
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
            }
        }
    }

    private fun unBondingTime(selectedChain: CosmosLine?): String {
        val unBondingTime = selectedChain?.getChainParam()?.getAsJsonObject("params")
            ?.getAsJsonObject("staking_params")?.getAsJsonObject("params")
            ?.get("unbonding_time")?.asString ?: ""
        return if (unBondingTime.isNotEmpty()) {
            unBondingTime.replace("s", "").toInt().div(60).div(60).div(24).toString()
        } else {
            ""
        }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}