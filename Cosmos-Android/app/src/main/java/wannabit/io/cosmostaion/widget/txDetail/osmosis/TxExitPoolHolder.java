package wannabit.io.cosmostaion.widget.txDetail.osmosis;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
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
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxExitPoolHolder extends TxHolder {
    ImageView itemExitPoolImg;
    TextView itemExitSender, itemExitPoolId, itemExitPoolShareInAmount,
            itemExitPoolTokenOutMinsSymbol1, itemExitPoolTokenOutMinsAmount1, itemExitPoolTokenOutMinsSymbol2, itemExitPoolTokenOutMinsAmount2;

    public TxExitPoolHolder(@NonNull View itemView) {
        super(itemView);
        itemExitPoolImg = itemView.findViewById(R.id.tx_exit_pool_icon);
        itemExitSender = itemView.findViewById(R.id.tx_exit_pool_sender);
        itemExitPoolId = itemView.findViewById(R.id.tx_exit_pool_id);
        itemExitPoolShareInAmount = itemView.findViewById(R.id.tx_exit_share_in_amount);
        itemExitPoolTokenOutMinsSymbol1 = itemView.findViewById(R.id.tx_token_out_min_symbol1);
        itemExitPoolTokenOutMinsAmount1 = itemView.findViewById(R.id.tx_token_out_min_amount1);
        itemExitPoolTokenOutMinsSymbol2 = itemView.findViewById(R.id.tx_token_out_min_symbol2);
        itemExitPoolTokenOutMinsAmount2 = itemView.findViewById(R.id.tx_token_out_min_amount2);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
        itemExitPoolImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgExitPool msg = Tx.MsgExitPool.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemExitSender.setText(msg.getSender());
            itemExitPoolId.setText("" + msg.getPoolId());
            itemExitPoolShareInAmount.setText("" + new BigDecimal(msg.getShareInAmount()).movePointLeft(18));

            Coin coin0 = new Coin(msg.getTokenOutMins(0).getDenom(), msg.getTokenOutMins(0).getAmount());
            Coin coin1 = new Coin(msg.getTokenOutMins(1).getDenom(), msg.getTokenOutMins(1).getAmount());

            WDp.showCoinDp(c, coin0, itemExitPoolTokenOutMinsSymbol1, itemExitPoolTokenOutMinsAmount1, baseChain);
            WDp.showCoinDp(c, coin1, itemExitPoolTokenOutMinsSymbol2, itemExitPoolTokenOutMinsAmount2, baseChain);
        } catch (Exception e) { }
    }
}
