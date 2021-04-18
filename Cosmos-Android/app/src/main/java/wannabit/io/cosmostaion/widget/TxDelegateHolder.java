package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class TxDelegateHolder extends TxHolder {
    ImageView itemDelegateImg;
    TextView itemDelegateTitle;
    TextView itemDelegator, itemValidator, itemMoniker, itemDelegateAmount, itemDelegateAmountDenom,
            itemAutoRewardAmount, itemAutoRewardAmountDenom;
    RelativeLayout itemAutoRewardLayer;

    public TxDelegateHolder(@NonNull View itemView) {
        super(itemView);
        itemDelegateImg = itemView.findViewById(R.id.tx_delegate_icon);
        itemDelegateTitle = itemView.findViewById(R.id.tx_delegate_text);
        itemDelegator = itemView.findViewById(R.id.tx_delegate_delegator);
        itemValidator = itemView.findViewById(R.id.tx_delegate_validator);
        itemMoniker = itemView.findViewById(R.id.tx_delegate_moniker);
        itemDelegateAmount = itemView.findViewById(R.id.tx_delegate_amount);
        itemDelegateAmountDenom = itemView.findViewById(R.id.tx_delegate_amount_symbol);
        itemAutoRewardAmount = itemView.findViewById(R.id.tx_auto_claimed);
        itemAutoRewardAmountDenom = itemView.findViewById(R.id.tx_auto_claimed_symbol);
        itemAutoRewardLayer = itemView.findViewById(R.id.tx_delegate_auto_reward);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        WDp.DpMainDenom(c, baseChain.getChain(), itemDelegateAmountDenom);
        WDp.DpMainDenom(c, baseChain.getChain(), itemAutoRewardAmountDenom);
        final int dpDecimal = WDp.mainDivideDecimal(baseChain);
        itemDelegateImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            cosmos.staking.v1beta1.Tx.MsgDelegate msg = cosmos.staking.v1beta1.Tx.MsgDelegate.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemDelegator.setText(msg.getDelegatorAddress());
            itemValidator.setText(msg.getValidatorAddress());
            itemMoniker.setText( "(" + baseData.getValidatorInfo(msg.getValidatorAddress()).getDescription().getMoniker() + ")");
            itemDelegateAmount.setText(WDp.getDpAmount2(c, new BigDecimal(msg.getAmount().getAmount()), dpDecimal, dpDecimal));
            itemAutoRewardAmount.setText(WDp.getDpAmount2(c, WDp.onParseAutoReward(response, msg.getDelegatorAddress(), position), dpDecimal, dpDecimal));

        } catch (Exception e) {}
    }



}
