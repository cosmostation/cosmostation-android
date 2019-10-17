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
import wannabit.io.cosmostaion.utils.WUtil;
public class GenerateAccountTask extends CommonTask {
    private BaseChain mBaseChain;

    public GenerateAccountTask(BaseApplication app, BaseChain basechain, TaskListener listener) {
        super(app, listener);
        this.mBaseChain = basechain;
        this.mResult.taskType = BaseConstant.TASK_INIT_ACCOUNT;
    }


    /**
     *
     * @param strings
     *  strings[0] : path
     *  strings[1] : entorpy seed
     *  strings[2] : word size
     *
     * @return
     */
    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            long id = mApp.getBaseDao().onInsertAccount(onGenAccount(strings[1], strings[0], strings[2]));
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



    private Account onGenAccount(String entropy, String path, String msize) {
        Account newAccount      = Account.getNewInstance();
        if (BaseChain.COSMOS_MAIN.equals(mBaseChain) || BaseChain.IRIS_MAIN.equals(mBaseChain) || BaseChain.BNB_MAIN.equals(mBaseChain)) {
            DeterministicKey    dKey            = WKey.getKeyWithPathfromEntropy(mBaseChain, entropy, Integer.parseInt(path));
            EncResult           encR            = CryptoHelper.doEncryptData(mApp.getString(R.string.key_mnemonic)+ newAccount.uuid, entropy, false);
            newAccount.address                  = WKey.getDpAddress(mBaseChain, dKey.getPublicKeyAsHex());
            newAccount.baseChain                = mBaseChain.getChain();
            newAccount.hasPrivateKey            = true;
            newAccount.resource                 = encR.getEncDataString();
            newAccount.spec                     = encR.getIvDataString();
            newAccount.fromMnemonic             = true;
            newAccount.path                     = path;
            newAccount.msize                    = Integer.parseInt(msize);
            newAccount.importTime               = System.currentTimeMillis();

        } else if (BaseChain.IOV_MAIN.equals(mBaseChain)) {
            HdAddress dKey = WKey.getEd25519KeyWithPathfromEntropy(mBaseChain, entropy, Integer.parseInt(path));
            EncResult encR = CryptoHelper.doEncryptData(mApp.getString(R.string.key_mnemonic)+ newAccount.uuid, entropy, false);
            newAccount.address                  = WKey.getIovDpAddress(dKey);
            newAccount.baseChain                = mBaseChain.getChain();
            newAccount.hasPrivateKey            = true;
            newAccount.resource                 = encR.getEncDataString();
            newAccount.spec                     = encR.getIvDataString();
            newAccount.fromMnemonic             = true;
            newAccount.path                     = path;
            newAccount.msize                    = Integer.parseInt(msize);
            newAccount.importTime               = System.currentTimeMillis();
        }
        return newAccount;

    }

}
