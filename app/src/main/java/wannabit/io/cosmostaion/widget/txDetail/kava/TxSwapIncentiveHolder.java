package wannabit.io.cosmostaion.widget.txDetail.kava;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;

import cosmos.tx.v1beta1.ServiceOuterClass;
import kava.incentive.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SWP;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_USDX;

public class TxSwapIncentiveHolder extends TxHolder {
    ImageView   itemSwapIncentiveImg;
    TextView    itemSwapIncentiveSender, itemSwapIncentiveName,
                itemSwapIncentiveKavaAmount, itemSwapIncentiveSwpAmount,
                itemSwapIncentiveHardAmount, itemSwapIncentiveUsdxAmount;

    public TxSwapIncentiveHolder(@NonNull View itemView) {
        super(itemView);
        itemSwapIncentiveImg            = itemView.findViewById(R.id.tx_msg_icon);
        itemSwapIncentiveSender         = itemView.findViewById(R.id.tx_incentive_sender);
        itemSwapIncentiveName           = itemView.findViewById(R.id.tx_multiplier_name);
//        itemSwapIncentiveKavaAmount     = itemView.findViewById(R.id.kava_amount);
//        itemSwapIncentiveSwpAmount      = itemView.findViewById(R.id.swp_amount);
//        itemSwapIncentiveHardAmount     = itemView.findViewById(R.id.hard_amount);
//        itemSwapIncentiveUsdxAmount     = itemView.findViewById(R.id.usdx_amount);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemSwapIncentiveImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgClaimSwapReward msg = Tx.MsgClaimSwapReward.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemSwapIncentiveSender.setText(msg.getSender());
//            itemSwapIncentiveKavaAmount.setText(WDp.getDpAmount2(c, new BigDecimal("0"), 6, 6));
//            itemSwapIncentiveSwpAmount.setText(WDp.getDpAmount2(c, new BigDecimal("0"), 6, 6));
//            itemSwapIncentiveHardAmount.setText(WDp.getDpAmount2(c, new BigDecimal("0"), 6, 6));
//            itemSwapIncentiveUsdxAmount.setText(WDp.getDpAmount2(c, new BigDecimal("0"), 6, 6));
            if (msg.getDenomsToClaimList() != null) {
                itemSwapIncentiveName.setText(msg.getDenomsToClaim(0).getMultiplierName());
            }

        } catch (Exception e) { }
    }
}
