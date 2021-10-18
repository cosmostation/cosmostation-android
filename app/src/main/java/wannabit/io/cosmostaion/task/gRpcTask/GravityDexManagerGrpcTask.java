package wannabit.io.cosmostaion.task.gRpcTask;

import java.util.concurrent.TimeUnit;

import cosmos.bank.v1beta1.QueryGrpc;
import cosmos.bank.v1beta1.QueryOuterClass;
import cosmos.base.query.v1beta1.Pagination;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_GRAVITY_MANAGER;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

public class GravityDexManagerGrpcTask extends CommonTask {
    private BaseChain mChain;
    private String mAddress;
    private QueryGrpc.QueryBlockingStub mStub;

    public GravityDexManagerGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String address) {
        super(app, listener);
        this.mChain = chain;
        this.mAddress = address;
        this.mResult.taskType = TASK_GRPC_FETCH_GRAVITY_MANAGER;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryAllBalancesRequest request = QueryOuterClass.QueryAllBalancesRequest.newBuilder().setAddress(mAddress).build();
            QueryOuterClass.QueryAllBalancesResponse response = mStub.allBalances(request);
            mResult.resultData = response.getBalancesList();
            mResult.resultData2 = mAddress;
            mResult.isSuccess = true;

        } catch (Exception e) { WLog.e( "GravityDexManagerGrpcTask "+ e.getMessage()); }
        return mResult;
    }

    private QueryOuterClass.QueryAllBalancesResponse pageJob(com.google.protobuf.ByteString nextKey) {
        try {
            Pagination.PageRequest pageRequest = Pagination.PageRequest.newBuilder().setKey(nextKey).build();
            QueryOuterClass.QueryAllBalancesRequest request = QueryOuterClass.QueryAllBalancesRequest.newBuilder().setPagination(pageRequest).setAddress(mAddress).build();
            QueryOuterClass.QueryAllBalancesResponse response = mStub.allBalances(request);
            if (response.hasPagination() && response.getPagination().getNextKey().size() > 0) {
                pageJob(response.getPagination().getNextKey());
            }

        } catch (Exception e) { WLog.e( "GravityDexManagerGrpcTask pageJob "+ e.getMessage()); }
        return  null;
    }
}
