package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_NOTICE_MINTSCAN;
import static wannabit.io.cosmostaion.utils.ThemeUtil.themeColor;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.util.ArrayList;
import java.util.Locale;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.BuildConfig;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.AccountListActivity;
import wannabit.io.cosmostaion.activities.AppLockSetActivity;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.setting.MnemonicListActivity;
import wannabit.io.cosmostaion.activities.setting.PrivateKeyRestoreActivity;
import wannabit.io.cosmostaion.activities.setting.WatchingWalletAddActivity;
import wannabit.io.cosmostaion.activities.txs.starname.StarNameWalletConnectActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.dialog.CurrencySetDialog;
import wannabit.io.cosmostaion.dialog.FilledVerticalButtonAlertDialog;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.req.PushStatusRequest;
import wannabit.io.cosmostaion.network.res.PushStatusResponse;
import wannabit.io.cosmostaion.network.res.ResMoonPaySignature;
import wannabit.io.cosmostaion.utils.PushManager;
import wannabit.io.cosmostaion.utils.ThemeUtil;

public class MainSettingFragment extends BaseFragment implements View.OnClickListener {

    public final static int SELECT_CURRENCY = 9034;
    public final static int SELECT_STARNAME_WALLET_CONNECT = 9035;

    private FrameLayout mBtnWallet, mBtnMnemonic, mBtnImportKey, mBtnWatchAddress, mBtnTheme, mBtnAppLock, mBtnCurrency,
            mBtnExplore, mBtnNotice, mBtnGuide, mBtnTelegram, mBtnHomepage, mBtnStarnameWc,
            mBtnTerm, mBtnGithub, mBtnVersion;

    private TextView mTvAppLock, mTvCurrency, mTvVersion, mTvTheme;

    private SwitchCompat txAlarmSwitch, noticeAlarmSwitch;

    public static MainSettingFragment newInstance() {
        return new MainSettingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);

