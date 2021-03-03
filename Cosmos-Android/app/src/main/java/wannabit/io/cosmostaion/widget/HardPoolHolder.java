package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.HardDetailActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.kava.HardInterestRate;
import wannabit.io.cosmostaion.model.kava.HardMyBorrow;
import wannabit.io.cosmostaion.model.kava.HardMyDeposit;
import wannabit.io.cosmostaion.model.kava.HardParam;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_HARVEST_MARKET_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;

public class HardPoolHolder extends BaseHolder {

    CardView            itemRoot;
    private ImageView   hardPoolImg;
    private TextView    hardPoolTitle;
    private TextView    supplyApyTv, borrowApyTv, supplyIncentiveApyTv, borrowIncentiveApyTv;
    private TextView    depositAmountTv, depositDenomTv, depositValueTv;
    private TextView    borrowAmountTv, borrowDenomTv, borrowValueTv;

    public HardPoolHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView.findViewById(R.id.card_root);
        hardPoolImg = itemView.findViewById(R.id.pool_img);
        hardPoolTitle = itemView.findViewById(R.id.pool_title);
        supplyApyTv = itemView.findViewById(R.id.supply_apy);
        borrowApyTv = itemView.findViewById(R.id.borrow_apy);
        supplyIncentiveApyTv = itemView.findViewById(R.id.supply_incentive_apy);
        borrowIncentiveApyTv = itemView.findViewById(R.id.borrow_incentive_apy);
        depositAmountTv = itemView.findViewById(R.id.deposited_amount);
        depositDenomTv = itemView.findViewById(R.id.deposited_denom);
        depositValueTv = itemView.findViewById(R.id.deposited_value);
        borrowAmountTv = itemView.findViewById(R.id.borrow_amount);
        borrowDenomTv = itemView.findViewById(R.id.borrow_denom);
        borrowValueTv = itemView.findViewById(R.id.borrow_value);
    }

    @Override
    public void onBindMyHardPool(Context context, BaseChain chain, BaseData baseData, HardParam hardParam, HardParam.HardMoneyMarket hardMoneyMarket, IncentiveReward incentiveReward,
                                 ArrayList<HardInterestRate> HardInterestRates, ArrayList<HardMyDeposit> myDeposit, ArrayList<HardMyBorrow> myBorrow) {
//        WLog.w("hardMoneyMarket " + hardMoneyMarket.spot_market_id);
        try {
            Picasso.get().load(KAVA_HARVEST_MARKET_IMG_URL + "lp" + hardMoneyMarket.denom + ".png").fit().into(hardPoolImg);
        } catch (Exception e) { }

        String marketTitle = hardMoneyMarket.denom.equals(TOKEN_KAVA) ? "kava" : hardMoneyMarket.denom;
        hardPoolTitle.setText(marketTitle.toUpperCase() + " POOL");

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
//        WLog.w("supplyIncentiveApy " + supplyIncentiveApy);
//        WLog.w("borrowIncentive " + borrowIncentive);

        supplyApyTv.setText(WDp.getPercentDp(supplyApy.multiply(new BigDecimal("100"))));
        borrowApyTv.setText(WDp.getPercentDp(borrowApy.multiply(new BigDecimal("100"))));
        supplyIncentiveApyTv.setText(WDp.getPercentDp(supplyIncentiveApy.multiply(new BigDecimal("100"))));
        borrowIncentiveApyTv.setText(WDp.getPercentDp(borrowIncentive.multiply(new BigDecimal("100"))));

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
            WDp.showCoinDp(context, myDepositCoin, depositDenomTv, depositAmountTv, chain);
            int decimal =  WUtil.getKavaCoinDecimal(myDepositCoin.denom);
            MarketPrice price = baseData.mKavaTokenPrices.get(hardParam.getSpotMarketId(myDepositCoin.denom));
            if (price != null) {
                myDepositValue = (new BigDecimal(myDepositCoin.amount)).movePointLeft(decimal).multiply(new BigDecimal(price.price));
            }

        } else {
            WDp.showCoinDp(context, hardMoneyMarket.denom, "0", depositDenomTv, depositAmountTv, chain);
        }
        depositValueTv.setText(WDp.getDpRawDollor(context, myDepositValue, 2));


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
            WDp.showCoinDp(context, myBorrowCoin, borrowDenomTv, borrowAmountTv, chain);
            int decimal =  WUtil.getKavaCoinDecimal(myBorrowCoin.denom);
            MarketPrice price = baseData.mKavaTokenPrices.get(hardParam.getSpotMarketId(myBorrowCoin.denom));
            if (price != null) {
                myBorrowValue = (new BigDecimal(myBorrowCoin.amount)).movePointLeft(decimal).multiply(new BigDecimal(price.price));
            }

        } else {
            WDp.showCoinDp(context, hardMoneyMarket.denom, "0", borrowDenomTv, borrowAmountTv, chain);
        }
        borrowValueTv.setText(WDp.getDpRawDollor(context, myBorrowValue, 2));



        itemRoot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, HardDetailActivity.class);
                intent.putExtra("hard_money_market_denom", hardMoneyMarket.denom);
                context.startActivity(intent);
            }
        });
    }
}
