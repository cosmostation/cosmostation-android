package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdBonding;
import wannabit.io.cosmostaion.network.res.ResLcdBondings;
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
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;

public class BondingStateTask extends CommonTask {

    private Account mAccount;

    public BondingStateTask(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount           = account;
        this.mResult.taskType   = BaseConstant.TASK_FETCH_BONDING_STATE;
    }


    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (getChain(mAccount.baseChain).equals(KAVA_MAIN)) {
                Response<ResLcdBondings> response = ApiClient.getKavaChain(mApp).getBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateBondingStates(mAccount.id, WUtil.getBondingFromLcds(mAccount.id, response.body().result, KAVA_MAIN));
                    } else {
                        mApp.getBaseDao().onDeleteBondingStates(mAccount.id);
                    }
                }

            } else if (getChain(mAccount.baseChain).equals(KAVA_TEST)) {
                Response<ResLcdBondings> response = ApiClient.getKavaTestChain(mApp).getBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateBondingStates(mAccount.id, WUtil.getBondingFromLcds(mAccount.id, response.body().result, KAVA_TEST));
                    } else {
                        mApp.getBaseDao().onDeleteBondingStates(mAccount.id);
                    }
                }

            } else if (getChain(mAccount.baseChain).equals(BAND_MAIN)) {
                Response<ResLcdBondings> response = ApiClient.getBandChain(mApp).getBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateBondingStates(mAccount.id, WUtil.getBondingFromLcds(mAccount.id, response.body().result, BAND_MAIN));
                    } else {
                        mApp.getBaseDao().onDeleteBondingStates(mAccount.id);
                    }
                }

            } else if (getChain(mAccount.baseChain).equals(IOV_MAIN)) {
                Response<ResLcdBondings> response = ApiClient.getIovChain(mApp).getBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateBondingStates(mAccount.id, WUtil.getBondingFromLcds(mAccount.id, response.body().result, IOV_MAIN));
                    } else {
                        mApp.getBaseDao().onDeleteBondingStates(mAccount.id);
                    }
                }

            } else if (getChain(mAccount.baseChain).equals(IOV_TEST)) {
                Response<ResLcdBondings> response = ApiClient.getIovTestChain(mApp).getBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null &&response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateBondingStates(mAccount.id, WUtil.getBondingFromLcds(mAccount.id, response.body().result, IOV_TEST));
                    } else {
                        mApp.getBaseDao().onDeleteBondingStates(mAccount.id);
                    }
                }

            } else if (getChain(mAccount.baseChain).equals(CERTIK_MAIN)) {
                Response<ResLcdBondings> response = ApiClient.getCertikChain(mApp).getBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateBondingStates(mAccount.id, WUtil.getBondingFromLcds(mAccount.id, response.body().result, CERTIK_MAIN));
                    } else {
                        mApp.getBaseDao().onDeleteBondingStates(mAccount.id);
                    }
                }

            } else if (getChain(mAccount.baseChain).equals(CERTIK_TEST)) {
                Response<ResLcdBondings> response = ApiClient.getCertikTestChain(mApp).getBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateBondingStates(mAccount.id, WUtil.getBondingFromLcds(mAccount.id, response.body().result, CERTIK_TEST));
                    } else {
                        mApp.getBaseDao().onDeleteBondingStates(mAccount.id);
                    }
                }

            } else if (getChain(mAccount.baseChain).equals(SECRET_MAIN)) {
                Response<ResLcdBondings> response = ApiClient.getSecretChain(mApp).getBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateBondingStates(mAccount.id, WUtil.getBondingFromLcds(mAccount.id, response.body().result, SECRET_MAIN));
                    } else {
                        mApp.getBaseDao().onDeleteBondingStates(mAccount.id);
                    }
                }

            }
            mResult.isSuccess = true;

        } catch (Exception e) {
            WLog.w("BondingStateTask Error " + e.getMessage());
        }
        return mResult;
    }
}
