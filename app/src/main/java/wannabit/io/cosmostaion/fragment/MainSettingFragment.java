package wannabit.io.cosmostaion.fragment;

import static wannabit.io.cosmostaion.base.BaseConstant.COSMOSTATION_BLOG;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOSTATION_GITHUB;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOSTATION_HOMEPAGE;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOSTATION_TELEGRAM;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOSTATION_TERM_EN;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOSTATION_TERM_KR;
import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_NOTICE_MINTSCAN;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
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
import android.widget.Toast;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.hardware.fingerprint.FingerprintManagerCompat;

import com.google.zxing.client.android.Intents;
import com.google.zxing.integration.android.IntentIntegrator;
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
import wannabit.io.cosmostaion.activities.LedgerSelectActivity;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.ManageWalletConnectActivity;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.activities.PasswordSetActivity;
import wannabit.io.cosmostaion.activities.QRcodeActivity;
import wannabit.io.cosmostaion.activities.setting.MnemonicListActivity;
import wannabit.io.cosmostaion.activities.setting.PrivateKeyRestoreActivity;
import wannabit.io.cosmostaion.activities.setting.WatchingWalletAddActivity;
import wannabit.io.cosmostaion.activities.txs.starname.StarNameWalletConnectActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dao.Price;
import wannabit.io.cosmostaion.databinding.FragmentMainSettingBinding;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.dialog.CurrencySetDialog;
import wannabit.io.cosmostaion.dialog.FilledVerticalButtonAlertDialog;
import wannabit.io.cosmostaion.dialog.PriceColorChangeDialog;
import wannabit.io.cosmostaion.dialog.WaitDialog;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.PushStatusResponse;
import wannabit.io.cosmostaion.utils.LanguageUtil;
import wannabit.io.cosmostaion.utils.LedgerManager;
import wannabit.io.cosmostaion.utils.PushManager;
import wannabit.io.cosmostaion.utils.ThemeUtil;

public class MainSettingFragment extends BaseFragment implements View.OnClickListener {

    private FragmentMainSettingBinding mainSettingBinding;

    protected WaitDialog mDialogWait;

    public static MainSettingFragment newInstance() {
        return new MainSettingFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mainSettingBinding = FragmentMainSettingBinding.inflate(inflater, container, false);
        mainSettingBinding.cardWallet.setOnClickListener(this);
        mainSettingBinding.cardMnemonic.setOnClickListener(this);
        mainSettingBinding.cardKey.setOnClickListener(this);
        mainSettingBinding.cardWatchAddress.setOnClickListener(this);
        mainSettingBinding.cardTheme.setOnClickListener(this);
        mainSettingBinding.cardLanguage.setOnClickListener(this);
        mainSettingBinding.cardAutoPass.setOnClickListener(this);
        mainSettingBinding.cardCurrency.setOnClickListener(this);
        mainSettingBinding.cardPriceColorChange.setOnClickListener(this);
        mainSettingBinding.cardExplore.setOnClickListener(this);
        mainSettingBinding.cardNotice.setOnClickListener(this);
        mainSettingBinding.cardHomepage.setOnClickListener(this);
        mainSettingBinding.cardBlog.setOnClickListener(this);
        mainSettingBinding.cardTelegram.setOnClickListener(this);
        mainSettingBinding.cardStarnameWalletConnect.setOnClickListener(this);
        mainSettingBinding.cardTerm.setOnClickListener(this);
        mainSettingBinding.cardGithub.setOnClickListener(this);
        mainSettingBinding.cardVersion.setOnClickListener(this);
        mainSettingBinding.cardWalletConnect.setOnClickListener(this);
        mainSettingBinding.cardLedger.setOnClickListener(this);
        mainSettingBinding.cardPrivacy.setOnClickListener(this);
        mainSettingBinding.switchUsingApplock.setOnClickListener(this);
        mainSettingBinding.switchUsingBio.setOnClickListener(this);
        mainSettingBinding.switchAlaram.setOnCheckedChangeListener(switchListener());

        onUpdateView();
        loadPushStatus();

        mainSettingBinding.versionText.setText("v" + BuildConfig.VERSION_NAME);
        return mainSettingBinding.getRoot();
    }

