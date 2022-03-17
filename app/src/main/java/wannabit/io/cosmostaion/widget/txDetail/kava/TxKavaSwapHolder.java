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
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxKavaSwapHolder extends TxHolder {
    ImageView   itemSwapCoinImg;
    TextView    itemSwapCoinType, itemSwapCoinSender,
                itemSwapTokenInAmount, itemSwapTokenInAmountSymbol, itemSwapTokenOutAmount, itemSwapTokenOutAmountSymbol;

    public TxKavaSwapHolder(@NonNull View itemView) {
        super(itemView);
        itemSwapCoinImg = itemView.findViewById(R.id.tx_swap_coin_icon);
        itemSwapCoinType = itemView.findViewById(R.id.tx_swap_coin_type);
        itemSwapCoinSender = itemView.findViewById(R.id.tx_swap_coin_sender);
        itemSwapTokenInAmount = itemView.findViewById(R.id.tx_swap_token_in_amount);
        itemSwapTokenInAmountSymbol = itemView.findViewById(R.id.tx_swap_token_in_amount_symbol);
        itemSwapTokenOutAmount = itemView.findViewById(R.id.tx_swap_token_out_amount);
        itemSwapTokenOutAmountSymbol = itemView.findViewById(R.id.tx_swap_token_out_amount_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemSwapCoinImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        if (response.getTx().getBody().getMessages(position).getTypeUrl().contains("MsgSwapExactForTokens")) {
            try {
                Tx.MsgSwapExactForTokens msg = Tx.MsgSwapExactForTokens.parseFrom(response.getTx().getBody().getMessages(position).getValue());
                itemSwapCoinType.setText("SwapExactForTokens");
                itemSwapCoinSender.setText(msg.getRequester());

                Coin inCoin = null;
                if (response.getTxResponse().getLogsCount() > position) {
                    for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                        if (event.getType().equals("transfer")) {
                            if (event.getAttributesCount() >= 6) {
                                String value = event.getAttributes(2).getValue();
                                Pattern p = Pattern.compile("([0-9])+");
                                Matcher m = p.matcher(value);
                                if (m.find()) {
                                    String amount = m.group();
                                    String denom = value.substring(m.end());
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
                                Matcher m = p.matcher(value);
                                if (m.find()) {
                                    String amount = m.group();
                                    String denom = value.substring(m.end());
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
                Tx.MsgSwapForExactTokens msg = Tx.MsgSwapForExactTokens.parseFrom(response.getTx().getBody().getMessages(position).getValue());
                itemSwapCoinType.setText("SwapForExactTokens");
                itemSwapCoinSender.setText(msg.getRequester());

                Coin inCoin = null;
                if (response.getTxResponse().getLogsCount() > position) {
                    for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                        if (event.getType().equals("transfer")) {
                            if (event.getAttributesCount() >= 6) {
                                String value = event.getAttributes(2).getValue();
                                Pattern p = Pattern.compile("([0-9])+");
                                Matcher m = p.matcher(value);
                                if (m.find()) {
                                    String amount = m.group();
                                    String denom = value.substring(m.end());
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
                                Matcher m = p.matcher(value);
                                if (m.find()) {
                                    String amount = m.group();
                                    String denom = value.substring(m.end());
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
