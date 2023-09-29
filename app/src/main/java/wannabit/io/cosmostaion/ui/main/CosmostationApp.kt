package wannabit.io.cosmostaion.ui.main

import android.app.Activity
import android.app.Application
import android.content.Context
import android.os.Bundle
import android.webkit.WebView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.google.firebase.FirebaseApp
import net.sqlcipher.database.SQLiteDatabase
import wannabit.io.cosmostaion.BuildConfig
import wannabit.io.cosmostaion.common.BaseUtils
import wannabit.io.cosmostaion.database.AppDatabase
import wannabit.io.cosmostaion.database.CipherHelper
import wannabit.io.cosmostaion.database.Prefs
import wannabit.io.cosmostaion.ui.viewmodel.ApplicationViewModel
import java.util.UUID

class CosmostationApp : Application(), ViewModelStoreOwner {
    companion object {
        lateinit var instance: CosmostationApp
            private set
    }

    init {
        instance = this
    }

    private val mViewModelStore = ViewModelStore()
    lateinit var applicationViewModel: ApplicationViewModel
    private var appStatus: AppStatus? = null

    override fun getViewModelStore(): ViewModelStore {
        return mViewModelStore
    }

    override fun onCreate() {
        super.onCreate()
        registerActivityLifecycleCallbacks(ApplicationLifecycleStatus())
        applicationViewModel = ViewModelProvider(
            this,
            ViewModelProvider.AndroidViewModelFactory(this)
        )[ApplicationViewModel::class.java]
        initialize()
        FirebaseApp.initializeApp(this)
        SQLiteDatabase.loadLibs(this)

        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
    }

    private fun initialize() {
        if (Prefs.passphrase.isEmpty()) {
            Prefs.passphrase = CipherHelper.encrypt(UUID.randomUUID().toString())
        }
    }

    private fun isReturnedForeground(): Boolean {
        return appStatus?.ordinal == AppStatus.RETURNED_TO_FOREGROUND.ordinal
    }

    fun needShowLockScreen(): Boolean {
        if (!isReturnedForeground() || AppDatabase.getInstance().passwordDao().selectAll().isEmpty() || !Prefs.appLock || AppDatabase.getInstance().baseAccountDao().selectAll().isEmpty())
            return false
        return true
    }

    class ApplicationLifecycleStatus : ActivityLifecycleCallbacks {
        private var running = 0

        override fun onActivityStarted(activity: Activity) {
            if (++running == 1) {
                instance.appStatus = AppStatus.RETURNED_TO_FOREGROUND
            } else if (running > 1) {
                instance.appStatus = AppStatus.FOREGROUND
            }
        }

        override fun onActivityStopped(activity: Activity) {
            if (--running == 0) {
                instance.appStatus = AppStatus.BACKGROUND
            }
        }

        override fun onActivityCreated(activity: Activity, savedInstanceState: Bundle?) {}

        override fun onActivityResumed(activity: Activity) {}

        override fun onActivityPaused(activity: Activity) {}

        override fun onActivitySaveInstanceState(activity: Activity, outState: Bundle) {}

        override fun onActivityDestroyed(activity: Activity) {}
    }

    enum class AppStatus {
        BACKGROUND, RETURNED_TO_FOREGROUND, FOREGROUND
    }
}