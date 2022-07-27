package wannabit.io.cosmostaion.widget.tokenDetail;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class TokenStakingOldHolder extends BaseHolder {
    private CardView        mCardRoot;
    private RelativeLayout  mVestingLayer, mDelegatedLayer, mUnbondingLayer, mRewardLayer;
    private RelativeLayout  mLockedLayer, mFrozenLayer;
    private RelativeLayout  mOkStakingLayer, mOkUnbondingLayer;

    private TextView        mTotalAmount, mAvailableAmount;
    private TextView        mlockedAmount, mFrozenAmount;
    private TextView        mOkStakingAmount, mOkUnbondingAmount;

    public TokenStakingOldHolder(@NonNull View itemView) {
        super(itemView);
        mCardRoot               = itemView.findViewById(R.id.card_root);
        mTotalAmount            = itemView.findViewById(R.id.total_amount);
        mAvailableAmount        = itemView.findViewById(R.id.available_amount);

        mlockedAmount           = itemView.findViewById(R.id.locked_amount);
        mFrozenAmount           = itemView.findViewById(R.id.frozen_amount);

        mOkStakingAmount        = itemView.findViewById(R.id.ok_staking_amount);
        mOkUnbondingAmount      = itemView.findViewById(R.id.ok_unbonding_amount);

        mVestingLayer           = itemView.findViewById(R.id.vesting_layer);
        mDelegatedLayer         = itemView.findViewById(R.id.delegated_layer);
        mUnbondingLayer         = itemView.findViewById(R.id.unbonding_layer);
        mRewardLayer            = itemView.findViewById(R.id.reward_layer);
        mLockedLayer            = itemView.findViewById(R.id.locked_layer);
        mFrozenLayer            = itemView.findViewById(R.id.frozen_layer);
        mOkStakingLayer         = itemView.findViewById(R.id.ok_staking_layer);
        mOkUnbondingLayer       = itemView.findViewById(R.id.ok_unbonding_layer);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final ChainConfig chainConfig = ChainFactory.getChain(chain);
        final int stakingDivideDecimal = WDp.getDenomDecimal(baseData, chainConfig, denom);
        final int stakingDisplayDecimal = WDp.mainDisplayDecimal(chain);

        if (chain.equals(BaseChain.BNB_MAIN)) {
            mVestingLayer.setVisibility(View.GONE);
            mDelegatedLayer.setVisibility(View.GONE);
            mUnbondingLayer.setVisibility(View.GONE);
            mRewardLayer.setVisibility(View.GONE);
            mLockedLayer.setVisibility(View.GONE);
            mFrozenLayer.setVisibility(View.VISIBLE);
            mOkStakingLayer.setVisibility(View.GONE);
            mOkUnbondingLayer.setVisibility(View.GONE);

            final BigDecimal totalAmount = baseData.getAllBnbTokenAmount(denom);
            final BigDecimal availableAmount = baseData.availableAmount(denom);
            final BigDecimal lockedAmount = baseData.lockedAmount(denom);
            final BigDecimal frozenAmount = baseData.frozenAmount(denom);
            mTotalAmount.setText(WDp.getDpAmount2(c, totalAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mAvailableAmount.setText(WDp.getDpAmount2(c, availableAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mlockedAmount.setText(WDp.getDpAmount2(c, lockedAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mFrozenAmount.setText(WDp.getDpAmount2(c, frozenAmount, stakingDivideDecimal, stakingDisplayDecimal));

        } else {
            mVestingLayer.setVisibility(View.GONE);
            mDelegatedLayer.setVisibility(View.GONE);
            mUnbondingLayer.setVisibility(View.GONE);
            mRewardLayer.setVisibility(View.GONE);
            mLockedLayer.setVisibility(View.GONE);
            mFrozenLayer.setVisibility(View.GONE);
            mOkStakingLayer.setVisibility(View.VISIBLE);
            mOkUnbondingLayer.setVisibility(View.VISIBLE);

            final BigDecimal totalAmount = baseData.getAllExToken(denom);
            final BigDecimal availableAmount = baseData.availableAmount(denom);
            final BigDecimal lockedAmount = baseData.lockedAmount(denom);
            final BigDecimal depositAmount = baseData.okDepositAmount();
            final BigDecimal withdrawAmount = baseData.okWithdrawAmount();
            mTotalAmount.setText(WDp.getDpAmount2(c, totalAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mAvailableAmount.setText(WDp.getDpAmount2(c, availableAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mlockedAmount.setText(WDp.getDpAmount2(c, lockedAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mOkStakingAmount.setText(WDp.getDpAmount2(c, depositAmount, stakingDivideDecimal, stakingDisplayDecimal));
            mOkUnbondingAmount.setText(WDp.getDpAmount2(c, withdrawAmount, stakingDivideDecimal, stakingDisplayDecimal));

        }
        mCardRoot.setCardBackgroundColor(ContextCompat.getColor(c, chainConfig.chainBgColor()));
    }
}
