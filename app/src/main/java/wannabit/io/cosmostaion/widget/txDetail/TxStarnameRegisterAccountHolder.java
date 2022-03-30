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

public class TxStarnameRegisterAccountHolder extends TxHolder {
    ImageView itemMsgImg;
    TextView itemMsgTitle;
    TextView itemStarname, itemOwner, itemRegister, itemStarnameFee;

    public TxStarnameRegisterAccountHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
        itemStarname = itemView.findViewById(R.id.tx_starname);
        itemOwner = itemView.findViewById(R.id.tx_owner);
        itemRegister = itemView.findViewById(R.id.tx_register);
        itemStarnameFee = itemView.findViewById(R.id.tx_starname_fee_amount);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        try {
            Tx.MsgRegisterAccount msg = Tx.MsgRegisterAccount.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemStarname.setText(msg.getName() + "*" + msg.getDomain());
            itemOwner.setText(msg.getOwner());
            itemRegister.setText(msg.getRegisterer());

            BigDecimal starnameFee = baseData.getStarNameRegisterAccountFee("open");
            itemStarnameFee.setText(WDp.getDpAmount2(c, starnameFee, 6, 6));

        } catch (Exception e) {
        }
    }
}
