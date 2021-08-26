package wannabit.io.cosmostaion.widget.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.math.BigDecimal;

import osmosis.gamm.v1beta1.PoolOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.LabsListActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.fragment.chains.osmosis.ListPoolFragment;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class PoolOtherHolder extends BaseHolder {
    CardView itemRoot;
    TextView itemPoolId;
    TextView itemPoolType;
    TextView itemTotalLiquidityValue;
    TextView itemTotalLiquidityAmount1, itemTotalLiquiditySymbol1, itemTotalLiquidityAmount2, itemTotalLiquiditySymbol2;
    TextView itemMyAvailableAmount1, itemMyAvailableSymbol1, itemMyAvailableAmount2, itemMyAvailableSymbol2;

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

        itemMyAvailableAmount1  = itemView.findViewById(R.id.mypool_amount1);
        itemMyAvailableSymbol1  = itemView.findViewById(R.id.mypool_symbol1);
        itemMyAvailableAmount2  = itemView.findViewById(R.id.mypool_amount2);
        itemMyAvailableSymbol2  = itemView.findViewById(R.id.mypool_symbol2);
    }

    @Override
    public void onBindOtherPool(Context context, BaseActivity activity, BaseData baseData, PoolOuterClass.Pool otherPool) {
        Coin coin0 = new Coin(otherPool.getPoolAssets(0).getToken().getDenom(), otherPool.getPoolAssets(0).getToken().getAmount());
        Coin coin1 = new Coin(otherPool.getPoolAssets(1).getToken().getDenom(), otherPool.getPoolAssets(1).getToken().getAmount());

        itemPoolId.setText("POOL #" + otherPool.getId());
        itemPoolType.setText(WUtil.dpOsmosisTokenName(coin0.denom) + " / " + WUtil.dpOsmosisTokenName(coin1.denom));

        itemTotalLiquidityValue.setText("" + WDp.usdValue(baseData, baseData.getBaseDenom(coin0.denom), new BigDecimal(coin0.amount), WUtil.getOsmosisCoinDecimal(coin0.denom)));

        BigDecimal coin0Value = WDp.usdValue(baseData, baseData.getBaseDenom(coin0.denom), new BigDecimal(coin0.amount), WUtil.getOsmosisCoinDecimal(coin0.denom));
        BigDecimal coin1Value = WDp.usdValue(baseData, baseData.getBaseDenom(coin1.denom), new BigDecimal(coin1.amount), WUtil.getOsmosisCoinDecimal(coin1.denom));
        BigDecimal PoolValue = coin0Value.add(coin1Value);
        itemTotalLiquidityValue.setText(WDp.getDpRawDollor(context, PoolValue, 2));

        WDp.showCoinDp(context, coin0, itemTotalLiquiditySymbol1, itemTotalLiquidityAmount1, BaseChain.OSMOSIS_MAIN);
        WDp.showCoinDp(context, coin1, itemTotalLiquiditySymbol2, itemTotalLiquidityAmount2, BaseChain.OSMOSIS_MAIN);

        BigDecimal availableCoin0 = baseData.getAvailable(coin0.denom);
        Coin Coin0 = new Coin(otherPool.getPoolAssets(0).getToken().getDenom(), availableCoin0.toPlainString());
        BigDecimal availableCoin1 = baseData.getAvailable(coin1.denom);
        Coin Coin1 = new Coin(otherPool.getPoolAssets(1).getToken().getDenom(), availableCoin1.toPlainString());

        WDp.showCoinDp(context, Coin0, itemMyAvailableSymbol1, itemMyAvailableAmount1, BaseChain.OSMOSIS_MAIN);
        WDp.showCoinDp(context, Coin1, itemMyAvailableSymbol2, itemMyAvailableAmount2, BaseChain.OSMOSIS_MAIN);

        itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LabsListActivity)activity).onCheckStartJoinPool(otherPool.getId());
            }
        });
    }
}
