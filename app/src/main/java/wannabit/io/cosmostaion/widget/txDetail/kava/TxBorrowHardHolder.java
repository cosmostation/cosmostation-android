package wannabit.io.cosmostaion.widget.txDetail.kava;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import kava.hard.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxBorrowHardHolder extends TxHolder {
    TextView  itemBorrower, itemBorrowAmount, itemBorrowDenom;

    public TxBorrowHardHolder(@NonNull View itemView) {
        super(itemView);
        itemBorrower = itemView.findViewById(R.id.tx_hard_borrower);
        itemBorrowAmount = itemView.findViewById(R.id.borrow_amount);
        itemBorrowDenom = itemView.findViewById(R.id.borrow_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        try {
            Tx.MsgBorrow msg = Tx.MsgBorrow.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemBorrower.setText(msg.getBorrower());
            WDp.setDpCoin(c, baseData, chainConfig, msg.getAmount(0).getDenom(), msg.getAmount(0).getAmount(), itemBorrowDenom, itemBorrowAmount);

        } catch (Exception e) { }
    }
}
