package wannabit.io.cosmostaion.widget.txDetail.certik;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import shentu.oracle.v1alpha1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxTaskResponseHolder extends TxHolder {
    private ImageView itemTaskResponseImg;
    private TextView itemTaskResponseOperator;
    private TextView itemTaskResponseContract;
    private TextView itemTaskResponseScore;

    public TxTaskResponseHolder(@NonNull View itemView) {
        super(itemView);
        itemTaskResponseImg = itemView.findViewById(R.id.tx_task_response_icon);
        itemTaskResponseOperator = itemView.findViewById(R.id.tx_task_response_operator);
        itemTaskResponseContract = itemView.findViewById(R.id.tx_task_response_contract);
        itemTaskResponseScore = itemView.findViewById(R.id.tx_task_response_score);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemTaskResponseImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgTaskResponse msg = Tx.MsgTaskResponse.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemTaskResponseOperator.setText(msg.getOperator());
            itemTaskResponseContract.setText(msg.getContract());
            itemTaskResponseScore.setText("" + msg.getScore());
        } catch (Exception e) {
        }
    }
}
