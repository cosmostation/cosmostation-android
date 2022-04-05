package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_DESMOS_PROFILE_INFO;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.concurrent.TimeUnit;

import desmos.profiles.v1beta1.QueryGrpc;
import desmos.profiles.v1beta1.QueryProfile;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class DesmosProfileGrpcTask extends CommonTask {
    private final BaseChain mChain;
    private final String mDtag;
    private final QueryGrpc.QueryBlockingStub mStub;

    public DesmosProfileGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String dtag) {
        super(app, listener);
        this.mChain = chain;
        this.mDtag = dtag;
        this.result.taskType = TASK_GRPC_FETCH_DESMOS_PROFILE_INFO;
        this.mStub = desmos.profiles.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryProfile.QueryProfileRequest request = QueryProfile.QueryProfileRequest.newBuilder().setUser(mDtag).build();
            QueryProfile.QueryProfileResponse response = mStub.profile(request);
            this.result.isSuccess = true;
            this.result.resultData = response.getProfile();

        } catch (Exception e) {
            WLog.e("DesmosProfileGrpcTask " + e.getMessage());
        }
        return result;
    }
}
