package wannabit.io.cosmostaion.widget.mainWallet;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.ValidatorListActivity;
import wannabit.io.cosmostaion.activities.VoteListActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletCudosHolder extends BaseHolder {

    public TextView         mTvCudosTotal, mTvCudosValue, mTvCudosAvailable, mTvCudosDelegated, mTvCudosUnBonding, mTvCudosRewards;
    public RelativeLayout   mCudosVestingLayer;
    public TextView         mTvCudosVesting;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletCudosHolder(@NonNull View itemView) {
        super(itemView);
        mTvCudosTotal        = itemView.findViewById(R.id.cudos_amount);
        mTvCudosValue        = itemView.findViewById(R.id.cudos_value);
        mTvCudosAvailable    = itemView.findViewById(R.id.cudos_available);
        mTvCudosDelegated    = itemView.findViewById(R.id.cudos_delegate);
        mTvCudosUnBonding    = itemView.findViewById(R.id.cudos_unbonding);
        mTvCudosRewards      = itemView.findViewById(R.id.cudos_reward);

        mCudosVestingLayer   = itemView.findViewById(R.id.cudos_vesting_layer);
        mTvCudosVesting      = itemView.findViewById(R.id.cudos_vesting);

        mBtnStake            = itemView.findViewById(R.id.btn_cudos_reward);
        mBtnVote             = itemView.findViewById(R.id.btn_cudos_vote);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final String denom = WDp.mainDenom(mainActivity.mBaseChain);
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal vestingAmount = baseData.getVesting(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvCudosTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 18, 6));
        mTvCudosAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 18, 6));
        mTvCudosVesting.setText(WDp.getDpAmount2(mainActivity, vestingAmount, 18, 6));
        mTvCudosDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 18, 6));
        mTvCudosUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 18, 6));
        mTvCudosRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 18, 6));
        mTvCudosValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 18));

        if (!vestingAmount.equals(BigDecimal.ZERO)) { mCudosVestingLayer.setVisibility(View.VISIBLE);
        } else { mCudosVestingLayer.setVisibility(View.GONE); }

        mainActivity.getBaseDao().onUpdateLastTotalAccount(mainActivity.mAccount, totalAmount.toPlainString());

        mBtnStake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent validators = new Intent(mainActivity, ValidatorListActivity.class);
                mainActivity.startActivity(validators);
            }
        });
        mBtnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent proposals = new Intent(mainActivity, VoteListActivity.class);
                mainActivity.startActivity(proposals);
            }
        });
    }
}
