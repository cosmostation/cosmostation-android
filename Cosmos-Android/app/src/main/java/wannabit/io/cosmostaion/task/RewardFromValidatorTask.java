package wannabit.io.cosmostaion.task;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.utils.WLog;

public class RewardFromValidatorTask extends CommonTask {

    private Account     mAccount;
    private String      mValidatorAddr;


    public RewardFromValidatorTask(BaseApplication app, TaskListener listener, Account account, String validatorAddr) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_REWARDS_VALIDATOR;
        this.mAccount = account;
        this.mValidatorAddr = validatorAddr;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<Coin>> response = ApiClient.getWannabitChain(mApp).getRewardFromValidator(mAccount.address, mValidatorAddr).execute();
            if(!response.isSuccessful()) {
                mResult.isSuccess = false;
                mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                return mResult;
            }

            if(response.body() != null && response.body().size() > 0) {
                ArrayList<Coin> amounts = response.body();
                long time = System.currentTimeMillis();
//                WLog.w("RewardFromValidatorTask Coin : " + amounts.size());
                Reward temp = new Reward(mAccount.id, mValidatorAddr, amounts, time);
                mResult.resultData = temp;
                mResult.isSuccess = true;
            }

        } catch (Exception e) {
            WLog.w("RewardFromValidatorTask Error " + e.getMessage());
        }

        return mResult;
    }
}
