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

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;

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
            if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_MAIN)) {
                Response<ResLcdUnBondings> response = ApiClient.getCosmosChain(mApp).getUnBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateUnbondingStates(mAccount.id, WUtil.getUnbondingFromLcds(mApp, COSMOS_MAIN, mAccount.id, response.body().result));
                    } else {
                        mApp.getBaseDao().onDeleteUnbondingStates(mAccount.id);
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(IRIS_MAIN)) {
                Response<ArrayList<ResLcdUnBonding>> response = ApiClient.getIrisChain(mApp).getUnBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().size() > 0) {
                        mApp.getBaseDao().onUpdateUnbondingStates(mAccount.id, WUtil.getUnbondingFromLcds(mApp, IRIS_MAIN, mAccount.id, response.body()));
                    } else {
                        mApp.getBaseDao().onDeleteUnbondingStates(mAccount.id);
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(KAVA_MAIN)) {
                Response<ResLcdUnBondings> response = ApiClient.getKavaChain(mApp).getUnBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateUnbondingStates(mAccount.id, WUtil.getUnbondingFromLcds(mApp, KAVA_MAIN, mAccount.id, response.body().result));
                    } else {
                        mApp.getBaseDao().onDeleteUnbondingStates(mAccount.id);
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BAND_MAIN)) {
                Response<ResLcdUnBondings> response = ApiClient.getBandChain(mApp).getUnBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateUnbondingStates(mAccount.id, WUtil.getUnbondingFromLcds(mApp, BAND_MAIN, mAccount.id, response.body().result));
                    } else {
                        mApp.getBaseDao().onDeleteUnbondingStates(mAccount.id);
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(KAVA_TEST)) {
                Response<ResLcdUnBondings> response = ApiClient.getKavaTestChain(mApp).getUnBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateUnbondingStates(mAccount.id, WUtil.getUnbondingFromLcds(mApp, KAVA_TEST, mAccount.id, response.body().result));
                    } else {
                        mApp.getBaseDao().onDeleteUnbondingStates(mAccount.id);
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(IOV_MAIN)) {
                Response<ResLcdUnBondings> response = ApiClient.getIovChain(mApp).getUnBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateUnbondingStates(mAccount.id, WUtil.getUnbondingFromLcds(mApp, IOV_MAIN, mAccount.id, response.body().result));
                    } else {
                        mApp.getBaseDao().onDeleteUnbondingStates(mAccount.id);
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(IOV_TEST)) {
                Response<ResLcdUnBondings> response = ApiClient.getIovTestChain(mApp).getUnBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateUnbondingStates(mAccount.id, WUtil.getUnbondingFromLcds(mApp, IOV_TEST, mAccount.id, response.body().result));
                    } else {
                        mApp.getBaseDao().onDeleteUnbondingStates(mAccount.id);
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(CERTIK_MAIN)) {
                Response<ResLcdUnBondings> response = ApiClient.getCertikChain(mApp).getUnBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateUnbondingStates(mAccount.id, WUtil.getUnbondingFromLcds(mApp, CERTIK_MAIN, mAccount.id, response.body().result));
                    } else {
                        mApp.getBaseDao().onDeleteUnbondingStates(mAccount.id);
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(CERTIK_TEST)) {
                Response<ResLcdUnBondings> response = ApiClient.getCertikTestChain(mApp).getUnBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateUnbondingStates(mAccount.id, WUtil.getUnbondingFromLcds(mApp, CERTIK_TEST, mAccount.id, response.body().result));
                    } else {
                        mApp.getBaseDao().onDeleteUnbondingStates(mAccount.id);
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(AKASH_MAIN)) {
                Response<ResLcdUnBondings> response = ApiClient.getAkashChain(mApp).getUnBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateUnbondingStates(mAccount.id, WUtil.getUnbondingFromLcds(mApp, CERTIK_TEST, mAccount.id, response.body().result));
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
