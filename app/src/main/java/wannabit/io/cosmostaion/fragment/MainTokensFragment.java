package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;

import android.app.Activity;
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
import wannabit.io.cosmostaion.activities.tokenDetail.NativeTokenDetailActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.NativeTokenGrpcActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.StakingTokenDetailActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.StakingTokenGrpcActivity;
import wannabit.io.cosmostaion.activities.txs.common.SendActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dao.Asset;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.Cw20Asset;
import wannabit.io.cosmostaion.dialog.SelectCWTokenDialog;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class MainTokensFragment extends BaseFragment {
    public final static int SECTION_NATIVE_GRPC = 0;
    public final static int SECTION_IBC_GRPC = 1;
    public final static int SECTION_ETHER_GRPC = 2;
    public final static int SECTION_ERC20_GRPC = 3;

    public final static int SECITON_CONTRACT_EDIT = 10;

    public final static int SECTION_NATIVE = 20;
    public final static int SECTION_ETC = 21;

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
    private ArrayList<Coin> mIbcGrpc = new ArrayList<>();
    private ArrayList<Coin> mEtherGrpc = new ArrayList<>();
    private ArrayList<Cw20Asset> mErc20Grpc = new ArrayList<>();

    private ArrayList<Balance> mNative = new ArrayList<>();
    private ArrayList<Balance> mEtc = new ArrayList<>();

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

        mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getMainActivity().onClickQrCopy(mChainConfig, mAccount);
            }
        });

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getMainActivity(), R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onUpdateInfo();
                getMainActivity().onFetchAllData();
            }
        });

        mRecyclerView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (mSwipeRefreshLayout.isRefreshing()) return true;
                else return false;
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
                if (mChainConfig.bridgeCoinSupport() || mChainConfig.erc20CoinSupport()) {
                    return position == 0 || position == mNativeGrpc.size() || position == mNativeGrpc.size() + mIbcGrpc.size();

                } else {
                    if (isGRPC(baseChain)) {
                        return position == 0 || position == mNativeGrpc.size();
                    } else {
                        return position == 0 || position == mNative.size();
                    }
                }
            }

            // 헤더 제목 조건
            @Override
            public String getSectionGrpcHeader(BaseChain baseChain, ArrayList<Coin> coins, int section) {
                if (section == SECTION_NATIVE_GRPC) {
                    return getMainActivity().getString(R.string.str_native_token_title);
                } else if (section == SECTION_IBC_GRPC) {
                    return getMainActivity().getString(R.string.str_ibc_token_title);
                } else if (section == SECTION_ETHER_GRPC) {
                    return getMainActivity().getString(R.string.str_sif_ether_token_title);
                }
                return getMainActivity().getString(R.string.str_unknown_token_title);
            }

            @Override
            public String getSectionCw20Header(BaseChain baseChain, ArrayList<Cw20Asset> cw20Assets, int section) {
                if (section == SECTION_ERC20_GRPC) {
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
        mErc20Grpc = getBaseDao().mCw20MyAssets;
        mNativeGrpc.clear();
        mIbcGrpc.clear();
        mEtherGrpc.clear();

        for (Coin coin : getBaseDao().mGrpcBalance) {
            final Asset asset = getBaseDao().getAsset(coin.denom);
            if (asset != null) {
                if (asset.type.equalsIgnoreCase("staking") || asset.type.equalsIgnoreCase("native")) {
                    mNativeGrpc.add(coin);

                } else if (asset.type.equalsIgnoreCase("ibc")) {
                    mIbcGrpc.add(coin);

                } else if (asset.type.equalsIgnoreCase("bridge")) {
                    mEtherGrpc.add(coin);
                }
            }
        }

        mNative.clear();
        mEtc.clear();

        for (Balance balance : getBaseDao().mBalances) {
            if (balance.symbol.equalsIgnoreCase(mainDenom)) {
                mNative.add(balance);
            } else {
                mEtc.add(balance);
            }
        }

        if (isGRPC(mBaseChain)) {
            WUtil.onSortingCoins(mNativeGrpc, mBaseChain);
            WUtil.onSortingContract(mErc20Grpc);
        } else {
            WUtil.onSortingNativeCoins(mEtc, mBaseChain);
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

    private class TokensAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == SECITON_CONTRACT_EDIT) {
                return new EditHolder(getLayoutInflater().inflate(R.layout.item_edit, viewGroup, false));
            } else {
                return new AssetHolder(getLayoutInflater().inflate(R.layout.item_token, viewGroup, false));
            }
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
            if (isGRPC(mBaseChain)) {
                if (getItemViewType(position) == SECTION_NATIVE_GRPC) {
                    onNativeGrpcItem(viewHolder, mChainConfig, position);
                } else if (getItemViewType(position) == SECTION_IBC_GRPC) {
                    onBindIbcAuthToken(viewHolder, mChainConfig, position - mNativeGrpc.size());
                } else {
                    if (mChainConfig.bridgeCoinSupport() && getItemViewType(position) == SECTION_ETHER_GRPC) {
                        onBindEthToken(viewHolder, mChainConfig, position - mNativeGrpc.size() - mIbcGrpc.size());

                    } else if (mChainConfig.erc20CoinSupport()) {
                        if (getItemViewType(position) == SECTION_ERC20_GRPC) {
                            onBindCw20GrpcToken(viewHolder, position - mNativeGrpc.size() - mIbcGrpc.size());
                        } else if (getItemViewType(position) == SECITON_CONTRACT_EDIT) {
                            onBindEdit(viewHolder);
                        }
                    }
                }

            } else {
                if (getItemViewType(position) == SECTION_NATIVE) {
                    onBindNativeItem(viewHolder, mChainConfig, position);
                } else if (getItemViewType(position) == SECTION_ETC) {
                    onBindEtcToken(viewHolder, mChainConfig, position - mNative.size());
                }
            }
        }

        private void onNativeGrpcItem(RecyclerView.ViewHolder viewHolder, ChainConfig chainConfig, int position) {
            final AssetHolder holder = (AssetHolder) viewHolder;
            final Coin coin = mNativeGrpc.get(position);
            final Asset asset = getBaseDao().getAsset(coin.denom);

            BigDecimal totalAmount = BigDecimal.ZERO;
            if (asset != null) {
                if (asset.base_denom.equalsIgnoreCase(chainConfig.mainDenom())) {
                    totalAmount = getBaseDao().getAllMainAsset(chainConfig.mainDenom());
                } else {
                    totalAmount = getBaseDao().getAvailable(asset.base_denom).add(getBaseDao().getVesting(asset.base_denom));
                }

                WDp.setDpSymbolImg(getBaseDao(), chainConfig, asset.base_denom, holder.itemImg);
                WDp.setDpSymbol(getMainActivity(), getBaseDao(), chainConfig, asset.base_denom, holder.itemSymbol);
                holder.itemPath.setText(asset.description);

                holder.itemPerPrice.setText(WDp.dpPerUserCurrencyValue(getBaseDao(), asset.base_denom));
                valueChangeStatus(getActivity(), getBaseDao(), asset.base_denom, holder.itemUpDown);

                holder.itemBalance.setText(WDp.getDpAmount2(totalAmount, asset.decimal, 6));
                holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), asset.base_denom, totalAmount, asset.decimal));

                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent;
                        if (asset.base_denom.equalsIgnoreCase(chainConfig.mainDenom())) {
                            intent = new Intent(getMainActivity(), StakingTokenGrpcActivity.class);
                        } else {
                            intent = new Intent(getMainActivity(), NativeTokenGrpcActivity.class);
                        }
                        intent.putExtra("denom", asset.base_denom);
                        startActivity(intent);
                    }
                });
            }
        }

        private void onBindIbcAuthToken(RecyclerView.ViewHolder viewHolder, ChainConfig chainConfig, int position) {
            final AssetHolder holder = (AssetHolder) viewHolder;
            final Coin coin = mIbcGrpc.get(position);
            final Asset asset = getBaseDao().getAsset(mIbcGrpc.get(position).denom);

            if (asset != null) {
                WDp.setDpSymbolImg(getBaseDao(), chainConfig, asset.denom, holder.itemImg);
                WDp.setDpSymbol(getMainActivity(), getBaseDao(), chainConfig, asset.denom, holder.itemSymbol);
                holder.itemPath.setText(assetDpPath(asset.path));

                holder.itemPerPrice.setText(WDp.dpPerUserCurrencyValue(getBaseDao(), asset.base_denom));
                valueChangeStatus(getActivity(), getBaseDao(), asset.base_denom, holder.itemUpDown);

                holder.itemBalance.setText(WDp.getDpAmount2(new BigDecimal(coin.amount), asset.decimal, 6));
                holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), asset.base_denom, new BigDecimal(coin.amount), asset.decimal));

                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getMainActivity(), SendActivity.class);
                        intent.putExtra("sendTokenDenom", asset.denom);
                        startActivity(intent);
                    }
                });
            }
        }

        private void onBindEthToken(RecyclerView.ViewHolder viewHolder, ChainConfig chainConfig, int position) {
            final AssetHolder holder = (AssetHolder) viewHolder;
            final Coin coin = mEtherGrpc.get(position);
            final Asset asset = getBaseDao().getAsset(coin.denom);

            if (asset != null) {
                WDp.setDpSymbolImg(getBaseDao(), chainConfig, asset.denom, holder.itemImg);
                WDp.setDpSymbol(getMainActivity(), getBaseDao(), chainConfig, asset.denom, holder.itemSymbol);
                holder.itemPath.setText(assetDpPath(asset.path));

                holder.itemPerPrice.setText(WDp.dpPerUserCurrencyValue(getBaseDao(), asset.base_denom));
                valueChangeStatus(getActivity(), getBaseDao(), asset.base_denom, holder.itemUpDown);

                holder.itemBalance.setText(WDp.getDpAmount2(getContext(), new BigDecimal(coin.amount), asset.decimal, 6));
                holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), asset.base_denom, new BigDecimal(coin.amount), asset.decimal));

                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getMainActivity(), SendActivity.class);
                        intent.putExtra("sendTokenDenom", asset.denom);
                        startActivity(intent);
                    }
                });
            }
        }

        private void onBindCw20GrpcToken(RecyclerView.ViewHolder viewHolder, int position) {
            final AssetHolder holder = (AssetHolder) viewHolder;
            final Cw20Asset asset = mErc20Grpc.get(position);

            if (asset != null) {
                Picasso.get().load(asset.assetImg()).fit().placeholder(R.drawable.token_default).error(R.drawable.token_default).into(holder.itemImg);
                holder.itemSymbol.setText(asset.denom.toUpperCase());
                holder.itemPath.setText("");

                holder.itemPerPrice.setText(WDp.dpPerUserCurrencyValue(getBaseDao(), asset.denom));
                valueChangeStatus(getActivity(), getBaseDao(), asset.denom, holder.itemUpDown);

                holder.itemBalance.setText(WDp.getDpAmount2(getContext(), asset.getAmount(), asset.decimal, 6));
                holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), asset.denom, asset.getAmount(), asset.decimal));

                holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getMainActivity(), SendActivity.class);
                        intent.putExtra("sendTokenDenom", asset.denom);
                        startActivity(intent);
                    }
                });
            }
        }

        private void onBindEdit(RecyclerView.ViewHolder viewHolder) {
            final EditHolder holder = (EditHolder) viewHolder;
            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    SelectCWTokenDialog dialog = SelectCWTokenDialog.newInstance(bundle);
                    dialog.setCancelable(false);
                    dialog.setTargetFragment(MainTokensFragment.this, SECITON_CONTRACT_EDIT);
                    getFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();
                }
            });
        }

        private void onBindNativeItem(RecyclerView.ViewHolder viewHolder, ChainConfig chainConfig, int position) {
            final AssetHolder holder = (AssetHolder) viewHolder;
            final Balance balance = mNative.get(position);
            BigDecimal totalAmount = BigDecimal.ZERO;

            WDp.setDpSymbolImg(getBaseDao(), chainConfig, balance.symbol, holder.itemImg);
            WDp.setDpSymbol(getMainActivity(), getBaseDao(), chainConfig, balance.symbol, holder.itemSymbol);
            holder.itemPerPrice.setText(chainConfig.coinFullName(balance.symbol));
            holder.itemPath.setText("(" + balance.symbol + ")");
            holder.itemPerPrice.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorGray1));

            if (mBaseChain.equals(BNB_MAIN))
                totalAmount = getBaseDao().getAllBnbTokenAmount(balance.symbol);
            else totalAmount = getBaseDao().getAllExToken(balance.symbol);

            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 0, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), balance.symbol, totalAmount, 0));

            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getMainActivity(), StakingTokenDetailActivity.class);
                    startActivity(intent);
                }
            });
        }

        private void onBindEtcToken(RecyclerView.ViewHolder viewHolder, ChainConfig chainConfig, int position) {
            final AssetHolder holder = (AssetHolder) viewHolder;
            final Balance balance = mEtc.get(position);
            BigDecimal totalAmount = BigDecimal.ZERO;
            BigDecimal convertAmount = BigDecimal.ZERO;

            WDp.setDpSymbolImg(getBaseDao(), chainConfig, balance.symbol, holder.itemImg);
            WDp.setDpSymbol(getMainActivity(), getBaseDao(), chainConfig, balance.symbol, holder.itemSymbol);
            holder.itemPath.setText("(" + balance.symbol + ")");

            if (mBaseChain.equals(BNB_MAIN)) {
                holder.itemPerPrice.setText(getBaseDao().getBnbToken(balance.symbol).name);
                totalAmount = getBaseDao().getAllBnbTokenAmount(balance.symbol);
                convertAmount = WDp.getBnbConvertAmount(getBaseDao(), balance.symbol, totalAmount);

            } else {
                holder.itemPerPrice.setText(getBaseDao().okToken(balance.symbol).description);
                totalAmount = getBaseDao().getAllExToken(balance.symbol);
                convertAmount = WDp.convertTokenToOkt(getBaseDao(), balance.symbol);
            }
            holder.itemPerPrice.setTextColor(ContextCompat.getColor(getActivity(), R.color.colorGray1));

            holder.itemBalance.setText(WDp.getDpAmount2(getContext(), totalAmount, 0, 6));
            holder.itemValue.setText(WDp.dpUserCurrencyValue(getBaseDao(), chainConfig.mainDenom(), convertAmount, 0));

            holder.itemRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getMainActivity(), NativeTokenDetailActivity.class);
                    intent.putExtra("denom", balance.symbol);
                    startActivity(intent);
                }
            });
        }

        @Override
        public int getItemCount() {
            int defaultCount = mNativeGrpc.size() + mIbcGrpc.size();
            if (isGRPC(mBaseChain)) {
                if (mChainConfig.bridgeCoinSupport()) {
                    return defaultCount + mEtherGrpc.size();
                } else if (mChainConfig.erc20CoinSupport()) {
                    return defaultCount + mErc20Grpc.size() + 1;
                } else {
                    return defaultCount;
                }
            } else {
                return getBaseDao().mBalances.size();
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (mChainConfig.bridgeCoinSupport()) {
                if (position < mNativeGrpc.size()) {
                    return SECTION_NATIVE_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcGrpc.size()) {
                    return SECTION_IBC_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcGrpc.size() + mEtherGrpc.size()) {
                    return SECTION_ETHER_GRPC;
                }

            } else if (mChainConfig.erc20CoinSupport()) {
                if (position < mNativeGrpc.size()) {
                    return SECTION_NATIVE_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcGrpc.size()) {
                    return SECTION_IBC_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcGrpc.size() + mErc20Grpc.size()) {
                    return SECTION_ERC20_GRPC;
                } else {
                    return SECITON_CONTRACT_EDIT;
                }

            } else if (isGRPC(mBaseChain)) {
                if (position < mNativeGrpc.size()) {
                    return SECTION_NATIVE_GRPC;
                } else if (position < mNativeGrpc.size() + mIbcGrpc.size()) {
                    return SECTION_IBC_GRPC;
                }

            } else {
                if (mNative != null) {
                    if (position < mNative.size()) {
                        return SECTION_NATIVE;
                    } else if (position < mNative.size() + mEtc.size()) {
                        return SECTION_ETC;
                    }
                } else {
                    if (position < mEtc.size()) {
                        return SECTION_ETC;
                    }
                }
            }
            return 0;
        }

        public class AssetHolder extends RecyclerView.ViewHolder {
            private CardView itemRoot;
            private ImageView itemImg;
            private TextView itemSymbol, itemPath, itemPerPrice, itemUpDown, itemBalance, itemValue;

            public AssetHolder(View v) {
                super(v);
                itemRoot = itemView.findViewById(R.id.token_card);
                itemImg = itemView.findViewById(R.id.token_img);
                itemSymbol = itemView.findViewById(R.id.token_symbol);
                itemPath = itemView.findViewById(R.id.token_path);
                itemPerPrice = itemView.findViewById(R.id.per_price);
                itemUpDown = itemView.findViewById(R.id.up_down);
                itemBalance = itemView.findViewById(R.id.token_balance);
                itemValue = itemView.findViewById(R.id.token_value);
            }
        }

        public class EditHolder extends RecyclerView.ViewHolder {
            private CardView itemRoot;

            public EditHolder(@NonNull View itemView) {
                super(itemView);
                itemRoot = itemView.findViewById(R.id.edit_card);
            }
        }
    }

    private void valueChangeStatus(Context c, BaseData baseData, String denom, TextView changeTxt) {
        BigDecimal lastUpDown = WDp.valueChange(baseData, denom);
        if (BigDecimal.ZERO.compareTo(lastUpDown) > 0) {
            changeTxt.setText(lastUpDown + "%");
            changeTxt.setTextColor(ContextCompat.getColor(c, R.color.colorVoteNo));

        } else if (BigDecimal.ZERO.compareTo(lastUpDown) < 0) {
            changeTxt.setText("+" + lastUpDown + "%");
            changeTxt.setTextColor(ContextCompat.getColor(c, R.color.colorVoteYes));

        } else {
            changeTxt.setText("");
        }
    }

    private String assetDpPath(String path) {
        return path.replace("bnb-beacon-chain", "binance")
                .replace("ethereum", "eth")
                .replace("ethereum", "eth")
                .replace("persistence", "persis")
                .replace("gravity-bridge", "gravity")
                .replace("konstellation", "konstel")
                .replace("assetmantle", "assetman")
                .replace(">", " → ");
    }

    public MainActivity getMainActivity() { return (MainActivity) getBaseActivity(); }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        if (requestCode == SECITON_CONTRACT_EDIT && resultCode == Activity.RESULT_OK) {
            onUpdateInfo();
            getMainActivity().onFetchAllData();
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    // Section Header
    public class RecyclerViewHeader extends RecyclerView.ItemDecoration {
        private final int topPadding;

        private final boolean sticky;
        private final SectionCallback sectionCallback;

        private View headerView;
        private CardView mRoot;
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
                mRoot = (CardView) headerView.findViewById(R.id.root);
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
                        mRoot.setVisibility(View.VISIBLE);
                        title = sectionCallback.getSectionGrpcHeader(mBaseChain, mNativeGrpc, mSection);
                        mItemCnt.setText("" + mNativeGrpc.size());
                    } else if (mSection == SECTION_IBC_GRPC) {
                        mRoot.setVisibility(View.VISIBLE);
                        title = sectionCallback.getSectionGrpcHeader(mBaseChain, mIbcGrpc, mSection);
                        mItemCnt.setText("" + mIbcGrpc.size());
                    }

                    // ether bridge token
                    else if (mSection == SECTION_ETHER_GRPC) {
                        mRoot.setVisibility(View.VISIBLE);
                        title = sectionCallback.getSectionGrpcHeader(mBaseChain, mEtherGrpc, mSection);
                        mItemCnt.setText("" + mEtherGrpc.size());
                    }

                    // cw20 token
                    else if (mSection == SECTION_ERC20_GRPC) {
                        mRoot.setVisibility(View.VISIBLE);
                        title = sectionCallback.getSectionCw20Header(mBaseChain, mErc20Grpc, mSection);
                        mItemCnt.setText("" + mErc20Grpc.size());
                    }

                    else if (mSection == SECITON_CONTRACT_EDIT) {
                        mRoot.setVisibility(View.GONE);
                    }

                } else {
                    if (mSection == SECTION_NATIVE) {
                        title = sectionCallback.getSecitonHeader(mBaseChain, mNative, mSection);
                        mItemCnt.setText("" + mNative.size());
                    } else if (mSection == SECTION_ETC) {
                        title = sectionCallback.getSecitonHeader(mBaseChain, mEtc, mSection);
                        mItemCnt.setText("" + mEtc.size());
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

        String getSectionCw20Header(BaseChain baseChain, ArrayList<Cw20Asset> cw20Assets, int section);

        String getSecitonHeader(BaseChain baseChain, ArrayList<Balance> balances, int section);
    }
}