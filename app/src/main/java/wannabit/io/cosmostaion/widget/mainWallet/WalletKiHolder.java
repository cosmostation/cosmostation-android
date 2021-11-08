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


public class WalletKiHolder extends BaseHolder {
    public TextView mTvKiTotal, mTvKiValue, mTvKiAvailable, mTvKiDelegated, mTvKiUnBonding, mTvKiRewards;
    public RelativeLayout mKiVestingLayer;
    public TextView         mTvKiVesting;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletKiHolder(@NonNull View itemView) {
        super(itemView);
        mTvKiTotal         = itemView.findViewById(R.id.ki_amount);
        mTvKiValue         = itemView.findViewById(R.id.ki_value);
        mTvKiAvailable     = itemView.findViewById(R.id.ki_available);
        mTvKiDelegated     = itemView.findViewById(R.id.ki_delegate);
        mTvKiUnBonding     = itemView.findViewById(R.id.ki_unbonding);
        mTvKiRewards       = itemView.findViewById(R.id.ki_reward);

        mKiVestingLayer    = itemView.findViewById(R.id.ki_vesting_layer);
        mTvKiVesting       = itemView.findViewById(R.id.ki_vesting);

        mBtnStake           = itemView.findViewById(R.id.btn_ki_reward);
        mBtnVote            = itemView.findViewById(R.id.btn_ki_vote);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final String denom = WDp.mainDenom(mainActivity.mBaseChain);
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvKiTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvKiAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvKiDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvKiUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvKiRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvKiValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));
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
