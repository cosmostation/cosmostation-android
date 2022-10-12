package wannabit.io.cosmostaion.task.gRpcTask.simulate;

import cosmos.tx.v1beta1.ServiceGrpc;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.cosmos.Signer;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Fee;
import wannabit.io.cosmostaion.network.ChannelBuilder;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;

public class SimulCreateProfileGrpcTask extends CommonTask {

    private Account             mAccount;
    private BaseChain           mBaseChain;
    private String              mDtag, mNickname, mBio, mProfileUri, mCoverUri, mMemo;
    private Fee                 mFees;
    private String              mChainId;

    public SimulCreateProfileGrpcTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, String dTag, String nickname, String bio,
                                      String profileUri, String coverUri, String address, String memo, Fee fee, String chainId) {
        super(app, listener);
        this.mAccount = account;
        this.mBaseChain = basechain;
        this.mDtag = dTag;
        this.mNickname = nickname;
        this.mBio = bio;
        this.mProfileUri = profileUri;
        this.mCoverUri = coverUri;
        this.mAccount.address = address;
        this.mFees = fee;
        this.mMemo = memo;
        this.mChainId = chainId;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.SimulateRequest simulateTxRequest = Signer.getGrpcCreateProfileSimulateReq(WKey.onAuthResponse(mBaseChain, mAccount), mDtag, mNickname, mBio, mProfileUri, mCoverUri, mAccount.address, mFees, mMemo, WKey.getECKey(mApp, mAccount), mChainId);
            ServiceOuterClass.SimulateResponse response = txService.simulate(simulateTxRequest);
            mResult.resultData = response.getGasInfo();
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("SimulCreateProfileGrpcTask " + e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}
