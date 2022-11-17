package wannabit.io.cosmostaion.widget;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import org.apache.commons.lang3.StringUtils;
import org.jetbrains.annotations.NotNull;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
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

    public void onBindOldBnbHistory(@NotNull MainActivity mainActivity, ChainConfig chainConfig, BnbHistory history) {
        historyType.setText(WDp.DpBNBTxType(mainActivity, history, mainActivity.mAccount.address));
        history_time.setText(WDp.getTimeformat(mainActivity, history.timeStamp));
        history_time_gap.setText(WDp.getTimeGap(mainActivity, history.timeStamp));
        history_block.setText(history.blockHeight + " block");
        historySuccess.setVisibility(View.GONE);

        historyRoot.setOnClickListener(v -> {
            String url = chainConfig.explorerHistoryLink(history.txHash);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mainActivity.startActivity(intent);
        });
    }

    public void onBindOldOkHistory(@NotNull MainActivity mainActivity, ChainConfig chainConfig, ResOkHistory.Data.transactionData history) {
        historyType.setText(history.txId);
        historySuccess.setVisibility(View.GONE);
        history_time.setText(WDp.getDpTime(mainActivity, Long.parseLong(history.transactionTime)));
        history_time_gap.setText(WDp.getOkcTimeTxGap(mainActivity, Long.parseLong(history.transactionTime)));
        history_block.setText(history.height + " block");

        historyRoot.setOnClickListener(v -> {
            String url = chainConfig.explorerHistoryLink(history.txId);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mainActivity.startActivity(intent);
        });
    }
}
