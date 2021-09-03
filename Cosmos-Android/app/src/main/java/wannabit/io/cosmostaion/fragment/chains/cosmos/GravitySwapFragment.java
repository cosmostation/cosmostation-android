package wannabit.io.cosmostaion.fragment.chains.cosmos;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseFragment;

public class GravitySwapFragment extends BaseFragment {

    public static GravitySwapFragment newInstance(Bundle bundle) {
        GravitySwapFragment fragment = new GravitySwapFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

//    @Override
//    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
//        View rootView = inflater.inflate(R.layout.fragment_swap_practice, container, false);
//        return rootView;
//    }
}
