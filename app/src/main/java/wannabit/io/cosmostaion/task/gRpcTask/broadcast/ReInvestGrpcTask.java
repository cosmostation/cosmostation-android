package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_INVALID_PASSWORD;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_BROAD_REINVEST;

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
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

public class ReInvestGrpcTask extends CommonTask {

    private final BaseChain mBaseChain;
    private final Account mAccount;
    private final String mValidatorAddress;
    private final Coin mReInvestAmount;
    private final String mReInvestMemo;
    private final Fee mReInvestFees;
    private final String mChainId;

    private QueryOuterClass.QueryAccountResponse mAuthResponse;
    private ECKey ecKey;

    public ReInvestGrpcTask(BaseApplication app, TaskListener listener, BaseChain basechain, Account mAccount, String mValidatorAddress, Coin mReInvestAmount, String mReInvestMemo, Fee mReInvestFees, String chainId) {
        super(app, listener);
        this.mBaseChain = basechain;
        this.mAccount = mAccount;
        this.mValidatorAddress = mValidatorAddress;
        this.mReInvestAmount = mReInvestAmount;
        this.mReInvestMemo = mReInvestMemo;
        this.mReInvestFees = mReInvestFees;
        this.mChainId = chainId;
        this.result.taskType = TASK_GRPC_BROAD_REINVEST;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        Password checkPw = context.getBaseDao().getPassword();
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
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcReInvestReq(mAuthResponse, mValidatorAddress, mReInvestAmount, mReInvestFees, mReInvestMemo, ecKey, mChainId);
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
            WLog.e("ReInvestGrpcTask " + e.getMessage());
            result.isSuccess = false;
        }
        return result;
    }
}
