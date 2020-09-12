package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.HashMap;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.IrisToken;
import wannabit.io.cosmostaion.dao.OkToken;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.model.type.BnbHistory;
import wannabit.io.cosmostaion.network.req.ReqTxToken;
import wannabit.io.cosmostaion.network.res.ResApiTxList;
import wannabit.io.cosmostaion.network.res.ResBnbTic;
import wannabit.io.cosmostaion.network.res.ResHistory;
import wannabit.io.cosmostaion.task.FetchTask.ApiTokenTxsHistoryTask;
import wannabit.io.cosmostaion.task.FetchTask.HistoryTask;
import wannabit.io.cosmostaion.task.FetchTask.TokenHistoryTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.FEE_BNB_SEND;
import static wannabit.io.cosmostaion.base.BaseConstant.IS_TEST;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_BINANCE_TEST_BTC;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_TEST_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_HTLC_KAVA_TEST_BTC;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_IRIS_ATTO;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_MUON;
import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_OK_TEST;

public class TokenDetailActivity extends BaseActivity implements View.OnClickListener, TaskListener {

    private ImageView               mChainBg;
    private Toolbar                 mToolbar;

    private ImageView               mBtnWebDetail, mBtnAddressDetail;
    private ImageView               mKeyState;
    private TextView                mAddress;

    private CardView                mAtomCard, mIrisCard, mBnbCard, mKavaCard, mOkCard, TokenCard;

    private LinearLayout            mAtomAction, mAtomTransfer;
    private RelativeLayout          mBtnSendAtom, mBtnReceiveAtom, mBtnBuyAtom;
    private TextView                mTvAtomTotal, mTvAtomValue, mTvAtomAvailable, mTvAtomDelegated, mTvAtomUnBonding, mTvAtomRewards;

    private LinearLayout            mIrisAction, mIrisTransfer;
    private RelativeLayout          mBtnSendIris, mBtnReceiveIris;
    private TextView                mTvIrisTotal, mTvIrisValue, mTvIrisAvailable, mTvIrisDelegated, mTvIrisUnBonding, mTvIrisRewards;

    private LinearLayout            mBnbAction, mBnbTransfer;
    private RelativeLayout          mBtnSendBnb, mBtnReceiveBnb, mBtnInterChain, mBtnBuyBnb;
    private TextView                mTvBnbTotal, mTvBnbValue, mTvBnbBalance, mTvBnbLocked;

    private LinearLayout            mKavaAction, mKavaTransfer;
    private RelativeLayout          mBtnSendKava, mBtnReceiveKava, mBtnBuyKava;
    private TextView                mTvKavaTotal, mTvKavaValue, mTvKavaAvailable, mTvKavaVesting, mTvKavaDelegated, mTvKavaUnBonding, mTvKavaRewards;

    private LinearLayout            mOkAction;
    private RelativeLayout          mOkVote, mBtnOkSend, mBtnOkReceive;
    private LinearLayout            mOkTransfer;
    private TextView                mOkTotalAmount, mOkTotalValue, mOkAvailable, mOkLocked, mOkDeposit, mOkWithdrawing;

    private LinearLayout            mBtnTokenDetail;
    private RelativeLayout          mBtnSendToken, mBtnReceiveToken, mBtnBep3Send;
    private RelativeLayout          mTokenRewardLayer, mTokenLockedLayer;
    private ImageView               mTokenImg, mTokenLink;
    private TextView                mTvTokenSymbol, mTvTokenTotal, mTvTokenValue, mTvTokenDenom, mTvTokenAvailable, mTvTokenReward, mTvTokenLocked;

    private TextView                mHistoryCnt;
    private SwipeRefreshLayout      mSwipeRefreshLayout;
    private RecyclerView            mRecyclerView;
    private LinearLayout            mEmptyHistory;

    private Balance                         mBalance;
    private IrisToken                       mIrisToken;
    private BnbToken                        mBnbToken;
    private HashMap<String, ResBnbTic>      mBnbTics = new HashMap<>();

    private String                          mOkDenom;
    private OkToken                         mOkToken;

    private TokenHistoryAdapter             mTokenHistoryAdapter;

