package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

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

public class ChangeRewardAddressGrpcTask extends CommonTask {
    private BaseChain   mBaseChain;
    private Account     mAccount;
    private String      mToRewardAddress;
    private String      mMemo;
    private Fee         mFees;
    private String      mChainId;

    public ChangeRewardAddressGrpcTask(BaseApplication app, TaskListener listener, BaseChain basechain, Account mAccount, String mToRewardAddress, String mMemo, Fee mFees, String chainId) {
        super(app, listener);
        this.mBaseChain = basechain;
        this.mAccount = mAccount;
        this.mToRewardAddress = mToRewardAddress;
        this.mMemo = mMemo;
        this.mFees = mFees;
        this.mChainId = chainId;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcRewardAddressChangeReq(WKey.onAuthResponse(mBaseChain, mAccount), mToRewardAddress, mFees, mMemo, WKey.getECKey(mApp, mAccount), mChainId);
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
            WLog.e( "ChangeRewardAddressGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}
