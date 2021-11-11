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

public class WalletComdexHolder extends BaseHolder {

    public TextView         mTvComdexTotal, mTvComdexValue, mTvComdexAvailable, mTvComdexDelegated, mTvComdexUnBonding, mTvComdexRewards;
    public RelativeLayout   mComdexVestingLayer;
    public TextView         mTvComdexVesting;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletComdexHolder(@NonNull View itemView) {
        super(itemView);
        mTvComdexTotal        = itemView.findViewById(R.id.comdex_amount);
        mTvComdexValue        = itemView.findViewById(R.id.comdex_value);
        mTvComdexAvailable    = itemView.findViewById(R.id.comdex_available);
        mTvComdexDelegated    = itemView.findViewById(R.id.comdex_delegate);
        mTvComdexUnBonding    = itemView.findViewById(R.id.comdex_unbonding);
        mTvComdexRewards      = itemView.findViewById(R.id.comdex_reward);

        mComdexVestingLayer   = itemView.findViewById(R.id.comdex_vesting_layer);
        mTvComdexVesting      = itemView.findViewById(R.id.comdex_vesting);

        mBtnStake           = itemView.findViewById(R.id.btn_comdex_reward);
        mBtnVote            = itemView.findViewById(R.id.btn_comdex_vote);
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

        mTvComdexTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvComdexAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvComdexVesting.setText(WDp.getDpAmount2(mainActivity, vestingAmount, 6, 6));
        mTvComdexDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvComdexUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvComdexRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvComdexValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));

        if (!vestingAmount.equals(BigDecimal.ZERO)) { mComdexVestingLayer.setVisibility(View.VISIBLE);
        } else { mComdexVestingLayer.setVisibility(View.GONE); }

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
