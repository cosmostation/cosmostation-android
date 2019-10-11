package wannabit.io.cosmostaion.activities;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import java.math.BigDecimal;
import java.util.ArrayList;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.model.type.Proposal;
import wannabit.io.cosmostaion.model.type.Validator;
import wannabit.io.cosmostaion.network.res.ResLcdProposalTally;
import wannabit.io.cosmostaion.network.res.ResLcdProposalVoted;
import wannabit.io.cosmostaion.task.FetchTask.ProposalDetailTask;
import wannabit.io.cosmostaion.task.FetchTask.ProposalProposerTask;
import wannabit.io.cosmostaion.task.FetchTask.ProposalTallyTask;
import wannabit.io.cosmostaion.task.FetchTask.ProposalVotedListTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WKey;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

public class VoteDetailActivity extends BaseActivity implements TaskListener, View.OnClickListener {

    private ImageView mChainBg;
    private Toolbar mToolbar;
    private Button mVoteBtn;
    private ImageView mVoteStatusImg;
    private TextView mVoteStatusTxt;
    private ImageView mVoteWebBtn;
    private TextView mVoteTitle, mVoteProposer, mVoteStartTime, mVoteFinishTime, mVoteMsg;
    private ImageView mMsgExpendBtn;
    private View mDivider;
    private LinearLayout mTurnoutLayer, mQuorumLayer;
    private TextView mVoteTurnout, mVoteQuorum;
    private ImageView mYesDone, mNoDone, mVetoDone, mAbstainDone;
    private ProgressBar mYesProgress, mNoProgress, mVetoProgress, mAbstainProgress;
    private TextView mYesRate, mYesCnt, mNoRate, mNoCnt, mVetoRate, mVetoCnt, mAbstainRate, mAbstainCnt;
    private ImageView mYesCntImg, mNoCntImg, mVetoCntImg, mAbstainCntImg;

    private String  mProposalId;
    private BigDecimal mBondedToken;

    private Proposal mProposal;
    private String mProposer;
    private ResLcdProposalTally mTally;
    private ArrayList<ResLcdProposalVoted> mVotes = new ArrayList<>();

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

        mTurnoutLayer = findViewById(R.id.vote_turnout_layer);
        mVoteTurnout = findViewById(R.id.vote_turnout);
        mDivider = findViewById(R.id.vote_quorum_divider);
        mQuorumLayer = findViewById(R.id.vote_quorum_layer);
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
        mProposalId = getIntent().getStringExtra("proposalId");
        mTopValidators = getIntent().getParcelableArrayListExtra("topValidators");
        mBondedToken = new BigDecimal(getIntent().getStringExtra("bondedToken"));
        WLog.w("mTopValidators " + mTopValidators.size());
        WLog.w("mBondedToken " + mBondedToken.toPlainString());

