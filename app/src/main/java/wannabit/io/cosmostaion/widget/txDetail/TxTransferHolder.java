package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class TxTransferHolder extends TxHolder {
    ImageView itemSendReceiveImg;
    TextView itemSendRecieveTv;
    TextView itemFromAddress, itemToAddress;
    TextView itemAmount, itemAmountDenom;

    public TxTransferHolder(@NonNull View itemView) {
        super(itemView);
        itemSendReceiveImg = itemView.findViewById(R.id.tx_send_icon);
        itemSendRecieveTv = itemView.findViewById(R.id.tx_send_text);
        itemFromAddress = itemView.findViewById(R.id.tx_send_from_address);
        itemToAddress = itemView.findViewById(R.id.tx_send_to_address);

        itemAmount = itemView.findViewById(R.id.tx_transfer_amount);
        itemAmountDenom = itemView.findViewById(R.id.tx_transfer_amount_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemSendReceiveImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            cosmos.bank.v1beta1.Tx.MsgSend msg = cosmos.bank.v1beta1.Tx.MsgSend.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemFromAddress.setText(msg.getFromAddress());
            itemToAddress.setText(msg.getToAddress());
            if (address.equals(msg.getFromAddress())) {
                itemSendRecieveTv.setText(R.string.tx_send);
            }
            if (address.equals(msg.getToAddress())) {
                itemSendRecieveTv.setText(R.string.tx_receive);
            }
            ArrayList<Coin> toDpCoin = new ArrayList<>();
            for (CoinOuterClass.Coin coin: msg.getAmountList()) {
                toDpCoin.add(new Coin(coin.getDenom(), coin.getAmount()));
            }
            WDp.showCoinDp(c, baseData, toDpCoin.get(0), itemAmountDenom, itemAmount, baseChain);
        } catch (Exception e) {}
    }
}
