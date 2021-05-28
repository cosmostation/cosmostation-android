package wannabit.io.cosmostaion.task.SingleFetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdSingleBonding;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;

public class SingleBondingStateTask extends CommonTask {

    private Account mAccount;
    private String  mValidatorAddr;

    public SingleBondingStateTask(BaseApplication app, TaskListener listener, Account account, String validatorAddr) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_SINGLE_BONDING;
        this.mAccount = account;
        this.mValidatorAddr = validatorAddr;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (getChain(mAccount.baseChain).equals(KAVA_MAIN)) {
                Response<ResLcdSingleBonding> response = ApiClient.getKavaChain(mApp).getBonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(KAVA_TEST)) {
                Response<ResLcdSingleBonding> response = ApiClient.getKavaTestChain(mApp).getBonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(BAND_MAIN)) {
                Response<ResLcdSingleBonding> response = ApiClient.getBandChain(mApp).getBonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(IOV_MAIN)) {
                Response<ResLcdSingleBonding> response = ApiClient.getIovChain(mApp).getBonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(IOV_TEST)) {
                Response<ResLcdSingleBonding> response = ApiClient.getIovTestChain(mApp).getBonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(CERTIK_MAIN)) {
                Response<ResLcdSingleBonding> response = ApiClient.getCertikChain(mApp).getBonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(CERTIK_TEST)) {
                Response<ResLcdSingleBonding> response = ApiClient.getCertikTestChain(mApp).getBonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(SECRET_MAIN)) {
                Response<ResLcdSingleBonding> response = ApiClient.getSecretChain(mApp).getBonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(FETCHAI_MAIN)) {
                Response<ResLcdSingleBonding> response = ApiClient.getFetchChain(mApp).getBonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(SIF_MAIN)) {
                Response<ResLcdSingleBonding> response = ApiClient.getSifChain(mApp).getBonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(KI_MAIN)) {
                Response<ResLcdSingleBonding> response = ApiClient.getKiChain(mApp).getBonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            }



        } catch (Exception e) {
            WLog.w("SingleBondingStateTask Error " + e.getMessage());
        }
        return mResult;
    }
}
