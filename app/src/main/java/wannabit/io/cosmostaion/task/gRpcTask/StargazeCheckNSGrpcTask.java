package wannabit.io.cosmostaion.task.gRpcTask;

import static cosmwasm.wasm.v1.QueryGrpc.newBlockingStub;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STARGAZE_NS;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import com.google.gson.Gson;
import com.google.protobuf.ByteString;

import java.util.concurrent.TimeUnit;

import cosmwasm.wasm.v1.QueryGrpc;
import cosmwasm.wasm.v1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.dao.NSStargazeAddressInfoReq;
import wannabit.io.cosmostaion.dao.NSStargazeInfoReq;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class StargazeCheckNSGrpcTask extends CommonTask {
    private ChainConfig mChainConfig;
    private String mUserInput;
    private QueryGrpc.QueryBlockingStub mStub;

    public StargazeCheckNSGrpcTask(BaseApplication app, TaskListener listener, ChainConfig chainConfig, String userInput) {
        super(app, listener);
        this.mChainConfig = chainConfig;
        this.mUserInput = userInput;
        this.mResult.taskType = TASK_GRPC_FETCH_STARGAZE_NS;
        this.mStub = newBlockingStub(ChannelBuilder.getChain(BaseChain.STARGAZE_MAIN)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            String jsonData;
            if (!WDp.isValidChainAddress(mChainConfig, mUserInput)) {
                NSStargazeInfoReq infoReq = new NSStargazeInfoReq(mUserInput);
                jsonData = new Gson().toJson(infoReq);
            } else {
                NSStargazeAddressInfoReq infoReq = new NSStargazeAddressInfoReq(mUserInput);
                jsonData = new Gson().toJson(infoReq);
            }
            ByteString queryData = ByteString.copyFromUtf8(jsonData);
            QueryOuterClass.QuerySmartContractStateRequest request = QueryOuterClass.QuerySmartContractStateRequest.newBuilder().setAddress(BaseConstant.NS_STARGZE_ADDRESS).setQueryData(queryData).build();
            QueryOuterClass.QuerySmartContractStateResponse response = mStub.smartContractState(request);

            String data = response.getData().toStringUtf8().replaceAll("\"", "");
            if (WDp.isValidChainAddress(mChainConfig, data)) {
                mResult.resultData = data;
            } else {
                mResult.resultData = data + "." + mChainConfig.addressPrefix();
            }
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("StargazeCheckNSGrpcTask " + e.getMessage());
        }
        return mResult;
    }
}
