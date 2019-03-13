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
import wannabit.io.cosmostaion.utils.WLog;

public class DashBoardAtomFragment extends BaseFragment {
    private TextView            mTvAtomValue, mTvAtomTotal, mTvAtomUndelegated,
                                mTvAtomDelegated, mTvAtomUnBonding, mTvAtomRewards;

    public static DashBoardAtomFragment newInstance(Bundle bundle) {
        DashBoardAtomFragment fragment = new DashBoardAtomFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView       = inflater.inflate(R.layout.fragment_dash_atom, container, false);
        mTvAtomValue        = rootView.findViewById(R.id.dash_atom_value);
        mTvAtomTotal        = rootView.findViewById(R.id.dash_atom_amount);
        mTvAtomUndelegated  = rootView.findViewById(R.id.dash_atom_undelegate);
        mTvAtomDelegated    = rootView.findViewById(R.id.dash_atom_delegate);
        mTvAtomUnBonding    = rootView.findViewById(R.id.dash_atom_unbonding);
        mTvAtomRewards      = rootView.findViewById(R.id.dash_atom_reward);

        onUpdateView();
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        onUpdateView();
    }


    private void onUpdateView() {
        mTvAtomValue.setText("N/A");
        mTvAtomTotal.setText(WDp.getDpAllAtom(getContext(), getMainActivity().mBalances, getMainActivity().mBondings, getMainActivity().mUnbondings, getMainActivity().mRewards, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
        mTvAtomUndelegated.setText(WDp.getDpAtomBalance(getContext(), getMainActivity().mBalances, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
        mTvAtomDelegated.setText(WDp.getDpAllDelegatedAmount(getContext(), getMainActivity().mBondings, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
        mTvAtomUnBonding.setText(WDp.getDpAllUnbondingAmount(getContext(), getMainActivity().mUnbondings, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
        mTvAtomRewards.setText(WDp.getDpAllAtomRewardAmount(getContext(), getMainActivity().mRewards, BaseChain.getChain(getMainActivity().mAccount.baseChain)));
    }




    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }
}
