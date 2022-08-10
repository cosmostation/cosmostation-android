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

import cosmos.staking.v1beta1.Staking;
import de.hdodenhof.circleimageview.CircleImageView;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.utils.WDp;

public class AllValidatorHolder extends RecyclerView.ViewHolder {
    CardView itemRoot;
    CircleImageView itemAvatar;
    ImageView itemRevoked, itemBandOracleOff;
    TextView itemTvMoniker;
    TextView itemTvVotingPower;
    TextView itemTvCommission;
    
    public AllValidatorHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView.findViewById(R.id.card_validator);
        itemAvatar = itemView.findViewById(R.id.avatar_validator);
        itemRevoked = itemView.findViewById(R.id.avatar_validator_revoke);
        itemTvMoniker = itemView.findViewById(R.id.moniker_validator);
        itemBandOracleOff = itemView.findViewById(R.id.band_oracle_off);
        itemTvVotingPower = itemView.findViewById(R.id.delegate_power_validator);
        itemTvCommission = itemView.findViewById(R.id.delegate_commission_validator);
    }

    public void onBindAuthzAllValidatorList(BaseData baseData, ChainConfig chainConfig, Staking.Validator otherValidator) {
        if (chainConfig == null) return;
        final int dpDecimal = WDp.getDenomDecimal(baseData, chainConfig, chainConfig.mainDenom());
        itemTvVotingPower.setText(WDp.getDpAmount2(itemView.getContext(), new BigDecimal(otherValidator.getTokens()), dpDecimal, 6));
        itemTvCommission.setText(WDp.getDpEstAprCommission(baseData, chainConfig.baseChain(), new BigDecimal(otherValidator.getCommission().getCommissionRates().getRate()).movePointLeft(18)));
        try {
            Picasso.get().load(chainConfig.monikerUrl() + otherValidator.getOperatorAddress() + ".png").fit().placeholder(R.drawable.validator_none_img).error(R.drawable.validator_none_img).into(itemAvatar);
        } catch (Exception e) { }

        itemTvMoniker.setText(otherValidator.getDescription().getMoniker());
        if (otherValidator.getJailed()) {
            itemAvatar.setBorderColor(ContextCompat.getColor(itemView.getContext(), R.color.colorRed));
            itemRevoked.setVisibility(View.VISIBLE);
        } else {
            itemAvatar.setBorderColor(ContextCompat.getColor(itemView.getContext(), R.color.colorGray3));
            itemRevoked.setVisibility(View.GONE);
        }
        if (baseData.mGRpcMyValidators.contains(otherValidator)) {
            itemRoot.setCardBackgroundColor(ContextCompat.getColor(itemView.getContext(), chainConfig.chainBgColor()));
        } else {
            itemRoot.setCardBackgroundColor(ContextCompat.getColor(itemView.getContext(), R.color.colorTransBg));
        }

        if (chainConfig.baseChain().equals(BaseChain.BAND_MAIN)) {
            if (!baseData.mChainParam.isOracleEnable(otherValidator.getOperatorAddress())) {
                itemBandOracleOff.setVisibility(View.VISIBLE);
            }
        }
    }
}
