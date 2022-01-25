package wannabit.io.cosmostaion.widget.txDetail.kava;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
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
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_SWP;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_USDX;

public class TxHardIncentiveHolder extends TxHolder {
    ImageView       itemMsgImg;
    RelativeLayout  incen0Layer, incen1Layer, incen2Layer, incen3Layer;
    TextView        itemSender, itemMultiplier, itemKavaDenom, itemKavaAmount, itemSwpDenom, itemSwpAmount, itemHardDenom, itemHardAmount, itemUsdxDenom, itemUsdxAmount;

    public TxHardIncentiveHolder(@NonNull View itemView) {
        super(itemView);
        itemMsgImg = itemView.findViewById(R.id.tx_msg_icon);
        itemSender = itemView.findViewById(R.id.tx_incentive_sender);
        itemMultiplier = itemView.findViewById(R.id.tx_multiplier_name);
        incen0Layer = itemView.findViewById(R.id.incen0Layer);
        itemKavaDenom = itemView.findViewById(R.id.kava_symbol);
        itemKavaAmount = itemView.findViewById(R.id.kava_amount);
        incen1Layer = itemView.findViewById(R.id.incen1Layer);
        itemSwpDenom = itemView.findViewById(R.id.swp_symbol);
        itemSwpAmount = itemView.findViewById(R.id.swp_amount);
        incen2Layer = itemView.findViewById(R.id.incen2Layer);
        itemHardDenom = itemView.findViewById(R.id.hard_symbol);
        itemHardAmount = itemView.findViewById(R.id.hard_amount);
        incen3Layer = itemView.findViewById(R.id.incen3Layer);
        itemUsdxDenom = itemView.findViewById(R.id.usdx_symbol);
        itemUsdxAmount = itemView.findViewById(R.id.usdx_amount);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemMsgImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgClaimHardReward msg = Tx.MsgClaimHardReward.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemSender.setText(msg.getSender());
            if (msg.getDenomsToClaimList() != null) {
                itemMultiplier.setText(msg.getDenomsToClaim(0).getMultiplierName());
            }
            ArrayList<Coin> incentiveCoins = WDp.onParseKavaIncentiveGrpc(response, position);
            if (incentiveCoins.size() > 0) {
                incen0Layer.setVisibility(View.VISIBLE);
                WDp.showCoinDp(c, baseData, incentiveCoins.get(0), itemKavaDenom, itemKavaAmount, baseChain);
            }
            if (incentiveCoins.size() > 1) {
                incen1Layer.setVisibility(View.VISIBLE);
                WDp.showCoinDp(c, baseData, incentiveCoins.get(1), itemSwpDenom, itemSwpAmount, baseChain);
            }
            if (incentiveCoins.size() > 2) {
                incen2Layer.setVisibility(View.VISIBLE);
                WDp.showCoinDp(c, baseData, incentiveCoins.get(2), itemHardDenom, itemHardAmount, baseChain);
            }
            if (incentiveCoins.size() > 3) {
                incen3Layer.setVisibility(View.VISIBLE);
                WDp.showCoinDp(c, baseData, incentiveCoins.get(3), itemUsdxDenom, itemUsdxAmount, baseChain);
            }

        } catch (Exception e) { }
    }
}
