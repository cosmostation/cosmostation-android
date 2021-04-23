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

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SIF;

public class WalletSifHolder extends BaseHolder {
    public TextView         mTvSifTotal, mTvSifValue, mTvSifAvailable, mTvSifDelegated, mTvSifUnBonding, mTvSifRewards;
    public RelativeLayout   mSifVestingLayer;
    public TextView         mTvSifVesting;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletSifHolder(@NonNull View itemView) {
        super(itemView);
        mTvSifTotal         = itemView.findViewById(R.id.sif_amount);
        mTvSifValue         = itemView.findViewById(R.id.sif_value);
        mTvSifAvailable     = itemView.findViewById(R.id.sif_available);
        mTvSifDelegated     = itemView.findViewById(R.id.sif_delegate);
        mTvSifUnBonding     = itemView.findViewById(R.id.sif_unbonding);
        mTvSifRewards       = itemView.findViewById(R.id.sif_reward);

        mSifVestingLayer    = itemView.findViewById(R.id.sif_vesting_layer);
        mTvSifVesting       = itemView.findViewById(R.id.sif_vesting);

        mBtnStake           = itemView.findViewById(R.id.btn_sif_reward);
        mBtnVote            = itemView.findViewById(R.id.btn_sif_vote);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final BigDecimal availableAmount = baseData.availableAmount(TOKEN_SIF);
        final BigDecimal delegateAmount = baseData.delegatedSumAmount();
        final BigDecimal unbondingAmount = baseData.unbondingSumAmount();
        final BigDecimal rewardAmount = baseData.rewardAmount(TOKEN_SIF);
        final BigDecimal totalAmount = baseData.getAllMainAssetOld(TOKEN_SIF);

        mTvSifTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 18, 6));
        mTvSifAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 18, 6));
        mTvSifDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 18, 6));
        mTvSifUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 18, 6));
        mTvSifRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 18, 6));
        mTvSifValue.setText(WDp.getDpMainAssetValue(mainActivity, baseData, totalAmount, mainActivity.mBaseChain));
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
