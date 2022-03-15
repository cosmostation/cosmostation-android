package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_BROADCAST;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_INVALID_PASSWORD;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_TX_SIMPLE_SEND;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigInteger;
import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
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

public class SimpleSendTask extends CommonTask {

    private Account         mAccount;
    private String          mToAddress;
    private ArrayList<Coin> mToSendAmount;
    private String          mToSendMemo;
    private Fee             mToFees;

    public SimpleSendTask(BaseApplication app, TaskListener listener, Account account,
                          String toAddress, ArrayList<Coin> toSendAmount, String toSendMemo,
                          Fee toFees) {
        super(app, listener);
        this.mAccount           = account;
        this.mToAddress         = toAddress;
        this.mToSendAmount      = toSendAmount;
        this.mToSendMemo        = toSendMemo;
        this.mToFees            = toFees;
        this.mResult.taskType   = TASK_GEN_TX_SIMPLE_SEND;
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
                mResult.errorCode = ERROR_CODE_INVALID_PASSWORD;
                return mResult;
            }

            Response<ResOkAccountInfo> accountResponse = ApiClient.getOkexChain(mApp).getAccountInfo(mAccount.address).execute();
            if (!accountResponse.isSuccessful()) {
                mResult.errorCode = ERROR_CODE_BROADCAST;
                return mResult;
            }
            mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromOkLcd(mAccount.id, accountResponse.body()));
            mApp.getBaseDao().mOkAccountInfo = accountResponse.body();

            ECKey ecKey;
            if (mAccount.fromMnemonic) {
                String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
                DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(mAccount, entropy);
                ecKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
            } else {
                String privateKey = CryptoHelper.doDecryptData(mApp.getString(R.string.key_private) + mAccount.uuid, mAccount.resource, mAccount.spec);
                ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
            }

            Msg singleSendMsg = MsgGenerator.genTransferMsg(mAccount.address, mToAddress, mToSendAmount, getChain(mAccount.baseChain));
            ArrayList<Msg> msgs= new ArrayList<>();
            msgs.add(singleSendMsg);

            ReqBroadCast reqBroadCast = MsgGenerator.getOKexBroadcaseReq(mAccount, msgs, mToFees, mToSendMemo, ecKey, mApp.getBaseDao().getChainId());
            Response<ResBroadTx> response = ApiClient.getOkexChain(mApp).broadTx(reqBroadCast).execute();
            if(response.isSuccessful() && response.body() != null) {
                if (response.body().txhash != null) {
                    mResult.resultData = response.body().txhash;
                }
                if (response.body().code != null) {
                    mResult.errorCode = response.body().code;
                    mResult.errorMsg = response.body().raw_log;
                    return mResult;
                }
                mResult.isSuccess = true;

            } else {
                mResult.errorCode = ERROR_CODE_BROADCAST;
            }

        } catch (Exception e) {
            if(BuildConfig.DEBUG) e.printStackTrace();

        }
        return mResult;
    }
}
