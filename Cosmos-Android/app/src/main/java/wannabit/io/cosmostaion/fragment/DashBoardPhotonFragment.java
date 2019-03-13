package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.utils.WDp;

public class DashBoardPhotonFragment extends BaseFragment {
    private TextView        mTvPhotonValue, mTvPhotonTotal, mTvPhotonBalance, mTvPhotonRewards;

    public static DashBoardPhotonFragment newInstance(Bundle bundle) {
        DashBoardPhotonFragment fragment = new DashBoardPhotonFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_dash_photon, container, false);
        mTvPhotonValue          = rootView.findViewById(R.id.dash_photon_value);
        mTvPhotonTotal          = rootView.findViewById(R.id.dash_photon_amount);
        mTvPhotonBalance        = rootView.findViewById(R.id.dash_photon_balance);
        mTvPhotonRewards        = rootView.findViewById(R.id.dash_photon_reward);

        onUpdateView();
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        onUpdateView();
    }

    private void onUpdateView() {
        mTvPhotonValue.setText("N/A");
        mTvPhotonTotal.setText(WDp.getDpAllPhoton(getContext(), getMainActivity().mBalances, getMainActivity().mRewards, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
        mTvPhotonBalance.setText(WDp.getDpPhotonBalance(getContext(), getMainActivity().mBalances, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
        mTvPhotonRewards.setText(WDp.getDpAllPhotonRewardAmount(getContext(), getMainActivity().mRewards, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
    }




    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }
}
