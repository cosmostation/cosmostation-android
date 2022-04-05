package wannabit.io.cosmostaion.task.gRpcTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_SIF_POOL_ASSET_LIST;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import sifnode.clp.v1.Querier;
import sifnode.clp.v1.QueryGrpc;
import sifnode.clp.v1.Types;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;


public class SifDexPoolAssetListGrpcTask extends CommonTask {
    private final BaseChain mChain;
    private final String mAddress;
    private final ArrayList<Types.Asset> mResultData = new ArrayList<>();
    private final QueryGrpc.QueryBlockingStub mStub;

    public SifDexPoolAssetListGrpcTask(BaseApplication app, TaskListener listener, BaseChain chain, String address) {
        super(app, listener);
        this.mChain = chain;
        this.mAddress = address;
        this.result.taskType = TASK_GRPC_FETCH_SIF_POOL_ASSET_LIST;
        this.mStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Querier.AssetListReq request = Querier.AssetListReq.newBuilder().setLpAddress(mAddress).build();
            Querier.AssetListRes response = mStub.getAssetList(request);
            mResultData.addAll(response.getAssetsList());

            result.isSuccess = true;
            result.resultData = mResultData;

        } catch (Exception e) {
            WLog.e("SifDexPoolAssetListGrpcTask " + e.getMessage());
        }
        return result;
    }
}

