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

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_DVPN;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SECRET;

public class WalletSentinelHolder extends BaseHolder {

    public TextView         mTvDvpnTotal, mTvDvpnValue, mTvDvpnAvailable, mTvDvpnDelegated, mTvDvpnUnBonding, mTvDvpnRewards;
    public RelativeLayout   mDvpnVestingLayer;
    public TextView         mTvDvpnVesting;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletSentinelHolder(@NonNull View itemView) {
        super(itemView);
        mTvDvpnTotal        = itemView.findViewById(R.id.dvpn_amount);
        mTvDvpnValue        = itemView.findViewById(R.id.dvpn_value);
        mTvDvpnAvailable    = itemView.findViewById(R.id.dvpn_available);
        mTvDvpnDelegated    = itemView.findViewById(R.id.dvpn_delegate);
        mTvDvpnUnBonding    = itemView.findViewById(R.id.dvpn_unbonding);
        mTvDvpnRewards      = itemView.findViewById(R.id.dvpn_reward);

        mDvpnVestingLayer   = itemView.findViewById(R.id.dvpn_vesting_layer);
        mTvDvpnVesting      = itemView.findViewById(R.id.dvpn_vesting);

        mBtnStake           = itemView.findViewById(R.id.btn_dvpn_reward);
        mBtnVote            = itemView.findViewById(R.id.btn_dvpn_vote);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final BigDecimal availableAmount = WDp.getAvailableCoin(baseData.mBalances, TOKEN_DVPN);;
        final BigDecimal delegateAmount = WDp.getAllDelegatedAmount(baseData.mBondings, baseData.mAllValidators, mainActivity.mBaseChain);
        final BigDecimal unbondingAmount = WDp.getAllUnbondingAmount(baseData.mUnbondings);
        final BigDecimal rewardAmount = WDp.getAllRewardAmount(baseData.mRewards, TOKEN_DVPN);
        final BigDecimal totalAmount = availableAmount.add(delegateAmount).add(unbondingAmount).add(rewardAmount);

        mTvDvpnTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvDvpnAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvDvpnDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvDvpnUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvDvpnRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvDvpnValue.setText(WDp.getDpMainAssetValue(mainActivity, baseData, totalAmount, mainActivity.mBaseChain));

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
