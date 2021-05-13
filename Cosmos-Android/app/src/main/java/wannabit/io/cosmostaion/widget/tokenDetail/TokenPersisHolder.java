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

public class TokenPersisHolder extends BaseHolder {
    public TextView         mTvXprtTotal, mTvXprtValue, mTvXprtAvailable, mTvXprtDelegated, mTvXprtUnBonding, mTvXprtRewards;
    public RelativeLayout   mXprtVestingLayer;
    public TextView         mTvXprtVesting;

    public TokenPersisHolder(@NonNull View itemView) {
        super(itemView);
        mTvXprtTotal        = itemView.findViewById(R.id.xprt_amount);
        mTvXprtValue        = itemView.findViewById(R.id.xprt_value);
        mTvXprtAvailable    = itemView.findViewById(R.id.xprt_available);
        mTvXprtDelegated    = itemView.findViewById(R.id.xprt_delegate);
        mTvXprtUnBonding    = itemView.findViewById(R.id.xprt_unbonding);
        mTvXprtRewards      = itemView.findViewById(R.id.xprt_reward);

        mXprtVestingLayer   = itemView.findViewById(R.id.xprt_vesting_layer);
        mTvXprtVesting      = itemView.findViewById(R.id.xprt_vesting);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal vestingAmount = baseData.getVesting(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvXprtTotal.setText(WDp.getDpAmount2(c, totalAmount, 6, 6));
        mTvXprtAvailable.setText(WDp.getDpAmount2(c, availableAmount, 6, 6));
        mTvXprtVesting.setText(WDp.getDpAmount2(c, vestingAmount, 6, 6));
        mTvXprtDelegated.setText(WDp.getDpAmount2(c, delegateAmount, 6, 6));
        mTvXprtUnBonding.setText(WDp.getDpAmount2(c, unbondingAmount, 6, 6));
        mTvXprtRewards.setText(WDp.getDpAmount2(c, rewardAmount, 6, 6));
        mTvXprtValue.setText(WDp.getDpMainAssetValue(c, baseData, totalAmount, chain));
        if (!vestingAmount.equals(BigDecimal.ZERO)) { mXprtVestingLayer.setVisibility(View.VISIBLE);
        } else { mXprtVestingLayer.setVisibility(View.GONE); }

    }
}
