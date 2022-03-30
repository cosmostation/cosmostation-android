package wannabit.io.cosmostaion.task.gRpcTask.simulate;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_SIMULATE_IBC_TRANSFER;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import cosmos.auth.v1beta1.QueryGrpc;
import cosmos.auth.v1beta1.QueryOuterClass;
import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import ibc.lightclients.tendermint.v1.Tendermint;
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

public class SimulIBCTransferGrpcTask extends CommonTask {

    private Account mAccount;
    private BaseChain mBaseChain;
    private String mSender;
    private String mReceiver;
    private String mTokenDenom, mTokenAmount;
    private String mPortId, mChannelId;
    private Fee mFees;
    private String mChainId;

    private QueryOuterClass.QueryAccountResponse mAuthResponse;
    private ECKey ecKey;
    private ibc.core.channel.v1.QueryGrpc.QueryBlockingStub mStub;

    public SimulIBCTransferGrpcTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, String sender, String recevier, String tokenDenom, String tokenAmount,
                                    String portId, String channelId, Fee fee, String chainId) {
        super(app, listener);
        this.mAccount = account;
        this.mBaseChain = basechain;
        this.mSender = sender;
        this.mReceiver = recevier;
        this.mTokenDenom = tokenDenom;
        this.mTokenAmount = tokenAmount;
        this.mPortId = portId;
        this.mChannelId = channelId;
        ;
        this.mFees = fee;
        this.mChainId = chainId;
        this.mResult.taskType = TASK_GRPC_SIMULATE_IBC_TRANSFER;
        this.mStub = ibc.core.channel.v1.QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest req = ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateRequest.newBuilder().setChannelId(mChannelId).setPortId(mPortId).build();
            ibc.core.channel.v1.QueryOuterClass.QueryChannelClientStateResponse res = mStub.channelClientState(req);
            Tendermint.ClientState value = Tendermint.ClientState.parseFrom(res.getIdentifiedClientState().getClientState().getValue());

            if (mAccount.fromMnemonic) {
                String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
                DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(mAccount, entropy);
                ecKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
            } else {
                String privateKey = CryptoHelper.doDecryptData(mApp.getString(R.string.key_private) + mAccount.uuid, mAccount.resource, mAccount.spec);
                ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
            }

            //simulate
            QueryGrpc.QueryBlockingStub authStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            QueryOuterClass.QueryAccountRequest request = QueryOuterClass.QueryAccountRequest.newBuilder().setAddress(mAccount.address).build();
            mAuthResponse = authStub.account(request);

            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.SimulateRequest simulateTxRequest = Signer.getGrpcIbcTransferSimulateReq(mAuthResponse, mSender, mReceiver, mTokenDenom, mTokenAmount, mPortId, mChannelId, value.getLatestHeight(), mFees, "", ecKey, mChainId);
            ServiceOuterClass.SimulateResponse response = txService.simulate(simulateTxRequest);
            mResult.resultData = response.getGasInfo();
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("SimulIBCTransferGrpcTask " + e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }

}
