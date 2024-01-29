package wannabit.io.cosmostaion.ui.main

import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SwitchCompat
import androidx.core.content.ContextCompat
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.allCosmosLines
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.common.toMoveAnimation
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentSettingBinding
import wannabit.io.cosmostaion.ui.main.setting.SettingBottomFragment
import wannabit.io.cosmostaion.ui.main.setting.general.PushManager
import wannabit.io.cosmostaion.ui.main.setting.wallet.account.AccountActivity
import wannabit.io.cosmostaion.ui.main.setting.wallet.book.AddressBookListActivity
import wannabit.io.cosmostaion.ui.main.setting.wallet.chain.ChainActivity
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import java.util.Locale

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private val walletViewModel: WalletViewModel by activityViewModels()

    private var isClickable = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        setUpClickAction()
        setUpSwitchAction()
        checkAccountStatus()
    }

    private fun initView() {
        binding.apply {
            listOf(
                accountView, importView, legacyView, chainView, addressBookView,
                languageView, currencyView, priceView, alarmView, appLockView, bioView,
                mintscanView, homepageView, blogView, twitterView, telegramView, youtubeView,
                termView, privacyView, githubView, versionView
            ).forEach { it.setBackgroundResource(R.drawable.item_bg) }

            updateWalletView()
            updateDefaultView()

            val fingerprintManagerCompat = FingerprintManagerCompat.from(requireContext())
            if (fingerprintManagerCompat.isHardwareDetected && fingerprintManagerCompat.hasEnrolledFingerprints()) {
                bioTxt.text = getString(R.string.title_using_bio)
            } else {
                bioTxt.text = ""
            }
            version.text = "v " + BuildConfig.VERSION_NAME
        }
    }

    override fun onResume() {
        super.onResume()
        onUpdateSwitch()
    }

    private fun updateWalletView() {
        binding.apply {
            BaseData.baseAccount?.let { account ->
                accountName.text = account.name
                supportChainCnt.text =
                    allCosmosLines().filter { it.isDefault }.distinctBy { it.name }.count()
                        .toString()
            }

            walletViewModel.pushStatus(Prefs.fcmToken)
        }
    }

    private fun checkAccountStatus() {
        ApplicationViewModel.shared.currentAccountResult.observe(viewLifecycleOwner) {
            updateWalletView()
        }
    }

    private fun updateDefaultView() {
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
        }
    }

    private fun setUpClickAction() {
        binding.apply {
            accountView.setOnClickListener {
                Intent(requireContext(), AccountActivity::class.java).apply {
                    startActivity(this)
                    requireActivity().toMoveAnimation()
                }
            }

            chainView.setOnClickListener {
                Intent(requireContext(), ChainActivity::class.java).apply {
                    startActivity(this)
                    requireActivity().toMoveAnimation()
                }
            }

            addressBookView.setOnClickListener {
                Intent(requireContext(), AddressBookListActivity::class.java).apply {
                    startActivity(this)
                    requireActivity().toMoveAnimation()
                }
            }

            languageView.setOnClickListener {
                SettingBottomFragment(SettingType.LANGUAGE).show(
                    parentFragmentManager, SettingBottomFragment::class.java.name
                )
                setClickableOnce(isClickable)
            }

            currencyView.setOnClickListener {
                SettingBottomFragment(SettingType.CURRENCY).show(
                    parentFragmentManager, SettingBottomFragment::class.java.name
                )
                parentFragmentManager.setFragmentResultListener(
                    "currency", this@SettingFragment
                ) { _, _ ->
                    currency.text = BaseData.currencyName()
                    walletViewModel.price(BaseData.currencyName(), true)
                }
                setClickableOnce(isClickable)
            }

            priceView.setOnClickListener {
                SettingBottomFragment(SettingType.PRICE_STATUS).show(
                    parentFragmentManager, SettingBottomFragment::class.java.name
                )
                parentFragmentManager.setFragmentResultListener(
                    "priceStyle", this@SettingFragment
                ) { _, bundle ->
                    val priceStyle = bundle.getInt("priceStyle")
                    if (Prefs.priceStyle != priceStyle) {
                        Prefs.priceStyle = priceStyle
                    }
                    updateDefaultView()
                }
                setClickableOnce(isClickable)
            }

            mintscanView.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse(CosmostationConstants.EXPLORER_BASE_URL)
                    )
                )
            }

            homepageView.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse(CosmostationConstants.COSMOSTATION_HOMEPAGE)
                    )
                )
            }

            twitterView.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse(CosmostationConstants.COSMOSTATION_TWITTER)
                    )
                )
            }

            blogView.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse(CosmostationConstants.COSMOSTATION_BLOG)
                    )
                )
            }

            telegramView.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse(CosmostationConstants.COSMOSTATION_TELEGRAM)
                    )
                )
            }

            youtubeView.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse(CosmostationConstants.COSMOSTATION_YOUTUBE)
                    )
                )
            }

            termView.setOnClickListener {
                if (Prefs.language == BaseUtils.LANGUAGE_KOREAN || Locale.getDefault().language == "ko") {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(CosmostationConstants.COSMOSTATION_TERM_KR)
                        )
                    )
                } else {
                    startActivity(
                        Intent(
                            Intent.ACTION_VIEW,
                            Uri.parse(CosmostationConstants.COSMOSTATION_TERM_EN)
                        )
                    )
                }
            }

            privacyView.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse(CosmostationConstants.COSMOSTATION_PRIVACY_POLICY)
                    )
                )
            }

            githubView.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, Uri.parse(CosmostationConstants.COSMOSTATION_GITHUB)
                    )
                )
            }

            versionView.setOnClickListener {
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW,
                        Uri.parse("market://details?id=" + requireActivity().packageName)
                    )
                )
            }
        }
    }

    private fun setClickableOnce(clickable: Boolean) {
        if (clickable) {
            isClickable = false

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 1000)
        }
    }

    private fun onUpdateSwitch() {
        binding.apply {
            legacySwitch.isChecked = Prefs.displayLegacy
            legacySwitch.setSwitchView()

            alarmSwitch.setSwitchView()
            setUpAlarmSwitch()

            appLockSwitch.isChecked = Prefs.appLock
            appLockSwitch.setSwitchView()

            bioSwitch.isChecked = Prefs.usingBio
            bioSwitch.setSwitchView()

            CoroutineScope(Dispatchers.IO).launch {
                val addressCnt =
                    AppDatabase.getInstance().addressBookDao().selectAll().size.toString()
                withContext(Dispatchers.Main) {
                    favoriteAddressCnt.text = addressCnt
                }
            }
        }
    }

    private fun setUpSwitchAction() {
        binding.apply {
            onUpdateSwitch()

            legacySwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    legacySwitch.thumbDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_on)
                    Prefs.displayLegacy = true
                } else {
                    legacySwitch.thumbDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_off)
                    Prefs.displayLegacy = false
                }
                setVibrate()
                ApplicationViewModel.shared.displayLegacy(Prefs.displayLegacy)
            }

            alarmSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    alarmSwitch.thumbDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_on)
                } else {
                    alarmSwitch.thumbDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_off)
                }
                setVibrate()
                syncPushStatus()
            }

            appLockSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    appLockSwitch.thumbDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_on)
                    Prefs.appLock = true

                } else {
                    val intent = Intent(requireContext(), PasswordCheckActivity::class.java)
                    appLockCheckResultLauncher.launch(intent)
                    requireActivity().overridePendingTransition(
                        R.anim.anim_slide_in_bottom, R.anim.anim_fade_out
                    )
                }
                setVibrate()
            }

            bioSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    bioSwitch.thumbDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_on)
                    Prefs.usingBio = true
                } else {
                    bioSwitch.thumbDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_off)
                    Prefs.usingBio = false
                }
                setVibrate()
            }
        }
    }

    private fun SwitchCompat.setSwitchView() {
        thumbDrawable = if (isChecked) {
            ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_on)
        } else {
            ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_off)
        }
    }

    private fun setVibrate() {
        val vibrator = requireActivity().getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            vibrator.vibrate(VibrationEffect.createOneShot(100, 50))
        } else {
            vibrator.vibrate(100)
        }
    }

    private fun syncPushStatus() {
        if (!Prefs.alarmEnable) {
            PushManager.syncAddresses(Prefs.fcmToken)
        }
        PushManager.updateStatus(binding.alarmSwitch.isChecked, Prefs.fcmToken)
    }

    private fun setUpAlarmSwitch() {
        walletViewModel.pushStatusResult.observe(viewLifecycleOwner) {
            binding.alarmSwitch.isChecked = it
            Prefs.alarmEnable = it
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

enum class SettingType { LANGUAGE, CURRENCY, PRICE_STATUS, BUY_CRYPTO }