package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletAkashHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletAltheaHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletAxelarHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletBandHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletBinanceHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletBitcannaHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletCertikHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletCosmosHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletCrytoHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletEmoneyHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletFetchHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletGuideHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletIrisHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletJunoHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletKavaHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletKavaIncentiveHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletKiHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletMediHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletMintHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletOkexHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletOsmosisHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletPersisHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletPriceHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletRegenHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletRizonHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletSecretHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletSentinelHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletSifHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletSifIncentiveHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletStarnameHolder;
import wannabit.io.cosmostaion.widget.mainWallet.WalletUmeeHolder;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.ALTHEA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.AXELAR_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BITCANNA_MAIN;
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
import static wannabit.io.cosmostaion.base.BaseChain.JUNO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.MEDI_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OSMOSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.PERSIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.REGEN_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.RIZON_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.SECRET_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SENTINEL_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.SIF_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.UMEE_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;

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
                mMainWalletAdapter.notifyDataSetChanged();
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
//        getMainActivity().onUpdateAccountListAdapter();
    }


    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }

    private class MainWalletAdapter extends RecyclerView.Adapter<BaseHolder> {
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
        private static final int TYPE_PERSIS            = 11;
        private static final int TYPE_SENTINEL          = 12;
        private static final int TYPE_FETCH             = 13;
        private static final int TYPE_CRYPTO            = 14;
        private static final int TYPE_SIF               = 15;
        private static final int TYPE_KI                = 16;
        private static final int TYPE_RIZON             = 17;
        private static final int TYPE_MEDI              = 18;
        private static final int TYPE_ALTHEA            = 19;
        private static final int TYPE_OSMOSIS           = 20;
        private static final int TYPE_UMEE              = 21;
        private static final int TYPE_EMONEY            = 22;
        private static final int TYPE_AXELAR            = 23;
        private static final int TYPE_JUNO              = 24;
        private static final int TYPE_REGEN             = 25;
        private static final int TYPE_BITCANNA          = 26;

        private static final int TYPE_KAVA_INCENTIVE    = 40;
        private static final int TYPE_SIF_INCENTIVE     = 50;
        private static final int TYPE_PRICE             = 80;
        private static final int TYPE_MINT              = 81;
        private static final int TYPE_GIUDE             = 82;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            if (viewType == TYPE_COSMOS) {
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

            } else if (viewType == TYPE_PERSIS) {
                return new WalletPersisHolder(getLayoutInflater().inflate(R.layout.item_wallet_persis, viewGroup, false));

            } else if (viewType == TYPE_SENTINEL) {
                return new WalletSentinelHolder(getLayoutInflater().inflate(R.layout.item_wallet_sentinel, viewGroup, false));

            } else if (viewType == TYPE_FETCH) {
                return new WalletFetchHolder(getLayoutInflater().inflate(R.layout.item_wallet_fetch, viewGroup, false));

            } else if (viewType == TYPE_CRYPTO) {
                return new WalletCrytoHolder(getLayoutInflater().inflate(R.layout.item_wallet_cryto, viewGroup, false));

            } else if (viewType == TYPE_SIF) {
                return new WalletSifHolder(getLayoutInflater().inflate(R.layout.item_wallet_sif, viewGroup, false));

            } else if (viewType == TYPE_KI) {
                return new WalletKiHolder(getLayoutInflater().inflate(R.layout.item_wallet_ki, viewGroup, false));

            } else if (viewType == TYPE_RIZON) {
                return new WalletRizonHolder(getLayoutInflater().inflate(R.layout.item_wallet_rizon, viewGroup, false));

            } else if (viewType == TYPE_MEDI) {
                return new WalletMediHolder(getLayoutInflater().inflate(R.layout.item_wallet_medi, viewGroup, false));

            } else if (viewType == TYPE_ALTHEA) {
                return new WalletAltheaHolder(getLayoutInflater().inflate(R.layout.item_wallet_althea, viewGroup, false));

            } else if (viewType == TYPE_OSMOSIS) {
                return new WalletOsmosisHolder(getLayoutInflater().inflate(R.layout.item_wallet_osmosis, viewGroup, false));

            } else if (viewType == TYPE_UMEE) {
                return new WalletUmeeHolder(getLayoutInflater().inflate(R.layout.item_wallet_umee, viewGroup, false));

            } else if (viewType == TYPE_EMONEY) {
                return new WalletEmoneyHolder(getLayoutInflater().inflate(R.layout.item_wallet_emoney, viewGroup, false));

            } else if (viewType == TYPE_AXELAR) {
                return new WalletAxelarHolder(getLayoutInflater().inflate(R.layout.item_wallet_axelar, viewGroup, false));

            } else if (viewType == TYPE_JUNO) {
                return new WalletJunoHolder(getLayoutInflater().inflate(R.layout.item_wallet_juno, viewGroup, false));

            } else if (viewType == TYPE_REGEN) {
                return new WalletRegenHolder(getLayoutInflater().inflate(R.layout.item_wallet_regen, viewGroup, false));

            } else if (viewType == TYPE_BITCANNA) {
                return new WalletBitcannaHolder(getLayoutInflater().inflate(R.layout.item_wallet_bitcanna, viewGroup, false));

            }


            else if (viewType == TYPE_PRICE) {
                return new WalletPriceHolder(getLayoutInflater().inflate(R.layout.item_wallet_price, viewGroup, false));

            } else if (viewType == TYPE_MINT) {
                return new WalletMintHolder(getLayoutInflater().inflate(R.layout.item_wallet_mint, viewGroup, false));

            } else if (viewType == TYPE_GIUDE) {
                return new WalletGuideHolder(getLayoutInflater().inflate(R.layout.item_wallet_guide, viewGroup, false));

            } else if (viewType == TYPE_KAVA_INCENTIVE) {
                return new WalletKavaIncentiveHolder(getLayoutInflater().inflate(R.layout.item_wallet_kavaincentive, viewGroup, false));

            } else if (viewType == TYPE_SIF_INCENTIVE) {
                return new WalletSifIncentiveHolder(getLayoutInflater().inflate(R.layout.item_wallet_sifincentive, viewGroup, false));

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
            if (getMainActivity().mBaseChain == null) { return 0; }
            if (getMainActivity().mBaseChain.equals(KAVA_MAIN) || getMainActivity().mBaseChain.equals(SIF_MAIN)) {
                return 5;
            } else if (isGRPC(getMainActivity().mBaseChain )) {
                return 4;
            } else {
                if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain .equals(BNB_TEST) ||
                        getMainActivity().mBaseChain.equals(OKEX_MAIN) || getMainActivity().mBaseChain .equals(OK_TEST)) {
                    return 3;
                } else {
                    return 4;
                }
            }
        }

        @Override
        public int getItemViewType(int position) {
            if (getMainActivity().mBaseChain.equals(SIF_MAIN)) {
                if (position == 0) {
                    return TYPE_SIF;
                } else if (position == 1) {
                    return TYPE_SIF_INCENTIVE;
                } else if (position == 2) {
                    return TYPE_PRICE;
                } else if (position == 3) {
                    return TYPE_MINT;
                } else if (position == 4) {
                    return TYPE_GIUDE;
                }

            } else if (isGRPC(getMainActivity().mBaseChain)) {
                if (position == 0) {
                    if (getMainActivity().mBaseChain.equals(COSMOS_MAIN) || getMainActivity().mBaseChain.equals(COSMOS_TEST)) { return TYPE_COSMOS; }
                    else if (getMainActivity().mBaseChain.equals(IRIS_MAIN) || getMainActivity().mBaseChain.equals(IRIS_TEST)) { return TYPE_IRIS; }
                    else if (getMainActivity().mBaseChain.equals(AKASH_MAIN)) { return TYPE_AKASH; }
                    else if (getMainActivity().mBaseChain.equals(SENTINEL_MAIN)) { return TYPE_SENTINEL; }
                    else if (getMainActivity().mBaseChain.equals(PERSIS_MAIN)) { return TYPE_PERSIS; }
                    else if (getMainActivity().mBaseChain.equals(CRYPTO_MAIN)) { return TYPE_CRYPTO; }
                    else if (getMainActivity().mBaseChain.equals(OSMOSIS_MAIN)) { return TYPE_OSMOSIS; }
                    else if (getMainActivity().mBaseChain.equals(IOV_MAIN)) { return TYPE_STARNAME; }
                    else if (getMainActivity().mBaseChain.equals(MEDI_MAIN) || getMainActivity().mBaseChain.equals(MEDI_TEST)) { return TYPE_MEDI; }
                    else if (getMainActivity().mBaseChain.equals(CERTIK_MAIN)) { return TYPE_CERTIK; }
                    else if (getMainActivity().mBaseChain.equals(EMONEY_MAIN)) { return TYPE_EMONEY; }
                    else if (getMainActivity().mBaseChain.equals(FETCHAI_MAIN)) { return TYPE_FETCH; }
                    else if (getMainActivity().mBaseChain.equals(BAND_MAIN)) { return TYPE_BAND; }
                    else if (getMainActivity().mBaseChain.equals(RIZON_MAIN) || getMainActivity().mBaseChain.equals(RIZON_TEST)) { return TYPE_RIZON; }
                    else if (getMainActivity().mBaseChain.equals(JUNO_MAIN)) { return TYPE_JUNO; }
                    else if (getMainActivity().mBaseChain.equals(REGEN_MAIN)) { return TYPE_REGEN; }
                    else if (getMainActivity().mBaseChain.equals(BITCANNA_MAIN)) { return TYPE_BITCANNA; }
                    else if (getMainActivity().mBaseChain.equals(ALTHEA_TEST)) { return TYPE_ALTHEA; }
                    else if (getMainActivity().mBaseChain.equals(UMEE_TEST)) { return TYPE_UMEE; }
                    else if (getMainActivity().mBaseChain.equals(AXELAR_TEST)) { return TYPE_AXELAR; }
                } else if (position == 1) {
                    return TYPE_PRICE;
                } else if (position == 2) {
                    return TYPE_MINT;
                } else if (position == 3) {
                    return TYPE_GIUDE;
                }


            } else if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST) ||
                    getMainActivity().mBaseChain.equals(OKEX_MAIN) || getMainActivity().mBaseChain.equals(OK_TEST)) {
                if (position == 0) {
                    if (getMainActivity().mBaseChain.equals(BNB_MAIN) || getMainActivity().mBaseChain.equals(BNB_TEST)) { return TYPE_BINANCE; }
                    else if (getMainActivity().mBaseChain.equals(OKEX_MAIN) || getMainActivity().mBaseChain.equals(OK_TEST)) { return TYPE_OKEX; }
                } else if (position == 1) {
                    return TYPE_PRICE;
                } else if (position == 2) {
                    return TYPE_GIUDE;
                }

            } else if (getMainActivity().mBaseChain.equals(KAVA_MAIN)) {
                if (position == 0) {
                    return TYPE_KAVA;
                } else if (position == 1) {
                    return TYPE_KAVA_INCENTIVE;
                } else if (position == 2) {
                    return TYPE_PRICE;
                } else if (position == 3) {
                    return TYPE_MINT;
                } else if (position == 4) {
                    return TYPE_GIUDE;
                }

            } else {
                if (position == 0) {
                    if (getMainActivity().mBaseChain.equals(SECRET_MAIN)) { return TYPE_SECRET; }
                    else if (getMainActivity().mBaseChain.equals(KI_MAIN)) { return TYPE_KI; }
                } else if (position == 1) {
                    return TYPE_PRICE;

                } else if (position == 2) {
                    return TYPE_MINT;

                } else if (position == 3) {
                    return TYPE_GIUDE;

                }
            }
            return 0;
        }
    }
}