package wannabit.io.cosmostaion.task.UserTask;

import org.bitcoinj.crypto.DeterministicKey;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.crypto.EncResult;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;

public class OverrideAccountTask extends CommonTask {

    private Account mAccount;

    public OverrideAccountTask(BaseApplication app, TaskListener listener, Account mAccount) {
        super(app, listener);
        this.mAccount = mAccount;
        this.mResult.taskType = BaseConstant.TASK_OVERRIDE_ACCOUNT;
    }

    /**
     *
     * @param strings
     *  strings[0] : path
     *  strings[1] : mnemonic seed
     *  strings[2] : mnemonic size
     *
     * @return
     */
    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            long id = mApp.getBaseDao().onOverrideAccount(onModAccount(mAccount, strings[1], strings[0], strings[02]));
            if(id > 0) {
                mResult.isSuccess = true;
                mApp.getBaseDao().setLastUser(id);

            } else {
                mResult.errorMsg = "Override error";
                mResult.errorCode = 7002;
            }

        } catch (Exception e){

        }
        return mResult;
    }

    private Account onModAccount(Account account, String seed, String path, String msize) {
        DeterministicKey dKey       = WKey.getKeyWithPath(seed, Integer.parseInt(path));
        EncResult encR              = CryptoHelper.doEncryptData(mApp.getString(R.string.key_mnemonic)+ account.uuid, seed, false);
        account.address             = WKey.getCosmosUserDpAddress(dKey.getPublicKeyAsHex());
        account.hasPrivateKey    = true;
        account.resource         = encR.getEncDataString();
        account.spec             = encR.getIvDataString();
        account.fromMnemonic     = true;
        account.path             = path;
        account.msize            = Integer.parseInt(msize);
        return account;
    }
}

