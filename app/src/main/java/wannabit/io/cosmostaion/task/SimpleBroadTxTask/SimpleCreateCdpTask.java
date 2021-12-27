package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigInteger;
import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.BuildConfig;
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
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_TX_CREATE_CDP;

public class SimpleCreateCdpTask extends CommonTask {

    private Account         mAccount;
    private String          mSender;
    private Coin            mCollateralCoin;
    private Coin            mPrincipalCoin;
    private String          mMemo;
    private Fee             mFees;
    private String          mCollateralType;

    public SimpleCreateCdpTask(BaseApplication app, TaskListener listener,
                               Account account, String sender,
                               Coin collateralCoin, Coin principalCoin,
                               String memo, Fee fees, String collateralType) {
        super(app, listener);
        this.mAccount = account;
        this.mSender = sender;
        this.mCollateralCoin = collateralCoin;
        this.mPrincipalCoin = principalCoin;
        this.mMemo = memo;
        this.mFees = fees;
        this.mCollateralType = collateralType;
        this.mResult.taskType   = TASK_GEN_TX_CREATE_CDP;
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

            if (!mAccount.address.equals(mSender)) {
                mResult.isSuccess = false;
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

            ECKey ecKey;
            if (mAccount.fromMnemonic) {
                String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
                DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(getChain(mAccount.baseChain), entropy, Integer.parseInt(mAccount.path), mAccount.newBip44, mAccount.customPath);
                ecKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
            } else {
                String privateKey = CryptoHelper.doDecryptData(mApp.getString(R.string.key_private) + mAccount.uuid, mAccount.resource, mAccount.spec);
                ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
            }

            Msg createCdpMsg = MsgGenerator.genCreateCdpMsg(mSender, mCollateralCoin, mPrincipalCoin, mCollateralType, BaseChain.getChain(mAccount.baseChain));
            ArrayList<Msg> msgs= new ArrayList<>();
            msgs.add(createCdpMsg);

            WLog.w("createCdpMsg : " +  WUtil.prettyPrinter(createCdpMsg));

            ReqBroadCast reqBroadCast = MsgGenerator.getBroadcaseReq(mAccount, msgs, mFees, mMemo, ecKey, mApp.getBaseDao().getChainId());
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

            }

        } catch (Exception e) {
            if(BuildConfig.DEBUG) e.printStackTrace();
        }
        return mResult;
    }
}
