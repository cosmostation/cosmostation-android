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

public class TxSetAddressHolder extends TxHolder {
    ImageView itemAddressImg;
    TextView itemAddressTitle;
    TextView itemDelegator, itemWithdrawAddress;

    public TxSetAddressHolder(@NonNull View itemView) {
        super(itemView);
        itemAddressImg = itemView.findViewById(R.id.tx_address_icon);
        itemAddressTitle = itemView.findViewById(R.id.tx_address_text);
        itemDelegator = itemView.findViewById(R.id.tx_address_delegator);
        itemWithdrawAddress = itemView.findViewById(R.id.tx_address_withdraw);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemAddressImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            cosmos.distribution.v1beta1.Tx.MsgSetWithdrawAddress msg = cosmos.distribution.v1beta1.Tx.MsgSetWithdrawAddress.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemDelegator.setText(msg.getDelegatorAddress());
            itemWithdrawAddress.setText(msg.getWithdrawAddress());
        } catch (Exception e) {
        }
    }
}
