package wannabit.io.cosmostaion.widget.tokenDetail;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class TokenSentinelHolder extends BaseHolder {
    private TextView mTvdvpnTotal, mTvdvpnValue, mTvdvpnAvailable, mTvdvpnDelegated, mTvdvpnUnBonding, mTvdvpnRewards;
    public RelativeLayout mdvpnVestingLayer;
    public TextView         mTvdvpnVesting;

    public TokenSentinelHolder(@NonNull View itemView) {
        super(itemView);
        mTvdvpnTotal       = itemView.findViewById(R.id.dvpn_amount);
        mTvdvpnValue       = itemView.findViewById(R.id.dvpn_value);
        mTvdvpnAvailable   = itemView.findViewById(R.id.dvpn_available);
        mTvdvpnDelegated   = itemView.findViewById(R.id.dvpn_delegate);
        mTvdvpnUnBonding   = itemView.findViewById(R.id.dvpn_unbonding);
        mTvdvpnRewards     = itemView.findViewById(R.id.dvpn_reward);

        mdvpnVestingLayer  = itemView.findViewById(R.id.dvpn_vesting_layer);
        mTvdvpnVesting     = itemView.findViewById(R.id.dvpn_vesting);

    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal vestingAmount = baseData.getVesting(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvdvpnTotal.setText(WDp.getDpAmount2(c, totalAmount, 6, 6));
        mTvdvpnAvailable.setText(WDp.getDpAmount2(c, availableAmount, 6, 6));
        mTvdvpnVesting.setText(WDp.getDpAmount2(c, vestingAmount, 6, 6));
        mTvdvpnDelegated.setText(WDp.getDpAmount2(c, delegateAmount, 6, 6));
        mTvdvpnUnBonding.setText(WDp.getDpAmount2(c, unbondingAmount, 6, 6));
        mTvdvpnRewards.setText(WDp.getDpAmount2(c, rewardAmount, 6, 6));
        mTvdvpnValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));
        if (!vestingAmount.equals(BigDecimal.ZERO)) { mdvpnVestingLayer.setVisibility(View.VISIBLE);
        } else { mdvpnVestingLayer.setVisibility(View.GONE); }
    }
}

