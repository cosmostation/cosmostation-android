package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_INVALID_PASSWORD;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_GEN_TX_SIF_SWAP;

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

public class SifSwapGrpcTask extends CommonTask {

    private Account mAccount;
    private BaseChain mBaseChain;
    private String mSinger;
    private String mInputDenom;
    private String mInputAmount;
    private String mOutputDenom;
    private String mOutputAmount;
    private String mMemo;
    private Fee mFees;
    private String mChainId;

    private QueryOuterClass.QueryAccountResponse mAuthResponse;
    private ECKey ecKey;

    public SifSwapGrpcTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, String signer, String inputDenom, String inputAmount, String outputDenom, String outputAmount, String memo, Fee fee, String chainId) {
        super(app, listener);
        this.mAccount = account;
        this.mBaseChain = basechain;
        this.mSinger = signer;
        this.mInputDenom = inputDenom;
        this.mInputAmount = inputAmount;
        this.mOutputDenom = outputDenom;
        this.mOutputAmount = outputAmount;
        this.mFees = fee;
        this.mMemo = memo;
        this.mChainId = chainId;
        this.result.taskType = TASK_GRPC_GEN_TX_SIF_SWAP;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        Password checkPw = context.getBaseDao().onSelectPassword();
        if (!CryptoHelper.verifyData(strings[0], checkPw.resource, context.getString(R.string.key_password))) {
            result.isSuccess = false;
            result.errorCode = ERROR_CODE_INVALID_PASSWORD;
            return result;
        }

        try {
            if (mAccount.fromMnemonic) {
                String entropy = CryptoHelper.doDecryptData(context.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
                DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(mAccount, entropy);
                ecKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
            } else {
                String privateKey = CryptoHelper.doDecryptData(context.getString(R.string.key_private) + mAccount.uuid, mAccount.resource, mAccount.spec);
                ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
            }

            QueryGrpc.QueryBlockingStub authStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            QueryOuterClass.QueryAccountRequest request = QueryOuterClass.QueryAccountRequest.newBuilder().setAddress(mAccount.address).build();
            mAuthResponse = authStub.account(request);

            //broadCast
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcSifSwapReq(mAuthResponse, mSinger, mInputDenom, mInputAmount, mOutputDenom, mOutputAmount, mFees, mMemo, ecKey, mChainId);
            ServiceOuterClass.BroadcastTxResponse response = txService.broadcastTx(broadcastTxRequest);
            result.resultData = response.getTxResponse().getTxhash();
            if (response.getTxResponse().getCode() > 0) {
                result.errorCode = response.getTxResponse().getCode();
                result.errorMsg = response.getTxResponse().getRawLog();
                result.isSuccess = false;
            } else {
                result.isSuccess = true;
            }

        } catch (Exception e) {
            WLog.e("SifSwapGrpcTask " + e.getMessage());
            result.isSuccess = false;
        }
        return result;
    }
}
