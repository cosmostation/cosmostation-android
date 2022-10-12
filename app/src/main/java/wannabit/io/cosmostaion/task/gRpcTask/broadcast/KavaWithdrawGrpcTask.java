package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

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

public class KavaWithdrawGrpcTask extends CommonTask {

    private Account                 mAccount;
    private BaseChain               mBaseChain;
    private String                  mFrom;
    private String                  mShares;
    private Coin                    mMinTokenA, mMinTokenB;
    private long                    mDeadline;
    private String                  mMemo;
    private Fee                     mFees;
    private String                  mChainId;

    public KavaWithdrawGrpcTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, String from, String shares,
                                Coin minTokenA, Coin minTokenB, String memo, Fee fee, String chainId) {
        super(app, listener);
        this.mAccount = account;
        this.mBaseChain = basechain;
        this.mFrom = from;
        this.mShares = shares;
        this.mMinTokenA = minTokenA;
        this.mMinTokenB = minTokenB;
        this.mDeadline = (System.currentTimeMillis() / 1000) + 300;
        this.mMemo = memo;
        this.mFees = fee;
        this.mChainId = chainId;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.BroadcastTxRequest broadcastTxRequest = Signer.getGrpcKavaWithdrawReq(WKey.onAuthResponse(mBaseChain, mAccount), mFrom, mShares, mMinTokenA, mMinTokenB, mDeadline, mFees, mMemo, WKey.getECKey(mApp, mAccount), mChainId);
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
            WLog.e( "KavaWithdrawGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}
