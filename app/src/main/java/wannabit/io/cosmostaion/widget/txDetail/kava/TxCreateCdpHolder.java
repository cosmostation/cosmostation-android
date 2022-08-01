package wannabit.io.cosmostaion.widget.txDetail.kava;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import kava.cdp.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxCreateCdpHolder extends TxHolder {
    TextView  itemMsgTitle;
    TextView  itemSender, itemCollateralAmount, itemCollateralDenom,
              itemPrincipalAmount, itemPrincipalDenom;

    public TxCreateCdpHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
        itemSender = itemView.findViewById(R.id.tx_cdp_sender);
        itemCollateralAmount = itemView.findViewById(R.id.tx_collateral_amount);
        itemCollateralDenom = itemView.findViewById(R.id.tx_collateral_symbol);
        itemPrincipalAmount = itemView.findViewById(R.id.tx_principal_amount);
        itemPrincipalDenom = itemView.findViewById(R.id.tx_principal_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        try {
            Tx.MsgCreateCDP msg = Tx.MsgCreateCDP.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemSender.setText(msg.getSender());
            WDp.setDpCoin(c, baseData, chainConfig, msg.getCollateral().getDenom(), msg.getCollateral().getAmount(), itemCollateralDenom, itemCollateralAmount);
            WDp.setDpCoin(c, baseData, chainConfig, msg.getPrincipal().getDenom(), msg.getPrincipal().getAmount(), itemPrincipalDenom, itemPrincipalAmount);

        } catch (Exception e) { }
    }
}
