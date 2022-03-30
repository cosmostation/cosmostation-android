package wannabit.io.cosmostaion.widget.txDetail.kava;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import kava.incentive.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxMintingIncentiveHolder extends TxHolder {
    ImageView itemMsgImg;
    TextView itemSender, itemMultiplier;

    public TxMintingIncentiveHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemSender = itemView.findViewById(R.id.tx_incentive_sender);
        itemMultiplier = itemView.findViewById(R.id.tx_multiplier_name);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemMsgImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgClaimUSDXMintingReward msg = Tx.MsgClaimUSDXMintingReward.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemSender.setText(msg.getSender());
            itemMultiplier.setText(msg.getMultiplierName());

        } catch (Exception e) {
        }
    }
}

