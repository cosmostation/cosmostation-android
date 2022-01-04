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

public class WalletChihuahuaHolder extends BaseHolder {

    public TextView         mTvChihuahuaTotal, mTvChihuahuaValue, mTvChihuahuaAvailable, mTvChihuahuaDelegated, mTvChihuahuaUnBonding, mTvChihuahuaRewards;
    public RelativeLayout   mChihuahuaVestingLayer;
    public TextView         mTvChihuahuaVesting;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletChihuahuaHolder(@NonNull View itemView) {
        super(itemView);
        mTvChihuahuaTotal        = itemView.findViewById(R.id.chihuahua_amount);
        mTvChihuahuaValue        = itemView.findViewById(R.id.chihuahua_value);
        mTvChihuahuaAvailable    = itemView.findViewById(R.id.chihuahua_available);
        mTvChihuahuaDelegated    = itemView.findViewById(R.id.chihuahua_delegate);
        mTvChihuahuaUnBonding    = itemView.findViewById(R.id.chihuahua_unbonding);
        mTvChihuahuaRewards      = itemView.findViewById(R.id.chihuahua_reward);

        mChihuahuaVestingLayer   = itemView.findViewById(R.id.chihuahua_vesting_layer);
        mTvChihuahuaVesting      = itemView.findViewById(R.id.chihuahua_vesting);

        mBtnStake          = itemView.findViewById(R.id.btn_chihuahua_reward);
        mBtnVote           = itemView.findViewById(R.id.btn_chihuahua_vote);
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

        mTvChihuahuaTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvChihuahuaAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvChihuahuaVesting.setText(WDp.getDpAmount2(mainActivity, vestingAmount, 6, 6));
        mTvChihuahuaDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvChihuahuaUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvChihuahuaRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvChihuahuaValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));

        if (!vestingAmount.equals(BigDecimal.ZERO)) { mChihuahuaVestingLayer.setVisibility(View.VISIBLE);
        } else { mChihuahuaVestingLayer.setVisibility(View.GONE); }

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
