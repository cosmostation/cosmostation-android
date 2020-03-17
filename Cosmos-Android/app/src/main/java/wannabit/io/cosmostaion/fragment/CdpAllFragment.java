package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.KavaCdpListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WLog;

public class CdpAllFragment extends BaseFragment {

    public static CdpAllFragment newInstance(Bundle bundle) {
        CdpAllFragment fragment = new CdpAllFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_all_cdp, container, false);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        WLog.w("CdpAllFragment onRefreshTab");
        if(!isAdded()) return;
    }

    public KavaCdpListActivity getMainActivity() {
        return (KavaCdpListActivity)getBaseActivity();
    }
}
