package wannabit.io.cosmostaion.task.gRpcTask;

import java.util.ArrayList;

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

import static cosmos.staking.v1beta1.Staking.BondStatus.BOND_STATUS_UNBONDED;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_UNBONDED_VALIDATORS;

public class UnBondedValidatorsGrpcTask extends CommonTask {
    private BaseChain mChain;
    private ArrayList<Staking.Validator> mResultData = new ArrayList<>();
    private QueryGrpc.QueryBlockingStub mStub;

    public UnBondedValidatorsGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_UNBONDED_VALIDATORS;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }



    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryValidatorsRequest request = QueryOuterClass.QueryValidatorsRequest.newBuilder().setStatus(BOND_STATUS_UNBONDED.name()).build();
            QueryOuterClass.QueryValidatorsResponse response = mStub.validators(request);
            mResultData.addAll(response.getValidatorsList());

            if (response.hasPagination() && response.getPagination().getNextKey().size() > 0) {
                pageJob(response.getPagination().getNextKey());
            }
            this.mResult.isSuccess = true;
            this.mResult.resultData = mResultData;
            WLog.w("UnBondedValidators " + mResultData.size());

        } catch (Exception e) { WLog.e( "BondedValidatorsGrpcTask "+ e.getMessage()); }
        return mResult;
    }

    private QueryOuterClass.QueryValidatorsResponse pageJob(com.google.protobuf.ByteString nextKey) {
        try {
            Pagination.PageRequest pageRequest = Pagination.PageRequest.newBuilder().setKey(nextKey).build();
            QueryOuterClass.QueryValidatorsRequest request = QueryOuterClass.QueryValidatorsRequest.newBuilder().setPagination(pageRequest).setStatus(BOND_STATUS_UNBONDED.name()).build();
            QueryOuterClass.QueryValidatorsResponse response = mStub.validators(request);
            mResultData.addAll(response.getValidatorsList());
            if (response.hasPagination() && response.getPagination().getNextKey().size() > 0) {
                pageJob(response.getPagination().getNextKey());
            }
        } catch (Exception e) { WLog.e( "BondedValidatorsGrpcTask pageJob "+ e.getMessage()); }
        return  null;
    }
}