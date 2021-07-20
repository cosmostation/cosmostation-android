package wannabit.io.cosmostaion.widget.txDetail.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

import cosmos.base.abci.v1beta1.Abci;
import cosmos.tx.v1beta1.ServiceOuterClass;
import osmosis.gamm.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxJoinPoolHolder extends TxHolder {
    ImageView itemJoinPoolImg;
    TextView itemJoinSender, itemJoinPoolId,
            itemJoinPoolTokenInSymbol1, itemJoinPoolTokenInAmount1, itemJoinPoolTokenInSymbol2, itemJoinPoolTokenInAmount2, itemJoinPoolTokenOutSymbol, itemJoinPoolTokenOutAmount;

    public TxJoinPoolHolder(@NonNull View itemView) {
        super(itemView);
        itemJoinPoolImg = itemView.findViewById(R.id.tx_join_pool_icon);
        itemJoinSender = itemView.findViewById(R.id.tx_join_pool_sender);
        itemJoinPoolId = itemView.findViewById(R.id.tx_join_pool_id);
        itemJoinPoolTokenInSymbol1 = itemView.findViewById(R.id.tx_token_in_symbol1);
        itemJoinPoolTokenInAmount1 = itemView.findViewById(R.id.tx_token_in_amount1);
        itemJoinPoolTokenInSymbol2 = itemView.findViewById(R.id.tx_token_in_symbol2);
        itemJoinPoolTokenInAmount2 = itemView.findViewById(R.id.tx_token_in_amount2);
        itemJoinPoolTokenOutSymbol = itemView.findViewById(R.id.tx_join_token_out_symbol);
        itemJoinPoolTokenOutAmount = itemView.findViewById(R.id.tx_join_token_out_amount);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemJoinPoolImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgJoinPool msg = Tx.MsgJoinPool.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemJoinSender.setText(msg.getSender());
            itemJoinPoolId.setText("" + msg.getPoolId());

            Coin coin0 = null;
            Coin coin1 = null;
            Coin coin2 = null;
            if (response.getTxResponse().getLogsCount() > position) {
                for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                    if (event.getType().equals("transfer")) {
                        String InValue1 = event.getAttributesList().get(2).getValue().split(",")[0];
                        String InValue2 = event.getAttributesList().get(2).getValue().split(",")[1];
                        String OutValue = event.getAttributesList().get(5).getValue();
                        if (InValue1.contains("ibc") && InValue2.contains("ibc")) {
                            coin0 = new Coin(InValue1.replaceAll(InValue1.split("ibc")[0], ""), InValue1.split("ibc")[0]);
                            coin1 = new Coin(InValue2.replaceAll(InValue2.split("ibc")[0], ""), InValue2.split("ibc")[0]);
                        } else {
                            coin0 = new Coin(InValue1.replaceAll(InValue1.split("ibc")[0], ""), InValue1.split("ibc")[0]);
                            coin1 = new Coin(InValue2.replaceAll(InValue2.replaceAll("[^0-9]", ""), ""), InValue2.replaceAll("[^0-9]", ""));
                        }
                        coin2 = new Coin(OutValue.replaceAll(OutValue.split("gamm")[0], ""), OutValue.split("gamm")[0]);
                    }
                }
            }
            if (coin0 != null && coin1 != null) {
                WDp.showCoinDp(c, coin0, itemJoinPoolTokenInSymbol1, itemJoinPoolTokenInAmount1, baseChain);
                WDp.showCoinDp(c, coin1, itemJoinPoolTokenInSymbol2, itemJoinPoolTokenInAmount2, baseChain);
            } else {
                itemJoinPoolTokenInAmount1.setText("");
                itemJoinPoolTokenInSymbol1.setText("");
                itemJoinPoolTokenInAmount2.setText("");
                itemJoinPoolTokenInSymbol2.setText("");
            }
            if (coin2 != null) {
                WDp.showCoinDp(c, coin2, itemJoinPoolTokenOutSymbol, itemJoinPoolTokenOutAmount, baseChain);
            } else {
                itemJoinPoolTokenOutAmount.setText("");
                itemJoinPoolTokenOutSymbol.setText("");
            }
        } catch (Exception e) { }
    }
}
