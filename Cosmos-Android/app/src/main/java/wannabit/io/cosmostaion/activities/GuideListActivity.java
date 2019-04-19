package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.utils.WLog;

public class GuideListActivity extends BaseActivity {

    private Toolbar                 mToolbar;
    private RecyclerView            mRecyclerView;

    private GuideListAdapter        mGuideListAdapter;
    private ArrayList<GuideItem>    mGuideItems = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide_list);
        mToolbar            = findViewById(R.id.tool_bar);
        mRecyclerView       = findViewById(R.id.recycler);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);

        initGuideContent();
        mGuideListAdapter = new GuideListAdapter();
        mRecyclerView.setAdapter(mGuideListAdapter);

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



    private void initGuideContent() {
        mGuideItems.clear();
        ArrayList<String> categories = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_category)));
        for(int i = 0; i < categories.size(); i ++) {
            GuideItem temp = new GuideItem();
            temp.category = categories.get(i);
            if(i == 0) {
                temp.titles = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_title_cosmos)));

            } else if (i == 1) {
                temp.titles = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_title_wallet)));

            } else if (i == 2) {
                temp.titles = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_title_reward)));

            } else if (i == 3) {
                temp.titles = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_title_transaction)));

            } else if (i == 4) {
                temp.titles = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_title_vote)));

            } else if (i == 5) {
                temp.titles = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_title_general)));

            } else if (i == 6) {
                temp.titles = new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.guide_title_trouble)));

            }
            mGuideItems.add(temp);
        }

    }


    private class GuideListAdapter extends RecyclerView.Adapter<GuideListAdapter.GuideListHolder> {


        @NonNull
        @Override
        public GuideListHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new GuideListHolder(getLayoutInflater().inflate(R.layout.item_guide_category, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull GuideListHolder holder, final int position) {
            final GuideItem guideItem = mGuideItems.get(position);
            holder.guide_category.setText(guideItem.category);

            for(int i = 0; i < 10 ; i++ ) {
                holder.guide_title_layer[i].setVisibility(View.GONE);
            }

            for(int i = 0; i < guideItem.titles.size() ; i++ ) {
                final int innerPosition = i;
                holder.guide_title[i].setText(guideItem.titles.get(i));
                holder.guide_title_layer[i].setVisibility(View.VISIBLE);
                holder.guide_title_layer[i].setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        WLog.w("Click " + position + "  " + innerPosition);
                        Intent intent = new Intent(GuideListActivity.this, GuideDetailActivity.class);
                        intent.putExtra("category", position);
                        intent.putExtra("content", innerPosition);
                        startActivity(intent);

                    }
                });
            }

            boolean expanded = guideItem.expanded;
            holder.guide_category_details.setVisibility(expanded ? View.VISIBLE : View.GONE);
            holder.guide_category_arrow.setImageDrawable(expanded ? getResources().getDrawable(R.drawable.arrow_up_gr) : getResources().getDrawable(R.drawable.arrow_down_gr));


            holder.guide_category_Layer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    boolean expanded = guideItem.expanded;
                    guideItem.expanded = !expanded;
                    notifyItemChanged(position);
                }
            });


        }

        @Override
        public int getItemCount() {
            return mGuideItems.size();
        }

        public class GuideListHolder extends RecyclerView.ViewHolder {
            FrameLayout     guide_category_Layer;
            TextView        guide_category;
            ImageView       guide_category_arrow;
            LinearLayout    guide_category_details;
            View            guide_center_separator;
            FrameLayout[]   guide_title_layer   = new FrameLayout[10];
            TextView[]      guide_title         = new TextView[10];

            public GuideListHolder(@NonNull View itemView) {
                super(itemView);
                guide_category_Layer        = itemView.findViewById(R.id.guide_category_Layer);
                guide_category              = itemView.findViewById(R.id.guide_category);
                guide_category_arrow        = itemView.findViewById(R.id.guide_category_arrow);
                guide_category_details      = itemView.findViewById(R.id.guide_category_details);
                guide_center_separator      = itemView.findViewById(R.id.guide_center_separator);

                for(int i = 0; i < guide_title_layer.length; i++) {
                    guide_title_layer[i]    = itemView.findViewById(getResources().getIdentifier("guide_title_layer" + i , "id", getPackageName()));
                    guide_title[i]          = itemView.findViewById(getResources().getIdentifier("guide_title" + i , "id", getPackageName()));
                }
            }
        }
    }

    public class GuideItem {

        public String category;
        public ArrayList<String> titles;
        public boolean expanded = false;

        public GuideItem() {
        }
    }

}
