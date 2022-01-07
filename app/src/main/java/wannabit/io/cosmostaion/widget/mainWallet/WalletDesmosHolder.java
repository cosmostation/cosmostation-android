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
import wannabit.io.cosmostaion.activities.chains.desmos.ProfileDetailActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletDesmosHolder extends BaseHolder {

    public TextView         mTvDesmosTotal, mTvDesmosValue, mTvDesmosAvailable, mTvDesmosDelegated, mTvDesmosUnBonding, mTvDesmosRewards;
    public RelativeLayout   mDesmosVestingLayer;
    public TextView         mTvDesmosVesting;
    public RelativeLayout   mBtnStake, mBtnVote, mBtnAirdrop;

    public WalletDesmosHolder(@NonNull View itemView) {
        super(itemView);
        mTvDesmosTotal        = itemView.findViewById(R.id.desmos_amount);
        mTvDesmosValue        = itemView.findViewById(R.id.desmos_value);
        mTvDesmosAvailable    = itemView.findViewById(R.id.desmos_available);
        mTvDesmosDelegated    = itemView.findViewById(R.id.desmos_delegate);
        mTvDesmosUnBonding    = itemView.findViewById(R.id.desmos_unbonding);
        mTvDesmosRewards      = itemView.findViewById(R.id.desmos_reward);

        mDesmosVestingLayer   = itemView.findViewById(R.id.desmos_vesting_layer);
        mTvDesmosVesting      = itemView.findViewById(R.id.desmos_vesting);

        mBtnStake             = itemView.findViewById(R.id.btn_desmos_reward);
        mBtnVote              = itemView.findViewById(R.id.btn_desmos_vote);
        mBtnAirdrop           = itemView.findViewById(R.id.btn_claim_airdrop);
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

        mTvDesmosTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvDesmosAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvDesmosVesting.setText(WDp.getDpAmount2(mainActivity, vestingAmount, 6, 6));
        mTvDesmosDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvDesmosUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvDesmosRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvDesmosValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));

        if (!vestingAmount.equals(BigDecimal.ZERO)) { mDesmosVestingLayer.setVisibility(View.VISIBLE);
        } else { mDesmosVestingLayer.setVisibility(View.GONE); }

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
        mBtnAirdrop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.onClickProfile();
            }
        });
    }
}
