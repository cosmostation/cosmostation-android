package wannabit.io.cosmostaion.fragment.chains.kava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseFragment;

public class HarvestMarketFragment extends BaseFragment {

    public static HarvestMarketFragment newInstance(Bundle bundle) {
        HarvestMarketFragment fragment = new HarvestMarketFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_harvest_market, container, false);

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
    }
}
