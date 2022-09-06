package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.dialog.CommonAlertDialog;
import wannabit.io.cosmostaion.utils.WalletConnectManager;

public class ManageWalletConnectActivity extends BaseActivity {
    private WalletConnectManageAdapter adapter;
    private List<String> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_connect_management);
        Toolbar mToolbar = findViewById(R.id.tool_bar);
        RecyclerView recyclerView = findViewById(R.id.recycler_view);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        items = WalletConnectManager.getWhiteList(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        adapter = new WalletConnectManageAdapter();
        recyclerView.setAdapter(adapter);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private class WalletConnectManageAdapter extends RecyclerView.Adapter<WalletConnectManageAdapter.WalletConnectViewHolder> {

        @NonNull
        @Override
        public WalletConnectViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new WalletConnectViewHolder(getLayoutInflater().inflate(R.layout.item_wallet_connect, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull WalletConnectViewHolder viewHolder, int position) {
            final String item = items.get(position);
            viewHolder.urlText.setText(item);
            viewHolder.deleteImage.setOnClickListener(view -> {
                CommonAlertDialog.showDoubleButton(ManageWalletConnectActivity.this, getString(R.string.str_wallet_connect), getString(R.string.str_disconnect), getString(R.string.str_confirm), view1 -> {
                    WalletConnectManager.removeWhiteList(ManageWalletConnectActivity.this, item);
                    items.remove(item);
                    adapter.notifyDataSetChanged();
                }, getString(R.string.str_cancel), null);
            });
        }

        @Override
        public int getItemCount() {
            return items.size();
        }

        public class WalletConnectViewHolder extends RecyclerView.ViewHolder {
            TextView urlText;
            ImageView deleteImage;

            public WalletConnectViewHolder(@NonNull View itemView) {
                super(itemView);
                urlText = itemView.findViewById(R.id.url);
                deleteImage = itemView.findViewById(R.id.delete);
            }
        }
    }
}