package wannabit.io.cosmostaion.widget.kava;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.fragment.txs.kava.ListHardFragment;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class HardIncentiveHolder extends BaseHolder {
    TextView kavaIncentiveAmount, hardIncentiveAmount;
    RelativeLayout itemBtnClaim;

    public HardIncentiveHolder(@NonNull View itemView) {
        super(itemView);
        kavaIncentiveAmount = itemView.findViewById(R.id.kava_incentive_amount);
        hardIncentiveAmount = itemView.findViewById(R.id.hard_incentive_amount);
        itemBtnClaim = itemView.findViewById(R.id.btn_claim_incentive);
    }

    @Override
    public void onBindHardIncentive(Context c, ListHardFragment fragment, IncentiveReward incentiveReward) {
        kavaIncentiveAmount.setText(WDp.getDpAmount2(c, incentiveReward.getHardPoolKavaRewardAmount(), 6, 6));
        hardIncentiveAmount.setText(WDp.getDpAmount2(c, incentiveReward.getHardPoolHardRewardAmount(), 6, 6));
        itemBtnClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.onCheckStartClaimIncentive();
            }
        });

    }
}
