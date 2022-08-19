package wannabit.io.cosmostaion.widget.tokenDetail;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class TokenStakingNewHolder extends BaseHolder {
    private CardView            mCardRoot;
    private RelativeLayout      mVestingLayer;

    private TextView            mTotalAmount, mAvailableAmount;
    private TextView            mVestingAmount;
    private TextView            mDelegatedAmount, mUnbondingAmount, mRewardAmount;

    public TokenStakingNewHolder(@NonNull View itemView) {
        super(itemView);
        mCardRoot               = itemView.findViewById(R.id.card_root);
        mTotalAmount            = itemView.findViewById(R.id.total_amount);
        mAvailableAmount        = itemView.findViewById(R.id.available_amount);
        mVestingAmount          = itemView.findViewById(R.id.vesting_amount);
        mDelegatedAmount        = itemView.findViewById(R.id.delegated_amount);
        mUnbondingAmount        = itemView.findViewById(R.id.unbonding_amount);
        mRewardAmount           = itemView.findViewById(R.id.reward_amount);

        mVestingLayer           = itemView.findViewById(R.id.vesting_layer);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final int stakingDivideDecimal = WDp.getDenomDecimal(baseData, ChainFactory.getChain(chain), denom);
        final int stakingDisplayDecimal = WDp.mainDisplayDecimal(chain);
        final BigDecimal totalToken = baseData.getAllMainAsset(denom);
        mTotalAmount.setText(WDp.getDpAmount2(c, totalToken, stakingDivideDecimal, stakingDisplayDecimal));
        mAvailableAmount.setText(WDp.getDpAmount2(c, baseData.getAvailable(denom), stakingDivideDecimal, stakingDisplayDecimal));
        mDelegatedAmount.setText(WDp.getDpAmount2(c, baseData.getDelegationSum(), stakingDivideDecimal, stakingDisplayDecimal));
        mUnbondingAmount.setText(WDp.getDpAmount2(c, baseData.getUndelegationSum(), stakingDivideDecimal, stakingDisplayDecimal));
        mRewardAmount.setText(WDp.getDpAmount2(c, baseData.getRewardSum(denom), stakingDivideDecimal, stakingDisplayDecimal));

        final BigDecimal vestingAmount = baseData.getVesting(denom);
        if (vestingAmount.compareTo(BigDecimal.ZERO) > 0) {
            mVestingLayer.setVisibility(View.VISIBLE);
            mVestingAmount.setText(WDp.getDpAmount2(c, vestingAmount, stakingDivideDecimal, stakingDisplayDecimal));
        }
        mCardRoot.setCardBackgroundColor(WDp.getChainBgColor(c, chain));
    }
}
