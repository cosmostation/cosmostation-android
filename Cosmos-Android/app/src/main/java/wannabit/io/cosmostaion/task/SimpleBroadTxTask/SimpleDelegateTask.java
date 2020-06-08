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
import wannabit.io.cosmostaion.network.res.ResLcdKavaAccountInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class SimpleDelegateTask extends CommonTask {

    private Account         mAccount;
    private String          mToValidatorAddress;
    private Coin            mToDelegateAmount;
    private String          mToDelegateMemo;
    private Fee             mToFees;

    public SimpleDelegateTask(BaseApplication app, TaskListener listener,
                              Account account, String toValidatorAddress,
                              Coin toDelegateAmount,
                              String toDelegateMemo, Fee toFees) {
        super(app, listener);
        this.mAccount = account;
        this.mToValidatorAddress = toValidatorAddress;
        this.mToDelegateAmount = toDelegateAmount;
        this.mToDelegateMemo = toDelegateMemo;
        this.mToFees = toFees;
        this.mResult.taskType   = BaseConstant.TASK_GEN_TX_SIMPLE_DELEGATE;
    }


    /**
     *
     * @param strings
     *  strings[0] : password
     *
     * @return
     */
    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Password checkPw = mApp.getBaseDao().onSelectPassword();
            if(!CryptoHelper.verifyData(strings[0], checkPw.resource, mApp.getString(R.string.key_password))) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_INVALID_PASSWORD;
                return mResult;
            }

            if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.COSMOS_MAIN)) {
                Response<ResLcdAccountInfo> accountResponse = ApiClient.getCosmosChain(mApp).getAccountInfo(mAccount.address).execute();
                if(!accountResponse.isSuccessful()) {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return mResult;
                }
                mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromLcd(mAccount.id, accountResponse.body()));
                mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromLcd(mAccount.id, accountResponse.body()));
                mAccount = mApp.getBaseDao().onSelectAccount(""+mAccount.id);

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.IRIS_MAIN)) {
                Response<ResLcdAccountInfo> response = ApiClient.getIrisChain(mApp).getBankInfo(mAccount.address).execute();
                if(!response.isSuccessful()) {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return mResult;
                }
                mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromLcd(mAccount.id, response.body()));
                mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromLcd(mAccount.id, response.body()));
                mAccount = mApp.getBaseDao().onSelectAccount(""+mAccount.id);

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.KAVA_MAIN)) {
                Response<ResLcdKavaAccountInfo> response = ApiClient.getKavaChain(mApp).getAccountInfo(mAccount.address).execute();
                if(!response.isSuccessful()) {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return mResult;
                }
                mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromKavaLcd(mAccount.id, response.body(), BaseChain.getChain(mAccount.baseChain)));
                mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromKavaLcd(mAccount.id, response.body(), BaseChain.getChain(mAccount.baseChain)));
                mAccount = mApp.getBaseDao().onSelectAccount(""+mAccount.id);

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.KAVA_TEST)) {
                Response<ResLcdKavaAccountInfo> response = ApiClient.getKavaTestChain(mApp).getAccountInfo(mAccount.address).execute();
                if(!response.isSuccessful()) {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return mResult;
                }
                mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromKavaLcd(mAccount.id, response.body(), BaseChain.getChain(mAccount.baseChain)));
                mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromKavaLcd(mAccount.id, response.body(), BaseChain.getChain(mAccount.baseChain)));
                mAccount = mApp.getBaseDao().onSelectAccount(""+mAccount.id);

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.BAND_MAIN)) {
                Response<ResLcdAccountInfo> accountResponse = ApiClient.getBandChain(mApp).getAccountInfo(mAccount.address).execute();
                if(!accountResponse.isSuccessful()) {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return mResult;
                }
                mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromLcd(mAccount.id, accountResponse.body()));
                mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromLcd(mAccount.id, accountResponse.body()));
                mAccount = mApp.getBaseDao().onSelectAccount(""+mAccount.id);

            }

            String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(BaseChain.getChain(mAccount.baseChain), entropy, Integer.parseInt(mAccount.path), mAccount.newBip44);

            Msg singleDelegateMsg = MsgGenerator.genDelegateMsg(mAccount.address, mToValidatorAddress, mToDelegateAmount, BaseChain.getChain(mAccount.baseChain));
            ArrayList<Msg> msgs= new ArrayList<>();
            msgs.add(singleDelegateMsg);

//            WLog.w("singleDelegateMsg : " +  WUtil.prettyPrinter(singleDelegateMsg));

            ReqBroadCast reqBroadCast = MsgGenerator.getBraodcaseReq(mAccount, msgs, mToFees, mToDelegateMemo, deterministicKey);
            if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.COSMOS_MAIN)) {
                Response<ResBroadTx> response = ApiClient.getCosmosChain(mApp).broadTx(reqBroadCast).execute();
                if (response.isSuccessful() && response.body() != null) {
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

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.IRIS_MAIN)) {
                Response<ResBroadTx> response = ApiClient.getIrisChain(mApp).broadTx(reqBroadCast).execute();
                if (response.isSuccessful() && response.body() != null) {
                    if (response.body().hash != null) {
                        mResult.resultData = response.body().hash;
                    }
                    if(response.body().check_tx.code != null) {
                        mResult.errorCode = response.body().check_tx.code;
                        mResult.errorMsg = response.body().raw_log;
                        return mResult;
                    }
                    mResult.isSuccess = true;

                } else {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.KAVA_MAIN)) {
                Response<ResBroadTx> response = ApiClient.getKavaChain(mApp).broadTx(reqBroadCast).execute();
                if (response.isSuccessful() && response.body() != null) {
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
                if (response.isSuccessful() && response.body() != null) {
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

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.BAND_MAIN)) {
                Response<ResBroadTx> response = ApiClient.getBandChain(mApp).broadTx(reqBroadCast).execute();
                if (response.isSuccessful() && response.body() != null) {
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
