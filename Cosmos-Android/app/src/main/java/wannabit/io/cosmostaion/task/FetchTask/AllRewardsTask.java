package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.MediChain;
import wannabit.io.cosmostaion.network.res.ResLcdAllRewards;
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
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_ALL_REWARDS;

public class AllRewardsTask extends CommonTask {

    private Account mAccount;

    public AllRewardsTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount = account;
        this.mResult.taskType = TASK_FETCH_ALL_REWARDS;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(KAVA_MAIN)) {
                Response<ResLcdAllRewards> response = ApiClient.getKavaChain(mApp).getAllRewards(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result.rewards;
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(SECRET_MAIN)) {
                Response<ResLcdAllRewards> response = ApiClient.getSecretChain(mApp).getAllRewards(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result.rewards;
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(IOV_MAIN)) {
                Response<ResLcdAllRewards> response = ApiClient.getIovChain(mApp).getAllRewards(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result.rewards;
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(CERTIK_MAIN)) {
                Response<ResLcdAllRewards> response = ApiClient.getCertikChain(mApp).getAllRewards(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result.rewards;
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(FETCHAI_MAIN)) {
                Response<ResLcdAllRewards> response = ApiClient.getFetchChain(mApp).getAllRewards(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result.rewards;
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(SIF_MAIN)) {
                Response<ResLcdAllRewards> response = ApiClient.getSifChain(mApp).getAllRewards(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result.rewards;
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(KI_MAIN)) {
                Response<ResLcdAllRewards> response = ApiClient.getKiChain(mApp).getAllRewards(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result.rewards;
                    mResult.isSuccess = true;
                }

            }


            else if (BaseChain.getChain(mAccount.baseChain).equals(KAVA_TEST)) {
                Response<ResLcdAllRewards> response = ApiClient.getKavaTestChain(mApp).getAllRewards(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result.rewards;
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(IOV_TEST)) {
                Response<ResLcdAllRewards> response = ApiClient.getIovTestChain(mApp).getAllRewards(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result.rewards;
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(CERTIK_TEST)) {
                Response<ResLcdAllRewards> response = ApiClient.getCertikTestChain(mApp).getAllRewards(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result.rewards;
                    mResult.isSuccess = true;
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(MEDI_TEST)) {
                Response<ResLcdAllRewards> response = ApiClient.getMediTestChain(mApp).getAllRewards(mAccount.address).execute();
                if (response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body().result.rewards;
                    mResult.isSuccess = true;
                }

            }

        } catch (Exception e) {
            WLog.w("AllRewardsTask Error " + e.getMessage());

        }
        return mResult;
    }
}

