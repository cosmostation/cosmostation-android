package wannabit.io.cosmostaion.task.gRpcTask;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import kava.hard.v1beta1.QueryGrpc;
import kava.hard.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_HARD_MY_BORROW;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

public class KavaHardMyBorrowGrpcTask extends CommonTask {
    private BaseChain mChain;
    private Account mAccount;
    private ArrayList<QueryOuterClass.BorrowResponse> mResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mStub;

    public KavaHardMyBorrowGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, Account account) {
        super(app, listener);
        this.mChain = chain;
        this.mAccount = account;
        this.mResult.taskType = TASK_GRPC_FETCH_KAVA_HARD_MY_BORROW;
        this.mStub = kava.hard.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryBorrowsRequest request = QueryOuterClass.QueryBorrowsRequest.newBuilder().setOwner(mAccount.address).build();
            QueryOuterClass.QueryBorrowsResponse response = mStub.borrows(request);
            mResultData.addAll(response.getBorrowsList());

            mResult.resultData = mResultData;
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("KavaHardMyBorrowGrpcTask " + e.getMessage());
        }
        return mResult;
    }
}

