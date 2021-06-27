package wannabit.io.cosmostaion.widget.tokenDetail;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class TokenStarnameHolder extends BaseHolder {
    public TextView mTvIovTotal, mTvIovValue, mTvIovAvailable, mTvIovDelegated, mTvIovUnBonding, mTvIovRewards;

    public TokenStarnameHolder(@NonNull View itemView) {
        super(itemView);
        mTvIovTotal         = itemView.findViewById(R.id.iov_total_amount);
        mTvIovValue         = itemView.findViewById(R.id.iov_total_value);
        mTvIovAvailable     = itemView.findViewById(R.id.iov_available);
        mTvIovDelegated     = itemView.findViewById(R.id.iov_delegate);
        mTvIovUnBonding     = itemView.findViewById(R.id.iov_unbonding);
        mTvIovRewards       = itemView.findViewById(R.id.iov_reward);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvIovTotal.setText(WDp.getDpAmount2(c, totalAmount, 6, 6));
        mTvIovAvailable.setText(WDp.getDpAmount2(c, availableAmount, 6, 6));
        mTvIovDelegated.setText(WDp.getDpAmount2(c, delegateAmount, 6, 6));
        mTvIovUnBonding.setText(WDp.getDpAmount2(c, unbondingAmount, 6, 6));
        mTvIovRewards.setText(WDp.getDpAmount2(c, rewardAmount, 6, 6));
        mTvIovValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));

    }
}
