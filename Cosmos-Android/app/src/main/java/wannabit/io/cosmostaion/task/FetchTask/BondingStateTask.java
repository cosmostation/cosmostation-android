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
            if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.COSMOS_MAIN)) {
//                Response<ResLcdBondings> response = ApiClient.getCosmosChain(mApp).getBondingList(mAccount.address).execute();
//                if(response.isSuccessful()) {
//                    if (response.body() != null && response.body().result != null &&response.body().result.size() > 0) {
//                        mApp.getBaseDao().onUpdateBondingStates(mAccount.id, WUtil.getBondingFromLcds(mAccount.id, response.body().result, BaseChain.COSMOS_MAIN));
//                    } else {
//                        mApp.getBaseDao().onDeleteBondingStates(mAccount.id);
//                    }
//                }
                //TODO rollback cosmos-hub2
                Response<ArrayList<ResLcdBonding>> response = ApiClient.getCosmosChain(mApp).getBondingListLegacy(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null &&response.body().size() > 0) {
                        mApp.getBaseDao().onUpdateBondingStates(mAccount.id, WUtil.getBondingFromLcds(mAccount.id, response.body(), BaseChain.COSMOS_MAIN));
                    } else {
                        mApp.getBaseDao().onDeleteBondingStates(mAccount.id);
                    }
                }


            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.IRIS_MAIN)) {
                Response<ArrayList<ResLcdBonding>> response = ApiClient.getIrisChain(mApp).getBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().size() > 0) {
                        mApp.getBaseDao().onUpdateBondingStates(mAccount.id, WUtil.getBondingFromLcds(mAccount.id, response.body(), BaseChain.IRIS_MAIN));
                    } else {
                        mApp.getBaseDao().onDeleteBondingStates(mAccount.id);
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.KAVA_MAIN)) {
                Response<ResLcdBondings> response = ApiClient.getKavaChain(mApp).getBondingList(mAccount.address).execute();
                if(response.isSuccessful()) {
                    if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                        mApp.getBaseDao().onUpdateBondingStates(mAccount.id, WUtil.getBondingFromLcds(mAccount.id, response.body().result, BaseChain.COSMOS_MAIN));
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
