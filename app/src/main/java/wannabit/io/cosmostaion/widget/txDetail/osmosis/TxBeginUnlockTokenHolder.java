package wannabit.io.cosmostaion.widget.txDetail.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.base.abci.v1beta1.Abci;
import cosmos.tx.v1beta1.ServiceOuterClass;
import osmosis.lockup.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxBeginUnlockTokenHolder extends TxHolder {
    TextView itemUnLockTokenOwner, itemUnlockTokenId, itemUnlockTokenCompleteTime;

    public TxBeginUnlockTokenHolder(@NonNull View itemView) {
        super(itemView);
        itemUnLockTokenOwner = itemView.findViewById(R.id.tx_unlock_token_owner);
        itemUnlockTokenId = itemView.findViewById(R.id.tx_unlock_token_id);
        itemUnlockTokenCompleteTime = itemView.findViewById(R.id.tx_unlock_token_complete_time);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        try {
            Tx.MsgBeginUnlocking msg = Tx.MsgBeginUnlocking.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemUnLockTokenOwner.setText(msg.getOwner());
            itemUnlockTokenId.setText("" + msg.getID());

            int duraion = 0;
            if (response.getTxResponse().getLogsCount() > position) {
                for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                    if (event.getType().equals("begin_unlock")) {
                        for (Abci.Attribute attribute : event.getAttributesList()) {
                            if (attribute.getKey().equals("duration")) {
                                String[] stringHour = attribute.getValue().split("h");
                                duraion = Integer.parseInt(stringHour[0]) * 60 * 60 * 1000;
                            }
                        }
                    }
                }
            }

            if (duraion != 0) {
                long timeStampSeconds = WDp.dateToLong2(c, response.getTxResponse().getTimestamp());
                long completeTime = timeStampSeconds + duraion;
                itemUnlockTokenCompleteTime.setText(WDp.getDpTime(c, completeTime));
            } else {
                itemUnlockTokenCompleteTime.setText("--");
            }
        } catch (Exception e) { }
    }
}
