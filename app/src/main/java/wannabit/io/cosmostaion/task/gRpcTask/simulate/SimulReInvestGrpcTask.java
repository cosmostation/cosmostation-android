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

public class SimulReInvestGrpcTask extends CommonTask {

    private BaseChain   mBaseChain;
    private Account     mAccount;
    private String      mValidatorAddress;
    private Coin        mReInvestAmount;
    private String      mReInvestMemo;
    private Fee         mReInvestFees;
    private String      mChainId;

    public SimulReInvestGrpcTask(BaseApplication app, TaskListener listener, BaseChain basechain, Account mAccount, String mValidatorAddress, Coin mReInvestAmount, String mReInvestMemo, Fee mReInvestFees, String chainId) {
        super(app, listener);
        this.mBaseChain = basechain;
        this.mAccount = mAccount;
        this.mValidatorAddress = mValidatorAddress;
        this.mReInvestAmount = mReInvestAmount;
        this.mReInvestMemo = mReInvestMemo;
        this.mReInvestFees = mReInvestFees;
        this.mChainId = chainId;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.SimulateRequest simulateTxRequest = Signer.getGrpcReInvestSimulateReq(WKey.onAuthResponse(mBaseChain, mAccount), mValidatorAddress, mReInvestAmount, mReInvestFees, mReInvestMemo, WKey.getECKey(mApp, mAccount), mChainId);
            ServiceOuterClass.SimulateResponse response = txService.simulate(simulateTxRequest);

            mResult.resultData = response.getGasInfo();
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e( "SimulReInvestGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
            mResult.errorMsg = e.getMessage();
        }
        return mResult;
    }
}