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

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BAND;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_CERTIK;

public class WalletBandHolder extends BaseHolder {
    public TextView mTvBandTotal, mTvBandValue, mTvBandAvailable, mTvBandDelegated, mTvBandUnBonding, mTvBandRewards;
    public RelativeLayout mBtnStake, mBtnVote;

    public WalletBandHolder(@NonNull View itemView) {
        super(itemView);
        mTvBandTotal        = itemView.findViewById(R.id.band_total_amount);
        mTvBandValue        = itemView.findViewById(R.id.band_total_value);
        mTvBandAvailable    = itemView.findViewById(R.id.band_available);
        mTvBandDelegated    = itemView.findViewById(R.id.band_delegate);
        mTvBandUnBonding    = itemView.findViewById(R.id.band_unbonding);
        mTvBandRewards      = itemView.findViewById(R.id.band_reward);
        mBtnStake           = itemView.findViewById(R.id.btn_band_delegate);
        mBtnVote            = itemView.findViewById(R.id.btn_band_vote);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final String denom = WDp.mainDenom(mainActivity.mBaseChain);
        final BigDecimal availableAmount = baseData.availableAmount(denom);
        final BigDecimal delegateAmount = baseData.delegatedSumAmount();
        final BigDecimal unbondingAmount = baseData.unbondingSumAmount();
        final BigDecimal rewardAmount = baseData.rewardAmount(denom);
        final BigDecimal totalAmount = baseData.getAllMainAssetOld(denom);

        mTvBandTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvBandAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvBandDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvBandUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvBandRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvBandValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));
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
