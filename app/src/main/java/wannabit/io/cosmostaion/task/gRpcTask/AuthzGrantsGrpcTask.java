package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_AUTHZ_GRANT_LIST;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cosmos.authz.v1beta1.Authz;
import cosmos.authz.v1beta1.QueryGrpc;
import cosmos.authz.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class AuthzGrantsGrpcTask extends CommonTask {
    private BaseChain mChain;
    private Account mAccount;
    private String mGranter;
    private ArrayList<Authz.Grant> mResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mStub;

    public AuthzGrantsGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, Account account, String granter) {
        super(app, listener);
        this.mChain = chain;
        this.mAccount = account;
        this.mGranter = granter;
        this.mResult.taskType = TASK_GRPC_FETCH_AUTHZ_GRANT_LIST;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryGrantsRequest request = QueryOuterClass.QueryGrantsRequest.newBuilder().setGranter(mGranter).setGrantee(mAccount.address).build();
            QueryOuterClass.QueryGrantsResponse response = mStub.grants(request);
            for (Authz.Grant grant : response.getGrantsList()) {
                mResultData.add(grant);
            }

            mResult.isSuccess = true;
            mResult.resultData = mResultData;

        } catch (Exception e) { WLog.e( "AuthzGrantsGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}
