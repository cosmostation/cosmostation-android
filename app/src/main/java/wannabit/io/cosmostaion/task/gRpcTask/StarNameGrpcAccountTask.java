package wannabit.io.cosmostaion.task.gRpcTask;

import android.text.TextUtils;

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

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STARNAME_ACCOUNT;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

public class StarNameGrpcAccountTask extends CommonTask {
    private BaseChain                   mChain;
    private Account                     mAccount;
    private QueryGrpc.QueryBlockingStub mStub;

    public StarNameGrpcAccountTask(BaseApplication app, TaskListener listener, BaseChain chain, Account account) {
        super(app, listener);
        this.mChain = chain;
        this.mAccount = account;
        this.mResult.taskType = TASK_GRPC_FETCH_STARNAME_ACCOUNT;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Pagination.PageRequest pageRequest = Pagination.PageRequest.newBuilder().setLimit(500).build();
            QueryOuterClass.QueryOwnerAccountsRequest request = QueryOuterClass.QueryOwnerAccountsRequest.newBuilder().setPagination(pageRequest).setOwner(mAccount.address).build();
            QueryOuterClass.QueryOwnerAccountsResponse response = mStub.ownerAccounts(request);

            ArrayList<Types.Account> returnValue = new ArrayList<>();
            for (Types.Account account: response.getAccountsList()) {
                if (!TextUtils.isEmpty(account.getName().getValue())) {
                    returnValue.add(account);
                }
            }
            mResult.resultData = returnValue;
            mResult.isSuccess = true;

        } catch (Exception e) { WLog.e( "StarNameGrpcAccountTask "+ e.getMessage()); }
        return mResult;
    }
}
