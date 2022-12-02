package wannabit.io.cosmostaion.task.gRpcTask.broadcast;

import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.methods.response.EthSendTransaction;

import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class Erc20SendGrpcTask extends CommonTask {

    private Web3j           mWeb3j;
    private String          mHexValue;

    public Erc20SendGrpcTask(BaseApplication app, TaskListener listener, Web3j web3j, String hexValue) {
        super(app, listener);
        this.mWeb3j = web3j;
        this.mHexValue = hexValue;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            EthSendTransaction ethSendTransaction = mWeb3j.ethSendRawTransaction(mHexValue).send();
            mResult.resultData = ethSendTransaction.getTransactionHash();
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.e( "Erc20SendGrpcTask "+ e.getMessage());
            mResult.isSuccess = false;
        }
        return mResult;
    }
}
