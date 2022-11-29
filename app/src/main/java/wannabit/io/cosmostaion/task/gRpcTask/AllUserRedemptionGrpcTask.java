package wannabit.io.cosmostaion.task.gRpcTask;

import static stride.records.QueryGrpc.QueryBlockingStub;
import static stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserRequest;
import static stride.records.QueryOuterClass.QueryAllUserRedemptionRecordForUserResponse;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_ALL_USER_REDEMPTION;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import stride.records.Genesis;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class AllUserRedemptionGrpcTask extends CommonTask {

    private ChainConfig mChainConfig;
    private Account mAccount;
    private String mChainId;
    private long mDay;
    private QueryBlockingStub mStub;
    private ArrayList<Genesis.UserRedemptionRecord> mResultData = new ArrayList<>();

    public AllUserRedemptionGrpcTask(BaseApplication app, TaskListener listener, ChainConfig chainConfig, Account account, String chainId, long day) {
        super(app, listener);
        this.mChainConfig = chainConfig;
        this.mAccount = account;
        this.mChainId = chainId;
        this.mDay = day;
        this.mResult.taskType = TASK_GRPC_FETCH_ALL_USER_REDEMPTION;
        this.mStub = stride.records.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChainConfig.baseChain())).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryAllUserRedemptionRecordForUserRequest request = QueryAllUserRedemptionRecordForUserRequest.newBuilder().setAddress(mAccount.address).setChainId(mChainId).setDay(mDay).setLimit(50).build();
            QueryAllUserRedemptionRecordForUserResponse response = mStub.userRedemptionRecordForUser(request);
            mResultData.addAll(response.getUserRedemptionRecordList());

            mResult.resultData = mResultData;
            mResult.isSuccess = true;

        } catch (Exception e) { WLog.e( "AllUserRedemptionGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}
