package wannabit.io.cosmostaion.widget;

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

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BAND;

public class WalletBandHolder extends BaseHolder {
    public TextView mTvBandTotal, mTvBandValue, mTvBandAvailable, mTvBandDelegated, mTvBandUnBonding, mTvBandRewards;
    public RelativeLayout mBtnStake, mBtnVote;

    public WalletBandHolder(@NonNull View itemView) {
        super(itemView);
        mTvBandTotal        = itemView.findViewById(R.id.band_total_amount);
        mTvBandValue        = itemView.findViewById(R.id.band_total_value);
        mTvBandAvailable    = itemView.findViewById(R.id.band_available);
        mTvBandDelegated    = itemView.findViewById(R.id.band_delegate);
        mTvBandUnBonding    = itemView.findViewById(R.id.band_unbonding);
        mTvBandRewards      = itemView.findViewById(R.id.band_reward);
        mBtnStake           = itemView.findViewById(R.id.btn_band_delegate);
        mBtnVote            = itemView.findViewById(R.id.btn_band_vote);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final BigDecimal availableAmount = WDp.getAvailableCoin(baseData.mBalances, TOKEN_BAND);
        final BigDecimal delegateAmount = WDp.getAllDelegatedAmount(baseData.mBondings, baseData.mAllValidators, mainActivity.mBaseChain);
        final BigDecimal unbondingAmount = WDp.getAllUnbondingAmount(baseData.mUnbondings);
        final BigDecimal rewardAmount = WDp.getAllRewardAmount(baseData.mRewards, TOKEN_BAND);
        final BigDecimal totalAmount = availableAmount.add(delegateAmount).add(unbondingAmount).add(rewardAmount);

        mTvBandTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvBandAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvBandDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvBandUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvBandRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvBandValue.setText(WDp.getValueOfBand(mainActivity, baseData, totalAmount));

        mainActivity.getBaseDao().onUpdateLastTotalAccount(mainActivity.mAccount, totalAmount.toPlainString());

        mBtnStake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent validators = new Intent(mainActivity, ValidatorListActivity.class);
                validators.putExtra("rewards", baseData.mRewards);
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
