package wannabit.io.cosmostaion.task.gRpcTask;

import static cosmos.staking.v1beta1.Staking.BondStatus.BOND_STATUS_UNBONDING;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_UNBONDING_VALIDATORS;
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

public class UnBondingValidatorsGrpcTask extends CommonTask {
    private final BaseChain mChain;
    private final ArrayList<Staking.Validator> mResultData = new ArrayList<>();
    private final QueryGrpc.QueryBlockingStub mStub;

    public UnBondingValidatorsGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.result.taskType = TASK_GRPC_FETCH_UNBONDING_VALIDATORS;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }


    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Pagination.PageRequest pageRequest = Pagination.PageRequest.newBuilder().setLimit(300).build();
            QueryOuterClass.QueryValidatorsRequest request = QueryOuterClass.QueryValidatorsRequest.newBuilder().setPagination(pageRequest).setStatus(BOND_STATUS_UNBONDING.name()).build();
            QueryOuterClass.QueryValidatorsResponse response = mStub.validators(request);
            mResultData.addAll(response.getValidatorsList());

            this.result.isSuccess = true;
            this.result.resultData = mResultData;
//            WLog.w("UnBondingValidators " + mResultData.size());

//            QueryOuterClass.QueryValidatorsRequest request = QueryOuterClass.QueryValidatorsRequest.newBuilder().setStatus(BOND_STATUS_UNBONDING.name()).build();
//            QueryOuterClass.QueryValidatorsResponse response = mStub.validators(request);
//            mResultData.addAll(response.getValidatorsList());
//
//            if (response.hasPagination() && response.getPagination().getNextKey().size() > 0) {
//                pageJob(response.getPagination().getNextKey());
//            }
//            this.mResult.isSuccess = true;
//            this.mResult.resultData = mResultData;
//            WLog.w("UnBondingValidators " + mResultData.size());

        } catch (Exception e) {
            WLog.e("BondedValidatorsGrpcTask " + e.getMessage());
        }
        return result;
    }

    private QueryOuterClass.QueryValidatorsResponse pageJob(com.google.protobuf.ByteString nextKey) {
        try {
            Pagination.PageRequest pageRequest = Pagination.PageRequest.newBuilder().setKey(nextKey).build();
            QueryOuterClass.QueryValidatorsRequest request = QueryOuterClass.QueryValidatorsRequest.newBuilder().setPagination(pageRequest).setStatus(BOND_STATUS_UNBONDING.name()).build();
            QueryOuterClass.QueryValidatorsResponse response = mStub.validators(request);
            mResultData.addAll(response.getValidatorsList());
            if (response.hasPagination() && response.getPagination().getNextKey().size() > 0) {
                pageJob(response.getPagination().getNextKey());
            }
        } catch (Exception e) {
            WLog.e("BondedValidatorsGrpcTask pageJob " + e.getMessage());
        }
        return null;
    }
}