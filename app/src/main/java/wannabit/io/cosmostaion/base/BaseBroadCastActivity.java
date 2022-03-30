package wannabit.io.cosmostaion.base;

import java.math.BigDecimal;
import java.util.ArrayList;

import irismod.nft.QueryOuterClass;
import osmosis.gamm.poolmodels.balancer.BalancerPool;
import osmosis.gamm.v1beta1.Tx;
import osmosis.lockup.Lock;
import sifnode.clp.v1.Querier;
import starnamed.x.starname.v1beta1.Types;
import tendermint.liquidity.v1beta1.Liquidity;
import wannabit.io.cosmostaion.dao.IbcPath;
import wannabit.io.cosmostaion.model.GDexManager;
import wannabit.io.cosmostaion.model.hdac.HdacUtxo;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Validator;

public class BaseBroadCastActivity extends BaseActivity {

    public int mTxType = -1;
    public Fee mTxFee;
    public String mTxMemo;
    public String mDenom;                             //Transfer
    public String mToAddress;                         //Transfer
    public ArrayList<Coin> mAmounts;                           //Transfer
    public Coin mAmount;                            //Delegate, Undelegate, Redelegate, ReInvest
    public String mValAddress;                        //Delegate, Undelegate, ReInvest
    public String mToValAddress;                      //Redelegate
    public ArrayList<Coin> mRewards = new ArrayList<>();       //Reward
    public ArrayList<String> mValAddresses = new ArrayList<>();  //ClaimReward
    public String mNewRewardAddress;                  //SetRewardAddress
    public String mProposalId;                        //Vote
    public String mOpinion;                           //Vote

    public String mStarNameDomain;                            //starname domain
    public String mStarNameDomainType;                        //starname domain type
    public String mStarNameAccount;                           //starname
    public ArrayList<Types.Resource> mStarNameResources = new ArrayList();       //starname
    public boolean mIsDomain;


    public Tx.SwapAmountInRoute mOsmosisSwapAmountInRoute;                          // osmosis
    public long mOsmosisPoolId;
    public BalancerPool.Pool mOsmosisPool;
    public Coin mPoolCoin0;
    public Coin mPoolCoin1;
    public Coin mLpToken;
    public long mOsmosisLockupDuration;
    public ArrayList<Lock.PeriodLock> mOsmosisLockups = new ArrayList<>();
    public Coin mSwapInCoin;
    public Coin mSwapOutCoin;

    public long mGDexPoolId;                                // cosmos
    public Liquidity.Pool mGDexPool;
    public GDexManager mGDexManager;
    public String mGDexSwapOrderPrice;
    public Coin mGDexPoolCoinSupply;


    public String mIncentiveMultiplier;


    public sifnode.clp.v1.Types.Pool mSifPool;                                   // sif swap
    public Coin mSifSwapInCoin;
    public Coin mSifSwapOutCoin;
    public Coin mSifDepositCoin0;
    public Coin mSifDepositCoin1;
    public Coin mSifWithdrawCoin;
    public Querier.LiquidityProviderRes mMyProvider;

    public IbcPath.Path mPath;

    // NFT
    public String mNftDenomId;
    public String mNftDenomName;
    public String mNftTokenId;
    public String mNftName;
    public String mNftDescription;
    public String mNftHash;
    public QueryOuterClass.QueryNFTResponse mIrisResponse;

    // AirDrop
    public String mDtag;
    public String mNickname;
    public String mBio;
    public String mCoverImg;
    public String mProfileImg;
    public BaseChain mDesmosToLinkChain;
    public Long mDesmosToLinkAccountId;
    public BigDecimal mDesmosAirDropAmount = BigDecimal.ZERO;

    // Kava
    public kava.swap.v1beta1.QueryOuterClass.PoolResponse mKavaSwapPool;
    public kava.swap.v1beta1.QueryOuterClass.DepositResponse mKavaDepositPool;
    public Coin mKavaSwapIn;
    public Coin mKavaSwapOut;
    public Coin mKavaPoolTokenA;
    public Coin mKavaPoolTokenB;
    public BigDecimal mKavaShareAmount = BigDecimal.ZERO;
    public Coin mKavaMinTokenA;
    public Coin mKavaMinTokenB;
    public Coin mCollateral;
    public Coin mPrincipal;
    public String mCollateralType;
    public Coin mPayment;
    public ArrayList<Coin> mHardPoolCoins = new ArrayList<>();

    // contract
    public String mContractAddress;


    public ArrayList<HdacUtxo> mHdacUtxo;                                  //rizon swap
    public BigDecimal mHdacBalance;                               //rizon swap amount
    public ArrayList<String> mHdacWords;                                 //rizon swap Hdac Mnemonic words

    public ArrayList<Validator> mValidators = new ArrayList<>();    //ClaimReward old


    public void onNextStep() {
    }

    public void onBeforeStep() {
    }
}
