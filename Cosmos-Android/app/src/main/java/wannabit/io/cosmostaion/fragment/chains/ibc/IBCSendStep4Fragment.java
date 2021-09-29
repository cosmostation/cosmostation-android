package wannabit.io.cosmostaion.fragment.chains.ibc;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseFragment;

public class IBCSendStep4Fragment extends BaseFragment {

    public static IBCSendStep4Fragment newInstance(Bundle bundle) {
        IBCSendStep4Fragment fragment = new IBCSendStep4Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_redelegate_step0, container, false);

        return rootView;
    }
}
