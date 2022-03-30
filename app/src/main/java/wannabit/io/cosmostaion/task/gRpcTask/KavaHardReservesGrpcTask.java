package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_HARD_RESERVES;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cosmos.base.v1beta1.CoinOuterClass;
import kava.hard.v1beta1.QueryGrpc;
import kava.hard.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class KavaHardReservesGrpcTask extends CommonTask {
    private BaseChain mChain;
    private ArrayList<CoinOuterClass.Coin> mResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mStub;

    public KavaHardReservesGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_KAVA_HARD_RESERVES;
        this.mStub = kava.hard.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryReservesRequest request = QueryOuterClass.QueryReservesRequest.newBuilder().build();
            QueryOuterClass.QueryReservesResponse response = mStub.reserves(request);
            mResultData.addAll(response.getAmountList());

            mResult.resultData = mResultData;
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("KavaHardReservesGrpcTask " + e.getMessage());
        }
        return mResult;
    }

}
