package wannabit.io.cosmostaion.fragment.chains.rizon;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseFragment;

public class EventHorizonStep1Fragment extends BaseFragment{

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private RelativeLayout mProgress;
    private TextView mNotYet;

    public static EventHorizonStep1Fragment newInstance(Bundle bundle) {
        EventHorizonStep1Fragment fragment = new EventHorizonStep1Fragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_farming_list, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mProgress               = rootView.findViewById(R.id.reward_progress);
        mNotYet                 = rootView.findViewById(R.id.text_not_yet);
        return rootView;
    }

    @Override
    public void onRefreshTab() {
        mSwipeRefreshLayout.setVisibility(View.GONE);
        mRecyclerView.setVisibility(View.GONE);
        mProgress.setVisibility(View.GONE);
        mNotYet.setVisibility(View.VISIBLE);
    }
}
