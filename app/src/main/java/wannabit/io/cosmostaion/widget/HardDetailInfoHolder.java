package wannabit.io.cosmostaion.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;

import cosmos.base.v1beta1.CoinOuterClass;
import kava.hard.v1beta1.Hard;
import kava.hard.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.HardDetailActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_HARD_POOL_IMG_URL;

public class HardDetailInfoHolder extends BaseHolder {
    private ImageView   mPoolImg;
    private TextView    mPoolTitle;
    private TextView    mSupplyApyTv, mBorrowApyTv, mSupplyIncentiveApyTv, mBorrowIncentiveApyTv;
    private TextView    mPoolSupplyAmount, mPoolSupplyAmountDenom, mPoolSupplyValue;
    private TextView    mPoolBorrowedAmount, mPoolBorrowedAmountDenom, mPoolBorrowedValue;
    private TextView    mRemainBorrowableAmount, mRemainBorrowableAmountDenom, mRemainBorrowableValue;

    public HardDetailInfoHolder(@NonNull View itemView) {
        super(itemView);
        mPoolImg                     = itemView.findViewById(R.id.pool_img);
        mPoolTitle                   = itemView.findViewById(R.id.pool_title);
        mSupplyApyTv                 = itemView.findViewById(R.id.supply_apy);
        mBorrowApyTv                 = itemView.findViewById(R.id.borrow_apy);
        mSupplyIncentiveApyTv        = itemView.findViewById(R.id.supply_incentive_apy);
        mBorrowIncentiveApyTv        = itemView.findViewById(R.id.borrow_incentive_apy);

        mPoolSupplyAmount            = itemView.findViewById(R.id.total_deposited_amount);
        mPoolSupplyAmountDenom       = itemView.findViewById(R.id.total_deposited_symbol);
        mPoolSupplyValue             = itemView.findViewById(R.id.total_deposited_value);
        mPoolBorrowedAmount          = itemView.findViewById(R.id.total_borrow_amount);
        mPoolBorrowedAmountDenom     = itemView.findViewById(R.id.total_borrow_symbol);
        mPoolBorrowedValue           = itemView.findViewById(R.id.total_borrow_value);
        mRemainBorrowableAmount      = itemView.findViewById(R.id.remain_borrowable_amount);
        mRemainBorrowableAmountDenom = itemView.findViewById(R.id.remain_borrowable_symbol);
        mRemainBorrowableValue       = itemView.findViewById(R.id.remain_borrowable_value);
    }

