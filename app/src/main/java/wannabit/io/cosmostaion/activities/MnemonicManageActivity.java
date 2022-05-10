package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;

public class MnemonicManageActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private Button mBtnCreateMnemonic;

    private MnemonicListAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mnemonic_manage);
        mToolbar = findViewById(R.id.tool_bar);
        mRecyclerView = findViewById(R.id.recycler);
        mBtnCreateMnemonic = findViewById(R.id.btn_create_mnemonic);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MnemonicListAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mBtnCreateMnemonic.setOnClickListener(this);

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
        if (v.equals(mBtnCreateMnemonic)) {

        }
    }

    private class MnemonicListAdapter extends RecyclerView.Adapter<MnemonicListAdapter.ListHolder> {

        @NonNull
        @Override
        public ListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            View rootView = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_mnemonic_list, viewGroup, false);
            return new ListHolder(rootView);
        }

        @Override
        public void onBindViewHolder(@NonNull ListHolder viewHolder, int position) {
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        public class ListHolder extends RecyclerView.ViewHolder {
            private CardView itemRoot;
            private TextView itemMnemonicName, itemDerivedCnt, itemWordsCnt, itemImportedDate;

            public ListHolder(View v) {
                super(v);
                itemRoot = itemView.findViewById(R.id.card_root);
                itemMnemonicName = itemView.findViewById(R.id.mnemonic_nickname);
                itemDerivedCnt = itemView.findViewById(R.id.derived_cnt);
                itemWordsCnt = itemView.findViewById(R.id.words_cnt);
                itemImportedDate = itemView.findViewById(R.id.imported_date);
            }
        }
    }
}
