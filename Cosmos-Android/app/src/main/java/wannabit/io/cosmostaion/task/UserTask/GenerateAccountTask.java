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
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class GenerateAccountTask extends CommonTask {

    public GenerateAccountTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
        this.mResult.taskType = BaseConstant.TASK_INIT_ACCOUNT;
    }


    /**
     *
     * @param strings
     *  strings[0] : chainType
     *  strings[1] : path
     *  strings[2] : entorpy seed
     *  strings[3] : word size
     *
     * @return
     */
    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            long id = mApp.getBaseDao().onInsertAccount(onGenAccount(strings[2], strings[1], strings[0], strings[3]));
            if(id > 0) {
                mResult.isSuccess = true;
                mApp.getBaseDao().setLastUser(id);

            } else {
                mResult.errorMsg = "Already existed account";
                mResult.errorCode = 7001;
            }

        } catch (Exception e){

        }
        return mResult;
    }



    private Account onGenAccount(String entropy, String path, String chainType, String msize) {
        Account             newAccount      = Account.getNewInstance();
        DeterministicKey    dKey            = WKey.getKeyWithPathfromEntropy(entropy, Integer.parseInt(path));
        EncResult           encR            = CryptoHelper.doEncryptData(mApp.getString(R.string.key_mnemonic)+ newAccount.uuid, entropy, false);
//        newAccount.address                  = WKey.getCosmosUserDpAddress(dKey.getPublicKeyAsHex());
        newAccount.address                  = WKey.getDpAddress(chainType, dKey.getPublicKeyAsHex());
        newAccount.baseChain                = chainType;
        newAccount.hasPrivateKey            = true;
        newAccount.resource                 = encR.getEncDataString();
        newAccount.spec                     = encR.getIvDataString();
        newAccount.fromMnemonic             = true;
        newAccount.path                     = path;
        newAccount.msize                    = Integer.parseInt(msize);
        newAccount.importTime               = System.currentTimeMillis();
        return newAccount;
    }

}
