package wannabit.io.cosmostaion.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.Locale;

import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.AccountListActivity;
import wannabit.io.cosmostaion.activities.AppLockSetActivity;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.chains.starname.StarNameWalletConnectActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_Currency_Set;
import wannabit.io.cosmostaion.dialog.Dialog_Starname_WC_Confirm;

import static wannabit.io.cosmostaion.base.BaseChain.AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.BNB_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.CERTIK_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.OK_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_AKASH_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BAND_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BINANCE_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_BINANCE_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_IOV_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_KAVA_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_KAVA_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_OKEX_MAIN;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_OKEX_TEST;

public class MainSettingFragment extends BaseFragment implements View.OnClickListener {

    public final static int SELECT_CURRENCY                 = 9034;
    public final static int SELECT_MARKET                   = 9035;
    public final static int SELECT_STARNAME_WALLET_CONNECT  = 9036;

    private FrameLayout mBtnWallet, mBtnAlaram, mBtnAppLock, mBtnCurrency, mBtnBasePrice,
                        mBtnGuide, mBtnTelegram, mBtnExplore, mBtnHomepage, mBtnStarnameWc,
                        mBtnTerm, mBtnGithub, mBtnVersion;

    private TextView    mTvAppLock, mTvCurrency, mTvBasePrice, mTvVersion;
    private TextView    mTvTitleExplore;

    public static MainSettingFragment newInstance(Bundle bundle) {
        MainSettingFragment fragment = new MainSettingFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_setting, container, false);
        mBtnWallet = rootView.findViewById(R.id.card_wallet);
        mBtnAlaram = rootView.findViewById(R.id.card_alaram);
        mBtnAppLock = rootView.findViewById(R.id.card_applock);
        mBtnCurrency = rootView.findViewById(R.id.card_currency);
        mBtnBasePrice = rootView.findViewById(R.id.card_base_price);
        mBtnGuide = rootView.findViewById(R.id.card_guide);
        mBtnTelegram = rootView.findViewById(R.id.card_telegram);
        mBtnExplore = rootView.findViewById(R.id.card_explore);
        mBtnHomepage = rootView.findViewById(R.id.card_homepage);
        mBtnStarnameWc = rootView.findViewById(R.id.card_starname_wallet_connect);
        mBtnTerm = rootView.findViewById(R.id.card_term);
        mBtnGithub = rootView.findViewById(R.id.card_github);
        mBtnVersion = rootView.findViewById(R.id.card_version);
        mTvAppLock = rootView.findViewById(R.id.applock_text);
        mTvCurrency = rootView.findViewById(R.id.currency_text);
        mTvBasePrice = rootView.findViewById(R.id.base_price_text);
        mTvVersion = rootView.findViewById(R.id.version_text);
        mTvTitleExplore = rootView.findViewById(R.id.title_explore);
        mBtnWallet.setOnClickListener(this);
        mBtnAlaram.setOnClickListener(this);
        mBtnAppLock.setOnClickListener(this);
        mBtnCurrency.setOnClickListener(this);
        mBtnBasePrice.setOnClickListener(this);
        mBtnGuide.setOnClickListener(this);
        mBtnTelegram.setOnClickListener(this);
        mBtnExplore.setOnClickListener(this);
        mBtnHomepage.setOnClickListener(this);
        mBtnStarnameWc.setOnClickListener(this);
        mBtnTerm.setOnClickListener(this);
        mBtnGithub.setOnClickListener(this);
        mBtnVersion.setOnClickListener(this);

        mTvVersion.setText("v" + BuildConfig.VERSION_NAME);

