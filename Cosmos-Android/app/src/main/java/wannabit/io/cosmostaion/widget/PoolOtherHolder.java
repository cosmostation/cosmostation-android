package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.math.BigDecimal;

import osmosis.gamm.v1beta1.PoolOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.fragment.chains.osmosis.ListPoolFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class PoolOtherHolder extends BaseHolder {
    CardView itemRoot;
    TextView itemPoolId;
    TextView itemPoolType;
    TextView itemTotalLiquidityValue;
    TextView itemTotalLiquidityAmount1, itemTotalLiquiditySymbol1, itemTotalLiquidityAmount2, itemTotalLiquiditySymbol2;

    public PoolOtherHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView.findViewById(R.id.card_pool_all);
        itemPoolId = itemView.findViewById(R.id.pool_market_id);
        itemPoolType = itemView.findViewById(R.id.pool_market_type);
        itemTotalLiquidityValue = itemView.findViewById(R.id.pool_total_liquidity_value);
        itemTotalLiquidityAmount1 = itemView.findViewById(R.id.pool_total_liquidity_amount1);
        itemTotalLiquiditySymbol1 = itemView.findViewById(R.id.pool_total_liquidity_symbol1);
        itemTotalLiquidityAmount2 = itemView.findViewById(R.id.pool_total_liquidity_amount2);
        itemTotalLiquiditySymbol2 = itemView.findViewById(R.id.pool_total_liquidity_symbol2);
    }

    @Override
    public void onBindOtherPool(Context context, ListPoolFragment fragment, BaseData baseData, PoolOuterClass.Pool otherPool) {
        Coin coin0 = new Coin(otherPool.getPoolAssets(0).getToken().getDenom(), otherPool.getPoolAssets(0).getToken().getAmount());
        Coin coin1 = new Coin(otherPool.getPoolAssets(1).getToken().getDenom(), otherPool.getPoolAssets(1).getToken().getAmount());

        itemPoolId.setText("POOL #" + otherPool.getId());
        itemPoolType.setText(WUtil.getOsmosisTokenName(coin0.denom) + " / " + WUtil.getOsmosisTokenName(coin1.denom));

        itemTotalLiquidityValue.setText("" + WDp.usdValue(baseData, baseData.getBaseDenom(coin0.denom), new BigDecimal(coin0.amount), WUtil.getOsmosisCoinDecimal(coin0.denom)));

        BigDecimal coin0Value = WDp.usdValue(baseData, baseData.getBaseDenom(coin0.denom), new BigDecimal(coin0.amount), WUtil.getOsmosisCoinDecimal(coin0.denom));
        BigDecimal coin1Value = WDp.usdValue(baseData, baseData.getBaseDenom(coin1.denom), new BigDecimal(coin1.amount), WUtil.getOsmosisCoinDecimal(coin1.denom));
        BigDecimal PoolValue = coin0Value.add(coin1Value);
        itemTotalLiquidityValue.setText(WDp.getDpRawDollor(context, PoolValue, 2));

        WDp.showCoinDp(context, coin0, itemTotalLiquiditySymbol1, itemTotalLiquidityAmount1, BaseChain.OSMOSIS_MAIN);
        WDp.showCoinDp(context, coin1, itemTotalLiquiditySymbol2, itemTotalLiquidityAmount2, BaseChain.OSMOSIS_MAIN);

        itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { fragment.onCheckStartJoinPool(); return; }
        });
    }
}
