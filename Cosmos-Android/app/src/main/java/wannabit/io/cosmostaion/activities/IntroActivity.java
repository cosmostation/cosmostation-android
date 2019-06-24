package wannabit.io.cosmostaion.activities;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.util.ArrayList;
import java.util.Set;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.utils.DeviceUuidFactory;
import wannabit.io.cosmostaion.utils.WLog;

public class IntroActivity extends BaseActivity implements View.OnClickListener {

    private ShimmerTextView logoTitle;
    private LinearLayout    bottomLayer1, bottomLayer2, bottomDetail, btnImportMnemonic, btnWatchAddress;
    private Button          mCreate, mImport;
    private LinearLayout    mNmemonicLayer, mWatchLayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        logoTitle           = findViewById(R.id.logo_title);
        bottomLayer1        = findViewById(R.id.bottom_layer1);
        bottomLayer2        = findViewById(R.id.bottom_layer2);
        bottomDetail        = findViewById(R.id.import_detail);
        btnImportMnemonic   = findViewById(R.id.btn_import_mnemonic);
        btnWatchAddress     = findViewById(R.id.btn_watch_address);
        mImport             = findViewById(R.id.btn_import);
        mCreate             = findViewById(R.id.btn_create);
        mNeedLeaveTime = false;

        mNmemonicLayer = findViewById(R.id.import_mnemonic_layer);
        mWatchLayer = findViewById(R.id.import_watch_layer);


        mImport.setOnClickListener(this);
        mCreate.setOnClickListener(this);
        btnImportMnemonic.setOnClickListener(this);
        btnWatchAddress.setOnClickListener(this);

        Bundle pushBundle = getIntent().getExtras();
        if(pushBundle != null) {
            Set<String> keys = pushBundle.keySet();
            for (String key:keys) {
                WLog.w("push " + key + " : " + pushBundle.get(key));
            }
        }

        WLog.w("UUID  " + new DeviceUuidFactory(this).getDeviceUuidS());
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.P) {
            WLog.w("" + ((ActivityManager)getSystemService(Context.ACTIVITY_SERVICE)).isBackgroundRestricted());
        }

//        WLog.w("FCM token Already : " + FirebaseInstanceId.getInstance().getInstanceId().getResult().getToken());
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
                    }
                });

        onHardCodeHub2();
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
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
                        onStartMainActivity();
                    }
                }
            }
        }, 1500);
    }

    private void onInitView(){
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


        logoTitle.setVisibility(View.VISIBLE);
        Shimmer shimmer = new Shimmer();
        shimmer.setDuration(1500)
                .setStartDelay(600)
                .setDirection(Shimmer.ANIMATION_DIRECTION_LTR);
        shimmer.start(logoTitle);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mImport)) {
            mImport.setVisibility(View.GONE);
            bottomDetail.setVisibility(View.VISIBLE);

            Animation fadein = AnimationUtils.loadAnimation(this, R.anim.fade_in4);
            mNmemonicLayer.startAnimation(fadein);
            mWatchLayer.startAnimation(fadein);


        } else if (v.equals(mCreate)) {
            startActivity(new Intent(IntroActivity.this, CreateActivity.class));

        } else if (v.equals(btnImportMnemonic)) {
            startActivity(new Intent(IntroActivity.this, RestoreActivity.class));

        } else if (v.equals(btnWatchAddress)) {
            startActivity(new Intent(IntroActivity.this, WatchingAccountAddActivity.class));
        }

    }

    public void onHardCodeHub2() {
        ArrayList<Account> accounts = getBaseDao().onSelectAccounts();
        for(Account account:accounts) {
            account.baseChain = BaseChain.COSMOS_MAIN.getChain();
            getBaseDao().onUpdateAccount(account);
        }
    }
}

