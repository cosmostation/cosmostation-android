package wannabit.io.cosmostaion.activities;

import android.os.Bundle;

import com.google.gson.JsonObject;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBlockInfo;
import wannabit.io.cosmostaion.network.res.ResBroadTx;
import wannabit.io.cosmostaion.network.res.ResLcdAccountInfo;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class TxResultActivity extends BaseActivity {

    private ResBroadTx                  mResBroadTx;
    private ResTxInfo                   mResTxInfo;
    private ResBlockInfo                mResBlockInfo;
    private int                         mTxType;
    private Account                     mAccount;
    public ArrayList<Balance>           mBalances = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tx_result);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mAccount    = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBalances   = getBaseDao().onSelectBalance(mAccount.id);
        mResBroadTx = getBaseDao().getTxResult();
        mTxType     = getIntent().getIntExtra("txType", BaseConstant.TASK_GEN_TX_SIMPLE_SEND);
//        if(mResBroadTx == null) {
//            onStartMainActivity();
//        } else {
            onFetchTx();
//        }
    }


    private void onUpdateViews() {
        WLog.w("onUpdateViews");
//        String hash = "5FD8F3AE1DDDFCDE4D45331862341C40A527B3F648AC93E2BF83DDC24F570902";
    }

    private void onUpdateTimes() {
        WLog.w("onUpdateTimes");
    }

    private void onUpdateBalances() {
        WLog.w("onUpdateBalances");
        mBalances = getBaseDao().onSelectBalance(mAccount.id);
    }


    private void onFetchTx() {
        ApiClient.getWannabitChain(getBaseContext(), BaseChain.getChain(mAccount.baseChain)).getSearchTx(mResBroadTx.hash).enqueue(new Callback<ResTxInfo>() {
            @Override
            public void onResponse(Call<ResTxInfo> call, Response<ResTxInfo> response) {
                if(response.isSuccessful() && response.body() != null) {
                    WLog.w("getSearchTx : " + response.body().height);
                    mResTxInfo = response.body();
                    onUpdateViews();

                } else {
                    WLog.w("onFetchTx Error!!");
                }
            }

            @Override
            public void onFailure(Call<ResTxInfo> call, Throwable t) {
                WLog.w("onFailure " + t.getMessage());
                t.printStackTrace();
            }
        });
    }

    private void onFetchBlock(String height) {
        ApiClient.getWannabitChain(getBaseContext(), BaseChain.getChain(mAccount.baseChain)).getSearchBlock(height).enqueue(new Callback<ResBlockInfo>() {
            @Override
            public void onResponse(Call<ResBlockInfo> call, Response<ResBlockInfo> response) {
                if (response.isSuccessful() && response.body() != null) {
                    WLog.w("onFetchBlock : " + response.body().block_meta.header.time);
                    mResBlockInfo = response.body();
                    onUpdateTimes();
                }
            }

            @Override
            public void onFailure(Call<ResBlockInfo> call, Throwable t) {

            }
        });
    }

    private void onFetchSingleAccount() {
        ApiClient.getWannabitChain(getBaseContext(), BaseChain.getChain(mAccount.baseChain)).getAccountInfo(mAccount.address).enqueue(new Callback<ResLcdAccountInfo>() {
            @Override
            public void onResponse(Call<ResLcdAccountInfo> call, Response<ResLcdAccountInfo> response) {
                if(response != null) {
                    getBaseDao().onUpdateAccount(WUtil.getAccountFromLcd(mAccount.id, response.body()));
                    getBaseDao().onUpdateBalances(WUtil.getBalancesFromLcd(mAccount.id, response.body()));
                    onUpdateBalances();
                }
            }

            @Override
            public void onFailure(Call<ResLcdAccountInfo> call, Throwable t) {

            }
        });
    }
}
