package wannabit.io.cosmostaion.task.UserTask;

import java.util.ArrayList;

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

public class GeneratePkeyAccountTask extends CommonTask {
    private BaseChain mBaseChain;
    private String mPKey;
    private String mAddress;
    private int mCustomPath;

    private ArrayList<BaseChain> mHideChains = new ArrayList<>();

    public GeneratePkeyAccountTask(BaseApplication app, BaseChain basechain, TaskListener listener, String pKey, String address, int customPath) {
        super(app, listener);
        this.mBaseChain = basechain;
        this.mPKey = pKey;
        this.mAddress = address;
        this.mCustomPath = customPath;
        this.result.taskType = BaseConstant.TASK_INIT_PKEY_ACCOUNT;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            long id = context.getBaseDao().onInsertAccount(onGenAccount());
            if (id > 0) {
                result.isSuccess = true;
                mHideChains = context.getBaseDao().userHideChains();
                if (mHideChains.contains(mBaseChain)) {
                    int position = mHideChains.indexOf(mBaseChain);
                    if (position >= 0) {
                        mHideChains.remove(position);
                    }
                    context.getBaseDao().setUserHidenChains(mHideChains);
                }
                context.getBaseDao().setLastUser(id);
                context.getBaseDao().setLastChain(mBaseChain.getChain());

            } else {
                result.errorMsg = "Already existed account";
                result.errorCode = 7001;
            }

        } catch (Exception e) {

        }
        return result;
    }


    private Account onGenAccount() {
        Account newAccount = Account.getNewInstance();
        if (mPKey.toLowerCase().startsWith("0x")) {
            mPKey = mPKey.substring(2);
        }
        EncResult encR = CryptoHelper.doEncryptData(context.getString(R.string.key_private) + newAccount.uuid, mPKey, false);

        newAccount.baseChain = mBaseChain.getChain();
        newAccount.address = mAddress;
        newAccount.hasPrivateKey = true;
        newAccount.resource = encR.getEncDataString();
        newAccount.spec = encR.getIvDataString();
        newAccount.fromMnemonic = false;
        newAccount.path = "-1";
        newAccount.msize = -1;
        newAccount.importTime = System.currentTimeMillis();
        newAccount.customPath = mCustomPath;
        return newAccount;
    }
}
