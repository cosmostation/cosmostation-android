package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.kava.HardMyBorrow;
import wannabit.io.cosmostaion.model.kava.HardMyDeposit;
import wannabit.io.cosmostaion.model.kava.HardParam;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HARD;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class HardDetailMyStatusHolder extends BaseHolder {
    private ImageView       mDepositCoinImg;
    private TextView        mDepositCoinTitle;
    private TextView        mDepositAmountTv, mDepositDenomTv, mDepositValueTv;
    private TextView        mBorrowedAmountTv, mBorrowedDenomTv, mBorrowedValueTv;
    private TextView        mBorrowableAmountTv, mBorrowableDenomTv, mBorrowableValueTv;
    private RelativeLayout  mMyBtnSupply, mMyBtnWithdraw, mMyBtnBorrow, mMyBtnRepay;

    public HardDetailMyStatusHolder(@NonNull View itemView) {
        super(itemView);
        mDepositCoinImg         = itemView.findViewById(R.id.deposit_icon);
        mDepositCoinTitle       = itemView.findViewById(R.id.deposit_denom);

        mDepositAmountTv        = itemView.findViewById(R.id.deposited_amount);
        mDepositDenomTv         = itemView.findViewById(R.id.deposited_symbol);
        mDepositValueTv         = itemView.findViewById(R.id.deposited_value);
        mBorrowedAmountTv       = itemView.findViewById(R.id.borrowed_amount);
        mBorrowedDenomTv        = itemView.findViewById(R.id.borrowed_symbol);
        mBorrowedValueTv        = itemView.findViewById(R.id.borrowed_value);
        mBorrowableAmountTv     = itemView.findViewById(R.id.borrowable_amount);
        mBorrowableDenomTv      = itemView.findViewById(R.id.borrowable_symbol);
        mBorrowableValueTv      = itemView.findViewById(R.id.borrowable_value);

        mMyBtnSupply            = itemView.findViewById(R.id.btn_deposit);
        mMyBtnWithdraw          = itemView.findViewById(R.id.btn_withdraw);
        mMyBtnBorrow            = itemView.findViewById(R.id.btn_borrow);
        mMyBtnRepay             = itemView.findViewById(R.id.btn_repay);
    }


    @Override
    public void onBindHardDetailMyStatus(Context context, BaseData baseData, BaseChain chain, String denom, ArrayList<HardMyDeposit> myDeposit,
                                         ArrayList<HardMyBorrow> myBorrow, ArrayList<Coin> moduleCoins, ArrayList<Coin> reserveCoin) {
        final HardParam hardParam                       = baseData.mHardParam;
        final HardParam.HardMoneyMarket hardMoneyMarket = hardParam.getHardMoneyMarket(denom);
        final MarketPrice price                         = baseData.mKavaTokenPrices.get(hardParam.getSpotMarketId(denom));
        final int decimal                               =  WUtil.getKavaCoinDecimal(denom);

        try {
            Picasso.get().load(KAVA_COIN_IMG_URL + hardMoneyMarket.denom + ".png").fit().into(mDepositCoinImg);
        } catch (Exception e) { }
        WDp.showCoinDp(context, hardMoneyMarket.denom, "0", mDepositCoinTitle, null, chain);

        //Display My Supply
        Coin myDepositCoin = null;
        BigDecimal myDepositValue = BigDecimal.ZERO;
        if (myDeposit != null && myDeposit.size() > 0) {
            for (Coin coin: myDeposit.get(0).amount) {
                if (coin.denom.equals(hardMoneyMarket.denom)) {
                    myDepositCoin = coin;
                }
            }
        }
        if (myDepositCoin != null) {
            WDp.showCoinDp(context, myDepositCoin, mDepositDenomTv, mDepositAmountTv, chain);
            if (price != null) {
                myDepositValue = (new BigDecimal(myDepositCoin.amount)).movePointLeft(decimal).multiply(new BigDecimal(price.price));
            }

        } else {
            WDp.showCoinDp(context, hardMoneyMarket.denom, "0", mDepositDenomTv, mDepositAmountTv, chain);
        }
        mDepositValueTv.setText(WDp.getDpRawDollor(context, myDepositValue, 2));

        //Display My Borrowed
        Coin myBorrowCoin = null;
        BigDecimal myBorrowValue = BigDecimal.ZERO;
        if (myBorrow != null && myBorrow.size() > 0) {
            for (Coin coin: myBorrow.get(0).amount) {
                if (coin.denom.equals(hardMoneyMarket.denom)) {
                    myBorrowCoin = coin;
                }
            }
        }
        if (myBorrowCoin != null) {
            WDp.showCoinDp(context, myBorrowCoin, mBorrowedDenomTv, mBorrowedAmountTv, chain);
            if (price != null) {
                myBorrowValue = (new BigDecimal(myBorrowCoin.amount)).movePointLeft(decimal).multiply(new BigDecimal(price.price));
            }

        } else {
            WDp.showCoinDp(context, hardMoneyMarket.denom, "0", mBorrowedDenomTv, mBorrowedAmountTv, chain);
        }
        mBorrowedValueTv.setText(WDp.getDpRawDollor(context, myBorrowValue, 2));


        //Display My Borrowable
        BigDecimal totalLTVValue = BigDecimal.ZERO;
        BigDecimal totalBorrowedValue = BigDecimal.ZERO;
        BigDecimal totalBorrowAbleValue = BigDecimal.ZERO;
        BigDecimal totalBorrowAbleAmount = BigDecimal.ZERO;

        BigDecimal SystemBorrowableAmount   = BigDecimal.ZERO;
        BigDecimal SystemBorrowableValue    = BigDecimal.ZERO;
        BigDecimal moduleAmount             = BigDecimal.ZERO;
        BigDecimal reserveAmount            = BigDecimal.ZERO;

        if (myDeposit != null && myDeposit.size() > 0) {
            for (Coin coin: myDeposit.get(0).amount) {
                int innnerDecimal       = WUtil.getKavaCoinDecimal(coin.denom);
                BigDecimal LTV          = hardParam.getLTV(coin.denom);
                BigDecimal depositValue = BigDecimal.ZERO;
                BigDecimal ltvValue     = BigDecimal.ZERO;
                if (coin.denom.equals("usdx") || coin.denom.equals("busd")) {
                    depositValue = (new BigDecimal(coin.amount)).movePointLeft(innnerDecimal);

                } else {
                    MarketPrice innerPrice = baseData.mKavaTokenPrices.get(hardParam.getSpotMarketId(coin.denom));
                    if (price != null) {
                        depositValue = (new BigDecimal(coin.amount)).movePointLeft(innnerDecimal).multiply(new BigDecimal(innerPrice.price));
                    }

                }
                ltvValue = depositValue.multiply(LTV);
                totalLTVValue = totalLTVValue.add(ltvValue);

            }
        }

        if (myBorrow != null && myBorrow.size() > 0) {
            for (Coin coin: myBorrow.get(0).amount) {
                int innnerDecimal   = WUtil.getKavaCoinDecimal(coin.denom);
                BigDecimal value    = BigDecimal.ZERO;
                if (coin.denom.equals("usdx") || coin.denom.equals("busd")) {
                    value = (new BigDecimal(coin.amount)).movePointLeft(innnerDecimal);

                } else {
                    MarketPrice innerPrice = baseData.mKavaTokenPrices.get(hardParam.getSpotMarketId(coin.denom));
                    if (innerPrice != null) {
                        value = (new BigDecimal(coin.amount)).movePointLeft(innnerDecimal).multiply(new BigDecimal(innerPrice.price));
                    }

                }
                totalBorrowedValue = totalBorrowedValue.add(value);
            }
        }
        totalBorrowAbleValue = (totalLTVValue.subtract(totalBorrowedValue)).max(BigDecimal.ZERO);
        if (price != null) {
            totalBorrowAbleAmount = totalBorrowAbleValue.movePointRight(decimal).divide(new BigDecimal(price.price), decimal, RoundingMode.DOWN);
        }
//        WLog.w("totalLTVValue " + totalLTVValue);
//        WLog.w("totalBorrowAbleValue " + totalBorrowAbleValue);
//        WLog.w("totalBorrowAbleAmount " + totalBorrowAbleAmount);

        if (moduleCoins != null) {
            for (Coin coin: moduleCoins) {
                if (coin.denom.equals(hardMoneyMarket.denom)) {
                    moduleAmount = new BigDecimal(coin.amount);
                }
            }
        }
        if (reserveCoin != null) {
            for (Coin coin: reserveCoin) {
                if (coin.denom.equals(hardMoneyMarket.denom)) {
                    reserveAmount = new BigDecimal(coin.amount);
                }
            }
        }
        BigDecimal moduleBorrowable = moduleAmount.subtract(reserveAmount);
        if (hardMoneyMarket.borrow_limit.has_max_limit) {
            SystemBorrowableAmount = new BigDecimal(hardMoneyMarket.borrow_limit.maximum_limit).min(moduleBorrowable);
        } else {
            SystemBorrowableAmount = moduleBorrowable;
        }
        if (price != null) {
            SystemBorrowableValue = SystemBorrowableAmount.movePointLeft(decimal).multiply(new BigDecimal(price.price));
        }
//        WLog.w("SystemBorrowableValue " + SystemBorrowableValue);
//        WLog.w("SystemBorrowableAmount " + SystemBorrowableAmount);

        BigDecimal finalBorrowableValue = totalBorrowAbleValue.min(SystemBorrowableValue);
        BigDecimal finalBorrowableAmount = totalBorrowAbleAmount.min(SystemBorrowableAmount);

        WDp.showCoinDp(context, hardMoneyMarket.denom, finalBorrowableAmount.toPlainString(), mBorrowableDenomTv, mBorrowableAmountTv, chain);
        mBorrowableValueTv.setText(WDp.getDpRawDollor(context, finalBorrowableValue, 2));


    }
}
