package wannabit.io.cosmostaion.widget.kava;

import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

import kava.cdp.v1beta1.Genesis;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.CdpDetailActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class CdpDetailMyAvailableHolder extends BaseHolder {
    private ImageView   mEmptyCollateralImg, mEmptyPrincipalImg;
    private TextView    mEmptyCollateralDenom, mEmptyCollateralAmount, mEmptyPrincipalDenom,
                        mEmptyPrincipalAmount, mEmptyKavaAmount;
    private TextView    mEmptyCollateralValue, mEmptyPrincipalValue, mEmptyKavaValue;
    private RelativeLayout  mKavalayout, mKavaValuelayout;

    public CdpDetailMyAvailableHolder(@NonNull View itemView) {
        super(itemView);
        mEmptyCollateralImg = itemView.findViewById(R.id.collateral_icon);
        mEmptyCollateralAmount = itemView.findViewById(R.id.collateral_amount);
        mEmptyCollateralDenom = itemView.findViewById(R.id.collateral_denom);
        mEmptyCollateralValue = itemView.findViewById(R.id.collateral_value);
        mEmptyPrincipalImg = itemView.findViewById(R.id.principal_icon);
        mEmptyPrincipalAmount = itemView.findViewById(R.id.principal_amount);
        mEmptyPrincipalDenom = itemView.findViewById(R.id.principal_denom);
        mEmptyPrincipalValue = itemView.findViewById(R.id.principal_value);
        mEmptyKavaAmount = itemView.findViewById(R.id.kava_amount);
        mEmptyKavaValue = itemView.findViewById(R.id.kava_value);
        mKavalayout = itemView.findViewById(R.id.kava_layout);
        mKavaValuelayout = itemView.findViewById(R.id.kava_value_layout);
    }

    @Override
    public void onBindCdpDetailAvailable(CdpDetailActivity context, BaseData baseData, String collateralType) {
        final ChainConfig chainConfig = ChainFactory.getChain(KAVA_MAIN);
        final Genesis.CollateralParam collateralParam = baseData.getCollateralParamByType(collateralType);
        final String cDenom = collateralParam.getDenom();
        final String pDenom = collateralParam.getDebtLimit().getDenom();
        final BigDecimal currentPrice = baseData.getKavaOraclePrice(collateralParam.getLiquidationMarketId());
        final BigDecimal cAvailable = baseData.getAvailable(cDenom);
        final BigDecimal pAvailable = baseData.getAvailable(pDenom);
        final BigDecimal kAvailable = baseData.getAvailable(chainConfig.mainDenom());

        WDp.setDpSymbol(context, baseData, chainConfig, collateralParam.getDenom(), mEmptyCollateralDenom);
        mEmptyCollateralAmount.setText(WDp.getDpAmount2(context, cAvailable, WDp.getDenomDecimal(baseData, chainConfig, cDenom), WDp.getDenomDecimal(baseData, chainConfig, cDenom)));
        BigDecimal collateralValue = cAvailable.movePointLeft(WDp.getDenomDecimal(baseData, chainConfig, cDenom)).multiply(currentPrice).setScale(2, RoundingMode.DOWN);
        mEmptyCollateralValue.setText(WDp.getDpRawDollor(context, collateralValue, 2));

        WDp.setDpSymbol(context, baseData, chainConfig, collateralParam.getDebtLimit().getDenom(), mEmptyPrincipalDenom);
        mEmptyPrincipalAmount.setText(WDp.getDpAmount2(context, pAvailable, WDp.getDenomDecimal(baseData, chainConfig, pDenom), WDp.getDenomDecimal(baseData, chainConfig, pDenom)));
        BigDecimal principalValue = pAvailable.movePointLeft(WDp.getDenomDecimal(baseData, chainConfig, pDenom)).setScale(2, RoundingMode.DOWN);
        mEmptyPrincipalValue.setText(WDp.getDpRawDollor(context, principalValue, 2));

        mEmptyKavaAmount.setText(WDp.getDpAmount2(context, kAvailable, 6, 6));
        BigDecimal kavaValue = WDp.usdValue(baseData, chainConfig.mainDenom(), kAvailable, 6);
        mEmptyKavaValue.setText(WDp.getDpRawDollor(context, kavaValue, 2));

        WDp.setDpSymbolImg(baseData, chainConfig, cDenom, mEmptyCollateralImg);
        WDp.setDpSymbolImg(baseData, chainConfig, pDenom, mEmptyPrincipalImg);
        if (cDenom.equalsIgnoreCase(chainConfig.mainDenom())) {
            mKavalayout.setVisibility(View.GONE);
            mKavaValuelayout.setVisibility(View.GONE);
        } else {
            mKavalayout.setVisibility(View.VISIBLE);
            mKavaValuelayout.setVisibility(View.VISIBLE);
        }
    }
}
