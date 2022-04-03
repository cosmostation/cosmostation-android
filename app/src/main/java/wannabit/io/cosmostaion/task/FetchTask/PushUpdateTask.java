package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.ReqPushAlarm;
import wannabit.io.cosmostaion.network.res.ResPushAlarm;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;

public class PushUpdateTask extends CommonTask {

    private final Account mAccount;
    private final String mPushToken;
    private final boolean mEnable;

    public PushUpdateTask(BaseApplication app, TaskListener listener, Account account, String token, boolean enable) {
        super(app, listener);
        this.mAccount = account;
        this.mPushToken = token;
        this.mEnable = enable;
        this.result.taskType = BaseConstant.TASK_PUSH_STATUS_UPDATE;
        this.result.resultData = enable;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {

            ReqPushAlarm reqPushAlarm = new ReqPushAlarm();
            if (BaseChain.COSMOS_MAIN.hasChainName(mAccount.baseChain)) {
                reqPushAlarm.chain_id = 1;
            } else if (BaseChain.IRIS_MAIN.hasChainName(mAccount.baseChain)) {
                reqPushAlarm.chain_id = 2;
            } else if (BaseChain.KAVA_MAIN.hasChainName(mAccount.baseChain)) {
                reqPushAlarm.chain_id = 3;
            }
            reqPushAlarm.device_type = "android";
            reqPushAlarm.address = mAccount.address;
            reqPushAlarm.alarm_token = mPushToken;
            reqPushAlarm.alarm_status = mEnable;

            Response<ResPushAlarm> response = ApiClient.getCosmostationOld(context).updateAlarm(reqPushAlarm).execute();
            if (response.isSuccessful() && response.body() != null && response.body().result) {
                result.isSuccess = true;
            }

        } catch (Exception e) {
            if (BuildConfig.DEBUG) e.printStackTrace();

        }
        return result;
    }
}
