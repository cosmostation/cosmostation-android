package wannabit.io.cosmostaion.task.FetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdValidators;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

public class AllValidatorInfoTask extends CommonTask {
    private BaseChain   mChain;

    public AllValidatorInfoTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_ALL_VALIDATOR;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.COSMOS_MAIN)) {
                Response<ResLcdValidators> response = ApiClient.getCosmosChain(mApp).getValidatorDetailList().execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.IRIS_MAIN)) {
                int page = 0;
                boolean needMore = true;
                ArrayList<Validator> allResult = new ArrayList<>();
                do {
                    page ++;
                    Response<ArrayList<Validator>> response = ApiClient.getIrisChain(mApp).getValidatorList(""+page, "100").execute();
                    if (!response.isSuccessful()) {
                        mResult.isSuccess = false;
                        mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                        needMore = false;
                    }

                    if (response.body() != null && response.body().size() > 0) {
                        if(response.body().size() == 100) {
                            allResult.addAll(response.body());

                        } else {
                            allResult.addAll(response.body());
                            mResult.isSuccess = true;
                            needMore = false;
                        }
                    }

                } while (needMore);
                mResult.resultData = allResult;

            } else if (mChain.equals(BaseChain.KAVA_MAIN)) {
                Response<ResLcdValidators> response = ApiClient.getKavaChain(mApp).getValidatorDetailList().execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.KAVA_TEST)) {
                Response<ResLcdValidators> response = ApiClient.getKavaTestChain(mApp).getValidatorDetailList().execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.BAND_MAIN)) {
                Response<ResLcdValidators> response = ApiClient.getBandChain(mApp).getValidatorDetailList().execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.IOV_TEST)) {
                Response<ResLcdValidators> response = ApiClient.getIovTestChain(mApp).getValidatorDetailList().execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BaseChain.OK_TEST)) {
                Response<ArrayList<Validator>> response = ApiClient.getOkTestChain(mApp).getValidatorDetailList().execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null && response.body() != null && response.body().size() > 0) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }

            }


        } catch (Exception e) {
            WLog.w("AllValidatorInfo Error " + e.getMessage());
        }

        return mResult;
    }
}
