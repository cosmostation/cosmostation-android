package wannabit.io.cosmostaion.activities.chains.desmos;

import static wannabit.io.cosmostaion.base.BaseConstant.TASK_GRPC_FETCH_PROFILE_INFO;

import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.Toolbar;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import desmos.profiles.v1beta1.ModelsProfile;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.task.gRpcTask.ProfileInfoGrpcTask;
import wannabit.io.cosmostaion.utils.WDp;

public class ProfileDetailActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private Toolbar mToolbar;
    private ImageView mProfileCoverImg, mProfileImg;
    private TextView mProfileDtag;
    private RelativeLayout mBtnEdit;

    private RecyclerView mRecyclerView;
    private ProfileDetailAdapter mAdapter;

    private ModelsProfile.Profile mProfile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_detail);
        mToolbar = findViewById(R.id.toolbar);
        mProfileCoverImg = findViewById(R.id.profile_cover_img);
        mProfileImg = findViewById(R.id.profile_img);
        mProfileDtag = findViewById(R.id.profile_dtag);
        mRecyclerView = findViewById(R.id.recycler);
        mBtnEdit = findViewById(R.id.btn_edit);

        Window window = this.getWindow();
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(Color.TRANSPARENT);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        account = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        baseChain = BaseChain.getChain(account.baseChain);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mAdapter = new ProfileDetailAdapter();
        mRecyclerView.setAdapter(mAdapter);

        onFetchProfile();
        mBtnEdit.setOnClickListener(this);
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

    private void onUpdateView() {
        Glide.with(this).load(mProfile.getPictures().getCover()).diskCacheStrategy(DiskCacheStrategy.ALL).
                placeholder(R.drawable.img_none).error(R.drawable.img_none).into(mProfileCoverImg);
        Glide.with(this).load(mProfile.getPictures().getProfile()).diskCacheStrategy(DiskCacheStrategy.ALL).
                placeholder(R.drawable.profileimg_none).error(R.drawable.profileimg_none).into(mProfileImg);
        mProfileDtag.setText(mProfile.getDtag());
    }


    private void onFetchProfile() {
        if (account == null) return;
        mProfile = null;
        new ProfileInfoGrpcTask(getBaseApplication(), this, baseChain, account.address).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if (isFinishing()) return;
        if (result.taskType == TASK_GRPC_FETCH_PROFILE_INFO) {
            if (result.isSuccess && result.resultData != null) {
                mProfile = (ModelsProfile.Profile) result.resultData;
                onUpdateView();
                mAdapter.notifyDataSetChanged();
            }
        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnEdit)) {
            Toast.makeText(this, R.string.str_preparing, Toast.LENGTH_SHORT).show();
            return;
        }
    }

    private class ProfileDetailAdapter extends RecyclerView.Adapter<ProfileDetailAdapter.ProfileHolder> {

        @NonNull
        @Override
        public ProfileHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
            return new ProfileDetailAdapter.ProfileHolder(getLayoutInflater().inflate(R.layout.item_profile_detail_info, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull ProfileHolder profileHolder, int position) {
            profileHolder.card_root.setCardBackgroundColor(WDp.getChainBgColor(ProfileDetailActivity.this, baseChain));
            if (mProfile != null) {
                profileHolder.profile_nickname.setText(mProfile.getNickname());
                profileHolder.profile_bio.setText(mProfile.getBio());
            }
        }

        @Override
        public int getItemCount() {
            return 1;
        }

        public class ProfileHolder extends RecyclerView.ViewHolder {
            private final CardView card_root;
            private final TextView profile_nickname;
            private final TextView profile_bio;

            public ProfileHolder(@NonNull View itemView) {
                super(itemView);
                card_root = itemView.findViewById(R.id.card_root);
                profile_nickname = itemView.findViewById(R.id.profile_nickname);
                profile_bio = itemView.findViewById(R.id.profile_bio);
            }
        }
    }

}
