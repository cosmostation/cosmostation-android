package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CUDOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EMONEY_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.EVMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.FETCHAI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.GRABRIDGE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.INJ_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.JUNO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.PROVENANCE_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.ASSET_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.BINANCE_TOKEN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.EMONEY_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.OKEX_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BTCB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_BUSD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_XRPB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ION;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SWP;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_USDX;

import android.annotation.SuppressLint;
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
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.fulldive.wallet.presentation.accounts.AccountShowDialogFragment;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;

import tendermint.liquidity.v1beta1.Liquidity;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.BridgeTokenGrpcActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.ContractTokenGrpcActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.IBCTokenDetailActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.NativeTokenDetailActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.NativeTokenGrpcActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.POOLTokenDetailActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.StakingTokenDetailActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.StakingTokenGrpcActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IBusyFetchListener;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Assets;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.Cw20Assets;
import wannabit.io.cosmostaion.dao.IbcToken;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class MainTokensFragment extends BaseFragment implements IBusyFetchListener, IRefreshTabListener {

    public final static int SECTION_NATIVE_GRPC = 0;
    public final static int SECTION_IBC_AUTHED_GRPC = 1;
    public final static int SECTION_OSMOSIS_POOL_GRPC = 2;
    public final static int SECTION_ETHER_GRPC = 3;
    public final static int SECTION_IBC_UNKNOWN_GRPC = 4;
    public final static int SECTION_GRAVICTY_DEX_GRPC = 5;
    public final static int SECTION_INJECTIVE_POOL_GRPC = 6;
    public final static int SECTION_KAVA_BEP2_GRPC = 7;
    public final static int SECTION_ETC_GRPC = 8;
    public final static int SECTION_CW20_GRPC = 9;
    public final static int SECTION_UNKNOWN_GRPC = 10;

    public final static int SECTION_NATIVE = 20;
    public final static int SECTION_ETC = 21;
    public final static int SECTION_UNKNOWN = 22;

    private int mSection;                       // section 구분

    private CardView mCardView;
    private ImageView itemKeyStatus;
    private TextView mWalletAddress;
    private TextView mTotalValue;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayout mEmptyToken;

    private TokensAdapter mTokensAdapter;

    private RecyclerViewHeader mRecyclerViewHeader;

    private final ArrayList<Coin> mNativeGrpc = new ArrayList<>();
    private final ArrayList<Coin> mIbcAuthedGrpc = new ArrayList<>();
    private final ArrayList<Coin> mOsmosisPoolGrpc = new ArrayList<>();
    private final ArrayList<Coin> mEtherGrpc = new ArrayList<>();
    private final ArrayList<Coin> mIbcUnknownGrpc = new ArrayList<>();
    private final ArrayList<Coin> mGravityDexGrpc = new ArrayList<>();
    private final ArrayList<Coin> mInjectivePoolGrpc = new ArrayList<>();
    private final ArrayList<Coin> mKavaBep2Grpc = new ArrayList<>();
    private final ArrayList<Coin> mEtcGrpc = new ArrayList<>();
    private ArrayList<Cw20Assets> mCW20Grpc = new ArrayList<>();
    private final ArrayList<Coin> mUnknownGrpc = new ArrayList<>();

    private final ArrayList<Balance> mNative = new ArrayList<>();
    private final ArrayList<Balance> mEtc = new ArrayList<>();
    private final ArrayList<Balance> mUnKnown = new ArrayList<>();

    private Account mAccount;
    private BaseChain mBaseChain;

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

    @SuppressLint("ClickableViewAccessibility")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_tokens, container, false);
        mCardView = rootView.findViewById(R.id.card_root);
        itemKeyStatus = rootView.findViewById(R.id.img_account);
        mWalletAddress = rootView.findViewById(R.id.wallet_address);
        mTotalValue = rootView.findViewById(R.id.total_value);
        mSwipeRefreshLayout = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mEmptyToken = rootView.findViewById(R.id.empty_token);

        mCardView.setOnClickListener(v -> showAddressDialog());

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(rootView.getContext(), R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            onUpdateInfo();
            getMainActivity().onFetchAllData();
        });

        mRecyclerView.setOnTouchListener((View view, MotionEvent motionEvent) -> mSwipeRefreshLayout.isRefreshing());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mTokensAdapter = new TokensAdapter();
        mRecyclerView.setAdapter(mTokensAdapter);

        mRecyclerViewHeader = new RecyclerViewHeader(getMainActivity(), true, getSectionGrpcCall());
        mRecyclerView.addItemDecoration(mRecyclerViewHeader);

        onUpdateInfo();
        return rootView;
    }

    private void onUpdateInfo() {
        if (getMainActivity() == null || getMainActivity().account == null) return;
        mAccount = getMainActivity().account;
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        mCardView.setCardBackgroundColor(WDp.getChainBgColor(getMainActivity(), mBaseChain));
        if (mAccount.hasPrivateKey) {
            itemKeyStatus.setColorFilter(WDp.getChainColor(getMainActivity(), mBaseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        } else {
            itemKeyStatus.setColorFilter(ContextCompat.getColor(getMainActivity(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        }
        mWalletAddress.setText(mAccount.address);
        mTotalValue.setText(WDp.dpAllAssetValueUserCurrency(mBaseChain, getBaseDao()));
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

                } else if (baseChain.equals(SIF_MAIN) || baseChain.equals(GRABRIDGE_MAIN)) {
                    return position == 0 || position == mNativeGrpc.size() || position == mNativeGrpc.size() + mIbcAuthedGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size() + mIbcUnknownGrpc.size();

                } else if (baseChain.equals(INJ_MAIN)) {
                    return position == 0 || position == mNativeGrpc.size() || position == mNativeGrpc.size() + mIbcAuthedGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size() + mInjectivePoolGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size() + mInjectivePoolGrpc.size() + mIbcUnknownGrpc.size();

                } else if (baseChain.equals(KAVA_MAIN)) {
                    return position == 0 || position == mNativeGrpc.size() || position == mNativeGrpc.size() + mIbcAuthedGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mKavaBep2Grpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mKavaBep2Grpc.size() + mEtcGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mKavaBep2Grpc.size() + mEtcGrpc.size() + mIbcUnknownGrpc.size();

                } else if (baseChain.equals(JUNO_MAIN)) {
                    return position == 0 || position == mNativeGrpc.size() || position == mNativeGrpc.size() + mIbcAuthedGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mCW20Grpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mCW20Grpc.size() + mIbcUnknownGrpc.size();

                } else if (baseChain.isGRPC()) {
                    return position == 0 || position == mNativeGrpc.size() || position == mNativeGrpc.size() + mIbcAuthedGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mIbcUnknownGrpc.size();

                } else {
                    return position == 0 || position == mNative.size() || position == mNative.size() + mEtc.size();
                }
            }

            // 헤더 제목 조건
            @Override
            public String getSectionGrpcHeader(BaseChain baseChain, ArrayList<Coin> coins, int section) {
                if (section == SECTION_NATIVE_GRPC) {
                    return getString(R.string.str_native_token_title);
                } else if (section == SECTION_IBC_AUTHED_GRPC) {
                    return getString(R.string.str_ibc_token_title);
                } else if (section == SECTION_IBC_UNKNOWN_GRPC) {
                    return getString(R.string.str_unknown_ibc_token_title);
                } else if (section == SECTION_UNKNOWN_GRPC) {
                    return getString(R.string.str_unknown_token_title);
                } else if (section == SECTION_OSMOSIS_POOL_GRPC || section == SECTION_INJECTIVE_POOL_GRPC) {
                    return getString(R.string.str_pool_coin_title);
                } else if (section == SECTION_ETHER_GRPC) {
                    return getString(R.string.str_sif_ether_token_title);
                } else if (section == SECTION_GRAVICTY_DEX_GRPC) {
                    return getString(R.string.str_gravity_dex_token_title);
                } else if (section == SECTION_KAVA_BEP2_GRPC) {
                    return getString(R.string.str_kava_bep2_token_title);
                } else if (section == SECTION_ETC_GRPC) {
                    return getString(R.string.str_etc_token_title);
                }

                return getString(R.string.str_unknown_token_title);
            }

            @Override
            public String getSectionCw20Header(BaseChain baseChain, ArrayList<Cw20Assets> cw20Assets, int section) {
                if (section == SECTION_CW20_GRPC) {
                    return getString(R.string.str_cw20_token_title);
                }
                return getString(R.string.str_unknown_token_title);
            }

            @Override
            public String getSecitonHeader(BaseChain baseChain, ArrayList<Balance> balances, int section) {
                if (section == SECTION_NATIVE) {
                    return getString(R.string.str_native_token_title);
                } else if (section == SECTION_ETC) {
                    if (baseChain.equals(OKEX_MAIN)) {
                        return getString(R.string.str_oec_kip10_title);
                    }
                    return getString(R.string.str_etc_token_title);
                } else if (section == SECTION_UNKNOWN) {
                    return getString(R.string.str_unknown_token_title);
                }
                return getString(R.string.str_unknown_token_title);
            }
        };
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        getMainActivity().getMenuInflater().inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_accounts:
                getMainActivity().onClickSwitchWallet();
                break;
            case R.id.menu_explorer:
                getMainActivity().onExplorerView();
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefreshTab() {
        if (!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
        mRecyclerView.getRecycledViewPool().clear();
        onUpdateInfo();
        onUpdateView();
    }

    @Override
    public void onBusyFetch() {
        if (!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void onUpdateView() {
        final String mainDenom = getMainActivity().baseChain.getMainDenom();
        mCW20Grpc = getBaseDao().getCw20sGrpc(getMainActivity().baseChain);
        mNativeGrpc.clear();
        mIbcAuthedGrpc.clear();
        mOsmosisPoolGrpc.clear();
        mEtherGrpc.clear();
        mIbcUnknownGrpc.clear();
        mGravityDexGrpc.clear();
        mInjectivePoolGrpc.clear();
        mKavaBep2Grpc.clear();
        mEtcGrpc.clear();
        mUnknownGrpc.clear();
        for (Coin coin : getBaseDao().mGrpcBalance) {
            if (coin.denom.equalsIgnoreCase(mainDenom)) {
                mNativeGrpc.add(coin);
            } else if (coin.isIbc()) {
                final IbcToken ibcToken = getBaseDao().getIbcToken(coin.getIbcHash());
                if (ibcToken != null && ibcToken.auth) {
                    mIbcAuthedGrpc.add(coin);
                } else {
                    mIbcUnknownGrpc.add(coin);
                }
            } else if (getMainActivity().baseChain.equals(OSMOSIS_MAIN) && coin.osmosisAmm()) {
                mOsmosisPoolGrpc.add(coin);
            } else if (getMainActivity().baseChain.equals(OSMOSIS_MAIN) && coin.denom.equalsIgnoreCase(TOKEN_ION) ||
                    getMainActivity().baseChain.equals(EMONEY_MAIN) && coin.denom.startsWith("e")) {
                mNativeGrpc.add(coin);
            } else if (getMainActivity().baseChain.equals(SIF_MAIN) && coin.denom.startsWith("c") ||
                    getMainActivity().baseChain.equals(GRABRIDGE_MAIN) && coin.denom.startsWith("gravity") ||
                    getMainActivity().baseChain.equals(INJ_MAIN) && coin.denom.startsWith("peggy")) {
                mEtherGrpc.add(coin);
            } else if (getMainActivity().baseChain.equals(COSMOS_MAIN) && coin.denom.startsWith("pool")) {
                mGravityDexGrpc.add(coin);
            } else if (getMainActivity().baseChain.equals(INJ_MAIN) && coin.denom.startsWith("share")) {
                mInjectivePoolGrpc.add(coin);
            } else if (getMainActivity().baseChain.equals(KAVA_MAIN)) {
                if (coin.denom.equals(TOKEN_HARD) || coin.denom.equalsIgnoreCase(TOKEN_USDX) || coin.denom.equalsIgnoreCase(TOKEN_SWP)) {
                    mNativeGrpc.add(coin);
                } else if (coin.denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BNB) || coin.denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BTCB) ||
                        coin.denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_XRPB) || coin.denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BUSD)) {
                    mKavaBep2Grpc.add(coin);
                } else if (coin.denom.equalsIgnoreCase("btch") || coin.denom.equalsIgnoreCase("hbtc")) {
                    mEtcGrpc.add(coin);
                }
            } else {
                mUnknownGrpc.add(coin);
            }
        }
        mNative.clear();
        mEtc.clear();
        mUnKnown.clear();
        for (Balance balance : getBaseDao().mBalances) {
            if (balance.symbol.equalsIgnoreCase(mainDenom)) {
                mNative.add(balance);
            } else if (getMainActivity().baseChain.equals(BNB_MAIN)) {
                mEtc.add(balance);
            } else if (getMainActivity().baseChain.equals(OKEX_MAIN)) {
                mEtc.add(balance);
            } else {
                mUnKnown.add(balance);
            }
        }

        if (getMainActivity().baseChain.isGRPC()) {
            WUtil.onSortingCoins(mNativeGrpc, getMainActivity().baseChain);
            WUtil.onSortingGravityPool(mGravityDexGrpc, getBaseDao());
            WUtil.onSortingOsmosisPool(mOsmosisPoolGrpc);
            WUtil.onSortingInjectivePool(mInjectivePoolGrpc);

        } else if (getMainActivity().baseChain.equals(BNB_MAIN) || getMainActivity().baseChain.equals(OKEX_MAIN)) {
            WUtil.onSortingNativeCoins(mEtc, getMainActivity().baseChain);
        } else {
            WUtil.onSortingNativeCoins(mNative, getMainActivity().baseChain);
        }

        if (getMainActivity().baseChain.isGRPC()) {
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
            if (getMainActivity().baseChain.equals(OSMOSIS_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, position);
                } else if (getItemViewType(position) == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, position - mNativeGrpc.size());
                } else if (getItemViewType(position) == SECTION_OSMOSIS_POOL_GRPC) {
                    onBindOsmoPoolToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                } else if (getItemViewType(position) == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mOsmosisPoolGrpc.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN_GRPC) {
                    onBindUnKnownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mOsmosisPoolGrpc.size() - mIbcUnknownGrpc.size());
                }

            } else if (getMainActivity().baseChain.equals(SIF_MAIN) || getMainActivity().baseChain.equals(GRABRIDGE_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, position);
                } else if (getItemViewType(position) == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, position - mNativeGrpc.size());
                } else if (getItemViewType(position) == SECTION_ETHER_GRPC) {
                    onBindErcToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                } else if (getItemViewType(position) == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mEtherGrpc.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN_GRPC) {
                    onBindUnKnownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mEtherGrpc.size() - mIbcUnknownGrpc.size());
                }

            } else if (getMainActivity().baseChain.equals(COSMOS_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, position);
                } else if (getItemViewType(position) == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, position - mNativeGrpc.size());
                } else if (getItemViewType(position) == SECTION_GRAVICTY_DEX_GRPC) {
                    onBindGravityDexToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                } else if (getItemViewType(position) == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mGravityDexGrpc.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN_GRPC) {
                    onBindUnKnownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mGravityDexGrpc.size() - mIbcUnknownGrpc.size());
                }

            } else if (getMainActivity().baseChain.equals(INJ_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, position);
                } else if (getItemViewType(position) == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, position - mNativeGrpc.size());
                } else if (getItemViewType(position) == SECTION_ETHER_GRPC) {
                    onBindErcToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                } else if (getItemViewType(position) == SECTION_INJECTIVE_POOL_GRPC) {
                    onBindInjectivePoolToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mEtherGrpc.size());
                } else if (getItemViewType(position) == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mEtherGrpc.size() - mInjectivePoolGrpc.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN_GRPC) {
                    onBindUnKnownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mEtherGrpc.size() - mInjectivePoolGrpc.size() - mIbcUnknownGrpc.size());
                }

            } else if (getMainActivity().baseChain.equals(KAVA_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, position);
                } else if (getItemViewType(position) == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, position - mNativeGrpc.size());
                } else if (getItemViewType(position) == SECTION_KAVA_BEP2_GRPC) {
                    onBindKavaBep2Token(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                } else if (getItemViewType(position) == SECTION_ETC_GRPC) {
                    onBindEtcGrpcToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mKavaBep2Grpc.size());
                } else if (getItemViewType(position) == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mKavaBep2Grpc.size() - mEtcGrpc.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN_GRPC) {
                    onBindUnKnownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mKavaBep2Grpc.size() - mEtcGrpc.size() - mIbcUnknownGrpc.size());
                }

            } else if (getMainActivity().baseChain.equals(JUNO_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, position);
                } else if (getItemViewType(position) == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, position - mNativeGrpc.size());
                } else if (getItemViewType(position) == SECTION_CW20_GRPC) {
                    onBindCw20GrpcToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                } else if (getItemViewType(position) == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mCW20Grpc.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN_GRPC) {
                    onBindUnKnownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mCW20Grpc.size() - mIbcUnknownGrpc.size());
                }

            } else if (getMainActivity().baseChain.isGRPC()) {
                if (getItemViewType(position) == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, position);
                } else if (getItemViewType(position) == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, position - mNativeGrpc.size());
                } else if (getItemViewType(position) == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN_GRPC) {
                    onBindUnKnownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mIbcUnknownGrpc.size());
                }

            } else if (getMainActivity().baseChain.equals(OKEX_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE) {
                    onBindNativeItem(viewHolder, position);
                } else if (getItemViewType(position) == SECTION_ETC) {
                    onBindEtcToken(viewHolder, position - mNative.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN) {
                    onBindUnKnownCoin(viewHolder, position - mNative.size() - mEtc.size());
                }
            } else if (getMainActivity().baseChain.equals(BNB_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE) {
                    onBindNativeItem(viewHolder, position);
                } else if (getItemViewType(position) == SECTION_ETC) {
                    onBindEtcToken(viewHolder, position - mNative.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN) {
                    onBindUnKnownCoin(viewHolder, position - mNative.size() - mEtc.size());
                }
            } else {
                if (getItemViewType(position) == SECTION_NATIVE) {
                    onBindNativeItem(viewHolder, position);
                } else if (getItemViewType(position) == SECTION_UNKNOWN) {
                    onBindUnKnownCoin(viewHolder, position - mNative.size());
                }
            }
        }

        @Override
        public int getItemCount() {
            if (getMainActivity().baseChain.equals(JUNO_MAIN)) {
                return getBaseDao().mGrpcBalance.size() + mCW20Grpc.size();
            } else if (getMainActivity().baseChain.isGRPC()) {
                return getBaseDao().mGrpcBalance.size();
            } else {
                return getBaseDao().mBalances.size();
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (getMainActivity().baseChain.equals(OSMOSIS_MAIN)) {
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

            } else if (getMainActivity().baseChain.equals(SIF_MAIN) || getMainActivity().baseChain.equals(GRABRIDGE_MAIN)) {
                if (position < mNativeGrpc.size()) {
                    return SECTION_NATIVE_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size()) {
                    return SECTION_IBC_AUTHED_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size()) {
                    return SECTION_ETHER_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size() + mIbcUnknownGrpc.size()) {
                    return SECTION_IBC_UNKNOWN_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size() + mIbcUnknownGrpc.size() + mUnknownGrpc.size()) {
                    return SECTION_UNKNOWN_GRPC;
                }

            } else if (getMainActivity().baseChain.equals(COSMOS_MAIN)) {
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

            } else if (getMainActivity().baseChain.equals(INJ_MAIN)) {
                if (position < mNativeGrpc.size()) {
                    return SECTION_NATIVE_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size()) {
                    return SECTION_IBC_AUTHED_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size()) {
                    return SECTION_ETHER_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size() + mInjectivePoolGrpc.size()) {
                    return SECTION_INJECTIVE_POOL_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size() + mInjectivePoolGrpc.size() + mIbcUnknownGrpc.size()) {
                    return SECTION_IBC_UNKNOWN_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size() + mInjectivePoolGrpc.size() + mIbcUnknownGrpc.size() + mUnknownGrpc.size()) {
                    return SECTION_UNKNOWN_GRPC;
                }

            } else if (getMainActivity().baseChain.equals(KAVA_MAIN)) {
                if (position < mNativeGrpc.size()) {
                    return SECTION_NATIVE_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size()) {
                    return SECTION_IBC_AUTHED_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mKavaBep2Grpc.size()) {
                    return SECTION_KAVA_BEP2_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mKavaBep2Grpc.size() + mEtcGrpc.size()) {
                    return SECTION_ETC_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mKavaBep2Grpc.size() + mEtcGrpc.size() + mIbcUnknownGrpc.size()) {
                    return SECTION_IBC_UNKNOWN_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mKavaBep2Grpc.size() + mEtcGrpc.size() + mIbcUnknownGrpc.size() + mUnknownGrpc.size()) {
                    return SECTION_UNKNOWN_GRPC;
                }

            } else if (getMainActivity().baseChain.equals(JUNO_MAIN)) {
                if (position < mNativeGrpc.size()) {
                    return SECTION_NATIVE_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size()) {
                    return SECTION_IBC_AUTHED_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mCW20Grpc.size()) {
                    return SECTION_CW20_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mCW20Grpc.size() + mIbcUnknownGrpc.size()) {
                    return SECTION_IBC_UNKNOWN_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mCW20Grpc.size() + mIbcUnknownGrpc.size() + mUnknownGrpc.size()) {
                    return SECTION_UNKNOWN_GRPC;
                }

            } else if (getMainActivity().baseChain.isGRPC()) {
                if (position < mNativeGrpc.size()) {
                    return SECTION_NATIVE_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size()) {
                    return SECTION_IBC_AUTHED_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mIbcUnknownGrpc.size()) {
                    return SECTION_IBC_UNKNOWN_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mIbcUnknownGrpc.size() + mUnknownGrpc.size()) {
                    return SECTION_UNKNOWN_GRPC;
                }
            } else if (getMainActivity().baseChain.equals(OKEX_MAIN)) {
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

            } else if (getMainActivity().baseChain.equals(BNB_MAIN)) {
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
            private final CardView itemRoot;
            private final ImageView itemImg;
            private final TextView itemSymbol;
            private final TextView itemInnerSymbol;
            private final TextView itemFullName;
            private final TextView itemBalance;
            private final TextView itemValue;

            public AssetHolder(View v) {
                super(v);
                itemRoot = itemView.findViewById(R.id.token_card);
                itemImg = itemView.findViewById(R.id.token_img);
                itemSymbol = itemView.findViewById(R.id.token_symbol);
                itemInnerSymbol = itemView.findViewById(R.id.token_inner_symbol);
                itemFullName = itemView.findViewById(R.id.token_fullname);
                itemBalance = itemView.findViewById(R.id.token_balance);
                itemValue = itemView.findViewById(R.id.token_value);
            }
        }
    }

    //with Native gRPC
    private void onNativeGrpcItem(TokensAdapter.AssetHolder holder, final int position) {
        final Coin coin = mNativeGrpc.get(position);
        final BaseChain chain = BaseChain.getChainByDenom(coin.denom);

        Picasso.get().cancelRequest(holder.itemImg);

        if (chain != null) {
            holder.itemSymbol.setText(getString(chain.getSymbolTitle()));
            holder.itemSymbol.setTextColor(ContextCompat.getColor(requireContext(), chain.getDenomColor()));
            holder.itemFullName.setText(chain.getFullNameCoin());
            if (coin.denom.equals(SECRET_MAIN.getMainDenom())) {
                holder.itemInnerSymbol.setText("(" + coin.denom + ")");
            } else {
                holder.itemInnerSymbol.setText("");
            }
            holder.itemImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), chain.getCoinIcon()));
        } else if (coin.denom.equals(TOKEN_ION)) {
            holder.itemSymbol.setText(getString(R.string.str_uion_c));
            holder.itemSymbol.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorIon));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Ion Coin");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_ion));
        } else if (coin.denom.equals(TOKEN_HARD)) {
            Picasso.get().load(KAVA_COIN_IMG_URL + coin.denom + ".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(holder.itemImg);
            holder.itemSymbol.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorHard));
            holder.itemSymbol.setText(coin.denom.toUpperCase());
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("HardPool Gov. Coin");
        } else if (coin.denom.equals(TOKEN_USDX)) {
            Picasso.get().load(KAVA_COIN_IMG_URL + coin.denom + ".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(holder.itemImg);
            holder.itemSymbol.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorUsdx));
            holder.itemSymbol.setText(coin.denom.toUpperCase());
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("USD Stable Asset");
        } else if (coin.denom.equals(TOKEN_SWP)) {
            Picasso.get().load(KAVA_COIN_IMG_URL + coin.denom + ".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(holder.itemImg);
            holder.itemSymbol.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorSwp));
            holder.itemSymbol.setText(coin.denom.toUpperCase());
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText("Kava Swap Coin");
        } else if (coin.denom.startsWith("e")) {
            holder.itemSymbol.setText(coin.denom.toUpperCase());
            holder.itemSymbol.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText(coin.denom.substring(1).toUpperCase() + " on E-Money Network");
            Picasso.get().load(EMONEY_COIN_IMG_URL + coin.denom + ".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(holder.itemImg);
        }

        BigDecimal amount = getBaseDao().getAllMainAsset(coin.denom);
        int divideDecimal = 6;
        int displayDecimal = 6;
        int divider = 6;

        if (coin.denom.equals(TOKEN_ION) || coin.denom.startsWith("e")) {
            amount = getBaseDao().getAvailable(coin.denom);
        } else if (coin.denom.equals(FETCHAI_MAIN.getMainDenom()) || coin.denom.equals(INJ_MAIN.getMainDenom()) || coin.denom.equals(SIF_MAIN.getMainDenom())) {
            divideDecimal = 18;
            divider = 18;
        } else if (coin.denom.equals(EVMOS_MAIN.getMainDenom()) || coin.denom.equals(CUDOS_MAIN.getMainDenom())) {
            divideDecimal = 18;
        } else if (coin.denom.equals(PROVENANCE_MAIN.getMainDenom())) {
            divideDecimal = 9;
        } else if (coin.denom.equals(CRYPTO_MAIN.getMainDenom())) {
            divideDecimal = 8;
            divider = 8;
        } else if (coin.denom.equals(TOKEN_HARD) || coin.denom.equals(TOKEN_USDX) || coin.denom.equals(TOKEN_SWP)) {
            amount = getBaseDao().getAvailable(coin.denom).add(getBaseDao().getVesting(coin.denom));
            divideDecimal = WUtil.getKavaCoinDecimal(getBaseDao(), coin.denom);
        }

        holder.itemBalance.setText(WDp.getDpAmount2(amount, divideDecimal, displayDecimal));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, amount, divider));

        holder.itemRoot.setOnClickListener(v -> {
            if (mNativeGrpc.get(position).denom.equalsIgnoreCase(getMainActivity().baseChain.getMainDenom())) {
                Intent intent = new Intent(getMainActivity(), StakingTokenGrpcActivity.class);
                intent.putExtra("denom", coin.denom);
                startActivity(intent);
            } else {
                Intent intent = new Intent(getMainActivity(), NativeTokenGrpcActivity.class);
                intent.putExtra("denom", coin.denom);
                startActivity(intent);
            }
        });
    }

    //with Authed IBC gRPC
    private void onBindIbcAuthToken(TokensAdapter.AssetHolder holder, int position) {
        final Coin coin = mIbcAuthedGrpc.get(position);
        final IbcToken ibcToken = getBaseDao().getIbcToken(coin.denom);
        holder.itemSymbol.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite));
        holder.itemFullName.setEllipsize(TextUtils.TruncateAt.MIDDLE);
        holder.itemInnerSymbol.setText("");
        if (ibcToken == null) {
            holder.itemSymbol.setText(R.string.str_unknown);
            holder.itemFullName.setText("");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(new BigDecimal(coin.amount), 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, BigDecimal.ZERO, 6));
        } else {
            holder.itemSymbol.setText(ibcToken.display_denom.toUpperCase());
            holder.itemFullName.setText(ibcToken.channel_id);
            holder.itemBalance.setText(WDp.getDpAmount2(new BigDecimal(coin.amount), ibcToken.decimal, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), getBaseDao().getBaseDenom(coin.denom), new BigDecimal(coin.amount), ibcToken.decimal));
            try {
                Picasso.get().load(ibcToken.moniker).fit().placeholder(R.drawable.token_default_ibc).error(R.drawable.token_default_ibc).into(holder.itemImg);
            } catch (Exception e) {
            }
        }

        holder.itemRoot.setOnClickListener(v -> {
            Intent intent = new Intent(getMainActivity(), IBCTokenDetailActivity.class);
            intent.putExtra("denom", coin.denom);
            startActivity(intent);
        });
    }

    //with Unknown IBC gRPC
    private void onBindIbcUnknownToken(TokensAdapter.AssetHolder holder, int position) {
        final Coin coin = mIbcUnknownGrpc.get(position);
        final IbcToken ibcToken = getBaseDao().getIbcToken(coin.denom);
        holder.itemInnerSymbol.setText("");
        holder.itemSymbol.setText(R.string.str_unknown);
        if (ibcToken == null) {
            holder.itemFullName.setText("");
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(new BigDecimal(coin.amount), 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, BigDecimal.ZERO, 6));
        } else {
            holder.itemFullName.setText(ibcToken.channel_id);
            holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_default_ibc));
            holder.itemBalance.setText(WDp.getDpAmount2(new BigDecimal(coin.amount), 6, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, new BigDecimal(coin.amount), 6));
        }

        holder.itemRoot.setOnClickListener(v -> {
            Intent intent = new Intent(getMainActivity(), IBCTokenDetailActivity.class);
            intent.putExtra("denom", coin.denom);
            startActivity(intent);
        });
    }

    //with Osmosis Pool gRPC
    private void onBindOsmoPoolToken(TokensAdapter.AssetHolder holder, int position) {
        final Coin coin = mOsmosisPoolGrpc.get(position);
        holder.itemSymbol.setText(coin.osmosisAmmDpDenom());
        holder.itemSymbol.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite));
        holder.itemInnerSymbol.setText("");
        holder.itemFullName.setText(coin.denom);
        holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_pool));
        holder.itemBalance.setText(WDp.getDpAmount2(new BigDecimal(coin.amount), 18, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, new BigDecimal(coin.amount), 18));

        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getMainActivity(), POOLTokenDetailActivity.class);
                intent.putExtra("denom", coin.denom);
                startActivity(intent);
            }
        });
    }

    //with Cosmos Gravity Dex gRPC
    private void onBindGravityDexToken(TokensAdapter.AssetHolder holder, int position) {
        final Coin coin = mGravityDexGrpc.get(position);
        Picasso.get().load(COSMOS_COIN_IMG_URL + "gravitydex.png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(holder.itemImg);
        holder.itemBalance.setText(WDp.getDpAmount2(new BigDecimal(coin.amount), 6, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, new BigDecimal(coin.amount), 6));
        holder.itemInnerSymbol.setText("");
        Liquidity.Pool poolInfo = getBaseDao().getGravityPoolByDenom(coin.denom);
        if (poolInfo != null) {
            holder.itemSymbol.setText("GDEX-" + poolInfo.getId());
            holder.itemSymbol.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite));
            holder.itemFullName.setText("pool/" + poolInfo.getId());
        }

        holder.itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getMainActivity(), POOLTokenDetailActivity.class);
                intent.putExtra("denom", coin.denom);
                startActivity(intent);
            }
        });
    }

    //with Injective Pool gRPC
    private void onBindInjectivePoolToken(TokensAdapter.AssetHolder holder, int position) {
        final Coin coin = mInjectivePoolGrpc.get(position);
        holder.itemSymbol.setText("SHARE" + coin.denom.substring(5));
        holder.itemSymbol.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite));
        holder.itemInnerSymbol.setText("");
        holder.itemFullName.setText("Pool Asset");
        holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_ic));
        holder.itemBalance.setText(WDp.getDpAmount2(new BigDecimal(coin.amount), 18, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, BigDecimal.ZERO, 6));

        holder.itemRoot.setOnClickListener(v -> {
            Intent intent = new Intent(getMainActivity(), POOLTokenDetailActivity.class);
            intent.putExtra("denom", coin.denom);
            startActivity(intent);
        });
    }

    //with Erc gRPC
    private void onBindErcToken(TokensAdapter.AssetHolder holder, int position) {
        final Coin coin = mEtherGrpc.get(position);
        final Assets assets = getBaseDao().getAsset(coin.denom);
        if (assets != null) {
            holder.itemSymbol.setText(assets.origin_symbol);
            holder.itemSymbol.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite));
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText(assets.display_symbol);
            Picasso.get().load(ASSET_IMG_URL + assets.logo).fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(holder.itemImg);

            BigDecimal totalAmount = getBaseDao().getAvailable(assets.denom);
            holder.itemBalance.setText(WDp.getDpAmount2(totalAmount, assets.decimal, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), assets.origin_symbol, totalAmount, assets.decimal));

            holder.itemRoot.setOnClickListener(v -> {
                Intent intent = new Intent(getMainActivity(), BridgeTokenGrpcActivity.class);
                intent.putExtra("denom", assets.denom);
                startActivity(intent);
            });
        }
    }

    //bind kava bep2 tokens with gRPC
    private void onBindKavaBep2Token(TokensAdapter.AssetHolder holder, int position) {
        final Coin coin = mKavaBep2Grpc.get(position);
        Picasso.get().load(KAVA_COIN_IMG_URL + coin.denom + ".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(holder.itemImg);
        holder.itemSymbol.setText(coin.denom.toUpperCase());
        holder.itemSymbol.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite));
        holder.itemInnerSymbol.setText("");
        holder.itemFullName.setText(coin.denom.toUpperCase() + " on Kava Chain");

        BigDecimal totalAmount = getBaseDao().getAvailable(coin.denom);
        String baseDenom = WDp.getKavaBaseDenom(coin.denom);
        int bep2decimal = WUtil.getKavaCoinDecimal(getBaseDao(), coin.denom);
        holder.itemBalance.setText(WDp.getDpAmount2(totalAmount, bep2decimal, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), baseDenom, totalAmount, bep2decimal));

        holder.itemRoot.setOnClickListener(v -> {
            Intent intent = new Intent(getMainActivity(), NativeTokenGrpcActivity.class);
            intent.putExtra("denom", coin.denom);
            startActivity(intent);
        });
    }

    //bind kava etc tokens with gRPC
    private void onBindEtcGrpcToken(TokensAdapter.AssetHolder holder, int position) {
        final Coin coin = mEtcGrpc.get(position);
        Picasso.get().load(KAVA_COIN_IMG_URL + "hbtc.png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(holder.itemImg);
        holder.itemSymbol.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite));
        holder.itemSymbol.setText(coin.denom.toUpperCase());
        holder.itemInnerSymbol.setText("(" + coin.denom + ")");
        holder.itemFullName.setText(coin.denom.toUpperCase() + " on Kava Chain");

        BigDecimal tokenTotalAmount = getBaseDao().getAvailable(coin.denom).add(getBaseDao().getVesting(coin.denom));
        BigDecimal convertedKavaAmount = WDp.convertTokenToKava(getBaseDao(), coin.denom);
        holder.itemBalance.setText(WDp.getDpAmount2(tokenTotalAmount, WUtil.getKavaCoinDecimal(getBaseDao(), coin.denom), 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), KAVA_MAIN.getMainDenom(), convertedKavaAmount, 6));
    }

    //bind cw20 tokens with gRPC
    private void onBindCw20GrpcToken(TokensAdapter.AssetHolder holder, int position) {
        final Cw20Assets cw20Asset = mCW20Grpc.get(position);
        Picasso.get().load(cw20Asset.logo).fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(holder.itemImg);
        holder.itemSymbol.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite));
        holder.itemSymbol.setText(cw20Asset.denom.toUpperCase());
        holder.itemInnerSymbol.setText("");
        holder.itemFullName.setText(cw20Asset.contract_address);

        int decimal = cw20Asset.decimal;
        holder.itemBalance.setText(WDp.getDpAmount2(cw20Asset.getAmount(), decimal, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), cw20Asset.denom, cw20Asset.getAmount(), decimal));

        holder.itemRoot.setOnClickListener(v -> {
            Intent intent = new Intent(getMainActivity(), ContractTokenGrpcActivity.class);
            intent.putExtra("cw20Asset", cw20Asset);
            startActivity(intent);
        });
    }

    //with Unknown Token gRPC
    private void onBindUnKnownToken(TokensAdapter.AssetHolder holder, int position) {
        final Coin coin = mUnknownGrpc.get(position);
        holder.itemSymbol.setText(R.string.str_unknown);
        holder.itemSymbol.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite));
        holder.itemInnerSymbol.setText("");
        holder.itemFullName.setText("");
        holder.itemImg.setImageDrawable(getResources().getDrawable(R.drawable.token_ic));
        holder.itemBalance.setText(WDp.getDpAmount2(new BigDecimal(coin.amount), 6, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, BigDecimal.ZERO, 6));
    }


    //with native tokens
    private void onBindNativeItem(TokensAdapter.AssetHolder holder, int position) {
        final Balance balance = mNative.get(position);
        if (getMainActivity().baseChain.equals(BNB_MAIN)) {
            final String denom = mNative.get(position).symbol;
            final BigDecimal amount = getBaseDao().getAllBnbTokenAmount(denom);
            final BnbToken bnbToken = getBaseDao().getBnbToken(denom);
            if (bnbToken != null) {
                holder.itemSymbol.setText(bnbToken.original_symbol.toUpperCase());
                holder.itemInnerSymbol.setText("(" + bnbToken.symbol + ")");
                holder.itemFullName.setText(BNB_MAIN.getFullNameCoin());
                holder.itemImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), BNB_MAIN.getCoinIcon()));
                holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), BNB_MAIN));
                holder.itemBalance.setText(WDp.getDpAmount2(amount, 0, 6));
                holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), BNB_MAIN.getMainDenom(), amount, 0));
            }
            holder.itemRoot.setOnClickListener(v -> {
                Intent intent = new Intent(getMainActivity(), StakingTokenDetailActivity.class);
                startActivity(intent);
            });

        } else if (getMainActivity().baseChain.equals(OKEX_MAIN)) {
            final OkToken okToken = getBaseDao().okToken(balance.symbol);
            holder.itemSymbol.setText(okToken.original_symbol.toUpperCase());
            holder.itemInnerSymbol.setText("(" + okToken.symbol + ")");
            holder.itemFullName.setText(OKEX_MAIN.getFullNameCoin());
            if (balance.symbol.equals(OKEX_MAIN.getMainDenom())) {
                holder.itemSymbol.setTextColor(WDp.getChainColor(getContext(), getMainActivity().baseChain));
                holder.itemImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), OKEX_MAIN.getCoinIcon()));

                BigDecimal totalAmount = getBaseDao().getAllExToken(balance.symbol);
                holder.itemBalance.setText(WDp.getDpAmount2(totalAmount, 0, 6));
                holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, totalAmount, 0));
            }
            holder.itemRoot.setOnClickListener(v -> {
                Intent intent = new Intent(getMainActivity(), StakingTokenDetailActivity.class);
                startActivity(intent);
            });
        }
    }

    //with Etc tokens (binance, okex)
    private void onBindEtcToken(TokensAdapter.AssetHolder holder, int position) {
        final Balance balance = mEtc.get(position);
        final String denom = balance.symbol;
        switch (getMainActivity().baseChain) {
            case OKEX_MAIN: {
                final OkToken okToken = getBaseDao().okToken(denom);
                if (okToken != null) {
                    holder.itemSymbol.setText(okToken.original_symbol.toUpperCase());
                    holder.itemInnerSymbol.setText("(" + okToken.symbol + ")");
                    holder.itemFullName.setText(okToken.description);
                    holder.itemSymbol.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite));
                    Picasso.get().load(OKEX_COIN_IMG_URL + okToken.original_symbol + ".png").placeholder(R.drawable.token_ic).error(R.drawable.token_ic).fit().into(holder.itemImg);
                }

                BigDecimal totalAmount = getBaseDao().getAllExToken(denom);
                BigDecimal convertAmount = WDp.convertTokenToOkt(getBaseDao(), denom);
                holder.itemBalance.setText(WDp.getDpAmount2(totalAmount, 0, 6));
                holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), OKEX_MAIN.getMainDenom(), convertAmount, 0));
                holder.itemRoot.setOnClickListener(v -> {
                    Intent intent = new Intent(getMainActivity(), NativeTokenDetailActivity.class);
                    intent.putExtra("denom", denom);
                    startActivity(intent);
                });

                break;
            }
            case BNB_MAIN: {
                final BigDecimal amount = getBaseDao().getAllBnbTokenAmount(denom);
                final BnbToken bnbToken = getBaseDao().getBnbToken(denom);

                holder.itemSymbol.setText(bnbToken.original_symbol.toUpperCase());
                holder.itemInnerSymbol.setText("(" + bnbToken.symbol + ")");
                holder.itemFullName.setText(bnbToken.name);
                Picasso.get().load(BINANCE_TOKEN_IMG_URL + bnbToken.original_symbol + ".png").fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic).into(holder.itemImg);
                holder.itemSymbol.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite));
                holder.itemBalance.setText(WDp.getDpAmount2(amount, 0, 6));

                final BigDecimal convertAmount = WUtil.getBnbConvertAmount(getBaseDao(), denom, amount);
                holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), BNB_MAIN.getMainDenom(), convertAmount, 0));
                holder.itemRoot.setOnClickListener(v -> {
                    Intent intent = new Intent(getMainActivity(), NativeTokenDetailActivity.class);
                    intent.putExtra("denom", denom);
                    startActivity(intent);
                });
                break;
            }
        }
    }

    //with Unknown coin oec, bnb
    private void onBindUnKnownCoin(TokensAdapter.AssetHolder holder, int position) {
        final Balance balance = mUnKnown.get(position);
        holder.itemSymbol.setText(R.string.str_unknown);
        holder.itemSymbol.setTextColor(ContextCompat.getColor(requireContext(), R.color.colorWhite));
        holder.itemInnerSymbol.setText("");
        holder.itemFullName.setText("");
        holder.itemImg.setImageDrawable(ContextCompat.getDrawable(requireContext(), R.drawable.token_ic));
        holder.itemBalance.setText(WDp.getDpAmount2(balance.balance, 6, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, BigDecimal.ZERO, 6));
    }

    private void showAddressDialog() {
        AccountShowDialogFragment show = AccountShowDialogFragment.Companion.newInstance(
                mAccount.getAccountTitle(requireContext()),
                mAccount.address
        );
        showDialog(show);
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

            topPadding = dpToPx(context, 32);
        }

        // dp -> pixel 단위로 변경
        private int dpToPx(Context context, int dp) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        }

        @Override
        public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDrawOver(c, parent, state);

            if (headerView == null) {
                headerView = inflateHeaderView(parent);
                mHeaderTitle = headerView.findViewById(R.id.header_title);
                mItemCnt = headerView.findViewById(R.id.recycler_cnt);
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
                if (getMainActivity().baseChain.isGRPC()) {
                    if (mSection == SECTION_NATIVE_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(getMainActivity().baseChain, mNativeGrpc, mSection);
                        mItemCnt.setText("" + mNativeGrpc.size());
                    } else if (mSection == SECTION_IBC_AUTHED_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(getMainActivity().baseChain, mIbcAuthedGrpc, mSection);
                        mItemCnt.setText("" + mIbcAuthedGrpc.size());
                    } else if (mSection == SECTION_IBC_UNKNOWN_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(getMainActivity().baseChain, mIbcUnknownGrpc, mSection);
                        mItemCnt.setText("" + mIbcUnknownGrpc.size());
                    } else if (mSection == SECTION_UNKNOWN_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(getMainActivity().baseChain, mUnknownGrpc, mSection);
                        mItemCnt.setText("" + mUnknownGrpc.size());
                    }

                    // osmosis pool token
                    else if (mSection == SECTION_OSMOSIS_POOL_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(getMainActivity().baseChain, mOsmosisPoolGrpc, mSection);
                        mItemCnt.setText("" + mOsmosisPoolGrpc.size());
                    }

                    // injective pool token
                    else if (mSection == SECTION_INJECTIVE_POOL_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(getMainActivity().baseChain, mInjectivePoolGrpc, mSection);
                        mItemCnt.setText("" + mInjectivePoolGrpc.size());
                    }

                    // ether bridge token
                    else if (mSection == SECTION_ETHER_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(getMainActivity().baseChain, mEtherGrpc, mSection);
                        mItemCnt.setText("" + mEtherGrpc.size());
                    }

                    // cosmos gravity dex token
                    else if (mSection == SECTION_GRAVICTY_DEX_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(getMainActivity().baseChain, mGravityDexGrpc, mSection);
                        mItemCnt.setText("" + mGravityDexGrpc.size());
                    }

                    // kava token
                    else if (mSection == SECTION_KAVA_BEP2_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(getMainActivity().baseChain, mKavaBep2Grpc, mSection);
                        mItemCnt.setText("" + mKavaBep2Grpc.size());
                    } else if (mSection == SECTION_ETC_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(getMainActivity().baseChain, mEtcGrpc, mSection);
                        mItemCnt.setText("" + mEtcGrpc.size());
                    }

                    // cw20 token
                    else if (mSection == SECTION_CW20_GRPC) {
                        title = sectionCallback.getSectionCw20Header(getMainActivity().baseChain, mCW20Grpc, mSection);
                        mItemCnt.setText("" + mCW20Grpc.size());
                    }

                } else {
                    if (mSection == SECTION_NATIVE) {
                        title = sectionCallback.getSecitonHeader(getMainActivity().baseChain, mNative, mSection);
                        mItemCnt.setText("" + mNative.size());
                    } else if (mSection == SECTION_ETC) {
                        title = sectionCallback.getSecitonHeader(getMainActivity().baseChain, mEtc, mSection);
                        mItemCnt.setText("" + mEtc.size());
                    } else if (mSection == SECTION_UNKNOWN) {
                        title = sectionCallback.getSecitonHeader(getMainActivity().baseChain, mUnKnown, mSection);
                        mItemCnt.setText("" + mUnKnown.size());
                    }
                }
                mHeaderTitle.setText(title);
                if (!previousHeader.equals(title) || sectionCallback.isSection(getMainActivity().baseChain, position)) {
                    drawHeader(c, child, headerView);
                    previousHeader = title;
                }
            }
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            int position = parent.getChildAdapterPosition(view);
            if (sectionCallback.isSection(getMainActivity().baseChain, position)) {
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

        String getSectionCw20Header(BaseChain baseChain, ArrayList<Cw20Assets> cw20Assets, int section);

        String getSecitonHeader(BaseChain baseChain, ArrayList<Balance> balances, int section);
    }
}