package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.DESMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.NEUTRON_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import org.apache.commons.lang3.StringUtils;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResNotice;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletAuthzHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletBinanceHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletChainHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletDesmosAppHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletGuideHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletKavaIncentiveHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletMedipassHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletMintHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletNeutronHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletOkexHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletPriceHolder;

public class MainSendFragment extends BaseFragment {
    private CardView mCardView;
    private CardView mNoticeView;
    private ImageView itemKeyStatus;
    private TextView mWalletAddress, mEthAddress;
    private TextView mNoticeTitle;
    private TextView mNoticeInfo;
    private TextView mTotalValue;

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private MainWalletAdapter mMainWalletAdapter;

    private Account mAccount;
    private BaseChain mBaseChain;
    private ChainConfig mChainConfig;

    public static MainSendFragment newInstance() {
        return new MainSendFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_send, container, false);
        mNoticeView = rootView.findViewById(R.id.notice_root);
        mNoticeTitle = rootView.findViewById(R.id.title_notice);
        mNoticeInfo = rootView.findViewById(R.id.info_notice);
        mCardView = rootView.findViewById(R.id.card_root);
        itemKeyStatus = rootView.findViewById(R.id.img_account);
        mWalletAddress = rootView.findViewById(R.id.wallet_address);
        mEthAddress = rootView.findViewById(R.id.eth_address);
        mTotalValue = rootView.findViewById(R.id.total_value);
        mSwipeRefreshLayout = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView = rootView.findViewById(R.id.recycler);

        mCardView.setOnClickListener(v -> getMainActivity().onClickQrCopy(mChainConfig, mAccount));

