package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.base.BaseFragment;

public class BroadCastStepFeeFragment extends BaseFragment {

    public static BroadCastStepFeeFragment newInstance(Bundle bundle) {
        BroadCastStepFeeFragment fragment = new BroadCastStepFeeFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }


    private BaseBroadCastActivity getSActivity() {
        return (BaseBroadCastActivity)getBaseActivity();
    }
}
