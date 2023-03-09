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

public class SimulPersisLiquidGrpcTask extends CommonTask {

    private Account mAccount;
    private BaseChain mBaseChain;
    private String mDelegatorAddress;
    private Coin mSwapInCoin;
    private String mMemo;
    private Fee mFees;
    private String mChainId;
    private int mTxType;

    public SimulPersisLiquidGrpcTask(BaseApplication app, TaskListener listener, Account account, BaseChain basechain, String delegatorAddress,
                                     Coin swapInCoin, String memo, Fee fee, String chainId, int txType) {
        super(app, listener);
        this.mAccount = account;
        this.mBaseChain = basechain;
        this.mDelegatorAddress = delegatorAddress;
        this.mSwapInCoin = swapInCoin;
        this.mMemo = memo;
        this.mFees = fee;
        this.mChainId = chainId;
        this.mTxType = txType;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            ServiceGrpc.ServiceBlockingStub txService = ServiceGrpc.newBlockingStub(ChannelBuilder.getChain(mBaseChain));
            ServiceOuterClass.SimulateRequest simulateTxRequest = Signer.getGrpcPersisLiquidSimulateReq(WKey.onAuthResponse(mBaseChain, mAccount), mDelegatorAddress, mSwapInCoin, mTxType, mFees, mMemo, WKey.getECKey(mApp, mAccount), mChainId, mAccount.customPath, mBaseChain);;
            ServiceOuterClass.SimulateResponse response = txService.simulate(simulateTxRequest);
            mResult.resultData = response.getGasInfo();
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e("SimulPersisLiquidGrpcTask " + e.getMessage());
            mResult.isSuccess = false;
            mResult.errorMsg = e.getMessage();
        }
        return mResult;
    }
}
