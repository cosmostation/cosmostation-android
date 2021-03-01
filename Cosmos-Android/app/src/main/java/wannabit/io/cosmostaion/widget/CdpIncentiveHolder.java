package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.fragment.chains.kava.ListCdpFragment;
import wannabit.io.cosmostaion.network.res.ResKavaIncentiveReward5;
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
    public void onBindUsdxIncentive(Context c, ListCdpFragment fragment, ResKavaIncentiveReward5.IncentiveReward5 incentiveReward) {
        itemIncentiveAmount.setText(WDp.getDpAmount2(c, incentiveReward.getMintingRewardAmount(), 6, 6));
        itemBtnClaim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fragment.onCheckStartClaimIncentive();
            }
        });
    }
}
