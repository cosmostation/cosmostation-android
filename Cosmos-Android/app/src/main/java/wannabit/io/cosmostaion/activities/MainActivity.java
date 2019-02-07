package wannabit.io.cosmostaion.activities;

import android.os.AsyncTask;
import android.os.Bundle;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.UnBondingState;
import wannabit.io.cosmostaion.task.AccountInfoTask;
import wannabit.io.cosmostaion.task.AllValidatorInfoTask;
import wannabit.io.cosmostaion.task.BondingStateTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.UnBondingStateTask;
import wannabit.io.cosmostaion.utils.WLog;

public class MainActivity extends BaseActivity implements TaskListener{

    private ArrayList<Account> accouts = new ArrayList<>();
    private ArrayList<Balance> balances = new ArrayList<>();
    private ArrayList<BondingState> bondings = new ArrayList<>();
    private ArrayList<UnBondingState> unbondings = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        accouts = getBaseDao().onSelectAccounts();
        if(accouts.size() > 0) {
            WLog.w("accouts : " +accouts.size());
            WLog.w("accouts : " +accouts.get(0).address);

            balances = getBaseDao().onSelectBalance(""+accouts.get(0).id);
            WLog.w("balances : " +balances.size());

            bondings = getBaseDao().onSelectBondingStates(""+accouts.get(0).id);
            WLog.w("bondings : " +bondings.size());

            unbondings = getBaseDao().onSelectUnbondingStates(""+accouts.get(0).id);
            WLog.w("unbondings : " +unbondings.size());
        }

        new AccountInfoTask(getBaseApplication(), this, accouts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new AllValidatorInfoTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new BondingStateTask(getBaseApplication(), this, accouts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new UnBondingStateTask(getBaseApplication(), this, accouts).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


    }


    @Override
    public void onTaskResponse(TaskResult result) {
        WLog.w("onTaskResponse : " + result.taskType + "  " + result.isSuccess);
    }
}
