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
import wannabit.io.cosmostaion.model.kava.SwapDeposit;
import wannabit.io.cosmostaion.model.kava.SwapPool;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class MyPoolListHolder extends BaseHolder {
    CardView itemRoot;
    TextView itemMyPoolType;
    TextView itemMyTotalLiquidityAmount, itemMyTotalLiquidityValue;
    TextView itemMyTotalLiquidityAmount1, itemMyTotalLiquiditySymbol1, itemMyTotalLiquidityAmount2, itemMyTotalLiquiditySymbol2;
    TextView itemMyShareValue;
    TextView itemMyAvailableAmount0, itemMyAvailableSymbol0, itemMyAvailableAmount1, itemMyAvailableSymbol1;

    public MyPoolListHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView.findViewById(R.id.card_pool_all);
        itemMyPoolType = itemView.findViewById(R.id.mypool_market_type);
        itemMyTotalLiquidityAmount  = itemView.findViewById(R.id.mypool_total_liquidity_amount);
        itemMyTotalLiquidityValue   = itemView.findViewById(R.id.mypool_total_liquidity_value);
        itemMyTotalLiquidityAmount1 = itemView.findViewById(R.id.mypool_total_liquidity_amount1);
        itemMyTotalLiquiditySymbol1 = itemView.findViewById(R.id.mypool_total_liquidity_symbol1);
        itemMyTotalLiquidityAmount2 = itemView.findViewById(R.id.mypool_total_liquidity_amount2);
        itemMyTotalLiquiditySymbol2 = itemView.findViewById(R.id.mypool_total_liquidity_symbol2);

        itemMyShareValue            = itemView.findViewById(R.id.mypool_share_liquidity_value);
        itemMyAvailableAmount0      = itemView.findViewById(R.id.mypool_deposit_amount0);
        itemMyAvailableSymbol0      = itemView.findViewById(R.id.mypool_deposit_symbol0);
        itemMyAvailableAmount1      = itemView.findViewById(R.id.mypool_deposit_amount1);
        itemMyAvailableSymbol1      = itemView.findViewById(R.id.mypool_deposit_symbol1);
    }

    @Override
    public void onBindMyPool(Context context, BaseActivity activity, BaseData baseData, SwapPool myPool, SwapDeposit myDeposit) {
        Coin coin0 = myPool.coins.get(0);
        Coin coin1 = myPool.coins.get(1);
        int coin0Decimal = WUtil.getKavaCoinDecimal(coin0.denom);
        int coin1Decimal = WUtil.getKavaCoinDecimal(coin1.denom);
        BigDecimal coin0price = WDp.getKavaPriceFeed(baseData, coin0.denom);
        BigDecimal coin1price = WDp.getKavaPriceFeed(baseData, coin1.denom);

        BigDecimal coin0Value = new BigDecimal(coin0.amount).multiply(coin0price).movePointLeft(coin0Decimal).setScale(2, RoundingMode.DOWN);
        BigDecimal coin1Value = new BigDecimal(coin1.amount).multiply(coin1price).movePointLeft(coin0Decimal).setScale(2, RoundingMode.DOWN);

        itemMyPoolType.setText(myPool.name.toUpperCase());
        BigDecimal poolValue = coin0Value.add(coin1Value);
        itemMyTotalLiquidityAmount.setText(WDp.getDpAmount2(context, new BigDecimal(myPool.total_shares), 6, 6));
        itemMyTotalLiquidityValue.setText(WDp.getDpRawDollor(context, poolValue, 2));

        WUtil.dpKavaTokenName(context, itemMyTotalLiquiditySymbol1, coin0.denom);
        WUtil.dpKavaTokenName(context, itemMyTotalLiquiditySymbol2, coin1.denom);
        itemMyTotalLiquidityAmount1.setText(WDp.getDpAmount2(context, new BigDecimal(coin0.amount), coin0Decimal, 6));
        itemMyTotalLiquidityAmount2.setText(WDp.getDpAmount2(context, new BigDecimal(coin1.amount), coin1Decimal, 6));

        Coin my0 = myDeposit.shares_value.get(0);
        Coin my1 = myDeposit.shares_value.get(1);
        BigDecimal my0Value = new BigDecimal(my0.amount).multiply(coin0price).movePointLeft(-coin0Decimal).setScale(2, RoundingMode.DOWN);
        BigDecimal my1Value = new BigDecimal(my1.amount).multiply(coin1price).movePointLeft(-coin0Decimal).setScale(2, RoundingMode.DOWN);

        BigDecimal myShareValue = my0Value.add(my1Value);
        itemMyShareValue.setText(WDp.getDpRawDollor(context, myShareValue, 2));

        WUtil.dpKavaTokenName(context, itemMyAvailableSymbol0, my0.denom);
        WUtil.dpKavaTokenName(context, itemMyAvailableSymbol1, my1.denom);
        itemMyAvailableAmount0.setText(WDp.getDpAmount2(context, new BigDecimal(my0.amount), coin0Decimal, 6));
        itemMyAvailableAmount1.setText(WDp.getDpAmount2(context, new BigDecimal(my1.amount), coin1Decimal, 6));

        itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DAppsList5Activity)activity).onClickMyPool(myPool, myDeposit);
            }
        });
    }
}
