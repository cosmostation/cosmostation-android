package wannabit.io.cosmostaion.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

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
import wannabit.io.cosmostaion.task.UserTask.InitPasswordTask;
import wannabit.io.cosmostaion.utils.KeyboardListener;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.StopViewPager;

public class PasswordSetActivity extends BaseActivity implements KeyboardListener, TaskListener {

    private LinearLayout mLayerContents;
    private TextView mPassowrdTitle, mPassowrdMsg1, mPassowrdMsg2;
    private ImageView[] mIvCircle = new ImageView[5];

    private StopViewPager mViewPager;
    private KeyboardPagerAdapter mAdapter;

    private String mUserInput = "";
    private String mConfirmInput = "";
    private boolean mIsConfirmSequence;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_set);
        mLayerContents = findViewById(R.id.layer_contents);
        mPassowrdTitle = findViewById(R.id.tv_password_title);
        mPassowrdMsg1 = findViewById(R.id.tv_password_msg1);
        mPassowrdMsg2 = findViewById(R.id.tv_password_msg2);
        mViewPager = findViewById(R.id.pager_keyboard);
        mNeedLeaveTime = false;

        for (int i = 0; i < mIvCircle.length; i++) {
            mIvCircle[i] = findViewById(getResources().getIdentifier("img_circle" + i, "id", getPackageName()));
        }

        mViewPager.setOffscreenPageLimit(2);
        mAdapter = new KeyboardPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        onInitView();
    }

    private void onInitView() {
        mIsConfirmSequence = false;
        mUserInput = "";
        mConfirmInput = "";

        for (int i = 0; i < mIvCircle.length; i++) {
            mIvCircle[i].setBackground(getDrawable(R.drawable.ic_pass_gr));
        }
        mViewPager.setCurrentItem(0, true);
    }

    private void onUpdateCnt() {
        if (mUserInput == null)
            mUserInput = "";

        final int inputLength = mUserInput.length();
        for (int i = 0; i < mIvCircle.length; i++) {
            if (i < inputLength)
                mIvCircle[i].setBackground(getDrawable(R.drawable.ic_pass_pu));
            else
                mIvCircle[i].setBackground(getDrawable(R.drawable.ic_pass_gr));
        }
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

    private void onFinishInput() {
        if (mIsConfirmSequence) {
            mPassowrdMsg1.setVisibility(View.INVISIBLE);
            if (mConfirmInput.equals(mUserInput)) {
                onShowWaitDialog();
                new InitPasswordTask(getBaseApplication(), this).execute(mConfirmInput);

            } else {
                onShakeView();
                Toast.makeText(getBaseContext(), getString(R.string.error_msg_password_not_same), Toast.LENGTH_SHORT).show();
            }

        } else {
            mPassowrdMsg1.setVisibility(View.VISIBLE);
            if (mAdapter != null && mAdapter.getFragments() != null) {
                for (KeyboardFragment frag : mAdapter.getFragments()) {
                    if (frag != null)
                        frag.onShuffleKeyboard();
                }
            }
            mIsConfirmSequence = true;
            mConfirmInput = mUserInput;
            mUserInput = "";
            for (int i = 0; i < mIvCircle.length; i++) {
                mIvCircle[i].setBackground(getDrawable(R.drawable.ic_pass_gr));
            }
            mViewPager.setCurrentItem(0, true);
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
            onFinishInput();

        } else if (mUserInput.length() == 5 && !WUtil.checkPasscodePattern(mUserInput)) {
            onInitView();
            return;
        }
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

    @Override
    public void onBackPressed() {
        if (mUserInput != null && mUserInput.length() > 0) {
            userDeleteKey();
        } else if (mIsConfirmSequence) {
            mIsConfirmSequence = false;
            mConfirmInput = "";
            onInitView();
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        onHideWaitDialog();
        onNextPage();
    }

    public void onNextPage() {
        Intent result = new Intent();
        setResult(Activity.RESULT_OK, result);
        finish();
        overridePendingTransition(R.anim.fade_in, R.anim.slide_out_bottom);
    }


    public class KeyboardPagerAdapter extends FragmentPagerAdapter {

        private ArrayList<KeyboardFragment> mFragments = new ArrayList<>();

        public KeyboardPagerAdapter(FragmentManager fm) {
            super(fm);
            mFragments.clear();
            NumberKeyBoardFragment number = NumberKeyBoardFragment.newInstance();
            number.setListener(PasswordSetActivity.this);
            mFragments.add(number);

            AlphabetKeyBoardFragment alphabet = AlphabetKeyBoardFragment.newInstance();
            alphabet.setListener(PasswordSetActivity.this);
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
