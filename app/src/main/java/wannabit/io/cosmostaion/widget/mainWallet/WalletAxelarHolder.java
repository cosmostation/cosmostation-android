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

public class WalletAxelarHolder extends BaseHolder {
    public TextView         mTvAxelarTotal, mTvAxelarValue;
    public TextView         mTvAxelarAvailable, mTvAxelarDelegated, mTvAxelarUnBonding, mTvAxelarRewards;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletAxelarHolder(@NonNull View itemView) {
        super(itemView);
        mTvAxelarTotal            = itemView.findViewById(R.id.axelar_amount);
        mTvAxelarValue            = itemView.findViewById(R.id.axelar_value);
        mTvAxelarAvailable        = itemView.findViewById(R.id.axelar_available);
        mTvAxelarDelegated        = itemView.findViewById(R.id.axelar_delegate);
        mTvAxelarUnBonding        = itemView.findViewById(R.id.axelar_unbonding);
        mTvAxelarRewards          = itemView.findViewById(R.id.axelar_reward);
        mBtnStake                 = itemView.findViewById(R.id.btn_axelar_reward);
        mBtnVote                  = itemView.findViewById(R.id.btn_axelar_vote);
    }
    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final String denom = WDp.mainDenom(mainActivity.mBaseChain);
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);
        mTvAxelarTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvAxelarAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvAxelarDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvAxelarUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvAxelarRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvAxelarValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));
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
