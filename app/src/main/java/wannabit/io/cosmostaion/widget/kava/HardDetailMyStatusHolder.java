package wannabit.io.cosmostaion.widget.kava;

import static wannabit.io.cosmostaion.base.BaseChain.KAVA_MAIN;

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
import wannabit.io.cosmostaion.activities.txs.kava.HardDetailActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.base.chains.ChainConfig;
import wannabit.io.cosmostaion.base.chains.ChainFactory;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;

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
    public void onBindHardDetailMyStatus(HardDetailActivity context, BaseData baseData, String denom, ArrayList<QueryOuterClass.DepositResponse> myDeposit,
                                         ArrayList<QueryOuterClass.BorrowResponse> myBorrow, ArrayList<Coin> moduleCoins, ArrayList<CoinOuterClass.Coin> reserveCoin) {
        final ChainConfig chainConfig                       = ChainFactory.getChain(KAVA_MAIN);
        final Hard.Params hardParam                         = baseData.mHardParams;
        final Hard.MoneyMarket hardMoneyMarket              = WUtil.getHardMoneyMarket(hardParam, denom);

        WDp.setDpSymbolImg(baseData, chainConfig, hardMoneyMarket.getDenom(), mDepositCoinImg);
        WDp.setDpSymbol(context, baseData, chainConfig, hardMoneyMarket.getDenom(), mDepositCoinTitle);

        //Display My Supply
        final BigDecimal totalSuppliedValue = WUtil.getHardSuppliedValueByDenom(context, baseData, denom, myDeposit);
        final BigDecimal totalSuppliedAmount = WUtil.getHardSuppliedAmountByDenom(context, baseData, denom, myDeposit);
        WDp.setDpCoin(context, baseData, chainConfig, hardMoneyMarket.getDenom(), totalSuppliedAmount.toPlainString(), mDepositDenomTv, mDepositAmountTv);
        mDepositValueTv.setText(WDp.getDpRawDollor(context, totalSuppliedValue, 2));

        //Display My Borrowed
        final BigDecimal totalBorrowedValue = WUtil.getHardBorrowedValueByDenom(context, baseData, denom, myBorrow);
        final BigDecimal totalBorrowedAmount = WUtil.getHardBorrowedAmountByDenom(context, baseData, denom, myBorrow);
        WDp.setDpCoin(context, baseData, chainConfig, hardMoneyMarket.getDenom(), totalBorrowedAmount.toPlainString(), mBorrowedDenomTv, mBorrowedAmountTv);
        mBorrowedValueTv.setText(WDp.getDpRawDollor(context, totalBorrowedValue, 2));

        //Display My Borrowable
        final BigDecimal finalBorrowableValue = WUtil.getHardBorrowableValueByDenom(context, baseData, denom, myDeposit, myBorrow, moduleCoins, reserveCoin);
        final BigDecimal finalBorrowableAmount = WUtil.getHardBorrowableAmountByDenom(context, baseData, denom, myDeposit, myBorrow, moduleCoins, reserveCoin);
        WDp.setDpCoin(context, baseData, chainConfig, hardMoneyMarket.getDenom(), finalBorrowableAmount.toPlainString(), mBorrowableDenomTv, mBorrowableAmountTv);
        mBorrowableValueTv.setText(WDp.getDpRawDollor(context, finalBorrowableValue, 2));

        mMyBtnSupply.setOnClickListener(v -> context.onHardDeposit());
        mMyBtnWithdraw.setOnClickListener(v -> context.onHardWithdraw());
        mMyBtnBorrow.setOnClickListener(v -> context.onHardBorrow());
        mMyBtnRepay.setOnClickListener(v -> context.onHardRepay());
    }
}