        themeColor = ThemeUtil.modLoad(getBaseActivity());
        ThemeUtil.applyTheme(themeColor);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_setting, container, false);
        mBtnWallet = rootView.findViewById(R.id.card_wallet);
        mBtnMnemonic = rootView.findViewById(R.id.card_mnemonic);
        mBtnImportKey = rootView.findViewById(R.id.card_key);
        mBtnWatchAddress = rootView.findViewById(R.id.card_watch_address);
        mBtnTheme = rootView.findViewById(R.id.card_theme);
        mBtnAppLock = rootView.findViewById(R.id.card_applock);
        mBtnCurrency = rootView.findViewById(R.id.card_currency);
        mBtnExplore = rootView.findViewById(R.id.card_explore);
        mBtnNotice = rootView.findViewById(R.id.card_notice);
        mBtnGuide = rootView.findViewById(R.id.card_guide);
        mBtnTelegram = rootView.findViewById(R.id.card_telegram);
        mBtnHomepage = rootView.findViewById(R.id.card_homepage);
        mBtnStarnameWc = rootView.findViewById(R.id.card_starname_wallet_connect);
        mBtnTerm = rootView.findViewById(R.id.card_term);
        mBtnGithub = rootView.findViewById(R.id.card_github);
        mBtnVersion = rootView.findViewById(R.id.card_version);
        mTvAppLock = rootView.findViewById(R.id.applock_text);
        mTvCurrency = rootView.findViewById(R.id.currency_text);
        mTvVersion = rootView.findViewById(R.id.version_text);
        mTvTheme = rootView.findViewById(R.id.theme_text);
        txAlarmSwitch = rootView.findViewById(R.id.switch_tx_alaram);
        noticeAlarmSwitch = rootView.findViewById(R.id.switch_notice_alaram);
        txAlarmSwitch.setOnCheckedChangeListener(switchListener());
        noticeAlarmSwitch.setOnCheckedChangeListener(switchListener());

        mBtnMnemonic.setOnClickListener(this);
        mBtnWallet.setOnClickListener(this);
        mBtnImportKey.setOnClickListener(this);
        mBtnWatchAddress.setOnClickListener(this);
        mBtnTheme.setOnClickListener(this);
        mBtnAppLock.setOnClickListener(this);
        mBtnCurrency.setOnClickListener(this);
        mBtnExplore.setOnClickListener(this);
        mBtnNotice.setOnClickListener(this);
        mBtnGuide.setOnClickListener(this);
        mBtnTelegram.setOnClickListener(this);
        mBtnHomepage.setOnClickListener(this);
        mBtnStarnameWc.setOnClickListener(this);
        mBtnTerm.setOnClickListener(this);
        mBtnGithub.setOnClickListener(this);
        mBtnVersion.setOnClickListener(this);

        mTvVersion.setText("v" + BuildConfig.VERSION_NAME);
        return rootView;

    }

    private CompoundButton.OnCheckedChangeListener switchListener() {
        return (view, checked) -> syncPushStatus();
    }

    private void syncPushStatus() {
        if (!getBaseDao().getNoticePushEnable() && !getBaseDao().getTxPushEnable()) {
            PushManager.syncAddresses(requireContext(), getBaseDao(), getBaseDao().getFCMToken());
        }

        PushManager.updateStatus(requireContext(), getBaseDao(), txAlarmSwitch.isChecked(), noticeAlarmSwitch.isChecked(), getBaseDao().getFCMToken());
    }

    @Override
    public void onResume() {
        super.onResume();
        if (getBaseDao().getUsingAppLock()) {
            mTvAppLock.setText(R.string.str_app_applock_enabled);
        } else {
            mTvAppLock.setText(R.string.str_app_applock_diabeld);
        }

        if (themeColor.equals("light")) {
            mTvTheme.setText(R.string.str_theme_light);
        } else if (themeColor.equals("dark")) {
            mTvTheme.setText(R.string.str_theme_dark);
        } else {
            mTvTheme.setText(R.string.str_theme_system);
        }

        loadPushStatus();
    }

    private void loadPushStatus() {
        ApiClient.getCosmostationOld(requireContext()).getPushStatus(getBaseDao().getFCMToken()).enqueue(new Callback<PushStatusResponse>() {
            @Override
            public void onResponse(Call<PushStatusResponse> call, Response<PushStatusResponse> response) {
                if (response.isSuccessful()) {
                    noticeAlarmSwitch.setChecked(response.body().sub_notice);
                    txAlarmSwitch.setChecked(response.body().sub_tx);
                    getBaseDao().setTxPushEnable(response.body().sub_tx);
                    getBaseDao().setNoticePushEnable(response.body().sub_notice);
                }
            }

            @Override
            public void onFailure(Call<PushStatusResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onRefreshTab() {
        if (!isAdded()) return;
        mTvCurrency.setText(getBaseDao().getCurrencyString());
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnWallet)) {
            startActivity(new Intent(getBaseActivity(), AccountListActivity.class));

        } else if (v.equals(mBtnMnemonic)) {
            startActivity(new Intent(getBaseActivity(), MnemonicListActivity.class));

        } else if (v.equals(mBtnImportKey)) {
            startActivity(new Intent(getBaseActivity(), PrivateKeyRestoreActivity.class));

        } else if (v.equals(mBtnWatchAddress)) {
            startActivity(new Intent(getBaseActivity(), WatchingWalletAddActivity.class));

        } else if (v.equals(mBtnTheme)) {
            FilledVerticalButtonAlertDialog.showTripleButton(getBaseActivity(), null, null,
                    getString(R.string.str_theme_system), view -> {
                        themeColor = ThemeUtil.DEFAULT_MODE;
                        ThemeUtil.applyTheme(themeColor);
                        mTvTheme.setText(R.string.str_theme_system);
                        ThemeUtil.modSave(getBaseActivity(), themeColor);
                    }, null,
                    getString(R.string.str_theme_light), view -> {
                        themeColor = ThemeUtil.LIGHT_MODE;
                        ThemeUtil.applyTheme(themeColor);
                        mTvTheme.setText(R.string.str_theme_light);
                        ThemeUtil.modSave(getBaseActivity(), themeColor);
                    }, null,
                    getString(R.string.str_theme_dark), view -> {
                        themeColor = ThemeUtil.DARK_MODE;
                        ThemeUtil.applyTheme(themeColor);
                        mTvTheme.setText(R.string.str_theme_dark);
                        ThemeUtil.modSave(getBaseActivity(), themeColor);
                    }, null);

        } else if (v.equals(mBtnAppLock)) {
            startActivity(new Intent(getBaseActivity(), AppLockSetActivity.class));

        } else if (v.equals(mBtnCurrency)) {
            CurrencySetDialog currency_dialog = CurrencySetDialog.newInstance(null);
            currency_dialog.setCancelable(true);
            currency_dialog.setTargetFragment(this, SELECT_CURRENCY);
            getFragmentManager().beginTransaction().add(currency_dialog, "dialog").commitNowAllowingStateLoss();
            return;

        } else if (v.equals(mBtnExplore)) {
            String url = getMainActivity().mChainConfig.explorerUrl();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);

        } else if (v.equals(mBtnNotice)) {
            String url = EXPLORER_NOTICE_MINTSCAN + ChainFactory.getChain(getMainActivity().mBaseChain).chainName();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);

        } else if (v.equals(mBtnGuide)) {
            if (Locale.getDefault().getLanguage().toLowerCase().equals("ko")) {
                Intent guideIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://guide.cosmostation.io/app_wallet_ko.html"));
                startActivity(guideIntent);
            } else {
                Intent guideIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://guide.cosmostation.io/app_wallet_en.html"));
                startActivity(guideIntent);
            }

        } else if (v.equals(mBtnTelegram)) {
            Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/cosmostation"));
            startActivity(telegram);

        } else if (v.equals(mBtnHomepage)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cosmostation.io/"));
            startActivity(intent);

        } else if (v.equals(mBtnTerm)) {
            if (Locale.getDefault().getLanguage().toLowerCase().equals("ko")) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cosmostation.io/service_kr.html"));
                startActivity(intent);
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cosmostation.io/service_en.html"));
                startActivity(intent);
            }

        } else if (v.equals(mBtnGithub)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/cosmostation/cosmostation-android"));
            startActivity(intent);

        } else if (v.equals(mBtnVersion)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + getMainActivity().getPackageName()));
            startActivity(intent);

        } else if (v.equals(mBtnStarnameWc)) {
            AlertDialogUtils.showDoubleButtonDialog(getMainActivity(), getString(R.string.str_starname_walletconnect_alert_title), getString(R.string.str_starname_walletconnect_alert_msg),
                    getString(R.string.str_cancel), null,
                    getString(R.string.str_continue), view -> {
                        Intent resultIntent = new Intent();
                        onActivityResult(SELECT_STARNAME_WALLET_CONNECT, Activity.RESULT_OK, resultIntent);
                    });
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

        }
        if (requestCode == SELECT_STARNAME_WALLET_CONNECT && resultCode == Activity.RESULT_OK) {
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
