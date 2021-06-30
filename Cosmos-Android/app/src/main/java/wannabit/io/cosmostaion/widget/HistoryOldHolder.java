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
import wannabit.io.cosmostaion.activities.TxDetailActivity;
import wannabit.io.cosmostaion.activities.TxDetailgRPCActivity;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.network.res.ResApiTxList;
import wannabit.io.cosmostaion.network.res.ResApiTxListCustom;
import wannabit.io.cosmostaion.network.res.ResOkHistory;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.PERSISTENCE_COSMOS_EVENT_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.PERSISTENCE_COSMOS_EVENT_END;
import static wannabit.io.cosmostaion.base.BaseConstant.PERSISTENCE_COSMOS_EVENT_START;
import static wannabit.io.cosmostaion.base.BaseConstant.PERSISTENCE_KAVA_EVENT_ADDRESS;
import static wannabit.io.cosmostaion.base.BaseConstant.PERSISTENCE_KAVA_EVENT_END;
import static wannabit.io.cosmostaion.base.BaseConstant.PERSISTENCE_KAVA_EVENT_START;
import static wannabit.io.cosmostaion.base.BaseConstant.TX_TYPE_SEND;

public class HistoryOldHolder extends HistoryHolder {
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

    public void onBindOldGrpcHistory(@NotNull MainActivity mainActivity, ResApiTxListCustom history) {
        historyType.setText(history.getMsgType(mainActivity, mainActivity.mAccount.address));
        history_time.setText(WDp.getTimeTxformat(mainActivity, history.timestamp));
        history_time_gap.setText(WDp.getTimeTxGap(mainActivity, history.timestamp));
        history_block.setText(history.height + "block");
        if (history.isSuccess()) {
            historySuccess.setVisibility(View.GONE);
        } else {
            historySuccess.setVisibility(View.VISIBLE);
        }
        historyRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(history.chain_id) && !mainActivity.getBaseDao().getChainIdGrpc().equals(history.chain_id)) {
                    String url = WUtil.getTxExplorer(mainActivity.mBaseChain, history.tx_hash);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    mainActivity.startActivity(intent);
                } else {
                    Intent txDetail = new Intent(mainActivity, TxDetailgRPCActivity.class);
                    txDetail.putExtra("txHash", history.tx_hash);
                    txDetail.putExtra("isGen", false);
                    txDetail.putExtra("isSuccess", true);
                    mainActivity.startActivity(txDetail);
                }
            }
        });
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

    public void onBindOldHistory(@NotNull MainActivity mainActivity, ResApiTxList.Data tx) {
        historyType.setTextColor(mainActivity.getResources().getColor(R.color.colorWhite));
        historyRoot.setCardBackgroundColor(mainActivity.getResources().getColor(R.color.colorTransBg));
        if (tx.logs != null) {
            historySuccess.setVisibility(View.GONE);
        } else {
            historySuccess.setVisibility(View.VISIBLE);
        }
        historyType.setText(WDp.DpTxType(mainActivity, tx.messages, mainActivity.mAccount.address));
        history_time.setText(WDp.getTimeTxformat(mainActivity, tx.time));
        history_time_gap.setText(WDp.getTimeTxGap(mainActivity, tx.time));
        history_block.setText("" + tx.height + "block");
        historyRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!TextUtils.isEmpty(tx.chain_id) && !mainActivity.getBaseDao().getChainId().equals(tx.chain_id)) {
                    String url = WUtil.getTxExplorer(mainActivity.mBaseChain, tx.tx_hash);
                    Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                    mainActivity.startActivity(intent);

                } else {
                    Intent txDetail = new Intent(mainActivity, TxDetailActivity.class);
                    txDetail.putExtra("txHash", tx.tx_hash);
                    txDetail.putExtra("isGen", false);
                    txDetail.putExtra("isSuccess", true);
                    mainActivity.startActivity(txDetail);
                }
            }
        });

        //TODO STAKE DROP EVENT
        if (WDp.getHistoryDpType(tx.messages, mainActivity.mAccount.address) == TX_TYPE_SEND) {
            if (tx.height > PERSISTENCE_COSMOS_EVENT_START && tx.height < PERSISTENCE_COSMOS_EVENT_END) {
                if (tx.messages.get(0).value.to_address.equals(PERSISTENCE_COSMOS_EVENT_ADDRESS) && tx.messages.get(0).value.from_address.equals(mainActivity.mAccount.address)) {
                    historyType.setText("Persistence\nStake Drop");
                    historyType.setTextColor(mainActivity.getResources().getColor(R.color.colorStakeDrop));
                    historyRoot.setCardBackgroundColor(mainActivity.getResources().getColor(R.color.colorStakeDropBG));
                }

            } else if (tx.height > PERSISTENCE_KAVA_EVENT_START && tx.height < PERSISTENCE_KAVA_EVENT_END) {
                if (tx.messages.get(0).value.to_address.equals(PERSISTENCE_KAVA_EVENT_ADDRESS) && tx.messages.get(0).value.from_address.equals(mainActivity.mAccount.address)) {
                    historyType.setText("Persistence\nStake Drop");
                    historyType.setTextColor(mainActivity.getResources().getColor(R.color.colorStakeDrop));
                    historyRoot.setCardBackgroundColor(mainActivity.getResources().getColor(R.color.colorStakeDropBG));
                }

            }
        }
    }
}
