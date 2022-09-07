package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_NOTICE_MINTSCAN;
import static wannabit.io.cosmostaion.utils.ThemeUtil.themeColor;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CompoundButton;
import android.widget.FrameLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.widget.SwitchCompat;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;

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
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.ManageWalletConnectActivity;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.activities.PasswordSetActivity;
import wannabit.io.cosmostaion.activities.setting.MnemonicListActivity;
import wannabit.io.cosmostaion.activities.setting.PrivateKeyRestoreActivity;
import wannabit.io.cosmostaion.activities.setting.WatchingWalletAddActivity;
import wannabit.io.cosmostaion.activities.txs.starname.StarNameWalletConnectActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.dialog.CurrencySetDialog;
import wannabit.io.cosmostaion.dialog.FilledVerticalButtonAlertDialog;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.PushStatusResponse;
import wannabit.io.cosmostaion.utils.PushManager;
import wannabit.io.cosmostaion.utils.ThemeUtil;

public class MainSettingFragment extends BaseFragment implements View.OnClickListener {

    public final static int SELECT_CURRENCY = 9034;
    public final static int SELECT_STARNAME_WALLET_CONNECT = 9035;

    public final static int SELECT_CHECK_FOR_APP_LOCK = 1;
    public final static int SELECT_CHECK_FOR_AUTO_PASS = 2;

    private FrameLayout mBtnWallet, mBtnMnemonic, mBtnImportKey, mBtnWatchAddress, mBtnTheme, mBtnAppLock, mBtnBio, mBtnAutoPass, mBtnCurrency,
            mBtnExplore, mBtnNotice, mBtnHomepage, mBtnBlog, mBtnTelegram, mBtnStarnameWc,
            mBtnTerm, mBtnGithub, mBtnVersion, mBtnWalletConnect;

    private TextView mTvBio, mTvAutoPassTime, mTvCurrency, mTvVersion, mTvTheme;

    private SwitchCompat mSwitchUsingAppLock, mSwitchUsingUsingBio;

    private int mCheckMode = -1;

    private SwitchCompat alarmSwitch;

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
        mBtnBio = rootView.findViewById(R.id.card_bio);
        mBtnAutoPass = rootView.findViewById(R.id.card_auto_pass);
        mBtnCurrency = rootView.findViewById(R.id.card_currency);
        mBtnExplore = rootView.findViewById(R.id.card_explore);
        mBtnNotice = rootView.findViewById(R.id.card_notice);
        mBtnHomepage = rootView.findViewById(R.id.card_homepage);
        mBtnBlog = rootView.findViewById(R.id.card_blog);
        mBtnTelegram = rootView.findViewById(R.id.card_telegram);
        mBtnStarnameWc = rootView.findViewById(R.id.card_starname_wallet_connect);
        mBtnTerm = rootView.findViewById(R.id.card_term);
        mBtnGithub = rootView.findViewById(R.id.card_github);
        mBtnVersion = rootView.findViewById(R.id.card_version);
        mBtnWalletConnect = rootView.findViewById(R.id.card_wallet_connect);
        mTvCurrency = rootView.findViewById(R.id.currency_text);
        mTvVersion = rootView.findViewById(R.id.version_text);
        mTvTheme = rootView.findViewById(R.id.theme_text);
        alarmSwitch = rootView.findViewById(R.id.switch_alaram);
        alarmSwitch.setOnCheckedChangeListener(switchListener());

        mSwitchUsingAppLock = rootView.findViewById(R.id.switch_using_applock);
        mTvBio = rootView.findViewById(R.id.bio_title);
        mSwitchUsingUsingBio = rootView.findViewById(R.id.switch_using_bio);
        mTvAutoPassTime = rootView.findViewById(R.id.auto_pass_time);

        mBtnMnemonic.setOnClickListener(this);
        mBtnWallet.setOnClickListener(this);
        mBtnImportKey.setOnClickListener(this);
        mBtnWatchAddress.setOnClickListener(this);
        mBtnTheme.setOnClickListener(this);
        mBtnAppLock.setOnClickListener(this);
        mBtnBio.setOnClickListener(this);
        mBtnAutoPass.setOnClickListener(this);
        mBtnCurrency.setOnClickListener(this);
        mBtnExplore.setOnClickListener(this);
        mBtnNotice.setOnClickListener(this);
        mBtnWalletConnect.setOnClickListener(this);
        mBtnHomepage.setOnClickListener(this);
        mBtnBlog.setOnClickListener(this);
        mBtnTelegram.setOnClickListener(this);
        mBtnStarnameWc.setOnClickListener(this);
        mBtnTerm.setOnClickListener(this);
        mBtnGithub.setOnClickListener(this);
        mBtnVersion.setOnClickListener(this);

