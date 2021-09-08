package wannabit.io.cosmostaion.task.gRpcTask;

import java.util.concurrent.TimeUnit;

import cosmos.bank.v1beta1.QueryGrpc;
import cosmos.bank.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_DENOM_SUPPLY;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

public class SupplyDenomGrpcTask extends CommonTask {
    private BaseChain mChain;
    private String mDenom;
    private QueryGrpc.QueryBlockingStub mStub;

    public SupplyDenomGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String denom) {
        super(app, listener);
        this.mChain = chain;
        this.mDenom = denom;
        this.mResult.taskType = TASK_GRPC_FETCH_DENOM_SUPPLY;
        this.mStub = cosmos.bank.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QuerySupplyOfRequest request = QueryOuterClass.QuerySupplyOfRequest.newBuilder().setDenom(mDenom).build();
            QueryOuterClass.QuerySupplyOfResponse response = mStub.supplyOf(request);

            mApp.getBaseDao().mGDexPoolTokens.add(new Coin(response.getAmount().getDenom(), response.getAmount().getAmount()));
            mResult.resultData = mApp.getBaseDao().mGDexPoolTokens;
            mResult.isSuccess = true;

        } catch (Exception e) { WLog.e( "SupplyDenomGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}
