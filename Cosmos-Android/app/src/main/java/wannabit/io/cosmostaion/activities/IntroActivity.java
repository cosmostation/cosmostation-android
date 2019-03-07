package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.FirebaseApp;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqTx;
import wannabit.io.cosmostaion.network.res.ResHistory;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.test.TestActivity;
import wannabit.io.cosmostaion.utils.DeviceUuidFactory;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

import static com.romainpiel.shimmer.Shimmer.ANIMATION_DIRECTION_LTR;
/*
public class IntroActivity extends BaseActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);

//        startActivity(new Intent(IntroActivity.this, TestActivity.class));
//
//        startActivity(new Intent(IntroActivity.this, CreateActivity.class));

//        startActivity(new Intent(IntroActivity.this, PasswordSetActivity.class));
//        startActivity(new Intent(IntroActivity.this, MainActivity.class));


//        Retrofit retrofit = new Retrofit.Builder()
//                .baseUrl(getString(R.string.url_lcd_wannabit))
//                .client(WUtil.getUnsafeOkHttpClient().build())
//                .addConverterFactory(GsonConverterFactory.create())
//                .build();
//
//        retrofit.create(retorfitTest.class).getVersion().enqueue(new Callback<JsonObject>() {
//            @Override
//            public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
//                WLog.w("111 : " + response.body().toString());
//            }
//
//            @Override
//            public void onFailure(Call<JsonObject> call, Throwable t) {
//                WLog.w("2222 " + t.getMessage());
//            }
//        });

//        WLog.w("" + WKey.convertDpAddressToDpOpAddress("cosmos1gfzexy3z0qfc97mjudjy5zeg2fje6k442phy6r"));
//        WLog.w("" + WKey.convertDpOpAddressToDpAddress("cosmosvaloper1gfzexy3z0qfc97mjudjy5zeg2fje6k4404r3ks"));

//        if(getBaseDao().onSelectAccounts().size() > 1) {
//            onStartListActivity();
//        } else if(getBaseDao().onSelectAccounts().size() == 1)  {
//            onStartMainActivity();
//        } else {
//            startActivity(new Intent(IntroActivity.this, CreateActivity.class));
//        }
    }
}
*/

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

        mNmemonicLayer = findViewById(R.id.import_mnemonic_layer);
        mWatchLayer = findViewById(R.id.import_watch_layer);


        mImport.setOnClickListener(this);
        mCreate.setOnClickListener(this);
        btnImportMnemonic.setOnClickListener(this);
        btnWatchAddress.setOnClickListener(this);

//        Account account = getBaseDao().onSelectAccounts().get(0);
//        WLog.w("account : " + account.address + "  " + account.baseChain);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
//                if(getBaseDao().onSelectAccounts().size() == 0) {
//                    onInitView();
//                } else if(getBaseDao().onSelectAccounts().size() == 1)  {
//                    onStartMainActivity();
//                } else {
//                    onStartListActivity();
//                }
                if(getBaseDao().onSelectAccounts().size() == 0) {
                    onInitView();
                } else {
                    onStartMainActivity();
                }
            }
        }, 1500);

//        onShowWaitDialog();

//        WLog.w("INTRO");
//        startActivity(new Intent(IntroActivity.this, TestActivity.class));

        WLog.w("UUID  " + new DeviceUuidFactory(this).getDeviceUuidS());
//        WLog.w("FCM token: " + FirebaseInstanceId.getInstance().getInstanceId().toString());
        FirebaseApp app = FirebaseApp.initializeApp(this);
        if(app == null) {
            WLog.w("app null");
        }
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {
                        if (!task.isSuccessful()) {
                            WLog.w("getInstanceId failed" + task.getException());
                            return;
                        }

                        // Get new Instance ID token
                        String token = task.getResult().getToken();
                        WLog.w("token  " + token);

//                        // Log and toast
//                        String msg = getString(R.string.msg_token_fmt, token);
//                        WLog.w( msg);
//                        Toast.makeText(MainActivity.this, msg, Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @Override
    protected void onResume() {
        super.onResume();

//        startActivity(new Intent(IntroActivity.this, TestActivity.class));

//        logoTitle.setVisibility(View.INVISIBLE);


//        startActivity(new Intent(IntroActivity.this, RestoreActivity.class));

//        startActivity(new Intent(IntroActivity.this, CreateActivity.class));
//        startActivity(new Intent(IntroActivity.this, PasswordSetActivity.class));

//        Intent intent = new Intent(IntroActivity.this, PasswordCheckActivity.class);
//        startActivityForResult(intent, BaseConstant.CONST_PW_SIMPLE_CHECK);
//        overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);



//        if(bottomLayer2.getVisibility() != View.VISIBLE)
//            onInitView();
//
//        onTest();
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

//            final Animation fadein = AnimationUtils.loadAnimation(this, R.anim.fade_in4);
//            Animation fadeout = AnimationUtils.loadAnimation(this, R.anim.fade_out4);
//            fadeout.setAnimationListener(new Animation.AnimationListener() {
//                @Override
//                public void onAnimationStart(Animation animation) { }
//                @Override
//                public void onAnimationRepeat(Animation animation) { }
//
//                @Override
//                public void onAnimationEnd(Animation animation) {
//                    mImport.setVisibility(View.GONE);
//                    mImport.setClickable(false);
//                    bottomDetail.setVisibility(View.VISIBLE);
//                    bottomDetail.startAnimation(fadein);
//                }
//            });
//            mImport.startAnimation(fadeout);

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

//    @Override
//    protected void onStop() {
//        super.onStop();
//        finish();
//    }
}

