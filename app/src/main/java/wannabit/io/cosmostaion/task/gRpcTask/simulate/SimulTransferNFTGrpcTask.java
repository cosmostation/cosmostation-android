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

public class SimulTransferNFTGrpcTask extends CommonTask {

    private Account                                      mAccount;
    private BaseChain                                    mBaseChain;
    private String                                       mRecipient;
    private String                                       mDenomId;
    private String                                       mId;
    private irismod.nft.QueryOuterClass.QueryNFTResponse mIrisResponse;
    private Fee                                          mFees;
    private String                                       mMemo;
    private String                                       mChainId;

    public SimulTransferNFTGrpcTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, String sender, String recipient, String denomId, String id,
                                    irismod.nft.QueryOuterClass.QueryNFTResponse irisResponse, String memo, Fee fee, String chainId) {
        super(app, listener);
        this.mAccount = account;
        this.mBaseChain = basechain;
        this.mAccount.address = sender;
        this.mRecipient = recipient;
        this.mDenomId = denomId;
        this.mId = id;
        this.mIrisResponse = irisResponse;
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
                simulateTxRequest = Signer.getGrpcSendNftIrisSimulateReq(WKey.onAuthResponse(mBaseChain, mAccount), mAccount.address, mRecipient, mDenomId, mId, mIrisResponse, mFees, mMemo, WKey.getECKey(mApp, mAccount), mChainId);
            } else if (mBaseChain.equals(CRYPTO_MAIN)) {
                simulateTxRequest = Signer.getGrpcSendNftCroSimulateReq(WKey.onAuthResponse(mBaseChain, mAccount),mAccount.address, mRecipient, mDenomId,mId, mFees, mMemo, WKey.getECKey(mApp, mAccount), mChainId);
            }
            if (simulateTxRequest != null) {
                ServiceOuterClass.SimulateResponse response = txService.simulate(simulateTxRequest);
                mResult.resultData = response.getGasInfo();
                mResult.isSuccess = true;
            }

        } catch (Exception e) {
            WLog.e("SimulTransferNFTGrpcTask " + e.getMessage());
            mResult.isSuccess = false;
            mResult.errorMsg = e.getMessage();
        }
        return mResult;
    }

}
