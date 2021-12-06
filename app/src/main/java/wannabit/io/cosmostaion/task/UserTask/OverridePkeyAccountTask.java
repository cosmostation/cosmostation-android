package wannabit.io.cosmostaion.task.UserTask;

import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.crypto.EncResult;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;

public class OverridePkeyAccountTask extends CommonTask {
    private BaseChain   mBaseChain;
    private String      mPKey;
    private Account     mAccount;

    public OverridePkeyAccountTask(BaseApplication app, BaseChain chain, TaskListener listener, String pKey, Account account) {
        super(app, listener);
        this.mBaseChain = chain;
        this.mPKey = pKey;
        this.mAccount = account;
        this.mResult.taskType = BaseConstant.TASK_OVERRIDE_ACCOUNT;
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
        EncResult encR          = CryptoHelper.doEncryptData(mPKey, mAccount.getPrivateKeySha1(), false);

        mAccount.address         = WKey.getDpAddress(BaseChain.getChain(mAccount.baseChain), mPKey);
        mAccount.hasPrivateKey   = true;
        mAccount.resource        = encR.getEncDataString();
        mAccount.spec            = encR.getIvDataString();
        mAccount.fromMnemonic    = false;
        mAccount.path            = "-1";
        mAccount.msize           = -1;
        mAccount.newBip44        = false;
        mAccount.customPath      = -1;
        return mAccount;
    }
}
