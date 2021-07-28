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

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;

public class ValidatorInfoUnbondedTask extends CommonTask {
    private BaseChain mChain;

    public ValidatorInfoUnbondedTask(BaseApplication app, TaskListener listener, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_UNBONDED_VALIDATOR;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(KAVA_MAIN)) {
                Response<ResLcdValidators> response = ApiClient.getKavaChain(mApp).getUnBondedValidatorDetailList().execute();
                if(!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if(response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(BAND_MAIN)) {
                Response<ResLcdValidators> response = ApiClient.getBandChain(mApp).getUnBondedValidatorDetailList().execute();
                if(!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if(response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(OKEX_MAIN)) {
                Response<ArrayList<Validator>> response = ApiClient.getOkexChain(mApp).getUnBondedValidatorDetailList().execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(CERTIK_MAIN)) {
                Response<ResLcdValidators> response = ApiClient.getCertikChain(mApp).getUnBondedValidatorDetailList().execute();
                if(!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if(response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(SECRET_MAIN)) {
                Response<ResLcdValidators> response = ApiClient.getSecretChain(mApp).getUnBondedValidatorDetailList().execute();
                if(!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if(response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(FETCHAI_MAIN)) {
                Response<ResLcdValidators> response = ApiClient.getFetchChain(mApp).getUnBondedValidatorDetailList().execute();
                if(!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if(response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(KI_MAIN)) {
                Response<ResLcdValidators> response = ApiClient.getKiChain(mApp).getUnBondedValidatorDetailList().execute();
                if(!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if(response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(MEDI_MAIN)) {
                Response<ResLcdValidators> response = ApiClient.getMediChain(mApp).getUnBondedValidatorDetailList().execute();
                if(!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if(response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            }

            else if (mChain.equals(KAVA_TEST)) {
                Response<ResLcdValidators> response = ApiClient.getKavaTestChain(mApp).getUnBondedValidatorDetailList().execute();
                if(!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if(response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(OK_TEST)) {
                Response<ArrayList<Validator>> response = ApiClient.getOkTestChain(mApp).getUnBondedValidatorDetailList().execute();
                if (!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if (response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(CERTIK_TEST)) {
                Response<ResLcdValidators> response = ApiClient.getCertikTestChain(mApp).getUnBondedValidatorDetailList().execute();
                if(!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if(response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(MEDI_TEST)) {
                Response<ResLcdValidators> response = ApiClient.getMediTestChain(mApp).getUnBondedValidatorDetailList().execute();
                if(!response.isSuccessful()) {
                    mResult.isSuccess = false;
                    mResult.errorCode = BaseConstant.ERROR_CODE_NETWORK;
                    return mResult;
                }

                if(response.body() != null && response.body().result != null && response.body().result.size() > 0) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            }



        } catch (Exception e) {
            WLog.w("UnbondedValidatorInfo Error " + e.getMessage());
        }

        return mResult;
    }
}
