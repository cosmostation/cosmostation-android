package wannabit.io.cosmostaion.widget.tokenDetail;

import android.content.Context;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SIF;

public class TokenSifHolder extends BaseHolder {


    private LinearLayout mSifTransfer;
    private RelativeLayout mBtnSendSif, mBtnReceiveSif;
    private RelativeLayout  mSifVestingLayer, mSifDepositLayer, mSifIncentiveLayer;
    private TextView mTvSifTotal, mTvSifValue, mTvSifAvailable, mTvSifVesting,
            mTvSifDelegated, mTvSifUnBonding, mTvSifRewards, mTvSifDeposit, mTvSifIncentive;

    public TokenSifHolder(@NonNull View itemView) {
        super(itemView);
        mTvSifTotal            = itemView.findViewById(R.id.dash_sif_amount);
        mTvSifValue            = itemView.findViewById(R.id.dash_sif_value);
        mTvSifAvailable        = itemView.findViewById(R.id.dash_sif_undelegate);
        mTvSifDelegated        = itemView.findViewById(R.id.dash_sif_delegate);
        mTvSifUnBonding        = itemView.findViewById(R.id.dash_sif_unbonding);
        mTvSifRewards          = itemView.findViewById(R.id.dash_sif_reward);
        mTvSifVesting          = itemView.findViewById(R.id.dash_sif_vesting);
        mTvSifDeposit          = itemView.findViewById(R.id.dash_sif_harvest_deposited);
        mTvSifIncentive        = itemView.findViewById(R.id.dash_sif_unclaimed_incentive);

        mSifVestingLayer       = itemView.findViewById(R.id.sif_harvest_vesting_layer);
        mSifDepositLayer       = itemView.findViewById(R.id.sif_harvest_deposit_layer);
        mSifIncentiveLayer     = itemView.findViewById(R.id.sif_harvest_incentive_layer);


        mSifTransfer           = itemView.findViewById(R.id.layer_sif_transfer);
        mBtnSendSif            = itemView.findViewById(R.id.btn_sif_send);
        mBtnReceiveSif         = itemView.findViewById(R.id.btn_sif_receive);
        mSifTransfer.setVisibility(View.GONE);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        final BigDecimal availableAmount = baseData.availableAmount(TOKEN_SIF);
        final BigDecimal vestingAmount = baseData.lockedAmount(TOKEN_SIF);
        final BigDecimal delegateAmount = baseData.delegatedSumAmount();
        final BigDecimal unbondingAmount = baseData.unbondingSumAmount();
        final BigDecimal rewardAmount = baseData.rewardAmount(TOKEN_SIF);
        final BigDecimal totalAmount = baseData.getAllMainAssetOld(TOKEN_SIF);

        mTvSifTotal.setText(WDp.getDpAmount2(c, totalAmount, 18, 18));
        mTvSifAvailable.setText(WDp.getDpAmount2(c, availableAmount, 18, 18));
        mTvSifDelegated.setText(WDp.getDpAmount2(c, delegateAmount, 18, 18));
        mTvSifUnBonding.setText(WDp.getDpAmount2(c, unbondingAmount, 18, 18));
        mTvSifRewards.setText(WDp.getDpAmount2(c, rewardAmount, 18, 18));
        mTvSifVesting.setText(WDp.getDpAmount2(c, vestingAmount, 18, 18));
        mTvSifValue.setText(WDp.getDpMainAssetValue(c, baseData, totalAmount, chain));

        if (!vestingAmount.equals(BigDecimal.ZERO)) {
            mSifVestingLayer.setVisibility(View.VISIBLE);
        }

    }
}
