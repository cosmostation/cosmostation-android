package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;

public class TxHolder extends RecyclerView.ViewHolder {
    public TxHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
    }
}
