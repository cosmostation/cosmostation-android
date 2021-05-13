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

public class WalletCrytoHolder extends BaseHolder {
    public TextView         mTvCroTotal, mTvCroValue, mTvCroAvailable, mTvCroDelegated, mTvCroUnBonding, mTvCroRewards;
    public RelativeLayout   mCroVestingLayer;
    public TextView         mTvCroVesting;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletCrytoHolder(@NonNull View itemView) {
        super(itemView);
        mTvCroTotal         = itemView.findViewById(R.id.cro_amount);
        mTvCroValue         = itemView.findViewById(R.id.cro_value);
        mTvCroAvailable     = itemView.findViewById(R.id.cro_available);
        mTvCroDelegated     = itemView.findViewById(R.id.cro_delegate);
        mTvCroUnBonding     = itemView.findViewById(R.id.cro_unbonding);
        mTvCroRewards       = itemView.findViewById(R.id.cro_reward);

        mCroVestingLayer    = itemView.findViewById(R.id.cro_vesting_layer);
        mTvCroVesting       = itemView.findViewById(R.id.cro_vesting);

        mBtnStake           = itemView.findViewById(R.id.btn_cro_reward);
        mBtnVote            = itemView.findViewById(R.id.btn_cro_vote);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final BigDecimal availableAmount = baseData.getAvailable(WDp.mainDenom(mainActivity.mBaseChain));
        final BigDecimal vestingAmount = baseData.getVesting(WDp.mainDenom(mainActivity.mBaseChain));
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(WDp.mainDenom(mainActivity.mBaseChain));
        final BigDecimal totalAmount = baseData.getAllMainAsset(WDp.mainDenom(mainActivity.mBaseChain));

        mTvCroTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 8, 6));
        mTvCroAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 8, 6));
        mTvCroVesting.setText(WDp.getDpAmount2(mainActivity, vestingAmount, 8, 6));
        mTvCroDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 8, 6));
        mTvCroUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 8, 6));
        mTvCroRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 8, 6));
        mTvCroValue.setText(WDp.getDpMainAssetValue(mainActivity, baseData, totalAmount, mainActivity.mBaseChain));

        if (!vestingAmount.equals(BigDecimal.ZERO)) { mCroVestingLayer.setVisibility(View.VISIBLE);
        } else { mCroVestingLayer.setVisibility(View.GONE); }

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
