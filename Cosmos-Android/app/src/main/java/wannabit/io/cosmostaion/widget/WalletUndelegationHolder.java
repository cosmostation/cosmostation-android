package wannabit.io.cosmostaion.widget;

import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.dao.UnBondingState;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseChain.IOV_TEST;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;

public class WalletUndelegationHolder extends WalletHolder {
    private CardView            mUndelegateCard;
    private TextView            mUndelegateCnt;
    private RelativeLayout      mUndelegateLayer0, mUndelegateLayer1, mUndelegateLayer2, mUndelegateLayer3, mUndelegateLayer4;
    private TextView            mUndelegateMoniker0, mUndelegateMoniker1, mUndelegateMoniker2, mUndelegateMoniker3, mUndelegateMoniker4;
    private TextView            mUndelegateAmount0, mUndelegateAmount1, mUndelegateAmount2, mUndelegateAmount3, mUndelegateAmount4;
    private TextView            mUndelegateTime0, mUndelegateTime1, mUndelegateTime2, mUndelegateTime3, mUndelegateTime4;

    public WalletUndelegationHolder(@NonNull View itemView) {
        super(itemView);
        mUndelegateCard         = itemView.findViewById(R.id.card_root);
        mUndelegateCnt          = itemView.findViewById(R.id.undelegate_count);

        mUndelegateLayer0       = itemView.findViewById(R.id.undelegate_detail_layer0);
        mUndelegateLayer1       = itemView.findViewById(R.id.undelegate_detail_layer1);
        mUndelegateLayer2       = itemView.findViewById(R.id.undelegate_detail_layer2);
        mUndelegateLayer3       = itemView.findViewById(R.id.undelegate_detail_layer3);
        mUndelegateLayer4       = itemView.findViewById(R.id.undelegate_detail_layer4);

        mUndelegateMoniker0     = itemView.findViewById(R.id.undelegate_detail_moniker0);
        mUndelegateMoniker1     = itemView.findViewById(R.id.undelegate_detail_moniker1);
        mUndelegateMoniker2     = itemView.findViewById(R.id.undelegate_detail_moniker2);
        mUndelegateMoniker3     = itemView.findViewById(R.id.undelegate_detail_moniker3);
        mUndelegateMoniker4     = itemView.findViewById(R.id.undelegate_detail_moniker4);

        mUndelegateAmount0      = itemView.findViewById(R.id.undelegate_detail_amount0);
        mUndelegateAmount1      = itemView.findViewById(R.id.undelegate_detail_amount1);
        mUndelegateAmount2      = itemView.findViewById(R.id.undelegate_detail_amount2);
        mUndelegateAmount3      = itemView.findViewById(R.id.undelegate_detail_amount3);
        mUndelegateAmount4      = itemView.findViewById(R.id.undelegate_detail_amount4);

        mUndelegateTime0        = itemView.findViewById(R.id.undelegate_detail_time0);
        mUndelegateTime1        = itemView.findViewById(R.id.undelegate_detail_time1);
        mUndelegateTime2        = itemView.findViewById(R.id.undelegate_detail_time2);
        mUndelegateTime3        = itemView.findViewById(R.id.undelegate_detail_time3);
        mUndelegateTime4        = itemView.findViewById(R.id.undelegate_detail_time4);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        mUndelegateLayer1.setVisibility(View.GONE);
        mUndelegateLayer2.setVisibility(View.GONE);
        mUndelegateLayer3.setVisibility(View.GONE);
        mUndelegateLayer4.setVisibility(View.GONE);
        mUndelegateCard.setCardBackgroundColor(WDp.getChainBgColor(mainActivity, mainActivity.mBaseChain));

        final ArrayList<UnBondingState>  unbondings = mainActivity.mUnbondings;
        final int dpDecimal = mainActivity.mBaseChain.equals(IRIS_MAIN) ? 18 : 6;
        WUtil.onSortUnbondingsRecent(unbondings);
        mUndelegateCnt.setText(String.valueOf(unbondings.size()));
        mUndelegateMoniker0.setText(WUtil.getMonikerName(unbondings.get(0).validatorAddress, mainActivity.mAllValidators, false));
        mUndelegateAmount0.setText(WDp.getDpAmount2(mainActivity, unbondings.get(0).balance, dpDecimal, 6));
        mUndelegateTime0.setText(WDp.getUnbondingTimeleft(mainActivity, unbondings.get(0).completionTime));

        if (unbondings.size() > 1) {
            mUndelegateLayer1.setVisibility(View.VISIBLE);
            mUndelegateMoniker1.setText(WUtil.getMonikerName(unbondings.get(1).validatorAddress, mainActivity.mAllValidators, false));
            mUndelegateAmount1.setText(WDp.getDpAmount2(mainActivity, unbondings.get(1).balance, dpDecimal, 6));
            mUndelegateTime1.setText(WDp.getUnbondingTimeleft(mainActivity, unbondings.get(1).completionTime));
        }
        if (unbondings.size() > 2) {
            mUndelegateLayer2.setVisibility(View.VISIBLE);
            mUndelegateMoniker2.setText(WUtil.getMonikerName(unbondings.get(2).validatorAddress, mainActivity.mAllValidators, false));
            mUndelegateAmount2.setText(WDp.getDpAmount2(mainActivity, unbondings.get(2).balance, dpDecimal, 6));
            mUndelegateTime2.setText(WDp.getUnbondingTimeleft(mainActivity, unbondings.get(2).completionTime));
        }
        if (unbondings.size() > 3) {
            mUndelegateLayer3.setVisibility(View.VISIBLE);
            mUndelegateMoniker3.setText(WUtil.getMonikerName(unbondings.get(3).validatorAddress, mainActivity.mAllValidators, false));
            mUndelegateAmount3.setText(WDp.getDpAmount2(mainActivity, unbondings.get(3).balance, dpDecimal, 6));
            mUndelegateTime3.setText(WDp.getUnbondingTimeleft(mainActivity, unbondings.get(3).completionTime));
        }
        if (unbondings.size() > 4) {
            mUndelegateLayer4.setVisibility(View.VISIBLE);
            mUndelegateMoniker4.setText(WUtil.getMonikerName(unbondings.get(4).validatorAddress, mainActivity.mAllValidators, false));
            mUndelegateAmount4.setText(WDp.getDpAmount2(mainActivity, unbondings.get(4).balance, dpDecimal, 6));
            mUndelegateTime4.setText(WDp.getUnbondingTimeleft(mainActivity, unbondings.get(4).completionTime));
        }

    }
}
