package wannabit.io.cosmostaion.ui.main

import android.Manifest
import android.annotation.SuppressLint
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.os.VibrationEffect
import android.os.Vibrator
import android.util.Patterns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.result.ActivityResult
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.widget.SwitchCompat
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.walletconnect.util.bytesToHex
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.chain.allChains
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.common.CosmostationConstants
import wannabit.io.cosmostaion.common.makeToast
import wannabit.io.cosmostaion.common.toMoveAnimation
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.databinding.FragmentSettingBinding
import wannabit.io.cosmostaion.ui.main.dapp.DappActivity
import wannabit.io.cosmostaion.ui.main.setting.SettingBottomFragment
import wannabit.io.cosmostaion.ui.main.setting.StyleFragment
import wannabit.io.cosmostaion.ui.main.setting.general.DevDialogActivity
import wannabit.io.cosmostaion.ui.main.setting.general.PushManager
import wannabit.io.cosmostaion.ui.main.setting.wallet.account.AccountActivity
import wannabit.io.cosmostaion.ui.main.setting.wallet.book.AddressBookListActivity
import wannabit.io.cosmostaion.ui.main.setting.wallet.chain.ChainActivity
import wannabit.io.cosmostaion.ui.main.setting.wallet.importQR.ImportBarcodeActivity
import wannabit.io.cosmostaion.ui.main.setting.wallet.importQR.ImportCheckKeyFragment
import wannabit.io.cosmostaion.ui.main.setting.wallet.importQR.ImportQrActivity
import wannabit.io.cosmostaion.ui.main.setting.wallet.importQR.QrImportConfirmListener
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity
import wannabit.io.cosmostaion.ui.qr.WaitingDialog
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import java.util.Locale

class SettingFragment : Fragment() {

    private var _binding: FragmentSettingBinding? = null
    private val binding get() = _binding!!

    private val walletViewModel: WalletViewModel by activityViewModels()

    private var isClickable = true

