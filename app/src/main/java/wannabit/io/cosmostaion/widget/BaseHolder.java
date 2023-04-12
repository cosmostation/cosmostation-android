package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;

import cosmos.base.v1beta1.CoinOuterClass;
import kava.cdp.v1beta1.Genesis;
import kava.hard.v1beta1.Hard;
import kava.swap.v1beta1.QueryOuterClass;
import osmosis.gamm.v1beta1.BalancerPool;
import sifnode.clp.v1.Querier;
import sifnode.clp.v1.Types;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.txs.kava.CdpDetailActivity;
import wannabit.io.cosmostaion.activities.txs.kava.HardDetailActivity;
import wannabit.io.cosmostaion.activities.txs.sif.SifDexListActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.fragment.txs.kava.ListCdpFragment;
import wannabit.io.cosmostaion.fragment.txs.kava.ListHardFragment;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;
import wannabit.io.cosmostaion.model.type.Coin;

public class BaseHolder extends RecyclerView.ViewHolder {

    public BaseHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void onBindHolder(MainActivity mainActivity) {

    }

    public void onBindTokenHolder(Context context, BaseChain chain, BaseData baseData, String denom) {
    }

    public void onBindMyCdp(Context context, BaseData baseData, kava.cdp.v1beta1.QueryOuterClass.CDPResponse myCdp) {
    }

    public void onBindOtherCdp(Context context, Genesis.CollateralParam otherCdp) {
    }

    public void onBindMyHardStatus(Context context, BaseData baseData, ArrayList<kava.hard.v1beta1.QueryOuterClass.DepositResponse> myDeposit, ArrayList<kava.hard.v1beta1.QueryOuterClass.BorrowResponse> myBorrow) {
    }

    public void onBindMyHardPool(Context context, BaseData baseData, Hard.Params hardParams, Hard.MoneyMarket hardMoneyMarket, IncentiveReward incentiveReward, ArrayList<kava.hard.v1beta1.QueryOuterClass.MoneyMarketInterestRate> HardInterestRates, ArrayList<kava.hard.v1beta1.QueryOuterClass.DepositResponse> myDeposit, ArrayList<kava.hard.v1beta1.QueryOuterClass.BorrowResponse> myBorrow, int postion) {
    }

    public void onBindCdpDetailInfo(CdpDetailActivity context, BaseData baseData, kava.cdp.v1beta1.QueryOuterClass.CDPResponse myCdp, String collateralType, BigDecimal debtAmount) {
    }

    public void onBindCdpDetailMyStatus(CdpDetailActivity context, BaseData baseData, kava.cdp.v1beta1.QueryOuterClass.CDPResponse myCdp, String collateralType, BigDecimal selfDepositAmount) {
    }

    public void onBindCdpDetailAvailable(CdpDetailActivity context, BaseData baseData, String collateralType) {
    }

    public void onBindHardDetailInfo(HardDetailActivity context, BaseData baseData, String denom, IncentiveReward incentiveReward, ArrayList<kava.hard.v1beta1.QueryOuterClass.MoneyMarketInterestRate> HardInterestRates,
                                     ArrayList<CoinOuterClass.Coin> totalDeposit, ArrayList<CoinOuterClass.Coin> totalborrow, ArrayList<Coin> moduleCoins, ArrayList<CoinOuterClass.Coin> reserveCoin) {
    }

    public void onBindHardDetailMyStatus(HardDetailActivity context, BaseData baseData, String denom, ArrayList<kava.hard.v1beta1.QueryOuterClass.DepositResponse> myDeposit,
                                         ArrayList<kava.hard.v1beta1.QueryOuterClass.BorrowResponse> myBorrow, ArrayList<Coin> moduleCoins, ArrayList<CoinOuterClass.Coin> reserveCoin) {
    }

    public void onBindHardDetailAvailable(HardDetailActivity context, BaseData baseData, String denom) {
    }

    public void onBindKavaMyPool(Context context, BaseActivity activity, BaseData baseData, QueryOuterClass.PoolResponse myPool, QueryOuterClass.DepositResponse myDeposit) {
    }

    public void onBindKavaOtherPool(Context context, BaseActivity activity, BaseData baseData, QueryOuterClass.PoolResponse otherPool) {
    }

    public void onBindSifMyPool(Context context, SifDexListActivity activity, BaseData baseData, Types.Pool myPool, Querier.LiquidityProviderRes myProvider) {
    }

    public void onBindSifOtherPool(Context context, SifDexListActivity activity, BaseData baseData, Types.Pool otherPool) {
    }

}
