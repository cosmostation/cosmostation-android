package wannabit.io.cosmostaion.fragment.broadcast.kava;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.broadcast.kava.CreateCdpActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.fragment.SendStep1Fragment;

public class CreateCdpStep1Fragment extends BaseFragment {

    public static CreateCdpStep1Fragment newInstance(Bundle bundle) {
        CreateCdpStep1Fragment fragment = new CreateCdpStep1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_tx_step_memo, container, false);
        return rootView;
    }

    private CreateCdpActivity getSActivity() {
        return (CreateCdpActivity)getBaseActivity();
    }
}
