package wannabit.io.cosmostaion.widget;

import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import cosmos.tx.v1beta1.ServiceOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.TxDetailgRPCActivity;
import wannabit.io.cosmostaion.activities.WebActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;

public class TxCommonHolder extends TxHolder {
    ImageView itemStatusImg;
    TextView itemStatusTxt, itemFailTxt, itemHash, itemHeight, itemMsgCnt, itemGas,
            itemTime, itemTimeGap, itemMemo, itemFee, itemFeeDenom , itemFeeUsed, itemFeeUsedDenom,
            itemFeeLimit, itemFeeLimitDenom;
    RelativeLayout itemFeeLayer, itemFeeUsedLayer, itemFeeLimitLayer;
    ImageView itemBtnHashLink;

    public TxCommonHolder(@NonNull View itemView) {
        super(itemView);
        itemStatusImg = itemView.findViewById(R.id.tx_status_img);
        itemStatusTxt = itemView.findViewById(R.id.tx_status);
        itemFailTxt = itemView.findViewById(R.id.tx_fail_msg);
        itemHeight = itemView.findViewById(R.id.tx_block_height);
        itemMsgCnt = itemView.findViewById(R.id.tx_msg_cnt);
        itemGas = itemView.findViewById(R.id.tx_gas_info);
        itemTime = itemView.findViewById(R.id.tx_block_time);
        itemTimeGap = itemView.findViewById(R.id.tx_block_time_gap);
        itemHash = itemView.findViewById(R.id.tx_hash);
        itemMemo = itemView.findViewById(R.id.str_tx_memo);
        itemBtnHashLink = itemView.findViewById(R.id.tx_hash_link);

        itemFeeLayer = itemView.findViewById(R.id.tx_fee_layer);
        itemFee = itemView.findViewById(R.id.tx_fee);
        itemFeeDenom = itemView.findViewById(R.id.tx_fee_symbol);
        itemFeeUsedLayer = itemView.findViewById(R.id.tx_fee_used_layer);
        itemFeeUsed = itemView.findViewById(R.id.tx_used_fee);
        itemFeeUsedDenom = itemView.findViewById(R.id.tx_fee_used_symbol);
        itemFeeLimitLayer = itemView.findViewById(R.id.tx_fee_limit_layer);
        itemFeeLimit = itemView.findViewById(R.id.tx_limit_fee);
        itemFeeLimitDenom = itemView.findViewById(R.id.tx_fee_limit_symbol);
    }

    public void onBindCommon(TxDetailgRPCActivity c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, boolean isGen) {
        WDp.DpMainDenom(c, baseChain.getChain(), itemFeeDenom);
        WDp.DpMainDenom(c, baseChain.getChain(), itemFeeUsedDenom);
        WDp.DpMainDenom(c, baseChain.getChain(), itemFeeLimitDenom);
        if (response.getTxResponse().getCode() != 0) {
            itemStatusImg.setImageDrawable(c.getDrawable(R.drawable.fail_ic));
            itemStatusTxt.setText(R.string.str_failed_c);
            itemFailTxt.setVisibility(View.VISIBLE);
        } else {
            itemStatusImg.setImageDrawable(c.getDrawable(R.drawable.success_ic));
            itemStatusTxt.setText(R.string.str_success_c);
        }
        itemHeight.setText("" + response.getTxResponse().getHeight());
        itemMsgCnt.setText("" + response.getTx().getBody().getMessagesCount());
        itemGas.setText(String.format("%s / %s", ""+response.getTxResponse().getGasUsed() , ""+response.getTxResponse().getGasWanted()));
        itemFee.setText(WDp.getDpAmount2(c, WDp.onParseFee(response), 6, 6));
        itemFeeLayer.setVisibility(View.VISIBLE);
        itemTime.setText(WDp.getTimeTxformat(c, response.getTxResponse().getTimestamp()));
        itemTimeGap.setText(WDp.getTimeTxGap(c, response.getTxResponse().getTimestamp()));
        itemHash.setText(response.getTxResponse().getTxhash());
        itemMemo.setText(response.getTx().getBody().getMemo());

        itemBtnHashLink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent webintent = new Intent(c, WebActivity.class);
                webintent.putExtra("txid", response.getTxResponse().getTxhash());
                webintent.putExtra("chain", baseChain.getChain());
                webintent.putExtra("goMain", isGen);
                c.startActivity(webintent);
            }
        });
    }
}
