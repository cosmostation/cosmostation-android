package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_PRICES;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.ArrayList;
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

public class KavaMarketPriceGrpcTask extends CommonTask {

    private BaseChain mChain;
    private ArrayList<QueryOuterClass.CurrentPriceResponse> mResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mStub;

    public KavaMarketPriceGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_KAVA_PRICES;
        this.mStub = kava.pricefeed.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryPricesRequest request = QueryOuterClass.QueryPricesRequest.newBuilder().build();
            QueryOuterClass.QueryPricesResponse response = mStub.prices(request);
            mResultData.addAll(response.getPricesList());

            this.mResult.isSuccess = true;
            this.mResult.resultData = mResultData;

        } catch (Exception e) {
            WLog.e("KavaMarketPriceGrpcTask " + e.getMessage());
        }
        return mResult;
    }
}
