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

public class WalletEmoneyHolder extends BaseHolder {
    public TextView         mTvEmoneyTotal, mTvEmoneyValue;
    public TextView         mTvEmoneyAvailable, mTvEmoneyDelegated, mTvEmoneyUnBonding, mTvEmoneyRewards;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletEmoneyHolder(@NonNull View itemView) {
        super(itemView);
        mTvEmoneyTotal            = itemView.findViewById(R.id.emoney_amount);
        mTvEmoneyValue            = itemView.findViewById(R.id.emoney_value);
        mTvEmoneyAvailable        = itemView.findViewById(R.id.emoney_available);
        mTvEmoneyDelegated        = itemView.findViewById(R.id.emoney_delegate);
        mTvEmoneyUnBonding        = itemView.findViewById(R.id.emoney_unbonding);
        mTvEmoneyRewards          = itemView.findViewById(R.id.emoney_reward);
        mBtnStake                 = itemView.findViewById(R.id.btn_emoney_reward);
        mBtnVote                  = itemView.findViewById(R.id.btn_emoney_vote);
    }
    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final String denom = WDp.mainDenom(mainActivity.mBaseChain);
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);
        mTvEmoneyTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvEmoneyAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvEmoneyDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvEmoneyUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvEmoneyRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvEmoneyValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));
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
