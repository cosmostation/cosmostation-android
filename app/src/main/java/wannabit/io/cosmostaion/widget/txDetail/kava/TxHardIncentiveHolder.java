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

public class TxHardIncentiveHolder extends TxHolder {
    ImageView itemMsgImg;
    TextView  itemSender, itemMultiplier, itemKavaDenom, itemKavaAmount, itemHardDenom, itemHardAmount;

    public TxHardIncentiveHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemSender = itemView.findViewById(R.id.tx_incentive_sender);
        itemMultiplier = itemView.findViewById(R.id.tx_multiplier_name);
//        itemKavaDenom = itemView.findViewById(R.id.kava_symbol);
//        itemKavaAmount = itemView.findViewById(R.id.kava_amount);
//        itemHardDenom = itemView.findViewById(R.id.hard_symbol);
//        itemHardAmount = itemView.findViewById(R.id.hard_amount);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemMsgImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgClaimHardReward msg = Tx.MsgClaimHardReward.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemSender.setText(msg.getSender());
//            itemKavaAmount.setText(WDp.getDpAmount2(c, new BigDecimal("0"), 6, 6));
//            itemHardAmount.setText(WDp.getDpAmount2(c, new BigDecimal("0"), 6, 6));
            if (msg.getDenomsToClaimList() != null) {
                itemMultiplier.setText(msg.getDenomsToClaim(0).getMultiplierName());
            }

        } catch (Exception e) { }
    }
}
