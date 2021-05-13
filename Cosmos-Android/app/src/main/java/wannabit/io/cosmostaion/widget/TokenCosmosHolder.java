package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class TokenCosmosHolder extends BaseHolder {
    private TextView mTvAtomTotal, mTvAtomValue, mTvAtomAvailable, mTvAtomDelegated, mTvAtomUnBonding, mTvAtomRewards;

    public TokenCosmosHolder(@NonNull View itemView) {
        super(itemView);
        mTvAtomTotal            = itemView.findViewById(R.id.dash_atom_amount);
        mTvAtomValue            = itemView.findViewById(R.id.dash_atom_value);
        mTvAtomAvailable        = itemView.findViewById(R.id.dash_atom_undelegate);
        mTvAtomDelegated        = itemView.findViewById(R.id.dash_atom_delegate);
        mTvAtomUnBonding        = itemView.findViewById(R.id.dash_atom_unbonding);
        mTvAtomRewards          = itemView.findViewById(R.id.dash_atom_reward);

    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = availableAmount.add(delegateAmount).add(unbondingAmount).add(rewardAmount);

        mTvAtomTotal.setText(WDp.getDpAmount2(c, totalAmount, 6, 6));
        mTvAtomAvailable.setText(WDp.getDpAmount2(c, availableAmount, 6, 6));
        mTvAtomDelegated.setText(WDp.getDpAmount2(c, delegateAmount, 6, 6));
        mTvAtomUnBonding.setText(WDp.getDpAmount2(c, unbondingAmount, 6, 6));
        mTvAtomRewards.setText(WDp.getDpAmount2(c, rewardAmount, 6, 6));
        mTvAtomValue.setText(WDp.getDpMainAssetValue(c, baseData, totalAmount, chain));

    }
}
