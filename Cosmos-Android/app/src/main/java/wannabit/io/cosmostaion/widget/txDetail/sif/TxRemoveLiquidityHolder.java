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

public class TxRemoveLiquidityHolder extends TxHolder {
    ImageView   itemRLImg;
    TextView    itemRLSigner, itemRLExternalAssetlAmount, itemRLExternalAssetSymbol;

    public TxRemoveLiquidityHolder(@NonNull View itemView) {
        super(itemView);
        itemRLImg                   = itemView.findViewById(R.id.tx_remove_liquidity_icon);
        itemRLSigner                = itemView.findViewById(R.id.tx_remove_liquidity_signer);
        itemRLExternalAssetlAmount  = itemView.findViewById(R.id.tx_remove_liquidity_amount);
        itemRLExternalAssetSymbol   = itemView.findViewById(R.id.tx_remove_liquidity_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemRLImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgRemoveLiquidity msg = Tx.MsgRemoveLiquidity.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemRLSigner.setText(msg.getSigner());

            Coin coin = null;
            if (response.getTxResponse().getLogsCount() > position) {
                for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                    if (event.getType().equals("transfer")) {
                        String value = event.getAttributesList().get(2).getValue();

                        String Amount = "";
                        if (value.contains("ibc/")) {
                            Amount = value.split("ibc/")[0];
                        } else {
                            Amount = value.replaceAll("[^0-9]", "");
                        }
                        coin = new Coin(value.replaceAll(Amount, ""), Amount);
                    }
                }
            }
            if (coin != null) {
                WDp.showCoinDp(c, coin, itemRLExternalAssetSymbol, itemRLExternalAssetlAmount, baseChain);
            } else {
                itemRLExternalAssetlAmount.setText("");
                itemRLExternalAssetSymbol.setText("0");
            }
        } catch (Exception e) {
            WLog.w("Exception " + e.getMessage());
        }
    }
}
