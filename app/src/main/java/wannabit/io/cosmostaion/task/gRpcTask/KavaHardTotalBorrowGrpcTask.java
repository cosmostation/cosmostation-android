package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_HARD_TOTAL_BORROW;
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

public class KavaHardTotalBorrowGrpcTask extends CommonTask {
    private BaseChain mChain;
    private ArrayList<CoinOuterClass.Coin> mResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mStub;

    public KavaHardTotalBorrowGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_KAVA_HARD_TOTAL_BORROW;
        this.mStub = kava.hard.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryTotalBorrowedRequest request = QueryOuterClass.QueryTotalBorrowedRequest.newBuilder().build();
            QueryOuterClass.QueryTotalBorrowedResponse response = mStub.totalBorrowed(request);
            mResultData.addAll(response.getBorrowedCoinsList());

            mResult.resultData = mResultData;
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("KavaHardTotalBorrowGrpcTask " + e.getMessage());
        }
        return mResult;
    }
}