    private CompoundButton.OnCheckedChangeListener switchListener() {
        return (view, checked) -> syncPushStatus();
    }

    private void syncPushStatus() {
        if (!getBaseDao().getAlarmEnable()) {
            PushManager.syncAddresses(requireContext(), getBaseDao(), getBaseDao().getFCMToken());
        }
        PushManager.updateStatus(requireContext(), getBaseDao(), mainSettingBinding.switchAlaram.isChecked(), getBaseDao().getFCMToken());
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        mainSettingBinding = null;
    }

    private void loadPushStatus() {
        ApiClient.getCosmostationOld(requireContext()).getPushStatus(getBaseDao().getFCMToken()).enqueue(new Callback<PushStatusResponse>() {
            @Override
            public void onResponse(Call<PushStatusResponse> call, Response<PushStatusResponse> response) {
                if (response.isSuccessful()) {
                    mainSettingBinding.switchAlaram.setChecked(response.body().subscribe);
                    getBaseDao().setAlarmEnable(response.body().subscribe);
                }
            }

            @Override
            public void onFailure(Call<PushStatusResponse> call, Throwable t) {

            }
        });
    }

    @Override
    public void onResume() {
        super.onResume();
        if (isAdded()) return;
    }

    @Override
    public void onRefreshTab() {
        if (!isAdded()) return;
        onUpdateCurrency();
        onUpdateAutoPass();
    }

    private void onUpdateView() {
        onUpdatePriceColor(getMainActivity().getBaseDao().getPriceColorOption());

        if (ThemeUtil.modLoad(getBaseActivity()).equals(ThemeUtil.LIGHT_MODE)) {
            mainSettingBinding.themeText.setText(R.string.str_theme_light);
        } else if (ThemeUtil.modLoad(getBaseActivity()).equals(ThemeUtil.DARK_MODE)) {
            mainSettingBinding.themeText.setText(R.string.str_theme_dark);
        } else {
            mainSettingBinding.themeText.setText(R.string.str_theme_system);
        }

        if (LanguageUtil.modLoad(getBaseActivity()).equals(LanguageUtil.LANGUAGE_ENGLISH)) {
            mainSettingBinding.languageText.setText(R.string.str_language_english);
        } else if (LanguageUtil.modLoad(getBaseActivity()).equals(LanguageUtil.LANGUAGE_KOREAN)) {
            mainSettingBinding.languageText.setText(R.string.str_language_korean);
        } else if (LanguageUtil.modLoad(getBaseActivity()).equals(LanguageUtil.LANGUAGE_JAPANESE)) {
            mainSettingBinding.languageText.setText(R.string.str_language_japanese);
        } else {
            mainSettingBinding.languageText.setText(R.string.str_theme_system);
        }

        mainSettingBinding.switchUsingApplock.setChecked(getBaseDao().getUsingAppLock());
        mainSettingBinding.switchUsingBio.setChecked(getBaseDao().getUsingFingerPrint());

        FingerprintManagerCompat mFingerprintManagerCompat = FingerprintManagerCompat.from(getActivity());
        if ((Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) && mFingerprintManagerCompat.isHardwareDetected() && mFingerprintManagerCompat.hasEnrolledFingerprints()) {
            mainSettingBinding.bioTitle.setText(getString(R.string.str_using_fingerprints));
        } else {
            mainSettingBinding.bioTitle.setText("");
        }
    }

    private void onUpdateCurrency() {
        mainSettingBinding.currencyText.setText(getBaseDao().getCurrencyString());
    }

