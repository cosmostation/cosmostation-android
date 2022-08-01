package wannabit.io.cosmostaion.widget.txDetail.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cosmos.base.abci.v1beta1.Abci;
import cosmos.tx.v1beta1.ServiceOuterClass;
import osmosis.gamm.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxExitPoolHolder extends TxHolder {
    TextView itemExitSender, itemExitPoolId,
            itemExitPoolTokenOutSymbol1, itemExitPoolTokenOutAmount1, itemExitPoolTokenOutSymbol2, itemExitPoolTokenOutAmount2, itemExitPoolTokenInSymbol, itemExitPoolTokenInAmount;

    public TxExitPoolHolder(@NonNull View itemView) {
        super(itemView);
        itemExitSender = itemView.findViewById(R.id.tx_exit_pool_sender);
        itemExitPoolId = itemView.findViewById(R.id.tx_exit_pool_id);
        itemExitPoolTokenOutSymbol1 = itemView.findViewById(R.id.tx_token_out_symbol1);
        itemExitPoolTokenOutAmount1 = itemView.findViewById(R.id.tx_token_out_amount1);
        itemExitPoolTokenOutSymbol2 = itemView.findViewById(R.id.tx_token_out_symbol2);
        itemExitPoolTokenOutAmount2 = itemView.findViewById(R.id.tx_token_out_amount2);
        itemExitPoolTokenInSymbol = itemView.findViewById(R.id.tx_exit_token_in_symbol);
        itemExitPoolTokenInAmount = itemView.findViewById(R.id.tx_exit_token_in_amount);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        try {
            Tx.MsgExitPool msg = Tx.MsgExitPool.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemExitSender.setText(msg.getSender());
            itemExitPoolId.setText("" + msg.getPoolId());

            Coin outCoin0 = null;
            Coin outCoin1 = null;
            Coin inCoin = null;
            if (response.getTxResponse().getLogsCount() > position) {
                for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                    if (event.getType().equals("transfer")) {
                        String OutValue0 = event.getAttributesList().get(2).getValue().split(",")[0];
                        String OutValue1 = event.getAttributesList().get(2).getValue().split(",")[1];
                        String InValue = event.getAttributesList().get(5).getValue();

                        Pattern p = Pattern.compile("([0-9])+");
                        Matcher m1 = p.matcher(OutValue0);
                        if (m1.find()) {
                            String amount = m1.group();
                            String denom = OutValue0.substring(m1.end());
                            outCoin0 = new Coin(denom, amount);
                        }

                        Matcher m2 = p.matcher(OutValue1);
                        if (m2.find()) {
                            String amount = m2.group();
                            String denom = OutValue1.replaceAll(m2.group(), "");
                            outCoin1 = new Coin(denom, amount);
                        }

                        inCoin = new Coin(InValue.replaceAll(InValue.split("gamm")[0], ""), InValue.split("gamm")[0]);
                    }
                }
            }
            if (outCoin0 != null && outCoin1 != null) {
                WDp.setDpCoin(c, baseData, chainConfig, outCoin0, itemExitPoolTokenOutSymbol1, itemExitPoolTokenOutAmount1);
                WDp.setDpCoin(c, baseData, chainConfig, outCoin1, itemExitPoolTokenOutSymbol2, itemExitPoolTokenOutAmount2);
            } else {
                itemExitPoolTokenOutAmount1.setText("");
                itemExitPoolTokenOutSymbol1.setText("");
                itemExitPoolTokenOutAmount2.setText("");
                itemExitPoolTokenOutSymbol2.setText("");
            }
            if (inCoin != null) {
                WDp.setDpCoin(c, baseData, chainConfig, inCoin, itemExitPoolTokenInSymbol, itemExitPoolTokenInAmount);
            } else {
                itemExitPoolTokenInAmount.setText("");
                itemExitPoolTokenInSymbol.setText("");
            }
        } catch (Exception e) { }
    }
}
