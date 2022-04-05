package wannabit.io.cosmostaion.activities.chains.starname;

import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_DELETE_DOMAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_RENEW_DOMAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.CONST_PW_TX_REPLACE_STARNAME;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_MSG_TYPE_DELETE_DOMAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_MSG_TYPE_RENEW_DOMAIN;
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
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.StarNameGrpcDomainInfoTask;
import wannabit.io.cosmostaion.task.gRpcTask.StarNameGrpcResolveTask;
import wannabit.io.cosmostaion.utils.StarnameAssets;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class StarNameDomainDetailActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar mToolbar;
    private TextView mToolTitle;
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private Button mBtnDelete, mBtnRenew, mBtnEdit;

    private String mMyDomain;
    private MyDomainAdapter mAdapter;
    private Types.Domain mDomain_gRPC;
    private Types.Account mDomainResolve_gRPC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starname_detail);
        mToolbar = findViewById(R.id.toolbar);
        mToolTitle = findViewById(R.id.tool_title);
        mSwipeRefreshLayout = findViewById(R.id.layer_refresher);
        mRecyclerView = findViewById(R.id.recycler);
        mBtnDelete = findViewById(R.id.btn_delete);
        mBtnRenew = findViewById(R.id.btn_renew);
        mBtnEdit = findViewById(R.id.btn_edit);

        account = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        baseChain = BaseChain.getChain(account.baseChain);
        mMyDomain = getIntent().getStringExtra("domain");
        WLog.w("mMyDomain " + mMyDomain);

        mToolTitle.setText(getString(R.string.str_domain_detail));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(this, R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(() -> onFetchData());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MyDomainAdapter();
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
            if (!account.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                showDialog(add);
                return;
            }
            if (mDomain_gRPC.getType().equals("open")) {
                Toast.makeText(getBaseContext(), R.string.error_cannot_delete_open_domain, Toast.LENGTH_SHORT).show();
                return;
            }
            BigDecimal available = getBaseDao().getAvailable(baseChain.getMainDenom());
            BigDecimal txFee = WUtil.getEstimateGasFeeAmount(this, baseChain, CONST_PW_TX_DELETE_DOMAIN, 0);
            if (available.compareTo(txFee) < 0) {
                Toast.makeText(this, R.string.error_not_enough_starname_fee, Toast.LENGTH_SHORT).show();
                return;
            }
            Intent intent = new Intent(this, DeleteStarNameActivity.class);
            intent.putExtra("ToDeleType", IOV_MSG_TYPE_DELETE_DOMAIN);
            intent.putExtra("ToDeleDomain", mDomain_gRPC.getName());
            intent.putExtra("Time", mDomain_gRPC.getValidUntil());
            startActivity(intent);

        } else if (v.equals(mBtnRenew)) {
            if (!account.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                showDialog(add);
                return;
            }

            BigDecimal available = getBaseDao().getAvailable(baseChain.getMainDenom());
            BigDecimal starNameFee = getBaseDao().getStarNameRenewDomainFee(mDomain_gRPC.getName(), mDomain_gRPC.getType());
            BigDecimal txFee = WUtil.getEstimateGasFeeAmount(getBaseContext(), baseChain, CONST_PW_TX_RENEW_DOMAIN, 0);
            if (available.compareTo(starNameFee.add(txFee)) < 0) {
                Toast.makeText(this, R.string.error_not_enough_starname_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, ReNewStarNameActivity.class);
            intent.putExtra("ToRenewType", IOV_MSG_TYPE_RENEW_DOMAIN);
            intent.putExtra("IsOpen", mDomain_gRPC.getType());
            intent.putExtra("ToRenewDomain", mMyDomain);
            intent.putExtra("Time", mDomain_gRPC.getValidUntil());
            startActivity(intent);

        } else if (v.equals(mBtnEdit)) {
            if (!account.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                showDialog(add);
                return;
            }

            BigDecimal available = getBaseDao().getAvailable(baseChain.getMainDenom());
            BigDecimal starNameFee = getBaseDao().getReplaceFee();
            BigDecimal txFee = WUtil.getEstimateGasFeeAmount(getBaseContext(), baseChain, CONST_PW_TX_REPLACE_STARNAME, 0);
            if (available.compareTo(starNameFee.add(txFee)) < 0) {
                Toast.makeText(this, R.string.error_not_enough_starname_fee, Toast.LENGTH_SHORT).show();
                return;
            }

            Intent intent = new Intent(this, ReplaceStarNameActivity.class);
            intent.putExtra("IsDomain", true);
            intent.putExtra("ToReplaceDomain", mMyDomain);
            startActivity(intent);

        }
    }

    private void onFetchData() {
        mTaskCount = 2;
        new StarNameGrpcDomainInfoTask(getBaseApplication(), this, baseChain, mMyDomain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        new StarNameGrpcResolveTask(getBaseApplication(), this, baseChain, "", mMyDomain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

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
                mDomainResolve_gRPC = (Types.Account) result.resultData;
            }

        }

        if (mTaskCount == 0) {
            onHideWaitDialog();
            mAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }


    private class MyDomainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_HEADER = 0;
        private static final int TYPE_RESOURCE = 1;
        private static final int TYPE_EMPTY = 2;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_HEADER) {
                return new MyDomainHeaderHolder(getLayoutInflater().inflate(R.layout.item_domain_detail_header, viewGroup, false));
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
                final MyDomainHeaderHolder holder = (MyDomainHeaderHolder) viewHolder;
                if (mDomain_gRPC != null && mDomainResolve_gRPC != null) {
                    holder.itemDomain.setText("*" + mDomain_gRPC.getName());
                    holder.itemType.setText(mDomain_gRPC.getType().toUpperCase());
                    if (mDomain_gRPC.getType().equals("open")) {
                        holder.itemType.setTextColor(getResources().getColor(R.color.colorIov));
                    } else {
                        holder.itemType.setTextColor(getResources().getColor(R.color.colorWhite));
                    }
                    holder.itemExpireDate.setText(WDp.getDpTime(getBaseContext(), mDomain_gRPC.getValidUntil() * 1000));
                    holder.itemAddressCnt.setText("" + mDomainResolve_gRPC.getResourcesCount());
                    holder.itemBtnWebLink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent guideIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://starname.me/" + "*" + mDomain_gRPC.getName()));
                            startActivity(guideIntent);
                        }
                    });
                }

            } else if (getItemViewType(position) == TYPE_RESOURCE) {
                final MyResourceHolder holder = (MyResourceHolder) viewHolder;
                final Types.Resource resource = mDomainResolve_gRPC.getResources(position - 1);
                Picasso.get().load(StarnameAssets.getStarNameChainImgUrl(resource.getUri())).fit().into(holder.itemChainImg);
                holder.itemChainName.setText(StarnameAssets.getStarNameChainName(resource.getUri()));
                holder.itemAddress.setText(resource.getResource());
            }

        }

        @Override
        public int getItemCount() {
            if (mDomainResolve_gRPC != null && mDomainResolve_gRPC.getResourcesCount() > 0) {
                return mDomainResolve_gRPC.getResourcesCount() + 1;
            }
            return 2;
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_HEADER;
            } else {
                if (mDomainResolve_gRPC != null && mDomainResolve_gRPC.getResourcesCount() > 0) {
                    return TYPE_RESOURCE;
                } else {
                    return TYPE_EMPTY;
                }
            }
        }


        public class MyDomainHeaderHolder extends RecyclerView.ViewHolder {
            private CardView itemRoot;
            private ImageView itemBtnWebLink;
            private TextView itemDomain, itemType, itemAddressCnt, itemExpireDate;

            public MyDomainHeaderHolder(View v) {
                super(v);
                itemRoot = itemView.findViewById(R.id.card_root);
                itemDomain = itemView.findViewById(R.id.starname_domain_name);
                itemBtnWebLink = itemView.findViewById(R.id.web_detail);
                itemType = itemView.findViewById(R.id.domain_type);
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
