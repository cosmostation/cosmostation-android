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
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BAND;

public class WalletCosmosHolder extends WalletHolder {
    public TextView         mTvAtomTotal, mTvAtomValue, mTvAtomAvailable, mTvAtomDelegated, mTvAtomUnBonding, mTvAtomRewards;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletCosmosHolder(@NonNull View itemView) {
        super(itemView);
        mTvAtomTotal        = itemView.findViewById(R.id.atom_amount);
        mTvAtomValue        = itemView.findViewById(R.id.atom_value);
        mTvAtomAvailable    = itemView.findViewById(R.id.atom_available);
        mTvAtomDelegated    = itemView.findViewById(R.id.atom_delegate);
        mTvAtomUnBonding    = itemView.findViewById(R.id.atom_unbonding);
        mTvAtomRewards      = itemView.findViewById(R.id.atom_reward);
        mBtnStake           = itemView.findViewById(R.id.btn_cosmos_reward);
        mBtnVote            = itemView.findViewById(R.id.btn_cosmos_vote);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final BigDecimal availableAmount = WDp.getAvailableCoin(mainActivity.mBalances, TOKEN_ATOM);
        final BigDecimal delegateAmount = WDp.getAllDelegatedAmount(mainActivity.mBondings, baseData.mAllValidators, mainActivity.mBaseChain);
        final BigDecimal unbondingAmount = WDp.getUnbondingAmount(mainActivity.mUnbondings);
        final BigDecimal rewardAmount = WDp.getAllRewardAmount(mainActivity.mRewards, TOKEN_ATOM);
        final BigDecimal totalAmount = availableAmount.add(delegateAmount).add(unbondingAmount).add(rewardAmount);

        mTvAtomTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvAtomAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvAtomDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvAtomUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvAtomRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvAtomValue.setText(WDp.getValueOfAtom(mainActivity, mainActivity.getBaseDao(), totalAmount));

        mainActivity.getBaseDao().onUpdateLastTotalAccount(mainActivity.mAccount, totalAmount.toPlainString());

        mBtnStake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent validators = new Intent(mainActivity, ValidatorListActivity.class);
                validators.putExtra("rewards", mainActivity.mRewards);
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
