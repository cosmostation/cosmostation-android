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

public class TokenOsmosisHolder extends BaseHolder {
    public TextView mTvOsmosisTotal, mTvOsmosisAvailable, mTvOsmosisDelegated, mTvOsmosisUnBonding, mTvOsmosisRewards;

    public TokenOsmosisHolder(@NonNull View itemView) {
        super(itemView);
        mTvOsmosisTotal       = itemView.findViewById(R.id.osmosis_amount);
        mTvOsmosisAvailable   = itemView.findViewById(R.id.osmosis_available);
        mTvOsmosisDelegated   = itemView.findViewById(R.id.osmosis_delegate);
        mTvOsmosisUnBonding   = itemView.findViewById(R.id.osmosis_unbonding);
        mTvOsmosisRewards     = itemView.findViewById(R.id.osmosis_reward);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvOsmosisTotal.setText(WDp.getDpAmount2(c, totalAmount, 6, 6));
        mTvOsmosisAvailable.setText(WDp.getDpAmount2(c, availableAmount, 6, 6));
        mTvOsmosisDelegated.setText(WDp.getDpAmount2(c, delegateAmount, 6, 6));
        mTvOsmosisUnBonding.setText(WDp.getDpAmount2(c, unbondingAmount, 6, 6));
        mTvOsmosisRewards.setText(WDp.getDpAmount2(c, rewardAmount, 6, 6));
    }
}
