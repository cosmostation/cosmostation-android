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

public class TxHardPoolLiquidateHolder extends TxHolder {
    ImageView itemMsgImg;
    TextView itemKeeper, itemBorrower;

    public TxHardPoolLiquidateHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemKeeper = itemView.findViewById(R.id.hard_keeper);
        itemBorrower = itemView.findViewById(R.id.hard_borrower);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemMsgImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgLiquidate msg = Tx.MsgLiquidate.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemKeeper.setText(msg.getKeeper());
            itemBorrower.setText(msg.getBorrower());

        } catch (Exception e) {
        }
    }
}
