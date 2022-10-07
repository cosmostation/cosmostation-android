package wannabit.io.cosmostaion.task;

import android.os.AsyncTask;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigInteger;

import cosmos.auth.v1beta1.QueryGrpc;
import cosmos.auth.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.utils.WKey;

public class CommonTask extends AsyncTask<String, Void, TaskResult> {

    protected BaseApplication         mApp;
    protected TaskListener            mListener;
    protected TaskResult              mResult;

    public CommonTask(BaseApplication app, TaskListener listener) {
        this.mApp       = app;
        this.mListener  = listener;
        this.mResult    = new TaskResult();
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        return null;
    }

    @Override
    protected void onPostExecute(TaskResult taskResult) {
        super.onPostExecute(taskResult);
        if(mListener != null)
            mListener.onTaskResponse(taskResult);
    }

    public ECKey ecKey(Account account) {
        if (account.fromMnemonic) {
            String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + account.uuid, account.resource, account.spec);
            DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(account, entropy);
            return ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
        } else {
            String privateKey = CryptoHelper.doDecryptData(mApp.getString(R.string.key_private) + account.uuid, account.resource, account.spec);
            return ECKey.fromPrivate(new BigInteger(privateKey, 16));
        }
    }

    public QueryOuterClass.QueryAccountResponse onAuthResponse(BaseChain baseChain, Account account) {
        QueryGrpc.QueryBlockingStub authStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(baseChain));
        QueryOuterClass.QueryAccountRequest request = QueryOuterClass.QueryAccountRequest.newBuilder().setAddress(account.address).build();
        return authStub.account(request);
    }
}
