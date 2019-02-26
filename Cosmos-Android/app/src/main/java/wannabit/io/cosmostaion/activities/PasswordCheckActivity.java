package wannabit.io.cosmostaion.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.text.TextUtils;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_Tx_Result;
import wannabit.io.cosmostaion.dialog.Dialog_UsingBio;
import wannabit.io.cosmostaion.fragment.AlphabetKeyBoardFragment;
import wannabit.io.cosmostaion.fragment.KeyboardFragment;
import wannabit.io.cosmostaion.fragment.NumberKeyBoardFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleDelegateTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleRewardTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleSendTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleUndelegateTask;
import wannabit.io.cosmostaion.task.UserTask.CheckPasswordTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.UserTask.DeleteUserTask;
import wannabit.io.cosmostaion.utils.KeyboardListener;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.StopViewPager;

public class PasswordCheckActivity extends BaseActivity implements KeyboardListener, TaskListener {

    private LinearLayout            mLayerContents;
    private TextView                mPassowrdTitle, mPassowrdMsg1, mPassowrdMsg2;
    private ImageView[]             mIvCircle = new ImageView[5];

    private StopViewPager               mViewPager;
    private KeyboardPagerAdapter        mAdapter;

    private String                      mUserInput = "";
    private int                         mPurpose;


    private Account                  mAccount;
    private String                   mTargetAddress;
    private ArrayList<Coin>          mTargetCoins;
    private String                   mTargetMemo;
    private Fee                      mTargetFee;
    private Coin                     mDAmount;
    private String                   mUAmount;

