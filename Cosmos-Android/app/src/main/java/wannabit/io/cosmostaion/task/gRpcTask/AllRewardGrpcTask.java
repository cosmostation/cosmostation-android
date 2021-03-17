package wannabit.io.cosmostaion.task.gRpcTask;


import java.util.ArrayList;

import cosmos.distribution.v1beta1.Distribution;
import cosmos.distribution.v1beta1.QueryGrpc;
import cosmos.distribution.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_ALL_REWARDS;

public class AllRewardGrpcTask extends CommonTask {
    private BaseChain mChain;
    private Account mAccount;
    private ArrayList<Distribution.DelegationDelegatorReward> mResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mStub;

    public AllRewardGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, Account account) {
        super(app, listener);
        this.mChain = chain;
        this.mAccount = account;
        this.mResult.taskType = TASK_GRPC_FETCH_ALL_REWARDS;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryDelegationTotalRewardsRequest request = QueryOuterClass.QueryDelegationTotalRewardsRequest.newBuilder().setDelegatorAddress(mAccount.address).build();
            QueryOuterClass.QueryDelegationTotalRewardsResponse response = mStub.delegationTotalRewards(request);
            mResultData.addAll(response.getRewardsList());
            this.mResult.isSuccess = true;
            this.mResult.resultData = mResultData;
//            WLog.w("AllReward " + mResultData.size());

        } catch (Exception e) { WLog.e( "AllRewardGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}
