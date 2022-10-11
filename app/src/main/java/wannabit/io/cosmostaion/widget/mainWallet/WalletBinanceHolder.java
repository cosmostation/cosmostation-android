package wannabit.io.cosmostaion.widget.mainWallet;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BNB;

import android.Manifest;
import android.text.Html;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.zxing.integration.android.IntentIntegrator;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletBinanceHolder extends BaseHolder {
    public TextView mTvBnbTotal, mTvBnbValue, mTvBnbBalance, mTvBnbLocked, mTvBnbFrozen;
    public RelativeLayout mBtnWalletConnect, mBtnBep3Send;

    public WalletBinanceHolder(@NonNull View itemView) {
        super(itemView);
        mTvBnbTotal = itemView.findViewById(R.id.bnb_amount);
        mTvBnbValue = itemView.findViewById(R.id.bnb_value);
        mTvBnbBalance = itemView.findViewById(R.id.bnb_available);
        mTvBnbLocked = itemView.findViewById(R.id.bnb_locked);
        mTvBnbFrozen = itemView.findViewById(R.id.bnb_frozen);
        mBtnWalletConnect = itemView.findViewById(R.id.btn_wallet_connect);
        mBtnBep3Send = itemView.findViewById(R.id.btn_bep3_send);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final String denom = ChainFactory.getChain(BaseChain.BNB_MAIN).mainDenom();
        final BigDecimal availableAmount = baseData.availableAmount(denom);
        final BigDecimal lockedAmount = baseData.lockedAmount(denom);
        final BigDecimal frozenAmount = baseData.frozenAmount(denom);
        final BigDecimal totalAmount = availableAmount.add(lockedAmount).add(frozenAmount);

        mTvBnbTotal.setText(WDp.getDpAmount2(totalAmount, 0, 6));
        mTvBnbBalance.setText(WDp.getDpAmount2(availableAmount, 0, 6));
        mTvBnbLocked.setText(WDp.getDpAmount2(lockedAmount, 0, 6));
        mTvBnbFrozen.setText(WDp.getDpAmount2(frozenAmount, 0, 6));
        mTvBnbValue.setText(WDp.dpAssetValue(baseData, denom, totalAmount, 0));

        mainActivity.getBaseDao().onUpdateLastTotalAccount(mainActivity.mAccount, totalAmount.toPlainString());

        mBtnWalletConnect.setOnClickListener(v -> {
            if (!mainActivity.mAccount.hasPrivateKey) {
                CommonAlertDialog.showDoubleButton(mainActivity, mainActivity.getString(R.string.str_only_observe_title), mainActivity.getString(R.string.str_only_observe_msg),
                        mainActivity.getString(R.string.str_close), null,
                        Html.fromHtml("<font color=\"#9C6CFF\">" + mainActivity.getString(R.string.str_add_mnemonics) + "</font>", Html.FROM_HTML_MODE_COMPACT), view -> mainActivity.onAddMnemonicForAccount());
                return;
            }
            new TedPermission(mainActivity).setPermissionListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted() {
                            IntentIntegrator integrator = new IntentIntegrator(mainActivity);
                            integrator.setOrientationLocked(true);
                            mainActivity.walletConnectResultLauncher.launch(integrator.createScanIntent());
                        }

                        @Override
                        public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                            Toast.makeText(mainActivity, R.string.error_permission, Toast.LENGTH_SHORT).show();
                        }
                    })
                    .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
                    .setRationaleMessage(mainActivity.getString(R.string.str_permission_qr))
                    .check();
        });
        mBtnBep3Send.setOnClickListener(v -> mainActivity.onStartHTLCSendActivity(TOKEN_HTLC_BINANCE_BNB));
    }
}

