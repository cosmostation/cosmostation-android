package wannabit.io.cosmostaion.task.UserTask;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Password;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;

public class DeleteUserTask extends CommonTask {

    public DeleteUserTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
        this.mResult.taskType = BaseConstant.TASK_DELETE_USER;
    }

    /**
     *
     * @param strings
     *  strings[0] : password
     *
     * @return
     */
    @Override
    protected TaskResult doInBackground(String... strings) {
        Password checkPw = mApp.getBaseDao().onSelectPassword();
        if (!CryptoHelper.verifyData(strings[0], checkPw.resource, mApp.getString(R.string.key_password))) {;
            mResult.isSuccess = false;
            mResult.errorCode = BaseConstant.ERROR_CODE_INVALID_PASSWORD;
            return mResult;
        } else {
            mResult.isSuccess = true;
        }
//        WLog.w("CheckPasswordTask : " + mResult.isSuccess);
        return mResult;
    }
}
