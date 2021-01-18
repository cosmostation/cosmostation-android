package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.WalletAddressHolder;
import wannabit.io.cosmostaion.widget.WalletAkashHolder;
import wannabit.io.cosmostaion.widget.WalletBandHolder;
import wannabit.io.cosmostaion.widget.WalletBinanceHolder;
import wannabit.io.cosmostaion.widget.WalletCertikHolder;
import wannabit.io.cosmostaion.widget.WalletCosmosHolder;
import wannabit.io.cosmostaion.widget.WalletGuideHolder;
import wannabit.io.cosmostaion.widget.WalletHolder;
import wannabit.io.cosmostaion.widget.WalletIrisHolder;
import wannabit.io.cosmostaion.widget.WalletKavaHolder;
import wannabit.io.cosmostaion.widget.WalletMintHolder;
import wannabit.io.cosmostaion.widget.WalletOkexHolder;
import wannabit.io.cosmostaion.widget.WalletPriceHolder;
import wannabit.io.cosmostaion.widget.WalletSecretHolder;
import wannabit.io.cosmostaion.widget.WalletStarnameHolder;
import wannabit.io.cosmostaion.widget.WalletUndelegationHolder;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;

public class MainSendFragment extends BaseFragment {

    private SwipeRefreshLayout              mSwipeRefreshLayout;
    private RecyclerView                    mRecyclerView;
    private MainWalletAdapter               mMainWalletAdapter;


