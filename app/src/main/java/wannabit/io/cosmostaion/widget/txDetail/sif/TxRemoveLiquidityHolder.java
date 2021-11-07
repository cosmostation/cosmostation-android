package wannabit.io.cosmostaion.widget.txDetail.sif;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

import cosmos.base.abci.v1beta1.Abci;
import cosmos.tx.v1beta1.ServiceOuterClass;
import sifnode.clp.v1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxRemoveLiquidityHolder extends TxHolder {
    ImageView   itemRLImg;
    TextView    itemRLSigner,
                itemRLNativeAssetAmount, itemRLNativeAssetSymbol, itemRLExternalAssetlAmount, itemRLExternalAssetSymbol;

    public TxRemoveLiquidityHolder(@NonNull View itemView) {
        super(itemView);
        itemRLImg                   = itemView.findViewById(R.id.tx_remove_liquidity_icon);
        itemRLSigner                = itemView.findViewById(R.id.tx_remove_liquidity_signer);
        itemRLNativeAssetAmount     = itemView.findViewById(R.id.tx_liquidity_native_amount);
        itemRLNativeAssetSymbol     = itemView.findViewById(R.id.tx_liquidity_native_symbol);
        itemRLExternalAssetlAmount  = itemView.findViewById(R.id.tx_liquidity_exteranl_amount);
        itemRLExternalAssetSymbol   = itemView.findViewById(R.id.tx_liquidity_exteranl_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemRLImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgRemoveLiquidity msg = Tx.MsgRemoveLiquidity.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemRLSigner.setText(msg.getSigner());

            Coin coin0 = null;
            Coin coin1 = null;
            if (response.getTxResponse().getLogsCount() > position) {
                for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                    if (event.getType().equals("transfer")) {
                        String value = event.getAttributesList().get(2).getValue();

                        String Amount0 = "";
                        String Amount1 = "";
                        if (value.contains(",")) {
                            String[] assetValue = value.split(",");
                            if (assetValue[1] != null && assetValue[0].contains("ibc/")) {
                                Amount0 = assetValue[0].split("ibc")[0];
                            } else {
                                Amount0 = assetValue[0].split("c")[0];
                            }
                            Amount1 = assetValue[1].replaceAll("[^0-9]", "");

                            coin0= new Coin(assetValue[0].replaceAll(Amount0, ""), Amount0);
                            coin1 = new Coin(assetValue[1].replaceAll(Amount1, ""), Amount1);

                        } else {
                            if (value.contains("ibc/")) {
                                Amount0 = value.split("ibc")[0];
                                Amount1 = BigDecimal.ZERO.toPlainString();
                            } else if (value.contains("rowan")){
                                Amount0 = BigDecimal.ZERO.toPlainString();
                                Amount1 = value.replaceAll("[^0-9]", "");
                            } else {
                                Amount0 = value.replaceAll("[^0-9]", "");
                                Amount1 = BigDecimal.ZERO.toPlainString();
                            }

                            coin0 = new Coin(msg.getExternalAsset().getSymbol(), Amount0);
                            coin1 = new Coin(BaseConstant.TOKEN_SIF, Amount1);
                        }
                    }
                }
            }
            WDp.showCoinDp(c, baseData, coin1, itemRLNativeAssetSymbol, itemRLNativeAssetAmount, baseChain);
            WDp.showCoinDp(c, baseData, coin0, itemRLExternalAssetSymbol, itemRLExternalAssetlAmount, baseChain);

        } catch (Exception e) {
            WLog.w("Exception " + e.getMessage());
        }
    }
}
