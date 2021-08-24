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

public class TokenMediblocHolder extends BaseHolder {
    public TextView mTvMediTotal, mTvMediAvailable, mTvMediDelegated, mTvMediUnBonding, mTvMediRewards;

    public TokenMediblocHolder(@NonNull View itemView) {
        super(itemView);
        mTvMediTotal       = itemView.findViewById(R.id.medi_amount);
        mTvMediAvailable   = itemView.findViewById(R.id.medi_available);
        mTvMediDelegated   = itemView.findViewById(R.id.medi_delegate);
        mTvMediUnBonding   = itemView.findViewById(R.id.medi_unbonding);
        mTvMediRewards     = itemView.findViewById(R.id.medi_reward);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvMediTotal.setText(WDp.getDpAmount2(c, totalAmount, 6, 6));
        mTvMediAvailable.setText(WDp.getDpAmount2(c, availableAmount, 6, 6));
        mTvMediDelegated.setText(WDp.getDpAmount2(c, delegateAmount, 6, 6));
        mTvMediUnBonding.setText(WDp.getDpAmount2(c, unbondingAmount, 6, 6));
        mTvMediRewards.setText(WDp.getDpAmount2(c, rewardAmount, 6, 6));
    }
}
