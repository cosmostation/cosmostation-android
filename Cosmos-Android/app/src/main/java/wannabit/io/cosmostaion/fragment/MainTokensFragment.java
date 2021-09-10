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

import tendermint.liquidity.v1beta1.Liquidity;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.BridgeTokenGrpcActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.IBCTokenDetailActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.NativeTokenDetailActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.NativeTokenGrpcActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.POOLTokenDetailActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.StakingTokenDetailActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.StakingTokenGrpcActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.IbcToken;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.AXELAR_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EMONEY_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.UMEE_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.OKEX_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.SIF_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_AKASH;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ALTHEA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_AXELAR;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BAND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_COSMOS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CRO;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_DVPN;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_FET;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_XRPB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ION;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KI;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_MEDI;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_NGM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OSMOSIS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_RIZON;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SECRET;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SIF;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SWP;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_UMEE;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_USDX;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_XPRT;

public class MainTokensFragment extends BaseFragment {

    public final static int     SECTION_NATIVE_GRPC             = 0;
    public final static int     SECTION_IBC_AUTHED_GRPC         = 1;
    public final static int     SECTION_OSMOSIS_POOL_GRPC       = 2;
    public final static int     SECTION_SIF_ETHER_GRPC          = 3;
    public final static int     SECTION_IBC_UNKNOWN_GRPC        = 4;
    public final static int     SECTION_GRAVICTY_DEX_GRPC       = 5;
    public final static int     SECTION_UNKNOWN_GRPC            = 6;

    public final static int     SECTION_NATIVE                  = 6;
    public final static int     SECTION_KAVA_BEP2               = 7;
    public final static int     SECTION_ETC                     = 8;
    public final static int     SECTION_UNKNOWN                 = 9;

    private int                 mSection;                       // section 구분

    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;
    private LinearLayout        mEmptyToken;

    private TokensAdapter       mTokensAdapter;

    private RecyclerViewHeader  mRecyclerViewHeader;

    private ArrayList<Coin>     mNativeGrpc = new ArrayList<>();
    private ArrayList<Coin>     mIbcAuthedGrpc = new ArrayList<>();
    private ArrayList<Coin>     mOsmosisPoolGrpc = new ArrayList<>();
    private ArrayList<Coin>     mSifEtherGrpc = new ArrayList<>();
    private ArrayList<Coin>     mIbcUnknownGrpc = new ArrayList<>();
    private ArrayList<Coin>     mGravityDexGrpc = new ArrayList<>();
    private ArrayList<Coin>     mUnknownGrpc = new ArrayList<>();

    private ArrayList<Balance>  mNative = new ArrayList<>();
    private ArrayList<Balance>  mKavaBep2 = new ArrayList<>();
    private ArrayList<Balance>  mEtc = new ArrayList<>();
    private ArrayList<Balance>  mUnKnown = new ArrayList<>();

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

        mRecyclerViewHeader = new RecyclerViewHeader(getMainActivity(), true, getSectionGrpcCall());
        mRecyclerView.addItemDecoration(mRecyclerViewHeader);

