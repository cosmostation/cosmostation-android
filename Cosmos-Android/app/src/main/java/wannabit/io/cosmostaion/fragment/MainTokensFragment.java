package wannabit.io.cosmostaion.fragment;

import android.app.Activity;
import android.content.Intent;
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
import java.util.ArrayList;
import java.util.HashMap;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.TokenDetailActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.IrisToken;
import wannabit.io.cosmostaion.dialog.Dialog_TokenSorting;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbTic;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_IRIS_ATTO;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_MUON;
import static wannabit.io.cosmostaion.base.BaseConstant.IS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IMG_URL;

public class MainTokensFragment extends BaseFragment implements View.OnClickListener {

    public final static int SELECT_TOKEN_SORTING = 6004;
    public final static int ORDER_NAME = 0;
    public final static int ORDER_AMOUNT = 1;
    public final static int ORDER_VALUE = 2;

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
    private int                 mOrder;

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
        if (mBnbTics == null || mBnbTics.size() < 0) {
            mOrder = ORDER_NAME;
        }
        onUpdateView();
    }

    @Override
    public void onBusyFetch() {
        if(!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void onUpdateView() {
        mBalances = getMainActivity().mBalances;
        if (mOrder == ORDER_NAME) {
            mTokenSortType.setText(R.string.str_name);
            WUtil.onSortingTokenByName(mBalances, getMainActivity().mBaseChain);
        } else if (mOrder == ORDER_AMOUNT) {
            mTokenSortType.setText(R.string.str_amount);
            WUtil.onSortingTokenByAmount(mBalances, getMainActivity().mBaseChain);
        } else if (mOrder == ORDER_VALUE && getMainActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
            mTokenSortType.setText(R.string.str_value);
            WUtil.onSortingTokenByValue(mBalances, getMainActivity().mBaseChain, mBnbTics);
        }
        WDp.DpMainDenom(getMainActivity(), getMainActivity().mBaseChain.getChain(), mDenomTitle);
        if (getMainActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));
            onUpdateTotalCard();
            onFetchCosmosTokenPrice();

        } else if (getMainActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg4));
            onUpdateTotalCard();
            onFetchIrisTokenPrice();

        } else if (getMainActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg5));
            onUpdateTotalCard();
            onFetchBnbTokenPrice();
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
            BigDecimal totalAtomAmount = BigDecimal.ZERO;
            for (Balance balance:mBalances) {
                if (balance.symbol.equals(COSMOS_ATOM) || (IS_TEST && balance.symbol.equals(COSMOS_MUON))) {
                    totalAtomAmount = totalAtomAmount.add(WDp.getAllAtom(getMainActivity().mBalances, getMainActivity().mBondings, getMainActivity().mUnbondings, getMainActivity().mRewards, getMainActivity().mAllValidators));
                } else {

                }
            }
            mTotalAmount.setText(WDp.getDpAmount(getContext(), totalAtomAmount, 6, getMainActivity().mBaseChain));
            mTotalValue.setText(WDp.getValueOfAtom(getContext(), getBaseDao(), totalAtomAmount));

        } else if (getMainActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            BigDecimal totalIrisAmount = BigDecimal.ZERO;
            for (Balance balance:mBalances) {
                if (balance.symbol.equals(COSMOS_IRIS_ATTO)) {
                    totalIrisAmount = totalIrisAmount.add(WDp.getAllIris(getMainActivity().mBalances, getMainActivity().mBondings, getMainActivity().mUnbondings, getMainActivity().mIrisReward));
                } else {

                }
            }
            mTotalAmount.setText(WDp.getDpAmount(getContext(), totalIrisAmount, 6, getMainActivity().mBaseChain));
            mTotalValue.setText(WDp.getValueOfIris(getContext(), getBaseDao(), totalIrisAmount));

        } else if (getMainActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
            BigDecimal totalBnbAmount = BigDecimal.ZERO;
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
            mTotalAmount.setText(WDp.getDpAmount(getContext(), totalBnbAmount, 6, getMainActivity().mBaseChain));
            mTotalValue.setText(WDp.getValueOfBnb(getContext(), getBaseDao(), totalBnbAmount));
        }

    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnSort)) {
            Dialog_TokenSorting bottomSheetDialog = Dialog_TokenSorting.getInstance();
            bottomSheetDialog.setArguments(null);
            bottomSheetDialog.setTargetFragment(MainTokensFragment.this, SELECT_TOKEN_SORTING);
            bottomSheetDialog.show(getFragmentManager(), "dialog");

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
            private TextView    itemSymbol, itemInnerSymbol, itemFullName, itemBalance, itemValue;

            public AssetHolder(View v) {
                super(v);
                itemRoot        = itemView.findViewById(R.id.token_card);
                itemImg         = itemView.findViewById(R.id.token_img);
                itemSymbol      = itemView.findViewById(R.id.token_symbol);
                itemInnerSymbol = itemView.findViewById(R.id.token_inner_symbol);
                itemFullName    = itemView.findViewById(R.id.token_fullname);
                itemBalance     = itemView.findViewById(R.id.token_balance);
                itemValue       = itemView.findViewById(R.id.token_value);
            }
        }

    }

    private void onBindCosmosItem(TokensAdapter.AssetHolder holder, final int position) {
        final Balance balance = mBalances.get(position);
        if (balance.symbol.equals(COSMOS_ATOM) || (IS_TEST && balance.symbol.equals(COSMOS_MUON))) {
            holder.itemSymbol.setText(getString(R.string.str_atom_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), BaseChain.COSMOS_MAIN));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText(balance.symbol);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.atom_ic));

            BigDecimal totalAmount = WDp.getAllAtom(getMainActivity().mBalances, getMainActivity().mBondings, getMainActivity().mUnbondings, getMainActivity().mRewards, getMainActivity().mAllValidators);
            holder.itemBalance.setText(WDp.getDpAmount(getContext(), totalAmount, 6, getMainActivity().mBaseChain));
            holder.itemValue.setText(WDp.getValueOfAtom(getContext(), getBaseDao(), totalAmount));
        } else {
            // TODO no this case yet!
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
        }
        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getMainActivity(), TokenDetailActivity.class);
                intent.putExtra("balance", balance);
                intent.putExtra("allValidators", getMainActivity().mAllValidators);
                intent.putExtra("rewards", getMainActivity().mRewards);
                startActivity(intent);
            }
        });

    }

    private void onBindIrisItem(TokensAdapter.AssetHolder holder, final int position) {
        final Balance balance = mBalances.get(position);
        final IrisToken token = WUtil.getIrisToken(getMainActivity().mIrisTokens, balance);
        if (token != null) {
            holder.itemSymbol.setText(token.base_token.symbol.toUpperCase());
            holder.itemInnerSymbol.setText("(" + token.base_token.id + ")");
            holder.itemFullName.setText(token.base_token.name);
            BigDecimal amount = BigDecimal.ZERO;
            if (balance.symbol.equals(COSMOS_IRIS_ATTO)) {
                amount = WDp.getAllIris(getMainActivity().mBalances, getMainActivity().mBondings, getMainActivity().mUnbondings, getMainActivity().mIrisReward);
                holder.itemBalance.setText(WDp.getDpAmount(getContext(), amount, 6, getMainActivity().mBaseChain));
                holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.iris_toket_img));
                holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), BaseChain.IRIS_MAIN));
                holder.itemValue.setText(WDp.getValueOfIris(getContext(), getBaseDao(), amount));
            } else {
                holder.itemBalance.setText(WDp.getDpAmount(getContext(), balance.balance, 6, getMainActivity().mBaseChain));
                holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_ic));
                holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
                holder.itemValue.setText("-");
            }
        }
        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getMainActivity(), TokenDetailActivity.class);
                intent.putExtra("balance", balance);
                intent.putExtra("irisToken", token);
                intent.putExtra("irisreward", getMainActivity().mIrisReward);
                intent.putExtra("allValidators", getMainActivity().mAllValidators);
                startActivity(intent);
            }
        });
    }

    private void onBindBnbItem(TokensAdapter.AssetHolder holder, final int position) {
        Balance balance = mBalances.get(position);
        BnbToken token = WUtil.getBnbToken(getMainActivity().mBnbTokens, balance);

        if (token != null) {
            holder.itemSymbol.setText(token.original_symbol);
            holder.itemInnerSymbol.setText("(" + token.symbol + ")");
            holder.itemFullName.setText(token.name);
            holder.itemBalance.setText(WDp.getDpAmount(getContext(), balance.getAllBnbBalance(), 6, getMainActivity().mBaseChain));

            BigDecimal amount = BigDecimal.ZERO;
            if (balance.symbol.equals(COSMOS_BNB)) {
                amount = balance.getAllBnbBalance();
                holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.bnb_token_img));
                holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), BaseChain.BNB_MAIN));

            } else {
                holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
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
            holder.itemValue.setText(WDp.getValueOfBnb(getContext(), getBaseDao(), amount));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getMainActivity(), TokenDetailActivity.class);
                    intent.putExtra("balance", balance);
                    intent.putExtra("bnbToken", token);
                    intent.putExtra("bnbTics", mBnbTics);
                    startActivity(intent);

                }
            });
        }
    }

    private void onFetchCosmosTokenPrice() {
        onUpdateTotalCard();
    }

    private void onFetchIrisTokenPrice() {
        onUpdateTotalCard();
    }

    private void onFetchBnbTokenPrice() {
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == SELECT_TOKEN_SORTING && resultCode == Activity.RESULT_OK) {
            mOrder = data.getIntExtra("sorting", ORDER_NAME);
            onUpdateView();
        }
    }
}
