package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_HARD_MY_BORROW;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

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

public class KavaHardMyBorrowGrpcTask extends CommonTask {
    private final BaseChain mChain;
    private final Account mAccount;
    private final ArrayList<QueryOuterClass.BorrowResponse> mResultData = new ArrayList<>();
    private final QueryGrpc.QueryBlockingStub mStub;

    public KavaHardMyBorrowGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, Account account) {
        super(app, listener);
        this.mChain = chain;
        this.mAccount = account;
        this.result.taskType = TASK_GRPC_FETCH_KAVA_HARD_MY_BORROW;
        this.mStub = kava.hard.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryBorrowsRequest request = QueryOuterClass.QueryBorrowsRequest.newBuilder().setOwner(mAccount.address).build();
            QueryOuterClass.QueryBorrowsResponse response = mStub.borrows(request);
            mResultData.addAll(response.getBorrowsList());

            result.resultData = mResultData;
            result.isSuccess = true;

        } catch (Exception e) {
            WLog.e("KavaHardMyBorrowGrpcTask " + e.getMessage());
        }
        return result;
    }
}

