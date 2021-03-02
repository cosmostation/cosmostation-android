package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.kava.HardMyBorrow;
import wannabit.io.cosmostaion.model.kava.HardMyDeposit;
import wannabit.io.cosmostaion.model.kava.HardParam;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class HardMyStatusHolder extends BaseHolder {
    TextView totalDepositValueTv, totalBorrowValueTv, borrowLimitValueTv;

    public HardMyStatusHolder(@NonNull View itemView) {
        super(itemView);
        totalDepositValueTv = itemView.findViewById(R.id.total_deposit_value);
        totalBorrowValueTv = itemView.findViewById(R.id.total_borrow_value);
        borrowLimitValueTv = itemView.findViewById(R.id.borrow_limit_value);

    }

    @Override
    public void onBindMyHardStatus(Context context, BaseData baseData, ArrayList<HardMyDeposit> myDeposit, ArrayList<HardMyBorrow> myBorrow) {
        final HardParam hardParam   = baseData.mHardParam;
        BigDecimal totalDepostValue = BigDecimal.ZERO;
        BigDecimal borrowLimitValue = BigDecimal.ZERO;

        if (myDeposit != null && myDeposit.size() > 0) {
            for (Coin coin: myDeposit.get(0).amount) {
                int decimal             = WUtil.getKavaCoinDecimal(coin.denom);
                BigDecimal LTV          = hardParam.getLTV(coin.denom);
                BigDecimal depositValue = BigDecimal.ZERO;
                BigDecimal borrowValue  = BigDecimal.ZERO;
                if (coin.denom.equals("usdx") || coin.denom.equals("busd")) {
                    depositValue = (new BigDecimal(coin.amount)).movePointLeft(decimal);

                } else {
                    MarketPrice price = baseData.mKavaTokenPrices.get(hardParam.getSpotMarketId(coin.denom));
                    if (price != null) {
                        depositValue = (new BigDecimal(coin.amount)).movePointLeft(decimal).multiply(new BigDecimal(price.price));
                    }

                }
                borrowValue = depositValue.multiply(LTV);
                borrowLimitValue = borrowLimitValue.add(borrowValue);
                totalDepostValue = totalDepostValue.add(depositValue);

            }
        }
//        WLog.w("totalDepostValue" + totalDepostValue);
        totalDepositValueTv.setText(WDp.getDpRawDollor(context, totalDepostValue, 2));
        borrowLimitValueTv.setText(WDp.getDpRawDollor(context, borrowLimitValue, 2));


        BigDecimal totalBorrowValue = BigDecimal.ZERO;
        if (myBorrow != null && myBorrow.size() > 0) {
            for (Coin coin: myBorrow.get(0).amount) {
                int decimal =  WUtil.getKavaCoinDecimal(coin.denom);
                BigDecimal value = BigDecimal.ZERO;
                if (coin.denom.equals("usdx") || coin.denom.equals("busd")) {
                    value = (new BigDecimal(coin.amount)).movePointLeft(decimal);

                } else {
                    MarketPrice price = baseData.mKavaTokenPrices.get(hardParam.getSpotMarketId(coin.denom));
                    if (price != null) {
                        value = (new BigDecimal(coin.amount)).movePointLeft(decimal).multiply(new BigDecimal(price.price));
                    }

                }
                totalBorrowValue = totalBorrowValue.add(value);
            }
        }
//        WLog.w("totalBorrowValue" + totalBorrowValue);
        totalBorrowValueTv.setText(WDp.getDpRawDollor(context, totalBorrowValue, 2));
    }
}
