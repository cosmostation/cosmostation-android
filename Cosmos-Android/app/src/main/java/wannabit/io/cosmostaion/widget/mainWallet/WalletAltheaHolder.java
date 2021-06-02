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
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletAltheaHolder extends BaseHolder {
    public TextView         mTvAltheaTotal, mTvAltheaValue, mTvAltheaAvailable, mTvAltheaDelegated, mTvAltheaUnBonding, mTvAltheaRewards;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletAltheaHolder(@NonNull View itemView) {
        super(itemView);
        mTvAltheaTotal       = itemView.findViewById(R.id.althea_amount);
        mTvAltheaValue       = itemView.findViewById(R.id.althea_value);
        mTvAltheaAvailable   = itemView.findViewById(R.id.althea_available);
        mTvAltheaDelegated   = itemView.findViewById(R.id.althea_delegate);
        mTvAltheaUnBonding   = itemView.findViewById(R.id.althea_unbonding);
        mTvAltheaRewards     = itemView.findViewById(R.id.althea_reward);
        mBtnStake           = itemView.findViewById(R.id.btn_althea_reward);
        mBtnVote            = itemView.findViewById(R.id.btn_althea_vote);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final String denom = WDp.mainDenom(mainActivity.mBaseChain);
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvAltheaTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvAltheaAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvAltheaDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvAltheaUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvAltheaRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvAltheaValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));
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
