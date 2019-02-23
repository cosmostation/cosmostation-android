package wannabit.io.cosmostaion.task.UserTask;

import org.bitcoinj.core.ECKey;
import org.bitcoinj.crypto.DeterministicKey;

import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;

public class GenerateEmptyAccountTask extends CommonTask {

    public GenerateEmptyAccountTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
        this.mResult.taskType = BaseConstant.TASK_INIT_EMPTY_ACCOUNT;
    }

    /**
     *
     * @param strings
     *  strings[0] : chainType
     *  strings[1] : address
     * @return
     */
    @Override
    protected TaskResult doInBackground(String... strings) {

        long id = mApp.getBaseDao().onInsertAccount(onGenEmptyAccount(strings[0], strings[1]));
        if(id > 0) {
            mResult.isSuccess = true;
            mApp.getBaseDao().setLastUser(id);
        } else {
            mResult.errorMsg = "Already existed account";
            mResult.errorCode = 7001;
        }

        return mResult;
    }

    private Account onGenEmptyAccount(String chainType, String address) {
        Account newAccount          = Account.getNewInstance();
        newAccount.address          = address;
        newAccount.baseChain        = chainType;
        newAccount.hasPrivateKey    = false;
        newAccount.fromMnemonic     = false;

        return newAccount;
    }
}
