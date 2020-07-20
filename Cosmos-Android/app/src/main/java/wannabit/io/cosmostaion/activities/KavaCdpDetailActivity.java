package wannabit.io.cosmostaion.activities;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.widget.NestedScrollView;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import wannabit.io.cosmostaion.R;
import wannabit.io.cosmostaion.activities.broadcast.kava.CreateCdpActivity;
import wannabit.io.cosmostaion.activities.broadcast.kava.DepositCdpActivity;
import wannabit.io.cosmostaion.activities.broadcast.kava.DrawDebtActivity;
import wannabit.io.cosmostaion.activities.broadcast.kava.RepayCdpActivity;
import wannabit.io.cosmostaion.activities.broadcast.kava.WithdrawCdpActivity;
import wannabit.io.cosmostaion.base.BaseActivity;
import wannabit.io.cosmostaion.base.BaseChain;
import wannabit.io.cosmostaion.base.BaseConstant;
import wannabit.io.cosmostaion.dialog.Dialog_Help_Msg;
import wannabit.io.cosmostaion.dialog.Dialog_Safe_Score_Staus;
import wannabit.io.cosmostaion.dialog.Dialog_WatchMode;
import wannabit.io.cosmostaion.model.type.Coin;
import wannabit.io.cosmostaion.network.ApiClient;
import wannabit.io.cosmostaion.network.res.ResCdpDepositStatus;
import wannabit.io.cosmostaion.network.res.ResCdpOwnerStatus;
import wannabit.io.cosmostaion.network.res.ResCdpParam;
import wannabit.io.cosmostaion.network.res.ResKavaMarketPrice;
import wannabit.io.cosmostaion.network.res.ResKavaSupply;
import wannabit.io.cosmostaion.task.FetchTask.KavaCdpByDepositorTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaCdpByOwnerTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaCdpParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaMarketPriceTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.TOKEN_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_CDP_MARKET_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_CREATE_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_MSG_TYPE_DRAWDEBT_CDP;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_CDP_DEPOSIT;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_CDP_OWENER;
import static wannabit.io.cosmostaion.base.BaseConstant.TASK_FETCH_KAVA_TOKEN_PRICE;

public class KavaCdpDetailActivity extends BaseActivity implements TaskListener, View.OnClickListener {

    private Toolbar             mToolbar;
    private SwipeRefreshLayout  mSwipeRefreshLayout;
    private NestedScrollView    mNestedScrollView;
    private RelativeLayout      mLoadingLayer;

    private CardView            mCdpInfoCard, mMyCard, mMyEmptyCard;

    private ImageView           mInfoMarketImg;
    private TextView            mInfoMarketId;

    private LinearLayout        mInfoMyLayer;
    private TextView            mInfoRiskScore;
    private ImageView           mInfoImgRisk;
    private TextView            mInfoDebtValueTitle, mInfoDebtValue, mInfoCollateralValueTitle, mInfoCollateralValue;

    private LinearLayout        mInfoEmptyLayer;
    private TextView            mInfoEmptyCollateralRate;

    private RelativeLayout      mInfoCollateralRateView;
    private LinearLayout        mInfoCollateralRateLayer, mStabilityFeeLayer, mInfoLiquidationPenaltyLayer;
    private TextView            mInfoCollateralRate, mInfoStabilityFee, mInfoLiquidationPenalty,
                                mInfoCurrentPriceTitle, mInfoCurrentPrice, mInfoLiquidationPriceTitle, mInfoLiquidationPrice;
    private RelativeLayout      mInfoLiquidationPriceLayer;


    private ImageView           mEmptyCollateralImg, mEmptyPrincipalImg;
    private TextView            mEmptyCollateralDenom, mEmptyCollateralAmount,
                                mEmptyPrincipalDenom, mEmptyPrincipalAmount,
                                mEmptyKavaAmount;
    private TextView            mEmptyCollateralValue, mEmptyPrincipalValue, mEmptyKavaValue;

    private ImageView           mMyCollateralImg;
    private TextView            mMyCollateralDenom;
    private LinearLayout        mMySelfDepositLayer;
    private TextView            mMySelfDepositAmount;
    private LinearLayout        mMyTotalDepositLayer;
    private TextView            mMyTotalDepositAmount;
    private LinearLayout        mMyWithdrawableLayer;
    private TextView            mMyWithdrawableAmountTitle;
    private TextView            mMyWithdrawableAmount;
    private TextView            mMySelfDepositValue, mMyTotalDepositValue, mMyWithdrawableValue;

    private RelativeLayout      mMyBtnDeposit, mMyBtnWithdraw;
    private TextView            mMyBtnDepositTxt, mMyBtnWithdrawTxt;
    private ImageView           mMyPrincipalImg;
    private TextView            mMyPrincipalDenom;

