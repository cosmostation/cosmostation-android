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

public class TxDelegatorIncentiveHolder extends TxHolder {
    TextView        itemDeleIncentiveSender, itemDeleIncentiveName;
    RelativeLayout  incen0Layer, incen1Layer, incen2Layer, incen3Layer;
    TextView        incen0Denom, incen0Amount, incen1Denom, incen1Amount, incen2Denom, incen2Amount, incen3Denom, incen3Amount;

    public TxDelegatorIncentiveHolder(@NonNull View itemView) {
        super(itemView);
        itemDeleIncentiveSender = itemView.findViewById(R.id.tx_incentive_sender);
        itemDeleIncentiveName = itemView.findViewById(R.id.tx_multiplier_name);
        incen0Layer = itemView.findViewById(R.id.incen0Layer);
        incen0Denom = itemView.findViewById(R.id.incen0_symbol);
        incen0Amount = itemView.findViewById(R.id.incen0_amount);
        incen1Layer = itemView.findViewById(R.id.incen1Layer);
        incen1Denom = itemView.findViewById(R.id.incen1_symbol);
        incen1Amount = itemView.findViewById(R.id.incen1_amount);
        incen2Layer = itemView.findViewById(R.id.incen2Layer);
        incen2Denom = itemView.findViewById(R.id.incen2_symbol);
        incen2Amount = itemView.findViewById(R.id.incen2_amount);
        incen3Layer = itemView.findViewById(R.id.incen3Layer);
        incen3Denom = itemView.findViewById(R.id.incen3_symbol);
        incen3Amount = itemView.findViewById(R.id.incen3_amount);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        try {
            Tx.MsgClaimDelegatorReward msg = Tx.MsgClaimDelegatorReward.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemDeleIncentiveSender.setText(msg.getSender());
            if (msg.getDenomsToClaimList() != null) {
                itemDeleIncentiveName.setText(msg.getDenomsToClaim(0).getMultiplierName());
            }

            ArrayList<Coin> incentiveCoins = WDp.onParseKavaIncentiveGrpc(response, position);
            if (incentiveCoins.size() > 0) {
                incen0Layer.setVisibility(View.VISIBLE);
                WDp.setDpCoin(c, baseData, chainConfig, incentiveCoins.get(0), incen0Denom, incen0Amount);
            }
            if (incentiveCoins.size() > 1) {
                incen1Layer.setVisibility(View.VISIBLE);
                WDp.setDpCoin(c, baseData, chainConfig, incentiveCoins.get(1), incen1Denom, incen1Amount);
            }
            if (incentiveCoins.size() > 2) {
                incen2Layer.setVisibility(View.VISIBLE);
                WDp.setDpCoin(c, baseData, chainConfig, incentiveCoins.get(2), incen2Denom, incen2Amount);
            }
            if (incentiveCoins.size() > 3) {
                incen3Layer.setVisibility(View.VISIBLE);
                WDp.setDpCoin(c, baseData, chainConfig, incentiveCoins.get(3), incen3Denom, incen3Amount);
            }

        } catch (Exception e) { }
    }
}