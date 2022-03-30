package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_NFTOKEN_INFO;

import irismod.nft.QueryGrpc;
import irismod.nft.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class NFTokenInfoGrpcTask extends CommonTask {
    private BaseChain mChain;
    private String mDenomId;
    private String mTokenId;
    private QueryGrpc.QueryBlockingStub mIrisStub;
    private chainmain.nft.v1.QueryGrpc.QueryBlockingStub mCryptoStub;

    public NFTokenInfoGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String denomId, String tokenId) {
        super(app, listener);
        this.mChain = chain;
        this.mDenomId = denomId;
        this.mTokenId = tokenId;
        this.mResult.taskType = TASK_GRPC_FETCH_NFTOKEN_INFO;
        this.mIrisStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
        this.mCryptoStub = chainmain.nft.v1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.IRIS_MAIN)) {
                QueryOuterClass.QueryNFTRequest request = QueryOuterClass.QueryNFTRequest.newBuilder().setDenomId(mDenomId).setTokenId(mTokenId).build();
                QueryOuterClass.QueryNFTResponse response = mIrisStub.nFT(request);

                mResult.isSuccess = true;
                mResult.resultData = response;

            } else if (mChain.equals(BaseChain.CRYPTO_MAIN)) {
                chainmain.nft.v1.QueryOuterClass.QueryNFTRequest request = chainmain.nft.v1.QueryOuterClass.QueryNFTRequest.newBuilder().setDenomId(mDenomId).setTokenId(mTokenId).build();
                chainmain.nft.v1.QueryOuterClass.QueryNFTResponse response = mCryptoStub.nFT(request);

                mResult.isSuccess = true;
                mResult.resultData = response.getNft();
            }

        } catch (Exception e) {
            WLog.e("NFTokenInfoGrpcTask " + e.getMessage());
        }
        return mResult;
    }

}
