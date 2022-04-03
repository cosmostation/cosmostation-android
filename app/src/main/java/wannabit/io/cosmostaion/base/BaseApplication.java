package wannabit.io.cosmostaion.base;

import android.app.Activity;
import android.app.Application;
import android.os.Bundle;

import androidx.annotation.NonNull;

import com.fulldive.wallet.di.EnrichableLifecycleCallbacks;
import com.fulldive.wallet.di.IInjectorHolder;
import com.fulldive.wallet.di.components.ApplicationComponent;
import com.joom.lightsaber.Injector;
import com.joom.lightsaber.Lightsaber;
import com.squareup.picasso.Picasso;

import java.util.UUID;

import wannabit.io.cosmostaion.utils.DeviceUuidFactory;

public class BaseApplication extends Application implements IInjectorHolder {

    private Injector appInjector;

    private BaseData mBaseData;
    private AppStatus mAppStatus;

    @Override
    public void onCreate() {
        super.onCreate();
        appInjector = new Lightsaber.Builder().build().createInjector(new ApplicationComponent(getApplicationContext()));

        //        FirebaseApp.initializeApp(this);
        new DeviceUuidFactory(this);

        registerActivityLifecycleCallbacks(new LifecycleCallbacks());
        registerActivityLifecycleCallbacks(new EnrichableLifecycleCallbacks(this));

        Picasso.Builder builder = new Picasso.Builder(this);
        Picasso built = builder.build();
        built.setIndicatorsEnabled(false);
        Picasso.setSingletonInstance(built);

        getBaseDao().mCopySalt = UUID.randomUUID().toString();
    }

    public BaseData getBaseDao() {
        if (mBaseData == null)
            mBaseData = new BaseData(this);
        return mBaseData;
    }

    public boolean isReturnedForeground() {
        return mAppStatus.ordinal() == AppStatus.RETURNED_TO_FOREGROUND.ordinal();
    }

    public boolean needShowLockScreen() {
        if (!isReturnedForeground() ||
                !getBaseDao().onHasPassword() ||
                !getBaseDao().getUsingAppLock() ||
                (getBaseDao().onSelectAccounts().size() <= 0)) return false;

        if (getBaseDao().getAppLockTriggerTime() == 0) {
            return true;
        } else if (getBaseDao().getAppLockTriggerTime() == 1) {
            return (getBaseDao().getAppLockLeaveTime() + BaseConstant.CONSTANT_10S) < System.currentTimeMillis();
        } else if (getBaseDao().getAppLockTriggerTime() == 2) {
            return (getBaseDao().getAppLockLeaveTime() + BaseConstant.CONSTANT_30S) < System.currentTimeMillis();
        } else if (getBaseDao().getAppLockTriggerTime() == 3) {
            return (getBaseDao().getAppLockLeaveTime() + BaseConstant.CONSTANT_M) < System.currentTimeMillis();
        }
        return true;
    }

    @NonNull
    @Override
    public Injector getInjector() {
        return appInjector;
    }

    public enum AppStatus {
        BACKGROUND,
        RETURNED_TO_FOREGROUND,
        FOREGROUND;
    }

    public class LifecycleCallbacks implements ActivityLifecycleCallbacks {

        private int running = 0;

        @Override
        public void onActivityStarted(Activity activity) {
            if (++running == 1) {
                mAppStatus = AppStatus.RETURNED_TO_FOREGROUND;
                getBaseDao().mCopySalt = UUID.randomUUID().toString();
            } else if (running > 1) {
                mAppStatus = AppStatus.FOREGROUND;
            }

        }

        @Override
        public void onActivityStopped(Activity activity) {
            if (--running == 0) {
                mAppStatus = AppStatus.BACKGROUND;
                getBaseDao().mCopySalt = null;
                getBaseDao().mCopyEncResult = null;
            }
        }

        @Override
        public void onActivityCreated(Activity activity, Bundle savedInstanceState) {
        }

        @Override
        public void onActivityResumed(Activity activity) {
        }

        @Override
        public void onActivityPaused(Activity activity) {
        }


        @Override
        public void onActivitySaveInstanceState(Activity activity, Bundle outState) {
        }

        @Override
        public void onActivityDestroyed(Activity activity) {
        }
    }
}
