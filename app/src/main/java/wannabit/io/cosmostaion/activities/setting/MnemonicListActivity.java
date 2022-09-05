package wannabit.io.cosmostaion.activities.setting;

import android.content.Intent;
import android.os.Bundle;
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

import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.MWords;

public class MnemonicListActivity extends BaseActivity implements View.OnClickListener {

    private Toolbar mToolbar;
    private RecyclerView mRecyclerView;
    private Button mBtnImportMnemonic, mBtnCreateMnemonic;

    private MnemonicListAdapter mAdapter;

    private ArrayList<MWords> mMyMnemonics = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mnemonic_list);
        mToolbar = findViewById(R.id.tool_bar);
        mRecyclerView = findViewById(R.id.recycler);
        mBtnImportMnemonic = findViewById(R.id.btn_import_mnemonic);
        mBtnCreateMnemonic = findViewById(R.id.btn_create_mnemonic);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new MnemonicListAdapter();
        mRecyclerView.setAdapter(mAdapter);

        mBtnImportMnemonic.setOnClickListener(this);
        mBtnCreateMnemonic.setOnClickListener(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        mMyMnemonics = getBaseDao().onSelectAllMnemonics();
        mAdapter.notifyDataSetChanged();
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
        if (v.equals(mBtnImportMnemonic)) {
            startActivity(new Intent(MnemonicListActivity.this, MnemonicRestoreActivity.class));

        } else if (v.equals(mBtnCreateMnemonic)) {
            startActivity(new Intent(MnemonicListActivity.this, MnemonicCreateActivity.class));
        }
    }

    private class MnemonicListAdapter extends RecyclerView.Adapter<MnemonicListAdapter.ListHolder> {

        @NonNull
        @Override
        public ListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ListHolder(getLayoutInflater().inflate(R.layout.item_mnemonic_list, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ListHolder holder, int position) {
            MWords mWord = mMyMnemonics.get(position);
            holder.itemMnemonicName.setText(mWord.getName());
            holder.itemDerivedCnt.setText("" + mWord.getLinkedWalletCnt(getBaseDao()));
            holder.itemWordsCnt.setText("" + mWord.wordsCnt);
            holder.itemImportedDate.setText(mWord.getImportDate(MnemonicListActivity.this));

            holder.itemRoot.setOnClickListener(view -> {
                if (getBaseDao().isAutoPass()) {
                    Intent checkintent = new Intent(MnemonicListActivity.this, MnemonicDetailActivity.class);
                    checkintent.putExtra("mnemonicId", mWord.id);
                    startActivity(checkintent);

                } else {
                    Intent intent = new Intent(MnemonicListActivity.this, PasswordCheckActivity.class);
                    intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_CHECK_MNEMONIC);
                    intent.putExtra("checkid", mWord.id);
                    startActivity(intent);
                    overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);
                }
            });
        }

        @Override
        public int getItemCount() {
            return mMyMnemonics.size();
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
