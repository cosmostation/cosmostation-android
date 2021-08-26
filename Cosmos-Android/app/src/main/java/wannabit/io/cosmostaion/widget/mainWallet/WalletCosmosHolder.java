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
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.BaseHolder;

import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.COSMOS_TEST;

public class WalletCosmosHolder extends BaseHolder {
    public TextView         mTvDenomTitle;
    public TextView         mTvAtomTotal, mTvAtomValue, mTvAtomAvailable, mTvAtomDelegated, mTvAtomUnBonding, mTvAtomRewards;
    public RelativeLayout   mBtnStake, mBtnVote, mBtnGravityDex, mBtnWalletConnect;

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
        mBtnGravityDex      = itemView.findViewById(R.id.btn_cosmos_gravity_dex);
        mBtnWalletConnect   = itemView.findViewById(R.id.btn_cosmos_wallet_connect);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        if (mainActivity.mBaseChain.equals(COSMOS_MAIN)) {
            mTvDenomTitle.setText(mainActivity.getString(R.string.s_atom));

        } else if (mainActivity.mBaseChain.equals(COSMOS_TEST)) {
            mTvDenomTitle.setText(mainActivity.getString(R.string.s_muon));
        }
        final String denom = WDp.mainDenom(mainActivity.mBaseChain);
        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvAtomTotal.setText(WDp.getDpAmount2(mainActivity, totalAmount, 6, 6));
        mTvAtomAvailable.setText(WDp.getDpAmount2(mainActivity, availableAmount, 6, 6));
        mTvAtomDelegated.setText(WDp.getDpAmount2(mainActivity, delegateAmount, 6, 6));
        mTvAtomUnBonding.setText(WDp.getDpAmount2(mainActivity, unbondingAmount, 6, 6));
        mTvAtomRewards.setText(WDp.getDpAmount2(mainActivity, rewardAmount, 6, 6));
        mTvAtomValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, 6));
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
        mBtnGravityDex.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent proposals = new Intent(mainActivity, VoteListActivity.class);
//                mainActivity.startActivity(proposals);
                Toast.makeText(mainActivity, mainActivity.getString(R.string.error_prepare), Toast.LENGTH_SHORT).show();
            }
        });
        mBtnWalletConnect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent proposals = new Intent(mainActivity, VoteListActivity.class);
//                mainActivity.startActivity(proposals);
                Toast.makeText(mainActivity, mainActivity.getString(R.string.error_prepare), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
