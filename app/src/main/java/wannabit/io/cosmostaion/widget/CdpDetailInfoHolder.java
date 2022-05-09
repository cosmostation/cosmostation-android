package wannabit.io.cosmostaion.widget;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_CDP_IMG_URL;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;

import kava.cdp.v1beta1.Genesis;
import kava.cdp.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.CdpDetail5Activity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.dialog.AlertDialogUtils;
import wannabit.io.cosmostaion.dialog.Dialog_Safe_Score_Staus;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class CdpDetailInfoHolder extends BaseHolder {
    private ImageView       mInfoMarketImg;
    private TextView        mInfoMarketType, mInfoMarketId;
    private LinearLayout    mInfoRiskHelp;
    private ImageView       mInfoImgRisk;
    private TextView        mInfoRiskScore;
    private RelativeLayout  mInfoLiquidationPriceLayer;
    private LinearLayout    mInfoCollateralRateHelp, mStabilityFeeHelp, mInfoLiquidationPenaltyHelp;
    private TextView        mInfoCollateralRate, mInfoStabilityFee, mInfoLiquidationPenalty,
                            mInfoCurrentPriceTitle, mInfoCurrentPrice, mInfoLiquidationPriceTitle, mInfoLiquidationPrice;
    private TextView        mInfoMaxDebtAmount, mInfoRemainDebtAmount;

    BigDecimal              mRiskRate= BigDecimal.ZERO;
    BigDecimal              mLiquidationPrice = BigDecimal.ZERO;

    public CdpDetailInfoHolder(@NonNull View itemView) {
        super(itemView);

        mInfoMarketImg                  = itemView.findViewById(R.id.market_img);
        mInfoMarketType                 = itemView.findViewById(R.id.cdp_market_type);
        mInfoMarketId                   = itemView.findViewById(R.id.market_title);
        mInfoRiskHelp                   = itemView.findViewById(R.id.safe_rate_layer);
        mInfoImgRisk                    = itemView.findViewById(R.id.cdp_safe_img);
        mInfoRiskScore                  = itemView.findViewById(R.id.cdp_safe_rate);
        mInfoCollateralRateHelp         = itemView.findViewById(R.id.collateral_rate_layer);
        mInfoCollateralRate             = itemView.findViewById(R.id.collateral_rate);
        mStabilityFeeHelp               = itemView.findViewById(R.id.stability_fee_layer);
        mInfoStabilityFee               = itemView.findViewById(R.id.stability_fee);
        mInfoLiquidationPenaltyHelp     = itemView.findViewById(R.id.liquidation_penalty_layer);
        mInfoLiquidationPenalty         = itemView.findViewById(R.id.liquidation_penalty);
        mInfoCurrentPriceTitle          = itemView.findViewById(R.id.current_price_title);
        mInfoCurrentPrice               = itemView.findViewById(R.id.current_price);
        mInfoLiquidationPriceLayer      = itemView.findViewById(R.id.liquidation_price_layer);
        mInfoLiquidationPriceTitle      = itemView.findViewById(R.id.liquidation_price_title);
        mInfoLiquidationPrice           = itemView.findViewById(R.id.liquidation_price);
        mInfoMaxDebtAmount              = itemView.findViewById(R.id.max_debt_amount);
        mInfoRemainDebtAmount           = itemView.findViewById(R.id.remain_debt_amount);
    }

    @Override
    public void onBindCdpDetailInfo(CdpDetail5Activity context, BaseData baseData, QueryOuterClass.CDPResponse myCdp, String collateralType, BigDecimal debtAmount) {
        final Genesis.CollateralParam collateralParam   = baseData.getCollateralParamByType(collateralType);
        final String cDenom                             = collateralParam.getDenom();
        final BigDecimal currentPrice                   = baseData.getKavaOraclePrice(collateralParam.getLiquidationMarketId());

        try {
            Picasso.get().load(KAVA_CDP_IMG_URL + collateralParam.getType() + ".png").fit().into(mInfoMarketImg);
        } catch (Exception e) { }

        mInfoMarketType.setText(collateralParam.getType().toUpperCase());
        mInfoMarketId.setText(collateralParam.getSpotMarketId().toUpperCase());

        mInfoCollateralRate.setText(WDp.getPercentDp(new BigDecimal(collateralParam.getLiquidationRatio()).movePointLeft(16), 2));
        mInfoStabilityFee.setText(WDp.getPercentDp(WUtil.getDpStabilityFee(collateralParam), 2));
        mInfoLiquidationPenalty.setText(WDp.getPercentDp(new BigDecimal(collateralParam.getLiquidationPenalty()).movePointLeft(16), 2));
        mInfoCurrentPriceTitle.setText(String.format(context.getString(R.string.str_current_title3), WUtil.getKavaTokenName(baseData, cDenom)));
        mInfoCurrentPrice.setText(WDp.getDpRawDollor(context, currentPrice, 4));

        mInfoMaxDebtAmount.setText(WDp.getDpAmount2(context, new BigDecimal(baseData.mCdpParams.getGlobalDebtLimit().getAmount()), 6, 6));
        mInfoRemainDebtAmount.setText(WDp.getDpAmount2(context, new BigDecimal(baseData.mCdpParams.getGlobalDebtLimit().getAmount()).subtract(debtAmount), 6, 6));

        if (myCdp != null) {
            mLiquidationPrice = WUtil.getLiquidationPrice(context, baseData, myCdp, collateralParam);
            mRiskRate = new BigDecimal(100).subtract((currentPrice.subtract(mLiquidationPrice)).movePointRight(2).divide(currentPrice, 2, RoundingMode.DOWN));
            WDp.DpRiskRate(context, mRiskRate, mInfoRiskScore, mInfoImgRisk);

            mInfoLiquidationPriceTitle.setText(String.format(context.getString(R.string.str_liquidation_title3),  WUtil.getKavaTokenName(baseData, cDenom)));
            mInfoLiquidationPrice.setText(WDp.getDpRawDollor(context, mLiquidationPrice, 4));
            mInfoLiquidationPrice.setTextColor(WDp.getDpRiskColor(context, mRiskRate));
            mInfoLiquidationPriceLayer.setVisibility(View.VISIBLE);

        }

        mInfoRiskHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("riskRate", mRiskRate.toPlainString());
                bundle.putString("liquidationPrice", mLiquidationPrice.toPlainString());
                bundle.putString("currentPrice", currentPrice.toPlainString());
                bundle.putString("denom", cDenom);
                Dialog_Safe_Score_Staus dialog = Dialog_Safe_Score_Staus.newInstance(bundle);
                dialog.setCancelable(true);
                context.getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

            }
        });
        mInfoCollateralRateHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowHelpPopup(context, context.getString(R.string.str_help_collateral_rate_t), context.getString(R.string.str_help_collateral_rate));
            }
        });
        mStabilityFeeHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowHelpPopup(context, context.getString(R.string.str_help_stability_fee_t), context.getString(R.string.str_help_stability_fee));

            }
        });
        mInfoLiquidationPenaltyHelp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onShowHelpPopup(context, context.getString(R.string.str_help_liquidation_penalty_t), context.getString(R.string.str_help_liquidation_penalty));

            }
        });
    }

    private void onShowHelpPopup(CdpDetail5Activity context, String title, String msg) {
        AlertDialogUtils.showSingleButtonDialog(context, title, msg, "OK", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}
