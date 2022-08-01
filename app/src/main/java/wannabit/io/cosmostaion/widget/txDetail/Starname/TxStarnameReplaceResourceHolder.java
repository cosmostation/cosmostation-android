package wannabit.io.cosmostaion.widget.txDetail.Starname;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.List;

import cosmos.tx.v1beta1.ServiceOuterClass;
import starnamed.x.starname.v1beta1.Tx;
import starnamed.x.starname.v1beta1.Types;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxStarnameReplaceResourceHolder extends TxHolder {
    ImageView itemMsgImg;
    TextView itemMsgTitle;
    TextView itemStarname, itemStarnameFee, itemAddressCnt;

    public TxStarnameReplaceResourceHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
        itemStarname = itemView.findViewById(R.id.tx_starname);
        itemStarnameFee = itemView.findViewById(R.id.tx_starname_fee_amount);
        itemAddressCnt = itemView.findViewById(R.id.tx_address_cnt);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        try {
            Tx.MsgReplaceAccountResources msg = Tx.MsgReplaceAccountResources.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemStarname.setText(msg.getName() + "*" + msg.getDomain());

            BigDecimal starnameFee = baseData.getReplaceFee();
            itemStarnameFee.setText(WDp.getDpAmount2(c, starnameFee, 6, 6));

            List<Types.Resource> resources = msg.getNewResourcesList();
            if (resources == null || resources.size() == 0) {
                itemAddressCnt.setText("0");
            } else {
                itemAddressCnt.setText("" + resources.size());
            }

        } catch (Exception e) {}
    }
}
