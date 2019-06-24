package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.support.v7.widget.SwitchCompat;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;

public class AppLockSetActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar         mToolbar;
    private FrameLayout     mBtnUsingAppLock, mBtnUsingFingerprint, mBtnAppLockTime;
    private SwitchCompat    mSwitchUsingAppLock, mSwitchUsingFingerprint;
    private TextView        mTvAppLockTime;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_applock_set);
        mToolbar                    = findViewById(R.id.tool_bar);
        mBtnUsingAppLock            = findViewById(R.id.card_using_applock);
        mBtnUsingFingerprint        = findViewById(R.id.card_using_fingerprint);
        mBtnAppLockTime             = findViewById(R.id.card_applock_time);
        mSwitchUsingAppLock         = findViewById(R.id.switch_using_applock);
        mSwitchUsingFingerprint     = findViewById(R.id.switch_fingerprint);
        mTvAppLockTime              = findViewById(R.id.applock_time_text);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBtnUsingAppLock.setOnClickListener(this);
        mBtnUsingFingerprint.setOnClickListener(this);
        mBtnAppLockTime.setOnClickListener(this);

    }

    private void onUpdateView() {
        if(getBaseDao().getUsingAppLock()) {
            mBtnUsingFingerprint.setVisibility(View.GONE);
            mBtnAppLockTime.setVisibility(View.GONE);

        } else {
            mBtnUsingFingerprint.setVisibility(View.VISIBLE);
            mBtnAppLockTime.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnUsingAppLock)) {

        } else if (v.equals(mBtnUsingFingerprint)) {

        } else if (v.equals(mBtnAppLockTime)) {

        }
    }
}
