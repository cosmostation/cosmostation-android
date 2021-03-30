package wannabit.io.cosmostaion.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

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
import wannabit.io.cosmostaion.activities.TokenDetailActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.NativeTokenDetailActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.StakingTokenDetailActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.dialog.Dialog_TokenSorting;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResBnbTic;
import wannabit.io.cosmostaion.network.res.ResCgcTic;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.OKEX_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_AKASH;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BAND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_DVPN;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SECRET;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_XPRT;

public class MainTokensFragment extends BaseFragment implements View.OnClickListener {

    public final static int         SELECT_TOKEN_SORTING = 6004;
    public final static int         ORDER_NAME = 0;
    public final static int         ORDER_AMOUNT = 1;
    public final static int         ORDER_VALUE = 2;

    private SwipeRefreshLayout      mSwipeRefreshLayout;
    private RecyclerView            mRecyclerView;
    private LinearLayout            mEmptyToken;
    private CardView                mCardTotal;
    private TextView                mTotalValue, mTotalAmount, mDenomTitle;
    private TextView                mTokenSize, mTokenSortType;
    private LinearLayout            mBtnSort;
    private TextView                mKavaOracle;

    private TokensAdapter               mTokensAdapter;
    private ArrayList<Balance>          mBalances = new ArrayList<>();
    private HashMap<String, ResBnbTic>  mBnbTics = new HashMap<>();
    private int                         mOrder;

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
        mKavaOracle             = rootView.findViewById(R.id.kava_oracle);
        mTokenSize              = rootView.findViewById(R.id.token_cnt);
        mTokenSortType          = rootView.findViewById(R.id.token_sort_type);
        mBtnSort                = rootView.findViewById(R.id.btn_token_sort);
        mBtnSort.setOnClickListener(this);
        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMainActivity().onFetchAllData();
//                mTokensAdapter.notifyDataSetChanged();
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
//        onUpdateView();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (getMainActivity().mBaseChain.equals(COSMOS_MAIN)) {
            if (getMainActivity().mAccount.pushAlarm) {
                getMainActivity().getMenuInflater().inflate(R.menu.main_menu_alaram_on, menu);
            } else {
                getMainActivity().getMenuInflater().inflate(R.menu.main_menu_alaram_off, menu);
            }
        } else {
            getMainActivity().getMenuInflater().inflate(R.menu.main_menu, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_accounts :
                getMainActivity().onShowTopAccountsView();
                break;
            case R.id.menu_notification_off:
                getMainActivity().onUpdateUserAlarm(getMainActivity().mAccount, true);
                break;
            case R.id.menu_notification_on:
                getMainActivity().onUpdateUserAlarm(getMainActivity().mAccount, false);
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
        mBalances = getBaseDao().mBalances;
        if (mOrder == ORDER_NAME) {
            mTokenSortType.setText(R.string.str_name);
            WUtil.onSortingTokenByName(mBalances, getMainActivity().mBaseChain);
        } else if (mOrder == ORDER_AMOUNT) {
            mTokenSortType.setText(R.string.str_amount);
            WUtil.onSortingTokenByAmount(mBalances, getMainActivity().mBaseChain);
        } else if (mOrder == ORDER_VALUE && (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST))) {
            mTokenSortType.setText(R.string.str_value);
            WUtil.onSortingBnbTokenByValue(mBalances, mBnbTics);
        } else if (mOrder == ORDER_VALUE && (getMainActivity().mBaseChain.equals(KAVA_MAIN) || getMainActivity().mBaseChain.equals(KAVA_TEST))) {
            mTokenSortType.setText(R.string.str_value);
            WUtil.onSortingKavaTokenByValue(getMainActivity().mBaseChain, getBaseDao(), mBalances);
        }
        WDp.DpMainDenom(getMainActivity(), getMainActivity().mBaseChain.getChain(), mDenomTitle);
        if (getMainActivity().mBaseChain.equals(COSMOS_MAIN)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgCosmos));
            onUpdateTotalCard();
            onFetchCosmosTokenPrice();

        } else if (getMainActivity().mBaseChain.equals(IRIS_MAIN)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgIris));
            onUpdateTotalCard();
            onFetchIrisTokenPrice();

        } else if (getMainActivity().mBaseChain.equals(BNB_MAIN)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgBinance));
            onUpdateTotalCard();
            onFetchBnbTokenPrice();

        } else if (getMainActivity().mBaseChain.equals(KAVA_MAIN)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgKava));
            onUpdateTotalCard();

        } else if (getMainActivity().mBaseChain.equals(IOV_MAIN)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgStarname));
            onUpdateTotalCard();
            onFetchIovTokenPrice();

        } else if (getMainActivity().mBaseChain.equals(BAND_MAIN)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgBand));
            onUpdateTotalCard();

        } else if (getMainActivity().mBaseChain.equals(CERTIK_MAIN)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgCertik));
            onUpdateTotalCard();

        } else if (getMainActivity().mBaseChain.equals(SECRET_MAIN)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgSecret));
            onUpdateTotalCard();

        } else if (getMainActivity().mBaseChain.equals(AKASH_MAIN)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgAkash));
            onUpdateTotalCard();

        } else if (getMainActivity().mBaseChain.equals(OKEX_MAIN)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgOkex));
            onUpdateTotalCard();
            onFetchOKexTokenPrice();

        } else if (getMainActivity().mBaseChain.equals(PERSIS_MAIN)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgPersis));
            onUpdateTotalCard();

        } else if (getMainActivity().mBaseChain.equals(SENTINEL_MAIN)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBgSentinel));
            onUpdateTotalCard();

        }

        else if (getMainActivity().mBaseChain.equals(COSMOS_TEST)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            onUpdateTotalCard();

        } else if (getMainActivity().mBaseChain.equals(IRIS_TEST)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            onUpdateTotalCard();

        } else if (getMainActivity().mBaseChain.equals(BNB_TEST)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            onUpdateTotalCard();

        } else if (getMainActivity().mBaseChain.equals(KAVA_TEST)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            onUpdateTotalCard();

        } else if (getMainActivity().mBaseChain.equals(IOV_TEST)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            onUpdateTotalCard();

        } else if (getMainActivity().mBaseChain.equals(OK_TEST)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            onUpdateTotalCard();
            onFetchOKexTokenPrice();

        } else if (getMainActivity().mBaseChain.equals(CERTIK_TEST)) {
            mCardTotal.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            onUpdateTotalCard();

        }

        if (isGRPC(getMainActivity().mBaseChain)) {
            mTokenSize.setText(""+getBaseDao().mGrpcBalance.size());
            if (getBaseDao().mGrpcBalance != null && getBaseDao().mGrpcBalance.size() > 0) {
//                mTokensAdapter.notifyDataSetChanged();
                mTokensAdapter.notifyItemRangeChanged(0, getBaseDao().mGrpcBalance.size());
                mEmptyToken.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);

            } else {
                mEmptyToken.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            }

        } else {
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

    }

    private void onUpdateTotalCard() {
        if (isGRPC(getMainActivity().mBaseChain)) {
            BigDecimal totalAmount = getBaseDao().getAllMainAsset(WDp.mainDenom(getMainActivity().mBaseChain));
            mTotalAmount.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            mTotalValue.setText(WDp.getDpMainAssetValue(getContext(), getBaseDao(), totalAmount, getMainActivity().mBaseChain));

        } else if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) {
            BigDecimal totalBnbAmount = BigDecimal.ZERO;
            for (Balance balance:mBalances) {
                if (balance.symbol.equals(TOKEN_BNB)) {
                    totalBnbAmount = totalBnbAmount.add(balance.getAllBnbBalance());
                } else {
                    ResBnbTic tic = mBnbTics.get(WUtil.getBnbTicSymbol(balance.symbol));
                    if (tic != null) {
                        totalBnbAmount = totalBnbAmount.add(balance.exchangeToBnbAmount(tic));
                    }
                }
            }
            mTotalAmount.setText(WDp.getDpAmount2(getContext(), totalBnbAmount, 0, 6));
            mTotalValue.setText(WDp.getValueOfBnb(getContext(), getBaseDao(), totalBnbAmount));

        } else if (getMainActivity().mBaseChain.equals(KAVA_MAIN) || getMainActivity().mBaseChain.equals(KAVA_TEST)) {
            BigDecimal totalKavaAmount = BigDecimal.ZERO;
            for (Balance balance:mBalances) {
                if (balance.symbol.equals(TOKEN_KAVA)) {
                    totalKavaAmount = totalKavaAmount.add(WDp.getAllKava(getBaseDao(), getBaseDao().mBalances, getBaseDao().mBondings, getBaseDao().mUnbondings, getBaseDao().mRewards, getBaseDao().mAllValidators));
                } else {
                    BigDecimal tokenTotalAmount = WDp.getKavaTokenAll(getBaseDao(), mBalances, balance.symbol);
                    BigDecimal tokenTotalValue = WDp.kavaTokenDollorValue(getMainActivity().mBaseChain, getBaseDao(), balance.symbol, tokenTotalAmount);
                    BigDecimal convertedKavaAmount = tokenTotalValue.divide(getBaseDao().getLastKavaDollorTic(), WUtil.getKavaCoinDecimal(TOKEN_KAVA), RoundingMode.DOWN).movePointRight(WUtil.getKavaCoinDecimal(TOKEN_KAVA));
                    totalKavaAmount = totalKavaAmount.add(convertedKavaAmount);
                }
            }
            mTotalAmount.setText(WDp.getDpAmount2(getContext(), totalKavaAmount, 6, 6));
            mTotalValue.setText(WDp.getValueOfKava(getContext(), getBaseDao(), totalKavaAmount));

        } else if (getMainActivity().mBaseChain.equals(IOV_MAIN) || getMainActivity().mBaseChain.equals(IOV_TEST)) {
            BigDecimal totalIovAmount = BigDecimal.ZERO;
            for (Balance balance:mBalances) {
                if (balance.symbol.equals(TOKEN_IOV) || balance.symbol.equals(TOKEN_IOV_TEST) ) {
                    totalIovAmount = totalIovAmount.add(WDp.getAllIov(getBaseDao().mBalances, getBaseDao().mBondings, getBaseDao().mUnbondings, getBaseDao().mRewards, getBaseDao().mAllValidators));
                }
            }
            mTotalAmount.setText(WDp.getDpAmount2(getContext(), totalIovAmount, 6, 6));
            mTotalValue.setText(WDp.getValueOfIov(getContext(), getBaseDao(), totalIovAmount));

        } else if (getMainActivity().mBaseChain.equals(BAND_MAIN)) {
            BigDecimal totalBandAmount = BigDecimal.ZERO;
            for (Balance balance:mBalances) {
                if (balance.symbol.equals(TOKEN_BAND)) {
                    totalBandAmount = totalBandAmount.add(WDp.getAllBand(getBaseDao().mBalances, getBaseDao().mBondings, getBaseDao().mUnbondings, getBaseDao().mRewards, getBaseDao().mAllValidators));
                }
            }
            mTotalAmount.setText(WDp.getDpAmount2(getContext(), totalBandAmount, 6, 6));
            mTotalValue.setText(WDp.getValueOfBand(getContext(), getBaseDao(), totalBandAmount));

        } else if (getMainActivity().mBaseChain.equals(OKEX_MAIN) || getMainActivity().mBaseChain.equals(OK_TEST)) {
            BigDecimal totalOkAmount = BigDecimal.ZERO;
            for (Balance balance:mBalances) {
                if (balance.symbol.equals(TOKEN_OK)) {
                    totalOkAmount = totalOkAmount.add(WDp.getAllOk(balance, getBaseDao().mOkStaking, getBaseDao().mOkUnbonding));

                } else {
                    OkToken token = WUtil.getOkToken(getBaseDao().mOkTokenList, balance.symbol);
                    if (token == null) continue;
                    BigDecimal tokenTotalAmount = balance.balance.add(balance.locked);
                    BigDecimal tokenTotalValue = WDp.okExTokenDollorValue(getBaseDao(), token, tokenTotalAmount);
                    BigDecimal convertedOKTAmount = tokenTotalValue.divide(getBaseDao().getLastOKexDollorTic(), 6, RoundingMode.DOWN);
                    totalOkAmount = totalOkAmount.add(convertedOKTAmount);

                }
            }
            mTotalAmount.setText(WDp.getDpAmount2(getContext(), totalOkAmount, 0, 6));
            mTotalValue.setText(WDp.getDpMainAssetValue(getContext(), getBaseDao(), totalOkAmount, getMainActivity().mBaseChain));

        } else if (getMainActivity().mBaseChain.equals(CERTIK_MAIN) || getMainActivity().mBaseChain.equals(CERTIK_TEST)) {
            BigDecimal totalCtkAmount = BigDecimal.ZERO;
            for (Balance balance:mBalances) {
                if (balance.symbol.equals(TOKEN_CERTIK) ) {
                    totalCtkAmount = totalCtkAmount.add(WDp.getAllCtk(getBaseDao().mBalances, getBaseDao().mBondings, getBaseDao().mUnbondings, getBaseDao().mRewards, getBaseDao().mAllValidators));
                }
            }
            mTotalAmount.setText(WDp.getDpAmount2(getContext(), totalCtkAmount, 6, 6));
            mTotalValue.setText(WDp.getValueOfCertik(getContext(), getBaseDao(), totalCtkAmount));

        } else if (getMainActivity().mBaseChain.equals(SECRET_MAIN)) {
            BigDecimal totalScrtAmount = BigDecimal.ZERO;
            for (Balance balance:mBalances) {
                if (balance.symbol.equals(TOKEN_SECRET) ) {
                    totalScrtAmount = totalScrtAmount.add(WDp.getAllSecret(getBaseDao().mBalances, getBaseDao().mBondings, getBaseDao().mUnbondings, getBaseDao().mRewards, getBaseDao().mAllValidators));
                }
            }
            mTotalAmount.setText(WDp.getDpAmount2(getContext(), totalScrtAmount, 6, 6));
            mTotalValue.setText(WDp.getValueOfSecret(getContext(), getBaseDao(), totalScrtAmount));

        } else if (getMainActivity().mBaseChain.equals(SENTINEL_MAIN)) {
            BigDecimal totalAmount = BigDecimal.ZERO;
            for (Balance balance:mBalances) {
                if (balance.symbol.equals(TOKEN_DVPN) ) {
                    totalAmount = totalAmount.add(WDp.getAllSentinel(getBaseDao().mBalances, getBaseDao().mBondings, getBaseDao().mUnbondings, getBaseDao().mRewards, getBaseDao().mAllValidators));
                }
            }
            mTotalAmount.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            mTotalValue.setText(WDp.getDpMainAssetValue(getContext(), getBaseDao(), totalAmount, getMainActivity().mBaseChain));

        }

        if (getMainActivity().mBaseChain.equals(KAVA_MAIN) || getMainActivity().mBaseChain.equals(KAVA_TEST)) {
            mKavaOracle.setVisibility(View.VISIBLE);
        } else {
            mKavaOracle.setVisibility(View.GONE);
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
            if (getMainActivity().mBaseChain.equals(COSMOS_MAIN)) {
                onBindCosmosItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(IRIS_MAIN)) {
                onBindIrisItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) {
                onBindBnbItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(KAVA_MAIN)) {
                onBindKavaItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(KAVA_TEST)) {
                onBindKavaTestItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(IOV_MAIN) || getMainActivity().mBaseChain.equals(IOV_TEST)) {
                onBindIovItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(BAND_MAIN)) {
                onBindBandItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(OKEX_MAIN) || getMainActivity().mBaseChain.equals(OK_TEST)) {
                onBindOkItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(CERTIK_MAIN) || getMainActivity().mBaseChain.equals(CERTIK_TEST)) {
                onBindCertikItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(SECRET_MAIN)) {
                onBindSecretItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(AKASH_MAIN)) {
                onBindAkashItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(PERSIS_MAIN)) {
                onBindPersisItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(SENTINEL_MAIN)) {
                onBindSentinelItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(COSMOS_TEST)) {
                onBindCosmosTestItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(IRIS_TEST)) {
                onBindIrisTestItem(viewHolder, position);
            }
        }

        @Override
        public int getItemCount() {
            if (isGRPC(getMainActivity().mBaseChain)) {
                return getBaseDao().mGrpcBalance.size();
            } else {
                return mBalances.size();
            }
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
        final Coin coin = getBaseDao().mGrpcBalance.get(position);
        if (coin.denom.equals(TOKEN_ATOM)) {
            holder.itemSymbol.setText(getString(R.string.str_atom_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), COSMOS_MAIN));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Cosmos Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.atom_ic));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_ATOM);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.getDpMainAssetValue(getContext(), getBaseDao(), totalAmount, getMainActivity().mBaseChain));

        } else {

        }

        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getMainActivity(), TokenDetailActivity.class);
