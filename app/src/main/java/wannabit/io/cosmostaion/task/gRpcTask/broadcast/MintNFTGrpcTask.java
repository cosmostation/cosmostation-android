package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

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

public class MintNFTGrpcTask extends CommonTask {

    private Account                 mAccount;
    private BaseChain               mBaseChain;
    private String                  mSigner;
    private String                  mDenomId, mDenomName;
    private String                  mId, mName, mUri;
    private String                  mData;
    private Fee                     mFees;
    private String                  mMemo;
    private String                  mChainId;

    public MintNFTGrpcTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, String signer, String denomId, String denomName,
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
        this.mFees = fee;
        this.mMemo = memo;
        this.mChainId = chainId;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = null;
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            if (mBaseChain.equals(IRIS_MAIN)) {
                broadcastTxRequest = Signer.getGrpcCreateNftIrisReq(WKey.onAuthResponse(mBaseChain, mAccount), mSigner, mDenomId, mDenomName, mId, mName, mUri, mData, mFees, mMemo, WKey.getECKey(mApp, mAccount), mChainId);
            } else if (mBaseChain.equals(CRYPTO_MAIN)) {
                broadcastTxRequest = Signer.getGrpcCreateNftCroReq(WKey.onAuthResponse(mBaseChain, mAccount), mSigner, mDenomId, mDenomName, mId, mName, mUri, mData, mFees, mMemo, WKey.getECKey(mApp, mAccount), mChainId);
            }
            if (broadcastTxRequest != null) {
                ServiceOuterClass.BroadcastTxResponse response = txService.broadcastTx(broadcastTxRequest);
                mResult.resultData = response.getTxResponse().getTxhash();
                if (response.getTxResponse().getCode() > 0) {
                    mResult.errorCode = response.getTxResponse().getCode();
                    mResult.errorMsg = response.getTxResponse().getRawLog();
                    mResult.isSuccess = false;
                } else {
                    mResult.isSuccess = true;
                }
            }

        } catch (Exception e) {
            WLog.e( "MintNFTGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}
