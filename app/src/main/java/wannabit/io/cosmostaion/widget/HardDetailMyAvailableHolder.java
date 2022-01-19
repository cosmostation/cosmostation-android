package wannabit.io.cosmostaion.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;

import kava.hard.v1beta1.Hard;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.HardDetailActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

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
    public void onBindHardDetailAvailable(HardDetailActivity context, BaseData baseData, BaseChain chain, String denom) {
        final Hard.Params hardParam             = baseData.mHardParams;
        final Hard.MoneyMarket hardMoneyMarket  = WUtil.getHardMoneyMarket(hardParam, denom);

        if (denom.equals(TOKEN_KAVA)) {
            mAssetDepositLayer.setVisibility(View.GONE);
            mDepositValue.setVisibility(View.GONE);
        }
        BigDecimal targetAvailable = baseData.getAvailable(denom);
        BigDecimal kavaAvailable = baseData.getAvailable(TOKEN_KAVA);

        // Display each usd value
        BigDecimal targetPrice = BigDecimal.ZERO;
        if (!denom.equals("usdx")) {
            targetPrice = baseData.getKavaOraclePrice(hardMoneyMarket.getSpotMarketId());
        } else {
            targetPrice = BigDecimal.ONE;
        }
        BigDecimal targetValue = targetAvailable.movePointLeft(WUtil.getKavaCoinDecimal(baseData, denom)).multiply(targetPrice);
        WDp.showCoinDp(context, baseData, denom, targetAvailable.toPlainString(), mAssetDepositDenom, mAssetDepositAmount, chain);
        mDepositValue.setText(WDp.getDpRawDollor(context, targetValue, 2));
        WUtil.DpKavaTokenImg(baseData, mAssetDepositImg, denom);

        BigDecimal kavaValue = BigDecimal.ZERO;
        kavaValue = kavaAvailable.movePointLeft(6).multiply(baseData.getKavaOraclePrice("kava:usd:30"));
        WDp.showCoinDp(context, baseData, TOKEN_KAVA, kavaAvailable.toPlainString(), mAssetKavaDenom, mAssetKavaAmount, chain);
        mKavaValue.setText(WDp.getDpRawDollor(context, kavaValue, 2));

    }
}
