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
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.util.ArrayList;

import starnamed.x.starname.v1beta1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.starname.RegisterStarNameDomainActivity;
import wannabit.io.cosmostaion.activities.chains.starname.StarNameDomainDetailActivity;
import wannabit.io.cosmostaion.activities.chains.starname.StarNameListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.utils.WDp;

public class MyDomainFragment extends BaseFragment implements View.OnClickListener {
    private SwipeRefreshLayout      mSwipeRefreshLayout;
    private RecyclerView            mRecyclerView;
    private TextView                mDomainCnt;
    private Button                  mRegisterDomain;

    private MyDomainAdapter         mMyDomainAdapter;
    public ArrayList<Types.Domain>  mDomains_gRPC = new ArrayList<>();

    public static MyDomainFragment newInstance(Bundle bundle) {
        MyDomainFragment fragment = new MyDomainFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_starname_my_domain, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mDomainCnt              = rootView.findViewById(R.id.domain_cnt);
        mRegisterDomain         = rootView.findViewById(R.id.btn_register);
        mRegisterDomain.setOnClickListener(this);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getSActivity().onFetch();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mMyDomainAdapter = new MyDomainAdapter();
        mRecyclerView.setAdapter(mMyDomainAdapter);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mDomains_gRPC = getSActivity().mDomains_gRPC;
        mDomainCnt.setText("" + mDomains_gRPC.size());
        mMyDomainAdapter.notifyDataSetChanged();
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
        if (v.equals(mRegisterDomain)) {
            if (!getSActivity().mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                getFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
                return;
            }

            Intent intent = new Intent(getSActivity(), RegisterStarNameDomainActivity.class);
            startActivity(intent);
        }
    }


    private class MyDomainAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
        private static final int TYPE_MY_DOMAIN                 = 1;
        private static final int TYPE_PROMOTION                 = 2;


        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_MY_DOMAIN) {
                return new MyDomainAdapter.MyDomainHolder(getLayoutInflater().inflate(R.layout.item_starname_domain, viewGroup, false));
            } else if(viewType == TYPE_PROMOTION) {
                return  new MyDomainAdapter.MyDomainPromotionHolder(getLayoutInflater().inflate(R.layout.item_starname_domain_promotion, viewGroup, false));
            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (getItemViewType(position) == TYPE_MY_DOMAIN) {
                final Types.Domain domain = mDomains_gRPC.get(position);
                final Types.Account domainAccount = getSActivity().getDomainResolve(domain.getName());
                final MyDomainHolder holder = (MyDomainHolder)viewHolder;

                holder.itemDomain.setText("*" + domain.getName());
                holder.itemType.setText(domain.getType().toUpperCase());
                if (domain.getType().equals("open")) {
                    holder.itemType.setTextColor(getResources().getColor(R.color.colorIov));
                } else {
                    holder.itemType.setTextColor(getResources().getColor(R.color.colorWhite));
                }
                holder.itemExpireDate.setText(WDp.getDpTime(getContext(), domain.getValidUntil() * 1000));
                holder.itemAddressCnt.setText("" + domainAccount.getResourcesCount());
                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getSActivity(), StarNameDomainDetailActivity.class);
                        intent.putExtra("domain", domain.getName());
                        startActivity(intent);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if (mDomains_gRPC.size() == 0) {
                return 1;
            } else {
                return mDomains_gRPC.size();
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (mDomains_gRPC.size() == 0) {
                return TYPE_PROMOTION;
            } else {
                return TYPE_MY_DOMAIN;
            }
        }

        public class MyDomainPromotionHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;

            public MyDomainPromotionHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.card_root);
            }
        }

        public class MyDomainHolder extends RecyclerView.ViewHolder {
            CardView itemRoot;
            TextView itemDomain, itemType, itemExpireDate, itemAddressCnt;
            public MyDomainHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot            = itemView.findViewById(R.id.card_root);
                itemDomain          = itemView.findViewById(R.id.starname_domain_name);
                itemType            = itemView.findViewById(R.id.domain_type);
                itemExpireDate      = itemView.findViewById(R.id.expire_date);
                itemAddressCnt      = itemView.findViewById(R.id.connected_addressed);
            }
        }

    }
}
