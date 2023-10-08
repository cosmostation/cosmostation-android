package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import java.util.concurrent.TimeUnit;

import cosmos.base.tendermint.v1beta1.Query;
import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
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

public class IBCTransferGrpcTask extends CommonTask {

    private Account                 mAccount;
    private BaseChain               mBaseChain;
    private String                  mSender;
    private String                  mReceiver;
    private ChainConfig             mToChainConfig;
    private String                  mTokenDenom, mTokenAmount;
    private AssetPath               mAssetPath;
    private Fee                     mFees;
    private String                  mChainId;


    public IBCTransferGrpcTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, String sender, String recevier, ChainConfig toChainConfig, String tokenDenom, String tokenAmount,
                               AssetPath assetPath, Fee fee, String chainId) {
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
        this.mChainId = chainId;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            cosmos.base.tendermint.v1beta1.ServiceGrpc.ServiceBlockingStub toStub = cosmos.base.tendermint.v1beta1.ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mToChainConfig.baseChain())).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
            Query.GetNodeInfoRequest nodeInfoRequest = Query.GetNodeInfoRequest.newBuilder().build();
            Query.GetNodeInfoResponse nodeInfoResponse = toStub.getNodeInfo(nodeInfoRequest);
            String[] parts = nodeInfoResponse.getNodeInfo().getNetwork().split("-");
            long revisionNumber;
            try {
                revisionNumber = Long.parseLong(parts[parts.length - 1]);
            } catch (NumberFormatException e) {
                revisionNumber = 0;
            }

            Query.GetLatestBlockRequest blockRequest = Query.GetLatestBlockRequest.newBuilder().build();
            Query.GetLatestBlockResponse blockResponse = toStub.getLatestBlock(blockRequest);
            long revisionHeight = blockResponse.getBlock().getHeader().getHeight();

            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcIbcTransferReq(WKey.onAuthResponse(mBaseChain, mAccount), mSender, mReceiver, mTokenDenom, mTokenAmount, mAssetPath, revisionNumber, revisionHeight, mFees, "", WKey.getECKey(mApp, mAccount), mChainId, mAccount.customPath, mBaseChain);
            ServiceOuterClass.BroadcastTxResponse response = txService.broadcastTx(broadcastTxRequest);
            mResult.resultData = response.getTxResponse().getTxhash();
            if (response.getTxResponse().getCode() > 0) {
                mResult.errorCode = response.getTxResponse().getCode();
                mResult.errorMsg = response.getTxResponse().getRawLog();
                mResult.isSuccess = false;
            } else {
                mResult.isSuccess = true;
            }

        } catch (Exception e) {
            WLog.e( "IBCTransferGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}
