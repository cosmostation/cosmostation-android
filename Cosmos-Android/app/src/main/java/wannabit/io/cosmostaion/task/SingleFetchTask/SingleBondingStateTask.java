package wannabit.io.cosmostaion.task.SingleFetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdBonding;
import wannabit.io.cosmostaion.network.res.ResLcdSingleBonding;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

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
            if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.COSMOS_MAIN)) {
                Response<ResLcdSingleBonding> response = ApiClient.getCosmosChain(mApp).getBonding(mAccount.address, mValidatorAddr).execute();
                if(response.isSuccessful()) {
                    if(response.body() != null && response.body().result != null)
                        mApp.getBaseDao().onUpdateBondingState(mAccount.id, WUtil.getBondingFromLcd(mAccount.id, response.body().result, BaseChain.COSMOS_MAIN));
                    else
                        mApp.getBaseDao().onDeleteBondingStates(mAccount.id);
                }
                mResult.isSuccess = true;

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.IRIS_MAIN)) {
                Response<ResLcdBonding> response = ApiClient.getIrisChain(mApp).getBonding(mAccount.address, mValidatorAddr).execute();
                if(response.isSuccessful()) {
                    if(response.body() != null){
                        mApp.getBaseDao().onUpdateBondingState(mAccount.id, WUtil.getBondingFromLcd(mAccount.id, response.body(), BaseChain.IRIS_MAIN));
                    } else {
                        mApp.getBaseDao().onDeleteBondingStates(mAccount.id);
                    }

                }
                mResult.isSuccess = true;

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.KAVA_MAIN)) {
                Response<ResLcdSingleBonding> response = ApiClient.getKavaChain(mApp).getBonding(mAccount.address, mValidatorAddr).execute();
                if(response.isSuccessful()) {
                    if(response.body() != null && response.body().result != null)
                        mApp.getBaseDao().onUpdateBondingState(mAccount.id, WUtil.getBondingFromLcd(mAccount.id, response.body().result, BaseChain.KAVA_MAIN));
                    else
                        mApp.getBaseDao().onDeleteBondingStates(mAccount.id);
                }
                mResult.isSuccess = true;

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.KAVA_TEST)) {
                Response<ResLcdSingleBonding> response = ApiClient.getKavaTestChain(mApp).getBonding(mAccount.address, mValidatorAddr).execute();
                if(response.isSuccessful()) {
                    if(response.body() != null && response.body().result != null)
                        mApp.getBaseDao().onUpdateBondingState(mAccount.id, WUtil.getBondingFromLcd(mAccount.id, response.body().result, BaseChain.KAVA_TEST));
                    else
                        mApp.getBaseDao().onDeleteBondingStates(mAccount.id);
                }
                mResult.isSuccess = true;

            } else if (BaseChain.getChain(mAccount.baseChain).equals(BaseChain.BAND_MAIN)) {
                Response<ResLcdSingleBonding> response = ApiClient.getBandChain(mApp).getBonding(mAccount.address, mValidatorAddr).execute();
                if(response.isSuccessful()) {
                    if(response.body() != null && response.body().result != null)
                        mApp.getBaseDao().onUpdateBondingState(mAccount.id, WUtil.getBondingFromLcd(mAccount.id, response.body().result, BaseChain.COSMOS_MAIN));
                    else
                        mApp.getBaseDao().onDeleteBondingStates(mAccount.id);
                }
                mResult.isSuccess = true;

            }



        } catch (Exception e) {
            WLog.w("SingleBondingStateTask Error " + e.getMessage());
        }
        return mResult;
    }
}
