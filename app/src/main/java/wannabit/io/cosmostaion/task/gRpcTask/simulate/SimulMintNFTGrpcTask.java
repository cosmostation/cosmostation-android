package wannabit.io.cosmostaion.task.gRpcTask.simulate;

import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;

import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

public class SimulMintNFTGrpcTask extends CommonTask {

    private Account                 mAccount;
    private BaseChain               mBaseChain;
    private String                  mSigner;
    private String                  mDenomId, mDenomName;
    private String                  mId, mName, mUri;
    private String                  mData;
    private Fee                     mFees;
    private String                  mMemo;
    private String                  mChainId;

    public SimulMintNFTGrpcTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, String signer, String denomId, String denomName,
                                String id, String name, String uri, String data, String memo, Fee fee, String chainId) {
        super(app, listener);
        this.mAccount = account;
        this.mBaseChain = basechain;
        this.mSigner = signer;
        this.mDenomId = denomId;
        this.mDenomName = denomName;
        this.mId = id;
        this.mName = name;
        this.mUri = uri;
        this.mData = data;
        this.mMemo = memo;
        this.mFees = fee;
        this.mChainId = chainId;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ServiceOuterClass.SimulateRequest simulateTxRequest = null;
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            if (mBaseChain.equals(IRIS_MAIN)) {
                simulateTxRequest = Signer.getGrpcCreateNftIrisSimulateReq(WKey.onAuthResponse(mBaseChain, mAccount), mSigner, mDenomId, mDenomName, mId, mName, mUri, mData, mFees, mMemo, WKey.getECKey(mApp, mAccount), mChainId);
            } else if (mBaseChain.equals(CRYPTO_MAIN)) {
                simulateTxRequest = Signer.getGrpcCreateNftCroSimulateReq(WKey.onAuthResponse(mBaseChain, mAccount), mSigner, mDenomId, mDenomName, mId, mName, mUri, mData, mFees, mMemo, WKey.getECKey(mApp, mAccount), mChainId);
            }
            if (simulateTxRequest != null) {
                ServiceOuterClass.SimulateResponse response = txService.simulate(simulateTxRequest);
                mResult.resultData = response.getGasInfo();
                mResult.isSuccess = true;
            }

        } catch (Exception e) {
            WLog.e("SimulMintNFTGrpcTask " + e.getMessage());
            mResult.isSuccess = false;
            mResult.errorMsg = e.getMessage();
        }
        return mResult;
    }

}
