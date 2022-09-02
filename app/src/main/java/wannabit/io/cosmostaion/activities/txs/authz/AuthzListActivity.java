package wannabit.io.cosmostaion.activities.txs.authz;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import cosmos.base.v1beta1.CoinOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.AuthzGranterGrpcTask;
import wannabit.io.cosmostaion.task.gRpcTask.BalanceGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;


public class AuthzListActivity extends BaseActivity implements TaskListener {

    private Toolbar mToolbar;
    private TextView mTitle;

    private RelativeLayout mLoadingLayer;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private AuthzListAdapter mAuthzListAdapter;

    private ArrayList<String> mGranters = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authz_list);
        mToolbar = findViewById(R.id.tool_bar);
        mTitle = findViewById(R.id.toolbar_title);
        mSwipeRefreshLayout = findViewById(R.id.layer_refresher);
        mRecyclerView = findViewById(R.id.recycler);
        mLoadingLayer = findViewById(R.id.loadingLayer);
        mTitle.setText(getString(R.string.str_authz_list_title));

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(AuthzListActivity.this, R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onFetchAuthzGranterListInfo();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAuthzListAdapter = new AuthzListAdapter();
        mRecyclerView.setAdapter(mAuthzListAdapter);

        onFetchAuthzGranterListInfo();
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

    private void onFetchAuthzGranterListInfo() {
        new AuthzGranterGrpcTask(getBaseApplication(), result -> {
            mGranters = (ArrayList<String>) result.resultData;
            runOnUiThread(() -> onUpdateView());

        }, mBaseChain, mAccount).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    private void onUpdateView() {
        mSwipeRefreshLayout.setRefreshing(false);
        mLoadingLayer.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.VISIBLE);
        mAuthzListAdapter.notifyDataSetChanged();
    }

    private class AuthzListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_AUTHZ_LIST = 1;
        private static final int TYPE_AUTHZ_EMPTY = 2;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_AUTHZ_LIST) {
                return new GranterHolder(getLayoutInflater().inflate(R.layout.item_authz_granter_list, viewGroup, false));
            } else {
                return new EmptyHolder(getLayoutInflater().inflate(R.layout.item_authz_empty, viewGroup, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_AUTHZ_LIST) {
                onBindGranterItemViewHolder(viewHolder, position);
            } else {
                onBindEmptyItemViewHolder(viewHolder);
            }
        }

        private void onBindGranterItemViewHolder(RecyclerView.ViewHolder viewHolder, int position) {
            final GranterHolder holder = (GranterHolder) viewHolder;
            final ChainConfig chainConfig = ChainFactory.getChain(mBaseChain);
            final String granter = mGranters.get(position);
            holder.granterAddress.setText(granter);

            new Thread(() -> {
                new BalanceGrpcTask(getBaseApplication(), result -> {
                    ArrayList<CoinOuterClass.Coin> balances = (ArrayList<CoinOuterClass.Coin>) result.resultData;
                    if (balances != null && balances.size() > 0) {
                        for (CoinOuterClass.Coin coin : balances) {
                            if (coin.getDenom().equalsIgnoreCase(chainConfig.mainDenom())) {
                                Coin tempCoin = new Coin(coin.getDenom(), coin.getAmount());
                                runOnUiThread(() -> {
                                    WDp.setDpCoin(AuthzListActivity.this, getBaseDao(), chainConfig, tempCoin, holder.granterDenom, holder.granterAvailable);
                                });
                                return;
                            }
                        }
                    }
                }, mBaseChain, granter).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }).start();

            holder.granterLayer.setOnClickListener(view -> {
                Intent intent = new Intent(AuthzListActivity.this, AuthzDetailActivity.class);
                intent.putExtra("granter", granter);
                startActivity(intent);
            });
        }

        private void onBindEmptyItemViewHolder(RecyclerView.ViewHolder viewHolder) {
            final EmptyHolder holder = (EmptyHolder) viewHolder;
            final ChainConfig chainConfig = ChainFactory.getChain(mBaseChain);
            holder.emptyLayer.setCardBackgroundColor(ContextCompat.getColor(AuthzListActivity.this, chainConfig.chainBgColor()));
        }

        @Override
        public int getItemCount() {
            if (mGranters == null || mGranters.size() < 1) {
                return 1;
            } else {
                return mGranters.size();
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (mGranters == null || mGranters.size() < 1) {
                return TYPE_AUTHZ_EMPTY;
            } else {
                return TYPE_AUTHZ_LIST;
            }
        }

        public class GranterHolder extends RecyclerView.ViewHolder {
            LinearLayout granterLayer;
            TextView granterAddress, granterAvailable, granterDenom;

            public GranterHolder(@NonNull View itemView) {
                super(itemView);
                granterLayer = itemView.findViewById(R.id.granter_layer);
                granterAddress = itemView.findViewById(R.id.granter_address);
                granterAvailable = itemView.findViewById(R.id.granterAvailable);
                granterDenom = itemView.findViewById(R.id.granterDenom);
            }
        }

        public class EmptyHolder extends RecyclerView.ViewHolder {
            CardView emptyLayer;

            public EmptyHolder(@NonNull View itemView) {
                super(itemView);
                emptyLayer = itemView.findViewById(R.id.card_root);
            }
        }
    }
}


