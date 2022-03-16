package wannabit.io.cosmostaion.widget.txDetail.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

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

public class TxTokenSwapHolder extends TxHolder {
    ImageView itemSwapCoinImg;
    TextView itemSwapCoinType, itemSwapCoinSender, itemSwapCoinPoolId,
            itemSwapTokenInAmount, itemSwapTokenInAmountSymbol, itemSwapTokenOutAmount, itemSwapTokenOutAmountSymbol;

    public TxTokenSwapHolder(@NonNull View itemView) {
        super(itemView);
        itemSwapCoinImg = itemView.findViewById(R.id.tx_swap_coin_icon);
        itemSwapCoinType = itemView.findViewById(R.id.tx_swap_coin_type);
        itemSwapCoinSender = itemView.findViewById(R.id.tx_swap_coin_sender);
        itemSwapCoinPoolId = itemView.findViewById(R.id.tx_swap_coin_pool_id);
        itemSwapTokenInAmount = itemView.findViewById(R.id.tx_swap_token_in_amount);
        itemSwapTokenInAmountSymbol = itemView.findViewById(R.id.tx_swap_token_in_amount_symbol);
        itemSwapTokenOutAmount = itemView.findViewById(R.id.tx_swap_token_out_amount);
        itemSwapTokenOutAmountSymbol = itemView.findViewById(R.id.tx_swap_token_out_amount_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemSwapCoinImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        if (response.getTx().getBody().getMessages(position).getTypeUrl().contains("MsgSwapExactAmountIn")) {
            try {
                Tx.MsgSwapExactAmountIn msg = Tx.MsgSwapExactAmountIn.parseFrom(response.getTx().getBody().getMessages(position).getValue());
                String[] Type = response.getTx().getBody().getMessages(position).getTypeUrl().split("\\.");
                itemSwapCoinType.setText(Type[Type.length - 1]);

                itemSwapCoinSender.setText(msg.getSender());
                itemSwapCoinPoolId.setText("" + msg.getRoutes(0).getPoolId());

                Coin inCoin = null;
                if (response.getTxResponse().getLogsCount() > position) {
                    for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                        if (event.getType().equals("transfer")) {
                            if (event.getAttributesCount() >= 6) {
                                String value = event.getAttributes(2).getValue();
                                Pattern p = Pattern.compile("([0-9])+");
                                Matcher m1 = p.matcher(value);
                                if (m1.find()) {
                                    String amount = m1.group();
                                    String denom = value.substring(m1.end());
                                    inCoin = new Coin(denom, amount);
                                }
                            }
                        }
                    }
                }
                if (inCoin != null) {
                    WDp.showCoinDp(c, baseData, inCoin, itemSwapTokenInAmountSymbol, itemSwapTokenInAmount, baseChain );
                } else {
                    itemSwapTokenInAmount.setText("");
                    itemSwapTokenInAmountSymbol.setText("");
                }

                Coin outCoin = null;
                if (response.getTxResponse().getLogsCount() > position) {
                    for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                        if (event.getType().equals("transfer")) {
                            if (event.getAttributesCount() >= 6) {
                                String value = event.getAttributes(event.getAttributesCount() - 1).getValue();
                                Pattern p = Pattern.compile("([0-9])+");
                                Matcher m1 = p.matcher(value);
                                if (m1.find()) {
                                    String amount = m1.group();
                                    String denom = value.substring(m1.end());
                                    outCoin = new Coin(denom, amount);
                                }
                            }
                        }
                    }
                }
                if (outCoin != null) {
                    WDp.showCoinDp(c, baseData, outCoin, itemSwapTokenOutAmountSymbol, itemSwapTokenOutAmount, baseChain);
                } else {
                    itemSwapTokenOutAmount.setText("");
                    itemSwapTokenOutAmountSymbol.setText("");
                }
                return;
            } catch (Exception e) { }

        } else {
            try {
                Tx.MsgSwapExactAmountOut msg = Tx.MsgSwapExactAmountOut.parseFrom(response.getTx().getBody().getMessages(position).getValue());
                String[] Type = response.getTx().getBody().getMessages(position).getTypeUrl().split("\\.");
                itemSwapCoinType.setText(Type[Type.length - 1]);

                itemSwapCoinSender.setText(msg.getSender());
                itemSwapCoinPoolId.setText("" + msg.getRoutes(0).getPoolId());

                Coin inCoin = null;
                if (response.getTxResponse().getLogsCount() > position) {
                    for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                        if (event.getType().equals("transfer")) {
                            if (event.getAttributesCount() >= 6) {
                                String value = event.getAttributes(2).getValue();
                                Pattern p = Pattern.compile("([0-9])+");
                                Matcher m1 = p.matcher(value);
                                if (m1.find()) {
                                    String amount = m1.group();
                                    String denom = value.substring(m1.end());
                                    inCoin = new Coin(denom, amount);
                                }
                            }
                        }
                    }
                }
                if (inCoin != null) {
                    WDp.showCoinDp(c, baseData, inCoin, itemSwapTokenInAmountSymbol, itemSwapTokenInAmount, baseChain);
                } else {
                    itemSwapTokenInAmount.setText("");
                    itemSwapTokenInAmountSymbol.setText("");
                }

                Coin outCoin = null;
                if (response.getTxResponse().getLogsCount() > position) {
                    for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                        if (event.getType().equals("transfer")) {
                            if (event.getAttributesCount() >= 6) {
                                String value = event.getAttributes(event.getAttributesCount() - 1).getValue();
                                Pattern p = Pattern.compile("([0-9])+");
                                Matcher m1 = p.matcher(value);
                                if (m1.find()) {
                                    String amount = m1.group();
                                    String denom = value.substring(m1.end());
                                    outCoin = new Coin(denom, amount);
                                }
                            }
                        }
                    }
                }
                if (outCoin != null) {
                    WDp.showCoinDp(c, baseData, outCoin, itemSwapTokenOutAmountSymbol, itemSwapTokenOutAmount, baseChain);
                } else {
                    itemSwapTokenOutAmount.setText("");
                    itemSwapTokenOutAmountSymbol.setText("");
                }
                return;
            } catch (Exception e) { }
        }
    }
}
