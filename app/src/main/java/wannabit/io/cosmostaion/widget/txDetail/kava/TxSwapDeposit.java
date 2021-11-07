package wannabit.io.cosmostaion.widget.txDetail.kava;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.model.type.Msg;
import wannabit.io.cosmostaion.network.res.ResTxInfo;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.widget.txDetail.TxHolder;

public class TxSwapDeposit extends TxHolder {
    ImageView   itemDepositCoinImg;
    TextView    itemDepositCoinSender,
                itemDepositTokenInAmount0, itemDepositTokenInSymbol0, itemDepositTokenInAmount1, itemDepositTokenInSymbol1;

    public TxSwapDeposit(@NonNull View itemView) {
        super(itemView);
        itemDepositCoinImg          = itemView.findViewById(R.id.tx_join_pool_icon);
        itemDepositCoinSender       = itemView.findViewById(R.id.tx_join_pool_sender);
        itemDepositTokenInAmount0   = itemView.findViewById(R.id.tx_token_in_amount1);
        itemDepositTokenInSymbol0   = itemView.findViewById(R.id.tx_token_in_symbol1);
        itemDepositTokenInAmount1   = itemView.findViewById(R.id.tx_token_in_amount2);
        itemDepositTokenInSymbol1   = itemView.findViewById(R.id.tx_token_in_symbol2);
    }

    public void onBind(Context c, BaseData baseData, BaseChain baseChain, ResTxInfo res, Msg msg) {
        itemDepositCoinImg.setColorFilter(WDp.getChainColor(c, baseChain), android.graphics.PorterDuff.Mode.SRC_IN);
        if (baseChain.equals(BaseChain.KAVA_MAIN)) {
            itemDepositCoinSender.setText(msg.value.depositor);
            ArrayList<Coin> depositCoins = res.simpleDeposits();
            if (depositCoins.size() > 0) {
                WDp.showCoinDp(c, baseData, depositCoins.get(0), itemDepositTokenInSymbol0, itemDepositTokenInAmount0, baseChain);
            }
            if (depositCoins.size() > 1) {
                WDp.showCoinDp(c, baseData, depositCoins.get(1), itemDepositTokenInSymbol1, itemDepositTokenInAmount1, baseChain);
            }

        }

    }
}
