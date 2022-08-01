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

public class TxIssueDenomHolder extends TxHolder {
    ImageView itemIssueDenomImg;
    TextView  itemIssueDenomId, itemIssueDenomName, itemIssueDenomSchema;

    public TxIssueDenomHolder(@NonNull View itemView) {
        super(itemView);
        itemIssueDenomImg = itemView.findViewById(R.id.tx_issue_denom_icon);
        itemIssueDenomId = itemView.findViewById(R.id.tx_issue_denom_id);
        itemIssueDenomName = itemView.findViewById(R.id.tx_issue_denom_name);
        itemIssueDenomSchema = itemView.findViewById(R.id.tx_issue_denom_schema);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        itemIssueDenomImg.setColorFilter(ContextCompat.getColor(c, chainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);

        if (chainConfig.baseChain().equals(BaseChain.IRIS_MAIN)) {
            try {
                Tx.MsgIssueDenom msg = Tx.MsgIssueDenom.parseFrom(response.getTx().getBody().getMessages(position).getValue());
                itemIssueDenomId.setText(msg.getId());
                itemIssueDenomName.setText(msg.getName());
                itemIssueDenomSchema.setText(msg.getSchema());
            } catch (Exception e) { }

        } else if (chainConfig.baseChain().equals(BaseChain.CRYPTO_MAIN)) {
            try {
                chainmain.nft.v1.Tx.MsgIssueDenom msg = chainmain.nft.v1.Tx.MsgIssueDenom.parseFrom(response.getTx().getBody().getMessages(position).getValue());
                itemIssueDenomId.setText(msg.getId());
                itemIssueDenomName.setText(msg.getName());
                itemIssueDenomSchema.setText(msg.getSchema());
            } catch (Exception e) { }
        }
    }
}
