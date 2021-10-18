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
import wannabit.io.cosmostaion.activities.chains.kava.HardDetailActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.kava.HardMyBorrow;
import wannabit.io.cosmostaion.model.kava.HardMyDeposit;
import wannabit.io.cosmostaion.model.kava.HardParam;
import wannabit.io.cosmostaion.model.kava.MarketPrice;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;

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
    public void onBindHardDetailMyStatus(HardDetailActivity context, BaseData baseData, BaseChain chain, String denom, ArrayList<HardMyDeposit> myDeposit,
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
        final BigDecimal totalSuppliedValue = WUtil.getHardSuppliedValueByDenom(context, baseData, denom, myDeposit);
        final BigDecimal totalSuppliedAmount = WUtil.getHardSuppliedAmountByDenom(context, baseData, denom, myDeposit);
        WDp.showCoinDp(context, hardMoneyMarket.denom, totalSuppliedAmount.toPlainString(), mDepositDenomTv, mDepositAmountTv, chain);
        mDepositValueTv.setText(WDp.getDpRawDollor(context, totalSuppliedValue, 2));

        //Display My Borrowed
        final BigDecimal totalBorrowedValue = WUtil.getHardBorrowedValueByDenom(context, baseData, denom, myBorrow);
        final BigDecimal totalBorrowedAmount = WUtil.getHardBorrowedAmountByDenom(context, baseData, denom, myBorrow);
        WDp.showCoinDp(context, hardMoneyMarket.denom, totalBorrowedAmount.toPlainString(), mBorrowedDenomTv, mBorrowedAmountTv, chain);
        mBorrowedValueTv.setText(WDp.getDpRawDollor(context, totalBorrowedValue, 2));

        //Display My Borrowable
        final BigDecimal finalBorrowableValue = WUtil.getHardBorrowableValueByDenom(context, baseData, denom, myDeposit, myBorrow, moduleCoins, reserveCoin);
        final BigDecimal finalBorrowableAmount = WUtil.getHardBorrowableAmountByDenom(context, baseData, denom, myDeposit, myBorrow, moduleCoins, reserveCoin);
        WDp.showCoinDp(context, hardMoneyMarket.denom, finalBorrowableAmount.toPlainString(), mBorrowableDenomTv, mBorrowableAmountTv, chain);
        mBorrowableValueTv.setText(WDp.getDpRawDollor(context, finalBorrowableValue, 2));

        mMyBtnSupply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onHardDeposit();
            }
        });
        mMyBtnWithdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onHardWithdraw();
            }
        });
        mMyBtnBorrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onHardBorrow();
            }
        });
        mMyBtnRepay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.onHardRepay();
            }
        });
    }
}
