package wannabit.io.cosmostaion.activities.txs.starname;

import static wannabit.io.cosmostaion.base.BaseConstant.IOV_MSG_TYPE_DELETE_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_MSG_TYPE_RENEW_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STARNAME_DOMAIN_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_STARNAME_RESOLVE;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

import starnamed.x.starname.v1beta1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.StarNameGrpcDomainInfoTask;
import wannabit.io.cosmostaion.task.gRpcTask.StarNameGrpcResolveTask;
import wannabit.io.cosmostaion.utils.StarnameAssets;
import wannabit.io.cosmostaion.utils.WDp;

public class StarNameAccountDetailActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar mToolbar;
    private TextView mToolTitle;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private Button mBtnDelete, mBtnRenew, mBtnEdit;

    private String mMyDomain;
    private String mMyAccount;
    private MyAccountAdapter mAdapter;
    private Types.Domain mDomain_gRPC;
    private Types.Account mAccountResolve_gRPC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starname_detail);
        mToolbar = findViewById(R.id.tool_bar);
        mToolTitle = findViewById(R.id.tool_title);
        mSwipeRefreshLayout = findViewById(R.id.layer_refresher);
        mRecyclerView = findViewById(R.id.recycler);
        mBtnDelete = findViewById(R.id.btn_delete);
        mBtnRenew = findViewById(R.id.btn_renew);
        mBtnEdit = findViewById(R.id.btn_edit);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = ChainFactory.getChain(mBaseChain);
        mMyDomain = getIntent().getStringExtra("domain");
        mMyAccount = getIntent().getStringExtra("account");

        mToolTitle.setText(getString(R.string.str_account_detail));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(StarNameAccountDetailActivity.this, R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(() -> onFetchData());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MyAccountAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mBtnDelete.setOnClickListener(this);
        mBtnRenew.setOnClickListener(this);
        mBtnEdit.setOnClickListener(this);

        onShowWaitDialog();
        onFetchData();
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

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnDelete)) {
            if (!mAccount.hasPrivateKey) {
                onInsertKeyDialog();
                return;
            }
            if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
                Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, DeleteStarNameActivity.class);
            intent.putExtra("ToDeleType", IOV_MSG_TYPE_DELETE_ACCOUNT);
            intent.putExtra("ToDeleDomain", mAccountResolve_gRPC.getDomain());
            intent.putExtra("ToDeleAccount", mAccountResolve_gRPC.getName().getValue());
            intent.putExtra("Time", mAccountResolve_gRPC.getValidUntil());
            startActivity(intent);

        } else if (v.equals(mBtnRenew)) {
            if (!mAccount.hasPrivateKey) {
                onInsertKeyDialog();
                return;
            }
            if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
                Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            BigDecimal available = getBaseDao().getAvailable(mChainConfig.mainDenom());
            BigDecimal starNameFee = getBaseDao().getStarNameRenewAccountFee(mDomain_gRPC.getType());
            if (available.compareTo(starNameFee) < 0) {
                Toast.makeText(this, R.string.error_not_enough_starname_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, ReNewStarNameActivity.class);
            intent.putExtra("ToRenewType", IOV_MSG_TYPE_RENEW_ACCOUNT);
            intent.putExtra("IsOpen", mDomain_gRPC.getType());
            intent.putExtra("ToRenewDomain", mMyDomain);
            intent.putExtra("ToRenewAccount", mMyAccount);
            intent.putExtra("Time", mAccountResolve_gRPC.getValidUntil());
            startActivity(intent);

        } else if (v.equals(mBtnEdit)) {
            if (!mAccount.hasPrivateKey) {
                onInsertKeyDialog();
                return;
            }
            if (!WDp.isTxFeePayable(this, getBaseDao(), mChainConfig)) {
                Toast.makeText(this, R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            BigDecimal available = getBaseDao().getAvailable(mChainConfig.mainDenom());
            BigDecimal starNameFee = getBaseDao().getReplaceFee();
            if (available.compareTo(starNameFee) < 0) {
                Toast.makeText(this, R.string.error_not_enough_starname_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, ReplaceStarNameActivity.class);
            intent.putExtra("IsDomain", false);
            intent.putExtra("ToReplaceDomain", mMyDomain);
            intent.putExtra("ToReplaceAccount", mMyAccount);
            startActivity(intent);

        }
    }

    private void onFetchData() {
        mTaskCount = 2;
        new StarNameGrpcDomainInfoTask(getBaseApplication(), this, mBaseChain, mMyDomain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new StarNameGrpcResolveTask(getBaseApplication(), this, mBaseChain, mMyAccount, mMyDomain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if (isFinishing()) return;
        if (result.taskType == TASK_GRPC_FETCH_STARNAME_DOMAIN_INFO) {
            if (result.isSuccess && result.resultData != null) {
                mDomain_gRPC = (Types.Domain) result.resultData;
            }

        } else if (result.taskType == TASK_GRPC_FETCH_STARNAME_RESOLVE) {
            if (result.isSuccess && result.resultData != null) {
                mAccountResolve_gRPC = (Types.Account) result.resultData;
            }

        }
        if (mTaskCount == 0) {
            onHideWaitDialog();
            mAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }

    private class MyAccountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_HEADER = 0;
        private static final int TYPE_RESOURCE = 1;
        private static final int TYPE_EMPTY = 2;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_HEADER) {
                return new MyAccountHeaderHolder(getLayoutInflater().inflate(R.layout.item_account_detail_header, viewGroup, false));
            } else if (viewType == TYPE_RESOURCE) {
                return new MyResourceHolder(getLayoutInflater().inflate(R.layout.item_starname_resource, viewGroup, false));
            } else if (viewType == TYPE_EMPTY) {
                return new MyResourceEmptyHolder(getLayoutInflater().inflate(R.layout.item_starname_resource_empty, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_HEADER) {
                final MyAccountHeaderHolder holder = (MyAccountHeaderHolder) viewHolder;
                if (mAccountResolve_gRPC != null) {
                    holder.itemStarName.setText(mAccountResolve_gRPC.getName().getValue() + "*" + mAccountResolve_gRPC.getDomain());
                    holder.itemExpireDate.setText(WDp.getDpTime(getBaseContext(), mAccountResolve_gRPC.getValidUntil() * 1000));
                    holder.itemAddressCnt.setText("" + mAccountResolve_gRPC.getResourcesCount());
                    holder.itemBtnWebLink.setOnClickListener(v -> {
                        Intent guideIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://starname.me/" + mAccountResolve_gRPC.getName().getValue() + "*" + mAccountResolve_gRPC.getDomain()));
                        startActivity(guideIntent);
                    });
                }

            } else if (getItemViewType(position) == TYPE_RESOURCE) {
                final MyResourceHolder holder = (MyResourceHolder) viewHolder;
                final Types.Resource resource = mAccountResolve_gRPC.getResources(position - 1);
                Picasso.get().load(StarnameAssets.getStarNameChainImgUrl(resource.getUri())).fit().into(holder.itemChainImg);
                holder.itemChainName.setText(StarnameAssets.getStarNameChainName(resource.getUri()));
                holder.itemAddress.setText(resource.getResource());
            }

        }

        @Override
        public int getItemCount() {
            if (mAccountResolve_gRPC != null && mAccountResolve_gRPC.getResourcesCount() > 0) {
                return mAccountResolve_gRPC.getResourcesCount() + 1;
            } else {
                return +2;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_HEADER;
            } else {
                if (mAccountResolve_gRPC != null && mAccountResolve_gRPC.getResourcesCount() > 0) {
                    return TYPE_RESOURCE;
                } else {
                    return TYPE_EMPTY;

                }
            }
        }


        public class MyAccountHeaderHolder extends RecyclerView.ViewHolder {
            private CardView itemRoot;
            private ImageView itemBtnWebLink;
            private TextView itemStarName, itemAddressCnt, itemExpireDate;

            public MyAccountHeaderHolder(View v) {
                super(v);
                itemRoot = itemView.findViewById(R.id.card_root);
                itemStarName = itemView.findViewById(R.id.starname_name);
                itemBtnWebLink = itemView.findViewById(R.id.web_detail);
                itemAddressCnt = itemView.findViewById(R.id.connected_addressed);
                itemExpireDate = itemView.findViewById(R.id.expire_date);
            }
        }

        public class MyResourceHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            ImageView itemChainImg;
            TextView itemChainName, itemAddress;

            public MyResourceHolder(View v) {
                super(v);
                itemRoot = itemView.findViewById(R.id.card_root);
                itemChainImg = itemView.findViewById(R.id.chain_img);
                itemChainName = itemView.findViewById(R.id.chain_name);
                itemAddress = itemView.findViewById(R.id.chain_address);
            }
        }

        public class MyResourceEmptyHolder extends RecyclerView.ViewHolder {

            public MyResourceEmptyHolder(View v) {
                super(v);
            }
        }
    }
}
