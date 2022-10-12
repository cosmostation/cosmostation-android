package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

import java.util.ArrayList;

import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

public class Cw20SendGrpcTask extends CommonTask {
    private BaseChain       mBaseChain;
    private Account         mAccount;
    private String          mFromAddress;
    private String          mToAddress;
    private String          mContractAddress;
    private ArrayList<Coin> mAmount;
    private String          mMemo;
    private Fee             mFees;
    private String          mChainId;

    public Cw20SendGrpcTask(BaseApplication app, TaskListener listener,  Account mAccount, BaseChain basechain, String fromaddress, String toAddress, String contractAddress, ArrayList<Coin> amount, String mMemo, Fee mFees, String chainId) {
        super(app, listener);
        this.mAccount = mAccount;
        this.mBaseChain = basechain;
        this.mFromAddress = fromaddress;
        this.mToAddress = toAddress;
        this.mContractAddress = contractAddress;
        this.mAmount = amount;
        this.mMemo = mMemo;
        this.mFees = mFees;
        this.mChainId = chainId;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcCw20SendReq(WKey.onAuthResponse(mBaseChain, mAccount), mFromAddress, mToAddress, mContractAddress, mAmount, mFees, mMemo, WKey.getECKey(mApp, mAccount), mChainId);
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
            WLog.e( "Cw20SendGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}
