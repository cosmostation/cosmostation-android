package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.dao.Account;
import wannabit.io.cosmostaion.fragment.chains.kava.ListCdpFragment;
import wannabit.io.cosmostaion.network.res.ResKavaCdpParam;
import wannabit.io.cosmostaion.network.res.ResKavaIncentiveReward5;
import wannabit.io.cosmostaion.network.res.ResKavaMyCdps;

public class BaseHolder extends RecyclerView.ViewHolder {

    public BaseHolder(@NonNull View itemView) {
        super(itemView);
    }

    public void onBindHolder(MainActivity mainActivity) {

    }

    public void onBindTokenHolder(Context context, BaseChain chain, BaseData baseData, String denom) {

    }

    public void onBindUsdxIncentive(Context context, ListCdpFragment fragment, ResKavaIncentiveReward5.IncentiveReward5 incentiveReward) {

    }

    public void onBindMyCdp(Context context, BaseData baseData, ResKavaMyCdps.MyCDP myCdp) {

    }

    public void onBindOtherCdp(Context context, ResKavaCdpParam.KavaCollateralParam otherCdp) {

    }

}
