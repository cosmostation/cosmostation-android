package wannabit.io.cosmostaion.widget.tokenDetail;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class TokenAkashHolder extends BaseHolder {
    private TextView        mTvAkashTotal, mTvAkashValue, mTvAkashAvailable, mTvAkashDelegated, mTvAkashUnBonding, mTvAkashRewards;
    public RelativeLayout   mAkashVestingLayer;
    public TextView         mTvAkashVesting;

    public TokenAkashHolder(@NonNull View itemView) {
        super(itemView);
        mTvAkashTotal       = itemView.findViewById(R.id.akash_amount);
        mTvAkashValue       = itemView.findViewById(R.id.akash_value);
        mTvAkashAvailable   = itemView.findViewById(R.id.akash_available);
        mTvAkashDelegated   = itemView.findViewById(R.id.akash_delegate);
        mTvAkashUnBonding   = itemView.findViewById(R.id.akash_unbonding);
        mTvAkashRewards     = itemView.findViewById(R.id.akash_reward);

        mAkashVestingLayer  = itemView.findViewById(R.id.akash_vesting_layer);
        mTvAkashVesting     = itemView.findViewById(R.id.akash_vesting);

    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal vestingAmount = baseData.getVesting(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvAkashTotal.setText(WDp.getDpAmount2(c, totalAmount, 6, 6));
        mTvAkashAvailable.setText(WDp.getDpAmount2(c, availableAmount, 6, 6));
        mTvAkashVesting.setText(WDp.getDpAmount2(c, vestingAmount, 6, 6));
        mTvAkashDelegated.setText(WDp.getDpAmount2(c, delegateAmount, 6, 6));
        mTvAkashUnBonding.setText(WDp.getDpAmount2(c, unbondingAmount, 6, 6));
        mTvAkashRewards.setText(WDp.getDpAmount2(c, rewardAmount, 6, 6));
        mTvAkashValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));
        if (!vestingAmount.equals(BigDecimal.ZERO)) { mAkashVestingLayer.setVisibility(View.VISIBLE);
        } else { mAkashVestingLayer.setVisibility(View.GONE); }
    }
}
