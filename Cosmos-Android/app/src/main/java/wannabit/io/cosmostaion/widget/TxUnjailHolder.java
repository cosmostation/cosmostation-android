package wannabit.io.cosmostaion.widget;

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

public class TxUnjailHolder extends TxHolder{
    ImageView itemUnjailImg;
    TextView itemUnjailTitle;
    TextView itemValidator, itemMoniker;

    public TxUnjailHolder(@NonNull View itemView) {
        super(itemView);
        itemUnjailImg = itemView.findViewById(R.id.tx_unjail_icon);
        itemUnjailTitle = itemView.findViewById(R.id.tx_unjail_text);
        itemValidator = itemView.findViewById(R.id.tx_unjail_validator);
        itemMoniker = itemView.findViewById(R.id.tx_unjail_moniker);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemUnjailImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        try {
            cosmos.staking.v1beta1.Tx.MsgUndelegate msg = cosmos.staking.v1beta1.Tx.MsgUndelegate.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemValidator.setText(msg.getValidatorAddress());
            itemMoniker.setText("(" + baseData.getValidatorInfo(msg.getValidatorAddress()).getDescription().getMoniker() + ")");
        } catch (Exception e) {}
    }
}
