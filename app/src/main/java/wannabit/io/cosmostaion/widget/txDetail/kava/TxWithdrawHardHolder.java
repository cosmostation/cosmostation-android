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

public class TxWithdrawHardHolder extends TxHolder {
    TextView  itemDepositor, itemDepositType, itemWithdrawAmount, itemWithdrawAmountDenom;

    public TxWithdrawHardHolder(@NonNull View itemView) {
        super(itemView);
        itemDepositor = itemView.findViewById(R.id.tx_depositor);
        itemDepositType = itemView.findViewById(R.id.tx_deposit_type);
        itemWithdrawAmount = itemView.findViewById(R.id.tx_withdraw_amount);
        itemWithdrawAmountDenom = itemView.findViewById(R.id.tx_withdraw_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        try {
            Tx.MsgWithdraw msg = Tx.MsgWithdraw.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemDepositor.setText(msg.getDepositor());
            WDp.setDpCoin(c, baseData, chainConfig, msg.getAmount(0).getDenom(), msg.getAmount(0).getAmount(), itemWithdrawAmountDenom, itemWithdrawAmount);

        } catch (Exception e) { }
    }
}
