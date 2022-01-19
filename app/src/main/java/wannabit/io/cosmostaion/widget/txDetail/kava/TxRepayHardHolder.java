package wannabit.io.cosmostaion.widget.txDetail.kava;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import kava.hard.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxRepayHardHolder extends TxHolder {
    ImageView itemMsgImg;
    TextView  itemSender, itemOwener, itemRepayAmount, itemRepayDenom;

    public TxRepayHardHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemSender = itemView.findViewById(R.id.tx_sender);
        itemOwener = itemView.findViewById(R.id.tx_owener);
        itemRepayAmount = itemView.findViewById(R.id.repay_amount);
        itemRepayDenom = itemView.findViewById(R.id.repay_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemMsgImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgRepay msg = Tx.MsgRepay.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemSender.setText(msg.getSender());
            itemOwener.setText(msg.getSender());
            WDp.showCoinDp(c, baseData, msg.getAmount(0).getDenom(), msg.getAmount(0).getAmount(), itemRepayDenom, itemRepayAmount, BaseChain.KAVA_MAIN);

        } catch (Exception e) { }
    }
}

