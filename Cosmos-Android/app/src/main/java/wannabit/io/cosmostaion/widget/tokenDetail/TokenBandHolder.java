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

public class TokenBandHolder extends BaseHolder {
    public TextView mTvBandTotal, mTvBandValue, mTvBandAvailable, mTvBandDelegated, mTvBandUnBonding, mTvBandRewards;

    public TokenBandHolder(@NonNull View itemView) {
        super(itemView);
        mTvBandTotal       = itemView.findViewById(R.id.band_amount);
        mTvBandValue       = itemView.findViewById(R.id.band_value);
        mTvBandAvailable   = itemView.findViewById(R.id.band_available);
        mTvBandDelegated   = itemView.findViewById(R.id.band_delegate);
        mTvBandUnBonding   = itemView.findViewById(R.id.band_unbonding);
        mTvBandRewards     = itemView.findViewById(R.id.band_reward);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvBandTotal.setText(WDp.getDpAmount2(c, totalAmount, 6, 6));
        mTvBandAvailable.setText(WDp.getDpAmount2(c, availableAmount, 6, 6));
        mTvBandDelegated.setText(WDp.getDpAmount2(c, delegateAmount, 6, 6));
        mTvBandUnBonding.setText(WDp.getDpAmount2(c, unbondingAmount, 6, 6));
        mTvBandRewards.setText(WDp.getDpAmount2(c, rewardAmount, 6, 6));
        mTvBandValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));

    }
}
