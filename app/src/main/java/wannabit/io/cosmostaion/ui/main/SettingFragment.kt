package wannabit.io.cosmostaion.ui.main

import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.chain.cosmosClass.ChainCosmos
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.goneOrVisible
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentSettingBinding
import wannabit.io.cosmostaion.ui.main.setting.SettingBottomFragment
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import java.util.Locale

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private val walletViewModel: WalletViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        clickAction()
        switchAction()
    }

    private fun initView() {
        binding.apply {
            accountView.setBackgroundResource(R.drawable.item_bg)
            chainView.setBackgroundResource(R.drawable.item_bg)
            addressBookView.setBackgroundResource(R.drawable.item_bg)

            languageView.setBackgroundResource(R.drawable.item_bg)
            currencyView.setBackgroundResource(R.drawable.item_bg)
            priceView.setBackgroundResource(R.drawable.item_bg)
            alarmView.setBackgroundResource(R.drawable.item_bg)
            appLockView.setBackgroundResource(R.drawable.item_bg)
            autoPassView.setBackgroundResource(R.drawable.item_bg)

            mintscanView.setBackgroundResource(R.drawable.item_bg)
            homepageView.setBackgroundResource(R.drawable.item_bg)
            blogView.setBackgroundResource(R.drawable.item_bg)
            telegramView.setBackgroundResource(R.drawable.item_bg)

            termView.setBackgroundResource(R.drawable.item_bg)
            privacyView.setBackgroundResource(R.drawable.item_bg)
            githubView.setBackgroundResource(R.drawable.item_bg)
            versionView.setBackgroundResource(R.drawable.item_bg)

            walletUpdateView()
            generalUpdateView()

            version.text = "v " + BuildConfig.VERSION_NAME
        }
    }

    private fun walletUpdateView() {
        val baseAccount = BaseData.baseAccount
        baseAccount?.let { account ->
            binding.apply {
                accountName.text = account.name
                supportChainCnt.text = allCosmosLines().count().toString()
                favoriteAddressCnt.text = "0"
            }
        }
    }

    private fun generalUpdateView() {
        binding.apply {
            when (Prefs.language) {
                BaseUtils.LANGUAGE_ENGLISH -> {
                    language.text = getString(R.string.str_language_en)
                }
                BaseUtils.LANGUAGE_KOREAN -> {
                    language.text = getString(R.string.str_language_kr)
                }
                else -> {
                    language.text = getString(R.string.str_system)
                }
            }

            currency.text = BaseData.currencyName()
            when (Prefs.priceStyle) {
                0 -> {
                    priceUpImg.setImageResource(R.drawable.icon_price_up)
                    priceDownImg.setImageResource(R.drawable.icon_price_down)
                }
                else -> {
                    priceUpImg.setImageResource(R.drawable.icon_price_up_reverse)
                    priceDownImg.setImageResource(R.drawable.icon_price_down_reverse)
                }
            }

            autoPass.text = BaseData.autoPass(requireContext())
        }
    }

    private fun clickAction() {
        var isClickable = true
        binding.apply {
            languageView.setOnClickListener {
                val bottomSheet = SettingBottomFragment(SettingType.LANGUAGE)
                if (isClickable) {
                    isClickable = false
                    bottomSheet.show(parentFragmentManager, SettingBottomFragment::class.java.name)

                    Handler().postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            currencyView.setOnClickListener {
                val bottomSheet = SettingBottomFragment(SettingType.CURRENCY)
                if (isClickable) {
                    isClickable = false
                    bottomSheet.show(parentFragmentManager, SettingBottomFragment::class.java.name)
                    parentFragmentManager.setFragmentResultListener("currency", this@SettingFragment) { _, _ ->
                        currency.text = BaseData.currencyName()
                        walletViewModel.price(BaseData.currencyName(), true)
                    }

                    Handler().postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            priceView.setOnClickListener {
                val bottomSheet = SettingBottomFragment(SettingType.PRICE_STATUS)
                if (isClickable) {
                    isClickable = false
                    bottomSheet.show(parentFragmentManager, SettingBottomFragment::class.java.name)
                    parentFragmentManager.setFragmentResultListener("price", this@SettingFragment) { _, _ ->
                        walletViewModel.changeObserve()
                        generalUpdateView()
                    }

                    Handler().postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            autoPassView.setOnClickListener {
                val bottomSheet = SettingBottomFragment(SettingType.AUTO_PASS)
                if (isClickable) {
                    isClickable = false
                    bottomSheet.show(parentFragmentManager, SettingBottomFragment::class.java.name)
                    parentFragmentManager.setFragmentResultListener("autoPass", this@SettingFragment) { _, _ ->
                        generalUpdateView()
                    }

                    Handler().postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            mintscanView.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(BaseConstant.EXPLORER_BASE_URL)))
            }

            homepageView.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(BaseConstant.COSMOSTATION_HOMEPAGE)))
            }

            blogView.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(BaseConstant.COSMOSTATION_BLOG)))
            }

            telegramView.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(BaseConstant.COSMOSTATION_TELEGRAM)))
            }

            termView.setOnClickListener {
                if (Prefs.language == BaseUtils.LANGUAGE_KOREAN || Locale.getDefault().language == "ko") {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(BaseConstant.COSMOSTATION_TERM_KR)))
                } else {
                    startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(BaseConstant.COSMOSTATION_TERM_EN)))
                }
            }

            privacyView.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(BaseConstant.COSMOSTATION_PRIVACY_POLICY)))
            }

            githubView.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(BaseConstant.COSMOSTATION_GITHUB)))
            }

            versionView.setOnClickListener {
                startActivity(Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + requireActivity().packageName)))
            }
        }
    }

    private fun switchAction() {
        binding.apply {
            if (alarmSwitch.isChecked) {
                alarmSwitch.thumbDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_on)
            } else {
                alarmSwitch.thumbDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_off)
            }

            alarmSwitch.setOnCheckedChangeListener { _, isChecked ->
                val thumbDrawable: Drawable?
                if (isChecked) {
                    thumbDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_on)
                } else {
                    thumbDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_off)
                }
                alarmSwitch.thumbDrawable = thumbDrawable
            }

            if (appLockSwitch.isChecked) {
                appLockSwitch.thumbDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_on)
            } else {
                appLockSwitch.thumbDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_off)
            }

            appLockSwitch.setOnCheckedChangeListener { _, isChecked ->
                val thumbDrawable: Drawable?
                if (isChecked) {
                    thumbDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_on)
                } else {
                    thumbDrawable = ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_off)
                }
                appLockSwitch.thumbDrawable = thumbDrawable
            }
        }

    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

enum class SettingType { LANGUAGE, CURRENCY, PRICE_STATUS, AUTO_PASS }