package wannabit.io.cosmostaion.task.SimpleBroadTxTask;

import com.binance.dex.api.client.BinanceDexApiClientFactory;
import com.binance.dex.api.client.BinanceDexApiRestClient;
import com.binance.dex.api.client.BinanceDexEnvironment;
import com.binance.dex.api.client.Wallet;
import com.binance.dex.api.client.domain.TransactionMetadata;
import com.binance.dex.api.client.domain.broadcast.TransactionOption;
import com.binance.dex.api.client.domain.broadcast.Transfer;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Response;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
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
    private Account         mAccount;
    private String          mToAddress;
    private ArrayList<Coin> mToSendAmount;
    private String          mToSendMemo;
    private Fee             mToFees;

    public SimpleBnbSendTask(BaseApplication app, TaskListener listener, Account mAccount, String mToAddress, ArrayList<Coin> mToSendAmount, String mToSendMemo, Fee mToFees) {
        super(app, listener);
        this.mAccount = mAccount;
        this.mToAddress = mToAddress;
        this.mToSendAmount = mToSendAmount;
        this.mToSendMemo = mToSendMemo;
        this.mToFees = mToFees;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ResBnbAccountInfo> response = ApiClient.getBnbChain().getAccountInfo(mAccount.address).execute();
            if(!response.isSuccessful()) {
                mResult.errorCode = BaseConstant.ERROR_CODE_BROADCAST;
                return mResult;
            }
            mApp.getBaseDao().onUpdateAccount(WUtil.getAccountFromBnbLcd(mAccount.id, response.body()));
            mApp.getBaseDao().onUpdateBalances(mAccount.id, WUtil.getBalancesFromBnbLcd(mAccount.id, response.body()));
            mAccount = mApp.getBaseDao().onSelectAccount(""+mAccount.id);

            Wallet wallet = new Wallet(WKey.getECKey(mApp, mAccount).getPrivateKeyAsHex(), BinanceDexEnvironment.PROD);
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
                mResult.resultData = resp.get(0).getHash();
                mResult.isSuccess = true;
            } else {
                mResult.errorCode = resp.get(0).getCode();
                mResult.errorMsg = resp.get(0).getLog();
            }

        } catch (Exception e) {
            if(BuildConfig.DEBUG) {
                e.printStackTrace();
            }

        }
        return mResult;
    }
}
