package wannabit.io.cosmostaion.activities.tokenDetail;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.SendActivity;
import wannabit.io.cosmostaion.activities.TokenDetailActivity;
import wannabit.io.cosmostaion.activities.WebActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.network.res.ResApiTxList;
import wannabit.io.cosmostaion.network.res.ResApiTxListCustom;
import wannabit.io.cosmostaion.task.FetchTask.ApiStakeTxsHistoryTask;
import wannabit.io.cosmostaion.task.FetchTask.ApiTokenTxsHistoryTask;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.HistoryHolder;
import wannabit.io.cosmostaion.widget.TokenDetailBaseHolder;
import wannabit.io.cosmostaion.widget.TokenDetailHardHolder;
import wannabit.io.cosmostaion.widget.VestingHolder;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class NativeTokenDetailActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar                 mToolbar;
    private ImageView               mBtnExplorer, mBtnAddressPopup;
    private ImageView               mKeyState;
    private TextView                mAddress;
    private SwipeRefreshLayout      mSwipeRefreshLayout;
    private RecyclerView            mRecyclerView;

    private RelativeLayout          mBtnSend;
    private LinearLayout            mBep3Layer;
    private RelativeLayout          mBtnBep3Send, mBtnBep3SimpleSend;

    private NativeTokenAdapter      mAdapter;

    private String                          mDenom;
    private ArrayList<ResApiTxList.Data>    mApiTxHistory = new ArrayList<>();
    private ArrayList<ResApiTxListCustom>   mApiTxCustomHistory = new ArrayList<>();
    private Boolean                         mHasVesting = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail_native);

        mToolbar                = findViewById(R.id.tool_bar);
        mBtnExplorer            = findViewById(R.id.web_detail);
        mBtnAddressPopup        = findViewById(R.id.address_detail);
        mKeyState               = findViewById(R.id.img_account);
        mAddress                = findViewById(R.id.account_Address);
        mSwipeRefreshLayout     = findViewById(R.id.layer_refresher);
        mRecyclerView           = findViewById(R.id.recycler);
        mBtnSend                = findViewById(R.id.btn_send);
        mBep3Layer              = findViewById(R.id.layer_bep3);
        mBtnBep3Send            = findViewById(R.id.btn_bep3_send);
        mBtnBep3SimpleSend      = findViewById(R.id.btn_bep3_simple_send);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mDenom = getIntent().getStringExtra("denom");
        //check vesting
        if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
            if (getBaseDao().mKavaAccount.value.getCalcurateVestingCntByDenom(mDenom) > 0) {
                mHasVesting = true;
            }
        }
        //check is bep3 sendable!!
        if (WUtil.isBep3Coin(mDenom)) { mBep3Layer.setVisibility(View.VISIBLE); }
        else { mBtnSend.setVisibility(View.VISIBLE); }

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onFetchTokenHistory();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new NativeTokenAdapter();
        mRecyclerView.setAdapter(mAdapter);

        onFetchTokenHistory();
        mAddress.setText(mAccount.address);
        mKeyState.setColorFilter(WDp.getChainColor(getBaseContext(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        mBtnExplorer.setOnClickListener(this);
        mBtnAddressPopup.setOnClickListener(this);
        mBtnSend.setOnClickListener(this);
        mBtnBep3Send.setOnClickListener(this);
        mBtnBep3SimpleSend.setOnClickListener(this);
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
        mApiTxHistory.clear();
        mApiTxCustomHistory.clear();
        if (mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(IRIS_MAIN)) {

        } else if (mBaseChain.equals(COSMOS_TEST) || mBaseChain.equals(IRIS_TEST)) {

        } else if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
            new ApiTokenTxsHistoryTask(getBaseApplication(), this, mAccount.address, mDenom, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        } else if (mBaseChain.equals(SIF_MAIN)) {
            new ApiTokenTxsHistoryTask(getBaseApplication(), this, mAccount.address, mDenom, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
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

        } else if (v.equals(mBtnSend) || v.equals(mBtnBep3SimpleSend)) {
            Intent intent = new Intent(NativeTokenDetailActivity.this, SendActivity.class);
            intent.putExtra("sendTokenDenom", mDenom);
            startActivity(intent);

        } else if (v.equals(mBtnBep3Send)) {
            onStartHTLCSendActivity(mDenom);

        }

    }


    private class NativeTokenAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_UNKNOWN               = -1;
        private static final int TYPE_BASE_COSMOS         = 0;
        private static final int TYPE_BASE_IRIS           = 1;
        private static final int TYPE_BASE_KAVA           = 2;
        private static final int TYPE_BASE_SIF            = 3;

        private static final int TYPE_HARD                  = 98;
        private static final int TYPE_VESTING               = 99;
        private static final int TYPE_HISTORY               = 100;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_UNKNOWN) {

            } else if (viewType == TYPE_BASE_COSMOS) {

            } else if (viewType == TYPE_BASE_IRIS) {

            } else if (viewType == TYPE_BASE_KAVA) {
                return new TokenDetailBaseHolder(getLayoutInflater().inflate(R.layout.item_token_base, viewGroup, false));

            } else if (viewType == TYPE_BASE_SIF) {
                return new TokenDetailBaseHolder(getLayoutInflater().inflate(R.layout.item_token_base, viewGroup, false));

            }


            else if (viewType == TYPE_HARD) {
                return new TokenDetailHardHolder(getLayoutInflater().inflate(R.layout.item_token_hard, viewGroup, false));

            } else if (viewType == TYPE_VESTING) {
                return new VestingHolder(getLayoutInflater().inflate(R.layout.layout_vesting_schedule, viewGroup, false));

            } else if (viewType == TYPE_HISTORY) {
                return new HistoryHolder(getLayoutInflater().inflate(R.layout.item_history, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_BASE_COSMOS) {

            } else if (getItemViewType(position) == TYPE_BASE_IRIS) {

            } else if (getItemViewType(position) == TYPE_BASE_KAVA) {
                ((TokenDetailBaseHolder)viewHolder).onBindKavaToken(getBaseContext(), mBaseChain, getBaseDao(), mDenom);

            } else if (getItemViewType(position) == TYPE_BASE_SIF) {
                ((TokenDetailBaseHolder)viewHolder).onBindSifToken(getBaseContext(), mBaseChain, getBaseDao(), mDenom);

            } else if (getItemViewType(position) == TYPE_HARD) {
                ((TokenDetailHardHolder)viewHolder).onBindHard(getBaseContext(), mBaseChain, getBaseDao());

            } else if (getItemViewType(position) == TYPE_VESTING) {
                ((VestingHolder)viewHolder).onBindTokenHolder(getBaseContext(), mBaseChain, getBaseDao(), mDenom);

            } else if (getItemViewType(position) == TYPE_HISTORY) {
                ResApiTxList.Data tx = null;
                if (mApiTxCustomHistory.size() > 0) {

                } else if (mApiTxHistory.size() > 0) {
                    if (mHasVesting) {
                        tx = mApiTxHistory.get(position - 2);
                    } else {
                        tx = mApiTxHistory.get(position - 1);
                    }
                    ((HistoryHolder)viewHolder).onBindHistory(NativeTokenDetailActivity.this, tx, mAccount.address);
                }

            } else if (getItemViewType(position) == TYPE_UNKNOWN) {

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
            if (mBaseChain.equals(COSMOS_MAIN) || mBaseChain.equals(COSMOS_TEST)) {
                if (position == 0) return TYPE_BASE_COSMOS;
                else return TYPE_HISTORY;

            } else if (mBaseChain.equals(IRIS_MAIN) || mBaseChain.equals(IRIS_TEST)) {
                if (position == 0) return TYPE_BASE_IRIS;
                else return TYPE_HISTORY;

            } else if (mBaseChain.equals(KAVA_MAIN) || mBaseChain.equals(KAVA_TEST)) {
                if (mDenom.equals(TOKEN_HARD)) {
                    if (mHasVesting) {
                        if (position == 0) return TYPE_HARD;
                        if (position == 1) return TYPE_VESTING;
                        else return TYPE_HISTORY;
                    } else {
                        if (position == 0) return TYPE_HARD;
                        else return TYPE_HISTORY;
                    }

                } else {
                    if (position == 0) return TYPE_BASE_KAVA;
                    else return TYPE_HISTORY;
                }

            } else if (mBaseChain.equals(SIF_MAIN)) {
                if (position == 0) return TYPE_BASE_SIF;
                else return TYPE_HISTORY;

            }
            return TYPE_UNKNOWN;
        }
    }
}
