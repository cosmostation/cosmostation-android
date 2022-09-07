package wannabit.io.cosmostaion.widget.kava;

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

import cosmos.base.v1beta1.CoinOuterClass;
import kava.hard.v1beta1.Hard;
import kava.hard.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.txs.kava.HardDetailActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.base.chains.Kava;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class HardPoolHolder extends BaseHolder {

    CardView            itemRoot;
    private ImageView   hardPoolImg;
    private TextView    hardPoolTitle;
    private TextView    supplyApyTv, borrowApyTv, supplyIncentiveApyTv, borrowIncentiveApyTv;
    private TextView    depositAmountTv, depositDenomTv, depositValueTv;
    private TextView    borrowAmountTv, borrowDenomTv, borrowValueTv;

    public HardPoolHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot                = itemView.findViewById(R.id.card_root);
        hardPoolImg             = itemView.findViewById(R.id.pool_img);
        hardPoolTitle           = itemView.findViewById(R.id.pool_title);
        supplyApyTv             = itemView.findViewById(R.id.supply_apy);
        borrowApyTv             = itemView.findViewById(R.id.borrow_apy);
        supplyIncentiveApyTv    = itemView.findViewById(R.id.supply_incentive_apy);
        borrowIncentiveApyTv    = itemView.findViewById(R.id.borrow_incentive_apy);
        depositAmountTv         = itemView.findViewById(R.id.deposited_amount);
        depositDenomTv          = itemView.findViewById(R.id.deposited_denom);
        depositValueTv          = itemView.findViewById(R.id.deposited_value);
        borrowAmountTv          = itemView.findViewById(R.id.borrow_amount);
        borrowDenomTv           = itemView.findViewById(R.id.borrow_denom);
        borrowValueTv           = itemView.findViewById(R.id.borrow_value);
    }

    @Override
    public void onBindMyHardPool(Context context, BaseData baseData, Hard.Params hardParams, Hard.MoneyMarket hardMoneyMarket, IncentiveReward incentiveReward,
                                 ArrayList<QueryOuterClass.MoneyMarketInterestRate> HardInterestRates, ArrayList<QueryOuterClass.DepositResponse> myDeposit, ArrayList<QueryOuterClass.BorrowResponse> myBorrow, int position) {
        final ChainConfig chainConfig = ChainFactory.getChain(BaseChain.KAVA_MAIN);
        String baseDenom = "";
        if (hardMoneyMarket.getDenom().startsWith("ibc/")) baseDenom = baseData.getBaseDenom(hardMoneyMarket.getDenom());
        else baseDenom = hardMoneyMarket.getDenom();
        try {
            Picasso.get().load(Kava.KAVA_HARD_POOL_IMG_URL + "lp" + baseDenom + ".png").fit().into(hardPoolImg);
        } catch (Exception e) { }

        String marketTitle = hardParams.getMoneyMarkets(position).getSpotMarketId().replace(":30", "");
        hardPoolTitle.setText(marketTitle.toUpperCase());

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

        supplyApyTv.setText(WDp.getPercentDp(supplyApy.multiply(new BigDecimal("100"))));
        borrowApyTv.setText(WDp.getPercentDp(borrowApy.multiply(new BigDecimal("100"))));
        supplyIncentiveApyTv.setText(WDp.getPercentDp(supplyIncentiveApy.multiply(new BigDecimal("100"))));
        borrowIncentiveApyTv.setText(WDp.getPercentDp(borrowIncentive.multiply(new BigDecimal("100"))));

        Coin myDepositCoin = null;
        BigDecimal myDepositValue = BigDecimal.ZERO;
        if (myDeposit != null && myDeposit.size() > 0) {
            for (CoinOuterClass.Coin coin: myDeposit.get(0).getAmountList()) {
                if (coin.getDenom().equalsIgnoreCase(hardMoneyMarket.getDenom())) {
                    myDepositCoin = new Coin(coin.getDenom(), coin.getAmount());
                }
            }
        }
        if (myDepositCoin != null) {
            WDp.setDpCoin(context, baseData, chainConfig, myDepositCoin, depositDenomTv, depositAmountTv);
            int decimal = WDp.getDenomDecimal(baseData, chainConfig, myDepositCoin.denom);
            myDepositValue = (new BigDecimal(myDepositCoin.amount)).movePointLeft(decimal).multiply(baseData.getKavaOraclePrice(WUtil.getSpotMarketId(hardParams, myDepositCoin.denom)));

        } else {
            WDp.setDpCoin(context, baseData, chainConfig, hardMoneyMarket.getDenom(), "0", depositDenomTv, depositAmountTv);
        }
        depositValueTv.setText(WDp.getDpRawDollor(context, myDepositValue, 2));


        Coin myBorrowCoin = null;
        BigDecimal myBorrowValue = BigDecimal.ZERO;
        if (myBorrow != null && myBorrow.size() > 0) {
            for (CoinOuterClass.Coin coin: myBorrow.get(0).getAmountList()) {
                if (coin.getDenom().equalsIgnoreCase(hardMoneyMarket.getDenom())) {
                    myBorrowCoin = new Coin(coin.getDenom(), coin.getAmount());
                }
            }
        }
        if (myBorrowCoin != null) {
            WDp.setDpCoin(context, baseData, chainConfig, myBorrowCoin, borrowDenomTv, borrowAmountTv);
            int decimal = WDp.getDenomDecimal(baseData, chainConfig, myBorrowCoin.denom);
            myBorrowValue = (new BigDecimal(myBorrowCoin.amount)).movePointLeft(decimal).multiply(baseData.getKavaOraclePrice(WUtil.getSpotMarketId(hardParams, myBorrowCoin.denom)));

        } else {
            WDp.setDpCoin(context, baseData, chainConfig, hardMoneyMarket.getDenom(), "0", borrowDenomTv, borrowAmountTv);
        }
        borrowValueTv.setText(WDp.getDpRawDollor(context, myBorrowValue, 2));


        itemRoot.setOnClickListener(v -> {
            Intent intent = new Intent(context, HardDetailActivity.class);
            intent.putExtra("hard_money_market_denom", hardMoneyMarket.getDenom());
            context.startActivity(intent);
        });
    }
}
