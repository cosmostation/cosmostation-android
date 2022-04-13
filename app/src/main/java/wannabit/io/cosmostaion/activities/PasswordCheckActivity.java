package wannabit.io.cosmostaion.activities;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_CHECK_MNEMONIC;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_CHECK_PRIVATE_KEY;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_PURPOSE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_SIMPLE_CHECK;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_BORROW_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_CLAIM_HARVEST_REWARD;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_CLAIM_INCENTIVE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_CREATE_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_DELETE_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_DELETE_DOMAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_DEPOSIT_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_DEPOSIT_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_DRAW_DEBT_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_EXECUTE_CONTRACT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_GDEX_DEPOSIT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_GDEX_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_GDEX_WITHDRAW;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_HTLS_REFUND;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_IBC_TRANSFER;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_KAVA_EXIT_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_KAVA_JOIN_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_KAVA_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_LINK_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_MINT_NFT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OK_DEPOSIT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OK_DIRECT_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OK_WITHDRAW;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OSMOSIS_BEGIN_UNBONDING;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OSMOSIS_EARNING;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OSMOSIS_EXIT_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OSMOSIS_JOIN_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_OSMOSIS_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_PROFILE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REGISTER_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REGISTER_DOMAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REINVEST;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_RENEW_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_RENEW_DOMAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REPAY_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REPAY_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REPLACE_STARNAME;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_RIZON_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SEND_NFT;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIF_CLAIM_INCENTIVE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIF_EXIT_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIF_JOIN_POOL;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIF_SWAP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_DELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_REDELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_REWARD;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_UNDELEGATE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_VOTE;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_WITHDRAW_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_WITHDRAW_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_INVALID_PASSWORD;
import static wannabit.io.cosmostaion.base.BaseConstant.NFT_INFURA;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_TX_BNB_HTLC_REFUND;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.fulldive.wallet.interactors.accounts.AccountsInteractor;
import com.fulldive.wallet.interactors.secret.InvalidPasswordException;
import com.fulldive.wallet.interactors.secret.SecretInteractor;
import com.fulldive.wallet.presentation.system.keyboard.KeyboardListener;
import com.fulldive.wallet.presentation.system.keyboard.KeyboardPagerAdapter;
import com.fulldive.wallet.rx.AppSchedulers;
import com.google.gson.Gson;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.math.RoundingMode;
import java.util.ArrayList;

import io.reactivex.disposables.Disposable;
import irismod.nft.QueryOuterClass;
import osmosis.gamm.v1beta1.Tx;
import osmosis.lockup.Lock;
import sifnode.clp.v1.Querier;
import starnamed.x.starname.v1beta1.Types;
import tendermint.liquidity.v1beta1.Liquidity;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.ITimelessActivity;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.StationNFTData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.HdacBurnTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleBnbHtlcRefundTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleBnbSendTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleClaimHarvestRewardTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleHtlcRefundTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleOkDepositTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleOkDirectVoteTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleOkWithdrawTask;
import wannabit.io.cosmostaion.task.SimpleBroadTxTask.SimpleSendTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.ChangeRewardAddressGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.ClaimRewardsGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.CreateProfileGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.Cw20SendGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.DelegateGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.DeleteAccountGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.DeleteDomainGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.GravityDepositGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.GravitySwapGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.GravityWithdrawGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.IBCTransferGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.KavaBorrowHardGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.KavaClaimIncentiveAllGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.KavaCreateCdpGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.KavaDepositCdpGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.KavaDepositGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.KavaDepositHardGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.KavaDrawDebtCdpGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.KavaRepayCdpGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.KavaRepayHardGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.KavaSwapGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.KavaWithdrawCdpGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.KavaWithdrawGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.KavaWithdrawHardGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.LinkAccountGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.MintNFTGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.OsmosisBeginUnbondingGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.OsmosisExitPooGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.OsmosisJoinPoolGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.OsmosisStartLockGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.OsmosisSwapInTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.ReInvestGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.RedelegateGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.RegisterAccountGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.RegisterDomainGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.RenewAccountGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.RenewDomainGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.ReplaceStarNameGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.SendGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.SifDepositGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.SifIncentiveGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.SifSwapGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.SifWithdrawGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.TransferNFTGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.UndelegateGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.broadcast.VoteGrpcTask;
import wannabit.io.cosmostaion.utils.OsmosisPeriodLockWrapper;
import wannabit.io.cosmostaion.utils.StarnameResourceWrapper;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.LockedViewPager;

