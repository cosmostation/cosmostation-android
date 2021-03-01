package wannabit.io.cosmostaion.widget;

import android.content.Context;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.fragment.chains.kava.ListCdpFragment;
import wannabit.io.cosmostaion.model.kava.MyCdp;
import wannabit.io.cosmostaion.model.kava.CollateralParam;
import wannabit.io.cosmostaion.model.kava.IncentiveReward;

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

    public void onBindMyCdp(Context context, BaseData baseData, MyCdp myCdp) {

    }

    public void onBindOtherCdp(Context context, CollateralParam otherCdp) {

    }

}
