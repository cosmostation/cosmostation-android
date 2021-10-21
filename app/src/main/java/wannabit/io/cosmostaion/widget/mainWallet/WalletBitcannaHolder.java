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

public class WalletBitcannaHolder extends BaseHolder {
    public TextView         mTvBitCannaTotal, mTvBitCannaValue;
    public TextView         mTvBitCannaAvailable, mTvBitCannaDelegated, mTvBitCannaUnBonding, mTvBitCannaRewards;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletBitcannaHolder(@NonNull View itemView) {
        super(itemView);
        mTvBitCannaTotal            = itemView.findViewById(R.id.bitcanna_amount);
        mTvBitCannaValue            = itemView.findViewById(R.id.bitcanna_value);
        mTvBitCannaAvailable        = itemView.findViewById(R.id.bitcanna_available);
        mTvBitCannaDelegated        = itemView.findViewById(R.id.bitcanna_delegate);
        mTvBitCannaUnBonding        = itemView.findViewById(R.id.bitcanna_unbonding);
        mTvBitCannaRewards          = itemView.findViewById(R.id.bitcanna_reward);
        mBtnStake               = itemView.findViewById(R.id.btn_bitcanna_reward);
        mBtnVote                = itemView.findViewById(R.id.btn_bitcanna_vote);
    }
    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final String denom = WDp.mainDenom(mainActivity.mBaseChain);
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);
        mTvBitCannaTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvBitCannaAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvBitCannaDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvBitCannaUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvBitCannaRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvBitCannaValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));
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
