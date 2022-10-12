package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

import java.util.Map;

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

public class AuthzVoteGrpcTask extends CommonTask {
    private BaseChain               mBaseChain;
    private Account                 mAccount;
    private String                  mGranter;
    private Map<Integer, String>    mOpinionMap;
    private String                  mMemo;
    private Fee                     mFee;
    private String                  mChainId;

    public AuthzVoteGrpcTask(BaseApplication app, TaskListener listener, BaseChain basechain, Account account, String granter, Map<Integer, String> opinionMap, String memo, Fee fee, String chainId) {
        super(app, listener);
        this.mBaseChain = basechain;
        this.mAccount = account;
        this.mGranter = granter;
        this.mOpinionMap = opinionMap;
        this.mMemo = memo;
        this.mFee = fee;
        this.mChainId = chainId;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcAuthzVoteReq(WKey.onAuthResponse(mBaseChain, mAccount), mAccount.address, mGranter, mOpinionMap, mFee, mMemo, WKey.getECKey(mApp, mAccount), mChainId);
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
            WLog.e("AuthzVoteGrpcTask " + e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}
