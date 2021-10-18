package wannabit.io.cosmostaion.widget.mainWallet;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletSifIncentiveHolder extends BaseHolder {
    private TextView            mCurrentLmAmount;
    private TextView            mMaxDate;
    private RelativeLayout      mBtnIncentive;

    public WalletSifIncentiveHolder(@NonNull View itemView) {
        super(itemView);
        mCurrentLmAmount        = itemView.findViewById(R.id.current_lm_amount);
        mMaxDate                = itemView.findViewById(R.id.max_date);
        mBtnIncentive           = itemView.findViewById(R.id.btn_sif_incentive);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        BigDecimal currentLmAmount = BigDecimal.ZERO;
        String maxDate = null;

        if (baseData.mSifLmIncentive != null) {
            currentLmAmount = new BigDecimal(baseData.mSifLmIncentive.totalClaimableCommissionsAndClaimableRewards);
            maxDate = baseData.mSifLmIncentive.maturityDateISO;
        }

        mCurrentLmAmount.setText(WDp.getDpAmount2(mainActivity, currentLmAmount, 0, 6));
        if (maxDate == null) {
            mMaxDate.setText("--");
        } else {
            mMaxDate.setText(WDp.getTimeformat(mainActivity, maxDate));
        }
    }
}
