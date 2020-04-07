package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.HtlcSendActivity;
import wannabit.io.cosmostaion.base.BaseFragment;

public class HtlcSendStep2Fragment extends BaseFragment {

    public static HtlcSendStep2Fragment newInstance(Bundle bundle) {
        HtlcSendStep2Fragment fragment = new HtlcSendStep2Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_send_step1, container, false);
        return rootView;
    }


    private HtlcSendActivity getSActivity() {
        return (HtlcSendActivity)getBaseActivity();
    }
}
