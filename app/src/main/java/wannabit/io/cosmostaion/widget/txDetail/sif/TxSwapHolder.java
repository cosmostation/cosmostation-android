package wannabit.io.cosmostaion.widget.txDetail.sif;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import cosmos.base.abci.v1beta1.Abci;
import cosmos.tx.v1beta1.ServiceOuterClass;
import sifnode.clp.v1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxSwapHolder extends TxHolder {
    TextView    itemSwapSigner,
                itemSwapSendAssetAmount, itemSwapSendAssetSymbol, itemSwapReceiveAssetlAmount, itemSwapReceiveAssetSymbol;

    public TxSwapHolder(@NonNull View itemView) {
        super(itemView);
        itemSwapSigner               = itemView.findViewById(R.id.tx_swap_signer);
        itemSwapSendAssetAmount      = itemView.findViewById(R.id.tx_swap_send_asset_amount);
        itemSwapSendAssetSymbol      = itemView.findViewById(R.id.tx_swap_send_asset_symbol);
        itemSwapReceiveAssetlAmount  = itemView.findViewById(R.id.tx_swap_received_asset_amount);
        itemSwapReceiveAssetSymbol   = itemView.findViewById(R.id.tx_swap_received_asset_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        try {
            Tx.MsgSwap msg = sifnode.clp.v1.Tx.MsgSwap.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemSwapSigner.setText(msg.getSigner());

            Coin sendCoin = null;
            Coin receiveCoin = null;
            if (response.getTxResponse().getLogsCount() > position) {
                for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                    if (event.getType().equals("transfer")) {
                        String sendValue = event.getAttributesList().get(2).getValue();
                        String receiveValue = event.getAttributesList().get(5).getValue();

                        Pattern p = Pattern.compile("([0-9])+");
                        Matcher m1 = p.matcher(sendValue);
                        if (m1.find()) {
                            String amount = m1.group();
                            String denom = sendValue.substring(m1.end());
                            sendCoin = new Coin(denom, amount);
                        }

                        Matcher m2 = p.matcher(receiveValue);
                        if (m2.find()) {
                            String amount = m2.group();
                            String denom = receiveValue.substring(m2.end());
                            receiveCoin = new Coin(denom, amount);
                        }
                    }
                }
            }
            if (sendCoin != null && receiveCoin != null) {
                WDp.setDpCoin(c, baseData, chainConfig, sendCoin, itemSwapSendAssetSymbol, itemSwapSendAssetAmount);
                WDp.setDpCoin(c, baseData, chainConfig, receiveCoin, itemSwapReceiveAssetSymbol, itemSwapReceiveAssetlAmount);
            } else {
                itemSwapSendAssetAmount.setText("");
                itemSwapSendAssetSymbol.setText("");
                itemSwapReceiveAssetlAmount.setText("");
                itemSwapReceiveAssetSymbol.setText("");
            }
        } catch (Exception e) {
            WLog.w("Exception " + e.getMessage());
        }
    }
}
