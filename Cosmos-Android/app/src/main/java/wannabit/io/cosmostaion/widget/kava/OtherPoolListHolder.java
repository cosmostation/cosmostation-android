package wannabit.io.cosmostaion.widget.kava;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.DAppsList5Activity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.kava.SwapPool;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class OtherPoolListHolder extends BaseHolder {
    CardView itemRoot;
    TextView itemPoolType;
    TextView itemTotalLiquidityAmount, itemTotalLiquidityValue;
    TextView itemTotalLiquidityAmount1, itemTotalLiquiditySymbol1, itemTotalLiquidityAmount2, itemTotalLiquiditySymbol2;

    public OtherPoolListHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView.findViewById(R.id.card_pool_all);
        itemPoolType = itemView.findViewById(R.id.pool_market_type);
        itemTotalLiquidityAmount = itemView.findViewById(R.id.pool_total_liquidity_amount);
        itemTotalLiquidityValue = itemView.findViewById(R.id.pool_total_liquidity_value);
        itemTotalLiquidityAmount1 = itemView.findViewById(R.id.pool_total_liquidity_amount1);
        itemTotalLiquiditySymbol1 = itemView.findViewById(R.id.pool_total_liquidity_symbol1);
        itemTotalLiquidityAmount2 = itemView.findViewById(R.id.pool_total_liquidity_amount2);
        itemTotalLiquiditySymbol2 = itemView.findViewById(R.id.pool_total_liquidity_symbol2);
    }

    @Override
    public void onBindOtherPool(Context context, BaseActivity activity, BaseData baseData, SwapPool otherPool) {
        Coin coin0 = otherPool.coins.get(0);
        Coin coin1 = otherPool.coins.get(1);
        int coin0Decimal = WUtil.getKavaCoinDecimal(coin0.denom);
        int coin1Decimal = WUtil.getKavaCoinDecimal(coin1.denom);
        BigDecimal coin0price = WDp.getKavaPriceFeed(baseData, coin0.denom);
        BigDecimal coin1price = WDp.getKavaPriceFeed(baseData, coin1.denom);
        BigDecimal coin0value = new BigDecimal(coin0.amount).multiply(coin0price).movePointLeft(coin0Decimal).setScale(2, RoundingMode.DOWN);
        BigDecimal coin1value = new BigDecimal(coin1.amount).multiply(coin1price).movePointLeft(coin0Decimal).setScale(2, RoundingMode.DOWN);

        itemPoolType.setText(otherPool.name.toUpperCase());

        BigDecimal poolValue = coin0value.add(coin1value);
        itemTotalLiquidityAmount.setText(WDp.getDpAmount2(context, new BigDecimal(otherPool.total_shares), 6, 6));
        itemTotalLiquidityValue.setText(WDp.getDpRawDollor(context, poolValue, 2));

        WUtil.dpKavaTokenName(context, itemTotalLiquiditySymbol1, coin0.denom);
        WUtil.dpKavaTokenName(context, itemTotalLiquiditySymbol2, coin1.denom);
        itemTotalLiquidityAmount1.setText(WDp.getDpAmount2(context, new BigDecimal(coin0.amount), coin0Decimal, 6));
        itemTotalLiquidityAmount2.setText(WDp.getDpAmount2(context, new BigDecimal(coin1.amount), coin1Decimal, 6));

        itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DAppsList5Activity)activity).onCheckStartJoinPool(otherPool);
            }
        });
    }
}
