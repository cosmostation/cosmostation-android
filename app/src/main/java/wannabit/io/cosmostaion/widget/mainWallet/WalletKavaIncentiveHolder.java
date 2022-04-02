package wannabit.io.cosmostaion.widget.mainWallet;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SWP;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletKavaIncentiveHolder extends BaseHolder {

    private TextView mKavaIncetive;
    private TextView mHardIncetive;
    private TextView mSwpIncetive;
    private RelativeLayout mBtnIncentive;

    public WalletKavaIncentiveHolder(@NonNull View itemView) {
        super(itemView);
        mKavaIncetive = itemView.findViewById(R.id.kava_incentive);
        mHardIncetive = itemView.findViewById(R.id.hard_incentive);
        mSwpIncetive = itemView.findViewById(R.id.swp_incentive);
        mBtnIncentive = itemView.findViewById(R.id.btn_kava_incentive);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        BigDecimal mKavaIncetiveAmount = BigDecimal.ZERO;
        BigDecimal mHardIncetiveAmount = BigDecimal.ZERO;
        BigDecimal mSwpIncetiveAmount = BigDecimal.ZERO;

        if (baseData.mIncentiveRewards != null) {
            mKavaIncetiveAmount = baseData.mIncentiveRewards.getRewardSum(TOKEN_KAVA);
            mHardIncetiveAmount = baseData.mIncentiveRewards.getRewardSum(TOKEN_HARD);
            mSwpIncetiveAmount = baseData.mIncentiveRewards.getRewardSum(TOKEN_SWP);
        }

        mKavaIncetive.setText(WDp.getDpAmount2(mKavaIncetiveAmount, 6, 6));
        mHardIncetive.setText(WDp.getDpAmount2(mHardIncetiveAmount, 6, 6));
        mSwpIncetive.setText(WDp.getDpAmount2(mSwpIncetiveAmount, 6, 6));

        mBtnIncentive.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onClickIncentive();
            }
        });
    }
}
