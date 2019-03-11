package wannabit.io.cosmostaion.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.test.TestAdapter;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;


public class MainSendFragment extends BaseFragment implements View.OnClickListener {

    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private NestedScrollView    mNestedScrollView;

    private ImageView           mBtnAddressDetail;
    private TextView            mAddress;
    private TextView            mTvAtomTotal, mTvAtomUndelegated,
                                mTvAtomDelegated, mTvAtomUnBonding, mTvAtomRewards;
    private TextView            mTvPhotonTotal, mTvPhotonBalance, mTvPhotonRewards;
    private TextView            mTvAtomTitle, mTvPhotonTitle;


    public static MainSendFragment newInstance(Bundle bundle) {
        MainSendFragment fragment = new MainSendFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_send, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mNestedScrollView       = rootView.findViewById(R.id.layer_scrollview);
        mBtnAddressDetail       = rootView.findViewById(R.id.address_detail);
        mAddress                = rootView.findViewById(R.id.account_Address);
        mTvAtomTotal            = rootView.findViewById(R.id.dash_atom_amount);
        mTvAtomUndelegated      = rootView.findViewById(R.id.dash_atom_undelegate);
        mTvAtomDelegated        = rootView.findViewById(R.id.dash_atom_delegate);
        mTvAtomUnBonding        = rootView.findViewById(R.id.dash_atom_unbonding);
        mTvAtomRewards          = rootView.findViewById(R.id.dash_atom_reward);
        mTvPhotonTotal          = rootView.findViewById(R.id.dash_photon_amount);
        mTvPhotonBalance        = rootView.findViewById(R.id.dash_photon_balance);
        mTvPhotonRewards        = rootView.findViewById(R.id.dash_photon_reward);

        mTvAtomTitle            = rootView.findViewById(R.id.dash_atom_title);
        mTvPhotonTitle          = rootView.findViewById(R.id.dash_photon_title);



        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                if(!getMainActivity().onFetchAccountInfo()) {
                    mSwipeRefreshLayout.setRefreshing(false);
                }
            }
        });

        mNestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    if (getMainActivity().mFloatBtn.isShown()) {
                        getMainActivity().mFloatBtn.hide();
                    }
                }
                if (scrollY < oldScrollY) {
                    if (!getMainActivity().mFloatBtn.isShown()) {
                        getMainActivity().mFloatBtn.show();
                    }
                }
            }
        });

        mBtnAddressDetail.setOnClickListener(this);
        onUpdateView();
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
        onUpdateView();
    }

    private void onUpdateView() {
        if(getMainActivity() == null || getMainActivity().mAccount == null) return;

//        if(getMainActivity().mAccount.baseChain.equals(BaseChain.GAIA_12K.getChain()))
//            mTestnet.setVisibility(View.VISIBLE);

        mTvAtomTitle.setText(WDp.DpAtom(getContext(), getMainActivity().mAccount.baseChain));
        mTvPhotonTitle.setText(WDp.DpPoton(getContext(), getMainActivity().mAccount.baseChain));

        mAddress.setText(getMainActivity().mAccount.address);

        mTvAtomTotal.setText(WDp.getDpAllAtom(getContext(), getMainActivity().mBalances, getMainActivity().mBondings, getMainActivity().mUnbondings, getMainActivity().mRewards));
        mTvAtomUndelegated.setText(WDp.getDpAtomBalance(getContext(), getMainActivity().mBalances));
        mTvAtomDelegated.setText(WDp.getDpAllDelegatedAmount(getContext(), getMainActivity().mBondings));
        mTvAtomUnBonding.setText(WDp.getDpAllUnbondingAmount(getContext(), getMainActivity().mUnbondings));
        mTvAtomRewards.setText(WDp.getDpAllAtomRewardAmount(getContext(), getMainActivity().mRewards));

        mTvPhotonTotal.setText(WDp.getDpAllPhoton(getContext(), getMainActivity().mBalances, getMainActivity().mRewards));
        mTvPhotonBalance.setText(WDp.getDpPhotonBalance(getContext(), getMainActivity().mBalances));
        mTvPhotonRewards.setText(WDp.getDpAllPhotonRewardAmount(getContext(), getMainActivity().mRewards));
    }

    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBtnAddressDetail)) {
            Bundle bundle = new Bundle();
            bundle.putString("address", getMainActivity().mAccount.address);
            if(TextUtils.isEmpty(getMainActivity().mAccount.nickName)) bundle.putString("title", "Wallet " + getMainActivity().mAccount.id);
            else bundle.putString("title", getMainActivity().mAccount.nickName);
            Dialog_AccountShow show = Dialog_AccountShow.newInstance(bundle);
            show.setCancelable(true);
            show.show(getFragmentManager().beginTransaction(), "dialog");
        }
    }
}
/*
public class MainSendFragment extends BaseFragment {

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private LinearLayoutManager mLinearLayoutManager;
    private TestAdapter mTestAdapter;

    public static MainSendFragment newInstance(Bundle bundle) {
        MainSendFragment fragment = new MainSendFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_send, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });

        mLinearLayoutManager = new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false);
        mRecyclerView.setLayoutManager(mLinearLayoutManager);
        mRecyclerView.setHasFixedSize(true);
        mTestAdapter = new TestAdapter(getBaseActivity());
        mRecyclerView.setAdapter(mTestAdapter);
        return rootView;
    }
}
*/


