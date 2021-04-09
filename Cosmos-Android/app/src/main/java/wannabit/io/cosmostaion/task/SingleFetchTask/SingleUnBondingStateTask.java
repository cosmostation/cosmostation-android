package wannabit.io.cosmostaion.task.SingleFetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdSingleUnBonding;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;

public class SingleUnBondingStateTask extends CommonTask {

    private Account mAccount;
    private String  mValidatorAddr;

    public SingleUnBondingStateTask(BaseApplication app, TaskListener listener, Account account, String validatorAddr) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_SINGLE_UNBONDING;
        this.mAccount = account;
        this.mValidatorAddr = validatorAddr;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(KAVA_MAIN)) {
                Response<ResLcdSingleUnBonding> response = ApiClient.getKavaChain(mApp).getUnbonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(KAVA_TEST)) {
                Response<ResLcdSingleUnBonding> response = ApiClient.getKavaTestChain(mApp).getUnbonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BAND_MAIN)) {
                Response<ResLcdSingleUnBonding> response = ApiClient.getBandChain(mApp).getUnbonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(IOV_MAIN)) {
                Response<ResLcdSingleUnBonding> response = ApiClient.getIovChain(mApp).getUnbonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(IOV_TEST)) {
                Response<ResLcdSingleUnBonding> response = ApiClient.getIovTestChain(mApp).getUnbonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(CERTIK_MAIN)) {
                Response<ResLcdSingleUnBonding> response = ApiClient.getCertikChain(mApp).getUnbonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(CERTIK_TEST)) {
                Response<ResLcdSingleUnBonding> response = ApiClient.getCertikTestChain(mApp).getUnbonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(SECRET_MAIN)) {
                Response<ResLcdSingleUnBonding> response = ApiClient.getSecretChain(mApp).getUnbonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(SENTINEL_MAIN)) {
                Response<ResLcdSingleUnBonding> response = ApiClient.getSentinelChain(mApp).getUnbonding(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            }

        } catch (Exception e) {
            WLog.w("SingleUnBondingStateTask Error " + e.getMessage());
        }
        return mResult;
    }
}
