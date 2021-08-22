package wannabit.io.cosmostaion.widget.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.Date;

import osmosis.gamm.v1beta1.PoolOuterClass;
import osmosis.incentives.GaugeOuterClass;
import osmosis.lockup.Lock;
import osmosis.poolincentives.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class EarningMyHolder extends RecyclerView.ViewHolder {
    CardView itemRoot;
    TextView itemPoolId;
    TextView itemPoolCoinPair;
    TextView itemPoolApr;

    TextView itemBondedAmount, itemBondedDenom, itemBondedValue;
    TextView itemUnBondingAmount, itemUnBondingDenom, itemUnBondingValue;
    TextView itemUnBondedAmount, itemUnBondedDenom, itemUnBondedValue;
    TextView itemRewardAmount;
    TextView itemAvailableAmount, itemAvailableDenom, itemAvailableValue;


    public EarningMyHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView.findViewById(R.id.earning_root);
        itemPoolId = itemView.findViewById(R.id.myfarming_pool_id);
        itemPoolCoinPair = itemView.findViewById(R.id.myfarming_coin_pair);
        itemPoolApr = itemView.findViewById(R.id.myfarming_apr);
        itemBondedAmount = itemView.findViewById(R.id.bonded_amount);
        itemBondedDenom = itemView.findViewById(R.id.bonded_denom);
        itemBondedValue = itemView.findViewById(R.id.bonded_value);
        itemUnBondingAmount = itemView.findViewById(R.id.unbonding_amount);
        itemUnBondingDenom = itemView.findViewById(R.id.unbonding_denom);
        itemUnBondingValue = itemView.findViewById(R.id.unbonding_value);
        itemUnBondedAmount = itemView.findViewById(R.id.unbonded_amount);
        itemUnBondedDenom = itemView.findViewById(R.id.unbonded_denom);
        itemUnBondedValue = itemView.findViewById(R.id.unbonded_value);
        itemRewardAmount = itemView.findViewById(R.id.next_reward_amount);
        itemAvailableAmount = itemView.findViewById(R.id.available_amount);
        itemAvailableDenom = itemView.findViewById(R.id.available_denom);
        itemAvailableValue = itemView.findViewById(R.id.available_value);
    }

    public void onBindView(Context c, BaseActivity activity, BaseData baseData,
                           PoolOuterClass.Pool pool, ArrayList<Lock.PeriodLock> lockups, ArrayList<GaugeOuterClass.Gauge> gauges) {

        Coin coin0 = new Coin(pool.getPoolAssets(0).getToken().getDenom(), pool.getPoolAssets(0).getToken().getAmount());
        Coin coin1 = new Coin(pool.getPoolAssets(1).getToken().getDenom(), pool.getPoolAssets(1).getToken().getAmount());
        BigDecimal lpCoinPrice = WUtil.getOsmoLpTokenPerUsdPrice(baseData, pool);
        BigDecimal apr = WUtil.getPoolArp(baseData, pool, gauges, 2);
        BigDecimal totalShare = new BigDecimal(pool.getTotalShares().getAmount());

        itemPoolId.setText("MY EARNING #" + pool.getId());
        itemPoolCoinPair.setText(WUtil.dpOsmosisTokenName(coin0.denom) + " / " + WUtil.dpOsmosisTokenName(coin1.denom));
        itemPoolApr.setText(WDp.getPercentDp(apr));

        //pool incentives
        BigDecimal incentive1Day = WUtil.getNextIncentiveAmount(gauges, 0);
        BigDecimal incentive7Day = WUtil.getNextIncentiveAmount(gauges, 1);
        BigDecimal incentive14Day = WUtil.getNextIncentiveAmount(gauges, 2);

        //display lock
        BigDecimal bondedAmount = BigDecimal.ZERO;
        BigDecimal unbondingAmount = BigDecimal.ZERO;
        BigDecimal unbondedAmount = BigDecimal.ZERO;
        BigDecimal myRewards = BigDecimal.ZERO;

        for (Lock.PeriodLock lockup: lockups) {
            Coin lpCoin = new Coin(lockup.getCoins(0).getDenom(), lockup.getCoins(0).getAmount());
            BigDecimal myShare = new BigDecimal(lpCoin.amount);
            BigDecimal myShareRate = myShare.divide(totalShare, 24, RoundingMode.DOWN);
            long now = new Date().getTime();
            long day7 = now + 604800000;
            long endTime = lockup.getEndTime().getSeconds() * 1000;

            if (lpCoin.osmosisAmmPoolId() == pool.getId()) {
                if (endTime == -62135596800000l) {
                    bondedAmount = bondedAmount.add(new BigDecimal(lpCoin.amount));
                    if (lockup.getDuration().getSeconds() == 86400) {
                        myRewards = myRewards.add(myShareRate.multiply(incentive1Day));
                    } else if (lockup.getDuration().getSeconds() == 604800) {
                        myRewards = myRewards.add(myShareRate.multiply(incentive7Day));
                    } else if (lockup.getDuration().getSeconds() == 1209600) {
                        myRewards = myRewards.add(myShareRate.multiply(incentive14Day));
                    }

                } else if (endTime > now) {
                    unbondingAmount = unbondingAmount.add(new BigDecimal(lpCoin.amount));
                    if (lockup.getEndTime().getSeconds() > day7) {
                        myRewards = myRewards.add(myShareRate.multiply(incentive7Day));
                    } else {
                        myRewards = myRewards.add(myShareRate.multiply(incentive1Day));
                    }

                } else {
                    unbondedAmount = unbondedAmount.add(new BigDecimal(lpCoin.amount));
                    myRewards = myRewards.add(myShareRate.multiply(incentive1Day));

                }
            }
        }

        itemBondedAmount.setText(WDp.getDpAmount2(c, bondedAmount, 18, 6));
        itemBondedDenom.setText("GAMM-" + pool.getId());
        itemUnBondingAmount.setText(WDp.getDpAmount2(c, unbondingAmount, 18, 6));
        itemUnBondingDenom.setText("GAMM-" + pool.getId());
        itemUnBondedAmount.setText(WDp.getDpAmount2(c, unbondedAmount, 18, 6));
        itemUnBondedDenom.setText("GAMM-" + pool.getId());

        BigDecimal bonedCoinValue = bondedAmount.multiply(lpCoinPrice).movePointLeft(18).setScale(2, RoundingMode.DOWN);
        itemBondedValue.setText(WDp.getDpRawDollor(c, bonedCoinValue, 2));
        BigDecimal unbondingCoinValue = unbondingAmount.multiply(lpCoinPrice).movePointLeft(18).setScale(2, RoundingMode.DOWN);
        itemUnBondingValue.setText(WDp.getDpRawDollor(c, unbondingCoinValue, 2));
        BigDecimal unbondedCoinValue = unbondedAmount.multiply(lpCoinPrice).movePointLeft(18).setScale(2, RoundingMode.DOWN);
        itemUnBondedValue.setText(WDp.getDpRawDollor(c, unbondedCoinValue, 2));

        //display available
        BigDecimal availableAmount = baseData.getAvailable("gamm/pool/" + pool.getId());
        BigDecimal availableValue = availableAmount.multiply(lpCoinPrice).movePointLeft(18).setScale(2, RoundingMode.DOWN);
        itemAvailableAmount.setText(WDp.getDpAmount2(c, availableAmount, 18, 6));
        itemAvailableDenom.setText("GAMM-" + pool.getId());
        itemAvailableValue.setText(WDp.getDpRawDollor(c, availableValue, 2));

        //display reward
        itemRewardAmount.setText(WDp.getDpAmount2(c, myRewards, 6, 6));
    }
}