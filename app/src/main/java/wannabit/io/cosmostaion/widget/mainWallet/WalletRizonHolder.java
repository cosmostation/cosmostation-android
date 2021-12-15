package wannabit.io.cosmostaion.widget.mainWallet;

import android.content.Intent;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

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

public class WalletRizonHolder extends BaseHolder {
    public TextView         mTvRizonTotal, mTvRizonValue, mTvRizonAvailable, mTvRizonDelegated, mTvRizonUnBonding, mTvRizonRewards;
    public RelativeLayout   mBtnStake, mBtnVote, mBtnSwap;

    public WalletRizonHolder(@NonNull View itemView) {
        super(itemView);
        mTvRizonTotal       = itemView.findViewById(R.id.rizon_amount);
        mTvRizonValue       = itemView.findViewById(R.id.rizon_value);
        mTvRizonAvailable   = itemView.findViewById(R.id.rizon_available);
        mTvRizonDelegated   = itemView.findViewById(R.id.rizon_delegate);
        mTvRizonUnBonding   = itemView.findViewById(R.id.rizon_unbonding);
        mTvRizonRewards     = itemView.findViewById(R.id.rizon_reward);
        mBtnStake           = itemView.findViewById(R.id.btn_rizon_reward);
        mBtnVote            = itemView.findViewById(R.id.btn_rizon_vote);
        mBtnSwap            = itemView.findViewById(R.id.btn_rizon_swap);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final String denom = WDp.mainDenom(mainActivity.mBaseChain);
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvRizonTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvRizonAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvRizonDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvRizonUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvRizonRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvRizonValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));
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
        mBtnSwap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!baseData.mChainParam.swap_enabled) {
                    Toast.makeText(mainActivity, mainActivity.getString(R.string.error_rizon_swap_finished), Toast.LENGTH_SHORT).show();
                    return;
                }
                mainActivity.onClickEventHorizon();
            }
        });
    }
}
