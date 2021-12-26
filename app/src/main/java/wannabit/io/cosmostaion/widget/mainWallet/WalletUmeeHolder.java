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

public class WalletUmeeHolder extends BaseHolder {
    public TextView         mTvUmeeTotal, mTvUmeeValue;
    public TextView         mTvUmeeAvailable, mTvUmeeVesting, mTvUmeeDelegated, mTvUmeeUnBonding, mTvUmeeRewards;
    public RelativeLayout   mTvUmeeVestingLayer;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletUmeeHolder(@NonNull View itemView) {
        super(itemView);
        mTvUmeeTotal            = itemView.findViewById(R.id.umee_amount);
        mTvUmeeValue            = itemView.findViewById(R.id.umee_value);
        mTvUmeeAvailable        = itemView.findViewById(R.id.umee_available);
        mTvUmeeDelegated        = itemView.findViewById(R.id.umee_delegate);
        mTvUmeeUnBonding        = itemView.findViewById(R.id.umee_unbonding);
        mTvUmeeRewards          = itemView.findViewById(R.id.umee_reward);

        mTvUmeeVestingLayer     = itemView.findViewById(R.id.umee_vesting_layer);
        mTvUmeeVesting          = itemView.findViewById(R.id.umee_vesting);

        mBtnStake               = itemView.findViewById(R.id.btn_umee_reward);
        mBtnVote                = itemView.findViewById(R.id.btn_umee_vote);
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

        mTvUmeeTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvUmeeAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvUmeeVesting.setText(WDp.getDpAmount2(mainActivity, vestingAmount, 6, 6));
        mTvUmeeDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvUmeeUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvUmeeRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvUmeeValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));

        if (!vestingAmount.equals(BigDecimal.ZERO)) { mTvUmeeVestingLayer.setVisibility(View.VISIBLE);
        } else { mTvUmeeVestingLayer.setVisibility(View.GONE); }

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
