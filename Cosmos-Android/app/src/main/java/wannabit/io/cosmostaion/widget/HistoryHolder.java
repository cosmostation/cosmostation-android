package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.TxDetailActivity;
import wannabit.io.cosmostaion.activities.tokenDetail.NativeTokenDetailActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.network.res.ResApiTxList;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class HistoryHolder extends BaseHolder {

    private CardView    historyRoot;
    private TextView    historyType, historySuccess, history_time, history_block,
                        history_time_gap;

    public HistoryHolder(@NonNull View itemView) {
        super(itemView);
        historyRoot             = itemView.findViewById(R.id.card_history);
        historyType             = itemView.findViewById(R.id.history_type);
        historySuccess          = itemView.findViewById(R.id.history_success);
        history_time            = itemView.findViewById(R.id.history_time);
        history_block           = itemView.findViewById(R.id.history_block_height);
        history_time_gap        = itemView.findViewById(R.id.history_time_gap);
    }

    public void onBindHistory(BaseActivity c, ResApiTxList.Data tx, String address) {
        if (tx != null) {
            if (tx.logs != null) {
                historySuccess.setVisibility(View.GONE);
            } else {
                historySuccess.setVisibility(View.VISIBLE);
            }
            historyType.setText(WDp.DpTxType(c, tx.messages, address));
            history_time.setText(WDp.getTimeTxformat(c, tx.time));
            history_time_gap.setText(WDp.getTimeTxGap(c, tx.time));
            history_block.setText("" + tx.height + " block");
            historyRoot.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent txDetail = new Intent(c, TxDetailActivity.class);
                    txDetail.putExtra("txHash", tx.tx_hash);
                    txDetail.putExtra("isGen", false);
                    txDetail.putExtra("isSuccess", true);
                    c.startActivity(txDetail);
                }
            });

        }
    }
}
