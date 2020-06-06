package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdUnBonding;
import wannabit.io.cosmostaion.network.res.ResLcdUnBondings;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class UnBondingStateTask extends CommonTask {

    private Account mAccount;

    public UnBondingStateTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount           = account;
        this.mResult.taskType   = BaseConstant.TASK_FETCH_UNBONDING_STATE;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.COSMOS_MAIN)) {
                Response<ResLcdUnBondings> response = ApiClient.getCosmosChain(mApp).getUnBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateUnbondingStates(mAccount.id, WUtil.getUnbondingFromLcds(mApp, BaseChain.COSMOS_MAIN, mAccount.id, response.body().result));
                    } else {
                        mApp.getBaseDao().onDeleteUnbondingStates(mAccount.id);
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.IRIS_MAIN)) {
                Response<ArrayList<ResLcdUnBonding>> response = ApiClient.getIrisChain(mApp).getUnBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().size() > 0) {
                        mApp.getBaseDao().onUpdateUnbondingStates(mAccount.id, WUtil.getUnbondingFromLcds(mApp, BaseChain.IRIS_MAIN, mAccount.id, response.body()));
                    } else {
                        mApp.getBaseDao().onDeleteUnbondingStates(mAccount.id);
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.KAVA_MAIN)) {
                Response<ResLcdUnBondings> response = ApiClient.getKavaChain(mApp).getUnBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateUnbondingStates(mAccount.id, WUtil.getUnbondingFromLcds(mApp, BaseChain.KAVA_MAIN, mAccount.id, response.body().result));
                    } else {
                        mApp.getBaseDao().onDeleteUnbondingStates(mAccount.id);
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.BAND_MAIN)) {
                Response<ResLcdUnBondings> response = ApiClient.getBandChain(mApp).getUnBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateUnbondingStates(mAccount.id, WUtil.getUnbondingFromLcds(mApp, BaseChain.COSMOS_MAIN, mAccount.id, response.body().result));
                    } else {
                        mApp.getBaseDao().onDeleteUnbondingStates(mAccount.id);
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.KAVA_TEST)) {
                Response<ResLcdUnBondings> response = ApiClient.getKavaTestChain(mApp).getUnBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateUnbondingStates(mAccount.id, WUtil.getUnbondingFromLcds(mApp, BaseChain.KAVA_MAIN, mAccount.id, response.body().result));
                    } else {
                        mApp.getBaseDao().onDeleteUnbondingStates(mAccount.id);
                    }
                }

            }
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.w("UnBondingStateTask Error " + e.getMessage());
        }
        return mResult;
    }
}
