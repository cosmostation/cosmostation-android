package wannabit.io.cosmostaion.widget.txDetail.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import osmosis.lockup.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxLockTokenHolder extends TxHolder {
    TextView itemLockTokenOwner, itemLockTokenDuration, itemLockTokenAmount, itemLockTokenSymbol;

    public TxLockTokenHolder(@NonNull View itemView) {
        super(itemView);
        itemLockTokenOwner = itemView.findViewById(R.id.tx_lock_token_owner);
        itemLockTokenDuration = itemView.findViewById(R.id.tx_lock_token_duration);
        itemLockTokenAmount= itemView.findViewById(R.id.tx_lock_token_amount);
        itemLockTokenSymbol = itemView.findViewById(R.id.tx_lock_token_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        try {
            Tx.MsgLockTokens msg = Tx.MsgLockTokens.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemLockTokenOwner.setText(msg.getOwner());
            long durationDay = msg.getDuration().getSeconds() / 86400;
            itemLockTokenDuration.setText("" + durationDay + " days");

            Coin coinLock = new Coin(msg.getCoins(0).getDenom(), msg.getCoins(0).getAmount());
            WDp.setDpCoin(c, baseData, chainConfig, coinLock, itemLockTokenSymbol, itemLockTokenAmount);
        } catch (Exception e) { }
    }
}
