package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_OSMOSIS_LOCKUP_STATUS;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import osmosis.lockup.Lock;
import osmosis.lockup.QueryGrpc;
import osmosis.lockup.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class OsmosisLockupStatusGrpcTask extends CommonTask {
    private BaseChain mChain;
    private String mAddress;
    private QueryGrpc.QueryBlockingStub mStub;
    private ArrayList<Lock.PeriodLock> mResultData = new ArrayList<>();

    public OsmosisLockupStatusGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String address) {
        super(app, listener);
        this.mChain = chain;
        this.mAddress = address;
        this.result.taskType = TASK_GRPC_FETCH_OSMOSIS_LOCKUP_STATUS;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.AccountLockedPastTimeRequest request = QueryOuterClass.AccountLockedPastTimeRequest.newBuilder().setOwner(mAddress).build();
            QueryOuterClass.AccountLockedPastTimeResponse response = mStub.accountLockedPastTime(request);

            for (Lock.PeriodLock lock : response.getLocksList()) {
                mResultData.add(lock);
            }
            result.resultData = mResultData;
            result.isSuccess = true;

        } catch (Exception e) {
            WLog.e("OsmosisLockupStatusGrpcTask " + e.getMessage());
        }
        return result;
    }
}
