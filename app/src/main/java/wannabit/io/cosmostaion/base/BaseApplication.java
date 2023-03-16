package wannabit.io.cosmostaion.base;

import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.webkit.WebView;

import androidx.appcompat.app.AppCompatDelegate;

import com.google.common.collect.Lists;
import com.google.firebase.FirebaseApp;
import com.squareup.picasso.Picasso;
import com.walletconnect.android.Core;
import com.walletconnect.android.CoreClient;
import com.walletconnect.android.relay.ConnectionType;
import com.walletconnect.sign.client.Sign;
import com.walletconnect.sign.client.SignClient;

import java.util.UUID;

import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.utils.DeviceUuidFactory;
import wannabit.io.cosmostaion.utils.ThemeUtil;

public class BaseApplication extends Application {

    private BaseData mBaseData;
    private AppStatus mAppStatus;
    private static BaseApplication instance;

    public static BaseApplication getInstance() {
        return instance;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        instance = this;
        FirebaseApp.initializeApp(this);
        new DeviceUuidFactory(this);

        registerActivityLifecycleCallbacks(new LifecycleCallbacks());

        Picasso.Builder builder = new Picasso.Builder(this);
        Picasso built = builder.build();
        built.setIndicatorsEnabled(false);
        Picasso.setSingletonInstance(built);

        getBaseDao().mCopySalt = UUID.randomUUID().toString();

        String themeColor = ThemeUtil.modLoad(getApplicationContext());
        ThemeUtil.applyTheme(themeColor);

        if (themeColor.equals(ThemeUtil.DEFAULT_MODE)) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM);
            }
            // 안드로이드 10 미만
            else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_AUTO_BATTERY);
            }
        } else if (themeColor.equals(ThemeUtil.LIGHT_MODE)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        } else if (themeColor.equals(ThemeUtil.DARK_MODE)) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        }

        initWalletConnectV2();

        if (BuildConfig.DEBUG) {
            WebView.setWebContentsDebuggingEnabled(true);
        }
    }

    private void initWalletConnectV2() {
        String projectId = BuildConfig.WALLETCONNECT_API_KEY;
        String relayUrl = "relay.walletconnect.com";
        String serverUrl = "wss://" + relayUrl + "?projectId=" + projectId;
        ConnectionType connectionType = ConnectionType.AUTOMATIC;
        Core.Model.AppMetaData metaData = new Core.Model.AppMetaData(getString(R.string.str_wc_peer_name), getString(R.string.str_wc_peer_url), getString(R.string.str_wc_peer_desc), Lists.newArrayList(), "cosmostation://wc");
        CoreClient.INSTANCE.initialize(metaData, serverUrl, connectionType, this, null);
        SignClient.INSTANCE.initialize(new Sign.Params.Init(CoreClient.INSTANCE), error -> null);
    }

    public BaseData getBaseDao() {
        if (mBaseData == null) mBaseData = new BaseData(this);
        return mBaseData;
    }

    public boolean isReturnedForground() {
        return mAppStatus.ordinal() == AppStatus.RETURNED_TO_FOREGROUND.ordinal();
    }

    public boolean needShowLockScreen() {
        if (!isReturnedForground() || !getBaseDao().onHasPassword() || !getBaseDao().getUsingAppLock() || (getBaseDao().onSelectAccounts().size() <= 0))
            return false;
        return true;
    }

    public enum AppStatus {
        BACKGROUND, RETURNED_TO_FOREGROUND, FOREGROUND;
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
