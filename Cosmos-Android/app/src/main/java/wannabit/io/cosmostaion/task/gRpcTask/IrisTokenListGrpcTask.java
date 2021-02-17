package wannabit.io.cosmostaion.task.gRpcTask;


import com.google.protobuf2.Any;

import java.util.ArrayList;

import cosmos.base.query.v1beta1.Pagination;
import irismod.token.QueryGrpc;
import irismod.token.QueryOuterClass;
import irismod.token.TokenOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_IRIS_TOKEN_LIST;

public class IrisTokenListGrpcTask extends CommonTask {
    private BaseChain mChain;
    private QueryGrpc.QueryBlockingStub mStub;
    private ArrayList<TokenOuterClass.Token> mResultData = new ArrayList<>();

    public IrisTokenListGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mChain = chain;
        this.mResult.taskType = TASK_GRPC_FETCH_IRIS_TOKEN_LIST;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain));
    }


    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryOuterClass.QueryTokensRequest request = QueryOuterClass.QueryTokensRequest.newBuilder().build();
            QueryOuterClass.QueryTokensResponse response = mStub.tokens(request);
            for (Any token: response.getTokensList()) {
                mResultData.add(TokenOuterClass.Token.parseFrom(token.getValue()));
            }

            if (response.hasPagination() && response.getPagination().getNextKey().size() > 0) {
                pageJob(response.getPagination().getNextKey());
            }
            this.mResult.isSuccess = true;
            this.mResult.resultData = mResultData;
//            WLog.w("Iris tokens " + mResultData.size());


        } catch (Exception e) { WLog.e( "IrisTokenListGrpcTask "+ e.getMessage()); }
        return mResult;
    }


    private QueryOuterClass.QueryTokensResponse pageJob(com.google.protobuf.ByteString nextKey) {
        try {
            Pagination.PageRequest pageRequest = Pagination.PageRequest.newBuilder().setKey(nextKey).build();
            QueryOuterClass.QueryTokensRequest request = QueryOuterClass.QueryTokensRequest.newBuilder().setPagination(pageRequest).build();
            QueryOuterClass.QueryTokensResponse response = mStub.tokens(request);
            for (Any token: response.getTokensList()) {
                mResultData.add(TokenOuterClass.Token.parseFrom(token.getValue()));
            }

            if (response.hasPagination() && response.getPagination().getNextKey().size() > 0) {
                pageJob(response.getPagination().getNextKey());
            }

        } catch (Exception e) { WLog.e( "BalanceGrpcTask pageJob "+ e.getMessage()); }
        return  null;
    }
}
