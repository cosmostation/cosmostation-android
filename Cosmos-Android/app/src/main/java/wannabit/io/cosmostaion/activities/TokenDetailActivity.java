package wannabit.io.cosmostaion.activities;

import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.math.BigDecimal;
import java.math.RoundingMode;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.BnbToken;
import wannabit.io.cosmostaion.dao.IrisToken;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_IRIS_ATTO;

public class TokenDetailActivity extends BaseActivity implements View.OnClickListener {

    private ImageView               mChainBg;
    private Toolbar                 mToolbar;

    private CardView                mAtomCard, mIrisCard, mBnbCard, TokenCard;
    private TextView                mTvAtomTotal, mTvAtomValue, mTvAtomUndelegated,
                                    mTvAtomDelegated, mTvAtomUnBonding, mTvAtomRewards;
    private TextView                mTvIrisTotal, mTvIrisValue, mTvIrisUndelegated,
                                    mTvIrisDelegated, mTvIrisUnBonding, mTvIrisRewards;
    private TextView                mTvBnbTotal, mTvBnbValue, mTvBnbBalance, mTvBnbLocked;
    private ImageView               mTokenImg;
    private TextView                mTvTokenSymbol, mTvTokenTotal, mTvTokenValue, mTvTokenDenom,
                                    mTvTokenAvailable, mTvTokenReward;
    private LinearLayout            mAtomAction, mIrisAction;
    private RelativeLayout          mBnbAction;
    private RelativeLayout          mBtnSendAtom, mBtnSendIris, mBtnSendBnb, mBtnSendToken;
    private TextView                mHistoryCnt;
    private SwipeRefreshLayout      mSwipeRefreshLayout;
    private RecyclerView            mRecyclerView;
    private LinearLayout            mEmptyHistory;

