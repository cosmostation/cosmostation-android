package wannabit.io.cosmostaion.fragment.chains.cosmos;

import android.os.Bundle;

import wannabit.io.cosmostaion.base.BaseFragment;

public class GravityPoolListFragment extends BaseFragment {

    public static GravityPoolListFragment newInstance(Bundle bundle) {
        GravityPoolListFragment fragment = new GravityPoolListFragment();
        fragment.setArguments(bundle);
        return fragment;
    }
}
