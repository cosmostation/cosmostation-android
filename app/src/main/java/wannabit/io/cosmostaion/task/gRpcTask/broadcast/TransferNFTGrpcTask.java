package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigInteger;

import cosmos.auth.v1beta1.QueryGrpc;
import cosmos.auth.v1beta1.QueryOuterClass;
import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Password;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_INVALID_PASSWORD;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_GEN_TX_TRANSFER_NFT;

public class TransferNFTGrpcTask extends CommonTask {

    private Account                                      mAccount;
    private BaseChain                                    mBaseChain;
    private String                                       mRecipient;
    private String                                       mDenomId;
    private String                                       mId;
    private irismod.nft.QueryOuterClass.QueryNFTResponse mIrisResponse;
    private Fee                                          mFees;
    private String                                       mMemo;
    private String                                       mChainId;

    private QueryOuterClass.QueryAccountResponse mAuthResponse;
    private ECKey ecKey;

    public TransferNFTGrpcTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, String sender, String recipient, String denomId, String id,
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
        this.mResult.taskType = TASK_GRPC_GEN_TX_TRANSFER_NFT;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        Password checkPw = mApp.getBaseDao().onSelectPassword();
        if (!CryptoHelper.verifyData(strings[0], checkPw.resource, mApp.getString(R.string.key_password))) {
            mResult.isSuccess = false;
            mResult.errorCode = ERROR_CODE_INVALID_PASSWORD;
            return mResult;
        }

        try {
            if (mAccount.fromMnemonic) {
                String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
                DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(mAccount, entropy);
                ecKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
            } else {
                String privateKey = CryptoHelper.doDecryptData(mApp.getString(R.string.key_private) + mAccount.uuid, mAccount.resource, mAccount.spec);
                ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
            }

            QueryGrpc.QueryBlockingStub authStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            QueryOuterClass.QueryAccountRequest request = QueryOuterClass.QueryAccountRequest.newBuilder().setAddress(mAccount.address).build();
            mAuthResponse = authStub.account(request);

            //broadCast
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = null;
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            if (mBaseChain.equals(IRIS_MAIN)) {
                broadcastTxRequest = Signer.getGrpcSendNftIrisReq(mAuthResponse, mAccount.address, mRecipient, mDenomId, mId, mIrisResponse, mFees, mMemo, ecKey, mChainId);
            } else if (mBaseChain.equals(CRYPTO_MAIN)) {
                broadcastTxRequest = Signer.getGrpcSendNftCroReq(mAuthResponse, mAccount.address, mRecipient, mDenomId, mId, mFees, mMemo, ecKey, mChainId);
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
            WLog.e( "TransferNFTGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}
