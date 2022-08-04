package wannabit.io.cosmostaion.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.text.Html;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.google.firebase.messaging.FirebaseMessaging;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.dialog.Dialog_AddAccount;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResVersionCheck;
import wannabit.io.cosmostaion.utils.PushManager;
import wannabit.io.cosmostaion.utils.WLog;


public class IntroActivity extends BaseActivity implements View.OnClickListener {

    private ImageView bgImg;
    private LinearLayout bottomLayer1, bottomLayer2;
    private Button mStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        bgImg = findViewById(R.id.intro_bg);
        bottomLayer1 = findViewById(R.id.bottom_layer1);
        bottomLayer2 = findViewById(R.id.bottom_layer2);
        mStart = findViewById(R.id.btn_start);
        mNeedLeaveTime = false;

        mStart.setOnClickListener(this);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        FirebaseMessaging.getInstance().getToken().addOnCompleteListener(task -> {
            if (!task.isSuccessful()) {
                return;
            }
            String token = task.getResult();
            if (!getBaseDao().getFCMToken().equals(token)) {
                if (getBaseDao().getTxPushEnable() || getBaseDao().getNoticePushEnable()) {
                    PushManager.syncAddresses(this, getBaseDao(), token);
                } else {
                    getBaseDao().setFCMToken(token);
                }
            }
        });

        getBaseDao().upgradeAaccountAddressforPath();
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        if (getBaseDao().getDBVersion() < BaseConstant.DB_VERSION) {
            onShowDBUpdate();
        } else {
            onCheckAppVersion();
        }
    }

    private void onInitJob() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if (getBaseDao().onSelectAccounts().size() == 0) {
                    onInitView();
                } else {
                    if (getBaseApplication().needShowLockScreen()) {
                        Intent intent = new Intent(IntroActivity.this, AppLockActivity.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
                    } else {
                        if (getIntent().getExtras() != null && getIntent().getExtras().getString("notifyto") != null) {
                            Account account = getBaseDao().onSelectExistAccount2(getIntent().getExtras().getString("notifyto"));
                            if (account != null) {
                                getBaseDao().setLastUser(account.id);
                                onStartMainActivity(2);
                                return;
                            }
                        }
                        onStartMainActivity(0);
                    }
                }
            }
        }, 2500);

    }

    private void onInitView() {
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out5);
        bgImg.startAnimation(fadeOutAnimation);

        final Animation mFadeInAni = AnimationUtils.loadAnimation(this, R.anim.fade_in2);
        Animation mFadeOutAni = AnimationUtils.loadAnimation(this, R.anim.fade_out2);
        mFadeOutAni.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                bottomLayer2.setVisibility(View.VISIBLE);
                bottomLayer2.startAnimation(mFadeInAni);
            }
        });
        bottomLayer1.startAnimation(mFadeOutAni);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mStart)) {
            Dialog_AddAccount add = Dialog_AddAccount.newInstance(null);
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
        }
    }

    private void onShowDBUpdate() {
        ProgressDialog dialog = new ProgressDialog(IntroActivity.this);
        dialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        dialog.setTitle("DB Upgrading..");
        dialog.setMessage("Please wait for upgrade\n(Do not close the application)");
        dialog.setCancelable(false);
        dialog.show();
        Thread update = new Thread() {
            @Override
            public void run() {
                getBaseDao().upgradeMnemonicDB();

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        dialog.dismiss();
                        getBaseDao().setDBVersion(BaseConstant.DB_VERSION);
                        onCheckAppVersion();
                    }
                });
            }
        };
        update.start();
    }

    private void onCheckAppVersion() {
        ApiClient.getCosmostationOld(this).getVersion().enqueue(new Callback<ResVersionCheck>() {
            @Override
            public void onResponse(Call<ResVersionCheck> call, Response<ResVersionCheck> response) {
                if (response.isSuccessful()) {
                    if (!response.body().enable) {
                        onDisableDialog();
                    } else {
                        if (response.body().version > BuildConfig.VERSION_CODE) {
                            onUpdateDialog();
                        } else {
                            onInitJob();
                        }
                    }
                } else {
                    onNetworkDialog();
                }
            }

            @Override
            public void onFailure(Call<ResVersionCheck> call, Throwable t) {
                if (BuildConfig.DEBUG) {
                    WLog.w("onCheckAppVersion onFailure " + t.getMessage());
                }
                onNetworkDialog();

            }
        });

    }

    private void onNetworkDialog() {
        AlertDialogUtils.showSingleButtonDialog(this, getString(R.string.str_network_error_title), getString(R.string.str_network_error_msg),
                getString(R.string.str_retry), view -> onRetryVersionCheck(), false);
    }

    private void onDisableDialog() {
        AlertDialogUtils.showSingleButtonDialog(this, getString(R.string.str_disabled_app_title), getString(R.string.str_disabled_app_msg),
                getString(R.string.str_confirm), view -> finish(), false);
    }

    private void onUpdateDialog() {
        AlertDialogUtils.showSingleButtonDialog(this, getString(R.string.str_update_title), getString(R.string.str_update_msg),
                Html.fromHtml("<font color=\"#05D2DD\">" + getString(R.string.str_go_store) + "</font>"), view -> onStartPlaystore(), false);
    }

    public void onRetryVersionCheck() {
        onCheckAppVersion();
    }

    public void onStartPlaystore() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + this.getPackageName()));
        startActivity(intent);
    }

}

