package wannabit.io.cosmostaion.task.SingleFetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResLcdSingleBonding;
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
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;

public class SingleSelfBondingStateTask extends CommonTask {

    private String  mDelegateAddr;
    private String  mValidatorAddr;
    private BaseChain mChain;

    public SingleSelfBondingStateTask(BaseApplication app, TaskListener listener, String delegateAddr, String validatorAddr, BaseChain chain) {
        super(app, listener);
        this.mResult.taskType   = BaseConstant.TASK_FETCH_SINGLE_SELF_BONDING;
        this.mDelegateAddr = delegateAddr;
        this.mValidatorAddr = validatorAddr;
        this.mChain = chain;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(KAVA_MAIN)) {
                Response<ResLcdSingleBonding> response = ApiClient.getKavaChain(mApp).getBonding(mDelegateAddr, mValidatorAddr).execute();
                if (response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(SECRET_MAIN)) {
                Response<ResLcdSingleBonding> response = ApiClient.getSecretChain(mApp).getBonding(mDelegateAddr, mValidatorAddr).execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            } else if (mChain.equals(KI_MAIN)) {
                Response<ResLcdSingleBonding> response = ApiClient.getKiChain(mApp).getBonding(mDelegateAddr, mValidatorAddr).execute();
                if(response.isSuccessful() && response.body() != null && response.body().result != null) {
                    mResult.resultData = response.body().result;
                    mResult.isSuccess = true;
                }

            }

        } catch (Exception e) {
            WLog.w("SingleBondingStateTask Error " + e.getMessage());
        }
        return mResult;
    }
}
