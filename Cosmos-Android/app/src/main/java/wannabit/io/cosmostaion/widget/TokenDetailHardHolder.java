package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;

public class TokenDetailHardHolder extends BaseHolder {
    private TextView mTvHardTotal, mTvHardValue, mTvHardAvailable, mTvHardVesting;

    public TokenDetailHardHolder(@NonNull View itemView) {
        super(itemView);
        mTvHardTotal        = itemView.findViewById(R.id.hard_amount);
        mTvHardValue        = itemView.findViewById(R.id.hard_value);
        mTvHardAvailable    = itemView.findViewById(R.id.hard_available);
        mTvHardVesting      = itemView.findViewById(R.id.hard_vesting);
    }

    public void onBindHard(Context context, BaseChain chain, BaseData baseData) {
        final int dpDecimal = 6;
        final BigDecimal availableAmount    = WDp.getAvailableCoin(baseData.mBalances, TOKEN_HARD);
        final BigDecimal vestingAmount      = WDp.getKavaVestingAmount(baseData.mBalances, TOKEN_HARD);
        final BigDecimal totalValue         = WDp.kavaTokenDollorValue(chain, baseData, TOKEN_HARD, availableAmount.add(vestingAmount));
        final BigDecimal convertedToKava    = totalValue.divide(baseData.getLastKavaDollorTic(), 6, RoundingMode.DOWN);

        mTvHardAvailable.setText(WDp.getDpAmount2(context, availableAmount, dpDecimal, dpDecimal));
        mTvHardVesting.setText(WDp.getDpAmount2(context, vestingAmount, dpDecimal, dpDecimal));
        mTvHardTotal.setText(WDp.getDpAmount2(context, availableAmount.add(vestingAmount), dpDecimal, dpDecimal));
        mTvHardValue.setText(WDp.getValueOfKava(context, baseData, convertedToKava.movePointRight(6)));

    }
}
