package wannabit.io.cosmostaion.fragment

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CompoundButton
import android.widget.Toast
import androidx.activity.result.ActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.core.hardware.fingerprint.FingerprintManagerCompat
import com.google.zxing.client.android.Intents
import com.google.zxing.integration.android.IntentIntegrator
import com.gun0912.tedpermission.PermissionListener
import com.gun0912.tedpermission.TedPermission
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.activities.*
import wannabit.io.cosmostaion.activities.setting.MnemonicListActivity
import wannabit.io.cosmostaion.activities.setting.PrivateKeyRestoreActivity
import wannabit.io.cosmostaion.activities.setting.WatchingWalletAddActivity
import wannabit.io.cosmostaion.activities.txs.starname.StarNameWalletConnectActivity
import wannabit.io.cosmostaion.base.BaseConstant
import wannabit.io.cosmostaion.base.BaseFragment
import wannabit.io.cosmostaion.base.chains.ChainFactory
import wannabit.io.cosmostaion.dao.Price
import wannabit.io.cosmostaion.databinding.FragmentMainSettingBinding
import wannabit.io.cosmostaion.dialog.*
import wannabit.io.cosmostaion.network.ApiClient
import wannabit.io.cosmostaion.network.res.PushStatusResponse
import wannabit.io.cosmostaion.utils.LanguageUtil
import wannabit.io.cosmostaion.utils.LedgerManager
import wannabit.io.cosmostaion.utils.LedgerManager.Companion.instance
import wannabit.io.cosmostaion.utils.LedgerManager.ConnectListener
import wannabit.io.cosmostaion.utils.PushManager.syncAddresses
import wannabit.io.cosmostaion.utils.PushManager.updateStatus
import wannabit.io.cosmostaion.utils.ThemeUtil
import java.util.*

