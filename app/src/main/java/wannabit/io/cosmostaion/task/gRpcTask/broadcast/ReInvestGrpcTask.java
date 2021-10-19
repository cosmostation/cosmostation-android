package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

import org.bitcoinj.crypto.DeterministicKey;

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

import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_INVALID_PASSWORD;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_BROAD_REINVEST;

public class ReInvestGrpcTask extends CommonTask {

    private BaseChain       mBaseChain;
    private Account         mAccount;
    private String          mValidatorAddress;
    private Coin            mReInvestAmount;
    private String          mReInvestMemo;
    private Fee             mReInvestFees;
    private String          mChainId;

    private QueryOuterClass.QueryAccountResponse mAuthResponse;
    private DeterministicKey    deterministicKey;

    public ReInvestGrpcTask(BaseApplication app, TaskListener listener, BaseChain basechain, Account mAccount, String mValidatorAddress, Coin mReInvestAmount, String mReInvestMemo, Fee mReInvestFees, String chainId) {
        super(app, listener);
        this.mBaseChain = basechain;
        this.mAccount = mAccount;
        this.mValidatorAddress = mValidatorAddress;
        this.mReInvestAmount = mReInvestAmount;
        this.mReInvestMemo = mReInvestMemo;
        this.mReInvestFees = mReInvestFees;
        this.mChainId = chainId;
        this.mResult.taskType = TASK_GRPC_BROAD_REINVEST;
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
            String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
            deterministicKey = WKey.getKeyWithPathfromEntropy(getChain(mAccount.baseChain), entropy, Integer.parseInt(mAccount.path), mAccount.newBip44, mAccount.customPath);

            QueryGrpc.QueryBlockingStub authStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            QueryOuterClass.QueryAccountRequest request = QueryOuterClass.QueryAccountRequest.newBuilder().setAddress(mAccount.address).build();
            mAuthResponse = authStub.account(request);

            //broadCast
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcReInvestReq(mAuthResponse, mValidatorAddress, mReInvestAmount, mReInvestFees, mReInvestMemo, deterministicKey, mChainId);
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
            WLog.e( "ReInvestGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}
