package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_SIF_POOL_LIST;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cosmos.base.query.v1beta1.Pagination;
import sifnode.clp.v1.Querier;
import sifnode.clp.v1.QueryGrpc;
import sifnode.clp.v1.Types;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;


public class SifDexPoolListGrpcTask extends CommonTask {
    private BaseChain mChain;
    private QueryGrpc.QueryBlockingStub mStub;
    private ArrayList<Types.Pool> mResultData = new ArrayList<>();

    public SifDexPoolListGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.result.taskType = TASK_GRPC_FETCH_SIF_POOL_LIST;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Pagination.PageRequest pageRequest = Pagination.PageRequest.newBuilder().setLimit(200).build();
            Querier.PoolsReq request = Querier.PoolsReq.newBuilder().setPagination(pageRequest).build();
            Querier.PoolsRes response = mStub.getPools(request);

            for (Types.Pool pool : response.getPoolsList()) {
                mResultData.add(pool);
            }
            result.resultData = mResultData;
            result.isSuccess = true;

        } catch (Exception e) {
            WLog.e("SifDexPoolListGrpcTask " + e.getMessage());
        }
        return result;
    }
}