    public static MainSendFragment newInstance(Bundle bundle) {
        MainSendFragment fragment = new MainSendFragment();
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
        View rootView           = inflater.inflate(R.layout.fragment_main_send, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMainActivity().onFetchAllData();
            }
        });
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);
                if (dy > 0) {
                    if (getMainActivity().mFloatBtn.isShown()) {
                        getMainActivity().mFloatBtn.hide();
                    }
                }
                else if (dy < 0) {
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
        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        onUpdateView();
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
        mMainWalletAdapter.notifyDataSetChanged();
        mSwipeRefreshLayout.setRefreshing(false);
        getMainActivity().onUpdateAccountListAdapter();
    }

    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }

    private class MainWalletAdapter extends RecyclerView.Adapter<WalletHolder> {
        private static final int TYPE_ADDRESS           = 0;
        private static final int TYPE_COSMOS            = 1;
        private static final int TYPE_IRIS              = 2;
        private static final int TYPE_BINANCE           = 3;
        private static final int TYPE_KAVA              = 4;
        private static final int TYPE_STARNAME          = 5;
        private static final int TYPE_BAND              = 6;
        private static final int TYPE_OKEX              = 7;
        private static final int TYPE_CERTIK            = 8;
        private static final int TYPE_SECRET            = 9;
        private static final int TYPE_AKASH             = 10;
        private static final int TYPE_STAKE_DROP        = 30;
        private static final int TYPE_UNDELEGATIONS     = 40;
        private static final int TYPE_PRICE             = 80;
        private static final int TYPE_MINT              = 81;
        private static final int TYPE_GIUDE             = 82;

        @NonNull
        @Override
        public WalletHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_ADDRESS) {
                return new WalletAddressHolder(getLayoutInflater().inflate(R.layout.item_wallet_address, viewGroup, false));

            } else if (viewType == TYPE_COSMOS) {
                return new WalletCosmosHolder(getLayoutInflater().inflate(R.layout.item_wallet_cosmos, viewGroup, false));

            } else if (viewType == TYPE_IRIS) {
                return new WalletIrisHolder(getLayoutInflater().inflate(R.layout.item_wallet_iris, viewGroup, false));

            } else if (viewType == TYPE_BINANCE) {
                return new WalletBinanceHolder(getLayoutInflater().inflate(R.layout.item_wallet_binance, viewGroup, false));

            } else if (viewType == TYPE_KAVA) {
                return new WalletKavaHolder(getLayoutInflater().inflate(R.layout.item_wallet_kava, viewGroup, false));

            } else if (viewType == TYPE_STARNAME) {
                return new WalletStarnameHolder(getLayoutInflater().inflate(R.layout.item_wallet_starname, viewGroup, false));

            } else if (viewType == TYPE_BAND) {
                return new WalletBandHolder(getLayoutInflater().inflate(R.layout.item_wallet_band, viewGroup, false));

            } else if (viewType == TYPE_OKEX) {
                return new WalletOkexHolder(getLayoutInflater().inflate(R.layout.item_wallet_okex, viewGroup, false));

            } else if (viewType == TYPE_CERTIK) {
                return new WalletCertikHolder(getLayoutInflater().inflate(R.layout.item_wallet_certik, viewGroup, false));

            } else if (viewType == TYPE_SECRET) {
                return new WalletSecretHolder(getLayoutInflater().inflate(R.layout.item_wallet_secret, viewGroup, false));

            } else if (viewType == TYPE_AKASH) {
                return new WalletAkashHolder(getLayoutInflater().inflate(R.layout.item_wallet_akash, viewGroup, false));

            } else if (viewType == TYPE_PRICE) {
                return new WalletPriceHolder(getLayoutInflater().inflate(R.layout.item_wallet_price, viewGroup, false));

            } else if (viewType == TYPE_MINT) {
                return new WalletMintHolder(getLayoutInflater().inflate(R.layout.item_wallet_mint, viewGroup, false));

            } else if (viewType == TYPE_GIUDE) {
                return new WalletGuideHolder(getLayoutInflater().inflate(R.layout.item_wallet_guide, viewGroup, false));

            } else if (viewType == TYPE_UNDELEGATIONS) {
                return new WalletUndelegationHolder(getLayoutInflater().inflate(R.layout.item_wallet_undelegation, viewGroup, false));

            }
            return null;
        }

        @Override
        public void onBindViewHolder(@NonNull WalletHolder viewHolder, int position) {
            viewHolder.onBindHolder(getMainActivity());
        }

        @Override
        public int getItemCount() {
            if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST) ||
                    getMainActivity().mBaseChain.equals(OKEX_MAIN) || getMainActivity().mBaseChain.equals(OK_TEST)) {
                return 4;
            } else {
                if (getBaseDao().mUnbondings.size() > 0) {
                    return 6;
                } else {
                    return 5;
                }
            }
        }


        @Override
        public int getItemViewType(int position) {
            if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST) ||
                    getMainActivity().mBaseChain.equals(OKEX_MAIN) || getMainActivity().mBaseChain.equals(OK_TEST)) {
                if (position == 0) {
                    return TYPE_ADDRESS;
                } else if (position == 1) {
                    if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) { return TYPE_BINANCE; }
                    else if (getMainActivity().mBaseChain.equals(OKEX_MAIN) || getMainActivity().mBaseChain.equals(OK_TEST)) { return TYPE_OKEX; }
                } else if (position == 2) {
                    return TYPE_PRICE;
                } else if (position == 3) {
                    return TYPE_GIUDE;
                }

            } else {
                if (position == 0) {
                    return TYPE_ADDRESS;
                } else if (position == 1) {
                    if (getMainActivity().mBaseChain.equals(COSMOS_MAIN)) { return TYPE_COSMOS; }
                    else if (getMainActivity().mBaseChain.equals(IRIS_MAIN)) { return TYPE_IRIS; }
                    else if (getMainActivity().mBaseChain.equals(KAVA_MAIN)) { return TYPE_KAVA; }
                    else if (getMainActivity().mBaseChain.equals(IOV_MAIN)) { return TYPE_STARNAME; }
                    else if (getMainActivity().mBaseChain.equals(BAND_MAIN)) { return TYPE_BAND; }
                    else if (getMainActivity().mBaseChain.equals(CERTIK_MAIN)) { return TYPE_CERTIK; }
                    else if (getMainActivity().mBaseChain.equals(AKASH_MAIN)) { return TYPE_AKASH; }
                    else if (getMainActivity().mBaseChain.equals(SECRET_MAIN)) { return TYPE_SECRET; }
                } else if (position == 2) {
                    if (getBaseDao().mUnbondings.size() > 0) { return TYPE_UNDELEGATIONS; }
                    else { return TYPE_PRICE; }

                } else if (position == 3) {
                    if (getBaseDao().mUnbondings.size() > 0) { return TYPE_PRICE; }
                    else { return TYPE_MINT; }

                } else if (position == 4) {
                    if (getBaseDao().mUnbondings.size() > 0) { return TYPE_MINT; }
                    else { return TYPE_GIUDE; }

                } else if (position == 5) {
                    return TYPE_GIUDE;

                }
            }
            return TYPE_ADDRESS;
        }
    }
}