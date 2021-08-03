package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.google.protobuf.StringValue;
import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.stream.Stream;

import osmosis.gamm.v1beta1.PoolOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.CdpDetail5Activity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.fragment.chains.osmosis.ListPoolFragment;
import wannabit.io.cosmostaion.model.kava.CollateralParam;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.model.kava.MyCdp;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_CDP_IMG_URL;

public class PoolMyHolder extends BaseHolder {
    CardView itemRoot;
    TextView itemMyPoolId;
    TextView itemMyPoolType;
    TextView itemMyTotalLiquidityValue;
    TextView itemMyTotalLiquidityAmount1, itemMyTotalLiquiditySymbol1, itemMyTotalLiquidityAmount2, itemMyTotalLiquiditySymbol2;
    TextView itemMyLpAvailableAmount, itemMyLpAvailableSymbol;
    TextView itemMyWithdrawableAmount1, itemMyWithdrawableSymbol1, itemMyWithdrawableAmount2, itemMyWithdrawableSymbol2;

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

        itemMyLpAvailableAmount = itemView.findViewById(R.id.mypool_lp_available_amount);
        itemMyLpAvailableSymbol = itemView.findViewById(R.id.mypool_lp_available_symbol);
        itemMyWithdrawableAmount1 = itemView.findViewById(R.id.mypool_withdrawable_liquidity_amount1);
        itemMyWithdrawableSymbol1 = itemView.findViewById(R.id.mypool_withdrawable_liquidity_symbol1);
        itemMyWithdrawableAmount2 = itemView.findViewById(R.id.mypool_withdrawable_liquidity_amount2);
        itemMyWithdrawableSymbol2 = itemView.findViewById(R.id.mypool_withdrawable_liquidity_symbol2);
    }

    @Override
    public void onBindMyPool(Context context, ListPoolFragment fragment, BaseData baseData, PoolOuterClass.Pool myPool) {
        Coin coin0 = new Coin(myPool.getPoolAssets(0).getToken().getDenom(), myPool.getPoolAssets(0).getToken().getAmount());
        Coin coin1 = new Coin(myPool.getPoolAssets(1).getToken().getDenom(), myPool.getPoolAssets(1).getToken().getAmount());

        itemMyPoolId.setText("POOL #" + myPool.getId());
        itemMyPoolType.setText(WUtil.getOsmosisTokenName(coin0.denom) + " / " + WUtil.getOsmosisTokenName(coin1.denom));

        BigDecimal coin0Value = WDp.usdValue(baseData, baseData.getBaseDenom(coin0.denom), new BigDecimal(coin0.amount), WUtil.getOsmosisCoinDecimal(coin0.denom));
        BigDecimal coin1Value = WDp.usdValue(baseData, baseData.getBaseDenom(coin1.denom), new BigDecimal(coin1.amount), WUtil.getOsmosisCoinDecimal(coin1.denom));
        BigDecimal PoolValue = coin0Value.add(coin1Value);
        itemMyTotalLiquidityValue.setText(WDp.getDpRawDollor(context, PoolValue, 2));

        WDp.showCoinDp(context, coin0, itemMyTotalLiquiditySymbol1, itemMyTotalLiquidityAmount1, BaseChain.OSMOSIS_MAIN);
        WDp.showCoinDp(context, coin1, itemMyTotalLiquiditySymbol2, itemMyTotalLiquidityAmount2, BaseChain.OSMOSIS_MAIN);

        itemMyLpAvailableAmount.setText(WDp.getDpAmount2(context, baseData.getAvailable("gamm/pool/" + myPool.getId()), 18, 6));
        itemMyLpAvailableSymbol.setText("GAMM-" + myPool.getId());

        BigDecimal padding = new BigDecimal("0.97");
        BigDecimal totalShares = new BigDecimal(myPool.getTotalShares().getAmount());
        BigDecimal myShare = baseData.getAvailable("gamm/pool/" + myPool.getId());
        BigDecimal poolAmount0 = new BigDecimal(myPool.getPoolAssets(0).getToken().getAmount());
        BigDecimal poolAmount1 = new BigDecimal(myPool.getPoolAssets(1).getToken().getAmount());

        BigDecimal withdrawableAmount0 = poolAmount0.multiply(myShare).multiply(padding).divide(totalShares, 0, RoundingMode.DOWN);
        Coin withdrawableCoin0 = new Coin(myPool.getPoolAssets(0).getToken().getDenom(), withdrawableAmount0.toString());
        BigDecimal withdrawableAmount1 = poolAmount1.multiply(myShare).multiply(padding).divide(totalShares, 0, RoundingMode.DOWN);
        Coin withdrawableCoin1 = new Coin(myPool.getPoolAssets(1).getToken().getDenom(), withdrawableAmount1.toString());

        WDp.showCoinDp(context, withdrawableCoin0, itemMyWithdrawableSymbol1, itemMyWithdrawableAmount1, BaseChain.OSMOSIS_MAIN);
        WDp.showCoinDp(context, withdrawableCoin1, itemMyWithdrawableSymbol2, itemMyWithdrawableAmount2, BaseChain.OSMOSIS_MAIN);

    }
}
