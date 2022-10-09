package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_AUTO_PASS;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_PURPOSE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_SIMPLE_CHECK;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_PASSWORD_CHECK;

import android.app.Activity;
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
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import java.util.ArrayList;
import java.util.Map;

import irismod.nft.QueryOuterClass;
import osmosis.gamm.v1beta1.Tx;
import osmosis.lockup.Lock;
import sifnode.clp.v1.Querier;
import starnamed.x.starname.v1beta1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.dao.AssetPath;
import wannabit.io.cosmostaion.fragment.AlphabetKeyBoardFragment;
import wannabit.io.cosmostaion.fragment.KeyboardFragment;
import wannabit.io.cosmostaion.fragment.NumberKeyBoardFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.UserTask.CheckPasswordTask;
import wannabit.io.cosmostaion.utils.KeyboardListener;
import wannabit.io.cosmostaion.utils.OsmosisPeriodLockWrapper;
import wannabit.io.cosmostaion.utils.StarnameResourceWrapper;
import wannabit.io.cosmostaion.utils.WLog;
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

    private String mTargetAddress;
    private ArrayList<Coin> mTargetCoins;
    private String mTargetMemo;
    private Fee mTargetFee;
    private Coin mAmount;
    private Coin mUAmount;
    private String mFromReDelegateAddr;
    private String mToReDelegateAddr;
    private Coin mRAmount;
    private String mNewRewardAddress;

    private String mReInvestValAddr;
    private Coin mReInvestAmount;

    private Coin mOkStakeCoin;
    private ArrayList<String> mOKVoteValidator = new ArrayList<>();

    private Map<Integer, String> mSelectedOpinion;


    private String mDomain;
    private String mDomainType;
    private String mName;
    private ArrayList<Types.Resource> mResources = new ArrayList();


    private String mPoolId;
    private Coin mPoolCoin0;
    private Coin mPoolCoin1;
    private Coin mLpToken;
    private Tx.SwapAmountInRoute mSwapAmountInRoute;
    private Coin mSwapInCoin;
    private Coin mSwapOutCoin;
    private long mOsmosisLockupDuration;
    private ArrayList<Lock.PeriodLock> mOsmosisLockups = new ArrayList<>();

    private Coin mSifSwapInCoin;
    private Coin mSifSwapOutCoin;
    private Coin mSifDepositCoin0;
    private Coin mSifDepositCoin1;
    private Coin mSifWithdrawCoin;
    private Querier.LiquidityProviderRes mMyprovider;

    // NFT
    private String mNftDenomId;
    private String mNftDenomName;
    private String mNftName;
    private String mNftDescription;
    private String mNftHash;
    private String mNftTokenId;
    private QueryOuterClass.QueryNFTResponse mIrisResponse;

    // airdrop
    private String mDtag;
    private String mNickname;
    private String mBio;
    private String mProfileUri;
    private String mCoverUri;

    private Coin mKavaSwapin;
    private Coin mKavaSwapOut;
    private Coin mKavaPoolTokenA;
    private Coin mKavaPoolTokenB;
    private String mKavaShareAmount;
    private Coin mCollateral;
    private Coin mPrincipal;
    private String mCollateralType;
    private Coin mPayment;
    private ArrayList<Coin> mHardPoolCoins;
    private String mMultiplierName;

    private String mContractAddress;
    private AssetPath mAssetPath;

    private String mGranter;

    public ArrayList<String> mValOpAddresses_V1;

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

        mPurpose = getIntent().getIntExtra(CONST_PW_PURPOSE, CONST_PW_SIMPLE_CHECK);

        if (mPurpose != CONST_PW_SIMPLE_CHECK) {
            mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
            mBaseChain = getChain(mAccount.baseChain);
        }
        mTargetAddress = getIntent().getStringExtra("toAddress");
        mTargetCoins = getIntent().getParcelableArrayListExtra("amount");
        mTargetMemo = getIntent().getStringExtra("memo");
        mTargetFee = getIntent().getParcelableExtra("fee");
        mAmount = getIntent().getParcelableExtra("Amount");
        mUAmount = getIntent().getParcelableExtra("uAmount");
        mRAmount = getIntent().getParcelableExtra("rAmount");
        mFromReDelegateAddr = getIntent().getStringExtra("fromValidatorAddr");
        mToReDelegateAddr = getIntent().getStringExtra("toValidatorAddr");
        mNewRewardAddress = getIntent().getStringExtra("newRewardAddress");
        mReInvestValAddr = getIntent().getStringExtra("reInvestValAddr");
        mReInvestAmount = getIntent().getParcelableExtra("reInvestAmount");
        mOkStakeCoin = getIntent().getParcelableExtra("stakeAmount");
        mOKVoteValidator = getIntent().getStringArrayListExtra("voteVal");
        mSelectedOpinion = (Map<Integer, String>) getIntent().getSerializableExtra("selectedProposals");

        mMultiplierName = getIntent().getStringExtra("multiplierName");

        mDomain = getIntent().getStringExtra("domain");
        mDomainType = getIntent().getStringExtra("domainType");
        mName = getIntent().getStringExtra("name");
        StarnameResourceWrapper wrapper = (StarnameResourceWrapper) getIntent().getSerializableExtra("resource");
        if (wrapper != null) {
            mResources = wrapper.array;
        }

        mPoolId = String.valueOf(getIntent().getLongExtra("mPoolId", 0));
        mPoolCoin0 = getIntent().getParcelableExtra("mPoolCoin0");
        mPoolCoin1 = getIntent().getParcelableExtra("mPoolCoin1");
        mLpToken = getIntent().getParcelableExtra("mLpToken");
        mSwapInCoin = getIntent().getParcelableExtra("SwapInputCoin");
        mSwapOutCoin = getIntent().getParcelableExtra("SwapOutputcoin");
        mOsmosisLockupDuration = getIntent().getLongExtra("mOsmoDuraion", 0);
        OsmosisPeriodLockWrapper lockupsWrapper = (OsmosisPeriodLockWrapper) getIntent().getSerializableExtra("osmosislockups");
        if (lockupsWrapper != null) {
            mOsmosisLockups = lockupsWrapper.array;
        }

        mSifSwapInCoin = getIntent().getParcelableExtra("SifSwapInCoin");
        mSifSwapOutCoin = getIntent().getParcelableExtra("SifSwapOutCoin");
        mSifDepositCoin0 = getIntent().getParcelableExtra("SifDepositCoin0");
        mSifDepositCoin1 = getIntent().getParcelableExtra("SifDepositCoin1");
        mSifWithdrawCoin = getIntent().getParcelableExtra("SifWithdrawCoin");
        mMyprovider = (Querier.LiquidityProviderRes) getIntent().getSerializableExtra("MyProvider");

        mNftDenomId = getIntent().getStringExtra("nftDenomId");
        mNftDenomName = getIntent().getStringExtra("nftDenomName");
        mNftName = getIntent().getStringExtra("nftName");
        mNftDescription = getIntent().getStringExtra("nftDescription");
        mNftHash = getIntent().getStringExtra("nftHash");
        mNftTokenId = getIntent().getStringExtra("nftTokenId");
        mIrisResponse = (QueryOuterClass.QueryNFTResponse) getIntent().getSerializableExtra("irisResponse");

        mDtag = getIntent().getStringExtra("mDtag");
        mNickname = getIntent().getStringExtra("mNickname");
        mBio = getIntent().getStringExtra("mBio");
        mProfileUri = getIntent().getStringExtra("mProfileImg");
        mCoverUri = getIntent().getStringExtra("mCoverImg");

        mKavaSwapin = getIntent().getParcelableExtra("kavaSwapIn");
        mKavaSwapOut = getIntent().getParcelableExtra("kavaSwapOut");
        mKavaPoolTokenA = getIntent().getParcelableExtra("mKavaPoolTokenA");
        mKavaPoolTokenB = getIntent().getParcelableExtra("mKavaPoolTokenB");
        mKavaShareAmount = getIntent().getStringExtra("mKavaShare");
        mKavaPoolTokenA = getIntent().getParcelableExtra("mKavaPoolTokenA");
        mKavaPoolTokenB = getIntent().getParcelableExtra("mKavaPoolTokenB");
        mCollateral = getIntent().getParcelableExtra("mCollateral");
        mPrincipal = getIntent().getParcelableExtra("mPrincipal");
        mCollateralType = getIntent().getStringExtra("mCollateralType");
        mPayment = getIntent().getParcelableExtra("mPayment");
        mHardPoolCoins = getIntent().getParcelableArrayListExtra("hardPoolCoins");
        mMultiplierName = getIntent().getStringExtra("multiplierName");

        mContractAddress = getIntent().getStringExtra("contractAddress");
        mAssetPath = getIntent().getParcelableExtra("assetPath");

        if (getIntent().getByteArrayExtra("osmosisSwapRoute") != null) {
            try {
                mSwapAmountInRoute = Tx.SwapAmountInRoute.parseFrom(getIntent().getByteArrayExtra("osmosisSwapRoute"));
            } catch (Exception e) {
                WLog.w("Passing bundle Error");
            }
        }

        mGranter = getIntent().getStringExtra("granter");

        mValOpAddresses_V1 = getIntent().getStringArrayListExtra("valOpAddresses");

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

