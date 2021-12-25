package wannabit.io.cosmostaion.fragment.chains.nft;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.chains.nft.NFTListActivity;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.NftMyHolder;

public class ListMyNftFragment extends BaseFragment {

    private RecyclerView        mRecyclerView;
    private LinearLayout        mEmptyNft;
    private RelativeLayout      mBtnCreateNft;
    private NFTListAdapter      mAdapter;

    public ArrayList<NFTListActivity.NFTCollectionId> mMyNFTs = new ArrayList<>();
    private long mPageTotalCnt;

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
        mRecyclerView           = rootView.findViewById(R.id.recycler);
        mEmptyNft               = rootView.findViewById(R.id.empty_nft);
        mBtnCreateNft           = rootView.findViewById(R.id.btn_create_nft);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseActivity(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new NFTListAdapter();
        mRecyclerView.setAdapter(mAdapter);

        RecyclerViewHeader recyclerViewHeader = new RecyclerViewHeader(getSActivity());
        mRecyclerView.addItemDecoration(recyclerViewHeader);

        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(@NonNull RecyclerView recyclerView, int dx, int dy) {
                super.onScrolled(recyclerView, dx, dy);

                int lastVisibleItemPosition = ((LinearLayoutManager) recyclerView.getLayoutManager()).findLastCompletelyVisibleItemPosition();
                int itemTotalCount = recyclerView.getAdapter().getItemCount() - 1;
                if (lastVisibleItemPosition == itemTotalCount) {
                    if (mPageTotalCnt > mMyNFTs.size()) {
                        getSActivity().onFetchNftListInfo();
                    }
                }
            }
        });

        mBtnCreateNft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getSActivity(), "준비중", Toast.LENGTH_SHORT).show();
            }
        });

        return rootView;
    }

    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        mMyNFTs = getSActivity().mMyNFTs;
        mPageTotalCnt = getSActivity().mPageTotalCnt;
        mAdapter.notifyDataSetChanged();

        if (mMyNFTs != null && mMyNFTs.size() > 0) {
            mEmptyNft.setVisibility(View.GONE);
            mRecyclerView.setVisibility(View.VISIBLE);
        } else {
            mEmptyNft.setVisibility(View.VISIBLE);
            mRecyclerView.setVisibility(View.GONE);
        }
    }

    private class NFTListAdapter extends RecyclerView.Adapter<BaseHolder> {

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new NftMyHolder(getLayoutInflater().inflate(R.layout.item_nft_list, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder viewHolder, int position) {
            if (mMyNFTs != null && mMyNFTs.size() > 0) {
                viewHolder.onBindNFT(getContext(), getSActivity(), mMyNFTs.get(position));
            }
        }

        @Override
        public int getItemCount() {
            return mMyNFTs.size();
        }
    }

    private NFTListActivity getSActivity() { return (NFTListActivity)getBaseActivity(); }

    public class RecyclerViewHeader extends RecyclerView.ItemDecoration {
        private final int       topPadding;

        private View            headerView;
        private TextView        mItemCnt;

        public RecyclerViewHeader(Context context) {
            topPadding = dpToPx(context, 30);
        }

        // dp -> pixel 단위로 변경
        private int dpToPx(Context context, int dp) {
            return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.getResources().getDisplayMetrics());
        }

        @Override
        public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.onDrawOver(c, parent, state);

            if (headerView == null) {
                headerView = inflateHeaderView(parent);
                mItemCnt = (TextView) headerView.findViewById(R.id.recycler_cnt);
                mItemCnt.setText(String.valueOf(mPageTotalCnt));
                fixLayoutSize(headerView, parent);
            }
            mItemCnt.setText(String.valueOf(mPageTotalCnt));

            for (int i = 0; i < parent.getChildCount(); i++) {
                View child = parent.getChildAt(i);
                drawHeader(c, child, headerView);
            }
        }

        @Override
        public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
            super.getItemOffsets(outRect, view, parent, state);

            int position = parent.getChildAdapterPosition(view);
            if (position == 0) {
                outRect.top = topPadding;
            }
        }

        private View inflateHeaderView(RecyclerView parent) {
            return LayoutInflater.from(parent.getContext()).inflate(R.layout.item_sticky_header, parent, false);
        }

        private void drawHeader(Canvas c, View child, View headerView) {
            c.save();
            headerView.draw(c);
            c.restore();
        }

        private void fixLayoutSize(View view, ViewGroup parent) {
            int widthSpec = View.MeasureSpec.makeMeasureSpec(parent.getWidth(),
                    View.MeasureSpec.EXACTLY);
            int heightSpec = View.MeasureSpec.makeMeasureSpec(parent.getHeight(),
                    View.MeasureSpec.UNSPECIFIED);

            int childWidth = ViewGroup.getChildMeasureSpec(widthSpec,
                    parent.getPaddingLeft() + parent.getPaddingRight(),
                    view.getLayoutParams().width);
            int childHeight = ViewGroup.getChildMeasureSpec(heightSpec,
                    parent.getPaddingTop() + parent.getPaddingBottom(),
                    view.getLayoutParams().height);

            view.measure(childWidth, childHeight);
            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
        }
    }
}
