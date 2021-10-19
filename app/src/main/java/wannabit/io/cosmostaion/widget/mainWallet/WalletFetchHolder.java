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

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_FET;

public class WalletFetchHolder extends BaseHolder {

    public TextView         mTvFetTotal, mTvFetValue, mTvFetAvailable, mTvFetDelegated, mTvFetUnBonding, mTvFetRewards;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletFetchHolder(@NonNull View itemView) {
        super(itemView);
        mTvFetTotal         = itemView.findViewById(R.id.fet_amount);
        mTvFetValue         = itemView.findViewById(R.id.fet_value);
        mTvFetAvailable     = itemView.findViewById(R.id.fet_available);
        mTvFetDelegated     = itemView.findViewById(R.id.fet_delegate);
        mTvFetUnBonding     = itemView.findViewById(R.id.fet_unbonding);
        mTvFetRewards       = itemView.findViewById(R.id.fet_reward);
        mBtnStake           = itemView.findViewById(R.id.btn_fet_reward);
        mBtnVote            = itemView.findViewById(R.id.btn_fet_vote);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final String denom = WDp.mainDenom(mainActivity.mBaseChain);
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvFetTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 18, 6));
        mTvFetAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 18, 6));
        mTvFetDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 18, 6));
        mTvFetUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 18, 6));
        mTvFetRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 18, 6));
        mTvFetValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 18));
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
