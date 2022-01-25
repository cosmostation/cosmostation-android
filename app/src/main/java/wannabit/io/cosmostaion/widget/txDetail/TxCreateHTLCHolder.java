package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import kava.bep3.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class TxCreateHTLCHolder extends TxHolder {
    ImageView       itemMsgImg;
    LinearLayout    itemExpectedLayer, itemStatusLayer;
    TextView        itemSendAmount, itemSendDenom, itemSender, itemRecipient, itemRandomHash, itemExpectIncome;

    public TxCreateHTLCHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemExpectedLayer = itemView.findViewById(R.id.expected_layer);
        itemSendAmount = itemView.findViewById(R.id.send_amount);
        itemSendDenom = itemView.findViewById(R.id.send_amount_denom);
        itemSender = itemView.findViewById(R.id.sender_addr);
        itemRecipient = itemView.findViewById(R.id.recipient_addr);
        itemRandomHash = itemView.findViewById(R.id.random_hash);
        itemExpectIncome = itemView.findViewById(R.id.expected_income);
        itemStatusLayer = itemView.findViewById(R.id.status_layer);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemMsgImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgCreateAtomicSwap msg = Tx.MsgCreateAtomicSwap.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemSender.setText(msg.getFrom());
            itemRecipient.setText(msg.getTo());
            itemRandomHash.setText(msg.getRandomNumberHash());
            itemStatusLayer.setVisibility(View.GONE);
            itemExpectedLayer.setVisibility(View.GONE);
            WDp.showCoinDp(c, baseData, msg.getAmount(0).getDenom() ,msg.getAmount(0).getAmount(), itemSendDenom, itemSendAmount, baseChain);

        } catch (Exception e) { }
    }
}

