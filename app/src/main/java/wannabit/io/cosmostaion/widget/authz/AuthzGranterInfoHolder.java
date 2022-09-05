package wannabit.io.cosmostaion.widget.authz;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class AuthzGranterInfoHolder extends RecyclerView.ViewHolder {
    private TextView mGranterAddress;
    private TextView mGranterTotalAmount, mGranterTotalValue;
    private TextView mGranterAvailable;
    private TextView mGranterVesting;
    private TextView mGranterDelegated;
    private TextView mGranterUnBonding;
    private TextView mGranterReward;
    private TextView mGranterCommission;

    private RelativeLayout mGranterVestingLayer;
    private RelativeLayout mGranterCommissionLayer;

    private ImageView mBtnExploer;

    public AuthzGranterInfoHolder(@NonNull View itemView) {
        super(itemView);
        mGranterAddress = itemView.findViewById(R.id.granter_address);
        mGranterTotalAmount = itemView.findViewById(R.id.granter_total_amount);
        mGranterTotalValue = itemView.findViewById(R.id.granter_total_value);
        mGranterAvailable = itemView.findViewById(R.id.granter_available);
        mGranterVesting = itemView.findViewById(R.id.granter_vesting);
        mGranterDelegated = itemView.findViewById(R.id.granter_delegate);
        mGranterUnBonding = itemView.findViewById(R.id.granter_unbonding);
        mGranterReward = itemView.findViewById(R.id.granter_reward);
        mGranterCommission = itemView.findViewById(R.id.granter_commission);

        mGranterVestingLayer = itemView.findViewById(R.id.vesting_layer);
        mGranterCommissionLayer = itemView.findViewById(R.id.commission_layer);

        mBtnExploer = itemView.findViewById(R.id.btn_explorer);
    }

    public void onBindGranterInfo(BaseData baseData, ChainConfig chainConfig, String granter, Coin available, Coin vesting, Coin delegated, Coin unDelegated,
                                  Coin reward, Coin commission) {
        if (chainConfig == null) return;
        final String stakingDenom = chainConfig.mainDenom();
        final int divideDecimal = WDp.getDenomDecimal(baseData, chainConfig, stakingDenom);

        final BigDecimal availableAmount = new BigDecimal(available.amount);
        final BigDecimal vestingAmount = new BigDecimal(vesting.amount);
        final BigDecimal delegateAmount = new BigDecimal(delegated.amount);
        final BigDecimal unbondingAmount = new BigDecimal(unDelegated.amount);
        final BigDecimal rewardAmount = new BigDecimal(reward.amount);
        final BigDecimal commissionAmount = new BigDecimal(commission.amount);
        final BigDecimal totalAmount = availableAmount.add(delegateAmount).add(vestingAmount).add(unbondingAmount).add(rewardAmount).add(commissionAmount);

        mGranterAddress.setText(granter);

        mGranterAvailable.setText(WDp.getDpAmount2(availableAmount, divideDecimal, 6));
        mGranterVesting.setText(WDp.getDpAmount2(vestingAmount, divideDecimal, 6));
        mGranterDelegated.setText(WDp.getDpAmount2(delegateAmount, divideDecimal, 6));
        mGranterUnBonding.setText(WDp.getDpAmount2(unbondingAmount, divideDecimal, 6));
        mGranterReward.setText(WDp.getDpAmount2(rewardAmount, divideDecimal, 6));
        mGranterCommission.setText(WDp.getDpAmount2(commissionAmount, divideDecimal, 6));
        if (BigDecimal.ZERO.compareTo(vestingAmount) < 0) {
            mGranterVestingLayer.setVisibility(View.VISIBLE);
        }
        if (BigDecimal.ZERO.compareTo(commissionAmount) < 0) {
            mGranterCommissionLayer.setVisibility(View.VISIBLE);
        }

        mGranterTotalAmount.setText(WDp.getDpAmount2(totalAmount, divideDecimal, 6));
        mGranterTotalValue.setText(WDp.dpUserCurrencyValue(baseData, stakingDenom, totalAmount, divideDecimal));

        mBtnExploer.setOnClickListener(view -> {
            String url = chainConfig.explorerAccountLink() + granter;
            itemView.getContext().startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse(url)));
        });
    }
}
