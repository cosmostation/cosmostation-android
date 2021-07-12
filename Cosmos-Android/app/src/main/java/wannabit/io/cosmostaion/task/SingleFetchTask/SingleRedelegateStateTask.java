package wannabit.io.cosmostaion.task.SingleFetchTask;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Redelegate;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdRedelegate;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.getChain;

public class SingleRedelegateStateTask extends CommonTask {

    private Account     mAccount;
    private Validator   mToValidtor;

    public SingleRedelegateStateTask(BaseApplication app, TaskListener listener, Account account, Validator toVal) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_SINGLE_REDELEGATE;
        this.mAccount = account;
        this.mToValidtor = toVal;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (getChain(mAccount.baseChain).equals(KAVA_MAIN)) {
                Response<ResLcdRedelegate> response = ApiClient.getKavaChain(mApp).getRedelegateHistory(mAccount.address, mToValidtor.operator_address).execute();
                if (response.isSuccessful()) {
                    if(response.body() != null && response.body().result != null) {
                        mResult.resultData = response.body().result;
                        mResult.isSuccess = true;
                    } else {
                        mResult.resultData = new ArrayList<Redelegate>();
                        mResult.isSuccess = true;
                    }
                }
                
            } else if (getChain(mAccount.baseChain).equals(CERTIK_MAIN)) {
                Response<ResLcdRedelegate> response = ApiClient.getCertikChain(mApp).getRedelegateHistory(mAccount.address, mToValidtor.operator_address).execute();
                if (response.isSuccessful()) {
                    if(response.body() != null && response.body().result != null) {
                        mResult.resultData = response.body().result;
                        mResult.isSuccess = true;
                    } else {
                        mResult.resultData = new ArrayList<Redelegate>();
                        mResult.isSuccess = true;
                    }
                }

            } else if (getChain(mAccount.baseChain).equals(SECRET_MAIN)) {
                Response<ResLcdRedelegate> response = ApiClient.getSecretChain(mApp).getRedelegateHistory(mAccount.address, mToValidtor.operator_address).execute();
                if (response.isSuccessful()) {
                    if(response.body() != null && response.body().result != null) {
                        mResult.resultData = response.body().result;
                        mResult.isSuccess = true;
                    } else {
                        mResult.resultData = new ArrayList<Redelegate>();
                        mResult.isSuccess = true;
                    }
                }

            } else if (getChain(mAccount.baseChain).equals(FETCHAI_MAIN)) {
                Response<ResLcdRedelegate> response = ApiClient.getFetchChain(mApp).getRedelegateHistory(mAccount.address, mToValidtor.operator_address).execute();
                if (response.isSuccessful()) {
                    if(response.body() != null && response.body().result != null) {
                        mResult.resultData = response.body().result;
                        mResult.isSuccess = true;
                    } else {
                        mResult.resultData = new ArrayList<Redelegate>();
                        mResult.isSuccess = true;
                    }
                }

            } else if (getChain(mAccount.baseChain).equals(SIF_MAIN)) {
                Response<ResLcdRedelegate> response = ApiClient.getSifChain(mApp).getRedelegateHistory(mAccount.address, mToValidtor.operator_address).execute();
                if (response.isSuccessful()) {
                    if(response.body() != null && response.body().result != null) {
                        mResult.resultData = response.body().result;
                        mResult.isSuccess = true;
                    } else {
                        mResult.resultData = new ArrayList<Redelegate>();
                        mResult.isSuccess = true;
                    }
                }

            } else if (getChain(mAccount.baseChain).equals(KI_MAIN)) {
                Response<ResLcdRedelegate> response = ApiClient.getKiChain(mApp).getRedelegateHistory(mAccount.address, mToValidtor.operator_address).execute();
                if (response.isSuccessful()) {
                    if(response.body() != null && response.body().result != null) {
                        mResult.resultData = response.body().result;
                        mResult.isSuccess = true;
                    } else {
                        mResult.resultData = new ArrayList<Redelegate>();
                        mResult.isSuccess = true;
                    }
                }

            }

            else if (getChain(mAccount.baseChain).equals(KAVA_TEST)) {
                Response<ResLcdRedelegate> response = ApiClient.getKavaTestChain(mApp).getRedelegateHistory(mAccount.address, mToValidtor.operator_address).execute();
                if (response.isSuccessful()) {
                    if(response.body() != null && response.body().result != null) {
                        mResult.resultData = response.body().result;
                        mResult.isSuccess = true;
                    } else {
                        mResult.resultData = new ArrayList<Redelegate>();
                        mResult.isSuccess = true;
                    }
                }

            } else if (getChain(mAccount.baseChain).equals(CERTIK_TEST)) {
                Response<ResLcdRedelegate> response = ApiClient.getCertikTestChain(mApp).getRedelegateHistory(mAccount.address, mToValidtor.operator_address).execute();
                if (response.isSuccessful()) {
                    if(response.body() != null && response.body().result != null) {
                        mResult.resultData = response.body().result;
                        mResult.isSuccess = true;
                    } else {
                        mResult.resultData = new ArrayList<Redelegate>();
                        mResult.isSuccess = true;
                    }
                }

            } else if (getChain(mAccount.baseChain).equals(MEDI_TEST)) {
                Response<ResLcdRedelegate> response = ApiClient.getMediTestChain(mApp).getRedelegateHistory(mAccount.address, mToValidtor.operator_address).execute();
                if (response.isSuccessful()) {
                    if(response.body() != null && response.body().result != null) {
                        mResult.resultData = response.body().result;
                        mResult.isSuccess = true;
                    } else {
                        mResult.resultData = new ArrayList<Redelegate>();
                        mResult.isSuccess = true;
                    }
                }

            }

        } catch (Exception e) {
            WLog.w("SingleUnBondingStateTask Error " + e.getMessage());
        }
        return mResult;
    }

}
