package wannabit.io.cosmostaion.fragment.chains.osmosis;

import android.os.Bundle;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.base.BaseFragment;

public class ExitPoolStep3Fragment extends BaseFragment {

    public static ExitPoolStep3Fragment newInstance(Bundle bundle) {
        ExitPoolStep3Fragment fragment = new ExitPoolStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
