package wannabit.io.cosmostaion.fragment;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.TypedValue;
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
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.NativeTokenDetailActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.StakingTokenDetailActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.IbcToken;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.SIF_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_AKASH;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ALTHEA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CRO;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_DVPN;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ION;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_MEDI;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OSMOSIS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_RIZON;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SIF;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_XPRT;

public class MainTokensFragment extends BaseFragment {

    public final static int     SECTION_NATIVE_GRPC = 0;
    public final static int     SECTION_IBC_AUTHED_GRPC = 1;
    public final static int     SECTION_OSMOSIS_POOL_GRPC = 2;
    public final static int     SECTION_SIF_ETHER_GRPC = 3;
    public final static int     SECTION_IBC_UNKNOWN_GRPC = 4;

    private int                 mSection;                   // section 구분

    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;
    private LinearLayout        mEmptyToken;
    private CardView            mCardTotal;
    private TextView            mTotalValue;

    private TokensAdapter       mTokensAdapter;

    private RecyclerViewHeader  mRecyclerViewHeader;

    private ArrayList<Coin>     mNativeGrpc = new ArrayList<>();
    private ArrayList<Coin>     mIbcAuthedGrpc = new ArrayList<>();
    private ArrayList<Coin>     mOsmosisPoolGrpc = new ArrayList<>();
    private ArrayList<Coin>     mSifEtherGrpc = new ArrayList<>();
    private ArrayList<Coin>     mIbcUnknownGrpc = new ArrayList<>();
    private ArrayList<Coin>     mUnknownGrpc = new ArrayList<>();

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
        mSwipeRefreshLayout = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mEmptyToken = rootView.findViewById(R.id.empty_token);
        mCardTotal = rootView.findViewById(R.id.card_total);
        mTotalValue = rootView.findViewById(R.id.total_value);
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

        if (isGRPC(getMainActivity().mBaseChain)) {
            mRecyclerViewHeader = new RecyclerViewHeader(getMainActivity(), true, getSectionGrpcCall());
        } else {
//            mRecyclerViewHeader = new RecyclerViewHeader(getMainActivity(), true, getSectionCall(getBaseDao().mBalances));
        }
        mRecyclerView.addItemDecoration(mRecyclerViewHeader);

