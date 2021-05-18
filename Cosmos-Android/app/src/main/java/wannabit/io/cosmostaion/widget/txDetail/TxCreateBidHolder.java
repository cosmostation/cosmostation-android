package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

import akash.market.v1beta1.BidOuterClass;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class TxCreateBidHolder extends TxHolder{
    ImageView itemCreateBidImg;
    TextView itemCreateBidTitle;
    TextView itemOwner, itemProvider, itemCreateBidPrice, itemCreateBidPriceDenom, itemCreateBidDeposit, itemCreateBidDepositDenom;

    public TxCreateBidHolder(@NonNull View itemView) {
        super(itemView);
        itemCreateBidImg = itemView.findViewById(R.id.tx_create_bid_icon);
        itemCreateBidTitle = itemView.findViewById(R.id.tx_create_bid_text);
        itemOwner = itemView.findViewById(R.id.tx_create_bid_owner);
        itemProvider = itemView.findViewById(R.id.tx_create_bid_provider);
        itemCreateBidPrice  = itemView.findViewById(R.id.tx_create_bid_price);
        itemCreateBidPriceDenom = itemView.findViewById(R.id.tx_create_bid_price_symbol);
        itemCreateBidDeposit  = itemView.findViewById(R.id.tx_create_bid_deposit);
        itemCreateBidDepositDenom = itemView.findViewById(R.id.tx_create_bid_deposit_symbol);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        WDp.DpMainDenom(c, baseChain.getChain(), itemCreateBidPriceDenom);
        WDp.DpMainDenom(c, baseChain.getChain(), itemCreateBidDepositDenom);
        final int dpDecimal = WDp.mainDivideDecimal(baseChain);
        itemCreateBidImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            BidOuterClass.MsgCreateBid msg = BidOuterClass.MsgCreateBid.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemOwner.setText(msg.getOrder().getOwner());
            itemProvider.setText(msg.getProvider());
            itemCreateBidPrice.setText(WDp.getDpAmount2(c, new BigDecimal(msg.getPrice().getAmount()), dpDecimal, dpDecimal));
            itemCreateBidDeposit.setText(WDp.getDpAmount2(c, new BigDecimal(msg.getDeposit().getAmount()), dpDecimal, dpDecimal));
        } catch (Exception e) {}
    }
}