    private var waitingDialog: WaitingDialog? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSettingBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initView()
        initChainManageCnt()
        setUpClickAction()
        setUpSwitchAction()
        checkAccountStatus()
        checkChangeNameData()
    }

    private fun initView() {
        binding.apply {
            listOf(
                accountView,
                importView,
                legacyView,
                testnetView,
                chainView,
                addressBookView,
                languageView,
                currencyView,
                styleView,
                priceView,
                alarmView,
                appLockView,
                bioView,
                helpView,
                homepageView,
                termView,
                privacyView,
                githubView,
                versionView,
                devView
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

            if (BaseData.pushRefreshIfNeed()) {
                lifecycleScope.launch(Dispatchers.IO) {
                    PushManager.updateStatus(Prefs.alarmEnable) { _, _ -> }
                }
            }

            waitingDialog = WaitingDialog.newInstance()
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
            }
        }
    }

    private fun initChainManageCnt() {
        lifecycleScope.launch(Dispatchers.IO) {
            val chainNames: MutableList<String> = mutableListOf()
            allChains().forEach { chain ->
                if (!chainNames.contains(chain.name)) {
                    chainNames.add(chain.name)
                }
            }
            withContext(Dispatchers.Main) {
                binding.supportChainCnt.text = chainNames.size.toString()
            }
        }
    }

    private fun checkAccountStatus() {
        ApplicationViewModel.shared.currentAccountResult.observe(viewLifecycleOwner) {
            updateWalletView()
        }
    }

    private fun checkChangeNameData() {
        ApplicationViewModel.shared.changeNameResult.observe(viewLifecycleOwner) { account ->
            if (BaseData.baseAccount?.id == account?.id) {
                binding.accountName.text = account?.name
            }
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

                BaseUtils.LANGUAGE_JAPANESE -> {
                    language.text = getString(R.string.title_language_ja)
                }

                else -> {
                    language.text = getString(R.string.str_system)
                }
            }
            currency.text = BaseData.currencyName()
            style.text = if (Prefs.style == 0) {
                getString(R.string.str_simple)
            } else {
                getString(R.string.str_pro)
            }

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

            importView.setOnClickListener {
                if (ActivityCompat.checkSelfPermission(
                        requireActivity(), Manifest.permission.CAMERA
                    ) == PackageManager.PERMISSION_DENIED
                ) {
                    cameraPermissionRequest.launch(Manifest.permission.CAMERA)

                } else {
                    startImportBarcodeActivity()
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
                handleOneClickWithDelay(
                    SettingBottomFragment.newInstance(null, SettingType.LANGUAGE)
                )
            }

            currencyView.setOnClickListener {
                handleOneClickWithDelay(
                    SettingBottomFragment.newInstance(null, SettingType.CURRENCY)
                )
                parentFragmentManager.setFragmentResultListener(
                    "currency", this@SettingFragment
                ) { _, _ ->
                    currency.text = BaseData.currencyName()
                    walletViewModel.price(BaseData.currencyName(), true)
                }
            }

            styleView.setOnClickListener {
                handleOneClickWithDelay(
                    StyleFragment()
                )
                parentFragmentManager.setFragmentResultListener(
                    "style", this@SettingFragment
                ) { _, bundle ->
                    val style = bundle.getInt("style")
                    if (Prefs.style != style) {
                        Prefs.style = style
                        ApplicationViewModel.shared.styleOption(true)
                    }
                    updateDefaultView()
                }
            }

            priceView.setOnClickListener {
                handleOneClickWithDelay(
                    SettingBottomFragment.newInstance(null, SettingType.PRICE_STATUS)
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
            }

            helpView.setOnClickListener {
                val url = when (Prefs.language) {
                    BaseUtils.LANGUAGE_KOREAN -> {
                        Uri.parse("https://www.cosmostation.io/kr/support/mobile")
                    }

                    BaseUtils.LANGUAGE_JAPANESE -> {
                        Uri.parse("https://www.cosmostation.io/jp/support/mobile")
                    }

                    else -> {
                        Uri.parse("https://www.cosmostation.io/en/support/mobile")
                    }
                }
                startActivity(
                    Intent(
                        Intent.ACTION_VIEW, url
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

            devView.setOnClickListener {
                val intent = Intent(requireContext(), DevDialogActivity::class.java)
                devResultLauncher.launch(intent)
            }
        }
    }

    private val qrCodeResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.getStringExtra("import")?.let { scanStr ->
                    val scanHexData = scanStr.toByteArray(Charsets.UTF_8).bytesToHex()
                    if (scanHexData.startsWith("55324673")) {
                        Handler(Looper.getMainLooper()).postDelayed({
                            ImportCheckKeyFragment.newInstance(scanStr, qrImportConfirmAction).show(
                                parentFragmentManager, ImportCheckKeyFragment::class.java.name
                            )
                        }, 500)

                    } else {
                        requireActivity().makeToast(R.string.error_unknown_qr_code)
                        return@registerForActivityResult
                    }
                }
            }
        }

    private val qrImportConfirmAction = object : QrImportConfirmListener {
        override fun qrImportConfirm(mnemonic: String) {
            Intent(requireContext(), ImportQrActivity::class.java).apply {
                putExtra("mnemonic", mnemonic)
                startActivity(this)
                requireActivity().toMoveAnimation()
            }
        }
    }

    private val devResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                result.data?.getStringExtra("url")?.let { url ->
                    val uri = Uri.parse(url)
                    Handler(Looper.getMainLooper()).postDelayed({
                        if (uri.scheme != null && uri.host != null && Patterns.WEB_URL.matcher(url)
                                .matches()
                        ) {
                            requireActivity().runOnUiThread {
                                Intent(requireActivity(), DappActivity::class.java).apply {
                                    putExtra("dapp", url)
                                    startActivity(this)
                                }
                            }

                        } else {
                            requireActivity().runOnUiThread {
                                requireActivity().makeToast(url)
                            }
                        }
                    }, 500)
                }
            }
        }

    private fun handleOneClickWithDelay(bottomSheetDialogFragment: BottomSheetDialogFragment) {
        if (isClickable) {
            isClickable = false

            bottomSheetDialogFragment.show(
                parentFragmentManager, bottomSheetDialogFragment::class.java.name
            )

            Handler(Looper.getMainLooper()).postDelayed({
                isClickable = true
            }, 300)
        }
    }

    private fun onUpdateSwitch() {
        binding.apply {
            legacySwitch.isChecked = Prefs.displayLegacy
            legacySwitch.setSwitchView()

            testnetSwitch.isChecked = Prefs.displayTestnet
            testnetSwitch.setSwitchView()

            alarmSwitch.isChecked = Prefs.alarmEnable
            alarmSwitch.setSwitchView()

            appLockSwitch.isChecked = Prefs.appLock
            appLockSwitch.setSwitchView()

            bioSwitch.isChecked = Prefs.usingBio
            bioSwitch.setSwitchView()

            lifecycleScope.launch(Dispatchers.IO) {
                val addressCnt =
                    AppDatabase.getInstance().addressBookDao().selectAll().size.toString()
                withContext(Dispatchers.Main) {
                    favoriteAddressCnt.text = addressCnt
                }
            }
        }
    }

    @SuppressLint("WrongConstant")
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
                if (requireActivity().supportFragmentManager.findFragmentByTag("dialog") == null) {
                    waitingDialog?.show(requireActivity().supportFragmentManager, "dialog")
                }
                setVibrate()
                ApplicationViewModel.shared.displayLegacy(Prefs.displayLegacy)

                Handler(Looper.getMainLooper()).postDelayed({
                    if (waitingDialog?.isAdded == true) {
                        waitingDialog?.dismissAllowingStateLoss()
                    }
                }, 1000)
            }

            testnetSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    testnetSwitch.thumbDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_on)
                    Prefs.displayTestnet = true
                } else {
                    testnetSwitch.thumbDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_off)
                    Prefs.displayTestnet = false
                }

                if (requireActivity().supportFragmentManager.findFragmentByTag("dialog") == null) {
                    waitingDialog?.show(requireActivity().supportFragmentManager, "dialog")
                }
                setVibrate()
                ApplicationViewModel.shared.displayTestnet(Prefs.displayTestnet)

                Handler(Looper.getMainLooper()).postDelayed({
                    if (waitingDialog?.isAdded == true) {
                        waitingDialog?.dismissAllowingStateLoss()
                        initChainManageCnt()
                    }
                }, 1000)
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
                waitingDialog?.show(requireActivity().supportFragmentManager, "dialog")
                PushManager.updateStatus(isChecked) { _, msg ->
                    requireActivity().makeToast(msg)
                    if (waitingDialog?.isVisible == true) {
                        waitingDialog?.dismissAllowingStateLoss()
                    }
                }
            }

            appLockSwitch.setOnCheckedChangeListener { _, isChecked ->
                if (isChecked) {
                    appLockSwitch.thumbDrawable =
                        ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_on)
                    Prefs.appLock = true

                } else {
                    val intent = Intent(requireContext(), PasswordCheckActivity::class.java)
                    appLockCheckResultLauncher.launch(intent)
                    if (Build.VERSION.SDK_INT >= 34) {
                        requireActivity().overrideActivityTransition(Activity.OVERRIDE_TRANSITION_OPEN, R.anim.anim_slide_in_bottom, R.anim.anim_fade_out)
                    } else {
                        requireActivity().overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_fade_out)
                    }
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

    private val appLockCheckResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                binding.appLockSwitch.thumbDrawable =
                    ContextCompat.getDrawable(requireContext(), R.drawable.switch_thumb_off)
                Prefs.appLock = false
            }
        }

    @SuppressLint("WrongConstant")
    private fun startImportBarcodeActivity() {
        val intent = Intent(requireContext(), ImportBarcodeActivity::class.java)
        qrCodeResultLauncher.launch(intent)
        if (Build.VERSION.SDK_INT >= 34) {
            requireActivity().overrideActivityTransition(Activity.OVERRIDE_TRANSITION_OPEN, R.anim.anim_slide_in_bottom, R.anim.anim_fade_out)
        } else {
            requireActivity().overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_fade_out)
        }
    }

    private val cameraPermissionRequest =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (isGranted) {
                startImportBarcodeActivity()
            } else {
                return@registerForActivityResult
            }
        }

    override fun onDestroyView() {
        _binding = null
        super.onDestroyView()
    }
}

enum class SettingType { LANGUAGE, CURRENCY, PRICE_STATUS, BUY_CRYPTO, END_POINT_EVM, END_POINT_COSMOS, END_POINT_SUI }