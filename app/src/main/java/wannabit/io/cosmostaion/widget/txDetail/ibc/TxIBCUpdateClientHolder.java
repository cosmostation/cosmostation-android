package wannabit.io.cosmostaion.widget.txDetail.ibc;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import ibc.core.client.v1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxIBCUpdateClientHolder extends TxHolder {
    ImageView itemIbcUpdateClienImg;
    TextView itemIbcUpdateClientSiner, itemIbcUpdateClientId;

    public TxIBCUpdateClientHolder(@NonNull View itemView) {
        super(itemView);
        itemIbcUpdateClienImg = itemView.findViewById(R.id.tx_ibc_update_client_icon);
        itemIbcUpdateClientSiner = itemView.findViewById(R.id.tx_ibc_update_client_siner);
        itemIbcUpdateClientId = itemView.findViewById(R.id.tx_ibc_update_client_id);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemIbcUpdateClienImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgUpdateClient msg = Tx.MsgUpdateClient.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemIbcUpdateClientSiner.setText(msg.getSigner());
            itemIbcUpdateClientId.setText("" + msg.getClientId());
        } catch (Exception e) {
        }
    }
}