        mTvVersion.setText("v" + BuildConfig.VERSION_NAME);
        onUpdateView();
        return rootView;

    }

    private CompoundButton.OnCheckedChangeListener switchListener() {
        return (view, checked) -> syncPushStatus();
    }

    private void syncPushStatus() {
        if (!getBaseDao().getAlarmEnable()) {
            PushManager.syncAddresses(requireContext(), getBaseDao(), getBaseDao().getFCMToken());
        }

        PushManager.updateStatus(requireContext(), getBaseDao(), alarmSwitch.isChecked(), getBaseDao().getFCMToken());
    }

    @Override
    public void onResume() {
        super.onResume();
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
                    alarmSwitch.setChecked(response.body().subscribe);
                    getBaseDao().setAlarmEnable(response.body().subscribe);
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
        onUpdateAutoPass();
    }

    private void onUpdateView() {
        mSwitchUsingAppLock.setChecked(getBaseDao().getUsingAppLock());
        mSwitchUsingUsingBio.setChecked(getBaseDao().getUsingFingerPrint());

        FingerprintManagerCompat mFingerprintManagerCompat = FingerprintManagerCompat.from(getActivity());
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) && mFingerprintManagerCompat.isHardwareDetected() && mFingerprintManagerCompat.hasEnrolledFingerprints()) {
            mTvBio.setText(getString(R.string.str_using_fingerprints));
        } else {
            mTvBio.setText("");
        }
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
            onClickAppLock();

        } else if (v.equals(mBtnBio)) {
            getBaseDao().setUsingFingerPrint(!getBaseDao().getUsingFingerPrint());
            onUpdateView();

        } else if (v.equals(mBtnAutoPass)) {
            onClickAutoPass();

        } else if (v.equals(mBtnCurrency) && !getMainActivity().isFinishing()) {
            CurrencySetDialog dialog = CurrencySetDialog.newInstance(null);
            dialog.setTargetFragment(this, SELECT_CURRENCY);
            dialog.show(getActivity().getSupportFragmentManager(), "dialog");

        } else if (v.equals(mBtnExplore)) {
            String url = getMainActivity().mChainConfig.explorerUrl();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);

        } else if (v.equals(mBtnWalletConnect)) {
            startActivity(new Intent(getContext(), ManageWalletConnectActivity.class));

        } else if (v.equals(mBtnNotice)) {
            String url = EXPLORER_NOTICE_MINTSCAN + ChainFactory.getChain(getMainActivity().mBaseChain).chainName();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);

        } else if (v.equals(mBtnHomepage)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.cosmostation.io/"));
            startActivity(intent);

        } else if (v.equals(mBtnBlog)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://medium.com/cosmostation"));
            startActivity(intent);

        } else if (v.equals(mBtnTelegram)) {
            Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse("https://t.me/cosmostation"));
            startActivity(telegram);

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
            CommonAlertDialog.showDoubleButton(getMainActivity(), getString(R.string.str_starname_walletconnect_alert_title), getString(R.string.str_starname_walletconnect_alert_msg),
                    getString(R.string.str_cancel), null,
                    getString(R.string.str_continue), view -> {
                        Intent resultIntent = new Intent();
                        onActivityResult(SELECT_STARNAME_WALLET_CONNECT, Activity.RESULT_OK, resultIntent);
                    });
        }
    }

    private void onClickAppLock() {
        mCheckMode = SELECT_CHECK_FOR_APP_LOCK;

        if (getBaseDao().getUsingAppLock()) {
            Intent intent = new Intent(getActivity(), PasswordCheckActivity.class);
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_SIMPLE_CHECK);
            startActivityForResult(intent, BaseConstant.CONST_PW_SIMPLE_CHECK);
            getActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);

        } else {
            if (getBaseDao().onHasPassword()) {
                getBaseDao().setUsingAppLock(true);
                onUpdateView();

            } else {
                Intent intent = new Intent(getActivity(), PasswordSetActivity.class);
                startActivityForResult(intent, BaseConstant.CONST_PW_INIT);
                getActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
            }
        }
    }

    private void onClickAutoPass() {
        mCheckMode = SELECT_CHECK_FOR_AUTO_PASS;

        if (!getBaseDao().onHasPassword()) {
            Intent intent = new Intent(getActivity(), PasswordSetActivity.class);
            startActivityForResult(intent, BaseConstant.CONST_PW_INIT);

        } else {
            Intent intent = new Intent(getActivity(), PasswordCheckActivity.class);
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_SIMPLE_CHECK);
            startActivityForResult(intent, BaseConstant.CONST_PW_SIMPLE_CHECK);
        }
        getActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
    }

    private void onShowAutoPassDialog() {
        FilledVerticalButtonAlertDialog.showQuadrupleButton(getBaseActivity(), null, getString(R.string.str_app_auto_pass_msg),
                getString(R.string.str_app_auto_pass_5m), view -> onSetAutoPass(1), null,
                getString(R.string.str_app_auto_pass_10m), view -> onSetAutoPass(2), null,
                getString(R.string.str_app_auto_pass_30m), view -> onSetAutoPass(3), null,
                getString(R.string.str_app_auto_pass_never), view -> onSetAutoPass(0), null);
    }

    private void onSetAutoPass(int value) {
        if (getBaseDao().getUsingAutoPassTime() != value) {
            getBaseDao().setUsingAutoPassTime(value);
            onUpdateAutoPass();
        }
    }

    private void onUpdateAutoPass() {
        if (!getBaseDao().isAutoPass()) {
            onSetAutoPass(0);
        }
        mTvAutoPassTime.setText(getBaseDao().getAutoPass(getActivity()));
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

        } else if (requestCode == BaseConstant.CONST_PW_SIMPLE_CHECK && resultCode == Activity.RESULT_OK) {
            if (mCheckMode == SELECT_CHECK_FOR_APP_LOCK) {
                getBaseDao().setUsingAppLock(false);
                onUpdateView();

            } else {
                new Handler(Looper.getMainLooper()).postDelayed(() -> onShowAutoPassDialog(), 300);
            }

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
