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
import osmosis.gamm.v1beta1.BalancerPool;
import sifnode.clp.v1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.DAppsList5Activity;
import wannabit.io.cosmostaion.activities.txs.osmosis.LabsListActivity;
import wannabit.io.cosmostaion.activities.txs.sif.SifDexListActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

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
    public void onBindOsmoOtherPool(Context context, BaseActivity activity, BaseData baseData, BalancerPool.Pool otherPool) {
        final ChainConfig chainConfig = ChainFactory.getChain(BaseChain.OSMOSIS_MAIN);
        itemPoolTypeLayer.setVisibility(View.VISIBLE);
        itemPoolImgLayer.setVisibility(View.GONE);
        Coin coin0 = new Coin(otherPool.getPoolAssets(0).getToken().getDenom(), otherPool.getPoolAssets(0).getToken().getAmount());
        Coin coin1 = new Coin(otherPool.getPoolAssets(1).getToken().getDenom(), otherPool.getPoolAssets(1).getToken().getAmount());

        itemPoolType.setText("#" + otherPool.getId() + " " + WDp.getDpSymbol(baseData, chainConfig, coin0.denom) + "/" + WDp.getDpSymbol(baseData, chainConfig, coin1.denom));

        itemTotalDepositValue.setText("" + WDp.usdValue(baseData, baseData.getBaseDenom(coin0.denom), new BigDecimal(coin0.amount), WDp.getDenomDecimal(baseData, chainConfig, coin0.denom)));

        BigDecimal coin0Value = WDp.usdValue(baseData, baseData.getBaseDenom(coin0.denom), new BigDecimal(coin0.amount), WDp.getDenomDecimal(baseData, chainConfig, coin0.denom));
        BigDecimal coin1Value = WDp.usdValue(baseData, baseData.getBaseDenom(coin1.denom), new BigDecimal(coin1.amount), WDp.getDenomDecimal(baseData, chainConfig, coin1.denom));
        BigDecimal PoolValue = coin0Value.add(coin1Value);
        itemTotalDepositValue.setText(WDp.getDpRawDollor(context, PoolValue, 2));

        WDp.setDpSymbol(context, baseData, chainConfig, coin0.denom, itemTotalDepositSymbol0);
        WDp.setDpSymbol(context, baseData, chainConfig, coin1.denom, itemTotalDepositSymbol1);
        itemTotalDepositAmount0.setText(WDp.getDpAmount2(new BigDecimal(coin0.amount), WDp.getDenomDecimal(baseData, chainConfig, coin0.denom), 6));
        itemTotalDepositAmount1.setText(WDp.getDpAmount2(new BigDecimal(coin1.amount), WDp.getDenomDecimal(baseData, chainConfig, coin1.denom), 6));

        BigDecimal availableCoin0 = baseData.getAvailable(coin0.denom);
        Coin Coin0 = new Coin(otherPool.getPoolAssets(0).getToken().getDenom(), availableCoin0.toPlainString());
        BigDecimal availableCoin1 = baseData.getAvailable(coin1.denom);
        Coin Coin1 = new Coin(otherPool.getPoolAssets(1).getToken().getDenom(), availableCoin1.toPlainString());

        WDp.setDpSymbol(context, baseData, chainConfig, Coin0.denom, itemMyAvailableSymbol0);
        WDp.setDpSymbol(context, baseData, chainConfig, Coin1.denom, itemMyAvailableSymbol1);
        itemMyAvailableAmount0.setText(WDp.getDpAmount2(new BigDecimal(Coin0.amount), WDp.getDenomDecimal(baseData, chainConfig, Coin0.denom), 6));
        itemMyAvailableAmount1.setText(WDp.getDpAmount2(new BigDecimal(Coin1.amount), WDp.getDenomDecimal(baseData, chainConfig, Coin1.denom), 6));

        itemRoot.setOnClickListener(v -> ((LabsListActivity) activity).onCheckStartJoinPool(otherPool.getId()));
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

    @Override
    public void onBindSifOtherPool(Context context, SifDexListActivity activity, BaseData baseData, Types.Pool otherPool) {
        final ChainConfig chainConfig = ChainFactory.getChain(BaseChain.SIF_MAIN);
        itemPoolTypeLayer.setVisibility(View.GONE);
        itemPoolImgLayer.setVisibility(View.VISIBLE);

        int rowanDecimal = WDp.getDenomDecimal(baseData, chainConfig, chainConfig.mainDenom());
        BigDecimal rowanAmount = new BigDecimal(otherPool.getNativeAssetBalance());

        BigDecimal externalAmount = new BigDecimal(otherPool.getExternalAssetBalance());
        String exteranlDenom = otherPool.getExternalAsset().getSymbol();
        int externalDecimal = WDp.getDenomDecimal(baseData, chainConfig, exteranlDenom);
        BigDecimal poolValue = WUtil.getSifPoolValue(baseData, otherPool);

        WDp.setDpSymbolImg(baseData, chainConfig, exteranlDenom, itemExternalImg);
        itemPoolSifType.setText("ROWAN : " + WDp.getDpSymbol(baseData, chainConfig, exteranlDenom).toUpperCase());
        itemTotalDepositValue.setText(WDp.getDpRawDollor(context, poolValue, 2));

        WDp.setDpSymbol(context, baseData, chainConfig, chainConfig.mainDenom(), itemTotalDepositSymbol0);
        WDp.setDpSymbol(context, baseData, chainConfig, exteranlDenom, itemTotalDepositSymbol1);
        itemTotalDepositAmount0.setText(WDp.getDpAmount2(context, rowanAmount, rowanDecimal, 6));
        if ("UNKNOWN".equalsIgnoreCase(WDp.getDpSymbol(baseData, chainConfig, exteranlDenom))) {
            itemTotalDepositAmount1.setText(WDp.getDpAmount2(context, BigDecimal.ZERO, externalDecimal, 6));
        } else {
            itemTotalDepositAmount1.setText(WDp.getDpAmount2(context, externalAmount, externalDecimal, 6));
        }

        //dp available
        BigDecimal availableRowan = baseData.getAvailable(chainConfig.mainDenom());
        BigDecimal availableExternal = baseData.getAvailable(exteranlDenom);
        WDp.setDpSymbol(context, baseData, chainConfig, chainConfig.mainDenom(), itemMyAvailableSymbol0);
        WDp.setDpSymbol(context, baseData, chainConfig, exteranlDenom, itemMyAvailableSymbol1);
        itemMyAvailableAmount0.setText(WDp.getDpAmount2(context, availableRowan, rowanDecimal, 6));
        itemMyAvailableAmount1.setText(WDp.getDpAmount2(context, availableExternal, externalDecimal, 6));

        itemRoot.setOnClickListener(v -> ((SifDexListActivity) activity).onCheckStartDepositPool(otherPool));
    }
}