        mVoteWebBtn.setOnClickListener(this);
        mMsgExpendBtn.setOnClickListener(this);
        onFetchData();
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
        WLog.w("onUpdateView");
        onHideWaitDialog();
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            if (mProposal.proposal_status.equals("DepositPeriod")) {
                mVoteStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_deposit_img));
            } else if (mProposal.proposal_status.equals("VotingPeriod")) {
                mVoteStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_voting_img));
            } else if (mProposal.proposal_status.equals("Rejected")) {
                mVoteStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_rejected_img));
            } else if (mProposal.proposal_status.equals("Passed")) {
                mVoteStatusImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_passed_img));
            } else {
                mVoteStatusImg.setVisibility(View.GONE);
            }
            mVoteStatusTxt.setText(mProposal.proposal_status);
            mVoteTitle.setText("# " + mProposal.id + ".  " + mProposal.content.value.title);
            for (Validator v: mTopValidators) {
                if (WKey.convertDpAddressToDpOpAddress(mProposer).equals(v.operator_address)) {
                    mProposer = v.description.moniker;
                    break;
                }
            }
            mVoteProposer.setText(mProposer);
            if (mProposal.proposal_status.equals("DepositPeriod")) {
                mVoteStartTime.setText(R.string.str_vote_wait_deposit);
                mVoteFinishTime.setText(R.string.str_vote_wait_deposit);
            } else {
                mVoteStartTime.setText(WDp.getTimeformat(getBaseContext(), mProposal.voting_start_time));
                mVoteFinishTime.setText(WDp.getTimeformat(getBaseContext(), mProposal.voting_end_time));
            }
            mVoteMsg.setText(mProposal.content.value.description);

            mVoteQuorum.setText(WDp.getDpString("40.00%", 3));
            if (mProposal.proposal_status.equals("VotingPeriod")) {

            } else {
//                mDivider.setVisibility(View.GONE);
//                mTurnoutLayer.setVisibility(View.GONE);
            }
            WLog.w(""+mTally.getYesPer().toPlainString());
            mYesProgress.setProgress(mTally.getYesPer().intValue());
            mYesRate.setText(WDp.getDpString(mTally.getYesPer().toPlainString() + "%", 3));
            mYesCnt.setText(""+WUtil.getVoterType(mVotes, "Yes"));

            mNoProgress.setProgress(mTally.getNoPer().intValue());
            mNoRate.setText(WDp.getDpString(mTally.getNoPer().toPlainString() + "%", 3));
            mNoCnt.setText(""+WUtil.getVoterType(mVotes, "No"));

            mVetoProgress.setProgress(mTally.getVetoPer().intValue());
            mVetoRate.setText(WDp.getDpString(mTally.getVetoPer().toPlainString() + "%", 3));
            mVetoCnt.setText(""+WUtil.getVoterType(mVotes, "NoWithVeto"));

            mAbstainProgress.setProgress(mTally.getAbstainPer().intValue());
            mAbstainRate.setText(WDp.getDpString(mTally.getAbstainPer().toPlainString() + "%", 3));
            mAbstainCnt.setText(""+WUtil.getVoterType(mVotes, "Abstain"));


        } else {

        }

    }

    @Override
    public void onClick(View v) {
        if (v.equals(mVoteWebBtn)) {

        } else if (v.equals(mMsgExpendBtn)) {
            if (mVoteMsg.getMaxLines() == 50) {
                mVoteMsg.setMaxLines(3);
                mVoteMsg.setText(mProposal.content.value.description);
                mMsgExpendBtn.setImageDrawable(getDrawable(R.drawable.arrow_down_gr));

            } else {
                mVoteMsg.setMaxLines(50);
                mVoteMsg.setText(mProposal.content.value.description);
                mMsgExpendBtn.setImageDrawable(getDrawable(R.drawable.arrow_up_gr));
            }

        }

    }


    public void onFetchData() {
        onShowWaitDialog();
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            this.mTaskCount = 4;
            new ProposalDetailTask(getBaseApplication(), this, mProposalId, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ProposalVotedListTask(getBaseApplication(), this, mProposalId, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ProposalProposerTask(getBaseApplication(), this, mProposalId, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new ProposalTallyTask(getBaseApplication(), this, mProposalId, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {

        }

    }


    @Override
    public void onTaskResponse(TaskResult result) {
        mTaskCount--;
        if(isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_PROPOSAL_DETAIL) {
            mProposal = (Proposal)result.resultData;

        } else if (result.taskType == BaseConstant.TASK_FETCH_PROPOSAL_PROPOSER) {
            mProposer = (String)result.resultData;

        } else if (result.taskType == BaseConstant.TASK_FETCH_PROPOSAL_TALLY) {
            mTally = (ResLcdProposalTally)result.resultData;

        } else if (result.taskType == BaseConstant.TASK_FETCH_PROPOSAL_VOTED) {
            mVotes = (ArrayList<ResLcdProposalVoted>)result.resultData;
        }

        if (mTaskCount == 0) {
            onUpdateView();
        }
    }


}
