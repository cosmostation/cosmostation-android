package wannabit.io.cosmostaion.utils;

import android.util.Log;

import wannabit.io.cosmostaion.BuildConfig;

public class WLog {
    public final static String LOG_TAG = "FDWallet";

    public static void e(String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(LOG_TAG, msg + "\n");
        }
    }

    public static void w(String msg) {
        if (BuildConfig.DEBUG) {
            Log.w(LOG_TAG, msg + "\n");
        }
    }
}
