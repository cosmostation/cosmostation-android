package wannabit.io.cosmostaion.fragment;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
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

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.NativeTokenDetailActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.StakingTokenDetailActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.dialog.Dialog_TokenSorting;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.ALTHEA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.OKEX_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.OSMOSIS_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.SIF_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_AKASH;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ALTHEA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BAND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CRO;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_DVPN;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_FET;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ION;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KI;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_MEDI;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OSMOSIS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_RIZON;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SECRET;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SIF;
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
    private TextView                mTotalValue, mTotalBtcValue;
    private TextView                mTokenSize, mTokenSortType;
    private LinearLayout            mBtnSort;

    private TokensAdapter           mTokensAdapter;
    private int                     mOrder;

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
        mTotalBtcValue          = rootView.findViewById(R.id.total_btc_amount);
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
            case R.id.menu_explorer :
                getMainActivity().onExplorerView();
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
        mRecyclerView.getRecycledViewPool().clear();
        onUpdateView();
    }

    @Override
    public void onBusyFetch() {
        if(!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void onUpdateView() {
        mCardTotal.setCardBackgroundColor(WDp.getChainBgColor(getContext(), getMainActivity().mBaseChain));
        onUpdateTotalCard();

        if (mOrder == ORDER_NAME) {
            mTokenSortType.setText(R.string.str_name);
            if (isGRPC(getMainActivity().mBaseChain)) {
                WUtil.onSortingTokenByNameV1(getBaseDao().mGrpcBalance, getMainActivity().mBaseChain);
            } else {
                WUtil.onSortingTokenByName(getBaseDao().mBalances, getMainActivity().mBaseChain);
            }
        } else if (mOrder == ORDER_AMOUNT) {
            mTokenSortType.setText(R.string.str_amount);
            if (isGRPC(getMainActivity().mBaseChain)) {
                WUtil.onSortingTokenByAmountV1(getBaseDao().mGrpcBalance, getMainActivity().mBaseChain);
            } else {
                WUtil.onSortingTokenByAmount(getBaseDao().mBalances, getMainActivity().mBaseChain);
            }
        } else if (mOrder == ORDER_VALUE) {
            mTokenSortType.setText(R.string.str_value);
            if (isGRPC(getMainActivity().mBaseChain)) {
                WUtil.onSortingTokenByValueV1(getBaseDao().mGrpcBalance, getMainActivity().mBaseChain);
            } else {
                WUtil.onSortingTokenByValue(getBaseDao().mBalances, getMainActivity().mBaseChain);
            }
        }

        if (isGRPC(getMainActivity().mBaseChain)) {
            if (getBaseDao().mGrpcBalance != null && getBaseDao().mGrpcBalance.size() > 0) {
                mTokensAdapter.notifyDataSetChanged();
                mEmptyToken.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);

            } else {
                mEmptyToken.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            }

        } else {
            if (getBaseDao().mBalances != null && getBaseDao().mBalances.size() > 0) {
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
        mTokenSize.setText(WDp.tokenCnt(getMainActivity().mBaseChain, getBaseDao()));
        mTotalBtcValue.setText(WDp.dpAllAssetValueBtc(getMainActivity().mBaseChain, getBaseDao()));
        mTotalValue.setText(WDp.dpAllAssetValueUserCurrency(getMainActivity().mBaseChain, getBaseDao()));
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
            View rowView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_token, viewGroup,false);
            AssetHolder viewHolder = new AssetHolder(rowView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull AssetHolder viewHolder, int position) {
            if (getMainActivity().mBaseChain.equals(COSMOS_MAIN)) {
                onBindCosmosItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(IRIS_MAIN)) {
                onBindIrisItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) {
                onBindBnbItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(KAVA_MAIN) || getMainActivity().mBaseChain.equals(KAVA_TEST)) {
                onBindKavaItem(viewHolder, position);
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
            } else if (getMainActivity().mBaseChain.equals(FETCHAI_MAIN)) {
                onBindFetchItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(CRYPTO_MAIN)) {
                onBindCrytoItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(SIF_MAIN)) {
                onBindSifItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(KI_MAIN)) {
                onBindKiItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(OSMOSIS_MAIN)) {
                onBindOsmosisItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(RIZON_TEST)) {
                onBindRizonItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(MEDI_TEST)) {
                onBindMediItem(viewHolder, position);
            } else if (getMainActivity().mBaseChain.equals(ALTHEA_TEST)) {
                onBindAltheaItem(viewHolder, position);
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
                return getBaseDao().mBalances.size();
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

    private void onBindBnbItem(TokensAdapter.AssetHolder holder, final int position) {
        final String denom      = getBaseDao().mBalances.get(position).symbol;
        final BigDecimal amount = getBaseDao().getAllBnbTokenAmount(denom);
        final BnbToken bnbToken = getBaseDao().getBnbToken(denom);
        if (bnbToken != null) {
            holder.itemSymbol.setText(bnbToken.original_symbol.toUpperCase());
            holder.itemInnerSymbol.setText("(" + bnbToken.symbol + ")");
            holder.itemFullName.setText(bnbToken.name);
            if (denom.equals(TOKEN_BNB)) {
                holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.bnb_token_img));
                holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), BNB_MAIN));
                holder.itemBalance.setText(WDp.getDpAmount2(getContext(), amount, 0, 6));
                holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), TOKEN_BNB, amount, 0));
                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getMainActivity(), StakingTokenDetailActivity.class);
                        startActivity(intent);
                    }
                });

            } else {
                Picasso.get().load(TOKEN_IMG_URL+bnbToken.original_symbol+".png") .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic) .into(holder.itemImg);
                holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
                holder.itemBalance.setText(WDp.getDpAmount2(getContext(), amount, 0, 6));

                final BigDecimal convertAmount = WUtil.getBnbConvertAmount(getBaseDao(), denom, amount);
                holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), TOKEN_BNB, convertAmount, 0));
                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getMainActivity(), NativeTokenDetailActivity.class);
                        intent.putExtra("denom", denom);
                        startActivity(intent);
                    }
                });
            }
        }
    }

    private void onBindKavaItem(TokensAdapter.AssetHolder holder, final int position) {
        final Balance balance = getBaseDao().mBalances.get(position);
        if (balance.symbol.equals(TOKEN_KAVA)) {
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), KAVA_MAIN));
            holder.itemSymbol.setText(getString(R.string.str_kava_c));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("Kava Staking Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.kava_token_img));

            BigDecimal totalAmount = getBaseDao().getAllMainAssetOld(TOKEN_KAVA);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, totalAmount, 6));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getMainActivity(), StakingTokenDetailActivity.class));
                }
            });

        } else {
            Picasso.get().load(KAVA_COIN_IMG_URL+balance.symbol+".png") .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic) .into(holder.itemImg);
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemSymbol.setText(balance.symbol.toUpperCase());
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            if (balance.symbol.equals("usdx")) { holder.itemFullName.setText("USD Stable Asset"); }
            else if (balance.symbol.equals(TOKEN_HARD)) { holder.itemFullName.setText("HardPool Gov. Token"); }
            else {  holder.itemFullName.setText(balance.symbol.toUpperCase() + " on Kava Chain"); }

            BigDecimal tokenTotalAmount = getBaseDao().availableAmount(balance.symbol).add(getBaseDao().lockedAmount(balance.symbol));
            BigDecimal convertedKavaAmount = WDp.convertTokenToKava(getBaseDao(), balance.symbol);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), tokenTotalAmount, WUtil.getKavaCoinDecimal(balance.symbol), 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), TOKEN_KAVA, convertedKavaAmount, 6));
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

    private void onBindOkItem(TokensAdapter.AssetHolder holder, final int position) {
        final Balance balance   = getBaseDao().mBalances.get(position);
        final OkToken okToken   = getBaseDao().okToken(balance.symbol);
        if (okToken != null) {
            holder.itemSymbol.setText(okToken.original_symbol.toUpperCase());
            holder.itemInnerSymbol.setText("(" + okToken.symbol + ")");
            holder.itemFullName.setText(okToken.description);
            if (balance.symbol.equals(TOKEN_OK)) {
                holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), getMainActivity().mBaseChain));
                holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.okex_token_img));

                BigDecimal totalAmount = getBaseDao().getAllExToken(balance.symbol);
                holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 0, 6));
                holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, totalAmount, 0));
                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(new Intent(getMainActivity(), StakingTokenDetailActivity.class));
                    }
                });

            } else {
                holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
                Picasso.get().load(OKEX_COIN_IMG_URL+  okToken.original_symbol + ".png").placeholder(R.drawable.token_ic).error(R.drawable.token_ic).fit().into(holder.itemImg);

                BigDecimal totalAmount = getBaseDao().getAllExToken(balance.symbol);
                BigDecimal convertAmount = WDp.convertTokenToOkt(getBaseDao(), balance.symbol);
                holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 0, 6));
                holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), TOKEN_OK, convertAmount, 0));
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
    }

    private void onBindCertikItem(TokensAdapter.AssetHolder holder, final int position) {
        final Balance balance = getBaseDao().mBalances.get(position);
        if (balance.symbol.equals(TOKEN_CERTIK)) {
            holder.itemSymbol.setText(getString(R.string.str_ctk_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), CERTIK_MAIN));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("Certik Staking Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.certik_token_img));

            BigDecimal totalAmount = getBaseDao().getAllMainAssetOld(TOKEN_CERTIK);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, totalAmount, 6));

        } else {

        }
    }

    private void onBindSecretItem(TokensAdapter.AssetHolder holder, final int position) {
        final Balance balance = getBaseDao().mBalances.get(position);
        if (balance.symbol.equals(TOKEN_SECRET)) {
            holder.itemSymbol.setText(getString(R.string.str_scrt_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), SECRET_MAIN));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("Secret Native Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokensecret));

            BigDecimal totalAmount = getBaseDao().getAllMainAssetOld(TOKEN_SECRET);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, totalAmount, 6));

        } else {

        }

    }

    private void onBindFetchItem(TokensAdapter.AssetHolder holder, final int position) {
        final Balance balance = getBaseDao().mBalances.get(position);
        if (balance.symbol.equals(TOKEN_FET)) {
            holder.itemSymbol.setText(getString(R.string.str_fet_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), FETCHAI_MAIN));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("Fetch.ai Staking Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokenfetchai));

            BigDecimal totalAmount = getBaseDao().getAllMainAssetOld(TOKEN_FET);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 18, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, totalAmount, 18));

        }
    }

    private void onBindSifItem(TokensAdapter.AssetHolder holder, final int position) {
        final Balance balance = getBaseDao().mBalances.get(position);
        final int dpDecimal = WUtil.getSifCoinDecimal(balance.symbol);
        if (balance.symbol.equals(TOKEN_SIF)) {
            holder.itemSymbol.setText(getString(R.string.str_sif_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), SIF_MAIN));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("Sif Chain Staking Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokensifchain));

            BigDecimal totalAmount = getBaseDao().getAllMainAssetOld(TOKEN_SIF);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, dpDecimal, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, totalAmount, dpDecimal));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getMainActivity(), StakingTokenDetailActivity.class));
                }
            });

        } else {
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemSymbol.setText(balance.symbol.substring(1).toUpperCase());
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText(balance.symbol.substring(1).toUpperCase() + " on Sif Chain");
            Picasso.get().load(SIF_COIN_IMG_URL+balance.symbol+".png") .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic) .into(holder.itemImg);

            BigDecimal totalAmount = getBaseDao().availableAmount(balance.symbol);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, dpDecimal, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol.substring(1), totalAmount, dpDecimal));

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

    private void onBindKiItem(TokensAdapter.AssetHolder holder, final int position) {
        final Balance balance = getBaseDao().mBalances.get(position);
        if (balance.symbol.equals(TOKEN_KI)) {
            holder.itemSymbol.setText(getString(R.string.str_ki_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), KI_MAIN));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("KiChain Staking Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_kifoundation));

            BigDecimal totalAmount = getBaseDao().getAllMainAssetOld(TOKEN_KI);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, totalAmount, 6));

        } else {

        }
    }

    private void onBindMediItem(TokensAdapter.AssetHolder holder, final int position) {
        final Balance balance = getBaseDao().mBalances.get(position);
        if (balance.symbol.equals(TOKEN_MEDI)) {
            holder.itemSymbol.setText(getString(R.string.str_medi_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), MEDI_TEST));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("Medibloc Staking Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokenmedibloc));

            BigDecimal totalAmount = getBaseDao().getAllMainAssetOld(TOKEN_MEDI);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, totalAmount, 6));

        } else {

        }
    }


    //with gRPC
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
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getMainActivity(), StakingTokenDetailActivity.class);
                    startActivity(intent);
                }
            });

        } else if (coin.denom.startsWith("ibc/")){
            holder.itemSymbol.setText("IBC");
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemInnerSymbol.setText("(unKnown)");
            holder.itemFullName.setText(coin.denom);
            holder.itemFullName.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));

        } else {

        }
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
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getMainActivity(), StakingTokenDetailActivity.class);
                    startActivity(intent);
                }
            });

        } else if (coin.denom.startsWith("ibc/")) {
            holder.itemSymbol.setText("IBC");
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemInnerSymbol.setText("(unKnown)");
            holder.itemFullName.setText(coin.denom);
            holder.itemFullName.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));

        } else {

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
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getMainActivity(), StakingTokenDetailActivity.class));
                }
            });

        } else if (coin.denom.startsWith("ibc/")) {
            holder.itemSymbol.setText("IBC");
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemInnerSymbol.setText("(unKnown)");
            holder.itemFullName.setText(coin.denom);
            holder.itemFullName.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));

        } else {

        }
    }

    private void onBindSentinelItem(TokensAdapter.AssetHolder holder, final int position) {
        final Coin coin = getBaseDao().mGrpcBalance.get(position);
        if (coin.denom.equals(TOKEN_DVPN)) {
            holder.itemSymbol.setText(getString(R.string.str_dvpn_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), SENTINEL_MAIN));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Sentinel Native Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokensentinel));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_DVPN);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getMainActivity(), StakingTokenDetailActivity.class));
                }
            });

        } else if (coin.denom.startsWith("ibc/")) {
            holder.itemSymbol.setText("IBC");
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemInnerSymbol.setText("(unKnown)");
            holder.itemFullName.setText(coin.denom);
            holder.itemFullName.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));

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
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getMainActivity(), StakingTokenDetailActivity.class));
                }
            });

        } else if (coin.denom.startsWith("ibc/")) {
            holder.itemSymbol.setText("IBC");
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemInnerSymbol.setText("(unKnown)");
            holder.itemFullName.setText(coin.denom);
            holder.itemFullName.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));

        } else {

        }
    }

    private void onBindCrytoItem(TokensAdapter.AssetHolder holder, final int position) {
        final Coin coin = getBaseDao().mGrpcBalance.get(position);
        if (coin.denom.equals(TOKEN_CRO)) {
            holder.itemSymbol.setText(getString(R.string.str_cro_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), CRYPTO_MAIN));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Crypto.org Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokencrypto));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_CRO);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 8, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 8));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getMainActivity(), StakingTokenDetailActivity.class));
                }
            });

        } else if (coin.denom.startsWith("ibc/")) {
            holder.itemSymbol.setText("IBC");
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemInnerSymbol.setText("(unKnown)");
            holder.itemFullName.setText(coin.denom);
            holder.itemFullName.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));

        } else {

        }
    }


    private void onBindRizonItem(TokensAdapter.AssetHolder holder, final int position) {
        final Coin coin = getBaseDao().mGrpcBalance.get(position);
        if (coin.denom.equals(TOKEN_RIZON)) {
            holder.itemSymbol.setText(getString(R.string.str_rizon_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), RIZON_TEST));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Rizon Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_rizon));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_RIZON);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getMainActivity(), StakingTokenDetailActivity.class));
                }
            });

        } else if (coin.denom.startsWith("ibc/")) {
            holder.itemSymbol.setText("IBC");
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemInnerSymbol.setText("(unKnown)");
            holder.itemFullName.setText(coin.denom);
            holder.itemFullName.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));

        } else {

        }
    }

    private void onBindAltheaItem(TokensAdapter.AssetHolder holder, final int position) {
        final Coin coin = getBaseDao().mGrpcBalance.get(position);
        if (coin.denom.equals(TOKEN_ALTHEA)) {
            holder.itemSymbol.setText(getString(R.string.str_althea_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), ALTHEA_TEST));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Althea Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokenalthea));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_ALTHEA);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getMainActivity(), StakingTokenDetailActivity.class));
                }
            });

        } else if (coin.denom.startsWith("ibc/")) {
            holder.itemSymbol.setText("IBC");
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemInnerSymbol.setText("(unKnown)");
            holder.itemFullName.setText(coin.denom);
            holder.itemFullName.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));

        } else {

        }
    }

    private void onBindOsmosisItem(TokensAdapter.AssetHolder holder, final int position) {
        final Coin coin = getBaseDao().mGrpcBalance.get(position);
        if (coin.denom.equals(TOKEN_OSMOSIS)) {
            holder.itemSymbol.setText(getString(R.string.str_osmosis_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), OSMOSIS_MAIN));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Osmosis Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_osmosis));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_OSMOSIS);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getMainActivity(), StakingTokenDetailActivity.class));
                }
            });

        } else if (coin.denom.startsWith("uion")) {
            holder.itemSymbol.setText(getString(R.string.str_uion_c));
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorIon));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Ion Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_ion));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_ION);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.startsWith("ibc/")) {
            holder.itemSymbol.setText("IBC");
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemInnerSymbol.setText("(unKnown)");
            holder.itemFullName.setText(coin.denom);
            holder.itemFullName.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));

        } else if (coin.denom.startsWith("gamm/")) {
            holder.itemSymbol.setText("AMM");
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemInnerSymbol.setText("(unKnown)");
            holder.itemFullName.setText(coin.denom);
            holder.itemFullName.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_ic));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));
        }
    }

    private void onBindBandItem(TokensAdapter.AssetHolder holder, final int position) {
        final Coin coin = getBaseDao().mGrpcBalance.get(position);
        if (coin.denom.equals(TOKEN_BAND)) {
            holder.itemSymbol.setText(getString(R.string.str_band_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), BAND_MAIN));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Band Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.band_token_img));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_BAND);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getMainActivity(), StakingTokenDetailActivity.class));
                }
            });

        } else if (coin.denom.startsWith("ibc/")) {
            holder.itemSymbol.setText("IBC");
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemInnerSymbol.setText("(unKnown)");
            holder.itemFullName.setText(coin.denom);
            holder.itemFullName.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));

        } else {

        }
    }

    private void onBindIovItem(TokensAdapter.AssetHolder holder, final int position) {
        final Coin coin = getBaseDao().mGrpcBalance.get(position);
        if (coin.denom.equals(TOKEN_IOV)) {
            holder.itemSymbol.setText(getString(R.string.str_iov_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), IOV_MAIN));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Starname Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.iov_token_img));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_IOV);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getMainActivity(), StakingTokenDetailActivity.class));
                }
            });

        } else if (coin.denom.startsWith("ibc/")) {
            holder.itemSymbol.setText("IBC");
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemInnerSymbol.setText("(unKnown)");
            holder.itemFullName.setText(coin.denom);
            holder.itemFullName.setEllipsize(TextUtils.TruncateAt.MIDDLE);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));

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
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else { }
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
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else { }

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
