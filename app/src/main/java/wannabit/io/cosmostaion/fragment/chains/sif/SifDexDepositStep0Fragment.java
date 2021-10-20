package wannabit.io.cosmostaion.fragment.chains.sif;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.sif.SifDepositPoolActivity;
import wannabit.io.cosmostaion.base.BaseFragment;

public class SifDexDepositStep0Fragment extends BaseFragment {

    public static SifDexDepositStep0Fragment newInstance(Bundle bundle) {
        SifDexDepositStep0Fragment fragment = new SifDexDepositStep0Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_join_pool_step0, container, false);
        return rootView;
    }

    private SifDepositPoolActivity getSActivity() {
        return (SifDepositPoolActivity)getBaseActivity();
    }
}
