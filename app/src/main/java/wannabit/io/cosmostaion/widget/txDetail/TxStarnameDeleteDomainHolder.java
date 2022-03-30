package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import starnamed.x.starname.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;

public class TxStarnameDeleteDomainHolder extends TxHolder {
    ImageView itemMsgImg;
    TextView itemMsgTitle;
    TextView itemDomain, itemOwner;


    public TxStarnameDeleteDomainHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
        itemDomain = itemView.findViewById(R.id.tx_domain);
        itemOwner = itemView.findViewById(R.id.tx_owner);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        try {
            Tx.MsgDeleteDomain msg = Tx.MsgDeleteDomain.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemDomain.setText("*" + msg.getDomain());
            itemOwner.setText(msg.getOwner());
        } catch (Exception e) {
        }
    }
}
