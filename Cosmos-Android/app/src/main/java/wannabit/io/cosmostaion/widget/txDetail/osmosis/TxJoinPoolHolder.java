package wannabit.io.cosmostaion.widget.txDetail.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import cosmos.base.v1beta1.CoinOuterClass;
import cosmos.tx.v1beta1.ServiceOuterClass;
import osmosis.gamm.v1beta1.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxJoinPoolHolder extends TxHolder {
    ImageView itemJoinPoolImg;
    TextView itemJoinSender, itemJoinPoolId, itemJoinPoolShareOutAmount,
             itemJoinPoolTokenInMaxsSymbol1, itemJoinPoolTokenInMaxsAmount1, itemJoinPoolTokenInMaxsSymbol2, itemJoinPoolTokenInMaxsAmount2;

    public TxJoinPoolHolder(@NonNull View itemView) {
        super(itemView);
        itemJoinPoolImg = itemView.findViewById(R.id.tx_join_pool_icon);
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

            List<Coin> TokenCoin = new ArrayList<>();
            for (CoinOuterClass.Coin coin: msg.getTokenInMaxsList()) {
                TokenCoin.add(new Coin(coin.getDenom(), coin.getAmount()));
            }
            WDp.showCoinDp(c, TokenCoin.get(0), itemJoinPoolTokenInMaxsSymbol1, itemJoinPoolTokenInMaxsAmount1, baseChain);
            WDp.showCoinDp(c, TokenCoin.get(1), itemJoinPoolTokenInMaxsSymbol2, itemJoinPoolTokenInMaxsAmount2, baseChain);
        } catch (Exception e) { }
    }
}
