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

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;

public class WalletCosmosHolder extends BaseHolder {
    public TextView         mTvDenomTitle;
    public TextView         mTvAtomTotal, mTvAtomValue, mTvAtomAvailable, mTvAtomDelegated, mTvAtomUnBonding, mTvAtomRewards;
    public RelativeLayout   mBtnStake, mBtnVote;

    public WalletCosmosHolder(@NonNull View itemView) {
        super(itemView);
        mTvDenomTitle       = itemView.findViewById(R.id.atom_title);
        mTvAtomTotal        = itemView.findViewById(R.id.atom_amount);
        mTvAtomValue        = itemView.findViewById(R.id.atom_value);
        mTvAtomAvailable    = itemView.findViewById(R.id.atom_available);
        mTvAtomDelegated    = itemView.findViewById(R.id.atom_delegate);
        mTvAtomUnBonding    = itemView.findViewById(R.id.atom_unbonding);
        mTvAtomRewards      = itemView.findViewById(R.id.atom_reward);
        mBtnStake           = itemView.findViewById(R.id.btn_cosmos_reward);
        mBtnVote            = itemView.findViewById(R.id.btn_cosmos_vote);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        if (mainActivity.mBaseChain.equals(COSMOS_MAIN)) {
            mTvDenomTitle.setText(mainActivity.getString(R.string.s_atom));

        } else if (mainActivity.mBaseChain.equals(COSMOS_TEST)) {
            mTvDenomTitle.setText(mainActivity.getString(R.string.s_muon));
        }
        final BigDecimal availableAmount = baseData.getAvailable(WDp.mainDenom(mainActivity.mBaseChain));
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(WDp.mainDenom(mainActivity.mBaseChain));
        final BigDecimal totalAmount = baseData.getAllMainAsset(WDp.mainDenom(mainActivity.mBaseChain));

        mTvAtomTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvAtomAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvAtomDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvAtomUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvAtomRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvAtomValue.setText(WDp.getDpMainAssetValue(mainActivity, baseData, totalAmount, mainActivity.mBaseChain));
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
