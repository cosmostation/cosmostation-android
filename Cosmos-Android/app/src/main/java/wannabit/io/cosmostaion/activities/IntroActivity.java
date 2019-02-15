package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.Bundle;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

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

        if(getBaseDao().onSelectAccounts().size() > 1) {
            onStartListActivity();
        } else if(getBaseDao().onSelectAccounts().size() == 1)  {
            onStartMainActivity();
        } else {
            startActivity(new Intent(IntroActivity.this, CreateActivity.class));
        }
    }
}

