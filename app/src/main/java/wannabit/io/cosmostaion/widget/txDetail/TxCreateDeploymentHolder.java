package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

import akash.deployment.v1beta1.DeploymentOuterClass;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class TxCreateDeploymentHolder extends TxHolder {
    ImageView itemCreateDeploymentImg;
    TextView itemCreateDeploymentId, itemVersionHash, itemCreateDeploymentDeposit, itemCreateDeploymentDepositDenom;

    public TxCreateDeploymentHolder(@NonNull View itemView) {
        super(itemView);
        itemCreateDeploymentImg = itemView.findViewById(R.id.tx_create_deployment_icon);
        itemCreateDeploymentId = itemView.findViewById(R.id.tx_create_deployment_id);
//        itemVersionHash                     = itemView.findViewById(R.id.tx_deployment_version_hash);
        itemCreateDeploymentDeposit = itemView.findViewById(R.id.tx_create_deployment_deposit);
        itemCreateDeploymentDepositDenom = itemView.findViewById(R.id.tx_create_deployment_deposit_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        WDp.DpMainDenom(c, baseChain.getChain(), itemCreateDeploymentDepositDenom);
        final int dpDecimal = WDp.mainDivideDecimal(baseChain);
        itemCreateDeploymentImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            DeploymentOuterClass.MsgCreateDeployment msg = DeploymentOuterClass.MsgCreateDeployment.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemCreateDeploymentId.setText("" + msg.getId().getOwner());
            itemCreateDeploymentDeposit.setText(WDp.getDpAmount2(new BigDecimal(msg.getDeposit().getAmount()), dpDecimal, dpDecimal));
        } catch (Exception e) {
        }
    }
}
