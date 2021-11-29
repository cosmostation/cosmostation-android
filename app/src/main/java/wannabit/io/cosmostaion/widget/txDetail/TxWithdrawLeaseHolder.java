package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;

import akash.market.v1beta2.LeaseOuterClass;
import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class TxWithdrawLeaseHolder extends TxHolder{
    ImageView itemWithdrawLeaseImg;
    TextView itemWithdrawLeaseTitle;
    TextView itemProvider, itemOwner, itemDseq, itemGseq, itemOseq;

    public TxWithdrawLeaseHolder(@NonNull View itemView) {
        super(itemView);
        itemWithdrawLeaseImg        = itemView.findViewById(R.id.tx_withdraw_lease_icon);
        itemWithdrawLeaseTitle      = itemView.findViewById(R.id.tx_withdraw_lease_text);
        itemProvider                = itemView.findViewById(R.id.tx_withdraw_lease_provider);
        itemOwner                   = itemView.findViewById(R.id.tx_withdraw_lease_owner);
        itemDseq                    = itemView.findViewById(R.id.tx_withdraw_lease_dseq);
        itemGseq                    = itemView.findViewById(R.id.tx_withdraw_lease_gseq);
        itemOseq                    = itemView.findViewById(R.id.tx_withdraw_lease_oseq);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemWithdrawLeaseImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        try {
            LeaseOuterClass.MsgWithdrawLease msg = LeaseOuterClass.MsgWithdrawLease.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemProvider.setText(msg.getBidId().getProvider());
            itemOwner.setText(msg.getBidId().getOwner());
            DecimalFormat format = new DecimalFormat("###,###");
            itemDseq.setText("" + format.format(msg.getBidId().getDseq()));
            itemGseq.setText("" + msg.getBidId().getGseq());
            itemOseq.setText("" + msg.getBidId().getOseq());
        } catch (Exception e) {}
    }
}
