package wannabit.io.cosmostaion.widget;

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
import wannabit.io.cosmostaion.utils.WLog;

public class TxTransferHolder extends TxHolder {
    ImageView itemSendReceiveImg;
    TextView itemSendRecieveTv;
    TextView itemFromAddress, itemToAddress;
    RelativeLayout itemSingleCoinLayer;
    TextView itemAmount, itemAmountDenom;
    LinearLayout itemMultiCoinLayer;
    RelativeLayout itemAmountLayer0, itemAmountLayer1, itemAmountLayer2, itemAmountLayer3, itemAmountLayer4;
    TextView itemAmount0, itemAmountDenom0, itemAmount1, itemAmountDenom1, itemAmount2, itemAmountDenom2,
            itemAmount3, itemAmountDenom3, itemAmount4, itemAmountDenom4;


    public TxTransferHolder(@NonNull View itemView) {
        super(itemView);
        itemSendReceiveImg = itemView.findViewById(R.id.tx_send_icon);
        itemSendRecieveTv = itemView.findViewById(R.id.tx_send_text);
        itemFromAddress = itemView.findViewById(R.id.tx_send_from_address);
        itemToAddress = itemView.findViewById(R.id.tx_send_to_address);

        itemSingleCoinLayer = itemView.findViewById(R.id.tx_send_single_coin_layer);
        itemAmount = itemView.findViewById(R.id.tx_transfer_amount);
        itemAmountDenom = itemView.findViewById(R.id.tx_transfer_amount_symbol);

        itemMultiCoinLayer = itemView.findViewById(R.id.tx_send_multi_coin_layer);
        itemAmountLayer0 = itemView.findViewById(R.id.tx_transfer_amount_layer0);
        itemAmountLayer1 = itemView.findViewById(R.id.tx_transfer_amount_layer1);
        itemAmountLayer2 = itemView.findViewById(R.id.tx_transfer_amount_layer2);
        itemAmountLayer3 = itemView.findViewById(R.id.tx_transfer_amount_layer3);
        itemAmountLayer4 = itemView.findViewById(R.id.tx_transfer_amount_layer4);
        itemAmount0 = itemView.findViewById(R.id.tx_transfer_amount0);
        itemAmount1 = itemView.findViewById(R.id.tx_transfer_amount1);
        itemAmount2 = itemView.findViewById(R.id.tx_transfer_amount2);
        itemAmount3 = itemView.findViewById(R.id.tx_transfer_amount3);
        itemAmount4 = itemView.findViewById(R.id.tx_transfer_amount4);
        itemAmountDenom0 = itemView.findViewById(R.id.tx_transfer_amount_symbol0);
        itemAmountDenom1 = itemView.findViewById(R.id.tx_transfer_amount_symbol1);
        itemAmountDenom2 = itemView.findViewById(R.id.tx_transfer_amount_symbol2);
        itemAmountDenom3 = itemView.findViewById(R.id.tx_transfer_amount_symbol3);
        itemAmountDenom4 = itemView.findViewById(R.id.tx_transfer_amount_symbol4);
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
            itemMultiCoinLayer.setVisibility(View.VISIBLE);
            itemAmountLayer0.setVisibility(View.VISIBLE);
            WDp.showCoinDp(c, toDpCoin.get(0), itemAmountDenom0, itemAmount0, baseChain);
            if (toDpCoin.size() > 1) {
                itemAmountLayer1.setVisibility(View.VISIBLE);
                WDp.showCoinDp(c, toDpCoin.get(1), itemAmountDenom1, itemAmount1, baseChain);
            }
            if (toDpCoin.size() > 2) {
                itemAmountLayer2.setVisibility(View.VISIBLE);
                WDp.showCoinDp(c, toDpCoin.get(2), itemAmountDenom2, itemAmount2, baseChain);
            }
            if (toDpCoin.size() > 3) {
                itemAmountLayer3.setVisibility(View.VISIBLE);
                WDp.showCoinDp(c, toDpCoin.get(3), itemAmountDenom1, itemAmount3, baseChain);
            }
            if (toDpCoin.size() > 4) {
                itemAmountLayer4.setVisibility(View.VISIBLE);
                WDp.showCoinDp(c, toDpCoin.get(4), itemAmountDenom4, itemAmount4, baseChain);
            }
        } catch (Exception e) {}
    }
}
