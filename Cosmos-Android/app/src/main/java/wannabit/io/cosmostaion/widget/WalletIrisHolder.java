package wannabit.io.cosmostaion.widget;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.CardView;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.ValidatorListActivity;
import wannabit.io.cosmostaion.activities.VoteListActivity;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS_ATTO;

public class WalletIrisHolder extends WalletHolder {
    public TextView mTvIrisTotal, mTvIrisValue, mTvIrisAvailable, mTvIrisDelegated, mTvIrisUnBonding, mTvIrisRewards;
    public RelativeLayout mBtnStake, mBtnVote;

    public WalletIrisHolder(@NonNull View itemView) {
        super(itemView);
        mTvIrisTotal        = itemView.findViewById(R.id.iris_amount);
        mTvIrisValue        = itemView.findViewById(R.id.iris_value);
        mTvIrisAvailable    = itemView.findViewById(R.id.iris_available);
        mTvIrisDelegated    = itemView.findViewById(R.id.iris_delegate);
        mTvIrisUnBonding    = itemView.findViewById(R.id.iris_unbonding);
        mTvIrisRewards      = itemView.findViewById(R.id.iris_reward);
        mBtnStake           = itemView.findViewById(R.id.btn_iris_reward);
        mBtnVote            = itemView.findViewById(R.id.btn_iris_vote);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BigDecimal availableAmount = WDp.getAvailableCoin(mainActivity.mBalances, TOKEN_IRIS_ATTO);
        final BigDecimal delegateAmount = WDp.getAllDelegatedAmount(mainActivity.mBondings, mainActivity.mAllValidators, mainActivity.mBaseChain);
        final BigDecimal unbondingAmount = WDp.getUnbondingAmount(mainActivity.mUnbondings);
        final BigDecimal rewardAmount = mainActivity.mIrisReward == null ? BigDecimal.ZERO : mainActivity.mIrisReward.getSimpleIrisReward();
        final BigDecimal totalAmount = availableAmount.add(delegateAmount).add(unbondingAmount).add(rewardAmount);

        mTvIrisTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 18, 6));
        mTvIrisAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 18, 6));
        mTvIrisDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 18, 6));
        mTvIrisUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 18, 6));
        mTvIrisRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 18, 6));
        mTvIrisValue.setText(WDp.getValueOfIris(mainActivity, mainActivity.getBaseDao(), totalAmount));

        mainActivity.getBaseDao().onUpdateLastTotalAccount(mainActivity.mAccount, totalAmount.toPlainString());

        mBtnStake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent validators = new Intent(mainActivity, ValidatorListActivity.class);
                validators.putExtra("irisreward", mainActivity.mIrisReward);
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
