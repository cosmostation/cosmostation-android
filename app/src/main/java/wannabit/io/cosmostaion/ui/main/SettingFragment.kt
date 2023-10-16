package wannabit.io.cosmostaion.ui.main

import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Handler
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.common.toMoveAnimation
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentSettingBinding
import wannabit.io.cosmostaion.ui.main.setting.SettingBottomFragment
import wannabit.io.cosmostaion.ui.main.setting.account.AccountActivity
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
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
        checkAccountStatus()
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

    override fun onResume() {
        super.onResume()
        onUpdateSwitch()
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

    private fun checkAccountStatus() {
        ApplicationViewModel.shared.currentAccountResult.observe(viewLifecycleOwner) {
            walletUpdateView()
        }
    }

    private fun generalUpdateView() {
        binding.apply {
            when (Prefs.language) {
                BaseUtils.LANGUAGE_ENGLISH -> {
                    language.text = getString(R.string.title_language_en)
                }
                BaseUtils.LANGUAGE_KOREAN -> {
                    language.text = getString(R.string.title_language_kr)
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
            accountView.setOnClickListener {
                Intent(requireContext(), AccountActivity::class.java).apply {
                    startActivity(this)
                    requireActivity().toMoveAnimation()
                }
            }

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
                    parentFragmentManager.setFragmentResultListener(
                        "currency",
                        this@SettingFragment
                    ) { _, _ ->
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
                    parentFragmentManager.setFragmentResultListener(
                        "price",
                        this@SettingFragment
                    ) { _, _ ->
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
                    parentFragmentManager.setFragmentResultListener(
                        "autoPass",
                        this@SettingFragment
                    ) { _, _ ->
                        generalUpdateView()
                    }

                    Handler().postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }

            mintscanView.setOnClickListener {
                startActivity(
                    Intent(Intent.ACTION_VIEW,
                        Uri.parse(CosmostationConstants.EXPLORER_BASE_URL)
                    )
                )
            }

            homepageView.setOnClickListener {
                startActivity(
                    Intent(Intent.ACTION_VIEW,
                        Uri.parse(CosmostationConstants.COSMOSTATION_HOMEPAGE)
                    )
                )
            }

            blogView.setOnClickListener {
                startActivity(
                    Intent(Intent.ACTION_VIEW,
                        Uri.parse(CosmostationConstants.COSMOSTATION_BLOG)
                    )
                )
            }

            telegramView.setOnClickListener {
                startActivity(
                    Intent(Intent.ACTION_VIEW,
                        Uri.parse(CosmostationConstants.COSMOSTATION_TELEGRAM)
                    )
                )
            }

            termView.setOnClickListener {
                if (Prefs.language == BaseUtils.LANGUAGE_KOREAN || Locale.getDefault().language == "ko") {
                    startActivity(
                        Intent(Intent.ACTION_VIEW,
                            Uri.parse(CosmostationConstants.COSMOSTATION_TERM_KR)
                        )
                    )
                } else {
                    startActivity(
                        Intent(Intent.ACTION_VIEW,
                            Uri.parse(CosmostationConstants.COSMOSTATION_TERM_EN)
                        )
                    )
                }
            }

            privacyView.setOnClickListener {
                startActivity(
                    Intent(Intent.ACTION_VIEW,
                        Uri.parse(CosmostationConstants.COSMOSTATION_PRIVACY_POLICY)
                    )
                )
            }

            githubView.setOnClickListener {
                startActivity(
                    Intent(Intent.ACTION_VIEW,
                        Uri.parse(CosmostationConstants.COSMOSTATION_GITHUB)
                    )
                )
            }

            versionView.setOnClickListener {
                startActivity(
                    Intent(Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + requireActivity().packageName)
                    )
                )
            }
        }
    }

    private fun onUpdateSwitch() {
        binding.apply {
            appLockSwitch.isChecked = Prefs.appLock
            if (appLockSwitch.isChecked) {
                appLockSwitch.thumbDrawable =
                    ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_on)
            } else {
                appLockSwitch.thumbDrawable =
                    ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_off)
            }
        }
    }

    private fun switchAction() {
        binding.apply {
            if (alarmSwitch.isChecked) {
                alarmSwitch.thumbDrawable =
                    ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_on)
            } else {
                alarmSwitch.thumbDrawable =
                    ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_off)
            }

            alarmSwitch.setOnCheckedChangeListener { _, isChecked ->
                val thumbDrawable: Drawable?
                if (isChecked) {
                    thumbDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_on)
                } else {
                    thumbDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_off)
                }
                alarmSwitch.thumbDrawable = thumbDrawable
            }

            onUpdateSwitch()
            appLockSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    appLockSwitch.thumbDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_on)
                    Prefs.appLock = true

                } else {
                    val intent = Intent(requireContext(), PasswordCheckActivity::class.java)
                    appLockCheckResultLauncher.launch(intent)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom,
                        R.anim.anim_fade_out
                    )
                }
            }
        }
    }

    private val appLockCheckResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                binding.appLockSwitch.thumbDrawable =
                    ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_off)
                Prefs.appLock = false
            }
    }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

enum class SettingType { LANGUAGE, CURRENCY, PRICE_STATUS, AUTO_PASS }