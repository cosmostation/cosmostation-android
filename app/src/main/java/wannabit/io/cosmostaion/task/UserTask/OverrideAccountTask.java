package wannabit.io.cosmostaion.task.UserTask;

import com.github.orogvany.bip32.wallet.HdAddress;

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
import wannabit.io.cosmostaion.utils.WLog;

public class OverrideAccountTask extends CommonTask {
    private BaseChain mBaseChain;
    private Account mAccount;
    private Boolean mKavaNewPath;
    private int mCustomPath;

    public OverrideAccountTask(BaseApplication app, BaseChain chain, Account account, TaskListener listener, boolean bip44, int customPath) {
        super(app, listener);
        this.mBaseChain = chain;
        this.mAccount = account;
        this.mKavaNewPath = bip44;
        this.mCustomPath = customPath;
        this.mResult.taskType = BaseConstant.TASK_OVERRIDE_ACCOUNT;
    }

    /**
     *
     * @param strings
     *  strings[0] : path
     *  strings[1] : entorpy
     *  strings[2] : word size
     *
     * @return
     */
    @Override
    protected TaskResult doInBackground(String... strings) {
        try {

            Account oAccount = onModAccount(mAccount, strings[1], strings[0], strings[02]);
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

    private Account onModAccount(Account account, String entropy, String path, String msize) {
        DeterministicKey dKey   = WKey.getKeyWithPathfromEntropy(BaseChain.getChain(mAccount.baseChain), entropy, Integer.parseInt(path), mKavaNewPath, mCustomPath);
        EncResult encR          = CryptoHelper.doEncryptData(mApp.getString(R.string.key_mnemonic)+ account.uuid, entropy, false);
        account.address         = WKey.getDpAddress(BaseChain.getChain(account.baseChain), dKey.getPublicKeyAsHex());
        account.hasPrivateKey   = true;
        account.resource        = encR.getEncDataString();
        account.spec            = encR.getIvDataString();
        account.fromMnemonic    = true;
        account.path            = path;
        account.msize           = Integer.parseInt(msize);
        account.newBip44        = mKavaNewPath;
        account.customPath      = mCustomPath;
        return account;
    }
}

