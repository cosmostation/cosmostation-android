package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_APP_LOCK;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_AUTO_PASS;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_DELETE_WALLET;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_PURPOSE;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.core.content.ContextCompat;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;
import androidx.core.os.CancellationSignal;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.fragment.AlphabetKeyBoardFragment;
import wannabit.io.cosmostaion.fragment.KeyboardFragment;
import wannabit.io.cosmostaion.fragment.NumberKeyBoardFragment;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.UserTask.CheckPasswordTask;
import wannabit.io.cosmostaion.utils.KeyboardListener;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.StopViewPager;

public class PasswordCheckActivity extends BaseActivity implements KeyboardListener, TaskListener {

    private LinearLayout mLayerContents;
    private TextView mPassowrdTitle, mPassowrdMsg1, mPassowrdMsg2;
    private ImageView[] mIvCircle = new ImageView[5];

    private StopViewPager mViewPager;
    private KeyboardPagerAdapter mAdapter;

    private String mUserInput = "";
    private int mPurpose;
    private boolean mAskQuite;

    private FingerprintManagerCompat mFingerprintManagerCompat;
    private CancellationSignal mCancellationSignal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_set);
        mLayerContents = findViewById(R.id.layer_contents);
        mPassowrdTitle = findViewById(R.id.tv_password_title);
        mPassowrdMsg1 = findViewById(R.id.tv_password_msg1);
        mPassowrdMsg2 = findViewById(R.id.tv_password_msg2);
        mViewPager = findViewById(R.id.pager_keyboard);
        mPassowrdMsg2.setVisibility(View.GONE);
        mNeedLeaveTime = false;

        for (int i = 0; i < mIvCircle.length; i++) {
            mIvCircle[i] = findViewById(getResources().getIdentifier("img_circle" + i, "id", getPackageName()));
        }

        mViewPager.setOffscreenPageLimit(2);
        mAdapter = new KeyboardPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        mPurpose = getIntent().getIntExtra(CONST_PW_PURPOSE, -1);
        onInitView();
    }

    @Override
    public void onBackPressed() {
        if (mUserInput != null && mUserInput.length() > 0) {
            userDeleteKey();
        } else {
            if (mAskQuite) {
                setResult(Activity.RESULT_CANCELED, getIntent());
                finish();
            } else {
                mAskQuite = true;
                Toast.makeText(getBaseContext(), R.string.str_ready_to_quite, Toast.LENGTH_SHORT).show();
            }
        }
    }

    private void onInitView() {
        mPassowrdTitle.setText(getString(R.string.str_init_password));
        mPassowrdMsg1.setText(getString(R.string.str_init_password));
        mUserInput = "";

        for (int i = 0; i < mIvCircle.length; i++) {
            mIvCircle[i].setBackground(ContextCompat.getDrawable(PasswordCheckActivity.this, R.drawable.ic_pass_gr));
        }
        mViewPager.setCurrentItem(0, true);
        if (getBaseDao().getUsingFingerPrint()) {
            if (mPurpose != CONST_PW_DELETE_WALLET && mPurpose != CONST_PW_APP_LOCK && mPurpose != CONST_PW_AUTO_PASS) {
                onCheckFingerPrint();
            }
        }
    }

    @Override
    public void userInsertKey(char input) {
        if (mUserInput == null || mUserInput.length() == 0) {
            mUserInput = String.valueOf(input);

        } else if (mUserInput.length() < 5) {
            mUserInput = mUserInput + input;
        }

        if (mUserInput.length() == 4) {
            mViewPager.setCurrentItem(1, true);

        } else if (mUserInput.length() == 5 && WUtil.checkPasscodePattern(mUserInput)) {
            onShowWaitDialog();
            new CheckPasswordTask(getBaseApplication(), this).execute(mUserInput);

        } else if (mUserInput.length() == 5 && !WUtil.checkPasscodePattern(mUserInput)) {
            onInitView();
            return;
        }

        mAskQuite = false;
        onUpdateCnt();

    }

    @Override
    public void userDeleteKey() {
        if (mUserInput == null || mUserInput.length() <= 0) {
            onBackPressed();
        } else if (mUserInput.length() == 4) {
            mUserInput = mUserInput.substring(0, mUserInput.length() - 1);
            mViewPager.setCurrentItem(0, true);
        } else {
            mUserInput = mUserInput.substring(0, mUserInput.length() - 1);
        }
        onUpdateCnt();
    }

    private void onShakeView() {
        mLayerContents.clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
        animation.reset();
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                onInitView();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {
            }
        });
        mLayerContents.startAnimation(animation);
    }

    private void onCheckFingerPrint() {
        mFingerprintManagerCompat = FingerprintManagerCompat.from(this);
        mCancellationSignal = new CancellationSignal();
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) && mFingerprintManagerCompat.isHardwareDetected() &&
                mFingerprintManagerCompat.hasEnrolledFingerprints() && getBaseDao().getUsingFingerPrint()) {

            mFingerprintManagerCompat.authenticate(null, 0, mCancellationSignal, new FingerprintManagerCompat.AuthenticationCallback() {
                @Override
                public void onAuthenticationError(int errMsgId, CharSequence errString) {
                    super.onAuthenticationError(errMsgId, errString);
                }

                @Override
                public void onAuthenticationHelp(int helpMsgId, CharSequence helpString) {
                    super.onAuthenticationHelp(helpMsgId, helpString);
                }

                @Override
                public void onAuthenticationSucceeded(FingerprintManagerCompat.AuthenticationResult result) {
                    super.onAuthenticationSucceeded(result);
                    if (mCancellationSignal != null)
                        mCancellationSignal.cancel();
                    setResult(Activity.RESULT_OK, getIntent());
                    finish();
                    overridePendingTransition(R.anim.fade_in, R.anim.slide_out_bottom);
                }

                @Override
                public void onAuthenticationFailed() {
                    super.onAuthenticationFailed();
                }

            }, null);
        }
    }

    private void onUpdateCnt() {
        if (mUserInput == null)
            mUserInput = "";

        final int inputLength = mUserInput.length();
        for (int i = 0; i < mIvCircle.length; i++) {
            if (i < inputLength)
                mIvCircle[i].setBackground(ContextCompat.getDrawable(PasswordCheckActivity.this, R.drawable.ic_pass_pu));
            else
                mIvCircle[i].setBackground(ContextCompat.getDrawable(PasswordCheckActivity.this, R.drawable.ic_pass_gr));
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        onHideWaitDialog();
        if (result.isSuccess) {
            if (mPurpose == CONST_PW_AUTO_PASS) {
                getBaseDao().setLastPassTime();
            }
            setResult(Activity.RESULT_OK, getIntent());
            finish();
            overridePendingTransition(R.anim.fade_in, R.anim.slide_out_bottom);
        } else {
            onShakeView();
            onInitView();
            Toast.makeText(getBaseContext(), getString(R.string.error_invalid_password), Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }

    public class KeyboardPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<KeyboardFragment> mFragments = new ArrayList<>();

        public KeyboardPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            NumberKeyBoardFragment number = NumberKeyBoardFragment.newInstance();
            number.setListener(PasswordCheckActivity.this);
            mFragments.add(number);

            AlphabetKeyBoardFragment alphabet = AlphabetKeyBoardFragment.newInstance();
            alphabet.setListener(PasswordCheckActivity.this);
            mFragments.add(alphabet);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        public ArrayList<KeyboardFragment> getFragments() {
            return mFragments;
        }
    }
}
