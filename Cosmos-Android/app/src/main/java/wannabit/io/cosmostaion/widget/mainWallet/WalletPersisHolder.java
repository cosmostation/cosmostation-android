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

public class WalletPersisHolder extends BaseHolder {

    public TextView         mTvXprtTotal, mTvXprtValue, mTvXprtAvailable, mTvXprtDelegated, mTvXprtUnBonding, mTvXprtRewards;
    public RelativeLayout   mXprtVestingLayer;
    public TextView         mTvXprtVesting;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletPersisHolder(@NonNull View itemView) {
        super(itemView);
        mTvXprtTotal        = itemView.findViewById(R.id.xprt_amount);
        mTvXprtValue        = itemView.findViewById(R.id.xprt_value);
        mTvXprtAvailable    = itemView.findViewById(R.id.xprt_available);
        mTvXprtDelegated    = itemView.findViewById(R.id.xprt_delegate);
        mTvXprtUnBonding    = itemView.findViewById(R.id.xprt_unbonding);
        mTvXprtRewards      = itemView.findViewById(R.id.xprt_reward);

        mXprtVestingLayer   = itemView.findViewById(R.id.xprt_vesting_layer);
        mTvXprtVesting      = itemView.findViewById(R.id.xprt_vesting);

        mBtnStake           = itemView.findViewById(R.id.btn_xprt_reward);
        mBtnVote            = itemView.findViewById(R.id.btn_xprt_vote);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final BigDecimal availableAmount = baseData.getAvailable(WDp.mainDenom(mainActivity.mBaseChain));
        final BigDecimal vestingAmount = baseData.getVesting(WDp.mainDenom(mainActivity.mBaseChain));
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(WDp.mainDenom(mainActivity.mBaseChain));
        final BigDecimal totalAmount = baseData.getAllMainAsset(WDp.mainDenom(mainActivity.mBaseChain));

        mTvXprtTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvXprtAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvXprtVesting.setText(WDp.getDpAmount2(mainActivity, vestingAmount, 6, 6));
        mTvXprtDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvXprtUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvXprtRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvXprtValue.setText(WDp.getDpMainAssetValue(mainActivity, baseData, totalAmount, mainActivity.mBaseChain));

        if (!vestingAmount.equals(BigDecimal.ZERO)) { mXprtVestingLayer.setVisibility(View.VISIBLE);
        } else { mXprtVestingLayer.setVisibility(View.GONE); }

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
