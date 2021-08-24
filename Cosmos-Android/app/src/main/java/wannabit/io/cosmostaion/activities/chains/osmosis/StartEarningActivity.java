package wannabit.io.cosmostaion.activities.chains.osmosis;

import android.os.Bundle;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;

public class StartEarningActivity extends BaseBroadCastActivity {

    //BaseBroadCastActivity의 mOsmosisLockupDuration 사용

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_step);
    }
}
