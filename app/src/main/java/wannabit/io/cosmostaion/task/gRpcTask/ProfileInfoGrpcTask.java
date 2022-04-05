package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_PROFILE_INFO;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.concurrent.TimeUnit;

import desmos.profiles.v1beta1.ModelsProfile;
import desmos.profiles.v1beta1.QueryGrpc;
import desmos.profiles.v1beta1.QueryProfile;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class ProfileInfoGrpcTask extends CommonTask {
    private final BaseChain mChain;
    private final String mAddress;
    private final QueryGrpc.QueryBlockingStub mStub;

    public ProfileInfoGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String address) {
        super(app, listener);
        this.mChain = chain;
        this.mAddress = address;
        this.result.taskType = TASK_GRPC_FETCH_PROFILE_INFO;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryProfile.QueryProfileRequest request = QueryProfile.QueryProfileRequest.newBuilder().setUser(mAddress).build();
            QueryProfile.QueryProfileResponse response = mStub.profile(request);

            result.isSuccess = true;
            result.resultData = ModelsProfile.Profile.parseFrom(response.getProfile().getValue());

        } catch (Exception e) {
            WLog.e("ProfileInfoGrpcTask " + e.getMessage());
        }
        return result;
    }
}