    @Override
    public void onBindHardDetailInfo(HardDetailActivity context, BaseChain chain, BaseData baseData, String denom, IncentiveReward incentiveReward, ArrayList<QueryOuterClass.MoneyMarketInterestRate> HardInterestRates,
                                     ArrayList<CoinOuterClass.Coin> totalDeposit, ArrayList<CoinOuterClass.Coin> totalborrow, ArrayList<Coin> moduleCoins, ArrayList<CoinOuterClass.Coin> reserveCoin, int position) {

        final Hard.Params hardParam             = baseData.mHardParams;
        final Hard.MoneyMarket hardMoneyMarket  = WUtil.getHardMoneyMarket(hardParam, denom);

        try {
            Picasso.get().load(KAVA_HARD_POOL_IMG_URL + "lp" + hardMoneyMarket.getDenom() + ".png").fit().into(mPoolImg);
        } catch (Exception e) { }
        String marketTitle = hardMoneyMarket.getSpotMarketId();
        mPoolTitle.setText(marketTitle.toUpperCase());

        BigDecimal supplyApy = BigDecimal.ZERO;
        BigDecimal borrowApy = BigDecimal.ZERO;
        for (QueryOuterClass.MoneyMarketInterestRate interestRates : HardInterestRates) {
            if (interestRates.getDenom().equalsIgnoreCase(hardMoneyMarket.getDenom())) {
                supplyApy = new BigDecimal(interestRates.getSupplyInterestRate());
                borrowApy = new BigDecimal(interestRates.getBorrowInterestRate());
            }
        }

        BigDecimal supplyIncentiveApy = incentiveReward.getSupplyRewardFactor(hardMoneyMarket.getDenom());
        BigDecimal borrowIncentive = incentiveReward.getBorrowRewardFactor(hardMoneyMarket.getDenom());

        mSupplyApyTv.setText(WDp.getPercentDp(supplyApy.multiply(new BigDecimal("100"))));
        mBorrowApyTv.setText(WDp.getPercentDp(borrowApy.multiply(new BigDecimal("100"))));
        mSupplyIncentiveApyTv.setText(WDp.getPercentDp(supplyIncentiveApy.multiply(new BigDecimal("100"))));
        mBorrowIncentiveApyTv.setText(WDp.getPercentDp(borrowIncentive.multiply(new BigDecimal("100"))));

        // display system total supplied
        Coin totalDepositCoin = null;
        BigDecimal totalDepositValue = BigDecimal.ZERO;
        if (totalDeposit != null) {
            for (CoinOuterClass.Coin coin: totalDeposit) {
                if (coin.getDenom().equalsIgnoreCase(hardMoneyMarket.getDenom())) {
                    totalDepositCoin = new Coin(coin.getDenom(), coin.getAmount());
                }
            }

        }
        if (totalDepositCoin != null) {
            WDp.showCoinDp(context, baseData, totalDepositCoin, mPoolSupplyAmountDenom, mPoolSupplyAmount, chain);
            int decimal =  WUtil.getKavaCoinDecimal(baseData, totalDepositCoin.denom);
            totalDepositValue = (new BigDecimal(totalDepositCoin.amount)).movePointLeft(decimal).multiply(baseData.getKavaOraclePrice(WUtil.getSpotMarketId(hardParam, totalDepositCoin.denom)));

        } else {
            WDp.showCoinDp(context, baseData, hardMoneyMarket.getDenom(), "0", mPoolSupplyAmountDenom, mPoolSupplyAmount, chain);
        }
        mPoolSupplyValue.setText(WDp.getDpRawDollor(context, totalDepositValue, 2));


        // display system total borrowed
        Coin totalBorrowCoin = null;
        BigDecimal totalBorrowValue = BigDecimal.ZERO;
        if (totalborrow != null) {
            for (CoinOuterClass.Coin coin: totalborrow) {
                if (coin.getDenom().equalsIgnoreCase(hardMoneyMarket.getDenom())) {
                    totalBorrowCoin = new Coin(coin.getDenom(), coin.getAmount());
                }
            }
        }
        if (totalBorrowCoin != null) {
            WDp.showCoinDp(context, baseData, totalBorrowCoin, mPoolBorrowedAmountDenom, mPoolBorrowedAmount, chain);
            int decimal =  WUtil.getKavaCoinDecimal(baseData, totalBorrowCoin.denom);
            totalBorrowValue = (new BigDecimal(totalBorrowCoin.amount)).movePointLeft(decimal).multiply( baseData.getKavaOraclePrice(WUtil.getSpotMarketId(hardParam, totalBorrowCoin.denom)));

        } else {
            WDp.showCoinDp(context, baseData, hardMoneyMarket.getDenom(), "0", mPoolBorrowedAmountDenom, mPoolBorrowedAmount, chain);
        }
        mPoolBorrowedValue.setText(WDp.getDpRawDollor(context, totalBorrowValue, 2));


        // display system remain borrowable
        BigDecimal SystemBorrowableAmount   = BigDecimal.ZERO;
        BigDecimal SystemBorrowableValue    = BigDecimal.ZERO;
        BigDecimal moduleAmount             = BigDecimal.ZERO;
        BigDecimal reserveAmount            = BigDecimal.ZERO;
        if (moduleCoins != null) {
            for (Coin coin: moduleCoins) {
                if (coin.denom.equals(hardMoneyMarket.getDenom())) {
                    moduleAmount = new BigDecimal(coin.amount);
                }
            }
        }
        if (reserveCoin != null) {
            for (CoinOuterClass.Coin coin: reserveCoin) {
                if (coin.getDenom().equalsIgnoreCase(hardMoneyMarket.getDenom())) {
                    reserveAmount = new BigDecimal(coin.getAmount());
                }
            }
        }
        BigDecimal moduleBorrowable = moduleAmount.subtract(reserveAmount);
        if (hardMoneyMarket.getBorrowLimit().getHasMaxLimit()) {
            SystemBorrowableAmount = new BigDecimal(hardMoneyMarket.getBorrowLimit().getMaximumLimit()).movePointLeft(18).min(moduleBorrowable);
        } else {
            SystemBorrowableAmount = moduleBorrowable;
        }
        WDp.showCoinDp(context, baseData, hardMoneyMarket.getDenom(), SystemBorrowableAmount.toPlainString(), mRemainBorrowableAmountDenom, mRemainBorrowableAmount, chain);

        int decimal =  WUtil.getKavaCoinDecimal(baseData, hardMoneyMarket.getDenom());
        SystemBorrowableValue = SystemBorrowableAmount.movePointLeft(decimal).multiply(baseData.getKavaOraclePrice(WUtil.getSpotMarketId(hardParam, hardMoneyMarket.getDenom())));
        mRemainBorrowableValue.setText(WDp.getDpRawDollor(context, SystemBorrowableValue, 2));
    }
}
