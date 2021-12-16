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

public class WalletLumHolder extends BaseHolder {

    public TextView         mTvLumTotal, mTvLumValue, mTvLumAvailable, mTvLumDelegated, mTvLumUnBonding, mTvLumRewards;
    public RelativeLayout   mLumVestingLayer;
    public TextView         mTvLumVesting;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletLumHolder(@NonNull View itemView) {
        super(itemView);
        mTvLumTotal        = itemView.findViewById(R.id.lum_amount);
        mTvLumValue        = itemView.findViewById(R.id.lum_value);
        mTvLumAvailable    = itemView.findViewById(R.id.lum_available);
        mTvLumDelegated    = itemView.findViewById(R.id.lum_delegate);
        mTvLumUnBonding    = itemView.findViewById(R.id.lum_unbonding);
        mTvLumRewards      = itemView.findViewById(R.id.lum_reward);

        mLumVestingLayer   = itemView.findViewById(R.id.lum_vesting_layer);
        mTvLumVesting      = itemView.findViewById(R.id.lum_vesting);

        mBtnStake          = itemView.findViewById(R.id.btn_lum_reward);
        mBtnVote           = itemView.findViewById(R.id.btn_lum_vote);
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

        mTvLumTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvLumAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvLumVesting.setText(WDp.getDpAmount2(mainActivity, vestingAmount, 6, 6));
        mTvLumDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvLumUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvLumRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvLumValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));

        if (!vestingAmount.equals(BigDecimal.ZERO)) { mLumVestingLayer.setVisibility(View.VISIBLE);
        } else { mLumVestingLayer.setVisibility(View.GONE); }

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
