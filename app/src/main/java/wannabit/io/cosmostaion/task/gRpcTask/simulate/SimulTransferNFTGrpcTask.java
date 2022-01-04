package wannabit.io.cosmostaion.task.gRpcTask.simulate;

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
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_SIMULATE_TRANSFER_NFT;

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

    private QueryOuterClass.QueryAccountResponse    mAuthResponse;
    private ECKey ecKey;

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
        this.mResult.taskType = TASK_GRPC_SIMULATE_TRANSFER_NFT;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mAccount.fromMnemonic) {
                String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
                DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(getChain(mAccount.baseChain), entropy, Integer.parseInt(mAccount.path), mAccount.newBip44, mAccount.customPath);
                ecKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
            } else {
                String privateKey = CryptoHelper.doDecryptData(mApp.getString(R.string.key_private) + mAccount.uuid, mAccount.resource, mAccount.spec);
                ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
            }

            //simulate
            QueryGrpc.QueryBlockingStub authStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            QueryOuterClass.QueryAccountRequest request = QueryOuterClass.QueryAccountRequest.newBuilder().setAddress(mAccount.address).build();
            mAuthResponse = authStub.account(request);

            ServiceOuterClass.SimulateRequest simulateTxRequest = null;
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            if (mBaseChain.equals(IRIS_MAIN)) {
                simulateTxRequest = Signer.getGrpcSendNftIrisSimulateReq(mAuthResponse, mAccount.address, mRecipient, mDenomId, mId, mIrisResponse, mFees, mMemo, ecKey, mChainId);
            } else if (mBaseChain.equals(CRYPTO_MAIN)) {
                simulateTxRequest = Signer.getGrpcSendNftCroSimulateReq(mAuthResponse,mAccount.address, mRecipient, mDenomId,mId, mFees, mMemo, ecKey, mChainId);
            }
            if (simulateTxRequest != null) {
                ServiceOuterClass.SimulateResponse response = txService.simulate(simulateTxRequest);
                mResult.resultData = response.getGasInfo();
                mResult.isSuccess = true;
            }

        } catch (Exception e) {
            WLog.e("SimulTransferNFTGrpcTask " + e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }

}
