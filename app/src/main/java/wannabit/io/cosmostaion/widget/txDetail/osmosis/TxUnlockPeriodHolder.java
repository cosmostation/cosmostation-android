package wannabit.io.cosmostaion.widget.txDetail.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxUnlockPeriodHolder extends TxHolder {
    ImageView itemUnlockPeriodImg;
    TextView itemUnLockPeriodOwner, itemUnlockPeriodId, itemUnLockPeriodAmount, itemUnLockPeriodAmountSymbol;

    public TxUnlockPeriodHolder(@NonNull View itemView) {
        super(itemView);
        itemUnlockPeriodImg = itemView.findViewById(R.id.tx_unlock_period_icon);
        itemUnLockPeriodOwner = itemView.findViewById(R.id.tx_unlock_period_owner);
        itemUnlockPeriodId = itemView.findViewById(R.id.tx_unlock_period_id);
        itemUnLockPeriodAmount = itemView.findViewById(R.id.tx_unlock_period_amount);
        itemUnLockPeriodAmountSymbol = itemView.findViewById(R.id.tx_unlock_period_amount_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemUnlockPeriodImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
//            Tx.MsgUnlockPeriodLock msg = Tx.MsgUnlockPeriodLock.parseFrom(response.getTx().getBody().getMessages(position).getValue());
//            itemUnLockPeriodOwner.setText(msg.getOwner());
//            itemUnlockPeriodId.setText("" + msg.getID());
//
//            Coin coin = null;
//            if (response.getTxResponse().getLogsCount() > position) {
//                for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
//                    if (event.getType().equals("transfer")) {
//                        for (Abci.Attribute attribute : event.getAttributesList()) {
//                            if (attribute.getKey().equals("amount")) {
//                                String value = attribute.getValue();
//                                coin = new Coin(value.replaceAll(value.split("gamm")[0], ""), value.split("gamm")[0]);
//                            }
//                        }
//                    }
//                }
//            }
//            if (coin != null) {
//                WDp.showCoinDp(c, baseData, coin, itemUnLockPeriodAmountSymbol,itemUnLockPeriodAmount, baseChain);
//            }
        } catch (Exception e) {
        }
    }
}
