package wannabit.io.cosmostaion.task.gRpcTask;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cosmos.bank.v1beta1.QueryGrpc;
import cosmos.bank.v1beta1.QueryOuterClass;
import cosmos.base.v1beta1.CoinOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_TOTAL_SUPPLY;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

public class TotalSupplyGrpcTask extends CommonTask {
    private BaseChain mChain;
    private ArrayList<CoinOuterClass.Coin> mResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mStub;

    public TotalSupplyGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_TOTAL_SUPPLY;
        this.mStub = cosmos.bank.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryTotalSupplyRequest request = QueryOuterClass.QueryTotalSupplyRequest.newBuilder().build();
            QueryOuterClass.QueryTotalSupplyResponse response = mStub.totalSupply(request);
            mResultData.addAll(response.getSupplyList());

            this.mResult.isSuccess = true;
            this.mResult.resultData = mResultData;

//            mApp.getBaseDao().mGDexPoolTokens.add(new Coin(response.getAmount().getDenom(), response.getAmount().getAmount()));
//            mResult.resultData = mApp.getBaseDao().mGDexPoolTokens;
//            mResult.isSuccess = true;

        } catch (Exception e) { WLog.e( "TotalSupplyGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}
