package wannabit.io.cosmostaion.fragment.chains.osmosis;

import android.os.Bundle;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.fragment.chains.kava.ClaimHardIncentiveStep0Fragment;

public class JoinPoolStep3Fragment extends BaseFragment {

    public static JoinPoolStep3Fragment newInstance(Bundle bundle) {
        JoinPoolStep3Fragment fragment = new JoinPoolStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }
}
