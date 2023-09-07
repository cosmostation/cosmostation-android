package wannabit.io.cosmostaion.ui.main

import android.app.Application
import android.webkit.WebView
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelStore
import androidx.lifecycle.ViewModelStoreOwner
import com.google.firebase.FirebaseApp
import net.sqlcipher.database.SQLiteDatabase
import wannabit.io.cosmostaion.BuildConfig
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

    override fun getViewModelStore(): ViewModelStore {
        return mViewModelStore
    }

    override fun onCreate() {
        super.onCreate()
        applicationViewModel = ViewModelProvider(this, ViewModelProvider.AndroidViewModelFactory(this))[ApplicationViewModel::class.java]
        initialize()
        FirebaseApp.initializeApp(this)
        SQLiteDatabase.loadLibs(this)
        //DeviceUuidFactory(this)
        //initWalletConnectV2()
        //Picasso
        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
    }

    private fun initialize() {
        if (Prefs.passphrase.isEmpty()) {
            Prefs.passphrase = CipherHelper.encrypt(UUID.randomUUID().toString())
        }
    }
}