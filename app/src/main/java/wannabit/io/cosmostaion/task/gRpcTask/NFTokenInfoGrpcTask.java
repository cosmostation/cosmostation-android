package wannabit.io.cosmostaion.task.gRpcTask;

import irismod.nft.QueryGrpc;
import irismod.nft.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_IRIS_NFTOKEN_INFO;

public class NFTokenInfoGrpcTask extends CommonTask {
    private BaseChain mChain;
    private String mDenomId;
    private String mTokenId;
    private QueryGrpc.QueryBlockingStub mStub;

    public NFTokenInfoGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String denomId, String tokenId) {
        super(app, listener);
        this.mChain = chain;
        this.mDenomId = denomId;
        this.mTokenId = tokenId;
        this.mResult.taskType = TASK_GRPC_FETCH_IRIS_NFTOKEN_INFO;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryNFTRequest request = QueryOuterClass.QueryNFTRequest.newBuilder().setDenomId(mDenomId).setTokenId(mTokenId).build();
            QueryOuterClass.QueryNFTResponse response = mStub.nFT(request);

            mResult.isSuccess = true;
            mResult.resultData = response.getNft();

        } catch (Exception e) { WLog.e( "NFTokenInfoGrpcTask "+ e.getMessage()); }
        return mResult;
    }

}
