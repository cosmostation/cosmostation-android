package wannabit.io.cosmostaion.ui.init

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.View
import android.view.WindowInsets
import android.view.WindowInsetsController
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.FirebaseApp
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.common.BaseConstant
import wannabit.io.cosmostaion.common.BaseData
import wannabit.io.cosmostaion.common.toMoveAnimation
import wannabit.io.cosmostaion.data.repository.address.AddressRepositoryImpl
import wannabit.io.cosmostaion.data.repository.wallet.WalletRepositoryImpl
import wannabit.io.cosmostaion.data.viewmodel.address.AddressBookViewModel
import wannabit.io.cosmostaion.data.viewmodel.address.AddressBookViewModelProviderFactory
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModel
import wannabit.io.cosmostaion.data.viewmodel.intro.WalletViewModelProviderFactory
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.legacy.LegacyMigrationHelper
import wannabit.io.cosmostaion.databinding.ActivityIntroBinding
import wannabit.io.cosmostaion.databinding.DialogUpdateAppBinding
import wannabit.io.cosmostaion.ui.main.CosmostationApp
import wannabit.io.cosmostaion.ui.main.MainActivity
import wannabit.io.cosmostaion.ui.main.dapp.DappActivity
import wannabit.io.cosmostaion.ui.main.setting.wallet.account.AccountInitListener
import wannabit.io.cosmostaion.ui.main.setting.wallet.account.AccountInitSelectFragment
import wannabit.io.cosmostaion.ui.password.AppLockActivity
import wannabit.io.cosmostaion.ui.password.PasswordCheckActivity

class IntroActivity : AppCompatActivity() {

    private lateinit var binding: ActivityIntroBinding

    private lateinit var walletViewModel: WalletViewModel
    private lateinit var addressBookViewModel: AddressBookViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFullScreen()
        initViewModel()
        initFirebase()
        migrateDatabaseIfNeed()
        checkAppVersion()
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
        val walletViewModelProviderFactory = WalletViewModelProviderFactory(walletRepository)
        walletViewModel =
            ViewModelProvider(this, walletViewModelProviderFactory)[WalletViewModel::class.java]

