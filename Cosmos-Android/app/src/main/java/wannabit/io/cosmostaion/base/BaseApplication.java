package wannabit.io.cosmostaion.base;

import android.app.Application;

import com.google.firebase.FirebaseApp;

import wannabit.io.cosmostaion.utils.DeviceUuidFactory;
import wannabit.io.cosmostaion.utils.WLog;

public class BaseApplication extends Application {

    private BaseData     mBaseData;

    @Override
    public void onCreate() {
        super.onCreate();
//        WLog.w("FirebaseApp initializeApp");
//        FirebaseApp.initializeApp(this);
        new DeviceUuidFactory(this);
    }

    public BaseData getBaseDao() {
        if (mBaseData == null)
            mBaseData = new BaseData(this);
        return mBaseData;
    }

}
