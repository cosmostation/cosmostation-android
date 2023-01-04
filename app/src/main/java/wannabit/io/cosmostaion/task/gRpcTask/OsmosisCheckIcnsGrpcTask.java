package wannabit.io.cosmostaion.task.gRpcTask;

import static cosmwasm.wasm.v1.QueryGrpc.newBlockingStub;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_OSMOSIS_ICNS;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import com.google.gson.Gson;
import com.google.protobuf.ByteString;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import cosmwasm.wasm.v1.QueryGrpc;
import cosmwasm.wasm.v1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.dao.ICNSInfoReq;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class OsmosisCheckIcnsGrpcTask extends CommonTask {
    private ChainConfig mChainConfig;
    private String mUserInput;
    private QueryGrpc.QueryBlockingStub mStub;

    public OsmosisCheckIcnsGrpcTask(BaseApplication app, TaskListener listener, ChainConfig chainConfig, String userInput) {
        super(app, listener);
        this.mChainConfig = chainConfig;
        this.mUserInput = userInput;
        this.mResult.taskType = TASK_GRPC_FETCH_OSMOSIS_ICNS;
        this.mStub = newBlockingStub(ChannelBuilder.getChain(BaseChain.OSMOSIS_MAIN)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ICNSInfoReq infoReq = new ICNSInfoReq(mUserInput);
            String jsonData = new Gson().toJson(infoReq);
            ByteString queryData = ByteString.copyFromUtf8(jsonData);
            QueryOuterClass.QuerySmartContractStateRequest request = QueryOuterClass.QuerySmartContractStateRequest.newBuilder().setAddress(BaseConstant.ICNS_OSMOSIS_ADDRESS).setQueryData(queryData).build();
            QueryOuterClass.QuerySmartContractStateResponse response = mStub.smartContractState(request);

            JSONObject json = new JSONObject(response.getData().toStringUtf8());
            mResult.resultData = json.get("bech32_address").toString();
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("OsmosisCheckIcnsGrpcTask " + e.getMessage());
        }
        return mResult;
    }
}
