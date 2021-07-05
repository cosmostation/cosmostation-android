package wannabit.io.cosmostaion.widget.txDetail.ibc;

import android.content.Context;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import org.json.JSONObject;

import java.util.ArrayList;

import cosmos.tx.v1beta1.ServiceOuterClass;
import ibc.core.channel.v1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxIBCReceiveHolder extends TxHolder {
    ImageView itemIbcReceiveImg;
    TextView itemIbcFromAddress, itemIbcToAddress;
    RelativeLayout itemIbcSingleCoinLayer;
    TextView itemIbcAmount, itemIbcAmountDenom;


    public TxIBCReceiveHolder(@NonNull View itemView) {
        super(itemView);
        itemIbcReceiveImg = itemView.findViewById(R.id.tx_ibc_receive_icon);
        itemIbcToAddress = itemView.findViewById(R.id.tx_ibc_receive_to_address);
        itemIbcFromAddress = itemView.findViewById(R.id.tx_ibc_receive_from_address);

        itemIbcSingleCoinLayer = itemView.findViewById(R.id.tx_ibc_receive_single_coin_layer);
        itemIbcAmount = itemView.findViewById(R.id.tx_ibc_receive_amount);
        itemIbcAmountDenom = itemView.findViewById(R.id.tx_ibc_receive_amount_symbol);

    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemIbcReceiveImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgRecvPacket msg = Tx.MsgRecvPacket.parseFrom(response.getTx().getBody().getMessages(position).getValue());
//            WLog.w("SSS : " + Base64.decode(msg.getPacket().getData()., Base64.DEFAULT));

        } catch (Exception e) {}
    }
}
