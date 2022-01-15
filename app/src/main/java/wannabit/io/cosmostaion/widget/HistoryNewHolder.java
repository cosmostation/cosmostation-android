package wannabit.io.cosmostaion.widget;

import android.content.Intent;
import android.net.Uri;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import org.jetbrains.annotations.NotNull;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.TxDetailgRPCActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.res.ResApiNewTxListCustom;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

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

    public void onBindNewHistory(@NotNull MainActivity mainActivity, BaseData baseData, ResApiNewTxListCustom history) {
        historyType.setText(history.getMsgType(mainActivity, mainActivity.mAccount.address));
        history_time.setText(WDp.getTimeTxformat(mainActivity, history.data.timestamp));
        history_time_gap.setText(WDp.getTimeTxGap(mainActivity, history.data.timestamp));
        final Coin coin = history.getDpCoin(mainActivity.mBaseChain);
        if (coin != null) {
            history_amount_symbol.setVisibility(View.VISIBLE);
            history_amount.setVisibility(View.VISIBLE);
            WDp.showCoinDp(mainActivity, baseData, coin.denom, coin.amount, history_amount_symbol, history_amount, mainActivity.mBaseChain);
        } else if (history.getMsgType(mainActivity, mainActivity.mAccount.address).equals(mainActivity.getString(R.string.tx_vote))) {
            history_amount_symbol.setVisibility(View.VISIBLE);
            history_amount_symbol.setText(history.getVoteOption());
            history_amount_symbol.setTextColor(mainActivity.getResources().getColor(R.color.colorWhite));
            history_amount.setVisibility(View.GONE);
        } else {
            history_amount_symbol.setVisibility(View.GONE);
            history_amount.setVisibility(View.GONE);
        }
        if (history.isSuccess()) {
            historySuccess.setVisibility(View.GONE);
        } else {
            historySuccess.setVisibility(View.VISIBLE);
        }
        historyRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(history.header.chain_id) && !mainActivity.getBaseDao().getChainIdGrpc().equals(history.header.chain_id)) {
                    String url = WUtil.getTxExplorer(mainActivity.mBaseChain, history.data.txhash);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    mainActivity.startActivity(intent);
                } else {
                    Intent txDetail = new Intent(mainActivity, TxDetailgRPCActivity.class);
                    txDetail.putExtra("txHash", history.data.txhash);
                    txDetail.putExtra("isGen", false);
                    txDetail.putExtra("isSuccess", true);
                    mainActivity.startActivity(txDetail);
                }
            }
        });
    }
}
