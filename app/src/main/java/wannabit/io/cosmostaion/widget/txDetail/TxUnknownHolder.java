package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;

import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;

public class TxUnknownHolder extends TxHolder {
    ImageView itemUnknownImg;
    TextView itemUnknownTitle;

    public TxUnknownHolder(@NonNull View itemView) {
        super(itemView);
        itemUnknownImg = itemView.findViewById(R.id.tx_unknown_icon);
        itemUnknownTitle = itemView.findViewById(R.id.tx_unknown_text);
    }

    public void onBindMsg(Context c, BaseData baseData, ChainConfig chainConfig, ServiceOuterClass.GetTxResponse response, int position, String address) {
        itemUnknownImg.setColorFilter(ContextCompat.getColor(c, chainConfig.chainColor()), android.graphics.PorterDuff.Mode.SRC_IN);
    }
}
