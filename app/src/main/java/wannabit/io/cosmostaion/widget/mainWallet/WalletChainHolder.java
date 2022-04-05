package wannabit.io.cosmostaion.widget.mainWallet;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.ValidatorListActivity;
import wannabit.io.cosmostaion.activities.VoteListActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletChainHolder extends BaseHolder {
    public CardView mTvChainCard;
    public ImageView mTvChainIcon;
    public TextView mTvChainDenom;
    public TextView mTvChainTotal, mTvChainValue, mTvChainAvailable, mTvChainDelegated, mTvChainUnBonding, mTvChainRewards;
    public RelativeLayout mChainVestingLayer;
    public TextView mTvChainVesting;
    public RelativeLayout mBtnStake, mBtnVote, mBtnDex, mBtnWalletConnect;
    public TextView mBtnDexTitle;

    public WalletChainHolder(@NonNull View itemView) {
        super(itemView);
        mTvChainCard = itemView.findViewById(R.id.card_root);
        mTvChainIcon = itemView.findViewById(R.id.chain_icon);
        mTvChainDenom = itemView.findViewById(R.id.chain_denom);
        mTvChainTotal = itemView.findViewById(R.id.chain_amount);
        mTvChainValue = itemView.findViewById(R.id.chain_value);
        mTvChainAvailable = itemView.findViewById(R.id.chain_available);
        mTvChainDelegated = itemView.findViewById(R.id.chain_delegate);
        mTvChainUnBonding = itemView.findViewById(R.id.chain_unbonding);
        mTvChainRewards = itemView.findViewById(R.id.chain_reward);

        mChainVestingLayer = itemView.findViewById(R.id.chain_vesting_layer);
        mTvChainVesting = itemView.findViewById(R.id.chain_vesting);

        mBtnStake = itemView.findViewById(R.id.btn_delegate);
        mBtnVote = itemView.findViewById(R.id.btn_vote);
        mBtnDex = itemView.findViewById(R.id.btn_dex);
        mBtnDexTitle = itemView.findViewById(R.id.dex_title);
        mBtnWalletConnect = itemView.findViewById(R.id.btn_wallet_connect);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final BaseData baseData = mainActivity.getBaseDao();
        final String denom = mainActivity.baseChain.getMainDenom();
        final int decimal = WDp.mainDivideDecimal(mainActivity.baseChain);
        mTvChainCard.setCardBackgroundColor(WDp.getChainBgColor(mainActivity, mainActivity.baseChain));
        WUtil.getWalletData(mainActivity.baseChain, mTvChainIcon, mTvChainDenom);

        final BigDecimal availableAmount = baseData.getAvailable(denom);
        final BigDecimal vestingAmount = baseData.getVesting(denom);
        final BigDecimal delegateAmount = baseData.getDelegationSum();
        final BigDecimal unbondingAmount = baseData.getUndelegationSum();
        final BigDecimal rewardAmount = baseData.getRewardSum(denom);
        final BigDecimal totalAmount = baseData.getAllMainAsset(denom);

        mTvChainTotal.setText(WDp.getDpAmount2(totalAmount, decimal, 6));
        mTvChainAvailable.setText(WDp.getDpAmount2(availableAmount, decimal, 6));
        mTvChainVesting.setText(WDp.getDpAmount2(vestingAmount, decimal, 6));
        mTvChainDelegated.setText(WDp.getDpAmount2(delegateAmount, decimal, 6));
        mTvChainUnBonding.setText(WDp.getDpAmount2(unbondingAmount, decimal, 6));
        mTvChainRewards.setText(WDp.getDpAmount2(rewardAmount, decimal, 6));
        mTvChainValue.setText(WDp.dpUserCurrencyValue(baseData, denom, totalAmount, decimal));

        if (!vestingAmount.equals(BigDecimal.ZERO)) {
            mChainVestingLayer.setVisibility(View.VISIBLE);
        } else {
            mChainVestingLayer.setVisibility(View.GONE);
        }

        mainActivity.getBaseDao().onUpdateLastTotalAccount(mainActivity.account, totalAmount.toPlainString());

        mBtnStake.setOnClickListener(v -> {
            Intent validators = new Intent(mainActivity, ValidatorListActivity.class);
            mainActivity.startActivity(validators);
        });
        mBtnVote.setOnClickListener(v -> {
            Intent proposals = new Intent(mainActivity, VoteListActivity.class);
            mainActivity.startActivity(proposals);
        });

        // dex, nft, desmos profile setting
        WUtil.getDexTitle(mainActivity.baseChain, mBtnDex, mBtnDexTitle);
        mBtnDex.setOnClickListener(v -> {
            if (mainActivity.baseChain.equals(BaseChain.DESMOS_MAIN)) {
                mainActivity.onClickProfile();
            } else {
                mainActivity.startActivity(WUtil.getDexIntent(mainActivity, mainActivity.baseChain));
            }
        });

        if (mainActivity.baseChain.equals(BaseChain.COSMOS_MAIN)) {
            mBtnWalletConnect.setVisibility(View.VISIBLE);
        } else {
            mBtnWalletConnect.setVisibility(View.GONE);
        }
        mBtnWalletConnect.setOnClickListener(v -> {
            Toast.makeText(mainActivity, R.string.error_prepare, Toast.LENGTH_SHORT).show();
        });
    }
}
