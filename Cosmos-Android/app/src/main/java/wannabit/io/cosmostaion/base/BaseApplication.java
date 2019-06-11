package wannabit.io.cosmostaion.base;

import android.app.Application;

import com.google.firebase.FirebaseApp;
import com.squareup.picasso.Picasso;

import wannabit.io.cosmostaion.utils.DeviceUuidFactory;
import wannabit.io.cosmostaion.utils.WLog;

public class BaseApplication extends Application {

    private BaseData     mBaseData;

    @Override
    public void onCreate() {
        super.onCreate();
        FirebaseApp.initializeApp(this);
        new DeviceUuidFactory(this);

        Picasso.Builder builder = new Picasso.Builder(this);
        Picasso built = builder.build();
        built.setIndicatorsEnabled(true);
        Picasso.setSingletonInstance(built);
    }

    public BaseData getBaseDao() {
        if (mBaseData == null)
            mBaseData = new BaseData(this);
        return mBaseData;
    }

}
