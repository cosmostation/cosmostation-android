package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_INVALID_PASSWORD;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_BROAD_REWARD_ADDRESS_CHANGE;

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

public class ChangeRewardAddressGrpcTask extends CommonTask {
    private final BaseChain mBaseChain;
    private final Account mAccount;
    private final String mToRewardAddress;
    private final String mMemo;
    private final Fee mFees;
    private final String mChainId;

    private QueryOuterClass.QueryAccountResponse mAuthResponse;
    private ECKey ecKey;

    public ChangeRewardAddressGrpcTask(BaseApplication app, TaskListener listener, BaseChain basechain, Account mAccount, String mToRewardAddress, String mMemo, Fee mFees, String chainId) {
        super(app, listener);
        this.mBaseChain = basechain;
        this.mAccount = mAccount;
        this.mToRewardAddress = mToRewardAddress;
        this.mMemo = mMemo;
        this.mFees = mFees;
        this.mChainId = chainId;
        this.result.taskType = TASK_GRPC_BROAD_REWARD_ADDRESS_CHANGE;
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
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcRewardAddressChangeReq(mAuthResponse, mToRewardAddress, mFees, mMemo, ecKey, mChainId);
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
            WLog.e("ClaimRewardsGrpcTask " + e.getMessage());
            result.isSuccess = false;
        }
        return result;
    }
}
