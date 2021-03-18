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

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CERTIK;

public class WalletCertikHolder extends BaseHolder {
    private TextView        mTvCertikTotal, mTvCertikValue, mTvCertikAvailable, mTvCertikDelegated, mTvCertikUnBonding, mTvCertikRewards;
    private RelativeLayout  mBtnCertikStake, mBtnCertikVote;

    public WalletCertikHolder(@NonNull View itemView) {
        super(itemView);
        mTvCertikTotal      = itemView.findViewById(R.id.certik_amount);
        mTvCertikValue      = itemView.findViewById(R.id.certik_value);
        mTvCertikAvailable  = itemView.findViewById(R.id.certik_available);
        mTvCertikDelegated  = itemView.findViewById(R.id.certik_delegate);
        mTvCertikUnBonding  = itemView.findViewById(R.id.certik_unbonding);
        mTvCertikRewards    = itemView.findViewById(R.id.certik_reward);
        mBtnCertikStake     = itemView.findViewById(R.id.btn_certik_reward);
        mBtnCertikVote      = itemView.findViewById(R.id.btn_certik_vote);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final BigDecimal availableAmount = WDp.getAvailableCoin(baseData.mBalances, TOKEN_CERTIK);
        final BigDecimal delegateAmount = WDp.getAllDelegatedAmount(baseData.mBondings, baseData.mAllValidators, mainActivity.mBaseChain);
        final BigDecimal unbondingAmount = WDp.getUnbondingAmount(baseData.mUnbondings);
        final BigDecimal rewardAmount = WDp.getAllRewardAmount(baseData.mRewards, TOKEN_CERTIK);
        final BigDecimal totalAmount = availableAmount.add(delegateAmount).add(unbondingAmount).add(rewardAmount);

        mTvCertikTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvCertikAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvCertikDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvCertikUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvCertikRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvCertikValue.setText(WDp.getValueOfCertik(mainActivity, baseData, totalAmount));

        mainActivity.getBaseDao().onUpdateLastTotalAccount(mainActivity.mAccount, totalAmount.toPlainString());

        mBtnCertikStake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent validators = new Intent(mainActivity, ValidatorListActivity.class);
                validators.putExtra("rewards", baseData.mRewards);
                mainActivity.startActivity(validators);
            }
        });
        mBtnCertikVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent proposals = new Intent(mainActivity, VoteListActivity.class);
                mainActivity.startActivity(proposals);
            }
        });

    }
}
