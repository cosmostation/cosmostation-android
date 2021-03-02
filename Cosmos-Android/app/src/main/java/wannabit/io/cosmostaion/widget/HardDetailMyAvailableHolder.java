package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.kava.HardParam;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
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
    public void onBindHardDetailAvailable(Context context, BaseData baseData, BaseChain chain, String denom) {
        final HardParam hardParam                       = baseData.mHardParam;
        final HardParam.HardMoneyMarket hardMoneyMarket = hardParam.getHardMoneyMarket(denom);

        if (denom.equals(TOKEN_KAVA) || denom.equals(TOKEN_HARD)) {
            mAssetDepositLayer.setVisibility(View.GONE);
            mDepositValue.setVisibility(View.GONE);
        }
        BigDecimal targetAvailable = WDp.getAvailableCoin(baseData.mBalances, denom);
        BigDecimal kavaAvailable = WDp.getAvailableCoin(baseData.mBalances, TOKEN_KAVA);

        WDp.showCoinDp(context, denom, targetAvailable.toPlainString(), mAssetDepositDenom, mAssetDepositAmount, chain);
        WDp.showCoinDp(context, TOKEN_KAVA, kavaAvailable.toPlainString(), mAssetKavaDenom, mAssetKavaAmount, chain);

        // Display each usd value
        MarketPrice targetPrice = baseData.mKavaTokenPrices.get(denom + ":usd:30");
        MarketPrice kavaPrice = baseData.mKavaTokenPrices.get("kava:usd:30");
        BigDecimal targetValue = BigDecimal.ZERO;
        BigDecimal kavaValue = BigDecimal.ZERO;
        if (targetPrice != null) {
            targetValue = targetAvailable.movePointLeft(WUtil.getKavaCoinDecimal(denom)).multiply(new BigDecimal(targetPrice.price));
        }
        if (kavaPrice != null) {
            kavaValue = targetAvailable.movePointLeft(6).multiply(new BigDecimal(kavaPrice.price));
        }
        mDepositValue.setText(WDp.getDpRawDollor(context, targetValue, 2));
        mKavaValue.setText(WDp.getDpRawDollor(context, kavaValue, 2));

        try {
            Picasso.get().load(KAVA_COIN_IMG_URL + denom + ".png").fit().into(mAssetDepositImg);
        } catch (Exception e) { }

    }
}
