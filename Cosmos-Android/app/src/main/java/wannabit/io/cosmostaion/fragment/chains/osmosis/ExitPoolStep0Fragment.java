package wannabit.io.cosmostaion.fragment.chains.osmosis;

import android.os.Bundle;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.base.BaseFragment;

public class ExitPoolStep0Fragment extends BaseFragment {

    public static ExitPoolStep0Fragment newInstance(Bundle bundle) {
        ExitPoolStep0Fragment fragment = new ExitPoolStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
