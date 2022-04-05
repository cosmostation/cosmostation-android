package wannabit.io.cosmostaion.fragment.chains.starname;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import starnamed.x.starname.v1beta1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.starname.RegisterStarNameAccountActivity;
import wannabit.io.cosmostaion.activities.chains.starname.StarNameAccountDetailActivity;
import wannabit.io.cosmostaion.activities.chains.starname.StarNameListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IBusyFetchListener;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.utils.WDp;

public class MyAccountFragment extends BaseFragment implements View.OnClickListener, IRefreshTabListener, IBusyFetchListener {
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private TextView mAccountCnt;
    private Button mRegisterAccount;

    private MyAccountAdapter mMyAccountAdapter;
    public ArrayList<Types.Account> mAccounts_gRPC = new ArrayList<>();

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
        mSwipeRefreshLayout = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mAccountCnt = rootView.findViewById(R.id.account_cnt);
        mRegisterAccount = rootView.findViewById(R.id.btn_register);
        mRegisterAccount.setOnClickListener(this);

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(rootView.getContext(), R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(() -> getSActivity().onFetch());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mMyAccountAdapter = new MyAccountAdapter();
        mRecyclerView.setAdapter(mMyAccountAdapter);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mAccounts_gRPC = getSActivity().mAccounts_gRPC;
        mAccountCnt.setText("" + mAccounts_gRPC.size());
        mMyAccountAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
    }

    @Override
    public void onBusyFetch() {
        mSwipeRefreshLayout.setRefreshing(false);
    }


    public StarNameListActivity getSActivity() {
        return (StarNameListActivity) getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mRegisterAccount)) {
            if (!getSActivity().account.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                showDialog(add);
                return;
            }

            Intent intent = new Intent(getSActivity(), RegisterStarNameAccountActivity.class);
            startActivity(intent);
        }

    }


    private class MyAccountAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_MY_ACCOUNT = 1;
        private static final int TYPE_PROMOTION = 2;

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_MY_ACCOUNT) {
                return new MyAccountHolder(getLayoutInflater().inflate(R.layout.item_starname_account, viewGroup, false));
            } else if (viewType == TYPE_PROMOTION) {
                return new MyAccountPromotionHolder(getLayoutInflater().inflate(R.layout.item_starname_account_promotion, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_MY_ACCOUNT) {
                final Types.Account account = mAccounts_gRPC.get(position);
                final MyAccountHolder holder = (MyAccountHolder) viewHolder;
                holder.itemAccount.setText(account.getName().getValue() + "*" + account.getDomain());
                holder.itemAddressCnt.setText("" + account.getResourcesCount());
                holder.itemExpireDate.setText(WDp.getDpTime(getContext(), account.getValidUntil() * 1000));
                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getSActivity(), StarNameAccountDetailActivity.class);
                        intent.putExtra("domain", account.getDomain());
                        intent.putExtra("account", account.getName().getValue());
                        startActivity(intent);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if (mAccounts_gRPC.size() == 0) {
                return 1;
            } else {
                return mAccounts_gRPC.size();
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (mAccounts_gRPC.size() == 0) {
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
                itemRoot = itemView.findViewById(R.id.card_root);
                itemAccount = itemView.findViewById(R.id.starname_account_name);
                itemAddressCnt = itemView.findViewById(R.id.connected_addressed);
                itemExpireDate = itemView.findViewById(R.id.expire_date);
            }
        }
    }
}