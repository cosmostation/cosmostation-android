package wannabit.io.cosmostaion.base;

import android.app.Activity;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import java.math.BigDecimal;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.AppLockActivity;
import wannabit.io.cosmostaion.activities.IntroActivity;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.activities.PasswordSetActivity;
import wannabit.io.cosmostaion.activities.RestoreActivity;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.crypto.CryptoHelper;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.BondingState;
import wannabit.io.cosmostaion.dao.Reward;
import wannabit.io.cosmostaion.dao.UnBondingState;
import wannabit.io.cosmostaion.dialog.Dialog_Wait;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResCgcTic;
import wannabit.io.cosmostaion.network.res.ResCmcTic;
import wannabit.io.cosmostaion.network.res.ResLcdIrisPool;
import wannabit.io.cosmostaion.network.res.ResLcdIrisReward;
import wannabit.io.cosmostaion.network.res.ResStakingPool;
import wannabit.io.cosmostaion.task.FetchTask.AccountInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.AllValidatorInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.BnbTokenListTask;
import wannabit.io.cosmostaion.task.FetchTask.BondingStateTask;
import wannabit.io.cosmostaion.task.FetchTask.IrisPoolTask;
import wannabit.io.cosmostaion.task.FetchTask.IrisRewardTask;
import wannabit.io.cosmostaion.task.FetchTask.UnBondingStateTask;
import wannabit.io.cosmostaion.task.FetchTask.UnbondedValidatorInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.UnbondingValidatorInfoTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.SingleInflationTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.SingleProvisionsTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.SingleRewardTask;
import wannabit.io.cosmostaion.task.SingleFetchTask.SingleStakingPoolTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.FetchCallBack;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class BaseActivity extends AppCompatActivity implements TaskListener {

    protected BaseApplication               mApplication;
    protected BaseData                      mData;
    protected Dialog_Wait                   mDialogWait;
    protected boolean                       mNeedLeaveTime = true;

    public Account                          mAccount;
    public BaseChain                        mBaseChain;
    public ArrayList<Validator>             mOtherValidators = new ArrayList<>();
    public ArrayList<Validator>             mTopValidators = new ArrayList<>();
    public ArrayList<Validator>             mMyValidators = new ArrayList<>();
    public ArrayList<Validator>             mAllValidators = new ArrayList<>();
    public ArrayList<Balance>               mBalances = new ArrayList<>();
    public ArrayList<BondingState>          mBondings = new ArrayList<>();
    public ArrayList<UnBondingState>        mUnbondings = new ArrayList<>();
    public ArrayList<Reward>                mRewards = new ArrayList<>();
    public BigDecimal                       mInflation = BigDecimal.ZERO;
    public BigDecimal                       mProvisions = BigDecimal.ZERO;
    public BigDecimal                       mBondedToken = BigDecimal.ZERO;

    public ResLcdIrisReward                 mIrisReward;
    public ResLcdIrisPool                   mIrisPool;

    public ArrayList<BnbToken>              mBnbTokens = new ArrayList<>();


    private int                             mTaskCount;
    private FetchCallBack                   mFetchCallback;

    @Override
    protected void onStart() {
        super.onStart();
        if(!(this instanceof PasswordSetActivity) && !(this instanceof PasswordCheckActivity) && !(this instanceof IntroActivity)) {
            if(getBaseApplication().needShowLockScreen()) {
                Intent intent = new Intent(BaseActivity.this, AppLockActivity.class);
                startActivity(intent);
                overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
            }
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mNeedLeaveTime) {
            getBaseDao().setAppLockLeaveTime();
        }

    }

    public BaseApplication getBaseApplication() {
        if (mApplication == null)
            mApplication = (BaseApplication) getApplication();
        return mApplication;
    }

    public BaseData getBaseDao() {
        if (mData == null)
            mData = getBaseApplication().getBaseDao();
        return mData;
    }

    public void onShowWaitDialog() {
        mDialogWait = new Dialog_Wait();
        mDialogWait.setCancelable(false);
        getSupportFragmentManager().beginTransaction().add(mDialogWait, "wait").commitNowAllowingStateLoss();
    }

    public void onHideWaitDialog() {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment prev = getSupportFragmentManager().findFragmentByTag("wait");
        if (prev != null) {
            ft.remove(prev).commitNowAllowingStateLoss();
        }
    }

    public void onHideKeyboard() {
        InputMethodManager imm = (InputMethodManager)getSystemService(Activity.INPUT_METHOD_SERVICE);
        View v = getCurrentFocus();
        if (v == null) {
            v = new View(getBaseContext());
        }
        imm.hideSoftInputFromWindow(v.getWindowToken(), 0);
        v.clearFocus();
    }

    public void onStartMainActivity() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void onChoiceNet(BaseChain chain) { }

    public void onShare(boolean isText) { }

    public void onShareType() { }

    public void onDeleteAccount(long id) {
        try {
            CryptoHelper.deleteKey(getString(R.string.key_mnemonic) + getBaseDao().onSelectAccount(""+id).uuid);
        } catch (Exception e) { }
        getBaseDao().onDeleteAccount(""+id);
        getBaseDao().onSelectBalance(id);
        getBaseDao().onDeleteBondingStates(id);
        getBaseDao().onDeleteUnbondingStates(id);

        if(getBaseDao().onSelectAccounts().size() > 0) {
            getBaseDao().setLastUser(getBaseDao().onSelectAccounts().get(0).id);
            onStartMainActivity();
        } else {
            getBaseDao().setLastUser(-1);
            Intent intent = new Intent(this, IntroActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(intent);
        }
    }

    public void onAddMnemonicForAccount() {
        startActivity(new Intent(BaseActivity.this, RestoreActivity.class));
    }



    public void onFetchAccountInfo(FetchCallBack callback) {
        if (mTaskCount > 0) {
//            return false;
            callback.fetchBusy();
        }

        ArrayList<Account> accounts = new ArrayList<Account>();
        accounts.add(mAccount);
        mFetchCallback = callback;
        mOtherValidators.clear();
        mAllValidators.clear();

        if (mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
            mTaskCount = 9;

            new AllValidatorInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnbondingValidatorInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnbondedValidatorInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new AccountInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new SingleInflationTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleProvisionsTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new SingleStakingPoolTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);



        } else if (mAccount.baseChain.equals(BaseChain.IRIS_MAIN.getChain())) {
            mTaskCount = 6;

            new AllValidatorInfoTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new AccountInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new UnBondingStateTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new IrisRewardTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

            new IrisPoolTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mAccount.baseChain.equals(BaseChain.BNB_MAIN.getChain())) {
            mTaskCount = 2;

            new AccountInfoTask(getBaseApplication(), this, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new BnbTokenListTask(getBaseApplication(), this).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);


        }
        onPriceTic(BaseChain.getChain(mAccount.baseChain));
//        return true;
    }

    @Override
    public void onTaskResponse(TaskResult result) {
//        WLog.w("onTaskResponse " + result.taskType + "   " + mTaskCount);
        mTaskCount--;
        if(isFinishing()) return;

        if (result.taskType == BaseConstant.TASK_FETCH_ACCOUNT) {
            mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
            mBalances = getBaseDao().onSelectBalance(mAccount.id);

        } else if (result.taskType == BaseConstant.TASK_FETCH_ALL_VALIDATOR) {
            if (!result.isSuccess) {
                Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                return;
            }
            ArrayList<Validator> temp = (ArrayList<Validator>)result.resultData;
            if (mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
                if(temp != null) {
                    mTopValidators = temp;
                }
            } else if (mAccount.baseChain.equals(BaseChain.IRIS_MAIN.getChain())) {
                mTopValidators = WUtil.getIrisTops(temp);
                mOtherValidators = WUtil.getIrisOthers(temp);
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_UNBONDING_VALIDATOR ||
                result.taskType == BaseConstant.TASK_FETCH_UNBONDED_VALIDATOR) {
            ArrayList<Validator> temp = (ArrayList<Validator>)result.resultData;
            if(temp != null) {
                mOtherValidators.addAll(temp);
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_BONDING_STATE) {
            mBondings = getBaseDao().onSelectBondingStates(mAccount.id);
            if (mAccount.baseChain.equals(BaseChain.COSMOS_MAIN.getChain())) {
                mTaskCount = mTaskCount + mBondings.size();
                mRewards.clear();
                for(BondingState bonding:mBondings) {
                    new SingleRewardTask(getBaseApplication(), this, mAccount, bonding.validatorAddress).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
                }
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_UNBONDING_STATE) {
            mUnbondings = getBaseDao().onSelectUnbondingStates(mAccount.id);

        } else if (result.taskType == BaseConstant.TASK_FETCH_SINGLE_REWARD) {
            Reward reward = (Reward)result.resultData;
            if(reward != null)
                onUpdateReward(reward);

        } else if (result.taskType == BaseConstant.TASK_FETCH_INFLATION) {
            try {
                mInflation = new BigDecimal((String)result.resultData);
            } catch (Exception e) {}

        } else if (result.taskType == BaseConstant.TASK_FETCH_PROVISIONS) {
            try {
                mProvisions = new BigDecimal((String)result.resultData);
            } catch (Exception e) {}

        } else if (result.taskType == BaseConstant.TASK_FETCH_STAKING_POOL) {
            try {
                mBondedToken = new BigDecimal(((ResStakingPool)result.resultData).result.bonded_tokens);
            } catch (Exception e) {}

        } else if (result.taskType == BaseConstant.TASK_IRIS_REWARD) {
            mIrisReward = (ResLcdIrisReward)result.resultData;

        } else if (result.taskType == BaseConstant.TASK_IRIS_POOL) {
            mIrisPool = (ResLcdIrisPool)result.resultData;

        } else if (result.taskType == BaseConstant.TASK_FETCH_BNB_TOKENS) {
            mBnbTokens = (ArrayList<BnbToken>)result.resultData;
            WLog.w("mBnbTokens " + mBnbTokens.size());
        }



        if(mTaskCount == 0) {
            mMyValidators.clear();
            for(Validator top:mTopValidators) {
                boolean already = false;
                for(BondingState bond:mBondings) {
                    if(bond.validatorAddress.equals(top.operator_address)) {
                        already = true;
                        break;
                    }
                }
                for(UnBondingState unbond:mUnbondings) {
                    if(unbond.validatorAddress.equals(top.operator_address) && !already) {
                        already = true;
                        break;
                    }
                }

                if(already) mMyValidators.add(top);
            }

            for(Validator other:mOtherValidators) {
                boolean already = false;
                for(BondingState bond:mBondings) {
                    if(bond.validatorAddress.equals(other.operator_address)) {
                        already = true;
                        break;
                    }
                }
                for(UnBondingState unbond:mUnbondings) {
                    if(unbond.validatorAddress.equals(other.operator_address) && !already) {
                        already = true;
                        break;
                    }
                }

                if(already) mMyValidators.add(other);
            }
            mAllValidators.addAll(mTopValidators);
            mAllValidators.addAll(mOtherValidators);
//            onHideWaitDialog();
//            onFetchCurrentPage();
            if(mFetchCallback != null) {
                mFetchCallback.fetchFinished();
            }
        }
    }

    private void onUpdateReward(Reward reward) {
        if(mRewards == null) mRewards = new ArrayList<>();
        if(mRewards.size() == 0) {
            mRewards.add(reward);
        } else {
            int match = -1;
            for(int i = 0; i < mRewards.size(); i++) {
                if(mRewards.get(i).validatorAddress.equals(reward.validatorAddress)) {
                    match = i; break;
                }
            }
            if(match > 0) {
                mRewards.remove(match);
            }
            mRewards.add(reward);
        }
    }

    public void onPriceTic(final BaseChain chain) {
        if (getBaseDao().getMarket() == 0) {
            ApiClient.getCGCClient(getBaseContext()).getPriceTic(WUtil.getCGCId(chain)).enqueue(new Callback<ResCgcTic>() {
                @Override
                public void onResponse(Call<ResCgcTic> call, Response<ResCgcTic> response) {
                    if(isFinishing()) return;
                    try {
                        getBaseDao().setLastPriceTic(chain, response.body());
                    } catch (Exception e) {
                        if (chain.equals(BaseChain.COSMOS_MAIN)) {
                            getBaseDao().setLastAtomTic(0d);
                            getBaseDao().setLastAtomUpDown(0d);

                        } else if (chain.equals(BaseChain.IRIS_MAIN)) {
                            getBaseDao().setLastIrisTic(0d);
                            getBaseDao().setLastIrisUpDown(0d);

                        } else if (chain.equals(BaseChain.BNB_MAIN)) {
                            getBaseDao().setLastBnbTic(0d);
                            getBaseDao().setLastBnbUpDown(0d);

                        }
                    }
                }

                @Override
                public void onFailure(Call<ResCgcTic> call, Throwable t) {
                    if (chain.equals(BaseChain.COSMOS_MAIN)) {
                        getBaseDao().setLastAtomTic(0d);
                        getBaseDao().setLastAtomUpDown(0d);

                    } else if (chain.equals(BaseChain.IRIS_MAIN)) {
                        getBaseDao().setLastIrisTic(0d);
                        getBaseDao().setLastIrisUpDown(0d);

                    } else if (chain.equals(BaseChain.BNB_MAIN)) {
                        getBaseDao().setLastBnbTic(0d);
                        getBaseDao().setLastBnbUpDown(0d);

                    }
                }
            });

        } else if (getBaseDao().getMarket() == 1) {
            ApiClient.getCMCClient(getBaseContext()).getPriceTic(WUtil.getCMCId(chain), getBaseDao().getCurrencyString()).enqueue(new Callback<JsonObject>() {
                @Override
                public void onResponse(Call<JsonObject> call, Response<JsonObject> response) {
                    if(isFinishing()) return;
                    try {
                        if(response.isSuccessful() && chain.equals(BaseChain.COSMOS_MAIN)) {
                            ResCmcTic mResCmcTic = new Gson().fromJson(response.body(), ResCmcTic.class);
                            getBaseDao().setLastAtomTic(mResCmcTic.getData().getQuotesMap().get(getBaseDao().getCurrencyString()).getPrice());
                            getBaseDao().setLastAtomUpDown(mResCmcTic.getData().getQuotesMap().get(getBaseDao().getCurrencyString()).getPercent_change_24h());

                        } else if (response.isSuccessful() && chain.equals(BaseChain.IRIS_MAIN)) {
                            ResCmcTic mResCmcTic = new Gson().fromJson(response.body(), ResCmcTic.class);
                            getBaseDao().setLastIrisTic(mResCmcTic.getData().getQuotesMap().get(getBaseDao().getCurrencyString()).getPrice());
                            getBaseDao().setLastIrisUpDown(mResCmcTic.getData().getQuotesMap().get(getBaseDao().getCurrencyString()).getPercent_change_24h());

                        } else if (response.isSuccessful() && chain.equals(BaseChain.BNB_MAIN)) {
                            ResCmcTic mResCmcTic = new Gson().fromJson(response.body(), ResCmcTic.class);
                            getBaseDao().setLastBnbTic(mResCmcTic.getData().getQuotesMap().get(getBaseDao().getCurrencyString()).getPrice());
                            getBaseDao().setLastBnbUpDown(mResCmcTic.getData().getQuotesMap().get(getBaseDao().getCurrencyString()).getPercent_change_24h());

                        }

                    } catch (Exception e) {
                        if (chain.equals(BaseChain.COSMOS_MAIN)) {
                            getBaseDao().setLastAtomTic(0d);
                            getBaseDao().setLastAtomUpDown(0d);

                        } else if (chain.equals(BaseChain.IRIS_MAIN)) {
                            getBaseDao().setLastIrisTic(0d);
                            getBaseDao().setLastIrisUpDown(0d);

                        } else if (chain.equals(BaseChain.BNB_MAIN)) {
                            getBaseDao().setLastBnbTic(0d);
                            getBaseDao().setLastBnbUpDown(0d);

                        }
                    }
                }

                @Override
                public void onFailure(Call<JsonObject> call, Throwable t) {
                    if (chain.equals(BaseChain.COSMOS_MAIN)) {
                        getBaseDao().setLastAtomTic(0d);
                        getBaseDao().setLastAtomUpDown(0d);

                    } else if (chain.equals(BaseChain.IRIS_MAIN)) {
                        getBaseDao().setLastIrisTic(0d);
                        getBaseDao().setLastIrisUpDown(0d);

                    } else if (chain.equals(BaseChain.BNB_MAIN)) {
                        getBaseDao().setLastBnbTic(0d);
                        getBaseDao().setLastBnbUpDown(0d);

                    }

                }
            });
        }
    }

}
