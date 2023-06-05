package wannabit.io.cosmostaion.ui.main

import android.app.Application
import android.webkit.WebView
import com.google.firebase.FirebaseApp
import net.sqlcipher.database.SQLiteDatabase
import wannabit.io.cosmostaion.BuildConfig

class CosmostationApp : Application() {
    companion object {
        lateinit var instance: CosmostationApp
            private set
    }

    init {
        instance = this
    }

    override fun onCreate() {
        super.onCreate()
        instance = this
        FirebaseApp.initializeApp(this)
        SQLiteDatabase.loadLibs(this)
        //DeviceUuidFactory(this)
        //initWalletConnectV2()
        //Picasso
        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true)
        }
    }
}