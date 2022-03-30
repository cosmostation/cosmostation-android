package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_KAVA_CDP_BY_DEPOSITOR;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import kava.cdp.v1beta1.Cdp;
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

public class KavaCdpsByDepositorGrpcTask extends CommonTask {
    private BaseChain mChain;
    private Account mAccount;
    private String mCollateralType;
    private ArrayList<Cdp.Deposit> mResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mStub;

    public KavaCdpsByDepositorGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, Account account, String collateralType) {
        super(app, listener);
        this.mChain = chain;
        this.mAccount = account;
        this.mCollateralType = collateralType;
        this.mResult.taskType = TASK_GRPC_FETCH_KAVA_CDP_BY_DEPOSITOR;
        this.mStub = kava.cdp.v1beta1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryDepositsRequest request = QueryOuterClass.QueryDepositsRequest.newBuilder().setOwner(mAccount.address).setCollateralType(mCollateralType).build();
            QueryOuterClass.QueryDepositsResponse response = mStub.deposits(request);
            mResultData.addAll(response.getDepositsList());

            mResult.resultData = mResultData;
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("KavaCdpsByDepositorGrpcTask " + e.getMessage());
        }
        return mResult;
    }
}
