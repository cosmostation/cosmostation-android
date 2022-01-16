package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;

import cosmos.base.v1beta1.CoinOuterClass;
import kava.hard.v1beta1.Hard;
import kava.hard.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class HardMyStatusHolder extends BaseHolder {
    TextView totalDepositValueTv, totalBorrowValueTv, borrowAbleValueTv, ltvValueTv;

    public HardMyStatusHolder(@NonNull View itemView) {
        super(itemView);
        totalDepositValueTv     = itemView.findViewById(R.id.total_deposit_value);
        totalBorrowValueTv      = itemView.findViewById(R.id.total_borrow_value);
        borrowAbleValueTv       = itemView.findViewById(R.id.borrowable_value);
        ltvValueTv              = itemView.findViewById(R.id.ltv_value);

    }

    @Override
    public void onBindMyHardStatus(Context context, BaseData baseData, ArrayList<QueryOuterClass.DepositResponse> myDeposit, ArrayList<QueryOuterClass.BorrowResponse> myBorrow) {
        final Hard.Params hardParams = baseData.mHardParams;
        BigDecimal totalDepostValue = BigDecimal.ZERO;
        BigDecimal totalLTVValue = BigDecimal.ZERO;

        if (myDeposit != null && myDeposit.size() > 0) {
            for (CoinOuterClass.Coin coin: myDeposit.get(0).getAmountList()) {
                int decimal             = WUtil.getKavaCoinDecimal(coin.getDenom());
                BigDecimal LTV          = WUtil.getLTV(hardParams, coin.getDenom());
                BigDecimal depositValue = BigDecimal.ZERO;
                BigDecimal ltvValue     = BigDecimal.ZERO;
                if (coin.getDenom().equalsIgnoreCase("usdx") || coin.getDenom().equalsIgnoreCase("busd")) {
                    depositValue = (new BigDecimal(coin.getAmount())).movePointLeft(decimal);

                } else {
                    kava.pricefeed.v1beta1.QueryOuterClass.CurrentPriceResponse price = baseData.mKavaTokenPrice.get(WUtil.getSpotMarketId(hardParams, coin.getDenom()));
                    if (price != null) {
                        depositValue = (new BigDecimal(coin.getAmount())).movePointLeft(decimal).multiply(new BigDecimal(price.getPrice()).movePointLeft(18));
                    }
                }
                ltvValue = depositValue.multiply(LTV);
                totalLTVValue = totalLTVValue.add(ltvValue);
                totalDepostValue = totalDepostValue.add(depositValue);
            }
        }
        totalDepositValueTv.setText(WDp.getDpRawDollor(context, totalDepostValue, 2));
        totalBorrowValueTv.setText(WDp.getDpRawDollor(context, totalLTVValue.movePointLeft(18), 2));


        BigDecimal totalBorrowedValue = BigDecimal.ZERO;
        if (myBorrow != null && myBorrow.size() > 0) {
            for (CoinOuterClass.Coin coin: myBorrow.get(0).getAmountList()) {
                int decimal =  WUtil.getKavaCoinDecimal(coin.getDenom());
                BigDecimal value = BigDecimal.ZERO;
                if (coin.getDenom().equalsIgnoreCase("usdx") || coin.getDenom().equalsIgnoreCase("busd")) {
                    value = (new BigDecimal(coin.getAmount())).movePointLeft(decimal);

                } else {
                    kava.pricefeed.v1beta1.QueryOuterClass.CurrentPriceResponse price = baseData.mKavaTokenPrice.get(WUtil.getSpotMarketId(hardParams, coin.getDenom()));
                    if (price != null) {
                        value = (new BigDecimal(coin.getAmount())).movePointLeft(decimal).multiply(new BigDecimal(price.getPrice()).movePointLeft(18));
                    }

                }
                totalBorrowedValue = totalBorrowedValue.add(value);
            }
        }
        ltvValueTv.setText(WDp.getDpRawDollor(context, totalBorrowedValue, 2));
        final BigDecimal totalBorrowAbleValue = (totalLTVValue.subtract(totalBorrowedValue)).max(BigDecimal.ZERO);
        borrowAbleValueTv.setText(WDp.getDpRawDollor(context, totalBorrowAbleValue.movePointLeft(18), 2));
    }
}
