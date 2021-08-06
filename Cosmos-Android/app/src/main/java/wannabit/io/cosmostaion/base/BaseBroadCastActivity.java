package wannabit.io.cosmostaion.base;

import java.util.ArrayList;

import osmosis.gamm.v1beta1.Tx;
import starnamed.x.starname.v1beta1.Types;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Validator;

public class BaseBroadCastActivity extends BaseActivity {

    public int                  mTxType = -1;
    public Fee                  mTxFee;
    public String               mTxMemo;
    public String               mDenom;                             //Transfer
    public String               mToAddress;                         //Transfer
    public ArrayList<Coin>      mAmounts;                           //Transfer
    public Coin                 mAmount;                            //Delegate, Undelegate, Redelegate, ReInvest
    public String               mValAddress;                        //Delegate, Undelegate, ReInvest
    public String               mToValAddress;                      //Redelegate
    public ArrayList<Coin>      mRewards = new ArrayList<>();       //Reward
    public ArrayList<String>    mValAddresses = new ArrayList<>();  //ClaimReward
    public String               mNewRewardAddress;                  //SetRewardAddress
    public String               mProposalId;                        //Vote
    public String               mOpinion;                           //Vote

    public String                       mStarNameDomain;                            //starname domain
    public String                       mStarNameDomainType;                        //starname domain type
    public String                       mStarNameAccount;                           //starname
    public ArrayList<Types.Resource>    mStarNameResources = new ArrayList();       //starname

    public Tx.SwapAmountInRoute         swapAmountInRoute;                          // osmosis

    public ArrayList<Validator> mValidators = new ArrayList<>();    //ClaimReward old


    public void onNextStep() { }

    public void onBeforeStep() { }

    public void onBroadcastTx() {

    }
}
