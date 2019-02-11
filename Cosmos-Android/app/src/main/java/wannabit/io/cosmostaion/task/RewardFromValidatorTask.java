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
    private Validator   mValidator;


    public RewardFromValidatorTask(BaseApplication app, TaskListener listener, Account account, Validator validator) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_REWARDS_VALIDATOR;
        this.mAccount = account;
        this.mValidator = validator;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            Response<ArrayList<Coin>> response = ApiClient.getWannabitChain(mApp).getRewardFromValidator(mAccount.address, mValidator.operator_address).execute();
            if(response.isSuccessful() && response.body() != null && response.body().size() > 0) {
                ArrayList<Coin> mValidators = response.body();
                WLog.w("RewardFromValidatorTask Coin : " + mValidators.size());
            }
            mResult.isSuccess = true;

//            Reward temp = new Reward();
//            temp.accountId = mAccount.id;
//            temp.validatorAddress = mValidator.operator_address;
//            temp.shares

        } catch (Exception e) {
            WLog.w("RewardFromValidatorTask Error " + e.getMessage());
        }

        return mResult;
    }
}
