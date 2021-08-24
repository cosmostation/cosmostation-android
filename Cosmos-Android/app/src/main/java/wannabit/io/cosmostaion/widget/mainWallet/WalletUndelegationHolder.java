package wannabit.io.cosmostaion.widget.mainWallet;

import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import org.jetbrains.annotations.NotNull;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseData;
import wannabit.io.cosmostaion.model.UnbondingInfo;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class WalletUndelegationHolder extends BaseHolder {
    private CardView            mUndelegateCard;
    private TextView            mUndelegateCnt;
    private RelativeLayout      mUndelegateLayer0, mUndelegateLayer1, mUndelegateLayer2, mUndelegateLayer3, mUndelegateLayer4;
    private TextView            mUndelegateTime0, mUndelegateTime1, mUndelegateTime2, mUndelegateTime3, mUndelegateTime4;
    private TextView            mUndelegateAmount0, mUndelegateAmount1, mUndelegateAmount2, mUndelegateAmount3, mUndelegateAmount4;
    private TextView            mUndelegateRemain0, mUndelegateRemain1, mUndelegateRemain2, mUndelegateRemain3, mUndelegateRemain4;

    public WalletUndelegationHolder(@NonNull View itemView) {
        super(itemView);
        mUndelegateCard         = itemView.findViewById(R.id.card_root);
        mUndelegateCnt          = itemView.findViewById(R.id.undelegate_count);

        mUndelegateLayer0       = itemView.findViewById(R.id.undelegate_detail_layer0);
        mUndelegateLayer1       = itemView.findViewById(R.id.undelegate_detail_layer1);
        mUndelegateLayer2       = itemView.findViewById(R.id.undelegate_detail_layer2);
        mUndelegateLayer3       = itemView.findViewById(R.id.undelegate_detail_layer3);
        mUndelegateLayer4       = itemView.findViewById(R.id.undelegate_detail_layer4);

        mUndelegateTime0        = itemView.findViewById(R.id.undelegate_detail_time0);
        mUndelegateTime1        = itemView.findViewById(R.id.undelegate_detail_time1);
        mUndelegateTime2        = itemView.findViewById(R.id.undelegate_detail_time2);
        mUndelegateTime3        = itemView.findViewById(R.id.undelegate_detail_time3);
        mUndelegateTime4        = itemView.findViewById(R.id.undelegate_detail_time4);

        mUndelegateAmount0      = itemView.findViewById(R.id.undelegate_detail_amount0);
        mUndelegateAmount1      = itemView.findViewById(R.id.undelegate_detail_amount1);
        mUndelegateAmount2      = itemView.findViewById(R.id.undelegate_detail_amount2);
        mUndelegateAmount3      = itemView.findViewById(R.id.undelegate_detail_amount3);
        mUndelegateAmount4      = itemView.findViewById(R.id.undelegate_detail_amount4);

        mUndelegateRemain0     = itemView.findViewById(R.id.undelegate_detail_time0_remain);
        mUndelegateRemain1     = itemView.findViewById(R.id.undelegate_detail_time1_remain);
        mUndelegateRemain2     = itemView.findViewById(R.id.undelegate_detail_time2_remain);
        mUndelegateRemain3     = itemView.findViewById(R.id.undelegate_detail_time3_remain);
        mUndelegateRemain4     = itemView.findViewById(R.id.undelegate_detail_time4_remain);
    }

    public void onBindHolder(@NotNull MainActivity mainActivity) {
        final int decimal = WDp.mainDivideDecimal(mainActivity.mBaseChain);
        final BaseData baseData = mainActivity.getBaseDao();
        mUndelegateLayer1.setVisibility(View.GONE);
        mUndelegateLayer2.setVisibility(View.GONE);
        mUndelegateLayer3.setVisibility(View.GONE);
        mUndelegateLayer4.setVisibility(View.GONE);
        mUndelegateCard.setCardBackgroundColor(WDp.getChainBgColor(mainActivity, mainActivity.mBaseChain));

        final ArrayList<UnbondingInfo.DpEntry> unbondings = WUtil.onSortUnbondingsRecent(mainActivity, baseData.mMyUnbondings);
        mUndelegateCnt.setText(String.valueOf(unbondings.size()));
        mUndelegateTime0.setText(WDp.getTimeformat(mainActivity, unbondings.get(0).completion_time));
        mUndelegateAmount0.setText(WDp.getDpAmount2(mainActivity, new BigDecimal(unbondings.get(0).balance), decimal, decimal));
        mUndelegateRemain0.setText(WDp.getUnbondingTimeleft(mainActivity, WDp.dateToLong(mainActivity, unbondings.get(0).completion_time)));

        if (unbondings.size() > 1) {
            mUndelegateLayer1.setVisibility(View.VISIBLE);
            mUndelegateTime1.setText(WDp.getTimeformat(mainActivity, unbondings.get(1).completion_time));
            mUndelegateAmount1.setText(WDp.getDpAmount2(mainActivity, new BigDecimal(unbondings.get(1).balance), decimal, decimal));
            mUndelegateRemain1.setText(WDp.getUnbondingTimeleft(mainActivity, WDp.dateToLong(mainActivity, unbondings.get(1).completion_time)));
        }
        if (unbondings.size() > 2) {
            mUndelegateLayer2.setVisibility(View.VISIBLE);
            mUndelegateTime2.setText(WDp.getTimeformat(mainActivity, unbondings.get(2).completion_time));
            mUndelegateAmount2.setText(WDp.getDpAmount2(mainActivity, new BigDecimal(unbondings.get(2).balance), decimal, decimal));
            mUndelegateRemain2.setText(WDp.getUnbondingTimeleft(mainActivity, WDp.dateToLong(mainActivity, unbondings.get(2).completion_time)));
        }
        if (unbondings.size() > 3) {
            mUndelegateLayer3.setVisibility(View.VISIBLE);
            mUndelegateTime3.setText(WDp.getTimeformat(mainActivity, unbondings.get(3).completion_time));
            mUndelegateAmount3.setText(WDp.getDpAmount2(mainActivity, new BigDecimal(unbondings.get(3).balance), decimal, decimal));
            mUndelegateRemain3.setText(WDp.getUnbondingTimeleft(mainActivity, WDp.dateToLong(mainActivity, unbondings.get(3).completion_time)));
        }
        if (unbondings.size() > 4) {
            mUndelegateLayer4.setVisibility(View.VISIBLE);
            mUndelegateTime4.setText(WDp.getTimeformat(mainActivity, unbondings.get(4).completion_time));
            mUndelegateAmount4.setText(WDp.getDpAmount2(mainActivity, new BigDecimal(unbondings.get(4).balance), decimal, decimal));
            mUndelegateRemain4.setText(WDp.getUnbondingTimeleft(mainActivity, WDp.dateToLong(mainActivity, unbondings.get(4).completion_time)));
        }

    }
}
