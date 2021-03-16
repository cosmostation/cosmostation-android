package wannabit.io.cosmostaion.widget;

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

import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS_TEST;

public class WalletIrisHolder extends WalletHolder {
    public TextView         mTvDenomTitle;
    public TextView mTvIrisTotal, mTvIrisValue, mTvIrisAvailable, mTvIrisDelegated, mTvIrisUnBonding, mTvIrisRewards;
    public RelativeLayout mBtnStake, mBtnVote;

    public WalletIrisHolder(@NonNull View itemView) {
        super(itemView);
        mTvDenomTitle       = itemView.findViewById(R.id.iris_title);
        mTvIrisTotal        = itemView.findViewById(R.id.iris_amount);
        mTvIrisValue        = itemView.findViewById(R.id.iris_value);
        mTvIrisAvailable    = itemView.findViewById(R.id.iris_available);
        mTvIrisDelegated    = itemView.findViewById(R.id.iris_delegate);
        mTvIrisUnBonding    = itemView.findViewById(R.id.iris_unbonding);
        mTvIrisRewards      = itemView.findViewById(R.id.iris_reward);
        mBtnStake           = itemView.findViewById(R.id.btn_iris_reward);
        mBtnVote            = itemView.findViewById(R.id.btn_iris_vote);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        if (mainActivity.mBaseChain.equals(IRIS_MAIN)) {
            mTvDenomTitle.setText(mainActivity.getString(R.string.s_iris));
        } else if (mainActivity.mBaseChain.equals(IRIS_TEST)) {
            mTvDenomTitle.setText(mainActivity.getString(R.string.s_bif));
        }

        final BigDecimal availableAmount = baseData.getAvailable(WDp.mainDenom(mainActivity.mBaseChain));
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(WDp.mainDenom(mainActivity.mBaseChain));
        final BigDecimal totalAmount = baseData.getAllMainAsset(WDp.mainDenom(mainActivity.mBaseChain));

        mTvIrisTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvIrisAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvIrisDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvIrisUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvIrisRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvIrisValue.setText(WDp.getDpMainAssetValue(mainActivity, baseData, totalAmount, mainActivity.mBaseChain));
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
