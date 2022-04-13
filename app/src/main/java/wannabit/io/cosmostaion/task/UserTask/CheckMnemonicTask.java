package wannabit.io.cosmostaion.task.UserTask;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Password;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;

public class CheckMnemonicTask extends CommonTask {

    private final Account mAccount;

    public CheckMnemonicTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.result.taskType = BaseConstant.TASK_CHECK_MNEMONIC;
        this.mAccount = account;
    }

    /**
     * @param strings strings[0] : password
     * @return
     */
    @Override
    protected TaskResult doInBackground(String... strings) {
        Password checkPw = context.getBaseDao().getPassword();
        if (!CryptoHelper.verifyData(strings[0], checkPw.resource, context.getString(R.string.key_password))) {
            result.isSuccess = false;
            result.errorCode = BaseConstant.ERROR_CODE_INVALID_PASSWORD;
            return result;
        } else {
            String entropy = CryptoHelper.doDecryptData(context.getString(R.string.key_mnemonic) + mAccount.uuid, mAccount.resource, mAccount.spec);
            result.resultData = entropy;
            result.isSuccess = true;
        }
        return result;
    }
}
