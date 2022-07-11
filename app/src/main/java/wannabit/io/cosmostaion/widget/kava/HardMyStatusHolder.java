package wannabit.io.cosmostaion.widget.kava;

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
import wannabit.io.cosmostaion.widget.BaseHolder;

public class HardMyStatusHolder extends BaseHolder {
    TextView totalDepositValueTv, totalBorrowValueTv, borrowAbleValueTv, ltvValueTv;

    public HardMyStatusHolder(@NonNull View itemView) {
        super(itemView);
        totalDepositValueTv     = itemView.findViewById(R.id.total_deposit_value);
        totalBorrowValueTv      = itemView.findViewById(R.id.total_borrow_value);
        ltvValueTv              = itemView.findViewById(R.id.ltv_value);
        borrowAbleValueTv       = itemView.findViewById(R.id.borrowable_value);
    }

    @Override
    public void onBindMyHardStatus(Context context, BaseData baseData, ArrayList<QueryOuterClass.DepositResponse> myDeposit, ArrayList<QueryOuterClass.BorrowResponse> myBorrow) {
        final Hard.Params hardParams = baseData.mHardParams;
        BigDecimal totalDepostValue = BigDecimal.ZERO;
        BigDecimal totalLTVValue = BigDecimal.ZERO;

        if (myDeposit != null && myDeposit.size() > 0) {
            for (CoinOuterClass.Coin coin: myDeposit.get(0).getAmountList()) {
                int decimal             = WUtil.getKavaCoinDecimal(baseData, coin.getDenom());
                BigDecimal LTV          = WUtil.getLTV(hardParams, coin.getDenom());
                BigDecimal depositValue = BigDecimal.ZERO;
                BigDecimal ltvValue     = BigDecimal.ZERO;
                if (coin.getDenom().equalsIgnoreCase("usdx") || coin.getDenom().equalsIgnoreCase("busd")) {
                    depositValue = (new BigDecimal(coin.getAmount())).movePointLeft(decimal);

                } else {
                    depositValue = (new BigDecimal(coin.getAmount())).movePointLeft(decimal).multiply(baseData.getKavaOraclePrice(WUtil.getSpotMarketId(hardParams, coin.getDenom())));
                }
                ltvValue = depositValue.multiply(LTV);
                totalLTVValue = totalLTVValue.add(ltvValue);
                totalDepostValue = totalDepostValue.add(depositValue);
            }
        }
        ltvValueTv.setText(WDp.getDpRawDollor(context, totalLTVValue, 2));
        totalDepositValueTv.setText(WDp.getDpRawDollor(context, totalDepostValue, 2));


        BigDecimal totalBorrowedValue = BigDecimal.ZERO;
        if (myBorrow != null && myBorrow.size() > 0) {
            for (CoinOuterClass.Coin coin: myBorrow.get(0).getAmountList()) {
                int decimal =  WUtil.getKavaCoinDecimal(baseData, coin.getDenom());
                BigDecimal value = BigDecimal.ZERO;
                if (coin.getDenom().equalsIgnoreCase("usdx") || coin.getDenom().equalsIgnoreCase("busd")) {
                    value = (new BigDecimal(coin.getAmount())).movePointLeft(decimal);

                } else {
                    value = (new BigDecimal(coin.getAmount())).movePointLeft(decimal).multiply(baseData.getKavaOraclePrice(WUtil.getSpotMarketId(hardParams, coin.getDenom())));

                }
                totalBorrowedValue = totalBorrowedValue.add(value);
            }
        }
        totalBorrowValueTv.setText(WDp.getDpRawDollor(context, totalBorrowedValue, 2));
        final BigDecimal totalBorrowAbleValue = (totalLTVValue.subtract(totalBorrowedValue)).max(BigDecimal.ZERO);
        borrowAbleValueTv.setText(WDp.getDpRawDollor(context, totalBorrowAbleValue, 2));
    }
}
