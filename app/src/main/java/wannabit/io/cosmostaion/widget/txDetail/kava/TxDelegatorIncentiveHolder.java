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

public class TxDelegatorIncentiveHolder extends TxHolder {
    ImageView   itemDeleIncentiveImg;
    TextView    itemDeleIncentiveSender, itemDeleIncentiveName,
                itemDeleIncentiveKavaAmount, itemDeleIncentiveSwpAmount,
                itemDeleIncentiveHardAmount, itemDeleIncentiveUsdxAmount;

    public TxDelegatorIncentiveHolder(@NonNull View itemView) {
        super(itemView);
        itemDeleIncentiveImg             = itemView.findViewById(R.id.tx_msg_icon);
        itemDeleIncentiveSender          = itemView.findViewById(R.id.tx_incentive_sender);
        itemDeleIncentiveName           = itemView.findViewById(R.id.tx_multiplier_name);
//        itemDeleIncentiveKavaAmount     = itemView.findViewById(R.id.kava_amount);
//        itemDeleIncentiveSwpAmount      = itemView.findViewById(R.id.swp_amount);
//        itemDeleIncentiveHardAmount     = itemView.findViewById(R.id.hard_amount);
//        itemDeleIncentiveUsdxAmount     = itemView.findViewById(R.id.usdx_amount);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemDeleIncentiveImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgClaimDelegatorReward msg = Tx.MsgClaimDelegatorReward.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemDeleIncentiveSender.setText(msg.getSender());
//            itemDeleIncentiveKavaAmount.setText(WDp.getDpAmount2(c, new BigDecimal("0"), 6, 6));
//            itemDeleIncentiveSwpAmount.setText(WDp.getDpAmount2(c, new BigDecimal("0"), 6, 6));
//            itemDeleIncentiveHardAmount.setText(WDp.getDpAmount2(c, new BigDecimal("0"), 6, 6));
//            itemDeleIncentiveUsdxAmount.setText(WDp.getDpAmount2(c, new BigDecimal("0"), 6, 6));
            if (msg.getDenomsToClaimList() != null) {
                itemDeleIncentiveName.setText(msg.getDenomsToClaim(0).getMultiplierName());
            }

        } catch (Exception e) { }
    }
}