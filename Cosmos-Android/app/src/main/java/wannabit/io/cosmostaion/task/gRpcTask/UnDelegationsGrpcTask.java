package wannabit.io.cosmostaion.task.gRpcTask;

import java.util.ArrayList;

import cosmos.base.query.v1beta1.Pagination;
import cosmos.staking.v1beta1.QueryGrpc;
import cosmos.staking.v1beta1.QueryOuterClass;
import cosmos.staking.v1beta1.Staking;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_UNDELEGATIONS;

public class UnDelegationsGrpcTask extends CommonTask {
    private BaseChain mChain;
    private Account mAccount;
    private ArrayList<Staking.UnbondingDelegation> mResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mStub;

    public UnDelegationsGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, Account account) {
        super(app, listener);
        this.mChain = chain;
        this.mAccount = account;
        this.mResult.taskType = TASK_GRPC_FETCH_UNDELEGATIONS;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryDelegatorUnbondingDelegationsRequest request = QueryOuterClass.QueryDelegatorUnbondingDelegationsRequest.newBuilder().setDelegatorAddr(mAccount.address).build();
            QueryOuterClass.QueryDelegatorUnbondingDelegationsResponse response = mStub.delegatorUnbondingDelegations(request);
            mResultData.addAll(response.getUnbondingResponsesList());
//            WLog.w("UnDelegations " + response);

//            if (response.hasPagination() && response.getPagination().getNextKey().size() > 0) {
//                pageJob(response.getPagination().getNextKey());
//            }
            this.mResult.isSuccess = true;
            this.mResult.resultData = mResultData;
//            WLog.w("UnDelegations " + mResultData.size());

        } catch (Exception e) { WLog.e( "UnDelegationsGrpcTask "+ e.getMessage()); }
        return mResult;
    }

    private cosmos.bank.v1beta1.QueryOuterClass.QueryAllBalancesResponse pageJob(com.google.protobuf.ByteString nextKey) {
        try {
            Pagination.PageRequest pageRequest = Pagination.PageRequest.newBuilder().setKey(nextKey).build();
            QueryOuterClass.QueryDelegatorUnbondingDelegationsRequest request = QueryOuterClass.QueryDelegatorUnbondingDelegationsRequest.newBuilder().setPagination(pageRequest).setDelegatorAddr(mAccount.address).build();
            QueryOuterClass.QueryDelegatorUnbondingDelegationsResponse response = mStub.delegatorUnbondingDelegations(request);
            mResultData.addAll(response.getUnbondingResponsesList());
            if (response.hasPagination() && response.getPagination().getNextKey().size() > 0) {
                pageJob(response.getPagination().getNextKey());
            }

        } catch (Exception e) { WLog.e( "UnDelegationsGrpcTask pageJob "+ e.getMessage()); }
        return  null;
    }
}
