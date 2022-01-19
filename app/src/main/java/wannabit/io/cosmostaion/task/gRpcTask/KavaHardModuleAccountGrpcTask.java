package wannabit.io.cosmostaion.task.gRpcTask;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cosmos.auth.v1beta1.Auth;
import kava.hard.v1beta1.QueryGrpc;
import kava.hard.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_HARD_MODULE_ACCOUNT;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

public class KavaHardModuleAccountGrpcTask extends CommonTask {
    private BaseChain mChain;
    private ArrayList<Auth.ModuleAccount> mResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mStub;

    public KavaHardModuleAccountGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_KAVA_HARD_MODULE_ACCOUNT;
        this.mStub = kava.hard.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryAccountsRequest request = QueryOuterClass.QueryAccountsRequest.newBuilder().build();
            QueryOuterClass.QueryAccountsResponse response = mStub.accounts(request);
            mResultData.addAll(response.getAccountsList());

            mResult.resultData = mResultData;
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("KavaHardModuleAccountGrpcTask " + e.getMessage());
        }
        return mResult;
    }
}
