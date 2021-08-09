package wannabit.io.cosmostaion.activities.chains.rizon;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.RizonSwapStatusHolder;

public class RizonSwapStatusActivity extends BaseBroadCastActivity {

    private Toolbar                             mToolbar;
    private SwipeRefreshLayout                  mSwipeRefreshLayout;
    private RecyclerView                        mRecyclerView;
    private RelativeLayout                      mLoadingLayer;

    private EventHorizonStatusAdapter           mEventHorizonStatusAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rizon_swap_status);
        mToolbar                = findViewById(R.id.tool_bar);
        mSwipeRefreshLayout     = findViewById(R.id.layer_refresher);
        mRecyclerView           = findViewById(R.id.recycler);
        mLoadingLayer           = findViewById(R.id.loadingLayer);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
            }
        });

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mEventHorizonStatusAdapter = new EventHorizonStatusAdapter();
        mRecyclerView.setAdapter(mEventHorizonStatusAdapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private class EventHorizonStatusAdapter extends RecyclerView.Adapter<BaseHolder> {
//        private static final int TYPE_MY_POOL            = 1;
//        private static final int TYPE_OTHER_POOL         = 2;

        @NonNull
        @Override
        public BaseHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new RizonSwapStatusHolder(getLayoutInflater().inflate(R.layout.item_rizon_event_horzion_status, viewGroup, false));
//            if (viewType == TYPE_MY_POOL) {
//                return new PoolMyHolder(getLayoutInflater().inflate(R.layout.item_pool_list_my, viewGroup, false));
//            } else if (viewType == TYPE_OTHER_POOL) {
//                return new PoolOtherHolder(getLayoutInflater().inflate(R.layout.item_pool_list_all, viewGroup, false));
//            }
        }

        @Override
        public void onBindViewHolder(@NonNull BaseHolder viewHolder, int position) {
//            if (getItemViewType(position) == TYPE_MY_POOL) {
//                final PoolOuterClass.Pool myPool = mPoolMyList.get(position);
//                viewHolder.onBindMyPool(getContext(), getSActivity(), getBaseDao(), myPool);
//            }
//            else if (getItemViewType(position) == TYPE_OTHER_POOL) {
//                final PoolOuterClass.Pool otherPool = mPoolOtherList.get(position - mPoolMyList.size());
//                viewHolder.onBindOtherPool(getContext(), getSActivity(), getBaseDao(), otherPool);
//            }
        }

        @Override
        public int getItemCount() {
            return 1;
        }
    }
}