        mBtnAlaram.setVisibility(View.GONE);
        return rootView;
    }


    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        mTvCurrency.setText(getBaseDao().getCurrencyString());
        mTvBasePrice.setText(getBaseDao().getMarketString(getMainActivity()));
        if(getBaseDao().getUsingAppLock()) {
            mTvAppLock.setText(R.string.str_app_applock_enabled);
        } else {
            mTvAppLock.setText(R.string.str_app_applock_diabeld);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnWallet)) {
            startActivity(new Intent(getBaseActivity(), AccountListActivity.class));

        } else if (v.equals(mBtnAlaram)) {
            Toast.makeText(getBaseActivity(), R.string.str_preparing, Toast.LENGTH_SHORT).show();

        } else if (v.equals(mBtnAppLock)) {
            startActivity(new Intent(getBaseActivity(), AppLockSetActivity.class));

        } else if (v.equals(mBtnCurrency)) {
            Dialog_Currency_Set currency_dialog = Dialog_Currency_Set.newInstance(null);
            currency_dialog.setCancelable(true);
            currency_dialog.setTargetFragment(this, SELECT_CURRENCY);
            getFragmentManager().beginTransaction().add(currency_dialog, "dialog").commitNowAllowingStateLoss();
            return;

        } else if (v.equals(mBtnBasePrice)) {
            //NO more coinmarketcap always using coingecko
//            Dialog_Market market = Dialog_Market.newInstance(null);
//            market.setCancelable(true);
//            market.setTargetFragment(this, SELECT_MARKET);
//            getFragmentManager().beginTransaction().add(market, "dialog").commitNowAllowingStateLoss();
//            return;
            return;

        } else if (v.equals(mBtnGuide)) {
            if(Locale.getDefault().getLanguage().toLowerCase().equals("ko")) {
                Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://guide.cosmostation.io/app_wallet_ko.html"));
                startActivity(guideIntent);
            } else {
                Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://guide.cosmostation.io/app_wallet_en.html"));
                startActivity(guideIntent);
            }

        } else if (v.equals(mBtnTelegram)) {
            Intent telegram = new Intent(Intent.ACTION_VIEW , Uri.parse("https://t.me/cosmostation"));
            startActivity(telegram);

        } else if (v.equals(mBtnExplore)) {
            if (getMainActivity().mBaseChain.equals(COSMOS_MAIN)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(EXPLORER_COSMOS_MAIN));
                startActivity(intent);
            } else if (getMainActivity().mBaseChain.equals(IRIS_MAIN)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(EXPLORER_IRIS_MAIN));
                startActivity(intent);
            } else if (getMainActivity().mBaseChain.equals(BNB_MAIN)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(EXPLORER_BINANCE_MAIN));
                startActivity(intent);
            } else if (getMainActivity().mBaseChain.equals(KAVA_MAIN)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(EXPLORER_KAVA_MAIN));
                startActivity(intent);
            } else if (getMainActivity().mBaseChain.equals(IOV_MAIN)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(EXPLORER_IOV_MAIN));
                startActivity(intent);
            } else if (getMainActivity().mBaseChain.equals(BAND_MAIN)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(EXPLORER_BAND_MAIN));
                startActivity(intent);
            } else if (getMainActivity().mBaseChain.equals(BNB_TEST)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(EXPLORER_BINANCE_TEST));
                startActivity(intent);
            } else if (getMainActivity().mBaseChain.equals(OKEX_MAIN)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(EXPLORER_OKEX_MAIN));
                startActivity(intent);
            } else if (getMainActivity().mBaseChain.equals(OK_TEST)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(EXPLORER_OKEX_TEST));
                startActivity(intent);
            } else if (getMainActivity().mBaseChain.equals(KAVA_TEST)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(EXPLORER_KAVA_TEST));
                startActivity(intent);
            } else if (getMainActivity().mBaseChain.equals(CERTIK_MAIN) || getMainActivity().mBaseChain.equals(CERTIK_TEST)) {
                String certikUrl = EXPLORER_CERTIK + "?net=" + getMainActivity().mBaseChain.getChain();
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(certikUrl));
                startActivity(intent);
            } else if (getMainActivity().mBaseChain.equals(AKASH_MAIN)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(EXPLORER_AKASH_MAIN));
                startActivity(intent);
            }

        } else if (v.equals(mBtnHomepage)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cosmostation.io/"));
            startActivity(intent);

        } else if (v.equals(mBtnTerm)) {
            if(Locale.getDefault().getLanguage().toLowerCase().equals("ko")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cosmostation.io/service_ko.html"));
                startActivity(intent);
            }  else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cosmostation.io/service_en.html"));
                startActivity(intent);
            }

        } else if (v.equals(mBtnGithub)) {
            Intent intent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://github.com/cosmostation/cosmostation-mobile"));
            startActivity(intent);

        } else if (v.equals(mBtnVersion)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + getMainActivity().getPackageName()));
            startActivity(intent);

        } else if (v.equals(mBtnStarnameWc)) {
            Dialog_Starname_WC_Confirm wc_dialog = Dialog_Starname_WC_Confirm.newInstance();
            wc_dialog.setCancelable(true);
            wc_dialog.setTargetFragment(this, SELECT_STARNAME_WALLET_CONNECT);
            getFragmentManager().beginTransaction().add(wc_dialog, "dialog").commitNowAllowingStateLoss();
        }

    }

    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if(requestCode == SELECT_CURRENCY && resultCode == Activity.RESULT_OK) {
            getBaseDao().setCurrency(data.getIntExtra("currency", 0));
            mTvCurrency.setText(getBaseDao().getCurrencyString());
            getMainActivity().onPriceTic(BaseChain.getChain(getMainActivity().mAccount.baseChain));

        } else if (requestCode == SELECT_MARKET && resultCode == Activity.RESULT_OK) {
            getBaseDao().setMarket(data.getIntExtra("market", 0));
            mTvBasePrice.setText(getBaseDao().getMarketString(getMainActivity()));
            getMainActivity().onPriceTic(BaseChain.getChain(getMainActivity().mAccount.baseChain));

        } else if (requestCode == SELECT_STARNAME_WALLET_CONNECT && resultCode == Activity.RESULT_OK) {
            new TedPermission(getContext()).setPermissionListener(new PermissionListener() {
                @Override
                public void onPermissionGranted() {
                    IntentIntegrator integrator = IntentIntegrator.forSupportFragment(MainSettingFragment.this);
                    integrator.setOrientationLocked(true);
                    integrator.initiateScan();
                }

                @Override
                public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                    Toast.makeText(getContext(), R.string.error_permission, Toast.LENGTH_SHORT).show();
                }
            })
            .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .setRationaleMessage(getString(R.string.str_permission_qr))
            .check();

        } else {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null && result.getContents() != null && result.getContents().trim().contains("bridge.walletconnect.org")) {
                Intent wcIntent = new Intent(getMainActivity(), StarNameWalletConnectActivity.class);
                wcIntent.putExtra("wcUrl", result.getContents().trim());
                startActivity(wcIntent);

            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

    }
}
