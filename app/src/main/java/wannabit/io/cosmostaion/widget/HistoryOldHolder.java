package wannabit.io.cosmostaion.widget;

import static wannabit.io.cosmostaion.base.BaseConstant.EXPLORER_OEC_TX;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import org.jetbrains.annotations.NotNull;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.network.res.ResOkHistory;
import wannabit.io.cosmostaion.utils.WDp;

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
        history_block.setText(history.blockHeight + " block");
        historySuccess.setVisibility(View.GONE);
        historyRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = ChainFactory.getChain(mainActivity.mBaseChain).explorerUrl() + "txs/" + history.txHash;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                mainActivity.startActivity(intent);
            }
        });
    }

    public void onBindOldOkHistory(@NotNull MainActivity mainActivity, ResOkHistory.Data.Hit history) {
        String type = history.transactionDatas.get(0).type;
        if (type.contains("/")) {
            historyType.setText(type.split("/")[type.split("/").length - 1].replace("Msg", ""));
        } else {
            historyType.setText(type);
        }
        historySuccess.setVisibility(View.GONE);
        history_time.setText(WDp.getDpTime(mainActivity, history.blockTimeU0));
        history_time_gap.setText(WDp.getTimeTxGap(mainActivity, history.blockTimeU0));
        history_block.setText(history.hash + " block");
        historyRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = EXPLORER_OEC_TX + "tx/" + history.hash;
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                mainActivity.startActivity(intent);
            }
        });
    }
}
