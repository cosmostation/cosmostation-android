package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.model.hdac.HdacUtxo;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_HDAC_UTXO;

public class HdacUtxoTask extends CommonTask {

    private String hdacAddress;

    public HdacUtxoTask(BaseApplication app, TaskListener listener, String address) {
        super(app, listener);
        this.hdacAddress = address;
        this.mResult.taskType = TASK_HDAC_UTXO;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<HdacUtxo>> response = ApiClient.getHdac(mApp).getUtxo(hdacAddress).execute();
            if (response.isSuccessful()) {
                mResult.isSuccess = true;
            }
        } catch (Exception e) {
            WLog.w("HdacUtxoTask Error " + e.getMessage());
        }
        return mResult;
    }
}
