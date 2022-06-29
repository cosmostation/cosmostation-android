package wannabit.io.cosmostaion.task.UserTask;

import android.annotation.SuppressLint;

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
    private Derive mDerive;
    private String mPKey;
    private boolean mIsPrivateKey;

    private ArrayList<BaseChain> mHideChains = new ArrayList<>();

    public GenerateAccountTask(BaseApplication app, TaskListener listener, MWords mWords, Derive derive, String pKey, boolean isPrivateKey) {
        super(app, listener);
        this.mWords = mWords;
        this.mDerive = derive;
        this.mPKey = pKey;
        this.mIsPrivateKey = isPrivateKey;
        this.mResult.taskType = BaseConstant.TASK_INIT_ACCOUNT;
    }

    @SuppressLint("NewApi")
    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            long id = mApp.getBaseDao().onInsertAccount(onGenAccount());
            if (id > 0) {
                mResult.isSuccess = true;
                mHideChains = mApp.getBaseDao().userHideChains();
                if (mHideChains.contains(mDerive.baseChain)) {
                    int position = mHideChains.indexOf(mHideChains.stream().filter(item -> item.equals(mDerive.baseChain)).findFirst().get());
                    if (position >= 0) {
                        mHideChains.remove(position);
                    }
                    mApp.getBaseDao().setUserHidenChains(mHideChains);
                }

            } else {
                mResult.errorMsg = "Already existed account";
                mResult.errorCode = 7001;
            }

        } catch (Exception e){
            WLog.w("error : " + e.getMessage());
        }
        return mResult;
    }

    private Account onGenAccount() {
        Account newAccount          = Account.getNewInstance();
        EncResult encR;
        if (mIsPrivateKey) {
            if (mPKey.toLowerCase().startsWith("0x")) {
                mPKey = mPKey.substring(2);
            }
            encR = CryptoHelper.doEncryptData(mApp.getString(R.string.key_private) + newAccount.uuid, mPKey, false);
            newAccount.fromMnemonic     = false;

        } else {
            encR = CryptoHelper.doEncryptData(mApp.getString(R.string.key_mnemonic)+ newAccount.uuid, mPKey, false);
            newAccount.fromMnemonic     = true;
            newAccount.msize            = mWords.wordsCnt;
            newAccount.mnemonicId       = mWords.id;
            newAccount.nickName         = mWords.getName() + " - " + String.valueOf(mDerive.path);
        }

        newAccount.path             = String.valueOf(mDerive.path);
        newAccount.customPath       = mDerive.hdpathtype;
        newAccount.address          = mDerive.dpAddress;
        newAccount.baseChain        = mDerive.baseChain.getChain();
        newAccount.hasPrivateKey    = true;
        newAccount.resource         = encR.getEncDataString();
        newAccount.spec             = encR.getIvDataString();
        newAccount.importTime       = System.currentTimeMillis();
        return newAccount;
    }

}
