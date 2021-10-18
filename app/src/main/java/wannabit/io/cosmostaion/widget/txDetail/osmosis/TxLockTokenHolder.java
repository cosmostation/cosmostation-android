package wannabit.io.cosmostaion.widget.txDetail.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

import cosmos.tx.v1beta1.ServiceOuterClass;
import osmosis.lockup.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxLockTokenHolder extends TxHolder {
    ImageView itemLockTokenImg;
    TextView itemLockTokenOwner, itemLockTokenDuration, itemLockTokenAmount, itemLockTokenSymbol;

    public TxLockTokenHolder(@NonNull View itemView) {
        super(itemView);
        itemLockTokenImg = itemView.findViewById(R.id.tx_lock_token_icon);
        itemLockTokenOwner = itemView.findViewById(R.id.tx_lock_token_owner);
        itemLockTokenDuration = itemView.findViewById(R.id.tx_lock_token_duration);
        itemLockTokenAmount= itemView.findViewById(R.id.tx_lock_token_amount);
        itemLockTokenSymbol = itemView.findViewById(R.id.tx_lock_token_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemLockTokenImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        try {
            Tx.MsgLockTokens msg = Tx.MsgLockTokens.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemLockTokenOwner.setText(msg.getOwner());
            long durationDay = msg.getDuration().getSeconds() / 86400;
            itemLockTokenDuration.setText("" + durationDay + " days");

            Coin coinLock = new Coin(msg.getCoins(0).getDenom(), msg.getCoins(0).getAmount());
            WDp.showCoinDp(c, coinLock, itemLockTokenSymbol, itemLockTokenAmount, baseChain);
        } catch (Exception e) { }
    }
}
