package wannabit.io.cosmostaion.task.UserTask;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.crypto.EncResult;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Derive;
import wannabit.io.cosmostaion.dao.MWords;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class GenerateAccountTask extends CommonTask {

    private MWords mWords;
    private Derive derive;
    private ArrayList<BaseChain> mHideChains = new ArrayList<>();

    public GenerateAccountTask(BaseApplication app, TaskListener listener, MWords mWords, Derive derive) {
        super(app, listener);
        this.mWords = mWords;
        this.derive = derive;
        this.mResult.taskType = BaseConstant.TASK_INIT_ACCOUNT;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            long id = mApp.getBaseDao().onInsertAccount(onGenAccount(strings[0]));
            if (id > 0) {
                mResult.isSuccess = true;
                mHideChains = mApp.getBaseDao().userHideChains();
                if (mHideChains.contains(derive.baseChain)) {
                    int position = mHideChains.indexOf(derive.baseChain);
                    if (position >= 0) {
                        mHideChains.remove(position);
                    }
                    mApp.getBaseDao().setUserHidenChains(mHideChains);
                }
                mApp.getBaseDao().setLastUser(id);
                mApp.getBaseDao().setLastChain(derive.baseChain.getChain());

            } else {
                mResult.errorMsg = "Already existed account";
                mResult.errorCode = 7001;
            }

        } catch (Exception e){
            WLog.w("error : " + e.getMessage());
        }
        return mResult;
    }

    private Account onGenAccount(String entropy) {
        Account newAccount          = Account.getNewInstance();
        EncResult encR              = CryptoHelper.doEncryptData(mApp.getString(R.string.key_mnemonic)+ newAccount.uuid, entropy, false);

        newAccount.address          = derive.dpAddress;
        newAccount.baseChain        = derive.baseChain.getChain();
        newAccount.hasPrivateKey    = true;
        newAccount.resource         = encR.getEncDataString();
        newAccount.spec             = encR.getIvDataString();
        newAccount.fromMnemonic     = true;
        newAccount.path             = String.valueOf(derive.path);
        newAccount.msize            = mWords.wordsCnt;
        newAccount.importTime       = System.currentTimeMillis();
        newAccount.customPath       = derive.hdpathtype;
        newAccount.mnemonicId       = mWords.id;
        newAccount.nickName         = mWords.getName() + " - " + String.valueOf(derive.path);
        return newAccount;
    }

}
