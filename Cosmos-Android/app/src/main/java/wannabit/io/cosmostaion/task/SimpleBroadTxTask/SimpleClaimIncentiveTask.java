package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigDecimal;
import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.cosmos.MsgGenerator;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Password;
import wannabit.io.cosmostaion.model.kava.ClaimMultiplier;
import wannabit.io.cosmostaion.model.kava.DenomsToClaim;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResLcdKavaAccountInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_KAVA_CLAIM_INCENTIVE;

public class SimpleClaimIncentiveTask extends CommonTask {

    private Account         mAccount;
    private String          mMultiplierName;
    private String          mMemo;
    private Fee             mFees;

    public SimpleClaimIncentiveTask(BaseApplication app, TaskListener listener, Account account, String multiplierName, String memo, Fee fees) {
        super(app, listener);
        this.mAccount = account;
        this.mMultiplierName = multiplierName;
        this.mMemo = memo;
        this.mFees = fees;
        this.mResult.taskType = TASK_GEN_KAVA_CLAIM_INCENTIVE;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Password checkPw = mApp.getBaseDao().onSelectPassword();
            if(!CryptoHelper.verifyData(strings[0], checkPw.resource, mApp.getString(R.string.key_password))) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_INVALID_PASSWORD;
                return mResult;
            }

            if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.KAVA_MAIN)) {
                Response<ResLcdKavaAccountInfo> response = ApiClient.getKavaChain(mApp).getAccountInfo(mAccount.address).execute();
                if(!response.isSuccessful()) {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return mResult;
                }
                mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromKavaLcd(mAccount.id, response.body()));
                mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromKavaLcd(mAccount.id, response.body()));
                mAccount = mApp.getBaseDao().onSelectAccount(""+mAccount.id);

            }
            String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(BaseChain.getChain(mAccount.baseChain), entropy, Integer.parseInt(mAccount.path), mAccount.newBip44);

            ArrayList<Msg> msgList= new ArrayList<>();
            if (mApp.getBaseDao().mIncentiveRewards.getMintingRewardAmount().compareTo(BigDecimal.ZERO) > 0) {
                Msg msg = MsgGenerator.genClaimUSDXMintingRewardMsg(mAccount.address, mMultiplierName, BaseChain.getChain(mAccount.baseChain));
                msgList.add(msg);
            }
            if (mApp.getBaseDao().mIncentiveRewards.getHardRewardDenoms().size() > 0) {
                ArrayList<DenomsToClaim> denoms_to_claims = new ArrayList<>();
                for (String denom: mApp.getBaseDao().mIncentiveRewards.getHardRewardDenoms()) {
                    denoms_to_claims.add(new DenomsToClaim(denom, mMultiplierName));
                }
                Msg msg = MsgGenerator.genClaimHardRewardMsg(mAccount.address, mMultiplierName, denoms_to_claims, BaseChain.getChain(mAccount.baseChain));
                msgList.add(msg);
            }
            if (mApp.getBaseDao().mIncentiveRewards.getDelegatorRewardDenoms().size() > 0) {
                ArrayList<DenomsToClaim> denoms_to_claims = new ArrayList<>();
                for (String denom: mApp.getBaseDao().mIncentiveRewards.getDelegatorRewardDenoms()) {
                    denoms_to_claims.add(new DenomsToClaim(denom, mMultiplierName));
                }
                Msg msg = MsgGenerator.genClaimDelegatorRewardMsg(mAccount.address, mMultiplierName, denoms_to_claims, BaseChain.getChain(mAccount.baseChain));
                msgList.add(msg);
            }
            if (mApp.getBaseDao().mIncentiveRewards.getSwapRewardDenoms().size() > 0) {
                ArrayList<DenomsToClaim> denoms_to_claims = new ArrayList<>();
                for (String denom: mApp.getBaseDao().mIncentiveRewards.getSwapRewardDenoms()) {
                    denoms_to_claims.add(new DenomsToClaim(denom, mMultiplierName));
                }
                Msg msg = MsgGenerator.genClaimSwapRewardMsg(mAccount.address, mMultiplierName, denoms_to_claims, BaseChain.getChain(mAccount.baseChain));
                msgList.add(msg);
            }


            ReqBroadCast reqBroadCast = MsgGenerator.getBroadcaseReq(mAccount, msgList, mFees, mMemo, deterministicKey, mApp.getBaseDao().getChainId());
            if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.KAVA_MAIN)) {
                Response<ResBroadTx> response = ApiClient.getKavaChain(mApp).broadTx(reqBroadCast).execute();
                if(response.isSuccessful() && response.body() != null) {
                    if (response.body().txhash != null) {
                        mResult.resultData = response.body().txhash;
                    }
                    if(response.body().code != null) {
                        mResult.errorCode = response.body().code;
                        mResult.errorMsg = response.body().raw_log;
                        return mResult;
                    }
                    mResult.isSuccess = true;

                } else {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.KAVA_TEST)) {
                Response<ResBroadTx> response = ApiClient.getKavaTestChain(mApp).broadTx(reqBroadCast).execute();
                if(response.isSuccessful() && response.body() != null) {
                    if (response.body().txhash != null) {
                        mResult.resultData = response.body().txhash;
                    }
                    if(response.body().code != null) {
                        mResult.errorCode = response.body().code;
                        mResult.errorMsg = response.body().raw_log;
                        return mResult;
                    }
                    mResult.isSuccess = true;

                } else {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                }
            }

        } catch (Exception e) {
            if(BaseConstant.IS_SHOWLOG) e.printStackTrace();
        }
        return mResult;
    }
}
