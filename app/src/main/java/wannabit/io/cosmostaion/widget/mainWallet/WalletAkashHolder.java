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

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_AKASH;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;

public class WalletAkashHolder extends BaseHolder {
    private TextView        mTvAkashTotal, mTvAkashValue, mTvAkashAvailable, mTvAkashDelegated, mTvAkashUnBonding, mTvAkashRewards;
    public RelativeLayout   mAkashVestingLayer;
    public TextView         mTvAkashVesting;
    private RelativeLayout  mBtnAkashStake, mBtnAkashVote;

    public WalletAkashHolder(@NonNull View itemView) {
        super(itemView);
        mTvAkashTotal       = itemView.findViewById(R.id.akash_amount);
        mTvAkashValue       = itemView.findViewById(R.id.akash_value);
        mTvAkashAvailable   = itemView.findViewById(R.id.akash_available);
        mTvAkashDelegated   = itemView.findViewById(R.id.akash_delegate);
        mTvAkashUnBonding   = itemView.findViewById(R.id.akash_unbonding);
        mTvAkashRewards     = itemView.findViewById(R.id.akash_reward);

        mAkashVestingLayer  = itemView.findViewById(R.id.akash_vesting_layer);
        mTvAkashVesting     = itemView.findViewById(R.id.akash_vesting);

        mBtnAkashStake      = itemView.findViewById(R.id.btn_akash_reward);
        mBtnAkashVote       = itemView.findViewById(R.id.btn_akash_vote);
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

        mTvAkashTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvAkashAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvAkashVesting.setText(WDp.getDpAmount2(mainActivity, vestingAmount, 6, 6));
        mTvAkashDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvAkashUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvAkashRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvAkashValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));
        if (!vestingAmount.equals(BigDecimal.ZERO)) { mAkashVestingLayer.setVisibility(View.VISIBLE);
        } else { mAkashVestingLayer.setVisibility(View.GONE); }

        mainActivity.getBaseDao().onUpdateLastTotalAccount(mainActivity.mAccount, totalAmount.toPlainString());

        mBtnAkashStake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent validators = new Intent(mainActivity, ValidatorListActivity.class);
                mainActivity.startActivity(validators);
            }
        });
        mBtnAkashVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent proposals = new Intent(mainActivity, VoteListActivity.class);
                mainActivity.startActivity(proposals);
            }
        });

    }
}
