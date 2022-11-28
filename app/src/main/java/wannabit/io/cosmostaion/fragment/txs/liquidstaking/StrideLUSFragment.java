package wannabit.io.cosmostaion.fragment.txs.liquidstaking;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseFragment;

public class StrideLUSFragment extends BaseFragment implements View.OnClickListener {

    public static StrideLUSFragment newInstance() {
        return new StrideLUSFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_stride_lus, container, false);
        return rootView;
    }

    @Override
    public void onClick(View v) {

    }
}
