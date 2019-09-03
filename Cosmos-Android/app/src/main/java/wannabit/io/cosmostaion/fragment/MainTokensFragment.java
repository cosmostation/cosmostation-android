package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.utils.WDp;

public class MainTokensFragment extends BaseFragment implements View.OnClickListener {

    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;
    private LinearLayout        mEmptyToken;

    private CardView            mCardTotal;
    private TextView            mTotalValue, mTotalDenom, mDenomTitle;
    private TextView            mTokenSize, mTokenSortType;
    private LinearLayout        mBtnSort;

    private TokensAdapter       mTokensAdapter;
    private ArrayList<Balance>  mBalances = new ArrayList<>();

    public static MainTokensFragment newInstance(Bundle bundle) {
        MainTokensFragment fragment = new MainTokensFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_tokens, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mEmptyToken             = rootView.findViewById(R.id.empty_token);
        mCardTotal              = rootView.findViewById(R.id.card_total);
        mTotalValue             = rootView.findViewById(R.id.total_value);
        mTotalDenom             = rootView.findViewById(R.id.total_denom_value);
        mDenomTitle             = rootView.findViewById(R.id.total_denom_title);
        mTokenSize              = rootView.findViewById(R.id.token_cnt);
        mTokenSortType          = rootView.findViewById(R.id.token_sort_type);
        mBtnSort                = rootView.findViewById(R.id.btn_token_sort);
        mBtnSort.setOnClickListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMainActivity().onFetchAllData();
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mTokensAdapter = new TokensAdapter();
        mRecyclerView.setAdapter(mTokensAdapter);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        onUpdateView();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_accounts :
                getMainActivity().onShowTopAccountsView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
        onUpdateView();
    }

    @Override
    public void onBusyFetch() {
        if(!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void onUpdateView() {
        mBalances = getMainActivity().mBalances;
        WDp.DpMainDenom(getMainActivity(), getMainActivity().mBaseChain.getChain(), mDenomTitle);
        if (getMainActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));

        } else if (getMainActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg4));

        } else if (getMainActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg5));

        }
        if (mBalances != null && mBalances.size() > 0) {
            mTokensAdapter.notifyDataSetChanged();
            mEmptyToken.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);

        } else {
            mEmptyToken.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);

        }

    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnSort)) {

        }
    }


    private class TokensAdapter extends RecyclerView.Adapter<TokensAdapter.AssetHolder> {

        @NonNull
        @Override
        public AssetHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new AssetHolder(getLayoutInflater().inflate(R.layout.item_token, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull AssetHolder viewHolder, int position) {
        }

        @Override
        public int getItemCount() {
            return mBalances.size();
        }

        public class AssetHolder extends RecyclerView.ViewHolder {
            private CardView    itemRoot;
            private ImageView   itemImg;
            private TextView    itemSymbol, itemFullName, itemBalance, itemValue;

            public AssetHolder(View v) {
                super(v);
                itemRoot        = itemView.findViewById(R.id.token_card);
                itemImg         = itemView.findViewById(R.id.token_img);
                itemSymbol      = itemView.findViewById(R.id.token_symbol);
                itemFullName    = itemView.findViewById(R.id.token_fullname);
                itemBalance     = itemView.findViewById(R.id.token_balance);
                itemValue       = itemView.findViewById(R.id.token_value);
            }
        }
    }

    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }
}
