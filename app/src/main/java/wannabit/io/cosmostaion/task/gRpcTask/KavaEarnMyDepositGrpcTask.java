package wannabit.io.cosmostaion.task.gRpcTask;

import static kava.earn.v1beta1.QueryGrpc.QueryBlockingStub;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.concurrent.TimeUnit;

import kava.earn.v1beta1.QueryGrpc;
import kava.earn.v1beta1.QueryOuterClass.QueryDepositsRequest;
import kava.earn.v1beta1.QueryOuterClass.QueryDepositsResponse;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class KavaEarnMyDepositGrpcTask extends CommonTask {

    private BaseChain mChain;
    private Account mAccount;
    private QueryBlockingStub mStub;

    public KavaEarnMyDepositGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, Account account) {
        super(app, listener);
        this.mChain = chain;
        this.mAccount = account;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryDepositsRequest request = QueryDepositsRequest.newBuilder().setDepositor(mAccount.address).build();
            QueryDepositsResponse response = mStub.deposits(request);

            mResult.resultData = response.getDepositsList();
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("KavaEarnMyDepositGrpcTask " + e.getMessage());
        }
        return mResult;
    }
}
