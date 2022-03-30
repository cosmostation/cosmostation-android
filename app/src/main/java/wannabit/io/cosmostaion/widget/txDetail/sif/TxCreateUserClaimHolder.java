package wannabit.io.cosmostaion.widget.txDetail.sif;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import sifnode.dispensation.v1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxCreateUserClaimHolder extends TxHolder {
    ImageView itemCreateClaimImg;
    TextView itemCreateClaimAddress, itemCreateClaimType;

    public TxCreateUserClaimHolder(@NonNull View itemView) {
        super(itemView);
        itemCreateClaimImg = itemView.findViewById(R.id.tx_create_user_claim_icon);
        itemCreateClaimAddress = itemView.findViewById(R.id.tx_create_user_claim_address);
        itemCreateClaimType = itemView.findViewById(R.id.tx_create_user_claim_type);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemCreateClaimImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgCreateUserClaim msg = Tx.MsgCreateUserClaim.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemCreateClaimAddress.setText(msg.getUserClaimAddress());
            String type = msg.getUserClaimType().getValueDescriptor().getName();
            String value = "";
            if (type.contains("UNSPECIFIED")) {
                value = "unspecified";
            } else if (type.contains("AIRDROP")) {
                value = "airdrop";
            } else if (type.contains("VALIDATOR_SUBSIDY")) {
                value = "validatorsubsidy";
            } else if (type.contains("LIQUIDITY_MINING")) {
                value = "liquidityMining";
            }
            itemCreateClaimType.setText(value);

        } catch (Exception e) {
        }
    }
}
