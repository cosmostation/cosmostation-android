package wannabit.io.cosmostaion.task.V1Task;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.model.Validator_V1;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResValidators_V1;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_V1_FETCH_BONDED_VALIDATORS;

public class BondedValidatorsTask_V1 extends CommonTask {
    private BaseChain               mChain;
    private int                     mOffset = 0;
    private boolean                 mBreak = false;
    private ArrayList<Validator_V1> resultData = new ArrayList<>();

    public BondedValidatorsTask_V1(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = TASK_V1_FETCH_BONDED_VALIDATORS;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
//        while(!mBreak) {
            ArrayList<Validator_V1> temp = onDoingJob(mOffset);
            resultData.addAll(temp);
//            if (temp.size() == 125) {
//                mOffset = mOffset + 125;
//            } else {
//                mBreak = true;
//            }
//        }
        mResult.resultData = resultData;
        mResult.isSuccess = true;
        return mResult;
    }

    private ArrayList<Validator_V1> onDoingJob(int offset) {
        ArrayList<Validator_V1> resultData = new ArrayList<>();
        try {
            if (mChain.equals(COSMOS_MAIN)) {
                Response<ResValidators_V1> response = ApiClient.getCosmosChain(mApp).getBondedValidatorList(125,  offset).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().validators != null) {
                        resultData = response.body().validators;
                    }
                }

            } else if (mChain.equals(IRIS_MAIN)) {
                Response<ResValidators_V1> response = ApiClient.getIrisChain(mApp).getBondedValidatorList(125,  offset).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().validators != null) {
                        resultData = response.body().validators;
                    }
                }

            } else if (mChain.equals(AKASH_MAIN)) {
                Response<ResValidators_V1> response = ApiClient.getAkashChain(mApp).getBondedValidatorList(125,  offset).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().validators != null) {
                        resultData = response.body().validators;
                    }
                }

            } else if (mChain.equals(COSMOS_TEST)) {
                Response<ResValidators_V1> response = ApiClient.getCosmosTestChain(mApp).getBondedValidatorList(125,  offset).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().validators != null) {
                        resultData = response.body().validators;
                    }
                }

            }  else if (mChain.equals(IRIS_TEST)) {
                Response<ResValidators_V1> response = ApiClient.getIrisTestChain(mApp).getBondedValidatorList(125,  offset).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().validators != null) {
                        resultData = response.body().validators;
                    }
                }
            }

        } catch (Exception e) { }

        return resultData;
    }
}