    private LinearLayout        mMyLoadnedLayer;
    private TextView            mMyLoadnedAmount, mMyLoadedValue;

    private LinearLayout        mMyCdpFeeLayer;
    private TextView            mMyCdpFeeAmount, mMyCdpFeeValue;


    private LinearLayout        mMyLoadableLayer;
    private TextView            mMyLoadableAmount, mMyLoadableValue;

    private RelativeLayout      mMyBtnDrawdebt, mMyBtnRepay;

    private Button              mOpenCdp;


    private ResCdpParam.Result          mCdpParam;
    private ResKavaMarketPrice.Result   mKavaTokenPrice;
    private ResCdpOwnerStatus.MyCDP     mMyOwenCdp;
    private ResCdpDepositStatus         mMyDeposits;

    private String                      mMarketDenom;
    private String                      mMaketId;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kava_cdp_detail);
        mToolbar                        = findViewById(R.id.tool_bar);
        mSwipeRefreshLayout             = findViewById(R.id.layer_refresher);
        mNestedScrollView               = findViewById(R.id.layer_scrollview);
        mLoadingLayer                   = findViewById(R.id.loadingLayer);
        mOpenCdp                        = findViewById(R.id.btn_open_cdp);

        mCdpInfoCard                    = findViewById(R.id.card_cdp_info);
        mInfoMarketImg                  = mCdpInfoCard.findViewById(R.id.market_img);
        mInfoMarketId                   = mCdpInfoCard.findViewById(R.id.market_id);
        mInfoMyLayer                    = mCdpInfoCard.findViewById(R.id.cdp_my_layer);
        mInfoRiskScore                  = mCdpInfoCard.findViewById(R.id.cdp_safe_rate);
        mInfoImgRisk                    = mCdpInfoCard.findViewById(R.id.cdp_safe_img);
        mInfoDebtValueTitle             = mCdpInfoCard.findViewById(R.id.cdp_debt_value_title);
        mInfoDebtValue                  = mCdpInfoCard.findViewById(R.id.cdp_debt_value);
        mInfoCollateralValueTitle       = mCdpInfoCard.findViewById(R.id.cdp_collateral_value_title);
        mInfoCollateralValue            = mCdpInfoCard.findViewById(R.id.cdp_collateral_value);

        mInfoEmptyLayer                 = mCdpInfoCard.findViewById(R.id.cdp_empty_layer);
        mInfoEmptyCollateralRate        = mCdpInfoCard.findViewById(R.id.cdp_collateral_rate);


        mInfoCollateralRateView         = mCdpInfoCard.findViewById(R.id.collateral_rate_view);
        mInfoCollateralRateLayer        = mCdpInfoCard.findViewById(R.id.collateral_rate_layer);
        mInfoCollateralRate             = mCdpInfoCard.findViewById(R.id.collateral_rate);
        mStabilityFeeLayer              = mCdpInfoCard.findViewById(R.id.stability_fee_layer);
        mInfoStabilityFee               = mCdpInfoCard.findViewById(R.id.stability_fee);
        mInfoLiquidationPenaltyLayer    = mCdpInfoCard.findViewById(R.id.liquidation_penalty_layer);
        mInfoLiquidationPenalty         = mCdpInfoCard.findViewById(R.id.liquidation_penalty);
        mInfoCurrentPriceTitle          = mCdpInfoCard.findViewById(R.id.current_price_title);
        mInfoCurrentPrice               = mCdpInfoCard.findViewById(R.id.current_price);
        mInfoLiquidationPriceLayer      = mCdpInfoCard.findViewById(R.id.liquidation_price_layer);
        mInfoLiquidationPriceTitle      = mCdpInfoCard.findViewById(R.id.liquidation_price_title);
        mInfoLiquidationPrice           = mCdpInfoCard.findViewById(R.id.liquidation_price);

        mMyCard                         = findViewById(R.id.card_cdp_my);
        mMyCollateralImg                = mMyCard.findViewById(R.id.collateral_icon);
        mMyCollateralDenom              = mMyCard.findViewById(R.id.collateral_denom);
        mMySelfDepositLayer             = mMyCard.findViewById(R.id.self_deposited_amount_layer);
        mMySelfDepositAmount            = mMyCard.findViewById(R.id.self_deposited_amount);
        mMyTotalDepositLayer            = mMyCard.findViewById(R.id.total_deposited_amount_layer);
        mMyTotalDepositAmount           = mMyCard.findViewById(R.id.total_deposited_amount);
        mMyWithdrawableLayer            = mMyCard.findViewById(R.id.expected_withdrawable_amount_layer);
        mMyWithdrawableAmountTitle      = mMyCard.findViewById(R.id.expected_withdrawable_amount_title);
        mMyWithdrawableAmount           = mMyCard.findViewById(R.id.expected_withdrawable_amount);
        mMySelfDepositValue             = mMyCard.findViewById(R.id.self_deposited_value);
        mMyTotalDepositValue            = mMyCard.findViewById(R.id.total_deposited_value);
        mMyWithdrawableValue            = mMyCard.findViewById(R.id.expected_withdrawable_value);
        mMyBtnDeposit                   = mMyCard.findViewById(R.id.btn_deposit);
        mMyBtnDepositTxt                = mMyCard.findViewById(R.id.btn_deposit_txt);
        mMyBtnWithdraw                  = mMyCard.findViewById(R.id.btn_withdraw);
        mMyBtnWithdrawTxt               = mMyCard.findViewById(R.id.btn_withdraw_txt);
        mMyPrincipalImg                 = mMyCard.findViewById(R.id.principal_icon);
        mMyPrincipalDenom               = mMyCard.findViewById(R.id.principal_denom);
        mMyLoadnedLayer                 = mMyCard.findViewById(R.id.loaned_amount_layer);
        mMyLoadnedAmount                = mMyCard.findViewById(R.id.loaned_amount);
        mMyLoadedValue                  = mMyCard.findViewById(R.id.loaned_value);
        mMyCdpFeeLayer                  = mMyCard.findViewById(R.id.cdp_fee_amount_layer);
        mMyCdpFeeAmount                 = mMyCard.findViewById(R.id.cdp_fee_amount);
        mMyCdpFeeValue                  = mMyCard.findViewById(R.id.fee_value);
        mMyLoadableLayer                = mMyCard.findViewById(R.id.expected_loanable_amount_layer);
        mMyLoadableAmount               = mMyCard.findViewById(R.id.expected_loanable_amount);
        mMyLoadableValue                = mMyCard.findViewById(R.id.loanable_value);
        mMyBtnDrawdebt                  = mMyCard.findViewById(R.id.btn_drawdebt);
        mMyBtnRepay                     = mMyCard.findViewById(R.id.btn_repay);


        mMyEmptyCard                    = findViewById(R.id.card_cdp_my_empty);
        mEmptyCollateralImg             = mMyEmptyCard.findViewById(R.id.collateral_icon);
        mEmptyCollateralAmount          = mMyEmptyCard.findViewById(R.id.collateral_amount);
        mEmptyCollateralDenom           = mMyEmptyCard.findViewById(R.id.collateral_denom);
        mEmptyCollateralValue           = mMyEmptyCard.findViewById(R.id.collateral_value);
        mEmptyPrincipalImg              = mMyEmptyCard.findViewById(R.id.principal_icon);
        mEmptyPrincipalAmount           = mMyEmptyCard.findViewById(R.id.principal_amount);
        mEmptyPrincipalDenom            = mMyEmptyCard.findViewById(R.id.principal_denom);
        mEmptyPrincipalValue            = mMyEmptyCard.findViewById(R.id.principal_value);
        mEmptyKavaAmount                = mMyEmptyCard.findViewById(R.id.kava_amount);
        mEmptyKavaValue                 = mMyEmptyCard.findViewById(R.id.kava_value);

        setSupportActionBar(mToolbar);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        mAccount = getBaseDao().onSelectAccount(getBaseDao().getLastUser());
        mBaseChain = BaseChain.getChain(mAccount.baseChain);
        mBalances = mAccount.getBalances();

        mMarketDenom = getIntent().getStringExtra("denom");
        mMaketId = getIntent().getStringExtra("marketId");

        mSwipeRefreshLayout.setColorSchemeColors(getResources().getColor(R.color.colorPrimary));
        mSwipeRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                onFetchCdpInfo();
            }
        });
        onFetchCdpInfo();
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

    String cDenom = "";
    String pDenom = "";
    BigDecimal cAvailable = BigDecimal.ZERO;
    BigDecimal pAvailable = BigDecimal.ZERO;
    BigDecimal kAvailable = BigDecimal.ZERO;
    BigDecimal currentPrice = BigDecimal.ZERO;
    ResCdpParam.KavaCollateralParam cParam;

    private void onUpdateView() {
        cParam = mCdpParam.getCollateralParamByDenom(mMarketDenom);
        if (cParam == null || mKavaTokenPrice == null) {onBackPressed();}

        cDenom = cParam.denom;
        pDenom = cParam.debt_limit.denom;
        cAvailable = WUtil.getTokenBalance(mBalances, cDenom) == null ? BigDecimal.ZERO : WUtil.getTokenBalance(mBalances, cDenom).balance;
        pAvailable = WUtil.getTokenBalance(mBalances, pDenom) == null ? BigDecimal.ZERO : WUtil.getTokenBalance(mBalances, pDenom).balance;
        kAvailable = WUtil.getTokenBalance(mBalances, TOKEN_KAVA) == null ? BigDecimal.ZERO : WUtil.getTokenBalance(mBalances, TOKEN_KAVA).balance;
        currentPrice = new BigDecimal(mKavaTokenPrice.price);

        onUpdateInfoView();
        onUpdateMyView();
        onUpdateAssetView();
        mLoadingLayer.setVisibility(View.GONE);
    }

    BigDecimal mRiskRate, mLiquidationPrice = BigDecimal.ZERO;
    private void onUpdateInfoView() {
        if (mMyOwenCdp == null) {
            mInfoMyLayer.setVisibility(View.GONE);
            mInfoEmptyLayer.setVisibility(View.VISIBLE);
            mInfoCollateralRateView.setVisibility(View.GONE);
            mInfoLiquidationPriceLayer.setVisibility(View.GONE);

        } else {
            mInfoMyLayer.setVisibility(View.VISIBLE);
            mInfoEmptyLayer.setVisibility(View.GONE);
            mInfoCollateralRateView.setVisibility(View.VISIBLE);
            mInfoLiquidationPriceLayer.setVisibility(View.VISIBLE);

            mLiquidationPrice = mMyOwenCdp.getLiquidationPrice(getBaseContext(), cParam);
            mRiskRate = new BigDecimal(100).subtract((currentPrice.subtract(mLiquidationPrice)).movePointRight(2).divide(currentPrice, 2, RoundingMode.DOWN));
            WDp.DpRiskRate(getBaseContext(), mRiskRate, mInfoRiskScore, mInfoImgRisk);

//            WLog.w("currentPrice " + currentPrice);
//            WLog.w("mLiquidationPrice " + mLiquidationPrice);
//            WLog.w("mRiskRate " + mRiskRate);

            mInfoDebtValueTitle.setText(String.format(getString(R.string.str_debt_value), mMyOwenCdp.getPDenom().toUpperCase()));
            mInfoCollateralValueTitle.setText(String.format(getString(R.string.str_collateral_value_title3), mMyOwenCdp.getDenom().toUpperCase()));

            final BigDecimal debtValue = new BigDecimal(mMyOwenCdp.cdp.principal.amount);
            final BigDecimal feeValue = mMyOwenCdp.getAccumulatedFees();
            final BigDecimal hiddenFeeValue = WDp.getCdpHiddenFee(getBaseContext(), debtValue.add(feeValue), cParam, mMyOwenCdp.cdp);
            final BigDecimal totalDebtValue = debtValue.add(feeValue).add(hiddenFeeValue);
            mInfoDebtValue.setText(WDp.getDpRawDollor(getBaseContext(), totalDebtValue.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)), 2));

            final BigDecimal currentCollateralValue = new BigDecimal(mMyOwenCdp.collateral_value.amount);
            mInfoCollateralValue.setText(WDp.getDpRawDollor(getBaseContext(), currentCollateralValue.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)), 2));

            mInfoLiquidationPriceTitle.setText(String.format(getString(R.string.str_liquidation_title3), cDenom.toUpperCase()));
            mInfoLiquidationPrice.setText(WDp.getDpRawDollor(getBaseContext(), mLiquidationPrice, 4));
        }

        mInfoEmptyCollateralRate.setText(WDp.getPercentDp(cParam.getDpLiquidationRatio(), 2));
        mInfoCollateralRate.setText(WDp.getPercentDp(cParam.getDpLiquidationRatio(), 2));
        mInfoStabilityFee.setText(WDp.getPercentDp(cParam.getDpStabilityFee(), 2));
        mInfoLiquidationPenalty.setText(WDp.getPercentDp(cParam.getDpLiquidationPenalty(), 2));
        mInfoCurrentPriceTitle.setText(String.format(getString(R.string.str_current_title3), cDenom.toUpperCase()));
        mInfoCurrentPrice.setText(WDp.getDpRawDollor(getBaseContext(), currentPrice, 4));

        mInfoMarketId.setText(cParam.getDpMarketId());
        try {
            Picasso.get().load(KAVA_CDP_MARKET_IMG_URL+  cParam.getImagePath()).fit().into(mInfoMarketImg);
        } catch (Exception e) { }


        mInfoMyLayer.setOnClickListener(this);
        mInfoEmptyLayer.setOnClickListener(this);
        mInfoCollateralRateLayer.setOnClickListener(this);
        mStabilityFeeLayer.setOnClickListener(this);
        mInfoLiquidationPenaltyLayer.setOnClickListener(this);

        mCdpInfoCard.setVisibility(View.VISIBLE);
    }

    private void onUpdateMyView() {
        if (mMyOwenCdp == null) {
            mMyCard.setVisibility(View.GONE);
            mOpenCdp.setVisibility(View.VISIBLE);
            mOpenCdp.setOnClickListener(this);

        } else {
            BigDecimal selfDepositAmount = mMyDeposits.getSelfDeposit(mAccount.address);
            mMySelfDepositAmount.setText(WDp.getDpAmount2(getBaseContext(), selfDepositAmount, WUtil.getKavaCoinDecimal(cDenom), WUtil.getKavaCoinDecimal(cDenom)));
            BigDecimal selfDepositValue = selfDepositAmount.movePointLeft(WUtil.getKavaCoinDecimal(cDenom)).multiply(currentPrice).setScale(2, RoundingMode.DOWN);
            mMySelfDepositValue.setText(WDp.getDpRawDollor(getBaseContext(), selfDepositValue, 2));

            mMyTotalDepositAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(mMyOwenCdp.cdp.collateral.amount), WUtil.getKavaCoinDecimal(cDenom), WUtil.getKavaCoinDecimal(cDenom)));
            BigDecimal totalDepositValue = new BigDecimal(mMyOwenCdp.cdp.collateral.amount).movePointLeft(WUtil.getKavaCoinDecimal(cDenom)).multiply(currentPrice).setScale(2, RoundingMode.DOWN);
            mMyTotalDepositValue.setText(WDp.getDpRawDollor(getBaseContext(), totalDepositValue, 2));

            mMyWithdrawableAmountTitle.setText(getString(R.string.str_expected_withdrawable_amount) + " " + cDenom.toUpperCase());
            BigDecimal maxWithdrawableAmount = mMyOwenCdp.getWithdrawableAmount(getBaseContext(), cParam, currentPrice, selfDepositAmount);
            BigDecimal maxWithdrawableValue = maxWithdrawableAmount.movePointLeft(WUtil.getKavaCoinDecimal(cDenom)).multiply(currentPrice);
            mMyWithdrawableAmount.setText(WDp.getDpAmount2(getBaseContext(), maxWithdrawableAmount, WUtil.getKavaCoinDecimal(cDenom), WUtil.getKavaCoinDecimal(cDenom)));
            mMyWithdrawableValue.setText(WDp.getDpRawDollor(getBaseContext(), maxWithdrawableValue.movePointLeft(WUtil.getKavaCoinDecimal(cDenom)), 2));


            final BigDecimal debtValue = mMyOwenCdp.getPrincipalAmount();
            mMyLoadnedAmount.setText(WDp.getDpAmount2(getBaseContext(), debtValue, WUtil.getKavaCoinDecimal(pDenom), WUtil.getKavaCoinDecimal(pDenom)));
            mMyLoadedValue.setText(WDp.getDpRawDollor(getBaseContext(), debtValue.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)), 2));

            final BigDecimal totalFeeValue = mMyOwenCdp.getEstimatedTotalFee(getBaseContext(), cParam);
            mMyCdpFeeAmount.setText(WDp.getDpAmount2(getBaseContext(), totalFeeValue, WUtil.getKavaCoinDecimal(pDenom), WUtil.getKavaCoinDecimal(pDenom)));
            mMyCdpFeeValue.setText(WDp.getDpRawDollor(getBaseContext(), totalFeeValue.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)), 2));

            final BigDecimal moreDebtAmount = mMyOwenCdp.getMoreLoanableAmount(getBaseContext(), cParam);
            mMyLoadableAmount.setText(WDp.getDpAmount2(getBaseContext(), moreDebtAmount, WUtil.getKavaCoinDecimal(pDenom), WUtil.getKavaCoinDecimal(pDenom)));
            mMyLoadableValue.setText(WDp.getDpRawDollor(getBaseContext(), moreDebtAmount.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)), 2));

            mMyBtnDepositTxt.setText(String.format(getString(R.string.str_btn_text_deposit), cDenom.toUpperCase()));
            mMyBtnWithdrawTxt.setText(String.format(getString(R.string.str_btn_text_withdraw), cDenom.toUpperCase()));

            mMyPrincipalDenom.setText(pDenom.toUpperCase());
            mMyCollateralDenom.setText(cParam.denom.toUpperCase());

            try {
                Picasso.get().load(KAVA_COIN_IMG_URL + cDenom + ".png").fit().into(mMyCollateralImg);
                Picasso.get().load(KAVA_COIN_IMG_URL + pDenom + ".png").fit().into(mMyPrincipalImg);
            } catch (Exception e) { }

            mMySelfDepositLayer.setOnClickListener(this);
            mMyTotalDepositLayer.setOnClickListener(this);
            mMyWithdrawableLayer.setOnClickListener(this);
            mMyLoadnedLayer.setOnClickListener(this);
            mMyCdpFeeLayer.setOnClickListener(this);
            mMyLoadableLayer.setOnClickListener(this);
            mMyBtnDeposit.setOnClickListener(this);
            mMyBtnWithdraw.setOnClickListener(this);
            mMyBtnDrawdebt.setOnClickListener(this);
            mMyBtnRepay.setOnClickListener(this);

            mMyCard.setVisibility(View.VISIBLE);
            mOpenCdp.setVisibility(View.GONE);
        }

    }

    private void onUpdateAssetView() {
        mEmptyCollateralDenom.setText(cParam.denom.toUpperCase());
        mEmptyCollateralAmount.setText(WDp.getDpAmount2(getBaseContext(), cAvailable, WUtil.getKavaCoinDecimal(cDenom), WUtil.getKavaCoinDecimal(cDenom)));
        BigDecimal collateralValue = cAvailable.movePointLeft(WUtil.getKavaCoinDecimal(cDenom)).multiply(currentPrice).setScale(2, RoundingMode.DOWN);
        mEmptyCollateralValue.setText(WDp.getDpRawDollor(getBaseContext(), collateralValue, 2));

        mEmptyPrincipalDenom.setText(cParam.debt_limit.denom.toUpperCase());
        mEmptyPrincipalAmount.setText(WDp.getDpAmount2(getBaseContext(), pAvailable, WUtil.getKavaCoinDecimal(pDenom), WUtil.getKavaCoinDecimal(pDenom)));
        BigDecimal principalValue = pAvailable.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)).setScale(2, RoundingMode.DOWN);
        mEmptyPrincipalValue.setText(WDp.getDpRawDollor(getBaseContext(), principalValue, 2));

        mEmptyKavaAmount.setText(WDp.getDpAmount2(getBaseContext(), kAvailable, WUtil.getKavaCoinDecimal(TOKEN_KAVA), WUtil.getKavaCoinDecimal(TOKEN_KAVA)));
        BigDecimal kavaValue = kAvailable.movePointLeft(WUtil.getKavaCoinDecimal(TOKEN_KAVA)).multiply(getBaseDao().getLastKavaDollorTic()).setScale(2, RoundingMode.DOWN);
        mEmptyKavaValue.setText(WDp.getDpRawDollor(getBaseContext(), kavaValue, 2));

        try {
            Picasso.get().load(KAVA_COIN_IMG_URL + cDenom + ".png").fit().into(mEmptyCollateralImg);
            Picasso.get().load(KAVA_COIN_IMG_URL + pDenom + ".png").fit().into(mEmptyPrincipalImg);
        } catch (Exception e) { }

        mMyEmptyCard.setVisibility(View.VISIBLE);
    }

    @Override
    public void onClick(View v) {
        if (v.equals(mInfoCollateralRateLayer) || v.equals(mInfoEmptyLayer)) {
            onShowHelpPopup(getString(R.string.str_help_collateral_rate_t), getString(R.string.str_help_collateral_rate));

        } else if (v.equals(mStabilityFeeLayer)) {
            onShowHelpPopup(getString(R.string.str_help_stability_fee_t), getString(R.string.str_help_stability_fee));

        } else if (v.equals(mInfoLiquidationPenaltyLayer)) {
            onShowHelpPopup(getString(R.string.str_help_liquidation_penalty_t), getString(R.string.str_help_liquidation_penalty));

        } else if (v.equals(mMySelfDepositLayer)) {
            onShowHelpPopup(getString(R.string.str_help_self_deposited_collateral_t),
                    String.format(getString(R.string.str_help_self_deposited_collateral_), mMarketDenom.toUpperCase()));

        } else if (v.equals(mMyTotalDepositLayer)) {
            onShowHelpPopup(getString(R.string.str_help_total_deposited_collateral_t),
                    String.format(getString(R.string.str_help_total_deposited_collateral), mMarketDenom.toUpperCase()));

        } else if (v.equals(mMyWithdrawableLayer)) {
            onShowHelpPopup(getString(R.string.str_expected_withdrawable_amount) + " " + mMarketDenom.toUpperCase(),
                    getString(R.string.str_help_withdrawable));

        } else if (v.equals(mMyLoadnedLayer)) {
            onShowHelpPopup(getString(R.string.str_help_loaned_amount_t), getString(R.string.str_help_loaned_amount));

        } else if (v.equals(mMyLoadableLayer)) {
            onShowHelpPopup(getString(R.string.str_help_remaining_loan_capacity_t), getString(R.string.str_help_remaining_loan_capacity));

        } else if (v.equals(mMyCdpFeeLayer)) {
            onShowHelpPopup(getString(R.string.str_help_total_fee_t), getString(R.string.str_help_total_fee));

        } else if (v.equals(mInfoMyLayer)) {
            Bundle bundle = new Bundle();
            bundle.putString("riskRate", mRiskRate.toPlainString());
            bundle.putString("liquidationPrice", mLiquidationPrice.toPlainString());
            bundle.putString("currentPrice", currentPrice.toPlainString());
            bundle.putString("denom", cDenom);
            Dialog_Safe_Score_Staus dialog = Dialog_Safe_Score_Staus.newInstance(bundle);
            dialog.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();

        } else if (v.equals(mMyBtnDeposit)) {
            onCheckStartDepositCdp();

        } else if (v.equals(mMyBtnWithdraw)) {
            onCheckStartWithdrawCdp();

        } else if (v.equals(mMyBtnDrawdebt)) {
            onCheckStartDrawDebtCdp();

        } else if (v.equals(mMyBtnRepay)) {
            onCheckStartRepayCdp();

        } else if (v.equals(mOpenCdp)) {
            onCheckStartCreateCdp();

        }
    }

    private void onShowHelpPopup(String title, String msg) {
        Bundle bundle = new Bundle();
        bundle.putString("title", title);
        bundle.putString("msg", msg);
        Dialog_Help_Msg dialog = Dialog_Help_Msg.newInstance(bundle);
        dialog.setCancelable(true);
        getSupportFragmentManager().beginTransaction().add(dialog, "dialog").commitNowAllowingStateLoss();
    }

    private void onCheckStartCreateCdp() {
        if (!onCommonCheck()) return;

        BigDecimal principalMinAmount = new BigDecimal(mCdpParam.debt_param.debt_floor);
        BigDecimal collateralMinAmount = principalMinAmount.movePointLeft(WUtil.getKavaCoinDecimal(pDenom) - WUtil.getKavaCoinDecimal(cDenom)).multiply(new BigDecimal("1.05263157895")).multiply(new BigDecimal(cParam.liquidation_ratio)).divide(currentPrice, 0, RoundingMode.UP);
        if (collateralMinAmount.compareTo(cAvailable) > 0) {
            Toast.makeText(getBaseContext(), R.string.error_less_than_min_deposit, Toast.LENGTH_SHORT).show();
            return;
        }
        if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            onCheckSupply(KAVA_MSG_TYPE_CREATE_CDP);

        } else {
            Intent intent = new Intent(KavaCdpDetailActivity.this, CreateCdpActivity.class);
            intent.putExtra("denom", mMarketDenom);
            intent.putExtra("marketId", mMaketId);
            startActivity(intent);
        }
    }

    private void onCheckStartDepositCdp() {
        if (!onCommonCheck()) return;

        if (cAvailable.compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enought_deposit_asset, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, DepositCdpActivity.class);
        intent.putExtra("denom", mMarketDenom);
        intent.putExtra("marketId", mMaketId);
        startActivity(intent);
    }

    private void onCheckStartWithdrawCdp() {
        if (!onCommonCheck()) return;

        BigDecimal selfDepositAmount = mMyDeposits.getSelfDeposit(mAccount.address);
        BigDecimal maxWithdrawableAmount = mMyOwenCdp.getWithdrawableAmount(getBaseContext(), cParam, currentPrice, selfDepositAmount);
        if (maxWithdrawableAmount.compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enought_withdraw_asset, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, WithdrawCdpActivity.class);
        intent.putExtra("denom", mMarketDenom);
        intent.putExtra("marketId", mMaketId);
        startActivity(intent);
    }

    private void onCheckStartDrawDebtCdp() {
        if (!onCommonCheck()) return;

        if (mMyOwenCdp.getMoreLoanableAmount(getBaseContext(), cParam).compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_can_not_draw_debt, Toast.LENGTH_SHORT).show();
            return;
        }
        if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            onCheckSupply(KAVA_MSG_TYPE_DRAWDEBT_CDP);

        } else {
            Intent intent = new Intent(KavaCdpDetailActivity.this, DrawDebtActivity.class);
            intent.putExtra("denom", mMarketDenom);
            intent.putExtra("marketId", mMaketId);
            startActivity(intent);
        }
    }

    private void onCheckStartRepayCdp() {
        if (!onCommonCheck()) return;

        if (pAvailable.compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_not_enought_principal_asset, Toast.LENGTH_SHORT).show();
            return;
        }

        boolean repayAll = true;
        boolean repayPart = true;
        BigDecimal debtFloor = new BigDecimal(mCdpParam.debt_param.debt_floor);
        BigDecimal rawDebt =  mMyOwenCdp.getPrincipalAmount();
        BigDecimal totalDebt =  mMyOwenCdp.getEstimatedTotalDebt(getBaseContext(), cParam);
        if (totalDebt.compareTo(pAvailable) > 0) { repayAll = false; }
        if (rawDebt.compareTo(debtFloor) <= 0) { repayPart = false; }
        if (!repayAll && !repayPart) {
            Toast.makeText(getBaseContext(), R.string.error_can_not_repay, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, RepayCdpActivity.class);
        intent.putExtra("denom", mMarketDenom);
        intent.putExtra("marketId", mMaketId);
        startActivity(intent);
    }

    private boolean onCommonCheck() {
        if(!mAccount.hasPrivateKey) {
            Dialog_WatchMode add = Dialog_WatchMode.newInstance();
            add.setCancelable(true);
            getSupportFragmentManager().beginTransaction().add(add, "dialog").commitNowAllowingStateLoss();
            return false;
        }

        if (mCdpParam.circuit_breaker) {
            Toast.makeText(getBaseContext(), R.string.error_circuit_breaker, Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }



    private int mTaskCount = 0;
    public void onFetchCdpInfo() {
        if (mBaseChain.equals(BaseChain.KAVA_MAIN) || mBaseChain.equals(BaseChain.KAVA_TEST)) {
            mTaskCount = 3;
            new KavaCdpParamTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain)).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new KavaMarketPriceTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), mMaketId).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            new KavaCdpByOwnerTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), mAccount.address, mMarketDenom).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
        }

    }

    @Override
    public void onTaskResponse(TaskResult result) {
        if(isFinishing()) return;
        mTaskCount--;
        if (result.taskType == BaseConstant.TASK_FETCH_KAVA_CDP_PARAM) {
            if (result.isSuccess && result.resultData != null) {
                mCdpParam = (ResCdpParam.Result)result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_TOKEN_PRICE) {
            if (result.isSuccess && result.resultData != null) {
                mKavaTokenPrice = (ResKavaMarketPrice.Result)result.resultData;
            }

        } else if (result.taskType == TASK_FETCH_KAVA_CDP_OWENER) {
            if (result.isSuccess && result.resultData != null) {
                mMyOwenCdp = (ResCdpOwnerStatus.MyCDP)result.resultData;
                mTaskCount = mTaskCount + 1;
                new KavaCdpByDepositorTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), mAccount.address, mMarketDenom).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }

        } else if (result.taskType == TASK_FETCH_KAVA_CDP_DEPOSIT) {
            if (result.isSuccess && result.resultData != null) {
                mMyDeposits = (ResCdpDepositStatus)result.resultData;
            }
        }

        if (mTaskCount == 0) {
            if (mCdpParam == null || mKavaTokenPrice == null) {
                Toast.makeText(getBaseContext(), getString(R.string.str_network_error_title), Toast.LENGTH_SHORT).show();
                onBackPressed();
                return;
            }
            onUpdateView();
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }


    private void onCheckSupply(String msgType) {
        ApiClient.getKavaChain(getBaseContext()).getSupply().enqueue(new Callback<ResKavaSupply>() {
            @Override
            public void onResponse(Call<ResKavaSupply> call, Response<ResKavaSupply> response) {
                if (response.isSuccessful()) {
                    WLog.w("GlobalDebtAmount " + mCdpParam.getGlobalDebtAmount());
                    WLog.w("CurrentDebtAmount " + response.body().getDebtAmount());

                    if (mCdpParam.getGlobalDebtAmount().compareTo(response.body().getDebtAmount()) <= 0 ) {
                        String msg = String.format(String.format(getString(R.string.error_no_more_debt_kava),
                                response.body().getDebtAmount().movePointLeft(6).toPlainString(),
                                mCdpParam.getGlobalDebtAmount().movePointLeft(6).toPlainString()));
                        Toast.makeText(getBaseContext(), msg, Toast.LENGTH_SHORT).show();

                    } else {
                        if (msgType.equals(KAVA_MSG_TYPE_CREATE_CDP)) {
                            Intent intent = new Intent(KavaCdpDetailActivity.this, CreateCdpActivity.class);
                            intent.putExtra("denom", mMarketDenom);
                            intent.putExtra("marketId", mMaketId);
                            startActivity(intent);

                        } else  if (msgType.equals(KAVA_MSG_TYPE_DRAWDEBT_CDP)) {
                            Intent intent = new Intent(KavaCdpDetailActivity.this, DrawDebtActivity.class);
                            intent.putExtra("denom", mMarketDenom);
                            intent.putExtra("marketId", mMaketId);
                            startActivity(intent);
                        }
                    }


                } else {
                    Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                    return;
                }

            }

            @Override
            public void onFailure(Call<ResKavaSupply> call, Throwable t) {
                Toast.makeText(getBaseContext(), R.string.error_network_error, Toast.LENGTH_SHORT).show();
                return;
            }
        });
    }


}
