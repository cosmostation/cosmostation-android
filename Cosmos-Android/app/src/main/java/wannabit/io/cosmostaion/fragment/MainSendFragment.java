package wannabit.io.cosmostaion.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.gun0912.tedpermission.PermissionListener;
import com.gun0912.tedpermission.TedPermission;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Locale;

import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.KavaCdpListActivity;
import wannabit.io.cosmostaion.activities.MainActivity;
import wannabit.io.cosmostaion.activities.PasswordCheckActivity;
import wannabit.io.cosmostaion.activities.ValidatorListActivity;
import wannabit.io.cosmostaion.activities.VoteListActivity;
import wannabit.io.cosmostaion.activities.WalletConnectActivity;
import wannabit.io.cosmostaion.activities.WebActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.base.BaseFragment;
import wannabit.io.cosmostaion.dao.Balance;
import wannabit.io.cosmostaion.dao.UnBondingState;
import wannabit.io.cosmostaion.dialog.Dialog_AccountShow;
import wannabit.io.cosmostaion.dialog.Dialog_WalletConnect;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_ATOM;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_BNB;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_IOV;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_IRIS_ATTO;
import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.SUPPORT_MOONPAY;


public class MainSendFragment extends BaseFragment implements View.OnClickListener {
    public final static int WALLET_CONNECT = 6013;


    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private NestedScrollView    mNestedScrollView;
    private ImageView           mBtnWebDetail, mBtnAddressDetail;
    private ImageView           mKeyState;
    private TextView            mAddress;

    private CardView            mAtomCard, mIrisCard, mBnbCard, mKavaCard, mIovCard;

    private TextView            mTvAtomTotal, mTvAtomValue, mTvAtomAvailable,
                                mTvAtomDelegated, mTvAtomUnBonding, mTvAtomRewards;
    private RelativeLayout      mBtnAtomReward, mBtnAtomVote;
    private TextView            mTvIrisTotal, mTvIrisValue, mTvIrisAvailable,
                                mTvIrisDelegated, mTvIrisUnBonding, mTvIrisRewards;
    private RelativeLayout      mBtnIrisReward, mBtnIrisVote;
    private TextView            mTvBnbTotal, mTvBnbValue, mTvBnbBalance, mTvBnbLocked;
    private RelativeLayout      mBtnBnbConnect, mBtnBep3Send;
    private TextView            mTvKavaTotal, mTvKavaValue, mTvKavaAvailable, mTvKavaVesting,
                                mTvKavaDelegated, mTvKavaUnBonding, mTvKavaRewards;
    private RelativeLayout      mBtnKavaReward, mBtnKavaVote, mBtnKavaCdp;
    private TextView            mTvIovTotal, mTvIovValue, mTvIovAvailable, mTvIovDeposited, mTvIovRewards;
    private RelativeLayout      mBtnIovDeposit, mBtnIovNameService;

    private CardView            mUndelegateCard;
    private TextView            mUndelegateCnt;
    private RelativeLayout      mUndelegateLayer0, mUndelegateLayer1, mUndelegateLayer2, mUndelegateLayer3, mUndelegateLayer4;
    private TextView            mUndelegateMoniker0, mUndelegateMoniker1, mUndelegateMoniker2, mUndelegateMoniker3, mUndelegateMoniker4;
    private TextView            mUndelegateAmount0, mUndelegateAmount1, mUndelegateAmount2, mUndelegateAmount3, mUndelegateAmount4;
    private TextView            mUndelegateTime0, mUndelegateTime1, mUndelegateTime2, mUndelegateTime3, mUndelegateTime4;

    private CardView            mKavaIncentiveCard;
    private RelativeLayout      mBtnParticipate;
    private TextView            mParticipateDone;

    private RelativeLayout      mPriceLayer;
    private TextView            mMarket;
    private TextView            mPerPrice, mUpDownPrice;
    private ImageView           mUpDownImg;
    private LinearLayout        mBuyLayer;
    private RelativeLayout      mBuyCoinBtn;
    private TextView            mBuyCoinTv;

    private LinearLayout        mMintCards;
    private TextView            mInflation, mYield;

    private ImageView           mGuideImg;
    private TextView            mGuideTitle, mGuideMsg;
    private LinearLayout        mGuideAction;
    private Button              mGuideBtn, mFaqBtn;


    public static MainSendFragment newInstance(Bundle bundle) {
        MainSendFragment fragment = new MainSendFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main_send, container, false);
        mSwipeRefreshLayout     = rootView.findViewById(R.id.layer_refresher);
        mNestedScrollView       = rootView.findViewById(R.id.layer_scrollview);
        mBtnWebDetail           = rootView.findViewById(R.id.web_detail);
        mBtnAddressDetail       = rootView.findViewById(R.id.address_detail);
        mKeyState               = rootView.findViewById(R.id.img_account);
        mAddress                = rootView.findViewById(R.id.account_Address);

        mAtomCard               = rootView.findViewById(R.id.card_atom);
        mTvAtomTotal            = mAtomCard.findViewById(R.id.dash_atom_amount);
        mTvAtomValue            = mAtomCard.findViewById(R.id.dash_atom_value);
        mTvAtomAvailable        = mAtomCard.findViewById(R.id.dash_atom_undelegate);
        mTvAtomDelegated        = mAtomCard.findViewById(R.id.dash_atom_delegate);
        mTvAtomUnBonding        = mAtomCard.findViewById(R.id.dash_atom_unbonding);
        mTvAtomRewards          = mAtomCard.findViewById(R.id.dash_atom_reward);
        mBtnAtomReward          = mAtomCard.findViewById(R.id.btn_cosmos_reward);
        mBtnAtomVote            = mAtomCard.findViewById(R.id.btn_cosmos_vote);

        mIrisCard               = rootView.findViewById(R.id.card_iris);
        mTvIrisTotal            = mIrisCard.findViewById(R.id.dash_iris_amount);
        mTvIrisValue            = mIrisCard.findViewById(R.id.dash_iris_value);
        mTvIrisAvailable        = mIrisCard.findViewById(R.id.dash_iris_undelegate);
        mTvIrisDelegated        = mIrisCard.findViewById(R.id.dash_iris_delegate);
        mTvIrisUnBonding        = mIrisCard.findViewById(R.id.dash_iris_unbonding);
        mTvIrisRewards          = mIrisCard.findViewById(R.id.dash_iris_reward);
        mBtnIrisReward          = mIrisCard.findViewById(R.id.btn_iris_reward);
        mBtnIrisVote            = mIrisCard.findViewById(R.id.btn_iris_vote);

