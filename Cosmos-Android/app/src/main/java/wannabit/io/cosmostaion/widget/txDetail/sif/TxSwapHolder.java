package wannabit.io.cosmostaion.widget.txDetail.sif;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.base.abci.v1beta1.Abci;
import cosmos.tx.v1beta1.ServiceOuterClass;
import sifnode.clp.v1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxSwapHolder extends TxHolder {
    ImageView   itemSwapImg;
    TextView    itemSwapSigner,
                itemSwapSendAssetAmount, itemSwapSendAssetSymbol, itemSwapReceiveAssetlAmount, itemSwapReceiveAssetSymbol;

    public TxSwapHolder(@NonNull View itemView) {
        super(itemView);
        itemSwapImg                  = itemView.findViewById(R.id.tx_swap_icon);
        itemSwapSigner               = itemView.findViewById(R.id.tx_swap_signer);
        itemSwapSendAssetAmount      = itemView.findViewById(R.id.tx_swap_send_asset_amount);
        itemSwapSendAssetSymbol      = itemView.findViewById(R.id.tx_swap_send_asset_symbol);
        itemSwapReceiveAssetlAmount  = itemView.findViewById(R.id.tx_swap_received_asset_amount);
        itemSwapReceiveAssetSymbol   = itemView.findViewById(R.id.tx_swap_received_asset_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemSwapImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

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

                        String sendAmount = "";
                        if (sendValue.contains("ibc")) {
                            sendAmount = sendValue.split("ibc")[0];
                        } else {
                            sendAmount = sendValue.replaceAll("[^0-9]", "");
                        }
                        sendCoin = new Coin(sendValue.replaceAll(sendAmount, ""), sendAmount);

                        String receiveAmount = "";
                        if (receiveValue.contains("ibc")) {
                            receiveAmount = receiveValue.split("ibc")[0];
                        } else {
                            receiveAmount = receiveValue.replaceAll("[^0-9]", "");
                        }
                        receiveCoin = new Coin(receiveValue.replaceAll(receiveAmount, ""), receiveAmount);
                    }
                }
            }
            if (sendCoin != null && receiveCoin != null) {
                WDp.showCoinDp(c, sendCoin, itemSwapSendAssetSymbol, itemSwapSendAssetAmount, baseChain);
                WDp.showCoinDp(c, receiveCoin, itemSwapReceiveAssetSymbol, itemSwapReceiveAssetlAmount, baseChain);
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
