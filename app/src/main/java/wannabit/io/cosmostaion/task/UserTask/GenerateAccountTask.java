package wannabit.io.cosmostaion.task.UserTask;

import com.fulldive.wallet.interactors.secret.MnemonicUtils;

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

public class GenerateAccountTask extends CommonTask {
    private final BaseChain mBaseChain;
    private final int mCustomPath;

    private ArrayList<BaseChain> mHideChains = new ArrayList<>();

    public GenerateAccountTask(BaseApplication app, BaseChain baseChain, TaskListener listener, int customPath) {
        super(app, listener);
        this.mBaseChain = baseChain;
        this.mCustomPath = customPath;
        this.result.taskType = BaseConstant.TASK_INIT_ACCOUNT;
    }


    /**
     * @param strings strings[0] : path
     *                strings[1] : entorpy seed
     *                strings[2] : word size
     * @return
     */
    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            long id = context.getBaseDao().onInsertAccount(onGenAccount(strings[1], strings[0], strings[2]));
            if (id > 0) {
                result.isSuccess = true;
                mHideChains = new ArrayList<>(context.getBaseDao().userHideChains());
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


    private Account onGenAccount(String entropy, String path, String size) throws Exception {
        Account newAccount = Account.getNewInstance();
        EncResult encR = CryptoHelper.doEncryptData(context.getString(R.string.key_mnemonic) + newAccount.uuid, entropy, false);

//        newAccount.address = WKey.getCreateDpAddressFromEntropy(mBaseChain, entropy, Integer.parseInt(path), mCustomPath);
        newAccount.address = MnemonicUtils.INSTANCE.createAddress(mBaseChain, entropy, Integer.parseInt(path), mCustomPath);
        newAccount.baseChain = mBaseChain.getChain();
        newAccount.hasPrivateKey = true;
        newAccount.resource = encR.getEncDataString();
        newAccount.spec = encR.getIvDataString();
        newAccount.fromMnemonic = true;
        newAccount.path = path;
        newAccount.msize = Integer.parseInt(size);
        newAccount.importTime = System.currentTimeMillis();
        newAccount.customPath = mCustomPath;
        return newAccount;
    }

}
