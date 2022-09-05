package wannabit.io.cosmostaion.widget;

import android.content.Intent;
import android.net.Uri;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.core.content.ContextCompat;

import org.jetbrains.annotations.NotNull;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.res.ResApiNewTxListCustom;
import wannabit.io.cosmostaion.utils.WDp;

public class HistoryNewHolder extends BaseHolder {
    private CardView historyRoot;
    private TextView historyType, historySuccess, history_time, history_amount, history_amount_symbol, history_time_gap;

    public HistoryNewHolder(@NonNull @NotNull View itemView) {
        super(itemView);
        historyRoot                     = itemView.findViewById(R.id.card_history);
        historyType                     = itemView.findViewById(R.id.history_type);
        historySuccess                  = itemView.findViewById(R.id.history_success);
        history_time                    = itemView.findViewById(R.id.history_time);
        history_time_gap                = itemView.findViewById(R.id.history_time_gap);
        history_amount                  = itemView.findViewById(R.id.history_amount);
        history_amount_symbol           = itemView.findViewById(R.id.history_amount_symobl);
    }

    public void onBindNewHistory(@NotNull MainActivity mainActivity, BaseData baseData, ChainConfig chainConfig, ResApiNewTxListCustom history) {
        final Coin coin = history.getDpCoin(mainActivity.mBaseChain);

        historyType.setText(history.getMsgType(mainActivity, mainActivity.mAccount.address));
        history_time.setText(WDp.getTimeTxformat(mainActivity, history.data.timestamp));
        history_time_gap.setText(WDp.getTimeTxGap(mainActivity, history.data.timestamp));

        if (coin != null) {
            history_amount_symbol.setVisibility(View.VISIBLE);
            history_amount.setVisibility(View.VISIBLE);
            WDp.setDpCoin(mainActivity, baseData, chainConfig, coin.denom, coin.amount, history_amount_symbol, history_amount);

        } else if (history.getMsgType(mainActivity, mainActivity.mAccount.address).equals(mainActivity.getString(R.string.tx_vote))) {
            history_amount_symbol.setVisibility(View.VISIBLE);
            history_amount_symbol.setText(history.getVoteOption());
            history_amount_symbol.setTextColor(ContextCompat.getColor(mainActivity, R.color.colorBlackDayNight));
            history_amount.setVisibility(View.GONE);

        } else {
            history_amount_symbol.setVisibility(View.INVISIBLE);
            history_amount.setVisibility(View.INVISIBLE);
        }

        if (history.isSuccess()) {
            historySuccess.setVisibility(View.GONE);
        } else {
            historySuccess.setVisibility(View.VISIBLE);
        }

        historyRoot.setOnClickListener(v -> {
            String url = chainConfig.explorerHistoryLink(history.data.txhash);
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
            mainActivity.startActivity(intent);
        });
    }
}