        mBnbCard                = rootView.findViewById(R.id.card_bnb);
        mTvBnbTotal             = mBnbCard.findViewById(R.id.dash_bnb_amount);
        mTvBnbValue             = mBnbCard.findViewById(R.id.dash_bnb_value);
        mTvBnbBalance           = mBnbCard.findViewById(R.id.dash_bnb_balance);
        mTvBnbLocked            = mBnbCard.findViewById(R.id.dash_bnb_locked);
        mBtnBnbConnect          = mBnbCard.findViewById(R.id.btn_wallet_connect);
        mBtnBep3Send            = mBnbCard.findViewById(R.id.btn_bep3_send);

        mKavaCard               = rootView.findViewById(R.id.card_kava);
        mTvKavaTotal            = mKavaCard.findViewById(R.id.dash_kava_amount);
        mTvKavaValue            = mKavaCard.findViewById(R.id.dash_kava_value);
        mTvKavaAvailable        = mKavaCard.findViewById(R.id.dash_kava_undelegate);
        mTvKavaVesting          = mKavaCard.findViewById(R.id.dash_kava_vesting);
        mTvKavaDelegated        = mKavaCard.findViewById(R.id.dash_kava_delegate);
        mTvKavaUnBonding        = mKavaCard.findViewById(R.id.dash_kava_unbonding);
        mTvKavaRewards          = mKavaCard.findViewById(R.id.dash_kava_reward);
        mBtnKavaReward          = mKavaCard.findViewById(R.id.btn_kava_reward);
        mBtnKavaVote            = mKavaCard.findViewById(R.id.btn_kava_vote);
        mBtnKavaCdp             = mKavaCard.findViewById(R.id.btn_kava_cdp);

        mIovCard                = rootView.findViewById(R.id.card_iov);
        mTvIovTotal             = mIovCard.findViewById(R.id.dash_iov_amount);
        mTvIovValue             = mIovCard.findViewById(R.id.dash_iov_value);
        mTvIovAvailable         = mIovCard.findViewById(R.id.dash_iov_balance);
        mTvIovDeposited         = mIovCard.findViewById(R.id.dash_iov_deposited);
        mTvIovRewards           = mIovCard.findViewById(R.id.dash_atom_reward);
        mBtnIovDeposit          = mIovCard.findViewById(R.id.btn_iov_deposit);
        mBtnIovNameService      = mIovCard.findViewById(R.id.btn_iov_name_service);

        mUndelegateCard         = rootView.findViewById(R.id.card_undelegate);
        mUndelegateCnt          = mUndelegateCard.findViewById(R.id.undelegate_count);
        mUndelegateLayer0       = mUndelegateCard.findViewById(R.id.undelegate_detail_layer0);
        mUndelegateLayer1       = mUndelegateCard.findViewById(R.id.undelegate_detail_layer1);
        mUndelegateLayer2       = mUndelegateCard.findViewById(R.id.undelegate_detail_layer2);
        mUndelegateLayer3       = mUndelegateCard.findViewById(R.id.undelegate_detail_layer3);
        mUndelegateLayer4       = mUndelegateCard.findViewById(R.id.undelegate_detail_layer4);
        mUndelegateMoniker0     = mUndelegateCard.findViewById(R.id.undelegate_detail_moniker0);
        mUndelegateMoniker1     = mUndelegateCard.findViewById(R.id.undelegate_detail_moniker1);
        mUndelegateMoniker2     = mUndelegateCard.findViewById(R.id.undelegate_detail_moniker2);
        mUndelegateMoniker3     = mUndelegateCard.findViewById(R.id.undelegate_detail_moniker3);
        mUndelegateMoniker4     = mUndelegateCard.findViewById(R.id.undelegate_detail_moniker4);
        mUndelegateAmount0      = mUndelegateCard.findViewById(R.id.undelegate_detail_amount0);
        mUndelegateAmount1      = mUndelegateCard.findViewById(R.id.undelegate_detail_amount1);
        mUndelegateAmount2      = mUndelegateCard.findViewById(R.id.undelegate_detail_amount2);
        mUndelegateAmount3      = mUndelegateCard.findViewById(R.id.undelegate_detail_amount3);
        mUndelegateAmount4      = mUndelegateCard.findViewById(R.id.undelegate_detail_amount4);
        mUndelegateTime0        = mUndelegateCard.findViewById(R.id.undelegate_detail_time0);
        mUndelegateTime1        = mUndelegateCard.findViewById(R.id.undelegate_detail_time1);
        mUndelegateTime2        = mUndelegateCard.findViewById(R.id.undelegate_detail_time2);
        mUndelegateTime3        = mUndelegateCard.findViewById(R.id.undelegate_detail_time3);
        mUndelegateTime4        = mUndelegateCard.findViewById(R.id.undelegate_detail_time4);

        mKavaIncentiveCard      = rootView.findViewById(R.id.card_kava_incentive);
        mBtnParticipate         = mKavaIncentiveCard.findViewById(R.id.btn_kava_incentive);
        mParticipateDone        = mKavaIncentiveCard.findViewById(R.id.kava_incentive_done);

        mPriceLayer             = rootView.findViewById(R.id.price_layer);
        mMarket                 = rootView.findViewById(R.id.dash_price_market);
        mPerPrice               = rootView.findViewById(R.id.dash_per_price);
        mUpDownPrice            = rootView.findViewById(R.id.dash_price_updown_tx);
        mUpDownImg              = rootView.findViewById(R.id.ic_price_updown);
        mBuyLayer               = rootView.findViewById(R.id.buy_layer);
        mBuyCoinBtn             = rootView.findViewById(R.id.btn_buy_coin);
        mBuyCoinTv              = rootView.findViewById(R.id.tv_buy_coin);

        mMintCards              = rootView.findViewById(R.id.cards_mint);
        mInflation              = rootView.findViewById(R.id.dash_inflation);
        mYield                  = rootView.findViewById(R.id.dash_yield);

