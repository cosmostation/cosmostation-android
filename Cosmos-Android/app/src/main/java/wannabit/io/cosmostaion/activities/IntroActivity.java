package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.romainpiel.shimmer.Shimmer;
import com.romainpiel.shimmer.ShimmerTextView;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Random;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.test.TestActivity;
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

        if(getBaseDao().onSelectAccounts().size() > 1) {
            onStartListActivity();
        } else if(getBaseDao().onSelectAccounts().size() == 1)  {
            onStartMainActivity();
        } else {
            startActivity(new Intent(IntroActivity.this, CreateActivity.class));
        }
    }
}
*/

public class IntroActivity extends BaseActivity implements View.OnClickListener {

    private ShimmerTextView logoTitle;
    private LinearLayout    bottomLayer1, bottomLayer2;
    private Button          mCreate, mImport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro);
        logoTitle       = findViewById(R.id.logo_title);
        bottomLayer1    = findViewById(R.id.bottom_layer1);
        bottomLayer2    = findViewById(R.id.bottom_layer2);
        mImport         = findViewById(R.id.btn_import);
        mCreate         = findViewById(R.id.btn_create);
        mImport.setOnClickListener(this);
        mCreate.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();

//        startActivity(new Intent(IntroActivity.this, CreateActivity.class));
//        startActivity(new Intent(IntroActivity.this, PasswordSetActivity.class));

//        Intent intent = new Intent(IntroActivity.this, PasswordCheckActivity.class);
//        startActivityForResult(intent, BaseConstant.CONST_PW_SIMPLE_CHECK);
//        overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);


        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                if(getBaseDao().onSelectAccounts().size() == 0) {
                    onInitView();
                } else if(getBaseDao().onSelectAccounts().size() == 1)  {
                    onStartMainActivity();
                } else {
                    onStartListActivity();
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

        } else if (v.equals(mCreate)) {
            startActivity(new Intent(IntroActivity.this, CreateActivity.class));
        }

    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}

