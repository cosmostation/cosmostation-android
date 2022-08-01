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
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxMintNFTHolder extends TxHolder {
    ImageView itemMintNFTImg;
    TextView  itemMintNFTTokenId, itemMintNFTDenomId, itemMintNFTName, itemMintNFTDescription, itemMintNFTUri;

    public TxMintNFTHolder(@NonNull View itemView) {
        super(itemView);
        itemMintNFTImg = itemView.findViewById(R.id.tx_mint_nft_icon);
        itemMintNFTTokenId = itemView.findViewById(R.id.tx_mint_nft_token_id);
        itemMintNFTDenomId = itemView.findViewById(R.id.tx_mint_nft_denom_id);
        itemMintNFTName = itemView.findViewById(R.id.tx_mint_nft_name);
        itemMintNFTDescription = itemView.findViewById(R.id.tx_mint_nft_description);
        itemMintNFTUri = itemView.findViewById(R.id.tx_mint_nft_url);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        itemMintNFTImg.setColorFilter(ContextCompat.getColor(c, chainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);

        if (chainConfig.baseChain().equals(BaseChain.IRIS_MAIN)) {
            try {
                Tx.MsgMintNFT msg = Tx.MsgMintNFT.parseFrom(response.getTx().getBody().getMessages(position).getValue());
                itemMintNFTTokenId.setText(msg.getId());
                itemMintNFTDenomId.setText(msg.getDenomId());
                itemMintNFTName.setText(msg.getName());
                itemMintNFTDescription.setText(WUtil.getNftDescription(msg.getData()));
                itemMintNFTUri.setText(msg.getUri());
            } catch (Exception e) { }

        } else if (chainConfig.baseChain().equals(BaseChain.CRYPTO_MAIN)) {
            try {
                chainmain.nft.v1.Tx.MsgMintNFT msg = chainmain.nft.v1.Tx.MsgMintNFT.parseFrom(response.getTx().getBody().getMessages(position).getValue());
                itemMintNFTTokenId.setText(msg.getId());
                itemMintNFTDenomId.setText(msg.getDenomId());
                itemMintNFTName.setText(msg.getName());
                itemMintNFTDescription.setText(WUtil.getNftDescription(msg.getData()));
                itemMintNFTUri.setText(msg.getUri());
            } catch (Exception e) { }
        }
    }
}
