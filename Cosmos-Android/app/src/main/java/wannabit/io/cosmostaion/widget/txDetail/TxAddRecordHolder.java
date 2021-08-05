package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class TxAddRecordHolder extends TxHolder {
    ImageView itemAddRecordImg;
    TextView  itemAddRecordTopicName, itemAddRecordWriterAddress, itemAddRecordOwnerAddress, itemAddRecordFeePayerAddress;

    public TxAddRecordHolder(@NonNull View itemView) {
        super(itemView);
        itemAddRecordImg                = itemView.findViewById(R.id.tx_add_record_icon);
        itemAddRecordTopicName          = itemView.findViewById(R.id.tx_add_record_topic_name);
        itemAddRecordWriterAddress      = itemView.findViewById(R.id.tx_add_record_writer_address);
        itemAddRecordOwnerAddress       = itemView.findViewById(R.id.tx_add_record_owner_address);
        itemAddRecordFeePayerAddress    = itemView.findViewById(R.id.tx_add_record_fee_payer_address);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemAddRecordImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            panacea.aol.v2.MsgAddRecord msg = panacea.aol.v2.MsgAddRecord.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemAddRecordTopicName.setText(msg.getTopicName());
            itemAddRecordWriterAddress.setText(msg.getWriterAddress());
            itemAddRecordOwnerAddress.setText(msg.getOwnerAddress());
            itemAddRecordFeePayerAddress.setText(msg.getFeePayerAddress());

        } catch (Exception e) { }
    }
}
