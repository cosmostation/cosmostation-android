package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.HtlcSendActivity;
import wannabit.io.cosmostaion.base.BaseFragment;

public class HtlcSendStep1Fragment extends BaseFragment {

    public static HtlcSendStep1Fragment newInstance(Bundle bundle) {
        HtlcSendStep1Fragment fragment = new HtlcSendStep1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_htlc_send_step1, container, false);
        return rootView;
    }


    private HtlcSendActivity getSActivity() {
        return (HtlcSendActivity)getBaseActivity();
    }
}
