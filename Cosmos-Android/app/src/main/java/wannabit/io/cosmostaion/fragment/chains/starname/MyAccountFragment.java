package wannabit.io.cosmostaion.fragment.chains.starname;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.starname.RegisterStarNameAccountActivity;
import wannabit.io.cosmostaion.activities.chains.starname.StarNameAccountDetailActivity;
import wannabit.io.cosmostaion.activities.chains.starname.StarNameListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.model.StarNameAccount;
import wannabit.io.cosmostaion.utils.WDp;

public class MyAccountFragment extends BaseFragment implements View.OnClickListener {

    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;
    private TextView            mAccountCnt;
    private Button              mRegisterAccount;

    private MyAccountAdapter    mMyAccountAdapter;
    private ArrayList<StarNameAccount> mMyStarNameAccounts = new ArrayList<>();

    public static MyAccountFragment newInstance(Bundle bundle) {
        MyAccountFragment fragment = new MyAccountFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_starname_my_account, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mAccountCnt             = rootView.findViewById(R.id.account_cnt);
        mRegisterAccount        = rootView.findViewById(R.id.btn_register);
        mRegisterAccount.setOnClickListener(this);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getSActivity().onFetch();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mMyAccountAdapter = new MyAccountAdapter();
        mRecyclerView.setAdapter(mMyAccountAdapter);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mMyStarNameAccounts = getSActivity().mMyStarNameAccounts;
        mAccountCnt.setText("" + mMyStarNameAccounts.size());
        mMyAccountAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onBusyFetch() {
        mSwipeRefreshLayout.setRefreshing(false);
    }


    public StarNameListActivity getSActivity() {
        return (StarNameListActivity)getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mRegisterAccount)) {
            if (!getSActivity().mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                getFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }

            Intent intent = new Intent(getSActivity(), RegisterStarNameAccountActivity.class);
            startActivity(intent);
        }

    }


    private class MyAccountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_MY_ACCOUNT                = 1;
        private static final int TYPE_PROMOTION                 = 2;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_MY_ACCOUNT) {
                return new MyAccountHolder(getLayoutInflater().inflate(R.layout.item_starname_account, viewGroup, false));
            } else if(viewType == TYPE_PROMOTION) {
                return new MyAccountPromotionHolder(getLayoutInflater().inflate(R.layout.item_starname_account_promotion, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_MY_ACCOUNT) {
                final StarNameAccount account = mMyStarNameAccounts.get(position);
                final MyAccountHolder holder = (MyAccountHolder)viewHolder;
                holder.itemAccount.setText(account.name + "*" + account.domain);
                holder.itemAddressCnt.setText("" + account.getResourceSize());
                holder.itemExpireDate.setText(WDp.getDpTime(getContext(), account.valid_until * 1000));
                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getSActivity(), StarNameAccountDetailActivity.class);
                        intent.putExtra("domain", account.domain);
                        intent.putExtra("account", account.name);
                        startActivity(intent);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if (mMyStarNameAccounts.size() == 0) {
                return 1;
            } else {
                return mMyStarNameAccounts.size();
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (mMyStarNameAccounts.size() == 0) {
                return TYPE_PROMOTION;
            } else {
                return TYPE_MY_ACCOUNT;
            }
        }

        public class MyAccountPromotionHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;

            public MyAccountPromotionHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.card_root);
            }
        }

        public class MyAccountHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            TextView itemAccount, itemAddressCnt, itemExpireDate;

            public MyAccountHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot            = itemView.findViewById(R.id.card_root);
                itemAccount         = itemView.findViewById(R.id.starname_account_name);
                itemAddressCnt      = itemView.findViewById(R.id.connected_addressed);
                itemExpireDate      = itemView.findViewById(R.id.expire_date);
            }
        }
    }
}