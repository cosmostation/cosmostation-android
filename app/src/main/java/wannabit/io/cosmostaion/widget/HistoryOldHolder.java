package wannabit.io.cosmostaion.widget;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import org.jetbrains.annotations.NotNull;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.TxDetailActivity;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.network.res.ResOkHistory;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class HistoryOldHolder extends BaseHolder {
    private CardView historyRoot;
    private TextView historyType, historySuccess, history_time, history_block, history_time_gap;

    public HistoryOldHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        historyRoot = itemView.findViewById(R.id.card_history);
        historyType = itemView.findViewById(R.id.history_type);
        historySuccess = itemView.findViewById(R.id.history_success);
        history_time = itemView.findViewById(R.id.history_time);
        history_block = itemView.findViewById(R.id.history_block_height);
        history_time_gap = itemView.findViewById(R.id.history_time_gap);
    }

    public void onBindOldBnbHistory(@NotNull MainActivity mainActivity, BnbHistory history) {
        historyType.setText(WDp.DpBNBTxType(mainActivity, history, mainActivity.mAccount.address));
        history_time.setText(WDp.getTimeformat(mainActivity, history.timeStamp));
        history_time_gap.setText(WDp.getTimeGap(mainActivity, history.timeStamp));
        history_block.setText(history.blockHeight + "block");
        historySuccess.setVisibility(View.GONE);
        historyRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (history.txType.equals("HTL_TRANSFER") || history.txType.equals("CLAIM_HTL") || history.txType.equals("REFUND_HTL") || history.txType.equals("TRANSFER")) {
                    Intent txDetail = new Intent(mainActivity, TxDetailActivity.class);
                    txDetail.putExtra("txHash", history.txHash);
                    txDetail.putExtra("isGen", false);
                    txDetail.putExtra("isSuccess", true);
                    txDetail.putExtra("bnbTime", history.timeStamp);
                    mainActivity.startActivity(txDetail);

                } else {
                    String url = WUtil.getTxExplorer(mainActivity.mBaseChain, history.txHash);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    mainActivity.startActivity(intent);
                }
            }
        });
    }

    public void onBindOldOkHistory(@NotNull MainActivity mainActivity, ResOkHistory.DataDetail history) {
        historySuccess.setVisibility(View.GONE);
        historyType.setText(WDp.DpOkTxType(mainActivity, history));
        history_time.setText(WDp.getDpTime(mainActivity, history.timestamp * 1000));
        history_time_gap.setText(WDp.getTimeTxGap(mainActivity, history.timestamp * 1000));
        history_block.setText(history.txhash + "block");
        historyRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = WUtil.getTxExplorer(mainActivity.mBaseChain, history.txhash);
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                mainActivity.startActivity(intent);
            }
        });
    }
}
