package wannabit.io.cosmostaion.widget.txDetail.nft;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import irismod.nft.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;

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

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemMintNFTImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        if (baseChain.equals(IRIS_MAIN)) {
            try {
                Tx.MsgMintNFT msg = Tx.MsgMintNFT.parseFrom(response.getTx().getBody().getMessages(position).getValue());
                itemMintNFTTokenId.setText(msg.getId());
                itemMintNFTDenomId.setText(msg.getDenomId());
                itemMintNFTName.setText(msg.getName());
                itemMintNFTDescription.setText(WUtil.getNftDescription(msg.getData()));
                itemMintNFTUri.setText(msg.getUri());
            } catch (Exception e) { }
        } else if (baseChain.equals(CRYPTO_MAIN)) {
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
