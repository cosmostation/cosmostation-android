package wannabit.io.cosmostaion.widget.txDetail.kava;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxSwapWithdraw extends TxHolder {
    ImageView   itemWithdrawCoinImg;
    TextView    itemWithdrawCoinSender,
                itemWithdrawTokenInAmount0, itemWithdrawTokenInSymbol0, itemWithdrawTokenInAmount1, itemWithdrawTokenInSymbol1;

    public TxSwapWithdraw(@NonNull View itemView) {
        super(itemView);
        itemWithdrawCoinImg          = itemView.findViewById(R.id.tx_exit_pool_icon);
        itemWithdrawCoinSender       = itemView.findViewById(R.id.tx_exit_pool_sender);
        itemWithdrawTokenInAmount0   = itemView.findViewById(R.id.tx_token_out_amount1);
        itemWithdrawTokenInSymbol0   = itemView.findViewById(R.id.tx_token_out_symbol1);
        itemWithdrawTokenInAmount1   = itemView.findViewById(R.id.tx_token_out_amount2);
        itemWithdrawTokenInSymbol1   = itemView.findViewById(R.id.tx_token_out_symbol2);
    }

    public void onBind(Context c, BaseChain baseChain, ResTxInfo res, Msg msg) {
        itemWithdrawCoinImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        if (baseChain.equals(BaseChain.KAVA_MAIN)) {
            itemWithdrawCoinSender.setText(msg.value.from);
            ArrayList<Coin> withdrawCoins = res.simpleWithdraws();
            if (withdrawCoins.size() > 0) {
                WDp.showCoinDp(c, withdrawCoins.get(0), itemWithdrawTokenInSymbol0, itemWithdrawTokenInAmount0, baseChain);
            }
            if (withdrawCoins.size() > 1) {
                WDp.showCoinDp(c, withdrawCoins.get(1), itemWithdrawTokenInSymbol1, itemWithdrawTokenInAmount1, baseChain);
            }

        }

    }
}
