package wannabit.io.cosmostaion.task.gRpcTask.simulate;

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


public class SimulCw20IbcSendGrpcTask extends CommonTask {
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

    public SimulCw20IbcSendGrpcTask(BaseApplication app, TaskListener listener, Account mAccount, BaseChain basechain, String sender, String remoteAddress, String contractAddress, AssetPath assetPath, ArrayList<Coin> amount, String mMemo, Fee mFees, String chainId) {
        super(app, listener);
        this.mAccount = mAccount;
        this.mBaseChain = basechain;
        this.mSender = sender;
        this.mRemoteAddress = remoteAddress;
        this.mContractAddress = contractAddress;
        this.mAssetPath = assetPath;
        this.mAmount = amount;
        this.mMemo = mMemo;
        this.mFees = mFees;
        this.mChainId = chainId;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.SimulateRequest simulateTxRequest = Signer.getGrpcCw20IbcTransferSimulateReq(WKey.onAuthResponse(mBaseChain, mAccount), mSender, mRemoteAddress, mContractAddress, mAssetPath, mAmount, mFees, mMemo, WKey.getECKey(mApp, mAccount), mChainId);
            ServiceOuterClass.SimulateResponse response = txService.simulate(simulateTxRequest);

            mResult.resultData = response.getGasInfo();
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e( "SimulCw20IbcSendGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
            mResult.errorMsg = e.getMessage();
        }
        return mResult;
    }

}
