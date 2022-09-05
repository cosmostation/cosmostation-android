package wannabit.io.cosmostaion.widget.osmosis;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import osmosis.gamm.v1beta1.BalancerPool;
import osmosis.incentives.GaugeOuterClass;
import osmosis.lockup.Lock;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.osmosis.EarningDetailActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.OsmosisGaugeWrapper;
import wannabit.io.cosmostaion.utils.OsmosisPeriodLockWrapper;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class EarningOtherHolder extends RecyclerView.ViewHolder {
    CardView itemRoot;
    TextView itemPoolId;
    TextView itemPoolCoinPair;
    TextView itemPoolApr;
    TextView itemAvailableAmount, itemAvailableDenom, itemAvailableValue;

    public EarningOtherHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView.findViewById(R.id.earning_root);
        itemPoolId = itemView.findViewById(R.id.farming_pool_id);
        itemPoolCoinPair = itemView.findViewById(R.id.farming_coin_pair);
        itemPoolApr = itemView.findViewById(R.id.farming_apr);
        itemAvailableAmount = itemView.findViewById(R.id.available_amount);
        itemAvailableDenom = itemView.findViewById(R.id.available_denom);
        itemAvailableValue = itemView.findViewById(R.id.available_value);
    }

    public void onBindView(Context c, BaseActivity activity, BaseData baseData,
                           BalancerPool.Pool pool, ArrayList<Lock.PeriodLock> lockups, ArrayList<GaugeOuterClass.Gauge> gauges) {
        final ChainConfig chainConfig = ChainFactory.getChain(BaseChain.OSMOSIS_MAIN);
        Coin coin0 = new Coin(pool.getPoolAssets(0).getToken().getDenom(), pool.getPoolAssets(0).getToken().getAmount());
        Coin coin1 = new Coin(pool.getPoolAssets(1).getToken().getDenom(), pool.getPoolAssets(1).getToken().getAmount());
        BigDecimal lpCoinPrice = WUtil.getOsmoLpTokenPerUsdPrice(baseData, pool);
        BigDecimal apr = WUtil.getPoolArp(baseData, pool, gauges, 2);

        itemPoolId.setText("#" + pool.getId() + " EARNING");
        itemPoolCoinPair.setText(WDp.getDpSymbol(baseData, chainConfig, coin0.denom) + " / " + WDp.getDpSymbol(baseData, chainConfig, coin1.denom));
        itemPoolApr.setText(WDp.getPercentDp(apr));

        //display available
        BigDecimal availableAmount = baseData.getAvailable("gamm/pool/" + pool.getId());
        BigDecimal availableValue = availableAmount.multiply(lpCoinPrice).movePointLeft(18).setScale(2, RoundingMode.DOWN);
        itemAvailableAmount.setText(WDp.getDpAmount2(c, availableAmount, 18, 6));
        itemAvailableDenom.setText("GAMM-" + pool.getId());
        itemAvailableValue.setText(WDp.getDpRawDollor(c, availableValue, 2));

        itemRoot.setOnClickListener(view -> {
            Intent intent = new Intent(activity, EarningDetailActivity.class);
            intent.putExtra("osmosisPool", pool.toByteArray());
            OsmosisGaugeWrapper gaugesWrapper = new OsmosisGaugeWrapper(gauges);
            intent.putExtra("osmosisGauges", gaugesWrapper);
            OsmosisPeriodLockWrapper lockupsWrapper = new OsmosisPeriodLockWrapper(lockups);
            intent.putExtra("osmosislockups", lockupsWrapper);
            activity.startActivity(intent);
        });
    }
}
