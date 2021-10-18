package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.CdpDetail5Activity;
import wannabit.io.cosmostaion.model.kava.CollateralParam;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_CDP_IMG_URL;

public class CdpOtherHolder extends BaseHolder {
    CardView itemRoot;
    ImageView itemImgMarket;
    TextView itemTitleMarket;
    TextView itemCollateralType;
    TextView itemCollateralRate, itemStabilityFee, itemLiquidationPenalty;

    public CdpOtherHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView.findViewById(R.id.card_cdp_all);
        itemImgMarket = itemView.findViewById(R.id.cdp_market_img);
        itemTitleMarket = itemView.findViewById(R.id.cdp_market_title);
        itemCollateralRate = itemView.findViewById(R.id.cdp_collateral_rate);
        itemStabilityFee = itemView.findViewById(R.id.cdp_stability_fee);
        itemLiquidationPenalty = itemView.findViewById(R.id.cdp_str_liquidation_penalty);
        itemCollateralType = itemView.findViewById(R.id.cdp_market_type);
    }

    @Override
    public void onBindOtherCdp(Context context, CollateralParam otherCdp) {
        itemCollateralType.setText(otherCdp.type.toUpperCase());
        itemTitleMarket.setText(otherCdp.getDpMarketId());
        itemCollateralRate.setText(WDp.getPercentDp(otherCdp.getDpLiquidationRatio(), 2));
        itemStabilityFee.setText(WDp.getPercentDp(otherCdp.getDpStabilityFee(), 2));
        itemLiquidationPenalty.setText(WDp.getPercentDp(otherCdp.getDpLiquidationPenalty(), 2));

        try {
            Picasso.get().load(KAVA_CDP_IMG_URL +  otherCdp.getImagePath()) .fit().into(itemImgMarket);
        } catch (Exception e) { }

        itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, CdpDetail5Activity.class);
                intent.putExtra("collateralParamType", otherCdp.type);
//                intent.putExtra("marketId", otherCdp.liquidation_market_id);
                context.startActivity(intent);
            }
        });
    }
}
