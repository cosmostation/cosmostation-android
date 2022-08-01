package wannabit.io.cosmostaion.widget.txDetail.kava;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import kava.cdp.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxCdpLiquidateHolder extends TxHolder {
    TextView  itemKeeper, itemBorrower, itemType;

    public TxCdpLiquidateHolder(@NonNull View itemView) {
        super(itemView);
        itemKeeper = itemView.findViewById(R.id.cdp_keeper);
        itemBorrower = itemView.findViewById(R.id.cdp_borrower);
        itemType = itemView.findViewById(R.id.cdp_type);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        try {
            Tx.MsgLiquidate msg = Tx.MsgLiquidate.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemKeeper.setText(msg.getKeeper());
            itemBorrower.setText(msg.getBorrower());
            itemType.setText(msg.getCollateralType());

        } catch (Exception e) { }
    }
}
