package wannabit.io.cosmostaion.widget.txDetail.kava;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SWP;

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

    public void onBind(Context c, BaseChain baseChain, ResTxInfo res, Msg msg, int position) {
        itemMsgImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        itemKavaAmount.setText(WDp.getDpAmount2(c, new BigDecimal("0"), 6, 6));
        itemHardAmount.setText(WDp.getDpAmount2(c, new BigDecimal("0"), 6, 6));
        itemSender.setText(msg.value.sender);
        itemMultiplier.setText(msg.value.denoms_to_claim.get(0).multiplier_name);

        ArrayList<Coin> incentiveCoins = res.simpleIncentives(position);

        for (Coin coin: incentiveCoins) {
            if (coin.denom.equalsIgnoreCase(TOKEN_KAVA)) {
                itemKavaAmount.setText(WDp.getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));
            }
            if (coin.denom.equalsIgnoreCase(TOKEN_HARD)) {
                itemHardAmount.setText(WDp.getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));
            }
        }
    }
}
