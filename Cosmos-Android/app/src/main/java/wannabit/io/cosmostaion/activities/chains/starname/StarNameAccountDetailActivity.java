package wannabit.io.cosmostaion.activities.chains.starname;

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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.model.StarNameDomain;
import wannabit.io.cosmostaion.model.StarNameResource;
import wannabit.io.cosmostaion.network.res.ResIovStarNameResolve;
import wannabit.io.cosmostaion.task.FetchTask.StarNameDomainInfoTask;
import wannabit.io.cosmostaion.task.FetchTask.StarNameGrpcResolveTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_MSG_TYPE_DELETE_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.IOV_MSG_TYPE_RENEW_ACCOUNT;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_STARNAME_DOMAIN_INFO;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_STARNAME_RESOLVE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;

public class StarNameAccountDetailActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar                     mToolbar;
    private TextView                    mToolTitle;
    private SwipeRefreshLayout          mSwipeRefreshLayout;
    private RecyclerView                mRecyclerView;
    private Button                      mBtnDelete, mBtnRenew, mBtnEdit;

    private String                              mMyDomain;
    private String                              mMyAccount;
    private StarNameDomain                      mStarNameDomain;
    private ResIovStarNameResolve.NameAccount   mMyNameAccount;
    private MyAccountAdapter                    mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_starname_detail);
        mToolbar                = findViewById(R.id.tool_bar);
        mToolTitle              = findViewById(R.id.tool_title);
        mSwipeRefreshLayout     = findViewById(R.id.layer_refresher);
        mRecyclerView           = findViewById(R.id.recycler);
        mBtnDelete              = findViewById(R.id.btn_delete);
        mBtnRenew               = findViewById(R.id.btn_renew);
        mBtnEdit                = findViewById(R.id.btn_edit);

        mAccount        = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain      = BaseChain.getChain(mAccount.baseChain);
        mMyDomain       = getIntent().getStringExtra("domain");
        mMyAccount      = getIntent().getStringExtra("account");
        WLog.w("mMyDomain " + mMyDomain);

        mToolTitle.setText(getString(R.string.str_account_detail));
        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onFetchData();
            }
        });
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
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }
            if (mBaseChain.equals(IOV_MAIN)) {
                if (mAccount.getTokenBalance(TOKEN_IOV).compareTo(new BigDecimal("150000")) < 0) {
                    Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                    return;
                }

            } else if (mBaseChain.equals(IOV_TEST)) {
                if (mAccount.getTokenBalance(TOKEN_IOV_TEST).compareTo(new BigDecimal("150000")) < 0) {
                    Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            Intent intent = new Intent(this, DeleteStarNameActivity.class);
            intent.putExtra("ToDeleType", IOV_MSG_TYPE_DELETE_ACCOUNT);
            intent.putExtra("ToDeleDomain", mMyDomain);
            intent.putExtra("ToDeleAccount", mMyAccount);
            intent.putExtra("Time", mMyNameAccount.valid_until);
            startActivity(intent);

        } else if (v.equals(mBtnRenew)) {
            if (!mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }
            if (mBaseChain.equals(IOV_MAIN)) {
                if (mAccount.getTokenBalance(TOKEN_IOV).compareTo(new BigDecimal("300000")) < 0) {
                    Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                    return;
                }

            } else if (mBaseChain.equals(IOV_TEST)) {
                if (mAccount.getTokenBalance(TOKEN_IOV_TEST).compareTo(new BigDecimal("300000")) < 0) {
                    Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                    return;
                }
            }

            Intent intent = new Intent(this, ReNewStarNameActivity.class);
            intent.putExtra("ToRenewType", IOV_MSG_TYPE_RENEW_ACCOUNT);
            intent.putExtra("IsOpen", mStarNameDomain.type.equals("open") ? true : false);
            intent.putExtra("ToRenewDomain", mMyDomain);
            intent.putExtra("ToRenewAccount", mMyAccount);
            intent.putExtra("Time", mMyNameAccount.valid_until);
            startActivity(intent);

        } else if (v.equals(mBtnEdit)) {
            if (!mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }
            if (mBaseChain.equals(IOV_MAIN)) {
                if (mAccount.getTokenBalance(TOKEN_IOV).compareTo(new BigDecimal("300000")) < 0) {
                    Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                    return;
                }

            } else if (mBaseChain.equals(IOV_TEST)) {
                if (mAccount.getTokenBalance(TOKEN_IOV_TEST).compareTo(new BigDecimal("300000")) < 0) {
                    Toast.makeText(getBaseContext(), R.string.error_not_enough_fee, Toast.LENGTH_SHORT).show();
                    return;
                }
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
        new StarNameDomainInfoTask(getBaseApplication(), this, mBaseChain, mMyDomain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
//        new StarNameGrpcResolveTask(getBaseApplication(), this, mBaseChain, mMyAccount + "*" + mMyDomain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

    }

    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if (isFinishing()) return;
        if (result.taskType == TASK_FETCH_STARNAME_DOMAIN_INFO) {
            if (result.isSuccess) {
                mStarNameDomain = (StarNameDomain)result.resultData;
                WLog.w("mStarNameDomain " + mStarNameDomain.admin);
            }

        } else if (result.taskType == TASK_FETCH_STARNAME_RESOLVE) {
            if (result.isSuccess) {
                mMyNameAccount = (ResIovStarNameResolve.NameAccount)result.resultData;
                WLog.w("mMyNameAccount " + mMyNameAccount.domain);
            }

        }
        if (mTaskCount == 0) {
            onHideWaitDialog();
            mAdapter.notifyDataSetChanged();
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }


    private class MyAccountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_HEADER        = 0;
        private static final int TYPE_RESOURCE      = 1;
        private static final int TYPE_EMPTY         = 2;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if(viewType == TYPE_HEADER) {
                return new MyAccountHeaderHolder(getLayoutInflater().inflate(R.layout.item_account_detail_header, viewGroup, false));
            } else if(viewType == TYPE_RESOURCE) {
                return new MyResourceHolder(getLayoutInflater().inflate(R.layout.item_starname_resource, viewGroup, false));
            } else if(viewType == TYPE_EMPTY) {
                return new MyResourceEmptyHolder(getLayoutInflater().inflate(R.layout.item_starname_resource_empty, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_HEADER) {
                final MyAccountHeaderHolder holder = (MyAccountHeaderHolder)viewHolder;
                if (mMyNameAccount != null) {
                    holder.itemStarName.setText(mMyNameAccount.name + "*" + mMyNameAccount.domain);
                    holder.itemExpireDate.setText(WDp.getDpTime(getBaseContext(), mMyNameAccount.valid_until * 1000));
                    holder.itemBtnWebLink.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View v) {
                            Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://starname.me/" + mMyNameAccount.name + "*" + mMyNameAccount.domain));
                            startActivity(guideIntent);
                        }
                    });
                }
                if (mMyNameAccount != null && mMyNameAccount.resources != null &&  mMyNameAccount.resources.size() > 0) {
                    holder.itemAddressCnt.setText("" + mMyNameAccount.resources.size());
                } else {
                    holder.itemAddressCnt.setText("0");
                }

            } else  if (getItemViewType(position) == TYPE_RESOURCE) {
                final MyResourceHolder holder = (MyResourceHolder)viewHolder;
                final StarNameResource resource = mMyNameAccount.resources.get(position - 1);
                holder.itemChainImg.setImageDrawable(WUtil.getStarNameChainImg(getBaseContext(), resource));
                holder.itemChainName.setText(WUtil.getStarNameChainName(resource));
                holder.itemAddress.setText(resource.resource);
            }

        }

        @Override
        public int getItemCount() {
            if (mMyNameAccount != null && mMyNameAccount.resources != null &&  mMyNameAccount.resources.size() > 0) {
                return mMyNameAccount.resources.size() + 1;
            } else {
                return + 2;
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (position == 0) {
                return TYPE_HEADER;
            } else {
                if (mMyNameAccount != null && mMyNameAccount.resources != null &&  mMyNameAccount.resources.size() > 0) {
                    return TYPE_RESOURCE;
                } else {
                    return TYPE_EMPTY;

                }
            }
        }


        public class MyAccountHeaderHolder extends RecyclerView.ViewHolder {
            private CardView itemRoot;
            private ImageView itemBtnWebLink;
            TextView itemStarName, itemAddressCnt, itemExpireDate;

            public MyAccountHeaderHolder(View v) {
                super(v);
                itemRoot            = itemView.findViewById(R.id.card_root);
                itemStarName        = itemView.findViewById(R.id.starname_name);
                itemBtnWebLink      = itemView.findViewById(R.id.web_detail);
                itemAddressCnt      = itemView.findViewById(R.id.connected_addressed);
                itemExpireDate      = itemView.findViewById(R.id.expire_date);
            }
        }

        public class MyResourceHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            ImageView itemChainImg;
            TextView itemChainName, itemAddress;

            public MyResourceHolder(View v) {
                super(v);
                itemRoot        = itemView.findViewById(R.id.card_root);
                itemChainImg    = itemView.findViewById(R.id.chain_img);
                itemChainName   = itemView.findViewById(R.id.chain_name);
                itemAddress     = itemView.findViewById(R.id.chain_address);
            }
        }

        public class MyResourceEmptyHolder extends RecyclerView.ViewHolder {

            public MyResourceEmptyHolder(View v) {
                super(v);
            }
        }
    }
}