    private ArrayList<ResHistory.InnerHits> mHistory = new ArrayList<>();
    private ArrayList<BnbHistory>           mBnbHistory = new ArrayList<>();
    private ArrayList<ResApiTxList.Data>    mApiTxHistory = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail);
        mChainBg                = findViewById(R.id.chain_bg);
        mToolbar                = findViewById(R.id.tool_bar);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mBtnWebDetail           = findViewById(R.id.web_detail);
        mBtnAddressDetail       = findViewById(R.id.address_detail);
        mKeyState               = findViewById(R.id.img_account);
        mAddress                = findViewById(R.id.account_Address);

        mAtomCard               = findViewById(R.id.card_atom);
        mTvAtomTotal            = mAtomCard.findViewById(R.id.dash_atom_amount);
        mTvAtomValue            = mAtomCard.findViewById(R.id.dash_atom_value);
        mTvAtomAvailable        = mAtomCard.findViewById(R.id.dash_atom_undelegate);
        mTvAtomDelegated        = mAtomCard.findViewById(R.id.dash_atom_delegate);
        mTvAtomUnBonding        = mAtomCard.findViewById(R.id.dash_atom_unbonding);
        mTvAtomRewards          = mAtomCard.findViewById(R.id.dash_atom_reward);
        mAtomAction             = mAtomCard.findViewById(R.id.layer_cosmos_actions);
        mAtomTransfer           = mAtomCard.findViewById(R.id.layer_cosmos_transfer);
        mBtnSendAtom            = mAtomCard.findViewById(R.id.btn_atom_send);
        mBtnReceiveAtom         = mAtomCard.findViewById(R.id.btn_atom_receive);
        mBtnBuyAtom             = mAtomCard.findViewById(R.id.btn_cosmos_buy);

        mIrisCard               = findViewById(R.id.card_iris);
        mTvIrisTotal            = mIrisCard.findViewById(R.id.dash_iris_amount);
        mTvIrisValue            = mIrisCard.findViewById(R.id.dash_iris_value);
        mTvIrisAvailable        = mIrisCard.findViewById(R.id.dash_iris_undelegate);
        mTvIrisDelegated        = mIrisCard.findViewById(R.id.dash_iris_delegate);
        mTvIrisUnBonding        = mIrisCard.findViewById(R.id.dash_iris_unbonding);
        mTvIrisRewards          = mIrisCard.findViewById(R.id.dash_iris_reward);
        mIrisAction             = mIrisCard.findViewById(R.id.layer_iris_actions);
        mIrisTransfer           = mIrisCard.findViewById(R.id.layer_iris_transfer);
        mBtnReceiveIris         = mIrisCard.findViewById(R.id.btn_iris_receive);
        mBtnSendIris            = mIrisCard.findViewById(R.id.btn_iris_send);

        mBnbCard                = findViewById(R.id.card_bnb);
        mTvBnbTotal             = mBnbCard.findViewById(R.id.dash_bnb_amount);
        mTvBnbValue             = mBnbCard.findViewById(R.id.dash_bnb_value);
        mTvBnbBalance           = mBnbCard.findViewById(R.id.dash_bnb_balance);
        mTvBnbLocked            = mBnbCard.findViewById(R.id.dash_bnb_locked);
        mBnbAction              = mBnbCard.findViewById(R.id.layer_bnb_module);
        mBtnInterChain          = mBnbCard.findViewById(R.id.btn_bep3_send2);
        mBnbTransfer            = mBnbCard.findViewById(R.id.layer_bnb_transfer);
        mBtnSendBnb             = mBnbCard.findViewById(R.id.btn_bnb_send);
        mBtnReceiveBnb          = mBnbCard.findViewById(R.id.btn_bnb_receive);
        mBtnBuyBnb              = mBnbCard.findViewById(R.id.btn_bnb_buy);

        mKavaCard               = findViewById(R.id.card_kava);
        mTvKavaTotal            = mKavaCard.findViewById(R.id.dash_kava_amount);
        mTvKavaValue            = mKavaCard.findViewById(R.id.dash_kava_value);
        mTvKavaAvailable        = mKavaCard.findViewById(R.id.dash_kava_undelegate);
        mTvKavaVesting          = mKavaCard.findViewById(R.id.dash_kava_vesting);
        mTvKavaDelegated        = mKavaCard.findViewById(R.id.dash_kava_delegate);
        mTvKavaUnBonding        = mKavaCard.findViewById(R.id.dash_kava_unbonding);
        mTvKavaRewards          = mKavaCard.findViewById(R.id.dash_kava_reward);
        mKavaAction             = mKavaCard.findViewById(R.id.layer_kava_actions);
        mKavaTransfer           = mKavaCard.findViewById(R.id.layer_kava_transfer);
        mBtnSendKava            = mKavaCard.findViewById(R.id.btn_kava_send);
        mBtnReceiveKava         = mKavaCard.findViewById(R.id.btn_kava_receive);
        mBtnBuyKava             = mKavaCard.findViewById(R.id.btn_kava_buy);

        mOkCard                 = findViewById(R.id.card_ok);
        mOkTotalAmount          = mOkCard.findViewById(R.id.ok_total_amount);
        mOkTotalValue           = mOkCard.findViewById(R.id.ok_total_value);
        mOkAvailable            = mOkCard.findViewById(R.id.ok_available);
        mOkLocked               = mOkCard.findViewById(R.id.ok_locked);
        mOkDeposit              = mOkCard.findViewById(R.id.ok_deposit);
        mOkWithdrawing          = mOkCard.findViewById(R.id.ok_withdrawing);
        mOkAction               = mOkCard.findViewById(R.id.layer_ok_actions);
        mOkVote                 = mOkCard.findViewById(R.id.btn_ok_vote);
        mOkTransfer             = mOkCard.findViewById(R.id.btn_ok_transfer);
        mBtnOkSend              = mOkCard.findViewById(R.id.btn_ok_send);
        mBtnOkReceive           = mOkCard.findViewById(R.id.btn_ok_receive);

        TokenCard               = findViewById(R.id.card_token);
        mTokenImg               = TokenCard.findViewById(R.id.dash_token_icon);
        mTokenLink              = TokenCard.findViewById(R.id.dash_token_link);
        mTvTokenSymbol          = TokenCard.findViewById(R.id.dash_token_symbol);
        mTvTokenTotal           = TokenCard.findViewById(R.id.dash_token_amount);
        mTvTokenValue           = TokenCard.findViewById(R.id.dash_token_value);
        mTvTokenDenom           = TokenCard.findViewById(R.id.dash_token_denom);
        mTvTokenAvailable       = TokenCard.findViewById(R.id.dash_token_available);
        mTokenLockedLayer       = TokenCard.findViewById(R.id.token_locked_layer);
        mTvTokenLocked          = TokenCard.findViewById(R.id.dash_token_lock);
        mTokenRewardLayer       = TokenCard.findViewById(R.id.token_reward_layer);
        mTvTokenReward          = TokenCard.findViewById(R.id.dash_token_reward);
        mBtnSendToken           = TokenCard.findViewById(R.id.btn_token_send);
        mBtnReceiveToken        = TokenCard.findViewById(R.id.btn_token_receive);
        mBtnTokenDetail         = TokenCard.findViewById(R.id.btn_token_web);
        mBtnBep3Send            = TokenCard.findViewById(R.id.btn_bep3_send);

        mHistoryCnt             = findViewById(R.id.token_cnt);
        mSwipeRefreshLayout     = findViewById(R.id.layer_refresher);
        mRecyclerView           = findViewById(R.id.recycler);
        mEmptyHistory           = findViewById(R.id.empty_history);

        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onFetchTokenHistory();
            }
        });
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this,LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setHasFixedSize(true);
        mTokenHistoryAdapter = new TokenHistoryAdapter();
        mRecyclerView.setAdapter(mTokenHistoryAdapter);

        mBalance = getIntent().getParcelableExtra("balance");
        mIrisToken = getIntent().getParcelableExtra("irisToken");
        mBnbToken = getIntent().getParcelableExtra("bnbToken");
        mBnbTics = (HashMap<String, ResBnbTic>)getIntent().getSerializableExtra("bnbTics");

        mAllValidators = getIntent().getParcelableArrayListExtra("allValidators");
        mIrisReward = getIntent().getParcelableExtra("irisreward");
        mRewards = getIntent().getParcelableArrayListExtra("rewards");

        mOkDenom = getIntent().getStringExtra("okDenom");
        mOkToken = WUtil.getOkToken(getBaseDao().mOkTokenList, mOkDenom);

        mBtnWebDetail.setOnClickListener(this);
        mBtnAddressDetail.setOnClickListener(this);

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
    protected void onResume() {
        super.onResume();
        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mBalances = getBaseDao().onSelectBalance(mAccount.id);
        mBondings = getBaseDao().onSelectBondingStates(mAccount.id);
        mUnbondings = getBaseDao().onSelectUnbondingStates(mAccount.id);

        mAddress.setText(mAccount.address);
        mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            if (mAccount.hasPrivateKey) {
                mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorAtom), android.graphics.PorterDuff.Mode.SRC_IN);
            }

        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            if (mAccount.hasPrivateKey) {
                mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorIris), android.graphics.PorterDuff.Mode.SRC_IN);
            }

        } else if (mBaseChain.equals(BaseChain.BNB_MAIN) || mBaseChain.equals(BaseChain.BNB_TEST)) {
            if (mAccount.hasPrivateKey) {
                mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorBnb), android.graphics.PorterDuff.Mode.SRC_IN);
            }

        } else if (mBaseChain.equals(BaseChain.KAVA_MAIN) || mBaseChain.equals(BaseChain.KAVA_TEST)) {
            if (mAccount.hasPrivateKey) {
                mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorKava), android.graphics.PorterDuff.Mode.SRC_IN);
            }

        } else if (mBaseChain.equals(BaseChain.OK_TEST)) {
            if (mAccount.hasPrivateKey) {
                mKeyState.setColorFilter(ContextCompat.getColor(getBaseContext(), R.color.colorOK), android.graphics.PorterDuff.Mode.SRC_IN);
            }

        }


        if (mBaseChain.equals(BaseChain.COSMOS_MAIN) && mBalance.symbol.equals(TOKEN_ATOM) ||
                mBaseChain.equals(BaseChain.COSMOS_MAIN) && (IS_TEST && mBalance.symbol.equals(TOKEN_MUON))) {
            mAtomCard.setVisibility(View.VISIBLE);
            mAtomAction.setVisibility(View.GONE);
            mAtomTransfer.setVisibility(View.VISIBLE);
            mBtnBuyAtom.setVisibility(View.VISIBLE);
            mBtnSendAtom.setOnClickListener(this);
            mBtnReceiveAtom.setOnClickListener(this);
            mBtnBuyAtom.setOnClickListener(this);

            BigDecimal totalAmount = WDp.getAllAtom(mBalances, mBondings, mUnbondings, mRewards, mAllValidators);
            mTvAtomTotal.setText(WDp.getDpAmount(this, totalAmount, 6, mBaseChain));
            mTvAtomAvailable.setText(WDp.getDpAvailableCoin(this, mBalances, mBaseChain, TOKEN_ATOM));
            mTvAtomDelegated.setText(WDp.getDpAllDelegatedAmount(this, mBondings, mAllValidators, mBaseChain));
            mTvAtomUnBonding.setText(WDp.getDpAllUnbondingAmount(this, mUnbondings, mAllValidators, mBaseChain));
            mTvAtomUnBonding.setText(WDp.getDpAllUnbondingAmount(this, mUnbondings, mAllValidators, mBaseChain));
            mTvAtomRewards.setText(WDp.getDpAllRewardAmount(this, mRewards, mBaseChain, TOKEN_ATOM));
            mTvAtomValue.setText(WDp.getValueOfAtom(this, getBaseDao(), totalAmount));

        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN) && mBalance.symbol.equals(TOKEN_IRIS_ATTO)) {
            mIrisCard.setVisibility(View.VISIBLE);
            mIrisAction.setVisibility(View.GONE);
            mIrisTransfer.setVisibility(View.VISIBLE);
            mBtnSendIris.setOnClickListener(this);
            mBtnReceiveIris.setOnClickListener(this);

            mTvIrisTotal.setText(WDp.getDpAmount(this, WDp.getAllIris(mBalances, mBondings, mUnbondings, mIrisReward, mAllValidators), 18, mBaseChain));
            mTvIrisAvailable.setText(WDp.getDpAmount(this, WDp.getAvailableCoin(mBalances, TOKEN_IRIS_ATTO), 18, mBaseChain));
            mTvIrisDelegated.setText(WDp.getDpAmount(this, WDp.getAllDelegatedAmount(mBondings, mAllValidators, mBaseChain), 18, mBaseChain));
            mTvIrisUnBonding.setText(WDp.getDpAmount(this, WDp.getUnbondingAmount(mUnbondings), 18, mBaseChain));
            mTvIrisRewards.setText(WDp.getDpAmount(this, mIrisReward.getSimpleIrisReward(), 18, mBaseChain));

            BigDecimal totalAmount = WDp.getAllIris(mBalances, mBondings, mUnbondings, mIrisReward, mAllValidators);
            mTvIrisValue.setText(WDp.getValueOfIris(this, getBaseDao(), totalAmount));

        } else if ((mBaseChain.equals(BaseChain.BNB_MAIN) || mBaseChain.equals(BaseChain.BNB_TEST)) && mBalance.symbol.equals(TOKEN_BNB)) {
            mBnbCard.setVisibility(View.VISIBLE);
            mBnbAction.setVisibility(View.GONE);
            mBnbTransfer.setVisibility(View.VISIBLE);
            mBtnSendBnb.setOnClickListener(this);
            mBtnReceiveBnb.setOnClickListener(this);
            mBtnInterChain.setVisibility(View.VISIBLE);
            mBtnInterChain.setOnClickListener(this);

            if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
                mBtnBuyBnb.setVisibility(View.VISIBLE);
                mBtnBuyBnb.setOnClickListener(this);
            } else {
                mBtnBuyBnb.setVisibility(View.GONE);
            }

            if (mBnbToken != null) {
                BigDecimal totalAmount = mBalance.locked.add(mBalance.balance);
                mTvBnbBalance.setText(WDp.getDpAmount(this, mBalance.balance, 8, mBaseChain));
                mTvBnbLocked.setText(WDp.getDpAmount(this, mBalance.locked, 8, mBaseChain));
                mTvBnbTotal.setText(WDp.getDpAmount(this, mBalance.locked.add(mBalance.balance), 8, mBaseChain));
                mTvBnbValue.setText(WDp.getValueOfBnb(this, getBaseDao(), totalAmount));

            } else {
                mTvBnbBalance.setText(WDp.getDpAmount(this, BigDecimal.ZERO, 8, mBaseChain));
                mTvBnbLocked.setText(WDp.getDpAmount(this, BigDecimal.ZERO, 8, mBaseChain));
                mTvBnbTotal.setText(WDp.getDpAmount(this, BigDecimal.ZERO, 8, mBaseChain));
                mTvBnbValue.setText(WDp.getValueOfBnb(this, getBaseDao(), BigDecimal.ZERO));
            }

        } else if ((mBaseChain.equals(BaseChain.KAVA_MAIN) || mBaseChain.equals(BaseChain.KAVA_TEST))&& mBalance.symbol.equals(TOKEN_KAVA)) {
            mKavaCard.setVisibility(View.VISIBLE);
            mKavaAction.setVisibility(View.GONE);
            mKavaTransfer.setVisibility(View.VISIBLE);
            if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                mBtnBuyKava.setVisibility(View.VISIBLE);
                mBtnBuyKava.setOnClickListener(this);
            } else {
                mBtnBuyKava.setVisibility(View.GONE);
            }
            mBtnSendKava.setOnClickListener(this);
            mBtnReceiveKava.setOnClickListener(this);

            BigDecimal totalAmount = WDp.getAllKava(mBalances, mBondings, mUnbondings, mRewards, mAllValidators);
            mTvKavaTotal.setText(WDp.getDpAmount(this, totalAmount, 6, mBaseChain));
            mTvKavaAvailable.setText(WDp.getDpAvailableCoin(this, mBalances, mBaseChain, TOKEN_KAVA));
            mTvKavaDelegated.setText(WDp.getDpAllDelegatedAmount(this, mBondings, mAllValidators, mBaseChain));
            mTvKavaUnBonding.setText(WDp.getDpAllUnbondingAmount(this, mUnbondings, mAllValidators, mBaseChain));
            mTvKavaRewards.setText(WDp.getDpAllRewardAmount(this, mRewards, mBaseChain, TOKEN_KAVA));
            mTvKavaVesting.setText(WDp.getDpVestedCoin(this, mBalances, mBaseChain, TOKEN_KAVA));
            mTvKavaValue.setText(WDp.getValueOfKava(this, getBaseDao(), totalAmount));

        } else if (mBaseChain.equals(BaseChain.OK_TEST) && mOkDenom.equals(TOKEN_OK_TEST)) {
            mOkCard.setVisibility(View.VISIBLE);
            mOkCard.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            mOkAction.setVisibility(View.GONE);
            mOkVote.setVisibility(View.GONE);
            mOkTransfer.setVisibility(View.VISIBLE);
            mBtnOkSend.setOnClickListener(this);
            mBtnOkReceive.setOnClickListener(this);

            BigDecimal availableAmount = WDp.getAvailableCoin(mBalances, TOKEN_OK_TEST);
            BigDecimal lockedAmount = WDp.getLockedCoin(mBalances, TOKEN_OK_TEST);
            BigDecimal depositAmount = WDp.getOkDepositCoin(getBaseDao().mOkDeposit);
            BigDecimal withdrawAmount = WDp.getOkWithdrawingCoin(getBaseDao().mOkWithdraw);
            BigDecimal totalAmount = availableAmount.add(lockedAmount).add(depositAmount).add(withdrawAmount);

            mOkTotalAmount.setText(WDp.getDpAmount2(getBaseContext(), totalAmount, 0, 6));
            mOkAvailable.setText(WDp.getDpAmount2(getBaseContext(), availableAmount, 0, 6));
            mOkLocked.setText(WDp.getDpAmount2(getBaseContext(), lockedAmount, 0, 6));
            mOkDeposit.setText(WDp.getDpAmount2(getBaseContext(), depositAmount, 0, 6));
            mOkWithdrawing.setText(WDp.getDpAmount2(getBaseContext(), withdrawAmount, 0, 6));
            mOkTotalValue.setText(WDp.getValueOfOk(getBaseContext(), getBaseDao(), totalAmount));


        } else {
            TokenCard.setVisibility(View.VISIBLE);
            mBtnBep3Send.setVisibility(View.GONE);
            mBtnSendToken.setOnClickListener(this);
            mBtnReceiveToken.setOnClickListener(this);

            if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN) && mIrisToken != null) {
                mTokenLink.setVisibility(View.GONE);
                mTvTokenSymbol.setText(mIrisToken.base_token.symbol.toUpperCase());
                mTvTokenDenom.setText(mBalance.symbol);

                //TODO check token reward amount!
                //TODO check token value!
                mTvTokenTotal.setText(WDp.getDpAmount(this, mBalance.balance, mIrisToken.base_token.decimal, mBaseChain));
                mTvTokenAvailable.setText(WDp.getDpAmount(this, mBalance.balance, mIrisToken.base_token.decimal, mBaseChain));
                mTokenRewardLayer.setVisibility(View.GONE);
                mTokenImg.setImageDrawable(getResources().getDrawable(R.drawable.token_ic));

            } else if (mBaseChain.equals(BaseChain.BNB_MAIN) && mBnbToken != null) {
                mTokenLink.setVisibility(View.VISIBLE);
                mBtnTokenDetail.setOnClickListener(this);
                mTvTokenSymbol.setText(mBnbToken.original_symbol);
                mTvTokenDenom.setText(mBnbToken.symbol);
                mTvTokenTotal.setText(WDp.getDpAmount(this, mBalance.getAllBnbBalance(), 8, mBaseChain));

                BigDecimal amount = BigDecimal.ZERO;
                ResBnbTic tic = mBnbTics.get(WUtil.getBnbTicSymbol(mBalance.symbol));
                if (tic != null) {
                    amount = mBalance.exchangeToBnbAmount(tic);
                }

                mTvTokenValue.setText(WDp.getValueOfBnb(this, getBaseDao(), amount));
                mTvTokenAvailable.setText(WDp.getDpAmount(this, mBalance.balance, 8, mBaseChain));
                mTokenRewardLayer.setVisibility(View.GONE);
                try {
                    Picasso.get().load(TOKEN_IMG_URL+mBnbToken.original_symbol+".png")
                            .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic)
                            .into(mTokenImg);
                } catch (Exception e) {}

            } else if (mBaseChain.equals(BaseChain.BNB_TEST) && mBnbToken != null) {
                mTokenLink.setVisibility(View.VISIBLE);
                mBtnTokenDetail.setOnClickListener(this);
                mTvTokenSymbol.setText(mBnbToken.original_symbol);
                mTvTokenDenom.setText(mBnbToken.symbol);
                mTvTokenTotal.setText(WDp.getDpAmount(this, mBalance.getAllBnbBalance(), 8, mBaseChain));

                BigDecimal amount = BigDecimal.ZERO;
                ResBnbTic tic = mBnbTics.get(WUtil.getBnbTicSymbol(mBalance.symbol));
                if (tic != null) {
                    amount = mBalance.exchangeToBnbAmount(tic);
                }

                mTvTokenValue.setText(WDp.getValueOfBnb(this, getBaseDao(), amount));
                mTvTokenAvailable.setText(WDp.getDpAmount(this, mBalance.balance, 8, mBaseChain));
                mTokenRewardLayer.setVisibility(View.GONE);
                try {
                    Picasso.get().load(TOKEN_IMG_URL+mBnbToken.original_symbol+".png")
                            .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic)
                            .into(mTokenImg);
                } catch (Exception e) {}
                if (mBalance.symbol.equals(TOKEN_HTLC_BINANCE_TEST_BTC)) {
                    mBtnBep3Send.setVisibility(View.VISIBLE);
                    mBtnBep3Send.setOnClickListener(this);
                } else {
                    mBtnBep3Send.setVisibility(View.GONE);
                }

            } else if (mBaseChain.equals(BaseChain.KAVA_MAIN) && mBalance != null) {
                mTokenLink.setVisibility(View.GONE);
                mTvTokenSymbol.setText(mBalance.symbol.toUpperCase());
                mTvTokenDenom.setText(mBalance.symbol);

                mTvTokenTotal.setText(WDp.getDpAmount2(this, mBalance.balance, WUtil.getKavaCoinDecimal(mBalance.symbol), WUtil.getKavaCoinDecimal(mBalance.symbol)));
                mTvTokenAvailable.setText(WDp.getDpAmount2(this, mBalance.balance, WUtil.getKavaCoinDecimal(mBalance.symbol), WUtil.getKavaCoinDecimal(mBalance.symbol)));
                BigDecimal tokenTotalValue = mBalance.kavaTokenDollorValue(getBaseDao().mKavaTokenPrices);
                BigDecimal convertedKavaAmount = tokenTotalValue.divide(getBaseDao().getLastKavaDollorTic(), WUtil.getKavaCoinDecimal(TOKEN_KAVA), RoundingMode.DOWN);
                mTvTokenValue.setText(WDp.getValueOfKava(this, getBaseDao(), convertedKavaAmount.movePointRight(WUtil.getKavaCoinDecimal(TOKEN_KAVA))));
                mTokenRewardLayer.setVisibility(View.GONE);
                try {
                    Picasso.get().load(KAVA_COIN_IMG_URL+mBalance.symbol+".png")
                            .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic)
                            .into(mTokenImg);

                } catch (Exception e) { }
                if (mBalance.symbol.equals(TOKEN_HTLC_KAVA_TEST_BNB)) {
                    mBtnBep3Send.setVisibility(View.VISIBLE);
                    mBtnBep3Send.setOnClickListener(this);
                } else if (mBalance.symbol.equals("usdx")) {
                    mTokenLink.setVisibility(View.VISIBLE);
                    mBtnTokenDetail.setOnClickListener(this);
                } else {
                    mBtnBep3Send.setVisibility(View.GONE);
                }

            } else if (mBaseChain.equals(BaseChain.KAVA_TEST) && mBalance != null) {
                mTokenLink.setVisibility(View.GONE);
                mTvTokenSymbol.setText(mBalance.symbol.toUpperCase());
                mTvTokenDenom.setText(mBalance.symbol);

                mTvTokenTotal.setText(WDp.getDpAmount2(this, mBalance.balance, WUtil.getKavaCoinDecimal(mBalance.symbol), WUtil.getKavaCoinDecimal(mBalance.symbol)));
                mTvTokenAvailable.setText(WDp.getDpAmount2(this, mBalance.balance, WUtil.getKavaCoinDecimal(mBalance.symbol), WUtil.getKavaCoinDecimal(mBalance.symbol)));
                BigDecimal tokenTotalValue = mBalance.kavaTokenDollorValue(getBaseDao().mKavaTokenPrices);
                BigDecimal convertedKavaAmount = tokenTotalValue.divide(getBaseDao().getLastKavaDollorTic(), WUtil.getKavaCoinDecimal(TOKEN_KAVA), RoundingMode.DOWN);
                mTvTokenValue.setText(WDp.getValueOfKava(this, getBaseDao(), convertedKavaAmount.movePointRight(WUtil.getKavaCoinDecimal(TOKEN_KAVA))));
                mTokenRewardLayer.setVisibility(View.GONE);
                try {
                    Picasso.get().load(KAVA_COIN_IMG_URL+mBalance.symbol+".png")
                            .fit().placeholder(R.drawable.token_ic).error(R.drawable.token_ic)
                            .into(mTokenImg);

                } catch (Exception e) { }
                if (mBalance.symbol.equals(TOKEN_HTLC_KAVA_TEST_BNB) || mBalance.symbol.equals(TOKEN_HTLC_KAVA_TEST_BTC)) {
                    mBtnBep3Send.setVisibility(View.VISIBLE);
                    mBtnBep3Send.setOnClickListener(this);
                } else if (mBalance.symbol.equals("usdx")) {
                    mTokenLink.setVisibility(View.VISIBLE);
                    mBtnTokenDetail.setOnClickListener(this);
                } else {
                    mBtnBep3Send.setVisibility(View.GONE);
                }

            } else if (mBaseChain.equals(BaseChain.OK_TEST) && mOkToken != null) {
                mTokenLink.setVisibility(View.VISIBLE);
                mTokenLockedLayer.setVisibility(View.VISIBLE);
                mBtnTokenDetail.setOnClickListener(this);
                mTvTokenSymbol.setText(mOkToken.original_symbol.toUpperCase());
                mTvTokenDenom.setText(mOkToken.description);

                BigDecimal availableAmount = WDp.getAvailableCoin(mBalances, mOkToken.symbol);
                BigDecimal lockedAmount = WDp.getLockedCoin(mBalances, mOkToken.symbol);
                BigDecimal totalAmount = availableAmount.add(lockedAmount);

                mTvTokenTotal.setText(WDp.getDpAmount2(getBaseContext(), totalAmount, 0, 8));
                mTvTokenAvailable.setText(WDp.getDpAmount2(getBaseContext(), availableAmount, 0, 8));
                mTvTokenLocked.setText(WDp.getDpAmount2(getBaseContext(), lockedAmount, 0, 8));
                mTvTokenValue.setText(WDp.getValueOfOk(getBaseContext(), getBaseDao(), totalAmount));
                mTokenImg.setImageDrawable(getDrawable(R.drawable.token_ic));

            } else {
                onBackPressed();
            }
        }
        onFetchTokenHistory();
    }

    private void onFetchTokenHistory() {
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            new ApiTokenTxsHistoryTask(getBaseApplication(), this, mAccount.address, mBalance.symbol, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            ReqTxToken req = new ReqTxToken(0, 1, true, mAccount.address, mBalance.symbol);
            new TokenHistoryTask(getBaseApplication(), this, req, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(BaseChain.BNB_MAIN) || mBaseChain.equals(BaseChain.BNB_TEST)) {
            new HistoryTask(getBaseApplication(), this, null, mBaseChain)
                    .executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, mAccount.address, WDp.threeMonthAgoTimeString(), WDp.cTimeString(), mBnbToken.symbol);

        } else if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            new ApiTokenTxsHistoryTask(getBaseApplication(), this, mAccount.address, mBalance.symbol, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(BaseChain.KAVA_TEST)) {
            new ApiTokenTxsHistoryTask(getBaseApplication(), this, mAccount.address, mBalance.symbol, mBaseChain).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);

        } else if (mBaseChain.equals(BaseChain.OK_TEST)) {
            mSwipeRefreshLayout.setRefreshing(false);

        }
    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        if (result.taskType == BaseConstant.TASK_FETCH_TOKEN_HISTORY) {
            ArrayList<ResHistory.InnerHits> hits = (ArrayList<ResHistory.InnerHits>)result.resultData;
            if(hits != null && hits.size() > 0) {
                mHistory = hits;
                mHistoryCnt.setText(""+mHistory.size());
                mTokenHistoryAdapter.notifyDataSetChanged();
                mEmptyHistory.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);
            } else {
                mHistoryCnt.setText("0");
                mEmptyHistory.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            }

        } else if (result.taskType == BaseConstant.TASK_FETCH_BNB_HISTORY) {
            ArrayList<BnbHistory> hits = (ArrayList<BnbHistory>)result.resultData;
            if (hits != null && hits.size() > 0) {
                mBnbHistory = hits;
                mHistoryCnt.setText(""+mBnbHistory.size());
                mTokenHistoryAdapter.notifyDataSetChanged();
                mEmptyHistory.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);

            } else {
                mHistoryCnt.setText("0");
                mEmptyHistory.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            }
        } else if (result.taskType == BaseConstant.TASK_FETCH_API_TOKEN_HISTORY) {
            ArrayList<ResApiTxList.Data> hits = (ArrayList<ResApiTxList.Data>)result.resultData;
            if (hits != null && hits.size() > 0) {
                WLog.w("hit size " + hits.size());
                mApiTxHistory = hits;
                mHistoryCnt.setText(""+mApiTxHistory.size());
                mTokenHistoryAdapter.notifyDataSetChanged();
                mEmptyHistory.setVisibility(View.GONE);
                mRecyclerView.setVisibility(View.VISIBLE);

            } else {
                mHistoryCnt.setText("0");
                mEmptyHistory.setVisibility(View.VISIBLE);
                mRecyclerView.setVisibility(View.GONE);
            }

        }
        mSwipeRefreshLayout.setRefreshing(false);
    }


    @Override
    public void onClick(View v) {
        if (v.equals(mBtnAddressDetail) || v.equals(mBtnReceiveAtom) || v.equals(mBtnReceiveIris) || v.equals(mBtnReceiveBnb) || v.equals(mBtnReceiveKava) || v.equals(mBtnOkReceive) || v.equals(mBtnReceiveToken)) {
            Bundle bundle = new Bundle();
            bundle.putString("address", mAccount.address);
            if (TextUtils.isEmpty(mAccount.nickName))
                bundle.putString("title", getString(R.string.str_my_wallet) + mAccount.id);
            else
                bundle.putString("title", mAccount.nickName);
            Dialog_AccountShow show = Dialog_AccountShow.newInstance(bundle);
            show.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(show, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mBtnWebDetail)) {
            if (mBaseChain.equals(BaseChain.KAVA_TEST)) { return; }
            Intent webintent = new Intent(this, WebActivity.class);
            webintent.putExtra("address", mAccount.address);
            webintent.putExtra("chain", mBaseChain.getChain());
            webintent.putExtra("goMain", false);
            startActivity(webintent);

        } else if (v.equals(mBtnSendAtom)) {
            if (onCheckSendable()) {
                startActivity(new Intent(TokenDetailActivity.this, SendActivity.class));
            }

        } else if (v.equals(mBtnSendIris)) {
            if (onCheckSendable()) {
                Intent intent = new Intent(TokenDetailActivity.this, SendActivity.class);
                intent.putExtra("irisToken", mIrisToken);
                startActivity(intent);
            }

        } else if (v.equals(mBtnSendBnb)) {
            if (onCheckSendable()) {
                Intent intent = new Intent(TokenDetailActivity.this, SendActivity.class);
                intent.putExtra("bnbToken", mBnbToken);
                startActivity(intent);
            }

        } else if (v.equals(mBtnSendKava)) {
            if (onCheckSendable()) {
                Intent intent = new Intent(TokenDetailActivity.this, SendActivity.class);
                intent.putExtra("kavaDenom", TOKEN_KAVA);
                startActivity(intent);
            }

        } else if (v.equals(mBtnOkSend)) {
            if (onCheckSendable()) {
                Intent intent = new Intent(TokenDetailActivity.this, SendActivity.class);
                intent.putExtra("okDenom", TOKEN_OK_TEST);
                startActivity(intent);
            }

        } else if (v.equals(mBtnSendToken)) {
            if (onCheckSendable()) {
                Intent intent = new Intent(TokenDetailActivity.this, SendActivity.class);
                if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                    intent.putExtra("irisToken", mIrisToken);
                } else if (mBaseChain.equals(BaseChain.BNB_MAIN) || mBaseChain.equals(BaseChain.BNB_TEST)) {
                    intent.putExtra("bnbToken", mBnbToken);
                    intent.putExtra("bnbTics", mBnbTics);
                } else if (mBaseChain.equals(BaseChain.KAVA_MAIN) || mBaseChain.equals(BaseChain.KAVA_TEST)) {
                    intent.putExtra("kavaDenom", mBalance.symbol);
                } else if (mBaseChain.equals(BaseChain.OK_TEST)) {
                    intent.putExtra("okDenom", mOkToken.symbol);
                }
                startActivity(intent);
            }

        } else if (v.equals(mBtnTokenDetail)) {
            if (mBaseChain.equals(BaseChain.BNB_MAIN) || mBaseChain.equals(BaseChain.BNB_TEST)) {
                Intent webintent = new Intent(this, WebActivity.class);
                webintent.putExtra("asset", mBnbToken.symbol);
                webintent.putExtra("chain", mBaseChain.getChain());
                startActivity(webintent);
            } else if (mBaseChain.equals(BaseChain.KAVA_MAIN) || mBaseChain.equals(BaseChain.KAVA_TEST)) {
                Intent webintent = new Intent(this, WebActivity.class);
                webintent.putExtra("asset", "usdx");
                webintent.putExtra("chain", mBaseChain.getChain());
                startActivity(webintent);
            } else if (mBaseChain.equals(BaseChain.OK_TEST)) {
                Intent webintent = new Intent(this, WebActivity.class);
                webintent.putExtra("asset", mOkToken.symbol);
                webintent.putExtra("chain", mBaseChain.getChain());
                startActivity(webintent);
            }

        } else if (v.equals(mBtnBuyAtom) || v.equals(mBtnBuyBnb) || v.equals(mBtnBuyKava)) {
            if (mAccount.hasPrivateKey) {
                onShowBuySelectFiat();
            } else {
                onShowBuyWarnNoKey();
            }
        } else if (v.equals(mBtnInterChain) || v.equals(mBtnBep3Send)) {
            onStartHTLCSendActivity(mBalance.symbol);
        }
    }

    public boolean onCheckSendable() {
        if (mAccount == null) return false;
        if (!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return false;
        }

        ArrayList<Balance> balances = getBaseDao().onSelectBalance(mAccount.id);
        boolean hasbalance = false;
        if (mBaseChain.equals(BaseChain.COSMOS_MAIN) || mBaseChain.equals(BaseChain.KAVA_MAIN) || mBaseChain.equals(BaseChain.KAVA_TEST)) {
            hasbalance  = true;

        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            if (WDp.getAvailableCoin(balances, TOKEN_IRIS_ATTO).compareTo(new BigDecimal("200000000000000000")) > 0) {
                hasbalance  = true;
            }
            if (!mIrisToken.base_token.symbol.equals(TOKEN_IRIS)) {
                Toast.makeText(getBaseContext(), R.string.error_iris_token_not_yet, Toast.LENGTH_SHORT).show();
                return false;
            }

        } else if (mBaseChain.equals(BaseChain.BNB_MAIN) || mBaseChain.equals(BaseChain.BNB_TEST)) {
            if (WDp.getAvailableCoin(balances, TOKEN_BNB).compareTo(new BigDecimal(FEE_BNB_SEND)) > 0) {
                hasbalance  = true;
            }

        } else if (mBaseChain.equals(BaseChain.OK_TEST)) {
            if (WDp.getAvailableCoin(balances, TOKEN_OK_TEST).compareTo(new BigDecimal("0.02")) > 0) {
                hasbalance  = true;
            }
        }

        if (!hasbalance){
            Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
        }
        return hasbalance;
    }

    private class TokenHistoryAdapter extends RecyclerView.Adapter<TokenHistoryAdapter.HistoryHolder> {

        @NonNull
        @Override
        public HistoryHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new HistoryHolder(getLayoutInflater().inflate(R.layout.item_history, viewGroup, false));
        }

        @Override
        public void onBindViewHolder(@NonNull HistoryHolder viewHolder, int position) {
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                final ResApiTxList.Data tx = mApiTxHistory.get(position);
                if (tx.logs != null) {
                    viewHolder.historySuccess.setVisibility(View.GONE);
                } else {
                    viewHolder.historySuccess.setVisibility(View.VISIBLE);
                }
                viewHolder.historyType.setText(WDp.DpTxType(getBaseContext(), tx.messages, mAccount.address));
                viewHolder.history_time.setText(WDp.getTimeTxformat(getBaseContext(), tx.time));
                viewHolder.history_time_gap.setText(WDp.getTimeTxGap(getBaseContext(), tx.time));
                viewHolder.history_block.setText("" + tx.height + " block");
                viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent txDetail = new Intent(getBaseContext(), TxDetailActivity.class);
                        txDetail.putExtra("txHash", tx.tx_hash);
                        txDetail.putExtra("isGen", false);
                        txDetail.putExtra("isSuccess", true);
                        startActivity(txDetail);
                    }
                });

            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                final ResHistory.Source source = mHistory.get(position)._source;
                if(source.result.Code > 0) {
                    viewHolder.historySuccess.setVisibility(View.VISIBLE);
                } else {
                    viewHolder.historySuccess.setVisibility(View.GONE);
                }
                viewHolder.historyType.setText(WDp.DpTxType(getBaseContext(), source.tx.value.msg, mAccount.address));
                viewHolder.history_time.setText(WDp.getTimeformat(getBaseContext(), source.time));
                viewHolder.history_time_gap.setText(WDp.getTimeGap(getBaseContext(), source.time));
                viewHolder.history_block.setText(source.height + " block");
                viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent txDetail = new Intent(getBaseContext(), TxDetailActivity.class);
                        txDetail.putExtra("txHash", source.hash);
                        txDetail.putExtra("isGen", false);
                        txDetail.putExtra("isSuccess", true);
                        startActivity(txDetail);

                    }
                });

            } else if (mBaseChain.equals(BaseChain.BNB_MAIN)) {
                final BnbHistory history = mBnbHistory.get(position);
                viewHolder.historyType.setText(history.txType);
                viewHolder.historyType.setText(WDp.DpBNBTxType(getBaseContext(), history, mAccount.address));
                viewHolder.history_time.setText(WDp.getTimeformat(getBaseContext(), history.timeStamp));
                viewHolder.history_time_gap.setText(WDp.getTimeGap(getBaseContext(), history.timeStamp));
                viewHolder.history_block.setText(history.blockHeight + " block");
                viewHolder.historySuccess.setVisibility(View.GONE);
                viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (history.txType.equals("HTL_TRANSFER") || history.txType.equals("CLAIM_HTL") || history.txType.equals("REFUND_HTL")) {
                            Intent txDetail = new Intent(getBaseContext(), TxDetailActivity.class);
                            txDetail.putExtra("txHash", history.txHash);
                            txDetail.putExtra("isGen", false);
                            txDetail.putExtra("isSuccess", true);
                            txDetail.putExtra("bnbTime", history.timeStamp);
                            startActivity(txDetail);

                        } else {
                            Intent webintent = new Intent(getBaseContext(), WebActivity.class);
                            webintent.putExtra("txid", history.txHash);
                            webintent.putExtra("chain", mBaseChain.getChain());
                            startActivity(webintent);
                        }
                    }
                });

            } else if (mBaseChain.equals(BaseChain.BNB_TEST)) {
                final BnbHistory history = mBnbHistory.get(position);
                viewHolder.historyType.setText(history.txType);
                viewHolder.historyType.setText(WDp.DpBNBTxType(getBaseContext(), history, mAccount.address));
                viewHolder.history_time.setText(WDp.getTimeformat(getBaseContext(), history.timeStamp));
                viewHolder.history_time_gap.setText(WDp.getTimeGap(getBaseContext(), history.timeStamp));
                viewHolder.history_block.setText(history.blockHeight + " block");
                viewHolder.historySuccess.setVisibility(View.GONE);
                viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent webintent = new Intent(getBaseContext(), WebActivity.class);
                        webintent.putExtra("txid", history.txHash);
                        webintent.putExtra("chain", mBaseChain.getChain());
                        startActivity(webintent);
                    }
                });

            } else if (mBaseChain.equals(BaseChain.KAVA_MAIN) || mBaseChain.equals(BaseChain.KAVA_TEST)) {
                final ResApiTxList.Data tx = mApiTxHistory.get(position);
                if (tx.logs != null) {
                    viewHolder.historySuccess.setVisibility(View.GONE);
                } else {
                    viewHolder.historySuccess.setVisibility(View.VISIBLE);
                }
                viewHolder.historyType.setText(WDp.DpTxType(getBaseContext(), tx.messages, mAccount.address));
                viewHolder.history_time.setText(WDp.getTimeTxformat(getBaseContext(), tx.time));
                viewHolder.history_time_gap.setText(WDp.getTimeTxGap(getBaseContext(), tx.time));
                viewHolder.history_block.setText("" + tx.height + " block");
                viewHolder.historyRoot.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent txDetail = new Intent(getBaseContext(), TxDetailActivity.class);
                        txDetail.putExtra("txHash", tx.tx_hash);
                        txDetail.putExtra("isGen", false);
                        txDetail.putExtra("isSuccess", true);
                        startActivity(txDetail);
                    }
                });
            }
        }

        @Override
        public int getItemCount() {
            if (mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                return mApiTxHistory.size();
            } else if (mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                return mHistory.size();
            } else if (mBaseChain.equals(BaseChain.BNB_MAIN) || mBaseChain.equals(BaseChain.BNB_TEST)) {
                return mBnbHistory.size();
            } else if (mBaseChain.equals(BaseChain.KAVA_MAIN) || mBaseChain.equals(BaseChain.KAVA_TEST)) {
                return mApiTxHistory.size();
            }
            return 0;
        }


        public class HistoryHolder extends RecyclerView.ViewHolder {
            private CardView historyRoot;
            private TextView historyType, historySuccess, history_time, history_block, history_time_gap;

            public HistoryHolder(View v) {
                super(v);
                historyRoot         = itemView.findViewById(R.id.card_history);
                historyType         = itemView.findViewById(R.id.history_type);
                historySuccess      = itemView.findViewById(R.id.history_success);
                history_time        = itemView.findViewById(R.id.history_time);
                history_block       = itemView.findViewById(R.id.history_block_height);
                history_time_gap    = itemView.findViewById(R.id.history_time_gap);
            }
        }
    }
}
