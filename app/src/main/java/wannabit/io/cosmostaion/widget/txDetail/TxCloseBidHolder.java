package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import akash.market.v1beta2.BidOuterClass;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class TxCloseBidHolder extends TxHolder {
    ImageView itemCloseBidImg;
    TextView itemCloseBidTitle;
    TextView itemProvider, itemOwner, itemDseq, itemGseq, itemOseq;

    public TxCloseBidHolder(@NonNull View itemView) {
        super(itemView);
        itemCloseBidImg = itemView.findViewById(R.id.tx_close_bid_icon);
        itemCloseBidTitle = itemView.findViewById(R.id.tx_close_bid_text);
        itemProvider = itemView.findViewById(R.id.tx_close_bid_provider);
        itemOwner = itemView.findViewById(R.id.tx_close_bid_owner);
        itemDseq = itemView.findViewById(R.id.tx_close_bid_dseq);
        itemGseq = itemView.findViewById(R.id.tx_close_bid_gseq);
        itemOseq = itemView.findViewById(R.id.tx_close_bid_oseq);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemCloseBidImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        try {
            BidOuterClass.MsgCloseBid msg = BidOuterClass.MsgCloseBid.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemProvider.setText(msg.getBidId().getProvider());
            itemOwner.setText(msg.getBidId().getOwner());
            itemDseq.setText("" + WDp.getDecimalFormat(0).format(msg.getBidId().getDseq()));
            itemGseq.setText("" + msg.getBidId().getGseq());
            itemOseq.setText("" + msg.getBidId().getOseq());
        } catch (Exception e) {
        }
    }
}
