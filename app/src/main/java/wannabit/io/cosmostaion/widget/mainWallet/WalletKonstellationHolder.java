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

public class WalletKonstellationHolder extends BaseHolder {

    public TextView         mTvKonstealltionTotal, mTvKonstealltionValue, mTvKonstealltionAvailable, mTvKonstealltionDelegated, mTvKonstealltionUnBonding, mTvKonstealltionRewards;
    public RelativeLayout   mKonstealltionVestingLayer;
    public TextView         mTvKonstealltionVesting;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletKonstellationHolder(@NonNull View itemView) {
        super(itemView);
        mTvKonstealltionTotal        = itemView.findViewById(R.id.konstellation_amount);
        mTvKonstealltionValue        = itemView.findViewById(R.id.konstellation_value);
        mTvKonstealltionAvailable    = itemView.findViewById(R.id.konstellation_available);
        mTvKonstealltionDelegated    = itemView.findViewById(R.id.konstellation_delegate);
        mTvKonstealltionUnBonding    = itemView.findViewById(R.id.konstellation_unbonding);
        mTvKonstealltionRewards      = itemView.findViewById(R.id.konstellation_reward);

        mKonstealltionVestingLayer   = itemView.findViewById(R.id.konstellation_vesting_layer);
        mTvKonstealltionVesting      = itemView.findViewById(R.id.konstellation_vesting);

        mBtnStake          = itemView.findViewById(R.id.btn_konstellation_reward);
        mBtnVote           = itemView.findViewById(R.id.btn_konstellation_vote);
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

        mTvKonstealltionTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvKonstealltionAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvKonstealltionVesting.setText(WDp.getDpAmount2(mainActivity, vestingAmount, 6, 6));
        mTvKonstealltionDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvKonstealltionUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvKonstealltionRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvKonstealltionValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));

        if (!vestingAmount.equals(BigDecimal.ZERO)) { mKonstealltionVestingLayer.setVisibility(View.VISIBLE);
        } else { mKonstealltionVestingLayer.setVisibility(View.GONE); }

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
