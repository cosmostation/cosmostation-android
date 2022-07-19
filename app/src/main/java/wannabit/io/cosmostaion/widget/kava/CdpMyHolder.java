package wannabit.io.cosmostaion.widget.kava;

import static wannabit.io.cosmostaion.base.chains.Kava.KAVA_CDP_IMG_URL;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;

import kava.cdp.v1beta1.Genesis;
import kava.cdp.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.CdpDetail5Activity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class CdpMyHolder extends BaseHolder {
    CardView itemRoot;
    ImageView itemImgMarket, itemImgRisk;
    TextView itemTitleMarket, itemRiskScore;
    TextView itemCollateralType;
    TextView itemDebtValueTitle, itemDebtValue, itemCollateralValueTitle, itemCollateralValue;
    TextView itemStabilityFee, itemLiquidationPenalty;
    TextView itemCurrentPriceTitle, itemCurrentPrice, itemLiquidationPriceTitle, itemLiquidationPrice;

    public CdpMyHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView.findViewById(R.id.card_cdp_my);
        itemImgMarket = itemView.findViewById(R.id.market_img);
        itemTitleMarket = itemView.findViewById(R.id.market_title);
        itemImgRisk = itemView.findViewById(R.id.cdp_safe_img);
        itemRiskScore = itemView.findViewById(R.id.cdp_safe_rate);
        itemDebtValueTitle = itemView.findViewById(R.id.cdp_debt_value_title);
        itemDebtValue = itemView.findViewById(R.id.cdp_debt_value);
        itemCollateralValueTitle = itemView.findViewById(R.id.cdp_collateral_value_title);
        itemCollateralValue = itemView.findViewById(R.id.cdp_collateral_value);
        itemStabilityFee = itemView.findViewById(R.id.cdp_stability_fee);
        itemLiquidationPenalty = itemView.findViewById(R.id.cdp_str_liquidation_penalty);
        itemCurrentPriceTitle = itemView.findViewById(R.id.current_price_title);
        itemCurrentPrice = itemView.findViewById(R.id.current_price);
        itemLiquidationPriceTitle = itemView.findViewById(R.id.liquidation_price_title);
        itemLiquidationPrice = itemView.findViewById(R.id.liquidation_price);
        itemCollateralType = itemView.findViewById(R.id.cdp_market_type);
    }

    @Override
    public void onBindMyCdp(Context c, BaseData baseData, QueryOuterClass.CDPResponse myCdp) {
        final Genesis.CollateralParam collateralParam = baseData.getCollateralParamByType(myCdp.getType());
        final int dpDecimal = WUtil.getKavaCoinDecimal(baseData, myCdp.getPrincipal().getDenom());

        if (collateralParam == null) { return; }

        BigDecimal currentPrice = BigDecimal.ZERO;
        BigDecimal liquidationPrice = BigDecimal.ZERO;
        BigDecimal riskRate = BigDecimal.ZERO;
        currentPrice = baseData.getKavaOraclePrice(collateralParam.getLiquidationMarketId());
        liquidationPrice = WUtil.getLiquidationPrice(c, baseData, myCdp, collateralParam);
        riskRate = new BigDecimal(100).subtract((currentPrice.subtract(liquidationPrice)).movePointRight(2).divide(currentPrice, 2, RoundingMode.DOWN));

        itemCollateralType.setText(collateralParam.getType().toUpperCase());
        itemTitleMarket.setText(collateralParam.getSpotMarketId().toUpperCase());
        itemDebtValueTitle.setText(String.format(c.getString(R.string.str_debt_value), myCdp.getPrincipal().getDenom().toUpperCase()));
        itemCollateralValueTitle.setText(String.format(c.getString(R.string.str_collateral_value_title3), myCdp.getCollateral().getDenom().toUpperCase()));

        final BigDecimal debtValue = new BigDecimal(myCdp.getPrincipal().getAmount());
        final BigDecimal feeValue = new BigDecimal(myCdp.getAccumulatedFees().getAmount());
        final BigDecimal hiddenFeeValue = WDp.getCdpGrpcHiddenFee(c, debtValue.add(feeValue), collateralParam, myCdp);
        final BigDecimal totalDebtValue = debtValue.add(feeValue).add(hiddenFeeValue);
        itemDebtValue.setText(WDp.getDpRawDollor(c, totalDebtValue.movePointLeft(dpDecimal), 2));

        final BigDecimal currentCollateralValue = new BigDecimal(myCdp.getCollateralValue().getAmount());
        itemCollateralValue.setText(WDp.getDpRawDollor(c, currentCollateralValue.movePointLeft(dpDecimal), 2));

        itemStabilityFee.setText(WDp.getPercentDp(WUtil.getDpStabilityFee(collateralParam), 2));
        itemLiquidationPenalty.setText(WDp.getPercentDp(WUtil.getDpLiquidationPenalty(collateralParam), 2));

        itemCurrentPriceTitle.setText(String.format(c.getString(R.string.str_current_title3), myCdp.getCollateral().getDenom().toUpperCase()));
        itemCurrentPrice.setText(WDp.getDpRawDollor(c, currentPrice, 4));

        itemLiquidationPriceTitle.setText(String.format(c.getString(R.string.str_liquidation_title3), myCdp.getCollateral().getDenom().toUpperCase()));
        itemLiquidationPrice.setText(WDp.getDpRawDollor(c, liquidationPrice, 4));
        itemLiquidationPrice.setTextColor(WDp.getDpRiskColor(c, riskRate));

        WDp.DpRiskRate(c, riskRate, itemRiskScore,  itemImgRisk);

        try {
            Picasso.get().load(KAVA_CDP_IMG_URL + collateralParam.getType() + ".png").fit().into(itemImgMarket);
        } catch (Exception e) { }

        itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, CdpDetail5Activity.class);
                intent.putExtra("collateralParamType", collateralParam.getType());
                c.startActivity(intent);
            }
        });
    }


}
