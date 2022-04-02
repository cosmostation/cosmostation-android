package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_SWAP_POOLS_INFO;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import kava.swap.v1beta1.QueryGrpc;
import kava.swap.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class KavaSwapPoolInfoGrpcTask extends CommonTask {
    private BaseChain mChain;
    private String mPoolId;
    private ArrayList<QueryOuterClass.PoolResponse> mResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mStub;

    public KavaSwapPoolInfoGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String poolId) {
        super(app, listener);
        this.mChain = chain;
        this.mPoolId = poolId;
        this.result.taskType = TASK_GRPC_FETCH_KAVA_SWAP_POOLS_INFO;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryPoolsRequest request = QueryOuterClass.QueryPoolsRequest.newBuilder().setPoolId(mPoolId).build();
            QueryOuterClass.QueryPoolsResponse response = mStub.pools(request);
            mResultData.addAll(response.getPoolsList());

            result.resultData = mResultData;
            result.isSuccess = true;

        } catch (Exception e) {
            WLog.e("KavaSwapPoolInfoGrpcTask " + e.getMessage());
        }
        return result;
    }
}
