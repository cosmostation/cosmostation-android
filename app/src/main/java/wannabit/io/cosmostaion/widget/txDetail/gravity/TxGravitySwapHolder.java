package wannabit.io.cosmostaion.widget.txDetail.gravity;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

import cosmos.tx.v1beta1.ServiceOuterClass;
import tendermint.liquidity.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxGravitySwapHolder extends TxHolder {
    ImageView itemGravitySwapImg;
    TextView itemGravitySwapRequestAddress, itemGravitySwapPoolId,
            itemGravitySwapDemandDenom, itemGravitySwapOrderPrice,
            itemGravitySwapOfferAmount, itemGravitySwapOfferSymbol;

    public TxGravitySwapHolder(@NonNull View itemView) {
        super(itemView);
        itemGravitySwapImg = itemView.findViewById(R.id.tx_gravity_swap_icon);
        itemGravitySwapRequestAddress = itemView.findViewById(R.id.tx_gravity_swap_request_address);
        itemGravitySwapPoolId = itemView.findViewById(R.id.tx_gravity_swap_pool_id);
        itemGravitySwapDemandDenom = itemView.findViewById(R.id.tx_gravity_swap_demand_coin_denom);
        itemGravitySwapOrderPrice = itemView.findViewById(R.id.tx_gravity_swap_order_price);
        itemGravitySwapOfferAmount = itemView.findViewById(R.id.tx_token_swap_amount);
        itemGravitySwapOfferSymbol = itemView.findViewById(R.id.tx_token_swap_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemGravitySwapImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgSwapWithinBatch msg = Tx.MsgSwapWithinBatch.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemGravitySwapRequestAddress.setText(msg.getSwapRequesterAddress());
            itemGravitySwapPoolId.setText("" + msg.getPoolId());
            WUtil.dpCosmosTokenName(c, baseData, itemGravitySwapDemandDenom, msg.getDemandCoinDenom());
            itemGravitySwapOrderPrice.setText(WDp.getDpAmount2(new BigDecimal(msg.getOrderPrice()), 18, 18));

            Coin coin = new Coin(msg.getOfferCoin().getDenom(), msg.getOfferCoin().getAmount());
            WDp.showCoinDp(c, baseData, coin, itemGravitySwapOfferSymbol, itemGravitySwapOfferAmount, BaseChain.COSMOS_MAIN);
        } catch (Exception e) {
        }
    }
}
