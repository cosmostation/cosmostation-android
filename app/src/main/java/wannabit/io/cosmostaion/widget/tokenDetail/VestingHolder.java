package wannabit.io.cosmostaion.widget.tokenDetail;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SWP;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.util.ArrayList;

import cosmos.vesting.v1beta1.Vesting;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class VestingHolder extends BaseHolder {
    private final CardView mVestingRoot;
    private final TextView mVestingCnt;
    private final RelativeLayout mVestingLayer0;
    private final RelativeLayout mVestingLayer1;
    private final RelativeLayout mVestingLayer2;
    private final RelativeLayout mVestingLayer3;
    private final RelativeLayout mVestingLayer4;
    private final TextView mVestingTime0;
    private final TextView mVestingTime1;
    private final TextView mVestingTime2;
    private final TextView mVestingTime3;
    private final TextView mVestingTime4;
    private final TextView mVestingGap0;
    private final TextView mVestingGap1;
    private final TextView mVestingGap2;
    private final TextView mVestingGap3;
    private final TextView mVestingGap4;
    private final TextView mVestingAmount0;
    private final TextView mVestingAmount1;
    private final TextView mVestingAmount2;
    private final TextView mVestingAmount3;
    private final TextView mVestingAmount4;

    public VestingHolder(@NonNull View itemView) {
        super(itemView);
        mVestingRoot = itemView.findViewById(R.id.card_root);
        mVestingCnt = itemView.findViewById(R.id.vesting_count);
        mVestingLayer0 = itemView.findViewById(R.id.vesting_layer0);
        mVestingLayer1 = itemView.findViewById(R.id.vesting_layer1);
        mVestingLayer2 = itemView.findViewById(R.id.vesting_layer2);
        mVestingLayer3 = itemView.findViewById(R.id.vesting_layer3);
        mVestingLayer4 = itemView.findViewById(R.id.vesting_layer4);
        mVestingTime0 = itemView.findViewById(R.id.vesting_time0);
        mVestingTime1 = itemView.findViewById(R.id.vesting_time1);
        mVestingTime2 = itemView.findViewById(R.id.vesting_time2);
        mVestingTime3 = itemView.findViewById(R.id.vesting_time3);
        mVestingTime4 = itemView.findViewById(R.id.vesting_time4);
        mVestingGap0 = itemView.findViewById(R.id.vesting_gap0);
        mVestingGap1 = itemView.findViewById(R.id.vesting_gap1);
        mVestingGap2 = itemView.findViewById(R.id.vesting_gap2);
        mVestingGap3 = itemView.findViewById(R.id.vesting_gap3);
        mVestingGap4 = itemView.findViewById(R.id.vesting_gap4);
        mVestingAmount0 = itemView.findViewById(R.id.vesting_amount0);
        mVestingAmount1 = itemView.findViewById(R.id.vesting_amount1);
        mVestingAmount2 = itemView.findViewById(R.id.vesting_amount2);
        mVestingAmount3 = itemView.findViewById(R.id.vesting_amount3);
        mVestingAmount4 = itemView.findViewById(R.id.vesting_amount4);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        mVestingRoot.setCardBackgroundColor(WDp.getChainBgColor(c, chain));
        if (denom.equals(TOKEN_HARD)) {
            mVestingRoot.setCardBackgroundColor(c.getResources().getColor(R.color.colorTransBghard));
        } else if (denom.equals(TOKEN_SWP)) {
            mVestingRoot.setCardBackgroundColor(c.getResources().getColor(R.color.colorTransBgswp));
        }
        onBindGRPC(c, chain, baseData, denom);
    }

    private void onBindGRPC(Context c, BaseChain chain, BaseData baseData, String denom) {
        ArrayList<Vesting.Period> vps = baseData.onParseRemainVestingsByDenom(denom);
        mVestingCnt.setText("(" + vps.size() + ")");

        mVestingTime0.setText(WDp.getDpTime(c, vps.get(0).getLength()));
        mVestingGap0.setText(WDp.getUnbondingTimeleft(c, vps.get(0).getLength()));
        mVestingAmount0.setText(WDp.getDpAmount2(WDp.getAmountVp(vps.get(0), denom), 6, 6));
        if (vps.size() > 1) {
            mVestingLayer1.setVisibility(View.VISIBLE);
            mVestingTime1.setText(WDp.getDpTime(c, vps.get(1).getLength()));
            mVestingGap1.setText(WDp.getUnbondingTimeleft(c, vps.get(1).getLength()));
            mVestingAmount1.setText(WDp.getDpAmount2(WDp.getAmountVp(vps.get(1), denom), 6, 6));
        }
        if (vps.size() > 2) {
            mVestingLayer2.setVisibility(View.VISIBLE);
            mVestingTime2.setText(WDp.getDpTime(c, vps.get(2).getLength()));
            mVestingGap2.setText(WDp.getUnbondingTimeleft(c, vps.get(2).getLength()));
            mVestingAmount2.setText(WDp.getDpAmount2(WDp.getAmountVp(vps.get(2), denom), 6, 6));
        }
        if (vps.size() > 3) {
            mVestingLayer3.setVisibility(View.VISIBLE);
            mVestingTime3.setText(WDp.getDpTime(c, vps.get(3).getLength()));
            mVestingGap3.setText(WDp.getUnbondingTimeleft(c, vps.get(3).getLength()));
            mVestingAmount3.setText(WDp.getDpAmount2(WDp.getAmountVp(vps.get(3), denom), 6, 6));
        }
        if (vps.size() > 4) {
            mVestingLayer4.setVisibility(View.VISIBLE);
            mVestingTime4.setText(WDp.getDpTime(c, vps.get(4).getLength()));
            mVestingGap4.setText(WDp.getUnbondingTimeleft(c, vps.get(4).getLength()));
            mVestingAmount4.setText(WDp.getDpAmount2(WDp.getAmountVp(vps.get(4), denom), 6, 6));
        }
    }
}
