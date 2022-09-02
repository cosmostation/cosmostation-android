package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseChain.*;
import static wannabit.io.cosmostaion.base.BaseConstant.*;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
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

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;

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
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.Crescent;
import wannabit.io.cosmostaion.base.chains.Kava;
import wannabit.io.cosmostaion.base.chains.Nyx;
import wannabit.io.cosmostaion.base.chains.Osmosis;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Assets;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.Cw20Assets;
import wannabit.io.cosmostaion.dao.IbcToken;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class MainTokensFragment extends BaseFragment {
    public final static int SECTION_NATIVE_GRPC = 0;
    public final static int SECTION_IBC_AUTHED_GRPC = 1;
    public final static int SECTION_POOL_GRPC = 2;
    public final static int SECTION_ETHER_GRPC = 3;
    public final static int SECTION_IBC_UNKNOWN_GRPC = 5;
    public final static int SECTION_KAVA_BEP2_GRPC = 6;
    public final static int SECTION_ETC_GRPC = 7;
    public final static int SECTION_CW20_GRPC = 8;
    public final static int SECTION_UNKNOWN_GRPC = 9;

    public final static int SECTION_NATIVE = 20;
    public final static int SECTION_ETC = 21;
    public final static int SECTION_UNKNOWN = 22;

    private int mSection;

    private CardView mCardView;
    private ImageView itemKeyStatus;
    private TextView mWalletAddress, mEthAddress;
    private TextView mTotalValue;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayout mEmptyToken;

    private TokensAdapter mTokensAdapter;

    private RecyclerViewHeader mRecyclerViewHeader;

    private ArrayList<Coin> mNativeGrpc = new ArrayList<>();
    private ArrayList<Coin> mIbcAuthedGrpc = new ArrayList<>();
    private ArrayList<Coin> mPoolGrpc = new ArrayList<>();
    private ArrayList<Coin> mEtherGrpc = new ArrayList<>();
    private ArrayList<Coin> mIbcUnknownGrpc = new ArrayList<>();
    private ArrayList<Coin> mKavaBep2Grpc = new ArrayList<>();
    private ArrayList<Coin> mEtcGrpc = new ArrayList<>();
    private ArrayList<Cw20Assets> mCW20Grpc = new ArrayList<>();
    private ArrayList<Coin> mUnknownGrpc = new ArrayList<>();

    private ArrayList<Balance> mNative = new ArrayList<>();
    private ArrayList<Balance> mEtc = new ArrayList<>();
    private ArrayList<Balance> mUnKnown = new ArrayList<>();

    private Account mAccount;
    private BaseChain mBaseChain;
    private ChainConfig mChainConfig;

    public static MainTokensFragment newInstance() {
        return new MainTokensFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_tokens, container, false);
        mCardView = rootView.findViewById(R.id.card_root);
        itemKeyStatus = rootView.findViewById(R.id.img_account);
        mWalletAddress = rootView.findViewById(R.id.wallet_address);
        mEthAddress = rootView.findViewById(R.id.eth_address);
        mTotalValue = rootView.findViewById(R.id.total_value);
        mSwipeRefreshLayout = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView = rootView.findViewById(R.id.recycler);
        mEmptyToken = rootView.findViewById(R.id.empty_token);

        mCardView.setOnClickListener(v -> getMainActivity().onClickQrCopy(mChainConfig, mAccount));

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getMainActivity(), R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            onUpdateInfo();
            getMainActivity().onFetchAllData();
        });

        mRecyclerView.setOnTouchListener((view, motionEvent) -> {
            if (mSwipeRefreshLayout.isRefreshing()) {
                return true;
            } else {
                return false;
            }
        });

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
        if (getMainActivity() == null || getMainActivity().mAccount == null) return;
        mAccount = getMainActivity().mAccount;
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = getMainActivity().mChainConfig;

        mCardView.setCardBackgroundColor(ContextCompat.getColor(getMainActivity(), mChainConfig.chainBgColor()));
        getMainActivity().setAccountKeyStatus(getActivity(), mAccount, mChainConfig, itemKeyStatus);
        mWalletAddress.setText(mAccount.address);
        getMainActivity().setEthAddress(mChainConfig, mEthAddress);
        mTotalValue.setText(WDp.dpAllAssetValueUserCurrency(mBaseChain, getBaseDao(), mChainConfig));
    }

    private SectionCallback getSectionGrpcCall() {
        return new SectionCallback() {
            // 헤더 구분 true / false
            @Override
            public boolean isSection(BaseChain baseChain, int position) {
                if (baseChain.equals(OSMOSIS_MAIN) || baseChain.equals(CRESCENT_MAIN)) {
                    return position == 0 || position == mNativeGrpc.size() || position == mNativeGrpc.size() + mIbcAuthedGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mPoolGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mPoolGrpc.size() + mIbcUnknownGrpc.size();

                } else if (baseChain.equals(SIF_MAIN) || baseChain.equals(GRABRIDGE_MAIN)) {
                    return position == 0 || position == mNativeGrpc.size() || position == mNativeGrpc.size() + mIbcAuthedGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size() + mIbcUnknownGrpc.size();

                } else if (baseChain.equals(INJ_MAIN)) {
                    return position == 0 || position == mNativeGrpc.size() || position == mNativeGrpc.size() + mIbcAuthedGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size() + mPoolGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size() + mPoolGrpc.size() + mIbcUnknownGrpc.size();

                } else if (baseChain.equals(KAVA_MAIN)) {
                    return position == 0 || position == mNativeGrpc.size() || position == mNativeGrpc.size() + mIbcAuthedGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mKavaBep2Grpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mKavaBep2Grpc.size() + mEtcGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mKavaBep2Grpc.size() + mEtcGrpc.size() + mIbcUnknownGrpc.size();

                } else if (baseChain.equals(JUNO_MAIN)) {
                    return position == 0 || position == mNativeGrpc.size() || position == mNativeGrpc.size() + mIbcAuthedGrpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mCW20Grpc.size()
                            || position == mNativeGrpc.size() + mIbcAuthedGrpc.size() + mCW20Grpc.size() + mIbcUnknownGrpc.size();

                } else if (isGRPC(baseChain)) {
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
                    return getMainActivity().getString(R.string.str_native_token_title);
                } else if (section == SECTION_IBC_AUTHED_GRPC) {
                    return getMainActivity().getString(R.string.str_ibc_token_title);
                } else if (section == SECTION_IBC_UNKNOWN_GRPC) {
                    return getMainActivity().getString(R.string.str_unknown_ibc_token_title);
                } else if (section == SECTION_UNKNOWN_GRPC) {
                    return getMainActivity().getString(R.string.str_unknown_token_title);
                } else if (section == SECTION_POOL_GRPC) {
                    return getMainActivity().getString(R.string.str_pool_coin_title);
                } else if (section == SECTION_ETHER_GRPC) {
                    return getMainActivity().getString(R.string.str_sif_ether_token_title);
                } else if (section == SECTION_KAVA_BEP2_GRPC) {
                    return getMainActivity().getString(R.string.str_kava_bep2_token_title);
                } else if (section == SECTION_ETC_GRPC) {
                    return getMainActivity().getString(R.string.str_etc_token_title);
                }

                return getMainActivity().getString(R.string.str_unknown_token_title);
            }

            @Override
            public String getSectionCw20Header(BaseChain baseChain, ArrayList<Cw20Assets> cw20Assets, int section) {
                if (section == SECTION_CW20_GRPC) {
                    return getMainActivity().getString(R.string.str_cw20_token_title);
                }
                return getMainActivity().getString(R.string.str_unknown_token_title);
            }

            @Override
            public String getSecitonHeader(BaseChain baseChain, ArrayList<Balance> balances, int section) {
                if (section == SECTION_NATIVE) {
                    return getMainActivity().getString(R.string.str_native_token_title);
                } else if (section == SECTION_ETC) {
                    if (baseChain.equals(OKEX_MAIN)) {
                        return getMainActivity().getString(R.string.str_oec_kip10_title);
                    }
                    return getMainActivity().getString(R.string.str_etc_token_title);
                } else if (section == SECTION_UNKNOWN) {
                    return getMainActivity().getString(R.string.str_unknown_token_title);
                }
                return getMainActivity().getString(R.string.str_unknown_token_title);
            }
        };
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
        final String mainDenom = mChainConfig.mainDenom();
        mCW20Grpc = getBaseDao().getCw20sGrpc(mBaseChain);
        mNativeGrpc.clear();
        mIbcAuthedGrpc.clear();
        mPoolGrpc.clear();
        mEtherGrpc.clear();
        mIbcUnknownGrpc.clear();
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
            } else if (mBaseChain.equals(OSMOSIS_MAIN) && coin.osmosisAmm() || mBaseChain.equals(CRESCENT_MAIN) && coin.isCrescnetPool() ||
                        mBaseChain.equals(INJ_MAIN) && coin.isInjectivePool()) {
                mPoolGrpc.add(coin);
            } else if (mBaseChain.equals(OSMOSIS_MAIN) && coin.denom.equalsIgnoreCase(Osmosis.OSMOSIS_ION_DENOM) ||
                    mBaseChain.equals(EMONEY_MAIN) && coin.denom.startsWith("e") ||
                    mBaseChain.equals(CRESCENT_MAIN) && coin.denom.equalsIgnoreCase(Crescent.CRESCENT_BCRE_DENOM) ||
                    mBaseChain.equals(NYX_MAIN) && coin.denom.equalsIgnoreCase(Nyx.NYX_NYM_DENOM)) {
                mNativeGrpc.add(coin);
            } else if (mBaseChain.equals(SIF_MAIN) && coin.denom.startsWith("c") ||
                    mBaseChain.equals(GRABRIDGE_MAIN) && coin.denom.startsWith("gravity") ||
                    mBaseChain.equals(INJ_MAIN) && coin.denom.startsWith("peggy")) {
                mEtherGrpc.add(coin);
            } else if (mBaseChain.equals(KAVA_MAIN)) {
                if (coin.denom.equals(Kava.KAVA_HARD_DENOM) || coin.denom.equalsIgnoreCase(Kava.KAVA_USDX_DENOM) || coin.denom.equalsIgnoreCase(Kava.KAVA_SWP_DENOM)) {
                    mNativeGrpc.add(coin);
                } else if (coin.denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BNB) || coin.denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BTCB) ||
                        coin.denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_XRPB) || coin.denom.equalsIgnoreCase(TOKEN_HTLC_KAVA_BUSD)) {
                    mKavaBep2Grpc.add(coin);
                } else if ("btch".equalsIgnoreCase(coin.denom) || "hbtc".equalsIgnoreCase(coin.denom)) {
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
            } else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(OKEX_MAIN)) {
                mEtc.add(balance);
            } else {
                mUnKnown.add(balance);
            }
        }

        if (isGRPC(mBaseChain)) {
            WUtil.onSortingCoins(mNativeGrpc, mBaseChain);
            WUtil.onSortingPool(mBaseChain, mPoolGrpc);

        } else if (mBaseChain.equals(BNB_MAIN) || mBaseChain.equals(OKEX_MAIN)) {
            WUtil.onSortingNativeCoins(mEtc, mBaseChain);
        } else {
            WUtil.onSortingNativeCoins(mNative, mBaseChain);
        }

        if (isGRPC(mBaseChain)) {
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
            if (mBaseChain.equals(OSMOSIS_MAIN) || mBaseChain.equals(CRESCENT_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, mChainConfig, position);
                } else if (getItemViewType(position) == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, mChainConfig, position - mNativeGrpc.size());
                } else if (getItemViewType(position) == SECTION_POOL_GRPC) {
                    onBindPoolToken(viewHolder, mChainConfig, position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                } else if (getItemViewType(position) == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, mChainConfig, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mPoolGrpc.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN_GRPC) {
                    onBindUnKnownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mPoolGrpc.size() - mIbcUnknownGrpc.size());
                }

            } else if (mBaseChain.equals(SIF_MAIN) || mBaseChain.equals(GRABRIDGE_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, mChainConfig, position);
                } else if (getItemViewType(position) == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, mChainConfig, position - mNativeGrpc.size());
                } else if (getItemViewType(position) == SECTION_ETHER_GRPC) {
                    onBindErcToken(viewHolder, mChainConfig, position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                } else if (getItemViewType(position) == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, mChainConfig,position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mEtherGrpc.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN_GRPC) {
                    onBindUnKnownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mEtherGrpc.size() - mIbcUnknownGrpc.size());
                }

            } else if (mBaseChain.equals(INJ_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, mChainConfig, position);
                } else if (getItemViewType(position) == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, mChainConfig, position - mNativeGrpc.size());
                } else if (getItemViewType(position) == SECTION_ETHER_GRPC) {
                    onBindErcToken(viewHolder, mChainConfig, position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                } else if (getItemViewType(position) == SECTION_POOL_GRPC) {
                    onBindPoolToken(viewHolder, mChainConfig, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mEtherGrpc.size());
                } else if (getItemViewType(position) == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, mChainConfig,position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mEtherGrpc.size() - mPoolGrpc.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN_GRPC) {
                    onBindUnKnownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mEtherGrpc.size() - mPoolGrpc.size() - mIbcUnknownGrpc.size());
                }

            } else if (mBaseChain.equals(KAVA_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, mChainConfig, position);
                } else if (getItemViewType(position) == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, mChainConfig, position - mNativeGrpc.size());
                } else if (getItemViewType(position) == SECTION_KAVA_BEP2_GRPC) {
                    onBindKavaBep2Token(viewHolder, mChainConfig, position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                } else if (getItemViewType(position) == SECTION_ETC_GRPC) {
                    onBindEtcGrpcToken(viewHolder, mChainConfig, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mKavaBep2Grpc.size());
                } else if (getItemViewType(position) == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, mChainConfig,position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mKavaBep2Grpc.size() - mEtcGrpc.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN_GRPC) {
                    onBindUnKnownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mKavaBep2Grpc.size() - mEtcGrpc.size() - mIbcUnknownGrpc.size());
                }

            } else if (mBaseChain.equals(JUNO_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, mChainConfig, position);
                } else if (getItemViewType(position) == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, mChainConfig,position - mNativeGrpc.size());
                } else if (getItemViewType(position) == SECTION_CW20_GRPC) {
                    onBindCw20GrpcToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                } else if (getItemViewType(position) == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, mChainConfig,position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mCW20Grpc.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN_GRPC) {
                    onBindUnKnownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mCW20Grpc.size() - mIbcUnknownGrpc.size());
                }

            } else if (isGRPC(mBaseChain)) {
                if (getItemViewType(position) == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, mChainConfig, position);
                } else if (getItemViewType(position) == SECTION_IBC_AUTHED_GRPC) {
                    onBindIbcAuthToken(viewHolder, mChainConfig, position - mNativeGrpc.size());
                } else if (getItemViewType(position) == SECTION_IBC_UNKNOWN_GRPC) {
                    onBindIbcUnknownToken(viewHolder, mChainConfig,position - mNativeGrpc.size() - mIbcAuthedGrpc.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN_GRPC) {
                    onBindUnKnownToken(viewHolder, position - mNativeGrpc.size() - mIbcAuthedGrpc.size() - mIbcUnknownGrpc.size());
                }

            } else if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(BNB_MAIN)) {
                if (getItemViewType(position) == SECTION_NATIVE) {
                    onBindNativeItem(viewHolder, mChainConfig, position);
                } else if (getItemViewType(position) == SECTION_ETC) {
                    onBindEtcToken(viewHolder, mChainConfig, position - mNative.size());
                } else if (getItemViewType(position) == SECTION_UNKNOWN) {
                    onBindUnKnownCoin(viewHolder, position - mNative.size() - mEtc.size());
                }
            }
        }

        @Override
        public int getItemCount() {
            if (mBaseChain.equals(JUNO_MAIN)) {
                return getBaseDao().mGrpcBalance.size() + mCW20Grpc.size();
            } else if (isGRPC(mBaseChain)) {
                return getBaseDao().mGrpcBalance.size();
            } else {
                return getBaseDao().mBalances.size();
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (mBaseChain.equals(OSMOSIS_MAIN) || mBaseChain.equals(CRESCENT_MAIN)) {
                if (position < mNativeGrpc.size()) {
                    return SECTION_NATIVE_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size()) {
                    return SECTION_IBC_AUTHED_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mPoolGrpc.size()) {
                    return SECTION_POOL_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mPoolGrpc.size() + mIbcUnknownGrpc.size()) {
                    return SECTION_IBC_UNKNOWN_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mPoolGrpc.size() + mIbcUnknownGrpc.size() + mUnknownGrpc.size()) {
                    return SECTION_UNKNOWN_GRPC;
                }

            } else if (mBaseChain.equals(SIF_MAIN) || mBaseChain.equals(GRABRIDGE_MAIN)) {
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

            } else if (mBaseChain.equals(INJ_MAIN)) {
                if (position < mNativeGrpc.size()) {
                    return SECTION_NATIVE_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size()) {
                    return SECTION_IBC_AUTHED_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size()) {
                    return SECTION_ETHER_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size() + mPoolGrpc.size()) {
                    return SECTION_POOL_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size() + mPoolGrpc.size() + mIbcUnknownGrpc.size()) {
                    return SECTION_IBC_UNKNOWN_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mEtherGrpc.size() + mPoolGrpc.size() + mIbcUnknownGrpc.size() + mUnknownGrpc.size()) {
                    return SECTION_UNKNOWN_GRPC;
                }

            } else if (mBaseChain.equals(KAVA_MAIN)) {
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

            } else if (mBaseChain.equals(JUNO_MAIN)) {
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

            } else if (isGRPC(mBaseChain)) {
                if (position < mNativeGrpc.size()) {
                    return SECTION_NATIVE_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size()) {
                    return SECTION_IBC_AUTHED_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mIbcUnknownGrpc.size()) {
                    return SECTION_IBC_UNKNOWN_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcAuthedGrpc.size() + mIbcUnknownGrpc.size() + mUnknownGrpc.size()) {
                    return SECTION_UNKNOWN_GRPC;
                }

            } else if (mBaseChain.equals(OKEX_MAIN) || mBaseChain.equals(BNB_MAIN)) {
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

    private void onNativeGrpcItem(TokensAdapter.AssetHolder holder, ChainConfig chainConfig, final int position) {
        final Coin coin = mNativeGrpc.get(position);
        final int denomDecimal = WDp.getDenomDecimal(getBaseDao(), chainConfig, coin.denom);

        if (coin.denom.equalsIgnoreCase(chainConfig.mainDenom())) {
            BigDecimal totalAmount = getBaseDao().getAllMainAsset(chainConfig.mainDenom());
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, denomDecimal, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, denomDecimal));

        } else {
            BigDecimal totalAmount = BigDecimal.ZERO;
            if (coin.denom.equalsIgnoreCase(Kava.KAVA_HARD_DENOM) || coin.denom.equalsIgnoreCase(Kava.KAVA_USDX_DENOM) || coin.denom.equalsIgnoreCase(Kava.KAVA_SWP_DENOM)) {
                totalAmount = getBaseDao().getAvailable(coin.denom).add(getBaseDao().getVesting(coin.denom));
            } else {
                totalAmount = getBaseDao().getAvailable(coin.denom);
            }
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, denomDecimal, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, totalAmount, 6));
        }
        WDp.setDpSymbolImg(getBaseDao(), chainConfig, coin.denom, holder.itemImg);
        WDp.setDpSymbol(getMainActivity(), getBaseDao(), chainConfig, coin.denom, holder.itemSymbol);
        holder.itemFullName.setText(chainConfig.coinFullName(coin.denom));
        holder.itemInnerSymbol.setText("");

        holder.itemRoot.setOnClickListener(v -> {
            Intent intent;
            if (mNativeGrpc.get(position).denom.equalsIgnoreCase(chainConfig.mainDenom())) {
                intent = new Intent(getMainActivity(), StakingTokenGrpcActivity.class);
            } else {
                intent = new Intent(getMainActivity(), NativeTokenGrpcActivity.class);
            }
            intent.putExtra("denom", coin.denom);
            startActivity(intent);
        });
    }

    //with Authed IBC gRPC
    private void onBindIbcAuthToken(TokensAdapter.AssetHolder holder, ChainConfig chainConfig, int position) {
        final Coin coin = mIbcAuthedGrpc.get(position);
        final IbcToken ibcToken = getBaseDao().getIbcToken(coin.denom);
        WDp.setDpSymbolImg(getBaseDao(), chainConfig, coin.denom, holder.itemImg);
        WDp.setDpSymbol(getMainActivity(), getBaseDao(), chainConfig, coin.denom, holder.itemSymbol);

        holder.itemInnerSymbol.setText("");
        holder.itemFullName.setText(ibcToken.channel_id);
        holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), ibcToken.decimal, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), getBaseDao().getBaseDenom(chainConfig, coin.denom), new BigDecimal(coin.amount), ibcToken.decimal));

        holder.itemRoot.setOnClickListener(v -> {
            Intent intent = new Intent(getMainActivity(), IBCTokenDetailActivity.class);
            intent.putExtra("denom", coin.denom);
            startActivity(intent);
        });
    }

    //with Unknown IBC gRPC
    private void onBindIbcUnknownToken(TokensAdapter.AssetHolder holder, ChainConfig chainConfig, int position) {
        final Coin coin = mIbcUnknownGrpc.get(position);
        final IbcToken ibcToken = getBaseDao().getIbcToken(coin.denom);
        WDp.setDpSymbolImg(getBaseDao(), chainConfig, coin.denom, holder.itemImg);
        WDp.setDpSymbol(getMainActivity(), getBaseDao(), chainConfig, coin.denom, holder.itemSymbol);

        holder.itemInnerSymbol.setText("");
        holder.itemFullName.setText(ibcToken.channel_id);
        holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, new BigDecimal(coin.amount), 6));

        holder.itemRoot.setOnClickListener(v -> {
            Intent intent = new Intent(getMainActivity(), IBCTokenDetailActivity.class);
            intent.putExtra("denom", coin.denom);
            startActivity(intent);
        });
    }

    //with Osmosis, Crescent, Injective gRPC
    private void onBindPoolToken(TokensAdapter.AssetHolder holder, ChainConfig chainConfig, int position) {
        final Coin coin = mPoolGrpc.get(position);
        final int poolDecimal = WDp.getDenomDecimal(getBaseDao(), chainConfig, coin.denom);
        WDp.setDpSymbolImg(getBaseDao(), chainConfig, coin.denom, holder.itemImg);
        WDp.setDpSymbol(getMainActivity(), getBaseDao(), chainConfig, coin.denom, holder.itemSymbol);
        holder.itemInnerSymbol.setText("");
        holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), poolDecimal, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), coin.denom, new BigDecimal(coin.amount), poolDecimal));

        if (mBaseChain.equals(OSMOSIS_MAIN)) holder.itemFullName.setText(coin.denom);
        else holder.itemFullName.setText("Pool Asset");

        holder.itemRoot.setOnClickListener(v -> {
            Intent intent = new Intent(getMainActivity(), POOLTokenDetailActivity.class);
            intent.putExtra("denom", coin.denom);
            startActivity(intent);
        });
    }

    //with Erc gRPC
    private void onBindErcToken(TokensAdapter.AssetHolder holder, ChainConfig chainConfig, int position) {
        final Coin coin = mEtherGrpc.get(position);
        final Assets assets = getBaseDao().getAsset(coin.denom);
        if (assets != null) {
            WDp.setDpSymbolImg(getBaseDao(), chainConfig, coin.denom, holder.itemImg);
            WDp.setDpSymbol(getMainActivity(), getBaseDao(), chainConfig, coin.denom, holder.itemSymbol);
            holder.itemInnerSymbol.setText("");
            holder.itemFullName.setText(assets.display_symbol);
            
            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), assets.decimal, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), assets.origin_symbol, new BigDecimal(coin.amount), assets.decimal));

            holder.itemRoot.setOnClickListener(v -> {
                Intent intent = new Intent(getMainActivity(), BridgeTokenGrpcActivity.class);
                intent.putExtra("denom", assets.denom);
                startActivity(intent);
            });
        }
    }

    //bind kava bep2 tokens with gRPC
    private void onBindKavaBep2Token(TokensAdapter.AssetHolder holder, ChainConfig chainConfig, int position) {
        final Coin coin = mKavaBep2Grpc.get(position);
        WDp.setDpSymbolImg(getBaseDao(), chainConfig, coin.denom, holder.itemImg);
        WDp.setDpSymbol(getMainActivity(), getBaseDao(), chainConfig, coin.denom, holder.itemSymbol);
        holder.itemInnerSymbol.setText("");
        holder.itemFullName.setText(coin.denom.toUpperCase() + " on Kava Chain");
        
        int bep2decimal = WDp.getDenomDecimal(getBaseDao(), chainConfig, WDp.getDpSymbol(getBaseDao(), chainConfig, coin.denom));
        holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), bep2decimal, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), WDp.getDpSymbol(getBaseDao(), chainConfig, coin.denom), new BigDecimal(coin.amount), bep2decimal));

        holder.itemRoot.setOnClickListener(v -> {
            Intent intent = new Intent(getMainActivity(), NativeTokenGrpcActivity.class);
            intent.putExtra("denom", coin.denom);
            startActivity(intent);
        });
    }

    //bind kava etc tokens with gRPC
    private void onBindEtcGrpcToken(TokensAdapter.AssetHolder holder, ChainConfig chainConfig, int position) {
        final Coin coin = mEtcGrpc.get(position);
        WDp.setDpSymbolImg(getBaseDao(), chainConfig, coin.denom, holder.itemImg);
        WDp.setDpSymbol(getMainActivity(), getBaseDao(), chainConfig, coin.denom, holder.itemSymbol);
        holder.itemInnerSymbol.setText("(" + coin.denom + ")");
        holder.itemFullName.setText(coin.denom.toUpperCase() + " on Kava Chain");

        BigDecimal tokenTotalAmount = getBaseDao().getAvailable(coin.denom).add(getBaseDao().getVesting(coin.denom));
        BigDecimal convertedKavaAmount = WDp.convertTokenToKava(getBaseDao(), chainConfig, coin.denom);
        holder.itemBalance.setText(WDp.getDpAmount2(getContext(), tokenTotalAmount, WDp.getDenomDecimal(getBaseDao(), chainConfig, coin.denom), 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), chainConfig.mainDenom(), convertedKavaAmount, 6));
    }

    //bind cw20 tokens with gRPC
    private void onBindCw20GrpcToken(TokensAdapter.AssetHolder holder, int position) {
        final Cw20Assets cw20Asset = mCW20Grpc.get(position);
        Picasso.get().load(cw20Asset.logo).fit().placeholder(R.drawable.token_default).error(R.drawable.token_default).into(holder.itemImg);
        holder.itemSymbol.setTextColor(ContextCompat.getColor(getMainActivity(), R.color.colorBlackDayNight));
        holder.itemSymbol.setText(cw20Asset.denom.toUpperCase());
        holder.itemInnerSymbol.setText("");
        holder.itemFullName.setText(cw20Asset.contract_address);

        int decimal = cw20Asset.decimal;
        holder.itemBalance.setText(WDp.getDpAmount2(getContext(), cw20Asset.getAmount(), decimal, 6));
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
        if (coin.denom.startsWith("pool")) {
            holder.itemSymbol.setText("POOL");
        } else {
            holder.itemSymbol.setText("UNKNOWN");
        }
        holder.itemSymbol.setTextColor(ContextCompat.getColor(getMainActivity(), R.color.colorBlackDayNight));
        holder.itemInnerSymbol.setText("");
        holder.itemFullName.setText("");
        holder.itemImg.setImageResource(R.drawable.token_default);
        holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), 6, 6));
        holder.itemValue.setText("");
    }
    
    //with native tokens
    private void onBindNativeItem(TokensAdapter.AssetHolder holder, ChainConfig chainConfig, int position) {
        final Balance balance = mNative.get(position);
        BigDecimal totalAmount = BigDecimal.ZERO;

        WDp.setDpSymbolImg(getBaseDao(), chainConfig, balance.symbol, holder.itemImg);
        WDp.setDpSymbol(getMainActivity(), getBaseDao(), chainConfig, balance.symbol, holder.itemSymbol);
        holder.itemFullName.setText(chainConfig.coinFullName(balance.symbol));
        holder.itemInnerSymbol.setText("(" + balance.symbol + ")");
        
        if (mBaseChain.equals(BNB_MAIN)) totalAmount = getBaseDao().getAllBnbTokenAmount(balance.symbol);
        else totalAmount = getBaseDao().getAllExToken(balance.symbol);

        holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 0, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, totalAmount, 0));

        holder.itemRoot.setOnClickListener(v -> {
            Intent intent = new Intent(getMainActivity(), StakingTokenDetailActivity.class);
            startActivity(intent);
        });
    }

    //with Etc tokens (binance, okex)
    private void onBindEtcToken(TokensAdapter.AssetHolder holder, ChainConfig chainConfig, int position) {
        final Balance balance = mEtc.get(position);
        BigDecimal totalAmount = BigDecimal.ZERO;
        BigDecimal convertAmount = BigDecimal.ZERO;

        WDp.setDpSymbolImg(getBaseDao(), chainConfig, balance.symbol, holder.itemImg);
        WDp.setDpSymbol(getMainActivity(), getBaseDao(), chainConfig, balance.symbol, holder.itemSymbol);
        holder.itemInnerSymbol.setText("(" + balance.symbol + ")");

        if (mBaseChain.equals(BNB_MAIN)) {
            holder.itemFullName.setText(getBaseDao().getBnbToken(balance.symbol).name);
            totalAmount = getBaseDao().getAllBnbTokenAmount(balance.symbol);
            convertAmount = WDp.getBnbConvertAmount(getBaseDao(), balance.symbol, totalAmount);

        } else {
            holder.itemFullName.setText(getBaseDao().okToken(balance.symbol).description);
            totalAmount = getBaseDao().getAllExToken(balance.symbol);
            convertAmount = WDp.convertTokenToOkt(getBaseDao(), balance.symbol);
        }

        holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 0, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), chainConfig.mainDenom(), convertAmount, 0));

        holder.itemRoot.setOnClickListener(v -> {
            Intent intent = new Intent(getMainActivity(), NativeTokenDetailActivity.class);
            intent.putExtra("denom", balance.symbol);
            startActivity(intent);
        });

    }

    //with Unknown coin oec, bnb
    private void onBindUnKnownCoin(TokensAdapter.AssetHolder holder, int position) {
        final Balance balance = mUnKnown.get(position);
        holder.itemSymbol.setText("UNKNOWN");
        holder.itemSymbol.setTextColor(ContextCompat.getColor(getMainActivity(), R.color.colorBlackDayNight));
        holder.itemInnerSymbol.setText("");
        holder.itemFullName.setText("");
        holder.itemImg.setImageResource(R.drawable.token_default);
        holder.itemBalance.setText(WDp.getDpAmount2(getContext(), balance.balance, 6, 6));
        holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, BigDecimal.ZERO, 6));
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
                if (isGRPC(mBaseChain)) {
                    if (mSection == SECTION_NATIVE_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(mBaseChain, mNativeGrpc, mSection);
                        mItemCnt.setText("" + mNativeGrpc.size());
                    } else if (mSection == SECTION_IBC_AUTHED_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(mBaseChain, mIbcAuthedGrpc, mSection);
                        mItemCnt.setText("" + mIbcAuthedGrpc.size());
                    } else if (mSection == SECTION_IBC_UNKNOWN_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(mBaseChain, mIbcUnknownGrpc, mSection);
                        mItemCnt.setText("" + mIbcUnknownGrpc.size());
                    } else if (mSection == SECTION_UNKNOWN_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(mBaseChain, mUnknownGrpc, mSection);
                        mItemCnt.setText("" + mUnknownGrpc.size());
                    }

                    // osmosis pool token
                    else if (mSection == SECTION_POOL_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(mBaseChain, mPoolGrpc, mSection);
                        mItemCnt.setText("" + mPoolGrpc.size());
                    }

                    // ether bridge token
                    else if (mSection == SECTION_ETHER_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(mBaseChain, mEtherGrpc, mSection);
                        mItemCnt.setText("" + mEtherGrpc.size());
                    }

                    // kava token
                    else if (mSection == SECTION_KAVA_BEP2_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(mBaseChain, mKavaBep2Grpc, mSection);
                        mItemCnt.setText("" + mKavaBep2Grpc.size());
                    } else if (mSection == SECTION_ETC_GRPC) {
                        title = sectionCallback.getSectionGrpcHeader(mBaseChain, mEtcGrpc, mSection);
                        mItemCnt.setText("" + mEtcGrpc.size());
                    }

                    // cw20 token
                    else if (mSection == SECTION_CW20_GRPC) {
                        title = sectionCallback.getSectionCw20Header(mBaseChain, mCW20Grpc, mSection);
                        mItemCnt.setText("" + mCW20Grpc.size());
                    }

                } else {
                    if (mSection == SECTION_NATIVE) {
                        title = sectionCallback.getSecitonHeader(mBaseChain, mNative, mSection);
                        mItemCnt.setText("" + mNative.size());
                    } else if (mSection == SECTION_ETC) {
                        title = sectionCallback.getSecitonHeader(mBaseChain, mEtc, mSection);
                        mItemCnt.setText("" + mEtc.size());
                    } else if (mSection == SECTION_UNKNOWN) {
                        title = sectionCallback.getSecitonHeader(mBaseChain, mUnKnown, mSection);
                        mItemCnt.setText("" + mUnKnown.size());
                    }
                }
                mHeaderTitle.setText(title);
                if (!previousHeader.equals(title) || sectionCallback.isSection(mBaseChain, position)) {
                    drawHeader(c, child, headerView);
                    previousHeader = title;
                }
            }
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            int position = parent.getChildAdapterPosition(view);
            if (sectionCallback.isSection(mBaseChain, position)) {
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