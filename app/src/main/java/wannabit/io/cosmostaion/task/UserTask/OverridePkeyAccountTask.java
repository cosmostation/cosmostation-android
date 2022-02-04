package wannabit.io.cosmostaion.task.UserTask;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.crypto.EncResult;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;

public class OverridePkeyAccountTask extends CommonTask {
    private String      mPKey;
    private Account     mAccount;
    private int         mCustomPath;

    public OverridePkeyAccountTask(BaseApplication app, TaskListener listener, String pKey, Account account, int customPath) {
        super(app, listener);
        this.mPKey = pKey;
        this.mAccount = account;
        this.mCustomPath = customPath;
        this.mResult.taskType = BaseConstant.TASK_OVERRIDE_PKEY_ACCOUNT;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {

            Account oAccount = onModAccount();
            long id = mApp.getBaseDao().onOverrideAccount(oAccount);
            if(id > 0) {
                mResult.isSuccess = true;
                mApp.getBaseDao().setLastUser(oAccount.id);

            } else {
                mResult.errorMsg = "Override error";
                mResult.errorCode = 7002;
            }

        } catch (Exception e){

        }
        return mResult;
    }

    private Account onModAccount() {
        if (mPKey.toLowerCase().startsWith("0x")) {
            mPKey = mPKey.substring(2);
        }
        EncResult encR          = CryptoHelper.doEncryptData(mApp.getString(R.string.key_private) + mAccount.uuid, mPKey, false);

        mAccount.hasPrivateKey   = true;
        mAccount.resource        = encR.getEncDataString();
        mAccount.spec            = encR.getIvDataString();
        mAccount.fromMnemonic    = false;
        mAccount.path            = "-1";
        mAccount.msize           = -1;
        mAccount.newBip44        = false;
        mAccount.customPath      = mCustomPath;
        return mAccount;
    }
}
