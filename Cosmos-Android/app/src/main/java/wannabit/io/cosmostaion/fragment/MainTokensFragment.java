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

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbTic;
import wannabit.io.cosmostaion.network.res.ResKeyBaseUser;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IMG_URL;

public class MainTokensFragment extends BaseFragment implements View.OnClickListener {

    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;
    private LinearLayout        mEmptyToken;

    private CardView            mCardTotal;
    private TextView            mTotalValue, mTotalAmount, mDenomTitle;
    private TextView            mTokenSize, mTokenSortType;
    private LinearLayout        mBtnSort;

    private TokensAdapter       mTokensAdapter;
    private ArrayList<Balance>  mBalances = new ArrayList<>();
    private HashMap<String, ResBnbTic>  mBnbTics = new HashMap<>();

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
        mTotalAmount            = rootView.findViewById(R.id.total_amount);
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
            onFetchBnbPrice();
        }
        mTokenSize.setText(""+mBalances.size());
        if (mBalances != null && mBalances.size() > 0) {
            mTokensAdapter.notifyDataSetChanged();
            mEmptyToken.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);

        } else {
            mEmptyToken.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);

        }

    }

    private void onUpdateTotalCard() {
        if (getMainActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {

        } else if (getMainActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {

        } else if (getMainActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
            BigDecimal totalValue, totalBnbAmount = BigDecimal.ZERO;
            for (Balance balance:mBalances) {
                if (balance.symbol.equals(COSMOS_BNB)) {
                    totalBnbAmount = totalBnbAmount.add(balance.getAllBnbBalance());
                } else {
                    ResBnbTic tic = mBnbTics.get(WUtil.getBnbTicSymbol(balance.symbol));
                    if (tic != null) {
                        totalBnbAmount = totalBnbAmount.add(balance.exchangeToBnbAmount(tic));
                    }
                }
            }
            mTotalAmount.setText(WDp.getDpAmount(getContext(), totalBnbAmount, 8, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
            if(getBaseDao().getCurrency() != 5) {
                totalValue = totalBnbAmount.multiply(new BigDecimal(""+getBaseDao().getLastBnbTic())).setScale(2, RoundingMode.DOWN);
            } else {
                totalValue = totalBnbAmount.multiply(new BigDecimal(""+getBaseDao().getLastBnbTic())).setScale(8, RoundingMode.DOWN);
            }
            mTotalValue.setText(WDp.getPriceDp(getContext(), totalValue, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));
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
            if (getMainActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                onBindCosmosItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                onBindIrisItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
                onBindBnbItem(viewHolder, position);
            }
        }

        @Override
        public int getItemCount() {
            return mBalances.size();
        }

        public class AssetHolder extends RecyclerView.ViewHolder {
            private CardView    itemRoot;
            private ImageView   itemImg;
            private TextView    itemSymbol, itemBnbSymbol, itemFullName, itemBalance, itemValue;

            public AssetHolder(View v) {
                super(v);
                itemRoot        = itemView.findViewById(R.id.token_card);
                itemImg         = itemView.findViewById(R.id.token_img);
                itemSymbol      = itemView.findViewById(R.id.token_symbol);
                itemBnbSymbol   = itemView.findViewById(R.id.token_bnb_symbol);
                itemFullName    = itemView.findViewById(R.id.token_fullname);
                itemBalance     = itemView.findViewById(R.id.token_balance);
                itemValue       = itemView.findViewById(R.id.token_value);
            }
        }

    }

    private void onBindCosmosItem(TokensAdapter.AssetHolder holder, final int position) {

    }

    private void onBindIrisItem(TokensAdapter.AssetHolder holder, final int position) {

    }

    private void onBindBnbItem(TokensAdapter.AssetHolder holder, final int position) {
        Balance balance = mBalances.get(position);
        BnbToken token = WUtil.getBnbToken(getMainActivity().mBnbTokens, balance);

        if (token != null) {
            holder.itemSymbol.setText(token.original_symbol);
            holder.itemBnbSymbol.setText("(" + token.symbol + ")");
            holder.itemFullName.setText(token.name);
            holder.itemBalance.setText(WDp.getDpAmount(getContext(), balance.getAllBnbBalance(), 8, BaseChain.getChain(getMainActivity().mAccount.baseChain)));

            BigDecimal price, amount = BigDecimal.ZERO;
            if (balance.symbol.equals(COSMOS_BNB)) {
                amount = balance.getAllBnbBalance();
                holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.bnb_token_img));

            } else {
                ResBnbTic tic = mBnbTics.get(WUtil.getBnbTicSymbol(balance.symbol));
                if (tic != null) {
                    amount = balance.exchangeToBnbAmount(tic);
                }
                try {
                    Picasso.get().load(TOKEN_IMG_URL+token.original_symbol+".png")
                            .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic)
                            .into(holder.itemImg);
                }catch (Exception e) {}
            }
            if(getBaseDao().getCurrency() != 5) {
                price = amount.multiply(new BigDecimal(""+getBaseDao().getLastBnbTic())).setScale(2, RoundingMode.DOWN);
            } else {
                price = amount.multiply(new BigDecimal(""+getBaseDao().getLastBnbTic())).setScale(8, RoundingMode.DOWN);
            }
            holder.itemValue.setText(WDp.getPriceDp(getContext(), price, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));

        }
    }




    private void onFetchBnbPrice() {
        mBnbTics.clear();
        for (int i = 0; i < mBalances.size(); i ++) {
            final int position = i;
            if (!mBalances.get(position).symbol.equals(COSMOS_BNB)) {
                final String ticSymbol = WUtil.getBnbTicSymbol(mBalances.get(position).symbol);
                ResBnbTic tic = mBnbTics.get(ticSymbol);
                if (tic == null) {
                    ApiClient.getBnbChain(getMainActivity()).getTic(ticSymbol).enqueue(new Callback<ArrayList<ResBnbTic>>() {
                        @Override
                        public void onResponse(Call<ArrayList<ResBnbTic>> call, Response<ArrayList<ResBnbTic>> response) {
                            if (isAdded() && response.isSuccessful() && response.body().size() > 0) {
                                mBnbTics.put(ticSymbol, response.body().get(0));
                                mTokensAdapter.notifyItemChanged(position);
                            } else {
                                mBnbTics.remove(ticSymbol);
                            }
                            onUpdateTotalCard();
                        }

                        @Override
                        public void onFailure(Call<ArrayList<ResBnbTic>> call, Throwable t) {
                            mBnbTics.remove(ticSymbol);
                            onUpdateTotalCard();
                        }
                    });
                }
            }
        }
    }

    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }
}
