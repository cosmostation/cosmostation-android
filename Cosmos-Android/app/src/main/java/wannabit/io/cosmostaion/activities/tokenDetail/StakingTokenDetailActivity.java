package wannabit.io.cosmostaion.activities.tokenDetail;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.activities.WebActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.network.res.ResApiTxList;
import wannabit.io.cosmostaion.network.res.ResApiTxListCustom;
import wannabit.io.cosmostaion.task.FetchTask.ApiTokenTxsHistoryTask;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.HistoryHolder;
import wannabit.io.cosmostaion.widget.TokenAkashHolder;
import wannabit.io.cosmostaion.widget.TokenCosmosHolder;
import wannabit.io.cosmostaion.widget.TokenCrytoHolder;
import wannabit.io.cosmostaion.widget.TokenIrisHolder;
import wannabit.io.cosmostaion.widget.TokenKavaHolder;
import wannabit.io.cosmostaion.widget.TokenPersisHolder;
import wannabit.io.cosmostaion.widget.VestingHolder;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CRYTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_SIMPLE_SEND;

public class StakingTokenDetailActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar             mToolbar;
    private ImageView           mBtnExplorer, mBtnAddressPopup;
    private RelativeLayout      mBtnSend;
    private ImageView           mKeyState;
    private TextView            mAddress;
    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;


    private StakingTokenAdapter             mAdapter;
    private ArrayList<ResApiTxList.Data>    mApiTxHistory = new ArrayList<>();
    private ArrayList<ResApiTxListCustom>   mApiTxCustomHistory = new ArrayList<>();
    private Boolean                         mHasVesting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail_staking);

        mToolbar                = findViewById(R.id.tool_bar);
        mBtnExplorer            = findViewById(R.id.web_detail);
        mBtnAddressPopup        = findViewById(R.id.address_detail);
        mBtnSend                = findViewById(R.id.btn_send);
        mKeyState               = findViewById(R.id.img_account);
        mAddress                = findViewById(R.id.account_Address);
        mSwipeRefreshLayout     = findViewById(R.id.layer_refresher);
        mRecyclerView           = findViewById(R.id.recycler);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        if (isGRPC(mBaseChain) && getBaseDao().mGRpcAccount != null) {
            if (getBaseDao().onParseRemainVestingsByDenom(WDp.mainDenom(mBaseChain)).size() > 0) {
                mHasVesting = true;
            }

        } else if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
            if (getBaseDao().mKavaAccount.value.getCalcurateVestingCntByDenom(WDp.mainDenom(mBaseChain)) > 0) {
                mHasVesting = true;
            }
        }

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onFetchTokenHistory();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new StakingTokenAdapter();
        mRecyclerView.setAdapter(mAdapter);

        onFetchTokenHistory();
        mAddress.setText(mAccount.address);
        mKeyState.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        mBtnExplorer.setOnClickListener(this);
        mBtnAddressPopup.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    private void onFetchTokenHistory() {
        if (mApiTxHistory != null) { mApiTxHistory.clear(); }
        if (mApiTxCustomHistory != null) { mApiTxCustomHistory.clear(); }
        if (isGRPC(mBaseChain)) {
            new ApiTokenTxsHistoryTask(getBaseApplication(), this, mAccount.address, WDp.mainDenom(mBaseChain), mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else {
            new ApiTokenTxsHistoryTask(getBaseApplication(), this, mAccount.address, WDp.mainDenom(mBaseChain), mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }

    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_API_TOKEN_HISTORY) {
            mApiTxHistory = (ArrayList<ResApiTxList.Data>)result.resultData;
            mAdapter.notifyDataSetChanged();
        }
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mBtnExplorer)) {
            Intent webintent = new Intent(this, WebActivity.class);
            webintent.putExtra("address", mAccount.address);
            webintent.putExtra("chain", mBaseChain.getChain());
            webintent.putExtra("goMain", false);
            startActivity(webintent);

        } else if (v.equals(mBtnAddressPopup)) {
            Bundle bundle = new Bundle();
            bundle.putString("address", mAccount.address);
            if (TextUtils.isEmpty(mAccount.nickName)) { bundle.putString("title", getString(R.string.str_my_wallet) + mAccount.id); }
            else { bundle.putString("title", mAccount.nickName); }
            Dialog_AccountShow show = Dialog_AccountShow.newInstance(bundle);
            show.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(show, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mBtnSend)) {
            if (mAccount == null) return;
            if (!mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }

            if (isGRPC(mBaseChain)) {
                BigDecimal feeAmount = WUtil.getEstimateGasFeeAmount(getBaseContext(), mBaseChain, CONST_PW_TX_SIMPLE_SEND, 0);
                if (getBaseDao().getAvailable(WDp.mainDenom(mBaseChain)).compareTo(feeAmount) <= 0) {
                    Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(StakingTokenDetailActivity.this, SendActivity.class);
                intent.putExtra("sendTokenDenom", WDp.mainDenom(mBaseChain));
                startActivity(intent);
            }
        }

    }


    private class StakingTokenAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_ATOM              = 0;
        private static final int TYPE_IRIS              = 1;
        private static final int TYPE_AKASH             = 2;
        private static final int TYPE_PERSISTENCE       = 3;
        private static final int TYPE_CRYTO             = 4;


        private static final int TYPE_KAVA              = 40;

        private static final int TYPE_VESTING           = 99;
        private static final int TYPE_HISTORY           = 100;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_ATOM) {
                return new TokenCosmosHolder(getLayoutInflater().inflate(R.layout.layout_card_atom, viewGroup, false));

            } else if (viewType == TYPE_IRIS) {
                return new TokenIrisHolder(getLayoutInflater().inflate(R.layout.layout_card_iris, viewGroup, false));

            } else if (viewType == TYPE_AKASH) {
                return new TokenAkashHolder(getLayoutInflater().inflate(R.layout.layout_card_akash, viewGroup, false));

            } else if (viewType == TYPE_PERSISTENCE) {
                return new TokenPersisHolder(getLayoutInflater().inflate(R.layout.layout_card_persistence, viewGroup, false));

            } else if (viewType == TYPE_CRYTO) {
                return new TokenCrytoHolder(getLayoutInflater().inflate(R.layout.layout_card_cryto, viewGroup, false));

            }

            else if (viewType == TYPE_KAVA) {
                return new TokenKavaHolder(getLayoutInflater().inflate(R.layout.layout_card_kava, viewGroup, false));

            }

            else if (viewType == TYPE_VESTING) {
                return new VestingHolder(getLayoutInflater().inflate(R.layout.layout_vesting_schedule, viewGroup, false));

            } else if (viewType == TYPE_HISTORY) {
                return new HistoryHolder(getLayoutInflater().inflate(R.layout.item_history, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder holder, int position) {
            if (getItemViewType(position) == TYPE_HISTORY) {
                ResApiTxList.Data tx = null;
                if (mApiTxCustomHistory.size() > 0) {

                } else if (mApiTxHistory.size() > 0) {
                    if (mHasVesting) {
                        tx = mApiTxHistory.get(position - 2);
                    } else {
                        tx = mApiTxHistory.get(position - 1);
                    }
                    ((HistoryHolder)holder).onBindHistory(StakingTokenDetailActivity.this, tx, mAccount.address);
                }

            }
//            else if (getItemViewType(position) == TYPE_VESTING) {
//
//            }
            else {
                holder.onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), WDp.mainDenom(mBaseChain));
            }


        }

        @Override
        public int getItemCount() {
            int cnt = 1;
            if (mApiTxCustomHistory != null) {
                cnt = cnt + mApiTxCustomHistory.size();
            }
            if (mApiTxHistory != null) {
                cnt = cnt + mApiTxHistory.size();
            }
            if (mHasVesting) {
                cnt = cnt + 1;
            }
            return cnt;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                if (mBaseChain.equals(COSMOS_MAIN)) {
                    return TYPE_ATOM;

                } else if (mBaseChain.equals(IRIS_MAIN)) {
                    return TYPE_IRIS;

                } else if (mBaseChain.equals(AKASH_MAIN)) {
                    return TYPE_AKASH;

                } else if (mBaseChain.equals(PERSIS_MAIN)) {
                    return TYPE_PERSISTENCE;

                } else if (mBaseChain.equals(CRYTO_MAIN)) {
                    return TYPE_CRYTO;

                }

                else if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                    return TYPE_KAVA;

                }
                return -1;

            } else if (position == 1 && mHasVesting) {
                return TYPE_VESTING;

            } else {
                return TYPE_HISTORY;
            }
        }
    }
}
