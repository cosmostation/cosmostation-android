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

public class WalletProvenanceHolder extends BaseHolder {

    public TextView         mTvHashTotal, mTvHashValue, mTvHashAvailable, mTvHashDelegated, mTvHashUnBonding, mTvHashRewards;
    public RelativeLayout   mHashVestingLayer;
    public TextView         mTvHashVesting;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletProvenanceHolder(@NonNull View itemView) {
        super(itemView);
        mTvHashTotal        = itemView.findViewById(R.id.hash_amount);
        mTvHashValue        = itemView.findViewById(R.id.hash_value);
        mTvHashAvailable    = itemView.findViewById(R.id.hash_available);
        mTvHashDelegated    = itemView.findViewById(R.id.hash_delegate);
        mTvHashUnBonding    = itemView.findViewById(R.id.hash_unbonding);
        mTvHashRewards      = itemView.findViewById(R.id.hash_reward);

        mHashVestingLayer   = itemView.findViewById(R.id.hash_vesting_layer);
        mTvHashVesting      = itemView.findViewById(R.id.hash_vesting);

        mBtnStake            = itemView.findViewById(R.id.btn_hash_reward);
        mBtnVote             = itemView.findViewById(R.id.btn_hash_vote);
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

        mTvHashTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 9, 6));
        mTvHashAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 9, 6));
        mTvHashVesting.setText(WDp.getDpAmount2(mainActivity, vestingAmount, 9, 6));
        mTvHashDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 9, 6));
        mTvHashUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 9, 6));
        mTvHashRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 9, 6));
        mTvHashValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 9));

        if (!vestingAmount.equals(BigDecimal.ZERO)) { mHashVestingLayer.setVisibility(View.VISIBLE);
        } else { mHashVestingLayer.setVisibility(View.GONE); }

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
