package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_BALANCE_OF_CW20;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import com.google.gson.Gson;
import com.google.protobuf.ByteString;

import org.json.JSONObject;

import java.util.concurrent.TimeUnit;

import cosmwasm.wasm.v1.QueryGrpc;
import cosmwasm.wasm.v1.QueryOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Cw20BalanceReq;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class Cw20BalanceGrpcTask extends CommonTask {
    private BaseChain mChain;
    private Account mAccount;
    private String mContAddress;
    private QueryGrpc.QueryBlockingStub mStub;

    public Cw20BalanceGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, Account account, String contAddress) {
        super(app, listener);
        this.mChain = chain;
        this.mAccount = account;
        this.mContAddress = contAddress;
        this.mResult.taskType = TASK_GRPC_FETCH_BALANCE_OF_CW20;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Cw20BalanceReq req = new Cw20BalanceReq(mAccount.address);
            String jsonData = new Gson().toJson(req);
            ByteString queryData = ByteString.copyFromUtf8(jsonData);
            QueryOuterClass.QuerySmartContractStateRequest request = QueryOuterClass.QuerySmartContractStateRequest.newBuilder().setAddress(mContAddress).setQueryData(queryData).build();
            QueryOuterClass.QuerySmartContractStateResponse response = mStub.smartContractState(request);

            JSONObject json = new JSONObject(response.getData().toStringUtf8());
            mApp.getBaseDao().setCw20Balance(mContAddress, json.get("balance").toString());

        } catch (Exception e) {
            WLog.e("Cw20BalanceGrpcTask " + e.getMessage());
        }
        return mResult;

    }
}
