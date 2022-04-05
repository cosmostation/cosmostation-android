package wannabit.io.cosmostaion.widget;

import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.math.BigDecimal;
import java.util.ArrayList;

import cosmos.base.v1beta1.CoinOuterClass;
import kava.hard.v1beta1.Hard;
import kava.hard.v1beta1.QueryOuterClass;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.kava.HardDetailActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

public class HardDetailMyStatusHolder extends BaseHolder {
    private final ImageView mDepositCoinImg;
    private final TextView mDepositCoinTitle;
    private final TextView mDepositAmountTv;
    private final TextView mDepositDenomTv;
    private final TextView mDepositValueTv;
    private final TextView mBorrowedAmountTv;
    private final TextView mBorrowedDenomTv;
    private final TextView mBorrowedValueTv;
    private final TextView mBorrowableAmountTv;
    private final TextView mBorrowableDenomTv;
    private final TextView mBorrowableValueTv;
    private final RelativeLayout mMyBtnSupply;
    private final RelativeLayout mMyBtnWithdraw;
    private final RelativeLayout mMyBtnBorrow;
    private final RelativeLayout mMyBtnRepay;

    public HardDetailMyStatusHolder(@NonNull View itemView) {
        super(itemView);
        mDepositCoinImg = itemView.findViewById(R.id.deposit_icon);
        mDepositCoinTitle = itemView.findViewById(R.id.deposit_denom);

        mDepositAmountTv = itemView.findViewById(R.id.deposited_amount);
        mDepositDenomTv = itemView.findViewById(R.id.deposited_symbol);
        mDepositValueTv = itemView.findViewById(R.id.deposited_value);
        mBorrowedAmountTv = itemView.findViewById(R.id.borrowed_amount);
        mBorrowedDenomTv = itemView.findViewById(R.id.borrowed_symbol);
        mBorrowedValueTv = itemView.findViewById(R.id.borrowed_value);
        mBorrowableAmountTv = itemView.findViewById(R.id.borrowable_amount);
        mBorrowableDenomTv = itemView.findViewById(R.id.borrowable_symbol);
        mBorrowableValueTv = itemView.findViewById(R.id.borrowable_value);

        mMyBtnSupply = itemView.findViewById(R.id.btn_deposit);
        mMyBtnWithdraw = itemView.findViewById(R.id.btn_withdraw);
        mMyBtnBorrow = itemView.findViewById(R.id.btn_borrow);
        mMyBtnRepay = itemView.findViewById(R.id.btn_repay);
    }

    @Override
    public void onBindHardDetailMyStatus(HardDetailActivity context, BaseData baseData, BaseChain chain, String denom, ArrayList<QueryOuterClass.DepositResponse> myDeposit,
                                         ArrayList<QueryOuterClass.BorrowResponse> myBorrow, ArrayList<Coin> moduleCoins, ArrayList<CoinOuterClass.Coin> reserveCoin) {

        final Hard.Params hardParam = baseData.mHardParams;
        final Hard.MoneyMarket hardMoneyMarket = WUtil.getHardMoneyMarket(hardParam, denom);

        WUtil.DpKavaTokenImg(baseData, mDepositCoinImg, hardMoneyMarket.getDenom());
        WUtil.dpKavaTokenName(context, baseData, mDepositCoinTitle, hardMoneyMarket.getDenom());

        //Display My Supply
        final BigDecimal totalSuppliedValue = WUtil.getHardSuppliedValueByDenom(context, baseData, denom, myDeposit);
        final BigDecimal totalSuppliedAmount = WUtil.getHardSuppliedAmountByDenom(context, baseData, denom, myDeposit);
        WDp.showCoinDp(context, baseData, hardMoneyMarket.getDenom(), totalSuppliedAmount.toPlainString(), mDepositDenomTv, mDepositAmountTv, chain);
        mDepositValueTv.setText(WDp.getDpRawDollor(context, totalSuppliedValue, 2));

        //Display My Borrowed
        final BigDecimal totalBorrowedValue = WUtil.getHardBorrowedValueByDenom(context, baseData, denom, myBorrow);
        final BigDecimal totalBorrowedAmount = WUtil.getHardBorrowedAmountByDenom(context, baseData, denom, myBorrow);
        WDp.showCoinDp(context, baseData, hardMoneyMarket.getDenom(), totalBorrowedAmount.toPlainString(), mBorrowedDenomTv, mBorrowedAmountTv, chain);
        mBorrowedValueTv.setText(WDp.getDpRawDollor(context, totalBorrowedValue, 2));

        //Display My Borrowable
        final BigDecimal finalBorrowableValue = WUtil.getHardBorrowableValueByDenom(context, baseData, denom, myDeposit, myBorrow, moduleCoins, reserveCoin);
        final BigDecimal finalBorrowableAmount = WUtil.getHardBorrowableAmountByDenom(context, baseData, denom, myDeposit, myBorrow, moduleCoins, reserveCoin);
        WDp.showCoinDp(context, baseData, hardMoneyMarket.getDenom(), finalBorrowableAmount.toPlainString(), mBorrowableDenomTv, mBorrowableAmountTv, chain);
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
