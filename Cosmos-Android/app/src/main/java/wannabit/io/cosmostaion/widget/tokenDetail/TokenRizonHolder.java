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

public class TokenRizonHolder extends BaseHolder {
    public TextView         mTvRizonTotal, mTvRizonValue, mTvRizonAvailable, mTvRizonDelegated, mTvRizonUnBonding, mTvRizonRewards;

    public TokenRizonHolder(@NonNull View itemView) {
        super(itemView);
        mTvRizonTotal       = itemView.findViewById(R.id.rizon_amount);
        mTvRizonValue       = itemView.findViewById(R.id.rizon_value);
        mTvRizonAvailable   = itemView.findViewById(R.id.rizon_available);
        mTvRizonDelegated   = itemView.findViewById(R.id.rizon_delegate);
        mTvRizonUnBonding   = itemView.findViewById(R.id.rizon_unbonding);
        mTvRizonRewards     = itemView.findViewById(R.id.rizon_reward);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvRizonTotal.setText(WDp.getDpAmount2(c, totalAmount, 6, 6));
        mTvRizonAvailable.setText(WDp.getDpAmount2(c, availableAmount, 6, 6));
        mTvRizonDelegated.setText(WDp.getDpAmount2(c, delegateAmount, 6, 6));
        mTvRizonUnBonding.setText(WDp.getDpAmount2(c, unbondingAmount, 6, 6));
        mTvRizonRewards.setText(WDp.getDpAmount2(c, rewardAmount, 6, 6));
        mTvRizonValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));

    }
}