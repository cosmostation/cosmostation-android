package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

import static wannabit.io.cosmostaion.base.BaseConstant.IOV_MSG_TYPE_RENEW_DOMAIN;

import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

public class RenewStarnameGrpcTask extends CommonTask {
    private BaseChain           mBaseChain;
    private Account             mAccount;
    private String              mDomain, mName, mMemo;
    private Fee                 mFees;
    private String              mChainId;
    private String              mStarnameType;

    public RenewStarnameGrpcTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, String domain, String name, String memo, Fee fee, String chainId, String starnameType) {
        super(app, listener);
        this.mAccount = account;
        this.mBaseChain = basechain;
        this.mDomain = domain;
        this.mName = name;
        this.mMemo = memo;
        this.mFees = fee;
        this.mChainId = chainId;
        this.mStarnameType = starnameType;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest;
            if (mStarnameType.equalsIgnoreCase(IOV_MSG_TYPE_RENEW_DOMAIN)) {
                broadcastTxRequest = Signer.getGrpcRenewDomainReq(WKey.onAuthResponse(mBaseChain, mAccount), mDomain, mAccount.address, mFees, mMemo, WKey.getECKey(mApp, mAccount), mChainId);
            } else {
                broadcastTxRequest = Signer.getGrpcRenewAccountReq(WKey.onAuthResponse(mBaseChain, mAccount), mDomain, mName, mAccount.address, mFees, mMemo, WKey.getECKey(mApp, mAccount), mChainId);
            }
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
            WLog.e( "RenewStarnameGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}
