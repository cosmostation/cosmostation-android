package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;

public class GuideDetailActivity extends BaseActivity {

    private Toolbar     mToolbar;
    private TextView    mTitle, mDetail;


    private int         mCategory, mContent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_detail);
        mToolbar        = findViewById(R.id.tool_bar);
        mTitle          = findViewById(R.id.guide_title);
        mDetail         = findViewById(R.id.guide_detail);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mCategory = getIntent().getIntExtra("category", 0);
        mContent = getIntent().getIntExtra("content", 0);
        if(mCategory == 0) {
            mTitle.setText(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_title_cosmos))).get(mContent));
            mDetail.setText(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_detail_cosmos))).get(mContent));

        } else if(mCategory == 1) {
            mTitle.setText(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_title_wallet))).get(mContent));
            mDetail.setText(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_detail_wallet))).get(mContent));

        } else if(mCategory == 2) {
            mTitle.setText(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_title_reward))).get(mContent));
            mDetail.setText(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_detail_reward))).get(mContent));

        } else if(mCategory == 3) {
            mTitle.setText(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_title_transaction))).get(mContent));
            mDetail.setText(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_detail_transaction))).get(mContent));

        } else if(mCategory == 4) {
            mTitle.setText(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_title_vote))).get(mContent));
            mDetail.setText(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_detail_vote))).get(mContent));

        } else if(mCategory == 5) {
            mTitle.setText(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_title_general))).get(mContent));
            mDetail.setText(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_detail_general))).get(mContent));

        } else if(mCategory == 6) {
            mTitle.setText(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_title_trouble))).get(mContent));
            mDetail.setText(new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_detail_trouble))).get(mContent));

        }


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

}
