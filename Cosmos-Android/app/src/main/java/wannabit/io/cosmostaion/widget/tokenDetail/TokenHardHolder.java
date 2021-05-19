package wannabit.io.cosmostaion.widget.tokenDetail;

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
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class TokenHardHolder extends BaseHolder {
    private TextView mTvHardTotal, mTvHardValue, mTvHardAvailable, mTvHardVesting;

    public TokenHardHolder(@NonNull View itemView) {
        super(itemView);
        mTvHardTotal        = itemView.findViewById(R.id.hard_amount);
        mTvHardValue        = itemView.findViewById(R.id.hard_value);
        mTvHardAvailable    = itemView.findViewById(R.id.hard_available);
        mTvHardVesting      = itemView.findViewById(R.id.hard_vesting);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final int dpDecimal = 6;
        final BigDecimal availableAmount    = WDp.getAvailableCoin(baseData.mBalances, TOKEN_HARD);
        final BigDecimal vestingAmount      = WDp.getKavaVestingAmount(baseData.mBalances, TOKEN_HARD);
        final BigDecimal convertedToKava    = WDp.convertTokenToKava(baseData, TOKEN_HARD);


        mTvHardAvailable.setText(WDp.getDpAmount2(c, availableAmount, dpDecimal, dpDecimal));
        mTvHardVesting.setText(WDp.getDpAmount2(c, vestingAmount, dpDecimal, dpDecimal));
        mTvHardTotal.setText(WDp.getDpAmount2(c, availableAmount.add(vestingAmount), dpDecimal, dpDecimal));
        mTvHardValue.setText(WDp.dpUserCurrencyValue(baseData, TOKEN_KAVA, convertedToKava, 6));
    }
}
