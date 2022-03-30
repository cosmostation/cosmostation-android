package wannabit.io.cosmostaion.widget.txDetail.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.base.abci.v1beta1.Abci;
import cosmos.tx.v1beta1.ServiceOuterClass;
import osmosis.lockup.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxBeginUnlockAllTokensHolder extends TxHolder {
    ImageView itemUnlockAllTokenImg;
    TextView itemUnLockAllTokenOwner, itemUnlockAllTokenCnt;

    public TxBeginUnlockAllTokensHolder(@NonNull View itemView) {
        super(itemView);
        itemUnlockAllTokenImg = itemView.findViewById(R.id.tx_unlock_all_token_icon);
        itemUnLockAllTokenOwner = itemView.findViewById(R.id.tx_unlock_all_token_owner);
        itemUnlockAllTokenCnt = itemView.findViewById(R.id.tx_unlock_all_token__cnt);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemUnlockAllTokenImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgBeginUnlockingAll msg = Tx.MsgBeginUnlockingAll.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemUnLockAllTokenOwner.setText(msg.getOwner());

            int unlockCnt = 0;
            if (response.getTxResponse().getLogsCount() > position) {
                for (Abci.StringEvent event : response.getTxResponse().getLogs(position).getEventsList()) {
                    if (event.getType().equals("begin_unlock") && event.getAttributesCount() > 0) {
                        unlockCnt = event.getAttributesCount() / 4;
                    }
                }
            }
            itemUnlockAllTokenCnt.setText("" + unlockCnt);
        } catch (Exception e) {
        }
    }
}
