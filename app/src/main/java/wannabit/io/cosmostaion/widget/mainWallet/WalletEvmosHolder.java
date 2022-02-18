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

public class WalletEvmosHolder extends BaseHolder {

    public TextView         mTvEvmosTotal, mTvEvmosValue, mTvEvmosAvailable, mTvEvmosDelegated, mTvEvmosUnBonding, mTvEvmosRewards;
    public RelativeLayout   mEvmosVestingLayer;
    public TextView         mTvEvmosVesting;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletEvmosHolder(@NonNull View itemView) {
        super(itemView);
        mTvEvmosTotal        = itemView.findViewById(R.id.evmos_amount);
        mTvEvmosValue        = itemView.findViewById(R.id.evmos_value);
        mTvEvmosAvailable    = itemView.findViewById(R.id.evmos_available);
        mTvEvmosDelegated    = itemView.findViewById(R.id.evmos_delegate);
        mTvEvmosUnBonding    = itemView.findViewById(R.id.evmos_unbonding);
        mTvEvmosRewards      = itemView.findViewById(R.id.evmos_reward);

        mEvmosVestingLayer   = itemView.findViewById(R.id.evmos_vesting_layer);
        mTvEvmosVesting      = itemView.findViewById(R.id.evmos_vesting);

        mBtnStake            = itemView.findViewById(R.id.btn_evmos_reward);
        mBtnVote             = itemView.findViewById(R.id.btn_evmos_vote);
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

        mTvEvmosTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 18, 6));
        mTvEvmosAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 18, 6));
        mTvEvmosVesting.setText(WDp.getDpAmount2(mainActivity, vestingAmount, 18, 6));
        mTvEvmosDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 18, 6));
        mTvEvmosUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 18, 6));
        mTvEvmosRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 18, 6));
        mTvEvmosValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 18));

        if (!vestingAmount.equals(BigDecimal.ZERO)) { mEvmosVestingLayer.setVisibility(View.VISIBLE);
        } else { mEvmosVestingLayer.setVisibility(View.GONE); }

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
