package wannabit.io.cosmostaion.task.gRpcTask.simulate;

import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.concurrent.TimeUnit;

import cosmos.base.tendermint.v1beta1.Query;
import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import ibc.core.channel.v1.QueryGrpc;
import ibc.lightclients.tendermint.v1.Tendermint;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.AssetPath;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

public class SimulIBCTransferGrpcTask extends CommonTask {

    private Account                 mAccount;
    private BaseChain               mBaseChain;
    private String                  mSender;
    private String                  mReceiver;
    private ChainConfig             mToChainConfig;
    private String                  mTokenDenom, mTokenAmount;
    private AssetPath               mAssetPath;
    private Fee                     mFees;

    public SimulIBCTransferGrpcTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, String sender, String recevier, ChainConfig toChainConfig, String tokenDenom, String tokenAmount,
                                    AssetPath assetPath, Fee fee) {
        super(app, listener);
        this.mAccount = account;
        this.mBaseChain = basechain;
        this.mSender = sender;
        this.mReceiver = recevier;
        this.mToChainConfig = toChainConfig;
        this.mTokenDenom = tokenDenom;
        this.mTokenAmount = tokenAmount;
        this.mAssetPath = assetPath;
        this.mFees = fee;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            QueryGrpc.QueryBlockingStub stub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
            ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest req = ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest.newBuilder().setChannelId(mAssetPath.channel).setPortId(mAssetPath.port).build();
            ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateResponse res = stub.channelClientState(req);
            Tendermint.ClientState value = Tendermint.ClientState.parseFrom(res.getIdentifiedClientState().getClientState().getValue());

            cosmos.base.tendermint.v1beta1.ServiceGrpc.ServiceBlockingStub toStub = cosmos.base.tendermint.v1beta1.ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mToChainConfig.baseChain())).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
            Query.GetLatestBlockRequest blockRequest = Query.GetLatestBlockRequest.newBuilder().build();
            Query.GetLatestBlockResponse blockResponse = toStub.getLatestBlock(blockRequest);
            long revisionHeight = blockResponse.getBlock().getHeader().getHeight();

            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.SimulateRequest simulateTxRequest = Signer.getGrpcIbcTransferSimulateReq(WKey.onAuthResponse(mBaseChain, mAccount), mSender, mReceiver, mTokenDenom, mTokenAmount, mAssetPath, value.getLatestHeight().getRevisionNumber(), revisionHeight, mFees, "", mAccount.customPath, mBaseChain);
            ServiceOuterClass.SimulateResponse response = txService.simulate(simulateTxRequest);
            mResult.resultData = response.getGasInfo();
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("SimulIBCTransferGrpcTask " + e.getMessage());
            mResult.isSuccess = false;
            mResult.errorMsg = e.getMessage();
        }
        return mResult;
    }

}
