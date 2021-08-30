package wannabit.io.cosmostaion.widget.txDetail.kava;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxHardPoolLiquidate extends TxHolder {
    ImageView itemMsgImg;
    TextView itemMsgTitle;
    TextView itemKeeper, itemBorrower;

    public TxHardPoolLiquidate(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
        itemKeeper = itemView.findViewById(R.id.hard_keeper);
        itemBorrower = itemView.findViewById(R.id.hard_borrower);
    }

    public void onBind(Context c, BaseChain baseChain, Msg msg) {
        itemMsgImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        itemKeeper.setText(msg.value.keeper);
        itemBorrower.setText(msg.value.borrower);

    }
}
