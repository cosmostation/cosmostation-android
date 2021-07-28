package wannabit.io.cosmostaion.task.SingleFetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdRewardFromVal;
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
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_SINGLE_REWARD;

public class SingleRewardTask extends CommonTask {

    private Account mAccount;
    private String mValidatorAddr;

    public SingleRewardTask(BaseApplication app, TaskListener listener, Account account, String validatorAddr) {
        super(app, listener);
        this.mResult.taskType = TASK_FETCH_SINGLE_REWARD;
        this.mAccount = account;
        this.mValidatorAddr = validatorAddr;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (getChain(mAccount.baseChain).equals(KAVA_MAIN)) {
                Response<ResLcdRewardFromVal> response = ApiClient.getKavaChain(mApp).getRewardFromValidator(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(BAND_MAIN)) {
                Response<ResLcdRewardFromVal> response = ApiClient.getBandChain(mApp).getRewardFromValidator(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(CERTIK_MAIN)) {
                Response<ResLcdRewardFromVal> response = ApiClient.getCertikChain(mApp).getRewardFromValidator(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(SECRET_MAIN)) {
                Response<ResLcdRewardFromVal> response = ApiClient.getSecretChain(mApp).getRewardFromValidator(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(FETCHAI_MAIN)) {
                Response<ResLcdRewardFromVal> response = ApiClient.getFetchChain(mApp).getRewardFromValidator(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(KI_MAIN)) {
                Response<ResLcdRewardFromVal> response = ApiClient.getKiChain(mApp).getRewardFromValidator(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            }

            else if (getChain(mAccount.baseChain).equals(KAVA_TEST)) {
                Response<ResLcdRewardFromVal> response = ApiClient.getKavaTestChain(mApp).getRewardFromValidator(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(CERTIK_TEST)) {
                Response<ResLcdRewardFromVal> response = ApiClient.getCertikTestChain(mApp).getRewardFromValidator(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(MEDI_TEST)) {
                Response<ResLcdRewardFromVal> response = ApiClient.getMediTestChain(mApp).getRewardFromValidator(mAccount.address, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            }

        } catch (Exception e) {
            WLog.w("SingleRewardTask Error " + e.getMessage());
        }

        return mResult;
    }
}