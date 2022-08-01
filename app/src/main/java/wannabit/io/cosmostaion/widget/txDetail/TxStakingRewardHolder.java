package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class TxStakingRewardHolder extends TxHolder {
    ImageView itemRewardImg;
    TextView itemRewardTitle;
    TextView itemDelegator, itemValidator, itemMoniker, itemRewardAmount, itemRewardAmountDenom;

    public TxStakingRewardHolder(@NonNull View itemView) {
        super(itemView);
        itemRewardImg = itemView.findViewById(R.id.tx_reward_icon);
        itemRewardTitle = itemView.findViewById(R.id.tx_reward_text);
        itemDelegator = itemView.findViewById(R.id.tx_reward_delegator);
        itemValidator = itemView.findViewById(R.id.tx_reward_validator);
        itemMoniker = itemView.findViewById(R.id.tx_reward_moniker);
        itemRewardAmount = itemView.findViewById(R.id.tx_reward_amount);
        itemRewardAmountDenom = itemView.findViewById(R.id.tx_reward_amount_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        itemRewardImg.setColorFilter(ContextCompat.getColor(c, chainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward msg = cosmos.distribution.v1beta1.Tx.MsgWithdrawDelegatorReward.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemDelegator.setText(msg.getDelegatorAddress());
            itemValidator.setText(msg.getValidatorAddress());
            itemMoniker.setText( "(" + baseData.getValidatorInfo(msg.getValidatorAddress()).getDescription().getMoniker() + ")");

            ArrayList<Coin> rewardsCoins = WDp.onParseStakeReward(response, position);
            WDp.setDpCoin(c, baseData, chainConfig, rewardsCoins.get(0), itemRewardAmountDenom, itemRewardAmount);

        } catch (Exception e) {}

    }
}
