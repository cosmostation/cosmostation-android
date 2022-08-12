package wannabit.io.cosmostaion.task.gRpcTask.simulate;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_SIMULATE_COMPUNDING;

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
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

public class SimulCompoundingGrpcTask extends CommonTask {

    private BaseChain   mBaseChain;
    private Account     mAccount;
    private String      mValidatorAddress;
    private Coin        mCompoundingAmount;
    private String      mCompoundingMemo;
    private Fee         mCompoundingFees;
    private String      mChainId;

    private QueryOuterClass.QueryAccountResponse mAuthResponse;
    private ECKey ecKey;

    public SimulCompoundingGrpcTask(BaseApplication app, TaskListener listener, BaseChain basechain, Account mAccount, String mValidatorAddress, Coin mCompoundingAmount, String mCompoundingMemo, Fee mCompoundingFees, String chainId) {
        super(app, listener);
        this.mBaseChain = basechain;
        this.mAccount = mAccount;
        this.mValidatorAddress = mValidatorAddress;
        this.mCompoundingAmount = mCompoundingAmount;
        this.mCompoundingMemo = mCompoundingMemo;
        this.mCompoundingFees = mCompoundingFees;
        this.mChainId = chainId;
        this.mResult.taskType   = TASK_GRPC_SIMULATE_COMPUNDING;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
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

            //Simulate
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.SimulateRequest simulateTxRequest = Signer.getGrpcCompoundingSimulateReq(mAuthResponse, mValidatorAddress, mCompoundingAmount, mCompoundingFees, mCompoundingMemo, ecKey, mChainId);
            ServiceOuterClass.SimulateResponse response = txService.simulate(simulateTxRequest);
//            WLog.w("response " +  response);
            mResult.resultData = response.getGasInfo();
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e( "SimulCompoundingGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}