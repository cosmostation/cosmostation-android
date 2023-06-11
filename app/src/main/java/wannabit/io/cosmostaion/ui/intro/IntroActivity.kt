package wannabit.io.cosmostaion.ui.intro

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.snackbar.Snackbar
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.R
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.database.legacy.LegacyMigrationHelper
import wannabit.io.cosmostaion.databinding.ActivityIntroBinding
import wannabit.io.cosmostaion.network.AppVersion
import wannabit.io.cosmostaion.network.CosmostationService
import wannabit.io.cosmostaion.ui.main.DashboardActivity

class IntroActivity : AppCompatActivity() {
    private lateinit var binding: ActivityIntroBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityIntroBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFirebase()
        checkAppVersion()
        migrateDatabaseIfNeed()
    }

    private fun migrateDatabaseIfNeed() = CoroutineScope(Dispatchers.IO).launch {
        LegacyMigrationHelper.migratePassword()
        LegacyMigrationHelper.migrateWallet()
    }


    private fun postProcessAppVersion() = CoroutineScope(Dispatchers.IO).launch {
        if (AppDatabase.getInstance().walletDao().selectAll().isEmpty()) {
            CoroutineScope(Dispatchers.Main).launch {
                supportFragmentManager.beginTransaction().add(R.id.fragment_container, EmptyWalletFragment()).commit()
            }
        } else {
            CoroutineScope(Dispatchers.Main).launch {
                startActivity(Intent(this@IntroActivity, DashboardActivity::class.java))
                finish()
            }
//                if (CosmostationApp.instance.needShowLockScreen()) {
//                    val intent = Intent(this@IntroActivity, AppLockActivity::class.java)
//                    startActivity(intent)
//                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out)
//                } else {
//                    if (intent.extras != null) if (intent.extras!!.getString("address") != null) {
//                        val account = baseDao.onSelectExistAccount2(intent.extras!!.getString("address"))
//                        if (account != null) {
//                            Prefs.lastUserId = account.id
//                            IntentUtils.startMainActivity(this@IntroActivity, 2)
//                            return@launch
//                        }
//                    } else if (intent.extras!!.getString("url") != null) {
//                        IntentUtils.startMainActivity(this@IntroActivity, 0)
//                        val intent = Intent(this@IntroActivity, AlertDialogActivity::class.java)
//                        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP or Intent.FLAG_ACTIVITY_NEW_TASK)
//                        intent.putExtra("title", getString(R.string.app_name))
//                        intent.putExtra("body", getIntent().extras!!.getString("body"))
//                        intent.putExtra("link", getIntent().extras!!.getString("url"))
//                        startActivity(intent)
//                        return@launch
//                    }
//                    IntentUtils.startMainActivity(this@IntroActivity, 0)
        }
    }

    private fun checkAppVersion() {
        CosmostationService.create().getVersion().enqueue(object : Callback<AppVersion> {
            override fun onResponse(call: Call<AppVersion>, response: Response<AppVersion>) {
                if (response.isSuccessful) {
                    response.body()?.let {
                        if (!it.enable) {
                            showDisableDialog()
                        } else if (it.version > BuildConfig.VERSION_CODE) {
                            showUpdateDialog()
                        } else {
                            postProcessAppVersion()
                        }
                    } ?: run {
                        showNetworkErrorDialog()
                    }
                } else {
                    showNetworkErrorDialog()
                }
            }

            override fun onFailure(call: Call<AppVersion>, t: Throwable) {
                showNetworkErrorDialog()
            }
        })
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