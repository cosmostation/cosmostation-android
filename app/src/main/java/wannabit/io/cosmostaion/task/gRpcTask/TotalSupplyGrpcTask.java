package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_TOTAL_SUPPLY;
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

public class TotalSupplyGrpcTask extends CommonTask {
    private BaseChain mChain;
    private ArrayList<CoinOuterClass.Coin> mResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mStub;

    public TotalSupplyGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.result.taskType = TASK_GRPC_FETCH_TOTAL_SUPPLY;
        this.mStub = cosmos.bank.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Pagination.PageRequest pageRequest = Pagination.PageRequest.newBuilder().setLimit(2000).build();
            QueryOuterClass.QueryTotalSupplyRequest request = QueryOuterClass.QueryTotalSupplyRequest.newBuilder().setPagination(pageRequest).build();
            QueryOuterClass.QueryTotalSupplyResponse response = mStub.totalSupply(request);
            mResultData.addAll(response.getSupplyList());

            this.result.isSuccess = true;
            this.result.resultData = mResultData;

//            mApp.getBaseDao().mGDexPoolTokens.add(new Coin(response.getAmount().getDenom(), response.getAmount().getAmount()));
//            mResult.resultData = mApp.getBaseDao().mGDexPoolTokens;
//            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("TotalSupplyGrpcTask " + e.getMessage());
        }
        return result;
    }
}
