package wannabit.io.cosmostaion.widget.txDetail.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
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

public class TxExitPoolHolder extends TxHolder {
    ImageView itemExitPoolImg;
    TextView itemExitSender, itemExitPoolId,
            itemExitPoolTokenOutSymbol1, itemExitPoolTokenOutAmount1, itemExitPoolTokenOutSymbol2, itemExitPoolTokenOutAmount2, itemExitPoolTokenInSymbol, itemExitPoolTokenInAmount;

    public TxExitPoolHolder(@NonNull View itemView) {
        super(itemView);
        itemExitPoolImg = itemView.findViewById(R.id.tx_exit_pool_icon);
        itemExitSender = itemView.findViewById(R.id.tx_exit_pool_sender);
        itemExitPoolId = itemView.findViewById(R.id.tx_exit_pool_id);
        itemExitPoolTokenOutSymbol1 = itemView.findViewById(R.id.tx_token_out_symbol1);
        itemExitPoolTokenOutAmount1 = itemView.findViewById(R.id.tx_token_out_amount1);
        itemExitPoolTokenOutSymbol2 = itemView.findViewById(R.id.tx_token_out_symbol2);
        itemExitPoolTokenOutAmount2 = itemView.findViewById(R.id.tx_token_out_amount2);
        itemExitPoolTokenInSymbol = itemView.findViewById(R.id.tx_exit_token_in_symbol);
        itemExitPoolTokenInAmount = itemView.findViewById(R.id.tx_exit_token_in_amount);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemExitPoolImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgExitPool msg = Tx.MsgExitPool.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemExitSender.setText(msg.getSender());
            itemExitPoolId.setText("" + msg.getPoolId());

            Coin coin0 = null;
            Coin coin1 = null;
            Coin coin2 = null;
            if (response.getTxResponse().getLogsCount() > position) {
                for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                    if (event.getType().equals("transfer")) {
                        String OutValue1 = event.getAttributesList().get(2).getValue().split(",")[0];
                        String OutValue2 = event.getAttributesList().get(2).getValue().split(",")[1];
                        String InValue = event.getAttributesList().get(5).getValue();
                        if (OutValue1.contains("ibc") && OutValue2.contains("ibc")) {
                            coin0 = new Coin(OutValue1.replaceAll(OutValue1.split("ibc")[0], ""), OutValue1.split("ibc")[0]);
                            coin1 = new Coin(OutValue2.replaceAll(OutValue2.split("ibc")[0], ""), OutValue2.split("ibc")[0]);
                        } else {
                            coin0 = new Coin(OutValue1.replaceAll(OutValue1.split("ibc")[0], ""), OutValue1.split("ibc")[0]);
                            coin1 = new Coin(OutValue2.replaceAll(OutValue2.replaceAll("[^0-9]", ""), ""), OutValue2.replaceAll("[^0-9]", ""));
                        }
                        coin2 = new Coin(InValue.replaceAll(InValue.split("gamm")[0], ""), InValue.split("gamm")[0]);
                    }
                }
            }
            WDp.showCoinDp(c, coin0, itemExitPoolTokenOutSymbol1, itemExitPoolTokenOutAmount1, baseChain);
            WDp.showCoinDp(c, coin1, itemExitPoolTokenOutSymbol2, itemExitPoolTokenOutAmount2, baseChain);
            WDp.showCoinDp(c, coin2, itemExitPoolTokenInSymbol, itemExitPoolTokenInAmount, baseChain);
        } catch (Exception e) { }
    }
}
