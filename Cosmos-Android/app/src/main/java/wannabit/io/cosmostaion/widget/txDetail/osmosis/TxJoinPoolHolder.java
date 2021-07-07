package wannabit.io.cosmostaion.widget.txDetail.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

import cosmos.tx.v1beta1.ServiceOuterClass;
import osmosis.gamm.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxJoinPoolHolder extends TxHolder {
    ImageView itemJoinPoolImg;
    LinearLayout itemJoinPoolTokenMaxLayer;
    TextView itemJoinSender, itemJoinPoolId, itemJoinPoolShareOutAmount,
             itemJoinPoolTokenInMaxsSymbol1, itemJoinPoolTokenInMaxsAmount1, itemJoinPoolTokenInMaxsSymbol2, itemJoinPoolTokenInMaxsAmount2;

    public TxJoinPoolHolder(@NonNull View itemView) {
        super(itemView);
        itemJoinPoolImg = itemView.findViewById(R.id.tx_join_pool_icon);
        itemJoinPoolTokenMaxLayer = itemView.findViewById(R.id.tx_token_in_max_layer);
        itemJoinSender = itemView.findViewById(R.id.tx_join_pool_sender);
        itemJoinPoolId = itemView.findViewById(R.id.tx_join_pool_id);
        itemJoinPoolShareOutAmount = itemView.findViewById(R.id.tx_join_share_out_amount);
        itemJoinPoolTokenInMaxsSymbol1 = itemView.findViewById(R.id.tx_token_in_max_symbol1);
        itemJoinPoolTokenInMaxsAmount1 = itemView.findViewById(R.id.tx_token_in_max_amount1);
        itemJoinPoolTokenInMaxsSymbol2 = itemView.findViewById(R.id.tx_token_in_max_symbol2);
        itemJoinPoolTokenInMaxsAmount2 = itemView.findViewById(R.id.tx_token_in_max_amount2);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemJoinPoolImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgJoinPool msg = Tx.MsgJoinPool.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemJoinSender.setText(msg.getSender());
            itemJoinPoolId.setText("" + msg.getPoolId());
            itemJoinPoolShareOutAmount.setText("" + new BigDecimal(msg.getShareOutAmount()).movePointLeft(18));

            WLog.w("SSS : " + msg.getTokenInMaxsCount());
            if (msg.getTokenInMaxsCount() > 1) {
                Coin coin0 = new Coin(msg.getTokenInMaxs(0).getDenom(), msg.getTokenInMaxs(0).getAmount());
                Coin coin1 = new Coin(msg.getTokenInMaxs(1).getDenom(), msg.getTokenInMaxs(1).getAmount());
                WDp.showCoinDp(c, coin0, itemJoinPoolTokenInMaxsSymbol1, itemJoinPoolTokenInMaxsAmount1, baseChain);
                 WDp.showCoinDp(c, coin1, itemJoinPoolTokenInMaxsSymbol2, itemJoinPoolTokenInMaxsAmount2, baseChain);

            } else {
                Coin coin0 = new Coin(msg.getTokenInMaxs(0).getDenom(), msg.getTokenInMaxs(0).getAmount());
                itemJoinPoolTokenMaxLayer.setVisibility(View.GONE);
                WDp.showCoinDp(c, coin0, itemJoinPoolTokenInMaxsSymbol1, itemJoinPoolTokenInMaxsAmount1, baseChain);
            }
        } catch (Exception e) { }
    }
}
