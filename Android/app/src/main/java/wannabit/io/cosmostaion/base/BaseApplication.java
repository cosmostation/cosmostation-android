package wannabit.io.cosmostaion.base;

import android.app.Application;

public class BaseApplication extends Application {

    private BaseData     mBaseData;

    public BaseData getBaseDao() {
        if (mBaseData == null)
            mBaseData = new BaseData(this);
        return mBaseData;
    }

}
