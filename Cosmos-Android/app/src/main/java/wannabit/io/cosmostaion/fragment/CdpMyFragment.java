package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.KavaCdpListActivity;
import wannabit.io.cosmostaion.activities.ValidatorListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WLog;

public class CdpMyFragment extends BaseFragment {

    public static CdpMyFragment newInstance(Bundle bundle) {
        CdpMyFragment fragment = new CdpMyFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_my_cdp, container, false);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        WLog.w("CdpMayFragment onRefreshTab");
        if(!isAdded()) return;
    }

    public KavaCdpListActivity getMainActivity() {
        return (KavaCdpListActivity)getBaseActivity();
    }
}