        mGuideImg               = rootView.findViewById(R.id.img_guide);
        mGuideTitle             = rootView.findViewById(R.id.title_guide);
        mGuideMsg               = rootView.findViewById(R.id.msg_guide);
        mGuideAction            = rootView.findViewById(R.id.action_guide);
        mGuideBtn               = rootView.findViewById(R.id.btn_guide);
        mFaqBtn                 = rootView.findViewById(R.id.btn_faq);

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                getMainActivity().onFetchAllData();
            }
        });

        mNestedScrollView.setOnScrollChangeListener(new View.OnScrollChangeListener() {
            @Override
            public void onScrollChange(View v, int scrollX, int scrollY, int oldScrollX, int oldScrollY) {
                if (scrollY > oldScrollY) {
                    if (getMainActivity().mFloatBtn.isShown()) {getMainActivity().mFloatBtn.hide();
                    }
                }
                if (scrollY < oldScrollY) {
                    if (!getMainActivity().mFloatBtn.isShown()) {
                        getMainActivity().mFloatBtn.show();
                    }
                }
            }
        });

        mBtnWebDetail.setOnClickListener(this);
        mBtnAddressDetail.setOnClickListener(this);
        mGuideBtn.setOnClickListener(this);
        mFaqBtn.setOnClickListener(this);
        mPriceLayer.setOnClickListener(this);
        mBtnAtomReward.setOnClickListener(this);
        mBtnAtomVote.setOnClickListener(this);
        mBtnIrisReward.setOnClickListener(this);
        mBtnIrisVote.setOnClickListener(this);
        mBtnKavaReward.setOnClickListener(this);
        mBtnKavaVote.setOnClickListener(this);
        mBtnKavaCdp.setOnClickListener(this);
        mBtnBnbConnect.setOnClickListener(this);
        mBtnBep3Send.setOnClickListener(this);
        mBuyCoinBtn.setOnClickListener(this);
        mBtnIovDeposit.setOnClickListener(this);
        mBtnIovNameService.setOnClickListener(this);
        mBtnParticipate.setOnClickListener(this);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
        onUpdateView();
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        if (getMainActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            if (getMainActivity().mAccount.pushAlarm) {
                getMainActivity().getMenuInflater().inflate(R.menu.main_menu_alaram_on, menu);
            } else {
                getMainActivity().getMenuInflater().inflate(R.menu.main_menu_alaram_off, menu);
            }
        } else {
            getMainActivity().getMenuInflater().inflate(R.menu.main_menu, menu);
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch(item.getItemId()) {
            case R.id.menu_accounts :
                getMainActivity().onShowTopAccountsView();
                break;
            case R.id.menu_notification_off:
                getMainActivity().onUpdateUserAlarm(getMainActivity().mAccount, true);
                break;
            case R.id.menu_notification_on:
                getMainActivity().onUpdateUserAlarm(getMainActivity().mAccount, false);
                break;

        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onRefreshTab() {
        if(!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
        onUpdateView();
    }

    @Override
    public void onBusyFetch() {
        if(!isAdded()) return;
        mSwipeRefreshLayout.setRefreshing(false);
    }

    private void onUpdateView() {
        if (getMainActivity() == null || getMainActivity().mAccount == null) return;

        mAddress.setText(getMainActivity().mAccount.address);
        mKeyState.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorGray0), android.graphics.PorterDuff.Mode.SRC_IN);

        if (getMainActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            mAtomCard.setVisibility(View.VISIBLE);
            mIrisCard.setVisibility(View.GONE);
            mBnbCard.setVisibility(View.GONE);
            mKavaCard.setVisibility(View.GONE);
            mIovCard.setVisibility(View.GONE);
            mUndelegateCard.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg2));
            mMintCards.setVisibility(View.VISIBLE);
            if (SUPPORT_MOONPAY) {
                mBuyLayer.setVisibility(View.VISIBLE);
                mBuyCoinTv.setText(R.string.str_buy_atom);
            }

            if (getMainActivity().mAccount.hasPrivateKey) {
                mKeyState.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorAtom), android.graphics.PorterDuff.Mode.SRC_IN);
            }
            mGuideImg.setImageDrawable(getResources().getDrawable(R.drawable.guide_img));
            mGuideTitle.setText(R.string.str_front_guide_title);
            mGuideMsg.setText(R.string.str_front_guide_msg);
            mGuideBtn.setText(R.string.str_guide);
            mFaqBtn.setText(R.string.str_faq);

        } else if (getMainActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            mAtomCard.setVisibility(View.GONE);
            mIrisCard.setVisibility(View.VISIBLE);
            mBnbCard.setVisibility(View.GONE);
            mKavaCard.setVisibility(View.GONE);
            mIovCard.setVisibility(View.GONE);
            mUndelegateCard.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg4));
            mMintCards.setVisibility(View.VISIBLE);
            mBuyLayer.setVisibility(View.GONE);
            if (getMainActivity().mAccount.hasPrivateKey) {
                mKeyState.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorIris), android.graphics.PorterDuff.Mode.SRC_IN);
            }
            mGuideImg.setImageDrawable(getResources().getDrawable(R.drawable.irisnet_img));
            mGuideTitle.setText(R.string.str_front_guide_title_iris);
            mGuideMsg.setText(R.string.str_front_guide_msg_iris);
            mGuideBtn.setText(R.string.str_faq_iris);
            mFaqBtn.setText(R.string.str_guide_iris);

        } else if (getMainActivity().mBaseChain.equals(BaseChain.BNB_MAIN) || getMainActivity().mBaseChain.equals(BaseChain.BNB_TEST)) {
            mAtomCard.setVisibility(View.GONE);
            mIrisCard.setVisibility(View.GONE);
            mBnbCard.setVisibility(View.VISIBLE);
            mKavaCard.setVisibility(View.GONE);
            mIovCard.setVisibility(View.GONE);
            mMintCards.setVisibility(View.GONE);
            if (getMainActivity().mBaseChain.equals(BaseChain.BNB_TEST)) {
                mBtnBep3Send.setVisibility(View.VISIBLE);
                mBnbCard.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            } else {
                mBtnBep3Send.setVisibility(View.GONE);
                mBnbCard.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg5));
            }
            if (SUPPORT_MOONPAY && getMainActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
                mBuyLayer.setVisibility(View.VISIBLE);
                mBuyCoinTv.setText(R.string.str_buy_bnb);
            } else {
                mBuyLayer.setVisibility(View.GONE);
            }
            if (getMainActivity().mAccount.hasPrivateKey) {
                mKeyState.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorBnb), android.graphics.PorterDuff.Mode.SRC_IN);
            }
            mGuideImg.setImageDrawable(getResources().getDrawable(R.drawable.binance_img));
            mGuideTitle.setText(R.string.str_front_guide_title_binance);
            mGuideMsg.setText(R.string.str_front_guide_msg_bnb);
            mGuideBtn.setText(R.string.str_faq_bnb);
            mFaqBtn.setText(R.string.str_guide_bnb);

        } else if (getMainActivity().mBaseChain.equals(BaseChain.KAVA_MAIN) || getMainActivity().mBaseChain.equals(BaseChain.KAVA_TEST)) {
            mAtomCard.setVisibility(View.GONE);
            mIrisCard.setVisibility(View.GONE);
            mBnbCard.setVisibility(View.GONE);
            mKavaCard.setVisibility(View.VISIBLE);
            mIovCard.setVisibility(View.GONE);
            mMintCards.setVisibility(View.VISIBLE);
            mUndelegateCard.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg7));
            if (getMainActivity().mBaseChain.equals(BaseChain.KAVA_TEST)) {
                mKavaCard.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg));
            } else {
                mKavaCard.setCardBackgroundColor(getResources().getColor(R.color.colorTransBg7));
            }
            if (SUPPORT_MOONPAY && getMainActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)) {
                mBuyLayer.setVisibility(View.VISIBLE);
                mBuyCoinTv.setText(R.string.str_buy_kava);
            } else {
                mBuyLayer.setVisibility(View.GONE);
            }
            if (getMainActivity().mAccount.hasPrivateKey) {
                mKeyState.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorKava), android.graphics.PorterDuff.Mode.SRC_IN);
            }
            mGuideImg.setImageDrawable(getResources().getDrawable(R.drawable.kavamain_img));
            mGuideTitle.setText(R.string.str_front_guide_title_kava);
            mGuideMsg.setText(R.string.str_front_guide_msg_kava);
            mGuideBtn.setText(R.string.str_faq_kava);
            mFaqBtn.setText(R.string.str_guide_kava);

        } else if (getMainActivity().mBaseChain.equals(BaseChain.IOV_MAIN)) {
            mAtomCard.setVisibility(View.GONE);
            mIrisCard.setVisibility(View.GONE);
            mBnbCard.setVisibility(View.GONE);
            mKavaCard.setVisibility(View.GONE);
            mIovCard.setVisibility(View.VISIBLE);
            mMintCards.setVisibility(View.GONE);
            mBuyLayer.setVisibility(View.GONE);
            if (getMainActivity().mAccount.hasPrivateKey) {
                mKeyState.setColorFilter(ContextCompat.getColor(getContext(), R.color.colorIov), android.graphics.PorterDuff.Mode.SRC_IN);
            }
            mGuideImg.setImageDrawable(getResources().getDrawable(R.drawable.iovmain_img));
            mGuideTitle.setText(R.string.str_front_guide_title_iov);
            mGuideMsg.setText(R.string.str_front_guide_msg_iov);
            mGuideBtn.setText(R.string.str_faq_iov);
            mFaqBtn.setText(R.string.str_guide_iov);
        }

        mMarket.setText("("+getBaseDao().getMarketString(getContext())+")");

        if (getMainActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            BigDecimal totalAmount = WDp.getAllAtom(getMainActivity().mBalances, getMainActivity().mBondings, getMainActivity().mUnbondings, getMainActivity().mRewards, getMainActivity().mAllValidators);
            mTvAtomTotal.setText(WDp.getDpAmount(getContext(), totalAmount, 6, getMainActivity().mBaseChain));
            mTvAtomAvailable.setText(WDp.getDpAvailableCoin(getContext(), getMainActivity().mBalances, getMainActivity().mBaseChain, COSMOS_ATOM));
            mTvAtomDelegated.setText(WDp.getDpAllDelegatedAmount(getContext(), getMainActivity().mBondings, getMainActivity().mAllValidators, getMainActivity().mBaseChain));
            mTvAtomUnBonding.setText(WDp.getDpAllUnbondingAmount(getContext(), getMainActivity().mUnbondings, getMainActivity().mAllValidators, getMainActivity().mBaseChain));
            mTvAtomRewards.setText(WDp.getDpAllRewardAmount(getContext(), getMainActivity().mRewards, getMainActivity().mBaseChain, COSMOS_ATOM));
            mTvAtomValue.setText(WDp.getValueOfAtom(getContext(), getBaseDao(), totalAmount));
            getBaseDao().onUpdateLastTotalAccount(getMainActivity().mAccount, totalAmount.toPlainString());

            try {
                mPerPrice.setText(WDp.getPriceDp(getContext(), new BigDecimal(""+getBaseDao().getLastAtomTic()), getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));
                mUpDownPrice.setText(WDp.getPriceUpDown(new BigDecimal(""+getBaseDao().getLastAtomUpDown())));
                if(getBaseDao().getLastAtomUpDown() > 0) {
                    mUpDownImg.setVisibility(View.VISIBLE);
                    mUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_up));
                } else if (getBaseDao().getLastAtomUpDown() < 0){
                    mUpDownImg.setVisibility(View.VISIBLE);
                    mUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_down));
                } else {
                    mUpDownImg.setVisibility(View.GONE);
                }

                mInflation.setText(WDp.getPercentDp(getMainActivity().mInflation.multiply(new BigDecimal("100"))));
                mYield.setText(WDp.getYieldString(getMainActivity().mBondedToken, getMainActivity().mProvisions, BigDecimal.ZERO));

            } catch (Exception e) {
                mTvAtomValue.setText("???");
                mPerPrice.setText("???");
                mUpDownPrice.setText("???");
                mUpDownImg.setVisibility(View.GONE);
            }

        } else if (getMainActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
            BigDecimal totalAmount = WDp.getAllIris(getMainActivity().mBalances, getMainActivity().mBondings, getMainActivity().mUnbondings, getMainActivity().mIrisReward, getMainActivity().mAllValidators);
            mTvIrisTotal.setText(WDp.getDpAllIris(getContext(), getMainActivity().mBalances, getMainActivity().mBondings, getMainActivity().mUnbondings, getMainActivity().mIrisReward, getMainActivity().mAllValidators, getMainActivity().mBaseChain));
            mTvIrisAvailable.setText(WDp.getDpAvailableCoin(getContext(), getMainActivity().mBalances, getMainActivity().mBaseChain, COSMOS_IRIS_ATTO));
            mTvIrisDelegated.setText(WDp.getDpAllDelegatedAmount(getContext(), getMainActivity().mBondings, getMainActivity().mAllValidators, getMainActivity().mBaseChain));
            mTvIrisUnBonding.setText(WDp.getDpAllUnbondingAmount(getContext(), getMainActivity().mUnbondings, getMainActivity().mAllValidators, getMainActivity().mBaseChain));
            mTvIrisRewards.setText(WDp.getDpAllIrisRewardAmount(getContext(), getMainActivity().mIrisReward, getMainActivity().mBaseChain));
            mTvIrisValue.setText(WDp.getValueOfIris(getContext(), getBaseDao(), totalAmount));
            getBaseDao().onUpdateLastTotalAccount(getMainActivity().mAccount, totalAmount.toPlainString());

            try {
                mPerPrice.setText(WDp.getPriceDp(getContext(), new BigDecimal(""+getBaseDao().getLastIrisTic()), getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));
                mUpDownPrice.setText(WDp.getPriceUpDown(new BigDecimal(""+getBaseDao().getLastIrisUpDown())));
                if(getBaseDao().getLastIrisUpDown() > 0) {
                    mUpDownImg.setVisibility(View.VISIBLE);
                    mUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_up));
                } else if (getBaseDao().getLastIrisUpDown() < 0){
                    mUpDownImg.setVisibility(View.VISIBLE);
                    mUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_down));
                } else {
                    mUpDownImg.setVisibility(View.GONE);
                }

                mInflation.setText(WDp.getPercentDp(new BigDecimal("4")));
                mYield.setText(WDp.getIrisYieldString(getMainActivity().mIrisPool, BigDecimal.ZERO));

            } catch (Exception e) {
                mTvIrisValue.setText("???");
                mPerPrice.setText("???");
                mUpDownPrice.setText("???");
                mUpDownImg.setVisibility(View.GONE);
            }

        } else if (getMainActivity().mBaseChain.equals(BaseChain.BNB_MAIN) || getMainActivity().mBaseChain.equals(BaseChain.BNB_TEST)) {
            try {
                if (getMainActivity().mBalances != null && WUtil.getTokenBalance(getMainActivity().mBalances, COSMOS_BNB) != null) {
                    Balance bnbToken = WUtil.getTokenBalance(getMainActivity().mBalances, COSMOS_BNB);
                    BigDecimal totalAmount = bnbToken.locked.add(bnbToken.balance);
                    mTvBnbBalance.setText(WDp.getDpAmount(getContext(), bnbToken.balance, 6, getMainActivity().mBaseChain));
                    mTvBnbLocked.setText(WDp.getDpAmount(getContext(), bnbToken.locked, 6, getMainActivity().mBaseChain));
                    mTvBnbTotal.setText(WDp.getDpAmount(getContext(), bnbToken.locked.add(bnbToken.balance), 6, getMainActivity().mBaseChain));
                    mTvBnbValue.setText(WDp.getValueOfBnb(getContext(), getBaseDao(), totalAmount));
                    getBaseDao().onUpdateLastTotalAccount(getMainActivity().mAccount, bnbToken.locked.add(bnbToken.balance).toPlainString());

                } else {
                    mTvBnbBalance.setText(WDp.getDpAmount(getContext(), BigDecimal.ZERO, 6, getMainActivity().mBaseChain));
                    mTvBnbLocked.setText(WDp.getDpAmount(getContext(), BigDecimal.ZERO, 6, getMainActivity().mBaseChain));
                    mTvBnbTotal.setText(WDp.getDpAmount(getContext(), BigDecimal.ZERO, 6, getMainActivity().mBaseChain));
                    mTvBnbValue.setText(WDp.getValueOfBnb(getContext(), getBaseDao(), BigDecimal.ZERO));
                    getBaseDao().onUpdateLastTotalAccount(getMainActivity().mAccount, BigDecimal.ZERO.toPlainString());
                }

                mPerPrice.setText(WDp.getPriceDp(getContext(), new BigDecimal(""+getBaseDao().getLastBnbTic()), getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));
                mUpDownPrice.setText(WDp.getPriceUpDown(new BigDecimal(""+getBaseDao().getLastBnbUpDown())));
                if(getBaseDao().getLastBnbUpDown() > 0) {
                    mUpDownImg.setVisibility(View.VISIBLE);
                    mUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_up));
                } else if (getBaseDao().getLastBnbUpDown() < 0){
                    mUpDownImg.setVisibility(View.VISIBLE);
                    mUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_down));
                } else {
                    mUpDownImg.setVisibility(View.GONE);
                }

            } catch (Exception e) {
                mTvIrisValue.setText("???");
                mPerPrice.setText("???");
                mUpDownPrice.setText("???");
                mUpDownImg.setVisibility(View.GONE);
            }

        } else if (getMainActivity().mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            BigDecimal totalAmount = WDp.getAllKava(getMainActivity().mBalances, getMainActivity().mBondings, getMainActivity().mUnbondings, getMainActivity().mRewards, getMainActivity().mAllValidators);
            mTvKavaTotal.setText(WDp.getDpAmount(getContext(), totalAmount, 6, getMainActivity().mBaseChain));
            mTvKavaAvailable.setText(WDp.getDpAvailableCoin(getContext(), getMainActivity().mBalances, getMainActivity().mBaseChain, COSMOS_KAVA));
            mTvKavaVesting.setText(WDp.getDpVestedCoin(getContext(), getMainActivity().mBalances, getMainActivity().mBaseChain, COSMOS_KAVA));
            mTvKavaDelegated.setText(WDp.getDpAllDelegatedAmount(getContext(), getMainActivity().mBondings, getMainActivity().mAllValidators, getMainActivity().mBaseChain));
            mTvKavaUnBonding.setText(WDp.getDpAllUnbondingAmount(getContext(), getMainActivity().mUnbondings, getMainActivity().mAllValidators, getMainActivity().mBaseChain));
            mTvKavaRewards.setText(WDp.getDpAllRewardAmount(getContext(), getMainActivity().mRewards, getMainActivity().mBaseChain, COSMOS_KAVA));
            mTvKavaValue.setText(WDp.getValueOfKava(getContext(), getBaseDao(), totalAmount));
            getBaseDao().onUpdateLastTotalAccount(getMainActivity().mAccount, totalAmount.toPlainString());
            mBtnKavaCdp.setVisibility(View.GONE);

            try {
                mPerPrice.setText(WDp.getPriceDp(getContext(), new BigDecimal(""+getBaseDao().getLastKavaTic()), getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));
                mUpDownPrice.setText(WDp.getPriceUpDown(new BigDecimal(""+getBaseDao().getLastKavaUpDown())));
                if(getBaseDao().getLastKavaUpDown() > 0) {
                    mUpDownImg.setVisibility(View.VISIBLE);
                    mUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_up));
                } else if (getBaseDao().getLastKavaUpDown() < 0){
                    mUpDownImg.setVisibility(View.VISIBLE);
                    mUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_down));
                } else {
                    mUpDownImg.setVisibility(View.GONE);
                }

                mInflation.setText(WDp.getPercentDp(getMainActivity().mInflation.multiply(new BigDecimal("100"))));
                mYield.setText(WDp.getYieldString(getMainActivity().mBondedToken, getMainActivity().mProvisions, BigDecimal.ZERO));

            } catch (Exception e) {
                mPerPrice.setText("???");
                mUpDownPrice.setText("???");
                mUpDownImg.setVisibility(View.GONE);
            }

        } else if (getMainActivity().mBaseChain.equals(BaseChain.KAVA_TEST)) {
            BigDecimal totalAmount = WDp.getAllTestKava(getMainActivity().mBalances, getMainActivity().mBondings, getMainActivity().mUnbondings, getMainActivity().mRewards, getMainActivity().mAllValidators);
            mTvKavaTotal.setText(WDp.getDpAmount(getContext(), totalAmount, 6, getMainActivity().mBaseChain));
            mTvKavaAvailable.setText(WDp.getDpAvailableCoin(getContext(), getMainActivity().mBalances, getMainActivity().mBaseChain, COSMOS_KAVA));
            mTvKavaVesting.setText(WDp.getDpVestedCoin(getContext(), getMainActivity().mBalances, getMainActivity().mBaseChain, COSMOS_KAVA));
            mTvKavaDelegated.setText(WDp.getDpAllDelegatedAmount(getContext(), getMainActivity().mBondings, getMainActivity().mAllValidators, getMainActivity().mBaseChain));
            mTvKavaUnBonding.setText(WDp.getDpAllUnbondingAmount(getContext(), getMainActivity().mUnbondings, getMainActivity().mAllValidators, getMainActivity().mBaseChain));
            mTvKavaRewards.setText(WDp.getDpAllRewardAmount(getContext(), getMainActivity().mRewards, getMainActivity().mBaseChain, COSMOS_KAVA));
            mTvKavaValue.setText(WDp.getValueOfKava(getContext(), getBaseDao(), totalAmount));
            getBaseDao().onUpdateLastTotalAccount(getMainActivity().mAccount, totalAmount.toPlainString());
            mBtnKavaCdp.setVisibility(View.VISIBLE);

            try {
                mPerPrice.setText(WDp.getPriceDp(getContext(), new BigDecimal(""+getBaseDao().getLastKavaTic()), getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));
                mUpDownPrice.setText(WDp.getPriceUpDown(new BigDecimal(""+getBaseDao().getLastKavaUpDown())));
                if(getBaseDao().getLastKavaUpDown() > 0) {
                    mUpDownImg.setVisibility(View.VISIBLE);
                    mUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_up));
                } else if (getBaseDao().getLastKavaUpDown() < 0){
                    mUpDownImg.setVisibility(View.VISIBLE);
                    mUpDownImg.setImageDrawable(getResources().getDrawable(R.drawable.ic_price_down));
                } else {
                    mUpDownImg.setVisibility(View.GONE);
                }

                mInflation.setText(WDp.getPercentDp(getMainActivity().mInflation.multiply(new BigDecimal("100"))));
                mYield.setText(WDp.getYieldString(getMainActivity().mBondedToken, getMainActivity().mProvisions, BigDecimal.ZERO));

            } catch (Exception e) {
                mPerPrice.setText("???");
                mUpDownPrice.setText("???");
                mUpDownImg.setVisibility(View.GONE);
            }

        } else if (getMainActivity().mBaseChain.equals(BaseChain.IOV_MAIN)) {
            if (getMainActivity().mBalances != null && WUtil.getTokenBalance(getMainActivity().mBalances, COSMOS_IOV) != null) {
                Balance iovToken = WUtil.getTokenBalance(getMainActivity().mBalances, COSMOS_IOV);
                mTvIovTotal.setText(WDp.getDpAmount2(getContext(), iovToken.balance, 9, 6));
                mTvIovAvailable.setText(WDp.getDpAmount2(getContext(), iovToken.balance, 9, 6));
                //TODO no price info yet
                mTvIovValue.setText(WDp.getZeroValue(getContext(), getBaseDao()));
                mTvIovDeposited.setText(WDp.getDpAmount2(getContext(), BigDecimal.ZERO, 9, 6));
                mTvIovRewards.setText(WDp.getDpAmount2(getContext(), BigDecimal.ZERO, 9, 6));
                getBaseDao().onUpdateLastTotalAccount(getMainActivity().mAccount, iovToken.balance.movePointLeft(9).toPlainString());
            } else {
                mTvIovTotal.setText(WDp.getDpAmount2(getContext(), BigDecimal.ZERO, 9, 6));
                mTvIovAvailable.setText(WDp.getDpAmount2(getContext(), BigDecimal.ZERO, 9, 6));
                mTvIovValue.setText(WDp.getZeroValue(getContext(), getBaseDao()));
                mTvIovDeposited.setText(WDp.getDpAmount2(getContext(), BigDecimal.ZERO, 9, 6));
                mTvIovRewards.setText(WDp.getDpAmount2(getContext(), BigDecimal.ZERO, 9, 6));
                getBaseDao().onUpdateLastTotalAccount(getMainActivity().mAccount, BigDecimal.ZERO.toPlainString());
            }

            try {
                mPerPrice.setText(WDp.getPriceDp(getContext(), BigDecimal.ZERO, getBaseDao().getCurrencySymbol(), getBaseDao().getCurrency()));
                mUpDownPrice.setText(WDp.getPriceUpDown(BigDecimal.ZERO));
                mUpDownImg.setVisibility(View.GONE);

            } catch (Exception e) {
                mPerPrice.setText("???");
                mUpDownPrice.setText("???");
                mUpDownImg.setVisibility(View.GONE);
            }
        }

        // Show Undelegate detail list at most 5 for ordered by completing time
        if (getMainActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
            mUndelegateLayer1.setVisibility(View.GONE);
            mUndelegateLayer2.setVisibility(View.GONE);
            mUndelegateLayer3.setVisibility(View.GONE);
            mUndelegateLayer4.setVisibility(View.GONE);
            if (getMainActivity().mUnbondings.size() > 0) {
                final ArrayList<UnBondingState> unbondings = getMainActivity().mUnbondings;
                WUtil.onSortUnbondingsRecent(unbondings);
                mUndelegateCard.setVisibility(View.VISIBLE);
                mUndelegateCnt.setText(String.valueOf(unbondings.size()));
                mUndelegateMoniker0.setText(WUtil.getMonikerName(unbondings.get(0).validatorAddress, getMainActivity().mAllValidators, false));
                mUndelegateAmount0.setText(WDp.getDpAmount2(getContext(), unbondings.get(0).balance, 6, 6));
                mUndelegateTime0.setText(WDp.getUnbondingTimeleft(getContext(), unbondings.get(0).completionTime));
                if (unbondings.size() > 1) {
                    mUndelegateLayer1.setVisibility(View.VISIBLE);
                    mUndelegateMoniker1.setText(WUtil.getMonikerName(unbondings.get(1).validatorAddress, getMainActivity().mAllValidators, false));
                    mUndelegateAmount1.setText(WDp.getDpAmount2(getContext(), unbondings.get(1).balance, 6, 6));
                    mUndelegateTime1.setText(WDp.getUnbondingTimeleft(getContext(), unbondings.get(1).completionTime));
                }
                if (unbondings.size() > 2) {
                    mUndelegateLayer2.setVisibility(View.VISIBLE);
                    mUndelegateMoniker2.setText(WUtil.getMonikerName(unbondings.get(2).validatorAddress, getMainActivity().mAllValidators, false));
                    mUndelegateAmount2.setText(WDp.getDpAmount2(getContext(), unbondings.get(2).balance, 6, 6));
                    mUndelegateTime2.setText(WDp.getUnbondingTimeleft(getContext(), unbondings.get(2).completionTime));
                }
                if (unbondings.size() > 3) {
                    mUndelegateLayer3.setVisibility(View.VISIBLE);
                    mUndelegateMoniker3.setText(WUtil.getMonikerName(unbondings.get(3).validatorAddress, getMainActivity().mAllValidators, false));
                    mUndelegateAmount3.setText(WDp.getDpAmount2(getContext(), unbondings.get(3).balance, 6, 6));
                    mUndelegateTime3.setText(WDp.getUnbondingTimeleft(getContext(), unbondings.get(3).completionTime));
                }
                if (unbondings.size() > 4) {
                    mUndelegateLayer4.setVisibility(View.VISIBLE);
                    mUndelegateMoniker4.setText(WUtil.getMonikerName(unbondings.get(4).validatorAddress, getMainActivity().mAllValidators, false));
                    mUndelegateAmount4.setText(WDp.getDpAmount2(getContext(), unbondings.get(4).balance, 6, 6));
                    mUndelegateTime4.setText(WDp.getUnbondingTimeleft(getContext(), unbondings.get(4).completionTime));
                }

            } else {
                mUndelegateCard.setVisibility(View.GONE);
            }

        } else {
            mUndelegateCard.setVisibility(View.GONE);
        }

        //Show Kava Incentive cards!!!
        if (getMainActivity().mBaseChain.equals(BaseChain.KAVA_TEST)) {
            mKavaIncentiveCard.setVisibility(View.VISIBLE);
        } else {
            mKavaIncentiveCard.setVisibility(View.GONE);
        }

        getMainActivity().onUpdateAccountListAdapter();
    }

    public MainActivity getMainActivity() {
        return (MainActivity)getBaseActivity();
    }

    @Override
    public void onClick(View v) {
        if(v.equals(mBtnAddressDetail)) {
            Bundle bundle = new Bundle();
            bundle.putString("address", getMainActivity().mAccount.address);
            if (TextUtils.isEmpty(getMainActivity().mAccount.nickName))
                bundle.putString("title", getString(R.string.str_my_wallet) + getMainActivity().mAccount.id);
            else
                bundle.putString("title", getMainActivity().mAccount.nickName);
            Dialog_AccountShow show = Dialog_AccountShow.newInstance(bundle);
            show.setCancelable(true);
            show.show(getFragmentManager().beginTransaction(), "dialog");

        } else if (v.equals(mBtnWebDetail)) {
            if (getMainActivity().mBaseChain.equals(BaseChain.KAVA_TEST)) { return; }
            Intent webintent = new Intent(getMainActivity(), WebActivity.class);
            webintent.putExtra("address", getMainActivity().mAccount.address);
            webintent.putExtra("chain", getMainActivity().mBaseChain.getChain());
            webintent.putExtra("goMain", false);
            startActivity(webintent);

        } else if (v.equals(mGuideBtn)) {
            if (getMainActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                if(Locale.getDefault().getLanguage().toLowerCase().equals("ko")) {
                    Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.cosmostation.io/files/cosmostation_guide_app_ko.pdf"));
                    startActivity(guideIntent);
                } else {
                    Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.cosmostation.io/files/cosmostation_guide_app_en.pdf"));
                    startActivity(guideIntent);
                }

            } else if (getMainActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.irisnet.org/"));
                startActivity(guideIntent);

            } else if (getMainActivity().mBaseChain.equals(BaseChain.BNB_MAIN) || getMainActivity().mBaseChain.equals(BaseChain.BNB_TEST)) {
                Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.binance.org"));
                startActivity(guideIntent);

            } else if (getMainActivity().mBaseChain.equals(BaseChain.KAVA_MAIN) || getMainActivity().mBaseChain.equals(BaseChain.KAVA_TEST)) {
                Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.kava.io/registration/"));
                startActivity(guideIntent);

            } else if (getMainActivity().mBaseChain.equals(BaseChain.IOV_MAIN)) {
                Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://iov.one/"));
                startActivity(guideIntent);

            }

        } else if (v.equals(mFaqBtn)) {
            if (getMainActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                if(Locale.getDefault().getLanguage().toLowerCase().equals("ko")) {
                    Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://guide.cosmostation.io/app_wallet_ko.html"));
                    startActivity(guideIntent);
                } else {
                    Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://guide.cosmostation.io/app_wallet_en.html"));
                    startActivity(guideIntent);
                }

            } else if (getMainActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/irisnet-blog"));
                startActivity(guideIntent);

            } else if (getMainActivity().mBaseChain.equals(BaseChain.BNB_MAIN) || getMainActivity().mBaseChain.equals(BaseChain.BNB_TEST)) {
                Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/@binance"));
                startActivity(guideIntent);

            } else if (getMainActivity().mBaseChain.equals(BaseChain.KAVA_MAIN) || getMainActivity().mBaseChain.equals(BaseChain.KAVA_TEST)) {
                Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://medium.com/kava-labs"));
                startActivity(guideIntent);

            } else if (getMainActivity().mBaseChain.equals(BaseChain.IOV_MAIN)) {
                Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.starname.network/"));
                startActivity(guideIntent);

            }

        } else if (v.equals(mPriceLayer)) {
            if (getMainActivity().mBaseChain.equals(BaseChain.COSMOS_MAIN)) {
                if (getBaseDao().getMarket() == 0) {
                    Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/cosmos"));
                    startActivity(guideIntent);
                } else {
                    Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://coinmarketcap.com/currencies/cosmos/"));
                    startActivity(guideIntent);
                }

            } else if (getMainActivity().mBaseChain.equals(BaseChain.IRIS_MAIN)) {
                if (getBaseDao().getMarket() == 0) {
                    Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/irisnet"));
                    startActivity(guideIntent);
                } else {
                    Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://coinmarketcap.com/currencies/irisnet"));
                    startActivity(guideIntent);
                }

            } else if (getMainActivity().mBaseChain.equals(BaseChain.BNB_MAIN)) {
                if (getBaseDao().getMarket() == 0) {
                    Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/binancecoin"));
                    startActivity(guideIntent);
                } else {
                    Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://coinmarketcap.com/currencies/binance-coin"));
                    startActivity(guideIntent);
                }

            } else if (getMainActivity().mBaseChain.equals(BaseChain.KAVA_MAIN) || getMainActivity().mBaseChain.equals(BaseChain.KAVA_TEST)) {
                if (getBaseDao().getMarket() == 0) {
                    Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://www.coingecko.com/en/coins/kava"));
                    startActivity(guideIntent);
                } else {
                    Intent guideIntent = new Intent(Intent.ACTION_VIEW , Uri.parse("https://coinmarketcap.com/currencies/kava"));
                    startActivity(guideIntent);
                }
            }

        } else if (v.equals(mBtnAtomReward) || v.equals(mBtnIrisReward) || v.equals(mBtnKavaReward)) {
            Intent validators = new Intent(getMainActivity(), ValidatorListActivity.class);
            validators.putExtra("myValidators", getMainActivity().mMyValidators);
            validators.putExtra("topValidators", getMainActivity().mTopValidators);
            validators.putExtra("otherValidators", getMainActivity().mOtherValidators);
            validators.putExtra("bondedToken", getMainActivity().mBondedToken.toPlainString());
            validators.putExtra("provisions", getMainActivity().mProvisions.toPlainString());
            validators.putExtra("rewards", getMainActivity().mRewards);
            validators.putExtra("irispool", getMainActivity().mIrisPool);
            validators.putExtra("irisreward", getMainActivity().mIrisReward);
            startActivity(validators);

        } else if (v.equals(mBtnAtomVote) || v.equals(mBtnIrisVote) || v.equals(mBtnKavaVote)) {
            if (getMainActivity().mBaseChain.equals(BaseChain.KAVA_TEST)) return;
            Intent proposals = new Intent(getMainActivity(), VoteListActivity.class);
            startActivity(proposals);

        } else if (v.equals(mBtnBnbConnect)) {
            if (getMainActivity().mAccount == null) return;
            if (!getMainActivity().mAccount.hasPrivateKey) {
                Dialog_WatchMode add = Dialog_WatchMode.newInstance();
                add.setCancelable(true);
                add.show(getFragmentManager(), "dialog");
                return;
            }
            new TedPermission(getContext()).setPermissionListener(new PermissionListener() {
                @Override
                public void onPermissionGranted() {
                    IntentIntegrator integrator = IntentIntegrator.forSupportFragment(MainSendFragment.this);
                    integrator.setOrientationLocked(true);
                    integrator.initiateScan();
                }

                @Override
                public void onPermissionDenied(ArrayList<String> deniedPermissions) {
                    Toast.makeText(getContext(), R.string.error_permission, Toast.LENGTH_SHORT).show();
                }
            })
            .setPermissions(Manifest.permission.WRITE_EXTERNAL_STORAGE)
            .setRationaleMessage(getString(R.string.str_permission_qr))
            .check();

        } else if (v.equals(mBtnBep3Send)) {
            getMainActivity().onStartHTLCSendActivity();

        } else if (v.equals(mBuyCoinBtn)) {
            if (getMainActivity().mAccount.hasPrivateKey) {
                getMainActivity().onShowBuySelectFiat();
            } else {
                getMainActivity().onShowBuyWarnNoKey();
            }

        } else if (v.equals(mBtnKavaCdp)) {
            startActivity(new Intent(getMainActivity(), KavaCdpListActivity.class));

        } else if (v.equals(mBtnIovDeposit)) {
            Toast.makeText(getContext(), R.string.error_not_yet, Toast.LENGTH_SHORT).show();

        } else if (v.equals(mBtnIovNameService)) {
            Toast.makeText(getContext(), R.string.error_not_yet, Toast.LENGTH_SHORT).show();

        } else if (v.equals(mBtnParticipate)) {
            WLog.w("mBtnParticipate");

        }

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == BaseConstant.CONST_PW_SIMPLE_CHECK && resultCode == Activity.RESULT_OK) {
            Intent wcIntent = new Intent(getMainActivity(), WalletConnectActivity.class);
            wcIntent.putExtra("wcUrl", data.getStringExtra("wcUrl"));
            startActivity(wcIntent);

        } else  if (requestCode == WALLET_CONNECT && resultCode == Activity.RESULT_OK) {
            Intent intent = new Intent(getMainActivity(), PasswordCheckActivity.class);
            intent.putExtra(BaseConstant.CONST_PW_PURPOSE, BaseConstant.CONST_PW_SIMPLE_CHECK);
            intent.putExtra("wcUrl", data.getStringExtra("wcUrl"));
            startActivityForResult(intent, BaseConstant.CONST_PW_SIMPLE_CHECK);
            getMainActivity().overridePendingTransition(R.anim.slide_in_bottom, R.anim.fade_out);

        } else {
            IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
            if (result != null && result.getContents() != null && result.getContents().trim().contains("wallet-bridge.binance.org")) {
                Bundle bundle = new Bundle();
                bundle.putString("wcUrl", result.getContents().trim());
                Dialog_WalletConnect connect = Dialog_WalletConnect.newInstance(bundle);
                connect.setCancelable(true);
                connect.setTargetFragment(MainSendFragment.this, WALLET_CONNECT);
                connect.show(getFragmentManager(), "dialog");

            } else {
                super.onActivityResult(requestCode, resultCode, data);
            }
        }

    }


}

