package wannabit.io.cosmostaion.widget.txDetail.kava;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cosmos.base.abci.v1beta1.Abci;
import cosmos.tx.v1beta1.ServiceOuterClass;
import kava.swap.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxKavaDepositPoolHolder extends TxHolder {
    TextView    itemDepositCoinSender,
                itemDepositTokenInAmount0, itemDepositTokenInSymbol0, itemDepositTokenInAmount1, itemDepositTokenInSymbol1;

    public TxKavaDepositPoolHolder(@NonNull View itemView) {
        super(itemView);
        itemDepositCoinSender       = itemView.findViewById(R.id.tx_join_pool_sender);
        itemDepositTokenInAmount0   = itemView.findViewById(R.id.tx_token_in_amount1);
        itemDepositTokenInSymbol0   = itemView.findViewById(R.id.tx_token_in_symbol1);
        itemDepositTokenInAmount1   = itemView.findViewById(R.id.tx_token_in_amount2);
        itemDepositTokenInSymbol1   = itemView.findViewById(R.id.tx_token_in_symbol2);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        try {
            Tx.MsgDeposit msg = Tx.MsgDeposit.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemDepositCoinSender.setText(msg.getDepositor());

            Coin inCoin0 = null;
            Coin inCoin1 = null;
            if (response.getTxResponse().getLogsCount() > position) {
                for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                    if (event.getType().equals("transfer")) {
                        String InValue0 = event.getAttributesList().get(2).getValue().split(",")[0];
                        String InValue1 = event.getAttributesList().get(2).getValue().split(",")[1];

                        Pattern p = Pattern.compile("([0-9])+");
                        Matcher m1 = p.matcher(InValue0);
                        if (m1.find()) {
                            String amount = m1.group();
                            String denom = InValue0.substring(m1.end());
                            inCoin0 = new Coin(denom, amount);
                        }

                        Matcher m2 = p.matcher(InValue1);
                        if (m2.find()) {
                            String amount = m2.group();
                            String denom = InValue1.substring(m2.end());
                            inCoin1 = new Coin(denom, amount);
                        }
                    }
                }
            }
            if (inCoin0 != null && inCoin1 != null) {
                WDp.setDpCoin(c, baseData, chainConfig, inCoin0, itemDepositTokenInSymbol0, itemDepositTokenInAmount0);
                WDp.setDpCoin(c, baseData, chainConfig, inCoin1, itemDepositTokenInSymbol1, itemDepositTokenInAmount1);
            } else {
                itemDepositTokenInAmount0.setText("");
                itemDepositTokenInSymbol0.setText("");
                itemDepositTokenInAmount1.setText("");
                itemDepositTokenInSymbol1.setText("");
            }
        } catch (Exception e) {
            WLog.w("Exception " + e.getMessage());
        }
    }
}
