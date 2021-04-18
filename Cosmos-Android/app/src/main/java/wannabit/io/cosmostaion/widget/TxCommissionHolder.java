package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

import cosmos.distribution.v1beta1.Tx;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class TxCommissionHolder extends TxHolder {
    ImageView itemCommissionImg;
    TextView itemCommissionTitle;
    TextView itemCommissionValidator, itemCommissionValidatorMoniker, itemCommissionAmount, itemCommissionAmountDenom;


    public TxCommissionHolder(@NonNull View itemView) {
        super(itemView);
        itemCommissionImg = itemView.findViewById(R.id.tx_commission_icon);
        itemCommissionTitle = itemView.findViewById(R.id.tx_commission_text);
        itemCommissionValidator = itemView.findViewById(R.id.tx_commission_validator);
        itemCommissionValidatorMoniker = itemView.findViewById(R.id.tx_commission_moniker);
        itemCommissionAmount = itemView.findViewById(R.id.tx_commission_amount);
        itemCommissionAmountDenom = itemView.findViewById(R.id.tx_commission_amount_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        WDp.DpMainDenom(c, baseChain.getChain(), itemCommissionAmountDenom);
        itemCommissionImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        final int dpDecimal = WDp.mainDivideDecimal(baseChain);

        try {
            Tx.MsgWithdrawValidatorCommission msg = Tx.MsgWithdrawValidatorCommission.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemCommissionValidator.setText(msg.getValidatorAddress());
            itemCommissionValidatorMoniker.setText( "(" + baseData.getValidatorInfo(msg.getValidatorAddress()).getDescription().getMoniker() + ")");
            itemCommissionAmount.setText(WDp.getDpAmount2(c, WDp.onParseCommission(response, position), dpDecimal, dpDecimal));

        } catch (Exception e) {}

    }
}
