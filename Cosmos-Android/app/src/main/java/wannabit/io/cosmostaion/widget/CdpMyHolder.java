package wannabit.io.cosmostaion.widget;

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

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.CdpDetailActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.model.kava.MyCdp;
import wannabit.io.cosmostaion.model.kava.CollateralParam;
import wannabit.io.cosmostaion.network.res.ResKavaMarketPrice;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_CDP_MARKET_IMG_URL;

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
    public void onBindMyCdp(Context c, BaseData baseData, MyCdp myCdp) {
        final CollateralParam collateralParam = baseData.mCdpParam.getCollateralParamByType(myCdp.cdp.type);
        final MarketPrice price = baseData.mKavaTokenPrices.get(collateralParam.liquidation_market_id);
        final int denomPDecimal = WUtil.getKavaCoinDecimal(myCdp.getPDenom());

        final BigDecimal currentPrice = new BigDecimal(price.price);
        final BigDecimal liquidationPrice = myCdp.getLiquidationPrice(c, collateralParam);
        final BigDecimal riskRate = new BigDecimal(100).subtract((currentPrice.subtract(liquidationPrice)).movePointRight(2).divide(currentPrice, 2, RoundingMode.DOWN));

        itemCollateralType.setText(collateralParam.type.toUpperCase());
        itemTitleMarket.setText(collateralParam.getDpMarketId());
        itemDebtValueTitle.setText(String.format(c.getString(R.string.str_debt_value), myCdp.getPDenom().toUpperCase()));
        itemCollateralValueTitle.setText(String.format(c.getString(R.string.str_collateral_value_title3), myCdp.getDenom().toUpperCase()));


        final BigDecimal debtValue = myCdp.getPrincipalAmount();
        final BigDecimal feeValue = myCdp.getAccumulatedFees();
        final BigDecimal hiddenFeeValue = WDp.getCdpHiddenFee(c, debtValue.add(feeValue), collateralParam, myCdp.cdp);
        final BigDecimal totalDebtValue = debtValue.add(feeValue).add(hiddenFeeValue);
        itemDebtValue.setText(WDp.getDpRawDollor(c, totalDebtValue.movePointLeft(denomPDecimal), 2));

        final BigDecimal currentCollateralValue = new BigDecimal(myCdp.collateral_value.amount);
        itemCollateralValue.setText(WDp.getDpRawDollor(c, currentCollateralValue.movePointLeft(denomPDecimal), 2));

        itemStabilityFee.setText(WDp.getPercentDp(collateralParam.getDpStabilityFee(), 2));
        itemLiquidationPenalty.setText(WDp.getPercentDp(collateralParam.getDpLiquidationPenalty(), 2));

        itemCurrentPriceTitle.setText(String.format(c.getString(R.string.str_current_title3), myCdp.getDenom().toUpperCase()));
        itemCurrentPrice.setText(WDp.getDpRawDollor(c, currentPrice, 4));

        itemLiquidationPriceTitle.setText(String.format(c.getString(R.string.str_liquidation_title3), myCdp.getDenom().toUpperCase()));
        itemLiquidationPrice.setText(WDp.getDpRawDollor(c, liquidationPrice, 4));
        itemLiquidationPrice.setTextColor(WDp.getDpRiskColor(c, riskRate));

        WDp.DpRiskRate(c, riskRate, itemRiskScore,  itemImgRisk);

        try {
            Picasso.get().load(KAVA_CDP_MARKET_IMG_URL+  collateralParam.getImagePath()).fit().into(itemImgMarket);
        } catch (Exception e) { }

        itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(c, CdpDetailActivity.class);
                intent.putExtra("collateralParamType", collateralParam.type);
                intent.putExtra("marketId", collateralParam.liquidation_market_id);
                c.startActivity(intent);
            }
        });
    }
}
