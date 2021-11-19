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

public class WalletInjHolder extends BaseHolder {

    public TextView         mTvInjTotal, mTvInjValue, mTvInjAvailable, mTvInjDelegated, mTvInjUnBonding, mTvInjRewards;
    public RelativeLayout   mInjVestingLayer;
    public TextView         mTvInjVesting;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletInjHolder(@NonNull View itemView) {
        super(itemView);
        mTvInjTotal        = itemView.findViewById(R.id.inj_amount);
        mTvInjValue        = itemView.findViewById(R.id.inj_value);
        mTvInjAvailable    = itemView.findViewById(R.id.inj_available);
        mTvInjDelegated    = itemView.findViewById(R.id.inj_delegate);
        mTvInjUnBonding    = itemView.findViewById(R.id.inj_unbonding);
        mTvInjRewards      = itemView.findViewById(R.id.inj_reward);

        mInjVestingLayer   = itemView.findViewById(R.id.inj_vesting_layer);
        mTvInjVesting      = itemView.findViewById(R.id.inj_vesting);

        mBtnStake          = itemView.findViewById(R.id.btn_inj_reward);
        mBtnVote           = itemView.findViewById(R.id.btn_inj_vote);
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

        mTvInjTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 18, 6));
        mTvInjAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 18, 6));
        mTvInjVesting.setText(WDp.getDpAmount2(mainActivity, vestingAmount, 18, 6));
        mTvInjDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 18, 6));
        mTvInjUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 18, 6));
        mTvInjRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 18, 6));
        mTvInjValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 18));

        if (!vestingAmount.equals(BigDecimal.ZERO)) { mInjVestingLayer.setVisibility(View.VISIBLE);
        } else { mInjVestingLayer.setVisibility(View.GONE); }

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
