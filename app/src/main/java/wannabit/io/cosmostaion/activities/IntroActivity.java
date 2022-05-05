package wannabit.io.cosmostaion.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.romainpiel.shimmer.ShimmerTextView;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_ChoiceNet;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResVersionCheck;
import wannabit.io.cosmostaion.utils.WLog;



public class IntroActivity extends BaseActivity implements View.OnClickListener {

    private ImageView       bgImg, bgImgGr;
    private ShimmerTextView logoTitle;
    private LinearLayout    bottomLayer1, bottomLayer2;
    private Button          mStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        bgImg               = findViewById(R.id.intro_bg);
        bgImgGr             = findViewById(R.id.intro_bg_gr);
        logoTitle           = findViewById(R.id.logo_title);
        bottomLayer1        = findViewById(R.id.bottom_layer1);
        bottomLayer2        = findViewById(R.id.bottom_layer2);
        mStart              = findViewById(R.id.btn_start);
        mNeedLeaveTime = false;

        mStart.setOnClickListener(this);

        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            return;
                        }
                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        WLog.w("FCM token new : " + token);
                        getBaseDao().setFCMToken(token);
                    }
                });

        getBaseDao().upgradeAaccountAddressforPath();
    }


    @Override
    protected void onPostResume() {
        super.onPostResume();
        onCheckAppVersion();
    }

    private void onInitJob() {
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(getBaseDao().onSelectAccounts().size() == 0) {
                    onInitView();
                } else {
                    if(getBaseApplication().needShowLockScreen()) {
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
        Animation fadeInAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_in5 );
        Animation fadeOutAnimation = AnimationUtils.loadAnimation(this, R.anim.fade_out5 );
        bgImgGr.startAnimation(fadeInAnimation);
        bgImg.startAnimation(fadeOutAnimation);

        final Animation mFadeInAni = AnimationUtils.loadAnimation(this, R.anim.fade_in2);
        Animation mFadeOutAni = AnimationUtils.loadAnimation(this, R.anim.fade_out2);
        mFadeOutAni.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }
            @Override
            public void onAnimationRepeat(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                bottomLayer2.setVisibility(View.VISIBLE);
                bottomLayer2.startAnimation(mFadeInAni);
            }
        });
        bottomLayer1.startAnimation(mFadeOutAni);


//        logoTitle.setVisibility(View.VISIBLE);
//        Shimmer shimmer = new Shimmer();
//        shimmer.setDuration(1500)
//                .setStartDelay(600)
//                .setDirection(Shimmer.ANIMATION_DIRECTION_LTR);
//        shimmer.start(logoTitle);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mStart)) {
            Bundle bundle = new Bundle();
            Dialog_ChoiceNet dialog = Dialog_ChoiceNet.newInstance(bundle);
            dialog.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();
        }
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
                if(BuildConfig.DEBUG) { WLog.w("onCheckAppVersion onFailure " + t.getMessage()); }
                onNetworkDialog();

            }
        });

    }

    private void onNetworkDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.str_network_error_title)
                .setMessage(R.string.str_network_error_msg)
                .setPositiveButton("Retry", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onCheckAppVersion();
                    }
                });
        AlertDialog ad = builder.create();
        ad.show();

    }

    private void onDisableDialog() {
        new AlertDialog.Builder(this)
                .setTitle(R.string.str_disabled_app_title)
                .setMessage(R.string.str_disabled_app_msg)
                .setPositiveButton(R.string.str_confirm,null)
                .create()
                .show();
    }

    private void onUpdateDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(R.string.str_update_title)
                .setMessage(R.string.str_update_msg)
                .setPositiveButton(R.string.str_go_store, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        onStartPlaystore();
                    }
                });
        AlertDialog ad = builder.create();
        ad.show();

    }

    public void onStartPlaystore() {
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("market://details?id=" + this.getPackageName()));
        startActivity(intent);
    }

}

