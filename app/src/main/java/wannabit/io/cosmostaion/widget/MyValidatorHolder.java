package wannabit.io.cosmostaion.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;

import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.distribution.v1beta1.Distribution;
import cosmos.staking.v1beta1.Staking;
import de.hdodenhof.circleimageview.CircleImageView;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.authz.AuthzDelegateActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.utils.WDp;

public class MyValidatorHolder extends RecyclerView.ViewHolder {
    private CardView itemRoot;
    private CircleImageView itemAvatar;
    private ImageView itemRevoked, itemBandOracleOff;
    private TextView itemTvMoniker;
    private TextView itemTvDelegateAmount;
    private TextView itemTvUndelegateAmount;
    private TextView itemTvReward;

    public MyValidatorHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView.findViewById(R.id.card_validator);
        itemAvatar = itemView.findViewById(R.id.avatar_validator);
        itemRevoked = itemView.findViewById(R.id.avatar_validator_revoke);
        itemTvMoniker = itemView.findViewById(R.id.moniker_validator);
        itemBandOracleOff = itemView.findViewById(R.id.band_oracle_off);
        itemTvDelegateAmount = itemView.findViewById(R.id.delegate_amount_validator);
        itemTvUndelegateAmount = itemView.findViewById(R.id.undelegate_amount_validator);
        itemTvReward = itemView.findViewById(R.id.my_reward_validator);
    }

    public void onBindAuthzValidatorList(AuthzDelegateActivity c, BaseData baseData, ChainConfig chainConfig, Staking.Validator myValidator,
                                         ArrayList<Staking.DelegationResponse> granterDelegations, ArrayList<Staking.UnbondingDelegation> granterUndelegations, ArrayList<Distribution.DelegationDelegatorReward> granterRewards) {
        if (chainConfig == null) return;
        int dpDecimal = WDp.getDenomDecimal(baseData, chainConfig, chainConfig.mainDenom());
        try {
            Picasso.get().load(chainConfig.monikerUrl() + myValidator.getOperatorAddress() + ".png").fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(itemAvatar);
        } catch (Exception e) { }
        itemTvMoniker.setText(myValidator.getDescription().getMoniker());
        itemRoot.setCardBackgroundColor(ContextCompat.getColor(c, chainConfig.chainBgColor()));
        if (myValidator.getJailed()) {
            itemAvatar.setBorderColor(ContextCompat.getColor(c, R.color.colorRed));
            itemRevoked.setVisibility(View.VISIBLE);
        } else {
            itemAvatar.setBorderColor(ContextCompat.getColor(c, R.color.colorGray3));
            itemRevoked.setVisibility(View.GONE);
        }

        BigDecimal delegatedAmount = BigDecimal.ZERO;
        for (Staking.DelegationResponse delegation : granterDelegations) {
            if (delegation.getDelegation().getValidatorAddress().equalsIgnoreCase(myValidator.getOperatorAddress())) {
                delegatedAmount = new BigDecimal(delegation.getBalance().getAmount());
            }
        }
        itemTvDelegateAmount.setText(WDp.getDpAmount2(c, delegatedAmount, dpDecimal, 6));
        BigDecimal unbondingAmount = BigDecimal.ZERO;
        for (Staking.UnbondingDelegation unbonding : granterUndelegations) {
            if (unbonding.getValidatorAddress().equalsIgnoreCase(myValidator.getOperatorAddress())) {
                for (Staking.UnbondingDelegationEntry entry : unbonding.getEntriesList()) {
                    unbondingAmount = unbondingAmount.add(new BigDecimal(entry.getBalance()));
                }
            }
        }
        itemTvUndelegateAmount.setText(WDp.getDpAmount2(c, unbondingAmount, dpDecimal, 6));

        BigDecimal rewardAmount = BigDecimal.ZERO;
        for (Distribution.DelegationDelegatorReward reward : granterRewards) {
            if (reward.getValidatorAddress().equalsIgnoreCase(myValidator.getOperatorAddress())) {
                for (CoinOuterClass.DecCoin coin : reward.getRewardList()) {
                    if (coin.getDenom().equalsIgnoreCase(chainConfig.mainDenom())) {
                        rewardAmount = rewardAmount.add(new BigDecimal(coin.getAmount()));
                    }
                }
            }
        }
        itemTvReward.setText(WDp.getDpAmount2(c, rewardAmount.movePointLeft(18), dpDecimal, 6));

        if (chainConfig.baseChain().equals(BaseChain.BAND_MAIN)) {
            if (!baseData.mChainParam.isOracleEnable(myValidator.getOperatorAddress())) {
                itemBandOracleOff.setVisibility(View.VISIBLE);
            }
        }

        itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                c.mValAddress = myValidator.getOperatorAddress();
                c.onNextStep();
            }
        });
    }
}