//                intent.putExtra("balance", balance);
//                startActivity(intent);
            }
        });
    }

    private void onBindIrisItem(TokensAdapter.AssetHolder holder, final int position) {
        final Coin coin = getBaseDao().mGrpcBalance.get(position);
        if (coin.denom.equals(TOKEN_IRIS)) {
            holder.itemSymbol.setText(getString(R.string.str_iris_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), IRIS_MAIN));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Iris Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.iris_toket_img));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_IRIS);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.getDpMainAssetValue(getContext(), getBaseDao(), totalAmount, getMainActivity().mBaseChain));

        } else {

        }

        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getMainActivity(), TokenDetailActivity.class);
//                intent.putExtra("balance", balance);
//                intent.putExtra("irisToken", token);
//                startActivity(intent);
            }
        });
    }

    private void onBindBnbItem(TokensAdapter.AssetHolder holder, final int position) {
        Balance balance = mBalances.get(position);
        BnbToken token = WUtil.getBnbToken(getBaseDao().mBnbTokens, balance);

        if (token != null) {
            holder.itemSymbol.setText(token.original_symbol);
            holder.itemInnerSymbol.setText("(" + token.symbol + ")");
            holder.itemFullName.setText(token.name);
            holder.itemBalance.setText(WDp.getDpAmount(getContext(), balance.getAllBnbBalance(), 6, getMainActivity().mBaseChain));
            Picasso.get().cancelRequest(holder.itemImg);

            BigDecimal amount = BigDecimal.ZERO;
            if (balance.symbol.equals(TOKEN_BNB)) {
                amount = balance.getAllBnbBalance();
                holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.bnb_token_img));
                holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), BNB_MAIN));

            } else {
                holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
                ResBnbTic tic = mBnbTics.get(WUtil.getBnbTicSymbol(balance.symbol));
                if (tic != null) {
                    amount = balance.exchangeToBnbAmount(tic);
                }
                try {
                    Picasso.get().load(TOKEN_IMG_URL+token.original_symbol+".png") .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic) .into(holder.itemImg);
                } catch (Exception e) {}
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

    private void onBindKavaItem(TokensAdapter.AssetHolder holder, final int position) {
        final Balance balance = mBalances.get(position);
        if (balance.symbol.equals(TOKEN_KAVA)) {
            holder.itemSymbol.setText(getString(R.string.str_kava_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), KAVA_MAIN));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("Kava Chain Native Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.kava_token_img));

            BigDecimal totalAmount = WDp.getAllKava(getBaseDao(), getBaseDao().mBalances, getBaseDao().mBondings, getBaseDao().mUnbondings, getBaseDao().mRewards, getBaseDao().mAllValidators);
            holder.itemBalance.setText(WDp.getDpAmount(getContext(), totalAmount, 6, getMainActivity().mBaseChain));
            holder.itemValue.setText(WDp.getValueOfKava(getContext(), getBaseDao(), totalAmount));

            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getMainActivity(), StakingTokenDetailActivity.class));
                }
            });

        } else {
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemSymbol.setText(balance.symbol.toUpperCase());
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            if (balance.symbol.equals("usdx")) {
                holder.itemFullName.setText("USD Stable Asset");
            } else if (balance.symbol.equals(TOKEN_HARD)) {
                holder.itemFullName.setText("Harvest Gov. Token");
            } else {
                holder.itemFullName.setText(balance.symbol.toUpperCase() + " on Kava Chain");
            }

            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_ic));
            try {
                Picasso.get().load(KAVA_COIN_IMG_URL+balance.symbol+".png") .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic) .into(holder.itemImg);

            } catch (Exception e) { }

            BigDecimal tokenTotalAmount = WDp.getKavaTokenAll(getBaseDao(), mBalances, balance.symbol);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), tokenTotalAmount, WUtil.getKavaCoinDecimal(balance.symbol), 6));
            BigDecimal tokenTotalValue = WDp.kavaTokenDollorValue(getMainActivity().mBaseChain, getBaseDao(), balance.symbol, tokenTotalAmount);
            BigDecimal convertedKavaAmount = tokenTotalValue.divide(getBaseDao().getLastKavaDollorTic(), WUtil.getKavaCoinDecimal(TOKEN_KAVA), RoundingMode.DOWN);
            holder.itemValue.setText(WDp.getValueOfKava(getContext(), getBaseDao(), convertedKavaAmount.movePointRight(WUtil.getKavaCoinDecimal(TOKEN_KAVA))));

            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getMainActivity(), NativeTokenDetailActivity.class);
                    intent.putExtra("denom", balance.symbol);
                    startActivity(intent);

                }
            });
        }
    }

    private void onBindKavaTestItem(TokensAdapter.AssetHolder holder, final int position) {
        final Balance balance = mBalances.get(position);
        if (balance.symbol.equals(TOKEN_KAVA)) {
            holder.itemSymbol.setText(getString(R.string.str_kava_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), KAVA_MAIN));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("Kava Chain Native Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.kava_token_img));

            BigDecimal totalAmount = WDp.getAllKava(getBaseDao(), getBaseDao().mBalances, getBaseDao().mBondings, getBaseDao().mUnbondings, getBaseDao().mRewards, getBaseDao().mAllValidators);
            holder.itemBalance.setText(WDp.getDpAmount(getContext(), totalAmount, 6, getMainActivity().mBaseChain));
            holder.itemValue.setText(WDp.getValueOfKava(getContext(), getBaseDao(), totalAmount));

            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getMainActivity(), StakingTokenDetailActivity.class));
                }
            });


        } else {
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemSymbol.setText(balance.symbol.toUpperCase());
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            if (balance.symbol.equals("usdx")) {
                holder.itemFullName.setText("USD Stable Asset");
            } else if (balance.symbol.equals(TOKEN_HARD)) {
                holder.itemFullName.setText("Harvest Gov. Token");
            } else {
                holder.itemFullName.setText(balance.symbol.toUpperCase() + " on Kava Chain");
            }

            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_ic));
            try {
                Picasso.get().load(KAVA_COIN_IMG_URL+balance.symbol+".png") .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic) .into(holder.itemImg);

            } catch (Exception e) { }

            BigDecimal tokenTotalAmount = WDp.getKavaTokenAll(getBaseDao(), mBalances, balance.symbol);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), tokenTotalAmount, WUtil.getKavaCoinDecimal(balance.symbol), 6));
            BigDecimal tokenTotalValue = WDp.kavaTokenDollorValue(getMainActivity().mBaseChain, getBaseDao(), balance.symbol, tokenTotalAmount);
            BigDecimal convertedKavaAmount = tokenTotalValue.divide(getBaseDao().getLastKavaDollorTic(), WUtil.getKavaCoinDecimal(TOKEN_KAVA), RoundingMode.DOWN);
            holder.itemValue.setText(WDp.getValueOfKava(getContext(), getBaseDao(), convertedKavaAmount.movePointRight(WUtil.getKavaCoinDecimal(TOKEN_KAVA))));

            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getMainActivity(), NativeTokenDetailActivity.class);
                    intent.putExtra("denom", balance.symbol);
                    startActivity(intent);

                }
            });

        }


    }

    private void onBindIovItem(TokensAdapter.AssetHolder holder, final int position) {
        final Balance balance = mBalances.get(position);
        if (balance.symbol.equals(TOKEN_IOV) || balance.symbol.equals(TOKEN_IOV_TEST)) {
            holder.itemSymbol.setText(getString(R.string.str_iov_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), IOV_MAIN));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("Starname Native Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.iov_token_img));

            BigDecimal totalAmount = WDp.getAllIov(getBaseDao().mBalances, getBaseDao().mBondings, getBaseDao().mUnbondings, getBaseDao().mRewards, getBaseDao().mAllValidators);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.getValueOfIov(getContext(), getBaseDao(), totalAmount));
        } else {
            //TODO no case yet

        }
        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO no  yet
            }
        });
    }

    private void onBindBandItem(TokensAdapter.AssetHolder holder, final int position) {
        final Balance balance = mBalances.get(position);
        if (balance.symbol.equals(TOKEN_BAND)) {
            holder.itemSymbol.setText(getString(R.string.str_band_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), BAND_MAIN));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("Band Chain Native Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.band_token_img));

            BigDecimal totalAmount = WDp.getAllBand(getBaseDao().mBalances, getBaseDao().mBondings, getBaseDao().mUnbondings, getBaseDao().mRewards, getBaseDao().mAllValidators);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.getValueOfBand(getContext(), getBaseDao(), totalAmount));
        } else {
            //TODO no case yet
        }
        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent intent = new Intent(getMainActivity(), TokenDetailActivity.class);
