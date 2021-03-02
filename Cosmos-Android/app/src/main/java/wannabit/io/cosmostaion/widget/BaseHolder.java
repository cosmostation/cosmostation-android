package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.chains.kava.CdpDetail5Activity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.fragment.chains.kava.ListCdpFragment;
import wannabit.io.cosmostaion.fragment.chains.kava.ListHardFragment;
import wannabit.io.cosmostaion.model.kava.HardInterestRate;
import wannabit.io.cosmostaion.model.kava.HardMyBorrow;
import wannabit.io.cosmostaion.model.kava.HardMyDeposit;
import wannabit.io.cosmostaion.model.kava.HardParam;
import wannabit.io.cosmostaion.model.kava.MyCdp;
import wannabit.io.cosmostaion.model.kava.CollateralParam;
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

    public void onBindUsdxIncentive(Context context, ListCdpFragment fragment, IncentiveReward incentiveReward) {
    }

    public void onBindHardIncentive(Context context, ListHardFragment fragment, IncentiveReward incentiveReward) {
    }

    public void onBindMyCdp(Context context, BaseData baseData, MyCdp myCdp) {
    }

    public void onBindOtherCdp(Context context, CollateralParam otherCdp) {
    }

    public void onBindMyHardStatus(Context context, BaseData baseData, ArrayList<HardMyDeposit> myDeposit, ArrayList<HardMyBorrow> myBorrow) {
    }

    public void onBindMyHardPool(Context context, BaseChain chain, BaseData baseData, HardParam hardParam, HardParam.HardMoneyMarket hardMoneyMarket, IncentiveReward incentiveReward, ArrayList<HardInterestRate> HardInterestRates, ArrayList<HardMyDeposit> myDeposit, ArrayList<HardMyBorrow> myBorrow) {
    }

    public void onBindCdpDetailInfo(CdpDetail5Activity context, BaseData baseData, MyCdp myCdp, String collateralType, BigDecimal debtAmount) {
    }

    public void onBindCdpDetailMyStatus(CdpDetail5Activity context, BaseData baseData, MyCdp myCdp, String collateralType, BigDecimal selfDepositAmount) {
    }

    public void onBindCdpDetailAvailable(CdpDetail5Activity context, BaseData baseData, String collateralType) {
    }

    public void onBindHardDetailInfo(Context context, BaseChain chain, BaseData baseData, String denom, IncentiveReward incentiveReward, ArrayList<HardInterestRate> HardInterestRates,
                                     ArrayList<Coin> totalDeposit, ArrayList<Coin> totalborrow, ArrayList<Coin> moduleCoins, ArrayList<Coin> reserveCoin) {
    }

    public void onBindHardDetailMyStatus(Context context, BaseData baseData, BaseChain chain, String denom, ArrayList<HardMyDeposit> myDeposit,
                                         ArrayList<HardMyBorrow> myBorrow, ArrayList<Coin> moduleCoins, ArrayList<Coin> reserveCoin) {

    }

    public void onBindHardDetailAvailable(Context context, BaseData baseData, BaseChain chain, String denom) {

    }
}
