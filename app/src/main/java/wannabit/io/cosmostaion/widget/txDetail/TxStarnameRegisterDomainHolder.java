package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

import cosmos.tx.v1beta1.ServiceOuterClass;
import starnamed.x.starname.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class TxStarnameRegisterDomainHolder extends TxHolder {
    ImageView itemMsgImg;
    TextView itemMsgTitle;
    TextView itemDomain, itemAdmin, itemType, itemStarnameFee;


    public TxStarnameRegisterDomainHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
        itemDomain = itemView.findViewById(R.id.tx_domain);
        itemAdmin = itemView.findViewById(R.id.tx_admin_address);
        itemType = itemView.findViewById(R.id.tx_domain_type);
        itemStarnameFee = itemView.findViewById(R.id.tx_starname_fee_amount);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        try {
            Tx.MsgRegisterDomain msg = Tx.MsgRegisterDomain.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemDomain.setText("*" + msg.getName());
            itemAdmin.setText(msg.getAdmin());
            itemType.setText(msg.getDomainType());

            BigDecimal starnameFee = baseData.getStarNameRegisterDomainFee(msg.getName(), msg.getDomainType());
            itemStarnameFee.setText(WDp.getDpAmount2(starnameFee, 6, 6));
        } catch (Exception e) {
        }
    }
}
