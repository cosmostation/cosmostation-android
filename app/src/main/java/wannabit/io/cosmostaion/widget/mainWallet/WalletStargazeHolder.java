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

public class WalletStargazeHolder extends BaseHolder {
    public TextView         mTvStargazeTotal, mTvStargazeValue;
    public TextView         mTvStargazeAvailable, mTvStargazeDelegated, mTvStargazeUnBonding, mTvStargazeRewards;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletStargazeHolder(@NonNull View itemView) {
        super(itemView);
        mTvStargazeTotal            = itemView.findViewById(R.id.stargaze_amount);
        mTvStargazeValue            = itemView.findViewById(R.id.stargaze_value);
        mTvStargazeAvailable        = itemView.findViewById(R.id.stargaze_available);
        mTvStargazeDelegated        = itemView.findViewById(R.id.stargaze_delegate);
        mTvStargazeUnBonding        = itemView.findViewById(R.id.stargaze_unbonding);
        mTvStargazeRewards          = itemView.findViewById(R.id.stargaze_reward);
        mBtnStake                   = itemView.findViewById(R.id.btn_stargaze_reward);
        mBtnVote                    = itemView.findViewById(R.id.btn_stargaze_vote);
    }
    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final String denom = WDp.mainDenom(mainActivity.mBaseChain);
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);
        mTvStargazeTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvStargazeAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvStargazeDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvStargazeUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvStargazeRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvStargazeValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));
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

