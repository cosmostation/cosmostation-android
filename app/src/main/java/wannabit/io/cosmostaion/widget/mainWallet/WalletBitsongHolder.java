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

public class WalletBitsongHolder extends BaseHolder {

    public TextView         mTvBitsongTotal, mTvBitsongValue, mTvBitsongAvailable, mTvBitsongDelegated, mTvBitsongUnBonding, mTvBitsongRewards;
    public RelativeLayout   mBitsongVestingLayer;
    public TextView         mTvBitsongVesting;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletBitsongHolder(@NonNull View itemView) {
        super(itemView);
        mTvBitsongTotal        = itemView.findViewById(R.id.bitsong_amount);
        mTvBitsongValue        = itemView.findViewById(R.id.bitsong_value);
        mTvBitsongAvailable    = itemView.findViewById(R.id.bitsong_available);
        mTvBitsongDelegated    = itemView.findViewById(R.id.bitsong_delegate);
        mTvBitsongUnBonding    = itemView.findViewById(R.id.bitsong_unbonding);
        mTvBitsongRewards      = itemView.findViewById(R.id.bitsong_reward);

        mBitsongVestingLayer   = itemView.findViewById(R.id.bitsong_vesting_layer);
        mTvBitsongVesting      = itemView.findViewById(R.id.bitsong_vesting);

        mBtnStake              = itemView.findViewById(R.id.btn_bitsong_reward);
        mBtnVote               = itemView.findViewById(R.id.btn_bitsong_vote);
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

        mTvBitsongTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvBitsongAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvBitsongVesting.setText(WDp.getDpAmount2(mainActivity, vestingAmount, 6, 6));
        mTvBitsongDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvBitsongUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvBitsongRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvBitsongValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));

        if (!vestingAmount.equals(BigDecimal.ZERO)) { mBitsongVestingLayer.setVisibility(View.VISIBLE);
        } else { mBitsongVestingLayer.setVisibility(View.GONE); }

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
