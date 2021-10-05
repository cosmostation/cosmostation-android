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

public class WalletJunoHolder extends BaseHolder {
    public TextView         mTvJunoTotal, mTvJunoValue;
    public TextView         mTvJunoAvailable, mTvJunoDelegated, mTvJunoUnBonding, mTvJunoRewards;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletJunoHolder(@NonNull View itemView) {
        super(itemView);
        mTvJunoTotal            = itemView.findViewById(R.id.juno_amount);
        mTvJunoValue            = itemView.findViewById(R.id.juno_value);
        mTvJunoAvailable        = itemView.findViewById(R.id.juno_available);
        mTvJunoDelegated        = itemView.findViewById(R.id.juno_delegate);
        mTvJunoUnBonding        = itemView.findViewById(R.id.juno_unbonding);
        mTvJunoRewards          = itemView.findViewById(R.id.juno_reward);
        mBtnStake               = itemView.findViewById(R.id.btn_juno_reward);
        mBtnVote                = itemView.findViewById(R.id.btn_juno_vote);
    }
    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final String denom = WDp.mainDenom(mainActivity.mBaseChain);
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);
        mTvJunoTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvJunoAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvJunoDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvJunoUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvJunoRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvJunoValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));
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
