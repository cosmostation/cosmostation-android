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
import wannabit.io.cosmostaion.activities.chains.sif.SifDexListActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletSifHolder extends BaseHolder {
    public TextView         mTvSifTotal, mTvSifValue, mTvSifAvailable, mTvSifDelegated, mTvSifUnBonding, mTvSifRewards;
    public RelativeLayout   mSifVestingLayer;
    public TextView         mTvSifVesting;
    public RelativeLayout   mBtnStake, mBtnVote, mBtnDex;

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
        mBtnDex             = itemView.findViewById(R.id.btn_sif_dex);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final String denom = WDp.mainDenom(mainActivity.mBaseChain);
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvSifTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 18, 6));
        mTvSifAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 18, 6));
        mTvSifDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 18, 6));
        mTvSifUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 18, 6));
        mTvSifRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 18, 6));
        mTvSifValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 18));
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
        mBtnDex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent dex = new Intent(mainActivity, SifDexListActivity.class);
                mainActivity.startActivity(dex);
            }
        });

    }
}
