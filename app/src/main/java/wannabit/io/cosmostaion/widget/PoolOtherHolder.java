package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.math.BigDecimal;
import java.math.RoundingMode;

import cosmos.base.v1beta1.CoinOuterClass;
import kava.swap.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.DAppsList5Activity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.utils.WDp;

public class PoolOtherHolder extends BaseHolder {
    CardView itemRoot;
    RelativeLayout itemPoolTypeLayer, itemPoolImgLayer;
    TextView itemPoolType, itemPoolSifType;
    ImageView itemExternalImg;
    TextView itemTotalDepositValue;
    TextView itemTotalDepositAmount0, itemTotalDepositSymbol0, itemTotalDepositAmount1, itemTotalDepositSymbol1;
    TextView itemMyAvailableAmount0, itemMyAvailableSymbol0, itemMyAvailableAmount1, itemMyAvailableSymbol1;

    public PoolOtherHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView.findViewById(R.id.card_pool_all);
        itemPoolTypeLayer = itemView.findViewById(R.id.pool_type_layer);
        itemPoolImgLayer = itemView.findViewById(R.id.pool_img_layer);
        itemPoolType = itemView.findViewById(R.id.pool_market_type);
        itemExternalImg = itemView.findViewById(R.id.pool_external_img);
        itemPoolSifType = itemView.findViewById(R.id.pool_type);
        itemTotalDepositValue = itemView.findViewById(R.id.pool_total_liquidity_value);
        itemTotalDepositAmount0 = itemView.findViewById(R.id.pool_total_liquidity_amount1);
        itemTotalDepositSymbol0 = itemView.findViewById(R.id.pool_total_liquidity_symbol1);
        itemTotalDepositAmount1 = itemView.findViewById(R.id.pool_total_liquidity_amount2);
        itemTotalDepositSymbol1 = itemView.findViewById(R.id.pool_total_liquidity_symbol2);

        itemMyAvailableAmount0 = itemView.findViewById(R.id.mypool_amount1);
        itemMyAvailableSymbol0 = itemView.findViewById(R.id.mypool_symbol1);
        itemMyAvailableAmount1 = itemView.findViewById(R.id.mypool_amount2);
        itemMyAvailableSymbol1 = itemView.findViewById(R.id.mypool_symbol2);
    }

    @Override
    public void onBindKavaOtherPool(Context context, BaseActivity activity, BaseData baseData, QueryOuterClass.PoolResponse otherPool) {
        final ChainConfig chainConfig = ChainFactory.getChain(activity.mBaseChain);
        itemPoolTypeLayer.setVisibility(View.VISIBLE);
        itemPoolImgLayer.setVisibility(View.GONE);
        CoinOuterClass.Coin coin0 = otherPool.getCoins(0);
        CoinOuterClass.Coin coin1 = otherPool.getCoins(1);
        int coin0Decimal = WDp.getDenomDecimal(baseData, chainConfig, coin0.getDenom());
        int coin1Decimal = WDp.getDenomDecimal(baseData, chainConfig, coin1.getDenom());
        BigDecimal coin0price = WDp.getKavaPriceFeed(baseData, coin0.getDenom());
        BigDecimal coin1price = WDp.getKavaPriceFeed(baseData, coin1.getDenom());
        BigDecimal coin0value = new BigDecimal(coin0.getAmount()).multiply(coin0price).movePointLeft(coin0Decimal).setScale(2, RoundingMode.DOWN);
        BigDecimal coin1value = new BigDecimal(coin1.getAmount()).multiply(coin1price).movePointLeft(coin1Decimal).setScale(2, RoundingMode.DOWN);

        itemPoolType.setText(WDp.getDpSymbol(baseData, chainConfig, coin0.getDenom()) + " : " + WDp.getDpSymbol(baseData, chainConfig, coin1.getDenom()));

        // Total deposit
        BigDecimal poolValue = coin0value.add(coin1value);
        itemTotalDepositValue.setText(WDp.getDpRawDollor(context, poolValue, 2));

        WDp.setDpSymbol(context, baseData, chainConfig, coin0.getDenom(), itemTotalDepositSymbol0);
        WDp.setDpSymbol(context, baseData, chainConfig, coin1.getDenom(), itemTotalDepositSymbol1);
        itemTotalDepositAmount0.setText(WDp.getDpAmount2(context, new BigDecimal(coin0.getAmount()), coin0Decimal, 6));
        itemTotalDepositAmount1.setText(WDp.getDpAmount2(context, new BigDecimal(coin1.getAmount()), coin1Decimal, 6));

        // available
        BigDecimal availableCoin0 = baseData.getAvailable(coin0.getDenom());
        BigDecimal availableCoin1 = baseData.getAvailable(coin1.getDenom());

        WDp.setDpSymbol(context, baseData, chainConfig, coin0.getDenom(), itemMyAvailableSymbol0);
        WDp.setDpSymbol(context, baseData, chainConfig, coin1.getDenom(), itemMyAvailableSymbol1);
        itemMyAvailableAmount0.setText(WDp.getDpAmount2(context, availableCoin0, coin0Decimal, 6));
        itemMyAvailableAmount1.setText(WDp.getDpAmount2(context, availableCoin1, coin1Decimal, 6));

        itemRoot.setOnClickListener(v -> ((DAppsList5Activity) activity).onCheckStartJoinPool(otherPool));
    }
}
