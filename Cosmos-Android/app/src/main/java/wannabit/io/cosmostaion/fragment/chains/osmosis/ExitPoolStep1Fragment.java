package wannabit.io.cosmostaion.fragment.chains.osmosis;

import android.os.Bundle;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.base.BaseFragment;

public class ExitPoolStep1Fragment extends BaseFragment{

    public static ExitPoolStep1Fragment newInstance(Bundle bundle) {
        ExitPoolStep1Fragment fragment = new ExitPoolStep1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
