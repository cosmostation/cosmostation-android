package wannabit.io.cosmostaion.widget.txDetail.kava;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import cosmos.tx.v1beta1.ServiceOuterClass;
import kava.incentive.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxSwapIncentiveHolder extends TxHolder {
    TextView        itemSwapIncentiveSender, itemSwapIncentiveName;
    RelativeLayout  incen0Layer, incen1Layer, incen2Layer, incen3Layer;
    TextView        itemKavaDenom, itemKavaAmount, itemSwpDenom, itemSwpAmount, itemHardDenom, itemHardAmount, itemUsdxDenom, itemUsdxAmount;

    public TxSwapIncentiveHolder(@NonNull View itemView) {
        super(itemView);
        itemSwapIncentiveSender         = itemView.findViewById(R.id.tx_incentive_sender);
        itemSwapIncentiveName           = itemView.findViewById(R.id.tx_multiplier_name);
        incen0Layer                     = itemView.findViewById(R.id.incen0Layer);
        itemKavaDenom                   = itemView.findViewById(R.id.kava_symbol);
        itemKavaAmount                  = itemView.findViewById(R.id.kava_amount);
        incen1Layer                     = itemView.findViewById(R.id.incen1Layer);
        itemSwpDenom                    = itemView.findViewById(R.id.swp_symbol);
        itemSwpAmount                   = itemView.findViewById(R.id.swp_amount);
        incen2Layer                     = itemView.findViewById(R.id.incen2Layer);
        itemHardDenom                   = itemView.findViewById(R.id.hard_symbol);
        itemHardAmount                  = itemView.findViewById(R.id.hard_amount);
        incen3Layer                     = itemView.findViewById(R.id.incen3Layer);
        itemUsdxDenom                   = itemView.findViewById(R.id.usdx_symbol);
        itemUsdxAmount                  = itemView.findViewById(R.id.usdx_amount);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        try {
            Tx.MsgClaimSwapReward msg = Tx.MsgClaimSwapReward.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemSwapIncentiveSender.setText(msg.getSender());
            if (msg.getDenomsToClaimList() != null) {
                itemSwapIncentiveName.setText(msg.getDenomsToClaim(0).getMultiplierName());
            }

            ArrayList<Coin> incentiveCoins = WDp.onParseKavaIncentiveGrpc(response, position);
            if (incentiveCoins.size() > 0) {
                incen0Layer.setVisibility(View.VISIBLE);
                WDp.setDpCoin(c, baseData, chainConfig, incentiveCoins.get(0), itemKavaDenom, itemKavaAmount);
            }
            if (incentiveCoins.size() > 1) {
                incen1Layer.setVisibility(View.VISIBLE);
                WDp.setDpCoin(c, baseData, chainConfig, incentiveCoins.get(1), itemSwpDenom, itemSwpAmount);
            }
            if (incentiveCoins.size() > 2) {
                incen2Layer.setVisibility(View.VISIBLE);
                WDp.setDpCoin(c, baseData, chainConfig, incentiveCoins.get(2), itemHardDenom, itemHardAmount);
            }
            if (incentiveCoins.size() > 3) {
                incen3Layer.setVisibility(View.VISIBLE);
                WDp.setDpCoin(c, baseData, chainConfig, incentiveCoins.get(3), itemUsdxDenom, itemUsdxAmount);
            }

        } catch (Exception e) { }
    }
}
