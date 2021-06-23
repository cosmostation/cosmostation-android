package wannabit.io.cosmostaion.widget.mainWallet;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletSifIncentiveHolder extends BaseHolder {
    private CardView            mIncentiveCard;
    private TextView            mCurrentVsAmount, mCurrentLmAmount;
    private TextView            mMaxVsAmount, mMaxLmAmount, mMaxDate;

    public WalletSifIncentiveHolder(@NonNull View itemView) {
        super(itemView);
        mIncentiveCard          = itemView.findViewById(R.id.card_root);

        mCurrentVsAmount        = itemView.findViewById(R.id.current_vs_amount);
        mCurrentLmAmount        = itemView.findViewById(R.id.current_lm_amount);
        mMaxVsAmount            = itemView.findViewById(R.id.max_vs_amount);
        mMaxLmAmount            = itemView.findViewById(R.id.max_lm_amount);
        mMaxDate                = itemView.findViewById(R.id.max_date);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        BigDecimal currentVsAmount = BigDecimal.ZERO;
        BigDecimal currentLmAmount = BigDecimal.ZERO;
        BigDecimal maxVsAmount = BigDecimal.ZERO;
        BigDecimal maxLmAmount = BigDecimal.ZERO;
        String maxDate = "-";

        if (baseData.mSifVsIncentive != null && baseData.mSifLmIncentive != null) {
            currentVsAmount = new BigDecimal(baseData.mSifVsIncentive.totalClaimableCommissionsAndClaimableRewards);
            currentLmAmount = new BigDecimal(baseData.mSifLmIncentive.totalClaimableCommissionsAndClaimableRewards);
            maxVsAmount = new BigDecimal(baseData.mSifVsIncentive.totalRewardsOnDepositedAssetsAtMaturity);
            maxLmAmount = new BigDecimal(baseData.mSifLmIncentive.totalRewardsOnDepositedAssetsAtMaturity);
            maxDate = baseData.mSifVsIncentive.maturityDateISO;
        }

        mCurrentVsAmount.setText(WDp.getDpAmount2(mainActivity, currentVsAmount, 0, 6));
        mCurrentLmAmount.setText(WDp.getDpAmount2(mainActivity, currentLmAmount, 0, 6));
        mMaxVsAmount.setText(WDp.getDpAmount2(mainActivity, maxVsAmount, 0, 6));
        mMaxLmAmount.setText(WDp.getDpAmount2(mainActivity, maxLmAmount, 0, 6));
        mMaxDate.setText(WDp.getTimeformat(mainActivity, maxDate));

    }
}
