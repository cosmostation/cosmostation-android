package wannabit.io.cosmostaion.task.UserTask;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Password;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;

public class InitPasswordTask extends CommonTask {

    public InitPasswordTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
        this.result.taskType = BaseConstant.TASK_INIT_PW;
    }

    /**
     * @param strings strings[0] : password
     * @return
     */
    @Override
    protected TaskResult doInBackground(String... strings) {
        Password newPw = new Password(CryptoHelper.signData(strings[0], context.getString(R.string.key_password)));
        long insert = context.getBaseDao().onInsertPassword(newPw);
        if (insert > 0) {
            result.isSuccess = true;
        }
        return result;
    }
}
