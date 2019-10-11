package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;

public class VoteDetailActivity extends BaseActivity {

    private ImageView mChainBg;
    private Toolbar mToolbar;
    private Button mVoteBtn;
    private ImageView mVoteStatusImg;
    private TextView mVoteStatusTxt;
    private ImageView mVoteWebBtn;
    private TextView mVoteTitle, mVoteProposer, mVoteStartTime, mVoteFinishTime, mVoteMsg;
    private ImageView mMsgExpendBtn;
    private TextView mVoteQuorum;
    private ImageView mYesDone, mNoDone, mVetoDone, mAbstainDone;
    private ProgressBar mYesProgress, mNoProgress, mVetoProgress, mAbstainProgress;
    private TextView mYesRate, mYesCnt, mNoRate, mNoCnt, mVetoRate, mVetoCnt, mAbstainRate, mAbstainCnt;
    private ImageView mYesCntImg, mNoCntImg, mVetoCntImg, mAbstainCntImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vote_detail);
        mToolbar = findViewById(R.id.tool_bar);
        mVoteBtn = findViewById(R.id.btn_action);
        mVoteStatusImg = findViewById(R.id.vote_status_img);
        mVoteStatusTxt = findViewById(R.id.vote_status);
        mVoteWebBtn = findViewById(R.id.vote_detail);
        mVoteTitle = findViewById(R.id.vote_title);
        mVoteProposer = findViewById(R.id.vote_proposer);
        mVoteStartTime = findViewById(R.id.vote_startTime);
        mVoteFinishTime = findViewById(R.id.vote_endTime);
        mVoteMsg = findViewById(R.id.vote_msg);
        mMsgExpendBtn = findViewById(R.id.vote_btn_expend);

        mVoteQuorum = findViewById(R.id.vote_quorum);
        mYesDone = findViewById(R.id.vote_yes_voted);
        mNoDone = findViewById(R.id.vote_no_voted);
        mVetoDone = findViewById(R.id.vote_veto_voted);
        mAbstainDone = findViewById(R.id.vote_abstain_voted);
        mYesProgress = findViewById(R.id.vote_yes_progress);
        mNoProgress = findViewById(R.id.vote_no_progress);
        mVetoProgress = findViewById(R.id.vote_veto_progress);
        mAbstainProgress = findViewById(R.id.vote_abstain_progress);
        mYesRate = findViewById(R.id.vote_yes_rate);
        mYesCnt = findViewById(R.id.vote_yes_cnt);
        mNoRate = findViewById(R.id.vote_no_rate);
        mNoCnt = findViewById(R.id.vote_no_cnt);
        mVetoRate = findViewById(R.id.vote_vetos_rate);
        mVetoCnt = findViewById(R.id.vote_veto_cnt);
        mAbstainRate = findViewById(R.id.vote_abstain_rate);
        mAbstainCnt = findViewById(R.id.vote_abstain_cnt);
        mYesCntImg = findViewById(R.id.vote_yes_cnt_img);
        mNoCntImg = findViewById(R.id.vote_no_cnt_img);
        mVetoCntImg = findViewById(R.id.vote_veto_cnt_img);
        mAbstainCntImg = findViewById(R.id.vote_abstain_cnt_img);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

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
