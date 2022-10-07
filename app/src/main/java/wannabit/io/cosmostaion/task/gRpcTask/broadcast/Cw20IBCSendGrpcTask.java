package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

import java.util.ArrayList;

import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.AssetPath;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

public class Cw20IBCSendGrpcTask extends CommonTask {
    private BaseChain       mBaseChain;
    private Account         mAccount;
    private String          mSender;
    private String          mRemoteAddress;
    private String          mContractAddress;
    private AssetPath       mAssetPath;
    private ArrayList<Coin> mAmount;
    private String          mMemo;
    private Fee             mFees;
    private String          mChainId;

    public Cw20IBCSendGrpcTask(BaseApplication app, TaskListener listener, Account mAccount, BaseChain basechain, String sender, String remoteAddress, String contractAddress, AssetPath assetPath, ArrayList<Coin> amount, String memo, Fee mFees, String chainId) {
        super(app, listener);
        this.mAccount = mAccount;
        this.mBaseChain = basechain;
        this.mSender = sender;
        this.mRemoteAddress = remoteAddress;
        this.mContractAddress = contractAddress;
        this.mAssetPath = assetPath;
        this.mAmount = amount;
        this.mMemo = memo;
        this.mFees = mFees;
        this.mChainId = chainId;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcCw20IbcTransferReq(WKey.onAuthResponse(mBaseChain, mAccount), mSender, mRemoteAddress, mContractAddress, mAssetPath, mAmount, mFees, mMemo, WKey.getECKey(mApp, mAccount), mChainId);
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
            WLog.e( "Cw20IBCSendGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}
