package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_COMMISSION;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.math.BigDecimal;
import java.util.concurrent.TimeUnit;

import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.distribution.v1beta1.QueryGrpc;
import cosmos.distribution.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class CommissionGrpcTask extends CommonTask {
    private BaseChain mChain;
    private String mValOpAddress;
    private Coin mCommission;
    private QueryGrpc.QueryBlockingStub mStub;

    public CommissionGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String valOpAddress) {
        super(app, listener);
        this.mChain = chain;
        this.mValOpAddress = valOpAddress;
        this.mResult.taskType = TASK_GRPC_FETCH_COMMISSION;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ChainConfig chainConfig = ChainFactory.getChain(mChain);
            QueryOuterClass.QueryValidatorCommissionRequest request = QueryOuterClass.QueryValidatorCommissionRequest.newBuilder().setValidatorAddress(mValOpAddress).build();
            QueryOuterClass.QueryValidatorCommissionResponse response = mStub.validatorCommission(request);

            for (CoinOuterClass.DecCoin commission : response.getCommission().getCommissionList()) {
                if (commission.getDenom().equalsIgnoreCase(chainConfig.mainDenom())) {
                    BigDecimal commissionAmount = new BigDecimal(commission.getAmount()).movePointLeft(18);
                    mCommission = new Coin(commission.getDenom(), commissionAmount.toPlainString());
                }
            }

            this.mResult.isSuccess = true;
            this.mResult.resultData = mCommission;

        } catch (Exception e) { WLog.e( "CommissionGrpcTask "+ e.getMessage()); }
        return mResult;
    }
}
