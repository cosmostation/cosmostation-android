package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.network.res.ResLcdKavaAccountInfo;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class VestingHolder extends BaseHolder {
    private CardView            mVestingRoot;
    private TextView            mVestingCnt, mVestingTotalAmount;
    private RelativeLayout      mVestingLayer0, mVestingLayer1, mVestingLayer2, mVestingLayer3, mVestingLayer4;
    private TextView            mVestingTime0, mVestingTime1, mVestingTime2, mVestingTime3, mVestingTime4;
    private TextView            mVestingGap0, mVestingGap1, mVestingGap2, mVestingGap3, mVestingGap4;
    private TextView            mVestingAmount0, mVestingAmount1, mVestingAmount2, mVestingAmount3, mVestingAmount4;

    public VestingHolder(@NonNull View itemView) {
        super(itemView);
        mVestingRoot            = itemView.findViewById(R.id.card_root);
        mVestingCnt             = itemView.findViewById(R.id.vesting_count);
        mVestingTotalAmount     = itemView.findViewById(R.id.total_vesting_amount);
        mVestingLayer0          = itemView.findViewById(R.id.vesting_layer0);
        mVestingLayer1          = itemView.findViewById(R.id.vesting_layer1);
        mVestingLayer2          = itemView.findViewById(R.id.vesting_layer2);
        mVestingLayer3          = itemView.findViewById(R.id.vesting_layer3);
        mVestingLayer4          = itemView.findViewById(R.id.vesting_layer4);
        mVestingTime0           = itemView.findViewById(R.id.vesting_time0);
        mVestingTime1           = itemView.findViewById(R.id.vesting_time1);
        mVestingTime2           = itemView.findViewById(R.id.vesting_time2);
        mVestingTime3           = itemView.findViewById(R.id.vesting_time3);
        mVestingTime4           = itemView.findViewById(R.id.vesting_time4);
        mVestingGap0            = itemView.findViewById(R.id.vesting_gap0);
        mVestingGap1            = itemView.findViewById(R.id.vesting_gap1);
        mVestingGap2            = itemView.findViewById(R.id.vesting_gap2);
        mVestingGap3            = itemView.findViewById(R.id.vesting_gap3);
        mVestingGap4            = itemView.findViewById(R.id.vesting_gap4);
        mVestingAmount0         = itemView.findViewById(R.id.vesting_amount0);
        mVestingAmount1         = itemView.findViewById(R.id.vesting_amount1);
        mVestingAmount2         = itemView.findViewById(R.id.vesting_amount2);
        mVestingAmount3         = itemView.findViewById(R.id.vesting_amount3);
        mVestingAmount4         = itemView.findViewById(R.id.vesting_amount4);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        mVestingRoot.setCardBackgroundColor(WDp.getChainBgColor(c, chain));

        final ResLcdKavaAccountInfo.Value mKavaAccount = baseData.mKavaAccount.value;
        mVestingCnt.setText("(" + mKavaAccount.getCalcurateVestingCntByDenom(denom) + ")");
        mVestingTotalAmount.setText(WDp.getDpAmount2(c, mKavaAccount.getCalcurateVestingAmountSumByDenom(TOKEN_KAVA), 6, 6));

        mVestingTime0.setText(WDp.getDpTime(c, mKavaAccount.getCalcurateTime(denom, 0)));
        mVestingGap0.setText(WDp.getUnbondingTimeleft(c, mKavaAccount.getCalcurateTime(denom, 0)));
        mVestingAmount0.setText(WDp.getDpAmount2(c, mKavaAccount.getCalcurateAmount(denom, 0), 6, 6));
        if (baseData.mKavaAccount.value.getCalcurateVestingCntByDenom(denom) > 1) {
            mVestingLayer1.setVisibility(View.VISIBLE);
            mVestingTime1.setText(WDp.getDpTime(c, mKavaAccount.getCalcurateTime(denom, 1)));
            mVestingGap1.setText(WDp.getUnbondingTimeleft(c, mKavaAccount.getCalcurateTime(denom, 1)));
            mVestingAmount1.setText(WDp.getDpAmount2(c, mKavaAccount.getCalcurateAmount(denom, 1), 6, 6));
        }
        if (baseData.mKavaAccount.value.getCalcurateVestingCntByDenom(denom) > 2) {
            mVestingLayer2.setVisibility(View.VISIBLE);
            mVestingTime2.setText(WDp.getDpTime(c, mKavaAccount.getCalcurateTime(denom, 2)));
            mVestingGap2.setText(WDp.getUnbondingTimeleft(c, mKavaAccount.getCalcurateTime(denom, 2)));
            mVestingAmount2.setText(WDp.getDpAmount2(c, mKavaAccount.getCalcurateAmount(denom, 2), 6, 6));
        }
        if (baseData.mKavaAccount.value.getCalcurateVestingCntByDenom(denom) > 3) {
            mVestingLayer3.setVisibility(View.VISIBLE);
            mVestingTime3.setText(WDp.getDpTime(c, mKavaAccount.getCalcurateTime(denom, 3)));
            mVestingGap3.setText(WDp.getUnbondingTimeleft(c, mKavaAccount.getCalcurateTime(denom, 3)));
            mVestingAmount3.setText(WDp.getDpAmount2(c, mKavaAccount.getCalcurateAmount(denom, 3), 6, 6));
        }
        if (baseData.mKavaAccount.value.getCalcurateVestingCntByDenom(denom) > 4) {
            mVestingLayer4.setVisibility(View.VISIBLE);
            mVestingTime4.setText(WDp.getDpTime(c, mKavaAccount.getCalcurateTime(denom, 4)));
            mVestingGap4.setText(WDp.getUnbondingTimeleft(c, mKavaAccount.getCalcurateTime(denom, 4)));
            mVestingAmount4.setText(WDp.getDpAmount2(c, mKavaAccount.getCalcurateAmount(denom, 4), 6, 6));
        }

    }
}