    private void onUpdatePriceColor(int value) {
        if (value == 1) {
            mainSettingBinding.iconPriceColorUp.setImageResource(R.drawable.icon_pricegreen);
            mainSettingBinding.iconPriceColorDown.setImageResource(R.drawable.icon_pricered);
        } else {
            mainSettingBinding.iconPriceColorUp.setImageResource(R.drawable.icon_pricered);
            mainSettingBinding.iconPriceColorDown.setImageResource(R.drawable.icon_pricegreen);
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mainSettingBinding.cardWallet)) {
            startActivity(new Intent(getBaseActivity(), AccountListActivity.class));

        } else if (v.equals(mainSettingBinding.cardMnemonic)) {
            startActivity(new Intent(getBaseActivity(), MnemonicListActivity.class));

        } else if (v.equals(mainSettingBinding.cardKey)) {
            startActivity(new Intent(getBaseActivity(), PrivateKeyRestoreActivity.class));

        } else if (v.equals(mainSettingBinding.cardWatchAddress)) {
            startActivity(new Intent(getBaseActivity(), WatchingWalletAddActivity.class));

        } else if (v.equals(mainSettingBinding.cardTheme)) {
            FilledVerticalButtonAlertDialog.showTripleButton(getBaseActivity(), null, null, getString(R.string.str_theme_system), view -> setTheme(getBaseActivity(), ThemeUtil.DEFAULT_MODE), null, getString(R.string.str_theme_light), view -> setTheme(getBaseActivity(), ThemeUtil.LIGHT_MODE), null, getString(R.string.str_theme_dark), view -> setTheme(getBaseActivity(), ThemeUtil.DARK_MODE), null);

        } else if (v.equals(mainSettingBinding.cardLanguage)) {
            FilledVerticalButtonAlertDialog.showQuadrupleButton(getBaseActivity(), null, null, getString(R.string.str_language_system), view -> setLanguage(getBaseActivity(), LanguageUtil.SYSTEM_MODE), null, getString(R.string.str_language_english), view -> setLanguage(getBaseActivity(), LanguageUtil.LANGUAGE_ENGLISH), null, getString(R.string.str_language_korean), view -> setLanguage(getBaseActivity(), LanguageUtil.LANGUAGE_KOREAN), null, getString(R.string.str_language_japanese), view -> setLanguage(getBaseActivity(), LanguageUtil.LANGUAGE_JAPANESE), null);

        } else if (v.equals(mainSettingBinding.switchUsingApplock)) {
            onClickAppLock();

        } else if (v.equals(mainSettingBinding.switchUsingBio)) {
            getBaseDao().setUsingFingerPrint(!getBaseDao().getUsingFingerPrint());
            onUpdateView();

        } else if (v.equals(mainSettingBinding.cardAutoPass)) {
            onClickAutoPass();

        } else if (v.equals(mainSettingBinding.cardCurrency) && !getMainActivity().isFinishing()) {
            CurrencySetDialog dialog = CurrencySetDialog.newInstance(null);
            dialog.show(getParentFragmentManager(), CurrencySetDialog.class.getName());
            getParentFragmentManager().setFragmentResultListener(CurrencySetDialog.CURRENCY_SET_BUNDLE_KEY, this, (requestKey, bundle) -> {
                int result = bundle.getInt(BaseConstant.POSITION);
                onSetCurrency(result);
            });

        } else if (v.equals(mainSettingBinding.cardPriceColorChange) && !getMainActivity().isFinishing()) {
            PriceColorChangeDialog dialog = PriceColorChangeDialog.newInstance(null);
            dialog.show(getParentFragmentManager(), PriceColorChangeDialog.class.getName());
            getParentFragmentManager().setFragmentResultListener(BaseConstant.PRE_PRICE_COLOR, this, (requestKey, bundle) -> {
                int result = bundle.getInt(BaseConstant.POSITION);
                onUpdatePriceColor(result);
            });

        } else if (v.equals(mainSettingBinding.cardExplore)) {
            String url = getMainActivity().mChainConfig.explorerUrl();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);

        } else if (v.equals(mainSettingBinding.cardWalletConnect)) {
            startActivity(new Intent(getContext(), ManageWalletConnectActivity.class));

        } else if (v.equals(mainSettingBinding.cardPrivacy)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(BaseConstant.COSMOSTATION_PRIVACY_POLICY));
            startActivity(intent);

        } else if (v.equals(mainSettingBinding.cardNotice)) {
            String url = EXPLORER_NOTICE_MINTSCAN + ChainFactory.getChain(getMainActivity().mBaseChain).chainName();
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            startActivity(intent);

        } else if (v.equals(mainSettingBinding.cardHomepage)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(COSMOSTATION_HOMEPAGE));
            startActivity(intent);

        } else if (v.equals(mainSettingBinding.cardBlog)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(COSMOSTATION_BLOG));
            startActivity(intent);

        } else if (v.equals(mainSettingBinding.cardTelegram)) {
            Intent telegram = new Intent(Intent.ACTION_VIEW, Uri.parse(COSMOSTATION_TELEGRAM));
            startActivity(telegram);

        } else if (v.equals(mainSettingBinding.cardTerm)) {
            if (LanguageUtil.modLoad(getBaseActivity()).equals(LanguageUtil.LANGUAGE_KOREAN) || Locale.getDefault().getLanguage().equals(LanguageUtil.LANGUAGE_KOREAN)) {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(COSMOSTATION_TERM_KR));
                startActivity(intent);
            } else {
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(COSMOSTATION_TERM_EN));
                startActivity(intent);
            }

        } else if (v.equals(mainSettingBinding.cardGithub)) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(COSMOSTATION_GITHUB));
            startActivity(intent);

        } else if (v.equals(mainSettingBinding.cardVersion)) {
            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setData(Uri.parse("market://details?id=" + getMainActivity().getPackageName()));
            startActivity(intent);

        } else if (v.equals(mainSettingBinding.cardLedger)) {
            onLedgerConnect();

        } else if (v.equals(mainSettingBinding.cardStarnameWalletConnect)) {
            CommonAlertDialog.showDoubleButton(getMainActivity(), getString(R.string.str_starname_walletconnect_alert_title), getString(R.string.str_starname_walletconnect_alert_msg), getString(R.string.str_cancel), null, getString(R.string.str_continue), view -> new TedPermission(getMainActivity()).setPermissionListener(new PermissionListener() {
                @Override
                public void onPermissionGranted() {
                    IntentIntegrator integrator = IntentIntegrator.forSupportFragment(MainSettingFragment.this);
                    integrator.setOrientationLocked(true);
                    integrator.setCaptureActivity(QRcodeActivity.class);
                    wcQrcodeResultLauncher.launch(integrator.createScanIntent());
                }

                @Override
                public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                    Toast.makeText(getContext(), R.string.error_permission, Toast.LENGTH_SHORT).show();
                }
            }).setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE).setRationaleMessage(getString(R.string.str_permission_qr)).check());
        }
    }

    private void onClickAppLock() {
        if (getBaseDao().getUsingAppLock()) {
            Intent intent = new Intent(getActivity(), PasswordCheckActivity.class);
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_APP_LOCK);
            appLockCheckResultLauncher.launch(intent);
            getActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);

        } else {
            if (getBaseDao().onHasPassword()) {
                getBaseDao().setUsingAppLock(true);
                onUpdateView();

            } else {
                Intent intent = new Intent(getActivity(), PasswordSetActivity.class);
                appLockCheckResultLauncher.launch(intent);
                getActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
            }
        }
    }

    private void onSetCurrency(int value) {
        if (getBaseDao().getCurrency() != value) {
            getBaseDao().setCurrency(value);
            onUpdateCurrency();

            ApiClient.getMintscan(getBaseApplication()).getPrice(getBaseDao().getCurrencyString().toLowerCase()).enqueue(new Callback<ArrayList<Price>>() {
                @Override
                public void onResponse(Call<ArrayList<Price>> call, Response<ArrayList<Price>> response) {
                    if (response.isSuccessful() && response.body() != null) {
                        getBaseDao().mPrices.clear();
                        for (Price price : response.body()) {
                            getBaseDao().mPrices.add(price);
                        }
                        getBaseDao().setLastPriceTime();
                    }
                }

                @Override
                public void onFailure(Call<ArrayList<Price>> call, Throwable t) {
                }
            });
        }
    }

    private void onClickAutoPass() {
        if (!getBaseDao().onHasPassword()) {
            Intent intent = new Intent(getActivity(), PasswordSetActivity.class);
            autoPassResultLauncher.launch(intent);

        } else {
            Intent intent = new Intent(getActivity(), PasswordCheckActivity.class);
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_AUTO_PASS);
            autoPassResultLauncher.launch(intent);
        }
        getActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
    }

    private void onShowAutoPassDialog() {
        FilledVerticalButtonAlertDialog.showQuadrupleButton(getBaseActivity(), null, getString(R.string.str_app_auto_pass_msg), getString(R.string.str_app_auto_pass_5m), view -> onSetAutoPass(1), null, getString(R.string.str_app_auto_pass_10m), view -> onSetAutoPass(2), null, getString(R.string.str_app_auto_pass_30m), view -> onSetAutoPass(3), null, getString(R.string.str_app_auto_pass_never), view -> onSetAutoPass(0), null);
    }

    private void onSetAutoPass(int value) {
        if (getBaseDao().getUsingAutoPassTime() != value) {
            getBaseDao().setUsingAutoPassTime(value);
            onUpdateAutoPass();
        }
    }

    private void onUpdateAutoPass() {
        mainSettingBinding.autoPassTime.setText(getBaseDao().getAutoPass(getActivity()));
    }

    private void onLedgerConnect() {
        if (LedgerManager.getInstance().isConnected()) {
            LedgerManager.getInstance().getBleManager().disconnect(() -> {
                onShowWaitDialog();
                mainSettingBinding.cardLedger.postDelayed((Runnable) this::showLedgerPicker, 1500);
                return null;
            });
        } else {
            onShowWaitDialog();
            showLedgerPicker();
        }
    }

    private void showLedgerPicker() {
        onHideWaitDialog();
        getActivity().runOnUiThread(() -> LedgerManager.getInstance().pickLedgerDevice(requireContext(), new LedgerManager.ConnectListener() {
            @Override
            public void error(@NonNull LedgerManager.ErrorType errorType) {
                FilledVerticalButtonAlertDialog.showNoButton(getContext(), getString(R.string.str_pairing_ledger_title), getString(errorType.getDescriptionResourceId()), true);
            }

            @Override
            public void connected() {
                startActivity(new Intent(getActivity(), LedgerSelectActivity.class));
            }
        }));
    }

    public MainActivity getMainActivity() {
        return (MainActivity) getBaseActivity();
    }

    private final ActivityResultLauncher<Intent> appLockCheckResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            getBaseDao().setUsingAppLock(false);
            onUpdateView();
        }
    });

    private final ActivityResultLauncher<Intent> autoPassResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK) {
            new Handler(Looper.getMainLooper()).postDelayed(() -> onShowAutoPassDialog(), 300);
        }
    });

    private final ActivityResultLauncher<Intent> wcQrcodeResultLauncher = registerForActivityResult(new ActivityResultContracts.StartActivityForResult(), result -> {
        if (result.getResultCode() == Activity.RESULT_OK && result.getData() != null && result.getData().getStringExtra(Intents.Scan.RESULT).trim().contains("bridge.walletconnect.org")) {
            Intent wcIntent = new Intent(getMainActivity(), StarNameWalletConnectActivity.class);
            wcIntent.putExtra("wcUrl", result.getData().getStringExtra(Intents.Scan.RESULT).trim());
            startActivity(wcIntent);
        } else {
            Toast.makeText(getActivity(), getActivity().getString(R.string.str_wc_not_supported), Toast.LENGTH_SHORT).show();
            return;
        }
    });

    private void setTheme(Context context, String themeColor) {
        ThemeUtil.applyTheme(themeColor);
        ThemeUtil.modSave(context, themeColor);
        getMainActivity().recreate();
    }

    private void setLanguage(Context context, String languageSet) {
        LanguageUtil.updateResources(context);
        LanguageUtil.modSave(context, languageSet);
        getMainActivity().recreate();
    }

    public void onShowWaitDialog() {
        if (getActivity().getSupportFragmentManager().findFragmentByTag("wait") == null) {
            mDialogWait = new WaitDialog();
        }
        mDialogWait.setCancelable(false);
        mDialogWait.show(getActivity().getSupportFragmentManager(), "wait");
    }

    public void onHideWaitDialog() {
        if (mDialogWait != null) {
            mDialogWait.dismissAllowingStateLoss();
        }
    }
}