class MainSettingFragment : BaseFragment(), View.OnClickListener {
    private var mainSettingBinding: FragmentMainSettingBinding? = null
    private var mDialogWait: WaitDialog? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setHasOptionsMenu(true)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mainSettingBinding = FragmentMainSettingBinding.inflate(inflater, container, false)
        mainSettingBinding!!.cardWallet.setOnClickListener(this)
        mainSettingBinding!!.cardMnemonic.setOnClickListener(this)
        mainSettingBinding!!.cardKey.setOnClickListener(this)
        mainSettingBinding!!.cardWatchAddress.setOnClickListener(this)
        mainSettingBinding!!.cardTheme.setOnClickListener(this)
        mainSettingBinding!!.cardLanguage.setOnClickListener(this)
        mainSettingBinding!!.cardAutoPass.setOnClickListener(this)
        mainSettingBinding!!.cardCurrency.setOnClickListener(this)
        mainSettingBinding!!.cardPriceColorChange.setOnClickListener(this)
        mainSettingBinding!!.cardExplore.setOnClickListener(this)
        mainSettingBinding!!.cardNotice.setOnClickListener(this)
        mainSettingBinding!!.cardHomepage.setOnClickListener(this)
        mainSettingBinding!!.cardBlog.setOnClickListener(this)
        mainSettingBinding!!.cardTelegram.setOnClickListener(this)
        mainSettingBinding!!.cardStarnameWalletConnect.setOnClickListener(this)
        mainSettingBinding!!.cardTerm.setOnClickListener(this)
        mainSettingBinding!!.cardGithub.setOnClickListener(this)
        mainSettingBinding!!.cardVersion.setOnClickListener(this)
        mainSettingBinding!!.cardWalletConnect.setOnClickListener(this)
        mainSettingBinding!!.cardLedger.setOnClickListener(this)
        mainSettingBinding!!.cardPrivacy.setOnClickListener(this)
        mainSettingBinding!!.switchUsingApplock.setOnClickListener(this)
        mainSettingBinding!!.switchUsingBio.setOnClickListener(this)
        mainSettingBinding!!.switchAlaram.setOnCheckedChangeListener(switchListener())
        onUpdateView()
        loadPushStatus()
        mainSettingBinding!!.versionText.text = "v" + BuildConfig.VERSION_NAME
        return mainSettingBinding!!.root
    }

    private fun switchListener(): CompoundButton.OnCheckedChangeListener {
        return CompoundButton.OnCheckedChangeListener { _: CompoundButton?, _: Boolean -> syncPushStatus() }
    }

    private fun syncPushStatus() {
        if (!baseDao.alarmEnable) {
            syncAddresses(requireContext(), baseDao, baseDao.fcmToken)
        }
        updateStatus(
            requireContext(),
            baseDao,
            mainSettingBinding!!.switchAlaram.isChecked,
            baseDao.fcmToken
        )
    }

    override fun onDestroy() {
        super.onDestroy()
        mainSettingBinding = null
    }

    private fun loadPushStatus() {
        ApiClient.getCosmostationOld(requireContext()).getPushStatus(baseDao.fcmToken)
            .enqueue(object : Callback<PushStatusResponse?> {
                override fun onResponse(
                    call: Call<PushStatusResponse?>,
                    response: Response<PushStatusResponse?>
                ) {
                    if (response.isSuccessful && response.body() != null) {
                        mainSettingBinding!!.switchAlaram.isChecked = response.body()!!.subscribe
                        baseDao.alarmEnable = response.body()!!.subscribe
                    }
                }

                override fun onFailure(call: Call<PushStatusResponse?>, t: Throwable) {}
            })
    }

    override fun onResume() {
        super.onResume()
        if (isAdded) return
    }

    override fun onRefreshTab() {
        if (!isAdded) return
        onUpdateCurrency()
        onUpdateAutoPass()
    }

    private fun onUpdateView() {
        onUpdatePriceColor(mainActivity.baseDao.priceColorOption)
        if (ThemeUtil.modLoad(baseActivity) == ThemeUtil.LIGHT_MODE) {
            mainSettingBinding!!.themeText.setText(R.string.str_theme_light)
        } else if (ThemeUtil.modLoad(baseActivity) == ThemeUtil.DARK_MODE) {
            mainSettingBinding!!.themeText.setText(R.string.str_theme_dark)
        } else {
            mainSettingBinding!!.themeText.setText(R.string.str_theme_system)
        }
        if (LanguageUtil.modLoad(baseActivity) == LanguageUtil.LANGUAGE_ENGLISH) {
            mainSettingBinding!!.languageText.setText(R.string.str_language_english)
        } else if (LanguageUtil.modLoad(baseActivity) == LanguageUtil.LANGUAGE_KOREAN) {
            mainSettingBinding!!.languageText.setText(R.string.str_language_korean)
        } else if (LanguageUtil.modLoad(baseActivity) == LanguageUtil.LANGUAGE_JAPANESE) {
            mainSettingBinding!!.languageText.setText(R.string.str_language_japanese)
        } else {
            mainSettingBinding!!.languageText.setText(R.string.str_theme_system)
        }
        mainSettingBinding!!.switchUsingApplock.isChecked = baseDao.usingAppLock
        mainSettingBinding!!.switchUsingBio.isChecked = baseDao.usingFingerPrint
        val mFingerprintManagerCompat = FingerprintManagerCompat.from(
            activity!!
        )
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M && mFingerprintManagerCompat.isHardwareDetected && mFingerprintManagerCompat.hasEnrolledFingerprints()) {
            mainSettingBinding!!.bioTitle.text = getString(R.string.str_using_fingerprints)
        } else {
            mainSettingBinding!!.bioTitle.text = ""
        }
    }

    private fun onUpdateCurrency() {
        mainSettingBinding!!.currencyText.text = baseDao.currencyString
    }

    private fun onUpdatePriceColor(value: Int) {
        if (value == 1) {
            mainSettingBinding!!.iconPriceColorUp.setImageResource(R.drawable.icon_pricegreen)
            mainSettingBinding!!.iconPriceColorDown.setImageResource(R.drawable.icon_pricered)
        } else {
            mainSettingBinding!!.iconPriceColorUp.setImageResource(R.drawable.icon_pricered)
            mainSettingBinding!!.iconPriceColorDown.setImageResource(R.drawable.icon_pricegreen)
        }
    }

    override fun onClick(v: View) {
        if (v == mainSettingBinding!!.cardWallet) {
            startActivity(Intent(baseActivity, AccountListActivity::class.java))
        } else if (v == mainSettingBinding!!.cardMnemonic) {
            startActivity(Intent(baseActivity, MnemonicListActivity::class.java))
        } else if (v == mainSettingBinding!!.cardKey) {
            startActivity(Intent(baseActivity, PrivateKeyRestoreActivity::class.java))
        } else if (v == mainSettingBinding!!.cardWatchAddress) {
            startActivity(Intent(baseActivity, WatchingWalletAddActivity::class.java))
        } else if (v == mainSettingBinding!!.cardTheme) {
            FilledVerticalButtonAlertDialog.showTripleButton(
                baseActivity,
                null,
                null,
                getString(R.string.str_theme_system),
                { setTheme(baseActivity, ThemeUtil.DEFAULT_MODE) },
                null,
                getString(R.string.str_theme_light),
                { setTheme(baseActivity, ThemeUtil.LIGHT_MODE) },
                null,
                getString(R.string.str_theme_dark),
                { setTheme(baseActivity, ThemeUtil.DARK_MODE) },
                null
            )
        } else if (v == mainSettingBinding!!.cardLanguage) {
            FilledVerticalButtonAlertDialog.showQuadrupleButton(
                baseActivity,
                null,
                null,
                getString(R.string.str_language_system),
                { setLanguage(baseActivity, LanguageUtil.SYSTEM_MODE) },
                null,
                getString(R.string.str_language_english),
                { setLanguage(baseActivity, LanguageUtil.LANGUAGE_ENGLISH) },
                null,
                getString(R.string.str_language_korean),
                { setLanguage(baseActivity, LanguageUtil.LANGUAGE_KOREAN) },
                null,
                getString(R.string.str_language_japanese),
                { setLanguage(baseActivity, LanguageUtil.LANGUAGE_JAPANESE) },
                null
            )
        } else if (v == mainSettingBinding!!.switchUsingApplock) {
            onClickAppLock()
        } else if (v == mainSettingBinding!!.switchUsingBio) {
            baseDao.usingFingerPrint = !baseDao.usingFingerPrint
            onUpdateView()
        } else if (v == mainSettingBinding!!.cardAutoPass) {
            onClickAutoPass()
        } else if (v == mainSettingBinding!!.cardCurrency && !mainActivity.isFinishing) {
            val dialog = CurrencySetDialog.newInstance(null)
            dialog.show(parentFragmentManager, CurrencySetDialog::class.java.name)
            parentFragmentManager.setFragmentResultListener(
                CurrencySetDialog.CURRENCY_SET_BUNDLE_KEY,
                this
            ) { _: String?, bundle: Bundle ->
                val result = bundle.getInt(BaseConstant.POSITION)
                onSetCurrency(result)
            }
        } else if (v == mainSettingBinding!!.cardPriceColorChange && !mainActivity.isFinishing) {
            val dialog = PriceColorChangeDialog.newInstance(null)
            dialog.show(parentFragmentManager, PriceColorChangeDialog::class.java.name)
            parentFragmentManager.setFragmentResultListener(
                BaseConstant.PRE_PRICE_COLOR,
                this
            ) { _: String?, bundle: Bundle ->
                val result = bundle.getInt(BaseConstant.POSITION)
                onUpdatePriceColor(result)
            }
        } else if (v == mainSettingBinding!!.cardExplore) {
            val url = mainActivity.mChainConfig.explorerUrl()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } else if (v == mainSettingBinding!!.cardWalletConnect) {
            startActivity(Intent(context, ManageWalletConnectActivity::class.java))
        } else if (v == mainSettingBinding!!.cardPrivacy) {
            val intent =
                Intent(Intent.ACTION_VIEW, Uri.parse(BaseConstant.COSMOSTATION_PRIVACY_POLICY))
            startActivity(intent)
        } else if (v == mainSettingBinding!!.cardNotice) {
            val url =
                BaseConstant.EXPLORER_NOTICE_MINTSCAN + ChainFactory.getChain(mainActivity.mBaseChain)
                    .chainName()
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url))
            startActivity(intent)
        } else if (v == mainSettingBinding!!.cardHomepage) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(BaseConstant.COSMOSTATION_HOMEPAGE))
            startActivity(intent)
        } else if (v == mainSettingBinding!!.cardBlog) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(BaseConstant.COSMOSTATION_BLOG))
            startActivity(intent)
        } else if (v == mainSettingBinding!!.cardTelegram) {
            val telegram = Intent(Intent.ACTION_VIEW, Uri.parse(BaseConstant.COSMOSTATION_TELEGRAM))
            startActivity(telegram)
        } else if (v == mainSettingBinding!!.cardTerm) {
            if (LanguageUtil.modLoad(baseActivity) == LanguageUtil.LANGUAGE_KOREAN || Locale.getDefault().language == LanguageUtil.LANGUAGE_KOREAN) {
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(BaseConstant.COSMOSTATION_TERM_KR))
                startActivity(intent)
            } else {
                val intent =
                    Intent(Intent.ACTION_VIEW, Uri.parse(BaseConstant.COSMOSTATION_TERM_EN))
                startActivity(intent)
            }
        } else if (v == mainSettingBinding!!.cardGithub) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(BaseConstant.COSMOSTATION_GITHUB))
            startActivity(intent)
        } else if (v == mainSettingBinding!!.cardVersion) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse("market://details?id=" + mainActivity.packageName)
            startActivity(intent)
        } else if (v == mainSettingBinding!!.cardLedger) {
            onLedgerConnect()
        } else if (v == mainSettingBinding!!.cardStarnameWalletConnect) {
            CommonAlertDialog.showDoubleButton(
                mainActivity,
                getString(R.string.str_starname_walletconnect_alert_title),
                getString(R.string.str_starname_walletconnect_alert_msg),
                getString(R.string.str_cancel),
                null,
                getString(R.string.str_continue)
            ) {
                TedPermission(
                    mainActivity
                ).setPermissionListener(object : PermissionListener {
                    override fun onPermissionGranted() {
                        val integrator =
                            IntentIntegrator.forSupportFragment(this@MainSettingFragment)
                        integrator.setOrientationLocked(true)
                        integrator.captureActivity = QRcodeActivity::class.java
                        wcQrcodeResultLauncher.launch(integrator.createScanIntent())
                    }

                    override fun onPermissionDenied(deniedPermissions: ArrayList<String>) {
                        Toast.makeText(context, R.string.error_permission, Toast.LENGTH_SHORT)
                            .show()
                    }
                }).setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .setRationaleMessage(getString(R.string.str_permission_qr)).check()
            }
        }
    }

    private fun onClickAppLock() {
        if (baseDao.usingAppLock) {
            val intent = Intent(activity, PasswordCheckActivity::class.java)
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_APP_LOCK)
            appLockCheckResultLauncher.launch(intent)
            activity!!.overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out)
        } else {
            if (baseDao.onHasPassword()) {
                baseDao.usingAppLock = true
                onUpdateView()
            } else {
                val intent = Intent(activity, PasswordSetActivity::class.java)
                appLockCheckResultLauncher.launch(intent)
                activity!!.overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out)
            }
        }
    }

    private fun onSetCurrency(value: Int) {
        if (baseDao.currency != value) {
            baseDao.currency = value
            onUpdateCurrency()
            ApiClient.getMintscan(baseApplication)
                .getPrice(baseDao.currencyString.lowercase(Locale.getDefault()))
                .enqueue(object : Callback<ArrayList<Price?>?> {
                    override fun onResponse(
                        call: Call<ArrayList<Price?>?>,
                        response: Response<ArrayList<Price?>?>
                    ) {
                        if (response.isSuccessful && response.body() != null) {
                            baseDao.mPrices.clear()
                            for (price in response.body()!!) {
                                baseDao.mPrices.add(price)
                            }
                            baseDao.setLastPriceTime()
                        }
                    }

                    override fun onFailure(call: Call<ArrayList<Price?>?>, t: Throwable) {}
                })
        }
    }

    private fun onClickAutoPass() {
        if (!baseDao.onHasPassword()) {
            val intent = Intent(activity, PasswordSetActivity::class.java)
            autoPassResultLauncher.launch(intent)
        } else {
            val intent = Intent(activity, PasswordCheckActivity::class.java)
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_AUTO_PASS)
            autoPassResultLauncher.launch(intent)
        }
        activity!!.overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out)
    }

    private fun onShowAutoPassDialog() {
        FilledVerticalButtonAlertDialog.showQuadrupleButton(
            baseActivity,
            null,
            getString(R.string.str_app_auto_pass_msg),
            getString(R.string.str_app_auto_pass_5m),
            { onSetAutoPass(1) },
            null,
            getString(R.string.str_app_auto_pass_10m),
            { onSetAutoPass(2) },
            null,
            getString(R.string.str_app_auto_pass_30m),
            { onSetAutoPass(3) },
            null,
            getString(R.string.str_app_auto_pass_never),
            { onSetAutoPass(0) },
            null
        )
    }

    private fun onSetAutoPass(value: Int) {
        if (baseDao.usingAutoPassTime != value) {
            baseDao.usingAutoPassTime = value
            onUpdateAutoPass()
        }
    }

    private fun onUpdateAutoPass() {
        mainSettingBinding!!.autoPassTime.text = baseDao.getAutoPass(activity)
    }

    private fun onLedgerConnect() {
        if (instance.isConnected()) {
            instance.bleManager.disconnect {
                onShowWaitDialog()
                mainSettingBinding!!.cardLedger.postDelayed({ showLedgerPicker() }, 1500)
            }
        } else {
            onShowWaitDialog()
            showLedgerPicker()
        }
    }

    private fun showLedgerPicker() {
        onHideWaitDialog()
        activity!!.runOnUiThread {
            instance.pickLedgerDevice(requireContext(), object : ConnectListener {
                override fun error(errorType: LedgerManager.ErrorType) {
                    context?.let {
                        FilledVerticalButtonAlertDialog.showNoButton(
                            it,
                            getString(R.string.str_pairing_ledger_title),
                            getString(errorType.descriptionResourceId),
                            true
                        )
                    }
                }

                override fun connected() {
                    startActivity(Intent(activity, LedgerSelectActivity::class.java))
                }
            })
        }
    }

    val mainActivity: MainActivity
        get() = baseActivity as MainActivity
    private val appLockCheckResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                baseDao.usingAppLock = false
                onUpdateView()
            }
        }
    private val autoPassResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK) {
                Handler(Looper.getMainLooper()).postDelayed({ onShowAutoPassDialog() }, 300)
            }
        }
    private val wcQrcodeResultLauncher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result: ActivityResult ->
            if (result.resultCode == Activity.RESULT_OK && result.data != null && result.data!!
                    .getStringExtra(Intents.Scan.RESULT)!!.trim { it <= ' ' }
                    .contains("bridge.walletconnect.org")
            ) {
                val wcIntent = Intent(mainActivity, StarNameWalletConnectActivity::class.java)
                wcIntent.putExtra("wcUrl", result.data!!
                    .getStringExtra(Intents.Scan.RESULT)!!.trim { it <= ' ' })
                startActivity(wcIntent)
            } else {
                Toast.makeText(
                    activity,
                    activity!!.getString(R.string.str_wc_not_supported),
                    Toast.LENGTH_SHORT
                ).show()
                return@registerForActivityResult
            }
        }

    private fun setTheme(context: Context, themeColor: String) {
        ThemeUtil.applyTheme(themeColor)
        ThemeUtil.modSave(context, themeColor)
        mainActivity.recreate()
    }

    private fun setLanguage(context: Context, languageSet: String) {
        LanguageUtil.updateResources(context)
        LanguageUtil.modSave(context, languageSet)
        mainActivity.recreate()
    }

    fun onShowWaitDialog() {
        if (activity!!.supportFragmentManager.findFragmentByTag("wait") == null) {
            mDialogWait = WaitDialog()
        }
        mDialogWait!!.isCancelable = false
        mDialogWait!!.show(activity!!.supportFragmentManager, "wait")
    }

    fun onHideWaitDialog() {
        if (mDialogWait != null) {
            mDialogWait!!.dismissAllowingStateLoss()
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        mainSettingBinding = null
    }

    companion object {
        @JvmStatic
        fun newInstance(): MainSettingFragment {
            return MainSettingFragment()
        }
    }
}