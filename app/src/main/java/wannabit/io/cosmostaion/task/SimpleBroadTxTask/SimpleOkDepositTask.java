package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_BROADCAST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_TX_OK_DEPOSIT;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigInteger;
import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
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
import wannabit.io.cosmostaion.network.res.ResOkAccountInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

public class SimpleOkDepositTask extends CommonTask {

    private Account mAccount;
    private Coin mDepositCoin;
    private String mMemo;
    private Fee mFees;

    public SimpleOkDepositTask(BaseApplication app, TaskListener listener, Account account, Coin coin, String memo, Fee fees) {
        super(app, listener);
        this.mAccount = account;
        this.mDepositCoin = coin;
        this.mMemo = memo;
        this.mFees = fees;
        this.result.taskType = TASK_GEN_TX_OK_DEPOSIT;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Password checkPw = context.getBaseDao().onSelectPassword();
            if (!CryptoHelper.verifyData(strings[0], checkPw.resource, context.getString(R.string.key_password))) {
                result.isSuccess = false;
                result.errorCode = BaseConstant.ERROR_CODE_INVALID_PASSWORD;
                return result;
            }

            Response<ResOkAccountInfo> accountResponse = ApiClient.getOkexChain(context).getAccountInfo(mAccount.address).execute();
            if (!accountResponse.isSuccessful()) {
                result.errorCode = ERROR_CODE_BROADCAST;
                return result;
            }
            context.getBaseDao().onUpdateAccount(WUtil.getAccountFromOkLcd(mAccount.id, accountResponse.body()));
            context.getBaseDao().mOkAccountInfo = accountResponse.body();

            ECKey ecKey;
            if (mAccount.fromMnemonic) {
                String entropy = CryptoHelper.doDecryptData(context.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
                DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(mAccount, entropy);
                ecKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
            } else {
                String privateKey = CryptoHelper.doDecryptData(context.getString(R.string.key_private) + mAccount.uuid, mAccount.resource, mAccount.spec);
                ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
            }

            Msg depositMsg = MsgGenerator.genOkDeposit(mAccount.address, mDepositCoin);
            ArrayList<Msg> msgs = new ArrayList<>();
            msgs.add(depositMsg);

            ReqBroadCast reqBroadCast = MsgGenerator.getOKexBroadcaseReq(mAccount, msgs, mFees, mMemo, ecKey, context.getBaseDao().getChainId());
            Response<ResBroadTx> response = ApiClient.getOkexChain(context).broadTx(reqBroadCast).execute();
            if (response.isSuccessful() && response.body() != null) {
                if (response.body().txhash != null) {
                    result.resultData = response.body().txhash;
                }
                if (response.body().code != null) {
                    result.errorCode = response.body().code;
                    result.errorMsg = response.body().raw_log;
                    return result;
                }
                result.isSuccess = true;

            } else {
                result.errorCode = ERROR_CODE_BROADCAST;
            }

        } catch (Exception e) {
            if (BuildConfig.DEBUG) e.printStackTrace();
        }

        return result;
    }
}
