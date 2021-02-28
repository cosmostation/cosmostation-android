package wannabit.io.cosmostaion.widget;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import wannabit.io.cosmostaion.R;

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
}