        val addressRepository = AddressRepositoryImpl()
        val addressBookViewModelProviderFactory =
            AddressBookViewModelProviderFactory(addressRepository)
        addressBookViewModel = ViewModelProvider(
            this, addressBookViewModelProviderFactory
        )[AddressBookViewModel::class.java]
    }

    override fun onPostResume() {
        super.onPostResume()
        walletViewModel.walletAppVersion()
    }

    private fun migrateDatabaseIfNeed() = CoroutineScope(Dispatchers.IO).launch {
        if (Prefs.version < BaseConstant.DB_VERSION) {
            LegacyMigrationHelper.migratePassword()
            LegacyMigrationHelper.migrateWallet()
        }

        updateAddressBook()
    }

    @SuppressLint("WrongConstant")
    private fun postProcessAppVersion() = CoroutineScope(Dispatchers.IO).launch {
        if (AppDatabase.getInstance().baseAccountDao().selectAll().isNotEmpty()) {
            val account = BaseData.getLastAccount()
            account?.let {
                BaseData.baseAccount = account
                account.initAccount()

                if (CosmostationApp.instance.needShowLockScreen()) {
                    val intent = Intent(this@IntroActivity, AppLockActivity::class.java)
                    startActivity(intent)
                    if (Build.VERSION.SDK_INT >= 34) {
                        overrideActivityTransition(
                            Activity.OVERRIDE_TRANSITION_OPEN,
                            R.anim.anim_slide_in_bottom,
                            R.anim.anim_fade_out
                        )
                    } else {
                        overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_fade_out)
                    }

                } else {
                    withContext(Dispatchers.Main) {
                        if (BaseData.appSchemeUrl.isNotEmpty()) {
                            Intent(this@IntroActivity, DappActivity::class.java).apply {
                                BaseData.isBackGround = false
                                putExtra("dappUrl", BaseData.appSchemeUrl)
                                addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP or Intent.FLAG_ACTIVITY_CLEAR_TOP)
                                startActivity(this)
                                if (Build.VERSION.SDK_INT >= 34) {
                                    overrideActivityTransition(
                                        Activity.OVERRIDE_TRANSITION_OPEN,
                                        R.anim.anim_slide_in_bottom,
                                        R.anim.anim_fade_out
                                    )
                                } else {
                                    overridePendingTransition(
                                        R.anim.anim_slide_in_bottom,
                                        R.anim.anim_fade_out
                                    )
                                }
                            }

                        } else {
                            Intent(this@IntroActivity, MainActivity::class.java).apply {
                                BaseData.isBackGround = true
                                if (intent.extras != null) {
                                    val pushType = try {
                                        intent.extras?.getString("push_type")
                                    } catch (e: Exception) {
                                        intent.extras?.getInt("push_type").toString()
                                    }
                                    putExtra("push_type", pushType)
                                    putExtra(
                                        "push_txhash",
                                        intent.extras?.getString("txhash") ?: ""
                                    )
                                    putExtra(
                                        "push_network",
                                        intent.extras?.getString("network") ?: ""
                                    )
                                }
                                startActivity(this)
                                flags =
                                    Intent.FLAG_ACTIVITY_CLEAR_TASK or Intent.FLAG_ACTIVITY_NEW_TASK
                            }
                        }
                    }
                }
            }

        } else {
            withContext(Dispatchers.Main) {
                initView()

                var isClickable = true
                binding.btnCreate.setOnClickListener {
                    if (isClickable) {
                        isClickable = false
                        AccountInitSelectFragment.newInstance(accountInitSelectAction).show(
                            supportFragmentManager, AccountInitSelectFragment::class.java.name
                        )
                    }
                    Handler(Looper.getMainLooper()).postDelayed({
                        isClickable = true
                    }, 1000)
                }
            }
        }
    }

    private val accountInitSelectAction = object : AccountInitListener {
        override fun initAction(initType: Int) {
            when (initType) {
                BaseConstant.CONST_NEW_ACCOUNT -> {
                    val intent = Intent(this@IntroActivity, PasswordCheckActivity::class.java)
                    createAccountResultLauncher.launch(intent)
                    pendingTransition()
                }

                BaseConstant.CONST_RESTORE_MNEMONIC_ACCOUNT -> {
                    val intent = Intent(this@IntroActivity, PasswordCheckActivity::class.java)
                    restoreMnemonicResultLauncher.launch(intent)
                    pendingTransition()
                }

                else -> {
                    val intent = Intent(this@IntroActivity, PasswordCheckActivity::class.java)
                    restorePrivateResultLauncher.launch(intent)
                    pendingTransition()
                }
            }
        }
    }

    private val createAccountResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                lifecycleScope.launch {
                    delay(300)
                    dismissInitSelectFragment()
                    withContext(Dispatchers.Main) {
                        Intent(this@IntroActivity, AccountInitActivity::class.java).apply {
                            putExtra("initType", BaseConstant.CONST_NEW_ACCOUNT)
                            startActivity(this)
                            toMoveAnimation()
                        }
                    }
                }
            }
        }

    private val restoreMnemonicResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                lifecycleScope.launch {
                    delay(300)
                    dismissInitSelectFragment()
                    withContext(Dispatchers.Main) {
                        Intent(this@IntroActivity, AccountInitActivity::class.java).apply {
                            putExtra("initType", BaseConstant.CONST_RESTORE_MNEMONIC_ACCOUNT)
                            startActivity(this)
                            toMoveAnimation()
                        }
                    }
                }
            }
        }

    private val restorePrivateResultLauncher: ActivityResultLauncher<Intent> =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) { result ->
            if (result.resultCode == Activity.RESULT_OK) {
                lifecycleScope.launch {
                    delay(300)
                    dismissInitSelectFragment()
                    withContext(Dispatchers.Main) {
                        Intent(this@IntroActivity, AccountInitActivity::class.java).apply {
                            putExtra("initType", BaseConstant.CONST_RESTORE_PRIVATE_ACCOUNT)
                            startActivity(this)
                            toMoveAnimation()
                        }
                    }
                }
            }
        }

    private fun dismissInitSelectFragment() {
        val accountInitSelectFragment =
            supportFragmentManager.findFragmentByTag(AccountInitSelectFragment::class.java.name)
        if (accountInitSelectFragment is BottomSheetDialogFragment) {
            accountInitSelectFragment.dismiss()
        }
    }

    @SuppressLint("WrongConstant")
    private fun pendingTransition() {
        if (Build.VERSION.SDK_INT >= 34) {
            overrideActivityTransition(
                Activity.OVERRIDE_TRANSITION_OPEN,
                R.anim.anim_slide_in_bottom,
                R.anim.anim_fade_out
            )
        } else {
            overridePendingTransition(R.anim.anim_slide_in_bottom, R.anim.anim_fade_out)
        }
    }

    private fun checkAppVersion() {
        walletViewModel.walletAppVersionResult.observe(this) { appVersion ->
            appVersion?.let { response ->
                if (!response.enable) {
                    showDisableDialog()
                } else if (response.version > BuildConfig.VERSION_CODE) {
                    showUpdateDialog()
                } else {
                    walletViewModel.defaultInfoData()
                }
            }
        }

        walletViewModel.defaultInfoDataResult.observe(this) {
            postProcessAppVersion()
        }

        walletViewModel.networkErrorMessage.observe(this) {
            showNetworkErrorDialog()
        }
    }

    private fun initFirebase() {
        if (FirebaseApp.getApps(this).isEmpty()) {
            FirebaseApp.initializeApp(this)
        }

        FirebaseMessaging.getInstance().token.addOnCompleteListener { task ->
            if (!task.isSuccessful) {
                return@addOnCompleteListener
            }
            val token = task.result
            if (Prefs.fcmToken != token) {
                Prefs.fcmToken = token
            }
        }
    }

    private fun initView() {
        val mFadeInAni = AnimationUtils.loadAnimation(this, R.anim.anim_fade_in)
        val mFadeOutAni = AnimationUtils.loadAnimation(this, R.anim.anim_fade_out)
        mFadeOutAni.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationRepeat(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                binding.btnCreate.visibility = View.VISIBLE
                binding.btnCreate.startAnimation(mFadeInAni)
            }
        })
        binding.bottomLayer.startAnimation(mFadeOutAni)
    }

    private fun updateAddressBook() {
        if (!Prefs.isUpdateAddressBook) {
            addressBookViewModel.updateAddressBookWithChainTag()
            Prefs.isUpdateAddressBook = true
        }
    }

    private fun showNetworkErrorDialog() {
        val snackBar = Snackbar.make(binding.root, "Network error", Snackbar.LENGTH_INDEFINITE)
        snackBar.setAction("Retry") {
            walletViewModel.walletAppVersion()
        }
        snackBar.show()
    }

    private fun showDisableDialog() {
        val binding = DialogUpdateAppBinding.inflate(layoutInflater)
        val alertDialog =
            AlertDialog.Builder(this, R.style.AppTheme_AlertDialogTheme).setView(binding.root)

        val dialog = alertDialog.create()
        dialog.show()
        dialog.setCancelable(false)

        binding.apply {
            dialogTitle.text = getString(R.string.str_under_maintenance)
            dialogMsg1.text = getString(R.string.str_disabled_app_msg)
            btnGoPlaystore.text = getString(R.string.str_confirm)
            btnGoPlaystore.setOnClickListener {
                finish()
            }
        }
    }

    private fun showUpdateDialog() {
        val binding = DialogUpdateAppBinding.inflate(layoutInflater)
        val alertDialog =
            AlertDialog.Builder(this, R.style.AppTheme_AlertDialogTheme).setView(binding.root)

        val dialog = alertDialog.create()
        dialog.setCancelable(false)
        dialog.show()

        binding.btnGoPlaystore.setOnClickListener {
            startPlayStore()
        }
    }

    private fun startPlayStore() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://details?id=" + this.packageName)
        startActivity(intent)
    }
}