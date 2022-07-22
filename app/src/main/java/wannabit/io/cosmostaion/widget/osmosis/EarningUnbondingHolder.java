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

import osmosis.gamm.v1beta1.BalancerPool;
import osmosis.incentives.GaugeOuterClass;
import osmosis.lockup.Lock;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class EarningUnbondingHolder extends RecyclerView.ViewHolder {
    CardView itemRoot;
    TextView itemLockId;
    TextView itemLockStatus;
    TextView itemUnbondingTime;
    TextView itemUnbondingGap;
    TextView itemAmount;
    TextView itemAmountDenom;
    TextView itemAmountValue;
    TextView itemRewardAmount;

    public EarningUnbondingHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot            = itemView.findViewById(R.id.card_root);
        itemLockId          = itemView.findViewById(R.id.lock_id);
        itemLockStatus      = itemView.findViewById(R.id.lock_status);
        itemUnbondingTime   = itemView.findViewById(R.id.time);
        itemUnbondingGap    = itemView.findViewById(R.id.time_gap);
        itemAmount          = itemView.findViewById(R.id.amount);
        itemAmountDenom     = itemView.findViewById(R.id.amount_denom);
        itemAmountValue     = itemView.findViewById(R.id.amount_value);
        itemRewardAmount    = itemView.findViewById(R.id.next_reward_amount);
    }

    public void onBindView(Context c, BaseData baseData, BalancerPool.Pool pool, Lock.PeriodLock lockup, ArrayList<GaugeOuterClass.Gauge> gauges) {
        BigDecimal totalShare = new BigDecimal(pool.getTotalShares().getAmount());
        BigDecimal lpCoinPrice = WUtil.getOsmoLpTokenPerUsdPrice(baseData, pool);
        Coin myLpCoin = new Coin(lockup.getCoins(0).getDenom(), lockup.getCoins(0).getAmount());
        BigDecimal myShare = new BigDecimal(myLpCoin.amount);
        BigDecimal myShareRate = myShare.divide(totalShare, 24, RoundingMode.DOWN);
        BigDecimal myLpCoinValue = (myShare).multiply(lpCoinPrice).movePointLeft(18).setScale(2, RoundingMode.DOWN);
        BigDecimal myReward = BigDecimal.ZERO;

        //pool incentives
        BigDecimal incentive1Day = WUtil.getNextIncentiveAmount(gauges, 0);
        BigDecimal incentive7Day = WUtil.getNextIncentiveAmount(gauges, 1);

        long now = new Date().getTime();
        long day7 = now + 604800000;
        long endTime = lockup.getEndTime().getSeconds() * 1000;
        if (endTime > day7) {
            myReward = myShareRate.multiply(incentive7Day);
        } else {
            myReward = myShareRate.multiply(incentive1Day);
        }

        itemLockId.setText("# " + lockup.getID());
        itemUnbondingTime.setText(WDp.getDpTime(c, endTime));
        itemUnbondingGap.setText(WDp.getGapTime(c, endTime));

        itemAmount.setText(WDp.getDpAmount2(c, myShare, 18, 18));
        itemAmountDenom.setText("GAMM-" + pool.getId());
        itemAmountValue.setText(WDp.getDpRawDollor(c, myLpCoinValue, 2));
        itemRewardAmount.setText(WDp.getDpAmount2(c, myReward, 6, 6));

    }
}
