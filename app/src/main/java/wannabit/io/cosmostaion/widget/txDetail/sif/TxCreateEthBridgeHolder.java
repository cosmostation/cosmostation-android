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
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxCreateEthBridgeHolder extends TxHolder {
    ImageView itemEthBridgeImg;
    TextView itemClaimType,
            itemBridgeAddress, itemTokenAddress, itemEthSender,
            itemBridgeValidator, itemBridgeMoniker,
            itemBridgeAssetAmount, itemBridgeAssetSymbol;

    public TxCreateEthBridgeHolder(@NonNull View itemView) {
        super(itemView);
        itemEthBridgeImg = itemView.findViewById(R.id.tx_eth_bridge_icon);
        itemClaimType = itemView.findViewById(R.id.tx_claim_type);
        itemBridgeAddress = itemView.findViewById(R.id.tx_bridge_contract_address);
        itemTokenAddress = itemView.findViewById(R.id.tx_token_contract_address);
        itemEthSender = itemView.findViewById(R.id.tx_ethereum_sender);
        itemBridgeValidator = itemView.findViewById(R.id.tx_bridge_validator);
        itemBridgeMoniker = itemView.findViewById(R.id.tx_bridge_moniker);
        itemBridgeAssetAmount = itemView.findViewById(R.id.tx_bridge_asset_amount);
        itemBridgeAssetSymbol = itemView.findViewById(R.id.tx_bridge_asset_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemEthBridgeImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaim msg = sifnode.ethbridge.v1.Tx.MsgCreateEthBridgeClaim.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemClaimType.setText("" + msg.getEthBridgeClaim().getClaimType());
            itemBridgeAddress.setText(msg.getEthBridgeClaim().getBridgeContractAddress());
            itemTokenAddress.setText(msg.getEthBridgeClaim().getTokenContractAddress());
            itemEthSender.setText(msg.getEthBridgeClaim().getEthereumSender());
            itemBridgeValidator.setText(msg.getEthBridgeClaim().getValidatorAddress());
            itemBridgeMoniker.setText("(" + baseData.getValidatorInfo(msg.getEthBridgeClaim().getValidatorAddress()).getDescription().getMoniker() + ")");

            Coin coin = null;
            if (response.getTxResponse().getLogsCount() > position) {
                for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                    if (event.getType().equals("transfer")) {
                        String Value = event.getAttributesList().get(2).getValue();

                        String Amount = Value.replaceAll("[^0-9]", "");
                        coin = new Coin(Value.replaceAll(Amount, ""), Amount);
                    }
                }
            }
            if (coin != null) {
                WDp.showCoinDp(c, baseData, coin, itemBridgeAssetSymbol, itemBridgeAssetAmount, baseChain);
            } else {
                itemBridgeAssetAmount.setText("");
                itemBridgeAssetSymbol.setText("");
            }
        } catch (Exception e) {
            WLog.w("Exception " + e.getMessage());
        }
    }
}
