package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class TxCreateDidHolder extends TxHolder {
    ImageView itemCreateDidImg;
    TextView itemCreateDid, itemCreateDidMethodId, itemCreateFromAddress;

    public TxCreateDidHolder(@NonNull View itemView) {
        super(itemView);
        itemCreateDidImg = itemView.findViewById(R.id.tx_create_did_icon);
        itemCreateDid = itemView.findViewById(R.id.tx_create_did);
        itemCreateDidMethodId = itemView.findViewById(R.id.tx_create_did_verification_method_id);
        itemCreateFromAddress = itemView.findViewById(R.id.tx_create_did_from_address);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemCreateDidImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            panacea.did.v2.MsgCreateDID msg = panacea.did.v2.MsgCreateDID.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemCreateDid.setText(msg.getDid());
            itemCreateDidMethodId.setText(msg.getVerificationMethodId());
            itemCreateFromAddress.setText(msg.getFromAddress());

        } catch (Exception e) {
        }
    }
}
