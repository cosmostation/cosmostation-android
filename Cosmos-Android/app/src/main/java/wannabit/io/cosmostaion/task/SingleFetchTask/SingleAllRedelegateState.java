package wannabit.io.cosmostaion.task.SingleFetchTask;

import org.json.JSONObject;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdRedelegate;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class SingleAllRedelegateState extends CommonTask {

    private Account mAccount;
    private String  mFromAddress;
    private String  mToAddress;

    public SingleAllRedelegateState(BaseApplication app, TaskListener listener, Account mAccount, String mFromAddress, String mToAddress) {
        super(app, listener);
        this.mAccount = mAccount;
        this.mFromAddress = mFromAddress;
        this.mToAddress = mToAddress;
        this.mResult.taskType   = BaseConstant.TASK_FETCH_SINGLE_ALL_REDELEGATE;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<ResLcdRedelegate>> response = ApiClient.getCosmosChain(mApp).getRedelegateAllHistory(mAccount.address, mFromAddress, mToAddress).execute();
            if(response.isSuccessful()) {
                if(response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                } else {
                    mResult.isSuccess = true;
                }
            } else {
                try {
                    JSONObject jObjError = new JSONObject(response.errorBody().string());
                    if(jObjError.toString().contains("no redelegation found")) {
                        mResult.isSuccess = true;
                    }
                } catch (Exception e) {
                    if(BaseConstant.IS_SHOWLOG) e.printStackTrace();
                }
            }

        } catch (Exception e) {
            WLog.w("SingleAllRedelegateState Error " + e.getMessage());
            if(BaseConstant.IS_SHOWLOG) e.printStackTrace();
        }
        return mResult;
    }

}