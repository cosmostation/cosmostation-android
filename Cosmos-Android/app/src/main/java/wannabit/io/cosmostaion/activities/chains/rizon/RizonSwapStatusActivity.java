package wannabit.io.cosmostaion.activities.chains.rizon;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.VoteListActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseBroadCastActivity;
import wannabit.io.cosmostaion.model.RizonSwapStatus;
import wannabit.io.cosmostaion.task.FetchTask.RizonSwapStatusTask;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.widget.BaseHolder;
import wannabit.io.cosmostaion.widget.RizonSwapStatusHolder;

public class RizonSwapStatusActivity extends BaseBroadCastActivity implements View.OnClickListener{

    private Toolbar                             mToolbar;
    private SwipeRefreshLayout                  mSwipeRefreshLayout;
    private RecyclerView                        mRecyclerView;
    private RelativeLayout                      mLoadingLayer;
    private Button                              mBtnDone;

    private EventHorizonStatusAdapter           mEventHorizonStatusAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rizon_swap_status);
        mToolbar                = findViewById(R.id.tool_bar);
        mSwipeRefreshLayout     = findViewById(R.id.layer_refresher);
        mRecyclerView           = findViewById(R.id.recycler);
        mLoadingLayer           = findViewById(R.id.loadingLayer);
        mBtnDone           = findViewById(R.id.btn_done);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());

        mBtnDone.setOnClickListener(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mSwipeRefreshLayout.setRefreshing(false);
            }
        });
        
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mEventHorizonStatusAdapter = new EventHorizonStatusAdapter();
        mRecyclerView.setAdapter(mEventHorizonStatusAdapter);
        mLoadingLayer.setVisibility(View.GONE);

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

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnDone)) {
            onBackPressed();
            return;
        }
    }

    private class EventHorizonStatusAdapter extends RecyclerView.Adapter<EventHorizonStatusAdapter.StatusHolder> {

        @NonNull
        @Override
        public EventHorizonStatusAdapter.StatusHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new EventHorizonStatusAdapter.StatusHolder(getLayoutInflater().inflate(R.layout.item_rizon_event_horzion_status, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull EventHorizonStatusAdapter.StatusHolder holder, int position) {
            final RizonSwapStatus rizonSwapStatus = getBaseDao().mRizonSwapStatus.get(position);
            holder.swap_result_status.setText(rizonSwapStatus.status.toUpperCase());

            holder.swap_result_time.setText(WDp.getDpTime(RizonSwapStatusActivity.this, rizonSwapStatus.hdacTx.time * 1000));
            holder.swap_result_id.setText(rizonSwapStatus.id);
            if (rizonSwapStatus.hdacTx.confirmations >= 1) {
                holder.swap_hdac_status.setText("Success");
                holder.swap_hdac_status_icon.setVisibility(View.GONE);
            } else {
                holder.swap_hdac_status.setText("Pending");
                holder.swap_hdac_status_icon.setVisibility(View.VISIBLE);
            }
            holder.swap_burn_from_address.setText(rizonSwapStatus.from);
            holder.swap_burn_tx_hash.setText(rizonSwapStatus.hdacTxId);
            holder.swap_burn_amount.setText("" + rizonSwapStatus.amount);

            if (rizonSwapStatus.rizonTx != null) {
                holder.swap_rizon_status.setText("Success");
                holder.swap_rizon_status_icon.setVisibility(View.GONE);
                holder.swap_rizon_status_tx_hash.setText(rizonSwapStatus.rizonTxId);
                holder.swap_rizon_status_mint_amount.setText("" + WDp.getDpAmount2(RizonSwapStatusActivity.this, new BigDecimal(rizonSwapStatus.amount), 0, 6 ));
            } else {
                holder.swap_rizon_status.setText("Pending");
                holder.swap_rizon_status_icon.setVisibility(View.VISIBLE);
                holder.swap_rizon_status_tx_hash.setText("--");
                holder.swap_rizon_status_mint_amount.setText("--");
            }
            holder.swap_rizon_to_Address.setText(mAccount.address);
        }

        @Override
        public int getItemCount() { return getBaseDao().mRizonSwapStatus.size(); }

        public class StatusHolder extends RecyclerView.ViewHolder {
            private CardView    card_status;
            private TextView    swap_result_status, swap_result_time, swap_result_id;
            private ImageView   swap_hdac_status_icon;
            private TextView    swap_hdac_status, swap_burn_from_address, swap_burn_tx_hash, swap_burn_amount;
            private ImageView   swap_rizon_status_icon;
            private TextView    swap_rizon_to_Address, swap_rizon_status, swap_rizon_status_tx_hash, swap_rizon_status_mint_amount;

            public StatusHolder(@NonNull View itemView) {
                super(itemView);
                card_status                     = itemView.findViewById(R.id.card_status_root);
                swap_result_status              = itemView.findViewById(R.id.tx_result_status);
                swap_result_time                = itemView.findViewById(R.id.tx_request_time);
                swap_result_id                  = itemView.findViewById(R.id.tx_request_id);
                swap_hdac_status_icon           = itemView.findViewById(R.id.hdac_swap_status_icon);
                swap_hdac_status                = itemView.findViewById(R.id.hdac_status);
                swap_burn_from_address          = itemView.findViewById(R.id.burn_from_address);
                swap_burn_tx_hash               = itemView.findViewById(R.id.burn_tx_hash);
                swap_burn_amount                = itemView.findViewById(R.id.burn_amount);
                swap_rizon_status_icon          = itemView.findViewById(R.id.rizon_swap_status_icon);
                swap_rizon_status               = itemView.findViewById(R.id.rizon_swap_status);
                swap_rizon_to_Address           = itemView.findViewById(R.id.rizon_to_address);
                swap_rizon_status_tx_hash       = itemView.findViewById(R.id.rizon_tx_hash);
                swap_rizon_status_mint_amount   = itemView.findViewById(R.id.rizon_mint_amount);
            }
        }
    }
}
