package wannabit.io.cosmostaion.widget.tokenDetail;

import android.content.Context;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.UnbondingInfo;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;

import static wannabit.io.cosmostaion.base.BaseChain.isGRPC;

public class UnBondingHolder extends BaseHolder {
    private CardView            mUndelegateCard;
    private TextView            mUndelegateCnt;
    private RelativeLayout      mUndelegateLayer0, mUndelegateLayer1, mUndelegateLayer2, mUndelegateLayer3, mUndelegateLayer4;
    private TextView            mUndelegateMoniker0, mUndelegateMoniker1, mUndelegateMoniker2, mUndelegateMoniker3, mUndelegateMoniker4;
    private TextView            mUndelegateAmount0, mUndelegateAmount1, mUndelegateAmount2, mUndelegateAmount3, mUndelegateAmount4;
    private TextView            mUndelegateTime0, mUndelegateTime1, mUndelegateTime2, mUndelegateTime3, mUndelegateTime4;

    public UnBondingHolder(@NonNull View itemView) {
        super(itemView);
        mUndelegateCard         = itemView.findViewById(R.id.card_root);
        mUndelegateCnt          = itemView.findViewById(R.id.undelegate_count);

        mUndelegateLayer0       = itemView.findViewById(R.id.undelegate_detail_layer0);
        mUndelegateLayer1       = itemView.findViewById(R.id.undelegate_detail_layer1);
        mUndelegateLayer2       = itemView.findViewById(R.id.undelegate_detail_layer2);
        mUndelegateLayer3       = itemView.findViewById(R.id.undelegate_detail_layer3);
        mUndelegateLayer4       = itemView.findViewById(R.id.undelegate_detail_layer4);

        mUndelegateMoniker0     = itemView.findViewById(R.id.undelegate_detail_moniker0);
        mUndelegateMoniker1     = itemView.findViewById(R.id.undelegate_detail_moniker1);
        mUndelegateMoniker2     = itemView.findViewById(R.id.undelegate_detail_moniker2);
        mUndelegateMoniker3     = itemView.findViewById(R.id.undelegate_detail_moniker3);
        mUndelegateMoniker4     = itemView.findViewById(R.id.undelegate_detail_moniker4);

        mUndelegateAmount0      = itemView.findViewById(R.id.undelegate_detail_amount0);
        mUndelegateAmount1      = itemView.findViewById(R.id.undelegate_detail_amount1);
        mUndelegateAmount2      = itemView.findViewById(R.id.undelegate_detail_amount2);
        mUndelegateAmount3      = itemView.findViewById(R.id.undelegate_detail_amount3);
        mUndelegateAmount4      = itemView.findViewById(R.id.undelegate_detail_amount4);

        mUndelegateTime0        = itemView.findViewById(R.id.undelegate_detail_time0);
        mUndelegateTime1        = itemView.findViewById(R.id.undelegate_detail_time1);
        mUndelegateTime2        = itemView.findViewById(R.id.undelegate_detail_time2);
        mUndelegateTime3        = itemView.findViewById(R.id.undelegate_detail_time3);
        mUndelegateTime4        = itemView.findViewById(R.id.undelegate_detail_time4);
    }

    @Override
    public void onBindTokenHolder(Context c, BaseChain chain, BaseData baseData, String denom) {
        mUndelegateCard.setCardBackgroundColor(WDp.getChainBgColor(c, chain));
        if (isGRPC(chain)) {
            onBindUnbondingGRPC(c, chain, baseData, denom);
        } else {
            onBindUnbonding(c, chain, baseData, denom);
        }
    }

    private void onBindUnbondingGRPC (Context c, BaseChain chain, BaseData baseData, String denom) {
        mUndelegateLayer1.setVisibility(View.GONE);
        mUndelegateLayer2.setVisibility(View.GONE);
        mUndelegateLayer3.setVisibility(View.GONE);
        mUndelegateLayer4.setVisibility(View.GONE);

        final int stakingDivideDecimal = WDp.mainDivideDecimal(chain);
        final int stakingDisplayDecimal = WDp.mainDivideDecimal(chain);

//        final ArrayList<Staking.UnbondingDelegationEntry> unbondings =
    }

    private void onBindUnbonding (Context c, BaseChain chain, BaseData baseData, String denom) {
        mUndelegateLayer1.setVisibility(View.GONE);
        mUndelegateLayer2.setVisibility(View.GONE);
        mUndelegateLayer3.setVisibility(View.GONE);
        mUndelegateLayer4.setVisibility(View.GONE);

        final int stakingDivideDecimal = WDp.mainDivideDecimal(chain);
        final int stakingDisplayDecimal = WDp.mainDivideDecimal(chain);

        final ArrayList<UnbondingInfo.DpEntry> unbondings = WUtil.onSortUnbondingsRecent(c, baseData.mMyUnbondings);
        mUndelegateCnt.setText(String.valueOf(unbondings.size()));
        mUndelegateMoniker0.setText(WUtil.getMonikerName(unbondings.get(0).validator_address, baseData.mAllValidators, false));
        mUndelegateAmount0.setText(WDp.getDpAmount2(c, new BigDecimal(unbondings.get(0).balance), stakingDivideDecimal, stakingDisplayDecimal));
        mUndelegateTime0.setText(WDp.getTimeformat(c, unbondings.get(0).completion_time));

        if (unbondings.size() > 1) {
            mUndelegateLayer1.setVisibility(View.VISIBLE);
            mUndelegateMoniker1.setText(WUtil.getMonikerName(unbondings.get(1).validator_address, baseData.mAllValidators, false));
            mUndelegateAmount1.setText(WDp.getDpAmount2(c, new BigDecimal(unbondings.get(1).balance), stakingDivideDecimal, stakingDisplayDecimal));
            mUndelegateTime1.setText(WDp.getTimeformat(c, unbondings.get(1).completion_time));
        }
        if (unbondings.size() > 2) {
            mUndelegateLayer2.setVisibility(View.VISIBLE);
            mUndelegateMoniker2.setText(WUtil.getMonikerName(unbondings.get(2).validator_address, baseData.mAllValidators, false));
            mUndelegateAmount2.setText(WDp.getDpAmount2(c, new BigDecimal(unbondings.get(2).balance), stakingDivideDecimal, stakingDisplayDecimal));
            mUndelegateTime2.setText(WDp.getTimeformat(c, unbondings.get(2).completion_time));
        }
        if (unbondings.size() > 3) {
            mUndelegateLayer3.setVisibility(View.VISIBLE);
            mUndelegateMoniker3.setText(WUtil.getMonikerName(unbondings.get(3).validator_address, baseData.mAllValidators, false));
            mUndelegateAmount3.setText(WDp.getDpAmount2(c, new BigDecimal(unbondings.get(3).balance), stakingDivideDecimal, stakingDisplayDecimal));
            mUndelegateTime3.setText(WDp.getTimeformat(c, unbondings.get(3).completion_time));
        }
        if (unbondings.size() > 4) {
            mUndelegateLayer4.setVisibility(View.VISIBLE);
            mUndelegateMoniker4.setText(WUtil.getMonikerName(unbondings.get(4).validator_address, baseData.mAllValidators, false));
            mUndelegateAmount4.setText(WDp.getDpAmount2(c, new BigDecimal(unbondings.get(4).balance), stakingDivideDecimal, stakingDisplayDecimal));
            mUndelegateTime4.setText(WDp.getTimeformat(c, unbondings.get(4).completion_time));
        }
    }
}
