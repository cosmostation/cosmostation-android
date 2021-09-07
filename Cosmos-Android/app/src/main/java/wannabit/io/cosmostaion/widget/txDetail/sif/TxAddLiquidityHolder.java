package wannabit.io.cosmostaion.widget.txDetail.sif;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.base.abci.v1beta1.Abci;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.dao.IbcToken;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxAddLiquidityHolder extends TxHolder {
    ImageView   itemALImg;
    TextView    itemALSigner, itemALExternalSymbol,
                itemALNativeAssetAmount, itemALNativeAssetSymbol, itemALExternalAssetlAmount, itemALExternalAssetSymbol;

    public TxAddLiquidityHolder(@NonNull View itemView) {
        super(itemView);
        itemALImg                   = itemView.findViewById(R.id.tx_add_liquidity_icon);
        itemALSigner                = itemView.findViewById(R.id.tx_add_liquidity_signer);
        itemALExternalSymbol        = itemView.findViewById(R.id.tx_add_liquidity_external_asset);
        itemALNativeAssetAmount     = itemView.findViewById(R.id.tx_add_liquidity_native_amount);
        itemALNativeAssetSymbol     = itemView.findViewById(R.id.tx_add_liquidity_native_symbol);
        itemALExternalAssetlAmount  = itemView.findViewById(R.id.tx_add_liquidity_exteranl_amount);
        itemALExternalAssetSymbol   = itemView.findViewById(R.id.tx_add_liquidity_exteranl_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemALImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            sifnode.clp.v1.Tx.MsgAddLiquidity msg = sifnode.clp.v1.Tx.MsgAddLiquidity.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemALSigner.setText(msg.getSigner());
            if (msg.getExternalAsset().getSymbol().startsWith("ibc/")) {
                IbcToken ibcToken = baseData.getIbcToken(msg.getExternalAsset().getSymbol().replaceAll("ibc/", ""));
                if (ibcToken.auth == true) {
                    itemALExternalSymbol.setText(ibcToken.display_denom);
                } else {
                    itemALExternalSymbol.setText("Unknown");
                }
            } else {
                itemALExternalSymbol.setText(msg.getExternalAsset().getSymbol());
            }

            Coin nativeCoin = null;
            Coin externalCoin = null;
            if (response.getTxResponse().getLogsCount() > position) {
                for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                    if (event.getType().equals("transfer")) {
                        String value = event.getAttributesList().get(2).getValue();

                        String nativeAmount = "";
                        String externalAmount = "";
                        if (value.contains(",")) {
                            String[] assetValue = value.split(",");
                            if (assetValue[1] != null && assetValue[0].contains("ibc/")) {
                                externalAmount = assetValue[0].split("ibc")[0];
                            } else {
                                externalAmount = assetValue[0].replaceAll("[^0-9]", "");
                            }
                            nativeAmount = assetValue[1].replaceAll("[^0-9]", "");

                            nativeCoin= new Coin(assetValue[1].replaceAll(nativeAmount, ""), nativeAmount);
                            externalCoin = new Coin(assetValue[0].replaceAll(externalAmount, ""), externalAmount);

                        } else {
                            nativeAmount = value.replaceAll("[^0-9]", "");

                            nativeCoin = new Coin(value.replaceAll(nativeAmount, ""), nativeAmount);
                        }
                    }
                }
            }
            if (externalCoin != null) {
                WDp.showCoinDp(c, nativeCoin, itemALNativeAssetSymbol, itemALNativeAssetAmount, baseChain);
                WDp.showCoinDp(c, externalCoin, itemALExternalAssetSymbol, itemALExternalAssetlAmount, baseChain);
            } else {
                WDp.showCoinDp(c, nativeCoin, itemALNativeAssetSymbol, itemALNativeAssetAmount, baseChain);
                itemALExternalAssetlAmount.setText("");
                itemALExternalAssetSymbol.setText("0");
            }
        } catch (Exception e) {
            WLog.w("Exception " + e.getMessage());
        }
    }
}
