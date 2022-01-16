package wannabit.io.cosmostaion.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;

import kava.cdp.v1beta1.Genesis;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.CdpDetail5Activity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class CdpDetailMyAvailableHolder extends BaseHolder {
    private ImageView   mEmptyCollateralImg, mEmptyPrincipalImg;
    private TextView    mEmptyCollateralDenom, mEmptyCollateralAmount, mEmptyPrincipalDenom,
                        mEmptyPrincipalAmount, mEmptyKavaAmount;
    private TextView    mEmptyCollateralValue, mEmptyPrincipalValue, mEmptyKavaValue;

    public CdpDetailMyAvailableHolder(@NonNull View itemView) {
        super(itemView);
        mEmptyCollateralImg             = itemView.findViewById(R.id.collateral_icon);
        mEmptyCollateralAmount          = itemView.findViewById(R.id.collateral_amount);
        mEmptyCollateralDenom           = itemView.findViewById(R.id.collateral_denom);
        mEmptyCollateralValue           = itemView.findViewById(R.id.collateral_value);
        mEmptyPrincipalImg              = itemView.findViewById(R.id.principal_icon);
        mEmptyPrincipalAmount           = itemView.findViewById(R.id.principal_amount);
        mEmptyPrincipalDenom            = itemView.findViewById(R.id.principal_denom);
        mEmptyPrincipalValue            = itemView.findViewById(R.id.principal_value);
        mEmptyKavaAmount                = itemView.findViewById(R.id.kava_amount);
        mEmptyKavaValue                 = itemView.findViewById(R.id.kava_value);
    }

    @Override
    public void onBindCdpDetailAvailable(CdpDetail5Activity context, BaseData baseData, String collateralType) {
        final Genesis.CollateralParam collateralParam   = baseData.getCollateralParamByType(collateralType);
        final String cDenom                             = collateralParam.getDenom();
        final String pDenom                             = collateralParam.getDebtLimit().getDenom();
        final BigDecimal currentPrice                   = new BigDecimal(baseData.mKavaTokenPrice.get(collateralParam.getLiquidationMarketId()).getPrice()).movePointLeft(18);
        final BigDecimal cAvailable                     = baseData.getAvailable(cDenom);
        final BigDecimal pAvailable                     = baseData.getAvailable(pDenom);
        final BigDecimal kAvailable                     = baseData.getAvailable(TOKEN_KAVA);


        mEmptyCollateralDenom.setText(collateralParam.getDenom().toUpperCase());
        mEmptyCollateralAmount.setText(WDp.getDpAmount2(context, cAvailable, WUtil.getKavaCoinDecimal(cDenom), WUtil.getKavaCoinDecimal(cDenom)));
        BigDecimal collateralValue = cAvailable.movePointLeft(WUtil.getKavaCoinDecimal(cDenom)).multiply(currentPrice).setScale(2, RoundingMode.DOWN);
        mEmptyCollateralValue.setText(WDp.getDpRawDollor(context, collateralValue, 2));

        mEmptyPrincipalDenom.setText(collateralParam.getDebtLimit().getDenom().toUpperCase());
        mEmptyPrincipalAmount.setText(WDp.getDpAmount2(context, pAvailable, WUtil.getKavaCoinDecimal(pDenom), WUtil.getKavaCoinDecimal(pDenom)));
        BigDecimal principalValue = pAvailable.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)).setScale(2, RoundingMode.DOWN);
        mEmptyPrincipalValue.setText(WDp.getDpRawDollor(context, principalValue, 2));

        mEmptyKavaAmount.setText(WDp.getDpAmount2(context, kAvailable, WUtil.getKavaCoinDecimal(TOKEN_KAVA), WUtil.getKavaCoinDecimal(TOKEN_KAVA)));
        BigDecimal kavaValue = WDp.usdValue(baseData, TOKEN_KAVA, kAvailable, 6);
        mEmptyKavaValue.setText(WDp.getDpRawDollor(context, kavaValue, 2));

        try {
            Picasso.get().load(KAVA_COIN_IMG_URL + cDenom + ".png").fit().into(mEmptyCollateralImg);
            Picasso.get().load(KAVA_COIN_IMG_URL + pDenom + ".png").fit().into(mEmptyPrincipalImg);
        } catch (Exception e) { }

    }
}
