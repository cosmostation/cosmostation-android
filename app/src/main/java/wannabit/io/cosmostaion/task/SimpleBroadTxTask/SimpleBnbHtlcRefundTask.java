package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GEN_TX_BNB_HTLC_REFUND;

import com.binance.dex.api.client.BinanceDexApiClientFactory;
import com.binance.dex.api.client.BinanceDexApiRestClient;
import com.binance.dex.api.client.BinanceDexEnvironment;
import com.binance.dex.api.client.Wallet;
import com.binance.dex.api.client.domain.TransactionMetadata;
import com.binance.dex.api.client.domain.broadcast.TransactionOption;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigInteger;
import java.util.List;

import retrofit2.Response;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Password;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbAccountInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class SimpleBnbHtlcRefundTask extends CommonTask {

    private Account mAccount;
    private String mSwapId;
    private String mMemo;

    private ECKey ecKey;

    public SimpleBnbHtlcRefundTask(BaseApplication app, TaskListener listener,
                                   Account account, String swapid, String memo) {
        super(app, listener);
        this.mAccount = account;
        this.mSwapId = swapid;
        this.mMemo = memo;
        this.mResult.taskType = TASK_GEN_TX_BNB_HTLC_REFUND;
    }


    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Password checkPw = mApp.getBaseDao().onSelectPassword();
            if (!CryptoHelper.verifyData(strings[0], checkPw.resource, mApp.getString(R.string.key_password))) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_INVALID_PASSWORD;
                return mResult;
            }

            if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.BNB_MAIN)) {
                Response<ResBnbAccountInfo> response = ApiClient.getBnbChain(mApp).getAccountInfo(mAccount.address).execute();
                if (!response.isSuccessful()) {
                    mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return mResult;
                }
                mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromBnbLcd(mAccount.id, response.body()));
                mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromBnbLcd(mAccount.id, response.body()));
                mAccount = mApp.getBaseDao().onSelectAccount("" + mAccount.id);

                if (mAccount.fromMnemonic) {
                    String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
                    DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(mAccount, entropy);
                    ecKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
                } else {
                    String privateKey = CryptoHelper.doDecryptData(mApp.getString(R.string.key_private) + mAccount.uuid, mAccount.resource, mAccount.spec);
                    ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
                }

                Wallet wallet = new Wallet(ecKey.getPrivateKeyAsHex(), BinanceDexEnvironment.PROD);
                wallet.setAccountNumber(mAccount.accountNumber);
                wallet.setSequence(Long.valueOf(mAccount.sequenceNumber));

                BinanceDexApiRestClient client = BinanceDexApiClientFactory.newInstance().newRestClient(BinanceDexEnvironment.PROD.getBaseUrl());
                TransactionOption options = new TransactionOption(mMemo, 82, null);
                List<TransactionMetadata> resp = client.refundHtlt(mSwapId, wallet, options, true);
                if (resp.get(0).isOk()) {
                    WLog.w("OK " + resp.get(0).getHash());
                    mResult.resultData = resp.get(0).getHash();
                    mResult.isSuccess = true;
                } else {
                    WLog.w("ERROR " + resp.get(0).getCode() + " " + resp.get(0).getLog());
                    mResult.errorCode = resp.get(0).getCode();
                    mResult.errorMsg = resp.get(0).getLog();
                }

            }

        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }

        }
        return mResult;
    }
}
