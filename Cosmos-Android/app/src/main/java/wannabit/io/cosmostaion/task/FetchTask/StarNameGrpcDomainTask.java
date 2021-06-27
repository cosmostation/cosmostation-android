package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cosmos.base.query.v1beta1.Pagination;
import starnamed.x.starname.v1beta1.QueryGrpc;
import starnamed.x.starname.v1beta1.QueryOuterClass;
import starnamed.x.starname.v1beta1.Types;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STARNAME_DOMAIN;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

public class StarNameGrpcDomainTask extends CommonTask {
    private BaseChain                   mChain;
    private Account                     mAccount;
    private QueryGrpc.QueryBlockingStub mStub;

    public StarNameGrpcDomainTask(BaseApplication app, TaskListener listener, BaseChain chain, Account account) {
        super(app, listener);
        this.mChain = chain;
        this.mAccount = account;
        this.mResult.taskType = TASK_GRPC_FETCH_STARNAME_DOMAIN;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Pagination.PageRequest pageRequest = Pagination.PageRequest.newBuilder().setLimit(30).build();
            QueryOuterClass.QueryOwnerDomainsRequest request = QueryOuterClass.QueryOwnerDomainsRequest.newBuilder().setPagination(pageRequest).setOwner(mAccount.address).build();
            QueryOuterClass.QueryOwnerDomainsResponse response = mStub.ownerDomains(request);

            ArrayList<Types.Domain> returnValue = new ArrayList<>();
            for (Types.Domain domain: response.getDomainsList()) {
                returnValue.add(domain);
            }
            mResult.resultData = returnValue;
            mResult.isSuccess = true;

        } catch (Exception e) { WLog.e( "StarNameGrpcDomainTask "+ e.getMessage()); }
        return mResult;
    }

}
