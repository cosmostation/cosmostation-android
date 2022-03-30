package wannabit.io.cosmostaion.task.UserTask;

import java.util.ArrayList;

import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;

public class GenerateEmptyAccountTask extends CommonTask {

    private ArrayList<BaseChain> mHideChains = new ArrayList<>();

    public GenerateEmptyAccountTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
        this.mResult.taskType = BaseConstant.TASK_INIT_EMPTY_ACCOUNT;
    }

    /**
     * @param strings strings[0] : chainType
     *                strings[1] : address
     * @return
     */
    @Override
    protected TaskResult doInBackground(String... strings) {

        long id = mApp.getBaseDao().onInsertAccount(onGenEmptyAccount(strings[0], strings[1]));
        if (id > 0) {
            mResult.isSuccess = true;
            mHideChains = mApp.getBaseDao().userHideChains();
            if (mHideChains.contains(BaseChain.getChain(strings[0]))) {
                int position = mHideChains.indexOf(BaseChain.getChain(strings[0]));
                if (position >= 0) {
                    mHideChains.remove(position);
                }
                mApp.getBaseDao().setUserHidenChains(mHideChains);
            }
            mApp.getBaseDao().setLastUser(id);
            mApp.getBaseDao().setLastChain(strings[0]);
        } else {
            mResult.errorMsg = "Already existed account";
            mResult.errorCode = 7001;
        }
        return mResult;
    }

    private Account onGenEmptyAccount(String chainType, String address) {
        Account newAccount = Account.getNewInstance();
        newAccount.address = address.toLowerCase();
        newAccount.baseChain = chainType;
        newAccount.hasPrivateKey = false;
        newAccount.fromMnemonic = false;
        newAccount.importTime = System.currentTimeMillis();

        return newAccount;
    }
}