public class PasswordCheckActivity extends BaseActivity implements ITimelessActivity, KeyboardListener, TaskListener {

    private LinearLayout mLayerContents;
    private TextView mPassowrdTitle, mPassowrdMsg1, mPassowrdMsg2;
    private final ImageView[] circleImageView = new ImageView[5];

    private LockedViewPager mViewPager;
    private KeyboardPagerAdapter adapter;

    private String userInput = "";
    private int mPurpose;
    private boolean mAskQuite;

    private String mTargetAddress;
    private ArrayList<Coin> mTargetCoins;
    private String mTargetMemo;
    private Fee mTargetFee;
    private Coin mDAmount;
    private Coin mUAmount;
    private String mFromReDelegateAddr;
    private String mToReDelegateAddr;
    private Coin mRAmount;
    private String mNewRewardAddress;

    private String mReInvestValAddr;
    private Coin mReInvestAmount;

    private String mProposalId;
    private String mOpinion;

    private String mSwapId;

    private Coin mOkStakeCoin;
    private ArrayList<String> mOKVoteValidator = new ArrayList<>();


    private String mDomain;
    private String mDomainType;
    private String mName;
    private ArrayList<Types.Resource> mResources = new ArrayList<>();


    private String mPoolId;
    private Coin mPoolCoin0;
    private Coin mPoolCoin1;
    private Coin mLpToken;
    private Tx.SwapAmountInRoute mSwapAmountInRoute;
    private Coin mSwapInCoin;
    private Coin mSwapOutCoin;
    private long mOsmosisLockupDuration;
    private ArrayList<Lock.PeriodLock> mOsmosisLockups = new ArrayList<>();

    private Liquidity.Pool mGDexPool;
    public String mGDexSwapOrderPrice;

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
    private String mDesmosToLinkChain;
    private Long mDesmosToLinkAccountId;

    private Coin mKavaSwapin;
    private Coin mKavaSwapOut;
    private Coin mKavaPoolTokenA;
    private Coin mKavaPoolTokenB;
    private String mKavaShareAmount;
    private Coin mKavaMinTokenA;
    private Coin mKavaMinTokenB;
    private Coin mCollateral;
    private Coin mPrincipal;
    private String mCollateralType;
    private Coin mPayment;
    private ArrayList<Coin> mHardPoolCoins;
    private String mMultiplierName;

    private String mContractAddress;

    private String mPortId;
    private String mChannelId;


    private String mHdacBurnRawTx;                 //for hdac burn & swap

    private long mIdToCheck;


    public ArrayList<String> mValOpAddresses_V1;

