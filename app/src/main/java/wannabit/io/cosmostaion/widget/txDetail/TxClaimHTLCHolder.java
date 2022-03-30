package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cosmos.base.abci.v1beta1.Abci;
import cosmos.tx.v1beta1.ServiceOuterClass;
import kava.bep3.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class TxClaimHTLCHolder extends TxHolder {
    ImageView itemMsgImg;
    RelativeLayout itemAmountLayer;
    TextView itemReceiveAmount, itemReceiveDenom, itemClaimer, itemRandomNumber, itemSwapId;

    public TxClaimHTLCHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemAmountLayer = itemView.findViewById(R.id.claim_amount_layer);
        itemReceiveAmount = itemView.findViewById(R.id.claim_amount);
        itemReceiveDenom = itemView.findViewById(R.id.claim_amount_denom);
        itemClaimer = itemView.findViewById(R.id.claimer_addr);
        itemRandomNumber = itemView.findViewById(R.id.claim_random_number);
        itemSwapId = itemView.findViewById(R.id.claim_swap_id);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemMsgImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgClaimAtomicSwap msg = Tx.MsgClaimAtomicSwap.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            Coin receiveCoin = null;
            if (response.getTxResponse().getLogsCount() > position) {
                for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                    if (event.getType().equals("transfer")) {
                        String receiveValue = event.getAttributesList().get(2).getValue();

                        Pattern p = Pattern.compile("([0-9])+");
                        Matcher m1 = p.matcher(receiveValue);
                        if (m1.find()) {
                            String amount = m1.group();
                            String denom = receiveValue.substring(m1.end());
                            receiveCoin = new Coin(denom, amount);
                        }
                    }
                }
            }
            if (receiveCoin != null) {
                WDp.showCoinDp(c, baseData, receiveCoin, itemReceiveDenom, itemReceiveAmount, baseChain);
            } else {
                itemReceiveAmount.setText("");
                itemReceiveDenom.setText("");
            }
            itemClaimer.setText(msg.getFrom());
            itemRandomNumber.setText(msg.getRandomNumber());
            itemSwapId.setText(msg.getSwapId());

        } catch (Exception e) {
        }
    }
}
