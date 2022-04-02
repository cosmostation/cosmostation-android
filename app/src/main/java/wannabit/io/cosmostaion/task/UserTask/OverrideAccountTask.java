package wannabit.io.cosmostaion.task.UserTask;

import org.bitcoinj.crypto.DeterministicKey;

import wannabit.io.cosmostaion.R;
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

public class OverrideAccountTask extends CommonTask {
    private Account mAccount;
    private int mCustomPath;

    public OverrideAccountTask(BaseApplication app, Account account, TaskListener listener, int customPath) {
        super(app, listener);
        this.mAccount = account;
        this.mCustomPath = customPath;
        this.result.taskType = BaseConstant.TASK_OVERRIDE_ACCOUNT;
    }

    /**
     * @param strings strings[0] : path
     *                strings[1] : entorpy
     *                strings[2] : word size
     * @return
     */
    @Override
    protected TaskResult doInBackground(String... strings) {
        try {

            Account oAccount = onModAccount(mAccount, strings[1], strings[0], strings[02]);
            long id = context.getBaseDao().onOverrideAccount(oAccount);
            if (id > 0) {
                result.isSuccess = true;
                context.getBaseDao().setLastUser(oAccount.id);

            } else {
                result.errorMsg = "Override error";
                result.errorCode = 7002;
            }

        } catch (Exception e) {

        }
        return result;
    }

    private Account onModAccount(Account account, String entropy, String path, String msize) {
        DeterministicKey dKey = WKey.getCreateKeyWithPathfromEntropy(BaseChain.getChain(mAccount.baseChain), entropy, Integer.parseInt(path), mCustomPath);
        EncResult encR = CryptoHelper.doEncryptData(context.getString(R.string.key_mnemonic) + account.uuid, entropy, false);
        account.address = WKey.getDpAddress(BaseChain.getChain(account.baseChain), dKey.getPublicKeyAsHex());
        account.hasPrivateKey = true;
        account.resource = encR.getEncDataString();
        account.spec = encR.getIvDataString();
        account.fromMnemonic = true;
        account.path = path;
        account.msize = Integer.parseInt(msize);
        account.customPath = mCustomPath;
        return account;
    }
}

