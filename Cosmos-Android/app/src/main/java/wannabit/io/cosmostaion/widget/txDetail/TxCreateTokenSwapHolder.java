package wannabit.io.cosmostaion.widget.txDetail;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;

import cosmos.tx.v1beta1.ServiceOuterClass;
import rizonworld.rizon.tokenswap.Tx;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;

public class TxCreateTokenSwapHolder extends TxHolder {
    ImageView itemCreateTokenSwapImg;
    TextView itemCreateTokenSwapHash, itemCreateTokenSwapRecipient, itemCreateTokenSwapAmount;

    public TxCreateTokenSwapHolder(@NonNull View itemView) {
        super(itemView);
        itemCreateTokenSwapImg              = itemView.findViewById(R.id.tx_create_token_swap_icon);
        itemCreateTokenSwapHash             = itemView.findViewById(R.id.tx_create_token_swap_hash);
        itemCreateTokenSwapRecipient        = itemView.findViewById(R.id.tx_create_token_swap_recipient);
        itemCreateTokenSwapAmount           = itemView.findViewById(R.id.tx_create_token_swap_amount);
    }

    public void onBindMsg(Context c, BaseData baseData, BaseChain baseChain, ServiceOuterClass.GetTxResponse response, int position, String address, boolean isGen) {
//        itemCreateTokenSwapImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);

        try {
            Tx.MsgCreateTokenswapRequest msg = Tx.MsgCreateTokenswapRequest.parseFrom(response.getTx().getBody().getMessages(position).getValue());
            itemCreateTokenSwapHash.setText(msg.getTxHash());
            itemCreateTokenSwapRecipient.setText(msg.getReceiver());
            BigDecimal receiveAmount = new BigDecimal(msg.getAmount()).movePointLeft(18);
            itemCreateTokenSwapAmount.setText(WDp.getDpAmount2(c, receiveAmount, 6, 6));

        } catch (Exception e) { }
    }
}
