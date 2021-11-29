package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;


import akash.cert.v1beta2.Cert;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class TxCreateCertificateHolder extends TxHolder{
    ImageView itemCreateCertificateImg;
    TextView itemOwner;

    public TxCreateCertificateHolder(@NonNull View itemView) {
        super(itemView);
        itemCreateCertificateImg = itemView.findViewById(R.id.tx_create_certificate_icon);
        itemOwner = itemView.findViewById(R.id.tx_create_certificate_owner);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemCreateCertificateImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Cert.MsgCreateCertificate msg = Cert.MsgCreateCertificate.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemOwner.setText(msg.getOwner());
        } catch (Exception e) {}
    }
}
