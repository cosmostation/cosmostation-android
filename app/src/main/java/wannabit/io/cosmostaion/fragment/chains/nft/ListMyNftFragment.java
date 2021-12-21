package wannabit.io.cosmostaion.fragment.chains.nft;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.nft.NFTListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;

public class ListMyNftFragment extends BaseFragment {

    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private RecyclerView        mRecyclerView;
    private LinearLayout        mEmptyNft;
    private RelativeLayout      mBtnCreateNft;

    public static ListMyNftFragment newInstance(Bundle bundle) {
        ListMyNftFragment fragment = new ListMyNftFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_nft_list, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mEmptyNft               = rootView.findViewById(R.id.empty_nft);
        mBtnCreateNft           = rootView.findViewById(R.id.btn_create_nft);

        return rootView;
    }

    private NFTListActivity getSActivity() { return (NFTListActivity)getBaseActivity(); }
}
