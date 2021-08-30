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
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_USDX;

public class TxDelegatorIncentive extends TxHolder {
    ImageView   itemDeleIncentiveImg;
    TextView    itemDeleIncentiveSender, itemDeleIncentiveName,
                itemDeleIncentiveKavaAmount, itemDeleIncentiveSwpAmount,
                itemDeleIncentiveHardAmount, itemDeleIncentiveUsdxAmount;

    public TxDelegatorIncentive(@NonNull View itemView) {
        super(itemView);
        itemDeleIncentiveImg             = itemView.findViewById(R.id.tx_msg_icon);
        itemDeleIncentiveSender          = itemView.findViewById(R.id.tx_incentive_sender);
        itemDeleIncentiveName           = itemView.findViewById(R.id.tx_multiplier_name);
        itemDeleIncentiveKavaAmount     = itemView.findViewById(R.id.kava_amount);
        itemDeleIncentiveSwpAmount      = itemView.findViewById(R.id.swp_amount);
        itemDeleIncentiveHardAmount     = itemView.findViewById(R.id.hard_amount);
        itemDeleIncentiveUsdxAmount     = itemView.findViewById(R.id.usdx_amount);
    }

    public void onBind(Context c, BaseChain baseChain, ResTxInfo res, Msg msg, int position) {
        itemDeleIncentiveImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        itemDeleIncentiveKavaAmount.setText(WDp.getDpAmount2(c, new BigDecimal("0"), 6, 6));
        itemDeleIncentiveSwpAmount.setText(WDp.getDpAmount2(c, new BigDecimal("0"), 6, 6));
        itemDeleIncentiveHardAmount.setText(WDp.getDpAmount2(c, new BigDecimal("0"), 6, 6));
        itemDeleIncentiveUsdxAmount.setText(WDp.getDpAmount2(c, new BigDecimal("0"), 6, 6));
        if (baseChain.equals(BaseChain.KAVA_MAIN)) {
            itemDeleIncentiveSender.setText(msg.value.sender);
            itemDeleIncentiveName.setText(msg.value.denoms_to_claim.get(0).multiplier_name);

            ArrayList<Coin> incentiveCoins = res.simpleIncentives(position);

            for (Coin coin: incentiveCoins) {
                if (coin.denom.equalsIgnoreCase(TOKEN_KAVA)) {
                    itemDeleIncentiveKavaAmount.setText(WDp.getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));
                }
                if (coin.denom.equalsIgnoreCase(TOKEN_SWP)) {
                    itemDeleIncentiveSwpAmount.setText(WDp.getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));
                }
                if (coin.denom.equalsIgnoreCase(TOKEN_HARD)) {
                    itemDeleIncentiveHardAmount.setText(WDp.getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));
                }
                if (coin.denom.equalsIgnoreCase(TOKEN_USDX)) {
                    itemDeleIncentiveUsdxAmount.setText(WDp.getDpAmount2(c, new BigDecimal(coin.amount), 6, 6));
                }
            }
        }
    }
}