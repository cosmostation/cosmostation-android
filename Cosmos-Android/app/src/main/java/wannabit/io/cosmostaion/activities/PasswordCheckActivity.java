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
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.fragment.AlphabetKeyBoardFragment;
import wannabit.io.cosmostaion.fragment.KeyboardFragment;
import wannabit.io.cosmostaion.fragment.NumberKeyBoardFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.ReInvestTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleBnbSendTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleChangeRewardAddressTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleCreateCdpTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleDelegateTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleDepositCdpTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleDrawBetCdpTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleRedelegateTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleRepayCdpTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleRewardTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleSendTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleUndelegateTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleVoteTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.UserTask.CheckMnemonicTask;
import wannabit.io.cosmostaion.task.UserTask.CheckPasswordTask;
import wannabit.io.cosmostaion.task.UserTask.DeleteUserTask;
import wannabit.io.cosmostaion.utils.KeyboardListener;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.StopViewPager;

public class PasswordCheckActivity extends BaseActivity implements KeyboardListener, TaskListener {

    private LinearLayout                mLayerContents;
    private TextView                    mPassowrdTitle, mPassowrdMsg1, mPassowrdMsg2;
    private ImageView[]                 mIvCircle = new ImageView[5];

    private StopViewPager               mViewPager;
    private KeyboardPagerAdapter        mAdapter;

    private String                      mUserInput = "";
    private int                         mPurpose;
    private boolean                     mAskQuite;

    private String                      mTargetAddress;
    private ArrayList<Coin>             mTargetCoins;
    private String                      mTargetMemo;
    private Fee                         mTargetFee;
    private Coin                        mDAmount;
    private Coin                        mUAmount;
    private Validator                   mFromReDelegate;
    private Validator                   mToReDelegate;
    private Coin                        mRAmount;
    private ArrayList<Validator>        mValidators = new ArrayList<>();
    private String                      mNewRewardAddress;

    private Validator                   mReInvestValidator;
    private Coin                        mReInvestAmount;

    private String                      mProposalId;
    private String                      mOpinion;

    private ArrayList<Coin>             mCollateralCoins;
    private ArrayList<Coin>             mPrincipalCoins;
    private ArrayList<Coin>             mPaymentCoins;
    private String                      mSender;
    private String                      mOwner;
    private String                      mDepositor;
    private String                      mCdpDenom;

