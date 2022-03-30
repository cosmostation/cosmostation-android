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

public class TxWithdrawHardHolder extends TxHolder {
    ImageView itemMsgImg;
    TextView itemDepositor, itemDepositType, itemWithdrawAmount, itemWithdrawAmountDenom;

    public TxWithdrawHardHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemDepositor = itemView.findViewById(R.id.tx_depositor);
        itemDepositType = itemView.findViewById(R.id.tx_deposit_type);
        itemWithdrawAmount = itemView.findViewById(R.id.tx_withdraw_amount);
        itemWithdrawAmountDenom = itemView.findViewById(R.id.tx_withdraw_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemMsgImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgWithdraw msg = Tx.MsgWithdraw.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemDepositor.setText(msg.getDepositor());
            WDp.showCoinDp(c, baseData, msg.getAmount(0).getDenom(), msg.getAmount(0).getAmount(), itemWithdrawAmountDenom, itemWithdrawAmount, BaseChain.KAVA_MAIN);

        } catch (Exception e) {
        }
    }
}
