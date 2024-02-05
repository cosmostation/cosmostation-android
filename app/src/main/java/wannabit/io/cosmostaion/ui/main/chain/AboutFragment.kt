package wannabit.io.cosmostaion.ui.main.chain

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
            selectedChain.param?.params?.let {
                if (Prefs.language == BaseUtils.LANGUAGE_KOREAN || Locale.getDefault().language == "ko") {
                    chainDescription.text = it.chainlistParams?.description?.ko
                } else if (Prefs.language == BaseUtils.LANGUAGE_ENGLISH || Locale.getDefault().language == "en") {
                    chainDescription.text = it.chainlistParams?.description?.en
                } else {
                    chainDescription.text = it.chainlistParams?.description?.ja
                }

                val unBondingTime = unBondingTime(selectedChain)
                val inflation = try {
                    (it.mintInflation?.inflation ?: 0).toString().toBigDecimal().movePointRight(2)
                        .setScale(2, RoundingMode.DOWN)
                } catch (e: NumberFormatException) {
                    BigDecimal("0.00")
                }
                val apr = try {
                    (it.apr ?: 0).toString().toBigDecimal().movePointRight(2)
                        .setScale(2, RoundingMode.DOWN)
                } catch (e: NumberFormatException) {
                    BigDecimal("0.00")
                }
                unbondingTime.text = unBondingTime.toString() + " Days"
                chainInflation.text = formatPercent(inflation.toPlainString())
                chainApr.text = formatPercent(apr.toPlainString())
            }
        }
    }

    private fun clickAction() {
        binding.apply {
            selectedChain.param?.params?.chainlistParams?.about?.let { about ->
                if (about.website?.isNotEmpty() == true) {
                    website.setOnClickListener {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(about.website)))
                    }
                }

                if (about.twitter?.isNotEmpty() == true) {
                    twitter.setOnClickListener {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(about.twitter)))
                    }
                }

                if (about.github?.isNotEmpty() == true) {
                    github.setOnClickListener {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(about.github)))
                    }
                }

                if (about.blog?.isNotEmpty() == true) {
                    blog.setOnClickListener {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(about.blog)))
                    }
                }

                if (about.coingecko?.isNotEmpty() == true) {
                    coingecko.setOnClickListener {
                        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(about.coingecko)))
                    }
                }
            }
        }
    }

    private fun unBondingTime(selectedChain: CosmosLine?): Int? {
        var result: Int? = 0
        var unBondingTime: String? = ""
        selectedChain?.param?.params?.let { param ->
            unBondingTime = param.stakingParams?.params?.unbondingTime ?: "0"
            result = unBondingTime?.replace("s", "")?.toInt()?.div(60)?.div(60)?.div(24)
        }
        return result
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}