    private long                     mIdToDelete;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_set);
        mLayerContents  = findViewById(R.id.layer_contents);
        mPassowrdTitle  = findViewById(R.id.tv_password_title);
        mPassowrdMsg1   = findViewById(R.id.tv_password_msg1);
        mPassowrdMsg2   = findViewById(R.id.tv_password_msg2);
        mViewPager      = findViewById(R.id.pager_keyboard);
        mPassowrdMsg2.setVisibility(View.INVISIBLE);

        for(int i = 0; i < mIvCircle.length; i++) {
            mIvCircle[i] = findViewById(getResources().getIdentifier("img_circle" + i , "id", getPackageName()));
        }

        mViewPager.setOffscreenPageLimit(2);
        mAdapter = new KeyboardPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        mPurpose = getIntent().getIntExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_SIMPLE_CHECK);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mTargetAddress = getIntent().getStringExtra("toAddress");
        mTargetCoins = getIntent().getParcelableArrayListExtra("amount");
        mTargetMemo = getIntent().getStringExtra("memo");
        mTargetFee = getIntent().getParcelableExtra("fee");
        mDAmount = getIntent().getParcelableExtra("dAmount");
        mUAmount = getIntent().getStringExtra("uAmount");

        mIdToDelete = getIntent().getLongExtra("id", -1);

        if(mDAmount != null)
            WLog.w("mDAmount " + mDAmount.denom + "  " + mDAmount.amount);

        if(mUAmount != null)
            WLog.w("mUAmount " + mUAmount);

        if(mTargetCoins != null)
            WLog.w("amlout " + mTargetCoins.get(0).denom + "  " + mTargetCoins.get(0).amount);

        if(mTargetFee != null)
            WLog.w("fee " + mTargetFee.gas + " " + mTargetFee.amount.get(0).denom + " " +  mTargetFee.amount.get(0).amount);



        onInitView();
    }

    @Override
    public void onBackPressed() {
        if(mUserInput != null && mUserInput.length() > 0) {
            userDeleteKey();
        } else {
            super.onBackPressed();
        }
    }

    private void onInitView() {
        mPassowrdTitle.setText(getString(R.string.str_init_password));
        mPassowrdMsg1.setText(getString(R.string.str_init_password));
        mUserInput = "";

        for(int i = 0; i < mIvCircle.length; i++) {
            mIvCircle[i].setBackground(getDrawable(R.drawable.ic_pass_gr));
        }
        mViewPager.setCurrentItem(0, true);
    }

    @Override
    public void userInsertKey(char input) {
        if(mUserInput == null || mUserInput.length() == 0) {
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
        if(mUserInput == null || mUserInput.length() <= 0) {
            onBackPressed();
        } else if (mUserInput.length() == 4) {
            mUserInput = mUserInput.substring(0, mUserInput.length()-1);
            mViewPager.setCurrentItem(0, true);
        } else {
            mUserInput = mUserInput.substring(0, mUserInput.length()-1);
        }
        onUpdateCnt();
    }


    private void onFinishInput() {
        //TODO Start Task
        if (mPurpose == BaseConstant.CONST_PW_SIMPLE_CHECK) {
            onShowWaitDialog();
            new CheckPasswordTask(getBaseApplication(), this).execute(mUserInput);

        } else if (mPurpose == BaseConstant.CONST_PW_TX_SIMPLE_SEND) {
            onShowWaitDialog();
            new SimpleSendTask(getBaseApplication(),
                    this,
                    mAccount,
                    mTargetAddress,
                    mTargetCoins,
                    mTargetMemo,
                    mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);

        } else if (mPurpose == BaseConstant.CONST_PW_TX_SIMPLE_DELEGATE) {
            onShowWaitDialog();
            new SimpleDelegateTask(getBaseApplication(),
                    this,
                    mAccount,
                    mTargetAddress,
                    mDAmount,
                    mTargetMemo,
                    mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);

        } else if (mPurpose == BaseConstant.CONST_PW_TX_SIMPLE_UNDELEGATE) {
            new SimpleUndelegateTask(getBaseApplication(),
                    this,
                    mAccount,
                    mTargetAddress,
                    mUAmount,
                    mTargetMemo,
                    mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);

        } else if (mPurpose == BaseConstant.CONST_PW_TX_SIMPLE_REWARD) {
            new SimpleRewardTask(getBaseApplication(),
                    this,
                    mAccount,
                    mTargetAddress,
                    mTargetMemo,
                    mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);

        } else if (mPurpose == BaseConstant.CONST_PW_DELETE_ACCOUNT) {
            onShowWaitDialog();
            new DeleteUserTask(getBaseApplication(), this).execute(mUserInput);

        }
    }

    private void onShakeView() {
        mLayerContents.clearAnimation();
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.shake);
        animation.reset();
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) { }

            @Override
            public void onAnimationEnd(Animation animation) {
                onInitView();
            }

            @Override
            public void onAnimationRepeat(Animation animation) { }
        });
        mLayerContents.startAnimation(animation);
    }


    private void onUpdateCnt() {
        if(mUserInput == null)
            mUserInput = "";

        final int inputLength = mUserInput.length();
        for(int i = 0; i < mIvCircle.length; i++) {
            if(i < inputLength)
                mIvCircle[i].setBackground(getDrawable(R.drawable.ic_pass_pu));
            else
                mIvCircle[i].setBackground(getDrawable(R.drawable.ic_pass_gr));
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        onHideWaitDialog();
        if (result.taskType == BaseConstant.TASK_PASSWORD_CHECK) {
            if(result.isSuccess) {
                setResult(Activity.RESULT_OK, new Intent());
                finish();
                overridePendingTransition(R.anim.fade_in, R.anim.slide_out_bottom);
            } else {
                onShakeView();
                onInitView();
                Toast.makeText(getBaseContext(), getString(R.string.error_invalid_password), Toast.LENGTH_SHORT).show();
            }

        } else if (result.taskType == BaseConstant.TASK_GEN_TX_SIMPLE_SEND ||
                    result.taskType == BaseConstant.TASK_GEN_TX_SIMPLE_DELEGATE ||
                    result.taskType == BaseConstant.TASK_GEN_TX_SIMPLE_UNDELEGATE ||
                    result.taskType == BaseConstant.TASK_GEN_TX_SIMPLE_REWARD) {
            if(result.isSuccess) {
                String hash = String.valueOf(result.resultData);
                if(!TextUtils.isEmpty(hash)) {
                    Bundle bundle = new Bundle();
                    bundle.putString("hash", hash);
                    Dialog_Tx_Result  dialog = Dialog_Tx_Result.newInstance(bundle);
                    dialog.setCancelable(false);
                    dialog.show(getSupportFragmentManager(), "dialog");
                } else {
                    onStartMainActivity();
                }
            } else {
                onStartMainActivity();
            }

        } else if (result.taskType == BaseConstant.TASK_DELETE_USER) {
            if(result.isSuccess) {
                onDeleteAccount(mIdToDelete);

            } else {
                onShakeView();
                onInitView();
                Toast.makeText(getBaseContext(), getString(R.string.error_invalid_password), Toast.LENGTH_SHORT).show();

            }
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
