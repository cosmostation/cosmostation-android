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
import wannabit.io.cosmostaion.network.res.ResLcdKavaAccountInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_TX_KAVA_JOIN_POOL;

public class SimpleKavaDepositTask extends CommonTask {

    private Account         mAccount;
    private Coin            mTokenA;
    private Coin            mTokenB;
    private String          mSlippage;
    private long            mDeadline;
    private String          mMemo;
    private Fee             mFees;

    public SimpleKavaDepositTask(BaseApplication app, TaskListener listener, Account account, Coin mTokenA, Coin mTokenB, String memo, Fee fees) {
        super(app, listener);
        this.mAccount = account;
        this.mTokenA = mTokenA;
        this.mTokenB = mTokenB;
        this.mSlippage = "0.300000000000000000";
        this.mDeadline = (System.currentTimeMillis() / 1000) + 300;
        this.mMemo = memo;
        this.mFees = fees;
        this.mResult.taskType = TASK_GEN_TX_KAVA_JOIN_POOL;
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
                if (!response.isSuccessful()) {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return mResult;
                }
                mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromKavaLcd(mAccount.id, response.body()));
                mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromKavaLcd(mAccount.id, response.body()));
                mAccount = mApp.getBaseDao().onSelectAccount("" + mAccount.id);
            }

            String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(BaseChain.getChain(mAccount.baseChain), entropy, Integer.parseInt(mAccount.path), mAccount.newBip44);

            ArrayList<Msg> msgs= new ArrayList<>();
            Msg depositMsg = MsgGenerator.genSwapDepositMsg(mAccount.address, mTokenA, mTokenB, mSlippage, mDeadline, BaseChain.getChain(mAccount.baseChain));
            msgs.add(depositMsg);



            ReqBroadCast reqBroadCast = MsgGenerator.getBroadcaseReq(mAccount, msgs, mFees, mMemo, deterministicKey, mApp.getBaseDao().getChainId());
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

        } catch (Exception e) {
            if(BaseConstant.IS_SHOWLOG) e.printStackTrace();
        }
        return mResult;
    }
}
