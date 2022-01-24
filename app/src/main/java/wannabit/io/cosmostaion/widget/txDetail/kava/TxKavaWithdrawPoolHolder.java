package wannabit.io.cosmostaion.widget.txDetail.kava;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cosmos.base.abci.v1beta1.Abci;
import cosmos.tx.v1beta1.ServiceOuterClass;
import kava.swap.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxKavaWithdrawPoolHolder extends TxHolder {
    ImageView   itemWithdrawCoinImg;
    TextView    itemWithdrawCoinSender,
                itemWithdrawTokenInAmount0, itemWithdrawTokenInSymbol0, itemWithdrawTokenInAmount1, itemWithdrawTokenInSymbol1;

    public TxKavaWithdrawPoolHolder(@NonNull View itemView) {
        super(itemView);
        itemWithdrawCoinImg          = itemView.findViewById(R.id.tx_exit_pool_icon);
        itemWithdrawCoinSender       = itemView.findViewById(R.id.tx_exit_pool_sender);
        itemWithdrawTokenInAmount0   = itemView.findViewById(R.id.tx_token_out_amount1);
        itemWithdrawTokenInSymbol0   = itemView.findViewById(R.id.tx_token_out_symbol1);
        itemWithdrawTokenInAmount1   = itemView.findViewById(R.id.tx_token_out_amount2);
        itemWithdrawTokenInSymbol1   = itemView.findViewById(R.id.tx_token_out_symbol2);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemWithdrawCoinImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgWithdraw msg = Tx.MsgWithdraw.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemWithdrawCoinSender.setText(msg.getFrom());

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
                            String denom = InValue0.replaceAll(m1.group(), "");
                            inCoin0 = new Coin(denom, amount);
                        }

                        Matcher m2 = p.matcher(InValue1);
                        if (m2.find()) {
                            String amount = m2.group();
                            String denom = InValue1.replaceAll(m2.group(), "");
                            inCoin1 = new Coin(denom, amount);
                        }
                    }
                }
            }
            if (inCoin0 != null && inCoin1 != null) {
                WDp.showCoinDp(c, baseData, inCoin0, itemWithdrawTokenInSymbol0, itemWithdrawTokenInAmount0, baseChain);
                WDp.showCoinDp(c, baseData, inCoin1, itemWithdrawTokenInSymbol1, itemWithdrawTokenInAmount1, baseChain);
            } else {
                itemWithdrawTokenInAmount0.setText("");
                itemWithdrawTokenInSymbol0.setText("");
                itemWithdrawTokenInAmount1.setText("");
                itemWithdrawTokenInSymbol1.setText("");
            }
        } catch (Exception e) {
            WLog.w("Exception " + e.getMessage());
        }
    }
}
