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
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletMediHolder extends BaseHolder {
    private TextView mTvMediTotal, mTvMediValue, mTvMediAvailable, mTvMediDelegated, mTvMediUnBonding, mTvMediRewards;
    private RelativeLayout mBtnMediStake, mBtnMediVote;

    public WalletMediHolder(@NonNull View itemView) {
        super(itemView);
        mTvMediTotal      = itemView.findViewById(R.id.medi_amount);
        mTvMediValue      = itemView.findViewById(R.id.medi_value);
        mTvMediAvailable  = itemView.findViewById(R.id.medi_available);
        mTvMediDelegated  = itemView.findViewById(R.id.medi_delegate);
        mTvMediUnBonding  = itemView.findViewById(R.id.medi_unbonding);
        mTvMediRewards    = itemView.findViewById(R.id.medi_reward);
        mBtnMediStake     = itemView.findViewById(R.id.btn_medi_reward);
        mBtnMediVote      = itemView.findViewById(R.id.btn_medi_vote);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final String denom = WDp.mainDenom(mainActivity.mBaseChain);
        final BigDecimal availableAmount = baseData.availableAmount(denom);
        final BigDecimal delegateAmount = baseData.delegatedSumAmount();
        final BigDecimal unbondingAmount = baseData.unbondingSumAmount();
        final BigDecimal rewardAmount = baseData.rewardAmount(denom);
        final BigDecimal totalAmount = baseData.getAllMainAssetOld(denom);

        mTvMediTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvMediAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvMediDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvMediUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvMediRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvMediValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));
        mainActivity.getBaseDao().onUpdateLastTotalAccount(mainActivity.mAccount, totalAmount.toPlainString());

        mBtnMediStake.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity.mBaseChain.equals(BaseChain.MEDI_MAIN)) {
                    Toast.makeText(mainActivity, "다음 버전에 업데이트 될 예정입니다.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Intent validators = new Intent(mainActivity, ValidatorListActivity.class);
                    mainActivity.startActivity(validators);
                }
            }
        });
        mBtnMediVote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mainActivity.mBaseChain.equals(BaseChain.MEDI_MAIN)) {
                    Toast.makeText(mainActivity, "다음 버전에 업데이트 될 예정입니다.", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    Intent proposals = new Intent(mainActivity, VoteListActivity.class);
                    mainActivity.startActivity(proposals);
                }
            }
        });
    }
}