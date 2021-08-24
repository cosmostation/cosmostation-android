package wannabit.io.cosmostaion.widget.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import osmosis.gamm.v1beta1.PoolOuterClass;
import osmosis.incentives.GaugeOuterClass;
import osmosis.lockup.Lock;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.EarningDetailActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class EarningBondedHolder extends RecyclerView.ViewHolder {
    CardView itemRoot;
    TextView itemLockId;
    TextView itemLockStatus;
    TextView itemUnbondingDuration;
    TextView itemAPR;
    TextView itemAmount;
    TextView itemAmountDenom;
    TextView itemAmountValue;
    TextView itemRewardAmount;
    RelativeLayout mBtnUnbonding;

    public EarningBondedHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot                = itemView.findViewById(R.id.card_root);
        itemLockId              = itemView.findViewById(R.id.lock_id);
        itemLockStatus          = itemView.findViewById(R.id.lock_status);
        itemUnbondingDuration   = itemView.findViewById(R.id.duration);
        itemAPR                 = itemView.findViewById(R.id.apr);
        itemAmount              = itemView.findViewById(R.id.amount);
        itemAmountDenom         = itemView.findViewById(R.id.amount_denom);
        itemAmountValue         = itemView.findViewById(R.id.amount_value);
        itemRewardAmount        = itemView.findViewById(R.id.next_reward_amount);
        mBtnUnbonding           = itemView.findViewById(R.id.btn_start_unbonding);
    }

    public void onBindView(Context c, BaseActivity activity, BaseData baseData,
                           PoolOuterClass.Pool pool, Lock.PeriodLock lockup, ArrayList<GaugeOuterClass.Gauge> gauges) {
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
        BigDecimal incentive14Day = WUtil.getNextIncentiveAmount(gauges, 2);

        BigDecimal apr1 = WUtil.getPoolArp(baseData, pool, gauges, 0);
        BigDecimal apr7 = WUtil.getPoolArp(baseData, pool, gauges, 1);
        BigDecimal apr14 = WUtil.getPoolArp(baseData, pool, gauges, 2);


        itemLockId.setText("# " + lockup.getID());
        if (lockup.getDuration().getSeconds() == 86400) {
            itemUnbondingDuration.setText("1 Day");
            itemAPR.setText(WDp.getPercentDp(apr1));
            myReward = myShareRate.multiply(incentive1Day);

        } else if (lockup.getDuration().getSeconds() == 604800) {
            itemUnbondingDuration.setText("7 Days");
            itemAPR.setText(WDp.getPercentDp(apr7));
            myReward = myShareRate.multiply(incentive7Day);

        } else if (lockup.getDuration().getSeconds() == 1209600) {
            itemUnbondingDuration.setText("14 Days");
            itemAPR.setText(WDp.getPercentDp(apr14));
            myReward = myShareRate.multiply(incentive14Day);

        }

        itemAmount.setText(WDp.getDpAmount2(c, myShare, 18, 18));
        itemAmountDenom.setText("GAMM-" + pool.getId());
        itemAmountValue.setText(WDp.getDpRawDollor(c, myLpCoinValue, 2));
        itemRewardAmount.setText(WDp.getDpAmount2(c, myReward, 6, 6));


        mBtnUnbonding.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ((EarningDetailActivity)activity).onCheckUnbonding(lockup);
            }
        });
    }
}
