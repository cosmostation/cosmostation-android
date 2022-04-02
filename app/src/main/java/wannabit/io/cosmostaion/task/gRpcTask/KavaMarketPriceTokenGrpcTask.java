package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_PRICE_TOKEN;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.concurrent.TimeUnit;

import kava.pricefeed.v1beta1.QueryGrpc;
import kava.pricefeed.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class KavaMarketPriceTokenGrpcTask extends CommonTask {

    private BaseChain mChain;
    private String mMarketId;
    private QueryGrpc.QueryBlockingStub mStub;

    public KavaMarketPriceTokenGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String marketId) {
        super(app, listener);
        this.mChain = chain;
        this.mMarketId = marketId;
        this.result.taskType = TASK_GRPC_FETCH_KAVA_PRICE_TOKEN;
        this.mStub = kava.pricefeed.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryPriceRequest request = QueryOuterClass.QueryPriceRequest.newBuilder().setMarketId(mMarketId).build();
            QueryOuterClass.QueryPriceResponse response = mStub.price(request);

            this.result.isSuccess = true;
            this.result.resultData = response.getPrice();

        } catch (Exception e) {
            WLog.e("KavaMarketPriceTokenGrpcTask " + e.getMessage());
        }
        return result;
    }
}
