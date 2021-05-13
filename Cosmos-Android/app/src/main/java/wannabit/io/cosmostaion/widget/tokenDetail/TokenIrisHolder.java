package wannabit.io.cosmostaion.widget.tokenDetail;

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
import wannabit.io.cosmostaion.widget.BaseHolder;

public class TokenIrisHolder extends BaseHolder {
    private TextView        mTvIrisTotal, mTvIrisValue, mTvIrisAvailable,
                            mTvIrisDelegated, mTvIrisUnBonding, mTvIrisRewards;

    public TokenIrisHolder(@NonNull View itemView) {
        super(itemView);
        mTvIrisTotal            = itemView.findViewById(R.id.dash_iris_amount);
        mTvIrisValue            = itemView.findViewById(R.id.dash_iris_value);
        mTvIrisAvailable        = itemView.findViewById(R.id.dash_iris_undelegate);
        mTvIrisDelegated        = itemView.findViewById(R.id.dash_iris_delegate);
        mTvIrisUnBonding        = itemView.findViewById(R.id.dash_iris_unbonding);
        mTvIrisRewards          = itemView.findViewById(R.id.dash_iris_reward);

    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = availableAmount.add(delegateAmount).add(unbondingAmount).add(rewardAmount);

        mTvIrisTotal.setText(WDp.getDpAmount2(c, totalAmount, 6, 6));
        mTvIrisAvailable.setText(WDp.getDpAmount2(c, availableAmount, 6, 6));
        mTvIrisDelegated.setText(WDp.getDpAmount2(c, delegateAmount, 6, 6));
        mTvIrisUnBonding.setText(WDp.getDpAmount2(c, unbondingAmount, 6, 6));
        mTvIrisRewards.setText(WDp.getDpAmount2(c, rewardAmount, 6, 6));
        mTvIrisValue.setText(WDp.getDpMainAssetValue(c, baseData, totalAmount, chain));

    }
}
