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

public class TokenAltheaHolder extends BaseHolder {
    public TextView mTvAltheaTotal, mTvAltheaValue, mTvAltheaAvailable, mTvAltheaDelegated, mTvAltheaUnBonding, mTvAltheaRewards;

    public TokenAltheaHolder(@NonNull View itemView) {
        super(itemView);
        mTvAltheaTotal       = itemView.findViewById(R.id.althea_amount);
        mTvAltheaValue       = itemView.findViewById(R.id.althea_value);
        mTvAltheaAvailable   = itemView.findViewById(R.id.althea_available);
        mTvAltheaDelegated   = itemView.findViewById(R.id.althea_delegate);
        mTvAltheaUnBonding   = itemView.findViewById(R.id.althea_unbonding);
        mTvAltheaRewards     = itemView.findViewById(R.id.althea_reward);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvAltheaTotal.setText(WDp.getDpAmount2(c, totalAmount, 18, 18));
        mTvAltheaAvailable.setText(WDp.getDpAmount2(c, availableAmount, 18, 18));
        mTvAltheaDelegated.setText(WDp.getDpAmount2(c, delegateAmount, 18, 18));
        mTvAltheaUnBonding.setText(WDp.getDpAmount2(c, unbondingAmount, 18, 18));
        mTvAltheaRewards.setText(WDp.getDpAmount2(c, rewardAmount, 18, 18));
        mTvAltheaValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 18));

    }
}