//    private void onFinishInput() {
//        if (mPurpose == CONST_PW_SIMPLE_CHECK) {
//            onShowWaitDialog();
//            new CheckPasswordTask(getBaseApplication(), this).execute(mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_SIMPLE_SEND) {
//            onShowWaitDialog();
//            if (isGRPC(mBaseChain)) {
//                new SendGrpcTask(getBaseApplication(), this, mBaseChain, mAccount, mTargetAddress, mTargetCoins, mTargetMemo, mTargetFee,
//                        getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//            } else if (mBaseChain.equals(BNB_MAIN)) {
//                new SimpleBnbSendTask(getBaseApplication(), this, mAccount, mTargetAddress, mTargetCoins, mTargetMemo, mTargetFee)
//                        .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//            } else {
//                new SimpleSendTask(getBaseApplication(), this, mAccount, mTargetAddress, mTargetCoins, mTargetMemo, mTargetFee)
//                        .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//            }
//
//        } else if (mPurpose == CONST_PW_TX_SIMPLE_DELEGATE) {
//            onShowWaitDialog();
//            new DelegateGrpcTask(getBaseApplication(), this, mBaseChain, mAccount, mTargetAddress, mAmount, mTargetMemo, mTargetFee,
//                    getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_SIMPLE_UNDELEGATE) {
//            onShowWaitDialog();
//            new UndelegateGrpcTask(getBaseApplication(), this, mBaseChain, mAccount, mTargetAddress, mUAmount, mTargetMemo, mTargetFee,
//                    getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_SIMPLE_REWARD) {
//            onShowWaitDialog();
//            new ClaimRewardsGrpcTask(getBaseApplication(), this, mBaseChain, mAccount, mValOpAddresses_V1, mTargetMemo, mTargetFee,
//                    getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_SIMPLE_REDELEGATE) {
//            onShowWaitDialog();
//            new RedelegateGrpcTask(getBaseApplication(), this, mBaseChain, mAccount, mFromReDelegateAddr, mToReDelegateAddr, mRAmount, mTargetMemo, mTargetFee,
//                    getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS) {
//            onShowWaitDialog();
//            new ChangeRewardAddressGrpcTask(getBaseApplication(), this, mBaseChain, mAccount, mNewRewardAddress, mTargetMemo, mTargetFee,
//                    getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_REINVEST) {
//            onShowWaitDialog();
//            new ReInvestGrpcTask(getBaseApplication(), this, mBaseChain, mAccount, mReInvestValAddr, mReInvestAmount, mTargetMemo, mTargetFee,
//                    getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_VOTE) {
//            onShowWaitDialog();
//            new VoteGrpcTask(getBaseApplication(), this, mBaseChain, mAccount, mSelectedOpinion, mTargetMemo, mTargetFee,
//                    getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_OK_DEPOSIT) {
//            new SimpleOkDepositTask(getBaseApplication(), this, mAccount, mOkStakeCoin, mTargetMemo, mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_OK_WITHDRAW) {
//            new SimpleOkWithdrawTask(getBaseApplication(), this, mAccount, mOkStakeCoin, mTargetMemo, mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_OK_DIRECT_VOTE) {
//            new SimpleOkDirectVoteTask(getBaseApplication(), this, mAccount, mOKVoteValidator, mTargetMemo, mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_REGISTER_DOMAIN) {
//            new RegisterDomainGrpcTask(getBaseApplication(), this, mAccount, mBaseChain,
//                    mDomain, mDomainType, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_REGISTER_ACCOUNT) {
//            new RegisterAccountGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mDomain,
//                    mName, mResources, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_DELETE_DOMAIN) {
//            new DeleteDomainGrpcTask(getBaseApplication(), this, mAccount, mBaseChain,
//                    mDomain, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_DELETE_ACCOUNT) {
//            new DeleteAccountGrpcTask(getBaseApplication(), this, mAccount, mBaseChain,
//                    mDomain, mName, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_RENEW_DOMAIN) {
//            new RenewDomainGrpcTask(getBaseApplication(), this, mAccount, mBaseChain,
//                    mDomain, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_RENEW_ACCOUNT) {
//            new RenewAccountGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mDomain,
//                    mName, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_REPLACE_STARNAME) {
//            new ReplaceStarNameGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mDomain,
//                    mName, mResources, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_OSMOSIS_SWAP) {
//            new OsmosisSwapInTask(getBaseApplication(), this, mAccount, mBaseChain, mSwapAmountInRoute, mSwapInCoin, mSwapOutCoin,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_OSMOSIS_JOIN_POOL) {
//            new OsmosisJoinPoolGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, Long.parseLong(mPoolId), mPoolCoin0, mPoolCoin1, mLpToken.amount,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_OSMOSIS_EXIT_POOL) {
//            new OsmosisExitPooGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, Long.parseLong(mPoolId), mPoolCoin0, mPoolCoin1, mLpToken.amount,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_OSMOSIS_EARNING) {
//            new OsmosisStartLockGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mOsmosisLockupDuration, mLpToken,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_OSMOSIS_BEGIN_UNBONDING) {
//            ArrayList<Long> tempList = new ArrayList<>();
//            for (Lock.PeriodLock lockup : mOsmosisLockups) {
//                tempList.add(lockup.getID());
//            }
//            new OsmosisBeginUnbondingGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, tempList,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_IBC_TRANSFER) {
//            new IBCTransferGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mTargetAddress, mTargetCoins.get(0).denom, mTargetCoins.get(0).amount,
//                    mAssetPath, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_SIF_SWAP) {
//            new SifSwapGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address,
//                    mSifSwapInCoin, mSifSwapOutCoin, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_SIF_JOIN_POOL) {
//            new SifDepositGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mSifDepositCoin1.denom, mSifDepositCoin0.amount, mSifDepositCoin1.amount,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_SIF_EXIT_POOL) {
//            BigDecimal myShareAllAmount = new BigDecimal(mMyprovider.getLiquidityProvider().getLiquidityProviderUnits());
//            BigDecimal myShareWithdrawAmount = new BigDecimal(mSifWithdrawCoin.amount);
//            String basisPoint = myShareWithdrawAmount.movePointRight(4).divide(myShareAllAmount, 0, RoundingMode.DOWN).toPlainString();
//            new SifWithdrawGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mSifWithdrawCoin.denom, basisPoint,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_MINT_NFT) {
//            StationNFTData nftData = new StationNFTData(mAccount.address, mNftName, mNftDescription, mNftDenomId, NFT_INFURA + mNftHash);
//            Gson gson = new Gson();
//            String jsonData = gson.toJson(nftData);
//            new MintNFTGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address,
//                    mNftDenomId, mNftDenomName, mNftHash.toLowerCase(), mNftName, NFT_INFURA + mNftHash, jsonData,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_SEND_NFT) {
//            new TransferNFTGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address,
//                    mTargetAddress, mNftDenomId, mNftTokenId, mIrisResponse, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_PROFILE) {
//            String profileUri = "";
//            String coverUri = "";
//            if (mProfileUri != null) {
//                profileUri = "https://ipfs.infura.io/ipfs/" + mProfileUri;
//            }
//            if (mCoverUri != null) {
//                coverUri = "https://ipfs.infura.io/ipfs/" + mCoverUri;
//            }
//            new CreateProfileGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mDtag, mNickname, mBio, profileUri, coverUri,
//                    mAccount.address, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_KAVA_SWAP) {
//            new KavaSwapGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mKavaSwapin, mKavaSwapOut,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_KAVA_JOIN_POOL) {
//            new KavaDepositGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mKavaPoolTokenA, mKavaPoolTokenB,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_KAVA_EXIT_POOL) {
//            new KavaWithdrawGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mKavaShareAmount, mKavaPoolTokenA, mKavaPoolTokenB,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_CREATE_CDP) {
//            new KavaCreateCdpGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mCollateral, mPrincipal, mCollateralType,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_DEPOSIT_CDP) {
//            new KavaDepositCdpGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mAccount.address, mCollateral, mCollateralType,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_WITHDRAW_CDP) {
//            new KavaWithdrawCdpGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mAccount.address, mCollateral, mCollateralType,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_DRAW_DEBT_CDP) {
//            new KavaDrawDebtCdpGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mPrincipal, mCollateralType,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_REPAY_CDP) {
//            new KavaRepayCdpGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mPayment, mCollateralType,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_DEPOSIT_HARD) {
//            new KavaDepositHardGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mHardPoolCoins,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_WITHDRAW_HARD) {
//            new KavaWithdrawHardGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mHardPoolCoins,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_BORROW_HARD) {
//            new KavaBorrowHardGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mHardPoolCoins,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_REPAY_HARD) {
//            new KavaRepayHardGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mAccount.address, mHardPoolCoins,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_CLAIM_INCENTIVE) {
//            new KavaClaimIncentiveAllGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mMultiplierName, getBaseDao(),
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_EXECUTE_CONTRACT) {
//            new Cw20SendGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mTargetAddress, mContractAddress, mTargetCoins,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_IBC_CONTRACT) {
//            new Cw20IBCSendGrpcTask(getBaseApplication(), this, mAccount, mBaseChain, mAccount.address, mTargetAddress, mContractAddress, mAssetPath, mTargetCoins,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_AUTHZ_DELEGATE) {
//            new AuthzDelegateGrpcTask(getBaseApplication(), this, mBaseChain, mAccount, mGranter, mTargetAddress, mAmount,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_AUTHZ_UNDELEGATE) {
//            new AuthzUndelegateGrpcTask(getBaseApplication(), this, mBaseChain, mAccount, mGranter, mTargetAddress, mUAmount,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_AUTHZ_REDELEGATE) {
//            new AuthzRedelegateGrpcTask(getBaseApplication(), this, mBaseChain, mAccount, mGranter, mFromReDelegateAddr, mToReDelegateAddr, mRAmount,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if ((mPurpose == CONST_PW_TX_AUTHZ_SEND)) {
//            new AuthzSendGrpcTask(getBaseApplication(), this, mBaseChain, mAccount, mGranter, mTargetAddress, mAmount,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if (mPurpose == CONST_PW_TX_AUTHZ_VOTE) {
//            new AuthzVoteGrpcTask(getBaseApplication(), this, mBaseChain, mAccount, mGranter, mSelectedOpinion,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if ((mPurpose == CONST_PW_TX_AUTHZ_CLAIM_REWARD)) {
//            new AuthzClaimRewardGrpcTask(getBaseApplication(), this, mBaseChain, mAccount, mGranter, mValOpAddresses_V1,
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//
//        } else if ((mPurpose == CONST_PW_TX_AUTHZ_CLAIM_COMMISSION)) {
//            new AuthzClaimCommissionGrpcTask(getBaseApplication(), this, mBaseChain, mAccount, WKey.convertDpAddressToDpOpAddress(mGranter, ChainFactory.getChain(mBaseChain)),
//                    mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mUserInput);
//        }
//    }

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
        if (result.taskType == TASK_PASSWORD_CHECK) {
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

        } else {
//            if (!result.isSuccess && result.errorCode == ERROR_CODE_INVALID_PASSWORD) {
//                onShakeView();
//                return;
//            }
//
//            if (isGRPC(mBaseChain)) {
//                Intent txIntent = new Intent(PasswordCheckActivity.this, TxDetailgRPCActivity.class);
//                txIntent.putExtra("isGen", true);
//                txIntent.putExtra("isSuccess", result.isSuccess);
//                txIntent.putExtra("errorCode", result.errorCode);
//                txIntent.putExtra("errorMsg", result.errorMsg);
//                String hash = String.valueOf(result.resultData);
//                if (!TextUtils.isEmpty(hash))
//                    txIntent.putExtra("txHash", hash);
//                startActivity(txIntent);
//
//            } else {
//                Intent txIntent = new Intent(PasswordCheckActivity.this, TxDetailActivity.class);
//                txIntent.putExtra("isGen", true);
//                txIntent.putExtra("isSuccess", result.isSuccess);
//                txIntent.putExtra("errorCode", result.errorCode);
//                txIntent.putExtra("errorMsg", result.errorMsg);
//                String hash = String.valueOf(result.resultData);
//                if (!TextUtils.isEmpty(hash))
//                    txIntent.putExtra("txHash", hash);
//                startActivity(txIntent);
//            }

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
