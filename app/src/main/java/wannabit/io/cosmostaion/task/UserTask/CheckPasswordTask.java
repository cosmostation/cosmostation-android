package wannabit.io.cosmostaion.task.UserTask;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Password;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;

public class CheckPasswordTask extends CommonTask {

    public CheckPasswordTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
        this.result.taskType = BaseConstant.TASK_PASSWORD_CHECK;
    }

    /**
     * @param strings strings[0] : password
     * @return
     */
    @Override
    protected TaskResult doInBackground(String... strings) {
        Password checkPw = context.getBaseDao().onSelectPassword();
        if (!CryptoHelper.verifyData(strings[0], checkPw.resource, context.getString(R.string.key_password))) {
            result.isSuccess = false;
            result.errorCode = BaseConstant.ERROR_CODE_INVALID_PASSWORD;
            return result;
        } else {
            result.isSuccess = true;
        }
//        WLog.w("CheckPasswordTask : " + mResult.isSuccess);
        return result;
    }
}
