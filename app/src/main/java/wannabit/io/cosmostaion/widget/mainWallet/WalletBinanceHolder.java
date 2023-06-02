package wannabit.io.cosmostaion.widget.mainWallet;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_BNB;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.google.zxing.integration.android.IntentIntegrator;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.QRcodeActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
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
        mTvBnbValue.setText(WDp.dpAssetValue(baseData, WDp.getGeckoId(baseData, mainActivity.mChainConfig), totalAmount, 0));

        mainActivity.getBaseDao().onUpdateLastTotalAccount(mainActivity.mAccount, totalAmount.toPlainString());

        mBtnWalletConnect.setOnClickListener(v -> {
            if (!mainActivity.mAccount.hasPrivateKey) {
                mainActivity.onInsertKeyDialog();
                return;
            }
            
            IntentIntegrator integrator = new IntentIntegrator(mainActivity);
            integrator.setOrientationLocked(true);
            integrator.setCaptureActivity(QRcodeActivity.class);
            mainActivity.walletConnectResultLauncher.launch(integrator.createScanIntent());
        });
        mBtnBep3Send.setOnClickListener(v -> mainActivity.onStartHTLCSendActivity(TOKEN_HTLC_BINANCE_BNB));
    }
}

