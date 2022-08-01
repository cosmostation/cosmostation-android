package wannabit.io.cosmostaion.widget.txDetail.nft;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import cosmos.tx.v1beta1.ServiceOuterClass;
import irismod.nft.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxTransferNFTHolder extends TxHolder {
    ImageView itemSendNFTImg;
    TextView  itemTitle, itemSendNFTSender, itemSendNFTRecipient, itemSendNFTTokenId, itemSendNFTDenomId;

    public TxTransferNFTHolder(@NonNull View itemView) {
        super(itemView);
        itemSendNFTImg = itemView.findViewById(R.id.tx_send_nft_icon);
        itemTitle = itemView.findViewById(R.id.tx_title);
        itemSendNFTSender = itemView.findViewById(R.id.tx_send_nft_sender);
        itemSendNFTRecipient = itemView.findViewById(R.id.tx_send_nft_recipient);
        itemSendNFTTokenId = itemView.findViewById(R.id.tx_send_nft_token_id);
        itemSendNFTDenomId = itemView.findViewById(R.id.tx_send_nft_denom_id);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        itemSendNFTImg.setColorFilter(ContextCompat.getColor(c, chainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);

        if (chainConfig.baseChain().equals(BaseChain.IRIS_MAIN)) {
            try {
                Tx.MsgTransferNFT msg = Tx.MsgTransferNFT.parseFrom(response.getTx().getBody().getMessages(position).getValue());
                if (address.equalsIgnoreCase(msg.getSender())) {
                    itemTitle.setText(c.getString(R.string.tx_send_nft));
                } else if (address.equalsIgnoreCase(msg.getRecipient())) {
                    itemTitle.setText(c.getString(R.string.tx_receive_nft));
                }
                itemSendNFTSender.setText(msg.getSender());
                itemSendNFTRecipient.setText(msg.getRecipient());
                itemSendNFTTokenId.setText(msg.getId());
                itemSendNFTDenomId.setText(msg.getDenomId());
            } catch (Exception e) { }

        } else if (chainConfig.baseChain().equals(BaseChain.CRYPTO_MAIN)) {
            try {
                chainmain.nft.v1.Tx.MsgTransferNFT msg = chainmain.nft.v1.Tx.MsgTransferNFT.parseFrom(response.getTx().getBody().getMessages(position).getValue());
                if (address.equalsIgnoreCase(msg.getSender())) {
                    itemTitle.setText(c.getString(R.string.tx_send_nft));
                } else if (address.equalsIgnoreCase(msg.getRecipient())) {
                    itemTitle.setText(c.getString(R.string.tx_receive_nft));
                }
                itemSendNFTSender.setText(msg.getSender());
                itemSendNFTRecipient.setText(msg.getRecipient());
                itemSendNFTTokenId.setText(msg.getId());
                itemSendNFTDenomId.setText(msg.getDenomId());
            } catch (Exception e) { }
        }
    }
}
