package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;

import java.math.BigInteger;
import java.util.concurrent.TimeUnit;

import cosmos.auth.v1beta1.QueryGrpc;
import cosmos.auth.v1beta1.QueryOuterClass;
import cosmos.base.tendermint.v1beta1.Query;
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

import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.ERROR_CODE_INVALID_PASSWORD;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_GEN_TX_KAVA_CREATE_HTLC;
import static wannabit.io.cosmostaion.network.ChannelBuilder.TIME_OUT;

public class KavaClaimHTLCGrpcTask extends CommonTask {

    private Account                 mAccount;
    private BaseChain               mBaseChain;
    private String                  mFrom;
    private String                  mSwapId;
    private String                  mRandomNumber;
    private String                  mMemo;
    private Fee                     mFees;

    private QueryOuterClass.QueryAccountResponse mAuthResponse;
    private ECKey ecKey;
    private cosmos.base.tendermint.v1beta1.ServiceGrpc.ServiceBlockingStub mStub;

    public KavaClaimHTLCGrpcTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, String from, String swapId,
                                  String randomNumber, Fee fee) {
        super(app, listener);
        this.mAccount = account;
        this.mBaseChain = basechain;
        this.mFrom = from;
        this.mSwapId = swapId;
        this.mRandomNumber = randomNumber;
        this.mFees = fee;
        this.mMemo = mApp.getString(R.string.str_create_swap_memo_c);
        this.mResult.taskType = TASK_GRPC_GEN_TX_KAVA_CREATE_HTLC;
        this.mStub = cosmos.base.tendermint.v1beta1.ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain)).withDeadlineAfter(TIME_OUT, TimeUnit.SECONDS);

    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Query.GetNodeInfoRequest receiveNodeInfo = Query.GetNodeInfoRequest.newBuilder().build();
            Query.GetNodeInfoResponse receiveInfo = mStub.getNodeInfo(receiveNodeInfo);

            if (mAccount.fromMnemonic) {
                String entropy = CryptoHelper.doDecryptData(mApp.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
                DeterministicKey deterministicKey = WKey.getKeyWithPathfromEntropy(getChain(mAccount.baseChain), entropy, Integer.parseInt(mAccount.path), mAccount.newBip44, mAccount.customPath);
                ecKey = ECKey.fromPrivate(new BigInteger(deterministicKey.getPrivateKeyAsHex(), 16));
            } else {
                String privateKey = CryptoHelper.doDecryptData(mApp.getString(R.string.key_private) + mAccount.uuid, mAccount.resource, mAccount.spec);
                ecKey = ECKey.fromPrivate(new BigInteger(privateKey, 16));
            }

            QueryGrpc.QueryBlockingStub authStub = QueryGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            QueryOuterClass.QueryAccountRequest request = QueryOuterClass.QueryAccountRequest.newBuilder().setAddress(mAccount.address).build();
            mAuthResponse = authStub.account(request);

            //broadCast
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcKavaClaimHTLCSwapReq(mAuthResponse, mFrom, mSwapId, mRandomNumber, mFees, mMemo, ecKey, receiveInfo.getNodeInfo().getNetwork());
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
            WLog.e( "KavaClaimHTLCGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}
