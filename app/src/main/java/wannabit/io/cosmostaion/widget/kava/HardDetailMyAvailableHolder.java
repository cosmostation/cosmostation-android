package wannabit.io.cosmostaion.widget.kava;

import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

import kava.hard.v1beta1.Hard;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.HardDetailActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class HardDetailMyAvailableHolder extends BaseHolder {
    private RelativeLayout      mAssetDepositLayer;
    private ImageView           mAssetDepositImg;
    private TextView            mAssetDepositDenom, mAssetDepositAmount, mAssetKavaDenom, mAssetKavaAmount;
    private TextView            mDepositValue, mKavaValue;

    public HardDetailMyAvailableHolder(@NonNull View itemView) {
        super(itemView);
        mAssetDepositLayer          = itemView.findViewById(R.id.collateral_layer);
        mAssetDepositImg            = itemView.findViewById(R.id.collateral_icon);
        mAssetDepositDenom          = itemView.findViewById(R.id.collateral_denom);
        mAssetDepositAmount         = itemView.findViewById(R.id.collateral_amount);

        mAssetKavaDenom             = itemView.findViewById(R.id.kava_denom);
        mAssetKavaAmount            = itemView.findViewById(R.id.kava_amount);

        mDepositValue               = itemView.findViewById(R.id.collateral_value);
        mKavaValue                  = itemView.findViewById(R.id.kava_value);
    }

    @Override
    public void onBindHardDetailAvailable(HardDetailActivity context, BaseData baseData, String denom) {
        final ChainConfig chainConfig           = ChainFactory.getChain(KAVA_MAIN);
        final Hard.Params hardParam             = baseData.mHardParams;
        final Hard.MoneyMarket hardMoneyMarket  = WUtil.getHardMoneyMarket(hardParam, denom);

        if (denom.equals(chainConfig.mainDenom())) {
            mAssetDepositLayer.setVisibility(View.GONE);
            mDepositValue.setVisibility(View.GONE);
        }
        BigDecimal targetAvailable = baseData.getAvailable(denom);
        BigDecimal kavaAvailable = baseData.getAvailable(chainConfig.mainDenom());

        // Display each usd value
        BigDecimal targetPrice = BigDecimal.ZERO;
        if (!denom.equals("usdx")) {
            targetPrice = baseData.getKavaOraclePrice(hardMoneyMarket.getSpotMarketId());
        } else {
            targetPrice = BigDecimal.ONE;
        }
        BigDecimal targetValue = targetAvailable.movePointLeft(WDp.getDenomDecimal(baseData, chainConfig, denom)).multiply(targetPrice);
        WDp.setDpCoin(context, baseData, chainConfig, denom, targetAvailable.toPlainString(), mAssetDepositDenom, mAssetDepositAmount);
        mDepositValue.setText(WDp.getDpRawDollor(context, targetValue, 2));
        WDp.setDpSymbolImg(baseData, chainConfig, denom, mAssetDepositImg);

        BigDecimal kavaValue = BigDecimal.ZERO;
        kavaValue = kavaAvailable.movePointLeft(6).multiply(baseData.getKavaOraclePrice("kava:usd:30"));
        WDp.setDpCoin(context, baseData, chainConfig, chainConfig.mainDenom(), kavaAvailable.toPlainString(), mAssetKavaDenom, mAssetKavaAmount);
        mKavaValue.setText(WDp.getDpRawDollor(context, kavaValue, 2));

    }
}
