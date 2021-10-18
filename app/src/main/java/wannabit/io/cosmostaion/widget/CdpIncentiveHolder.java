package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.fragment.chains.kava.ListCdpFragment;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.utils.WDp;

public class CdpIncentiveHolder extends BaseHolder {
    TextView itemIncentiveAmount, itemIncentiveAmountDenom;
    RelativeLayout itemBtnClaim;

    public CdpIncentiveHolder(@NonNull View itemView) {
        super(itemView);
        itemIncentiveAmount = itemView.findViewById(R.id.incentive_amount);
        itemIncentiveAmountDenom = itemView.findViewById(R.id.incentive_symbol);
        itemBtnClaim = itemView.findViewById(R.id.btn_claim_incentive);
    }

    @Override
    public void onBindUsdxIncentive(Context c, ListCdpFragment fragment, IncentiveReward incentiveReward) {
//        itemIncentiveAmount.setText(WDp.getDpAmount2(c, incentiveReward.getMintingRewardAmount(), 6, 6));
//        itemBtnClaim.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                fragment.onCheckStartClaimIncentive();
//            }
//        });
    }
}
