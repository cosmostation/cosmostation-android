package wannabit.io.cosmostaion.widget.txDetail;

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

public class TxReDelegateHolder extends TxHolder {
    ImageView itemRedelegateImg;
    TextView itemRedelegateTitle;
    TextView itemReDelegator, itemFromValidator, itemFromMoniker, itemToValidator, itemToMoniker, itemRedelegateAmount, itemReDelegateAmountDenom,
            itemAutoRewardAmount, itemAutoRewardAmountDenom;
    RelativeLayout itemAutoRewardLayer;

    public TxReDelegateHolder(@NonNull View itemView) {
        super(itemView);
        itemRedelegateImg = itemView.findViewById(R.id.tx_redelegate_icon);
        itemRedelegateTitle = itemView.findViewById(R.id.tx_undelegate_text);
        itemReDelegator = itemView.findViewById(R.id.tx_redelegate_redelegator);
        itemFromValidator = itemView.findViewById(R.id.tx_redelegate_from_validator);
        itemFromMoniker = itemView.findViewById(R.id.tx_redelegate_from_moniker);
        itemToValidator = itemView.findViewById(R.id.tx_redelegate_to_validator);
        itemToMoniker = itemView.findViewById(R.id.tx_redelegate_to_moniker);
        itemRedelegateAmount = itemView.findViewById(R.id.tx_redelegate_amount);
        itemReDelegateAmountDenom = itemView.findViewById(R.id.tx_redelegate_amount_symbol);
        itemAutoRewardAmount = itemView.findViewById(R.id.tx_auto_claimed);
        itemAutoRewardAmountDenom = itemView.findViewById(R.id.tx_auto_claimed_symbol);
        itemAutoRewardLayer = itemView.findViewById(R.id.tx_redelegate_auto_reward);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        WDp.DpMainDenom(c, baseChain.getChain(), itemReDelegateAmountDenom);
        WDp.DpMainDenom(c, baseChain.getChain(), itemAutoRewardAmountDenom);
        final int dpDecimal = WDp.mainDivideDecimal(baseChain);
        itemRedelegateImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            cosmos.staking.v1beta1.Tx.MsgBeginRedelegate msg = cosmos.staking.v1beta1.Tx.MsgBeginRedelegate.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemReDelegator.setText(msg.getDelegatorAddress());
            itemFromValidator.setText(msg.getValidatorSrcAddress());
            itemToValidator.setText(msg.getValidatorDstAddress());
            itemFromMoniker.setText( "(" + baseData.getValidatorInfo(msg.getValidatorSrcAddress()).getDescription().getMoniker() + ")");
            itemToMoniker.setText( "(" + baseData.getValidatorInfo(msg.getValidatorDstAddress()).getDescription().getMoniker() + ")");
            itemRedelegateAmount.setText(WDp.getDpAmount2(c, new BigDecimal(msg.getAmount().getAmount()), dpDecimal, dpDecimal));
            itemAutoRewardAmount.setText(WDp.getDpAmount2(c, WDp.onParseAutoReward(response, msg.getDelegatorAddress(), position), dpDecimal, dpDecimal));

        } catch (Exception e) {}
    }
}
