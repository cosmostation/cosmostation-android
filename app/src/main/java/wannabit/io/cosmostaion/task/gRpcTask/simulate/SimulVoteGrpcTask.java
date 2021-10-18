package wannabit.io.cosmostaion.task.gRpcTask.simulate;

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
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_SIMULATE_VOTE;

public class SimulVoteGrpcTask extends CommonTask {
    private BaseChain   mBaseChain;
    private Account     mAccount;
    private String      mProposalId;
    private String      mOpinion;
    private String      mMemo;
    private Fee         mFees;
    private String      mChainId;

    private QueryOuterClass.QueryAccountResponse mAuthResponse;
    private DeterministicKey deterministicKey;

    public SimulVoteGrpcTask(BaseApplication app, TaskListener listener, BaseChain basechain, Account account, String proposalId, String opinion, String memo, Fee fee, String chainId) {
        super(app, listener);
        this.mBaseChain         = basechain;
        this.mAccount           = account;
        this.mProposalId        = proposalId;
        this.mOpinion           = opinion;
        this.mMemo              = memo;
        this.mFees              = fee;
        this.mChainId           = chainId;
        this.mResult.taskType   = TASK_GRPC_SIMULATE_VOTE;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
            deterministicKey = WKey.getKeyWithPathfromEntropy(getChain(mAccount.baseChain), entropy, Integer.parseInt(mAccount.path), mAccount.newBip44, mAccount.customPath);

            QueryGrpc.QueryBlockingStub authStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            QueryOuterClass.QueryAccountRequest request = QueryOuterClass.QueryAccountRequest.newBuilder().setAddress(mAccount.address).build();
            mAuthResponse = authStub.account(request);

            //Simulate
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.SimulateRequest simulateTxRequest = Signer.getGrpcVoteSimulateReq(mAuthResponse, mProposalId, mOpinion, mFees, mMemo, deterministicKey, mChainId);
            ServiceOuterClass.SimulateResponse response = txService.simulate(simulateTxRequest);
//            WLog.w("response " +  response);
            mResult.resultData = response.getGasInfo();
            mResult.isSuccess = true;


        } catch (Exception e) {
            WLog.e( "SimulVoteGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}
