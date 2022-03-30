package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_HARD_MY_DEPOSIT;
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

public class KavaHardMyDepositGrpcTask extends CommonTask {
    private BaseChain mChain;
    private Account mAccount;
    private ArrayList<QueryOuterClass.DepositResponse> mResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mStub;

    public KavaHardMyDepositGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, Account account) {
        super(app, listener);
        this.mChain = chain;
        this.mAccount = account;
        this.mResult.taskType = TASK_GRPC_FETCH_KAVA_HARD_MY_DEPOSIT;
        this.mStub = kava.hard.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryDepositsRequest request = QueryOuterClass.QueryDepositsRequest.newBuilder().setOwner(mAccount.address).build();
            QueryOuterClass.QueryDepositsResponse response = mStub.deposits(request);
            mResultData.addAll(response.getDepositsList());

            mResult.resultData = mResultData;
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("KavaHardMyDepositGrpcTask " + e.getMessage());
        }
        return mResult;
    }
}
