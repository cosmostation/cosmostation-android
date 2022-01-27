package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigInteger;

import cosmos.auth.v1beta1.QueryGrpc;
import cosmos.auth.v1beta1.QueryOuterClass;
import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Password;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_INVALID_PASSWORD;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_GEN_TX_RENEW_DOMAIN;

public class RenewDomainGrpcTask extends CommonTask {
    private BaseChain           mBaseChain;
    private Account             mAccount;
    private String              mDomain, mMemo;
    private Fee                 mFees;
    private String              mChainId;

    private QueryOuterClass.QueryAccountResponse mAuthResponse;
    private ECKey ecKey;

    public RenewDomainGrpcTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, String domain, String memo, Fee fee, String chainId) {
        super(app, listener);
        this.mAccount = account;
        this.mBaseChain = basechain;
        this.mDomain = domain;
        this.mMemo = memo;
        this.mFees = fee;
        this.mChainId = chainId;
        this.mResult.taskType = TASK_GRPC_GEN_TX_RENEW_DOMAIN;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        Password checkPw = mApp.getBaseDao().onSelectPassword();
        if (!CryptoHelper.verifyData(strings[0], checkPw.resource, mApp.getString(R.string.key_password))) {
            mResult.isSuccess = false;
            mResult.errorCode = ERROR_CODE_INVALID_PASSWORD;
            return mResult;
        }

        try {
            if (mAccount.fromMnemonic) {
                String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
                DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(mAccount, entropy);
                ecKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
            } else {
                String privateKey = CryptoHelper.doDecryptData(mApp.getString(R.string.key_private) + mAccount.uuid, mAccount.resource, mAccount.spec);
                ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
            }

            QueryGrpc.QueryBlockingStub authStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            QueryOuterClass.QueryAccountRequest request = QueryOuterClass.QueryAccountRequest.newBuilder().setAddress(mAccount.address).build();
            mAuthResponse = authStub.account(request);

            //broadCast
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcRenewDomainReq(mAuthResponse, mDomain, mAccount.address, mFees, mMemo, ecKey, mChainId);
            ServiceOuterClass.BroadcastTxResponse response = txService.broadcastTx(broadcastTxRequest);
            mResult.resultData = response.getTxResponse().getTxhash();
            if (response.getTxResponse().getCode() > 0) {
                mResult.errorCode = response.getTxResponse().getCode();
                mResult.errorMsg = response.getTxResponse().getRawLog();
                mResult.isSuccess = false;
            } else {
                mResult.isSuccess = true;
            }

        } catch (Exception e) {
            WLog.e( "RenewDomainGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}
