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

public class TxUnDelegateHolder extends TxHolder {
    ImageView itemUndelegateImg;
    TextView itemUndelegateTitle;
    TextView itemUnDelegator, itemValidator, itemMoniker, itemUndelegateAmount, itemUnDelegateAmountDenom,
            itemAutoRewardAmount, itemAutoRewardAmountDenom;
    RelativeLayout itemAutoRewardLayer;

    public TxUnDelegateHolder(@NonNull View itemView) {
        super(itemView);
        itemUndelegateImg = itemView.findViewById(R.id.tx_undelegate_icon);
        itemUndelegateTitle = itemView.findViewById(R.id.tx_undelegate_text);
        itemUnDelegator = itemView.findViewById(R.id.tx_undelegate_undelegator);
        itemValidator = itemView.findViewById(R.id.tx_undelegate_validator);
        itemMoniker = itemView.findViewById(R.id.tx_undelegate_moniker);
        itemUndelegateAmount = itemView.findViewById(R.id.tx_undelegate_amount);
        itemUnDelegateAmountDenom = itemView.findViewById(R.id.tx_undelegate_amount_symbol);
        itemAutoRewardAmount = itemView.findViewById(R.id.tx_auto_claimed);
        itemAutoRewardAmountDenom = itemView.findViewById(R.id.tx_auto_claimed_symbol);
        itemAutoRewardLayer = itemView.findViewById(R.id.tx_undelegate_auto_reward);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        WDp.DpMainDenom(c, baseChain.getChain(), itemUnDelegateAmountDenom);
        WDp.DpMainDenom(c, baseChain.getChain(), itemAutoRewardAmountDenom);
        final int dpDecimal = WDp.mainDivideDecimal(baseChain);
        itemUndelegateImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            cosmos.staking.v1beta1.Tx.MsgUndelegate msg = cosmos.staking.v1beta1.Tx.MsgUndelegate.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemUnDelegator.setText(msg.getDelegatorAddress());
            itemValidator.setText(msg.getValidatorAddress());
            itemMoniker.setText( "(" + baseData.getValidatorInfo(msg.getValidatorAddress()).getDescription().getMoniker() + ")");
            itemUndelegateAmount.setText(WDp.getDpAmount2(c, new BigDecimal(msg.getAmount().getAmount()), dpDecimal, dpDecimal));
            itemAutoRewardAmount.setText(WDp.getDpAmount2(c, WDp.onParseAutoReward(response, msg.getDelegatorAddress(), position), dpDecimal, dpDecimal));

        } catch (Exception e) {}
    }

}
