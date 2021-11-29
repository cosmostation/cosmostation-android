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

import osmosis.gamm.v1beta1.BalancerPoolOuterClass;
import osmosis.incentives.GaugeOuterClass;
import osmosis.lockup.Lock;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.osmosis.EarningDetailActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class EarningUnbondedHolder extends RecyclerView.ViewHolder {
    CardView itemRoot;
    TextView itemLockId;
    TextView itemLockStatus;
    TextView itemAmount;
    TextView itemAmountDenom;
    TextView itemAmountValue;
    TextView itemRewardAmount;
    RelativeLayout mBtnUnLock;

    public EarningUnbondedHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot                = itemView.findViewById(R.id.card_root);
        itemLockId              = itemView.findViewById(R.id.lock_id);
        itemLockStatus          = itemView.findViewById(R.id.lock_status);
        itemAmount              = itemView.findViewById(R.id.amount);
        itemAmountDenom         = itemView.findViewById(R.id.amount_denom);
        itemAmountValue         = itemView.findViewById(R.id.amount_value);
        itemRewardAmount        = itemView.findViewById(R.id.next_reward_amount);
        mBtnUnLock              = itemView.findViewById(R.id.btn_start_unlock);

    }

    public void onBindView(Context c, BaseActivity activity, BaseData baseData,
                           BalancerPoolOuterClass.BalancerPool pool, Lock.PeriodLock lockup, ArrayList<GaugeOuterClass.Gauge> gauges) {
        BigDecimal totalShare = new BigDecimal(pool.getTotalShares().getAmount());
        BigDecimal lpCoinPrice = WUtil.getOsmoLpTokenPerUsdPrice(baseData, pool);
        Coin myLpCoin = new Coin(lockup.getCoins(0).getDenom(), lockup.getCoins(0).getAmount());
        BigDecimal myShare = new BigDecimal(myLpCoin.amount);
        BigDecimal myShareRate = myShare.divide(totalShare, 24, RoundingMode.DOWN);
        BigDecimal myLpCoinValue = (myShare).multiply(lpCoinPrice).movePointLeft(18).setScale(2, RoundingMode.DOWN);


        //pool incentives
        BigDecimal incentive1Day = WUtil.getNextIncentiveAmount(gauges, 0);
        BigDecimal myReward = myShareRate.multiply(incentive1Day);

        itemLockId.setText("# " + lockup.getID());

        itemAmount.setText(WDp.getDpAmount2(c, myShare, 18, 18));
        itemAmountDenom.setText("GAMM-" + pool.getId());
        itemAmountValue.setText(WDp.getDpRawDollor(c, myLpCoinValue, 2));
        itemRewardAmount.setText(WDp.getDpAmount2(c, myReward, 6, 6));

        mBtnUnLock.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               ((EarningDetailActivity)activity).onCheckUnlock(lockup);
            }
        });
    }
}