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

public class TxDepositCdpHolder extends TxHolder {
    TextView    itemMsgTitle;
    TextView    itemOwner, itemDepositor, itemCollateralAmount, itemCollateralDenom;

    public TxDepositCdpHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
        itemOwner = itemView.findViewById(R.id.tx_cdp_owner);
        itemDepositor = itemView.findViewById(R.id.tx_cdp_depositor);
        itemCollateralAmount = itemView.findViewById(R.id.tx_collateral_amount);
        itemCollateralDenom = itemView.findViewById(R.id.tx_collateral_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        try {
            Tx.MsgDeposit msg = Tx.MsgDeposit.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemOwner.setText(msg.getOwner());
            itemDepositor.setText(msg.getDepositor());
            WDp.setDpCoin(c, baseData, chainConfig, msg.getCollateral().getDenom(), msg.getCollateral().getAmount(), itemCollateralDenom, itemCollateralAmount);

        } catch (Exception e) { }
    }
}