    private Balance                 mBalance;
    private IrisToken               mIrisToken;
    private BnbToken                mBnbToken;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_token_detail);
        mChainBg                = findViewById(R.id.chain_bg);
        mToolbar                = findViewById(R.id.tool_bar);

        mAtomCard               = findViewById(R.id.card_atom);
        mTvAtomTotal            = mAtomCard.findViewById(R.id.dash_atom_amount);
        mTvAtomValue            = mAtomCard.findViewById(R.id.dash_atom_value);
        mTvAtomUndelegated      = mAtomCard.findViewById(R.id.dash_atom_undelegate);
        mTvAtomDelegated        = mAtomCard.findViewById(R.id.dash_atom_delegate);
        mTvAtomUnBonding        = mAtomCard.findViewById(R.id.dash_atom_unbonding);
        mTvAtomRewards          = mAtomCard.findViewById(R.id.dash_atom_reward);
        mAtomAction             = mAtomCard.findViewById(R.id.layer_cosmos_actions);
        mBtnSendAtom            = mAtomCard.findViewById(R.id.btn_atom_send);

        mIrisCard               = findViewById(R.id.card_iris);
        mTvIrisTotal            = mIrisCard.findViewById(R.id.dash_iris_amount);
        mTvIrisValue            = mIrisCard.findViewById(R.id.dash_iris_value);
        mTvIrisUndelegated      = mIrisCard.findViewById(R.id.dash_iris_undelegate);
        mTvIrisDelegated        = mIrisCard.findViewById(R.id.dash_iris_delegate);
        mTvIrisUnBonding        = mIrisCard.findViewById(R.id.dash_iris_unbonding);
        mTvIrisRewards          = mIrisCard.findViewById(R.id.dash_iris_reward);
        mIrisAction             = mIrisCard.findViewById(R.id.layer_iris_actions);
        mBtnSendIris            = mIrisCard.findViewById(R.id.btn_iris_send);

        mBnbCard                = findViewById(R.id.card_bnb);
        mTvBnbTotal             = mBnbCard.findViewById(R.id.dash_bnb_amount);
        mTvBnbValue             = mBnbCard.findViewById(R.id.dash_bnb_value);
        mTvBnbBalance           = mBnbCard.findViewById(R.id.dash_bnb_balance);
        mTvBnbLocked            = mBnbCard.findViewById(R.id.dash_bnb_locked);
        mBnbAction              = mBnbCard.findViewById(R.id.btn_wallet_connect);
        mBtnSendBnb             = mBnbCard.findViewById(R.id.btn_bnb_send);

        TokenCard               = findViewById(R.id.card_token);
        mTokenImg               = TokenCard.findViewById(R.id.dash_token_icon);
        mTvTokenSymbol          = TokenCard.findViewById(R.id.dash_token_symbol);
        mTvTokenTotal           = TokenCard.findViewById(R.id.dash_token_amount);
        mTvTokenValue           = TokenCard.findViewById(R.id.dash_token_value);
        mTvTokenDenom           = TokenCard.findViewById(R.id.dash_token_denom);
        mTvTokenAvailable       = TokenCard.findViewById(R.id.dash_token_available);
        mTvTokenReward          = TokenCard.findViewById(R.id.dash_token_reward);
        mBtnSendToken           = TokenCard.findViewById(R.id.btn_token_send);

        mHistoryCnt             = TokenCard.findViewById(R.id.token_cnt);
        mSwipeRefreshLayout     = findViewById(R.id.layer_refresher);
        mRecyclerView           = findViewById(R.id.recycler);
        mEmptyHistory           = findViewById(R.id.empty_history);

        mBalance = getIntent().getParcelableExtra("balance");
        mIrisToken = getIntent().getParcelableExtra("irisToken");
        mBnbToken = getIntent().getParcelableExtra("bnbToken");

        mAllValidators = getIntent().getParcelableArrayListExtra("allValidators");
        mIrisReward = getIntent().getParcelableExtra("irisreward");



    }

    @Override
    protected void onResume() {
        super.onResume();

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);

        if (mBaseChain.equals(BaseChain.COSMOS_MAIN) && mBalance.symbol.equals(COSMOS_ATOM)) {
            mAtomCard.setVisibility(View.VISIBLE);
            mAtomAction.setVisibility(View.GONE);
            mBtnSendAtom.setVisibility(View.VISIBLE);

        } else if (mBaseChain.equals(BaseChain.IRIS_MAIN) && mBalance.symbol.equals(COSMOS_IRIS_ATTO)) {
            mIrisCard.setVisibility(View.VISIBLE);
            mIrisAction.setVisibility(View.GONE);
            mBtnSendIris.setVisibility(View.VISIBLE);
            mBtnSendIris.setOnClickListener(this);

            mBalances = getBaseDao().onSelectBalance(mAccount.id);
            mBondings = getBaseDao().onSelectBondingStates(mAccount.id);
            mUnbondings = getBaseDao().onSelectUnbondingStates(mAccount.id);

            mTvIrisTotal.setText(WDp.getDpAllIris(this, mBalances, mBondings, mUnbondings, mIrisReward, mBaseChain));
            mTvIrisUndelegated.setText(WDp.getDpBalanceCoin(this, mBalances, mBaseChain, COSMOS_IRIS_ATTO));
            mTvIrisDelegated.setText(WDp.getDpAllDelegatedAmount(this, mBondings, mAllValidators, mBaseChain));
            mTvIrisUnBonding.setText(WDp.getDpAllUnbondingAmount(this, mUnbondings, mAllValidators, mBaseChain));
            mTvIrisRewards.setText(WDp.getDpAllIrisRewardAmount(this, mIrisReward, mBaseChain));

            BigDecimal totalAmount = WDp.getAllIris(mBalances, mBondings, mUnbondings, mIrisReward);
            BigDecimal totalPrice = BigDecimal.ZERO;
            if(getBaseDao().getCurrency() != 5) {
                totalPrice = totalAmount.multiply(new BigDecimal(""+getBaseDao().getLastIrisTic())).movePointLeft(18).setScale(2, RoundingMode.DOWN);
            } else {
                totalPrice = totalAmount.multiply(new BigDecimal(""+getBaseDao().getLastIrisTic())).movePointLeft(18).setScale(8, RoundingMode.DOWN);
            }
            mTvIrisValue.setText(WDp.getPriceDp(this, totalPrice, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));

        } else if (mBaseChain.equals(BaseChain.BNB_MAIN) && mBalance.symbol.equals(COSMOS_BNB)) {
            mBnbCard.setVisibility(View.VISIBLE);
            mBnbAction.setVisibility(View.GONE);
            mBtnSendBnb.setVisibility(View.VISIBLE);
            mBtnSendBnb.setOnClickListener(this);

            mBalances = getBaseDao().onSelectBalance(mAccount.id);
            Balance bnbToken = WUtil.getTokenBalance(mBalances, COSMOS_BNB);
            if (bnbToken != null) {
                mTvBnbBalance.setText(WDp.getDpAmount(this, bnbToken.balance, 6, mBaseChain));
                mTvBnbLocked.setText(WDp.getDpAmount(this, bnbToken.locked, 6, mBaseChain));
                mTvBnbTotal.setText(WDp.getDpAmount(this, bnbToken.locked.add(bnbToken.balance), 6, mBaseChain));
                BigDecimal totalAmount = bnbToken.locked.add(bnbToken.balance);
                BigDecimal totalPrice = BigDecimal.ZERO;
                if(getBaseDao().getCurrency() != 5) {
                    totalPrice = totalAmount.multiply(new BigDecimal(""+getBaseDao().getLastBnbTic())).setScale(2, RoundingMode.DOWN);
                } else {
                    totalPrice = totalAmount.multiply(new BigDecimal(""+getBaseDao().getLastBnbTic())).setScale(8, RoundingMode.DOWN);
                }
                mTvBnbValue.setText(WDp.getPriceDp(this, totalPrice, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));

            } else {
                mTvBnbBalance.setText(WDp.getDpAmount(this, BigDecimal.ZERO, 6, mBaseChain));
                mTvBnbLocked.setText(WDp.getDpAmount(this, BigDecimal.ZERO, 6, mBaseChain));
                mTvBnbTotal.setText(WDp.getDpAmount(this, BigDecimal.ZERO.add(bnbToken.balance), 6, mBaseChain));
                mTvBnbValue.setText(WDp.getPriceDp(this, BigDecimal.ZERO, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));
            }

        } else {
            TokenCard.setVisibility(View.VISIBLE);
            mBtnSendToken.setVisibility(View.VISIBLE);

        }
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mBtnSendAtom)) {

        } else if (v.equals(mBtnSendIris)) {

        } else if (v.equals(mBtnSendBnb)) {

        } else if (v.equals(mBtnSendToken)) {

        }

    }
}
