package wannabit.io.cosmostaion.task.FetchTask;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResKavaHardMyBorrow;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_HARD_MY_BORROW;

public class KavaHardMyBorrowTask extends CommonTask {
    private BaseChain mChain;
    private String mOwner;

    public KavaHardMyBorrowTask(BaseApplication app, TaskListener listener, BaseChain chain, String address) {
        super(app, listener);
        this.mResult.taskType = TASK_FETCH_KAVA_HARD_MY_BORROW;
        this.mChain = chain;
        this.mOwner = address;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
        try {
            if (mChain.equals(BaseChain.KAVA_MAIN)) {
                Response<ResKavaHardMyBorrow> response = ApiClient.getKavaChain(mApp).getHardMyBorrow(mOwner).execute();
                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;

                } else {
                    WLog.w("KavaHardMyBorrowTask : NOk");
                }

            } else if (mChain.equals(BaseChain.KAVA_TEST)) {
                Response<ResKavaHardMyBorrow> response = ApiClient.getKavaTestChain(mApp).getHardMyBorrow(mOwner).execute();
                if(response.isSuccessful() && response.body() != null) {
                    mResult.resultData = response.body();
                    mResult.isSuccess = true;

                } else {
                    WLog.w("KavaHardMyBorrowTask : NOk");
                }
            }

        } catch (Exception e) {
            WLog.w("KavaHardMyBorrowTask Error " + e.getMessage());
        }
        return mResult;
    }
}