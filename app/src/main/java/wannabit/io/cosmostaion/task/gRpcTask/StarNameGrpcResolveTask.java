package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STARNAME_RESOLVE;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.concurrent.TimeUnit;

import starnamed.x.starname.v1beta1.QueryGrpc;
import starnamed.x.starname.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class StarNameGrpcResolveTask extends CommonTask {
    private BaseChain mBaseChain;
    private String mAccount;
    private String mDomain;
    private QueryGrpc.QueryBlockingStub mStub;

    public StarNameGrpcResolveTask(BaseApplication app, TaskListener listener, BaseChain basecahin, String account, String domain) {
        super(app, listener);
        this.mBaseChain = basecahin;
        this.mAccount = account;
        this.mDomain = domain;
        this.mResult.taskType = TASK_GRPC_FETCH_STARNAME_RESOLVE;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryStarnameRequest request = QueryOuterClass.QueryStarnameRequest.newBuilder().setStarname(mAccount + "*" + mDomain).build();
            QueryOuterClass.QueryStarnameResponse response = mStub.starname(request);
            mResult.resultData = response.getAccount();
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("StarNameGrpcResolveTask " + e.getMessage());
        }
        return mResult;
    }
}
