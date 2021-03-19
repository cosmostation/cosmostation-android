package wannabit.io.cosmostaion.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.HardDetailActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.kava.HardInterestRate;
import wannabit.io.cosmostaion.model.kava.HardParam;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_HARD_POOL_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class HardDetailInfoHolder extends BaseHolder {
    private ImageView   mPoolImg;
    private TextView    mPoolTitle;
    private TextView    mSupplyApyTv, mBorrowApyTv, mSupplyIncentiveApyTv, mBorrowIncentiveApyTv;
    private TextView    mPoolSupplyAmount, mPoolSupplyAmountDenom, mPoolSupplyValue;
    private TextView    mPoolBorrowedAmount, mPoolBorrowedAmountDenom, mPoolBorrowedValue;
    private TextView    mRemainBorrowableAmount, mRemainBorrowableAmountDenom, mRemainBorrowableValue;

    public HardDetailInfoHolder(@NonNull View itemView) {
        super(itemView);
        mPoolImg                    = itemView.findViewById(R.id.pool_img);
        mPoolTitle                  = itemView.findViewById(R.id.pool_title);
        mSupplyApyTv                = itemView.findViewById(R.id.supply_apy);
        mBorrowApyTv                = itemView.findViewById(R.id.borrow_apy);
        mSupplyIncentiveApyTv       = itemView.findViewById(R.id.supply_incentive_apy);
        mBorrowIncentiveApyTv       = itemView.findViewById(R.id.borrow_incentive_apy);

        mPoolSupplyAmount           = itemView.findViewById(R.id.total_deposited_amount);
        mPoolSupplyAmountDenom      = itemView.findViewById(R.id.total_deposited_symbol);
        mPoolSupplyValue            = itemView.findViewById(R.id.total_deposited_value);
        mPoolBorrowedAmount         = itemView.findViewById(R.id.total_borrow_amount);
        mPoolBorrowedAmountDenom    = itemView.findViewById(R.id.total_borrow_symbol);
        mPoolBorrowedValue          = itemView.findViewById(R.id.total_borrow_value);
        mRemainBorrowableAmount      = itemView.findViewById(R.id.remain_borrowable_amount);
        mRemainBorrowableAmountDenom = itemView.findViewById(R.id.remain_borrowable_symbol);
        mRemainBorrowableValue       = itemView.findViewById(R.id.remain_borrowable_value);
    }

    @Override
    public void onBindHardDetailInfo(HardDetailActivity context, BaseChain chain, BaseData baseData, String denom, IncentiveReward incentiveReward, ArrayList<HardInterestRate> HardInterestRates,
                                     ArrayList<Coin> totalDeposit, ArrayList<Coin> totalborrow, ArrayList<Coin> moduleCoins, ArrayList<Coin> reserveCoin) {
        final HardParam hardParam                        = baseData.mHardParam;
        final HardParam.HardMoneyMarket hardMoneyMarket  = hardParam.getHardMoneyMarket(denom);
//        WLog.w("hardMoneyMarket.denom " + hardMoneyMarket.denom);

        try {
            Picasso.get().load(KAVA_HARD_POOL_IMG_URL + "lp" + hardMoneyMarket.denom + ".png").fit().into(mPoolImg);
        } catch (Exception e) { }
        String marketTitle = hardMoneyMarket.denom.equals(TOKEN_KAVA) ? "kava" : hardMoneyMarket.denom;
        mPoolTitle.setText(marketTitle.toUpperCase() + " POOL");

        BigDecimal supplyApy = BigDecimal.ZERO;
        BigDecimal borrowApy = BigDecimal.ZERO;
        for (HardInterestRate interestRate : HardInterestRates) {
            if (interestRate.denom.equals(hardMoneyMarket.denom)) {
                supplyApy = new BigDecimal(interestRate.supply_interest_rate);
                borrowApy = new BigDecimal(interestRate.borrow_interest_rate);
            }
        }
        BigDecimal supplyIncentiveApy = incentiveReward.getSupplyRewardFactor(hardMoneyMarket.denom);
        BigDecimal borrowIncentive = incentiveReward.getBorrowRewardFactor(hardMoneyMarket.denom);

        mSupplyApyTv.setText(WDp.getPercentDp(supplyApy.multiply(new BigDecimal("100"))));
        mBorrowApyTv.setText(WDp.getPercentDp(borrowApy.multiply(new BigDecimal("100"))));
        mSupplyIncentiveApyTv.setText(WDp.getPercentDp(supplyIncentiveApy.multiply(new BigDecimal("100"))));
        mBorrowIncentiveApyTv.setText(WDp.getPercentDp(borrowIncentive.multiply(new BigDecimal("100"))));

        // display system total supplied
//        WLog.w("totalDeposit " + totalDeposit.size());
        Coin totalDepositCoin = null;
        BigDecimal totalDepositValue = BigDecimal.ZERO;
        if (totalDeposit != null) {
            for (Coin coin: totalDeposit) {
                if (coin.denom.equals(hardMoneyMarket.denom)) {
                    totalDepositCoin = coin;
                }
            }

        }
        if (totalDepositCoin != null) {
            WDp.showCoinDp(context, totalDepositCoin, mPoolSupplyAmountDenom, mPoolSupplyAmount, chain);
            int decimal =  WUtil.getKavaCoinDecimal(totalDepositCoin.denom);
            MarketPrice price = baseData.mKavaTokenPrices.get(hardParam.getSpotMarketId(totalDepositCoin.denom));
            if (price != null) {
                totalDepositValue = (new BigDecimal(totalDepositCoin.amount)).movePointLeft(decimal).multiply(new BigDecimal(price.price));
            }

        } else {
            WDp.showCoinDp(context, hardMoneyMarket.denom, "0", mPoolSupplyAmountDenom, mPoolSupplyAmount, chain);
        }
        mPoolSupplyValue.setText(WDp.getDpRawDollor(context, totalDepositValue, 2));


        // display system total borrowed
        Coin totalBorrowCoin = null;
        BigDecimal totalBorrowValue = BigDecimal.ZERO;
        if (totalborrow != null) {
            for (Coin coin: totalborrow) {
                if (coin.denom.equals(hardMoneyMarket.denom)) {
                    totalBorrowCoin = coin;
                }
            }
        }
        if (totalBorrowCoin != null) {
            WDp.showCoinDp(context, totalBorrowCoin, mPoolBorrowedAmountDenom, mPoolBorrowedAmount, chain);
            int decimal =  WUtil.getKavaCoinDecimal(totalBorrowCoin.denom);
            MarketPrice price = baseData.mKavaTokenPrices.get(hardParam.getSpotMarketId(totalBorrowCoin.denom));
            if (price != null) {
                totalBorrowValue = (new BigDecimal(totalBorrowCoin.amount)).movePointLeft(decimal).multiply(new BigDecimal(price.price));
            }

        } else {
            WDp.showCoinDp(context, hardMoneyMarket.denom, "0", mPoolBorrowedAmountDenom, mPoolBorrowedAmount, chain);
        }
        mPoolBorrowedValue.setText(WDp.getDpRawDollor(context, totalBorrowValue, 2));


        // display system remain borrowable
        BigDecimal SystemBorrowableAmount   = BigDecimal.ZERO;
        BigDecimal SystemBorrowableValue    = BigDecimal.ZERO;
        BigDecimal moduleAmount             = BigDecimal.ZERO;
        BigDecimal reserveAmount            = BigDecimal.ZERO;
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
        WDp.showCoinDp(context, hardMoneyMarket.denom, SystemBorrowableAmount.toPlainString(), mRemainBorrowableAmountDenom, mRemainBorrowableAmount, chain);

        int decimal =  WUtil.getKavaCoinDecimal(hardMoneyMarket.denom);
        MarketPrice price = baseData.mKavaTokenPrices.get(hardParam.getSpotMarketId(hardMoneyMarket.denom));
        if (price != null) {
            SystemBorrowableValue = SystemBorrowableAmount.movePointLeft(decimal).multiply(new BigDecimal(price.price));
        }
        mRemainBorrowableValue.setText(WDp.getDpRawDollor(context, SystemBorrowableValue, 2));
    }
}
