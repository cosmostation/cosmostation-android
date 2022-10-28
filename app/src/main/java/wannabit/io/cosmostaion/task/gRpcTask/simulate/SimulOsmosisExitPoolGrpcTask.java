package wannabit.io.cosmostaion.task.gRpcTask.simulate;

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

public class SimulOsmosisExitPoolGrpcTask extends CommonTask {

    private Account             mAccount;
    private BaseChain           mBaseChain;
    private long                mPoolId;
    private String              mShareAmount, mMemo;
    private Coin                mWithdraw0Coin, mWithdraw1Coin;
    private Fee                 mFees;
    private String              mChainId;

    public SimulOsmosisExitPoolGrpcTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, long poolId, Coin Withdraw0Coin, Coin Withdraw1Coin, String shareAmount, String memo, Fee fee, String chainId) {
        super(app, listener);
        this.mAccount = account;
        this.mBaseChain = basechain;
        this.mPoolId = poolId;
        this.mWithdraw0Coin = Withdraw0Coin;
        this.mWithdraw1Coin = Withdraw1Coin;
        this.mShareAmount = shareAmount;
        this.mFees = fee;
        this.mMemo = memo;
        this.mChainId = chainId;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.SimulateRequest simulateTxRequest = Signer.getGrpcExitPoolSimulateReq(WKey.onAuthResponse(mBaseChain, mAccount), mPoolId, mWithdraw0Coin, mWithdraw1Coin, mShareAmount, mFees, mMemo, WKey.getECKey(mApp, mAccount), mChainId);
            ServiceOuterClass.SimulateResponse response = txService.simulate(simulateTxRequest);
            mResult.resultData = response.getGasInfo();
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("SimulOsmosisExitPoolGrpcTask " + e.getMessage());
            mResult.isSuccess = false;
            mResult.errorMsg = e.getMessage();
        }
        return mResult;
    }
}

