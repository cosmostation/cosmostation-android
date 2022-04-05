package wannabit.io.cosmostaion.task.FetchTask;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_HDAC_UTXO;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.model.hdac.HdacUtxo;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class HdacUtxoTask extends CommonTask {

    private final BaseChain baseChain;
    private final String hdacAddress;

    public HdacUtxoTask(BaseApplication app, TaskListener listener, BaseChain baseChain, String hdacAddress) {
        super(app, listener);
        this.baseChain = baseChain;
        this.hdacAddress = hdacAddress;
        this.result.taskType = TASK_HDAC_UTXO;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<HdacUtxo>> response;
            if (baseChain.equals(BaseChain.RIZON_TEST)) {
                response = ApiClient.getTestHdac(context).getUtxo(hdacAddress).execute();
            } else {
                response = ApiClient.getMainHdac(context).getUtxo(hdacAddress).execute();
            }
            if (response.isSuccessful()) {
                result.resultData = response.body();
                result.isSuccess = true;
            }
        } catch (Exception e) {
            WLog.w("HdacUtxoTask Error " + e.getMessage());
        }
        return result;
    }
}
