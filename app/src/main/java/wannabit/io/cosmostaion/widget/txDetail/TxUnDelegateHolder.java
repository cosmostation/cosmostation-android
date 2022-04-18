package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;

import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;

public class TxUnDelegateHolder extends TxHolder {
    ImageView       itemUndelegateImg;
    TextView        itemUndelegateTitle;
    TextView        itemUnDelegator, itemValidator, itemMoniker, itemUndelegateAmount, itemUnDelegateAmountDenom;

    RelativeLayout  commission0Layer, commission1Layer, commission2Layer, commission3Layer;
    TextView        itemCommissionAmount0, itemCommissionDenom0, itemCommissionAmount1, itemCommissionDenom1,
                    itemCommissionAmount2, itemCommissionDenom2, itemCommissionAmount3, itemCommissionDenom3;

    public TxUnDelegateHolder(@NonNull View itemView) {
        super(itemView);
        itemUndelegateImg = itemView.findViewById(R.id.tx_undelegate_icon);
        itemUndelegateTitle = itemView.findViewById(R.id.tx_undelegate_text);
        itemUnDelegator = itemView.findViewById(R.id.tx_undelegate_undelegator);
        itemValidator = itemView.findViewById(R.id.tx_undelegate_validator);
        itemMoniker = itemView.findViewById(R.id.tx_undelegate_moniker);
        itemUndelegateAmount = itemView.findViewById(R.id.tx_undelegate_amount);
        itemUnDelegateAmountDenom = itemView.findViewById(R.id.tx_undelegate_amount_symbol);
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

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        WDp.DpMainDenom(c, baseChain.getChain(), itemUnDelegateAmountDenom);
        final int dpDecimal = WDp.mainDivideDecimal(baseChain);
        itemUndelegateImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            cosmos.staking.v1beta1.Tx.MsgUndelegate msg = cosmos.staking.v1beta1.Tx.MsgUndelegate.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemUnDelegator.setText(msg.getDelegatorAddress());
            itemValidator.setText(msg.getValidatorAddress());
            itemMoniker.setText( "(" + baseData.getValidatorInfo(msg.getValidatorAddress()).getDescription().getMoniker() + ")");
            itemUndelegateAmount.setText(WDp.getDpAmount2(c, new BigDecimal(msg.getAmount().getAmount()), dpDecimal, dpDecimal));

            ArrayList<Coin> commissionCoins = WDp.onParseAutoReward(response, address, position);
            if (commissionCoins.size() > 0) {
                commission0Layer.setVisibility(View.VISIBLE);
                WDp.showCoinDp(c, baseData, commissionCoins.get(0), itemCommissionDenom0, itemCommissionAmount0, baseChain);
            }
            if (commissionCoins.size() > 1) {
                commission1Layer.setVisibility(View.VISIBLE);
                WDp.showCoinDp(c, baseData, commissionCoins.get(1), itemCommissionDenom1, itemCommissionAmount1, baseChain);
            }
            if (commissionCoins.size() > 2) {
                commission2Layer.setVisibility(View.VISIBLE);
                WDp.showCoinDp(c, baseData, commissionCoins.get(2), itemCommissionDenom2, itemCommissionAmount2, baseChain);
            }
            if (commissionCoins.size() > 3) {
                commission3Layer.setVisibility(View.VISIBLE);
                WDp.showCoinDp(c, baseData, commissionCoins.get(3), itemCommissionDenom3, itemCommissionAmount3, baseChain);
            }

        } catch (Exception e) {}
    }

}
