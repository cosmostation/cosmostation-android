package wannabit.io.cosmostaion.fragment.chains.rizon;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseFragment;

public class EventHorizonStep0Fragment extends BaseFragment{

    public static EventHorizonStep0Fragment newInstance(Bundle bundle) {
        EventHorizonStep0Fragment fragment = new EventHorizonStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_event_horizon_step0, container, false);
        return rootView;
    }
}
