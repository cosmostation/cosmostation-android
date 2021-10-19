package wannabit.io.cosmostaion.fragment.chains.sif;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.sif.SifSwapActivity;
import wannabit.io.cosmostaion.base.BaseFragment;

public class SifSwapStep3Fragment extends BaseFragment implements View.OnClickListener{

    public static SifSwapStep3Fragment newInstance(Bundle bundle) {
        SifSwapStep3Fragment fragment = new SifSwapStep3Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_swap_step3, container, false);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
    }

    private SifSwapActivity getSActivity() {
        return (SifSwapActivity)getBaseActivity();
    }

    @Override
    public void onClick(View v) {
    }
}
