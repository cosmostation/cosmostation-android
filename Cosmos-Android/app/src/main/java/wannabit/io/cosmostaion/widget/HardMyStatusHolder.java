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
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class HardMyStatusHolder extends BaseHolder {
    TextView totalDepositValueTv, totalBorrowValueTv, borrowAbleValueTv;

    public HardMyStatusHolder(@NonNull View itemView) {
        super(itemView);
        totalDepositValueTv = itemView.findViewById(R.id.total_deposit_value);
        totalBorrowValueTv = itemView.findViewById(R.id.total_borrow_value);
        borrowAbleValueTv = itemView.findViewById(R.id.borrowable_value);

    }

    @Override
    public void onBindMyHardStatus(Context context, BaseData baseData, ArrayList<HardMyDeposit> myDeposit, ArrayList<HardMyBorrow> myBorrow) {
        final HardParam hardParam   = baseData.mHardParam;
        BigDecimal totalDepostValue = BigDecimal.ZERO;
        BigDecimal totalLTVValue = BigDecimal.ZERO;

        if (myDeposit != null && myDeposit.size() > 0) {
            for (Coin coin: myDeposit.get(0).amount) {
                int decimal             = WUtil.getKavaCoinDecimal(coin.denom);
                BigDecimal LTV          = hardParam.getLTV(coin.denom);
                BigDecimal depositValue = BigDecimal.ZERO;
                BigDecimal ltvValue     = BigDecimal.ZERO;
                if (coin.denom.equals("usdx") || coin.denom.equals("busd")) {
                    depositValue = (new BigDecimal(coin.amount)).movePointLeft(decimal);

                } else {
                    MarketPrice price = baseData.mKavaTokenPrices.get(hardParam.getSpotMarketId(coin.denom));
                    if (price != null) {
                        depositValue = (new BigDecimal(coin.amount)).movePointLeft(decimal).multiply(new BigDecimal(price.price));
                    }

                }
                ltvValue = depositValue.multiply(LTV);
                totalLTVValue = totalLTVValue.add(ltvValue);
                totalDepostValue = totalDepostValue.add(depositValue);

            }
        }
//        WLog.w("totalDepostValue" + totalDepostValue);
        totalDepositValueTv.setText(WDp.getDpRawDollor(context, totalDepostValue, 2));


        BigDecimal totalBorrowedValue = BigDecimal.ZERO;
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
                totalBorrowedValue = totalBorrowedValue.add(value);
            }
        }
        WLog.w("totalLTVValue " + totalLTVValue);
//        WLog.w("totalBorrowedValue" + totalBorrowedValue);
        totalBorrowValueTv.setText(WDp.getDpRawDollor(context, totalBorrowedValue, 2));

        final BigDecimal totalBorrowAbleValue = (totalLTVValue.subtract(totalBorrowedValue)).max(BigDecimal.ZERO);
        borrowAbleValueTv.setText(WDp.getDpRawDollor(context, totalBorrowAbleValue, 2));
//        WLog.w("totalLTVValue" + totalLTVValue);
//        WLog.w("totalBorrowAbleValue" + totalBorrowAbleValue);
    }
}
