package wannabit.io.cosmostaion.task.V1Task;

import java.util.ArrayList;

import retrofit2.Response;
import wannabit.io.cosmostaion.base.BaseApplication;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBalance_V1;
import wannabit.io.cosmostaion.task.CommonTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WLog;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_V1_FETCH_BALANCE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS_TEST;

public class BalanceTask_V1 extends CommonTask {
    private Account             mAccount;
    private int                 mOffset = 0;
    private boolean             mBreak = false;
    private ArrayList<Coin>     mResultData = new ArrayList<>();

    public BalanceTask_V1(BaseApplication app, TaskListener listener, Account account) {
        super(app, listener);
        this.mAccount           = account;
        this.mResult.taskType   = TASK_V1_FETCH_BALANCE;
    }

    @Override
    protected TaskResult doInBackground(String... strings) {
//        while(!mBreak) {
            ArrayList<Coin> temp = onDoingJob(mOffset);
            mResultData.addAll(temp);
//            if (temp.size() == 100) {
//                mOffset = mOffset + 100;
//            } else {
//                mBreak = true;
//            }
//        }

        boolean hasMain = false;
        if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_MAIN)) {
            for (Coin coin: mResultData) { if (coin.denom.equals(TOKEN_ATOM)) hasMain = true; }
            if (!hasMain) { mResultData.add(new Coin(TOKEN_ATOM,"0")); }
        } else if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_TEST)) {
            for (Coin coin: mResultData) { if (coin.denom.equals(TOKEN_COSMOS_TEST)) hasMain = true; }
            if (!hasMain) { mResultData.add(new Coin(TOKEN_COSMOS_TEST,"0")); }
        } else if (BaseChain.getChain(mAccount.baseChain).equals(IRIS_MAIN)) {
            for (Coin coin: mResultData) { if (coin.denom.equals(TOKEN_IRIS)) hasMain = true; }
            if (!hasMain) { mResultData.add(new Coin(TOKEN_IRIS,"0")); }
        } else if (BaseChain.getChain(mAccount.baseChain).equals(IRIS_TEST)) {
            for (Coin coin: mResultData) { if (coin.denom.equals(TOKEN_IRIS_TEST)) hasMain = true; }
            if (!hasMain) { mResultData.add(new Coin(TOKEN_IRIS_TEST,"0")); }
        }

        mResult.resultData = mResultData;
        mResult.isSuccess = true;
        return mResult;
    }

    private ArrayList<Coin> onDoingJob(int offset) {
        ArrayList<Coin> resultData = new ArrayList<>();
        try {
            if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_MAIN)) {
                Response<ResBalance_V1> response = ApiClient.getCosmosChain(mApp).getBalance(mAccount.address, 100,  offset).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().balances != null) {
                        resultData = response.body().balances;
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(COSMOS_TEST)) {
                Response<ResBalance_V1> response = ApiClient.getCosmosTestChain(mApp).getBalance(mAccount.address, 100,  offset).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().balances != null) {
                        resultData = response.body().balances;
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(IRIS_MAIN)) {
                Response<ResBalance_V1> response = ApiClient.getIrisChain(mApp).getBalance(mAccount.address, 100,  offset).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().balances != null) {
                        resultData = response.body().balances;
                    }
                }

            } else if (BaseChain.getChain(mAccount.baseChain).equals(IRIS_TEST)) {
                Response<ResBalance_V1> response = ApiClient.getIrisTestChain(mApp).getBalance(mAccount.address, 100,  offset).execute();
                if (response.isSuccessful()) {
                    if (response.body() != null && response.body().balances != null) {
                        resultData = response.body().balances;
                    }
                }
            }

        } catch (Exception e) { }
        return resultData;
    }
}
