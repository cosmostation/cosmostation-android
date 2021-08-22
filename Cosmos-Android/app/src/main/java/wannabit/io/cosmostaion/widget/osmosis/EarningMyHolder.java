package wannabit.io.cosmostaion.widget.osmosis;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class EarningMyHolder extends BaseHolder {
    CardView itemRoot;
    TextView itemPoolId;
    TextView itemPoolCoinPair;
    TextView itemPoolApr;

    TextView itemBondedAmount, itemBondedDenom, itemBondedValue;
    TextView itemUnBondingAmount, itemUnBondingDenom, itemUnBondingValue;
    TextView itemUnBondedAmount, itemUnBondedDenom, itemUnBondedValue;
    TextView itemRewardAmount;
    TextView itemAvailableAmount, itemAvailableDenom, itemAvailableValue;


    public EarningMyHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView.findViewById(R.id.earning_root);
        itemPoolId = itemView.findViewById(R.id.myfarming_pool_id);
        itemPoolCoinPair = itemView.findViewById(R.id.myfarming_coin_pair);
        itemPoolApr = itemView.findViewById(R.id.myfarming_apr);
        itemBondedAmount = itemView.findViewById(R.id.bonded_amount);
        itemBondedDenom = itemView.findViewById(R.id.bonded_denom);
        itemBondedValue = itemView.findViewById(R.id.bonded_value);
        itemUnBondingAmount = itemView.findViewById(R.id.unbonding_amount);
        itemUnBondingDenom = itemView.findViewById(R.id.unbonding_denom);
        itemUnBondingValue = itemView.findViewById(R.id.unbonding_value);
        itemUnBondedAmount = itemView.findViewById(R.id.unbonded_amount);
        itemUnBondedDenom = itemView.findViewById(R.id.unbonded_denom);
        itemUnBondedValue = itemView.findViewById(R.id.unbonded_value);
        itemRewardAmount = itemView.findViewById(R.id.next_reward_amount);
        itemAvailableAmount = itemView.findViewById(R.id.available_amount);
        itemAvailableDenom = itemView.findViewById(R.id.available_denom);
        itemAvailableValue = itemView.findViewById(R.id.available_value);
    }
}