package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import java.util.ArrayList;

import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class TxReDelegateHolder extends TxHolder {
    ImageView       itemRedelegateImg;
    TextView        itemRedelegateTitle;
    TextView        itemReDelegator, itemFromValidator, itemFromMoniker, itemToValidator, itemToMoniker, itemRedelegateAmount, itemReDelegateAmountDenom;

    RelativeLayout  commission0Layer, commission1Layer, commission2Layer, commission3Layer;
    TextView        itemCommissionAmount0, itemCommissionDenom0, itemCommissionAmount1, itemCommissionDenom1,
                    itemCommissionAmount2, itemCommissionDenom2, itemCommissionAmount3, itemCommissionDenom3;

    public TxReDelegateHolder(@NonNull View itemView) {
        super(itemView);
        itemRedelegateImg = itemView.findViewById(R.id.tx_redelegate_icon);
        itemRedelegateTitle = itemView.findViewById(R.id.tx_undelegate_text);
        itemReDelegator = itemView.findViewById(R.id.tx_redelegate_redelegator);
        itemFromValidator = itemView.findViewById(R.id.tx_redelegate_from_validator);
        itemFromMoniker = itemView.findViewById(R.id.tx_redelegate_from_moniker);
        itemToValidator = itemView.findViewById(R.id.tx_redelegate_to_validator);
        itemToMoniker = itemView.findViewById(R.id.tx_redelegate_to_moniker);
        itemRedelegateAmount = itemView.findViewById(R.id.tx_redelegate_amount);
        itemReDelegateAmountDenom = itemView.findViewById(R.id.tx_redelegate_amount_symbol);
        commission0Layer = itemView.findViewById(R.id.commission_layout0);
        itemCommissionAmount0 = itemView.findViewById(R.id.tx_commission_amount0);
        itemCommissionDenom0 = itemView.findViewById(R.id.tx_commission_symbol0);
        commission1Layer = itemView.findViewById(R.id.commission_layout1);
        itemCommissionAmount1 = itemView.findViewById(R.id.tx_commission_amount1);
        itemCommissionDenom1 = itemView.findViewById(R.id.tx_commission_symbol1);
        commission2Layer = itemView.findViewById(R.id.commission_layout2);
        itemCommissionAmount2 = itemView.findViewById(R.id.tx_commission_amount2);
        itemCommissionDenom2 = itemView.findViewById(R.id.tx_commission_symbol2);
        commission3Layer = itemView.findViewById(R.id.commission_layout3);
        itemCommissionAmount3 = itemView.findViewById(R.id.tx_commission_amount3);
        itemCommissionDenom3 = itemView.findViewById(R.id.tx_commission_symbol3);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        itemRedelegateImg.setColorFilter(ContextCompat.getColor(c, chainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            cosmos.staking.v1beta1.Tx.MsgBeginRedelegate msg = cosmos.staking.v1beta1.Tx.MsgBeginRedelegate.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemReDelegator.setText(msg.getDelegatorAddress());
            itemFromValidator.setText(msg.getValidatorSrcAddress());
            itemToValidator.setText(msg.getValidatorDstAddress());
            itemFromMoniker.setText( "(" + baseData.getValidatorInfo(msg.getValidatorSrcAddress()).getDescription().getMoniker() + ")");
            itemToMoniker.setText( "(" + baseData.getValidatorInfo(msg.getValidatorDstAddress()).getDescription().getMoniker() + ")");
            WDp.setDpCoin(c, baseData, chainConfig, msg.getAmount().getDenom(), msg.getAmount().getAmount(), itemReDelegateAmountDenom, itemRedelegateAmount);

            ArrayList<Coin> commissionCoins = WDp.onParseAutoReward(response, address, position);
            if (commissionCoins.size() > 0) {
                commission0Layer.setVisibility(View.VISIBLE);
                WDp.setDpCoin(c, baseData, chainConfig, commissionCoins.get(0), itemCommissionDenom0, itemCommissionAmount0);
            }
            if (commissionCoins.size() > 1) {
                commission1Layer.setVisibility(View.VISIBLE);
                WDp.setDpCoin(c, baseData, chainConfig, commissionCoins.get(1), itemCommissionDenom1, itemCommissionAmount1);
            }
            if (commissionCoins.size() > 2) {
                commission2Layer.setVisibility(View.VISIBLE);
                WDp.setDpCoin(c, baseData, chainConfig, commissionCoins.get(2), itemCommissionDenom2, itemCommissionAmount2);
            }
            if (commissionCoins.size() > 3) {
                commission3Layer.setVisibility(View.VISIBLE);
                WDp.setDpCoin(c, baseData, chainConfig, commissionCoins.get(3), itemCommissionDenom3, itemCommissionAmount3);
            }

        } catch (Exception e) {}
    }
}
