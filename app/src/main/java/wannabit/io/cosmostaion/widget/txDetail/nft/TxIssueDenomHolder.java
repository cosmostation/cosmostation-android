package wannabit.io.cosmostaion.widget.txDetail.nft;

import static wannabit.io.cosmostaion.base.BaseChain.CRYPTO_MAIN;
import static wannabit.io.cosmostaion.base.BaseChain.IRIS_MAIN;

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
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxIssueDenomHolder extends TxHolder {
    ImageView itemIssueDenomImg;
    TextView itemIssueDenomId, itemIssueDenomName, itemIssueDenomSchema;

    public TxIssueDenomHolder(@NonNull View itemView) {
        super(itemView);
        itemIssueDenomImg = itemView.findViewById(R.id.tx_issue_denom_icon);
        itemIssueDenomId = itemView.findViewById(R.id.tx_issue_denom_id);
        itemIssueDenomName = itemView.findViewById(R.id.tx_issue_denom_name);
        itemIssueDenomSchema = itemView.findViewById(R.id.tx_issue_denom_schema);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemIssueDenomImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        if (baseChain.equals(IRIS_MAIN)) {
            try {
                Tx.MsgIssueDenom msg = Tx.MsgIssueDenom.parseFrom(response.getTx().getBody().getMessages(position).getValue());
                itemIssueDenomId.setText(msg.getId());
                itemIssueDenomName.setText(msg.getName());
                itemIssueDenomSchema.setText(msg.getSchema());
            } catch (Exception e) {
            }
        } else if (baseChain.equals(CRYPTO_MAIN)) {
            try {
                chainmain.nft.v1.Tx.MsgIssueDenom msg = chainmain.nft.v1.Tx.MsgIssueDenom.parseFrom(response.getTx().getBody().getMessages(position).getValue());
                itemIssueDenomId.setText(msg.getId());
                itemIssueDenomName.setText(msg.getName());
                itemIssueDenomSchema.setText(msg.getSchema());
            } catch (Exception e) {
            }
        }
    }
}
