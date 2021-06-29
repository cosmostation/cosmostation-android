package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdUnBondings;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_UNBONDING_STATE;

public class UnBondingStateTask extends CommonTask {

    private Account mAccount;

    public UnBondingStateTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount           = account;
        this.mResult.taskType   = TASK_FETCH_UNBONDING_STATE;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (getChain(mAccount.baseChain).equals(KAVA_MAIN)) {
                Response<ResLcdUnBondings> response = ApiClient.getKavaChain(mApp).getUnBondingList(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(BAND_MAIN)) {
                Response<ResLcdUnBondings> response = ApiClient.getBandChain(mApp).getUnBondingList(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(CERTIK_MAIN)) {
                Response<ResLcdUnBondings> response = ApiClient.getCertikChain(mApp).getUnBondingList(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(SECRET_MAIN)) {
                Response<ResLcdUnBondings> response = ApiClient.getSecretChain(mApp).getUnBondingList(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(FETCHAI_MAIN)) {
                Response<ResLcdUnBondings> response = ApiClient.getFetchChain(mApp).getUnBondingList(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(SIF_MAIN)) {
                Response<ResLcdUnBondings> response = ApiClient.getSifChain(mApp).getUnBondingList(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(KI_MAIN)) {
                Response<ResLcdUnBondings> response = ApiClient.getKiChain(mApp).getUnBondingList(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            }

            else if (getChain(mAccount.baseChain).equals(KAVA_TEST)) {
                Response<ResLcdUnBondings> response = ApiClient.getKavaTestChain(mApp).getUnBondingList(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(CERTIK_TEST)) {
                Response<ResLcdUnBondings> response = ApiClient.getCertikTestChain(mApp).getUnBondingList(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (getChain(mAccount.baseChain).equals(MEDI_TEST)) {
                Response<ResLcdUnBondings> response = ApiClient.getMediTestChain(mApp).getUnBondingList(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            }

        } catch (Exception e) {
            WLog.w("UnBondingStateTask Error " + e.getMessage());
        }
        return mResult;
    }
}
