package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.math.BigDecimal;
import java.math.RoundingMode;

import osmosis.gamm.v1beta1.PoolOuterClass;
import tendermint.liquidity.v1beta1.Liquidity;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.cosmos.GravityListActivity;
import wannabit.io.cosmostaion.activities.chains.kava.DAppsList5Activity;
import wannabit.io.cosmostaion.activities.chains.osmosis.LabsListActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.kava.SwapPool;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class PoolOtherHolder extends BaseHolder {
    CardView itemRoot;
    TextView itemPoolType;
    TextView itemTotalDepositValue;
    TextView itemTotalDepositAmount0, itemTotalDepositSymbol0, itemTotalDepositAmount1, itemTotalDepositSymbol1;
    TextView itemMyAvailableAmount0, itemMyAvailableSymbol0, itemMyAvailableAmount1, itemMyAvailableSymbol1;

    public PoolOtherHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView.findViewById(R.id.card_pool_all);
        itemPoolType = itemView.findViewById(R.id.pool_market_type);
        itemTotalDepositValue = itemView.findViewById(R.id.pool_total_liquidity_value);
        itemTotalDepositAmount0 = itemView.findViewById(R.id.pool_total_liquidity_amount1);
        itemTotalDepositSymbol0 = itemView.findViewById(R.id.pool_total_liquidity_symbol1);
        itemTotalDepositAmount1 = itemView.findViewById(R.id.pool_total_liquidity_amount2);
        itemTotalDepositSymbol1 = itemView.findViewById(R.id.pool_total_liquidity_symbol2);

        itemMyAvailableAmount0  = itemView.findViewById(R.id.mypool_amount1);
        itemMyAvailableSymbol0  = itemView.findViewById(R.id.mypool_symbol1);
        itemMyAvailableAmount1  = itemView.findViewById(R.id.mypool_amount2);
        itemMyAvailableSymbol1  = itemView.findViewById(R.id.mypool_symbol2);
    }

    @Override
    public void onBindOsmoOtherPool(Context context, BaseActivity activity, BaseData baseData, PoolOuterClass.Pool otherPool) {
        Coin coin0 = new Coin(otherPool.getPoolAssets(0).getToken().getDenom(), otherPool.getPoolAssets(0).getToken().getAmount());
        Coin coin1 = new Coin(otherPool.getPoolAssets(1).getToken().getDenom(), otherPool.getPoolAssets(1).getToken().getAmount());

        itemPoolType.setText(WUtil.dpOsmosisTokenName(coin0.denom) + " / " + WUtil.dpOsmosisTokenName(coin1.denom));

        itemTotalDepositValue.setText("" + WDp.usdValue(baseData, baseData.getBaseDenom(coin0.denom), new BigDecimal(coin0.amount), WUtil.getOsmosisCoinDecimal(coin0.denom)));

        BigDecimal coin0Value = WDp.usdValue(baseData, baseData.getBaseDenom(coin0.denom), new BigDecimal(coin0.amount), WUtil.getOsmosisCoinDecimal(coin0.denom));
        BigDecimal coin1Value = WDp.usdValue(baseData, baseData.getBaseDenom(coin1.denom), new BigDecimal(coin1.amount), WUtil.getOsmosisCoinDecimal(coin1.denom));
        BigDecimal PoolValue = coin0Value.add(coin1Value);
        itemTotalDepositValue.setText(WDp.getDpRawDollor(context, PoolValue, 2));

        WDp.showCoinDp(context, coin0, itemTotalDepositSymbol0, itemTotalDepositAmount0, BaseChain.OSMOSIS_MAIN);
        WDp.showCoinDp(context, coin1, itemTotalDepositSymbol1, itemTotalDepositAmount1, BaseChain.OSMOSIS_MAIN);

        BigDecimal availableCoin0 = baseData.getAvailable(coin0.denom);
        Coin Coin0 = new Coin(otherPool.getPoolAssets(0).getToken().getDenom(), availableCoin0.toPlainString());
        BigDecimal availableCoin1 = baseData.getAvailable(coin1.denom);
        Coin Coin1 = new Coin(otherPool.getPoolAssets(1).getToken().getDenom(), availableCoin1.toPlainString());

        WDp.showCoinDp(context, Coin0, itemMyAvailableSymbol0, itemMyAvailableAmount0, BaseChain.OSMOSIS_MAIN);
        WDp.showCoinDp(context, Coin1, itemMyAvailableSymbol1, itemMyAvailableAmount1, BaseChain.OSMOSIS_MAIN);

        itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((LabsListActivity)activity).onCheckStartJoinPool(otherPool.getId());
            }
        });
    }

    @Override
    public void onBindKavaOtherPool(Context context, BaseActivity activity, BaseData baseData, SwapPool otherPool) {
        Coin coin0 = otherPool.coins.get(0);
        Coin coin1 = otherPool.coins.get(1);
        int coin0Decimal = WUtil.getKavaCoinDecimal(coin0.denom);
        int coin1Decimal = WUtil.getKavaCoinDecimal(coin1.denom);
        BigDecimal coin0price = WDp.getKavaPriceFeed(baseData, coin0.denom);
        BigDecimal coin1price = WDp.getKavaPriceFeed(baseData, coin1.denom);
        BigDecimal coin0value = new BigDecimal(coin0.amount).multiply(coin0price).movePointLeft(coin0Decimal).setScale(2, RoundingMode.DOWN);
        BigDecimal coin1value = new BigDecimal(coin1.amount).multiply(coin1price).movePointLeft(coin1Decimal).setScale(2, RoundingMode.DOWN);

        itemPoolType.setText(otherPool.name.toUpperCase());

        // Total deposit
        BigDecimal poolValue = coin0value.add(coin1value);
        itemTotalDepositValue.setText(WDp.getDpRawDollor(context, poolValue, 2));

        WUtil.dpKavaTokenName(context, itemTotalDepositSymbol0, coin0.denom);
        WUtil.dpKavaTokenName(context, itemTotalDepositSymbol1, coin1.denom);
        itemTotalDepositAmount0.setText(WDp.getDpAmount2(context, new BigDecimal(coin0.amount), coin0Decimal, 6));
        itemTotalDepositAmount1.setText(WDp.getDpAmount2(context, new BigDecimal(coin1.amount), coin1Decimal, 6));

        // available
        BigDecimal availableCoin0 = baseData.availableAmount(coin0.denom);
        BigDecimal availableCoin1 = baseData.availableAmount(coin1.denom);

        WUtil.dpKavaTokenName(context, itemMyAvailableSymbol0, coin0.denom);
        WUtil.dpKavaTokenName(context, itemMyAvailableSymbol1, coin1.denom);
        itemMyAvailableAmount0.setText(WDp.getDpAmount2(context, availableCoin0, coin0Decimal, 6));
        itemMyAvailableAmount1.setText(WDp.getDpAmount2(context, availableCoin1, coin1Decimal, 6));

        itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((DAppsList5Activity)activity).onCheckStartJoinPool(otherPool);
            }
        });
    }

    @Override
    public void onBindGDexOtherPool(Context context, GravityListActivity activity, BaseData baseData, Liquidity.Pool otherPool) {
        String coin0Denom = otherPool.getReserveCoinDenoms(1);
        String coin1Denom = otherPool.getReserveCoinDenoms(0);
        BigDecimal coin0Amount = activity.getLpAmount(otherPool.getReserveAccountAddress(), coin0Denom);
        BigDecimal coin1Amount = activity.getLpAmount(otherPool.getReserveAccountAddress(), coin1Denom);
        int coin0Decimal = WUtil.getCosmosCoinDecimal(baseData, coin0Denom);
        int coin1Decimal = WUtil.getCosmosCoinDecimal(baseData, coin1Denom);

        itemPoolType.setText(WUtil.dpCosmosTokenName(baseData, coin0Denom) + " / " + WUtil.dpCosmosTokenName(baseData, coin1Denom));

        // Total deposit
        BigDecimal coin0Value = WDp.usdValue(baseData, baseData.getBaseDenom(coin0Denom), coin0Amount, WUtil.getCosmosCoinDecimal(baseData, coin0Denom));
        BigDecimal coin1Value = WDp.usdValue(baseData, baseData.getBaseDenom(coin1Denom), coin1Amount, WUtil.getCosmosCoinDecimal(baseData, coin1Denom));
        BigDecimal PoolValue = coin0Value.add(coin1Value);
        itemTotalDepositValue.setText(WDp.getDpRawDollor(context, PoolValue, 2));

        WUtil.dpCosmosTokenName(context, baseData, itemTotalDepositSymbol0, coin0Denom);
        WUtil.dpCosmosTokenName(context, baseData, itemTotalDepositSymbol1, coin1Denom);
        itemTotalDepositAmount0.setText(WDp.getDpAmount2(context, coin0Amount, coin0Decimal, 6));
        itemTotalDepositAmount1.setText(WDp.getDpAmount2(context, coin1Amount, coin1Decimal, 6));

        BigDecimal availableCoin0 = baseData.getAvailable(coin0Denom);
        Coin Coin0 = new Coin(coin0Denom, availableCoin0.toPlainString());
        BigDecimal availableCoin1 = baseData.getAvailable(coin1Denom);
        Coin Coin1 = new Coin(coin1Denom, availableCoin1.toPlainString());

        WDp.showCoinDp(context, Coin0, itemMyAvailableSymbol0, itemMyAvailableAmount0, BaseChain.COSMOS_MAIN);
        WDp.showCoinDp(context, Coin1, itemMyAvailableSymbol1, itemMyAvailableAmount1, BaseChain.COSMOS_MAIN);
    }
}
