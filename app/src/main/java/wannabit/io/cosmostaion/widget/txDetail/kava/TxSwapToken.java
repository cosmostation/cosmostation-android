package wannabit.io.cosmostaion.widget.txDetail.kava;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxSwapToken extends TxHolder {
    ImageView   itemSwapCoinImg;
    TextView    itemSwapCoinType, itemSwapCoinSender,
                itemSwapTokenInAmount, itemSwapTokenInAmountSymbol, itemSwapTokenOutAmount, itemSwapTokenOutAmountSymbol;

    public TxSwapToken(@NonNull View itemView) {
        super(itemView);
        itemSwapCoinImg = itemView.findViewById(R.id.tx_swap_coin_icon);
        itemSwapCoinType = itemView.findViewById(R.id.tx_swap_coin_type);
        itemSwapCoinSender = itemView.findViewById(R.id.tx_swap_coin_sender);
        itemSwapTokenInAmount = itemView.findViewById(R.id.tx_swap_token_in_amount);
        itemSwapTokenInAmountSymbol = itemView.findViewById(R.id.tx_swap_token_in_amount_symbol);
        itemSwapTokenOutAmount = itemView.findViewById(R.id.tx_swap_token_out_amount);
        itemSwapTokenOutAmountSymbol = itemView.findViewById(R.id.tx_swap_token_out_amount_symbol);
    }

    public void onBind(Context c, BaseData baseData, BaseChain baseChain, ResTxInfo res, Msg msg) {
        itemSwapCoinImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        if (baseChain.equals(BaseChain.KAVA_MAIN)) {
            itemSwapCoinType.setText(msg.type.split("/")[1]);
            itemSwapCoinSender.setText(msg.value.requester);

            Coin inCoin = res.simpleSwapInCoin();
            if (inCoin.amount == null || inCoin.denom == null) {
                itemSwapTokenInAmount.setText("-");
                itemSwapTokenInAmountSymbol.setText("-");
            } else {
                WDp.showCoinDp(c, baseData, inCoin, itemSwapTokenInAmountSymbol, itemSwapTokenInAmount, baseChain);
            }
            Coin outCoin = res.simpleSwapOutCoin();
            if (outCoin.amount == null || outCoin.denom == null) {
                itemSwapTokenOutAmount.setText("-");
                itemSwapTokenOutAmountSymbol.setText("-");
            } else {
                WDp.showCoinDp(c, baseData, outCoin, itemSwapTokenOutAmountSymbol, itemSwapTokenOutAmount, baseChain);
            }
        }

    }
}
