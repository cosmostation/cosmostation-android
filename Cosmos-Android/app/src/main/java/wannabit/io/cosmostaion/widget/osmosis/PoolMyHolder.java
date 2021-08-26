package wannabit.io.cosmostaion.widget.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.math.BigDecimal;
import java.math.RoundingMode;

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

public class PoolMyHolder extends BaseHolder {
    CardView itemRoot;
    TextView itemMyPoolId;
    TextView itemMyPoolType;
    TextView itemMyTotalLiquidityValue;
    TextView itemMyTotalLiquidityAmount1, itemMyTotalLiquiditySymbol1, itemMyTotalLiquidityAmount2, itemMyTotalLiquiditySymbol2;
    TextView itemMyLpAvailableAmount, itemMyLpAvailableSymbol;
    TextView itemMyAvailableAmount1, itemMyAvailableSymbol1, itemMyAvailableAmount2, itemMyAvailableSymbol2;
    TextView itemMypoolLpValue;

    public PoolMyHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView.findViewById(R.id.card_pool_all);
        itemMyPoolId = itemView.findViewById(R.id.mypool_market_id);
        itemMyPoolType = itemView.findViewById(R.id.mypool_market_type);
        itemMyTotalLiquidityValue = itemView.findViewById(R.id.mypool_total_liquidity_value);
        itemMyTotalLiquidityAmount1 = itemView.findViewById(R.id.mypool_total_liquidity_amount1);
        itemMyTotalLiquiditySymbol1 = itemView.findViewById(R.id.mypool_total_liquidity_symbol1);
        itemMyTotalLiquidityAmount2 = itemView.findViewById(R.id.mypool_total_liquidity_amount2);
        itemMyTotalLiquiditySymbol2 = itemView.findViewById(R.id.mypool_total_liquidity_symbol2);

        itemMyAvailableAmount1  = itemView.findViewById(R.id.mypool_amount1);
        itemMyAvailableSymbol1  = itemView.findViewById(R.id.mypool_symbol1);
        itemMyAvailableAmount2  = itemView.findViewById(R.id.mypool_amount2);
        itemMyAvailableSymbol2  = itemView.findViewById(R.id.mypool_symbol2);
        itemMyLpAvailableAmount = itemView.findViewById(R.id.mypool_lp_available_amount);
        itemMyLpAvailableSymbol = itemView.findViewById(R.id.mypool_lp_available_symbol);
        itemMypoolLpValue       = itemView.findViewById(R.id.mypool_lp_value);
    }

    @Override
    public void onBindMyPool(Context context, BaseActivity activity, BaseData baseData, PoolOuterClass.Pool myPool) {
        Coin coin0 = new Coin(myPool.getPoolAssets(0).getToken().getDenom(), myPool.getPoolAssets(0).getToken().getAmount());
        Coin coin1 = new Coin(myPool.getPoolAssets(1).getToken().getDenom(), myPool.getPoolAssets(1).getToken().getAmount());

        itemMyPoolId.setText("POOL #" + myPool.getId());
        itemMyPoolType.setText(WUtil.dpOsmosisTokenName(coin0.denom) + " / " + WUtil.dpOsmosisTokenName(coin1.denom));

        BigDecimal coin0Value = WDp.usdValue(baseData, baseData.getBaseDenom(coin0.denom), new BigDecimal(coin0.amount), WUtil.getOsmosisCoinDecimal(coin0.denom));
        BigDecimal coin1Value = WDp.usdValue(baseData, baseData.getBaseDenom(coin1.denom), new BigDecimal(coin1.amount), WUtil.getOsmosisCoinDecimal(coin1.denom));
        BigDecimal PoolValue = coin0Value.add(coin1Value);
        itemMyTotalLiquidityValue.setText(WDp.getDpRawDollor(context, PoolValue, 2));

        WDp.showCoinDp(context, coin0, itemMyTotalLiquiditySymbol1, itemMyTotalLiquidityAmount1, BaseChain.OSMOSIS_MAIN);
        WDp.showCoinDp(context, coin1, itemMyTotalLiquiditySymbol2, itemMyTotalLiquidityAmount2, BaseChain.OSMOSIS_MAIN);

        BigDecimal availableCoin0 = baseData.getAvailable(coin0.denom);
        Coin Coin0 = new Coin(myPool.getPoolAssets(0).getToken().getDenom(), availableCoin0.toPlainString());
        BigDecimal availableCoin1 = baseData.getAvailable(coin1.denom);
        Coin Coin1 = new Coin(myPool.getPoolAssets(1).getToken().getDenom(), availableCoin1.toPlainString());
        BigDecimal lpCoin = baseData.getAvailable("gamm/pool/" + myPool.getId());

        WDp.showCoinDp(context, Coin0, itemMyAvailableSymbol1, itemMyAvailableAmount1, BaseChain.OSMOSIS_MAIN);
        WDp.showCoinDp(context, Coin1, itemMyAvailableSymbol2, itemMyAvailableAmount2, BaseChain.OSMOSIS_MAIN);

        itemMyLpAvailableAmount.setText(WDp.getDpAmount2(context, lpCoin, 18, 6));
        itemMyLpAvailableSymbol.setText("GAMM-" + myPool.getId());

        BigDecimal lpCoinPrice = WUtil.getOsmoLpTokenPerUsdPrice(baseData, myPool);
        BigDecimal lpCoinValue = new BigDecimal(lpCoin.toPlainString()).multiply(lpCoinPrice).movePointLeft(18).setScale(2,RoundingMode.DOWN);
        String formatted = "$ " + lpCoinValue.toPlainString();
        itemMypoolLpValue.setText(formatted);

        itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WLog.w("PoolMyHolder onClick ");
                ((LabsListActivity)activity).onClickMyPool(myPool.getId());
            }
        });
    }
}