    private long                        mIdToDelete;
    private long                        mIdToCheck;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_set);
        mLayerContents  = findViewById(R.id.layer_contents);
        mPassowrdTitle  = findViewById(R.id.tv_password_title);
        mPassowrdMsg1   = findViewById(R.id.tv_password_msg1);
        mPassowrdMsg2   = findViewById(R.id.tv_password_msg2);
        mViewPager      = findViewById(R.id.pager_keyboard);
        mPassowrdMsg2.setVisibility(View.INVISIBLE);
        mNeedLeaveTime = false;

        for(int i = 0; i < mIvCircle.length; i++) {
            mIvCircle[i] = findViewById(getResources().getIdentifier("img_circle" + i , "id", getPackageName()));
        }

        mViewPager.setOffscreenPageLimit(2);
        mAdapter = new KeyboardPagerAdapter(getSupportFragmentManager());
        mViewPager.setAdapter(mAdapter);

        mPurpose = getIntent().getIntExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_SIMPLE_CHECK);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mTargetAddress = getIntent().getStringExtra("toAddress");
        mTargetCoins = getIntent().getParcelableArrayListExtra("amount");
        mTargetMemo = getIntent().getStringExtra("memo");
        mTargetFee = getIntent().getParcelableExtra("fee");
        mDAmount = getIntent().getParcelableExtra("dAmount");
        mUAmount = getIntent().getParcelableExtra("uAmount");
        mRAmount = getIntent().getParcelableExtra("rAmount");
        mFromReDelegate = getIntent().getParcelableExtra("fromValidator");
        mToReDelegate = getIntent().getParcelableExtra("toValidator");
        mValidators = getIntent().getParcelableArrayListExtra("validators");
        mNewRewardAddress = getIntent().getStringExtra("newRewardAddress");
        mReInvestValidator = getIntent().getParcelableExtra("reInvestValidator");
        mReInvestAmount = getIntent().getParcelableExtra("reInvestAmount");
        mProposalId = getIntent().getStringExtra("proposal_id");
        mOpinion = getIntent().getStringExtra("opinion");
        mCollateralCoins = getIntent().getParcelableArrayListExtra("collateralCoins");
        mPrincipalCoins = getIntent().getParcelableArrayListExtra("principalCoins");
        mPaymentCoins = getIntent().getParcelableArrayListExtra("payment");
        mSender = getIntent().getStringExtra("sender");
        mOwner = getIntent().getStringExtra("owner");
        mDepositor = getIntent().getStringExtra("depositor");
        mCdpDenom = getIntent().getStringExtra("cdp_denom");


        mIdToDelete = getIntent().getLongExtra("id", -1);
        mIdToCheck  = getIntent().getLongExtra("checkid", -1);

        onInitView();
    }

    @Override
    public void onBackPressed() {
        if(mUserInput != null && mUserInput.length() > 0) {
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

        mAskQuite = false;
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
        if (mPurpose == BaseConstant.CONST_PW_SIMPLE_CHECK) {
            onShowWaitDialog();
            new CheckPasswordTask(getBaseApplication(), this).execute(mUserInput);

        } else if (mPurpose == BaseConstant.CONST_PW_TX_SIMPLE_SEND) {
            onShowWaitDialog();
            if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.BNB_MAIN)) {
                new SimpleBnbSendTask(getBaseApplication(),
                        this,
                        mAccount,
                        mTargetAddress,
                        mTargetCoins,
                        mTargetMemo,
                        mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
            } else {
                new SimpleSendTask(getBaseApplication(),
                        this,
                        mAccount,
                        mTargetAddress,
                        mTargetCoins,
                        mTargetMemo,
                        mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
            }


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
            onShowWaitDialog();
            new SimpleUndelegateTask(getBaseApplication(),
                    this,
                    mAccount,
                    mTargetAddress,
                    mUAmount,
                    mTargetMemo,
                    mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);

        } else if (mPurpose == BaseConstant.CONST_PW_TX_SIMPLE_REWARD) {
            onShowWaitDialog();
            new SimpleRewardTask(getBaseApplication(),
                    this,
                    mAccount,
                    mValidators,
                    mTargetMemo,
                    mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);

        } else if (mPurpose == BaseConstant.CONST_PW_DELETE_ACCOUNT) {
            onShowWaitDialog();
            new DeleteUserTask(getBaseApplication(), this).execute(mUserInput);

        } else if (mPurpose == BaseConstant.CONST_PW_CHECK_MNEMONIC) {
            onShowWaitDialog();
            new CheckMnemonicTask(getBaseApplication(), this, getBaseDao().onSelectAccount(""+mIdToCheck)).execute(mUserInput);

        } else if (mPurpose == BaseConstant.CONST_PW_TX_SIMPLE_REDELEGATE) {
            onShowWaitDialog();
            new SimpleRedelegateTask(getBaseApplication(),
                    this,
                    mAccount,
                    mFromReDelegate,
                    mToReDelegate,
                    mRAmount,
                    mTargetMemo,
                    mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);

        } else if (mPurpose == BaseConstant.CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
            onShowWaitDialog();
            new SimpleChangeRewardAddressTask(getBaseApplication(),
                    this,
                    mAccount,
                    mNewRewardAddress,
                    mTargetMemo,
                    mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);

        } else if (mPurpose == BaseConstant.CONST_PW_TX_REINVEST) {
            onShowWaitDialog();
            new ReInvestTask(getBaseApplication(),
                    this,
                    mAccount,
                    mReInvestValidator.operator_address,
                    mReInvestAmount,
                    mTargetMemo,
                    mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);

        } else if (mPurpose == BaseConstant.CONST_PW_TX_VOTE) {
            onShowWaitDialog();
            new SimpleVoteTask(getBaseApplication(),
                    this,
                    mAccount,
                    mProposalId,
                    mOpinion,
                    mTargetMemo,
                    mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);

        } else if (mPurpose == BaseConstant.CONST_PW_TX_CREATE_CDP) {
            onShowWaitDialog();
            new SimpleCreateCdpTask(getBaseApplication(),
                    this,
                    mAccount,
                    mSender,
                    mCollateralCoins,
                    mPrincipalCoins,
                    mTargetMemo,
                    mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);

        } else if (mPurpose == BaseConstant.CONST_PW_TX_REPAY_CDP) {
            onShowWaitDialog();
            new SimpleRepayCdpTask(getBaseApplication(),
                    this,
                    mAccount,
                    mSender,
                    mPaymentCoins,
                    mCdpDenom,
                    mTargetMemo,
                    mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);

        } else if (mPurpose == BaseConstant.CONST_PW_TX_DRAW_DEBT_CDP) {
            onShowWaitDialog();
            new SimpleDrawBetCdpTask(getBaseApplication(),
                    this,
                    mAccount,
                    mSender,
                    mPrincipalCoins,
                    mCdpDenom,
                    mTargetMemo,
                    mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);

        } else if (mPurpose == BaseConstant.CONST_PW_TX_DEPOSIT_CDP) {
            onShowWaitDialog();
            new SimpleDepositCdpTask(getBaseApplication(),
                    this,
                    mAccount,
                    mOwner,
                    mDepositor,
                    mCollateralCoins,
                    mTargetMemo,
                    mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);

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
                setResult(Activity.RESULT_OK, getIntent());
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
                    result.taskType == BaseConstant.TASK_GEN_TX_SIMPLE_REWARD ||
                    result.taskType == BaseConstant.TASK_GEN_TX_SIMPLE_REDELEGATE ||
                    result.taskType == BaseConstant.TASK_GEN_TX_SIMPLE_REWARD_ADDRESS_CHANGE ||
                    result.taskType == BaseConstant.TASK_GEN_TX_REINVEST ||
                    result.taskType == BaseConstant.TASK_GEN_TX_BNB_SIMPLE_SEND ||
                    result.taskType == BaseConstant.TASK_GEN_TX_SIMPLE_VOTE ||
                    result.taskType == BaseConstant.TASK_GEN_TX_CREATE_CDP ||
                    result.taskType == BaseConstant.TASK_GEN_TX_REPAY_CDP ||
                    result.taskType == BaseConstant.TASK_GEN_TX_DRAW_DEBT_CDP ||
                    result.taskType == BaseConstant.TASK_GEN_TX_DEPOSIT_CDP) {
            if(!result.isSuccess && result.errorCode == BaseConstant.ERROR_CODE_INVALID_PASSWORD) {
                onShakeView();
                return;
            }

            if (mBaseChain.equals(BaseChain.COSMOS_MAIN) || mBaseChain.equals(BaseChain.KAVA_MAIN) || mBaseChain.equals(BaseChain.KAVA_TEST)) {
                Intent txIntent = new Intent(PasswordCheckActivity.this, TxDetailActivity.class);
                txIntent.putExtra("isGen", true);
                txIntent.putExtra("isSuccess", result.isSuccess);
                txIntent.putExtra("errorCode", result.errorCode);
                txIntent.putExtra("errorMsg", result.errorMsg);
                String hash = String.valueOf(result.resultData);
                if(!TextUtils.isEmpty(hash))
                    txIntent.putExtra("txHash", hash);
                startActivity(txIntent);

            } else {
                Intent txIntent = new Intent(PasswordCheckActivity.this, TxResultActivity.class);
                txIntent.putExtra("txType", result.taskType);
                txIntent.putExtra("isSuccess", result.isSuccess);
                String hash = String.valueOf(result.resultData);
                if(!TextUtils.isEmpty(hash))
                    txIntent.putExtra("txHash", hash);
                txIntent.putExtra("errorCode", result.errorCode);
                txIntent.putExtra("errorMsg", result.errorMsg);

                startActivity(txIntent);
            }



        } else if (result.taskType == BaseConstant.TASK_DELETE_USER) {
            if(result.isSuccess) {
                onDeleteAccount(mIdToDelete);

            } else {
                onShakeView();
                onInitView();
                Toast.makeText(getBaseContext(), getString(R.string.error_invalid_password), Toast.LENGTH_SHORT).show();

            }
        } else if (result.taskType == BaseConstant.TASK_CHECK_MNEMONIC) {
            if(result.isSuccess) {
                Intent checkintent = new Intent(PasswordCheckActivity.this, MnemonicCheckActivity.class);
                checkintent.putExtra("checkid", mIdToCheck);
                checkintent.putExtra("entropy", String.valueOf(result.resultData));
                startActivity(checkintent);

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
