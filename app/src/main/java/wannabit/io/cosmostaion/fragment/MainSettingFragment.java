package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.fulldive.wallet.presentation.chains.choicenet.ChoiceNetDialogFragment;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;

import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.AccountListActivity;
import wannabit.io.cosmostaion.activities.AppLockSetActivity;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.chains.starname.StarNameWalletConnectActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.IRefreshTabListener;
import wannabit.io.cosmostaion.dialog.Dialog_Currency_Set;

public class MainSettingFragment extends BaseFragment implements View.OnClickListener, IRefreshTabListener {

    public final static int SELECT_CURRENCY = 9034;
    public final static int SELECT_MARKET = 9035;
    public final static int SELECT_STARNAME_WALLET_CONNECT = 9036;

    private FrameLayout mBtnAddWallet;
    private FrameLayout mBtnWallet;
    private FrameLayout mBtnAlaram;
    private FrameLayout mBtnAppLock;
    private FrameLayout mBtnCurrency;
    private FrameLayout mBtnGuide;
    private FrameLayout mBtnDiscord;
    private FrameLayout mBtnTerm;
    private FrameLayout mBtnGithub;
    private FrameLayout mBtnVersion;

    private TextView mTvAppLock, mTvCurrency, mTvBasePrice, mTvVersion;

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
        if (getMainActivity().baseChain.equals(COSMOS_MAIN)) {
            if (getMainActivity().account.pushAlarm) {
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
                getMainActivity().onClickSwitchWallet();
                break;
            case R.id.menu_explorer:
                getMainActivity().onExplorerView();
                break;
            case R.id.menu_notification_off:
                getMainActivity().onUpdateUserAlarm(getMainActivity().account, true);
                break;
            case R.id.menu_notification_on:
                getMainActivity().onUpdateUserAlarm(getMainActivity().account, false);
                break;
        }
        return super.onOptionsItemSelected(item);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_setting, container, false);
        mBtnAddWallet = rootView.findViewById(R.id.add_wallet);
        mBtnWallet = rootView.findViewById(R.id.card_wallet);
        mBtnAlaram = rootView.findViewById(R.id.card_alaram);
        mBtnAppLock = rootView.findViewById(R.id.card_applock);
        mBtnCurrency = rootView.findViewById(R.id.card_currency);
        FrameLayout basePriceView = rootView.findViewById(R.id.card_base_price);
        mBtnDiscord = rootView.findViewById(R.id.card_discord);
        mBtnGuide = rootView.findViewById(R.id.card_guide);
//        mBtnTelegram = rootView.findViewById(R.id.card_telegram);
//        mBtnExplore = rootView.findViewById(R.id.card_explore);
//        mBtnHomepage = rootView.findViewById(R.id.card_homepage);
//        mBtnStarnameWc = rootView.findViewById(R.id.card_starname_wallet_connect);
        mBtnTerm = rootView.findViewById(R.id.card_term);
        mBtnGithub = rootView.findViewById(R.id.card_github);
        mBtnVersion = rootView.findViewById(R.id.card_version);
        mTvAppLock = rootView.findViewById(R.id.applock_text);
        mTvCurrency = rootView.findViewById(R.id.currency_text);
        mTvBasePrice = rootView.findViewById(R.id.base_price_text);
        mTvVersion = rootView.findViewById(R.id.version_text);

        mBtnAddWallet.setOnClickListener(this);
        mBtnWallet.setOnClickListener(this);
        mBtnAlaram.setOnClickListener(this);
        mBtnAppLock.setOnClickListener(this);
        mBtnCurrency.setOnClickListener(this);
        basePriceView.setOnClickListener(this);
        mBtnDiscord.setOnClickListener(this);
        mBtnGuide.setOnClickListener(this);
//        mBtnTelegram.setOnClickListener(this);
//        mBtnExplore.setOnClickListener(this);
//        mBtnHomepage.setOnClickListener(this);
//        mBtnStarnameWc.setOnClickListener(this);
        mBtnTerm.setOnClickListener(this);
        mBtnGithub.setOnClickListener(this);
        mBtnVersion.setOnClickListener(this);

