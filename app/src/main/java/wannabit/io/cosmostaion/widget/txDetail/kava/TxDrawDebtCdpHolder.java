package wannabit.io.cosmostaion.widget.txDetail.kava;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import kava.cdp.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxDrawDebtCdpHolder extends TxHolder {
    ImageView itemMsgImg;
    TextView itemMsgTitle;
    TextView itemSender, itemCdpDenom, itemPrincipalAmount, itemPrincipalDenom;

    public TxDrawDebtCdpHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
        itemSender = itemView.findViewById(R.id.tx_cdp_sender);
        itemCdpDenom = itemView.findViewById(R.id.tx_cdp_denom);
        itemPrincipalAmount = itemView.findViewById(R.id.tx_principal_amount);
        itemPrincipalDenom = itemView.findViewById(R.id.tx_principal_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemMsgImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgDrawDebt msg = Tx.MsgDrawDebt.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemSender.setText(msg.getSender());
            itemCdpDenom.setText(msg.getCollateralType());
            WDp.showCoinDp(c, baseData, msg.getPrincipal().getDenom(), msg.getPrincipal().getAmount(), itemPrincipalDenom, itemPrincipalAmount, BaseChain.KAVA_MAIN);

        } catch (Exception e) {
        }
    }
}
