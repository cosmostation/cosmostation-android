package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import org.bitcoinj.crypto.DeterministicKey;

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
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqBroadCast;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class ReInvestTask extends CommonTask {

    private Account         mAccount;
    private String          mValidatorAddress;
    private Coin            mReInvestAmount;
    private String          mReInvestMemo;
    private Fee             mReInvestFees;

    public ReInvestTask(BaseApplication app, TaskListener listener, Account mAccount, String mValidatorAddress, Coin mReInvestAmount, String mReInvestMemo, Fee mReInvestFees) {
        super(app, listener);
        this.mAccount = mAccount;
        this.mValidatorAddress = mValidatorAddress;
        this.mReInvestAmount = mReInvestAmount;
        this.mReInvestMemo = mReInvestMemo;
        this.mReInvestFees = mReInvestFees;
        this.mResult.taskType   = BaseConstant.TASK_GEN_TX_REINVEST;
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

            if (mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
                Response<ResLcdAccountInfo> accountResponse = ApiClient.getCosmosChain(mApp).getAccountInfo(mAccount.address).execute();
                if(!accountResponse.isSuccessful()) {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return mResult;
                }
                mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromLcd(mAccount.id, accountResponse.body()));
                mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromLcd(mAccount.id, accountResponse.body()));
                mAccount = mApp.getBaseDao().onSelectAccount(""+mAccount.id);

            } else if (mAccount.baseChain.equals(BaseChain.IRIS_MAIN.getChain())) {
                Response<ResLcdAccountInfo> response = ApiClient.getIrisChain(mApp).getBankInfo(mAccount.address).execute();
                if(!response.isSuccessful()) {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return mResult;
                }
                mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromLcd(mAccount.id, response.body()));
                mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromLcd(mAccount.id, response.body()));
                mAccount = mApp.getBaseDao().onSelectAccount(""+mAccount.id);
            }

            String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(BaseChain.getChain(mAccount.baseChain), entropy, Integer.parseInt(mAccount.path));


            Msg withdrawMsg = MsgGenerator.genWithdrawDeleMsg(mAccount.address, mValidatorAddress, BaseChain.getChain(mAccount.baseChain));
            Msg singleDelegateMsg = MsgGenerator.genDelegateMsg(mAccount.address, mValidatorAddress, mReInvestAmount, BaseChain.getChain(mAccount.baseChain));
            ArrayList<Msg> msgs= new ArrayList<>();
            msgs.add(withdrawMsg);
            msgs.add(singleDelegateMsg);

            ReqBroadCast reqBroadCast = MsgGenerator.getBraodcaseReq(mAccount, msgs, mReInvestFees, mReInvestMemo, deterministicKey);
            if (mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
                Response<ResBroadTx> response = ApiClient.getCosmosChain(mApp).broadTx(reqBroadCast).execute();
                if(response.isSuccessful() && response.body() != null) {
                    WLog.w("response.body() : " + response.body());
                    if (response.body().txhash != null) {
                        mResult.resultData = response.body().txhash;
                    }

                    if(response.body().code != null) {
                        WLog.w("response.code() : " + response.body().code + "  " + response.body().raw_log);
                        mResult.errorCode = response.body().code;
                        mResult.errorMsg = response.body().raw_log;
                        return mResult;
                    }
                    mResult.isSuccess = true;

                } else {
                    WLog.w("ReInvestTask not success!!");
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                }

            } else if (mAccount.baseChain.equals(BaseChain.IRIS_MAIN.getChain())) {
                Response<ResBroadTx> response = ApiClient.getIrisChain(mApp).broadTx(reqBroadCast).execute();
                if(response.isSuccessful() && response.body() != null) {
                    WLog.w("response.body() : " + response.body());
                    if (response.body().hash != null) {
                        mResult.resultData = response.body().hash;
                    }

                    if(response.body().check_tx.code != null) {
                        WLog.w("response.code() : " + response.body().check_tx.code);
                        mResult.errorCode = response.body().check_tx.code;
                        mResult.errorMsg = response.body().raw_log;
                        return mResult;
                    }
                    mResult.isSuccess = true;

                } else {
                    WLog.w("ReInvestTask not success!!");
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                }

            }


        } catch (Exception e) {
            WLog.w("e : " + e.getMessage());
            if(BaseConstant.IS_SHOWLOG) e.printStackTrace();
        }
        return mResult;
    }
}
