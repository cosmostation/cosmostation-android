package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.utils.WDp;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class TxHardPoolIncentive extends TxHolder {
    ImageView itemMsgImg;
    TextView itemMsgTitle;
    TextView itemSender, itemMultiplier, itemKavaDenom, itemKavaAmount, itemHardDenom, itemHardAmount;

    public TxHardPoolIncentive(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemMsgTitle = itemView.findViewById(R.id.tx_msg_text);
        itemSender = itemView.findViewById(R.id.tx_incentive_sender);
        itemMultiplier = itemView.findViewById(R.id.tx_multiplier_name);
        itemKavaDenom = itemView.findViewById(R.id.kava_symbol);
        itemKavaAmount = itemView.findViewById(R.id.kava_amount);
        itemHardDenom = itemView.findViewById(R.id.hard_symbol);
        itemHardAmount = itemView.findViewById(R.id.hard_amount);
    }

    public void onBind(Context c, BaseChain baseChain, ResTxInfo res, Msg msg) {
        itemMsgImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        itemSender.setText(msg.value.sender);
        itemMultiplier.setText(msg.value.multiplier_name);

        Coin kavaCoin = res.simpleHardPoolReward(TOKEN_KAVA);
        if (kavaCoin != null) {
            WDp.showCoinDp(c, kavaCoin, itemKavaDenom, itemKavaAmount, baseChain);
        } else {
            WDp.showCoinDp(c, TOKEN_KAVA, "0", itemKavaDenom, itemKavaAmount, baseChain);

        }
        Coin hardCoin = res.simpleHardPoolReward(TOKEN_HARD);
        if (hardCoin != null) {
            WDp.showCoinDp(c, hardCoin, itemHardDenom, itemHardAmount, baseChain);
        } else {
            WDp.showCoinDp(c, TOKEN_HARD, "0", itemHardDenom, itemHardAmount, baseChain);
        }
    }
}