        mSwipeRefreshLayout.setColorSchemeColors(ContextCompat.getColor(getMainActivity(), R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(() -> {
            getMainActivity().onFetchAllData();
            onUpdateView();
            mMainWalletAdapter.notifyDataSetChanged();
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (getMainActivity().mFloatBtn.isShown()) {
                        getMainActivity().mFloatBtn.hide();
                    }
                } else if (dy < 0) {
                    if (!getMainActivity().mFloatBtn.isShown()) {
                        getMainActivity().mFloatBtn.show();
                    }
                }
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mMainWalletAdapter = new MainWalletAdapter();
        mRecyclerView.setAdapter(mMainWalletAdapter);

        onUpdateView();
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if (!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
        onUpdateView();
    }

    @Override
    public void onBusyFetch() {
        if (!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void onUpdateView() {
        if (getMainActivity() == null || getMainActivity().mAccount == null) return;
        mAccount = getMainActivity().mAccount;
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mChainConfig = getMainActivity().mChainConfig;

        mCardView.setCardBackgroundColor(ContextCompat.getColor(getMainActivity(), mChainConfig.chainBgColor()));
        onNoticeView();

        getMainActivity().setAccountKeyStatus(getActivity(), mAccount, mChainConfig, itemKeyStatus);
        mWalletAddress.setText(mAccount.address);
        getMainActivity().setEthAddress(mChainConfig, mEthAddress);
        mTotalValue.setText(WDp.dpAllAssetValue(mBaseChain, getBaseDao(), mChainConfig));
        mMainWalletAdapter.notifyDataSetChanged();
    }

    private void onNoticeView() {
        mNoticeView.setCardBackgroundColor(ContextCompat.getColor(getMainActivity(), mChainConfig.chainBgColor()));
        ApiClient.getMintscan(getContext()).getNotice(mChainConfig.chainName(), true).enqueue(new Callback<ResNotice>() {
            @Override
            public void onResponse(Call<ResNotice> call, Response<ResNotice> response) {
                if (response != null && response.body() != null && response.isSuccessful()) {
                    ResNotice noticeInfo = response.body();
                    if (noticeInfo.boards.isEmpty()) {
                        mNoticeView.setVisibility(View.GONE);
                    } else {
                        mNoticeView.setVisibility(View.VISIBLE);
                        mNoticeTitle.setText(StringUtils.capitalize(noticeInfo.boards.get(0).type.toUpperCase()));
                        mNoticeInfo.setText(noticeInfo.boards.get(0).title);

                        mNoticeView.setOnClickListener(view -> {
                            String url = mChainConfig.noticeLink() + noticeInfo.boards.get(0).id;
                            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                            startActivity(intent);
                        });
                    }
                }
            }

            @Override
            public void onFailure(Call<ResNotice> call, Throwable t) {
                WLog.w("error : " + t.getMessage());
            }
        });
    }


    public MainActivity getMainActivity() {
        return (MainActivity) getBaseActivity();
    }

    private class MainWalletAdapter extends RecyclerView.Adapter<BaseHolder> {
        private static final int TYPE_WALLET = 0;
        private static final int TYPE_BINANCE = 1;
        private static final int TYPE_OKEX = 2;
        private static final int TYPE_NEUTRON = 3;

        private static final int TYPE_KAVA_INCENTIVE = 40;
        private static final int TYPE_DESMOS_APP = 50;
        private static final int TYPE_MEDIPASS = 60;
        private static final int TYPE_AUTHZ = 70;
        private static final int TYPE_PRICE = 80;
        private static final int TYPE_MINT = 81;
        private static final int TYPE_GIUDE = 82;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_OKEX) {
                return new WalletOkexHolder(getLayoutInflater().inflate(R.layout.item_wallet_okex, viewGroup, false));

            } else if (viewType == TYPE_BINANCE) {
                return new WalletBinanceHolder(getLayoutInflater().inflate(R.layout.item_wallet_binance, viewGroup, false));

            } else if (viewType == TYPE_NEUTRON) {
                return new WalletNeutronHolder(getLayoutInflater().inflate(R.layout.item_wallet_neutron, viewGroup, false));

            } else if (viewType == TYPE_WALLET) {
                return new WalletChainHolder(getLayoutInflater().inflate(R.layout.item_wallet_chain, viewGroup, false));

            } else if (viewType == TYPE_PRICE) {
                return new WalletPriceHolder(getLayoutInflater().inflate(R.layout.item_wallet_price, viewGroup, false));

            } else if (viewType == TYPE_MINT) {
                return new WalletMintHolder(getLayoutInflater().inflate(R.layout.item_wallet_mint, viewGroup, false));

            } else if (viewType == TYPE_GIUDE) {
                return new WalletGuideHolder(getLayoutInflater().inflate(R.layout.item_wallet_guide, viewGroup, false));

            } else if (viewType == TYPE_KAVA_INCENTIVE) {
                return new WalletKavaIncentiveHolder(getLayoutInflater().inflate(R.layout.item_wallet_kavaincentive, viewGroup, false));

            } else if (viewType == TYPE_DESMOS_APP) {
                return new WalletDesmosAppHolder(getLayoutInflater().inflate(R.layout.item_wallet_desmos_app, viewGroup, false));

            } else if (viewType == TYPE_MEDIPASS) {
                return new WalletMedipassHolder(getLayoutInflater().inflate(R.layout.item_wallet_medipass, viewGroup, false));

            } else if (viewType == TYPE_AUTHZ) {
                return new WalletAuthzHolder(getLayoutInflater().inflate(R.layout.item_wallet_authz, viewGroup, false));

            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder viewHolder, int position) {
            final MainActivity mainActivity = getMainActivity();
            viewHolder.onBindHolder(mainActivity);
        }

        @Override
        public int getItemCount() {
            if (getMainActivity().mBaseChain == null) return 0;

            if (getMainActivity().mChainConfig.baseChain().equals(KAVA_MAIN) || getMainActivity().mChainConfig.baseChain().equals(MEDI_MAIN) ||
                    getMainActivity().mChainConfig.baseChain().equals(DESMOS_MAIN)) return 6;
            else if (getMainActivity().mChainConfig.authzSupport()) return 5;
            else if (getMainActivity().mChainConfig.baseChain().equals(NEUTRON_TEST)) return 3;
            else if (isGRPC(getMainActivity().mChainConfig.baseChain())) return 4;
            else return 3;
        }

        @Override
        public int getItemViewType(int position) {
            if (getItemCount() == 6) {
                if (position == 0) return TYPE_WALLET;
                if (getMainActivity().mChainConfig.baseChain().equals(KAVA_MAIN)) {
                    if (position == 1) return TYPE_KAVA_INCENTIVE;
                    else if (position == 2) return TYPE_PRICE;
                    else if (position == 3) return TYPE_MINT;
                    else if (position == 4) return TYPE_AUTHZ;
                    else if (position == 5) return TYPE_GIUDE;

                } else if (getMainActivity().mChainConfig.baseChain().equals(MEDI_MAIN) || getMainActivity().mChainConfig.baseChain().equals(DESMOS_MAIN)) {
                    if (position == 1) return TYPE_PRICE;
                    else if (position == 2) return TYPE_MINT;
                    else if (position == 3) return TYPE_AUTHZ;
                    else if (position == 4) {
                        if (getMainActivity().mChainConfig.baseChain().equals(MEDI_MAIN)) return TYPE_MEDIPASS;
                        else if (getMainActivity().mChainConfig.baseChain().equals(DESMOS_MAIN)) return TYPE_DESMOS_APP;
                    }
                    else if (position == 5) return TYPE_GIUDE;
                }

            } else if (getItemCount() == 5) {
                if (position == 0) return TYPE_WALLET;
                else if (position == 1) return TYPE_PRICE;
                else if (position == 2) return TYPE_MINT;
                else if (position == 3) {
                    if (getMainActivity().mChainConfig.baseChain().equals(DESMOS_MAIN)) return TYPE_DESMOS_APP;
                    else return TYPE_AUTHZ;
                }
                else if (position == 4) return TYPE_GIUDE;

            } else if (getItemCount() == 4) {
                if (position == 0) return TYPE_WALLET;
                else if (position == 1) return TYPE_PRICE;
                else if (position == 2) return TYPE_MINT;
                else if (position == 3) return TYPE_GIUDE;

            } else {
                if (position == 0) {
                    if (getMainActivity().mChainConfig.baseChain().equals(NEUTRON_TEST)) return TYPE_NEUTRON;
                    else if (getMainActivity().mChainConfig.baseChain().equals(BNB_MAIN)) return TYPE_BINANCE;
                    else return TYPE_OKEX;
                } else if (position == 1) return TYPE_PRICE;
                else if (position == 2) return TYPE_GIUDE;
            }
            return 0;
        }
    }
}