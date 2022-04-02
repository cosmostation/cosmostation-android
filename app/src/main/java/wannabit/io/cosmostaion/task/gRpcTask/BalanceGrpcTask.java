package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_BALANCE;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cosmos.bank.v1beta1.QueryGrpc;
import cosmos.bank.v1beta1.QueryOuterClass;
import cosmos.base.query.v1beta1.Pagination;
import cosmos.base.v1beta1.CoinOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class BalanceGrpcTask extends CommonTask {
    private BaseChain mChain;
    private String mAddress;
    private ArrayList<CoinOuterClass.Coin> mResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mStub;

    public BalanceGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String address) {
        super(app, listener);
        this.mChain = chain;
        this.mAddress = address;
        this.result.taskType = TASK_GRPC_FETCH_BALANCE;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
        ;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Pagination.PageRequest pageRequest = Pagination.PageRequest.newBuilder().setLimit(1000).build();
            QueryOuterClass.QueryAllBalancesRequest request = QueryOuterClass.QueryAllBalancesRequest.newBuilder().setPagination(pageRequest).setAddress(mAddress).build();
            QueryOuterClass.QueryAllBalancesResponse response = mStub.allBalances(request);
            mResultData.addAll(response.getBalancesList());
//            WLog.w("response " + response.getBalancesList());

//            if (response.hasPagination() && response.getPagination().getNextKey().size() > 0) {
//                pageJob(response.getPagination().getNextKey());
//            }
            this.result.isSuccess = true;
            this.result.resultData = mResultData;
//            WLog.w("Balance " + mResultData.size());

        } catch (Exception e) {
            WLog.e("BalanceGrpcTask " + e.getMessage());
        }
        return result;
    }

    private QueryOuterClass.QueryAllBalancesResponse pageJob(com.google.protobuf.ByteString nextKey) {
        try {
            Pagination.PageRequest pageRequest = Pagination.PageRequest.newBuilder().setKey(nextKey).build();
            QueryOuterClass.QueryAllBalancesRequest request = QueryOuterClass.QueryAllBalancesRequest.newBuilder().setPagination(pageRequest).setAddress(mAddress).build();
            QueryOuterClass.QueryAllBalancesResponse response = mStub.allBalances(request);
            mResultData.addAll(response.getBalancesList());
            if (response.hasPagination() && response.getPagination().getNextKey().size() > 0) {
                pageJob(response.getPagination().getNextKey());
            }

        } catch (Exception e) {
            WLog.e("BalanceGrpcTask pageJob " + e.getMessage());
        }
        return null;
    }
}
