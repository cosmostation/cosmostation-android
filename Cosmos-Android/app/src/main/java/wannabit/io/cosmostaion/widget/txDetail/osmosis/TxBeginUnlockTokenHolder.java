package wannabit.io.cosmostaion.widget.txDetail.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.TimeZone;

import cosmos.base.abci.v1beta1.Abci;
import cosmos.tx.v1beta1.ServiceOuterClass;
import osmosis.lockup.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxBeginUnlockTokenHolder extends TxHolder {
    ImageView itemUnlockTokenImg;
    TextView itemUnLockTokenOwner, itemUnlockTokenId, itemUnlockTokenCompleteTime;

    public TxBeginUnlockTokenHolder(@NonNull View itemView) {
        super(itemView);
        itemUnlockTokenImg = itemView.findViewById(R.id.tx_unlock_token_icon);
        itemUnLockTokenOwner = itemView.findViewById(R.id.tx_unlock_token_owner);
        itemUnlockTokenId = itemView.findViewById(R.id.tx_unlock_token_id);
        itemUnlockTokenCompleteTime = itemView.findViewById(R.id.tx_unlock_token_complete_time);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemUnlockTokenImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
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
