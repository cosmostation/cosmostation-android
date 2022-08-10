package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_DELEGATIONS;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import cosmos.base.query.v1beta1.Pagination;
import cosmos.staking.v1beta1.QueryGrpc;
import cosmos.staking.v1beta1.QueryOuterClass;
import cosmos.staking.v1beta1.Staking;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;


public class DelegationsGrpcTask extends CommonTask {
    private BaseChain mChain;
    private String mAddress;
    private ArrayList<Staking.DelegationResponse> mResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mStub;

    public DelegationsGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String address) {
        super(app, listener);
        this.mChain = chain;
        this.mAddress = address;
        this.mResult.taskType = TASK_GRPC_FETCH_DELEGATIONS;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryDelegatorDelegationsRequest requset = QueryOuterClass.QueryDelegatorDelegationsRequest.newBuilder().setDelegatorAddr(mAddress).build();
            QueryOuterClass.QueryDelegatorDelegationsResponse response = mStub.delegatorDelegations(requset);
            mResultData.addAll(response.getDelegationResponsesList());
//            WLog.w("Delegations " + response.getDelegationResponsesList());

//            if (response.hasPagination() && response.getPagination().getNextKey().size() > 0) {
//                pageJob(response.getPagination().getNextKey());
//            }
            this.mResult.isSuccess = true;
            this.mResult.resultData = mResultData;
//            WLog.w("Delegations " + mResultData.size());

        } catch (Exception e) { WLog.e( "DelegationsGrpc "+ e.getMessage()); }
        return mResult;
    }

    private cosmos.bank.v1beta1.QueryOuterClass.QueryAllBalancesResponse pageJob(com.google.protobuf.ByteString nextKey) {
        try {
            Pagination.PageRequest pageRequest = Pagination.PageRequest.newBuilder().setKey(nextKey).build();
            QueryOuterClass.QueryDelegatorDelegationsRequest requset = QueryOuterClass.QueryDelegatorDelegationsRequest.newBuilder().setPagination(pageRequest).setDelegatorAddr(mAddress).build();
            QueryOuterClass.QueryDelegatorDelegationsResponse response = mStub.delegatorDelegations(requset);
            mResultData.addAll(response.getDelegationResponsesList());
            if (response.hasPagination() && response.getPagination().getNextKey().size() > 0) {
                pageJob(response.getPagination().getNextKey());
            }

        } catch (Exception e) { WLog.e( "DelegationsGrpc pageJob "+ e.getMessage()); }
        return  null;
    }
}
