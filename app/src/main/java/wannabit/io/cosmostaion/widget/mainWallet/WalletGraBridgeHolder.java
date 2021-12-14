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

public class WalletGraBridgeHolder extends BaseHolder {
    public TextView         mTvGraBridgeTotal, mTvGraBridgeValue;
    public TextView         mTvGraBridgeAvailable, mTvGraBridgeDelegated, mTvGraBridgeUnBonding, mTvGraBridgeRewards;
    public RelativeLayout   mGraBridgeVestingLayer;
    public TextView         mTvGraBridgeVesting;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletGraBridgeHolder(@NonNull View itemView) {
        super(itemView);
        mTvGraBridgeTotal            = itemView.findViewById(R.id.grabridge_amount);
        mTvGraBridgeValue            = itemView.findViewById(R.id.grabridge_value);
        mTvGraBridgeAvailable        = itemView.findViewById(R.id.grabridge_available);
        mTvGraBridgeDelegated        = itemView.findViewById(R.id.grabridge_delegate);
        mTvGraBridgeUnBonding        = itemView.findViewById(R.id.grabridge_unbonding);
        mTvGraBridgeRewards          = itemView.findViewById(R.id.grabridge_reward);

        mGraBridgeVestingLayer       = itemView.findViewById(R.id.grabridge_vesting_layer);
        mTvGraBridgeVesting          = itemView.findViewById(R.id.grabridge_vesting);

        mBtnStake                    = itemView.findViewById(R.id.btn_grabridge_reward);
        mBtnVote                     = itemView.findViewById(R.id.btn_grabridge_vote);
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
        mTvGraBridgeTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvGraBridgeAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvGraBridgeVesting.setText(WDp.getDpAmount2(mainActivity, vestingAmount, 6, 6));
        mTvGraBridgeDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvGraBridgeUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvGraBridgeRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvGraBridgeValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));

        if (!vestingAmount.equals(BigDecimal.ZERO)) { mGraBridgeVestingLayer.setVisibility(View.VISIBLE);
        } else { mGraBridgeVestingLayer.setVisibility(View.GONE); }

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


