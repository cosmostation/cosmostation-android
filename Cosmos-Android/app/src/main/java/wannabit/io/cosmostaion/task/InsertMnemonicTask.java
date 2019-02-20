package wannabit.io.cosmostaion.task;

import org.bitcoinj.crypto.HDKeyDerivation;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.crypto.EncResult;
import wannabit.io.cosmostaion.dao.Mnemonic;
import wannabit.io.cosmostaion.utils.WUtil;

public class InsertMnemonicTask extends CommonTask {

    public InsertMnemonicTask(BaseApplication app, TaskListener listener) {
        super(app, listener);
        this.mResult.taskType = BaseConstant.TASK_ADD_MN;
    }

    /**
     *
     * @param strings
     *  strings[0] : seed
     *  strings[1] : size
     */
    @Override
    protected TaskResult doInBackground(String... strings) {
        Mnemonic newMn      = Mnemonic.getNewInstance();
        EncResult encR      = CryptoHelper.doEncryptData(mApp.getString(R.string.key_mnemonic)+ newMn.uuid, strings[0], false);
        newMn.resource      = encR.getEncDataString();
        newMn.spec          = encR.getIvDataString();
        newMn.dpMasterKey   = HDKeyDerivation.createMasterPrivateKey(WUtil.HexStringToByteArray(strings[0])).getPublicKeyAsHex();
        newMn.typeSize      = Integer.parseInt(strings[1]);

        long result = mApp.getBaseDao().onInsertMnemonic(newMn);

        if(result  > 0 ) {
            mResult.isSuccess = true;
            mResult.resultData = result;
        } else {
            mResult.errorMsg = "Already existed mnemonics";
            mResult.errorCode = 7000;
        }
        return mResult;
    }
}
