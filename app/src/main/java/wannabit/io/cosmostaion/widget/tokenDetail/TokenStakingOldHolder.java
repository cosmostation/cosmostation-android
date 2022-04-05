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
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class TokenStakingOldHolder extends BaseHolder {
    private final CardView mCardRoot;
    private final RelativeLayout mVestingLayer;
    private final RelativeLayout mDelegatedLayer;
    private final RelativeLayout mUnbondingLayer;
    private final RelativeLayout mRewardLayer;
    private final RelativeLayout mLockedLayer;
    private final RelativeLayout mFrozenLayer;
    private final RelativeLayout mOkStakingLayer;
    private final RelativeLayout mOkUnbondingLayer;

    private final TextView mTotalAmount;
    private final TextView mAvailableAmount;
    private final TextView mDelegatedAmount;
    private final TextView mUnbondingAmount;
    private final TextView mRewardAmount;
    private final TextView mlockedAmount;
    private final TextView mFrozenAmount;
    private final TextView mOkStakingAmount;
    private final TextView mOkUnbondingAmount;

    public TokenStakingOldHolder(@NonNull View itemView) {
        super(itemView);
        mCardRoot = itemView.findViewById(R.id.card_root);
        mTotalAmount = itemView.findViewById(R.id.total_amount);
        mAvailableAmount = itemView.findViewById(R.id.available_amount);
        mDelegatedAmount = itemView.findViewById(R.id.delegated_amount);
        mUnbondingAmount = itemView.findViewById(R.id.unbonding_amount);
        mRewardAmount = itemView.findViewById(R.id.reward_amount);

        mlockedAmount = itemView.findViewById(R.id.locked_amount);
        mFrozenAmount = itemView.findViewById(R.id.frozen_amount);

        mOkStakingAmount = itemView.findViewById(R.id.ok_staking_amount);
        mOkUnbondingAmount = itemView.findViewById(R.id.ok_unbonding_amount);

        mVestingLayer = itemView.findViewById(R.id.vesting_layer);
        mDelegatedLayer = itemView.findViewById(R.id.delegated_layer);
        mUnbondingLayer = itemView.findViewById(R.id.unbonding_layer);
        mRewardLayer = itemView.findViewById(R.id.reward_layer);
        mLockedLayer = itemView.findViewById(R.id.locked_layer);
        mFrozenLayer = itemView.findViewById(R.id.frozen_layer);
        mOkStakingLayer = itemView.findViewById(R.id.ok_staking_layer);
        mOkUnbondingLayer = itemView.findViewById(R.id.ok_unbonding_layer);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final int stakingDivideDecimal = WDp.mainDivideDecimal(chain);
        final int stakingDisplayDecimal = WDp.mainDisplayDecimal(chain);

        if (chain.equals(BaseChain.BNB_MAIN)) {
            mVestingLayer.setVisibility(View.GONE);
            mDelegatedLayer.setVisibility(View.GONE);
            mUnbondingLayer.setVisibility(View.GONE);
            mRewardLayer.setVisibility(View.GONE);
            mLockedLayer.setVisibility(View.VISIBLE);
            mFrozenLayer.setVisibility(View.VISIBLE);
            mOkStakingLayer.setVisibility(View.GONE);
            mOkUnbondingLayer.setVisibility(View.GONE);

            final BigDecimal totalAmount = baseData.getAllBnbTokenAmount(denom);
            final BigDecimal availableAmount = baseData.availableAmount(denom);
            final BigDecimal lockedAmount = baseData.lockedAmount(denom);
            final BigDecimal frozenAmount = baseData.frozenAmount(denom);
            mTotalAmount.setText(WDp.getDpAmount2(totalAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mAvailableAmount.setText(WDp.getDpAmount2(availableAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mlockedAmount.setText(WDp.getDpAmount2(lockedAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mFrozenAmount.setText(WDp.getDpAmount2(frozenAmount, stakingDivideDecimal, stakingDisplayDecimal));

        } else if (chain.equals(BaseChain.OKEX_MAIN)) {
            mVestingLayer.setVisibility(View.GONE);
            mDelegatedLayer.setVisibility(View.GONE);
            mUnbondingLayer.setVisibility(View.GONE);
            mRewardLayer.setVisibility(View.GONE);
            mLockedLayer.setVisibility(View.VISIBLE);
            mFrozenLayer.setVisibility(View.GONE);
            mOkStakingLayer.setVisibility(View.VISIBLE);
            mOkUnbondingLayer.setVisibility(View.VISIBLE);

            final BigDecimal totalAmount = baseData.getAllExToken(denom);
            final BigDecimal availableAmount = baseData.availableAmount(denom);
            final BigDecimal lockedAmount = baseData.lockedAmount(denom);
            final BigDecimal depositAmount = baseData.okDepositAmount();
            final BigDecimal withdrawAmount = baseData.okWithdrawAmount();
            mTotalAmount.setText(WDp.getDpAmount2(totalAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mAvailableAmount.setText(WDp.getDpAmount2(availableAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mlockedAmount.setText(WDp.getDpAmount2(lockedAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mOkStakingAmount.setText(WDp.getDpAmount2(depositAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mOkUnbondingAmount.setText(WDp.getDpAmount2(withdrawAmount, stakingDivideDecimal, stakingDisplayDecimal));

        } else {
            mVestingLayer.setVisibility(View.GONE);
            mDelegatedLayer.setVisibility(View.VISIBLE);
            mUnbondingLayer.setVisibility(View.VISIBLE);
            mRewardLayer.setVisibility(View.VISIBLE);
            mLockedLayer.setVisibility(View.GONE);
            mFrozenLayer.setVisibility(View.GONE);
            mOkStakingLayer.setVisibility(View.GONE);
            mOkUnbondingLayer.setVisibility(View.GONE);

            final BigDecimal totalAmount = baseData.getAllMainAssetOld(denom);
            final BigDecimal availableAmount = baseData.availableAmount(denom);
            final BigDecimal delegateAmount = baseData.delegatedSumAmount();
            final BigDecimal unbondingAmount = baseData.unbondingSumAmount();
            final BigDecimal rewardAmount = baseData.rewardAmount(denom);
            mTotalAmount.setText(WDp.getDpAmount2(totalAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mAvailableAmount.setText(WDp.getDpAmount2(availableAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mDelegatedAmount.setText(WDp.getDpAmount2(delegateAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mUnbondingAmount.setText(WDp.getDpAmount2(unbondingAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mRewardAmount.setText(WDp.getDpAmount2(rewardAmount, stakingDivideDecimal, stakingDisplayDecimal));
        }
        mCardRoot.setCardBackgroundColor(WDp.getChainBgColor(c, chain));
    }
}
