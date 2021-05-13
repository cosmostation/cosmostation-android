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
import wannabit.io.cosmostaion.activities.chains.kava.DAppsList5Activity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class WalletKavaHolder extends BaseHolder {
    public TextView         mTvKavaTotal, mTvKavaValue, mTvKavaAvailable, mTvKavaDelegated, mTvKavaUnBonding, mTvKavaRewards;
    public RelativeLayout   mKavaVestingLayer, mKavaDepositLayer, mKavaIncentiveLayer;
    public TextView         mTvKavaVesting, mTvKavaDeposit, mTvKavaIncentive;
    public RelativeLayout   mBtnStake, mBtnVote, mBtnKavaDapp;

    public WalletKavaHolder(@NonNull View itemView) {
        super(itemView);
        mTvKavaTotal        = itemView.findViewById(R.id.kava_amount);
        mTvKavaValue        = itemView.findViewById(R.id.kava_value);
        mTvKavaAvailable    = itemView.findViewById(R.id.kava_available);
        mTvKavaDelegated    = itemView.findViewById(R.id.kava_delegate);
        mTvKavaUnBonding    = itemView.findViewById(R.id.kava_unbonding);
        mTvKavaRewards      = itemView.findViewById(R.id.kava_reward);

        mKavaVestingLayer   = itemView.findViewById(R.id.kava_vesting_layer);
        mKavaDepositLayer   = itemView.findViewById(R.id.kava_deposit_layer);
        mKavaIncentiveLayer = itemView.findViewById(R.id.kava_incentive_layer);
        mTvKavaVesting      = itemView.findViewById(R.id.kava_vesting);
        mTvKavaDeposit      = itemView.findViewById(R.id.kava_harvest_deposited);
        mTvKavaIncentive    = itemView.findViewById(R.id.kava_unclaimed_incentive);

        mBtnStake           = itemView.findViewById(R.id.btn_kava_reward);
        mBtnVote            = itemView.findViewById(R.id.btn_kava_vote);
        mBtnKavaDapp        = itemView.findViewById(R.id.btn_kava_dapp);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final BigDecimal availableAmount = baseData.availableAmount(TOKEN_KAVA);
        final BigDecimal vestingAmount = baseData.lockedAmount(TOKEN_KAVA);
        final BigDecimal delegateAmount = baseData.delegatedSumAmount();
        final BigDecimal unbondingAmount = baseData.unbondingSumAmount();
        final BigDecimal rewardAmount = baseData.rewardAmount(TOKEN_KAVA);
        final BigDecimal totalAmount = baseData.getAllMainAssetOld(TOKEN_KAVA);

        mTvKavaTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvKavaAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvKavaDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvKavaUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvKavaRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvKavaVesting.setText(WDp.getDpAmount2(mainActivity, vestingAmount, 6, 6));
        mTvKavaValue.setText(WDp.getValueOfKava(mainActivity, mainActivity.getBaseDao(), totalAmount));

        if (!vestingAmount.equals(BigDecimal.ZERO)) { mKavaVestingLayer.setVisibility(View.VISIBLE);
        } else { mKavaVestingLayer.setVisibility(View.GONE); }

        mainActivity.getBaseDao().onUpdateLastTotalAccount(mainActivity.mAccount, totalAmount.toPlainString());

        mBtnStake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.startActivity(new Intent(mainActivity, ValidatorListActivity.class));
            }
        });
        mBtnVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.startActivity(new Intent(mainActivity, VoteListActivity.class));
            }
        });
        mBtnKavaDapp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mainActivity.startActivity(new Intent(mainActivity, DAppsList5Activity.class));
            }
        });
    }
}