        return rootView;
    }

    private SectionCallback getSectionGrpcCall() {
        return new SectionCallback() {
            // 헤더 구분 true / false
            @Override
            public boolean isSection(BaseChain baseChain, int position) {
                if (isGRPC(baseChain)) {
                    // 조건
                }
                return position == 0;
            }

            // 헤더 제목 조건
            @Override
            public String getSectionHeader(BaseChain baseChain, ArrayList<Coin> coins, int section) {
                if (isGRPC(baseChain)) {
                    if (section == SECTION_NATIVE_GRPC) {
                        return getMainActivity().getString(R.string.str_native_token_title);
                    } else if (section == SECTION_IBC_AUTHED_GRPC) {
                        return getMainActivity().getString(R.string.str_ibc_token_title);
                    } else if (section == SECTION_IBC_UNKNOWN_GRPC) {
                        return getMainActivity().getString(R.string.str_unknown_ibc_token_title);
                    }

                    else if (section == SECTION_OSMOSIS_POOL_GRPC) {
                        return getMainActivity().getString(R.string.str_pool_token_title);
                    }

                    else if (section == SECTION_SIF_ETHER_GRPC) {
                        return getMainActivity().getString(R.string.str_sif_ether_token_title);
                    }
                }
                return getMainActivity().getString(R.string.str_unknown_token_title);
            }
        };
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
        switch (item.getItemId()) {
            case R.id.menu_accounts:
                getMainActivity().onShowTopAccountsView();
                break;
            case R.id.menu_explorer:
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
        if (!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
        mRecyclerView.getRecycledViewPool().clear();
        onUpdateView();
    }

    @Override
    public void onBusyFetch() {
        if (!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void onUpdateView() {
        mCardTotal.setCardBackgroundColor(WDp.getChainBgColor(getContext(), getMainActivity().mBaseChain));
        mTotalValue.setText(WDp.dpAllAssetValueUserCurrency(getMainActivity().mBaseChain, getBaseDao()));

        final String mainDenom = WDp.mainDenom(getMainActivity().mBaseChain);
        mNativeGrpc.clear();
        mIbcAuthedGrpc.clear();
        mOsmosisPoolGrpc.clear();
        mSifEtherGrpc.clear();
        mIbcUnknownGrpc.clear();
        mUnknownGrpc.clear();
        for (Coin coin : getBaseDao().mGrpcBalance) {
            if (coin.isIbc()) {
                final IbcToken ibcToken = BaseData.getIbcToken(coin.getIbcHash());
                if (ibcToken.auth == true) {
                    mIbcAuthedGrpc.add(coin);
                } else {
                    mIbcUnknownGrpc.add(coin);
                }
            } else {
                if (getMainActivity().mBaseChain.equals(OSMOSIS_MAIN)) {
                    if (coin.denom.equalsIgnoreCase(mainDenom) || coin.denom.equalsIgnoreCase(TOKEN_ION)) {
                        mNativeGrpc.add(coin);
                    } else if (coin.denom.startsWith("gamm/")) {
                        mOsmosisPoolGrpc.add(coin);
                    } else {
                        mUnknownGrpc.add(coin);
                    }
                } else if (getMainActivity().mBaseChain.equals(SIF_MAIN)) {
                    if (coin.denom.equalsIgnoreCase(mainDenom)) {
                        mNativeGrpc.add(coin);
                    } else if (coin.denom.startsWith("c")){
                        mSifEtherGrpc.add(coin);
                    } else {
                        mUnknownGrpc.add(coin);
                    }
                } else {
                    if (coin.denom.equalsIgnoreCase(mainDenom)) {
                        mNativeGrpc.add(coin);
                    } else {
                        mUnknownGrpc.add(coin);
                    }
                }
            }
        }

        if (isGRPC(getMainActivity().mBaseChain)) {
            WUtil.onSortingTokenV1(mNativeGrpc, getMainActivity().mBaseChain);
        } else {
            WUtil.onSortingTokenByName(getBaseDao().mBalances, getMainActivity().mBaseChain);
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

    private class TokensAdapter extends RecyclerView.Adapter<TokensAdapter.AssetHolder> {

        @NonNull
        @Override
        public AssetHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View rowView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_token, viewGroup, false);
            AssetHolder viewHolder = new AssetHolder(rowView);
            return viewHolder;
        }

        @Override
        public void onBindViewHolder(@NonNull AssetHolder viewHolder, int position) {
            mSection = getItemViewType(position);
            if (getMainActivity().mBaseChain.equals(OSMOSIS_MAIN)) {
                if (mSection == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, position);
                } else if (mSection == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, position - mNativeGrpc.size());
                } else if (mSection == SECTION_OSMOSIS_POOL_GRPC) {
                    onBindOsmoPoolToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                } else if (mSection == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mOsmosisPoolGrpc.size());
                }

            } else if (getMainActivity().mBaseChain.equals(SIF_MAIN)) {
                if (mSection == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, position);
                } else if (mSection == SECTION_SIF_ETHER_GRPC) {
                    onBindSifToken(viewHolder, position - mNativeGrpc.size());
                } else if (mSection == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, position - mNativeGrpc.size() - mSifEtherGrpc.size());
                } else if (mSection == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, position - mNativeGrpc.size() - mSifEtherGrpc.size() - mIbcAuthedGrpc.size());
                }

            } else if (isGRPC(getMainActivity().mBaseChain)) {
                if (mSection == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, position);
                } else if (mSection == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, position - mNativeGrpc.size());
                } else if (mSection == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                }
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

        @Override
        public int getItemViewType(int position) {
            if (getMainActivity().mBaseChain.equals(OSMOSIS_MAIN)) {
                if (position < mNativeGrpc.size()) {
                    return SECTION_NATIVE_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size()) {
                    return SECTION_IBC_AUTHED_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mOsmosisPoolGrpc.size()) {
                    return SECTION_OSMOSIS_POOL_GRPC;
                }
            } else if (getMainActivity().mBaseChain.equals(SIF_MAIN)) {
                if (position < mNativeGrpc.size()) {
                    return SECTION_NATIVE_GRPC;
                } else if (position < mNativeGrpc.size() + mSifEtherGrpc.size()) {
                    return SECTION_SIF_ETHER_GRPC;
                } else if (position < mNativeGrpc.size() + mSifEtherGrpc.size() + mIbcAuthedGrpc.size()) {
                    return SECTION_IBC_AUTHED_GRPC;
                } else if (position < mNativeGrpc.size() + mSifEtherGrpc.size() + mIbcAuthedGrpc.size() + mIbcUnknownGrpc.size()) {
                    return SECTION_IBC_UNKNOWN_GRPC;
                }
            } else if (isGRPC(getMainActivity().mBaseChain)) {
                if (position < mNativeGrpc.size()) {
                    return SECTION_NATIVE_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size()) {
                    return SECTION_IBC_AUTHED_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mIbcUnknownGrpc.size()) {
                    return SECTION_IBC_UNKNOWN_GRPC;
                }
            }
            return SECTION_NATIVE_GRPC;
        }

        public class AssetHolder extends RecyclerView.ViewHolder {
            private CardView itemRoot;
            private ImageView itemImg;
            private TextView itemSymbol, itemInnerSymbol, itemFullName, itemBalance, itemValue;

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

    //with Native gRPC
    private void onNativeGrpcItem(TokensAdapter.AssetHolder holder, final int position) {
        final Coin coin = mNativeGrpc.get(position);
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

        } else if (coin.denom.equals(TOKEN_IRIS)) {
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

        } else if (coin.denom.equals(TOKEN_AKASH)) {
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

        } else if (coin.denom.equals(TOKEN_DVPN)) {
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

        } else if (coin.denom.equals(TOKEN_XPRT)) {
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

        } else if (coin.denom.equals(TOKEN_CRO)) {
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

        } else if (coin.denom.equals(TOKEN_OSMOSIS)) {
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

        } else if (coin.denom.equals(TOKEN_ION)) {
            holder.itemSymbol.setText(getString(R.string.str_uion_c));
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorIon));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Ion Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_ion));

            BigDecimal totalAmount = getBaseDao().getAvailable(TOKEN_ION);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.equals(TOKEN_IOV)) {
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

        } else if (coin.denom.equals(TOKEN_SIF)) {
            holder.itemSymbol.setText(getString(R.string.str_sif_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), SIF_MAIN));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Sif Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokensifchain));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_SIF);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 18, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 18));

            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getMainActivity(), StakingTokenDetailActivity.class));
                }
            });
        } else if (coin.denom.equals(TOKEN_MEDI)) {
            holder.itemSymbol.setText(getString(R.string.str_medi_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), MEDI_MAIN));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Medibloc Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokenmedibloc));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_MEDI);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    startActivity(new Intent(getMainActivity(), StakingTokenDetailActivity.class));
                }
            });

        } else if (coin.denom.equals(TOKEN_RIZON)) {
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

        } else if (coin.denom.equals(TOKEN_ALTHEA)) {
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

        } else if (coin.denom.equals(TOKEN_COSMOS_TEST)) {
            holder.itemSymbol.setText(getString(R.string.str_muon_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), COSMOS_TEST));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Stargate Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.atom_ic));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_COSMOS_TEST);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.equals(TOKEN_IRIS_TEST)) {
            holder.itemSymbol.setText(getString(R.string.str_bif_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), IRIS_TEST));
            holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            holder.itemFullName.setText("Bifrost Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.iris_toket_img));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_IRIS_TEST);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));
        }
    }

    //with Authed IBC gRPC
    private void onBindIbcAuthToken(TokensAdapter.AssetHolder holder, int position) {
        final Coin coin = mIbcAuthedGrpc.get(position);
        final IbcToken ibcToken = getBaseDao().getIbcToken(coin.denom);
        holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
        holder.itemFullName.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        if (ibcToken == null) {
            holder.itemSymbol.setText("Unknown");
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText(coin.denom);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, BigDecimal.ZERO, 6));
        } else {
            holder.itemSymbol.setText(ibcToken.display_denom.toUpperCase());
            holder.itemInnerSymbol.setText("(" + ibcToken.base_denom + ")");
            holder.itemFullName.setText(coin.denom);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), ibcToken.decimal, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), ibcToken.base_denom, new BigDecimal(coin.amount), ibcToken.decimal));
            try {
                Picasso.get().load(ibcToken.moniker).fit().placeholder(R.drawable.token_default_ibc).error(R.drawable.token_default_ibc).into(holder.itemImg);
            } catch (Exception e) {
            }
        }
    }

    //with Unknown IBC gRPC
    private void onBindIbcUnknownToken(TokensAdapter.AssetHolder holder, int position) {
        final Coin coin = mIbcUnknownGrpc.get(position);
        final IbcToken ibcToken = getBaseDao().getIbcToken(coin.denom);
        if (ibcToken == null) {
            holder.itemSymbol.setText("Unknown");
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText(coin.denom);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, BigDecimal.ZERO, 6));
        } else {
            holder.itemSymbol.setText("Unknown");
            holder.itemInnerSymbol.setText("(" + ibcToken.base_denom + ")");
            holder.itemFullName.setText(coin.denom);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, new BigDecimal(coin.amount), 6));
        }
    }

    //with Osmosis Pool gRPC
    private void onBindOsmoPoolToken(TokensAdapter.AssetHolder holder, int position) {
        final Coin coin = mOsmosisPoolGrpc.get(position);
        holder.itemSymbol.setText(coin.osmosisAmmDpDenom());
        holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
        holder.itemInnerSymbol.setText("");
        holder.itemFullName.setText(coin.denom);
        holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_pool));
        holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 18, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, BigDecimal.ZERO, 18));
    }

    //with Sif Erc gRPC
    private void onBindSifToken(TokensAdapter.AssetHolder holder, int position) {
        final Coin coin = mSifEtherGrpc.get(position);
        final int dpDecimal = WUtil.getSifCoinDecimal(coin.denom);
        holder.itemSymbol.setText(coin.denom.substring(1).toUpperCase());
        holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
        holder.itemInnerSymbol.setText("(" + coin.denom + ")");
        holder.itemFullName.setText(coin.denom.substring(1).toUpperCase() + " on Sif Chain");
        Picasso.get().load(SIF_COIN_IMG_URL + coin.denom + ".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(holder.itemImg);

        BigDecimal totalAmount = getBaseDao().getAvailable(coin.denom);
        holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, dpDecimal, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom.substring(1), totalAmount, dpDecimal));

        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getMainActivity(), NativeTokenDetailActivity.class);
                intent.putExtra("denom", coin.denom);
                startActivity(intent);
            }
        });
    }

    public MainActivity getMainActivity() {
        return (MainActivity) getBaseActivity();
    }


    // Section Header
    public class RecyclerViewHeader extends RecyclerView.ItemDecoration {
        private final int topPadding;

        private final boolean sticky;
        private final SectionCallback sectionCallback;

        private View headerView;
        private TextView mHeaderTitle;
        private TextView mItemCnt;

        public RecyclerViewHeader(Context context, boolean sticky, @NonNull SectionCallback sectionCallback) {
            this.sticky = sticky;
            this.sectionCallback = sectionCallback;

            topPadding = dpToPx(context, 26);
        }

        // dp -> pixel 단위로 변경
        private int dpToPx(Context context, int dp) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        }

        @Override
        public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
            super.onDrawOver(c, parent, state);

            if (headerView == null) {
                headerView = inflateHeaderView(parent);
                mHeaderTitle = (TextView) headerView.findViewById(R.id.header_title);
                mItemCnt = (TextView) headerView.findViewById(R.id.recycler_cnt);
                fixLayoutSize(headerView, parent);
            }

            String previousHeader = "";
            for (int i = 0; i < parent.getChildCount(); i++) {
                View child = parent.getChildAt(i);
                final int position = parent.getChildAdapterPosition(child);
                if (position == RecyclerView.NO_POSITION) {
                    return;
                }

                String title = "";
                mSection = parent.getAdapter().getItemViewType(position);
                if (isGRPC(getMainActivity().mBaseChain)) {
                    if (mSection == SECTION_NATIVE_GRPC) {
                        title = sectionCallback.getSectionHeader(getMainActivity().mBaseChain, mNativeGrpc, mSection);
                        mItemCnt.setText("" + mNativeGrpc.size());
                    } else if (mSection == SECTION_IBC_AUTHED_GRPC) {
                        title = sectionCallback.getSectionHeader(getMainActivity().mBaseChain, mIbcAuthedGrpc, mSection);
                        mItemCnt.setText("" + mIbcAuthedGrpc.size());
                    } else if (mSection == SECTION_IBC_UNKNOWN_GRPC) {
                        title = sectionCallback.getSectionHeader(getMainActivity().mBaseChain, mIbcUnknownGrpc, mSection);
                        mItemCnt.setText("" + mIbcUnknownGrpc.size());
                    }

                    // osmosis pool token
                    else if (mSection == SECTION_OSMOSIS_POOL_GRPC) {
                        title = sectionCallback.getSectionHeader(getMainActivity().mBaseChain, mOsmosisPoolGrpc, mSection);
                        mItemCnt.setText("" + mOsmosisPoolGrpc.size());
                    }

                    // sif ether bridge token
                    else if (mSection == SECTION_SIF_ETHER_GRPC) {
                        title = sectionCallback.getSectionHeader(getMainActivity().mBaseChain, mSifEtherGrpc, mSection);
                        mItemCnt.setText("" + mSifEtherGrpc.size());
                    }
                }
                mHeaderTitle.setText(title);
                if (!previousHeader.equals(title)) {
                    drawHeader(c, child, headerView);
                    previousHeader = title;
                }
            }
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            int position = parent.getChildAdapterPosition(view);
            if (sectionCallback.isSection(getMainActivity().mBaseChain, position)) {
                outRect.top = topPadding;
            }
        }

        private void drawHeader(Canvas c, View child, View headerView) {
            c.save();
            if (sticky) {
                c.translate(0, Math.max(0, child.getTop() - headerView.getHeight()));
            } else {
                c.translate(0, child.getTop() - headerView.getHeight());
            }
            headerView.draw(c);
            c.restore();
        }

        private View inflateHeaderView(RecyclerView parent) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sticky_header, parent, false);
        }

        private void fixLayoutSize(View view, ViewGroup parent) {
            int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(),
                    View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(),
                    View.MeasureSpec.UNSPECIFIED);

            int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
                    parent.getPaddingLeft() + parent.getPaddingRight(),
                    view.getLayoutParams().width);
            int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
                    parent.getPaddingTop() + parent.getPaddingBottom(),
                    view.getLayoutParams().height);

            view.measure(childWidth, childHeight);

            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }

    public interface SectionCallback {
        boolean isSection(BaseChain baseChain, int position);
        String getSectionHeader(BaseChain baseChain, ArrayList<Coin> coins, int section);
    }
}