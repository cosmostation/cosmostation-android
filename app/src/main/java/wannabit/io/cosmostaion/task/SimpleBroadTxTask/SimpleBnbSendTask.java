package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import com.binance.dex.api.client.BinanceDexApiClientFactory;
import com.binance.dex.api.client.BinanceDexApiRestClient;
import com.binance.dex.api.client.BinanceDexEnvironment;
import com.binance.dex.api.client.Wallet;
import com.binance.dex.api.client.domain.TransactionMetadata;
import com.binance.dex.api.client.domain.broadcast.TransactionOption;
import com.binance.dex.api.client.domain.broadcast.Transfer;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigInteger;
import java.util.ArrayList;
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
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbAccountInfo;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

public class SimpleBnbSendTask extends CommonTask {
    private Account mAccount;
    private final String mToAddress;
    private final ArrayList<Coin> mToSendAmount;
    private final String mToSendMemo;
    private final Fee mToFees;

    private ECKey ecKey;

    public SimpleBnbSendTask(BaseApplication app, TaskListener listener, Account mAccount, String mToAddress, ArrayList<Coin> mToSendAmount, String mToSendMemo, Fee mToFees) {
        super(app, listener);
        this.mAccount = mAccount;
        this.mToAddress = mToAddress;
        this.mToSendAmount = mToSendAmount;
        this.mToSendMemo = mToSendMemo;
        this.mToFees = mToFees;
        this.result.taskType = BaseConstant.TASK_GEN_TX_BNB_SIMPLE_SEND;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Password checkPw = context.getBaseDao().getPassword();
            if (!CryptoHelper.verifyData(strings[0], checkPw.resource, context.getString(R.string.key_password))) {
                result.isSuccess = false;
                result.errorCode = BaseConstant.ERROR_CODE_INVALID_PASSWORD;
                return result;
            }

            if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.BNB_MAIN)) {
                Response<ResBnbAccountInfo> response = ApiClient.getBnbChain(context).getAccountInfo(mAccount.address).execute();
                if (!response.isSuccessful()) {
                    result.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                    return result;
                }
                context.getBaseDao().updateAccount(WUtil.getAccountFromBnbLcd(mAccount.id, response.body()));
                context.getBaseDao().updateBalances(mAccount.id, WUtil.getBalancesFromBnbLcd(mAccount.id, response.body()));
                mAccount = context.getBaseDao().onSelectAccount("" + mAccount.id);

                if (mAccount.fromMnemonic) {
                    String entropy = CryptoHelper.doDecryptData(context.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
                    DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(mAccount, entropy);
                    ecKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
                } else {
                    String privateKey = CryptoHelper.doDecryptData(context.getString(R.string.key_private) + mAccount.uuid, mAccount.resource, mAccount.spec);
                    ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
                }

                Wallet wallet = new Wallet(ecKey.getPrivateKeyAsHex(), BinanceDexEnvironment.PROD);
                wallet.setAccountNumber(mAccount.accountNumber);
                wallet.setSequence(Long.valueOf(mAccount.sequenceNumber));

                Transfer transfer = new Transfer();
                transfer.setCoin(mToSendAmount.get(0).denom);
                transfer.setFromAddress(mAccount.address);
                transfer.setToAddress(mToAddress);
                transfer.setAmount(mToSendAmount.get(0).amount);

                TransactionOption options = new TransactionOption(mToSendMemo, 82, null);

                BinanceDexApiRestClient client = BinanceDexApiClientFactory.newInstance().newRestClient(BinanceDexEnvironment.PROD.getBaseUrl());
                List<TransactionMetadata> resp = client.transfer(transfer, wallet, options, true);
                if (resp.get(0).isOk()) {
                    result.resultData = resp.get(0).getHash();
                    result.isSuccess = true;
                } else {
                    result.errorCode = resp.get(0).getCode();
                    result.errorMsg = resp.get(0).getLog();
                }

            }

        } catch (Exception e) {
            if (BuildConfig.DEBUG) {
                e.printStackTrace();
            }

        }
        return result;
    }
}
