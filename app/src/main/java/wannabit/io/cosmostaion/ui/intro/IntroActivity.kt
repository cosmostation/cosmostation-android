package wannabit.io.cosmostaion.ui.intro

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.legacy.LegacyMigrationHelper
import wannabit.io.cosmostaion.databinding.ActivityIntroBinding
import wannabit.io.cosmostaion.ui.main.CosmostationApp
import wannabit.io.cosmostaion.ui.main.MainActivity
import wannabit.io.cosmostaion.ui.password.AppLockActivity
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.ui.viewmodel.intro.WalletViewModelProviderFactory

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding

    private lateinit var introViewModel: WalletViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFullScreen()
        initViewModel()
        initFirebase()
        migrateDatabaseIfNeed()
        checkAppVersion()

        introViewModel.walletAppVersion()
        initPriceInfo()
    }

    private fun initFullScreen() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            window.setDecorFitsSystemWindows(false)
            supportActionBar?.hide()
            val controller = window.insetsController
            if (controller != null) {
                controller.hide(WindowInsets.Type.navigationBars())
                controller.systemBarsBehavior =
                    WindowInsetsController.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
            }
        } else {
            window.decorView.systemUiVisibility =
                (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN or View.SYSTEM_UI_FLAG_LAYOUT_STABLE or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        }
    }

    private fun initViewModel() {
        val walletRepository = WalletRepositoryImpl()
        val introViewModelProviderFactory = WalletViewModelProviderFactory(walletRepository)
        introViewModel = ViewModelProvider(this, introViewModelProviderFactory)[WalletViewModel::class.java]
    }

    override fun onPostResume() {
        super.onPostResume()
        if (Prefs.version >= BaseConstant.DB_VERSION) {
            checkAppVersion()
        }
    }

    private fun migrateDatabaseIfNeed() = CoroutineScope(Dispatchers.IO).launch {
        if (Prefs.version < BaseConstant.DB_VERSION) {
            LegacyMigrationHelper.migratePassword()
            LegacyMigrationHelper.migrateWallet()
        }
    }

    private fun postProcessAppVersion() = CoroutineScope(Dispatchers.IO).launch {
        delay(1500)
        val account = BaseData.getLastAccount()
        account?.let {
            BaseData.baseAccount = account
            if (CosmostationApp.instance.needShowLockScreen()) {
                val intent = Intent(this@IntroActivity, AppLockActivity::class.java)
                startActivity(intent)
                overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_fade_out)

            } else {
                if (AppDatabase.getInstance().baseAccountDao().selectAll().isEmpty()) {
                    CoroutineScope(Dispatchers.Main).launch {
                        supportFragmentManager.beginTransaction()
                            .add(R.id.fragment_container, EmptyWalletFragment()).commit()
                    }
                } else {
                    CoroutineScope(Dispatchers.Main).launch {
                        Intent(this@IntroActivity, MainActivity::class.java).apply {
                            startActivity(this)
                            flags = Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                        }
                    }
                }
            }
        }
    }

    private fun checkAppVersion() {
        introViewModel.walletAppVersionResult.observe(this) { appVersion ->
            appVersion?.let { response ->
                if (!response.enable) {
                    showDisableDialog()
                } else if (response.version > BuildConfig.VERSION_CODE) {
                    showUpdateDialog()
                } else {
                    postProcessAppVersion()
                }
            }
        }

        introViewModel.errorMessage.observe(this) {
            showNetworkErrorDialog()
        }
    }

    private fun initPriceInfo() {
        introViewModel.price(BaseData.currencyName().lowercase())
        introViewModel.asset()
    }

    private fun initFirebase() {
        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@addOnCompleteListener
            }
            val token = task.result
            if (Prefs.fcmToken != token) {
                if (Prefs.alarmEnable) {
//                    syncAddresses(token)
//                    updateStatus(token)
                }
                Prefs.fcmToken = token
            }
        }
    }

    private fun showNetworkErrorDialog() {
        val snackBar = Snackbar.make(binding.root, "Network error", Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction("Retry") {
            checkAppVersion()
        }
        snackBar.show()
    }

    private fun showDisableDialog() {
    }

    private fun showUpdateDialog() {
    }
}