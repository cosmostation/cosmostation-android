package wannabit.io.cosmostaion.widget.txDetail.ibc;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import cosmos.tx.v1beta1.ServiceOuterClass;
import ibc.applications.transfer.v1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxIBCSendHolder extends TxHolder {
    ImageView itemIbcSendImg;
    TextView itemIbcSendTv;
    TextView itemIbcFromAddress, itemIbcToAddress;
    RelativeLayout itemIbcSingleCoinLayer;
    TextView itemIbcAmount, itemIbcAmountDenom;


    public TxIBCSendHolder(@NonNull View itemView) {
        super(itemView);
        itemIbcSendImg = itemView.findViewById(R.id.tx_ibc_send_icon);
        itemIbcSendTv = itemView.findViewById(R.id.tx_ibc_send_text);
        itemIbcFromAddress = itemView.findViewById(R.id.tx_ibc_send_from_address);
        itemIbcToAddress = itemView.findViewById(R.id.tx_ibc_send_to_address);

        itemIbcSingleCoinLayer = itemView.findViewById(R.id.tx_ibc_send_single_coin_layer);
        itemIbcAmount = itemView.findViewById(R.id.tx_ibc_transfer_amount);
        itemIbcAmountDenom = itemView.findViewById(R.id.tx_ibc_transfer_amount_symbol);

    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemIbcSendImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgTransfer msg = Tx.MsgTransfer.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemIbcFromAddress.setText(msg.getSender());
            itemIbcToAddress.setText(msg.getReceiver());

            ArrayList<Coin> toDpCoin = new ArrayList<>();
            toDpCoin.add(new Coin(msg.getToken().getDenom(), msg.getToken().getAmount()));

            itemIbcSingleCoinLayer.setVisibility(View.VISIBLE);
            WDp.showCoinDp(c, baseData, toDpCoin.get(0), itemIbcAmountDenom, itemIbcAmount, baseChain);
        } catch (Exception e) {
        }
    }
}
