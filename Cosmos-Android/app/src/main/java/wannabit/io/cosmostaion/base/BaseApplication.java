package wannabit.io.cosmostaion.base;

import android.app.Application;

import wannabit.io.cosmostaion.utils.DeviceUuidFactory;

public class BaseApplication extends Application {

    private BaseData     mBaseData;

    @Override
    public void onCreate() {
        super.onCreate();
        new DeviceUuidFactory(this);
    }

    public BaseData getBaseDao() {
        if (mBaseData == null)
            mBaseData = new BaseData(this);
        return mBaseData;
    }

}