        mTvVersion.setText("v" + BuildConfig.VERSION_NAME);
        if (getBaseDao().getUsingAppLock()) {
            mTvAppLock.setText(R.string.str_app_applock_enabled);
        } else {
            mTvAppLock.setText(R.string.str_app_applock_diabeld);
        }

        mBtnAlaram.setVisibility(View.GONE);
        return rootView;
    }


    @Override
    public void onRefreshTab() {
        if (!isAdded()) return;
        mTvCurrency.setText(getBaseDao().getCurrencyString());
        mTvBasePrice.setText(getString(R.string.str_coingecko));
        if (getBaseDao().getUsingAppLock()) {
            mTvAppLock.setText(R.string.str_app_applock_enabled);
        } else {
            mTvAppLock.setText(R.string.str_app_applock_diabeld);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnAddWallet)) {
            showDialog(ChoiceNetDialogFragment.Companion.newInstance(true, ""));
        } else if (v.equals(mBtnWallet)) {
            startActivity(new Intent(getBaseActivity(), AccountListActivity.class));

        } else if (v.equals(mBtnAlaram)) {
            Toast.makeText(getBaseActivity(), R.string.str_preparing, Toast.LENGTH_SHORT).show();

        } else if (v.equals(mBtnAppLock)) {
            startActivity(new Intent(getBaseActivity(), AppLockSetActivity.class));

        } else if (v.equals(mBtnCurrency)) {
            Dialog_Currency_Set currency_dialog = Dialog_Currency_Set.newInstance(null);
            currency_dialog.setTargetFragment(this, SELECT_CURRENCY);
            showDialog(currency_dialog);
            return;

//        } else if (v.equals(mBtnBasePrice)) {
//            //NO more coinmarketcap always using coingecko
////            Dialog_Market market = Dialog_Market.newInstance(null);
////            market.setCancelable(true);
////            market.setTargetFragment(this, SELECT_MARKET);
////            getFragmentManager().beginTransaction().add(market, "dialog").commitNowAllowingStateLoss();
////            return;
//            return;
//
        } else if (v.equals(mBtnGuide)) {
            Intent guideIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.fulldive.com/faq"));
            startActivity(guideIntent);
//        } else if (v.equals(mBtnTelegram)) {
//            Intent telegram = new Intent(Intent.ACTION_VIEW , Uri.parse("https://t.me/cosmostation"));
//            startActivity(telegram);
//
//        } else if (v.equals(mBtnExplore)) {
//            String url  = WUtil.getExplorer(getMainActivity().mBaseChain);
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
//            startActivity(intent);
//
//        } else if (v.equals(mBtnHomepage)) {
//            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cosmostation.io/"));
//            startActivity(intent);
//
        } else if (v.equals(mBtnDiscord)) {
            Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://discord.gg/BW2unf5s8X"));
            startActivity(telegram);

        } else if (v.equals(mBtnTerm)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.fulldive.com/terms-of-use"));
            startActivity(intent);

        } else if (v.equals(mBtnGithub)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/imversed/imversed-wallet-android"));
            startActivity(intent);

        } else if (v.equals(mBtnVersion)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + getMainActivity().getPackageName()));
            startActivity(intent);

//        } else if (v.equals(mBtnStarnameWc)) {
//            Dialog_Starname_WC_Confirm wc_dialog = Dialog_Starname_WC_Confirm.newInstance();
//            wc_dialog.setCancelable(true);
//            wc_dialog.setTargetFragment(this, SELECT_STARNAME_WALLET_CONNECT);
//            getFragmentManager().beginTransaction().add(wc_dialog, "dialog").commitNowAllowingStateLoss();
        }

    }

    public MainActivity getMainActivity() {
        return (MainActivity) getBaseActivity();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_CURRENCY && resultCode == Activity.RESULT_OK) {
            getBaseDao().setCurrency(data.getIntExtra("currency", 0));
            mTvCurrency.setText(getBaseDao().getCurrencyString());

        } else if (requestCode == SELECT_MARKET && resultCode == Activity.RESULT_OK) {

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
