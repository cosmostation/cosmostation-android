package wannabit.io.cosmostaion.task.gRpcTask;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cosmos.base.query.v1beta1.Pagination;
import tendermint.liquidity.v1beta1.Liquidity;
import tendermint.liquidity.v1beta1.QueryGrpc;
import tendermint.liquidity.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_GRAVITY_POOL_LIST;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

public class GravityDexPoolGrpcTask extends CommonTask {
    private BaseChain mChain;
    private QueryGrpc.QueryBlockingStub mStub;
    private ArrayList<Liquidity.Pool> mResultData = new ArrayList<>();

    public GravityDexPoolGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_GRAVITY_POOL_LIST;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Pagination.PageRequest pageRequest = Pagination.PageRequest.newBuilder().setLimit(1000).build();
            QueryOuterClass.QueryLiquidityPoolsRequest request = QueryOuterClass.QueryLiquidityPoolsRequest.newBuilder().setPagination(pageRequest).build();
            QueryOuterClass.QueryLiquidityPoolsResponse response = mStub.liquidityPools(request);
            for (Liquidity.Pool pool: response.getPoolsList()) {
                mResultData.add(pool);
            }
            mResult.resultData = mResultData;
            mResult.isSuccess = true;

        } catch (Exception e) { WLog.e( "GravityDexPoolGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}

