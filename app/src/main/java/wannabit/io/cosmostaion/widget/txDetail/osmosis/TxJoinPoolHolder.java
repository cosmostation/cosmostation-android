package wannabit.io.cosmostaion.widget.txDetail.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

            Coin inCoin0 = null;
            Coin inCoin1 = null;
            Coin outCoin = null;
            if (response.getTxResponse().getLogsCount() > position) {
                for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                    if (event.getType().equals("transfer")) {
                        String InValue0 = event.getAttributesList().get(2).getValue().split(",")[0];
                        String InValue1 = event.getAttributesList().get(2).getValue().split(",")[1];
                        String OutValue = event.getAttributesList().get(5).getValue();

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

                        outCoin = new Coin(OutValue.replaceAll(OutValue.split("gamm")[0], ""), OutValue.split("gamm")[0]);
                    }
                }
            }
            if (inCoin0 != null && inCoin1 != null) {
                WDp.showCoinDp(c, baseData, inCoin0, itemJoinPoolTokenInSymbol1, itemJoinPoolTokenInAmount1, baseChain);
                WDp.showCoinDp(c, baseData, inCoin1, itemJoinPoolTokenInSymbol2, itemJoinPoolTokenInAmount2, baseChain);
            } else {
                itemJoinPoolTokenInAmount1.setText("");
                itemJoinPoolTokenInSymbol1.setText("");
                itemJoinPoolTokenInAmount2.setText("");
                itemJoinPoolTokenInSymbol2.setText("");
            }
            if (outCoin != null) {
                WDp.showCoinDp(c, baseData, outCoin, itemJoinPoolTokenOutSymbol, itemJoinPoolTokenOutAmount, baseChain);
            } else {
                itemJoinPoolTokenOutAmount.setText("");
                itemJoinPoolTokenOutSymbol.setText("");
            }
        } catch (Exception e) {
            WLog.w("Exception " + e.getMessage());
        }
    }
}
