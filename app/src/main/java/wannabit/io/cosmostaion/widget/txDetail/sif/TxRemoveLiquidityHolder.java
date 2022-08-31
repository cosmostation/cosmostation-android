package wannabit.io.cosmostaion.widget.txDetail.sif;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;
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

public class TxRemoveLiquidityHolder extends TxHolder {
    TextView    itemRLSigner,
                itemRLNativeAssetAmount, itemRLNativeAssetSymbol, itemRLExternalAssetlAmount, itemRLExternalAssetSymbol;

    public TxRemoveLiquidityHolder(@NonNull View itemView) {
        super(itemView);
        itemRLSigner                = itemView.findViewById(R.id.tx_remove_liquidity_signer);
        itemRLNativeAssetAmount     = itemView.findViewById(R.id.tx_liquidity_native_amount);
        itemRLNativeAssetSymbol     = itemView.findViewById(R.id.tx_liquidity_native_symbol);
        itemRLExternalAssetlAmount  = itemView.findViewById(R.id.tx_liquidity_exteranl_amount);
        itemRLExternalAssetSymbol   = itemView.findViewById(R.id.tx_liquidity_exteranl_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        try {
            Tx.MsgRemoveLiquidity msg = Tx.MsgRemoveLiquidity.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemRLSigner.setText(msg.getSigner());

            ArrayList<Coin> coins = new ArrayList<>();
            if (response.getTxResponse().getLogsCount() > position) {
                for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                    if ("transfer".equals(event.getType())) {
                        for (int i = 0; i < event.getAttributesList().size(); i++) {
                            if ("amount".equalsIgnoreCase(event.getAttributes(i).getKey())) {
                                String rawValue = event.getAttributes(i).getValue();
                                for (String rawCoin : rawValue.split(",")) {
                                    Pattern p = Pattern.compile("([0-9])+");
                                    Matcher m = p.matcher(rawCoin);
                                    if (m.find()) {
                                        String amount = m.group();
                                        String denom = rawCoin.substring(m.end());
                                        coins.add(new Coin(denom, amount));
                                    }
                                }
                            }
                        }
                    }
                }
            }
            WDp.setDpCoin(c, baseData, chainConfig, coins.get(0), itemRLNativeAssetSymbol, itemRLNativeAssetAmount);
            WDp.setDpCoin(c, baseData, chainConfig, coins.get(1), itemRLExternalAssetSymbol, itemRLExternalAssetlAmount);

        } catch (Exception e) {
            WLog.w("Exception " + e.getMessage());
        }
    }
}
