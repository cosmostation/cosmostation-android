package wannabit.io.cosmostaion.task.UserTask;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.crypto.EncResult;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Derive;
import wannabit.io.cosmostaion.dao.MWords;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;

public class OverrideAccountTask extends CommonTask {
    private MWords mWords;
    private Derive derive;

    private Account     mAccount;

    public OverrideAccountTask(BaseApplication app, Account account, TaskListener listener, MWords mWords, Derive derive) {
        super(app, listener);
        this.mAccount = account;
        this.mWords = mWords;
        this.derive = derive;
        this.mResult.taskType = BaseConstant.TASK_OVERRIDE_ACCOUNT;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Account oAccount = onModAccount(mAccount, strings[0]);
            long id = mApp.getBaseDao().onOverrideAccount(oAccount);
            if (id > 0) {
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

    private Account onModAccount(Account account, String entropy) {
        EncResult encR          = CryptoHelper.doEncryptData(mApp.getString(R.string.key_mnemonic)+ account.uuid, entropy, false);

        account.address         = derive.dpAddress;
        account.hasPrivateKey   = true;
        account.resource        = encR.getEncDataString();
        account.spec            = encR.getIvDataString();
        account.fromMnemonic    = true;
        account.path            = String.valueOf(derive.path);
        account.msize           = mWords.wordsCnt;
        account.customPath      = derive.hdpathtype;
        account.mnemonicId      = mWords.id;
        return account;
    }
}

