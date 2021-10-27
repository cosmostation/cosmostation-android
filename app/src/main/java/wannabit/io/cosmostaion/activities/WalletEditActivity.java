package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.RecyclerView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;

public class WalletEditActivity extends BaseActivity implements View.OnClickListener{

    private Toolbar         mToolbar;
    private TextView        mBtnDone;
    private RecyclerView    mDisplayRecyclerView;
    private RecyclerView    mHideRecyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wallet_edit);
        mToolbar                = findViewById(R.id.tool_bar);
        mBtnDone                = findViewById(R.id.btn_done);
        mDisplayRecyclerView    = findViewById(R.id.display_recycler);
        mHideRecyclerView       = findViewById(R.id.hide_recycler);

        mBtnDone.setOnClickListener(this);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
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

    }

}
