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
import wannabit.io.cosmostaion.network.res.ResCdpDepositStatus;
import wannabit.io.cosmostaion.network.res.ResCdpOwnerStatus;
import wannabit.io.cosmostaion.network.res.ResCdpParam;
import wannabit.io.cosmostaion.network.res.ResKavaMarketPrice;
import wannabit.io.cosmostaion.task.FetchTask.KavaCdpByDepositorTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaCdpByOwnerTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaCdpParamTask;
import wannabit.io.cosmostaion.task.FetchTask.KavaMarketPriceTask;
import wannabit.io.cosmostaion.task.TaskListener;
import wannabit.io.cosmostaion.task.TaskResult;
import wannabit.io.cosmostaion.utils.WDp;
import wannabit.io.cosmostaion.utils.WLog;
import wannabit.io.cosmostaion.utils.WUtil;

import static wannabit.io.cosmostaion.base.BaseConstant.COSMOS_KAVA;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_CDP_MARKET_IMG_URL;
import static wannabit.io.cosmostaion.base.BaseConstant.KAVA_COIN_IMG_URL;
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
    private ResCdpOwnerStatus.Result    mMyOwenCdp;
    private ArrayList<ResCdpDepositStatus.Result> mMyDepositList = new ArrayList<>();

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
        pDenom = cParam.debt_limit.get(0).denom;
        cAvailable = WUtil.getTokenBalance(mBalances, cDenom) == null ? BigDecimal.ZERO : WUtil.getTokenBalance(mBalances, cDenom).balance;
        pAvailable = WUtil.getTokenBalance(mBalances, pDenom) == null ? BigDecimal.ZERO : WUtil.getTokenBalance(mBalances, pDenom).balance;
        kAvailable = WUtil.getTokenBalance(mBalances, COSMOS_KAVA) == null ? BigDecimal.ZERO : WUtil.getTokenBalance(mBalances, COSMOS_KAVA).balance;
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

            mLiquidationPrice = WDp.getLiquidationPrice(mMyOwenCdp, new BigDecimal(cParam.liquidation_ratio));
            mRiskRate = new BigDecimal(100).subtract((currentPrice.subtract(mLiquidationPrice)).movePointRight(2).divide(currentPrice, 2, RoundingMode.DOWN));
            WDp.DpRiskRate(getBaseContext(), mRiskRate, mInfoRiskScore, mInfoImgRisk);

            mInfoDebtValueTitle.setText(mMyOwenCdp.getPDenom().toUpperCase() + " " + getString(R.string.str_debt_value));
            mInfoCollateralValueTitle.setText(WDp.DpCollateralValueTitle(getBaseContext(), mMyOwenCdp.getDenom().toUpperCase()));

            final BigDecimal debtValue = new BigDecimal(mMyOwenCdp.cdp.principal.get(0).amount);
            final BigDecimal feeValue = mMyOwenCdp.getAccumulatedFees();
            final BigDecimal hiddenFeeValue = WDp.getCdpHiddenFee(getBaseContext(), debtValue.add(feeValue), cParam, mMyOwenCdp.cdp);
            final BigDecimal totalDebtValue = debtValue.add(feeValue).add(hiddenFeeValue);
            mInfoDebtValue.setText(WDp.getDpRawDollor(getBaseContext(), totalDebtValue.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)), 2));

            final BigDecimal currentCollateralValue = new BigDecimal(mMyOwenCdp.collateral_value.amount);
            mInfoCollateralValue.setText(WDp.getDpRawDollor(getBaseContext(), currentCollateralValue.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)), 2));

            mInfoLiquidationPriceTitle.setText(WDp.DpLiquidationPriceTitle(getBaseContext(), cDenom.toUpperCase()));
            mInfoLiquidationPrice.setText(WDp.getDpRawDollor(getBaseContext(), mLiquidationPrice, 4));
        }

        mInfoEmptyCollateralRate.setText(WDp.getPercentDp(cParam.getDpLiquidationRatio(), 2));
        mInfoCollateralRate.setText(WDp.getPercentDp(cParam.getDpLiquidationRatio(), 2));
        mInfoStabilityFee.setText(WDp.getPercentDp(cParam.getDpStabilityFee(), 2));
        mInfoLiquidationPenalty.setText(WDp.getPercentDp(cParam.getDpLiquidationPenalty(), 2));
        mInfoCurrentPriceTitle.setText(WDp.DpCurrentPriceTitle(getBaseContext(), cDenom.toUpperCase()));
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
            BigDecimal selfDepositAmount = BigDecimal.ZERO;
            for (ResCdpDepositStatus.Result deposit:mMyDepositList) {
                if (deposit.cdp_id.equals(mMyOwenCdp.cdp.id) && deposit.depositor.equals(mAccount.address)) {
                    selfDepositAmount = new BigDecimal(deposit.amount.get(0).amount);
                }
            }
            mMySelfDepositAmount.setText(WDp.getDpAmount2(getBaseContext(), selfDepositAmount, WUtil.getKavaCoinDecimal(cDenom), WUtil.getKavaCoinDecimal(cDenom)));
            BigDecimal selfDepositValue = selfDepositAmount.movePointLeft(WUtil.getKavaCoinDecimal(cDenom)).multiply(currentPrice).setScale(2, RoundingMode.DOWN);
            mMySelfDepositValue.setText(WDp.getDpRawDollor(getBaseContext(), selfDepositValue, 2));

            mMyTotalDepositAmount.setText(WDp.getDpAmount2(getBaseContext(), new BigDecimal(mMyOwenCdp.cdp.collateral.get(0).amount), WUtil.getKavaCoinDecimal(cDenom), WUtil.getKavaCoinDecimal(cDenom)));
            BigDecimal totalDepositValue = new BigDecimal(mMyOwenCdp.cdp.collateral.get(0).amount).movePointLeft(WUtil.getKavaCoinDecimal(cDenom)).multiply(currentPrice).setScale(2, RoundingMode.DOWN);
            mMyTotalDepositValue.setText(WDp.getDpRawDollor(getBaseContext(), totalDepositValue, 2));

            mMyWithdrawableAmountTitle.setText(getString(R.string.str_expected_withdrawable_amount) + " " + cDenom.toUpperCase());
            BigDecimal cValue = new BigDecimal(mMyOwenCdp.collateral_value.amount);
            BigDecimal debtValue = new BigDecimal(mMyOwenCdp.cdp.principal.get(0).amount);
            BigDecimal feeValue = mMyOwenCdp.cdp.getAccumulatedFees();
            BigDecimal hiddenFeeValue = WDp.getCdpHiddenFee(getBaseContext(), debtValue.add(feeValue), cParam, mMyOwenCdp.cdp);
            BigDecimal toRepayValue = debtValue.add(feeValue).add(hiddenFeeValue);
            BigDecimal totalWithdrawableValue = cValue.subtract(toRepayValue.multiply(new BigDecimal(cParam.liquidation_ratio)).setScale(0, RoundingMode.DOWN));
            BigDecimal totalWithdrawableAmount = totalWithdrawableValue.movePointLeft(WUtil.getKavaCoinDecimal(pDenom) - WUtil.getKavaCoinDecimal(cDenom)).divide(new BigDecimal(mKavaTokenPrice.price), 0, RoundingMode.HALF_DOWN);
            BigDecimal myWithdrawableAmount = BigDecimal.ZERO;
            if (totalWithdrawableAmount.compareTo(selfDepositAmount) > 0) {
                myWithdrawableAmount = selfDepositAmount;
                WLog.w("myWithdrawableAmount " +  myWithdrawableAmount);
            } else {
                myWithdrawableAmount = totalWithdrawableAmount;
                WLog.w("myWithdrawableAmount " +  myWithdrawableAmount);
                myWithdrawableAmount = myWithdrawableAmount.multiply(new BigDecimal(0.95)).setScale(0, RoundingMode.DOWN);
                WLog.w("myWithdrawableAmount padding " +  myWithdrawableAmount);
            }
            BigDecimal myWithdrawableValue = myWithdrawableAmount.movePointLeft(WUtil.getKavaCoinDecimal(cDenom)).multiply(currentPrice).setScale(2, RoundingMode.DOWN);
            mMyWithdrawableAmount.setText(WDp.getDpAmount2(getBaseContext(), myWithdrawableAmount, WUtil.getKavaCoinDecimal(cDenom), WUtil.getKavaCoinDecimal(cDenom)));
            mMyWithdrawableValue.setText(WDp.getDpRawDollor(getBaseContext(), myWithdrawableValue, 2));
            WLog.w("debtValue " + debtValue);
            WLog.w("feeValue " + feeValue);
            WLog.w("hiddenFeeValue " + hiddenFeeValue);
            WLog.w("toRepayValue " + toRepayValue);


            mMyLoadnedAmount.setText(WDp.getDpAmount2(getBaseContext(), debtValue, WUtil.getKavaCoinDecimal(pDenom), WUtil.getKavaCoinDecimal(pDenom)));
            mMyLoadedValue.setText(WDp.getDpRawDollor(getBaseContext(), debtValue.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)), 2));

            mMyCdpFeeAmount.setText(WDp.getDpAmount2(getBaseContext(), feeValue.add(hiddenFeeValue), WUtil.getKavaCoinDecimal(pDenom), WUtil.getKavaCoinDecimal(pDenom)));
            mMyCdpFeeValue.setText(WDp.getDpRawDollor(getBaseContext(), feeValue.add(hiddenFeeValue).movePointLeft(WUtil.getKavaCoinDecimal(pDenom)), 2));

            BigDecimal maxDebtValue = cValue.divide(new BigDecimal(cParam.liquidation_ratio),0, BigDecimal.ROUND_DOWN);
            WLog.w("maxDebtValue " +  maxDebtValue);
            maxDebtValue = maxDebtValue.multiply(new BigDecimal(0.95)).setScale(0, RoundingMode.DOWN);
            WLog.w("maxDebtValue padding " +  maxDebtValue);
            BigDecimal moreDebtAmount = maxDebtValue.subtract(toRepayValue);
            mMyLoadableAmount.setText(WDp.getDpAmount2(getBaseContext(), moreDebtAmount, WUtil.getKavaCoinDecimal(pDenom), WUtil.getKavaCoinDecimal(pDenom)));
            mMyLoadableValue.setText(WDp.getDpRawDollor(getBaseContext(), moreDebtAmount.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)), 2));

            mMyBtnDepositTxt.setText(getString(R.string.str_collateral_deposit) + " " + cDenom.toUpperCase());
            mMyBtnWithdrawTxt.setText(getString(R.string.str_collateral_withdraw) + " " + cDenom.toUpperCase());
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

        mEmptyPrincipalDenom.setText(cParam.debt_limit.get(0).denom.toUpperCase());
        mEmptyPrincipalAmount.setText(WDp.getDpAmount2(getBaseContext(), pAvailable, WUtil.getKavaCoinDecimal(pDenom), WUtil.getKavaCoinDecimal(pDenom)));
        BigDecimal principalValue = pAvailable.movePointLeft(WUtil.getKavaCoinDecimal(pDenom)).setScale(2, RoundingMode.DOWN);
        mEmptyPrincipalValue.setText(WDp.getDpRawDollor(getBaseContext(), principalValue, 2));

        mEmptyKavaAmount.setText(WDp.getDpAmount2(getBaseContext(), kAvailable, WUtil.getKavaCoinDecimal(COSMOS_KAVA), WUtil.getKavaCoinDecimal(COSMOS_KAVA)));
        BigDecimal kavaValue = kAvailable.movePointLeft(WUtil.getKavaCoinDecimal(COSMOS_KAVA)).multiply(getBaseDao().getLastKavaDollorTic()).setScale(2, RoundingMode.DOWN);
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
            onShowHelpPopup(getString(R.string.str_help_withdrawable_t) + " " + mMarketDenom.toUpperCase(),
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

        BigDecimal principalMinAmount = new BigDecimal(mCdpParam.debt_params.get(0).debt_floor);
        BigDecimal collateralMinAmount = principalMinAmount.movePointLeft(WUtil.getKavaCoinDecimal(pDenom) - WUtil.getKavaCoinDecimal(cDenom)).multiply(new BigDecimal(1.05)).multiply(new BigDecimal(cParam.liquidation_ratio)).divide(currentPrice, 0, RoundingMode.DOWN);
        if (collateralMinAmount.compareTo(cAvailable) > 0) {
            Toast.makeText(getBaseContext(), R.string.error_less_than_min_deposit, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, CreateCdpActivity.class);
        intent.putExtra("denom", mMarketDenom);
        intent.putExtra("marketId", mMaketId);
        startActivity(intent);
    }

    private void onCheckStartRepayCdp() {
        //TODO add check logic!
        if (!onCommonCheck()) return;

        Intent intent = new Intent(this, RepayCdpActivity.class);
        intent.putExtra("denom", mMarketDenom);
        intent.putExtra("marketId", mMaketId);
        startActivity(intent);
    }

    private void onCheckStartDepositCdp() {
        if (!onCommonCheck()) return;

        if (cAvailable.compareTo(BigDecimal.ZERO) <= 0) {
            Toast.makeText(getBaseContext(), R.string.error_less_than_min_deposit, Toast.LENGTH_SHORT).show();
            return;
        }

        Intent intent = new Intent(this, DepositCdpActivity.class);
        intent.putExtra("denom", mMarketDenom);
        intent.putExtra("marketId", mMaketId);
        startActivity(intent);
    }

    private void onCheckStartWithdrawCdp() {
        //TODO add check logic!
        if (!onCommonCheck()) return;

        Intent intent = new Intent(this, WithdrawCdpActivity.class);
        intent.putExtra("denom", mMarketDenom);
        intent.putExtra("marketId", mMaketId);
        startActivity(intent);
    }

    private void onCheckStartDrawDebtCdp() {
        if (!onCommonCheck()) return;

        Intent intent = new Intent(this, DrawDebtActivity.class);
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

        if (mBaseChain.equals(BaseChain.KAVA_MAIN) || mBaseChain.equals(BaseChain.KAVA_TEST)) {
            if (WDp.getAvailableCoin(mBalances, COSMOS_KAVA).compareTo(BigDecimal.ONE) <= 0) {
                Toast.makeText(getBaseContext(), R.string.error_not_enough_budget, Toast.LENGTH_SHORT).show();
                return false;
            }
        }
        return true;
    }



    private int mTaskCount = 0;
    public void onFetchCdpInfo() {
        if (mBaseChain.equals(BaseChain.KAVA_MAIN)) {
            //not yet

        } else if (mBaseChain.equals(BaseChain.KAVA_TEST)) {
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
                mMyOwenCdp = (ResCdpOwnerStatus.Result)result.resultData;
                mTaskCount = mTaskCount + 1;
                new KavaCdpByDepositorTask(getBaseApplication(), this, BaseChain.getChain(mAccount.baseChain), mAccount.address, mMarketDenom).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR);
            }

        } else if (result.taskType == TASK_FETCH_KAVA_CDP_DEPOSIT) {
            if (result.isSuccess && result.resultData != null) {
                mMyDepositList = (ArrayList<ResCdpDepositStatus.Result>)result.resultData;
            }
        }

        if (mTaskCount == 0) {
            if (mCdpParam == null || mKavaTokenPrice == null) {
                WLog.w("ERROR");
                Toast.makeText(getBaseContext(), getString(R.string.str_network_error_title), Toast.LENGTH_SHORT).show();
                onBackPressed();
                return;
            }
            onUpdateView();
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }



}
