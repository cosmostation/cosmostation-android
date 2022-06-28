package wannabit.io.cosmostaion.task.UserTask;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.crypto.EncResult;
import wannabit.io.cosmostaion.dao.MWords;
import wannabit.io.cosmostaion.dao.Password;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WUtil;

public class GenerateMnemonicTask extends CommonTask {

    private ArrayList<String> mWords = new ArrayList<>();

    public GenerateMnemonicTask(BaseApplication app, TaskListener listener, ArrayList<String> words) {
        super(app, listener);
        this.mWords = words;
        this.mResult.taskType = BaseConstant.TASK_INIT_MNEMONIC;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        Password checkPw = mApp.getBaseDao().onSelectPassword();
        if(!CryptoHelper.verifyData(strings[0], checkPw.resource, mApp.getString(R.string.key_password))) {
            mResult.isSuccess = false;
            mResult.errorCode = BaseConstant.ERROR_CODE_INVALID_PASSWORD;
            return mResult;
        } else {
            long id = mApp.getBaseDao().onInsertMnemonics(onGenMWords(mWords));
            if (id > 0) {
                mResult.isSuccess = true;
                mResult.resultData = id;
            } else {
                mResult.errorMsg = "Already existed Mnemonic";
                mResult.errorCode = 7001;
            }
        }
        return mResult;
    }

    private MWords onGenMWords(ArrayList<String> words) {
        MWords tempMWords = MWords.getNewInstance();
        String entropy = WUtil.ByteArrayToHexString(WKey.toEntropy(words));
        EncResult encR = CryptoHelper.doEncryptData(mApp.getString(R.string.key_mnemonic) + tempMWords.uuid, entropy, false);

        tempMWords.resource = encR.getEncDataString();
        tempMWords.spec = encR.getIvDataString();
        tempMWords.wordsCnt = words.size();
        return tempMWords;
    }
}
