package wannabit.io.cosmostaion.widget.osmosis;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.widget.BaseHolder;

public class EarningOtherHolder extends BaseHolder {
    CardView itemRoot;
    TextView itemPoolId;
    TextView itemPoolCoinPair;
    TextView itemPoolApr;
    TextView itemAvailableAmount, itemAvailableDenom, itemAvailableValue;


    public EarningOtherHolder(@NonNull View itemView) {
        super(itemView);
        itemRoot = itemView.findViewById(R.id.earning_root);
        itemPoolId = itemView.findViewById(R.id.farming_pool_id);
        itemPoolCoinPair = itemView.findViewById(R.id.farming_coin_pair);
        itemPoolApr = itemView.findViewById(R.id.farming_apr);
        itemAvailableAmount = itemView.findViewById(R.id.available_amount);
        itemAvailableDenom = itemView.findViewById(R.id.available_denom);
        itemAvailableValue = itemView.findViewById(R.id.available_value);
    }
}