        return rootView;
    }

    private SectionCallback getSectionGrpcCall() {
        return new SectionCallback() {
            // 헤더 구분 true / false
            @Override
            public boolean isSection(BaseChain baseChain, int position) {
                if (baseChain.equals(OSMOSIS_MAIN)) {
                    return position == 0 || position == mNativeGrpc.size() || position == mNativeGrpc.size() + mIbcAuthedGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mOsmosisPoolGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mOsmosisPoolGrpc.size() + mIbcUnknownGrpc.size();

                } else if (baseChain.equals(COSMOS_MAIN)) {
                    return position == 0 || position == mNativeGrpc.size() || position == mNativeGrpc.size() + mIbcAuthedGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mGravityDexGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mGravityDexGrpc.size() + mIbcUnknownGrpc.size();

                } else if (baseChain.equals(SIF_MAIN)) {
                    return position == 0 || position == mNativeGrpc.size() || position == mNativeGrpc.size() + mIbcAuthedGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mSifEtherGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mSifEtherGrpc.size() + mIbcUnknownGrpc.size();

                } else if (isGRPC(baseChain)){
                    return position == 0 || position == mNativeGrpc.size() || position == mNativeGrpc.size() + mIbcAuthedGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mIbcUnknownGrpc.size();

                }

                else if (baseChain.equals(KAVA_MAIN)) {
                    return position == 0 || position == mNative.size() || position == mNative.size() + mKavaBep2.size();

                } else {
                    return position == 0 || position == mNative.size();
                }
            }

            // 헤더 제목 조건
            @Override
            public String getSectionGrpcHeader(BaseChain baseChain, ArrayList<Coin> coins, int section) {
                if (section == SECTION_NATIVE_GRPC) {
                    return getMainActivity().getString(R.string.str_native_token_title);
                } else if (section == SECTION_IBC_AUTHED_GRPC) {
                    return getMainActivity().getString(R.string.str_ibc_token_title);
                } else if (section == SECTION_IBC_UNKNOWN_GRPC) {
                    return getMainActivity().getString(R.string.str_unknown_ibc_token_title);
                } else if (section == SECTION_UNKNOWN) {
                    return getMainActivity().getString(R.string.str_unknown_token_title);
                }

                else if (section == SECTION_OSMOSIS_POOL_GRPC) {
                    return getMainActivity().getString(R.string.str_pool_token_title);
                }

                else if (section == SECTION_SIF_ETHER_GRPC) {
                    return getMainActivity().getString(R.string.str_sif_ether_token_title);
                }

                else if (section == SECTION_GRAVICTY_DEX_GRPC) {
                    return getMainActivity().getString(R.string.str_gravity_dex_token_title);
                }
                return getMainActivity().getString(R.string.str_unknown_token_title);
            }

            @Override
            public String getSecitonHeader(BaseChain baseChain, ArrayList<Balance> balances, int section) {
                if (section == SECTION_NATIVE) {
                    return getMainActivity().getString(R.string.str_native_token_title);
                } else if (section == SECTION_KAVA_BEP2) {
                    return getMainActivity().getString(R.string.str_kava_bep2_token_title);
                } else if (section == SECTION_ETC) {
                    return getMainActivity().getString(R.string.str_etc_token_title);
                } else if (section == SECTION_UNKNOWN) {
                    return getMainActivity().getString(R.string.str_unknown_token_title);
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
        final String mainDenom = WDp.mainDenom(getMainActivity().mBaseChain);
        mNativeGrpc.clear();
        mIbcAuthedGrpc.clear();
        mOsmosisPoolGrpc.clear();
        mSifEtherGrpc.clear();
        mIbcUnknownGrpc.clear();
        mGravityDexGrpc.clear();
        mUnknownGrpc.clear();
        for (Coin coin : getBaseDao().mGrpcBalance) {
            if (coin.denom.equalsIgnoreCase(mainDenom)) {
                mNativeGrpc.add(coin);
            } else if (coin.isIbc()) {
                final IbcToken ibcToken = BaseData.getIbcToken(coin.getIbcHash());
                if (ibcToken.auth == true) {
                    mIbcAuthedGrpc.add(coin);
                } else {
                    mIbcUnknownGrpc.add(coin);
                }
            } else if (getMainActivity().mBaseChain.equals(OSMOSIS_MAIN) && coin.osmosisAmm()) {
                mOsmosisPoolGrpc.add(coin);
            } else if (getMainActivity().mBaseChain.equals(OSMOSIS_MAIN) && coin.denom.equalsIgnoreCase(TOKEN_ION)) {
                mNativeGrpc.add(coin);
            } else if (getMainActivity().mBaseChain.equals(SIF_MAIN) && coin.denom.startsWith("c")) {
                mSifEtherGrpc.add(coin);
            } else if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) && coin.denom.startsWith("pool")) {
                mGravityDexGrpc.add(coin);
            } else {
                mUnknownGrpc.add(coin);
            }
        }
        mNative.clear();
        mKavaBep2.clear();
        mEtc.clear();
        mUnKnown.clear();
        for (Balance balance : getBaseDao().mBalances) {
            if (balance.symbol.equalsIgnoreCase(mainDenom)) {
                mNative.add(balance);
            } else if (getMainActivity().mBaseChain.equals(KAVA_MAIN)) {
                if (balance.symbol.equalsIgnoreCase(TOKEN_HARD) || balance.symbol.equalsIgnoreCase(TOKEN_USDX) || balance.symbol.equalsIgnoreCase(TOKEN_SWP)) {
                    mNative.add(balance);
                } else if (balance.symbol.equalsIgnoreCase(TOKEN_HTLC_KAVA_BNB) || balance.symbol.equalsIgnoreCase(TOKEN_HTLC_KAVA_BTCB) ||
                            balance.symbol.equalsIgnoreCase(TOKEN_HTLC_KAVA_XRPB) || balance.symbol.equalsIgnoreCase(TOKEN_HTLC_KAVA_BUSD)) {
                    mKavaBep2.add(balance);
                } else if (balance.symbol.equalsIgnoreCase("btch")) {
                    mEtc.add(balance);
                }
            } else if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) {
                mEtc.add(balance);
            } else if (getMainActivity().mBaseChain.equals(OKEX_MAIN)){
                mEtc.add(balance);
            } else {
                mUnKnown.add(balance);
            }
        }

        if (isGRPC(getMainActivity().mBaseChain)) {
            WUtil.onSortingCoins(mNativeGrpc, getMainActivity().mBaseChain);
            WUtil.onSortingGravityPool(mGravityDexGrpc, getBaseDao());
            WUtil.onSortingOsmosisPool(mOsmosisPoolGrpc);

        } else if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST) ||
                    getMainActivity().mBaseChain.equals(OKEX_MAIN) || getMainActivity().mBaseChain.equals(OK_TEST)) {
            WUtil.onSortingNativeCoins(mEtc, getMainActivity().mBaseChain);
        } else {
            WUtil.onSortingNativeCoins(mNative, getMainActivity().mBaseChain);
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
            if (getMainActivity().mBaseChain.equals(OSMOSIS_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, position);
                } else if (getItemViewType(position) == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, position - mNativeGrpc.size());
                } else if (getItemViewType(position) == SECTION_OSMOSIS_POOL_GRPC) {
                    onBindOsmoPoolToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                } else if (getItemViewType(position) == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mOsmosisPoolGrpc.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN_GRPC){
                    onBindUnKnownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mOsmosisPoolGrpc.size() - mIbcUnknownGrpc.size());
                }

            } else if (getMainActivity().mBaseChain.equals(SIF_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, position);
                } else if (getItemViewType(position) == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, position - mNativeGrpc.size());
                } else if (getItemViewType(position) == SECTION_SIF_ETHER_GRPC) {
                    onBindSifToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                }  else if (getItemViewType(position) == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mSifEtherGrpc.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN_GRPC){
                    onBindUnKnownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mSifEtherGrpc.size() - mIbcUnknownGrpc.size());
                }

            } else if (getMainActivity().mBaseChain.equals(COSMOS_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, position);
                } else if (getItemViewType(position) == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, position - mNativeGrpc.size());
                } else if (getItemViewType(position) == SECTION_GRAVICTY_DEX_GRPC){
                    onBindGravityDexToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                } else if (getItemViewType(position) == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mGravityDexGrpc.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN_GRPC){
                    onBindUnKnownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mGravityDexGrpc.size() - mIbcUnknownGrpc.size());
                }

            } else if (isGRPC(getMainActivity().mBaseChain)) {
                if (getItemViewType(position) == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, position);
                } else if (getItemViewType(position) == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, position - mNativeGrpc.size());
                } else if (getItemViewType(position) == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN_GRPC){
                    onBindUnKnownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mIbcUnknownGrpc.size());
                }

            }

            else if (getMainActivity().mBaseChain.equals(KAVA_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE) {
                    onBindNativeItem(viewHolder, position);
                } else if (getItemViewType(position) == SECTION_KAVA_BEP2) {
                    onBindKavaBep2Token(viewHolder, position - mNative.size());
                } else if (getItemViewType(position) == SECTION_ETC) {
                    onBindEtcToken(viewHolder, position - mNative.size() - mKavaBep2.size());
                }
            } else if (getMainActivity().mBaseChain.equals(OKEX_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE) {
                    onBindNativeItem(viewHolder, position);
                } else if (getItemViewType(position) == SECTION_ETC) {
                    onBindEtcToken(viewHolder, position - mNative.size());
                }
            } else if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) {
                if (getItemViewType(position) == SECTION_NATIVE) {
                    onBindNativeItem(viewHolder, position);
                } else if (getItemViewType(position) == SECTION_ETC) {
                    onBindEtcToken(viewHolder, position - mNative.size());
                }
            } else {
                if (getItemViewType(position) == SECTION_NATIVE) {
                    onBindNativeItem(viewHolder, position);
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
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mOsmosisPoolGrpc.size() + mIbcUnknownGrpc.size()) {
                    return SECTION_IBC_UNKNOWN_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mOsmosisPoolGrpc.size() + mIbcUnknownGrpc.size() + mUnknownGrpc.size()) {
                    return SECTION_UNKNOWN_GRPC;
                }

            } else if (getMainActivity().mBaseChain.equals(SIF_MAIN)) {
                if (position < mNativeGrpc.size()) {
                    return SECTION_NATIVE_GRPC;
                }  else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size()) {
                    return SECTION_IBC_AUTHED_GRPC;
                }  else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mSifEtherGrpc.size()) {
                    return SECTION_SIF_ETHER_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mSifEtherGrpc.size() + mIbcUnknownGrpc.size()) {
                    return SECTION_IBC_UNKNOWN_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mSifEtherGrpc.size() + mIbcUnknownGrpc.size() + mUnknownGrpc.size()) {
                    return SECTION_UNKNOWN_GRPC;
                }

            } else if (getMainActivity().mBaseChain.equals(COSMOS_MAIN)) {
                if (position < mNativeGrpc.size()) {
                    return SECTION_NATIVE_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size()) {
                    return SECTION_IBC_AUTHED_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mGravityDexGrpc.size()) {
                    return SECTION_GRAVICTY_DEX_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mGravityDexGrpc.size() + mIbcUnknownGrpc.size()) {
                    return SECTION_IBC_UNKNOWN_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mGravityDexGrpc.size() + mIbcUnknownGrpc.size() + mUnknownGrpc.size()) {
                    return SECTION_UNKNOWN_GRPC;
                }

            } else if (isGRPC(getMainActivity().mBaseChain)) {
                if (position < mNativeGrpc.size()) {
                    return SECTION_NATIVE_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size()) {
                    return SECTION_IBC_AUTHED_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mIbcUnknownGrpc.size()) {
                    return SECTION_IBC_UNKNOWN_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mIbcUnknownGrpc.size() + mUnknownGrpc.size()) {
                    return SECTION_UNKNOWN_GRPC;
                }
            }

            else if (getMainActivity().mBaseChain.equals(KAVA_MAIN)) {
                if (position < mNative.size()) {
                    return SECTION_NATIVE;
                } else if (position < mNative.size() + mKavaBep2.size()) {
                    return SECTION_KAVA_BEP2;
                } else if (position < mNative.size() + mKavaBep2.size() + mEtc.size()) {
                    return SECTION_ETC;
                } else if (position < mNative.size() + mKavaBep2.size() + mEtc.size() + mUnKnown.size()) {
                    return SECTION_UNKNOWN;
                }

            } else if (getMainActivity().mBaseChain.equals(OKEX_MAIN)) {
                if (mNative != null) {
                    if (position < mNative.size()) {
                        return SECTION_NATIVE;
                    } else if (position < mNative.size() + mEtc.size()) {
                        return SECTION_ETC;
                    } else if (position < mNative.size() + mEtc.size() + mUnKnown.size()) {
                        return SECTION_UNKNOWN;
                    }
                } else {
                    if (position < mEtc.size()) {
                        return SECTION_ETC;
                    } else if (position < mEtc.size() + mUnKnown.size()) {
                        return SECTION_UNKNOWN;
                    }
                }

            } else if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) {
                if (mNative != null) {
                    if (position < mNative.size()) {
                        return SECTION_NATIVE;
                    } else if (position < mNative.size() + mEtc.size()) {
                        return SECTION_ETC;
                    } else if (position < mNative.size() + mEtc.size() + mUnKnown.size()) {
                        return SECTION_UNKNOWN;
                    }
                } else {
                    if (position < mEtc.size()) {
                        return SECTION_ETC;
                    } else if (position < mEtc.size() + mUnKnown.size()) {
                        return SECTION_UNKNOWN;
                    }
                }
            } else {
                return SECTION_NATIVE;
            }
            return 0;
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
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Cosmos Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.atom_ic));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_ATOM);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.equals(TOKEN_IRIS)) {
            holder.itemSymbol.setText(getString(R.string.str_iris_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), IRIS_MAIN));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Iris Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.iris_toket_img));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_IRIS);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.equals(TOKEN_AKASH)) {
            holder.itemSymbol.setText(getString(R.string.str_akt_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), AKASH_MAIN));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Akash Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.akash_token_img));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_AKASH);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.equals(TOKEN_DVPN)) {
            holder.itemSymbol.setText(getString(R.string.str_dvpn_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), SENTINEL_MAIN));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Sentinel Native Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokensentinel));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_DVPN);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.equals(TOKEN_XPRT)) {
            holder.itemSymbol.setText(getString(R.string.str_xprt_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), PERSIS_MAIN));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Persistence Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokenpersistence));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_XPRT);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.equals(TOKEN_CRO)) {
            holder.itemSymbol.setText(getString(R.string.str_cro_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), CRYPTO_MAIN));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Crypto.org Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokencrypto));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_CRO);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 8, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 8));

        } else if (coin.denom.equals(TOKEN_OSMOSIS)) {
            holder.itemSymbol.setText(getString(R.string.str_osmosis_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), OSMOSIS_MAIN));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Osmosis Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_osmosis));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_OSMOSIS);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.equals(TOKEN_ION)) {
            holder.itemSymbol.setText(getString(R.string.str_uion_c));
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorIon));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Ion Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_ion));

            BigDecimal totalAmount = getBaseDao().getAvailable(TOKEN_ION);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.equals(TOKEN_IOV)) {
            holder.itemSymbol.setText(getString(R.string.str_iov_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), IOV_MAIN));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Starname Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.iov_token_img));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_IOV);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.equals(TOKEN_SIF)) {
            holder.itemSymbol.setText(getString(R.string.str_sif_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), SIF_MAIN));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Sif Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokensifchain));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_SIF);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 18, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 18));

        } else if (coin.denom.equals(TOKEN_MEDI)) {
            holder.itemSymbol.setText(getString(R.string.str_medi_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), MEDI_MAIN));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Medibloc Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokenmedibloc));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_MEDI);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.equals(TOKEN_CERTIK)) {
            holder.itemSymbol.setText(getString(R.string.str_ctk_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), CERTIK_MAIN));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Certik Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.certik_token_img));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_CERTIK);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.equals(TOKEN_RIZON)) {
            holder.itemSymbol.setText(getString(R.string.str_rizon_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), RIZON_TEST));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Rizon Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_rizon));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_RIZON);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.equals(TOKEN_ALTHEA)) {
            holder.itemSymbol.setText(getString(R.string.str_althea_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), ALTHEA_TEST));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Althea Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokenalthea));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_ALTHEA);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.equals(TOKEN_UMEE)) {
            holder.itemSymbol.setText(getString(R.string.str_umee_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), UMEE_TEST));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Umee Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_umee));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_UMEE);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.equals(TOKEN_NGM)) {
            holder.itemSymbol.setText(getString(R.string.str_ngm_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), EMONEY_MAIN));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Emoney Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_emoney));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_NGM);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.equals(TOKEN_AXELAR)) {
            holder.itemSymbol.setText(getString(R.string.str_axl_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), AXELAR_TEST));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Axelar Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_axelar));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_AXELAR);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.equals(TOKEN_COSMOS_TEST)) {
            holder.itemSymbol.setText(getString(R.string.str_muon_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), COSMOS_TEST));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Stargate Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.atom_ic));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_COSMOS_TEST);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));

        } else if (coin.denom.equals(TOKEN_IRIS_TEST)) {
            holder.itemSymbol.setText(getString(R.string.str_bif_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), IRIS_TEST));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Bifrost Staking Token");
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.iris_toket_img));

            BigDecimal totalAmount = getBaseDao().getAllMainAsset(TOKEN_IRIS_TEST);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));
        }

        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mNativeGrpc.get(position).denom.equalsIgnoreCase(WDp.mainDenom(getMainActivity().mBaseChain))) {
                    Intent intent = new Intent(getMainActivity(), StakingTokenGrpcActivity.class);
                    intent.putExtra("denom", coin.denom);
                    startActivity(intent);
                } else {
                    Intent intent = new Intent(getMainActivity(), NativeTokenGrpcActivity.class);
                    intent.putExtra("denom", coin.denom);
                    startActivity(intent);
                }
            }
        });
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
            holder.itemFullName.setText("");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, BigDecimal.ZERO, 6));
        } else {
            holder.itemSymbol.setText(ibcToken.display_denom.toUpperCase());
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText(ibcToken.channel_id);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), ibcToken.decimal, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), ibcToken.base_denom, new BigDecimal(coin.amount), ibcToken.decimal));
            try {
                Picasso.get().load(ibcToken.moniker).fit().placeholder(R.drawable.token_default_ibc).error(R.drawable.token_default_ibc).into(holder.itemImg);
            } catch (Exception e) { }
        }

        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getMainActivity(), IBCTokenDetailActivity.class);
                intent.putExtra("denom", coin.denom);
                startActivity(intent);
            }
        });
    }

    //with Unknown IBC gRPC
    private void onBindIbcUnknownToken(TokensAdapter.AssetHolder holder, int position) {
        final Coin coin = mIbcUnknownGrpc.get(position);
        final IbcToken ibcToken = getBaseDao().getIbcToken(coin.denom);
        if (ibcToken == null) {
            holder.itemSymbol.setText("Unknown");
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, BigDecimal.ZERO, 6));
        } else {
            holder.itemSymbol.setText("Unknown");
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText(ibcToken.channel_id);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, new BigDecimal(coin.amount), 6));
        }

        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getMainActivity(), IBCTokenDetailActivity.class);
                intent.putExtra("denom", coin.denom);
                startActivity(intent);
            }
        });
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

        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getMainActivity(), POOLTokenDetailActivity.class);
                intent.putExtra("denom", coin.denom);
                startActivity(intent);
            }
        });
    }

    //with Cosmos Gravity Dex gRpc
    private void onBindGravityDexToken(TokensAdapter.AssetHolder holder, int position) {
        final Coin coin = mGravityDexGrpc.get(position);
        Picasso.get().load(COSMOS_COIN_IMG_URL+"gravitydex.png") .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic) .into(holder.itemImg);
        holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, BigDecimal.ZERO, 6));
        holder.itemInnerSymbol.setText("");
        Liquidity.Pool poolInfo = getBaseDao().getGravityPoolByDenom(coin.denom);
        if (poolInfo != null) {
            holder.itemSymbol.setText("GDEX-" + poolInfo.getId());
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemFullName.setText("pool/" + poolInfo.getId());
        }
    }

    //with Sif Erc gRPC
    private void onBindSifToken(TokensAdapter.AssetHolder holder, int position) {
        final Coin coin = mSifEtherGrpc.get(position);
        final int dpDecimal = WUtil.getSifCoinDecimal(coin.denom);
        holder.itemSymbol.setText(coin.denom.substring(1).toUpperCase());
        holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
        holder.itemInnerSymbol.setText("");
        holder.itemFullName.setText(coin.denom.substring(1).toUpperCase() + " on Sif Chain");
        Picasso.get().load(SIF_COIN_IMG_URL + coin.denom + ".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(holder.itemImg);

        BigDecimal totalAmount = getBaseDao().getAvailable(coin.denom);
        holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, dpDecimal, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom.substring(1), totalAmount, dpDecimal));

        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getMainActivity(), BridgeTokenGrpcActivity.class);
                intent.putExtra("denom", coin.denom);
                startActivity(intent);
            }
        });
    }

    //with Unknown Token gRPC
    private void onBindUnKnownToken(TokensAdapter.AssetHolder holder, int position) {
        final Coin coin = mUnknownGrpc.get(position);
        holder.itemSymbol.setText(coin.denom.toUpperCase());
        holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
        holder.itemInnerSymbol.setText("");
        holder.itemFullName.setText("");
        holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_ic));
        holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, BigDecimal.ZERO, 6));
    }


    //with native tokens
    private void onBindNativeItem(TokensAdapter.AssetHolder holder, int position) {
        final Balance balance = mNative.get(position);
        if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) {
            final String denom      = mNative.get(position).symbol;
            final BigDecimal amount = getBaseDao().getAllBnbTokenAmount(denom);
            final BnbToken bnbToken = getBaseDao().getBnbToken(denom);
            if (bnbToken != null) {
                holder.itemSymbol.setText(bnbToken.original_symbol.toUpperCase());
                holder.itemInnerSymbol.setText("(" + bnbToken.symbol + ")");
                holder.itemFullName.setText(bnbToken.name);
                holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.bnb_token_img));
                holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), BNB_MAIN));
                holder.itemBalance.setText(WDp.getDpAmount2(getContext(), amount, 0, 6));
                holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), TOKEN_BNB, amount, 0));
            }
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getMainActivity(), StakingTokenDetailActivity.class);
                    startActivity(intent);
                }
            });

        } else if (balance.symbol.equals(TOKEN_KAVA)) {
            Picasso.get().cancelRequest(holder.itemImg);
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), KAVA_MAIN));
            holder.itemSymbol.setText(getString(R.string.str_kava_c));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("Kava Staking Token");
            Picasso.get().load(KAVA_COIN_IMG_URL+balance.symbol+".png") .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic) .into(holder.itemImg);

            BigDecimal totalAmount = getBaseDao().getAllMainAssetOld(TOKEN_KAVA);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, totalAmount, 6));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getMainActivity(), StakingTokenDetailActivity.class);
                    startActivity(intent);
                }
            });

        } else if (balance.symbol.equals(TOKEN_HARD)) {
            Picasso.get().load(KAVA_COIN_IMG_URL+balance.symbol+".png") .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic) .into(holder.itemImg);
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorHard));
            holder.itemSymbol.setText(balance.symbol.toUpperCase());
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("HardPool Gov. Token");

            BigDecimal tokenTotalAmount = getBaseDao().availableAmount(balance.symbol).add(getBaseDao().lockedAmount(balance.symbol));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), tokenTotalAmount, WUtil.getKavaCoinDecimal(balance.symbol), 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, tokenTotalAmount, 6));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getMainActivity(), NativeTokenDetailActivity.class);
                    intent.putExtra("denom", balance.symbol);
                    startActivity(intent);
                }
            });

        } else if (balance.symbol.equals(TOKEN_USDX)) {
            Picasso.get().load(KAVA_COIN_IMG_URL+balance.symbol+".png") .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic) .into(holder.itemImg);
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorUsdx));
            holder.itemSymbol.setText(balance.symbol.toUpperCase());
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("USD Stable Asset");

            BigDecimal tokenTotalAmount = getBaseDao().availableAmount(balance.symbol).add(getBaseDao().lockedAmount(balance.symbol));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), tokenTotalAmount, WUtil.getKavaCoinDecimal(balance.symbol), 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, tokenTotalAmount, 6));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getMainActivity(), NativeTokenDetailActivity.class);
                    intent.putExtra("denom", balance.symbol);
                    startActivity(intent);
                }
            });

        } else if (balance.symbol.equals(TOKEN_SWP)) {
            Picasso.get().load(KAVA_COIN_IMG_URL+balance.symbol+".png") .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic) .into(holder.itemImg);
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorSwp));
            holder.itemSymbol.setText(balance.symbol.toUpperCase());
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("Kava Swap Token");

            BigDecimal tokenTotalAmount = getBaseDao().availableAmount(balance.symbol).add(getBaseDao().lockedAmount(balance.symbol));
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), tokenTotalAmount, WUtil.getKavaCoinDecimal(balance.symbol), 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), TOKEN_SWP, tokenTotalAmount, 6));
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getMainActivity(), NativeTokenDetailActivity.class);
                    intent.putExtra("denom", balance.symbol);
                    startActivity(intent);
                }
            });

        } else if (getMainActivity().mBaseChain.equals(OKEX_MAIN)) {
            final OkToken okToken = getBaseDao().okToken(balance.symbol);
            holder.itemSymbol.setText(okToken.original_symbol.toUpperCase());
            holder.itemInnerSymbol.setText("(" + okToken.symbol + ")");
            holder.itemFullName.setText(okToken.description);
            if (balance.symbol.equals(TOKEN_OK)) {
                holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), getMainActivity().mBaseChain));
                holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.okex_token_img));

                BigDecimal totalAmount = getBaseDao().getAllExToken(balance.symbol);
                holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 0, 6));
                holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, totalAmount, 0));
            }
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getMainActivity(), StakingTokenDetailActivity.class);
                    startActivity(intent);
                }
            });

        } else if (balance.symbol.equals(TOKEN_BAND)) {
            holder.itemSymbol.setText(getString(R.string.str_band_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), BAND_MAIN));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("Band Staking Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.band_token_img));

            BigDecimal totalAmount = getBaseDao().getAllMainAssetOld(TOKEN_BAND);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, totalAmount, 6));

        } else if (balance.symbol.equals(TOKEN_SECRET)) {
            holder.itemSymbol.setText(getString(R.string.str_scrt_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), SECRET_MAIN));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("Secret Native Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokensecret));

            BigDecimal totalAmount = getBaseDao().getAllMainAssetOld(TOKEN_SECRET);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, totalAmount, 6));

        } else if (balance.symbol.equals(TOKEN_FET)) {
            holder.itemSymbol.setText(getString(R.string.str_fet_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), FETCHAI_MAIN));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("Fetch.ai Staking Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.tokenfetchai));

            BigDecimal totalAmount = getBaseDao().getAllMainAssetOld(TOKEN_FET);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 18, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, totalAmount, 18));

        } else if (balance.symbol.equals(TOKEN_KI)) {
            holder.itemSymbol.setText(getString(R.string.str_ki_c));
            holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), KI_MAIN));
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText("KiChain Staking Token");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_kifoundation));

            BigDecimal totalAmount = getBaseDao().getAllMainAssetOld(TOKEN_KI);
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, totalAmount, 6));
        }
    }

    //with kava bep2 tokens
    private void onBindKavaBep2Token(TokensAdapter.AssetHolder holder, int position) {
        final Balance balance = mKavaBep2.get(position);
        Picasso.get().load(KAVA_COIN_IMG_URL+balance.symbol+".png") .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic) .into(holder.itemImg);
        holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
        holder.itemSymbol.setText(balance.symbol.toUpperCase());
        holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
        holder.itemFullName.setText(balance.symbol.toUpperCase() + " on Kava Chain");

        BigDecimal tokenTotalAmount = getBaseDao().availableAmount(balance.symbol).add(getBaseDao().lockedAmount(balance.symbol));
        String baseDenom = WDp.getKavaBaseDenom(balance.symbol);
        int bep2decimal = WUtil.getKavaCoinDecimal(balance.symbol);
        holder.itemBalance.setText(WDp.getDpAmount2(getContext(), tokenTotalAmount, WUtil.getKavaCoinDecimal(balance.symbol), 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), baseDenom, tokenTotalAmount, bep2decimal));
        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getMainActivity(), NativeTokenDetailActivity.class);
                intent.putExtra("denom", balance.symbol);
                startActivity(intent);
            }
        });
    }

    //with Etc tokens (kava, binance, okex)
    private void onBindEtcToken(TokensAdapter.AssetHolder holder, int position) {
        final Balance balance   = mEtc.get(position);
        if (getMainActivity().mBaseChain.equals(KAVA_MAIN)) {
            Picasso.get().load(KAVA_COIN_IMG_URL+balance.symbol+".png") .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic) .into(holder.itemImg);
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            holder.itemSymbol.setText(balance.symbol.toUpperCase());
            holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
            holder.itemFullName.setText(balance.symbol.toUpperCase() + " on Kava Chain");

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

        } else if (getMainActivity().mBaseChain.equals(OKEX_MAIN)) {
            final OkToken okToken   = getBaseDao().okToken(balance.symbol);
            holder.itemSymbol.setText(okToken.original_symbol.toUpperCase());
            holder.itemInnerSymbol.setText("(" + okToken.symbol + ")");
            holder.itemFullName.setText(okToken.description);
            holder.itemSymbol.setTextColor(getResources().getColor(R.color.colorWhite));
            Picasso.get().load(OKEX_COIN_IMG_URL + okToken.original_symbol + ".png").placeholder(R.drawable.token_ic).error(R.drawable.token_ic).fit().into(holder.itemImg);

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

        } else if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) {
            final String denom      = mEtc.get(position).symbol;
            final BigDecimal amount = getBaseDao().getAllBnbTokenAmount(denom);
            final BnbToken bnbToken = getBaseDao().getBnbToken(denom);

            holder.itemSymbol.setText(bnbToken.original_symbol.toUpperCase());
            holder.itemInnerSymbol.setText("(" + bnbToken.symbol + ")");
            holder.itemFullName.setText(bnbToken.name);
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

            topPadding = dpToPx(context, 30);
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
                        title = sectionCallback.getSectionGrpcHeader(getMainActivity().mBaseChain, mNativeGrpc, mSection);
                        mItemCnt.setText("" + mNativeGrpc.size());
                    } else if (mSection == SECTION_IBC_AUTHED_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(getMainActivity().mBaseChain, mIbcAuthedGrpc, mSection);
                        mItemCnt.setText("" + mIbcAuthedGrpc.size());
                    } else if (mSection == SECTION_IBC_UNKNOWN_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(getMainActivity().mBaseChain, mIbcUnknownGrpc, mSection);
                        mItemCnt.setText("" + mIbcUnknownGrpc.size());
                    } else if (mSection == SECTION_UNKNOWN_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(getMainActivity().mBaseChain, mUnknownGrpc, mSection);
                        mItemCnt.setText("" + mUnknownGrpc.size());
                    }

                    // osmosis pool token
                    else if (mSection == SECTION_OSMOSIS_POOL_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(getMainActivity().mBaseChain, mOsmosisPoolGrpc, mSection);
                        mItemCnt.setText("" + mOsmosisPoolGrpc.size());
                    }

                    // sif ether bridge token
                    else if (mSection == SECTION_SIF_ETHER_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(getMainActivity().mBaseChain, mSifEtherGrpc, mSection);
                        mItemCnt.setText("" + mSifEtherGrpc.size());
                    }

                    // cosmos gravity dex token
                    else if (mSection == SECTION_GRAVICTY_DEX_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(getMainActivity().mBaseChain, mGravityDexGrpc, mSection);
                        mItemCnt.setText("" + mGravityDexGrpc.size());
                    }

                } else {
                    if (mSection == SECTION_NATIVE) {
                        title = sectionCallback.getSecitonHeader(getMainActivity().mBaseChain, mNative, mSection);
                        mItemCnt.setText("" + mNative.size());
                    } else if (mSection == SECTION_KAVA_BEP2) {
                        title = sectionCallback.getSecitonHeader(getMainActivity().mBaseChain, mKavaBep2, mSection);
                        mItemCnt.setText("" + mKavaBep2.size());
                    } else if (mSection == SECTION_ETC) {
                        title = sectionCallback.getSecitonHeader(getMainActivity().mBaseChain, mEtc, mSection);
                        mItemCnt.setText("" + mEtc.size());
                    } else if (mSection == SECTION_UNKNOWN) {
                        title = sectionCallback.getSecitonHeader(getMainActivity().mBaseChain, mUnKnown, mSection);
                        mItemCnt.setText("" + mUnKnown.size());
                    }
                }
                mHeaderTitle.setText(title);
                if (!previousHeader.equals(title) || sectionCallback.isSection(getMainActivity().mBaseChain, position)) {
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
        String getSectionGrpcHeader(BaseChain baseChain, ArrayList<Coin> coins, int section);
        String getSecitonHeader(BaseChain baseChain, ArrayList<Balance> balances, int section);
    }
}