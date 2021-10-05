package wannabit.io.cosmostaion.utils;

import android.util.Log;

import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.base.BaseConstant;

public class WLog {

    public static void e(String msg) {
        if (BuildConfig.DEBUG) {
            Log.e(BaseConstant.LOG_TAG, msg + "\n");
        }
    }

    public static void w(String msg) {
        if (BuildConfig.DEBUG) {
            Log.w(BaseConstant.LOG_TAG, msg+ "\n");
        }
    }
}
