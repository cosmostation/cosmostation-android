package wannabit.io.cosmostaion.widget;

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

public class TokenCrytoHolder extends BaseHolder {
    public TextView         mTvCroTotal, mTvCroValue, mTvCroAvailable, mTvCroDelegated, mTvCroUnBonding, mTvCroRewards;
    public RelativeLayout   mCroVestingLayer;
    public TextView         mTvCroVesting;

    public TokenCrytoHolder(@NonNull View itemView) {
        super(itemView);
        mTvCroTotal         = itemView.findViewById(R.id.cro_amount);
        mTvCroValue         = itemView.findViewById(R.id.cro_value);
        mTvCroAvailable     = itemView.findViewById(R.id.cro_available);
        mTvCroDelegated     = itemView.findViewById(R.id.cro_delegate);
        mTvCroUnBonding     = itemView.findViewById(R.id.cro_unbonding);
        mTvCroRewards       = itemView.findViewById(R.id.cro_reward);

        mCroVestingLayer    = itemView.findViewById(R.id.cro_vesting_layer);
        mTvCroVesting       = itemView.findViewById(R.id.cro_vesting);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal vestingAmount = baseData.getVesting(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvCroTotal.setText(WDp.getDpAmount2(c, totalAmount, 8, 8));
        mTvCroAvailable.setText(WDp.getDpAmount2(c, availableAmount, 8, 8));
        mTvCroVesting.setText(WDp.getDpAmount2(c, vestingAmount, 8, 8));
        mTvCroDelegated.setText(WDp.getDpAmount2(c, delegateAmount, 8, 8));
        mTvCroUnBonding.setText(WDp.getDpAmount2(c, unbondingAmount, 8, 8));
        mTvCroRewards.setText(WDp.getDpAmount2(c, rewardAmount, 8, 8));
        mTvCroValue.setText(WDp.getDpMainAssetValue(c, baseData, totalAmount, chain));
        if (!vestingAmount.equals(BigDecimal.ZERO)) { mCroVestingLayer.setVisibility(View.VISIBLE);
        } else { mCroVestingLayer.setVisibility(View.GONE); }

    }
}
