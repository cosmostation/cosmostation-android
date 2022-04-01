package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import akash.deployment.v1beta1.DeploymentOuterClass;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class TxCloseDeploymentHolder extends TxHolder {
    ImageView itemCloseDeploymentImg;
    TextView itemCloseDeploymentId, itemDseq;

    public TxCloseDeploymentHolder(@NonNull View itemView) {
        super(itemView);
        itemCloseDeploymentImg = itemView.findViewById(R.id.tx_close_deployment_icon);
        itemCloseDeploymentId = itemView.findViewById(R.id.tx_close_deployment_id);
        itemDseq = itemView.findViewById(R.id.tx_close_deployment_dseq);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemCloseDeploymentImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        try {
            DeploymentOuterClass.MsgCloseDeployment msg = DeploymentOuterClass.MsgCloseDeployment.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemCloseDeploymentId.setText(msg.getId().getOwner());
            itemDseq.setText(WDp.getDecimalFormat(0).format(msg.getId().getDseq()));
        } catch (Exception e) {
        }
    }
}
