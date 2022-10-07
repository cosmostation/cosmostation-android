package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

public class OsmosisExitPooGrpcTask extends CommonTask {

    private Account             mAccount;
    private BaseChain           mBaseChain;
    private long                mPoolId;
    private String              mShareAmount, mMemo;
    private Coin                mDeposit0Coin, mDeposit1Coin;
    private Fee                 mFees;
    private String              mChainId;

    public OsmosisExitPooGrpcTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, long poolId, Coin deposit0Coin, Coin deposit1Coin, String shareAmount, String memo, Fee fee, String chainId) {
        super(app, listener);
        this.mAccount = account;
        this.mBaseChain = basechain;
        this.mPoolId = poolId;
        this.mDeposit0Coin = deposit0Coin;
        this.mDeposit1Coin = deposit1Coin;
        this.mShareAmount = shareAmount;
        this.mFees = fee;
        this.mMemo = memo;
        this.mChainId = chainId;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcExitPoolReq(WKey.onAuthResponse(mBaseChain, mAccount), mPoolId, mDeposit0Coin, mDeposit1Coin, mShareAmount, mFees, mMemo, WKey.getECKey(mApp, mAccount), mChainId);
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
            WLog.e( "OsmosisExitPooGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}