    private AccountsInteractor accountsInteractor;
    private SecretInteractor secretInteractor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_SECURE, WindowManager.LayoutParams.FLAG_SECURE);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_password_set);

        accountsInteractor = getAppInjector().getInstance(AccountsInteractor.class);
        secretInteractor = getAppInjector().getInstance(SecretInteractor.class);

        mLayerContents = findViewById(R.id.layerContents);
        mPassowrdTitle = findViewById(R.id.titleTextView);
        mPassowrdMsg1 = findViewById(R.id.subtitleTextView);
        mPassowrdMsg2 = findViewById(R.id.hintTextView);
        mViewPager = findViewById(R.id.keyboardPager);
        mPassowrdMsg2.setVisibility(View.INVISIBLE);

        for (int i = 0; i < circleImageView.length; i++) {
            circleImageView[i] = findViewById(getResources().getIdentifier("circleImage" + i, "id", getPackageName()));
        }

        mViewPager.setOffscreenPageLimit(2);
        adapter = new KeyboardPagerAdapter(getSupportFragmentManager(), this);
        mViewPager.setAdapter(adapter);

        mPurpose = getIntent().getIntExtra(CONST_PW_PURPOSE, CONST_PW_SIMPLE_CHECK);

        if (mPurpose != CONST_PW_SIMPLE_CHECK) {
            account = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
            baseChain = getChain(account.baseChain);
        }
        mTargetAddress = getIntent().getStringExtra("toAddress");
        mTargetCoins = getIntent().getParcelableArrayListExtra("amount");
        mTargetMemo = getIntent().getStringExtra("memo");
        mTargetFee = getIntent().getParcelableExtra("fee");
        mDAmount = getIntent().getParcelableExtra("dAmount");
        mUAmount = getIntent().getParcelableExtra("uAmount");
        mRAmount = getIntent().getParcelableExtra("rAmount");
        mFromReDelegateAddr = getIntent().getStringExtra("fromValidatorAddr");
        mToReDelegateAddr = getIntent().getStringExtra("toValidatorAddr");
        mNewRewardAddress = getIntent().getStringExtra("newRewardAddress");
        mReInvestValAddr = getIntent().getStringExtra("reInvestValAddr");
        mReInvestAmount = getIntent().getParcelableExtra("reInvestAmount");
        mProposalId = getIntent().getStringExtra("proposal_id");
        mOpinion = getIntent().getStringExtra("opinion");
        mSwapId = getIntent().getStringExtra("swapId");
        mOkStakeCoin = getIntent().getParcelableExtra("stakeAmount");
        mOKVoteValidator = getIntent().getStringArrayListExtra("voteVal");

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

        mGDexPool = (Liquidity.Pool) getIntent().getSerializableExtra("gDexPool");
        mGDexSwapOrderPrice = getIntent().getStringExtra("gDexSwapOrderPrice");

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
        mDesmosToLinkChain = getIntent().getStringExtra("mDesmosToLinkChain");
        mDesmosToLinkAccountId = getIntent().getLongExtra("mDesmosToLinkAccountId", -1);

        mKavaSwapin = getIntent().getParcelableExtra("kavaSwapIn");
        mKavaSwapOut = getIntent().getParcelableExtra("kavaSwapOut");
        mKavaPoolTokenA = getIntent().getParcelableExtra("mKavaPoolTokenA");
        mKavaPoolTokenB = getIntent().getParcelableExtra("mKavaPoolTokenB");
        mKavaShareAmount = getIntent().getStringExtra("mKavaShare");
        mKavaMinTokenA = getIntent().getParcelableExtra("mKavaMinTokenA");
        mKavaMinTokenB = getIntent().getParcelableExtra("mKavaMinTokenB");
        mCollateral = getIntent().getParcelableExtra("mCollateral");
        mPrincipal = getIntent().getParcelableExtra("mPrincipal");
        mCollateralType = getIntent().getStringExtra("mCollateralType");
        mPayment = getIntent().getParcelableExtra("mPayment");
        mHardPoolCoins = getIntent().getParcelableArrayListExtra("hardPoolCoins");
        mMultiplierName = getIntent().getStringExtra("multiplierName");

        mContractAddress = getIntent().getStringExtra("contractAddress");

        mPortId = getIntent().getStringExtra("portId");
        mChannelId = getIntent().getStringExtra("channelId");

        if (getIntent().getByteArrayExtra("osmosisSwapRoute") != null) {
            try {
                mSwapAmountInRoute = Tx.SwapAmountInRoute.parseFrom(getIntent().getByteArrayExtra("osmosisSwapRoute"));
            } catch (Exception e) {
                WLog.w("Passing bundle Error");
            }
        }

        mHdacBurnRawTx = getIntent().getStringExtra("hdacBurnRawTx");


        mIdToCheck = getIntent().getLongExtra("checkid", -1);

        mValOpAddresses_V1 = getIntent().getStringArrayListExtra("valOpAddresses");

        onInitView();
    }

    @Override
    public void onBackPressed() {
        if (userInput != null && userInput.length() > 0) {
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
        mPassowrdTitle.setText(R.string.str_init_password);
        mPassowrdMsg1.setText(R.string.str_init_password);
        userInput = "";

        for (ImageView imageView : circleImageView) {
            imageView.setBackgroundResource(R.drawable.ic_pass_gr);
        }
        mViewPager.setCurrentItem(0, true);
    }

    @Override
    public void userInsertKey(char input) {
        if (userInput == null || userInput.length() == 0) {
            userInput = String.valueOf(input);

        } else if (userInput.length() < 5) {
            userInput = userInput + input;
        }

        if (userInput.length() == 4) {
            mViewPager.setCurrentItem(1, true);

        } else if (userInput.length() == 5 && WUtil.checkPasscodePattern(userInput)) {
            onFinishInput();

        } else if (userInput.length() == 5 && !WUtil.checkPasscodePattern(userInput)) {
            onInitView();
            return;
        }

        mAskQuite = false;
        onUpdateCnt();

    }

    @Override
    public void userDeleteKey() {
        if (userInput == null || userInput.length() <= 0) {
            onBackPressed();
        } else if (userInput.length() == 4) {
            userInput = userInput.substring(0, userInput.length() - 1);
            mViewPager.setCurrentItem(0, true);
        } else {
            userInput = userInput.substring(0, userInput.length() - 1);
        }
        onUpdateCnt();
    }


    private void onFinishInput() {
        switch (mPurpose) {
            case CONST_PW_SIMPLE_CHECK:
                actionCheckPassword();

                break;
            case CONST_PW_CHECK_MNEMONIC:
                fetchEntropy(MnemonicCheckActivity.class);

                break;
            case CONST_PW_CHECK_PRIVATE_KEY:
                fetchEntropy(PrivateKeyCheckActivity.class);

                break;
            case CONST_PW_TX_SIMPLE_SEND:
                showWaitDialog();
                if (baseChain.isGRPC()) {
                    new SendGrpcTask(getBaseApplication(), this, baseChain, account, mTargetAddress, mTargetCoins, mTargetMemo, mTargetFee,
                            getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                } else if (baseChain.equals(BNB_MAIN)) {
                    new SimpleBnbSendTask(getBaseApplication(), this, account, mTargetAddress, mTargetCoins, mTargetMemo, mTargetFee)
                            .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                } else {
                    new SimpleSendTask(getBaseApplication(), this, account, mTargetAddress, mTargetCoins, mTargetMemo, mTargetFee)
                            .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);
                }

                break;
            case CONST_PW_TX_SIMPLE_DELEGATE:
                showWaitDialog();
                new DelegateGrpcTask(getBaseApplication(), this, baseChain, account, mTargetAddress, mDAmount, mTargetMemo, mTargetFee,
                        getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_SIMPLE_UNDELEGATE:
                showWaitDialog();
                new UndelegateGrpcTask(getBaseApplication(), this, baseChain, account, mTargetAddress, mUAmount, mTargetMemo, mTargetFee,
                        getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_SIMPLE_REWARD:
                showWaitDialog();
                new ClaimRewardsGrpcTask(getBaseApplication(), this, baseChain, account, mValOpAddresses_V1, mTargetMemo, mTargetFee,
                        getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_SIMPLE_REDELEGATE:
                showWaitDialog();
                new RedelegateGrpcTask(getBaseApplication(), this, baseChain, account, mFromReDelegateAddr, mToReDelegateAddr, mRAmount, mTargetMemo, mTargetFee,
                        getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_SIMPLE_CHANGE_REWARD_ADDRESS:
                showWaitDialog();
                new ChangeRewardAddressGrpcTask(getBaseApplication(), this, baseChain, account, mNewRewardAddress, mTargetMemo, mTargetFee,
                        getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_REINVEST:
                showWaitDialog();
                new ReInvestGrpcTask(getBaseApplication(), this, baseChain, account, mReInvestValAddr, mReInvestAmount, mTargetMemo, mTargetFee,
                        getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_VOTE:
                showWaitDialog();
                new VoteGrpcTask(getBaseApplication(), this, baseChain, account, mProposalId, mOpinion, mTargetMemo, mTargetFee,
                        getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_HTLS_REFUND:
                showWaitDialog();
                if (baseChain.equals(BNB_MAIN)) {
                    new SimpleBnbHtlcRefundTask(getBaseApplication(), this, account,
                            mSwapId, mTargetMemo).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                } else if (baseChain.equals(KAVA_MAIN)) {
                    new SimpleHtlcRefundTask(getBaseApplication(), this, account, mSwapId,
                            mTargetMemo, mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);
                }

                break;
            case CONST_PW_TX_OK_DEPOSIT:
                new SimpleOkDepositTask(getBaseApplication(), this, account, mOkStakeCoin, mTargetMemo, mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_OK_WITHDRAW:
                new SimpleOkWithdrawTask(getBaseApplication(), this, account, mOkStakeCoin, mTargetMemo, mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_OK_DIRECT_VOTE:
                new SimpleOkDirectVoteTask(getBaseApplication(), this, account, mOKVoteValidator, mTargetMemo, mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_REGISTER_DOMAIN:
                new RegisterDomainGrpcTask(getBaseApplication(), this, account, baseChain,
                        mDomain, mDomainType, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_REGISTER_ACCOUNT:
                new RegisterAccountGrpcTask(getBaseApplication(), this, account, baseChain, mDomain,
                        mName, mResources, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_DELETE_DOMAIN:
                new DeleteDomainGrpcTask(getBaseApplication(), this, account, baseChain,
                        mDomain, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_DELETE_ACCOUNT:
                new DeleteAccountGrpcTask(getBaseApplication(), this, account, baseChain,
                        mDomain, mName, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_RENEW_DOMAIN:
                new RenewDomainGrpcTask(getBaseApplication(), this, account, baseChain,
                        mDomain, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_RENEW_ACCOUNT:
                new RenewAccountGrpcTask(getBaseApplication(), this, account, baseChain, mDomain,
                        mName, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_REPLACE_STARNAME:
                new ReplaceStarNameGrpcTask(getBaseApplication(), this, account, baseChain, mDomain,
                        mName, mResources, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_CLAIM_HARVEST_REWARD:
                new SimpleClaimHarvestRewardTask(getBaseApplication(), this, account, mMultiplierName,
                        mTargetMemo, mTargetFee).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_OSMOSIS_SWAP:
                new OsmosisSwapInTask(getBaseApplication(), this, account, baseChain, mSwapAmountInRoute, mSwapInCoin, mSwapOutCoin,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_OSMOSIS_JOIN_POOL:
                new OsmosisJoinPoolGrpcTask(getBaseApplication(), this, account, baseChain, Long.parseLong(mPoolId), mPoolCoin0, mPoolCoin1, mLpToken.amount,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_OSMOSIS_EXIT_POOL:
                new OsmosisExitPooGrpcTask(getBaseApplication(), this, account, baseChain, Long.parseLong(mPoolId), mPoolCoin0, mPoolCoin1, mLpToken.amount,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_OSMOSIS_EARNING:
                new OsmosisStartLockGrpcTask(getBaseApplication(), this, account, baseChain, mOsmosisLockupDuration, mLpToken,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_OSMOSIS_BEGIN_UNBONDING:
                ArrayList<Long> tempList = new ArrayList<>();
                for (Lock.PeriodLock lockup : mOsmosisLockups) {
                    tempList.add(lockup.getID());
                }
                new OsmosisBeginUnbondingGrpcTask(getBaseApplication(), this, account, baseChain, tempList,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_GDEX_SWAP:
                BigDecimal offerFee = new BigDecimal(mSwapInCoin.amount).multiply(new BigDecimal("0.0015")).setScale(0, RoundingMode.CEILING);
                Coin coinFee = new Coin(mSwapInCoin.denom, offerFee.toPlainString());
                new GravitySwapGrpcTask(getBaseApplication(), this, account, baseChain,
                        mGDexPool.getId(), mSwapInCoin, mSwapOutCoin.denom, coinFee, mGDexSwapOrderPrice, mTargetMemo, mTargetFee,
                        getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_GDEX_DEPOSIT:
                new GravityDepositGrpcTask(getBaseApplication(), this, account, baseChain, Long.parseLong(mPoolId), mPoolCoin0, mPoolCoin1,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_GDEX_WITHDRAW:
                new GravityWithdrawGrpcTask(getBaseApplication(), this, account, baseChain, Long.parseLong(mPoolId), mLpToken,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_IBC_TRANSFER:
                new IBCTransferGrpcTask(getBaseApplication(), this, account, baseChain, account.address, mTargetAddress, mTargetCoins.get(0).denom, mTargetCoins.get(0).amount,
                        mPortId, mChannelId, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_SIF_CLAIM_INCENTIVE:
                new SifIncentiveGrpcTask(getBaseApplication(), this, account, baseChain, account.address,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_SIF_SWAP:
                new SifSwapGrpcTask(getBaseApplication(), this, account, baseChain, account.address,
                        mSifSwapInCoin.denom, mSifSwapInCoin.amount, mSifSwapOutCoin.denom, mSifSwapOutCoin.amount,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_SIF_JOIN_POOL:
                new SifDepositGrpcTask(getBaseApplication(), this, account, baseChain, account.address, mSifDepositCoin1.denom, mSifDepositCoin0.amount, mSifDepositCoin1.amount,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_SIF_EXIT_POOL:
                BigDecimal myShareAllAmount = new BigDecimal(mMyprovider.getLiquidityProvider().getLiquidityProviderUnits());
                BigDecimal myShareWithdrawAmount = new BigDecimal(mSifWithdrawCoin.amount);
                String basisPoint = myShareWithdrawAmount.movePointRight(4).divide(myShareAllAmount, 0, RoundingMode.DOWN).toPlainString();
                new SifWithdrawGrpcTask(getBaseApplication(), this, account, baseChain, account.address, mSifWithdrawCoin.denom, basisPoint,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_MINT_NFT:
                StationNFTData nftData = new StationNFTData(account.address, mNftName, mNftDescription, mNftDenomId, NFT_INFURA + mNftHash);
                Gson gson = new Gson();
                String jsonData = gson.toJson(nftData);
                new MintNFTGrpcTask(getBaseApplication(), this, account, baseChain, account.address,
                        mNftDenomId, mNftDenomName, mNftHash.toLowerCase(), mNftName, NFT_INFURA + mNftHash, jsonData,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_SEND_NFT:
                new TransferNFTGrpcTask(getBaseApplication(), this, account, baseChain, account.address,
                        mTargetAddress, mNftDenomId, mNftTokenId, mIrisResponse, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_PROFILE:
                String profileUri = "";
                String coverUri = "";
                if (mProfileUri != null) {
                    profileUri = "https://ipfs.infura.io/ipfs/" + mProfileUri;
                }
                if (mCoverUri != null) {
                    coverUri = "https://ipfs.infura.io/ipfs/" + mCoverUri;
                }
                new CreateProfileGrpcTask(getBaseApplication(), this, account, baseChain, mDtag, mNickname, mBio, profileUri, coverUri,
                        account.address, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_LINK_ACCOUNT:
                Account toAccount = getBaseDao().onSelectAccount(mDesmosToLinkAccountId.toString());
                ECKey ecKey;
                if (toAccount.fromMnemonic) {
                    String entropy = CryptoHelper.doDecryptData(getString(R.string.key_mnemonic) + toAccount.uuid, toAccount.resource, toAccount.spec);
                    DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(toAccount, entropy);
                    ecKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
                } else {
                    String privateKey = CryptoHelper.doDecryptData(getString(R.string.key_private) + toAccount.uuid, toAccount.resource, toAccount.spec);
                    ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
                }
                new LinkAccountGrpcTask(getBaseApplication(), this, account, baseChain, account.address, BaseChain.getChain(mDesmosToLinkChain),
                        toAccount, ecKey, mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_KAVA_SWAP:
                new KavaSwapGrpcTask(getBaseApplication(), this, account, baseChain, account.address, mKavaSwapin, mKavaSwapOut,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_KAVA_JOIN_POOL:
                new KavaDepositGrpcTask(getBaseApplication(), this, account, baseChain, account.address, mKavaPoolTokenA, mKavaPoolTokenB,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_KAVA_EXIT_POOL:
                new KavaWithdrawGrpcTask(getBaseApplication(), this, account, baseChain, account.address, mKavaShareAmount, mKavaMinTokenA, mKavaMinTokenB,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_CREATE_CDP:
                new KavaCreateCdpGrpcTask(getBaseApplication(), this, account, baseChain, account.address, mCollateral, mPrincipal, mCollateralType,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_DEPOSIT_CDP:
                new KavaDepositCdpGrpcTask(getBaseApplication(), this, account, baseChain, account.address, account.address, mCollateral, mCollateralType,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_WITHDRAW_CDP:
                new KavaWithdrawCdpGrpcTask(getBaseApplication(), this, account, baseChain, account.address, account.address, mCollateral, mCollateralType,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_DRAW_DEBT_CDP:
                new KavaDrawDebtCdpGrpcTask(getBaseApplication(), this, account, baseChain, account.address, mPrincipal, mCollateralType,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_REPAY_CDP:
                new KavaRepayCdpGrpcTask(getBaseApplication(), this, account, baseChain, account.address, mPayment, mCollateralType,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_DEPOSIT_HARD:
                new KavaDepositHardGrpcTask(getBaseApplication(), this, account, baseChain, account.address, mHardPoolCoins,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_WITHDRAW_HARD:
                new KavaWithdrawHardGrpcTask(getBaseApplication(), this, account, baseChain, account.address, mHardPoolCoins,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_BORROW_HARD:
                new KavaBorrowHardGrpcTask(getBaseApplication(), this, account, baseChain, account.address, mHardPoolCoins,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_REPAY_HARD:
                new KavaRepayHardGrpcTask(getBaseApplication(), this, account, baseChain, account.address, account.address, mHardPoolCoins,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_CLAIM_INCENTIVE:
                new KavaClaimIncentiveAllGrpcTask(getBaseApplication(), this, account, baseChain, account.address, mMultiplierName, getBaseDao(),
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);

                break;
            case CONST_PW_TX_EXECUTE_CONTRACT:
                new Cw20SendGrpcTask(getBaseApplication(), this, account, baseChain, account.address, mTargetAddress, mContractAddress, mTargetCoins,
                        mTargetMemo, mTargetFee, getBaseDao().getChainIdGrpc()).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);
                break;
            case CONST_PW_TX_RIZON_SWAP:
                new HdacBurnTask(getBaseApplication(), this, baseChain, mHdacBurnRawTx).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, userInput);
                break;
        }
    }

    private void actionCheckPassword() {
        showWaitDialog();
        Disposable disposable = secretInteractor
                .checkPassword(userInput)
                .subscribeOn(AppSchedulers.INSTANCE.io())
                .observeOn(AppSchedulers.INSTANCE.ui())
                .doOnError(error -> WLog.e(error.toString()))
                .subscribe(
                        () -> {
                            WLog.w("Account was checked");
                            setResult(Activity.RESULT_OK, getIntent());
                            finish();
                            overridePendingTransition(R.anim.fade_in, R.anim.slide_out_bottom);
                        },
                        error -> {
                            hideWaitDialog();
                            onShakeView();
                            onInitView();
                            if (error instanceof InvalidPasswordException) {
                                Toast.makeText(getBaseContext(), R.string.error_invalid_password, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getBaseContext(), R.string.str_unknown_error_msg, Toast.LENGTH_SHORT).show();
                            }
                        }
                );
        compositeDisposable.add(disposable);
    }

    private void fetchEntropy(Class<?> cls) {
        showWaitDialog();

        Disposable disposable = secretInteractor
                .checkPassword(userInput)
                .andThen(accountsInteractor.getAccount(mIdToCheck))
                .flatMap(account ->
                        secretInteractor.decryptEntropy(account.uuid, account.resource, account.spec)
                )
                .subscribeOn(AppSchedulers.INSTANCE.io())
                .observeOn(AppSchedulers.INSTANCE.ui())
                .doOnError(error -> WLog.e(error.toString()))
                .subscribe(
                        entropy -> {
                            Intent checkintent = new Intent(PasswordCheckActivity.this, cls);
                            checkintent.putExtra("checkid", mIdToCheck);
                            checkintent.putExtra("entropy", entropy);
                            startActivity(checkintent);
                        },
                        error -> {
                            hideWaitDialog();
                            onShakeView();
                            onInitView();
                            if (error instanceof InvalidPasswordException) {
                                Toast.makeText(getBaseContext(), R.string.error_invalid_password, Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(getBaseContext(), R.string.str_unknown_error_msg, Toast.LENGTH_SHORT).show();
                            }
                        }
                );
        compositeDisposable.add(disposable);
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


    private void onUpdateCnt() {
        if (userInput == null)
            userInput = "";

        final int inputLength = userInput.length();
        for (int i = 0; i < circleImageView.length; i++) {
            circleImageView[i].setBackgroundResource(
                    i < inputLength ? R.drawable.ic_pass_pu : R.drawable.ic_pass_gr
            );
        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        hideWaitDialog();

        if (!result.isSuccess && result.errorCode == ERROR_CODE_INVALID_PASSWORD) {
            onShakeView();
            return;
        }

        if ((baseChain.equals(BNB_MAIN)) && result.taskType == TASK_GEN_TX_BNB_HTLC_REFUND) {
            Intent txIntent = new Intent(PasswordCheckActivity.this, TxDetailActivity.class);
            txIntent.putExtra("isGen", true);
            txIntent.putExtra("isSuccess", result.isSuccess);
            txIntent.putExtra("errorCode", result.errorCode);
            txIntent.putExtra("errorMsg", result.errorMsg);
            String hash = String.valueOf(result.resultData);
            if (!TextUtils.isEmpty(hash))
                txIntent.putExtra("txHash", hash);
            startActivity(txIntent);

        } else if (baseChain.isGRPC()) {
            Intent txIntent = new Intent(PasswordCheckActivity.this, TxDetailgRPCActivity.class);
            txIntent.putExtra("isGen", true);
            txIntent.putExtra("isSuccess", result.isSuccess);
            txIntent.putExtra("errorCode", result.errorCode);
            txIntent.putExtra("errorMsg", result.errorMsg);
            String hash = String.valueOf(result.resultData);
            if (!TextUtils.isEmpty(hash))
                txIntent.putExtra("txHash", hash);
            startActivity(txIntent);

        } else {
            Intent txIntent = new Intent(PasswordCheckActivity.this, TxDetailActivity.class);
            txIntent.putExtra("isGen", true);
            txIntent.putExtra("isSuccess", result.isSuccess);
            txIntent.putExtra("errorCode", result.errorCode);
            txIntent.putExtra("errorMsg", result.errorMsg);
            String hash = String.valueOf(result.resultData);
            if (!TextUtils.isEmpty(hash))
                txIntent.putExtra("txHash", hash);
            startActivity(txIntent);
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}
