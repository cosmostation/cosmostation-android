package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.utils.WDp;

public class WalletEditActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private TextView mBtnDone;
    private RecyclerView mDisplayRecyclerView;
    private RecyclerView mHideRecyclerView;
    private LinearLayout mEmptyChains;

    private DisplayListAdapter mDisplayListAdapter;
    private HideListAdapter mHideListAdapter;
    private ItemTouchHelper mItemTouchHelper;

    private ArrayList<BaseChain> mAllChains = new ArrayList<>();
    private ArrayList<BaseChain> mDisplayChains = new ArrayList<>();
    private ArrayList<BaseChain> mHideChains = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_edit);
        mToolbar = findViewById(R.id.tool_bar);
        mBtnDone = findViewById(R.id.btn_done);
        mDisplayRecyclerView = findViewById(R.id.display_recycler);
        mHideRecyclerView = findViewById(R.id.hide_recycler);
        mEmptyChains = findViewById(R.id.empty_chains);

        mBtnDone.setOnClickListener(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mDisplayRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mDisplayRecyclerView.setHasFixedSize(true);
        mDisplayListAdapter = new DisplayListAdapter();
        mDisplayRecyclerView.setAdapter(mDisplayListAdapter);

        mItemTouchHelper = new ItemTouchHelper(new ItemTouchHelperCallback(mDisplayListAdapter));
        mItemTouchHelper.attachToRecyclerView(mDisplayRecyclerView);

        mHideRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mHideRecyclerView.setHasFixedSize(true);
        mHideListAdapter = new HideListAdapter();
        mHideRecyclerView.setAdapter(mHideListAdapter);

        for (BaseChain baseChain : BaseChain.values()) {
            if (baseChain.isSupported() && !baseChain.equals(BaseChain.COSMOS_MAIN)) {
                mAllChains.add(baseChain);
            }
        }
        mDisplayChains = getBaseDao().userSortedChains();
        mHideChains = getBaseDao().userHideChains();

        if (mHideChains.size() <= 0) {
            mEmptyChains.setVisibility(View.VISIBLE);
            mHideRecyclerView.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onSaveUserChains();
                onBackPressed();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void onSaveUserChains() {
        getBaseDao().setUserHidenChains(mHideChains);
        getBaseDao().setUserSortedChains(mDisplayChains);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnDone)) {
            onSaveUserChains();
            finish();
        }
    }

    private class DisplayListAdapter extends RecyclerView.Adapter<DisplayListAdapter.DisplayHolder> implements ItemTouchHelperListener {

        @NonNull
        @Override
        public DisplayListAdapter.DisplayHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new DisplayListAdapter.DisplayHolder(getLayoutInflater().inflate(R.layout.item_edit_display, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull DisplayListAdapter.DisplayHolder holder, final int position) {
            final BaseChain chain = mDisplayChains.get(position);
            holder.chainCard.setCardBackgroundColor(WDp.getChainBgColor(WalletEditActivity.this, chain));
            WDp.getChainImg(WalletEditActivity.this, chain, holder.chainTokenImg);
            WDp.getChainTitle2(WalletEditActivity.this, chain, holder.chainName);

            holder.chainRemoveImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (getBaseDao().onSelectAccountsByChain(BaseChain.COSMOS_MAIN).size() <= 0) {
                        int dpAccountSum = 0;
                        for (BaseChain baseChain : mDisplayChains) {
                            if (!baseChain.equals(chain)) {
                                dpAccountSum = dpAccountSum + getBaseDao().onSelectAccountsByChain(baseChain).size();
                            }
                        }
                        if (dpAccountSum <= 0) {
                            Toast.makeText(WalletEditActivity.this, getString(R.string.error_reserve_1_account), Toast.LENGTH_SHORT).show();
                            return;
                        }
                    }
                    int displayChainIndex = mDisplayChains.indexOf(chain);
                    if (displayChainIndex >= 0) {
                        mDisplayChains.remove(displayChainIndex);
                        mHideChains.add(chain);
                        ArrayList<BaseChain> tempHide = new ArrayList<>();
                        for (BaseChain baseChain : mAllChains) {
                            if (mHideChains.contains(baseChain)) {
                                tempHide.add(baseChain);
                            }
                        }
                        mHideChains = tempHide;

                        if (mHideChains.size() > 0) {
                            mEmptyChains.setVisibility(View.GONE);
                            mHideRecyclerView.setVisibility(View.VISIBLE);
                        }
                        mDisplayListAdapter.notifyDataSetChanged();
                        mHideListAdapter.notifyDataSetChanged();
                    }
                }
            });
            holder.chainSort.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                        if (mItemTouchHelper != null)
                            mItemTouchHelper.startDrag(holder);
                    }
                    return false;
                }
            });
        }

        @Override
        public int getItemCount() {
            return mDisplayChains.size();
        }

        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            BaseChain fromItem = mDisplayChains.get(fromPosition);
            mDisplayChains.remove(fromPosition);
            mDisplayChains.add(toPosition, fromItem);
            notifyItemMoved(fromPosition, toPosition);
            return true;
        }

        public class DisplayHolder extends RecyclerView.ViewHolder {
            CardView chainCard;
            ImageView chainRemoveImg, chainTokenImg, chainSort;
            TextView chainName;

            public DisplayHolder(@NonNull View itemView) {
                super(itemView);
                chainCard = itemView.findViewById(R.id.chain_card);
                chainRemoveImg = itemView.findViewById(R.id.chain_remove_img);
                chainSort = itemView.findViewById(R.id.chainSort);
                chainTokenImg = itemView.findViewById(R.id.chain_img);
                chainName = itemView.findViewById(R.id.chain_name);
            }
        }
    }


    private class HideListAdapter extends RecyclerView.Adapter<HideListAdapter.HideHolder> {

        @NonNull
        @Override
        public HideListAdapter.HideHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new HideListAdapter.HideHolder(getLayoutInflater().inflate(R.layout.item_edit_hide, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull HideListAdapter.HideHolder holder, int position) {
            final BaseChain chain = mHideChains.get(position);
            holder.chainCard.setCardBackgroundColor(WDp.getChainBgColor(WalletEditActivity.this, chain));
            WDp.getChainImg(WalletEditActivity.this, chain, holder.chainTokenImg);
            WDp.getChainTitle2(WalletEditActivity.this, chain, holder.chainName);

            holder.chainAddImg.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int hideChainIndex = mHideChains.indexOf(chain);
                    if (hideChainIndex >= 0) {
                        mHideChains.remove(hideChainIndex);
                        mDisplayChains.add(chain);

                        if (mHideChains.size() <= 0) {
                            mEmptyChains.setVisibility(View.VISIBLE);
                            mHideRecyclerView.setVisibility(View.GONE);
                        }
                        mHideListAdapter.notifyDataSetChanged();
                        mDisplayListAdapter.notifyDataSetChanged();
                    }
                }
            });
        }

        @Override
        public int getItemCount() {
            return mHideChains.size();
        }

        public class HideHolder extends RecyclerView.ViewHolder {
            CardView chainCard;
            ImageView chainAddImg, chainTokenImg;
            TextView chainName;

            public HideHolder(@NonNull View itemView) {
                super(itemView);
                chainCard = itemView.findViewById(R.id.chain_card);
                chainAddImg = itemView.findViewById(R.id.chain_add_img);
                chainTokenImg = itemView.findViewById(R.id.chain_img);
                chainName = itemView.findViewById(R.id.chain_name);
            }
        }
    }

    public class ItemTouchHelperCallback extends ItemTouchHelper.Callback {
        ItemTouchHelperListener listener;

        public ItemTouchHelperCallback(ItemTouchHelperListener listener) {
            this.listener = listener;
        }

        @Override
        public int getMovementFlags(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder) {
            int dragFlags = ItemTouchHelper.UP | ItemTouchHelper.DOWN;
            int swipeFlags = ItemTouchHelper.START | ItemTouchHelper.END;

            return makeMovementFlags(dragFlags, swipeFlags);
        }

        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder source, @NonNull RecyclerView.ViewHolder target) {
            return listener.onItemMove(source.getAdapterPosition(), target.getAdapterPosition());
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        }

        @Override
        public boolean isItemViewSwipeEnabled() {
            return false;
        }
    }

    public interface ItemTouchHelperListener {
        boolean onItemMove(int fromPosition, int toPosition);
    }

}