//                intent.putExtra("balance", balance);
//                startActivity(intent);
            }
        });
    }

    private void onBindOkItem(TokensAdapter.AssetHolder holder, final int position) {
        final Balance balance   = mBalances.get(position);
        final OkToken token     = WUtil.getOkToken(getBaseDao().mOkTokenList, balance.symbol);
        if (token == null) {
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemSymbol.setText(balance.symbol.toUpperCase());
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), balance.balance, 0, 6));
            holder.itemFullName.setText("");
            return;
        }

        holder.itemSymbol.setText(token.original_symbol.toUpperCase());
        holder.itemInnerSymbol.setText("(" + token.symbol + ")");
        holder.itemFullName.setText(token.description);

        if (token.symbol.equals(TOKEN_OK)) {
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), getMainActivity().mBaseChain));
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.okex_token_img));

            BigDecimal totalAmount = WDp.getAllOk(balance, getBaseDao().mOkStaking, getBaseDao().mOkUnbonding);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 0, 6));
            holder.itemValue.setText(WDp.getDpMainAssetValue(getContext(), getBaseDao(), totalAmount, getMainActivity().mBaseChain));

        }  else {
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            Picasso.get().load(OKEX_COIN_IMG_URL+  token.original_symbol + ".png").placeholder(R.drawable.token_ic).error(R.drawable.token_ic).fit().into(holder.itemImg);

            BigDecimal totalAmount = balance.balance.add(balance.locked);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 0, 6));
            BigDecimal tokenTotalValue = WDp.okExTokenDollorValue(getBaseDao(), token, totalAmount);
            BigDecimal convertedOKTAmount = tokenTotalValue.divide(getBaseDao().getLastOKexDollorTic(), 6, RoundingMode.DOWN);
            holder.itemValue.setText(WDp.getDpMainAssetValue(getContext(), getBaseDao(), convertedOKTAmount, getMainActivity().mBaseChain));
        }

        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getMainActivity(), TokenDetailActivity.class);
                intent.putExtra("okDenom", balance.symbol);
                startActivity(intent);
            }
        });
    }

    private void onBindCertikItem(TokensAdapter.AssetHolder holder, final int position) {
        final Balance balance = mBalances.get(position);
        if (balance.symbol.equals(TOKEN_CERTIK)) {
            holder.itemSymbol.setText(getString(R.string.str_ctk_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), CERTIK_MAIN));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("Certik Staking Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.certik_token_img));

            BigDecimal totalAmount = WDp.getAllCtk(getBaseDao().mBalances, getBaseDao().mBondings, getBaseDao().mUnbondings, getBaseDao().mRewards, getBaseDao().mAllValidators);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.getValueOfCertik(getContext(), getBaseDao(), totalAmount));
        } else {
            //TODO no case yet

        }
        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //TODO no  yet
            }
        });
    }

    private void onBindSecretItem(TokensAdapter.AssetHolder holder, final int position) {
        final Balance balance = mBalances.get(position);
        if (balance.symbol.equals(TOKEN_SECRET)) {
            holder.itemSymbol.setText(getString(R.string.str_scrt_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), SECRET_MAIN));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("Secret Native Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokensecret));

            BigDecimal totalAmount = WDp.getAllSecret(getBaseDao().mBalances, getBaseDao().mBondings, getBaseDao().mUnbondings, getBaseDao().mRewards, getBaseDao().mAllValidators);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.getValueOfSecret(getContext(), getBaseDao(), totalAmount));

        } else {
            //TODO no case yet
        }
        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            }
        });

    }

    private void onBindSentinelItem(TokensAdapter.AssetHolder holder, final int position) {
        final Balance balance = mBalances.get(position);
        if (balance.symbol.equals(TOKEN_DVPN)) {
            holder.itemSymbol.setText(getString(R.string.str_dvpn_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), SENTINEL_MAIN));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("Sentinel Native Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokensentinel));

            BigDecimal totalAmount = WDp.getAllSentinel(getBaseDao().mBalances, getBaseDao().mBondings, getBaseDao().mUnbondings, getBaseDao().mRewards, getBaseDao().mAllValidators);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.getValueOfSecret(getContext(), getBaseDao(), totalAmount));

        }
    }

    private void onBindAkashItem(TokensAdapter.AssetHolder holder, final int position) {
        final Coin coin = getBaseDao().mGrpcBalance.get(position);
        if (coin.denom.equals(TOKEN_AKASH)) {
            holder.itemSymbol.setText(getString(R.string.str_akt_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), AKASH_MAIN));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Akash Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.akash_token_img));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_AKASH);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.getDpMainAssetValue(getContext(), getBaseDao(), totalAmount, getMainActivity().mBaseChain));

            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getMainActivity(), StakingTokenDetailActivity.class));
                }
            });

        } else {

        }
    }

    private void onBindPersisItem(TokensAdapter.AssetHolder holder, final int position) {
        final Coin coin = getBaseDao().mGrpcBalance.get(position);
        if (coin.denom.equals(TOKEN_XPRT)) {
            holder.itemSymbol.setText(getString(R.string.str_xprt_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), PERSIS_MAIN));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Persistence Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokenpersistence));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_XPRT);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.getDpMainAssetValue(getContext(), getBaseDao(), totalAmount, getMainActivity().mBaseChain));

            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    WLog.w("onClickonClickonClick");
                    startActivity(new Intent(getMainActivity(), StakingTokenDetailActivity.class));
                }
            });

        } else {

        }
    }

    private void onBindCosmosTestItem(TokensAdapter.AssetHolder holder, final int position) {
        final Coin coin = getBaseDao().mGrpcBalance.get(position);
        if (coin.denom.equals(TOKEN_COSMOS_TEST)) {
            holder.itemSymbol.setText(getString(R.string.str_muon_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), COSMOS_TEST));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Stargate Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.atom_ic));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_COSMOS_TEST);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.getDpMainAssetValue(getContext(), getBaseDao(), totalAmount, getMainActivity().mBaseChain));

        } else {

        }
    }

    private void onBindIrisTestItem(TokensAdapter.AssetHolder holder, final int position) {
        final Coin coin = getBaseDao().mGrpcBalance.get(position);
        if (coin.denom.equals(TOKEN_IRIS_TEST)) {
            holder.itemSymbol.setText(getString(R.string.str_bif_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), IRIS_TEST));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Bifrost Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.iris_toket_img));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_IRIS_TEST);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.getDpMainAssetValue(getContext(), getBaseDao(), totalAmount, getMainActivity().mBaseChain));

        } else {

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
            if (!mBalances.get(position).symbol.equals(TOKEN_BNB)) {
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

    private void onFetchIovTokenPrice() {
        onUpdateTotalCard();
    }

    private void onFetchOKexTokenPrice() {
        for (int i = 0; i < mBalances.size(); i ++) {
            final int position = i;
            if (mBalances.get(position).symbol.equals("okb-c4d")) {
//          if (mBalances.get(position).symbol.equals(TOKEN_OK_OKB)) {
                ApiClient.getCGCClient(getMainActivity()).getPriceTicLite("okb", "false", "false", "false", "false", "false").enqueue(new Callback<ResCgcTic>() {
                    @Override
                    public void onResponse(Call<ResCgcTic> call, Response<ResCgcTic> response) {
                        getBaseDao().mOKBPrice = new BigDecimal(response.body().market_data.current_price.usd);
                        mTokensAdapter.notifyItemChanged(position);
                        onUpdateTotalCard();
                    }

                    @Override
                    public void onFailure(Call<ResCgcTic> call, Throwable t) {
                        WLog.w("onFetchOKexTokenPrice onFailure");

                    }
                });
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
