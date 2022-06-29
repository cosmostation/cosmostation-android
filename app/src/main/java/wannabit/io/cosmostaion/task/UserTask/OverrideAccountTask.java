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

public class OverrideAccountTask extends CommonTask {

    private MWords mWords;
    private Derive mDerive;
    private String mPKey;
    private boolean mIsPrivateKey;

    private ArrayList<BaseChain> mHideChains = new ArrayList<>();

    public OverrideAccountTask(BaseApplication app, TaskListener listener, MWords mWords, Derive derive, String pKey, boolean isPrivateKey) {
        super(app, listener);
        this.mWords = mWords;
        this.mDerive = derive;
        this.mPKey = pKey;
        this.mIsPrivateKey = isPrivateKey;
        this.mResult.taskType = BaseConstant.TASK_OVERRIDE_ACCOUNT;
    }

    @SuppressLint("NewApi")
    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            long id = mApp.getBaseDao().onOverrideAccount(onModAccount());
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
                mResult.errorMsg = "Override error";
                mResult.errorCode = 7002;
            }

        } catch (Exception e){ }
        return mResult;
    }

    private Account onModAccount() {
        EncResult encR;
        Account existAccount = mApp.getBaseDao().onSelectExistAccount(mDerive.dpAddress, mDerive.baseChain);
        if (mIsPrivateKey) {
            if (mPKey.toLowerCase().startsWith("0x")) {
                mPKey = mPKey.substring(2);
            }
            encR = CryptoHelper.doEncryptData(mApp.getString(R.string.key_private) + existAccount.uuid, mPKey, false);
            existAccount.fromMnemonic = false;

        } else {
            encR = CryptoHelper.doEncryptData(mApp.getString(R.string.key_mnemonic)+ existAccount.uuid, mPKey, false);
            existAccount.fromMnemonic = true;
            existAccount.msize        = mWords.wordsCnt;
            existAccount.mnemonicId   = mWords.id;
        }

        existAccount.hasPrivateKey   = true;
        existAccount.resource        = encR.getEncDataString();
        existAccount.spec            = encR.getIvDataString();
        existAccount.path            = String.valueOf(mDerive.path);
        existAccount.customPath      = mDerive.hdpathtype;
        return existAccount;
    }
}

