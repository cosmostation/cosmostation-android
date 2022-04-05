package wannabit.io.cosmostaion.widget;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

import kava.cdp.v1beta1.Genesis;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.CdpDetail5Activity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class CdpDetailMyAvailableHolder extends BaseHolder {
    private final ImageView mEmptyCollateralImg;
    private final ImageView mEmptyPrincipalImg;
    private final TextView mEmptyCollateralDenom;
    private final TextView mEmptyCollateralAmount;
    private final TextView mEmptyPrincipalDenom;
    private final TextView mEmptyPrincipalAmount;
    private final TextView mEmptyKavaAmount;
    private final TextView mEmptyCollateralValue;
    private final TextView mEmptyPrincipalValue;
    private final TextView mEmptyKavaValue;

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
    }

    @Override
    public void onBindCdpDetailAvailable(CdpDetail5Activity context, BaseData baseData, String collateralType) {
        final Genesis.CollateralParam collateralParam = baseData.getCollateralParamByType(collateralType);
        final String cDenom = collateralParam.getDenom();
        final String pDenom = collateralParam.getDebtLimit().getDenom();
        final BigDecimal currentPrice = baseData.getKavaOraclePrice(collateralParam.getLiquidationMarketId());
        final BigDecimal cAvailable = baseData.getAvailable(cDenom);
        final BigDecimal pAvailable = baseData.getAvailable(pDenom);
        final BigDecimal kAvailable = baseData.getAvailable(TOKEN_KAVA);


        mEmptyCollateralDenom.setText(WUtil.getKavaTokenName(baseData, collateralParam.getDenom()));
        mEmptyCollateralAmount.setText(WDp.getDpAmount2(cAvailable, WUtil.getKavaCoinDecimal(baseData, cDenom), WUtil.getKavaCoinDecimal(baseData, cDenom)));
        BigDecimal collateralValue = cAvailable.movePointLeft(WUtil.getKavaCoinDecimal(baseData, cDenom)).multiply(currentPrice).setScale(2, RoundingMode.DOWN);
        mEmptyCollateralValue.setText(WDp.getDpRawDollor(context, collateralValue, 2));

        mEmptyPrincipalDenom.setText(WUtil.getKavaTokenName(baseData, collateralParam.getDebtLimit().getDenom()));
        mEmptyPrincipalAmount.setText(WDp.getDpAmount2(pAvailable, WUtil.getKavaCoinDecimal(baseData, pDenom), WUtil.getKavaCoinDecimal(baseData, pDenom)));
        BigDecimal principalValue = pAvailable.movePointLeft(WUtil.getKavaCoinDecimal(baseData, pDenom)).setScale(2, RoundingMode.DOWN);
        mEmptyPrincipalValue.setText(WDp.getDpRawDollor(context, principalValue, 2));

        mEmptyKavaAmount.setText(WDp.getDpAmount2(kAvailable, WUtil.getKavaCoinDecimal(baseData, TOKEN_KAVA), WUtil.getKavaCoinDecimal(baseData, TOKEN_KAVA)));
        BigDecimal kavaValue = WDp.usdValue(baseData, TOKEN_KAVA, kAvailable, 6);
        mEmptyKavaValue.setText(WDp.getDpRawDollor(context, kavaValue, 2));

        WUtil.DpKavaTokenImg(baseData, mEmptyCollateralImg, cDenom);
        WUtil.DpKavaTokenImg(baseData, mEmptyPrincipalImg, pDenom);
    }
}
