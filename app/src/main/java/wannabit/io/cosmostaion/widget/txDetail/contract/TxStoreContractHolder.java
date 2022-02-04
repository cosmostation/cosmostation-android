package wannabit.io.cosmostaion.widget.txDetail.contract;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import cosmwasm.wasm.v1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxStoreContractHolder extends TxHolder {
    ImageView   itemMsgImg;
    TextView    itemContractSender;

    public TxStoreContractHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgImg              = itemView.findViewById(R.id.tx_msg_icon);
        itemContractSender      = itemView.findViewById(R.id.contract_sender);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemMsgImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgStoreCode msg = Tx.MsgStoreCode.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemContractSender.setText(msg.getSender());

        } catch (Exception e) { }
    }
}
