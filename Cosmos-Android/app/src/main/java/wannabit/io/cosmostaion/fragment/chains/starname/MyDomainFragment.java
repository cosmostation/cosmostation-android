package wannabit.io.cosmostaion.fragment.chains.starname;

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
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.starname.StarNameListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.fragment.ValidatorMyFragment;
import wannabit.io.cosmostaion.model.StarNameDomain;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class MyDomainFragment extends BaseFragment {

    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;
    private TextView            mDomainCnt;
    private MyDomainAdapter     mMyDomainAdapter;

    private ArrayList<StarNameDomain> mMyStarNameDomains = new ArrayList<>();

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
        mMyStarNameDomains = getSActivity().mMyStarNameDomains;
        mDomainCnt.setText("" + mMyStarNameDomains.size());
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
                final StarNameDomain domain = mMyStarNameDomains.get(position);
                final MyDomainHolder holder = (MyDomainHolder)viewHolder;
                holder.itemDomain.setText("*" + domain.name);
                holder.itemType.setText(domain.type.toUpperCase());
                holder.itemExpireDate.setText(WDp.getDpTime(getContext(), domain.valid_until * 1000));
            }
        }

        @Override
        public int getItemCount() {
            if (mMyStarNameDomains.size() == 0) {
                return 1;
            } else {
                return mMyStarNameDomains.size();
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (mMyStarNameDomains.size() == 0) {
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
            TextView itemDomain, itemType, itemExpireDate;
            public MyDomainHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot            = itemView.findViewById(R.id.card_root);
                itemDomain          = itemView.findViewById(R.id.starname_domain_name);
                itemType            = itemView.findViewById(R.id.domain_type);
                itemExpireDate      = itemView.findViewById(R.id.expire_date);
            }
        }

    }
}
