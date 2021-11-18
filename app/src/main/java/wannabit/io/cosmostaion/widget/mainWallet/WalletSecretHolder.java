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

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CERTIK;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SECRET;

public class WalletSecretHolder extends BaseHolder {
    public TextView mTvSecretTotal, mTvSecretValue, mTvSecretAvailable, mTvSecretDelegated, mTvSecretUnBonding, mTvSecretRewards;
    public RelativeLayout mBtnStake, mBtnVote;

    public WalletSecretHolder(@NonNull View itemView) {
        super(itemView);
        mTvSecretTotal          = itemView.findViewById(R.id.secret_amount);
        mTvSecretValue          = itemView.findViewById(R.id.secret_value);
        mTvSecretAvailable      = itemView.findViewById(R.id.secret_available);
        mTvSecretDelegated      = itemView.findViewById(R.id.secret_delegate);
        mTvSecretUnBonding      = itemView.findViewById(R.id.secret_unbonding);
        mTvSecretRewards        = itemView.findViewById(R.id.secret_reward);
        mBtnStake               = itemView.findViewById(R.id.btn_secret_reward);
        mBtnVote                = itemView.findViewById(R.id.btn_secret_vote);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final String denom = WDp.mainDenom(mainActivity.mBaseChain);
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvSecretTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvSecretAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvSecretDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvSecretUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvSecretRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvSecretValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));
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
