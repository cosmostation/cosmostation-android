package wannabit.io.cosmostaion.task.gRpcTask;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import kava.cdp.v1beta1.QueryGrpc;
import kava.cdp.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_MY_CDPS;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

public class KavaCdpsByOwnerGrpcTask extends CommonTask {
    private BaseChain mChain;
    private Account   mAccount;
    private ArrayList<QueryOuterClass.CDPResponse> mResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mStub;

    public KavaCdpsByOwnerGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, Account account) {
        super(app, listener);
        this.mChain = chain;
        this.mAccount = account;
        this.mResult.taskType = TASK_GRPC_FETCH_KAVA_MY_CDPS;
        this.mStub = kava.cdp.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryCdpsRequest request = QueryOuterClass.QueryCdpsRequest.newBuilder().setOwner(mAccount.address).build();
            QueryOuterClass.QueryCdpsResponse response = mStub.cdps(request);
            mResultData.addAll(response.getCdpsList());

            mResult.resultData = mResultData;
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("KavaCdpsByOwnerGrpcTask " + e.getMessage());
        }
        return mResult;
    }
}
